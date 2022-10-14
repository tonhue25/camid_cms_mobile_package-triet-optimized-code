package co.siten.myvtg.service;

import co.siten.myvtg.bean.*;
import co.siten.myvtg.dao.MyMetfoneBusinesDao;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.dto.InfoMsgDTO;
import co.siten.myvtg.dto.RequestDto;
import co.siten.myvtg.exception.CallApiCancelSubscriberException;
import co.siten.myvtg.exception.CallApiChangeAccountBalanceException;
import co.siten.myvtg.exception.CallApiDausoException;
import co.siten.myvtg.exception.SitenException;
import co.siten.myvtg.model.cmpos.*;
import co.siten.myvtg.model.cmpre.SubIsdnMb;
import co.siten.myvtg.model.cmpre.SubMb;
import co.siten.myvtg.model.data.VSelfcareDataToBuy;
import co.siten.myvtg.model.myvtg.*;
import co.siten.myvtg.model.payment.DebitSub;
import co.siten.myvtg.util.*;
import com.viettel.bccs.cm.common.util.pre.DateUtils;
import com.viettel.bccs.cm.database.DAO.pre.Webservice.Pr.ProvisioningV2;
import com.viettel.common.ViettelService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.transaction.annotation.Transactional;
import sun.net.www.protocol.http.HttpURLConnection;

/**
 * @author thomc
 */
@org.springframework.stereotype.Service("AccountService")
@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private Environment environment;
    private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
    private ExchangeChannel channel;

    //    @Autowired
//    AsyncTaskService asyncTaskService;
    @Autowired
    ProService proService;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    MyvtgService myvtgService;

    @Autowired
    CmpreService cmpreService;

    @Autowired
    DataService dataService;

    @Autowired
    CmposService cmposService;

    @Autowired
    ProductService productService;

    @Autowired
    BillingService billingService;

    @Autowired
    PrecallService precallService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    SmService smService;

    @Autowired
    ApiGwService apiGwService;
    @Autowired
    MyMetfoneBusinesDao myMetfoneBusinesDao;
    @Autowired
    ConfigUtils configUtils;
    @Autowired
    MyvtgMasterDataDao myvtgDao;

    @Qualifier("asyncLogService")
    LogService logService;

    private static final String IS_CHECK_SUB = "1";
    private static final String LAST_ACC_STATUS = "0";
    private static final int SERIAL_LENGTH = 40;
    public static DecimalFormat dfCurrency = new DecimalFormat("#.##");
    public static DecimalFormat df = new DecimalFormat("#,###,###");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final int PAGE_NUMBER = 10;
    private static final int NEW_NOTIFICATION_NUMBER = 3;

    @Override
    public void wsGetSubInfo(String isdn, int subType, ResponseSuccessBean bean) throws Exception {
        SubAccountInfoBean accBean = myvtgService.getSubByIsdn(isdn);
        if (accBean == null) {
            throw new SitenException("ISDN not found: " + isdn);
        }

        if (CommonUtil.subtractDays(CommonUtil.getCurrentTime(), accBean.getLastSyncTime()) <= 1) {
            // ST_02 <= 1 ngay
            if (bean != null) {
                bean.setWsResponse(accBean);
            }
            return;
        } else {
            // ST_03 > 1 ngày
            String custName = "";
            Date birthday = null;
            String gender = "";
            String productCode = "";
            switch (subType) {
                case Constants.SUBTYPE_TRATRUOC:
                    AllCustSubForSelfcareBean preSubMb = cmpreService.getCustSubForSelfcare(isdn);
                    if (preSubMb == null) {
                        throw new SitenException("wsGetSubInfo() not found with: " + isdn);
                    } else {
                        // Cập nhật custname
                        custName = preSubMb.getCustName();
                        birthday = preSubMb.getBirthDate();
                        gender = preSubMb.getGender();
                        productCode = preSubMb.getProductCode();
                    }

                    break;
                case Constants.SUBTYPE_TRASAU:
                    AllCustSubForSelfcareBean posSubMb = cmpreService.getCustSubForSelfcare(isdn);
                    if (posSubMb == null) {
                        throw new SitenException("wsGetSubInfo() not found with: " + isdn);
                    } else {
                        // Cập nhật custname
                        custName = posSubMb.getCustName();
                        birthday = posSubMb.getBirthDate();
                        gender = posSubMb.getGender();
                        productCode = posSubMb.getProductCode();
                    }
                    break;
                default:
                    break;
            }

            // ST_04 Name = Cust_NAME
            // custName
            // ST_05
            // UPDATE to Sub table
            Sub s = myvtgService.findByIsdn(isdn);
            if (!CommonUtil.isEmpty(custName)) {
                s.setName(custName);
            }
            if (!CommonUtil.isEmpty(productCode)) {
                s.setProductCode(productCode);
            }
            if (!CommonUtil.isEmpty(gender)) {
                s.setGender("Male".equalsIgnoreCase(gender) ? Constants.GENDER_MALE : Constants.GENDER_FEMALE);
            }
            if (birthday != null) {
                s.setBirthDate(birthday);
            }
            s.setLastSyncTime(CommonUtil.getCurrentTime());
            accBean.setBirthday(birthday);
            accBean.setName(custName);
            if (bean != null) {
                bean.setWsResponse(accBean);
            }
            myvtgService.updateSub(s);
        }
    }

    @Override
    public void wsGetPostageDetailInfo(Environment environment, String isdn, int subType, Date startTime, Date endTime,
            int postType, int size, int page, String order, ResponseSuccessBean bean) {

        if (subType == Constants.SUBTYPE_TRATRUOC) {
            switch (postType) {
                case Constants.POSTTYPE_CALL:
                    List<PostageDetailInfoBean> objCallList = precallService.getPostageDetailInfoCall(isdn, startTime,
                            endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objCallList);
                    break;
                case Constants.POSTTYPE_SMS:
                    List<PostageDetailInfoBean> objSmsList = precallService.getPostageDetailInfoSms(isdn, startTime,
                            endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objSmsList);
                    break;
                case Constants.POSTTYPE_OTHER:
                    List<PostageDetailInfoBean> objOtherList = precallService.getPostageDetailInfoOther(environment, isdn,
                            startTime, endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objOtherList);
                    break;
                // NamDH1 Add For VTC
                case Constants.POSTTYPE_DATA:
                    List<PostageDetailInfoBean> objDataList = precallService.getPostageDetailInfoData(environment, isdn,
                            startTime, endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objDataList);
                    break;
                case Constants.POSTTYPE_VAS:
                    List<PostageDetailInfoBean> objVasList = precallService.getPostageDetailInfoVas(environment, isdn,
                            startTime, endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objVasList);
                    break;
                default:
                    break;
            }
        } else {
            switch (postType) {
                case Constants.POSTTYPE_CALL:
                    List<PostageDetailInfoBean> objCallList = billingService.getPostageDetailInfoCall(isdn, startTime,
                            endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objCallList);
                    break;
                case Constants.POSTTYPE_SMS:
                    List<PostageDetailInfoBean> objSmsList = billingService.getPostageDetailInfoSms(isdn, startTime,
                            endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objSmsList);
                    break;
                case Constants.POSTTYPE_OTHER:
                    List<PostageDetailInfoBean> objOtherList = billingService.getPostageDetailInfoOther(isdn, startTime,
                            endTime, getCountryCode(), size, page, order);
                    bean.setWsResponse(objOtherList);
                    break;
                default:
                    break;
            }
        }

    }

    @Override
    public void wsGetSubAccountInfo(String isdn, int subType, ResponseSuccessBean bean, Object language) {
        Sub subBean = myvtgService.findByIsdn(isdn);
        if (subBean == null) {
            bean.setWsResponse(null);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setMessage("Sub not found by isdn: " + isdn);
            return;
        }
        String languageStr = Constants.DEFAULT_LANGUAGE;
        if (language != null) {
            languageStr = language.toString();
        } else {
            Object appPr = myvtgService.getAppParam(Constants.APP_PARAM_DEFAULT_LANGUAGE);
            if (appPr != null) {
                languageStr = appPr.toString();
            }
        }

        if (subType == Constants.SUBTYPE_TRATRUOC) {
            getPrePaidInfo(isdn, bean, subBean, languageStr);
        } else {
            getPostPaidInfo(isdn, bean, subBean, languageStr);
        }
    }

    private void getPostPaidInfo(String isdn, ResponseSuccessBean bean, Sub subBean, String language) {
        if (subBean == null) {
            bean.setWsResponse(null);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setMessage("Sub is not exist with isdn: " + isdn);
            return;
        }
        SubAccountInfoAcc2Bean acc = getPostPaidMoney(subBean);

        /*
         *
         * List<DataPackageBean> dataPackageNameAndExpired =
         * getDataPackageNameAndExpired(subBean, language, null);
         * acc.setDataPkgName(getDataPackageName(dataPackageNameAndExpired));
         * getPreOrPostPaidInfoExtra(subBean, acc);
         */
        DecimalFormat dfCurrency = new DecimalFormat("#,###.00");

//        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = getCountryCode() + isdn;
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
//        try {
//            channel = proService.getChannel();
//        } catch (Exception e) {
//            logger.error("error", e);
//        }

        try {
//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//
//            request.set("MSISDN", msisdn);

            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccConfig> accConfigList = myvtgService.getAllAccConfig();

                    SubAccountInfoAcc2Bean accInfo = new SubAccountInfoAcc2Bean();
                    List<ProvisionBalanceBean> balanceList = getBalanceList(response, accConfigList, -1);
                    accInfo.setDebPost(acc.getDebPost());
                    accInfo.setPrePost(acc.getPrePost());
                    // accInfo.setProAcc(getSumAccByType(balanceList,
                    // Constants.ACC_TYPE_PROMOTION));
                    accInfo.setDataVolume(getSumAccByType(balanceList, Constants.ACC_TYPE_DATA));
                    List<DataPackageBean> dataList = getDataPackageNameAndExpired(subBean, language, response);
                    accInfo.setDataPkgName(getDataPackageName(dataList));
                    // System.out.println("subBean.getProductCode()" +
                    // subBean.getProductCode());
                    accInfo.setName(subBean.getProductCode());
                    getPreOrPostPaidInfoExtra(subBean, accInfo);
                    bean.setWsResponse(accInfo);
                } else {
                    bean.setMessage(getResponseDes(response));
                    bean.setErrorCode(Constants.ERROR_CODE_0);
                    bean.setWsResponse(null);
                }
            } else {
                bean.setErrorCode(Constants.ERROR_CODE_0);
                bean.setWsResponse(null);
            }

//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("getPrePaidInfo(): ", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } finally {

        }

        // bean.setWsResponse(acc);
    }

    private SubAccountInfoAcc2Bean getPostPaidMoney(Sub subBean) {
        BigDecimal preCallPost = BigDecimal.ZERO;
        BigDecimal debPost = BigDecimal.ZERO;

        try {

            preCallPost = paymentService.callGetHotChargeSub(subBean.getSubId());
            logger.info("preCallPost" + paymentService.callGetHotChargeSub(subBean.getSubId()));

            if (preCallPost == null) {
                preCallPost = BigDecimal.ZERO;
            }
            preCallPost = preCallPost.setScale(2, RoundingMode.FLOOR);
        } catch (Exception e) {
            logger.error(e);
            logger.info(e.getMessage());
        }

        try {
            DebitSub debitSub = paymentService.getDevPostByIsdn(subBean);

            // System.out.println("namdh1:" +subBean.getProductCode() );
            if (debitSub != null) {
                debPost = debitSub.getStaOfCycle().subtract(debitSub.getPayment()).add(debitSub.getPromotion())
                        .add(debitSub.getDiscount());
                debPost = debPost.setScale(2, RoundingMode.FLOOR);
            }

            // System.out.println("debPost:" +debPost );
        } catch (Exception e) {
            logger.error(e);
            logger.info(e.getMessage());
        }

        /*
         * BigDecimal dataRemain = BigDecimal.ZERO;
         *
         * try { dataRemain = getPosPaidDataRemain(subBean);
         * System.out.println("dataRemain:" +dataRemain ); } catch (Exception e)
         * { logger.error(e); logger.info(e.getMessage()); }
         */
        SubAccountInfoAcc2Bean acc = new SubAccountInfoAcc2Bean();
        acc.setDebPost(debPost);
        acc.setPrePost(preCallPost);
        acc.setName(subBean.getProductCode());
        // acc.setDataVolume(dataRemain);
        return acc;
    }

    private String getDataPackageName(List<DataPackageBean> datalist) {
        if (CommonUtil.isEmpty(datalist)) {
            return "";
        }

        StringBuilder name = new StringBuilder();
        for (DataPackageBean b : datalist) {
            name.append(b.getName()).append(" ,");
        }

        return (name.substring(0, name.length() - 2));
    }

    private GetCallAccountDetailPreBean getPostPaidChargingDetail(Sub subBean) {
        BigDecimal preCallPost = BigDecimal.ZERO;
        BigDecimal debPreMonthPost = BigDecimal.ZERO;
        BigDecimal debPost = BigDecimal.ZERO;
        Exception ex = null;

        try {
            if (isMovitel()) {
                preCallPost = billingService.callGetHotChargeSub(subBean.getSubId());
            } else {
                preCallPost = paymentService.callGetHotChargeSub(subBean.getSubId());
            }
            if (preCallPost == null) {
                preCallPost = BigDecimal.ZERO;
            }
            preCallPost = preCallPost.setScale(2, RoundingMode.FLOOR);
        } catch (Exception e) {
            logger.error(e);

        }

        try {
            DebitSub debitSub = paymentService.getDevPostByIsdn(subBean);
            if (debitSub != null) {
                debPost = debitSub.getStaOfCycle().subtract(debitSub.getPayment()).add(debitSub.getPromotion())
                        .add(debitSub.getDiscount());
                debPreMonthPost = debitSub.getStaOfCycle();
            }
        } catch (Exception e) {
            logger.error(e);
        }

        GetCallAccountDetailPreBean acc = new GetCallAccountDetailPreBean();
        acc.setDebPost(debPost);
        acc.setPrePost(preCallPost);
        acc.setDebPreMonthPost(debPreMonthPost);
        return acc;
    }

    private AccConfig getAccConfigByCode(Integer type, List<AccConfig> accList) {
        for (AccConfig acc : accList) {
            if (acc.getAccCode().equals(type)) {
                return acc;
            }
        }
        return null;
    }

    private DataPricePlanConfig getAccConfigByPricePlan(Long pricePlanId, List<DataPricePlanConfig> accList) {
        for (DataPricePlanConfig acc : accList) {
            if (acc.getPricePlan().equals(pricePlanId.intValue())) {
                return acc;
            }
        }
        return null;
    }

    private void getPrePaidInfo(String isdn, ResponseSuccessBean bean, Sub subBean, String language) {
        // check user exist
        Sub sub = myvtgService.findByIsdn(isdn);
        if (sub == null) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setWsResponse(null);
            return;
        }

//        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = getCountryCode() + isdn;
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
//        
//        try {
//            channel = proService.getChannel();
//        } catch (Exception e) {
//            logger.error("error", e);
//        }

        try {

//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//
//            request.set("MSISDN", msisdn);
            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccConfig> accConfigList = myvtgService.getAllAccConfig();

                    SubAccountInfoAccBean accInfo = new SubAccountInfoAccBean();
                    List<ProvisionBalanceBean> balanceList = getBalanceList(response, accConfigList, -1);
                    accInfo.setMainAcc(getSumAccByType(balanceList, Constants.ACC_TYPE_MAIN));
                    accInfo.setProAcc(getSumAccByType(balanceList, Constants.ACC_TYPE_PROMOTION));
                    accInfo.setDataVolume(getSumAccByType(balanceList, Constants.ACC_TYPE_DATA));
                    List<DataPackageBean> dataList = getDataPackageNameAndExpired(sub, language, response);
                    accInfo.setDataPkgName(getDataPackageName(dataList));
                    accInfo.setName(subBean.getProductCode());
                    getPreOrPostPaidInfoExtra(sub, accInfo);
                    bean.setWsResponse(accInfo);
                } else {
                    bean.setMessage(getResponseDes(response));
                    bean.setErrorCode(Constants.ERROR_CODE_0);
                    bean.setWsResponse(null);
                }
            } else {
                bean.setErrorCode(Constants.ERROR_CODE_0);
                bean.setWsResponse(null);
            }

//            logger.info("Request provisioning:\n" + request);
//            logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("getPrePaidInfo(): ", e);
//            logger.info("Request provisioning:\n" + request);
//            logger.info("Response provisioning:\n" + response);
        } finally {

        }
    }

    private void getPreOrPostPaidInfoExtra(Sub sub, Object accInfo) {
        String marketName = environment.getProperty(Constants.MARKET_NAME_CONFIG);
        if (marketName != null && marketName.equalsIgnoreCase(Constants.MARKET_NAME_UNITEL)) {
            String netSimPackageList = (String) myvtgService.getAppParam(Constants.NET_SIM_PACKAGE_LIST);
            if (netSimPackageList.contains(";" + sub.getProductCode() + ";")) {
                if (accInfo instanceof SubAccountInfoAccBean) {
                    ((SubAccountInfoAccBean) accInfo).setDataPkgName(sub.getProductCode());
                } else if (accInfo instanceof SubAccountInfoAcc2Bean) {
                    ((SubAccountInfoAcc2Bean) accInfo).setDataPkgName(sub.getProductCode());
                }
            }
        }

        if (marketName != null && marketName.equalsIgnoreCase(Constants.MARKET_NAME_METFONE)) {
            String productName = sub.getProductName();
            if (accInfo instanceof SubAccountInfoAccBean) {
                if (productName == null || !productName.isEmpty()) {
                    if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
                        productName = cmpreService.mfGetProductNameByProductCode(sub.getProductCode());
                        sub.setProductName(productName);
                        myvtgService.updateSub(sub);
                    } else if (sub.getSubType() == Constants.SUBTYPE_TRASAU) {
                        productName = cmposService.mfGetProductNameByProductCode(sub.getProductCode());
                        sub.setProductName(productName);
                        myvtgService.updateSub(sub);
                    }
                }
                ((SubAccountInfoAccBean) accInfo).setName(sub.getProductCode());
            } else if (accInfo instanceof SubAccountInfoAcc2Bean) {
                ((SubAccountInfoAcc2Bean) accInfo).setName(sub.getProductCode());
            }
        }
    }

    private void getPrePaidChargingDetail(String isdn, int subType, ResponseSuccessBean bean, Sub subBean) {

//        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + isdn;
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
//        try {
//            channel = proService.getChannel();
//        } catch (Exception e) {
//            logger.error("getPrePaidChargingDetail(): getChannel() error - " + e.getMessage(), e);
//            bean.setWsResponse(null);
//            bean.setErrorCode(Constants.ERROR_CODE_0);
//            bean.setMessage("Exception:" + e.getMessage());
//        }

        try {
//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);

            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccConfig> accConfigList = myvtgService.getAllAccConfig();

                    ProvisionCallBean mainAcc = new ProvisionCallBean();
                    List<ProvisionBalanceBean> balanceList = getBalanceList(response, accConfigList,
                            Constants.ACC_TYPE_MAIN);
                    mainAcc.setMoney(getSumAccByType(balanceList, Constants.ACC_TYPE_MAIN));
                    ProvisionBalanceBean maxExpiredBalance = getMaxExpiredBalance(balanceList);
                    mainAcc.setExpiredDate(maxExpiredBalance == null ? null : maxExpiredBalance.getExpireTime());

                    ProvisionCallBean promAcc = new ProvisionCallBean();
                    List<ProvisionBalanceBean> balancePromList = getBalanceList(response, accConfigList,
                            Constants.ACC_TYPE_PROMOTION);
                    promAcc.setMoney(getSumAccByType(balancePromList, Constants.ACC_TYPE_PROMOTION));
                    maxExpiredBalance = getMaxExpiredBalance(balancePromList);
                    promAcc.setExpiredDate(maxExpiredBalance == null ? null : maxExpiredBalance.getExpireTime());

                    List<ProvisionOtherBean> otherList = new Vector<>();

                    for (Integer accType : getListAccType(response, accConfigList)) {
                        if (accType > 3) {
                            ProvisionOtherBean other = new ProvisionOtherBean();
                            List<ProvisionBalanceBean> beanList = getBalanceList(response, accConfigList, accType);
                            ProvisionBalanceBean balancebean = getMaxExpiredBalance(beanList);

                            BigDecimal money = getSumAccByType(beanList, accType);
                            if (balancebean != null) {
                                other.setName(balancebean.getBalanceName());
                                other.setExpiredDate(balancebean.getExpireTime());
                                other.setValue(money == null ? ("0 " + balancebean.getUnitLabel())
                                        : (money.toString() + " " + balancebean.getUnitLabel()));
                            }
                            otherList.add(other);
                        }

                    }
                    CallAccountDetailBean r = new CallAccountDetailBean();
                    r.setMainAcc(mainAcc);
                    r.setPromAcc(promAcc);
                    r.setOthers(otherList);

                    bean.setWsResponse(r);
                }
            }

//            logger.info("Request provisioning:\n" + request);
            // logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("getPrePaidChargingDetail(isdn = " + isdn + ", subType = " + subType + ")", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);

            bean.setWsResponse(null);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setMessage("Exception: " + e.getMessage());
        }
    }

    private void getDataAccountDetail(String isdn, ResponseSuccessBean bean, String language) {
        // check user exist
        Sub sub = myvtgService.findByIsdn(isdn);
        if (sub == null) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setUserMsg("Sub " + isdn + " is not exist.");
            return;
        }

        if ((sub.getSubType() == Constants.SUBTYPE_TRATRUOC) || isMovitel()) {
            getPrePaidDataInfo(sub, bean, language);
        } else {
            DataAccountDetailBean dataBean = new DataAccountDetailBean();
            List<DataPackageBean> datalist = getDataPackageNameAndExpired(sub, language, null);
            BigDecimal dataVolume = getPosPaidDataRemain(sub);
            List<String> listRegisteredServiceCodes = cmposService.getRegisteredServiceCodes(sub.getSubId());
            List<Object[]> dataRemainList = new LinkedList<>();

            try {
                dataRemainList = paymentService.getListDataRemainForPostPaidMetfone(sub.getSubId(),
                        listRegisteredServiceCodes);
            } catch (Exception e) {
                logger.error("getListDataRemainForPostPaid", e);
            }

            dataBean.setTotalVolume(dataVolume);

            List<DataPakageToBuyBean> toBuyList = new LinkedList<>();
            if (!CommonUtil.isEmpty(datalist)) {
                dataBean.setMainPackage(datalist.get(0));
                dataBean.setTotalVolumeStr(datalist.get(0).getVolumeStr());
                dataBean.setExpiredDate(datalist.get(0).getExpiredDate());
                dataBean.setExpiredDateStr(datalist.get(0).getExpiredDateStr());
                for (DataPackageBean b : datalist) {
                    b.setPakagesToBuy(getPackagesToBuy(b));

                    // Nếu chỉ có 1 gói thì remain chính = tổng số luôn
                    if (datalist.size() == 1) {
                        b.setVolume(dataVolume);
                    } else if (!CommonUtil.isEmpty(dataRemainList)) {
                        // Nếu có nhiều gói thì set lại remain của từng gói
                        b.setVolume(getVolumeRemainByCode(dataRemainList, b.getCode()));
                    }
                }
            }
            dataBean.setPakagesToBuy(toBuyList);
            dataBean.setDataPackages(datalist);

            bean.setWsResponse(dataBean);
        }
    }

    private BigDecimal getVolumeRemainByCode(List<Object[]> dataRemainList, String code) {
        BigDecimal remain = BigDecimal.ZERO;
        for (Object[] obj : dataRemainList) {
            if (code.equalsIgnoreCase(obj[0].toString())) {
                remain = ((BigDecimal) obj[1]).setScale(0, RoundingMode.CEILING);
            }
        }

        return remain;
    }

    private Integer getAccountCodeByPackageCode(String packageCode, List<ProvisionPricePlanBean> dataList) {
        for (ProvisionPricePlanBean balance : dataList) {
            if (balance.getConfig() != null && balance.getConfig().getPackageCode().equalsIgnoreCase(packageCode)) {
                return balance.getConfig().getAccCode();
            }
        }
        return 0;
    }

    private void getPrePaidDataInfo(Sub sub, ResponseSuccessBean bean, String language) {
//        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + sub.getIsdn();
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
//        try {
//            channel = proService.getChannel();
//        } catch (Exception e) {
//            logger.error("error", e);
//        }

        try {
//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);

            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccConfig> accConfigList = myvtgService.getAllAccConfig();
                    String pricePlanIdKey = environment.getRequiredProperty("balanceIdKey");

                    String ids = ((String) response.get(pricePlanIdKey));

                    List<DataPricePlanConfig> ppConfigList = myvtgService.getAllPricePlanConfig(sub.getSimType());
                    DataAccountDetailBean dataBean = new DataAccountDetailBean();
                    List<ProvisionBalanceBean> dataList = getBalanceList(response, accConfigList,
                            Constants.ACC_TYPE_DATA);

                    dataBean.setTotalVolume(getSumAccByType(dataList, Constants.ACC_TYPE_DATA));

                    // try to get data package name
                    List<ProvisionPricePlanBean> provisionPricePlanBeans = getPricePlanList(response, sub.getSimType());
                    // ProvisionPricePlanBean pp =
                    // getMaxExpiredPricePlan(provisionPricePlanBeans);
                    // try to get more info
                    String productCode = sub.getProductCode();
                    if (isUnitelMarket() && isLTSub(productCode)) {
                        // try to update product name of LT packages

                    }
                    List<DataPackageBean> dataPackageNameAndExpired = getDataPackageNameAndExpired(sub, language,
                            response);

                    List<DataPakageToBuyBean> toBuyList = new LinkedList<>();
                    List<DataPackageBean> resultList = new LinkedList<DataPackageBean>();

                    if (!CommonUtil.isEmpty(dataPackageNameAndExpired)) {
                        dataBean.setMainPackage(dataPackageNameAndExpired.get(0));
                        dataBean.setTotalVolumeStr(dataPackageNameAndExpired.get(0).getVolumeStr());
                        dataBean.setExpiredDate(dataPackageNameAndExpired.get(0).getExpiredDate());
                        dataBean.setExpiredDateStr(dataPackageNameAndExpired.get(0).getExpiredDateStr());
                        for (DataPackageBean b : dataPackageNameAndExpired) {
                            b.setPakagesToBuy(getPackagesToBuy(b));
                        }
                    }
                    dataBean.setPakagesToBuy(toBuyList);
                    if (!CommonUtil.isEmpty(dataPackageNameAndExpired)) {
                        for (DataPackageBean b : dataPackageNameAndExpired) {
                            int accountCode = getAccountCodeByPackageCode(b.getCode(), provisionPricePlanBeans);
                            b.setVolume(getSumAccByAccCode(dataList, accountCode));
                            b.setAccCode(accountCode);
                        }

                        for (DataPackageBean b : dataPackageNameAndExpired) {
                            DataPackageBean b1 = isExistDataPackage(resultList, b.getAccCode());
                            if (b1 == null) {
                                resultList.add(b);
                            } else {
                                b1.setName(b1.getName() + ", " + b.getName());
                            }
                        }

                    }

                    // LT package volumn and expire get from Pro
                    if (isUnitelMarket() && isLTSub(productCode) && !CommonUtil.isEmpty(dataPackageNameAndExpired)) {
                        // Date expiredTime =
                        // getExpiredDateOfBiggestDataBalance(dataList);
                        // dataPackageNameAndExpired.get(0).setExpiredDate(expiredTime);
                        dataPackageNameAndExpired.get(0).setVolume(dataBean.getTotalVolume());
                    }

                    dataBean.setDataPackages(resultList);
                    // Get data levels to buy

                    bean.setWsResponse(dataBean);
                }
            }

//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("Error get data promotion", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } finally {

        }
    }

    private DataPackageBean isExistDataPackage(List<DataPackageBean> resultList, int accCode) {
        for (DataPackageBean b : resultList) {
            if (b.getAccCode().equals(accCode)) {
                return b;
            }
        }
        return null;
    }

    private List<DataPakageToBuyBean> getPackagesToBuy(DataPackageBean dataPackage) {
        List<DataPakageToBuyBean> bList = new Vector<>();
        VSelfcareDataToBuy toBuy = dataService.getVSelfcareDataToBuyByName(dataPackage.getName());
        String delim = "|";
        String regex = "(?<!\\\\)" + Pattern.quote(delim);
        if (toBuy != null && toBuy.getUnit() != null) {
            String[] toBuyArr = toBuy.getUnit().split(regex);
            for (int i = 0; i < toBuyArr.length; i++) {
                DataPakageToBuyBean d = new DataPakageToBuyBean();
                d.setPrice(new BigDecimal(toBuyArr[i].split(";")[0]));
                d.setVolume(new BigDecimal(toBuyArr[i].split(";")[1]));
                bList.add(d);
            }
        }

        if (bList.isEmpty()) {
            // try to create re-register current package if it's expired.
            Date expiredDate = dataPackage.getExpiredDate();
            Long now = new Date().getTime();
            BigDecimal dataVol = dataPackage.getVolume();
            if ((dataVol != null && dataVol.compareTo(BigDecimal.TEN) < 0)
                    || (expiredDate != null && expiredDate.getTime() < now) || expiredDate == null) {
                bList = new Vector<>();
                bList.add(new DataPakageToBuyBean(dataPackage.getVolume(), dataPackage.getPrice()));
            }
        }

        return bList;
    }

    /**
     * This method is using for 2 API: wsGetSubAccountInfo and
     * wsGetDataAccountDetail
     *
     * @param sub
     *
     * @return <package_name, data volume, exipred date, data_free, money_fee,
     * vas_code>
     */
    private BigDecimal getPosPaidDataRemain(Sub sub) {
        BigDecimal value = BigDecimal.ZERO;
        try {
            if (isMetfoneMarket()) {
                // Dung lượng data còn lại? bccs_rating.rt_account_benefit
                // List<String> listRegisteredServiceCodes =
                // cmposService.getQueries().getRegisteredServiceCodes(sub.getSubId());
                String listRegisteredServiceCodes = cmposService.getListRegisteredServiceCodes(sub.getSubId());

                value = paymentService.getDataRemainForPostPaid(sub.getSubId(), listRegisteredServiceCodes);
            } else if (isUnitelMarket()) {
                value = getPostPaidDataRemainForUnitel(sub);

            }
        } catch (Exception ex) {

        }
        return value;

    }

    private BigDecimal getPostPaidDataRemainForUnitel(Sub sub) {
        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        BigDecimal value = BigDecimal.ZERO;
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + sub.getIsdn();
        String processCode = environment.getRequiredProperty("pro_processcode_remaining_data");

        try {
            channel = proService.getChannel();
        } catch (Exception e) {
            logger.error("error", e);
        }

        try {
            request.setMessageType("1900");
            request.setProcessCode(processCode);
            request.set("MSISDN", msisdn);

            // send COMMAND, dung ham sendAll cua lib
            response = (ViettelService) channel.send(request);

            if (response != null) {
                String sVal = (String) response.get(environment.getRequiredProperty("value"));
                if (sVal != null && !sVal.isEmpty()) {
                    value = new BigDecimal(sVal).divide(
                            new BigDecimal(environment.getRequiredProperty("pro_data_rate")), 0, RoundingMode.FLOOR);
                }
            }
            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);

        } catch (Exception e) {
            logger.error("Error get data promotion", e);
            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        }
        return value;
    }

    private Tuple2<String, Date> getTuple2ByCode(List<Tuple2<String, Date>> tupleList, String code) {
        Tuple2<String, Date> r = null;
        if (tupleList == null) {
            return null;
        }
        for (Tuple2<String, Date> t : tupleList) {
            if (t.getField0().equalsIgnoreCase(code)) {
                return t;
            }
        }
        return r;
    }

    /**
     * @param sub
     *
     * @return <package_name, expired, data_free, money_fee, vas_code>
     */
    private List<DataPackageBean> getDataPackageNameAndExpired(Sub sub, String language, ViettelService response) {
        logger.info("getDataPackageNameAndExpired: called");
        List<DataPackageBean> result = new LinkedList<>();
        result = new LinkedList<>();
        List<Tuple2<String, Date>> relProCodeList;
        if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
            relProCodeList = cmpreService.getRegisteredRelProductCode(sub.getSubId());
        } else {
            relProCodeList = cmposService.getRegisteredRelProductCode(sub.getSubId());
        }

        List<String> codeList = new LinkedList<>();
        // if (isUnitelMarket() && isLTSub(sub.getProductCode())) {
        // co.siten.myvtg.model.cmpre.SubMb subMb =
        // cmpreService.getSubMbByIsdnAndStatus(sub.getIsdn(),
        // Constants.SUB_MB_STATUS_ACTIVE);
        // if (subMb != null) {
        // sub.setProductCode(subMb.getProductCode());
        // sub.setLtExpireDate(subMb.getChangeDatetime());
        // }
        // codeList.add(sub.getProductCode());
        // }

        if (!CommonUtil.isEmpty(relProCodeList)) {
            for (Tuple2<String, Date> relProCode : relProCodeList) {
                codeList.add(relProCode.getField0());
            }
        }

        List<SubServiceA> ssList = myvtgService.getRegisteredPackageData(codeList, language);

        if (CommonUtil.isEmpty(ssList)) {
            return null;
        } else {
            for (SubServiceA ss : ssList) {
                String type = ss.getType();
                // type=nD: là n ngày, nM: là n tháng, nH: là n giờ; nH-mH
                String regexD = "\\d+D";
                String regexM = "\\d+M";
                String regexH = "\\d+H";

                Tuple2<String, Date> tuple = getTuple2ByCode(relProCodeList, ss.getCode());
                String vasCode = ss.getCode();
                String expiredDateStr = null;
                Date expiredDate = null;
                String volumeStr = null;

                Calendar calendar = Calendar.getInstance();
                if (!CommonUtil.isEmpty(type)) {
                    if (isUnitelMarket() && isLTSub(sub.getProductCode())) {
                        calendar.setTime(sub.getLtExpireDate());
                    } else if (tuple != null) {
                        calendar.setTime(tuple.getField1());
                    }

                    if (type.matches(regexD)) {
                        calendar.add(Calendar.DATE, Integer.parseInt(type.split("D")[0]));
                    } else if (type.matches(regexM)) {
                        calendar.add(Calendar.MONTH, Integer.parseInt(type.split("M")[0]));
                    } else if (type.matches(regexH)) {
                        calendar.add(Calendar.HOUR, Integer.parseInt(type.split("H")[0]));
                    } else {
                        expiredDateStr = type;
                        if (ss.getVolume().equals(BigDecimal.ZERO)) {
                            volumeStr = Constants.VOLUMN_STR_UNLIMIT;
                        }
                    }
                }
                if (expiredDateStr == null) {
                    expiredDate = calendar.getTime();
                }

                BigDecimal dataFree = ss.getVolume();
                BigDecimal moneyFree = ss.getPrice();
                result.add(new DataPackageBean(vasCode, vasCode, dataFree, moneyFree, expiredDate, expiredDateStr,
                        volumeStr));
            }
            return result;
        }
    }

    private Date getExpiredDateOfBiggestDataBalance(List<ProvisionBalanceBean> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return null;
        }
        ProvisionBalanceBean maxBalance = null;
        for (ProvisionBalanceBean balance : dataList) {
            if (maxBalance == null || maxBalance.getBalanceValue().compareTo(balance.getBalanceValue()) < 0) {
                maxBalance = balance;
            }
        }
        if (maxBalance != null) {
            return maxBalance.getExpireTime();
        } else {
            return null;
        }
    }

    private List<Integer> getListAccType(ViettelService response, List<AccConfig> accConfigList) {
        List<Integer> accTypeList = new ArrayList<>();
        String[] typeIds = getItemList(response, "balanceIdKey", "&");

        for (int i = 0; i < typeIds.length; i++) {
            int typeId = Integer.parseInt(typeIds[i]);
            AccConfig config = getAccConfigByCode(typeId, accConfigList);
            if (config != null && !accTypeList.contains(config.getAccType())) {
                accTypeList.add(config.getAccType());
            }
        }
        return accTypeList;
    }

    private String[] getItemList(ViettelService response, String key, String splitChar) {
        String[] result = {};
        String keyStr = environment.getRequiredProperty(key);

        if (keyStr == null || keyStr.isEmpty()) {
            logger.error("Key " + key + " is not exist.");
            return result;
        }

        String valueStr = (String) response.get(keyStr);
        if (valueStr != null && !valueStr.isEmpty()) {
            result = valueStr.split(splitChar);
        }

        return result;
    }

    private List<ProvisionBalanceBean> getBalanceList(ViettelService response, List<AccConfig> accConfigList,
            int type) {
        List<ProvisionBalanceBean> rList = new ArrayList<>();
        String dateformat = environment.getRequiredProperty("pro_date_format");

        String[] typeIds = getItemList(response, "balanceIdKey", "&");
        String[] balances = getItemList(response, "balanceValueKey", "&");
        String[] expireTimes = getItemList(response, "expireTimeKey", "&");

        for (int i = 0; i < typeIds.length; i++) {
            int typeId = Integer.parseInt(typeIds[i]);
            AccConfig config = getAccConfigByCode(typeId, accConfigList);
            if (config != null && config.getAccType() != null && (config.getAccType().equals(type) || type == -1)) {
                BigDecimal value;
                if (config.getAccType().equals(Constants.ACC_TYPE_DATA)
                        || config.getAccType().equals(Constants.ACC_TYPE_DATAPROMOTION)) {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 0, RoundingMode.FLOOR);
                } else {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 2, RoundingMode.FLOOR);
                }

                ProvisionBalanceBean b = new ProvisionBalanceBean();
                b.setBalanceId(typeId);
                if (expireTimes.length > i) {
                    b.setExpireTime(AppUtil.getDateFromString(expireTimes[i], dateformat));
                }
                b.setBalanceName(config.getAccName());
                b.setBalanceValue(value);
                b.setAccType(config.getAccType());
                b.setUnitLabel(config.getUnitLabel());
                rList.add(b);
            }

        }
        return rList;
    }

    private List<AccountDetailValueBean> getAccountDetailAccList(ViettelService response, List<AccConfig> accConfigList,
            String titleDate) {
        List<AccountDetailValueBean> rList = new ArrayList<>();
        String dateformat = environment.getRequiredProperty("pro_date_format");

        String[] typeIds = getItemList(response, "balanceIdKey", "&");
        String[] balances = getItemList(response, "balanceValueKey", "&");
        String[] expireTimes = getItemList(response, "expireTimeKey", "&");

        DecimalFormat dfCurrency = new DecimalFormat("#,###.00");
        // DecimalFormat dfCurrency = new DecimalFormat("#,###");
        DecimalFormat dfData = new DecimalFormat("#,###");
        BigDecimal minVal = new BigDecimal(1.0);
        SimpleDateFormat dateFormat = getDateFormatForAccountDetail();
        for (int i = 0; i < typeIds.length; i++) {
            int typeId = Integer.parseInt(typeIds[i]);
            AccConfig config = getAccConfigByCode(typeId, accConfigList);
            if (config != null && config.getAccType() != null) {
                BigDecimal value;
                String valueStr;

                if (config.getAccType().equals(Constants.ACC_TYPE_DATA)
                        || config.getAccType().equals(Constants.ACC_TYPE_PROMOTION)
                        || config.getAccType().equals(Constants.ACC_TYPE_DATAPROMOTION)) {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 0, RoundingMode.FLOOR);
                    valueStr = dfData.format(value);

                } else {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 2, RoundingMode.FLOOR);

                    valueStr = dfCurrency.format(value);
                    if (value.compareTo(minVal) == -1) {
                        valueStr = "0" + dfCurrency.format(value);
                    }
                    if (value.compareTo(BigDecimal.ZERO) < 0) {
                        valueStr = "0";
                    }
                }

                AccountDetailValueBean b = new AccountDetailValueBean();
                b.setOrder(config.getAccOrder());

                if (expireTimes.length > i) {
                    b.setExp(titleDate + dateFormat.format(AppUtil.getDateFromString(expireTimes[i], dateformat)));
                }

                b.setTitle(config.getAccName());

                if ("0".equals(valueStr)) {
                    b.setValue(null);
                } else {
                    b.setValue(valueStr + " " + config.getUnitLabel());
                }

                rList.add(b);

                for (AccountDetailValueBean obj : rList) {
                    if (obj.getValue() != null) {

                    } else {
                        rList.remove(obj);
                    }
                }
            }

        }
        if (!CommonUtil.isEmpty(rList)) {
            Collections.sort(rList, new Comparator<AccountDetailValueBean>() {
                @Override
                public int compare(AccountDetailValueBean a, AccountDetailValueBean b) {
                    return a.getOrder().compareTo(b.getOrder());
                }
            });
        }

        return rList;
    }

    private List<AccountDetailValueBean> getAccountDetailAccListSumByAccountType(ViettelService response, List<AccConfig> accConfigList,
            String titleDate) {
        List<AccountDetailValueBean> rList = new ArrayList<>();
        String dateformat = environment.getRequiredProperty("pro_date_format");

        String[] typeIds = getItemList(response, "balanceIdKey", "&");
        String[] balances = getItemList(response, "balanceValueKey", "&");
        String[] expireTimes = getItemList(response, "expireTimeKey", "&");

        DecimalFormat dfCurrency = new DecimalFormat("#,###.00");
        // DecimalFormat dfCurrency = new DecimalFormat("#,###");
        DecimalFormat dfData = new DecimalFormat("#,###");
        BigDecimal minVal = new BigDecimal(1.0);
        SimpleDateFormat dateFormat = getDateFormatForAccountDetail();
        for (int i = 0; i < typeIds.length; i++) {
            int typeId = Integer.parseInt(typeIds[i]);
            AccConfig config = getAccConfigByCode(typeId, accConfigList);
            if (config != null && config.getAccType() != null) {
                BigDecimal value;
                String valueStr;
                BigDecimal relValue;

                if (config.getAccType().equals(Constants.ACC_TYPE_DATA)
                        || config.getAccType().equals(Constants.ACC_TYPE_DATAPROMOTION)
                        || config.getAccType().equals(Constants.ACC_TYPE_CALL_PROMOTION)
                        || config.getAccType().equals(Constants.ACC_TYPE_SMS_ROMOTION)) {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 0, RoundingMode.FLOOR);
                    valueStr = dfData.format(value);

                    relValue = new BigDecimal(balances[i]).divide(config.getUnit(), 0, RoundingMode.FLOOR);

                } else {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 2, RoundingMode.FLOOR);

                    relValue = new BigDecimal(balances[i]).divide(config.getUnit(), 2, RoundingMode.FLOOR);

                    valueStr = dfCurrency.format(value);
                    if (value.compareTo(minVal) == -1) {
                        valueStr = "0" + dfCurrency.format(value);
                    }
                    if (value.compareTo(BigDecimal.ZERO) < 0) {
                        valueStr = "0";
                    }
                }

                AccountDetailValueBean b = new AccountDetailValueBean();
                b.setRealValue(relValue);
                b.setAccType(config.getAccType());
                b.setBalanceId(typeId);
                b.setOrder(config.getAccOrder());
                b.setUnitLabel(config.getUnitLabel());

                if (expireTimes.length > i) {
                    b.setExp(titleDate + dateFormat.format(AppUtil.getDateFromString(expireTimes[i], dateformat)));

                    b.setExpireTime(AppUtil.getDateFromString(expireTimes[i], dateformat));
                }

                b.setTitle(config.getAccName());

                if ("0".equals(valueStr)) {
                    b.setValue(null);
                } else {
                    b.setValue(valueStr + " " + config.getUnitLabel());
                }
                // neu tra tri la 0 van hien thi $0.00
                //b.setValue(valueStr + " " + config.getUnitLabel());
                rList.add(b);

//				for (AccountDetailValueBean obj : rList) {
//					if (obj.getRealValue().compareTo(BigDecimal.ZERO) > 0) {
//
//					} else {
//						rList.remove(obj);
//					}                                                                                
//                                        
//				}
            }
        }

        List<AccountDetailValueBean> realLstAfterSum = new ArrayList<>();

        if (!CommonUtil.isEmpty(rList)) {

            AccountDetailValueBean accPromotion = new AccountDetailValueBean();
            AccountDetailValueBean dataPromotion = new AccountDetailValueBean();
            AccountDetailValueBean exchBalance = new AccountDetailValueBean();
            BigDecimal sum = BigDecimal.ZERO;
            BigDecimal sumData = BigDecimal.ZERO;
            BigDecimal exchData = BigDecimal.ZERO;

            Date expireTimeAccPro = new Date();
            Date expireTimeData = new Date();
            Date expireTimeAccExch = new Date();

            for (AccountDetailValueBean obj : rList) {

                if (obj.getAccType() == Constants.ACC_TYPE_PROMOTION) {
                    sum = sum.add(obj.getRealValue());
                    accPromotion.setAccType(obj.getAccType());
                    accPromotion.setOrder(obj.getOrder());
                    accPromotion.setTitle(obj.getTitle());
                    accPromotion.setUnitLabel(obj.getUnitLabel());
                    accPromotion.setExp(obj.getExp());
                    accPromotion.setBalanceId(obj.getBalanceId());

                    expireTimeAccPro = expireTimeAccPro != null && (expireTimeAccPro.before(obj.getExpireTime())) ? obj.getExpireTime() : expireTimeAccPro;

                } else if (obj.getAccType() == Constants.ACC_TYPE_DATA) {
                    sumData = sumData.add(obj.getRealValue());
                    dataPromotion.setAccType(obj.getAccType());
                    dataPromotion.setOrder(obj.getOrder());
                    dataPromotion.setTitle(obj.getTitle());
                    dataPromotion.setUnitLabel(obj.getUnitLabel());
                    dataPromotion.setExp(obj.getExp());
                    accPromotion.setBalanceId(obj.getBalanceId());
                    expireTimeData = expireTimeData != null && (expireTimeData.before(obj.getExpireTime())) ? obj.getExpireTime() : expireTimeData;
                } else if (obj.getAccType() == Constants.ACC_TYPE_BACLANCE_EXCHANGE) {
                    exchData = exchData.add(obj.getRealValue());
                    exchBalance.setAccType(obj.getAccType());
                    exchBalance.setOrder(obj.getOrder());
                    exchBalance.setTitle(obj.getTitle());
                    exchBalance.setUnitLabel(obj.getUnitLabel());
                    exchBalance.setExp(obj.getExp());
                    exchBalance.setBalanceId(obj.getBalanceId());

                    expireTimeAccExch = expireTimeAccExch != null && (expireTimeAccExch.before(obj.getExpireTime())) ? obj.getExpireTime() : expireTimeAccExch;
                } else if (obj.getTitle() != null && obj.getValue() != null) {
                    realLstAfterSum.add(obj);
                }
            }

            accPromotion.setRealValue(sum);
            // String valueStr = dfCurrency.format(sum) + " " + accPromotion.getUnitLabel();
            String valueStr = sum + " " + accPromotion.getUnitLabel();
            accPromotion.setValue(valueStr);
            accPromotion.setExp(titleDate + dateFormat.format(expireTimeAccPro));

            if (accPromotion.getTitle() != null && accPromotion.getValue() != null) {
                realLstAfterSum.add(accPromotion);
            }

            dataPromotion.setRealValue(sumData);
            //String valueStrData = dfCurrency.format(sumData) + " " + dataPromotion.getUnitLabel();
            String valueStrData = sumData + " " + dataPromotion.getUnitLabel();
            dataPromotion.setValue(valueStrData);

            dataPromotion.setExp(titleDate + dateFormat.format(expireTimeData));
            if (dataPromotion.getTitle() != null && dataPromotion.getValue() != null) {
                realLstAfterSum.add(dataPromotion);
            }

            exchBalance.setRealValue(sum);
            //String valueStrExch = dfCurrency.format(exchData) + " " + exchBalance.getUnitLabel();
            String valueStrExch = exchData + " " + exchBalance.getUnitLabel();
            exchBalance.setValue(valueStrExch);
            exchBalance.setExp(titleDate + dateFormat.format(expireTimeAccExch));
            if (exchBalance.getTitle() != null && exchBalance.getValue() != null) {
                realLstAfterSum.add(exchBalance);
            }

        }

//            for (AccountDetailValueBean obj : realLstAfterSum) {
//                if (obj.getRealValue().compareTo(BigDecimal.ZERO) == 0) {
//                    realLstAfterSum.remove(obj);
//                }
//            } 
//				}
        if (!CommonUtil.isEmpty(realLstAfterSum)) {
            Collections.sort(realLstAfterSum, new Comparator<AccountDetailValueBean>() {
                @Override
                public int compare(AccountDetailValueBean a, AccountDetailValueBean b) {
                    return a.getOrder().compareTo(b.getOrder());
                }
            });
        }

        return realLstAfterSum;
    }

    private List<AccountDetailValueBean> getAccountDetailAccListSumByAccountExchangeType(ViettelService response, List<AccConfig> accConfigList,
            String titleDate) {
        List<AccountDetailValueBean> rList = new ArrayList<>();
        String dateformat = environment.getRequiredProperty("pro_date_format");

        String[] typeIds = getItemList(response, "balanceIdKey", "&");
        String[] balances = getItemList(response, "balanceValueKey", "&");
        String[] expireTimes = getItemList(response, "expireTimeKey", "&");

        DecimalFormat dfCurrency = new DecimalFormat("#,###.00");
        // DecimalFormat dfCurrency = new DecimalFormat("#,###");
        DecimalFormat dfData = new DecimalFormat("#,###");
        BigDecimal minVal = new BigDecimal(1.0);
        SimpleDateFormat dateFormat = getDateFormatForAccountDetail();
        for (int i = 0; i < typeIds.length; i++) {
            int typeId = Integer.parseInt(typeIds[i]);
            AccConfig config = getAccConfigByCode(typeId, accConfigList);
            if (config != null && config.getAccType() != null) {
                BigDecimal value;
                String valueStr;
                BigDecimal relValue;

                if (config.getAccType().equals(Constants.ACC_TYPE_DATA)
                        || config.getAccType().equals(Constants.ACC_TYPE_DATAPROMOTION)
                        || config.getAccType().equals(Constants.ACC_TYPE_CALL_PROMOTION)
                        || config.getAccType().equals(Constants.ACC_TYPE_SMS_ROMOTION)) {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 0, RoundingMode.FLOOR);
                    valueStr = dfData.format(value);

                    relValue = new BigDecimal(balances[i]).divide(config.getUnit(), 0, RoundingMode.FLOOR);

                } else {
                    value = new BigDecimal(balances[i]).divide(config.getUnit(), 2, RoundingMode.FLOOR);

                    relValue = new BigDecimal(balances[i]).divide(config.getUnit(), 2, RoundingMode.FLOOR);

                    valueStr = dfCurrency.format(value);
                    if (value.compareTo(minVal) == -1) {
                        valueStr = "0" + dfCurrency.format(value);
                    }
                    if (value.compareTo(BigDecimal.ZERO) < 0) {
                        valueStr = "0";
                    }
                }

                AccountDetailValueBean b = new AccountDetailValueBean();
                b.setRealValue(relValue);
                b.setAccType(config.getAccType());
                b.setBalanceId(typeId);
                b.setOrder(config.getAccOrder());
                b.setUnitLabel(config.getUnitLabel());

                if (expireTimes.length > i) {
                    b.setExp(titleDate + dateFormat.format(AppUtil.getDateFromString(expireTimes[i], dateformat)));

                    b.setExpireTime(AppUtil.getDateFromString(expireTimes[i], dateformat));
                }

                b.setTitle(config.getAccName());

                if ("0".equals(valueStr)) {
                    b.setValue(null);
                } else {
                    b.setValue(valueStr + " " + config.getUnitLabel());
                }
                // neu tra tri la 0 van hien thi $0.00
                //b.setValue(valueStr + " " + config.getUnitLabel());
                rList.add(b);

//				for (AccountDetailValueBean obj : rList) {
//					if (obj.getRealValue().compareTo(BigDecimal.ZERO) > 0) {
//
//					} else {
//						rList.remove(obj);
//					}                                                                                
//                                        
//				}
            }
        }

        List<AccountDetailValueBean> realLstAfterSum = new ArrayList<>();

        if (!CommonUtil.isEmpty(rList)) {

            AccountDetailValueBean accPromotion = new AccountDetailValueBean();
            AccountDetailValueBean dataPromotion = new AccountDetailValueBean();
            AccountDetailValueBean exchBalance = new AccountDetailValueBean();
            BigDecimal sum = BigDecimal.ZERO;
            BigDecimal sumData = BigDecimal.ZERO;
            BigDecimal exchData = BigDecimal.ZERO;

            Date expireTimeAccPro = new Date();
            Date expireTimeData = new Date();
            Date expireTimeAccExch = new Date();

            for (AccountDetailValueBean obj : rList) {

                if (obj.getAccType() == Constants.ACC_TYPE_PROMOTION) {
                    sum = sum.add(obj.getRealValue());
                    accPromotion.setAccType(obj.getAccType());
                    accPromotion.setOrder(obj.getOrder());
                    accPromotion.setTitle(obj.getTitle());
                    accPromotion.setUnitLabel(obj.getUnitLabel());
                    accPromotion.setExp(obj.getExp());
                    accPromotion.setBalanceId(obj.getBalanceId());

                    expireTimeAccPro = expireTimeAccPro != null && (expireTimeAccPro.before(obj.getExpireTime())) ? obj.getExpireTime() : expireTimeAccPro;

                } else if (obj.getAccType() == Constants.ACC_TYPE_DATA) {
                    sumData = sumData.add(obj.getRealValue());
                    dataPromotion.setAccType(obj.getAccType());
                    dataPromotion.setOrder(obj.getOrder());
                    dataPromotion.setTitle(obj.getTitle());
                    dataPromotion.setUnitLabel(obj.getUnitLabel());
                    dataPromotion.setExp(obj.getExp());
                    accPromotion.setBalanceId(obj.getBalanceId());
                    expireTimeData = expireTimeData != null && (expireTimeData.before(obj.getExpireTime())) ? obj.getExpireTime() : expireTimeData;
                } else if (obj.getAccType() == Constants.ACC_TYPE_BACLANCE_EXCHANGE) {
                    exchData = exchData.add(obj.getRealValue());
                    exchBalance.setAccType(obj.getAccType());
                    exchBalance.setOrder(obj.getOrder());
                    exchBalance.setTitle(obj.getTitle());
                    exchBalance.setUnitLabel(obj.getUnitLabel());
                    exchBalance.setExp(obj.getExp());
                    exchBalance.setBalanceId(obj.getBalanceId());

                    expireTimeAccExch = expireTimeAccExch != null && (expireTimeAccExch.before(obj.getExpireTime())) ? obj.getExpireTime() : expireTimeAccExch;
                } else if (obj.getTitle() != null && obj.getValue() != null) {
                    realLstAfterSum.add(obj);
                }
            }

            accPromotion.setRealValue(sum);
            // String valueStr = dfCurrency.format(sum) + " " + accPromotion.getUnitLabel();
            String valueStr = sum + " " + accPromotion.getUnitLabel();
            accPromotion.setValue(valueStr);
            accPromotion.setExp(titleDate + dateFormat.format(expireTimeAccPro));

            if (accPromotion.getTitle() != null && accPromotion.getValue() != null) {
                realLstAfterSum.add(accPromotion);
            }

            dataPromotion.setRealValue(sumData);
            //String valueStrData = dfCurrency.format(sumData) + " " + dataPromotion.getUnitLabel();
            String valueStrData = sumData + " " + dataPromotion.getUnitLabel();
            dataPromotion.setValue(valueStrData);

            dataPromotion.setExp(titleDate + dateFormat.format(expireTimeData));
            if (dataPromotion.getTitle() != null && dataPromotion.getValue() != null) {
                realLstAfterSum.add(dataPromotion);
            }

            exchBalance.setRealValue(sum);
            //String valueStrExch = dfCurrency.format(exchData) + " " + exchBalance.getUnitLabel();
            String valueStrExch = exchData + " " + exchBalance.getUnitLabel();
            exchBalance.setValue(valueStrExch);
            exchBalance.setExp(titleDate + dateFormat.format(expireTimeAccExch));
            if (exchBalance.getTitle() != null && exchBalance.getValue() != null) {
                realLstAfterSum.add(exchBalance);
            }

        }

//            for (AccountDetailValueBean obj : realLstAfterSum) {
//                if (obj.getRealValue().compareTo(BigDecimal.ZERO) == 0) {
//                    realLstAfterSum.remove(obj);
//                }
//            } 
//				}
        if (!CommonUtil.isEmpty(realLstAfterSum)) {
            Collections.sort(realLstAfterSum, new Comparator<AccountDetailValueBean>() {
                @Override
                public int compare(AccountDetailValueBean a, AccountDetailValueBean b) {
                    return a.getOrder().compareTo(b.getOrder());
                }
            });
        }

        return realLstAfterSum;
    }

    private List<ProvisionPricePlanBean> getPricePlanList(ViettelService response, int simType) {
        List<ProvisionPricePlanBean> rList = new ArrayList<>();
        String dateformat = environment.getRequiredProperty("pro_date_format");

        String[] expiredDate = getItemList(response, "expireDateKey", "&");
        String[] ids = getItemList(response, "pricePlanIdKey", "&");

        int count = 0;
        for (String id : ids) {
            ProvisionPricePlanBean b = new ProvisionPricePlanBean();
            if (CommonUtil.isEmpty(id)) {
                continue;
            }
            DataPricePlanConfig config = myvtgService.getPricePlanConfigByPricePlanId(Integer.parseInt(id), simType);
            b.setConfig(config);
            try {
                if (expiredDate.length > count && !"null".equalsIgnoreCase(expiredDate[count])
                        && !CommonUtil.isEmpty(expiredDate[count])) {
                    b.setExpireDate(AppUtil.getDateFromString(expiredDate[count], dateformat));
                }
            } catch (Exception e) {
                logger.error("Error", e);
            }
            if (config != null) {
                count++;
                rList.add(b);
            }
            // b.setPricePlanName(config.getAccName());
        }
        return rList;
    }

    private List<AccountDetailValueBean> getDetailAccountPricePlanList(ViettelService response,
            List<DataPricePlanConfig> accConfigList, String titleDate, String language) {
        List<AccountDetailValueBean> rList = new ArrayList<>();
        String dateformat = environment.getRequiredProperty("pro_date_format");

        String[] expiredDate = getItemList(response, "expireDateKey", "&");
        String[] ids = getItemList(response, "pricePlanIdKey", "&");
        String[] billStatusList = getItemList(response, "Account.BILL_STATUS", "&");

        SimpleDateFormat dateFormat = getDateFormatForAccountDetail();
        for (int i = 0; i < ids.length; i++) {
            // Set bill_status = "null" in case missing "BILL_STATUS_LIST"
            String billStatusValue = (billStatusList != null && billStatusList.length > i) ? billStatusList[i] : "null";
            AccountDetailValueBean b = new AccountDetailValueBean();
            b.setBillStatus("null".equalsIgnoreCase(billStatusValue) || "0".equals(billStatusValue)
                    ? messageUtil.getMessage("Account.BILL_STATUS.active." + language)
                    : messageUtil.getMessage("Account.BILL_STATUS.failed." + language));
            b.setValue(b.getBillStatus());
            if (CommonUtil.isEmpty(ids[i])) {
                continue;
            }
            DataPricePlanConfig config = getAccConfigByPricePlan(Long.parseLong(ids[i]), accConfigList);

            try {
                if (expiredDate.length > i && !"null".equalsIgnoreCase(expiredDate[i])
                        && !CommonUtil.isEmpty(expiredDate[i])) {
                    b.setExp(titleDate + dateFormat.format(AppUtil.getDateFromString(expiredDate[i], dateformat)));
                }
            } catch (Exception e) {
                logger.error("Error", e);
            }
            if (config != null) {
                b.setTitle(config.getPackageName());
                rList.add(b);
            }
        }
        return rList;
    }

    private BigDecimal getSumAccByType(List<ProvisionBalanceBean> balanceList, int type) {
        BigDecimal sum = BigDecimal.ZERO;
        for (ProvisionBalanceBean bean : balanceList) {
            if (bean.getAccType() == type) {
                sum = sum.add(bean.getBalanceValue());
            }
        }
        return sum;
    }

    /**
     * @param balanceList
     * @param type
     *
     * @return
     *
     * @author daibq getSumAccByType
     */
    private BigDecimal getSumAccByType(List<ProvisionBalanceBean> balanceList, String type) {
        BigDecimal sum = BigDecimal.ZERO;
        for (ProvisionBalanceBean bean : balanceList) {
            String balanceId = DataUtil.isNullObject(bean.getBalanceId()) ? "check" : String.valueOf(bean.getBalanceId());
            if (type.contains(balanceId)) {
                sum = sum.add(bean.getBalanceValue());
            }
        }
        return sum;
    }

    private List<ProvisionBalanceBean> getListAccByType(List<ProvisionBalanceBean> balanceList, String type) {
        List<ProvisionBalanceBean> lst = new ArrayList<>();
        for (ProvisionBalanceBean bean : balanceList) {
            String balanceId = DataUtil.isNullObject(bean.getBalanceId()) ? "check" : String.valueOf(bean.getBalanceId());
            if (type.contains(balanceId) && bean.getBalanceValue().compareTo(BigDecimal.ZERO) > 0) {
                lst.add(bean);
            }
        }
        return lst;
    }

    private BigDecimal getSumAccByAccCode(List<ProvisionBalanceBean> balanceList, int accCode) {
        BigDecimal sum = BigDecimal.ZERO;
        for (ProvisionBalanceBean bean : balanceList) {
            if (bean.getBalanceId() == accCode) {
                sum = sum.add(bean.getBalanceValue());
            }
        }
        return sum;
    }

    private ProvisionBalanceBean getMaxExpiredBalance(List<ProvisionBalanceBean> balaceList) {
        if (!CommonUtil.isEmpty(balaceList)) {
            Collections.sort(balaceList, new Comparator<ProvisionBalanceBean>() {
                @Override
                public int compare(ProvisionBalanceBean a, ProvisionBalanceBean b) {
                    return a.getExpireTime().compareTo(b.getExpireTime());

                }
            });
            return balaceList.get(0);
        }
        return null;
    }

    private ProvisionPricePlanBean getMaxExpiredPricePlan(List<ProvisionPricePlanBean> planList) {
        if (!CommonUtil.isEmpty(planList)) {
            Collections.sort(planList, new Comparator<ProvisionPricePlanBean>() {
                @Override
                public int compare(ProvisionPricePlanBean a, ProvisionPricePlanBean b) {
                    if (a.getExpireDate() == null) {
                        return -1;
                    }
                    if (b.getExpireDate() == null) {
                        return 1;
                    }

                    return a.getExpireDate().compareTo(b.getExpireDate());

                }
            });
            return planList.get(planList.size() - 1);
        }
        return null;
    }

    private DataPackageBean getMaxExpiredDataPackageBean(List<DataPackageBean> planList) {
        if (!CommonUtil.isEmpty(planList)) {
            Collections.sort(planList, new Comparator<DataPackageBean>() {
                @Override
                public int compare(DataPackageBean a, DataPackageBean b) {
                    if (a.getExpiredDate() == null) {
                        return -1;
                    }
                    if (b.getExpiredDate() == null) {
                        return 1;
                    }

                    return a.getExpiredDate().compareTo(b.getExpiredDate());

                }
            });
            return planList.get(planList.size() - 1);
        }
        return null;
    }

    @Override
    public void wsGetCallAccountDetail(String isdn, int subType, ResponseSuccessBean bean) {
        Sub subBean = myvtgService.findByIsdn(isdn);
        if (subBean == null) {
            bean.setWsResponse(null);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setMessage("Sub does not exist by isdn: " + isdn);
            return;
        }
        if (subType == Constants.SUBTYPE_TRATRUOC) {
            getPrePaidChargingDetail(isdn, subType, bean, subBean);
        } else {
            bean.setWsResponse(getPostPaidChargingDetail(subBean));
        }

    }

    @Override
    public void wsGetDataAccountDetail(String isdn, ResponseSuccessBean bean, Object language) {
        String languageStr = Constants.DEFAULT_LANGUAGE;
        if (language != null) {
            languageStr = language.toString();
        } else {
            Object appPr = myvtgService.getAppParam(Constants.APP_PARAM_DEFAULT_LANGUAGE);
            if (appPr != null) {
                languageStr = appPr.toString();
            }
        }
        getDataAccountDetail(isdn, bean, languageStr);
    }

    @Override
    public void wsGetServiceInfo(String isdn, String language, String serviceCode, ResponseSuccessBean bean) {
        if (Constants.SERVICE_CODE_TRANSFER_MONEY.equalsIgnoreCase(serviceCode)) {
            Webservice wsObject = myvtgService.getWebserviceByName(Constants.WEBSERVICE_NAME_CHECK_CONDITION);
            WebServiceUtil wsUtil = new WebServiceUtil(wsObject, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("msisdn", getCountryCode() + isdn);
            SoapWSResponse response = wsUtil.callWebService(params, true);
            if (response == null || !response.getErrCode().equals(Constants.ERROR_CODE_0)) {
                bean.setErrorCode(Constants.ERROR_CODE_0);
                bean.setMessage("Call Webservice checkCondition fail!");
                bean.setWsResponse(null);
                return;
            }
        } else if (Constants.SERVICE_CODE_AIRTIME.equalsIgnoreCase(serviceCode)) {
            ServiceInfoBean s = myvtgService.getServiceInfoAirtime(isdn, language, serviceCode);
            s.setLockState(0);
            bean.setWsResponse(s);
            return;
        }
        ServiceInfoBean s = myvtgService.getServiceInfo(language, serviceCode);
        if (s == null) {
            bean.setWsResponse(null);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setMessage("ServiceA not found " + serviceCode);
        } else {
            s.setLockState(0);
            bean.setWsResponse(s);
        }
    }

    @Override
    public void wsGetCurrentUsedSubServices(String isdn, String language, ResponseSuccessBean bean) {
        // if (isUnitelMarket()) {
        List<SubServiceBean> result = new ArrayList<>();
        Sub sub = myvtgService.findByIsdn(isdn);
        if (sub != null) {

            String listRegisteredServiceCodes = null;
            if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
                listRegisteredServiceCodes = cmpreService.getListRegisteredServiceCodes(sub.getSubId());
            } else {
                listRegisteredServiceCodes = cmposService.getListRegisteredServiceCodes(sub.getSubId());
            }
            result = myvtgService.getCurrentUsedSubServices(language, isdn, listRegisteredServiceCodes);

        }
        bean.setWsResponse(result);
    }

    @Override
    public void wsDoRecharge(String isCheckSub, String isdn, String desIsdn, String serial, String language,
            ResponseSuccessBean bean) throws Exception {
        int desSubType = Constants.SUBTYPE_TRATRUOC;

        AllCustSubForSelfcareBean preSubMb = cmpreService.getCustSubForSelfcare(desIsdn);
        if (preSubMb == null) {
            if (IS_CHECK_SUB.equalsIgnoreCase(isCheckSub)) {
                // try to query on postpaid DB
                co.siten.myvtg.model.cmpos.AllTelServiceSubSelfcare posSubMb = cmposService
                        .getTelServiceSubSelfcare(desIsdn);
                if (posSubMb == null) {
                    bean.setErrorCode(Constants.ERROR_CODE_3);
                    bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.0." + language));
                    return;
                } else {
                    if (Constants.ACT_STATUS_03.equalsIgnoreCase(posSubMb.getActStatusBits())) {
                        bean.setErrorCode(Constants.ERROR_CODE_3);
                        bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.1." + language));
                        return;
                    } else if (!LAST_ACC_STATUS.equalsIgnoreCase(posSubMb.getActStatusBits().substring(0, 1))) {
                        bean.setErrorCode(Constants.ERROR_CODE_3);
                        bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.2." + language));
                        return;
                    }
                    desSubType = Constants.SUBTYPE_TRASAU;
                }
            } else {
                desSubType = Constants.SUBTYPE_TRASAU;
            }
        } else if (Constants.ACT_STATUS_03.equalsIgnoreCase(preSubMb.getActStatusBits())) {
            bean.setErrorCode(Constants.ERROR_CODE_3);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.1." + language));
            return;
        } else if (!LAST_ACC_STATUS.equalsIgnoreCase(preSubMb.getActStatusBits().substring(0, 1))) {
            bean.setErrorCode(Constants.ERROR_CODE_3);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.2." + language));
            return;
        }

        if (desSubType == Constants.SUBTYPE_TRATRUOC) {
            rechargePrepaid(isdn, desIsdn, serial, language, bean);
        } else if (desSubType == Constants.SUBTYPE_TRASAU) {
            rechargePostPaid(isdn, desIsdn, serial, language, bean);
        }
    }

    @Override
    public void wsDoRechargeCard(String isCheckSub, String isdn, String desIsdn, String serial, String language,
            ResponseSuccessBean bean, String programCode, String captchaCodeStr) throws Exception {
        logger.info("Start wsDoRechargeCard");
        int desSubType = Constants.SUBTYPE_TRATRUOC;
        AllCustSubForSelfcareBean preSubMb = cmpreService.getCustSubForSelfcare(desIsdn);
        if (preSubMb == null) {
            logger.info("preSubMb is null");
            if (IS_CHECK_SUB.equalsIgnoreCase(isCheckSub)) {
                // try to query on postpaid DB
                co.siten.myvtg.model.cmpos.AllTelServiceSubSelfcare posSubMb = cmposService
                        .getTelServiceSubSelfcare(desIsdn);
                if (posSubMb == null) {
                    bean.setErrorCode(Constants.ERROR_CODE_3);
                    bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.0." + language));
                    return;
                } else {
                    if (Constants.ACT_STATUS_03.equalsIgnoreCase(posSubMb.getActStatusBits())) {
                        bean.setErrorCode(Constants.ERROR_CODE_3);
                        bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.1." + language));
                        return;
                    } else if (!LAST_ACC_STATUS.equalsIgnoreCase(posSubMb.getActStatusBits().substring(0, 1))) {
                        bean.setErrorCode(Constants.ERROR_CODE_3);
                        bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.2." + language));
                        return;
                    }

                    desSubType = Constants.SUBTYPE_TRASAU;
                }
            } else {
                desSubType = Constants.SUBTYPE_TRASAU;
            }
        } else if (Constants.ACT_STATUS_03.equalsIgnoreCase(preSubMb.getActStatusBits())) {
            bean.setErrorCode(Constants.ERROR_CODE_3);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.1." + language));
            return;
        } else if (!LAST_ACC_STATUS.equalsIgnoreCase(preSubMb.getActStatusBits().substring(0, 1))) {
            bean.setErrorCode(Constants.ERROR_CODE_3);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.2." + language));
            return;
        }
        //daibq bo sung luong validate va them captcha
        logger.info("Start get captcha");
        if (DataUtil.isNullOrEmpty(programCode)) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage("myMetfone.toup.err.old.version." + language));
            return;
        }
        Captcha captcha = myvtgService.getCaptcha(isdn, programCode);
        Date dateCurr = new Date();
        // neu qua ngay thi update lai total error
        if (!DataUtil.isNullObject(captcha)
                && DataUtil.addTime(captcha.getCreateDate(), 1, null, null, null, 5).getTime() <= DataUtil.addTime(dateCurr, null, null, null, null, 4).getTime()) {
            captcha.setStatus(0L);
            captcha.setTotalError(0L);
            captcha.setCreateDate(new Date());
            captcha.setUpdateDate(null);
            myvtgService.update(captcha);
        }
        String maxErr10 = configUtils.getMessage("MAX_ERR_CAPT_10", "10").trim();
        String maxErr5 = configUtils.getMessage("MAX_ERR_CAPT_5", "5").trim();
        int expired = 0;
        expired = Integer.parseInt(configUtils.getMessage("EXPRIED_CAPTCHA", "180").trim());

        String captchaCode = DataUtil.rand(111, 999999);
        if (!DataUtil.isNullObject(captcha) && captcha.getTotalError() >= Integer.parseInt(maxErr10)) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(String.format(messageUtil.getMessage("myMetfone.toup.err.10." + language), maxErr10));
            bean.setUserMsg(String.format(messageUtil.getMessage("myMetfone.toup.err.10." + language), maxErr10));
            return;
        }
        //check truong hop bat dau nhap qua n config lan
        if (!DataUtil.isNullObject(captcha)
                && Long.parseLong(maxErr5) == captcha.getTotalError()) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setMessage(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), maxErr5));
            bean.setUserMsg(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), maxErr5));
            //dax thuc hien qua n lan. bat dau sinh captcha cho lan n +1
            Date dateEx = DataUtil.addSecond(dateCurr, expired);
            captcha.setCreateDate(dateCurr);
            captcha.setStatus(0L);
            captcha.setUpdateDate(null);
            captcha.setCaptchaCode(captchaCode);
            captcha.setTotalError(captcha.getTotalError() + 1L);
            captcha.setExpiredTime(dateEx);
            myvtgService.update(captcha);
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setCaptchaCode(captchaCode);
            metfoneBean.setTimeOut(String.valueOf(expired));
            bean.setWsResponse(metfoneBean);
            return;
        }
        //check truong hop bat dau nhap qua n +1 config lan
        if (!DataUtil.isNullObject(captcha)
                && (Long.parseLong(maxErr5) + 1L) <= captcha.getTotalError()) {
            // reload cho truong hop vao lai menu topup khi da sai  lon hon 5 lan 
            if (DataUtil.isNullOrEmpty(captchaCodeStr)) {
                bean.setErrorCode(Constants.ERROR_CODE_0);
                bean.setMessage(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), captcha.getTotalError().toString()));
                bean.setUserMsg(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), captcha.getTotalError().toString()));
                Date dateEx = DataUtil.addSecond(dateCurr, expired);
                captcha.setCreateDate(dateCurr);
                captcha.setUpdateDate(null);
                captcha.setStatus(0L);
                captcha.setCaptchaCode(captchaCode);
                captcha.setExpiredTime(dateEx);
                myvtgService.update(captcha);
                MetfoneBean metfoneBean = new MetfoneBean();
                metfoneBean.setCaptchaCode(captchaCode);
                metfoneBean.setTimeOut(String.valueOf(expired));
                bean.setWsResponse(metfoneBean);
                return;
            }
            if (!captchaCodeStr.equals(captcha.getCaptchaCode())
                    || dateCurr.getTime() > captcha.getExpiredTime().getTime()
                    || captcha.getStatus() >= 1L) {
                bean.setErrorCode(Constants.ERROR_CODE_0);
                bean.setMessage(messageUtil.getMessage("myMetfone.toup.err.expired." + language));
                bean.setUserMsg(messageUtil.getMessage("myMetfone.toup.err.expired." + language));
                //bat dau tu lan nay khi thuc hien sai serial voi bat dau thuc hien up date lai total error
                Date dateEx = DataUtil.addSecond(dateCurr, expired);
                captcha.setCreateDate(dateCurr);
                captcha.setUpdateDate(null);
                captcha.setStatus(0L);
                captcha.setCaptchaCode(captchaCode);
                captcha.setExpiredTime(dateEx);
                myvtgService.update(captcha);
                MetfoneBean metfoneBean = new MetfoneBean();
                metfoneBean.setCaptchaCode(captchaCode);
                metfoneBean.setTimeOut(String.valueOf(expired));
                bean.setWsResponse(metfoneBean);
                return;
            }
        }
        if (serial != null && serial.trim().length() > SERIAL_LENGTH) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
            if (DataUtil.isNullObject(captcha)) {
                captcha = new Captcha();
                captcha.setCreateDate(dateCurr);
                captcha.setExpiredTime(DataUtil.addSecond(dateCurr, expired));
                captcha.setStatus(0L);
                captcha.setIsdn(isdn);
                captcha.setProgramCode(programCode);
                captcha.setCaptchaCode(captchaCode);
                captcha.setTotalError(1L);
                myvtgService.insert(captcha);
//                myvtgService.insertCaptcha(isdn, captchaCode, expired, programCode, 1L);
            } else {
                captcha.setTotalError(captcha.getTotalError() + 1L);
                if ((Long.parseLong(maxErr5) + 1L) <= captcha.getTotalError()) {
                    bean.setErrorCode(Constants.ERROR_CODE_0);
                    Date dateEx = DataUtil.addSecond(dateCurr, expired);
                    captcha.setCreateDate(dateCurr);
                    captcha.setStatus(0L);
                    captcha.setUpdateDate(null);
                    captcha.setCaptchaCode(captchaCode);
                    captcha.setExpiredTime(dateEx);
                    MetfoneBean metfoneBean = new MetfoneBean();
                    metfoneBean.setCaptchaCode(captchaCode);
                    metfoneBean.setTimeOut(String.valueOf(expired));
                    bean.setWsResponse(metfoneBean);
                }
                myvtgService.update(captcha);
            }
            return;
        }
        if (desSubType == Constants.SUBTYPE_TRATRUOC) {
            rechargePrepaid(isdn, desIsdn, serial, language, bean, programCode, captcha, maxErr5, expired, dateCurr);
        } else if (desSubType == Constants.SUBTYPE_TRASAU) {
            rechargePostPaid(isdn, desIsdn, serial, language, bean);
        }
    }

    /**
     * daibq update luong topup
     *
     * @param isdn
     * @param desIsdn
     * @param serial
     * @param language
     * @param bean
     * @param programCode
     *
     * @throws Exception
     */
    private void rechargePrepaid(String isdn, String desIsdn,
            String serial, String language, ResponseSuccessBean bean,
            String programCode, Captcha captcha, String maxErr5, int expired, Date dateCurr
    )
            throws Exception {
        logger.info("Start rechargePrepaid of AccountServiceImpl");
        TransactionLog charLog;
        TransactionLog lockLog;
        String typeRecharge = environment.getRequiredProperty("recharge_type");

        // Init log
        ChargeHis c = new ChargeHis();
        c.setServiceId("PrePaid");
        c.setFee(BigDecimal.ZERO);
        c.setMsisdn(desIsdn);
        c.setRefillIsdn(isdn);
        c.setPinNumber(serial);
        // c.setSeriNumber(serial);
        String captchaCode = DataUtil.rand(111, 999999);
        if (isMetfoneMarket()) {
            // STEP 1: try to lock card and get value
            logger.info("Start connection with typeRecharge: " + typeRecharge);
            channel = proService.getChannel(typeRecharge);

            CardUtil cardUtil = new CardUtil(channel);
            logger.info("Start log card with serial: " + serial);
            lockLog = cardUtil.viewCardExchange(desIsdn, serial, environment);
            logger.info("End log card with error code: " + lockLog.getErrorCode());
            //daibq bo sung luu thue bao dn.
            lockLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
//            lockLog.setSeriNumber(serial);
            myvtgService.persistTransactionLog(lockLog);
            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // TODO: set user message with clear message, ex: The card is
                // already locked.
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));

                bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setRefillDate(lockLog.getRequestTime());
                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
                c.setTransactionLogId(lockLog.getId());
                c.setSeriNumber(lockLog.getSerial());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                //daibq bo sung luong captcha
                if (DataUtil.isNullObject(captcha)) {
                    captcha = new Captcha();
                    captcha.setCreateDate(dateCurr);
                    captcha.setExpiredTime(DataUtil.addSecond(dateCurr, expired));
                    captcha.setStatus(0L);
                    captcha.setIsdn(isdn);
                    captcha.setProgramCode(programCode);
                    captcha.setCaptchaCode(captchaCode);
                    captcha.setTotalError(1L);
                    myvtgService.insert(captcha);
                } else {
                    captcha.setTotalError(captcha.getTotalError() + 1L);
                    if ((Long.parseLong(maxErr5) + 1L) <= captcha.getTotalError()) {
                        bean.setErrorCode(Constants.ERROR_CODE_0);
                        Date dateEx = DataUtil.addSecond(dateCurr, expired);
                        captcha.setCreateDate(dateCurr);
                        captcha.setStatus(0L);
                        captcha.setUpdateDate(null);
                        captcha.setCaptchaCode(captchaCode);
                        captcha.setExpiredTime(dateEx);
                        MetfoneBean metfoneBean = new MetfoneBean();
                        metfoneBean.setCaptchaCode(captchaCode);
                        metfoneBean.setTimeOut(String.valueOf(expired));
                        bean.setWsResponse(metfoneBean);
                    }
                    myvtgService.update(captcha);
                }
                return;
            }

            // STEP 2: try to add money
            //daibq update captcha cho case nhap serial success
            //case nay da kiem tra serial và lock success nen ko tac dong den captcha nua
            if (!DataUtil.isNullObject(captcha)) {
                captcha.setTotalError(0L);
                captcha.setStatus(1L);
                captcha.setUpdateDate(new Date());
                myvtgService.update(captcha);
            }

            Double monney = lockLog.getMoney();
            monney = monney / (Integer.parseInt(environment.getRequiredProperty("recharge_rate")));
            logger.info("Start pay money with serial: " + serial + ">>> monney: " + monney);
            charLog = cardUtil.chargeMoneyExchange(desIsdn, monney, environment);
            logger.info("End pay money with error code: " + charLog.getErrorCode());
            //daibq bo sung luu thue bao dn.
            charLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
//            charLog.setSeriNumber(serial);
            myvtgService.persistTransactionLog(charLog);

            c.setRefillDate(charLog.getRequestTime());
            c.setRefillAmount(new BigDecimal(monney));

            if (charLog.getErrorCode() != null && !Constants.ERROR_CODE_0.equalsIgnoreCase(charLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setResult("UN-SUCC: Add balance, logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                c.setSeriNumber(serial);
                myvtgService.persistCharhis(c);
                return;
            } else {
                c.setResult("SUCC: logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_0));
                c.setSeriNumber(lockLog.getSerial());
                myvtgService.persistCharhis(c);
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setCaptchaCode("");
            metfoneBean.setTimeOut("");
            bean.setWsResponse(metfoneBean);
            return;

        } else {

            logger.info("isMetfoneMarket not metfone");
//            String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + isdn;
//            String desMsIsdn = environment.getRequiredProperty("COUNTRY_CODE") + desIsdn;
//
//            // STEP 1: try to lock card and get value
//            channel = proService.getChannel(Constants.PRO);
//            CardUtil cardUtil = new CardUtil(channel);
//
//            lockLog = cardUtil.lockCard(msisdn, serial, environment);
//            myvtgService.persistTransactionLog(lockLog);
//            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
//                bean.setErrorCode(Constants.ERROR_CODE_1);
//                // TODO: set user message with clear message, ex: The card is
//                // already locked.
//                c.setRefillDate(lockLog.getRequestTime());
//                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
//                c.setTransactionLogId(lockLog.getId());
//                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
//                myvtgService.persistCharhis(c);
//                //daibq bo sung luong captcha
//                if (DataUtil.isNullObject(captcha)) {
//                    captcha = new Captcha();
//                    captcha.setCreateDate(dateCurr);
//                    captcha.setExpiredTime(DataUtil.addSecond(dateCurr, expired));
//                    captcha.setStatus(0L);
//                    captcha.setIsdn(isdn);
//                    captcha.setProgramCode(programCode);
//                    captcha.setCaptchaCode(captchaCode);
//                    captcha.setTotalError(1L);
//                    myvtgService.insert(captcha);
////                    myvtgService.insertCaptcha(isdn, captchaCode, expired, programCode, 1L);
//                } else {
//                    captcha.setTotalError(captcha.getTotalError() + 1L);
//                    if ((Long.parseLong(maxErr5) + 1L) <= captcha.getTotalError()) {
//                        bean.setErrorCode(Constants.ERROR_CODE_0);
//                        Date dateEx = DataUtil.addSecond(dateCurr, expired);
//                        captcha.setCreateDate(dateCurr);
//                        captcha.setStatus(0L);
//                        captcha.setUpdateDate(null);
//                        captcha.setCaptchaCode(captchaCode);
//                        captcha.setExpiredTime(dateEx);
//                        MetfoneBean metfoneBean = new MetfoneBean();
//                        metfoneBean.setCaptchaCode(captchaCode);
//                        metfoneBean.setTimeOut(String.valueOf(expired));
//                        bean.setWsResponse(metfoneBean);
//
//                    }
//                    myvtgService.update(captcha);
//                }
//
//                return;
//            }
//
//            // STEP 2: try to add balance
//            //daibq update captcha cho case nhap serial success
//            //case nay da kiem tra serial và lock success nen ko tac dong den captcha nua
//            if (!DataUtil.isNullObject(captcha)) {
//                captcha.setTotalError(0L);
//                captcha.setStatus(1L);
//                captcha.setUpdateDate(new Date());
//                myvtgService.update(captcha);
//            }
//
//            BigDecimal money = new BigDecimal(Long.parseLong(lockLog.getResultValue())
//                    / (Integer.parseInt(environment.getRequiredProperty("recharge_rate"))));
//            TransactionLog tlog = chargeMoneyRetry(isdn, desMsIsdn, serial, bean, cardUtil, money, c);
//            if (bean.getErrorCode().equalsIgnoreCase(Constants.ERROR_CODE_1)) {
//                // Init transaction log
//                TransactionError te = new TransactionError();
//                te.setInsertDate(CommonUtil.getCurrentTime());
//                te.setShopCode(Constants.MYVTG);
//
//                // Add balance
//                try {
//                    te.setRequest("Try to retry failed recharge command (more detail form log with Id=" + tlog.getId()
//                            + " : Request from " + isdn + " to recharge for " + desIsdn + " ưith pin=" + serial
//                            + ", money=" + money + " kip");
//                    // try to add balance
//                    ResponseObj responseObj = callApiChangeAccountBalance(Constants.SUBTYPE_TRATRUOC, money, isdn);
//
//                    te.setResponse(responseObj.getResponseStr());
//                    te.setErrorCode("Successful");
//                    te.setMessage("Successfully add balance.");
//                    logService.insertTransactionLog(te);
//                } catch (Exception e) {
//                    logger.error("Eror", e);
//                    te.setErrorCode("Error");
//                    te.setMessage(e.getMessage());
//                    logService.insertTransactionLog(te);
//                }
//            }
//            MetfoneBean metfoneBean = new MetfoneBean();
//            metfoneBean.setCaptchaCode("");
//            metfoneBean.setTimeOut("");
//            bean.setWsResponse(metfoneBean);
        }
    }

    private void rechargePrepaid(String isdn, String desIsdn, String serial, String language, ResponseSuccessBean bean)
            throws Exception {
        TransactionLog charLog;
        TransactionLog lockLog;
        String typeRecharge = environment.getRequiredProperty("recharge_type");

        // Init log
        ChargeHis c = new ChargeHis();
        c.setServiceId("PrePaid");
        c.setFee(BigDecimal.ZERO);
        c.setMsisdn(desIsdn);
        c.setRefillIsdn(isdn);
        c.setPinNumber(serial);
        // c.setSeriNumber(serial);

        if (isMetfoneMarket() || isMovitel()) {
            // STEP 1: try to lock card and get value
            channel = proService.getChannel(typeRecharge);
            CardUtil cardUtil = new CardUtil(channel);
            lockLog = cardUtil.viewCardExchange(desIsdn, serial, environment);
//            c.setSeriNumber(lockLog.getSeriNumber());
            //daibq bo sung luu thue bao dn.
            lockLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
            myvtgService.persistTransactionLog(lockLog);
            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // TODO: set user message with clear message, ex: The card is
                // already locked.
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));

                bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setRefillDate(lockLog.getRequestTime());
                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
                c.setTransactionLogId(lockLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return;
            }

            // STEP 2: try to add money
            Double monney = lockLog.getMoney();
            monney = monney / (Integer.parseInt(environment.getRequiredProperty("recharge_rate")));
            charLog = cardUtil.chargeMoneyExchange(desIsdn, monney, environment);
            //daibq bo sung luu thue bao dn.
            charLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
            myvtgService.persistTransactionLog(charLog);

            c.setRefillDate(charLog.getRequestTime());
            c.setRefillAmount(new BigDecimal(monney));

            if (charLog.getErrorCode() != null && !Constants.ERROR_CODE_0.equalsIgnoreCase(charLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setResult("UN-SUCC: Add balance, logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return;
            } else {
                c.setSeriNumber(lockLog.getSeriNumber());
                c.setResult("SUCC: logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_0));
                myvtgService.persistCharhis(c);
            }
            return;

        } else {

            String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + isdn;
            String desMsIsdn = environment.getRequiredProperty("COUNTRY_CODE") + desIsdn;

            // STEP 1: try to lock card and get value
            channel = proService.getChannel(Constants.PRO);
            CardUtil cardUtil = new CardUtil(channel);

            lockLog = cardUtil.lockCard(msisdn, serial, environment);
            myvtgService.persistTransactionLog(lockLog);
            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // TODO: set user message with clear message, ex: The card is
                // already locked.
                c.setRefillDate(lockLog.getRequestTime());
                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
                c.setTransactionLogId(lockLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return;
            }

            // STEP 2: try to add balance
            BigDecimal money = new BigDecimal(Long.parseLong(lockLog.getResultValue())
                    / (Integer.parseInt(environment.getRequiredProperty("recharge_rate"))));
            TransactionLog tlog = chargeMoneyRetry(isdn, desMsIsdn, serial, bean, cardUtil, money, c);
            if (bean.getErrorCode().equalsIgnoreCase(Constants.ERROR_CODE_1)) {
                // Init transaction log
                TransactionError te = new TransactionError();
                te.setInsertDate(CommonUtil.getCurrentTime());
                te.setShopCode(Constants.MYVTG);

                // Add balance
                try {
                    te.setRequest("Try to retry failed recharge command (more detail form log with Id=" + tlog.getId()
                            + " : Request from " + isdn + " to recharge for " + desIsdn + " ưith pin=" + serial
                            + ", money=" + money + " kip");
                    // try to add balance
                    ResponseObj responseObj = callApiChangeAccountBalance(Constants.SUBTYPE_TRATRUOC, money, isdn);

                    te.setResponse(responseObj.getResponseStr());
                    te.setErrorCode("Successful");
                    te.setMessage("Successfully add balance.");
                    logService.insertTransactionLog(te);
                } catch (Exception e) {
                    logger.error("Eror", e);
                    te.setErrorCode("Error");
                    te.setMessage(e.getMessage());
                    logService.insertTransactionLog(te);
                }
            }
        }
    }

    private TransactionLog chargeMoneyRetry(String isdn, String desMsIsdn, String serial, ResponseSuccessBean bean,
            CardUtil cardUtil, BigDecimal money, ChargeHis c) {

        TransactionLog charLog = cardUtil.chargeMoney(desMsIsdn, money.longValue(), environment);
        myvtgService.persistTransactionLog(charLog);

        c.setRefillDate(charLog.getRequestTime());
        c.setRefillAmount(money);
        c.setRefillIsdn(isdn);
        c.setMsisdn(desMsIsdn);

        if (charLog.getErrorCode() != null && !Constants.ERROR_CODE_0.equalsIgnoreCase(charLog.getErrorCode())) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            c.setResult("UN-SUCC, Add balance, LogId=" + charLog.getId());
            c.setTransactionLogId(charLog.getId());
            c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
            logger.error("chargeMoneyRetry Error: " + charLog.getResponse());
        } else {
            c.setResult("SUCC, LogId=" + charLog.getId());
            c.setTransactionLogId(charLog.getId());
            c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_0));
        }
        myvtgService.persistCharhis(c);
        return charLog;
    }

    private void rechargePostPaid(String isdn, String desIsdn, String serial, String language, ResponseSuccessBean bean)
            throws Exception {
        // Init log
        TransactionLog log = new TransactionLog();
        ChargeHis c = new ChargeHis();
        log.setRequestTime(CommonUtil.getCurrentTime());
        log.setMsisdn(isdn);
        log.setChannel("Topup WS");
        log.setCommand("Call TopUp Postpaid webservice");
        c.setFee(BigDecimal.ZERO);
        c.setServiceId(Constants.WS_TOPUP_POSTPAID);
        c.setMsisdn(desIsdn);
        c.setPinNumber(serial);
        c.setRefillDate(log.getRequestTime());
        c.setRefillIsdn(isdn);

        // Init web service
        Webservice wsObject = myvtgService.getWebserviceByName(Constants.WS_TOPUP_POSTPAID);
        WebServiceUtil wsUtil = new WebServiceUtil(wsObject, environment);
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("account", desIsdn);
        params.put("pin", serial);
        SoapWSResponse wsResponse = wsUtil.callWebService(params, true);
        wsUtil.setTransLog(log);

        // parser result
        if (wsResponse != null) {
            try {

                String error = wsResponse.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = wsResponse.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    try {
                        SoapWSResponse response = new SoapWSResponse(respStr);
                        String respCode = response.getTextContent("/Envelope/Body/topUpCardResponse/return/pincode");

                        if (respCode != null && Constants.ERROR_CODE_00.equalsIgnoreCase(respCode)) {
                            log.setErrorCode(Constants.ERROR_CODE_00);

                        } else {
                            log.setErrorCode(Constants.ERROR_CODE_1);
                            bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
                            bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
                        }

                    } catch (Exception e) {
                        log.setExtraInfo(e.getMessage());
                        logger.error(e.getMessage(), e);
                    }

                } else {
                    log.setErrorCode(Constants.ERROR_CODE_1);
                    bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
                    bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
                    return;
                }
            } catch (Exception e) {
                log.setExtraInfo(e.getMessage());
                logger.error(e.getMessage(), e);
            }
        } else {
            log.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
            bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
        }

        // recharge log
        myvtgService.persistTransactionLog(log);
        logger.info("log.getErrorCode(): " + log.getErrorCode());

        if (log.getErrorCode() != null && !Constants.ERROR_CODE_00.equalsIgnoreCase(log.getErrorCode())) {

            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
            bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
            c.setResult("UN-SUCC, call webservice, LogId=" + log.getId());
            c.setTransactionLogId(log.getId());
            c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
            logger.error("TopUp PosPaid Error: " + log.getResponse());
            return;
        } else {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            c.setResult("SUCC, LogId=" + log.getId());
            c.setTransactionLogId(log.getId());
            bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.success." + language));
            c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_0));
            myvtgService.persistCharhis(c);

        }
        return;

    }

    @Override
    public void wsGetDataVolumeLevelToBuy(String isdn, String dataPackageCode, ResponseSuccessBean bean) {
        VSelfcareDataToBuy toBuy = dataService.getVSelfcareDataToBuyByName(dataPackageCode);
        String delim = "|";
        String regex = "(?<!\\\\)" + Pattern.quote(delim);
        if (toBuy == null) {
            bean.setWsResponse(null);
            return;
        }

        String[] toBuyArr = toBuy.getUnit().split(regex);
        List<DataPakageToBuyBean> bList = new Vector<>();
        for (int i = 0; i < toBuyArr.length; i++) {
            DataPakageToBuyBean d = new DataPakageToBuyBean();
            d.setPrice(new BigDecimal(toBuyArr[i].split(";")[0]));
            d.setVolume(new BigDecimal(toBuyArr[i].split(";")[1]));
            bList.add(d);
        }
        bean.setWsResponse(bList);
        // }
    }

    @Override
    public void wsGetDataPackageInfo(String isdn, String packageCode, String lang, ResponseSuccessBean bean) {
        // DataPackageInfoBean b = myvtgService.getDataPackageInfo(isdn,
        // packageCode, lang);
        Sub sub = myvtgService.findByIsdn(isdn);
        if (sub == null) {
            bean.setWsResponse(null);
            bean.setMessage("wsGetDataPackageInfo() : Sub " + isdn + " is not exist.");
            return;
        }

        List<String> listRegisteredServiceCodes = getRegisterServiceCodes(sub);

        DataPackageInfoBean b = myvtgService.getDataPackageInfo(sub, packageCode, lang,
                getServiceTypesFromConfig(sub.getSubType(), sub.getSimType()), listRegisteredServiceCodes);
        bean.setWsResponse(b);
    }

    private String getServiceTypesFromConfig(int subType, int simType) {

        if (subType == Constants.SUBTYPE_TRATRUOC && simType == Constants.SIM_TYPE_3G) {
            return (String) myvtgService.getAppParam(Constants.SERVICE_TYPES_PREPAID_AND_3G);
        } else if (subType == Constants.SUBTYPE_TRATRUOC && simType == Constants.SIM_TYPE_4G) {
            return (String) myvtgService.getAppParam(Constants.SERVICE_TYPES_PREPAID_AND_4G);
        } else if (subType == Constants.SUBTYPE_TRASAU && simType == Constants.SIM_TYPE_3G) {
            return (String) myvtgService.getAppParam(Constants.SERVICE_TYPES_POSPAID_AND_3G);
        } else if (subType == Constants.SUBTYPE_TRASAU && simType == Constants.SIM_TYPE_4G) {
            return (String) myvtgService.getAppParam(Constants.SERVICE_TYPES_POSPAID_AND_4G);
        }

        return "(' ')";
    }

    @Override
    public void wsGetPostageInfo(String isdn, int subType, Date startDate, Date endDate, String type,
            ResponseSuccessBean bean) {
        if (subType == Constants.SUBTYPE_TRATRUOC) {

            if (isMetfoneMarket()) {

                PostageInfoBean b = precallService.getPostageInfoSplitDataVas(environment, isdn, subType, startDate,
                        endDate, type, getCountryCode());
                bean.setWsResponse(b);
            } else {
                PostageInfoBean b = precallService.getPostageInfo(environment, isdn, subType, startDate, endDate, type,
                        getCountryCode());
                bean.setWsResponse(b);
            }
        } else {
            PostageInfoBean b = billingService.getPostageInfo(isdn, subType, startDate, endDate, getCountryCode());
            // PostageInfoBean b =
            // paymentService.getQueries().getPostageInfo(isdn,
            // subType, startDate, endDate);
            bean.setWsResponse(b);
        }
    }

    @Override
    public void wsFindIsdnToBuy(int subType, int pageSize, int pageNum, String language, BigDecimal minPrice,
            BigDecimal maxPrice, String isdnPattern, ResponseSuccessBean bean) {
        List<IsdnToBuyBean> l = smService.getIsdnToBuy(subType, pageSize, pageNum, minPrice, maxPrice, isdnPattern);
        if (CommonUtil.isEmpty(l)) {
            bean.setWsResponse(null);
        } else {
            bean.setWsResponse(l);
        }
    }

    @Override
    public void wsDoActionService(String isdn, String serviceCode, int actionType, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean) throws Exception {
        if (isUnitelMarket()) {
            doActionService(isdn, serviceCode, actionType, params, 0, bean);
        } else {
            // Subscriber subscriber =
            // myvtgService.getSubcriberByIsdnAndServiceCode(isdn, serviceCode);
            // if (subscriber == null) {
            // subscriber = new Subscriber();
            // SubscriberPK spk = new SubscriberPK();
            // spk.setIsdn(isdn);
            // spk.setSubServiceCode(serviceCode);
            //
            // subscriber.setId(spk);
            // subscriber.setSubId(myvtgService.getSubIdByIsdn(isdn));
            // subscriber.setRegisterTime(CommonUtil.getCurrentTime());
            // subscriber.setCreatedBy(Constants.MYVTG);
            // subscriber.setLastUpdatedBy(Constants.MYVTG);
            // subscriber.setState(Constants.SUBCRIBER_STATE_CANCEL);
            // subscriber.setStatus(1);
            // myvtgService.persist(subscriber);
            // }

            // it's already Un-Reggister
            // if (actionType == Constants.ACTION_TYPE_REGISTER
            // &&
            // subscriber.getState().equals(Constants.SUBCRIBER_STATE_REGISTERED))
            // {
            // bean.setErrorCode(Constants.ERROR_CODE_1);
            // bean.setMessage("Already Registered " + serviceCode);
            // bean.setUserMsg("This service/data package is already
            // registered.");
            // return;
            // } else if (actionType == Constants.ACTION_TYPE_CANCEL
            // &&
            // subscriber.getState().equals(Constants.SUBCRIBER_STATE_CANCEL)) {
            // bean.setErrorCode(Constants.ERROR_CODE_1);
            // bean.setMessage("Already Un-Registered " + serviceCode);
            // bean.setUserMsg("This service/data package is already
            // un-registered.");
            // return;
            // }
            doActionService(isdn, serviceCode, actionType, params, 0, bean);
        }

    }

    @Override
    public void wsDoActionServiceByOTP(String isdn, String serviceCode, int actionType, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean, RequestBean request) throws Exception {
        String service = "OTPForExchange";
        String language = request.getWsRequest().get("language") == null ? "kh" : request.getWsRequest().get("language").toString();

        if (request.getWsRequest().get("otp") == null) {
            logger.info("Error request : otp is null or empty");
            bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
            bean.setMessage("otp.invalid");
            bean.setUserMsg("OTP invalid or expired");
            return;
        }
        String OTP = request.getWsRequest().get("otp").toString();
        logger.info("OTP :" + OTP);
        Otp otpObj = myMetfoneBusinesDao.getOtpByIsdnAndService(isdn, service, OTP);
        Date currDate = new Date();
        if (DataUtil.isNullObject(otpObj) || otpObj.getExpireTime().getTime() < currDate.getTime() || otpObj.getStatus() == 1) {
            logger.info("Error requesst : otp err ");
            bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
            bean.setMessage("otp.invalid");
            bean.setUserMsg("OTP invalid or expired");
            return;
        }

        doActionService(isdn, serviceCode, actionType, params, 0, bean);
        try {
            if (!DataUtil.isNullObject(otpObj)) {
                otpObj.setTotalGetOtp(0L);
                otpObj.setStatus(1L);
                otpObj.setUpdateDate(currDate);
                myMetfoneBusinesDao.update(otpObj);
            }
        } catch (Exception ex) {

        }
    }

    private void doActionService(String isdn, String serviceCode, int actionType, LinkedHashMap<String, Object> params,
            Integer preState, ResponseSuccessBean bean) {
        WebserviceBean wsObjectBean = myvtgService.getWebserviceByServiceCodeAndActionType(serviceCode, actionType);
        String code = environment.getRequiredProperty("COUNTRY_CODE");
        if (wsObjectBean == null) {
            // Web-service not found, check database
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(
                    "The Webserive can't be found for this " + serviceCode + " service. Please, check the database.");
            bean.setUserMsg("Sorry! This action can't be done.");
            return;
        }
        logger.info("Action " + isdn);
        Action action = wsObjectBean.getAction();
        if (wsObjectBean.getWebservice() != null && action != null
                && action.getId().getChannelType().equals(Constants.ACTION_CHANNEL_TYPE_WEBSERVICE)) {
            // In-case using web-service
            if (actionType == Constants.ACTION_TYPE_REGISTER || actionType == Constants.ACTION_TYPE_CANCEL) {
                params = new LinkedHashMap<>();
                params.put("syntax", action.getSyntax());
            }
            SoapWSResponse obj = doCallWebservice(params, isdn, wsObjectBean);
            if (obj == null) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setUserMsg(messageUtil.getMessage("wsDoActionService.fail.en"));
                return;
            } else {
                //
                // ERROR_CODE_00 == "00" --> register directly, not fake mo (or
                // other delayed methods)
                // The app client should be display message "Registered
                // successfully", not "processing"
                bean.setErrorCode(obj.getErrCode());
                // bean.setErrorCode(
                //        obj.getErrCode().equals(Constants.ERROR_CODE_0) ? Constants.ERROR_CODE_1000 : obj.getErrCode());
                // bean.setErrorCode(Constants.ERROR_CODE_1000);
                try {
                    // get response message
                    String respMsg = obj.getTextContent(wsObjectBean.getWebservice().getXpathExtension01());
                    if (respMsg != null && !respMsg.isEmpty()) {
                        bean.setUserMsg(respMsg);
                    } else {
                        bean.setUserMsg(obj.getErrCode().equals(Constants.ERROR_CODE_0)
                                ? messageUtil.getMessage("wsDoActionService.success")
                                : messageUtil.getMessage("wsDoActionService.fail.en"));
                    }

                } catch (Exception e) {
                    logger.error("parse description error: ", e);
                    bean.setUserMsg(obj.getErrCode().equals(Constants.ERROR_CODE_0)
                            ? messageUtil.getMessage("wsDoActionService.success")
                            : messageUtil.getMessage("wsDoActionService.fail.en"));
                }

            }
        } else if (action != null && action.getId().getChannelType().equals(Constants.ACTION_CHANNEL_TYPE_FAKE_SMS)) {
            logger.info("Action " + isdn);

            // In-case using fake mo sms
            FakeMo fm = new FakeMo();
            fm.setChannel(action.getChannel());
            fm.setChannelType(action.getId().getChannelType());
            fm.setCreatedTime(CommonUtil.getCurrentTime());
            fm.setIsdn(isdn);
            fm.setPreState(preState);
            fm.setSubserviveCode(serviceCode);
            fm.setSyntax(action.getSyntax());
            fm.setWsId(action.getWsId());
            myvtgService.persistFake(fm);//@nhut update save infor fake MO
            bean.setUserMsg(messageUtil.getMessage("wsDoActionService.success.en"));
        } else if (action != null
                && action.getId().getChannelType().equals(Constants.ACTION_CHANNEL_TYPE_FAKE_MO_TABLE)) {
            // In-case using fake mo data --> Insert new record into data.MO
            // table
            // Mo fm = new Mo();
            // fm.setMsisdn(code + isdn);
            // fm.setActionType(action.getMoActionType());
            // fm.setChannel(action.getChannel() + "");
            // fm.setCommand(action.getSyntax());
            // fm.setParam(null);
            // fm.setReceiveTime(CommonUtil.getCurrentTime());
            //
            // dataService.persist(fm);
            // bean.setUserMsg(messageUtil.getMessage("wsDoActionService.success.en"));
            String rspCode = dataService.callSelcareFakeMO(code + isdn, action.getChannel().toString(),
                    action.getSyntax());
            if (Constants.ERROR_CODE_00.equals(rspCode)) {
                bean.setUserMsg(messageUtil.getMessage("wsDoActionService.success.en"));
            } else {
                bean.setUserMsg(messageUtil.getMessage("wsDoActionService.fail.en"));
            }
        } else if (action != null && action.getId().getChannelType().equals(Constants.ACTION_CHANNEL_TYPE_FAKE_USSD)) {

        }

        // Don't use Subscriber table anymore
        // if (!isUnitelMarket()) {
        // if (actionType == Constants.ACTION_TYPE_REGISTER || actionType ==
        // Constants.ACTION_TYPE_CANCEL) {
        // myvtgService.updateSubcriber(isdn, serviceCode,
        // Constants.SUBCRIBER_STATE_PENDDING);
        // }
        // }
    }

    private SoapWSResponse doCallWebservice(LinkedHashMap<String, Object> params, String isdn,
            WebserviceBean wsObjectBean) {
        if (wsObjectBean != null) {
            WebServiceUtil wsUtil = new WebServiceUtil(wsObjectBean.getWebservice(), environment);
            if (params == null) {
                params = new LinkedHashMap<>();
            }
            params.put("msisdn", getCountryCode() + isdn);
            params.put("xisdn", isdn);
            SoapWSResponse response = wsUtil.callWebService(params, true);
            return response;
        } else {
            return null;
        }
    }

    private SoapWSResponse doCallWebservice(LinkedHashMap<String, Object> params, Webservice wsObjectBean) {
        if (wsObjectBean != null) {
            WebServiceUtil wsUtil = new WebServiceUtil(wsObjectBean, environment);
            if (params == null) {
                params = new LinkedHashMap<>();
            }
            SoapWSResponse response = wsUtil.callWebService(params, false);
            return response;
        } else {
            return null;
        }
    }

    @Override
    public void wsRegisterBuyIsdn(String isdn, String isdnToBuy, ResponseSuccessBean bean) {
        bean.setErrorCode(Constants.ERROR_CODE_0);
        bean.setMessage("Not support now!");
        // Webservice wsObject =
        // myvtgService.getWebserviceByName(Constants.WEBSERVICE_NAME_REGISTER_BUY_ISDN);
        // WebServiceUtil wsUtil = new WebServiceUtil(wsObject, environment);
        // LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        // params.put("isdnToBuy", isdnToBuy);
        // ResponseObj response = wsUtil.callWebService(params, isdn);
        // System.out.println(response);
        // if (response == null) {
        // bean.setErrorCode(Constants.ERROR_CODE_1);
        // bean.setMessage("CALL WEB SERVICE ERROR");
        // } else {
        // bean.setErrorCode(response.getErrCode());
        // }
    }

    @Override
    public void wsDoBuyIsdn(String isdn, int subType, String newIsdn, String serialOfKit, String isdnOfKit,
            BigDecimal price, ResponseSuccessBean bean) throws Exception {
        SubMb subMb = cmpreService.getSubMbBySerial(serialOfKit);
        if (subMb == null) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage("SubMb does not exist with " + serialOfKit);
            return;
        }
        // Gọi webservice trừ tiền với số tiền là giá của số muốn đổi lên
        // Provisioning
        callApiChangeAccountBalance(subType, price.negate(), isdn);

        try {
            // Giải phóng số trong KIT nhập vào (thuê bao tìm được ở ST_01)

            // - Cập nhật CM_PRE2.SUB_MB: Bản ghi hiện tại status = 3
            cmpreService.updateSubMbBySubId(Constants.SUB_MB_STATUS_3, subMb.getSubId());

            // - Cập nhật các bản ghi CM_PRE2.SUB_ISDN_MB where sub_id = ? ->
            // chuyển
            // bản ghi hiện tại về status = 0, end_datetime = sysdate
            cmpreService.updateSubIsdnMb(Constants.SUB_MB_STATUS_0, subMb.getSubId());

            // - Cập nhật SM.STOCK_ISDN_MOBILE where isdn = ? trong KIT về
            // status = 3, owner_type = 1, owner_id = id kho thu hồi (VTC là
            // 105766)
            Object pr = myvtgService.getAppParam(Constants.RETRIEVE_STOCK_ID);
            if (pr == null) {
                throw new SitenException("retrieve_stock_id not exist in CONFIG ");
            }
            smService.updateStockIsdnMobile(Constants.STOCK_ISDN_MOBILE_STATUS_3, Constants.STOCK_ISDN_MOBILE_OWNTYPE_1,
                    new BigDecimal(pr.toString()), subMb.getIsdn());
        } catch (Exception e) {
            logger.error("error", e);
            throw new CallApiCancelSubscriberException(e.getMessage());
        }

        // - Gọi webservice hủy số lên Provisioning
        callApiCancelSubscriber(subType, isdnOfKit, subMb);

        // Đấu lại số mới với thông tin thuê bao KIT lấy từ bước 1 đổi số
        // isdn mới
        // ST_04
        activePrePaid(newIsdn, subType, serialOfKit, subMb);

    }

    private void callApiCancelSubscriber(int subType, String isdn, Object sub) throws CallApiCancelSubscriberException {
        try {
            if (subType == Constants.SUBTYPE_TRATRUOC) {
                SubMb subMb = (SubMb) sub;
                ViettelService rp = com.viettel.bccs.cm.database.DAO.pre.Webservice.Pr.ProvisioningV2.cancelSubscriber(
                        cmpreService.getSession(), isdn, subMb.getProductCode(), subMb.getImsi(), getCountryCode());
                if (!AppUtil.checkResponse(rp)) {
                    throw new SitenException(rp == null ? "cancelSubscriber return " : getResponseDes(rp));
                }
                saveActionLogPr(rp, subType, isdn, subMb.getImsi(), subMb.getSerial(), subMb.getSubId());
                saveActionAuditPre(subMb.getSubId(), Constants.ACTION_CODE_03, "");
            } else {
                co.siten.myvtg.model.cmpos.SubMbPos subMb = (co.siten.myvtg.model.cmpos.SubMbPos) sub;
                ViettelService rp = com.viettel.bccs.cm.database.DAO.Webservice.Pr.ProvisioningV2.cancelSubscriber(
                        cmposService.getSession(), isdn, subMb.getProductCode(), subMb.getImsi(), getCountryCode());
                if (!AppUtil.checkResponse(rp)) {
                    throw new SitenException(rp == null ? "cancelSubscriber return " : getResponseDes(rp));
                }
                saveActionLogPr(rp, subType, isdn, subMb.getImsi(), subMb.getSerial(), subMb.getSubId());
                saveActionAuditPre(subMb.getSubId(), Constants.ACTION_CODE_03, "");
            }

        } catch (Exception e) {
            logger.error("Error", e);
            throw new CallApiCancelSubscriberException(e.getMessage());
        }
    }

    private String getCountryCode() {
        return environment.getRequiredProperty("COUNTRY_CODE");
    }

    public ResponseObj callApiChangeAccountBalance(int subType, BigDecimal price, String isdn) throws Exception {
        ResponseObj response = new ResponseObj();
        Long subId = null;
        try {
            subId = myvtgService.getSubIdByIsdn(isdn);
            if (subId == null) {
                throw new SitenException("isdn " + isdn + " does not exist");
            }
        } catch (Exception e) {
            logger.error("Error", e);
            throw new CallApiChangeAccountBalanceException(e.getMessage());
        }

        if (subType == Constants.SUBTYPE_TRATRUOC) {
            ViettelService rp = ProvisioningV2.changeAccountBalance(isdn, price.doubleValue(), 0L, 0L,
                    Long.parseLong(subType + ""), getCountryCode());
            if (rp != null) {
                response.setResponseStr(rp.getDescription());
                response.setErrCode(rp.getResponseCode());
            }

            if (!AppUtil.checkResponse(rp)) {
                throw new SitenException(rp == null ? "changeAccountBalance return null" : getResponseDes(rp));
            }
            try {
                saveActionLogPr(rp, subType, isdn, null, null, null);
                saveActionAuditPre(subId, Constants.ACTION_CODE_44, Constants.ACTION_AUDIT_DES_REDUCE);
            } catch (Exception e) {
                logger.error(e);
                throw new CallApiCancelSubscriberException(e.getMessage());
            }

        }

        return response;
    }

    public void activeSubcriber(String isdn, int subType, String serialOfKit, String isdnOfKit, Object subMb)
            throws Exception {
        if (subType == Constants.SUBTYPE_TRATRUOC) {
            activePrePaid(isdn, subType, serialOfKit, (SubMb) subMb);
        } else {
            activePostPaid(subType, isdnOfKit, (co.siten.myvtg.model.cmpos.SubMbPos) subMb);
        }
    }

    private void activePrePaid(String isdn, int subType, String serialOfKit, SubMb subMb) throws Exception {
        try {
            logger.debug("activePrePaid " + isdn + " " + subType);
            SubMb newSubMb = new SubMb(subMb);

            newSubMb.setIsdn(isdn);
            newSubMb.setLastNumber(isdn.substring(isdn.length() - 1));
            cmpreService.persist(newSubMb);

            SubIsdnMb isdnMb = new SubIsdnMb();
            isdnMb.setEndDatetime(null);
            isdnMb.setIsdn(isdn);
            isdnMb.setStaDatetime(CommonUtil.getCurrentTime());
            isdnMb.setSubId(new BigDecimal(newSubMb.getSubId()));
            isdnMb.setStatus(BigDecimal.ONE);
            cmpreService.persistSubIsdnMb(isdnMb);

            smService.updateStatusStockIsdnMobile(Constants.STOCK_ISDN_MOBILE_STATUS_2, isdn);
            // - Gọi webserivice đấu nối số mới lên Provisioning

            ViettelService rp = ProvisioningV2.activePrePaidSubscriber(smService.getSession(),
                    productService.getSession(), cmpreService.getSession(), isdn, serialOfKit, subMb.getImsi(),
                    subMb.getOfferId(), subMb.getProductCode(), getCountryCode());
            logger.debug("activePrePaidSubscriber " + rp);
            if (!AppUtil.checkResponse(rp)) {
                throw new SitenException(rp == null ? "activePrePaidSubscriber return " : getResponseDes(rp));
            }
            saveActionLogPr(rp, subType, isdn, subMb.getImsi(), subMb.getSerial(), subMb.getSubId());
            saveActionAuditPre(newSubMb.getSubId(), Constants.ACTION_CODE_12, "");
        } catch (Exception e) {
            logger.error("activePrePaidSubscriber " + e.getMessage());
            CallApiDausoException ex = new CallApiDausoException(subMb, e);
            ex.setSerial(subMb.getSerial());
            throw ex;
        }
    }

    private void activePostPaid(int subType, String isdnOfKit, co.siten.myvtg.model.cmpos.SubMbPos subMb)
            throws Exception {
        co.siten.myvtg.model.cmpos.SubMbPos sub = new co.siten.myvtg.model.cmpos.SubMbPos(subMb);
        logger.debug("activePostPaid " + isdnOfKit);
        sub.setIsdn(isdnOfKit);
        cmposService.persist(sub);

        co.siten.myvtg.model.cmpos.SubIsdnMb isdnMb = new co.siten.myvtg.model.cmpos.SubIsdnMb();
        isdnMb.setEndDatetime(null);
        isdnMb.setIsdn(isdnOfKit);
        isdnMb.setStaDatetime(CommonUtil.getCurrentTime());
        isdnMb.setSubId(new BigDecimal(sub.getSubId()));
        isdnMb.setStatus(BigDecimal.ONE);
        cmposService.persist(isdnMb);

        smService.updateStatusStockIsdnMobile(Constants.STOCK_ISDN_MOBILE_STATUS_2, isdnOfKit);
        ContractOffer oldContractOffer = cmposService.getContractOffer(subMb.getSubId());
        ContractOffer contractOffer = new ContractOffer(oldContractOffer);
        cmposService.persist(contractOffer);

        OfferSub offerSub = cmposService.getOfferSub(subMb.getSubId());
        OfferSub newOfferSub = new OfferSub(offerSub);
        newOfferSub.setSubId(sub.getSubId());
        newOfferSub.setContractOfferId(contractOffer.getContractOfferId());
        cmposService.persist(newOfferSub);

        // - Gọi webserivice đấu nối số mới lên Provisioning
        // String isdnToBuyC = environment.getRequiredProperty("COUNTRY_CODE") +
        // isdnOfKit;
        Contract contract = cmposService.getContractById(subMb.getContractId());
        if (contract == null) {
            throw new SitenException("Contract not found");
        }
        Customer customer = cmposService.getCustomerById(contract.getCustId());
        if (customer == null) {
            throw new SitenException("Customer not found");
        }

        Long billcycleFrom = contract.getBillCycleFrom().longValue();
        Long offerId = contractOffer.getOfferId().longValue();
        try {
            ViettelService rp = com.viettel.bccs.cm.database.DAO.Webservice.Pr.ProvisioningV2.activeSubscriber(
                    cmposService.getSession(), smService.getSession(), productService.getSession(), isdnOfKit,
                    sub.getSubId(), sub.getContractId(), customer.getCustId(), sub.getDeposit().doubleValue(),
                    sub.getRegType(), sub.getQuota().doubleValue(), sub.getActStatus(), sub.getStatus().longValue(),
                    billcycleFrom, customer.getBusType(), sub.getSubType(), offerId, sub.getProductCode(),
                    sub.getSerial(), sub.getImsi(), getCountryCode());
            logger.debug("activePostPaid " + CommonUtil.convertObjectToJsonString(rp));
            if (!AppUtil.checkResponse(rp)) {
                throw new SitenException(rp == null ? "activeSubscriber return " : getResponseDes(rp));
            }
            saveActionLogPr(rp, subType, isdnOfKit, sub.getImsi(), sub.getSerial(), sub.getSubId());
            saveActionAuditPos(sub.getSubId(), Constants.ACTION_CODE_12, "");
        } catch (Exception e) {
            CallApiDausoException ex = new CallApiDausoException(subMb, e);
            ex.setSerial(subMb.getSerial());
            logger.error("activePostPaid ", e);
            throw ex;
        }
    }

    public void saveActionLogPr(ViettelService vs, int subType, String isdn, String imsi, String serial, Long subId) {
        if (subType == Constants.SUBTYPE_TRASAU) {
            ActionLogPr log = new ActionLogPr();
            log.setCreateDate(CommonUtil.getCurrentTime());
            log.setException(vs.getError());
            log.setIsdn(isdn);
            log.setRequest("");
            log.setResponse(CommonUtil.convertObjectToJsonString(vs.getResponse()));
            log.setResponseCode(vs.getResponseCode());
            log.setShopCode(Constants.MYVTG);
            log.setUserName(Constants.MYVTG);
            cmposService.persist(log);
        } else {
            co.siten.myvtg.model.cmpre.ActionLogPr log = new co.siten.myvtg.model.cmpre.ActionLogPr();
            log.setCreateDate(CommonUtil.getCurrentTime());
            log.setException(vs.getError());
            log.setIsdn(isdn);
            log.setRequest("");
            log.setResponse(CommonUtil.convertObjectToJsonString(vs.getResponse()));
            log.setResponseCode(vs.getResponseCode());
            log.setShopCode(Constants.MYVTG);
            log.setUserName(Constants.MYVTG);
            log.setImsi(imsi);
            log.setSerial(serial);
            log.setSubId(subId);
            InetAddress ip;
            try {
                ip = InetAddress.getLocalHost();
                log.setIp(ip.getHostAddress());
            } catch (UnknownHostException e) {
                logger.error("activePostPaid ", e);
            }
            log.setId(cmposService.getNextActionLogPrSeq());
            cmpreService.persistActionLogPr(log);
        }
    }

    private BigDecimal saveActionAuditPre(Long pkId, String actionCode, String description) {
        co.siten.myvtg.model.cmpre.ActionAudit aa = new co.siten.myvtg.model.cmpre.ActionAudit();
        aa.setActionCode(actionCode);
        aa.setIssueDatetime(CommonUtil.getCurrentTime());
        aa.setReasonId(BigDecimal.ZERO);
        aa.setShopCode(Constants.SHOP_CODE_MYVTG);
        aa.setUserName(Constants.SHOP_CODE_MYVTG);
        aa.setPkType(Constants.PK_TYPE_3);
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            aa.setIp(ip.getHostAddress());
        } catch (UnknownHostException e) {
            logger.error("error", e);
        }

        aa.setDescription(description);
        aa.setPkId(new BigDecimal(pkId));
        aa.setValid(null);
        aa.setActionAuditId(cmposService.getNextActionAuditSeq());
        cmpreService.persistActionAudit(aa);
        return aa.getActionAuditId();
    }

    private BigDecimal saveActionAuditPos(Long pkId, String actionCode, String description) {
        co.siten.myvtg.model.cmpos.ActionAudit aa = new co.siten.myvtg.model.cmpos.ActionAudit();
        aa.setIssueDatetime(CommonUtil.getCurrentTime());
        aa.setActionCode(actionCode);
        aa.setReasonId(BigDecimal.ZERO);
        aa.setShopCode(Constants.SHOP_CODE_MYVTG);
        aa.setUserName(Constants.SHOP_CODE_MYVTG);
        aa.setPkType(Constants.PK_TYPE_3);
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            aa.setIp(ip.getHostAddress());
        } catch (UnknownHostException e) {
            logger.error("error", e);
        }

        aa.setDescription(description);
        aa.setPkId(new BigDecimal(pkId));
        aa.setValid(null);
        cmposService.persist(aa);
        return aa.getActionAuditId();
    }

    @Override
    public void wsDoExchangeIsdn(String isdn, String newIsdn, int subType, BigDecimal price, ResponseSuccessBean bean)
            throws Exception {
        // ST01
        // Gọi webservice trừ tiền với số tiền là giá của số muốn đổi lên
        // Provisioning (addOrReduceMoneyProvisioning)
        callApiChangeAccountBalance(subType, price.negate(), isdn);
        Long subId = myvtgService.getSubIdByIsdn(isdn);
        // ST02 Cần xem xét thêm

        if (subType == Constants.SUBTYPE_TRATRUOC) {
            // ST03 Giải phóng số hiện tại:

            // - Cập nhật CM_PRE.SUB_MB where isdn = [isdn] and status = 2
            // o Gán lại status = 3 (Hủy)
            SubMb subMb = cmpreService.getSubMbByIsdnAndStatus(isdn, Constants.SUB_MB_STATUS_ACTIVE);
            subMb.setStatus(Constants.SUB_MB_STATUS_3);
            cmpreService.update(subMb);

            // - Cập nhật các bản ghi SUB_ISDN_MB của sub_id = [subId] về
            // status = 0 (Hủy)
            cmpreService.updateSubIsdnMb(Constants.SUB_MB_STATUS_0, subId);

            // - Cập nhật SM.STOCK_ISDN_MOBILE với isdn = [isdn] về:
            // o status = 3
            // o owner_type = 1
            // o owner_id = [retrieve_stock_id] (lấy từ bảng APP_PARAM với
            // tên retrieve_stock_id)
            Object pr = myvtgService.getAppParam(Constants.RETRIEVE_STOCK_ID);
            if (pr == null) {
                throw new SitenException("retrieve_stock_id not exist in CONFIG ");
            }
            smService.updateStockIsdnMobile(Constants.STOCK_ISDN_MOBILE_STATUS_3, Constants.STOCK_ISDN_MOBILE_OWNTYPE_1,
                    new BigDecimal(pr.toString()), isdn);
            // Hủy số cũ
            callApiCancelSubscriber(subType, isdn, subMb);

            // Đấu lại số mới:
            activePrePaid(newIsdn, subType, subMb.getSerial(), subMb);

        } else if (subType == Constants.SUBTYPE_TRASAU) {
            // ST03 Giải phóng số hiện tại:

            co.siten.myvtg.model.cmpos.SubMbPos subMb = cmposService.getSubMbByIsdnAndStatus(isdn,
                    Constants.SUB_MB_STATUS_ACTIVE);
            subMb.setStatus(Constants.SUB_MB_STATUS_3);
            cmposService.update(subMb);

            cmposService.updateSubIsdnMb(Constants.SUB_MB_STATUS_0, subId);
            OfferSub offerSub = cmposService.getOfferSub(subId);
            offerSub.setStatus(Constants.SUB_MB_STATUS_0);
            cmposService.update(offerSub);
            ContractOffer os = cmposService.getContractOffer(subId);
            os.setStatus(BigDecimal.ZERO);
            cmposService.update(os);

            Object pr = myvtgService.getAppParam(Constants.RETRIEVE_STOCK_ID);
            if (pr == null) {
                throw new SitenException("retrieve_stock_id not exist in CONFIG ");
            }
            smService.updateStockIsdnMobile(Constants.STOCK_ISDN_MOBILE_STATUS_3, Constants.STOCK_ISDN_MOBILE_OWNTYPE_1,
                    new BigDecimal(pr.toString()), isdn);
            // Hủy số cũ
            callApiCancelSubscriber(subType, isdn, subMb);

            // ST_04
            // Đấu lại số mới:
            activePostPaid(subType, newIsdn, subMb);
        }

    }

    @Override
    public void wsDoChangeSIM(String language, String isdn, int subType, String newIsdn, String serialOfKit,
            String isdnOfKit, ResponseSuccessBean bean) throws Exception {
        if (isUnitelMarket()) {
            if (serialOfKit == null || serialOfKit.length() < 10) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setUserMsg("The length of the serial is not correct");
                logger.error("The length of the serial is not correct");
                return;
            }

            Webservice wsObject = myvtgService.getWebserviceByName(Constants.WEBSERVICE_NAME_CHANGE_SIM_UNITEL);
            WebServiceUtil wsUtil = new WebServiceUtil(wsObject, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("isdn", isdn);
            params.put("serial", serialOfKit.substring(serialOfKit.length() - 10));

            SoapWSResponse wsResponse = wsUtil.callWebService(params, true);

            // parser result
            if (wsResponse != null) {
                try {
                    String error = wsResponse.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                    if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                        String respStr = wsResponse
                                .getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                        try {
                            SoapWSResponse response = new SoapWSResponse(respStr);
                            String respCode = response
                                    .getTextContent("/Envelope/Body/chageSIMResponse/return/responseCode");
                            String description = response
                                    .getTextContent("/Envelope/Body/chageSIMResponse/return/description");

                            // TODO: fix code for Unitel
                            String xPathDes = "/Envelope/Body/chageSIMResponse/return/messageEn";
                            if (language.equalsIgnoreCase(Constants.LOCAL_LANGUAGE)) {
                                xPathDes = "/Envelope/Body/chageSIMResponse/return/messageLa";
                            }
                            String message = response.getTextContent(xPathDes);

                            bean.setUserMsg(message);
                            bean.setMessage(description);
                            if (respCode != null && Constants.ERROR_CODE_01.equalsIgnoreCase(respCode)) {
                                bean.setErrorCode(Constants.ERROR_CODE_0);
                            } else {
                                bean.setErrorCode(Constants.ERROR_CODE_1);
                            }

                        } catch (Exception e) {
                            bean.setErrorCode(Constants.ERROR_CODE_1);
                            logger.error(e.getMessage(), e);
                        }

                    } else {
                        bean.setErrorCode(Constants.ERROR_CODE_1);
                    }
                } catch (Exception e) {
                    bean.setErrorCode(Constants.ERROR_CODE_1);
                    logger.error(e.getMessage(), e);
                }
            } else {
                bean.setErrorCode(Constants.ERROR_CODE_1);
            }
            TransactionLog log = wsUtil.getTransLog();
            myvtgService.persistTransactionLog(log);

            return;
        }

        // StockSim stockSim = smService.getStockSimBySerial(serialOfKit);
        // if (stockSim == null) {
        // bean.setErrorCode(Constants.ERROR_CODE_2);
        // bean.setMessage(messageUtil.getMessage("wsDoChangeSIM.error.2"));
        // return;
        // }
        // String imsi = stockSim.getImsi();
        // String msisdn = environment.getRequiredProperty("COUNTRY_CODE") +
        // isdn;
        // // Trả trước
        // if (subType == Constants.SUBTYPE_TRATRUOC) {
        // // ST_02 Đấu nối số thuê bao hiện tại với thông tin của SIM (Kit
        // // mới)
        // // mới:
        // SubMb subMb = cmpreService.getSubMbByIsdnAndStatus(isdn,
        // Constants.SUB_MB_STATUS_ACTIVE);
        // if (subMb == null)
        // throw new SitenException("SubMb not exist with isdn " + isdn);
        // Long subId = subMb.getSubId();
        // String oldImsi = subMb.getImsi();
        // String oldSerial = subMb.getSerial();
        //
        // subMb.setSerial(serialOfKit);
        // subMb.setImsi(imsi);
        //
        // // ST_03 Xóa thông tin gắn với SIM cũ:
        // SubSimMb sumSimMb =
        // cmpreService.getSubSimMbBySubIdAndStatus(subMb.getSubId(),
        // Constants.SUB_MB_STATUS_1);
        // if (sumSimMb == null)
        // throw new SitenException("sumSimMb not exist with subId " +
        // subMb.getSubId());
        // sumSimMb.setStatus(0);
        // sumSimMb.setEndDatetime(CommonUtil.getCurrentTime());
        // cmpreService.update(sumSimMb);
        // // ST_04: Insert thêm bản ghi mới vào SUB_SIM_MB với các thông tin
        // // IMSI,
        // // serial mới từ STOCK_SIM tìm được ở trên
        // SubSimMb newSubSimMb = new SubSimMb();
        // newSubSimMb.setImsi(imsi);
        // newSubSimMb.setSerial(serialOfKit);
        // newSubSimMb.setStaDatetime(CommonUtil.getCurrentTime());
        // newSubSimMb.setSubId(new BigDecimal(subId));
        // newSubSimMb.setStatus(1);
        // cmpreService.persist(newSubSimMb);
        //
        // // ST_05:Ghi log vào bảng CM_PRE.ACTION_AUDIT với action_code = 112
        // BigDecimal actionAuditId = saveActionAuditPre(subId,
        // Constants.ACTION_CODE_112,
        // Constants.ACTION_AUDIT_DES_CHANGE);
        //
        // // ST_06: Ghi log vào CM_PRE.ACTION_DETAIL
        // ActionDetail actionDetailI = new ActionDetail(actionAuditId,
        // Constants.COL_NAME_IMSI,
        // CommonUtil.getCurrentTime(), imsi, oldImsi, new BigDecimal(subId),
        // Constants.TABLE_NAME_SUB_MB);
        // cmpreService.persist(actionDetailI);
        //
        // ActionDetail actionDetailS = new ActionDetail(actionAuditId,
        // Constants.COL_NAME_SERIAL,
        // CommonUtil.getCurrentTime(), imsi, oldImsi, new BigDecimal(subId),
        // Constants.TABLE_NAME_SUB_MB);
        // cmpreService.persist(actionDetailS);
        //
        // // ST_07 Cập nhật lại trạng thái của Kit về HỦY:
        // cmpreService.updateSubMbByIsdn(Constants.SUB_MB_STATUS_3, msisdn,
        // CommonUtil.getCurrentTime());
        //
        // // ST_08: Trả số trên Kit về trạng thái Hủy và về kho thu
        // // hồi:
        // Object pr = myvtgService.getAppParam(Constants.RETRIEVE_STOCK_ID);
        // if (pr == null)
        // throw new SitenException("retrieve_stock_id not exist in CONFIG ");
        // smService.updateStockIsdnMobile(Constants.STOCK_ISDN_MOBILE_STATUS_3,
        // Constants.STOCK_ISDN_MOBILE_OWNTYPE_1,
        // new BigDecimal(pr.toString()), isdnOfKit);
        //
        // // ST_09 Gọi webservice để gọi lệnh đổi sim lên tổng đài
        // // (changeSimProvisioning):
        //
        // callApiChangeSim(subId, subType, isdnOfKit, subMb.getProductCode(),
        // oldImsi, imsi, oldSerial, serialOfKit);
        //
        // } else {
        // // Trả sau
        //
        // // ST_02 Đấu nối số thuê bao hiện tại với thông tin của SIM (Kit
        // // mới)
        // // mới:
        // co.siten.myvtg.model.cmpos.SubMb subMb =
        // cmposService.getSubMbByIsdnAndStatus(isdn,
        // Constants.SUB_MB_STATUS_ACTIVE);
        // if (subMb == null)
        // throw new SitenException("SubMb not exist with isdn " + isdn);
        //
        // Long subId = subMb.getSubId();
        // String oldImsi = subMb.getImsi();
        // String oldSerial = subMb.getSerial();
        //
        // subMb.setSerial(serialOfKit);
        // subMb.setImsi(imsi);
        //
        // // ST_03 Xóa thông tin gắn với SIM cũ:
        // co.siten.myvtg.model.cmpos.SubSimMb sumSimMb =
        // cmposService.getSubSimMbBySubIdAndStatus(subMb.getSubId(),
        // Constants.SUB_MB_STATUS_1);
        // sumSimMb.setStatus(0);
        // sumSimMb.setEndDatetime(CommonUtil.getCurrentTime());
        // cmposService.update(sumSimMb);
        // // ST_04: Insert thêm bản ghi mới vào SUB_SIM_MB với các thông tin
        // // IMSI,
        // // serial mới từ STOCK_SIM tìm được ở trên
        // co.siten.myvtg.model.cmpos.SubSimMb newSubSimMb = new
        // co.siten.myvtg.model.cmpos.SubSimMb();
        // newSubSimMb.setImsi(imsi);
        // newSubSimMb.setSerial(serialOfKit);
        // newSubSimMb.setStaDatetime(CommonUtil.getCurrentTime());
        // newSubSimMb.setSubId(new BigDecimal(subId));
        // newSubSimMb.setStatus(1);
        // cmposService.persist(newSubSimMb);
        //
        // // ST_05:Ghi log vào bảng CM_PRE.ACTION_AUDIT với action_code = 112
        // BigDecimal actionAuditId = saveActionAuditPos(subId,
        // Constants.ACTION_CODE_112,
        // Constants.ACTION_AUDIT_DES_CHANGE);
        //
        // // ST_06: Ghi log vào CM_PRE.ACTION_DETAIL
        // co.siten.myvtg.model.cmpos.ActionDetail actionDetailI = new
        // co.siten.myvtg.model.cmpos.ActionDetail(
        // actionAuditId, Constants.COL_NAME_IMSI, CommonUtil.getCurrentTime(),
        // imsi, oldImsi,
        // new BigDecimal(subId), Constants.TABLE_NAME_SUB_MB);
        // cmposService.persist(actionDetailI);
        //
        // co.siten.myvtg.model.cmpos.ActionDetail actionDetailS = new
        // co.siten.myvtg.model.cmpos.ActionDetail(
        // actionAuditId, Constants.COL_NAME_SERIAL,
        // CommonUtil.getCurrentTime(), imsi, oldImsi,
        // new BigDecimal(subId), Constants.TABLE_NAME_SUB_MB);
        // cmposService.persist(actionDetailS);
        //
        // // ST_07 Cập nhật lại trạng thái của Kit về HỦY:
        // cmposService.updateSubMbByIsdn(Constants.SUB_MB_STATUS_3, msisdn,
        // CommonUtil.getCurrentTime());
        //
        // // ST_08: Trả số trên Kit về trạng thái Hủy và về kho thu
        // // hồi:
        // Object pr = myvtgService.getAppParam(Constants.RETRIEVE_STOCK_ID);
        // smService.updateStockIsdnMobile(Constants.STOCK_ISDN_MOBILE_STATUS_3,
        // Constants.STOCK_ISDN_MOBILE_OWNTYPE_1,
        // new BigDecimal(pr.toString()), isdnOfKit);
        //
        // // ST_09 Gọi webservice để gọi lệnh đổi sim lên tổng đài
        // // (changeSimProvisioning):
        //
        // callApiChangeSim(subId, subType, isdnOfKit, subMb.getProductCode(),
        // oldImsi, imsi, oldSerial, serialOfKit);
        // }
    }

    private void callApiChangeSim(long subId, int subType, String isdn, String productCode, String oldImsi,
            String newImsi, String oldSerial, String newSerial) throws Exception {
        try {
            if (subType == Constants.SUBTYPE_TRATRUOC) {
                ViettelService rp = com.viettel.bccs.cm.database.DAO.pre.Webservice.Pr.ProvisioningV2.changeSim(
                        smService.getSession(), isdn, productCode, oldImsi, oldSerial, newImsi, newSerial,
                        getCountryCode());
                if (!AppUtil.checkResponse(rp)) {
                    throw new SitenException(rp == null ? "changeSim return null" : getResponseDes(rp));
                }
                saveActionLogPr(rp, subType, isdn, newImsi, newSerial, subId);
            } else {
                ViettelService rp = com.viettel.bccs.cm.database.DAO.Webservice.Pr.ProvisioningV2.changeSim(
                        smService.getSession(), isdn, productCode, oldImsi, oldSerial, newImsi, newSerial,
                        getCountryCode());
                if (!AppUtil.checkResponse(rp)) {
                    throw new SitenException(rp == null ? "changeSim return null" : getResponseDes(rp));
                }
                saveActionLogPr(rp, subType, isdn, newImsi, newSerial, subId);
            }

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void wsDoLockCallGoIsdn(String isdn, int subType, ResponseSuccessBean bean) throws Exception {
        if (subType == Constants.SUBTYPE_TRATRUOC) {
            // Trả trước
            SubMb subMb = cmpreService.getSubMbByIsdnAndStatus(isdn, Constants.SUB_MB_STATUS_ACTIVE);
            if (subMb == null) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                return;
            } else if (!"00".equalsIgnoreCase(subMb.getActStatus())) {
                bean.setErrorCode(Constants.ERROR_CODE_2);
                return;
            }

            subMb.setActStatus(Constants.ACT_STATUS_10);
            cmpreService.update(subMb);

            saveActionAuditPre(subMb.getSubId(), Constants.ACTION_CODE_06, Constants.ACTION_AUDIT_DES_BLOCK);
            callApiBlockOpenSub(subType, subMb.getProductCode(), isdn, "300", subMb.getActStatus(), "1",
                    environment.getRequiredProperty("COUNTRY_CODE"));
        } else {
            // Trả sau
            co.siten.myvtg.model.cmpos.SubMbPos subMb = cmposService.getSubMbByIsdnAndStatus(isdn,
                    Constants.SUB_MB_STATUS_ACTIVE);
            if (subMb == null) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                return;
            } else if (!"00".equalsIgnoreCase(subMb.getActStatus())) {
                bean.setErrorCode(Constants.ERROR_CODE_2);
                return;
            }
            subMb.setActStatus(Constants.ACT_STATUS_100);
            cmposService.update(subMb);
            saveActionAuditPos(subMb.getSubId(), Constants.ACTION_CODE_06, Constants.ACTION_AUDIT_DES_BLOCK);
            callApiBlockOpenSub(subType, subMb.getProductCode(), isdn, "300", subMb.getActStatus(), "1",
                    environment.getRequiredProperty("COUNTRY_CODE"));
        }
    }

    private String getResponseDes(ViettelService response) {
        if (response == null) {
            return "";
        }
        Object des = response.get(environment.getProperty("pro_des", "description"));
        if (des != null) {
            return des.toString();
        } else {
            return "";
        }

    }

    private void callApiBlockOpenSub(int subType, String productCode, String isdn, String modificationType,
            String oldActStatus, String numWay, String countryCode) throws Exception {
        if (subType == Constants.SUBTYPE_TRATRUOC) {
            ViettelService rp = com.viettel.bccs.cm.database.DAO.pre.Webservice.Pr.ProvisioningV2.blockOpenSub(
                    cmpreService.getSession(), productCode, isdn, modificationType, oldActStatus, numWay, countryCode);
            if (!AppUtil.checkResponse(rp)) {
                throw new SitenException(rp == null ? "blockOpenSub return null" : getResponseDes(rp));
            }
        } else {
            ViettelService rp = com.viettel.bccs.cm.database.DAO.Webservice.Pr.ProvisioningV2.blockOpenSubscriber(
                    cmposService.getSession(), isdn, productCode, oldActStatus, modificationType, numWay, countryCode);
            if (!AppUtil.checkResponse(rp)) {
                throw new SitenException(rp == null ? "blockOpenSubscriber return null" : getResponseDes(rp));
            }
        }
    }

    @Override
    public void wsDoExchangePostage(String isdn, int subType, BigDecimal exchangedMark, BigDecimal money,
            ResponseSuccessBean bean) throws Exception {
        Long subId = myvtgService.getSubIdByIsdn(isdn);
        if (subType == Constants.SUBTYPE_TRATRUOC) {
            SubCycleRe scr = myvtgService.getSubCycleReBySubId(new BigDecimal(subId));
            BigDecimal curMarkExchange = scr.getMarkExchange();
            if (curMarkExchange.compareTo(exchangedMark) < 0) {
                bean.setErrorCode(Constants.ERROR_CODE_2);
                bean.setMessage(Constants.NOT_ENOUGH_MARK_EXCHANGE);
                return;
            }
            scr.setMarkExchange(curMarkExchange.subtract(exchangedMark));
            myvtgService.updateSubCycleRe(scr);

            // Insert log vào bảng SUB_MARK_AUDIT
            SubMarkAudit sma = new SubMarkAudit();
            sma.setSubId(subId);
            sma.setSubType(subType + "");
            sma.setCreateDate(CommonUtil.getCurrentTime());
            sma.setMarkRate(scr.getMarkRate());
            sma.setMarkExchange(scr.getMarkExchange());
            sma.setOrgExchange(curMarkExchange);
            sma.setOrgRate(scr.getMarkRate());
            sma.setUserCreate(Constants.MYVTG);
            sma.setMonth(CommonUtil.getFirstDateOfCurrent());
            Object markTypeId = myvtgService.getAppParam(Constants.MARK_TYPE_ID_EXCH_POST);
            if (markTypeId == null) {
                sma.setMarkTypeId(BigDecimal.ZERO);
            } else {
                sma.setMarkTypeId(new BigDecimal(markTypeId.toString()));
            }
            sma.setStatus("1");
            myvtgService.persistSubMarkAudit(sma);

            // Thực hiện cộng cước: gọi đến hệ thống PROVISIONNING
            callApiChangeAccountBalance(subType, money, isdn);
        } else {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage("Not support post paid subscriber");
        }

    }

    @Override
    public void wsGetAppConfig(ResponseSuccessBean bean, Object version) throws Exception {
        String appPr;
        if (version == null) {
            appPr = myvtgService.getAppParam(Constants.APP_CLIENT_CONFIG);
        } else {
            appPr = myvtgService.getAppParamByVersion(Constants.APP_CLIENT_CONFIG, version.toString());
        }

        if (appPr == null) {
            bean.setWsResponse(null);
            bean.setMessage("getAppParam Not found" + Constants.APP_CLIENT_CONFIG);
        }

        ModelAppConfigWrapper modelAppConfigWrapper = new ModelAppConfigWrapper(appPr);
        bean.setWsResponse(modelAppConfigWrapper);
    }

    @Override
    public void wsDoBuyData(String isdn, String packageCode, Integer subType, Long price, Long volume,
            ResponseSuccessBean bean) throws Exception {
        VSelfcareDataToBuy toBuy = dataService.getVSelfcareDataToBuyByName(packageCode);
        Boolean founded = false;
        String delim = "|";
        String regex = "(?<!\\\\)" + Pattern.quote(delim);
        if (toBuy != null && toBuy.getUnit() != null) {
            String[] toBuyArr = toBuy.getUnit().split(regex);
            for (int i = 0; i < toBuyArr.length; i++) {
                String items[] = toBuyArr[i].split(";");
                try {
                    Long _price = Long.parseLong(items[0]);
                    Long _volume = Long.parseLong(items[1]);
                    if (_price == price && _volume == volume) {
                        founded = true;
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

            }
        }

        if (founded == true) {
            // call webservice/fakeMO to buy more data
            doActionService(isdn, packageCode, Constants.ACTION_TYPE_BUY_DATA, null, 1, bean);
        } else {
            // in case re-register data package
            // TODO: define REGISTERED_STATE = 1
            doActionService(isdn, packageCode, Constants.ACTION_TYPE_REGISTER, null, 1, bean);
        }
    }

    private Boolean isUnitelMarket() {
        String marketName = environment.getProperty(Constants.MARKET_NAME_CONFIG);
        return marketName != null && marketName.equalsIgnoreCase(Constants.MARKET_NAME_UNITEL);
    }

    private Boolean isMetfoneMarket() {
        String marketName = environment.getProperty(Constants.MARKET_NAME_CONFIG);
        return marketName != null && marketName.equalsIgnoreCase(Constants.MARKET_NAME_METFONE);
    }

    private Boolean isMovitel() {
        String marketName = environment.getProperty(Constants.MARKET_NAME_CONFIG);
        return marketName != null && marketName.equalsIgnoreCase(Constants.MARKET_NAME_MOVITEL);
    }

    private Boolean isLTSub(String productCode) {
        String netSimPackageList = (String) myvtgService.getAppParam(Constants.NET_SIM_PACKAGE_LIST);
        return netSimPackageList != null && netSimPackageList.contains(";" + productCode + ";");
    }

    @Override
    public void wsGetAccountsDetail(String isdn, Object language, ResponseSuccessBean bean) throws Exception {
        String languageStr = Constants.DEFAULT_LANGUAGE;
        if (language != null) {
            languageStr = language.toString();
        } else {
            Object appPr = myvtgService.getAppParam(Constants.APP_PARAM_DEFAULT_LANGUAGE);
            if (appPr != null) {
                languageStr = appPr.toString();
            }
        }

        Sub sub = myvtgService.findByIsdn(isdn);
        if (sub == null) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setUserMsg("Sub " + isdn + " is not exist.");
            return;
        }

        String titleInfo = messageUtil.getMessage("GeneralTitle." + languageStr);
        String titleAcc = messageUtil.getMessage("AcccountTitle." + languageStr);
        String titlePricePlan = messageUtil.getMessage("PricePlan." + languageStr);
        String expiredStr = messageUtil.getMessage("ExpiredDate." + languageStr);

        AccountDetailBean accInfo = new AccountDetailBean();
        accInfo.setTitle(titleInfo);

        List<AccountDetailBean> detailList = new LinkedList<>();
        AccountDetailBean accDetail = new AccountDetailBean();
        accDetail.setTitle(titleAcc);

        // AccountDetailBean pricePlanDetail = new AccountDetailBean();
        // pricePlanDetail.setTitle(titlePricePlan);
        detailList.add(accInfo);
        detailList.add(accDetail);
        // detailList.add(pricePlanDetail);

        if (Constants.SUBTYPE_TRATRUOC == sub.getSubType()) {

            getPrePaidAccountsDetailSumByAccountType(sub, expiredStr, languageStr, titleAcc, titlePricePlan, detailList);
        } else {

            getPostPaidAccountsDetail(sub, expiredStr, languageStr, titleAcc, titlePricePlan, detailList);
        }

        bean.setWsResponse(detailList);
    }

    @Override
    public void wsGetAccountsExchangeDetail(String isdn, Object language, ResponseSuccessBean bean) throws Exception {
        String languageStr = Constants.DEFAULT_LANGUAGE;
        if (language != null) {
            languageStr = language.toString();
        } else {
            Object appPr = myvtgService.getAppParam(Constants.APP_PARAM_DEFAULT_LANGUAGE);
            if (appPr != null) {
                languageStr = appPr.toString();
            }
        }

        Sub sub = myvtgService.findByIsdn(isdn);
        if (sub == null) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setUserMsg("Sub " + isdn + " is not exist.");
            return;
        }

        String titleInfo = messageUtil.getMessage("GeneralTitle." + languageStr);
        String titleAcc = messageUtil.getMessage("AcccountTitle." + languageStr);
        String titlePricePlan = messageUtil.getMessage("PricePlan." + languageStr);
        String expiredStr = messageUtil.getMessage("ExpiredDate." + languageStr);

        AccountDetailBean accInfo = new AccountDetailBean();
        accInfo.setTitle(titleInfo);

        List<AccountDetailBean> detailList = new LinkedList<>();
        AccountDetailBean accDetail = new AccountDetailBean();
        accDetail.setTitle(titleAcc);
        detailList.add(accInfo);
        detailList.add(accDetail);
        getPrePaidAccountsExchangeDetailSumByAccountType(sub, expiredStr, languageStr, titleAcc, titlePricePlan, detailList);

        bean.setWsResponse(detailList);
    }

    private SimpleDateFormat getDateFormatForAccountDetail() {
        String formatShow = environment.getProperty("pro_date_format_accountdetail_show");
        if (formatShow != null) {
            return AppUtil.getDateFormat(formatShow);
        } else {
            return AppUtil.getDateFormat("yyyy/MM/dd");
        }
    }

    private SimpleDateFormat getDateFormatForParserAccountDetail() {
        String formatShow = environment.getProperty("pro_date_format_accountdetail");
        if (formatShow != null) {
            return AppUtil.getDateFormat(formatShow);
        } else {
            return AppUtil.getDateFormat("yyyyMMdd");
        }
    }

    private Object getPostPaidAccountsDetail(Sub sub, String expiredStr, String languageStr, String titleAcc,
            String titlePricePlan, List<AccountDetailBean> detailList) {
        List<DataPackageBean> datalist = getDataPackageNameAndExpired(sub, languageStr, null);
        GetCallAccountDetailPreBean acc = getPostPaidChargingDetail(sub);
        List<AccountDetailValueBean> detailValueBeanList = new LinkedList<>();
        List<AccountDetailValueBean> pricePlanBeanList = new LinkedList<>();
        SimpleDateFormat dateFormat = getDateFormatForAccountDetail();
        DecimalFormat dfCurrency = new DecimalFormat("#,###.00");
        DecimalFormat dfData = new DecimalFormat("#,###");

        // BigDecimal remainingData = getPosPaidDataRemain(sub);
        // DataPackageBean expiredDate = getMinExpiredDataPackage(datalist);
        //
        // if (!CommonUtil.isEmpty(datalist)) {
        // for (DataPackageBean data : datalist) {
        // pricePlanBeanList.add(new AccountDetailValueBean(data.getName(),
        // null,
        // expiredStr + dateFormat.format(data.getExpiredDate())));
        // }
        // }
        //
        // if (BigDecimal.ZERO.compareTo(acc.getPrePost()) != 0)
        // detailValueBeanList
        // .add(new
        // AccountDetailValueBean(messageUtil.getMessage("Account.HotCharge." +
        // languageStr),
        // dfCurrency.format(acc.getPrePost()), null));
        // if (BigDecimal.ZERO.compareTo(acc.getDebPost()) != 0)
        // detailValueBeanList.add(new
        // AccountDetailValueBean(messageUtil.getMessage("Account.Debt." +
        // languageStr),
        // dfCurrency.format(acc.getDebPost()), null));
        // if (BigDecimal.ZERO.compareTo(acc.getDebPreMonthPost()) != 0)
        // detailValueBeanList
        // .add(new
        // AccountDetailValueBean(messageUtil.getMessage("Account.PreviousMonthDebt."
        // + languageStr),
        // dfCurrency.format(acc.getDebPreMonthPost()), null));
        /*
         * if (BigDecimal.ZERO.compareTo(remainingData) != 0)
         * detailValueBeanList.add(new AccountDetailValueBean(
         * messageUtil.getMessage("Account.RemainingData." + languageStr),
         * dfData.format(remainingData) + "MB", expiredDate == null ? null :
         * dateFormat.format(expiredDate.getExpiredDate())));
         */
        // List<AccountDetailValueBean> detailValueBeanList = new
        // LinkedList<>();
//        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + sub.getIsdn();
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
        try {
//            channel = proService.getChannel();
//
//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);

            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccountDetailValueBean> generalList = new LinkedList<>(); /// getGeneralInfoList(response,
                    /// languageStr);

                    if (BigDecimal.ZERO.compareTo(acc.getPrePost()) != 0) {
                        generalList.add(
                                new AccountDetailValueBean(messageUtil.getMessage("Account.HotCharge." + languageStr),
                                        dfCurrency.format(acc.getPrePost()) + " $", null));
                    }
                    if (BigDecimal.ZERO.compareTo(acc.getDebPost()) != 0) {
                        generalList
                                .add(new AccountDetailValueBean(messageUtil.getMessage("Account.Debt." + languageStr),
                                                dfCurrency.format(acc.getDebPost()) + " $", null));
                    }
                    if (BigDecimal.ZERO.compareTo(acc.getDebPreMonthPost()) != 0) {
                        generalList.add(new AccountDetailValueBean(
                                messageUtil.getMessage("Account.PreviousMonthDebt." + languageStr),
                                dfCurrency.format(acc.getDebPreMonthPost()) + " $", null));
                    }

                    detailList.get(0).setValues(generalList);

                    List<AccConfig> accConfigList = myvtgService.getAllAccConfigByLanguage(languageStr);
                    detailValueBeanList = getAccountDetailAccListSumByAccountType(response, accConfigList, expiredStr);
                    detailList.get(1).setValues(detailValueBeanList);

                    String pricePlanIdKey = environment.getRequiredProperty("balanceIdKey");
                    // String ids = ((String) response.get(pricePlanIdKey));

                }
            }
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("getPrePaidChargingDetail(isdn = " + sub.getIsdn() + ")", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        }
        return detailList;

        // detailList.get(1).setValues(pricePlanBeanList);
        // detailList.get(0).setValues(detailValueBeanList);
        // return null;
    }

    private List<AccountDetailBean> getPrePaidAccountsDetail(Sub sub, String titleDate, String language,
            String titleAcc, String titlePricePlan, List<AccountDetailBean> detailList) {

        List<AccountDetailValueBean> detailValueBeanList = new LinkedList<>();
//        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + sub.getIsdn();
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
        try {
//            channel = proService.getChannel();

//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);
            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccountDetailValueBean> generalList = getGeneralInfoList(response, language);
                    detailList.get(0).setValues(generalList);

                    List<AccConfig> accConfigList = myvtgService.getAllAccConfigByLanguage(language);
                    detailValueBeanList = getAccountDetailAccList(response, accConfigList, titleDate);
                    detailList.get(1).setValues(detailValueBeanList);

                    String pricePlanIdKey = environment.getRequiredProperty("balanceIdKey");
                    String ids = ((String) response.get(pricePlanIdKey));
                    List<DataPricePlanConfig> ppConfigList = myvtgService.getAllPricePlanConfig(sub.getSimType());
                    /*
                     * List<AccountDetailValueBean> provisionPricePlanBeans =
                     * getDetailAccountPricePlanList(response, ppConfigList,
                     * titleDate, language);
                     * detailList.get(2).setValues(provisionPricePlanBeans);
                     */
                }
            }
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("getPrePaidChargingDetail(isdn = " + sub.getIsdn() + ")", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        }
        return detailList;
    }

    private List<AccountDetailBean> getPrePaidAccountsDetailSumByAccountType(Sub sub, String titleDate, String language,
            String titleAcc, String titlePricePlan, List<AccountDetailBean> detailList) {
        Long t1 = System.currentTimeMillis();
        List<AccountDetailValueBean> detailValueBeanList = new LinkedList<>();
        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + sub.getIsdn();
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
        try {
//            channel = proService.getChannel();

//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);
            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccountDetailValueBean> generalList = getGeneralInfoList(response, language);
                    detailList.get(0).setValues(generalList);

                    List<AccConfig> accConfigList = myvtgService.getAllAccConfigByLanguage(language);
                    detailValueBeanList = getAccountDetailAccListSumByAccountType(response, accConfigList, titleDate);
                    detailList.get(1).setValues(detailValueBeanList);

                    String pricePlanIdKey = environment.getRequiredProperty("balanceIdKey");
                    String ids = ((String) response.get(pricePlanIdKey));
                    List<DataPricePlanConfig> ppConfigList = myvtgService.getAllPricePlanConfig(sub.getSimType());
                    /*
                     * List<AccountDetailValueBean> provisionPricePlanBeans =
                     * getDetailAccountPricePlanList(response, ppConfigList,
                     * titleDate, language);
                     * detailList.get(2).setValues(provisionPricePlanBeans);
                     */
                }
            }

            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("getPrePaidChargingDetail(isdn = " + sub.getIsdn() + ")", e);
            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } finally {
            logger.info("Time process getPrePaidAccountsDetailSumByAccountType" + (System.currentTimeMillis() - t1) + "ms");
        }
        return detailList;
    }

    private List<AccountDetailBean> getPrePaidAccountsExchangeDetailSumByAccountType(Sub sub, String titleDate, String language,
            String titleAcc, String titlePricePlan, List<AccountDetailBean> detailList) {
        Long t1 = System.currentTimeMillis();
        List<AccountDetailValueBean> detailValueBeanList = new LinkedList<>();
        ViettelService request = new ViettelService();
        ViettelService response = new ViettelService();
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + sub.getIsdn();
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
        try {
//            channel = proService.getChannel();

//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);
            // send COMMAND, dung ham sendAll cua lib
            response = ServiceClient.getInforSub(msisdn);

            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    List<AccountDetailValueBean> generalList = getGeneralInfoList(response, language);
                    detailList.get(0).setValues(generalList);

                    List<AccConfig> accConfigList = myvtgService.getAllAccConfigByLanguage(language);
                    detailValueBeanList = getAccountDetailAccListSumByAccountExchangeType(response, accConfigList, titleDate);
                    detailList.get(1).setValues(detailValueBeanList);

                }
            }

            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } catch (Exception e) {
            logger.error("getPrePaidChargingDetail(isdn = " + sub.getIsdn() + ")", e);
            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        } finally {
            logger.info("Time process getPrePaidAccountsDetailSumByAccountType" + (System.currentTimeMillis() - t1) + "ms");
        }
        return detailList;
    }

    private AccountDetailValueBean getDetailGeneralInfo(String key, String language, ViettelService response) {
        String msisdnKey = environment.getRequiredProperty(key);
        String value = ((String) response.get(msisdnKey));
        if (CommonUtil.isEmpty(value) || "null".equalsIgnoreCase(value)) {
            return null;
        }
        if ("Account.STATUS".equals(key)) {
            value = messageUtil.getMessage(key + "." + value + "." + language);
        }
        //daibq bo sung
        if ("Account.ACTIVE_STOP".equals(key) || "Account.SUSPEND_STOP".equals(key) || "Account.DISABLE_STOP".equals(key)) {
            try {
                value = getDateFormatForAccountDetail().format(getDateFormatForParserAccountDetail().parse(value));
            } catch (Exception e) {
                logger.error("parse Date error", e);
            }
        }
        return new AccountDetailValueBean(messageUtil.getMessage(key + "." + language), value, null);
    }

    private List<AccountDetailValueBean> getGeneralInfoList(ViettelService response, String language) {
        List<AccountDetailValueBean> rlist = new LinkedList<>();
        rlist.add(getDetailGeneralInfo("Account.MSISDN", language, response));
        rlist.add(getDetailGeneralInfo("Account.STATUS", language, response));
        rlist.add(getDetailGeneralInfo("Account.ACTIVE_STOP", language, response));
        rlist.add(getDetailGeneralInfo("Account.SUSPEND_STOP", language, response));
        rlist.add(getDetailGeneralInfo("Account.DISABLE_STOP", language, response));
        Predicate<AccountDetailValueBean> nullValue = Objects::isNull;
        rlist.removeIf(nullValue);
        return rlist;
    }

    private DataPackageBean getMinExpiredDataPackage(List<DataPackageBean> balaceList) {
        if (!CommonUtil.isEmpty(balaceList)) {
            Collections.sort(balaceList, new Comparator<DataPackageBean>() {
                @Override
                public int compare(DataPackageBean a, DataPackageBean b) {
                    return a.getExpiredDate().compareTo(b.getExpiredDate());
                }
            });
            return balaceList.get(0);
        }
        return null;
    }

    @Override
    public void wsDetachIpService(String ip, String serviceCode, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean) throws Exception {
        // TODO Auto-generated method stub
        Webservice wsObjectBean = myvtgService.getWebserviceByName(serviceCode);

        String code = environment.getRequiredProperty("COUNTRY_CODE");
        if (wsObjectBean == null) {
            // Web-service not found, check database
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(
                    "The Webserive can't be found for this " + serviceCode + " service. Please, check the database.");
            bean.setUserMsg("Sorry! This action can't be done.");
            return;
        }

        // In-case using web-service
        params = new LinkedHashMap<>();
        params.put("ip", ip);

        SoapWSResponse obj = doCallWebservice(params, wsObjectBean);
        if (obj == null) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setUserMsg(messageUtil.getMessage("wsDetachIpService.fail.en"));
            return;
        } else {

            bean.setErrorCode(
                    obj.getErrCode().equals(Constants.ERROR_CODE_0) ? Constants.ERROR_CODE_0 : obj.getErrCode());
            // bean.setErrorCode(Constants.ERROR_CODE_1000);
            try {

                // get response message
                String retVal = obj.getTextContent(wsObjectBean.getXpathExtension01());
                String respMsg = retVal.replaceFirst(code, "");
                if (respMsg != null && !respMsg.isEmpty()) {
                    DetachIpBean ipBean = new DetachIpBean();
                    ipBean.setIsdn(respMsg);
                    ipBean.setErrorCode(obj.getWSResponse());
                    ipBean.setErrorMessage(respMsg);
                    bean.setWsResponse(ipBean);

                } else {
                    bean.setUserMsg(obj.getErrCode().equals(Constants.ERROR_CODE_0)
                            ? messageUtil.getMessage("wsDetachIpService.success.en")
                            : messageUtil.getMessage("wsDetachIpService.fail.en"));
                }

            } catch (Exception e) {
                logger.error("parse description error: ", e);
                bean.setUserMsg(obj.getErrCode().equals(Constants.ERROR_CODE_0)
                        ? messageUtil.getMessage("wsDetachIpService.success.en")
                        : messageUtil.getMessage("wsDetachIpService.fail.en"));
            }
        }
    }

    private void rechargePrepaidNew(String isdn, String desIsdn, String serial, String language,
            ResponseSuccessBean bean) throws Exception {
        TransactionLog charLog;
        TransactionLog lockLog;
        String typeRecharge = environment.getRequiredProperty("recharge_type");
        String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + isdn;
        String desMsIsdn = environment.getRequiredProperty("COUNTRY_CODE") + desIsdn;
        // Init log
        ChargeHis c = new ChargeHis();
        c.setServiceId("PrePaid");
        c.setFee(BigDecimal.ZERO);
        c.setMsisdn(desIsdn);
        c.setRefillIsdn(isdn);
        c.setPinNumber(serial);
        // c.setSeriNumber(serial);

        if (isMetfoneMarket()) {
            // STEP 1: try to lock card and get value
            channel = proService.getChannel(typeRecharge);
            CardUtil cardUtil = new CardUtil(channel);
            lockLog = cardUtil.viewCardExchangeVT(desMsIsdn, serial, environment);
            myvtgService.persistTransactionLog(lockLog);
            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // System.out.println("namdh1 says: "+
                // messageUtil.getMessage("wsDoRecharge.fail."+language));

                // bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail."+language));
                // TODO: set user message with clear message, ex: The card is
                // already locked.
                c.setRefillDate(lockLog.getRequestTime());
                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
                c.setTransactionLogId(lockLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return;
            }

            // STEP 2: try to add money
            Double monney = lockLog.getMoney();
            monney = monney / (Integer.parseInt(environment.getRequiredProperty("recharge_rate_vt")));
            charLog = cardUtil.chargeMoneyExchangeVT(desMsIsdn, monney, environment);
            myvtgService.persistTransactionLog(charLog);

            c.setRefillDate(charLog.getRequestTime());
            c.setRefillAmount(new BigDecimal(monney));

            if (charLog.getErrorCode() != null && !Constants.ERROR_CODE_0.equalsIgnoreCase(charLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // System.out.println("namdh1 says 2: "+
                // messageUtil.getMessage("wsDoRecharge.fail."+language));
                // bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail."+language));
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setResult("UN-SUCC: Add balance, logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return;
            } else {
                c.setResult("SUCC: logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_0));
                myvtgService.persistCharhis(c);
            }
            return;

        } else {

        }
    }

//    @Override
//    public void wsCheckForceUpdateApp(ResponseSuccessBean bean, String language, Object curVersion) {
//        String newVersion;
//        String forceUpdate;
//        String msg = "New version is available. Please update application";
//
//        try {
//
//            newVersion = myvtgService.getAppParam(Constants.APP_VERSION);
//            forceUpdate = myvtgService.getAppParam(Constants.APP_FORCE_UPDATE);
//            msg = myvtgService.getAppParam(Constants.APP_FORCE_UPDATE_MESSAGE + "." + language);
//            if (curVersion == null) {
//                curVersion = myvtgService.getAppParam(Constants.APP_VERSION);
//            }
//            newVersion = newVersion.trim().replaceAll("\n ", "").replaceAll("\r", "");
//
//            if (Double.parseDouble(newVersion) > Double.parseDouble(curVersion.toString())) {
//
//                ForceUpdateVersionBean versionBean = new ForceUpdateVersionBean();
//                if (forceUpdate != null && "true".equals(forceUpdate)) {
//                    versionBean.setForceUpgrade("true");
//                    versionBean.setRecommendUpgrade("false");
//
//                } else {
//                    versionBean.setForceUpgrade("true");
//                    versionBean.setRecommendUpgrade("true");
//                }
//
//                versionBean.setUpdateInfo(msg);
//                versionBean.setCurrVersion(curVersion.toString());
//                versionBean.setNewVersion(newVersion);
//                bean.setWsResponse(versionBean);
//            } else {
//                ForceUpdateVersionBean versionBean = new ForceUpdateVersionBean();
//                versionBean.setForceUpgrade("false");
//                versionBean.setRecommendUpgrade("false");
//                versionBean.setUpdateInfo(msg);
//                versionBean.setCurrVersion(curVersion.toString());
//                versionBean.setNewVersion(newVersion);
//                bean.setWsResponse(versionBean);
//            }
//
//        } catch (Exception ex) {
//            logger.error("wsCheckForceUpdateApp: ", ex);
//        }
//    }
//    @Override
//    public void wsCheckForceUpdateApp(ResponseSuccessBean bean, String language, Object curVersion) {
//        String newVersion;
//        String forceUpdate;
//        String msg = "New version is available. Please update application";
//        try {
//            String currVersion = "";
//            String[] split = curVersion.toString().split("\\.");
//            if (split.length > 2) {
//                currVersion = split[0] + split[1];
//            } else {
//                currVersion = curVersion.toString();
//            }
//            newVersion = myvtgService.getAppParam(Constants.APP_VERSION);
//            forceUpdate = myvtgService.getAppParam(Constants.APP_FORCE_UPDATE);
//            msg = myvtgService.getAppParam(Constants.APP_FORCE_UPDATE_MESSAGE + "." + language);
//            if (currVersion == null) {
//                currVersion = myvtgService.getAppParam(Constants.APP_VERSION);
//            }
//            newVersion = newVersion.trim().replaceAll("\n ", "").replaceAll("\r", "");
//            if (Double.parseDouble(newVersion) > Double.parseDouble(currVersion)) {
//                ForceUpdateVersionBean versionBean = new ForceUpdateVersionBean();
//                if (forceUpdate != null && "true".equals(forceUpdate)) {
//                    versionBean.setForceUpgrade("true");
//                    versionBean.setRecommendUpgrade("false");
//                } else {
//                    versionBean.setForceUpgrade("true");
//                    versionBean.setRecommendUpgrade("true");
//                }
//                versionBean.setUpdateInfo(msg);
//                versionBean.setCurrVersion(currVersion);
//                versionBean.setNewVersion(newVersion);
//                bean.setWsResponse(versionBean);
//            } else {
//                ForceUpdateVersionBean versionBean = new ForceUpdateVersionBean();
//                versionBean.setForceUpgrade("false");
//                versionBean.setRecommendUpgrade("false");
//                versionBean.setUpdateInfo(msg);
//                versionBean.setCurrVersion(currVersion);
//                versionBean.setNewVersion(newVersion);
//                bean.setWsResponse(versionBean);
//            }
//        } catch (Exception ex) {
//            logger.error("wsCheckForceUpdateApp: ", ex);
//        }
//    }
    /**
     * @param bean
     * @param language
     * @param curVersion
     * @param isdn
     *
     * @throws Exception
     * @author daibq
     */
    public void wsCheckForceUpdateApp(ResponseSuccessBean bean, String language, Object curVersion, String isdn, String versionApp) throws Exception {
        String newVersion;
        String forceUpdate;
        String msg = "New version is available. Please update application";
        try {
            String currVersion = curVersion.toString().replace(".", "");
            if (versionApp.equals("Android")) {
                newVersion = myvtgService.getAppParam("app_version_android");
            } else if (versionApp.equals("iOS")) {
                newVersion = myvtgService.getAppParam("app_version_ios");
            } else {
                newVersion = myvtgService.getAppParam(Constants.APP_VERSION);
            }
            forceUpdate = myvtgService.getAppParam(Constants.APP_FORCE_UPDATE);
            msg = myvtgService.getAppParam(Constants.APP_FORCE_UPDATE_MESSAGE + "." + language);
            if (currVersion == null) {
                currVersion = myvtgService.getAppParam(Constants.APP_VERSION);
            }
            String newVersionDisplay = newVersion;
            newVersion = newVersion.trim().replaceAll("\n ", "").replaceAll("\r", "").replace(".", "");
            if (Double.parseDouble(newVersion) > Double.parseDouble(currVersion)) {
                ForceUpdateVersionBean versionBean = new ForceUpdateVersionBean();
                if (forceUpdate != null && "true".equals(forceUpdate)) {
                    versionBean.setForceUpgrade("true");
                    versionBean.setRecommendUpgrade("false");
                } else {
                    versionBean.setForceUpgrade("true");
                    versionBean.setRecommendUpgrade("true");
                }
                versionBean.setUpdateInfo(msg.replace("{1}", curVersion.toString()).replace("{2}", newVersionDisplay));
                versionBean.setCurrVersion(currVersion);
                versionBean.setNewVersion(newVersion);
                bean.setWsResponse(versionBean);
            } else {
                ForceUpdateVersionBean versionBean = new ForceUpdateVersionBean();
                versionBean.setForceUpgrade("false");
                versionBean.setRecommendUpgrade("false");
                versionBean.setUpdateInfo(msg);
                versionBean.setCurrVersion(currVersion);
                versionBean.setNewVersion(newVersion);
                String maxCount = configUtils.getMessage("MAX_COUNT", "3").trim();
                List<InfoUpdateVsApp> list = myvtgService.getLoginAfterUpdateVersion(isdn, null, currVersion);
//                Long isShowInfoupdate = 0L;
                String msgShowInfoupdate = myvtgService.getAppParamByTypAndName(Constants.APP_FORCE_IF_UPDATE_MESSAGE + "." + language);
                InfoMsgDTO dto = new InfoMsgDTO();
                dto.setInfoUpdateMsg(msgShowInfoupdate);
                dto.setIsShowInfoUpdate(0L);
                if (!DataUtil.isNullOrEmpty(msgShowInfoupdate)) {
                    if (list.size() < Long.parseLong(maxCount)) {
                        dto.setIsShowInfoUpdate(1L);
                        InfoUpdateVsApp infoUpdateVsApp = new InfoUpdateVsApp();
                        infoUpdateVsApp.setIsdn(isdn);
                        infoUpdateVsApp.setCreateDate(new Date());
                        infoUpdateVsApp.setStatus(Constants.STATUS_USE);
                        infoUpdateVsApp.setVersion(currVersion);
                        myvtgService.insert(infoUpdateVsApp);
                    }
                }
                String msgShowInfogame = myvtgService.getAppParamByTypAndName(Constants.IF_GAME_MESSAGE + "." + language);
                dto.setInfoGameMsg(msgShowInfogame);
                dto.setIsShowInfoGameMsg(0L);
                if (!DataUtil.isNullOrEmpty(msgShowInfogame)) {
                    dto.setIsShowInfoGameMsg(1L);
                }

                String msgShowInfoCovid19 = myvtgService.getAppParamByTypAndName(Constants.IF_COVID_MESSAGE + "." + language);
                dto.setIsShowInfoCovid19(0L);
                if (!DataUtil.isNullOrEmpty(msgShowInfoCovid19)) {
                    dto.setIsShowInfoCovid19(1L);
                }

                versionBean.setInfoMsg(dto);
                bean.setWsResponse(versionBean);
            }
        } catch (Exception ex) {
            logger.error("wsCheckForceUpdateApp: ", ex);
        }
    }

    private ServiceGroupBean getGroupByCode(List<ServiceGroupBean> gList, String code) {
        for (ServiceGroupBean g : gList) {
            if (g != null && g.getGroupCode() != null && g.getGroupCode().equalsIgnoreCase(code)) {
                return g;
            }
        }
        return null;
    }

    public void wsGetServices(String isdn, String language, ResponseSuccessBean bean) {
        try {
            List<ServiceBean> serviceList = new ArrayList<>();
            Sub sub = myvtgService.findByIsdn(isdn);
            if (sub != null) {
                String listRegisteredServiceCodes = null;
                if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
                    //daibq  bo sung dong bo du lieu vao bang sub_rel_product_new
                    logger.info("Start call API getInfoPackageVas ");
                    Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_SYS_VAS_RECORD);
                    logger.error("Time start" + new Date());
//                BaseRequestDto rq = new BaseRequestDto(new RequestDto(isdn, sub.getSubId(), language, dateFormat.format(new Date())));
                    RequestDto rq = new RequestDto(isdn, sub.getSubId(), dateFormat.format(new Date()));
                    String rqStr = CommonUtil.convertObjectToJsonString(rq);
                    logger.info("Requesst send getInfoPackageVas: " + rqStr);
                    WebServiceUtil.callApiRest(ws.getUrl(), rqStr);
                    logger.error("End start" + new Date());
                    logger.info("End call API getInfoPackageVas ");
                    logger.info("start getListRegisteredServiceCodes ");
                    listRegisteredServiceCodes = cmpreService.getListRegisteredServiceCodes(sub.getSubId());
                    logger.info("end  getListRegisteredServiceCodes ");
                } else {
                    listRegisteredServiceCodes = cmposService.getListRegisteredServiceCodes(sub.getSubId());
                }
                logger.info("start getAllUnregisterService ");
                serviceList = myvtgService.getAllUnregisterService(isdn, language, listRegisteredServiceCodes);
                logger.info("end getAllUnregisterService ");
            }

            List<ServiceGroupBean> serviceGroupList = new Vector<ServiceGroupBean>();
            for (ServiceBean service : serviceList) {
                if(null == service.getValidity()){
                    service.setValidity(messageUtil.getMessage("camId.depending." + language));
                }
                ServiceGroupBean group = getGroupByCode(serviceGroupList, service.getGroupCode());
                if (group == null) {
                    group = new ServiceGroupBean(service.getGroupName(), service.getGroupCode(), service);
                    serviceGroupList.add(group);
                } else {
                    group.getServices().add(service);
                }
            }
            if (!CommonUtil.isEmpty(serviceGroupList)) {
                bean.setWsResponse(serviceGroupList);
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            bean.setErrorCode("99");
            bean.setMessage(e.getMessage());
            bean.setErrorCode("System error! Please try again");
        }
    }

    @Override
    public void wsGetCurrentUsedServices(String language, String isdn, ResponseSuccessBean bean) {
        try {
//            List<ServiceBean> services = new ArrayList<>();
            List<ServiceAutoRenewBean> services = new ArrayList<>();
            Sub sub = myvtgService.findByIsdn(isdn);
            Map<String, Date> listRegisteredServiceCodes = new HashMap<>();
            if (sub != null) {
                if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
                    //daibq  bo sung dong bo du lieu vao bang sub_rel_product_new
                    logger.info("Start call API getInfoPackageVas ");
                    Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_SYS_VAS_RECORD);
                    logger.error("Time start" + new Date());
//                BaseRequestDto rq = new BaseRequestDto(new RequestDto(isdn, sub.getSubId(), language, dateFormat.format(new Date())));
                    RequestDto rq = new RequestDto(isdn, sub.getSubId(), dateFormat.format(new Date()));
                    String rqStr = CommonUtil.convertObjectToJsonString(rq);
                    logger.info("Requesst send getInfoPackageVas: " + rqStr);
                    WebServiceUtil.callApiRest(ws.getUrl(), rqStr);
                    logger.error("End start" + new Date());
                    logger.info("End call API getInfoPackageVas ");
                    listRegisteredServiceCodes = cmpreService.getListRegisteredAndExpiredDateServiceCodes(sub.getSubId());
                } else {
                    listRegisteredServiceCodes = cmposService.getListRegisteredAndExpiredDateServiceCodes(sub.getSubId());
                }

                StringBuilder listServiceCodes = new StringBuilder("(");

                for (Map.Entry<String, Date> entry: listRegisteredServiceCodes.entrySet()) {
                    listServiceCodes.append("'" + entry.getKey() + "',");
                }

                // append empty object
                listServiceCodes.append("' ')");

//                services = myvtgService.getCurrentUsedAllServices(language, isdn, listServiceCodes.toString());
                services = myvtgService.getCurrentUserAutoRenewAllServices(language, listServiceCodes.toString());
            }
            if (services.isEmpty()) {
                services = null;
            } else {
                for(ServiceAutoRenewBean service : services) {
                    Date date = listRegisteredServiceCodes.get(service.getSubCode());
                    if(date != null){
                        service.setCode(service.getSubCode());
                        String dateString = DateUtils.date2ddMMyyyyString(date);
                        service.setExpired(dateString);
                    } else {
                        date = new Date();
                        service.setExpired("------");
                    }
                    service.setExpiredDate(date);
                }
                services = services.stream().sorted(Comparator.comparing(ServiceAutoRenewBean::getExpiredDate).reversed()).collect(Collectors.toList());
            }
            bean.setWsResponse(services);
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            bean.setErrorCode("99");
            bean.setMessage(e.getMessage());
        }
    }

    @Override
    public void wsGetServiceDetail(String language, String isdn,
                                   String serviceCode, ResponseSuccessBean bean
    ) {
        try {
            List<ServiceDetailBean> objs = new ArrayList<>();

            Sub sub = myvtgService.findByIsdn(isdn);
            if (sub != null) {
//                //daibq  bo sung dong bo du lieu vao bang sub_rel_product_new
//                logger.info("Start call API getInfoPackageVas ");
//                Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_SYS_VAS_RECORD);
//                logger.error("Time start" + new Date());
////                BaseRequestDto rq = new BaseRequestDto(new RequestDto(isdn, sub.getSubId(), language, dateFormat.format(new Date())));
//                RequestDto rq = new RequestDto(isdn, sub.getSubId(), dateFormat.format(new Date()));
//                String rqStr = CommonUtil.convertObjectToJsonString(rq);
//                logger.info("Requesst send getInfoPackageVas: " + rqStr);
//                WebServiceUtil.callApiRest(ws.getUrl(), rqStr);
//                logger.error("End start" + new Date());
//                logger.info("End call API getInfoPackageVas ");
                List<String> listRegisteredServiceCodes = getRegisterServiceCodes(sub);
                objs = myvtgService.getServiceDetail(language, isdn, serviceCode, listRegisteredServiceCodes);
            }
            if (!objs.isEmpty()) {
                // Truong hop co tin moi
                ServiceDetailResultBean result = new ServiceDetailResultBean(objs.get(0).getName(), objs.get(0).getCode(),
                        objs.get(0).getFullDes(), objs.get(0).getImgDesUrl(), objs.get(0).getWebLink(),
                        objs.get(0).getPrice(), objs.get(0).getIsRegisterAble(), objs.get(0).getShortDes(), objs.get(0).getValidity(), 0, objs.get(0).getServiceType(), objs.get(0).getShowSubService());

                ArrayList<SubServiceDetailBean> details = new ArrayList<SubServiceDetailBean>();
                for (ServiceDetailBean d : objs) {
                    if(0 == objs.get(0).getShowSubService() && (d.getSubName().toLowerCase().contains("auto") || d.getSubName().toLowerCase().contains("non-auto") || d.getSubCode().toLowerCase().contains("auto") || d.getSubCode().toLowerCase().contains("renew"))){
                        result.setAutoRenew(1);
                    }
                    details.add(new SubServiceDetailBean(1 == objs.get(0).getShowSubService() ? d.getSubName().replaceAll("NON", "").replaceAll("AUTO", "").replaceAll("-","") : d.getSubName(), d.getSubCode(), d.getSubShortDes(),
                            d.getSubFullDes(), d.getSubIconUrl(),
                            d.getSubPrice() == null ? "0" : d.getSubPrice().toString() + d.getUnit(), d.getUnit(),
                            d.getState() == 0 ? "False" : "True", d.getSubName().toLowerCase().contains("non-auto") ? 0 : 1));
                }
                if(details.size() < 2){
                    result.setAutoRenew(0);
                }
                result.setPackages(details);
                bean.setWsResponse(result);
            } else {
                // Truong hop khong co tin moi
                bean.setErrorCode("0");
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            bean.setErrorCode("99");
            bean.setMessage(e.getMessage());
            bean.setErrorCode("System error! Please try again");
        }
    }

    private List<String> getRegisterServiceCodes(Sub sub) {
        List<String> listRegisteredServiceCodes = null;
        if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
            listRegisteredServiceCodes = cmpreService.getRegisteredServiceCodes(sub.getSubId());
        } else {
            listRegisteredServiceCodes = cmposService.getRegisteredServiceCodes(sub.getSubId());
        }
        return listRegisteredServiceCodes;
    }

    @Override
    public void wsGetServicesByGroup(String language, String isdn, String serviceGroupCode, ResponseSuccessBean bean, String validity) {
        // if (isUnitelMarket()) {
        try {
            List<ServiceBean> services = new ArrayList<>();
            Sub sub = myvtgService.findByIsdn(isdn);
            if (sub != null) {
                String listRegisteredServiceCodes = null;
                if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
                    listRegisteredServiceCodes = cmpreService.getListRegisteredServiceCodes(sub.getSubId());
                } else {
                    listRegisteredServiceCodes = cmposService.getListRegisteredServiceCodes(sub.getSubId());
                }
                services = myvtgService.getServicesByGroup(language, isdn, serviceGroupCode, listRegisteredServiceCodes, validity);
            }
            if (services.isEmpty()) {
                services = null;
            }
            if(null != services){
                for (ServiceBean service : services) {
                    if(null == service.getValidity()){
                        service.setValidity(messageUtil.getMessage("camId.depending." + language));
                    }
                }
            }
            bean.setWsResponse(services);
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            bean.setErrorCode("99");
            bean.setMessage(e.getMessage());
            bean.setErrorCode("System error! Please try again");
        }

    }

    @Override
    public void wsGetAllDataPackages(String language, String isdn, ResponseSuccessBean bean) {
        Sub sub = myvtgService.findByIsdn(isdn);
        if (sub == null) {
            bean.setWsResponse(null);
            return;
        }

        List<ServiceBean> result = new ArrayList<>();

        String listRegisteredServiceCodes = null;
        if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
            listRegisteredServiceCodes = cmpreService.getListRegisteredServiceCodes(sub.getSubId());
        } else {
            listRegisteredServiceCodes = cmposService.getListRegisteredServiceCodes(sub.getSubId());
        }

        List<ServiceBean> services = myvtgService.getAllDataPackages(language, sub.getIsdn(),
                getServiceTypesFromConfig(sub.getSubType(), sub.getSimType()), listRegisteredServiceCodes);

        if (services.isEmpty()) {
            bean.setWsResponse(null);
            return;
        }
        List<ServiceBean> serviceFiltered = filterPackages(sub, services);
        if (CommonUtil.isEmpty(serviceFiltered)) {
            bean.setWsResponse(null);
        } else {
            bean.setWsResponse(serviceFiltered);
        }

    }

    private List<ServiceBean> filterPackages(Sub sub, List<ServiceBean> services) {
        List<ServiceBean> serviceFiltered = new ArrayList<>();
        String marketName = environment.getProperty(Constants.MARKET_NAME_CONFIG);
        if (marketName != null && marketName.equalsIgnoreCase(Constants.MARKET_NAME_UNITEL)) {
            // filter Net SIM
            String appPr = (String) myvtgService.getAppParam(Constants.NET_SIM_PACKAGE_LIST);
            String productCode = sub.getProductCode() == null ? "" : sub.getProductCode();
            List<String> netSimPackageList = new ArrayList<>();
            if (appPr != null && !appPr.isEmpty()) {
                String[] appPrs = appPr.split(";");
                for (String pn : appPrs) {
                    netSimPackageList.add(pn.toUpperCase());
                }
            }

            Boolean isNetSim = false;
            if (netSimPackageList.contains(productCode)) {
                isNetSim = true;
                netSimPackageList.remove(productCode);
            }

            for (ServiceBean service : services) {
                if ((isNetSim && netSimPackageList.contains(service.getCode().toUpperCase()))
                        || (!isNetSim && !netSimPackageList.contains(service.getCode().toUpperCase()))) {
                    serviceFiltered.add(service);
                }
            }
            return serviceFiltered;
        }
        return services;
    }

    @Override
    public void wsGetServiceInfo(String isdn, String language, String serviceCode, Integer subType,
            ResponseSuccessBean bean) {

        String actStatus = cmpreService.getActStatus(isdn);
        if (actStatus == null) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setWsResponse(null);
        } else {
            Integer lockState = null;
            if (subType == 1) {
                // truong hop tra truoc
                if (actStatus.equals(Constants.ACT_STATUS_00)) {
                    // truong hop chua khoa
                    lockState = 0;
                } else {
                    // truong hop khoa
                    lockState = 1;
                }
            }
            if (subType == 2) {
                // truong hop tra sau
                if (actStatus.equals(Constants.ACT_STATUS_000)) {
                    // truong hop chua khoa
                    lockState = 0;
                } else {
                    // truong hop khoa
                    lockState = 1;
                }
            }
            // lay tham so dich vu
            BlockGoingCallBean serviceInfo = myvtgService.getServiceInfoForBlockGoingCall(language, serviceCode);
            if (serviceInfo == null) {
                bean.setWsResponse("0");
            } else {
                serviceInfo.setLockState(lockState);
                bean.setWsResponse(serviceInfo);
            }
        }
    }

    @Override
    public void wsUpdateCustomerInfo(String xmlParam, String serviceCode, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean, String language) throws Exception {
        // TODO Auto-generated method stub
        Webservice wsObjectBean = myvtgService.getWebserviceByName(serviceCode);

        if (wsObjectBean == null) {
            // Web-service not found, check database
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(
                    "The Webserive can't be found for this " + serviceCode + " service. Please, check the database.");
            bean.setUserMsg("Sorry! This action can't be done.");
            return;
        }

        params = new LinkedHashMap<>();
        params.put("customerInfo", xmlParam);
        SoapWSResponse obj = doCallWebservice(params, wsObjectBean);
        if (obj == null) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setUserMsg(messageUtil.getMessage("wsUpdateCustomerInfo.fail." + language));
            return;
        } else {

            try {
                // get response message
                String relVal = obj.getTextContent(wsObjectBean.getXpathResponseCode());

                logger.error("WS wsUpdateCustomerInfo:==================> " + relVal);

                if (Constants.ERROR_CODE_0.equals(relVal)) {

                    bean.setUserMsg(messageUtil.getMessage("wsUpdateCustomerInfo.success." + language));
                } else {
                    bean.setUserMsg(messageUtil.getMessage("wsUpdateCustomerInfo.fail." + language));

                }
                bean.setErrorCode(Constants.ERROR_CODE_0.equals(relVal) ? Constants.ERROR_CODE_0 : obj.getErrCode());

                bean.setWsResponse(new Object());

            } catch (Exception e) {
                logger.error("parse description error: ", e);
                bean.setUserMsg(obj.getErrCode().equals(Constants.ERROR_CODE_0)
                        ? messageUtil.getMessage("wsUpdateCustomerInfo.success." + language)
                        : messageUtil.getMessage("wsUpdateCustomerInfo.fail."));
            }

        }
    }

    @Override
    public void wsDoCallWebservice(LinkedHashMap<String, Object> params, String serviceCode,
            String language, ResponseSuccessBean bean) throws Exception {
        Webservice wsObjectBean = myvtgService.getWebserviceByName(serviceCode);
        if (wsObjectBean == null) {
            throw new Exception("WsObjectBean Invalid");
        }
        WebServiceUtil wsUtil = new WebServiceUtil(wsObjectBean, environment);
        SoapWSResponse wsResponse = wsUtil.callWebService(params, true);
        if (wsResponse != null) {
            try {
                //Get respone and set wsRespone
                getResult(serviceCode, wsResponse, bean);
                bean.setUserMsg(messageUtil.getMessage(serviceCode + ".success." + language));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            throw new Exception("WsResponse Invalid");
        }
    }

    private SoapWSResponse getResult(String serviceCode, SoapWSResponse wsResponse, ResponseSuccessBean bean) throws Exception {
        String result = wsResponse.getTextContent(Constants.WS_XPATH_BODY + serviceCode + Constants.WS_XPATH_RETURN);
        try {
            SoapWSResponse responeResultXML = new SoapWSResponse(result);
            String errorCode = responeResultXML.getTextContent(Constants.WS_XPATH_ERR_CODE);
            if (errorCode != null && Constants.ERROR_CODE_0.equalsIgnoreCase(errorCode)) {
                return responeResultXML;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * wsHistoryTopup
     *
     * @param request
     * @param language
     *
     * @return
     *
     * @author daibq
     */
    @Override
    public BaseResponseBean wsHistoryTopup(RequestBean request, String language) {
        logger.info("Start business wsHistoryTopup off AccountServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            isdn = DataUtil.fomatIsdn(isdn);
            List<Object> list = myvtgService.getHistoryTopup(isdn, Constants.COMMAN);
            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setLstHisTopup(list);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * wsHistoryTopUpNew
     *
     * @param request
     * @param language
     *
     * @return
     *
     * @author YaangVu
     */
    @Override
    public BaseResponseBean wsHistoryTopUpNew(RequestBean request, String language) {
        logger.info("Start business wsHistoryTopup off AccountServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            isdn = DataUtil.fomatIsdn(isdn);
            Map<String, List<Object>> list = myvtgService.getHistoryTopUpNew(isdn, "OCSHW_PAYMENT");
            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            bean.setWsResponse(list);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * wsHistoryCharge
     *
     * @param request
     * @param language
     *
     * @return
     *
     * @author YaangVu
     */
    @Override
    public BaseResponseBean wsHistoryCharge(RequestBean request, String language) {
        logger.info("Start business wsHistoryCharge off AccountServiceImpl");
        Double totalCall;
        Double totalData;
        Double totalSms;
        Double totalVas;
        Double total;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();

            isdn = DataUtil.fomatIsdn855(isdn);

            Date startTime = DataUtil.isNullObject(request.getWsRequest().get("startTime")) ? DataUtil.addTime(new Date(), 0, null, null, null, 5) : new Date(Long.parseLong(request.getWsRequest().get("startTime").toString()));

            Date endTime = DataUtil.isNullObject(request.getWsRequest().get("endTime")) ? new Date() : new Date(Long.parseLong(request.getWsRequest().get("endTime").toString()));

            if (DataUtil.addTime(endTime, -31, null, null, null, 5).getTime() > startTime.getTime()) {
                logger.info("Error request : startTime over 30 days ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.startTime.fail.", "myMetfone.Ishare.startTime.over.", startTime.toString());
            }

            String type = request.getWsRequest().get("type").toString();
            Map<String, Object> result = new HashMap<>();
            switch (type) {
                case "exchange":
                default:
                    totalCall = cmpreService.getTotalCamIdDetailCall(isdn, startTime, endTime, Common.TYPE_CAMID_EXCHANGE);
                    totalData = cmpreService.getTotalCamIdDetailData(isdn, startTime, endTime, Common.TYPE_CAMID_EXCHANGE);
                    totalSms = cmpreService.getTotalCamIdDetailSms(isdn, startTime, endTime, Common.TYPE_CAMID_EXCHANGE);
                    total = totalCall + totalData + totalSms;
                    result.put("total", CommonUtil.formatTwoDigital(total));
                    result.put("calling", CommonUtil.formatTwoDigital(totalCall));
                    result.put("sms", CommonUtil.formatTwoDigital(totalSms));
                    result.put("data", CommonUtil.formatTwoDigital(totalData));
                    break;
                case "basic":
                    totalCall = cmpreService.getTotalCamIdDetailCall(isdn, startTime, endTime, Common.TYPE_CAMID_BASIC);
                    totalData = cmpreService.getTotalCamIdDetailData(isdn, startTime, endTime, Common.TYPE_CAMID_BASIC);
                    totalSms = cmpreService.getTotalCamIdDetailSms(isdn, startTime, endTime, Common.TYPE_CAMID_BASIC);
                    totalVas = cmpreService.getTotalCamIdDetailVas(isdn, startTime, endTime, Common.TYPE_CAMID_BASIC);
                    total = totalCall + totalData + totalSms + totalVas;
                    result.put("total", CommonUtil.formatTwoDigital(total));
                    result.put("calling", CommonUtil.formatTwoDigital(totalCall));
                    result.put("sms", CommonUtil.formatTwoDigital(totalSms));
                    result.put("vas", CommonUtil.formatTwoDigital(totalVas));
                    result.put("data", CommonUtil.formatTwoDigital(totalData));
                    break;
                case "promotion":
                    totalCall = cmpreService.getTotalCamIdDetailCall(isdn, startTime, endTime, Common.TYPE_CAMID_PROMOTION);
                    totalData = cmpreService.getTotalCamIdDetailData(isdn, startTime, endTime, Common.TYPE_CAMID_PROMOTION);
                    totalSms = cmpreService.getTotalCamIdDetailSms(isdn, startTime, endTime, Common.TYPE_CAMID_PROMOTION);
                    total = totalCall + totalData + totalSms;
                    result.put("total", CommonUtil.formatTwoDigital(total));
                    result.put("calling", CommonUtil.formatTwoDigital(totalCall));
                    result.put("sms", CommonUtil.formatTwoDigital(totalSms));
                    result.put("data", CommonUtil.formatTwoDigital(totalData));
                    break;
                case "data":
                    List<HistoryChargeDataDetailsBean> listDetails = cmpreService.getCamIdDataDetails(isdn, startTime, endTime);
                    long quantity = 0;
                    double dataOfNumber = 0.0;
                    quantity = listDetails.stream()
                            .mapToLong(x -> x.getTotal())
                            .sum();

                    dataOfNumber = listDetails.stream()
                            .mapToDouble(x -> x.getMoney())
                            .sum();
                    listDetails.forEach(detail -> {
                        Double e = Double.valueOf(String.format("%.2f", detail.getMoney()));
                        detail.setMoney(e);
                    });
                    result.put("quantity", quantity);
                    result.put("total", CommonUtil.formatTwoDigital(dataOfNumber));
                    result.put("unit", "Mb");
                    List<Object> history = new ArrayList<>();
                    history.addAll(listDetails);
                    result.put("history", history);
                    break;
            }

            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);

            bean.setWsResponse(result);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * wsHistoryChargeDetail
     *
     * @param request
     * @param language
     *
     * @return
     *
     * @author YaangVu
     */
    @Override
    public BaseResponseBean wsHistoryChargeDetail(RequestBean request, String language) {
        logger.info("Start business wsHistoryCharge off AccountServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            isdn = DataUtil.fomatIsdn855(isdn); // format have to prefix 855 
            // dataType là loại ngày tháng được filter: today - lịch sử của hôm nay, 7days - 7 ngày trước, 30days - 30 ngày trước
            //String dataType = request.getWsRequest().get("dataType").toString();
            // type: loại lịch sử: sms, calling, data
            if (DataUtil.isNullObject(request.getWsRequest().get("type")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("type").toString())) {
                logger.info("Error request : type is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.type.empty.", language);
            }
            String type = request.getWsRequest().get("type").toString();

            // parentType: loại lịch sử cha cuả Type (chính là type trong wsHistoryCharge() ): basic, exchange, data, promotion
            String parentType = DataUtil.isNullObject(request.getWsRequest().get("parentType").toString()) ? "" : request.getWsRequest().get("parentType").toString();

            Date startTime = DataUtil.isNullObject(request.getWsRequest().get("startTime")) ? DataUtil.addTime(new Date(), 0, null, null, null, 5) : new Date(Long.parseLong(request.getWsRequest().get("startTime").toString()));

            Date endTime = DataUtil.isNullObject(request.getWsRequest().get("endTime")) ? new Date() : new Date(Long.parseLong(request.getWsRequest().get("endTime").toString()));

            if (DataUtil.addTime(endTime, -31, null, null, null, 5).getTime() > startTime.getTime()) {
                logger.info("Error request : startTime over 30 days ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.startTime.fail.", "myMetfone.Ishare.startTime.over.", startTime.toString());
            }
            Map<String, Object> result = new HashMap<>();
            List<HistoryChargeDetailsBean> listDetails = new ArrayList<HistoryChargeDetailsBean>(); // use for sms,call 
            List<HistoryChargeDataDetailsBean> listDataDetails = new ArrayList<HistoryChargeDataDetailsBean>(); // use for data
            List<HistoryChargeVasDetailsBean> listVasDetail = new ArrayList<HistoryChargeVasDetailsBean>(); // use for vas
            int total = 0;
            double totalMoney = 0.0;
            String totalMoneyFormatted = "";
            switch (type.toUpperCase()) {
                case Constants.TYPE_IS_SMS:
                    listDetails = cmpreService.getHistoryChargeSmsDetails(isdn, startTime, endTime, parentType);
                    listDetails.forEach((detail) -> {
                        detail.setPhoneNumber(DataUtil.formatIsdn0(detail.getPhoneNumber()));
                    });
                    total = listDetails.size();
                    totalMoney = listDetails.stream()
                            .mapToDouble(x -> x.getMoney())
                            .sum();
                    listDetails.forEach(detail -> {
                        detail.setMoney(Double.valueOf(String.format("%.2f", detail.getMoney())));
                    });

                    totalMoneyFormatted = String.format("%.2f", totalMoney);
                    result.put("total", total);
                    result.put("money", Double.valueOf(totalMoneyFormatted));
                    result.put("history", listDetails);
                    break;

                case Constants.TYPE_IS_CALLING:
                    listDetails = cmpreService.getHistoryChargeCallingDetails(isdn, startTime, endTime, parentType);
                    listDetails.forEach((detail) -> {
                        detail.setPhoneNumber(DataUtil.formatIsdn0(detail.getPhoneNumber()));
                    });
                    total = listDetails.size();
                    totalMoney = listDetails.stream()
                            .mapToDouble(x -> x.getMoney())
                            .sum();

                    listDetails.forEach(detail -> {
                        detail.setMoney(Double.valueOf(String.format("%.2f", detail.getMoney())));
                    });

                    totalMoneyFormatted = String.format("%.2f", totalMoney);
                    result.put("total", total);
                    result.put("money", Double.valueOf(totalMoneyFormatted));
                    result.put("history", listDetails);
                    break;

                case Constants.TYPE_IS_DATA:
                    listDataDetails = cmpreService.getHistoryChargeDataDetails(isdn, startTime, endTime, parentType);
                    total = listDataDetails.size();
                    totalMoney = listDataDetails.stream()
                            .mapToDouble(x -> x.getMoney())
                            .sum();

                    listDataDetails.forEach(detail -> {
                        detail.setMoney(Double.valueOf(String.format("%.2f", detail.getMoney())));
                    });

                    totalMoneyFormatted = String.format("%.2f", totalMoney);
                    double totalData = listDataDetails.stream().mapToDouble(x -> x.getTotal()).sum();
                    result.put("quantity", total);
                    result.put("data", totalData);
                    result.put("money", Double.valueOf(totalMoneyFormatted));
                    result.put("history", listDataDetails);
                    break;
                case Constants.TYPE_IS_VAS: {
                    listVasDetail = cmpreService.getHistoryChargeVasDetails(isdn, startTime, endTime, parentType);
                    total = listVasDetail.size();
                    totalMoney = listVasDetail.stream().mapToDouble(x -> x.getMoney()).sum();
                    listVasDetail.forEach(detail -> {
                        detail.setMoney(Double.valueOf(String.format("%.2f", detail.getMoney())));
                    });
                    totalMoneyFormatted = String.format("%.2f", totalMoney);

                    result.put("quantity", total);
                    result.put("money", Double.valueOf(totalMoneyFormatted));
                    result.put("history", listVasDetail);
                    break;
                }
                default:
                    break;
            }

            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);

            bean.setWsResponse(result);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * wsGetAccountInfo
     *
     * @param request
     * @param language
     *
     * @return
     *
     * @author daibq
     */
    @Override
    public BaseResponseBean wsGetAccountInfo(RequestBean request, String language) {
        logger.info("Start business wsGetAccountInfo off AccountServiceImpl");
        try {
//            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
//                logger.info("Error requesst : isdn is null ");
//                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
//            }
            if (DataUtil.isNullOrEmpty(request.getUsername())) {
                logger.info("Error requesst : user name is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            String isdn = request.getUsername();
            isdn = DataUtil.fomatIsdn(isdn);
            Sub subBean = myvtgService.findByIsdn(isdn);
            if (DataUtil.isNullObject(subBean)) {
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            BaseResponseBean response = getPrePaidInfoByTypeBalance(isdn, subBean, language);
            return response;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean wsGetAccountExchangeInfo(RequestBean request, String language) {
        logger.info("Start business wsGetAccountExchangeInfo off AccountServiceImpl");
        try {
//            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
//                logger.info("Error requesst : isdn is null ");
//                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
//            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }

            String isdn = request.getWsRequest().get("isdn").toString();
            isdn = DataUtil.fomatIsdn(isdn);
            Sub subBean = myvtgService.findByIsdn(isdn);
            if (DataUtil.isNullObject(subBean)) {
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            BaseResponseBean response = getPrePaidInfoByTypeBalanceExchange(isdn, subBean, language);
            return response;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * getPrePaidInfoByTypeBalance
     *
     * @param isdn
     * @param subBean
     * @param language
     *
     * @return
     *
     * @author daibq
     */
    private BaseResponseBean getPrePaidInfoByTypeBalance(String isdn, Sub subBean, String language) {
//        ViettelService request = new ViettelService();
        ViettelService response = null;
        String msisdn = getCountryCode() + isdn;
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
//        try {
//            channel = proService.getChannel();
//        } catch (Exception e) {
//            logger.error("error", e);
//        }
        try {
//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);
//            logger.info("Request provisioning:\n" + request);
            // call pro
            response = ServiceClient.getInforSub(msisdn);
            logger.info("Response provisioning:\n" + response);

            if (response != null) {

                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    String typeBasic = configUtils.getMessage("TYPE_BALANCE_BASIC");
                    String typeFreeMn = configUtils.getMessage("TYPE_BALANCE_FREE_MINUTE");
                    String typeExchange = configUtils.getMessage("TYPE_BALANCE_EXCHANGE");
                    String typeData = configUtils.getMessage("TYPE_BALANCE_DATA");
                    List<AccConfig> accConfigList = myvtgService.getAllAccConfig();
                    IsdnInfoBean isdnInfo = new IsdnInfoBean();
                    List<ProvisionBalanceBean> balanceList = getBalanceList(response, accConfigList, -1);
                    isdnInfo.setBasic(Double.parseDouble(dfCurrency.format(getSumAccByType(balanceList, typeBasic))));
                    isdnInfo.setFreeMinute(Double.parseDouble(dfCurrency.format(getSumAccByType(balanceList, typeFreeMn))));
                    isdnInfo.setExchange(Double.parseDouble(dfCurrency.format(getSumAccByType(balanceList, typeExchange))));
                    isdnInfo.setData(df.format(getSumAccByType(balanceList, typeData)));
//                    List<DataPackageBean> dataList = getDataPackageNameAndExpired(sub, language, response);
//                    accInfo.setDataPkgName(getDataPackageName(dataList));
//                    accInfo.setName(subBean.getProductCode());
//                    getPreOrPostPaidInfoExtra(sub, accInfo);
                    BaseResponseBean result = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
                    result.setWsResponse(isdnInfo);
                    return result;
                } else {
                    return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
                }
            } else {
                return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
            }
        } catch (Exception e) {
            logger.error("getPrePaidInfo(): ", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    private BaseResponseBean getPrePaidInfoByTypeBalanceExchange(String isdn, Sub subBean, String language) {
//        ViettelService request = new ViettelService();
        ViettelService response = null;
        String msisdn = getCountryCode() + isdn;
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
//        try {
//            channel = proService.getChannel();
//        } catch (Exception e) {
//            logger.error("error", e);
//        }
        try {
//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);
//            logger.info("Request provisioning:\n" + request);
            // call pro
            response = ServiceClient.getInforSub(msisdn);
            logger.info("Response provisioning:\n" + response);

            if (response != null) {

                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    String typeExchange = configUtils.getMessage("TYPE_BALANCE_EXCHANGE");
                    String typeData = configUtils.getMessage("TYPE_BALANCE_DATA");
                    List<AccConfig> accConfigList = myvtgService.getAllAccConfig();
                    IsdnInfoBean isdnInfo = new IsdnInfoBean();
                    List<ProvisionBalanceBean> balanceList = getBalanceList(response, accConfigList, -1);
                    isdnInfo.setExchange(Double.parseDouble(dfCurrency.format(getSumAccByType(balanceList, typeExchange))));
                    isdnInfo.setLstExchange(getListAccByType(balanceList, typeExchange));
                    BaseResponseBean result = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
                    result.setWsResponse(isdnInfo);
                    return result;
                } else {
                    return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
                }
            } else {
                return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
            }
        } catch (Exception e) {
            logger.error("getPrePaidInfo(): ", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * responseBean
     *
     * @param errorCode
     * @param description
     * @param content
     * @param language
     *
     * @return
     */
    private BaseResponseBean responseBean(String errorCode, String description, String content, String language) {
        return new BaseResponseBean(errorCode, messageUtil.getMessage(description + language), messageUtil.getMessage(content + language));
    }

    /**
     * wsGetListCamIDNotification(testing now)
     *
     * @param request
     * @param language
     *
     * @return
     *
     * @author kiennt88
     */
    @Override
    public BaseResponseBean wsGetListCamIDNotification(RequestBean request, String language) {
        boolean isActiveWithDeviceId = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("camId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camId").toString())) {
                if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                    logger.info("Error request : camId or deviceId is null ");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.camId.empty.", language);
                }
                isActiveWithDeviceId = true;
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                logger.info("Error request : camId or deviceId is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.saveInfo.deviceId.empty.", language);
            }

            String pageStr = "1";
            if (request.getWsRequest().get("page").toString() != null) {
                pageStr = DataUtil.isNullOrEmpty(request.getWsRequest().get("page").toString()) ? "1" : request.getWsRequest().get("page").toString();
            }
            int page = Integer.parseInt(pageStr);
            Map<String, Object> result = new HashMap<>();
            if (isActiveWithDeviceId) {
                String deviceId = request.getWsRequest().get("deviceId").toString();
                /*Get time this isdn clean all before*/
                boolean isClearAllBefore = apiGwService.isClearAllBeforeUnknow(deviceId);
                boolean isBlockNotification = apiGwService.isBlockNotificationUnknow(deviceId);
                boolean isCheckUnread = true;
                List<CamIdNotificationBean> notificationList = apiGwService.wsGetListCamIDNotificationUnknow(deviceId, PAGE_NUMBER, page, isClearAllBefore, !isCheckUnread, isBlockNotification);

                List<CamIdNotificationBean> unReadNotificationList = apiGwService.wsGetListCamIDNotificationUnknow(deviceId, PAGE_NUMBER, page, isClearAllBefore, isCheckUnread, isBlockNotification);
                int total = DataUtil.isNullOrEmpty(notificationList) ? 0 : notificationList.size();
                int unread = DataUtil.isNullOrEmpty(unReadNotificationList) ? 0 : unReadNotificationList.size();
                result.put("total", total);
                result.put("page", page);
                //get 3 latest notify
                List<CamIdNotificationBean> newNotification = new ArrayList<>();
                List<CamIdNotificationBean> earlierNotification = new ArrayList<>();

                if (total >= NEW_NOTIFICATION_NUMBER) {
                    newNotification = notificationList.subList(0, 3);
                    earlierNotification = notificationList.subList(3, total);
                } else {
                    newNotification = notificationList;
                }
                result.put("new", newNotification);
                result.put("earlier", earlierNotification);
                result.put("unread", unread);
            } else {
                String camId = request.getWsRequest().get("camId").toString();
                String deviceId = request.getWsRequest().get("deviceId").toString();
                /*Get time this isdn clean all before*/
                boolean isClearAllBefore = apiGwService.isClearAllBefore(camId);
                boolean isBlockNotification = apiGwService.isBlockNotification(camId);
                boolean isCheckUnread = true;

                //Get exactly osDevice
                String osDevice = apiGwService.getOSdevice(camId, deviceId);

                List<CamIdNotificationBean> notificationList = apiGwService.wsGetListCamIDNotification(camId, PAGE_NUMBER,
                        page, isClearAllBefore, isBlockNotification, !isCheckUnread, osDevice, deviceId);

                List<CamIdNotificationBean> unReadNotificationList = apiGwService.wsGetListCamIDNotification(camId, PAGE_NUMBER,
                        page, isClearAllBefore, isBlockNotification, isCheckUnread, osDevice, deviceId);

                int total = DataUtil.isNullOrEmpty(notificationList) ? 0 : notificationList.size();
                int unread = DataUtil.isNullOrEmpty(unReadNotificationList) ? 0 : unReadNotificationList.size();
                result.put("total", total);
                result.put("page", page);
                //get 3 latest notify
                List<CamIdNotificationBean> newNotification = new ArrayList<>();
                List<CamIdNotificationBean> earlierNotification = new ArrayList<>();

                if (total >= NEW_NOTIFICATION_NUMBER) {
                    newNotification = notificationList.subList(0, 3);
                    earlierNotification = notificationList.subList(3, total);
                } else {
                    newNotification = notificationList;
                }
                result.put("new", newNotification);
                result.put("earlier", earlierNotification);
                result.put("unread", unread);
            }
            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            bean.setWsResponse(result);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * wsUpdateIsReadCamIDNotification(testing now)
     *
     * @param request
     * @param language
     *
     * @return
     *
     * @author kiennt88
     */
    @Override
    public BaseResponseBean wsUpdateIsReadCamIDNotification(RequestBean request, String language) {
        boolean isUpdateWithDeviceId = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("camid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camid").toString())) {
                if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                    logger.info("Error request : camid is null ");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.camid.empty.", language);
                } else {
                    isUpdateWithDeviceId = true;
                }
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("notifyId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notifyId").toString())) {
                logger.info("Error request : notifyIdStr is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.notifyId.empty.", language);
            }
            String notifyIdStr = request.getWsRequest().get("notifyId").toString();
            Long notifyId = Long.parseLong(notifyIdStr);
            Map<String, Object> result = new HashMap<>();
            if (isUpdateWithDeviceId) {
                String deviceId = request.getWsRequest().get("deviceId").toString().trim();
                /*Check camid call api isRead already or not, if called so cancel this request*/
                boolean isReadNotifyBefore = apiGwService.isReadNotifyBeforeUnknow(deviceId, notifyId);
                if (isReadNotifyBefore) {
                    logger.info("### Update fail because read already");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.update.isRead.already.", language);
                }
                int updateStatus = apiGwService.wsUpdateIsReadCamIDNotificationUnknow(notifyId, deviceId);
                if (updateStatus == 0) {
                    logger.info("Error request :update fail ");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.update.isRead.fail.", language);
                }
                result.put("updated", updateStatus);
                BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
                bean.setWsResponse(result);
                return bean;
            } else {
                String camid = request.getWsRequest().get("camid").toString().trim();
                /*Check camid call api isRead already or not, if called so cancel this request*/
                boolean isReadNotifyBefore = apiGwService.isReadNotifyBefore(camid, notifyId);
                if (isReadNotifyBefore) {
                    logger.info("### Update fail because read already");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.update.isRead.already.", language);
                }

                int updateStatus = apiGwService.wsUpdateIsReadCamIDNotification(notifyId, camid);
                if (updateStatus == 0) {
                    logger.info("Error request :update fail ");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.update.isRead.fail.", language);
                }
                result.put("updated", updateStatus);
                BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
                bean.setWsResponse(result);
                return bean;
            }
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean wsGetCurrentUsedServicesExpired(RequestBean request, String language) {
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            List<ServiceBean> services = new ArrayList<>();
            String isdn = request.getWsRequest().get("isdn").toString();

            Date startTime = DataUtil.isNullObject(request.getWsRequest().get("startTime")) ? DataUtil.addTime(new Date(), 0, null, null, null, 5) : new Date(Long.parseLong(request.getWsRequest().get("startTime").toString()));

            Date endTime = DataUtil.isNullObject(request.getWsRequest().get("endTime")) ? new Date() : new Date(Long.parseLong(request.getWsRequest().get("endTime").toString()));

            if (DataUtil.addTime(endTime, -31, null, null, null, 5).getTime() > startTime.getTime()) {
                logger.info("Error request : startTime over 30 days ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.startTime.fail.", "myMetfone.Ishare.startTime.over.", startTime.toString());
            }

            Sub sub = myvtgService.findByIsdn(isdn);
            if (sub != null) {
                String listRegisteredServiceCodes = null;
                if (sub.getSubType() == Constants.SUBTYPE_TRATRUOC) {
                    //daibq  bo sung dong bo du lieu vao bang sub_rel_product_new
                    logger.info("Start call API getInfoPackageVas ");
                    Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_SYS_VAS_RECORD);//wsGetInfoPackageVas
                    logger.error("Time start" + new Date());
//                BaseRequestDto rq = new BaseRequestDto(new RequestDto(isdn, sub.getSubId(), language, dateFormat.format(new Date())));
                    RequestDto rq = new RequestDto(isdn, sub.getSubId(), dateFormat.format(new Date()));
                    String rqStr = CommonUtil.convertObjectToJsonString(rq);
                    logger.info("Requesst send getInfoPackageVas: " + rqStr);
                    WebServiceUtil.callApiRest(ws.getUrl(), rqStr);// update other late
                    logger.error("End start" + new Date());
                    logger.info("End call API getInfoPackageVas ");
                    listRegisteredServiceCodes = cmpreService.getListRegisteredServiceCodesExpired(sub.getSubId(), startTime, endTime);
                } else {
                    listRegisteredServiceCodes = cmposService.getListRegisteredServiceCodesExpired(sub.getSubId(), startTime, endTime);
                }
                services = myvtgService.getCurrentUsedAllServices(language, isdn, listRegisteredServiceCodes);
            }
            if (services.isEmpty()) {
                services = null;
            }
            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            bean.setWsResponse(services);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean wsClearAllCamIdNotification(RequestBean request, String language) {
        boolean isUpdateWithDeviceId = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("camid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camid").toString())) {
                if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                    logger.info("Error request : camid or deviceId is null ");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.camid.empty.", language);
                } else {
                    isUpdateWithDeviceId = true;
                }
            }
            boolean isCleanSuccess = false;
            if (isUpdateWithDeviceId) {
                String deviceId = request.getWsRequest().get("deviceId").toString();
                isCleanSuccess = apiGwService.clearAllCamIdNotificationUnknow(deviceId);
            } else {
                String camid = request.getWsRequest().get("camid").toString();
                isCleanSuccess = apiGwService.clearAllCamIdNotification(camid);
            }
            BaseResponseBean bean = new BaseResponseBean();
            if (isCleanSuccess) {
                bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
                bean.setWsResponse("1");
            } else {
                bean = responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.des.fail.", language);
            }
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean wsControlCamIdNotification(RequestBean request, String language) {
        boolean isUpdateWithDeviceId = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("camid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camid").toString())) {
                if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                    logger.info("Error request : camid and deviceId is null ");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.camid.empty.", language);
                } else {
                    isUpdateWithDeviceId = true;
                }
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("blockReceive")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("blockReceive").toString())) {
                logger.info("Error request : blockReceive is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.blockReceive.empty.", language);
            }
            String blockReceive = request.getWsRequest().get("blockReceive").toString();
            boolean isBlockSuccess = false;
            BaseResponseBean bean = new BaseResponseBean();
            if (isUpdateWithDeviceId) {
                String deviceId = request.getWsRequest().get("deviceId").toString();
                isBlockSuccess = apiGwService.blockReceiveCamidNotificationUnknow(deviceId, blockReceive);
            } else {
                String camid = request.getWsRequest().get("camid").toString();
                isBlockSuccess = apiGwService.blockReceiveCamidNotification(camid, blockReceive);
            }
            if (isBlockSuccess) {
                bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
                bean.setWsResponse("1");
            } else {
                bean = responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.des.fail.", language);
            }
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getCamIdNotificationStatus(RequestBean request, String language) {
        boolean isUpdateWithDeviceId = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("camid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camid").toString())) {
                if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                    logger.info("Error request : camid or deviceId is null ");
                    return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.camid.empty.", language);
                }
                isUpdateWithDeviceId = true;
            }
            boolean statusBlock;
            if (isUpdateWithDeviceId) {
                String camid = request.getWsRequest().get("deviceId").toString();
                statusBlock = apiGwService.isBlockNotificationUnknow(camid);
            } else {
                String camid = request.getWsRequest().get("camid").toString();
                statusBlock = apiGwService.isBlockNotification(camid);
            }
            BaseResponseBean bean = new BaseResponseBean();
            Map<String, Boolean> mapping = new HashMap<String, Boolean>();
            mapping.put("isBlock", statusBlock);
            bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            bean.setWsResponse(mapping);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean checkSubInfo(RequestBean request, String language) {
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "camid.checkSubInfo.des.fail.", "camid.checkSubInfo.isdn.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("language")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                logger.info("Error requesst : language is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "camid.checkSubInfo.des.fail", "myMetfone.Ishare.language.empty.", language);
            }

            String isdn = request.getWsRequest().get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            Double basicBal = getBasicBal(isdn);
            boolean isCheck = wsCheckExchange150Package(isdn);
            BaseResponseBean bean = new BaseResponseBean();
            Map<String, Object> mapping = new HashMap<>();
            mapping.put("basic_balance", basicBal);
            mapping.put("exist_su", isCheck);
            bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            bean.setWsResponse(mapping);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateCamIdAccessStatus(RequestBean request, String language) {
        try {
//            if (DataUtil.isNullObject(request.getWsRequest().get("camid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camid").toString())) {
//                logger.info("Error request : camid is null ");
//                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.camid.empty.", language);
//            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isLogin")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isLogin").toString())) {
                logger.info("Error request : IsLogin is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.camid.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                logger.info("Error request : deviceId is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.camid.empty.", language);
            }

            String camid = request.getWsRequest().get("camid") == null ? "" : request.getWsRequest().get("camid").toString();
            String isLogin = request.getWsRequest().get("isLogin").toString();
            String deviceId = request.getWsRequest().get("deviceId").toString();
            String lang = request.getWsRequest().get("language").toString();
            String versionApp = request.getWsRequest().get("versionApp") == null ? "" : request.getWsRequest().get("versionApp").toString();
            if (!"0".equals(isLogin) && !"1".equals(isLogin)) {
                logger.info("Error request : isLogin value is wrong ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "camId.islogin.value.", language);
            }
            //Get deviceid
            boolean statusUpdate = apiGwService.updateCamIdStatus(camid, isLogin, deviceId, lang, versionApp);
            BaseResponseBean bean;
            Map<String, Boolean> mapping = new HashMap<String, Boolean>();
            mapping.put("isUpdate", statusUpdate);
            if (statusUpdate) {
                bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            } else {
                bean = responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.des.fail.", language);
            }
            bean.setWsResponse(mapping);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * getBasicBal
     *
     * @param isdn
     * @param language
     *
     * @return
     *
     * @author kiennt88
     */
    private Double getBasicBal(String isdn) {
//        ViettelService request = new ViettelService();
        ViettelService response = null;
        String msisdn = getCountryCode() + isdn;
//        String processCode = environment.getRequiredProperty("pro_processcode_view_cuoc");
        Double basicBalance = 0.0;
        try {
//            request.setMessageType("1900");
//            request.setProcessCode(processCode);
//            request.set("MSISDN", msisdn);
//            logger.info("Request provisioning:\n" + request);
            // call pro
            response = ServiceClient.getInforSub(msisdn);
            logger.info("Response provisioning:\n" + response);
            if (response != null) {
                String prRespondCode = response.get("responseCode").toString();
                if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
                    String typeBasic = configUtils.getMessage("TYPE_BALANCE_BASIC");
                    List<AccConfig> accConfigList = myvtgService.getAllAccConfig();
                    IsdnInfoBean isdnInfo = new IsdnInfoBean();
                    List<ProvisionBalanceBean> balanceList = getBalanceList(response, accConfigList, -1);
                    basicBalance = Double.parseDouble(dfCurrency.format(getSumAccByType(balanceList, typeBasic)));
                    return basicBalance;
                }
            }
        } catch (Exception e) {
            logger.error("getPrePaidInfo(): ", e);
//            logger.info("Request provisioning:\n" + request);
            logger.info("Response provisioning:\n" + response);
        }
        return basicBalance;
    }

    /**
     * wsCheckPackage
     *
     * @param isdn
     * @param language check SU100$SU101
     * @return
     *
     * @author kiennt88
     */
    public boolean wsCheckExchange150Package(String isdn) {
        try {
            List<ServiceBean> services = new ArrayList<>();
            AllCustSubForSelfcareBean sub = cmpreService.getCustSubForSelfcare(isdn);
            if (sub != null) {
                String listRegisteredServiceCodes = null;
                listRegisteredServiceCodes = cmpreService.getListRegisteredServiceCodes(sub.getSubId());
                String productCodes = configUtils.getMessage("EX_PACKAGE");
                List<String> listProductCodes = Stream.of(productCodes.split(",")).collect(Collectors.toList());
                for (String productCode : listProductCodes) {
                    if (listRegisteredServiceCodes.contains(productCode)) {
                        return true;
                    }
                }
            }
            if (services.isEmpty()) {
                services = null;
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
        }
        return false;
    }

    @Override
    public BaseResponseBean wsHistoryTopUpNewByDate(RequestBean request, String language) {
        logger.info("Start business wsHistoryTopup off AccountServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            String toDate = (DataUtil.isNullObject(request.getWsRequest().get("toDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("toDate").toString())) ? DateTimeUtils.getSysDateTime() : (request.getWsRequest().get("toDate").toString());

            //set default khi khong co data fromDate tu request
            Date tDate = DateTimeUtils.convertStringToDateTime(toDate);
            Date d = DateTimeUtils.addNumDay(tDate, -90);
            String frDate = DateTimeUtils.convertDateTimeToString(d);

            String fromDate = (DataUtil.isNullObject(request.getWsRequest().get("fromDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fromDate").toString())) ? frDate : (request.getWsRequest().get("fromDate").toString());

            Date startDate = DateTimeUtils.convertStringToDateTime(fromDate);
            Date endDate = DateTimeUtils.convertStringToDateTime(toDate);
            if (startDate.getTime() > endDate.getTime()) {
                logger.info("Error requesst :  Max fromDate > toDate ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "ftth.fromDate.toDate.failed.", language);
            }

            Integer total = (DataUtil.isNullObject(request.getWsRequest().get("total"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("total").toString())) ? 1000
                    : Integer.parseInt(request.getWsRequest().get("total").toString());
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            int pageSize = request.getWsRequest().get("pageSize") != null ? Integer.valueOf(request.getWsRequest().get("pageSize").toString().trim()) : null;
            int pageNum = request.getWsRequest().get("pageNum") != null ? Integer.valueOf(request.getWsRequest().get("pageNum").toString().trim()) : null;
            isdn = DataUtil.fomatIsdn(isdn);
            List<Object> list = myvtgService.getHistoryTopUpNewByDate(isdn, "OCSHW_PAYMENT", fromDate, toDate, total, pageSize, pageNum);
            int totalHistoryTopup = myvtgService.getTotalHistoryTopUp(isdn, "OCSHW_PAYMENT", fromDate, toDate);
            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setTotal(totalHistoryTopup);
            metfoneBean.setLstHisTopup(list);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }
    @Override
    public BaseResponseBean wsHistoryTopUpLucky(RequestBean request, String language) {
        logger.info("Start business wsHistoryTopupLucky off AccountServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            String toDate = (DataUtil.isNullObject(request.getWsRequest().get("toDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("toDate").toString())) ? DateTimeUtils.getSysDateTime() : (request.getWsRequest().get("toDate").toString());

            //set default khi khong co data fromDate tu request
            Date tDate = DateTimeUtils.convertStringToDateTime(toDate);
            Date d = DateTimeUtils.addNumDay(tDate, -90);
            String frDate = DateTimeUtils.convertDateTimeToString(d);

            String fromDate = (DataUtil.isNullObject(request.getWsRequest().get("fromDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fromDate").toString())) ? frDate : (request.getWsRequest().get("fromDate").toString());

            Date startDate = DateTimeUtils.convertStringToDateTime(fromDate);
            Date endDate = DateTimeUtils.convertStringToDateTime(toDate);
            if (startDate.getTime() > endDate.getTime()) {
                logger.info("Error requesst :  Max fromDate > toDate ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "ftth.fromDate.toDate.failed.", language);
            }

            Integer total = (DataUtil.isNullObject(request.getWsRequest().get("total"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("total").toString())) ? 10000
                    : Integer.parseInt(request.getWsRequest().get("total").toString());
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            isdn = isdn.trim().replaceAll("[^0-9]", "");
            int pageSize = request.getWsRequest().get("pageSize") != null ? Integer.valueOf(request.getWsRequest().get("pageSize").toString().trim()) : null;
            int pageNum = request.getWsRequest().get("pageNum") != null ? Integer.valueOf(request.getWsRequest().get("pageNum").toString().trim()) : null;
            String userId = request.getWsRequest().get("userId") != null ? request.getWsRequest().get("userId").toString() : "";
            List<Object> list = new ArrayList<>();
            int totalHistoryTopup = 0;
            isdn = DataUtil.fomatIsdn(isdn);

            list = myvtgService.getHistoryTopUpLucky(isdn, "OCSHW_PAYMENT", fromDate, toDate, total, pageSize, pageNum, "0", userId);
            totalHistoryTopup = myvtgService.getTotalHistoryTopUpLucky(isdn, "OCSHW_PAYMENT", fromDate, toDate, userId);

            BaseResponseBean bean = responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language);
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setTotal(totalHistoryTopup);
            metfoneBean.setLstHisTopup(list);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @Override
    public void wsDoRechargeCardLucky(String isCheckSub, String isdn, String desIsdn, String serial, String language,
                                      ResponseSuccessBean bean, String programCode, String captchaCodeStr, String userId) throws Exception {
        logger.info("Start wsDoRechargeCard");
        int desSubType = Constants.SUBTYPE_TRATRUOC;
        if(DataUtil.isNullObject(isdn)){
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage("myMetfone.isdn.empty." + language));
            bean.setUserMsg(messageUtil.getMessage("myMetfone.isdn.empty." + language));
            return;
        }
        AllCustSubForSelfcareBean preSubMb = cmpreService.getCustSubForSelfcare(desIsdn);

        if (preSubMb == null) {
            logger.info("preSubMb is null");
            if (IS_CHECK_SUB.equalsIgnoreCase(isCheckSub)) {
                // try to query on postpaid DB
                co.siten.myvtg.model.cmpos.AllTelServiceSubSelfcare posSubMb = cmposService
                        .getTelServiceSubSelfcare(desIsdn);
                if (posSubMb == null) {
                    bean.setErrorCode(Constants.ERROR_CODE_3);
                    bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.0." + language));
                    return;
                } else {
                    if (Constants.ACT_STATUS_03.equalsIgnoreCase(posSubMb.getActStatusBits())) {
                        bean.setErrorCode(Constants.ERROR_CODE_3);
                        bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.1." + language));
                        return;
                    } else if (!LAST_ACC_STATUS.equalsIgnoreCase(posSubMb.getActStatusBits().substring(0, 1))) {
                        bean.setErrorCode(Constants.ERROR_CODE_3);
                        bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.2." + language));
                        return;
                    }

                    desSubType = Constants.SUBTYPE_TRASAU;
                }
            } else {
                desSubType = Constants.SUBTYPE_TRASAU;
            }
        } else if (Constants.ACT_STATUS_03.equalsIgnoreCase(preSubMb.getActStatusBits())) {
            bean.setErrorCode(Constants.ERROR_CODE_3);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.1." + language));
            return;
        } else if (!LAST_ACC_STATUS.equalsIgnoreCase(preSubMb.getActStatusBits().substring(0, 1))) {
            bean.setErrorCode(Constants.ERROR_CODE_3);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.error.3.2." + language));
            return;
        }
        //daibq bo sung luong validate va them captcha
        logger.info("Start get captcha");
        if (DataUtil.isNullOrEmpty(programCode)) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage("myMetfone.toup.err.old.version." + language));
            return;
        }
        Captcha captcha = myvtgService.getCaptcha(isdn, programCode);
        Date dateCurr = new Date();
        // neu qua ngay thi update lai total error
        if (!DataUtil.isNullObject(captcha)
                && DataUtil.addTime(captcha.getCreateDate(), 1, null, null, null, 5).getTime() <= DataUtil.addTime(dateCurr, null, null, null, null, 4).getTime()) {
            captcha.setStatus(0L);
            captcha.setTotalError(0L);
            captcha.setCreateDate(new Date());
            captcha.setUpdateDate(null);
            myvtgService.update(captcha);
        }
        String maxErr10 = configUtils.getMessage("MAX_ERR_CAPT_10", "10").trim();
        String maxErr5 = configUtils.getMessage("MAX_ERR_CAPT_5", "5").trim();
        int expired = 0;
        expired = Integer.parseInt(configUtils.getMessage("EXPRIED_CAPTCHA", "180").trim());

        String captchaCode = DataUtil.rand(111, 999999);
        if (!DataUtil.isNullObject(captcha) && captcha.getTotalError() >= Integer.parseInt(maxErr10)) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(String.format(messageUtil.getMessage("myMetfone.toup.err.10." + language), maxErr10));
            bean.setUserMsg(String.format(messageUtil.getMessage("myMetfone.toup.err.10." + language), maxErr10));
            return;
        }
        //check truong hop bat dau nhap qua n config lan
        if (!DataUtil.isNullObject(captcha)
                && Long.parseLong(maxErr5) == captcha.getTotalError()) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), maxErr5));
            bean.setUserMsg(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), maxErr5));
            //dax thuc hien qua n lan. bat dau sinh captcha cho lan n +1
            Date dateEx = DataUtil.addSecond(dateCurr, expired);
            captcha.setCreateDate(dateCurr);
            captcha.setStatus(0L);
            captcha.setUpdateDate(null);
            captcha.setCaptchaCode(captchaCode);
            captcha.setTotalError(captcha.getTotalError() + 1L);
            captcha.setExpiredTime(dateEx);
            myvtgService.update(captcha);
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setCaptchaCode(captchaCode);
            metfoneBean.setTimeOut(String.valueOf(expired));
            bean.setWsResponse(metfoneBean);
            return;
        }
        //check truong hop bat dau nhap qua n +1 config lan
        if (!DataUtil.isNullObject(captcha)
                && (Long.parseLong(maxErr5) + 1L) <= captcha.getTotalError()) {
            // reload cho truong hop vao lai menu topup khi da sai  lon hon 5 lan
            if (DataUtil.isNullOrEmpty(captchaCodeStr)) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), captcha.getTotalError().toString()));
                bean.setUserMsg(String.format(messageUtil.getMessage("myMetfone.toup.err.5." + language), captcha.getTotalError().toString()));
                Date dateEx = DataUtil.addSecond(dateCurr, expired);
                captcha.setCreateDate(dateCurr);
                captcha.setUpdateDate(null);
                captcha.setStatus(0L);
                captcha.setCaptchaCode(captchaCode);
                captcha.setExpiredTime(dateEx);
                myvtgService.update(captcha);
                MetfoneBean metfoneBean = new MetfoneBean();
                metfoneBean.setCaptchaCode(captchaCode);
                metfoneBean.setTimeOut(String.valueOf(expired));
                bean.setWsResponse(metfoneBean);
                return;
            }
            if (!captchaCodeStr.equals(captcha.getCaptchaCode())
                    || dateCurr.getTime() > captcha.getExpiredTime().getTime()
                    || captcha.getStatus() >= 1L) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage(messageUtil.getMessage("myMetfone.toup.err.expired." + language));
                bean.setUserMsg(messageUtil.getMessage("myMetfone.toup.err.expired." + language));
                //bat dau tu lan nay khi thuc hien sai serial voi bat dau thuc hien up date lai total error
                Date dateEx = DataUtil.addSecond(dateCurr, expired);
                captcha.setCreateDate(dateCurr);
                captcha.setUpdateDate(null);
                captcha.setStatus(0L);
                captcha.setCaptchaCode(captchaCode);
                captcha.setExpiredTime(dateEx);
                myvtgService.update(captcha);
                MetfoneBean metfoneBean = new MetfoneBean();
                metfoneBean.setCaptchaCode(captchaCode);
                metfoneBean.setTimeOut(String.valueOf(expired));
                bean.setWsResponse(metfoneBean);
                return;
            }
        }
        if (serial != null && serial.trim().length() > SERIAL_LENGTH) {
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
            if (DataUtil.isNullObject(captcha)) {
                captcha = new Captcha();
                captcha.setCreateDate(dateCurr);
                captcha.setExpiredTime(DataUtil.addSecond(dateCurr, expired));
                captcha.setStatus(0L);
                captcha.setIsdn(isdn);
                captcha.setProgramCode(programCode);
                captcha.setCaptchaCode(captchaCode);
                captcha.setTotalError(1L);
                myvtgService.insert(captcha);
//                myvtgService.insertCaptcha(isdn, captchaCode, expired, programCode, 1L);
            } else {
                captcha.setTotalError(captcha.getTotalError() + 1L);
                if ((Long.parseLong(maxErr5) + 1L) <= captcha.getTotalError()) {
                    bean.setErrorCode(Constants.ERROR_CODE_1);
                    Date dateEx = DataUtil.addSecond(dateCurr, expired);
                    captcha.setCreateDate(dateCurr);
                    captcha.setStatus(0L);
                    captcha.setUpdateDate(null);
                    captcha.setCaptchaCode(captchaCode);
                    captcha.setExpiredTime(dateEx);
                    MetfoneBean metfoneBean = new MetfoneBean();
                    metfoneBean.setCaptchaCode(captchaCode);
                    metfoneBean.setTimeOut(String.valueOf(expired));
                    bean.setWsResponse(metfoneBean);
                }
                myvtgService.update(captcha);
            }
            return;
        }

        ChargeHis chargeHis = new ChargeHis();
        if (desSubType == Constants.SUBTYPE_TRATRUOC) {
            chargeHis = rechargePrepaidLucky(isdn, desIsdn, serial, language, bean, programCode, captcha, maxErr5, expired, dateCurr, userId);
        } else if (desSubType == Constants.SUBTYPE_TRASAU) {
            chargeHis = rechargePostPaidLucky(isdn, desIsdn, serial, language, bean, userId);
        }
        String numSpin = "0";
        if(0 == chargeHis.getResultCode()){
            if(chargeHis.getRefillAmount().compareTo(new BigDecimal(1)) >= 0 && chargeHis.getRefillAmount().compareTo(new BigDecimal(2)) < 0){
                numSpin = "1";
            }else if(chargeHis.getRefillAmount().compareTo(new BigDecimal(2)) >= 0 && chargeHis.getRefillAmount().compareTo(new BigDecimal(5)) < 0){
                numSpin = "2";
            }else if(chargeHis.getRefillAmount().compareTo(new BigDecimal(5)) >= 0 && chargeHis.getRefillAmount().compareTo(new BigDecimal(10)) < 0){
                numSpin = "5";
            }else if(chargeHis.getRefillAmount().compareTo(new BigDecimal(10)) >= 0 && chargeHis.getRefillAmount().compareTo(new BigDecimal(20)) < 0){
                numSpin = "10";
            }else if(chargeHis.getRefillAmount().compareTo(new BigDecimal(20)) >= 0){
                numSpin = "20";
            }
            Webservice ws = myvtgDao.getWebserviceByName("addSpinTopupLucky");
            URL url = new URL(ws.getUrl());
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Content-Type", "application/json");

            String data = "{\n   \"sessionId\":\"7ea1801b-577f-4c32-bdb3-c3b9fb128f86\",\n   \"wsCode\":\"wsAddSpin\",\n   \"apiKey\":\"7ea1801b-577f-4c32-bdb3-c3b9fb128f86\",\n    \"token\":\"GF8kEJwpzz3B6p/df9KnIQ==\",\n   \"wsRequest\":{\n      \"programeCode\":\"TOPUPWEEKLY\",\n      \"msisdn\": \""+ isdn.trim() +"\",\n      \"numSpin\":\""+ numSpin.trim() +"\",\n       \"language\":\"en\"\n   }\n}\n";

            byte[] out = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            logger.info("Add spin: " + http.getResponseMessage());
            http.disconnect();
        }

        MetfoneBean metfoneBean = new MetfoneBean();
        metfoneBean.setTotalAmount( null != chargeHis.getRefillAmount() ? chargeHis.getRefillAmount().toString() : null);
        metfoneBean.setPinCode(chargeHis.getPinNumber());
        metfoneBean.setNumSpin(numSpin);
        bean.setWsResponse(metfoneBean);
    }

    private ChargeHis rechargePrepaidLucky(String isdn, String desIsdn,
                                           String serial, String language, ResponseSuccessBean bean,
                                           String programCode, Captcha captcha, String maxErr5, int expired, Date dateCurr, String userId
    )
            throws Exception {
        logger.info("Start rechargePrepaid of AccountServiceImpl");
        TransactionLog charLog;
        TransactionLog lockLog;
        String typeRecharge = environment.getRequiredProperty("recharge_type");

        // Init log
        ChargeHis c = new ChargeHis();
        c.setServiceId("PrePaid");
        c.setFee(BigDecimal.ZERO);
        c.setMsisdn(desIsdn);
        c.setRefillIsdn(isdn);
        c.setPinNumber(serial);
        if(!DataUtil.isNullObject(userId)){
            c.setUserId(new BigDecimal(userId));
        }
        // c.setSeriNumber(serial);
        String captchaCode = DataUtil.rand(111, 999999);
        if (isMetfoneMarket()) {
            // STEP 1: try to lock card and get value
            logger.info("Start connection with typeRecharge: " + typeRecharge);
            channel = proService.getChannel(typeRecharge);

            CardUtil cardUtil = new CardUtil(channel);
            logger.info("Start log card with serial: " + serial);
            lockLog = cardUtil.viewCardExchange(desIsdn, serial, environment);
            logger.info("End log card with error code: " + lockLog.getErrorCode());
            //daibq bo sung luu thue bao dn.
            lockLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
//            lockLog.setSeriNumber(serial);
            myvtgService.persistTransactionLog(lockLog);
            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // TODO: set user message with clear message, ex: The card is
                // already locked.
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));

                bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setRefillDate(lockLog.getRequestTime());
                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
                c.setTransactionLogId(lockLog.getId());
                c.setSeriNumber(lockLog.getSerial());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                //daibq bo sung luong captcha
                if (DataUtil.isNullObject(captcha)) {
                    captcha = new Captcha();
                    captcha.setCreateDate(dateCurr);
                    captcha.setExpiredTime(DataUtil.addSecond(dateCurr, expired));
                    captcha.setStatus(0L);
                    captcha.setIsdn(isdn);
                    captcha.setProgramCode(programCode);
                    captcha.setCaptchaCode(captchaCode);
                    captcha.setTotalError(1L);
                    myvtgService.insert(captcha);
                } else {
                    captcha.setTotalError(captcha.getTotalError() + 1L);
                    if ((Long.parseLong(maxErr5) + 1L) <= captcha.getTotalError()) {
                        bean.setErrorCode(Constants.ERROR_CODE_1);
                        Date dateEx = DataUtil.addSecond(dateCurr, expired);
                        captcha.setCreateDate(dateCurr);
                        captcha.setStatus(0L);
                        captcha.setUpdateDate(null);
                        captcha.setCaptchaCode(captchaCode);
                        captcha.setExpiredTime(dateEx);
                        MetfoneBean metfoneBean = new MetfoneBean();
                        metfoneBean.setCaptchaCode(captchaCode);
                        metfoneBean.setTimeOut(String.valueOf(expired));
                        bean.setWsResponse(metfoneBean);
                    }
                    myvtgService.update(captcha);
                }
                return c;
            }

            // STEP 2: try to add money
            //daibq update captcha cho case nhap serial success
            //case nay da kiem tra serial và lock success nen ko tac dong den captcha nua
            if (!DataUtil.isNullObject(captcha)) {
                captcha.setTotalError(0L);
                captcha.setStatus(1L);
                captcha.setUpdateDate(new Date());
                myvtgService.update(captcha);
            }

            Double monney = lockLog.getMoney();
            monney = monney / (Integer.parseInt(environment.getRequiredProperty("recharge_rate")));
            logger.info("Start pay money with serial: " + serial + ">>> monney: " + monney);
            charLog = cardUtil.chargeMoneyExchange(desIsdn, monney, environment);
            logger.info("End pay money with error code: " + charLog.getErrorCode());
            //daibq bo sung luu thue bao dn.
            charLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
//            charLog.setSeriNumber(serial);
            myvtgService.persistTransactionLog(charLog);

            c.setRefillDate(charLog.getRequestTime());
            c.setRefillAmount(new BigDecimal(Math.floor(monney * 100) / 100));

            if (charLog.getErrorCode() != null && !Constants.ERROR_CODE_0.equalsIgnoreCase(charLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setResult("UN-SUCC: Add balance, logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                c.setSeriNumber(lockLog.getSerial());
                myvtgService.persistCharhis(c);
                return c;
            } else {
                c.setResult("SUCC: logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_0));
                c.setSeriNumber(lockLog.getSeriNumber());
                myvtgService.persistCharhis(c);
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setCaptchaCode("");
            metfoneBean.setTimeOut("");
            bean.setWsResponse(metfoneBean);
            return c;

        } else {
            logger.info("isMetfoneMarket not metfone");
        }
        return c;
    }

    private ChargeHis rechargePostPaidLucky(String isdn, String desIsdn, String serial, String language, ResponseSuccessBean bean, String userId)
            throws Exception {
        TransactionLog charLog;
        TransactionLog lockLog;
        String typeRecharge = environment.getRequiredProperty("recharge_type");

        // Init log
        ChargeHis c = new ChargeHis();
        c.setServiceId("PrePaid");
        c.setFee(BigDecimal.ZERO);
        c.setMsisdn(desIsdn);
        c.setRefillIsdn(isdn);
        c.setPinNumber(serial);
        if(!DataUtil.isNullObject(userId)){
            c.setUserId(new BigDecimal(userId));
        }
        // c.setSeriNumber(serial);

        if (isMetfoneMarket() || isMovitel()) {
            // STEP 1: try to lock card and get value
            channel = proService.getChannel(typeRecharge);
            CardUtil cardUtil = new CardUtil(channel);
            lockLog = cardUtil.viewCardExchange(desIsdn, serial, environment);
            //daibq bo sung luu thue bao dn.
            lockLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
            myvtgService.persistTransactionLog(lockLog);
            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // TODO: set user message with clear message, ex: The card is
                // already locked.
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));

                bean.setUserMsg(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setRefillDate(lockLog.getRequestTime());
                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
                c.setTransactionLogId(lockLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return c;
            }

            // STEP 2: try to add money
            Double monney = lockLog.getMoney();
            monney = monney / (Integer.parseInt(environment.getRequiredProperty("recharge_rate")));
            charLog = cardUtil.chargeMoneyExchange(desIsdn, monney, environment);
            //daibq bo sung luu thue bao dn.
            charLog.setRefillIsdn(DataUtil.fomatIsdn(isdn));
            myvtgService.persistTransactionLog(charLog);

            c.setRefillDate(charLog.getRequestTime());
            c.setRefillAmount(new BigDecimal(Math.floor(monney * 100) / 100));

            if (charLog.getErrorCode() != null && !Constants.ERROR_CODE_0.equalsIgnoreCase(charLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage(messageUtil.getMessage("wsDoRecharge.fail." + language));
                c.setResult("UN-SUCC: Add balance, logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return c;
            } else {
                c.setResult("SUCC: logId = " + charLog.getId());
                c.setTransactionLogId(charLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_0));
                myvtgService.persistCharhis(c);
            }
            return c;

        } else {

            String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + isdn;
            String desMsIsdn = environment.getRequiredProperty("COUNTRY_CODE") + desIsdn;

            // STEP 1: try to lock card and get value
            channel = proService.getChannel(Constants.PRO);
            CardUtil cardUtil = new CardUtil(channel);

            lockLog = cardUtil.lockCard(msisdn, serial, environment);
            myvtgService.persistTransactionLog(lockLog);
            if (!Constants.ERROR_CODE_0.equalsIgnoreCase(lockLog.getErrorCode())) {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                // TODO: set user message with clear message, ex: The card is
                // already locked.
                c.setRefillDate(lockLog.getRequestTime());
                c.setResult("UN-SUCC: Lock card, logId = " + lockLog.getId());
                c.setTransactionLogId(lockLog.getId());
                c.setResultCode(Integer.parseInt(Constants.ERROR_CODE_1));
                myvtgService.persistCharhis(c);
                return c;
            }

            // STEP 2: try to add balance
            BigDecimal money = new BigDecimal(Long.parseLong(lockLog.getResultValue())
                    / (Integer.parseInt(environment.getRequiredProperty("recharge_rate"))));
            TransactionLog tlog = chargeMoneyRetry(isdn, desMsIsdn, serial, bean, cardUtil, money, c);
            if (bean.getErrorCode().equalsIgnoreCase(Constants.ERROR_CODE_1)) {
                // Init transaction log
                TransactionError te = new TransactionError();
                te.setInsertDate(CommonUtil.getCurrentTime());
                te.setShopCode(Constants.MYVTG);

                // Add balance
                try {
                    te.setRequest("Try to retry failed recharge command (more detail form log with Id=" + tlog.getId()
                            + " : Request from " + isdn + " to recharge for " + desIsdn + " ưith pin=" + serial
                            + ", money=" + money + " kip");
                    // try to add balance
                    ResponseObj responseObj = callApiChangeAccountBalance(Constants.SUBTYPE_TRATRUOC, money, isdn);

                    te.setResponse(responseObj.getResponseStr());
                    te.setErrorCode("Successful");
                    te.setMessage("Successfully add balance.");
                    logService.insertTransactionLog(te);
                } catch (Exception e) {
                    logger.error("Eror", e);
                    te.setErrorCode("Error");
                    te.setMessage(e.getMessage());
                    logService.insertTransactionLog(te);
                }
            }
        }
        return c;
    }
}
