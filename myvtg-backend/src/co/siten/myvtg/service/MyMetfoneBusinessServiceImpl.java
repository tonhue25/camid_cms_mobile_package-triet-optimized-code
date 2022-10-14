package co.siten.myvtg.service;

import co.siten.myvtg.bean.*;
import co.siten.myvtg.dao.ApiGwDao;
import co.siten.myvtg.dao.CmpreDao;
import co.siten.myvtg.dao.ImDao;
import co.siten.myvtg.dao.MkishareDao;
import co.siten.myvtg.dto.*;
import co.siten.myvtg.model.changeCardWS.ChangeCard;
import co.siten.myvtg.model.myvtg.*;
import co.siten.myvtg.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.dao.MyMetfoneBusinesDao;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.dao.PromoReportAppDao;
import co.siten.myvtg.dao.SmsDao;
import co.siten.myvtg.dao.SubDao;
import co.siten.myvtg.model.cmpre.SubMb;
import co.siten.myvtg.model.apigw.SubPushNotify;
import co.siten.myvtg.model.smsWS.SmsWebServiceResponse;
import com.viettel.bccs.cm.common.util.pre.DateUtils;
import com.viettel.common.ExchMsg;
import com.vtc.provisioning.client.Exchange;

import java.io.File;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author daibq
 */
@org.springframework.stereotype.Service("MyMetfoneBusinessService")

@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class MyMetfoneBusinessServiceImpl implements MyMetfoneBusinessService {

    @Autowired
    ExchangeService exchange;
    @Autowired
    private Environment environment;
    @Autowired
    ConfigUtils configUtils;
    @Autowired
    MyMetfoneBusinesDao dao;
    @Autowired
    SubDao subDao;
    @Autowired
    CmpreDao cmpreDao;
    @Autowired
    MkishareDao mkishareDao;
    @Autowired
    SmsService smsService;
    @Autowired
    MyvtgMasterDataDao myvtgDao;
    @Autowired
    ApiGwDao apiGwDao;
    @Autowired
    MyvtgService myvtgService;
    @Autowired
    SmsDao smsDao;
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    ImDao imDao;
    @Autowired
    PromoReportAppDao promoReportAppDao;
    @Autowired
    SmsServiceGlobal smsServiceGlobal;
    @Autowired
    FTTHService ftthService;
    @Autowired
    private FTPUploader fileUploader;

    private static final Logger logger = Logger.getLogger(MyMetfoneBusinessServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    private static final String DES_SUCC = "myMetfone.Ishare.des.succ.";
    private static final String RETURN_TAG = "return";
    private static final String FILE_NOT_FOUND = "FILE_NOT_FOUND";
    private static final String RESULT_TAG = "numberinfo";

    private static final String ISDN = "isdn";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String TYPE = "type";
    private static final String TYPE_ISDN = "typeisdn";
    private static final String monthlyFee = "monthlyfee";
    private static final String registerFee = "registerfee";
    private static final String TOTAL_PAGE = "totalPage";
    private static final String TOTAL_SIZE = "totalPage";

    private static final long NOT_UPDATE_YET = 1L;
    private static final long UPDATED = 0L;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat dateStr = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public static DecimalFormat dfCurrency = new DecimalFormat("#,###.#");
    public static final String getComTypeCamID_1 = "getComTypeCamID";
    String prefix;

    /**
     * @param request
     * @param language
     * @return
     * @author daibq getOTP commom
     */
    @Override
    public BaseResponseBean getOTPByService(RequestBean request, String language) {
        logger.info("Start business getOTP off MyMetfoneBusinessService");
        Date currDate = new Date();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("service")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("service").toString())) {
                logger.info("Error request : service is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.service.failed.", language);
            }
            String service = request.getWsRequest().get("service").toString();
            String isdn = request.getWsRequest().get("isdn").toString();
            isdn = DataUtil.fomatIsdn(isdn);
            int otp = dao.randomOTP();
            int expired = Integer.parseInt(configUtils.getMessage("EXPRIED_OTP", "180").trim());
            int maxOTP = Integer.parseInt(environment.getProperty("MAX_OTP_IN_DAY", "10").trim());
            Otp otpObj = dao.getOtpByIsdnAndService(isdn, service, null);

            // qua ngay thi reset total otp
            if (!DataUtil.isNullObject(otpObj)
                    && DataUtil.addTime(otpObj.getCreateDate(), 1, null, null, null, 5).getTime() <= DataUtil.addTime(currDate, null, null, null, null, 4).getTime()) {
                otpObj.setStatus(null);
                otpObj.setOtp(null);
                otpObj.setTotalGetOtp(0L);
                otpObj.setCreateDate(new Date());
                otpObj.setUpdateDate(null);
                otpObj.setExpireTime(null);
                dao.update(otpObj);
            }
            if (!"wsGetOTPDonate".equals(service)) {
                if (!DataUtil.isNullObject(otpObj) && maxOTP <= otpObj.getTotalGetOtp()) {
                    logger.info("Max send otp in day");
                    return responseUtil.responseBean(Constants.ERR_MAX_SEND_OTP, DES_FAIL, "mymetfone.check.max.otp.", language, String.valueOf(maxOTP), null, null, null);
                }
            }
            BaseResponseBean bean = new BaseResponseBean();
//            if ("wsGetOTPDonate".equals(service)) {
//                //Check phoneNumber locked or?
//                if (otpObj.getLockUntil() != null && otpObj.getLockUntil().after(new Date())) {
//                    logger.info("Error request : phoneNumber input wrong Otp more than 5 times");
//                    bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
//                    bean.setMessage("Fail");
//                    Map<String, String> data = new HashMap<String, String>();
//                    data.put("description", "request.over.otp.fail");
//                    data.put("remainTime", "" + DateTimeUtils.secondBetween2Dates(new Date(), otpObj.getLockUntil()));
//                    bean.setWsResponse(data);
//                    return bean;
//                }
//                //Check resend less than 30s
//                if (otpObj.getTimeAllowResend() != null && otpObj.getTimeAllowResend().after(new Date())) {
//                    logger.info("Error request : phoneNumber required resend OTP less than 30 second");
//                    bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
//                    bean.setMessage("Fail");
//                    Map<String, String> data = new HashMap<String, String>();
//                    data.put("description", "request.resend.otp.fail");
//                    data.put("remainTime", "" + DateTimeUtils.secondBetween2Dates(new Date(), otpObj.getTimeAllowResend()));
//                    bean.setWsResponse(data);
//                    return bean;
//                }
//            }
            int timeAllowResend = Integer.parseInt(configUtils.getMessage("TIME_ALLOW_RESEND", "30").trim());
            if (DataUtil.isNullObject(otpObj)) {
                otpObj = new Otp();
                otpObj.setIsdn(isdn);
                otpObj.setOtp(String.valueOf(otp));
                otpObj.setService(service);
                otpObj.setStatus(0L);
                otpObj.setTotalGetOtp(1L);
                otpObj.setCreateDate(currDate);
                otpObj.setExpireTime(DataUtil.addSecond(currDate, expired));
                otpObj.setTotalFail(0L);
                if ("wsGetOTPDonate".equals(service)) {
                    otpObj.setTimeAllowResend(DateTimeUtils.addNumSecond(currDate, timeAllowResend));
                }
                dao.save(otpObj);
            } else {
                otpObj.setStatus(0L);
                otpObj.setTotalGetOtp(otpObj.getTotalGetOtp() + 1);
                otpObj.setCreateDate(currDate);
                otpObj.setExpireTime(DataUtil.addSecond(currDate, expired));
                otpObj.setOtp(String.valueOf(otp));
                if ("wsGetOTPDonate".equals(service)) {
                    otpObj.setTimeAllowResend(DateTimeUtils.addNumSecond(currDate, timeAllowResend));
                }
                dao.update(otpObj);
            }

            String content = "";
            /*Check isdn's metfone or not*/
            String listIsdnMetfone = environment.getProperty("METFONE_ISDN", "97,88,71,31,60,66,67,68,90");
            int statusSend = 0;
            if (!StringUtils.isEmpty(listIsdnMetfone)) {
                String subIsdn = isdn.substring(0, 2);
                if (listIsdnMetfone.contains(subIsdn)) {
                    /*isdn's metfone*/
                    smsService.connectServer();
                    content = responseUtil.getMessage("myMetfone.sms.content." + language, String.valueOf(otp), null, null, null);
                    statusSend = smsService.sendUnicode(isdn, content);
                } else {
                    /*isdn's cellCard, Smart, ...*/
                    String contentType = "en".equals(language) ? "0" : "1";
                    content = responseUtil.getMessage("camId.sms.content." + language, String.valueOf(otp), null, null, null);
                    SmsWebServiceResponse smsWsResponse = smsServiceGlobal.callWebServiceSMSGlobal(DataUtil.fomatIsdn855(isdn), content, contentType);
                    try {
                        statusSend = Integer.valueOf(smsWsResponse.getResult());
                    } catch (NumberFormatException ne) {
                        logger.info("### Cannot get result after sent sms, get default value is -1");
                        statusSend = -1;
                    }
                }
            }
            if (statusSend == 1 || statusSend == 0) { // 1 is response from smsServiceGlobal
                return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "wsGetOtpIshare.success.", language);
            }
//            if (dao.getCountOtp(isdn, true, wsCode)) {
//                result = dao.updateOtp(isdn, String.valueOf(otp), expired, true, wsCode);
//            } else {
//                result = dao.insertOtp(isdn, String.valueOf(otp), expired, wsCode);
//            }
//            if (result != 0) {
//                smsService.connectServer();
//                int statusSend = smsService.sendSMS(isdn, content);
//                if (statusSend == 0) {
//                    return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "wsGetOtpIshare.success.", language);
//                }
//            }
            return responseUtil.responseBean(Constants.ERROR_SEND_SMS, DES_FAIL, "wsGetOtpIshare.fail.", language);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getOTPKeepChangeSim dung rieng cho nghiep vu changeIsdnKeepSim
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getOTPKeepChangeSim(RequestBean request, String language) {
        logger.info("Start business getOTP of MyMetfoneBusinessService");
        Date currDate = new Date();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsCode())) {
                logger.info("Error request : wsCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String service = request.getWsCode();
            String isdn = request.getWsRequest().get("isdn").toString();
            isdn = DataUtil.fomatIsdn(isdn);
            int otp = dao.randomOTP();
            int expired = Integer.parseInt(configUtils.getMessage("EXPRIED_OTP", "180").trim());
            int maxOTP = Integer.parseInt(environment.getProperty("MAX_OTP_IN_DAY", "10").trim());
            Otp otpObj = dao.getOtpByIsdnAndService(isdn, service, null);
            // qua ngay thi reset total otp
            if (!DataUtil.isNullObject(otpObj)
                    && DataUtil.addTime(otpObj.getCreateDate(), 1, null, null, null, 5).getTime() <= DataUtil.addTime(currDate, null, null, null, null, 4).getTime()) {
                otpObj.setStatus(null);
                otpObj.setOtp(null);
                otpObj.setTotalGetOtp(0L);
                otpObj.setCreateDate(new Date());
                otpObj.setUpdateDate(null);
                otpObj.setExpireTime(null);
                dao.update(otpObj);
            }
            if (!DataUtil.isNullObject(otpObj) && maxOTP <= otpObj.getTotalGetOtp()) {
                logger.info("Max send otp in day");
                return responseUtil.responseBean(Constants.ERR_MAX_SEND_OTP, DES_FAIL, "mymetfone.check.max.otp.", language, String.valueOf(maxOTP), null, null, null);
            }
            if (DataUtil.isNullObject(otpObj)) {
                otpObj = new Otp();
                otpObj.setIsdn(isdn);
                otpObj.setOtp(String.valueOf(otp));
                otpObj.setService(service);
                otpObj.setStatus(0L);
                otpObj.setTotalGetOtp(1L);
                otpObj.setCreateDate(currDate);
                otpObj.setExpireTime(DataUtil.addSecond(currDate, expired));
                dao.save(otpObj);
            } else {
                otpObj.setStatus(0L);
                otpObj.setTotalGetOtp(otpObj.getTotalGetOtp() + 1);
                otpObj.setCreateDate(currDate);
                otpObj.setExpireTime(DataUtil.addSecond(currDate, expired));
                otpObj.setOtp(String.valueOf(otp));
                dao.update(otpObj);
            }

            String content = responseUtil.getMessage("myMetfone.sms.content." + language, String.valueOf(otp), null, null, null);
            smsService.connectServer();
            int statusSend = smsService.sendSMS(isdn, content);
            if (statusSend == 0) {
                return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "wsGetOtpIshare.success.", language);
            }
//            if (dao.getCountOtp(isdn, true, wsCode)) {
//                result = dao.updateOtp(isdn, String.valueOf(otp), expired, true, wsCode);
//            } else {
//                result = dao.insertOtp(isdn, String.valueOf(otp), expired, wsCode);
//            }
//            if (result != 0) {
//                smsService.connectServer();
//                int statusSend = smsService.sendSMS(isdn, content);
//                if (statusSend == 0) {
//                    return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "wsGetOtpIshare.success.", language);
//                }
//            }
            return responseUtil.responseBean(Constants.ERROR_SEND_SMS, DES_FAIL, "wsGetOtpIshare.fail.", language);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getOTPIshare dung rieng cho nghiep vu ishare
     *
     * @param request
     * @param language
     * @return
     * @author daibq
     */
    @Override
    public BaseResponseBean getOTPIshare(RequestBean request, String language) {
        logger.info("Start business getOTP of MyMetfoneBusinessService");
        Date currDate = new Date();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsCode())) {
                logger.info("Error request : wsCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String service = request.getWsCode();
            String isdn = request.getWsRequest().get("isdn").toString();
            isdn = DataUtil.fomatIsdn(isdn);
            int maxOTP = Integer.parseInt(environment.getProperty("MAX_OTP_IN_DAY", "10").trim());
            Otp otpObj = dao.getOtpByIsdnAndService(isdn, service, null);
            // qua ngay thi reset total otp
            if (!DataUtil.isNullObject(otpObj)
                    && DataUtil.addTime(otpObj.getCreateDate(), 1, null, null, null, 5).getTime() <= DataUtil.addTime(currDate, null, null, null, null, 4).getTime()) {
                otpObj.setStatus(null);
                otpObj.setOtp(null);
                otpObj.setTotalGetOtp(0L);
                otpObj.setCreateDate(new Date());
                otpObj.setUpdateDate(null);
                otpObj.setExpireTime(null);
                dao.update(otpObj);
            }
            if (!DataUtil.isNullObject(otpObj) && maxOTP <= otpObj.getTotalGetOtp()) {
                logger.info("Max send otp in day");
                return responseUtil.responseBean(Constants.ERR_MAX_SEND_OTP, DES_FAIL, "mymetfone.check.max.otp.", language, String.valueOf(maxOTP), null, null, null);
            }
            SubMb subMb = cmpreDao.getSubMbByIsdn(isdn, null);
            if (subMb == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "SENDER_INVALID.", language);
            }
            if (!"00".equals(subMb.getActStatus())) {
                return responseUtil.responseBean(Constants.SUBMB_BLOCK, DES_FAIL, "SUBMB_BLOCK.", language);
            }
            Long days = Long.parseLong(configUtils.getMessage("days", "180"));
            if (System.currentTimeMillis() - subMb.getStaDatetime().getTime() < days * 86400l * 1000l) {
                return responseUtil.responseBean(Constants.LESS_DAYS, DES_FAIL, "LESS_DAYS.", language);
            }
            MoneyTransferItems numberTime = mkishareDao.checkNumberTime(subMb.getSubId());
            System.out.println("numberTime: " + numberTime.getNumberTime());
            Long daysBiggestSend = Long.parseLong(configUtils.getMessage("DAYS_BIGGEST_SEND"));
            if (numberTime.getNumberTime() >= daysBiggestSend) {
                return responseUtil.responseBean(Constants.NUMBER_TIME, DES_FAIL, "NUMBER_TIME.", language);
            }
            int otp = dao.randomOTP();
            int expired = Integer.parseInt(configUtils.getMessage("EXPRIED_OTP", "180").trim());
            if (DataUtil.isNullObject(otpObj)) {
                otpObj = new Otp();
                otpObj.setIsdn(isdn);
                otpObj.setOtp(String.valueOf(otp));
                otpObj.setService(service);
                otpObj.setStatus(0L);
                otpObj.setTotalGetOtp(1L);
                otpObj.setCreateDate(currDate);
                otpObj.setExpireTime(DataUtil.addSecond(currDate, expired));
                dao.save(otpObj);
            } else {
                otpObj.setStatus(0L);
                otpObj.setTotalGetOtp(otpObj.getTotalGetOtp() + 1);
                otpObj.setCreateDate(currDate);
                otpObj.setExpireTime(DataUtil.addSecond(currDate, expired));
                otpObj.setOtp(String.valueOf(otp));
                dao.update(otpObj);
            }

            String content = responseUtil.getMessage("myMetfone.sms.content." + language, String.valueOf(otp), null, null, null);
            smsService.connectServer();
            int statusSend = smsService.sendSMS(isdn, content);
            if (statusSend == 0) {
                return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "wsGetOtpIshare.success.", language);
            }
            return responseUtil.responseBean(Constants.ERROR_SEND_SMS, DES_FAIL, "wsGetOtpIshare.fail.", language);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language);
        }
    }

    /**
     * ishareTranfer
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean ishareTranfer(RequestBean request, String language) {
        logger.info("Start business ishareTranfer off MyMetfoneBusinessService");
        logger.info("Start time business ishareTranfer off MyMetfoneBusinessService: " + dateFormat.format(new Date()));
        String service = Constants.CHECK_OTP_ISHARE;
        String splitMsisdns = "";
        Otp otpObj = null;
        Date currDate = new Date();
        boolean updateOTP = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnSender")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnSender").toString())) {
                logger.info("Error requesst : isdnSender is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.sender.empty.", language);
            }
            prefix = "855";
            Double usdLeast = Double.parseDouble(configUtils.getMessage("USD_LEAST").trim());
            Double usdBiggest = Double.parseDouble(configUtils.getMessage("USD_BIGGEST").trim());
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnReceiver")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnReceiver").toString())) {
                logger.info("Error requesst : isdnReceiver is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.receiver.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("amount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("amount").toString())) {
                logger.info("Error requesst : amount is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.amount.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("otp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("otp").toString())) {
                logger.info("Error requesst : otp is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.empty.", language);
            }
//             wsCode = request.getWsCode();
            String isdnSender = DataUtil.fomatIsdn855(request.getWsRequest().get("isdnSender").toString());
            String isdnReceiver = DataUtil.fomatIsdn(request.getWsRequest().get("isdnReceiver").toString());
            String otp = request.getWsRequest().get("otp").toString();
            Double amount = null;
            try {
                amount = DataUtil.parseDouble(request.getWsRequest().get("amount").toString());
            } catch (Exception e) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.amount.empty.", language);
            }
            splitMsisdns = isdnSender.substring(3);
            //fix cho client de test. test xong bo case check equals  && !"652507".equals(otp)
            otpObj = dao.getOtpByIsdnAndService(splitMsisdns, service, otp);
            if (DataUtil.isNullObject(otpObj) || otpObj.getExpireTime().getTime() < currDate.getTime() || otpObj.getStatus() == 1) {
                logger.info("Error requesst : otp err ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
            }
//            if (!dao.checkOtp(splitMsisdns, otp, wsCode)) {
//                logger.info("Error requesst : otp err ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
//            }
            if (amount >= usdLeast && amount <= usdBiggest) {
                try {
                    //find the phone number
                    SubMb subMb = cmpreDao.getSubMbByIsdn(splitMsisdns, null);
                    if (subMb == null) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "SENDER_INVALID.", language);
//                        subMb = cmpreDao.getSubHp(splitMsisdns);
//                        if (subMb == null) {
//                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "SENDER_INVALID.", language);
//                        }
                    }
                    if (!"00".equals(subMb.getActStatus())) {
                        return responseUtil.responseBean(Constants.SUBMB_BLOCK, DES_FAIL, "SUBMB_BLOCK.", language);
                    }
                    Long days = Long.parseLong(configUtils.getMessage("days", "180"));
                    //check date start
                    if (System.currentTimeMillis() - subMb.getStaDatetime().getTime() >= days * 86400l * 1000l) {
                        try {
                            SubMb subMbReceiver = cmpreDao.getSubMb(isdnReceiver);
                            if (subMbReceiver == null) {
                                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "SUBMB_NOTFOUNDINFO.", language);
                            }
                            if ("03".equals(subMbReceiver.getActStatus())) {
                                return responseUtil.responseBean(Constants.SUBMB_BLOCK, DES_FAIL, "SUBMB_BLOCK.", language);
                            }
                            //check sender send over
                            MoneyTransferItems numberTime = mkishareDao.checkNumberTime(subMb.getSubId());
                            System.out.println("numberTime: " + numberTime.getNumberTime());
                            Long daysBiggestSend = Long.parseLong(configUtils.getMessage("DAYS_BIGGEST_SEND").trim());
                            if (numberTime.getNumberTime() >= daysBiggestSend) {
                                return responseUtil.responseBean(Constants.NUMBER_TIME, DES_FAIL, "NUMBER_TIME.", language);
                            }
                            //check receiver receive over
                            Long daysBiggestReceive = Long.parseLong(configUtils.getMessage("DAYS_BIGGEST_RECEIVE").trim());
                            MoneyTransferItems numberTimeReceiver = mkishareDao.checkNumberTimeReceiver(isdnReceiver);
                            if (numberTimeReceiver.getNumberTime() >= daysBiggestReceive) {
                                return responseUtil.responseBean(Constants.NUMBER_TIME, DES_FAIL, "NUMBER_TIME_RECEIVER.", language, isdnReceiver, null, null, null);
                            }
                            // check phone on OCS
                            InforSub inforSub = exchange.getInforOcs(isdnSender);
                            if (inforSub == null) {
                                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "SENDER_INVALID.", language);
                            } else {
                                //get number and money of phone number
                                String[] balanceNameLst = inforSub.getLstPrice();
                                String[] balanceValueLst = inforSub.getLstBalance();

                                String values = postionArrayList(balanceNameLst, balanceValueLst);
                                double moneySender = Double.parseDouble(values);
                                moneySender = moneySender / 1000000;
                                double fee = DataUtil.parseDouble(configUtils.getMessage("FEE_CHARGE").trim());
                                if (moneySender < amount + fee) {
                                    return responseUtil.responseBean(Constants.NOT_ENOUGH_MONEY, DES_FAIL, "NOT_ENOUGH_MONEY.", language);
                                }
                                int moneyDeduct = (int) ((amount + fee) * 1000000);
                                //tru tien sender
                                String callpro = configUtils.getMessage("CALL_PRO", "OFF").trim();
                                String resultDeduct = "0";
                                HashMap<String, ExchMsg> proResponse = null;
                                ExchMsg response = null;
                                ExchMsg rq = null;
                                if ("ON".equals(callpro)) {
                                    proResponse = exchange.addMoney(isdnSender, -moneyDeduct, "2000","ISA0010_CAMID");
                                    if (proResponse != null) {
                                        response = proResponse.get("RESPONSE");
                                        rq = proResponse.get("REQUEST");
                                        if (response != null) {
                                            String responseXML = response.toString();
                                            resultDeduct = response.getError();
                                            if (responseXML != null && responseXML.trim().length() >= 4000) {
                                                responseXML = responseXML.substring(0, 4000);
                                            }
                                            cmpreDao.insertProLog(splitMsisdns, subMb.getSerial(), rq.toString(), responseXML, response.getError());
                                        } else {
                                            return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                        }
                                    } else {
                                        return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                    }
                                }
                                if (!"0".equals(resultDeduct)) {//Check result when call provisioning
                                    //Case of call provisioning error
                                    return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                } else {
                                    int moneySenderAfter = (int) (moneySender * 1000000) - moneyDeduct;
                                    int moneyReceiverAfter = (int) (amount * 1000000);
                                    String newPhone = new StringBuilder().insert(0, "855").append(isdnReceiver).toString();
                                    //cong tien isdnReceiver
                                    String resultReceiver = "0";
                                    if ("ON".equals(callpro)) {
                                        proResponse = exchange.addMoney(newPhone, moneyReceiverAfter, "2000","ISA0010_CAMID");
                                        if (proResponse != null) {
                                            response = proResponse.get("RESPONSE");
                                            rq = proResponse.get("REQUEST");
                                            if (response != null) {
                                                String responseXML = response.toString();
                                                resultReceiver = response.getError();
                                                if (responseXML != null && responseXML.trim().length() >= 4000) {
                                                    responseXML = responseXML.substring(0, 4000);
                                                }
                                                cmpreDao.insertProLog(isdnReceiver, subMbReceiver.getSerial(), rq.toString(), responseXML, response.getError());
                                            } else {
                                                return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                            }
                                        } else {
                                            return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                        }
                                    }
                                    if (!"0".equals(resultReceiver)) {
                                        //nêu cong tien cho isdnReceiver that bai thi trả lại tiên cho isdnSender
                                        if ("ON".equals(callpro)) {
                                            proResponse = exchange.addMoney(isdnSender, moneyDeduct, "2000","ISA0010_CAMID");
                                            if (proResponse != null) {
                                                response = proResponse.get("RESPONSE");
                                                rq = proResponse.get("REQUEST");
                                                if (response != null) {
                                                    String responseXML = response.toString();
                                                    resultDeduct = response.getError();
                                                    if (responseXML != null && responseXML.trim().length() >= 4000) {
                                                        responseXML = responseXML.substring(0, 4000);
                                                    }
                                                    cmpreDao.insertProLog(splitMsisdns, subMb.getSerial(), rq.toString(), responseXML, response.getError());
                                                } else {
                                                    return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                                }
                                            } else {
                                                return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                            }
                                        }
                                        if (!"0".equals(resultDeduct)) {
                                            return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);
                                        }
                                        return responseUtil.responseBean(Constants.SYSTEM_BUSY, DES_FAIL, "SYSTEM_BUSY.", language);

                                    } else {
                                        try {
                                            moneySender = moneySender * 1000000;
                                            int usdTransfer = (int) (amount * 1000000);
                                            Long feeLog = (long) (fee * 100);
                                            //insert money_transfer_log
                                            int resultInsert = InsertTransfer(subMb.getSubId(), splitMsisdns, isdnReceiver, (int) moneySender, usdTransfer, moneySenderAfter, feeLog * 10000);
                                            if (resultInsert != 0) {

//                                                double dou = (double) usd;
//                                                dou = dou / 100;
                                                double moneyTransfer = (double) moneySenderAfter;
                                                moneyTransfer = moneyTransfer / 1000000;
                                                String isdn = splitMsisdns;
                                                //format date
                                                Date date = new Date();
                                                date.setTime(inforSub.getActiveStop().getTime());
                                                String cast = new SimpleDateFormat("dd/MM/yyyy").format(date);
                                                String contentSmsSender = responseUtil.getMessage("SUCCESS." + language, amount.toString(), isdnReceiver, null, null);
                                                //insert MT sender
                                                int resultMT = InsertMt(isdnSender, contentSmsSender);
                                                InforSub inforSubReseiver = exchange.getInforOcs(isdnReceiver);
                                                String[] balanceNameLstReseiver = inforSubReseiver.getLstPrice();
                                                String[] balanceValueLstReseiver = inforSubReseiver.getLstBalance();
                                                String valuesReseiver = postionArrayList(balanceNameLstReseiver, balanceValueLstReseiver);
                                                double moneyReseiver = Double.parseDouble(valuesReseiver);
                                                moneyReseiver = moneyReseiver / 1000000;

                                                //format money
                                                DecimalFormat format = new DecimalFormat("#.##");
                                                String moneyReseiverTransfer = format.format(moneyReseiver);

//                                                    String isdnReceiver = phoneNum;
                                                date.setTime(inforSubReseiver.getActiveStop().getTime());
                                                String castReseiver = new SimpleDateFormat("dd/MM/yyyy").format(date);

//                                                    String contentSmsReceiver = "The subscriber " + splitMsisdns + " transferred " + dou + "$ for you. Your banlance is " + moneyReseiver + "$ now and the expire date is " + castReseiver + ". Thank you to use Ishare service.";
//                                                    insert DB MT
                                                String contentSmsReceiverFirst = responseUtil.getMessage("SECCESS_RECEIVER_FIRST." + language, splitMsisdns, amount.toString(), null, null);
                                                String contentSmsReceiverSecond = responseUtil.getMessage("SECCESS_RECEIVER_SECOND." + language, splitMsisdns, amount.toString(), moneyReseiverTransfer, castReseiver);
                                                int resultMTReseiverFirst = InsertMt(newPhone, contentSmsReceiverFirst);
                                                int resultMTReseiverSecond = InsertMt(newPhone, contentSmsReceiverSecond);

                                                //write file to save log transfer money
                                                FileUtil fileUtil = new FileUtil();
                                                String folderLocal = configUtils.getMessage("FILE_PATH").trim();
                                                String content = configUtils.getMessage("FILE_CONTENT").trim();
                                                Random ran = new Random();
                                                content = content.replace("{4}", newPhone.startsWith("855") ? newPhone.substring(3) : newPhone).
                                                        replace("{9}", isdnSender.substring(3)).
                                                        replace("{11}", moneyReceiverAfter + "").
                                                        replace("{10}", System.currentTimeMillis() + ran.nextInt(1000000) + "").
                                                        replace("{44}", "").
                                                        replace("{34}", DateUtils.systimeString()).
                                                        replace("{0}", "0").
                                                        replace("{60}", "").
                                                        replace("{9}", "");
                                                String fileNameTmp = configUtils.getMessage("FILE_NAME_TMP").trim();
                                                String fileName = fileUtil.getFileName(mkishareDao, fileNameTmp);
                                                FileUtil.writeFile(folderLocal + "/" + fileName, content, logger);
                                                int resultInsLog = cmpreDao.insertLogTransferMoney(logger, isdnSender.substring(3), newPhone.startsWith("855") ? newPhone.substring(3) : newPhone,
                                                        (double) moneyReceiverAfter / 1000000.00d, feeLog * 10000, System.currentTimeMillis() + ran.nextInt(100000), fileName);
                                                logger.info("Result insert insertLogTransferMoney " + resultInsLog);
                                                updateOTP = true;
                                                return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "POPUP_USSD.", language, String.valueOf(amount), isdnReceiver, String.valueOf(moneyTransfer), cast);
                                            }
                                        } catch (Exception ex) {
                                            logger.info("Error: " + ex.getMessage(), ex);
                                        }

                                    }
                                }
                            }
                        } catch (Exception ex) {
                            logger.info("Error " + ex.getMessage());
                        }
                    } else {
                        return responseUtil.responseBean(Constants.LESS_DAYS, DES_FAIL, "LESS_DAYS.", language);
                    }
                } catch (Exception ex) {
                    logger.info("error" + ex.getMessage(), ex);
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsTransferIshare.fail.", language);
                }
            } else if (amount < usdLeast) {
                return responseUtil.responseBean(Constants.NOT_SUCCESS, DES_FAIL, "NOT_SUCCESS.", language);
            } else if (amount > usdBiggest) {
                return responseUtil.responseBean(Constants.NOT_SUCCESS, DES_FAIL, "TOO_MUCH_MONEY.", language);
            }

            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsTransferIshare.fail.", language);
        } catch (Exception ex) {
            logger.error("error process " + ex.getMessage());
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsTransferIshare.fail.", language);
        } finally {
            try {
                if (!DataUtil.isNullObject(otpObj) && updateOTP) {
                    otpObj.setTotalGetOtp(0L);
                    otpObj.setStatus(1L);
                    otpObj.setUpdateDate(currDate);
                    dao.update(otpObj);
                }
            } catch (Exception ex) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            }
        }
    }
//    /**
//     * ishareTranfer
//     *
//     * @param request
//     * @param language
//     * @return
//     */
//    @Override
//    public BaseResponseBean ishareTranfer(RequestBean request, String language) {
//        logger.info("Start business ishareTranfer off MyMetfoneBusinessService");
//        logger.info("Start time business ishareTranfer off MyMetfoneBusinessService: " + dateFormat.format(new Date()));
//        String wsCode = Constants.CHECK_OTP_ISHARE;
//        String splitMsisdns = "";
//        try {
//            if (DataUtil.isNullObject(request.getWsRequest().get("isdnSender")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnSender").toString())) {
//                logger.info("Error requesst : isdnSender is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.sender.empty.", language);
//            }
//            if (DataUtil.isNullObject(request.getWsRequest().get("isdnReceiver")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnReceiver").toString())) {
//                logger.info("Error requesst : isdnReceiver is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.receiver.empty.", language);
//            }
//            if (DataUtil.isNullObject(request.getWsRequest().get("amount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("amount").toString())) {
//                logger.info("Error requesst : amount is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.amount.empty.", language);
//            }
//            if (DataUtil.isNullObject(request.getWsRequest().get("otp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("otp").toString())) {
//                logger.info("Error requesst : otp is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.empty.", language);
//            }
////             wsCode = request.getWsCode();
//            String isdnSender = DataUtil.fomatIsdn855(request.getWsRequest().get("isdnSender").toString());
//            String isdnReceiver = DataUtil.fomatIsdn(request.getWsRequest().get("isdnReceiver").toString());
//            String otp = DataUtil.fomatIsdn(request.getWsRequest().get("otp").toString());
//            Double amount = null;
//            try {
//                amount = DataUtil.parseDouble(request.getWsRequest().get("amount").toString());
//            } catch (Exception e) {
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.amount.empty.", language);
//            }
//            splitMsisdns = isdnSender.substring(3);
//            //fix cho client de test. test xong bo case check equals  && !"652507".equals(otp)
//            if (!dao.checkOtp(splitMsisdns, otp, wsCode) && !"123456".equals(otp)) {
//                logger.info("Error requesst : otp err ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
//            }
//            Webservice ws = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_ISHARE);
//            if (ws == null) {
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
//            }
//
//            logger.info("Start time call service wsIshare: " + dateFormat.format(new Date()));
//            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
//            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
//            params.put("isdnSender", isdnSender);
//            params.put("isdnReceiver", isdnReceiver);
//            params.put("language", language);
//            params.put("otp", otp);
//            params.put("usd", (int) (amount * 100));
//            SoapWSResponse response = wsUtil.callWebService(params, true);
//
//            if (response == null) {
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
//                //	return;
//            } else {
//                return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/wsIshareResponse/return/decription"), response.getTextContent("/Envelope/Body/wsIshareResponse/return/content"));
//            }
//        } catch (Exception ex) {
//            logger.error("error process " + ex.getMessage());
//            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsTransferIshare.fail.", language);
//        } finally {
//            try {
//                if (!DataUtil.isNullOrEmpty(splitMsisdns) && !DataUtil.isNullOrEmpty(wsCode)) {
//                    dao.updateOtp(splitMsisdns, null, null, false, wsCode);
//                }
//            } catch (Exception ex) {
//                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
//            }
//        }
//
//    }

    /**
     * changeIsdnKeepSim
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean changeIsdnKeepSim(RequestBean request, String language) {
        logger.info("Start business changeIsdnKeepSim off MyMetfoneBusinessService");
        logger.info("Start time business changeIsdnKeepSim off MyMetfoneBusinessService: " + dateFormat.format(new Date()));
        String service = Constants.CHECK_OTP_CHANGE_ISDN_KEEP_SIM;
        String oldIsdn = "";
        Otp otpObj = null;
        Date currDate = new Date();
        boolean updateOTP = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("oldIsdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("oldIsdn").toString())) {
                logger.info("Error requesst : oldIsdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice wsKeepSim = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_CHANGE_KEEP_SIM);
            if (wsKeepSim == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("orderIsdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("orderIsdn").toString())) {
                logger.info("Error requesst : newIsdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("priceIsdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("priceIsdn").toString())) {
                logger.info("Error requesst : price is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("otpCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("otpCode").toString())) {
                logger.info("Error requesst : otp is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("orderType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("orderType").toString())) {
                logger.info("Error requesst : orderType is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("registerFee")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("registerFee").toString())) {
                logger.info("Error requesst : registerFee is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

//            wsCode = request.getWsCode();
            String otp = request.getWsRequest().get("otpCode").toString();
            String newIsdn = request.getWsRequest().get("orderIsdn").toString();
            oldIsdn = request.getWsRequest().get("oldIsdn").toString();
            String price = request.getWsRequest().get("priceIsdn").toString();
            String orderType = request.getWsRequest().get("orderType").toString();
            String registerFee = request.getWsRequest().get("registerFee").toString();

            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KM").trim();
            }

            otpObj = dao.getOtpByIsdnAndService(oldIsdn, service, otp);
            if (DataUtil.isNullObject(otpObj) || otpObj.getExpireTime().getTime() < currDate.getTime() || otpObj.getStatus() == 1) {
                logger.info("Error requesst : otp err ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
            }
            //fix cho client de test. test xong bo case check equals  && !"652507".equals(otp)
//            if (!dao.checkOtp(oldIsdn, otp, wsCode)) {
//                logger.info("Error requesst : otp err ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
//            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service lockIsdnByCus: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(wsKeepSim, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("newIsdn", newIsdn);
            params.put("oldIsdn", oldIsdn);
            params.put("price", Double.parseDouble(price));
            params.put("locale", locale);
            params.put("pinCode", otp);
            params.put("orderType", orderType);
            params.put("registerFee", Double.parseDouble(registerFee));
            SoapWSResponse response = wsUtil.callWebService(params, true);

            if (response == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                //	return;
            } else {
                if ("0".equals(response.getErrCode())) {
                    updateOTP = true;
                }
                return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/changeIsdnKeepSimResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/changeIsdnKeepSimResponse/return/errorDecription"));
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        } finally {
            try {
                if (!DataUtil.isNullObject(otpObj) && updateOTP) {
                    otpObj.setTotalGetOtp(0L);
                    otpObj.setStatus(1L);
                    otpObj.setUpdateDate(currDate);
                    dao.update(otpObj);
                }
            } catch (Exception ex) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            }
        }
    }

    /**
     * getListNumberToBuy
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getListNumberToBuy(RequestBean request, String language) {
        logger.info("Start business getListNumberToBuy off MyMetfoneBusinessService");
        try {
            Webservice ws = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_SEARCH_BY_SIM);
            if (ws == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String prefix = "";
            if (!DataUtil.isNullObject(request.getWsRequest().get("prefix")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("prefix").toString())) {
                prefix = request.getWsRequest().get("prefix").toString();
            }
            String fromPrice = "";
            if (!DataUtil.isNullObject(request.getWsRequest().get("fromPrice")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("fromPrice").toString())) {
                fromPrice = request.getWsRequest().get("fromPrice").toString();
            }
            String toPrice = "";
            if (!DataUtil.isNullObject(request.getWsRequest().get("toPrice")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("toPrice").toString())) {
                toPrice = request.getWsRequest().get("toPrice").toString();
            }
            String option = "";
            if (!DataUtil.isNullObject(request.getWsRequest().get("option")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("option").toString())) {
                option = request.getWsRequest().get("option").toString();
            }
            String pageNo = "";
            if (!DataUtil.isNullObject(request.getWsRequest().get("pageNo")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNo").toString())) {
                pageNo = request.getWsRequest().get("pageNo").toString();
            }
            String typeNumber = "";
            if (!DataUtil.isNullObject(request.getWsRequest().get("typeNumber")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("typeNumber").toString())) {
                typeNumber = request.getWsRequest().get("typeNumber").toString();
            }
            String pageSize = "";
            if (!DataUtil.isNullObject(request.getWsRequest().get("pageSize")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("pageSize").toString())) {
                pageSize = request.getWsRequest().get("pageSize").toString();
            }

//            String requesterNumber = "";
//            if (!DataUtil.isNullObject(request.getWsRequest().get("requesterNumber")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("requesterNumber").toString())) {
//                requesterNumber = request.getWsRequest().get("requesterNumber").toString();
//            }
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KM").trim();
            }
            //bodung param
            String isdn = DataUtil.isNullOrEmpty(request.getUsername()) ? "" : request.getUsername();
//            String requesterNumber = "";
//            if (!DataUtil.isNullObject(request.getWsRequest().get("requesterNumber")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("requesterNumber").toString())) {
//                requesterNumber = request.getWsRequest().get("requesterNumber").toString();
//            }
            //params = new LinkedHashMap<>();
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("fromPrice", fromPrice);
            params.put("toPrice", toPrice);
            params.put("status", "");
            params.put("pageNo", pageNo);
            params.put("pageSize", pageSize);
            params.put("option", option);
            params.put("prefix", prefix);
            params.put("conditionNumber", typeNumber);
            params.put("locale", locale);
            params.put("requesterNumber", isdn);
            SoapWSResponse response = wsUtil.callWebService(params, true);
            if (response == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                //	return;
            } else {
                if (Constants.ERROR_CODE_0.equals(response.getErrCode())) {
                    MetfoneBean metfoneBean = null;
                    Object dataResponse = null;
                    String xmlResponse = response.getResponse();

                    if (!StringUtils.isEmpty(xmlResponse)) {
                        logger.info(" xmlString:==================> " + xmlResponse);
                        Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                        String result = "";
                        for (Element e : doc.select(RETURN_TAG)) {
                            result = result + e.toString();
                        }
                        //   resultProductOffer = resultProductOffer + "</result>";
                        JSONObject data = XML.toJSONObject(result);
//.get(RESULT_TAG)
                        dataResponse = data.getJSONObject(RETURN_TAG).get(RESULT_TAG);
                        logger.info("dataObject" + dataResponse);
                        String totalPage = response.getTextContent("/Envelope/Body/getListNumberToBuyResponse/return/totalPage");
                        String totalSize = response.getTextContent("/Envelope/Body/getListNumberToBuyResponse/return/totalSize");
                        if (dataResponse instanceof JSONArray) {

                            metfoneBean = new MetfoneBean();
                            JSONArray objIsdn = (JSONArray) dataResponse;
                            List<Object> list = new ArrayList<>();
                            for (Object obj : objIsdn) {
                                JSONObject product = (JSONObject) obj;
                                IsdnInfoBean isdnInfo = new IsdnInfoBean();
                                isdnInfo.setIsdn(product.optString(ISDN));
                                isdnInfo.setPrice(product.optString(PRICE));
                                isdnInfo.setStatus(product.optString(STATUS));
                                isdnInfo.setType(product.optLong(TYPE));
                                isdnInfo.setTypeIsdn(product.optLong(TYPE_ISDN));
                                isdnInfo.setMonthlyFee(product.optDouble(monthlyFee));
                                isdnInfo.setRegisterFee(product.optDouble(registerFee));
                                isdnInfo.setProduct(product.optString("product"));
                                list.add(isdnInfo);
                            }
                            metfoneBean.setTotalPage(totalPage);
                            metfoneBean.setTotalSize(totalSize);
                            metfoneBean.setFee(configUtils.getMessage("FEE_BUY_NUMBER", "5.0").trim());
                            metfoneBean.setCommitDuration(configUtils.getMessage("commitDuration", "18.0").trim());
                            metfoneBean.setLstNumberToBuy(list);
                            logger.info("getListNumberToBuy Array:" + metfoneBean.getLstNumberToBuy().size());
                        } else {
                            metfoneBean = new MetfoneBean();
                            JSONObject product = (JSONObject) dataResponse;
                            IsdnInfoBean isdnInfo = new IsdnInfoBean();
                            List<Object> list = new ArrayList<>();
                            isdnInfo.setIsdn(product.optString(ISDN));
                            isdnInfo.setPrice(product.optString(PRICE));
                            isdnInfo.setStatus(product.optString(STATUS));
                            isdnInfo.setType(product.optLong(TYPE));
                            isdnInfo.setTypeIsdn(product.optLong(TYPE_ISDN));
                            isdnInfo.setMonthlyFee(product.optDouble(monthlyFee));
                            isdnInfo.setRegisterFee(product.optDouble(registerFee));
                            isdnInfo.setProduct(product.optString("product"));
                            list.add(isdnInfo);
                            metfoneBean.setLstNumberToBuy(list);
                            metfoneBean.setTotalPage(totalPage);
                            metfoneBean.setTotalSize(totalSize);
                            metfoneBean.setFee(configUtils.getMessage("FEE_BUY_NUMBER", "5.0").trim());
                            metfoneBean.setCommitDuration(configUtils.getMessage("commitDuration", "18.0").trim());
                            logger.info("getListNumberToBuy Array:" + metfoneBean.getLstNumberToBuy().size());
                        }

                        BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                        bean.setWsResponse(metfoneBean);
                        return bean;
                    } else {
                        return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/getListNumberToBuyResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getListNumberToBuyResponse/return/errorDecription"));
                    }
                } else {
                    return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/getListNumberToBuyResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getListNumberToBuyResponse/return/errorDecription"));
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getListOrderNumberHistory
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getListOrderNumberHistory(RequestBean request, String language) {
        logger.info("Start business getListOrderNumberHistory off MyMetfoneBusinessService");
        try {
            Webservice ws = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_LOCK_ISDN_HIS);
            if (ws == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KM").trim();
            }
            //params = new LinkedHashMap<>();
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("cusIsdn", isdn);
            params.put("locale", locale);
            SoapWSResponse response = wsUtil.callWebService(params, true);
            if (response == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                //	return;
            } else {
                if (Constants.ERROR_CODE_0.equals(response.getErrCode())) {
                    MetfoneBean metfoneBean = null;
                    Object dataResponse = null;
                    String xmlResponse = response.getResponse();

                    if (!StringUtils.isEmpty(xmlResponse)) {
                        logger.info(" xmlString:==================> " + xmlResponse);
                        Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                        String result = "";
                        for (Element e : doc.select(RETURN_TAG)) {
                            result = result + e.toString();
                        }
                        //   resultProductOffer = resultProductOffer + "</result>";
                        JSONObject data = XML.toJSONObject(result);
//.get(RESULT_TAG)
                        dataResponse = data.getJSONObject(RETURN_TAG).get("ordernumberhistory");
                        logger.info("dataObject" + dataResponse);
                        if (dataResponse instanceof JSONArray) {

                            metfoneBean = new MetfoneBean();
                            JSONArray objIsdn = (JSONArray) dataResponse;
                            List<Object> list = new ArrayList<>();
                            for (Object obj : objIsdn) {
                                JSONObject product = (JSONObject) obj;
                                IsdnInfoBean isdnInfo = new IsdnInfoBean();
                                isdnInfo.setIsdn(product.optString(ISDN));
                                isdnInfo.setExpiredDate(product.optString("expireddate"));
                                isdnInfo.setOrderedDate(product.optString("ordereddate"));
                                isdnInfo.setMonthlyFee(product.optDouble(monthlyFee));
                                isdnInfo.setRegisterFee(product.optDouble(registerFee));
                                isdnInfo.setProduct(product.optString("product"));
                                isdnInfo.setPrice(String.valueOf(product.optDouble("price")));
                                list.add(isdnInfo);
                            }
                            metfoneBean.setCommitDuration(configUtils.getMessage("commitDuration", "18.0").trim());
                            metfoneBean.setLstOrderedNumber(list);
                            logger.info("getListNumberToBuy Array:" + metfoneBean.getLstOrderedNumber().size());

                        } else {
                            metfoneBean = new MetfoneBean();
                            JSONObject product = (JSONObject) dataResponse;
                            IsdnInfoBean isdnInfo = new IsdnInfoBean();
                            List<Object> list = new ArrayList<>();
                            isdnInfo.setIsdn(product.optString(ISDN));
                            isdnInfo.setExpiredDate(product.optString("expireddate"));
                            isdnInfo.setOrderedDate(product.optString("ordereddate"));
                            isdnInfo.setMonthlyFee(product.optDouble(monthlyFee));
                            isdnInfo.setRegisterFee(product.optDouble(registerFee));
                            isdnInfo.setProduct(product.optString("product"));
                            isdnInfo.setPrice(String.valueOf(product.optDouble("price")));
                            list.add(isdnInfo);
                            metfoneBean.setCommitDuration(configUtils.getMessage("commitDuration", "18.0").trim());
                            metfoneBean.setLstOrderedNumber(list);
                            logger.info("getListNumberToBuy Array:" + metfoneBean.getLstOrderedNumber().size());
                        }
                        BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                        bean.setWsResponse(metfoneBean);
                        return bean;
                    } else {
                        return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/lockIsdnByCusHistoryResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/lockIsdnByCusHistoryResponse/return/errorDecription"));
                    }
                } else {
                    return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/lockIsdnByCusHistoryResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/lockIsdnByCusHistoryResponse/return/errorDecription"));
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * lockIsdnToBuy
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean lockIsdnToBuy(RequestBean request, String language) {
        logger.info("Start business lockIsdnToBuy off MyMetfoneBusinessService");
        try {
            Webservice ws = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_LOCK_ISDN);
            if (ws == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnFrm")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnFrm").toString())) {
                logger.info("Error requesst : isdnFrm is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("customerIsdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("customerIsdn").toString())) {
                logger.info("Error requesst : customerIsdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String isdnFrm = request.getWsRequest().get("isdnFrm").toString();
            String customerIsdn = request.getWsRequest().get("customerIsdn").toString();

            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KM").trim();
            }
            //params = new LinkedHashMap<>();
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("isdnFrm", isdnFrm);
            params.put("customerISDN", customerIsdn);
            params.put("locale", locale);
            SoapWSResponse response = wsUtil.callWebService(params, true);
            if (response == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                //	return;
            } else if (Constants.ERROR_CODE_0.equals(response.getErrCode())) {
                return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/lockIsdnByCusResponse/return/pincode"), "myMetfone.lock.isdn.", language, dateFormat.format(addTime(new Date(), 24)), null, null, null, null);
            } else {
                return responseUtil.responseBean(response.getErrCode(), response.getTextContent("/Envelope/Body/lockIsdnByCusResponse/return/messageCode"), response.getTextContent("/Envelope/Body/lockIsdnByCusResponse/return/messageCode"));
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkPackageByISDN(RequestBean request, String language) {
        logger.info("Start business checkPackageByISDN off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("productCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : productCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.productCode.failed.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String productCode = request.getWsRequest().get("productCode").toString();
            isdn = DataUtil.fomatIsdn(isdn);
            BaseResponseBean bean = responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, DES_FAIL, language);
            MetfoneBean metfoneBean = new MetfoneBean();
            if (!DataUtil.isNullOrEmpty(cmpreDao.checkPackage(logger, isdn, productCode))) {
                bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
                metfoneBean.setRegister(1L);
                bean.setWsResponse(metfoneBean);
                return bean;
            }
            metfoneBean.setRegister(0L);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean chargeHisInMonth(RequestBean request, String language) {
        logger.info("Start chargeHisInMonth API off MyMetfoneController");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("startDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("startDate").toString())) {
                logger.info("Error requesst : startDate is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.startDate.failed.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("endDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("endDate").toString())) {
                logger.info("Error requesst : endDate is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.endDate.failed.", language);
            }
            String isdn = DataUtil.fomatIsdn855(request.getWsRequest().get("isdn").toString());
//            String startDate = request.getWsRequest().get("startDate").toString();
//            String endDate = request.getWsRequest().get("endDate").toString();

            Date startDate = DateTimeUtils.convertStringToTime(request.getWsRequest().get("startDate").toString(), "dd/MM/yyyy HH:mm:ss");
            Date endDate = DateTimeUtils.convertStringToTime(request.getWsRequest().get("endDate").toString(), "dd/MM/yyyy HH:mm:ss");
            if (DataUtil.isNullObject(startDate)) {
                logger.info("Error requesst : startDate format error ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.startDate.failed.", language);
            }

            if (DataUtil.isNullObject(endDate)) {
                logger.info("Error requesst : endDate format error ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.endDate.failed.", language);
            }
            startDate = AppUtil.getTimeStartOfDay(startDate.getTime());
            endDate = AppUtil.getTimeEndOfDay(endDate.getTime());

            if (startDate.getTime() > endDate.getTime()) {
                logger.info("Error requesst :  Max startDate > endDate ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.startDate.endDate.failed1.", language);
            }
            long getDiff = endDate.getTime() - startDate.getTime();
            long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
            if (getDaysDiff > DataUtil.maxDay()) {
                logger.info("Error requesst : Max startDate to endDate ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.startDate.endDate.failed.", language);
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            List<Object> list = cmpreDao.chargeHisInMonthByIsdn(logger, isdn, startDate, endDate);
            BaseResponseBean bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            metfoneBean.setResultList(list);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * confirmOTP
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean confirmOTP(RequestBean request, String language) {
        logger.info("Start business confirmOTP off MyMetfoneBusinessService");
        Date currDate = new Date();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("otp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("otp").toString())) {
                logger.info("Error requesst : otp is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("service")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("service").toString())) {
                logger.info("Error requesst : service is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String otp = request.getWsRequest().get("otp").toString();
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            String service = request.getWsRequest().get("service").toString();
            Otp otpObj = dao.getOtpByIsdnAndService(isdn, service, otp);
            if (DataUtil.isNullObject(otpObj) || otpObj.getExpireTime().getTime() < currDate.getTime() || otpObj.getStatus() == 1) {
                logger.info("Error requesst : otp err ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
            }
            //fix cho client de test. test xong bo case check equals  && "652507".equals(otp)
//            if (dao.checkOtp(isdn, otp, service)) {
//                return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
//            }
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
        return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
//        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
    }

    /**
     * syncIsdnInfo
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean syncIsdnInfo(RequestBean request, String language) {
        logger.info("Start business syncIsdnInfo off MyMetfoneBusinessService");
        String syncIsdn = "";
        String service = "";
        try {
//            if (DataUtil.isNullObject(request.getWsRequest().get("otp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("otp").toString())) {
//                logger.info("Error requesst : otp is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.empty.", language);
//            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("syncIsdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("syncIsdn").toString())) {
                logger.info("Error requesst : syncIsdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.sync.isdn.empty.", language);
            }
//            if (DataUtil.isNullObject(request.getWsCode())) {
//                logger.info("Error requesst : WsCode is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
//            }
//            String otp = request.getWsRequest().get("otp").toString();
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            syncIsdn = request.getWsRequest().get("syncIsdn").toString();
//             service = request.getWsCode();
//            //fix cho client de test. test xong bo case check equals  && "652507".equals(otp)
//            if (!dao.checkOtp(syncIsdn, otp, service)) {
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
//            }
            SubMb subMBByIsdn = cmpreDao.getSubMbByIsdn(isdn);
            if (DataUtil.isNullObject(subMBByIsdn) || DataUtil.isNullObject(subMBByIsdn.getCustId())) {
                logger.info("Error  : subMBByIsdn  is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }

            String idNoByIsdn = cmpreDao.getIdNoByCustId(subMBByIsdn.getCustId());
            if (DataUtil.isNullOrEmpty(idNoByIsdn)) {
                logger.info("Error  : idNoByIsdn  is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            String[] syncIsdnStr = syncIsdn.split(",");
            String subSyncErr = "";
            String subSynced = "";
            String subSyncSucc = "";
            for (String str : syncIsdnStr) {
                str = DataUtil.fomatIsdn(str);
                SubMb subMBBySyncIsdn = cmpreDao.getSubMbByIsdn(str);
                if (DataUtil.isNullObject(subMBBySyncIsdn) || DataUtil.isNullObject(subMBBySyncIsdn.getCustId())) {
                    logger.info("Error  : subMBBySyncIsdn is null ");
                    subSyncErr += str + ",";
//                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.sync.isdn.empty.", language);
                    continue;
                }
                if (DataUtil.safeEqual(subMBBySyncIsdn.getCustId(), subMBByIsdn.getCustId())) {
                    logger.info("Error  : thue bao đã được đong bộ  từ trước ");
                    subSynced += str + ",";
                    continue;
//                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.sync.subscribers.", language);
                }
                String idNoBySyncIsdn = cmpreDao.getIdNoByCustId(subMBBySyncIsdn.getCustId());
                if (DataUtil.isNullOrEmpty(idNoBySyncIsdn)) {
                    logger.info("Error  : idNoBySyncIsdn is null ");
                    subSyncErr += str + ",";
                    continue;
//                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.sync.isdn.empty.", language);
                }

                if (!idNoBySyncIsdn.equals(idNoByIsdn)) {
                    logger.info("Error  : khong cung idNo ");
                    subSyncErr += str + ",";
                    continue;
                }
                boolean check = cmpreDao.updateSubMbByIsdn(syncIsdn, subMBByIsdn.getCustId());
                if (!check) {
                    logger.info("Error  : update file ");
                    subSyncErr += str + ",";
                    continue;
                }
                subSyncSucc += str + ",";
//else {
//
//                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.Ishare.socialNetwork.failed.", language);
//                }

//                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.sync.subscribers.error.", language);
            }
            String msg = "";
            String erroCode = "";
            String dess = "";
            if (!DataUtil.isNullOrEmpty(subSyncErr)) {
                erroCode = "1";
                dess = "Failed";
                msg += responseUtil.getMessage("myMetfone.sync.isdn.empty." + language, subSyncErr.substring(0, subSyncErr.length() - 1), null, null, null) + "\n";
            }
            if (!DataUtil.isNullOrEmpty(subSynced)) {
                dess = "Failed";
                erroCode = "1";
                msg += responseUtil.getMessage("myMetfone.sync.subscribers." + language, subSynced.substring(0, subSynced.length() - 1), null, null, null) + "\n";
            }
            if (!DataUtil.isNullOrEmpty(subSyncSucc)) {
                erroCode = "0";
                dess = "Success";
                msg += responseUtil.getMessage("myMetfone.sync.isdn." + language, subSyncSucc.substring(0, subSyncSucc.length() - 1), null, null, null) + "\n";
            }
            System.out.println(msg);
            return new BaseResponseBean(erroCode, dess, msg);

        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }//finally {
//            try {
//                if (!DataUtil.isNullOrEmpty(syncIsdn) && !DataUtil.isNullOrEmpty(service)) {
//                    dao.updateOtp(syncIsdn, null, null, false, service);
//                }
//            } catch (Exception ex) {
//                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
//            }
//        }

    }

    /**
     * wsForgotPassword
     *
     * @param request
     * @param language
     * @return
     * @author daibq
     */
    @Override
    public BaseResponseBean wsForgotPassword(RequestBean request, String language) {
        logger.info("Start business wsForgotPassword off AccountServiceImpl");
        String isdn = "";
        String service = "";
        Otp otpObj = null;
        Date currDate = new Date();
        boolean updateOTP = false;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.username.failed.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("newPass")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("newPass").toString())) {
                logger.info("Error requesst : newPass is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.newPass.failed.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("reNewPass")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("reNewPass").toString())) {
                logger.info("Error requesst : reNewPass is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.reNewPass.failed.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("otp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("otp").toString())) {
                logger.info("Error requesst : otp is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.otp.err.", language);
            }
            isdn = request.getWsRequest().get("isdn").toString();
            String newPass = request.getWsRequest().get("newPass").toString();
            newPass = AES.decrypt(newPass);
            if (newPass.length() != 6) {
                logger.info("Error requesst : newPass error length ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.newPass.length.failed.", language);
            }
            String reNewPass = request.getWsRequest().get("reNewPass").toString();
            reNewPass = AES.decrypt(reNewPass);
            String otp = request.getWsRequest().get("otp").toString();
            isdn = DataUtil.fomatIsdn(isdn);
            if (!newPass.equals(reNewPass)) {
                logger.info("Error requesst : reNewPass not equals newPass  ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.reNewPass.failed.", language);
            }
            if (DataUtil.isNullObject(request.getWsCode())) {
                logger.info("Error requesst : WsCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            service = request.getWsCode();
            otpObj = dao.getOtpByIsdnAndService(isdn, service, otp);
            if (DataUtil.isNullObject(otpObj) || otpObj.getExpireTime().getTime() < currDate.getTime() || otpObj.getStatus() == 1) {
                logger.info("Error requesst : otp err ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.otp.err.", language);
            }
//            fix cho client de test. test xong bo case check equals  && "652507".equals(otp)
//            if (!dao.checkOtp(isdn, otp, service)) {
//                logger.info("Error requesst : otp err ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.otp.err.", language);
//            }
            if (!apiGwDao.checkAgUserExist(isdn)) {
                logger.info("Error requesst : ussername  err ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.username.failed.", language);
            }
            String salt = DataUtil.getSalt();
            String newPassword = DataUtil.Encode_Data(newPass + salt);
            boolean ckeck = apiGwDao.changePass(isdn, newPassword, salt);
            if (ckeck) {
                updateOTP = true;
                return responseUtil.responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.username.succ.", language);
            }
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        } finally {
            try {
                if (!DataUtil.isNullObject(otpObj) && updateOTP) {
                    otpObj.setTotalGetOtp(0L);
                    otpObj.setStatus(1L);
                    otpObj.setUpdateDate(currDate);
                    dao.update(otpObj);
                }
            } catch (Exception ex) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            }
        }
    }

    /**
     * getAllUserServicesByIsdn
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getAllUserServicesByIsdn(RequestBean request, String language) {
        logger.info("Start business getAllUserServicesByIsdn off MyMetfoneBusinessService");
        try {
            Webservice ws = myvtgDao.getWebserviceByName(Constants.WEBSERVICE_SYS_VAS_RECORD);
            if (ws == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnFrm").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            Sub sub = myvtgService.findByIsdn(isdn);
            BaseRequestDto rq = new BaseRequestDto(new RequestDto(isdn, sub.getSubId(), language));
            String rqStr = CommonUtil.convertObjectToJsonString(rq);
            logger.info("Requesst send wsSyncVasRecord: " + rqStr);
            return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_FAIL, "myMetfone.failed.", language);
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * checkMetfoneCareByIsdn
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkUseMetfoneCare(RequestBean request, String language) {
        logger.info("Start business checkMetfoneCareByIsdn off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn855(request.getWsRequest().get("isdn").toString());
            BaseResponseBean bean = null;
            MetfoneBean metfoneBean = new MetfoneBean();
            String checkTestApp = configUtils.getMessage("OPEN_TEST_METFONE_+", "ON").trim().toUpperCase();
            logger.info("OPEN_TEST_METFONE_+: " + checkTestApp);
            bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            metfoneBean.setPermission(1L);
            if ("ON".equals(checkTestApp)) {
                if (!dao.checkUseMetfoneCare(isdn)) {
                    bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_FAIL, DES_FAIL, language);
                    metfoneBean.setPermission(0L);
                }
//                if (dao.checkUseMetfoneCare(isdn)) {
//                    bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
//                    metfoneBean.setPermission(1L);
//                } else {
//                    bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_FAIL, DES_FAIL, language);
//                    metfoneBean.setPermission(0L);
//                }
            }
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * sendEmail
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean sendEmail(RequestBean request, String language) {
        logger.info("Start business sendEmail off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("content")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("content").toString())) {
                logger.info("Error requesst : content is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.content.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("programCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("programCode").toString())) {
                logger.info("Error requesst : programCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.programcode.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isToAndCC")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isToAndCC").toString())) {
                logger.info("Error requesst : isToAndCC is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.istoandcc.empty.", language);
            }
            String isToAndCC = request.getWsRequest().get("isToAndCC").toString().trim().toUpperCase();
            String programCode = request.getWsRequest().get("programCode").toString().trim().toUpperCase();
            String host = configUtils.getMessage(programCode + "_HOST", "125.235.240.36");
            String port = configUtils.getMessage(programCode + "_PORT", "465");
            String auth = configUtils.getMessage(programCode + "_AUTHEN", "true");
            String ssl = configUtils.getMessage(programCode + "_SSL", "true");
            String from = configUtils.getMessage(programCode + "_FROM");
            String pass = configUtils.getMessage(programCode + "_PASS");
            String to = "";
            String cc = "";
            String fromAddr = "";
            if ("TRUE".equals(isToAndCC)) {
                to = request.getWsRequest().get("to").toString().trim();
                cc = request.getWsRequest().get("cc").toString().trim();
            } else if ("FALSE".equals(isToAndCC)) {
//                if (DataUtil.isNullObject(request.getWsRequest().get("fromAddr")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isToAndCC").toString())) {
//                    logger.info("Error requesst : fromAddr is null ");
//                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.fromAddr.empty.", language);
//                }

                fromAddr = request.getWsRequest().containsKey("fromAddr") && !DataUtil.isNullObject(request.getWsRequest().get("fromAddr")) ? request.getWsRequest().get("fromAddr").toString() : "";
                to = configUtils.getMessage(programCode + "_TO");
                cc = configUtils.getMessage(programCode + "_CC", "");
            }
            if (DataUtil.isNullOrEmpty(to)) {
                logger.info("Error requesst : to is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.to.empty.", language);
            }
            if (DataUtil.isNullOrEmpty(from)) {
                logger.info("Error requesst : from is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.from.empty.", language);
            }
            BaseResponseBean bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            String subject = "";
            if (request.getWsRequest().containsKey("subject")) {
                subject = request.getWsRequest().get("subject").toString();
            }
            if (DataUtil.isNullOrEmpty(subject) && subject.length() > 3000) {
                logger.info("Error requesst : subject max length ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.subject.length.", language);
            }
            String content = request.getWsRequest().get("content").toString();
//            if (content.length() > 4000) {
//                logger.info("Error requesst : content max length ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfon.email.content.length.", language);
//            }
            String attachmentBase64 = request.getWsRequest().containsKey("attachment") ? request.getWsRequest().get("attachment").toString() : "";
            String filename = "attachFile"; // wsRequest.get("fileName").toString();
            logger.info("Start call sendmail");
            String sendMail = configUtils.getMessage("SEND_MAIL", "ON").trim().toUpperCase();
            if ("ON".equals(sendMail)) {
                DataUtil.sendMail(from, pass, from, to, host, port, subject,
                        content, attachmentBase64, filename, auth, ssl, cc, logger);
            }
            if ("INTERNET_WIFI".equals(programCode.trim().toUpperCase())) {
                String contentStr = content;
                String cusName = "";
                String cusPhone = "";
                String cusAddr = "";
                String packageStr = "";
                String charge = "";
                String payAdvance = "";
                String dateRq = "";
                if (contentStr.contains(":")) {
                    contentStr = contentStr.substring(contentStr.indexOf(":"));
                }
                String[] contentArr = contentStr.split("-");
                for (String str : contentArr) {
                    if (str.toUpperCase().contains(configUtils.getMessage("MAIL_DATE_RQ").trim().toUpperCase())) {
                        dateRq = str.substring(str.indexOf(":") + 1, str.indexOf("\r\n\t")).trim();
                    }
                    if (str.toUpperCase().contains(configUtils.getMessage("MAIL_CUST_NAME").trim().toUpperCase())) {
                        cusName = str.substring(str.indexOf(":") + 1, str.indexOf("\r\n\t")).trim();
                    }
                    if (str.toUpperCase().contains(configUtils.getMessage("MAIL_CUST_PHONE").trim().toUpperCase())) {
                        cusPhone = str.substring(str.indexOf(":") + 1, str.indexOf("\r\n\t")).trim();
                    }
                    if (str.toUpperCase().contains(configUtils.getMessage("MAIL_CUST_ADDRESS").trim().toUpperCase())) {
                        cusAddr = str.substring(str.indexOf(":") + 1, str.indexOf("\r\n\t")).trim();
                    }
                    if (str.toUpperCase().contains(configUtils.getMessage("MAIL_PACKAGE").trim().toUpperCase())) {
                        packageStr = str.substring(str.indexOf(":") + 1, str.indexOf("\r\n\t")).trim();
                    }
                    if (str.toUpperCase().contains(configUtils.getMessage("MAIL_CHARGE").trim().toUpperCase())) {
                        charge = str.substring(str.indexOf(":") + 1, str.indexOf("\r\n\t")).trim();
                        charge = charge.contains("$") ? charge.replace("$", "").trim() : charge;
                    }
                    if (str.toUpperCase().contains(configUtils.getMessage("MAIL_PAY_ADVANCE").trim().toUpperCase())) {
                        payAdvance = str.substring(str.indexOf(":") + 1).trim();
                    }
                }
                //nhan tin
                String contentSms = configUtils.getMessage("myMetfon.email.content.sms");
                insertMt(DataUtil.fomatIsdn855(cusPhone), contentSms, "METFONE");
                insertLogSendMail(dateRq, from, to, cc, CommonUtil.convertObjectToJsonString(request), CommonUtil.convertObjectToJsonString(bean),
                        bean.getErrorCode(), fromAddr, programCode, subject, cusName, cusPhone, cusAddr, packageStr,
                        charge, payAdvance);
            }

            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }

    }

//    @Override
//    public ResponseDto inviteUseMetfone(RequestBean request, String language) {
//        logger.error("Start business inviteUseMetfone off MyMetfoneBusinessService");
//        try {
//            if (DataUtil.isNullObject(request.getWsRequest().get("isdnRecivers"))
//                    || DataUtil.isNullObject(request.getWsRequest().get("isdnSender"))) {
//                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
//            }
//
//            String isdnReciverStr = request.getWsRequest().get("isdnRecivers").toString();
//            String isdnSender = request.getWsRequest().get("isdnSender").toString();
//            String programCode = request.getWsRequest().containsKey("programCode") ? request.getWsRequest().get("programCode").toString() : "";
//            if (DataUtil.isNullOrEmpty(isdnReciverStr)
//                    || DataUtil.isNullOrEmpty(isdnSender)) {
//                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
//            }
//            isdnSender = DataUtil.fomatIsdn(isdnSender);
//            //todo call gui tin nhan
//            String[] isdnRecivers = isdnReciverStr.split(",");
//            Long MAX_INVITE_ON_DAY = Long.parseLong(configUtils.getMessage(Constants.MAX_INVITE_ON_DAY, "50").trim());
//            Long MAX_SELECT_INVITE = Long.parseLong(configUtils.getMessage(Constants.MAX_SELECT_INVITE, "20").trim());
//            if (isdnRecivers.length > MAX_SELECT_INVITE) {
//                return responseUtil.response(Constants.MAX_SHARE, "my.metfone.invite.max.share.select.", "my.metfone.invite.max.share.select.", language, MAX_SELECT_INVITE.toString(), MAX_SELECT_INVITE.toString(), null, null);
//            }
//
//            Long checkShare = dao.checkMaxShareOnDay(isdnSender);
//            if (checkShare >= MAX_INVITE_ON_DAY) {
//                return responseUtil.response(Constants.MAX_SHARE, "my.metfone.invite.max.share.", "my.metfone.invite.max.share.", language, MAX_INVITE_ON_DAY.toString(), null, null, null);
//            }
//            if ((checkShare + isdnRecivers.length) > MAX_INVITE_ON_DAY) {
//                return responseUtil.response(Constants.MAX_SHARE, "my.metfone.invite.max.share1.", "my.metfone.invite.max.share1.", language, MAX_INVITE_ON_DAY.toString(), checkShare.toString(), null, null);
//            }
//            smsService.connectServer();
//            Date date = new Date();
//            String isdnReciver = "";
//            int count = 0;
//            for (String str : isdnRecivers) {
//                //call gui tin nhan
//                date = DataUtil.addSecond(date, 1);
//                str = DataUtil.fomatIsdn(str);
//                if (DataUtil.isNullOrEmpty(programCode)) {
//                    //app my metfone
//                    if (dao.checkRegisterMyMetfone(str)) {
//                        isdnReciver += "," + str;
//                        String content = responseUtil.getMessage("my.metfone.sms.invite.content." + language, isdnSender, null, null, null);
//                        int statusSend = smsService.sendSMS(str, content);
//                        logger.info("Start insertInvite of BussinessDAO ");
//                        InviteLog inviteLog = new InviteLog();
//                        inviteLog.setIsdnSender(isdnSender);
//                        inviteLog.setIsdnReciver(str);
//                        inviteLog.setStatus((long) statusSend);
//                        inviteLog.setContent(content);
//                        inviteLog.setCreateDate(date);
//                        inviteLog.setStatusLogin(0L);
//                        dao.insert(inviteLog);
//                        count++;
//                    }
//
//                }
//            }
//            return responseUtil.response(Constants.ERROR_SUCCESS, "my.metfone.invite.succ.", "my.metfone.invite.succ.", language, String.valueOf(count), null, null, null);
//        } catch (Exception e) {
//            logger.error("parse description error: ", e);
//            return responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language);
//        }
//    }
    /**
     * inviteUseMetfone
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public ResponseDto inviteUseMetfone(RequestBean request, String language
    ) {
        logger.error("Start business inviteUseMetfoneTest off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnRecivers"))
                    || DataUtil.isNullObject(request.getWsRequest().get("isdnSender"))) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            String isdnReciverStr = request.getWsRequest().get("isdnRecivers").toString();
            String isdnSender = request.getWsRequest().get("isdnSender").toString();
            String programCode = request.getWsRequest().containsKey("programCode") ? request.getWsRequest().get("programCode").toString() : "";
            if (DataUtil.isNullOrEmpty(isdnReciverStr)
                    || DataUtil.isNullOrEmpty(isdnSender)) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            isdnSender = DataUtil.fomatIsdn(isdnSender);
            //todo call gui tin nhan
            String[] isdnRecivers = isdnReciverStr.split(",");
            Long MAX_INVITE_ON_DAY = Long.parseLong(configUtils.getMessage(Constants.MAX_INVITE_ON_DAY, "50").trim());
            Long MAX_SELECT_INVITE = Long.parseLong(configUtils.getMessage(Constants.MAX_SELECT_INVITE, "20").trim());
            if (isdnRecivers.length > MAX_SELECT_INVITE) {
                return responseUtil.response(Constants.MAX_SHARE, "my.metfone.invite.max.share.select.", "my.metfone.invite.max.share.select.", language, MAX_SELECT_INVITE.toString(), MAX_SELECT_INVITE.toString(), null, null);
            }

            if (DataUtil.isNullOrEmpty(programCode)) {
                Long checkShare = dao.checkMaxShareOnDay(isdnSender);
                if (checkShare >= MAX_INVITE_ON_DAY) {
                    return responseUtil.response(Constants.MAX_SHARE, "my.metfone.invite.max.share.", "my.metfone.invite.max.share.", language, MAX_INVITE_ON_DAY.toString(), null, null, null);
                }
                if ((checkShare + isdnRecivers.length) > MAX_INVITE_ON_DAY) {
                    return responseUtil.response(Constants.MAX_SHARE, "my.metfone.invite.max.share1.", "my.metfone.invite.max.share1.", language, MAX_INVITE_ON_DAY.toString(), checkShare.toString(), null, null);
                }
            }
            smsService.connectServer();
            Date date = new Date();
            String isdnReciver = "";
            int count = 0;
            for (String str : isdnRecivers) {
                //call gui tin nhan
                String content = "";
                int statusSend = 0;
                str = DataUtil.fomatIsdn(str);
                date = DataUtil.addSecond(date, 1);
                if (DataUtil.isNullOrEmpty(programCode)) {
                    //app my metfone
                    if (dao.checkRegisterMyMetfone(str)) {
                        content = responseUtil.getMessage("my.metfone.sms.invite.content." + language, isdnSender, null, null, null);
                        statusSend = smsService.sendSMS(str, content);
                        logger.info("Start insertInvite MyMetfone ");
                        InviteLog inviteLog = new InviteLog();
                        inviteLog.setIsdnSender(isdnSender);
                        inviteLog.setIsdnReciver(str);
                        inviteLog.setStatus((long) statusSend);
                        inviteLog.setContent(content);
                        inviteLog.setCreateDate(date);
                        inviteLog.setStatusLogin(0L);
                        dao.insert(inviteLog);
                        isdnReciver += "," + str;
                        count++;
                    }
                } else {
                    //game
                    if (dao.checkRegisterMyMetfone(str)) {
                        logger.info("Start insertInvite MyMetfone ");
                        content = responseUtil.getMessage("my.metfone.sms.invite.game.content." + language, null, null, null, null);
                        InviteLog inviteLog = new InviteLog();
                        inviteLog.setIsdnSender(isdnSender);
                        inviteLog.setIsdnReciver(str);
                        inviteLog.setStatus((long) statusSend);
                        inviteLog.setContent(content);
                        inviteLog.setCreateDate(date);
                        inviteLog.setStatusLogin(0L);
                        dao.insert(inviteLog);
                    }
                    if (dao.checkInviteGame(str, programCode)) {
                        content = responseUtil.getMessage("my.metfone.sms.invite.game.content." + language, null, null, null, null);
                        logger.info("Start insertInviteGame ");
                        InviteGame invite = new InviteGame();
                        invite.setIsdnSender(isdnSender);
                        invite.setIsdnReciver(str);
                        invite.setStatus((long) statusSend);
                        invite.setContent(content);
                        invite.setProgramCode(programCode);
                        invite.setCreateDate(date);
                        invite.setStatusLogin(0L);
                        dao.insert(invite);
                    }
                    if (!DataUtil.isNullOrEmpty(content)) {
                        smsService.sendUnicode(str, content);
                    }
                }
            }
            String response = "";
            if (DataUtil.isNullOrEmpty(programCode)) {
                response = responseUtil.getMessage("my.metfone.invite.succ." + language, String.valueOf(count), null, null, null);
            } else {
                response = responseUtil.getMessage("my.metfone.invite.game.succ." + language, null, null, null, null);
            }
            return responseUtil.response(Constants.ERROR_SUCCESS, response, response);
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language);
        }

    }

    /**
     * doRedeemInviteOrLoginFirst
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public ResponseDto doRedeemInviteOrLoginFirst(RequestBean request, String language
    ) {
        logger.info("Start doRedeemInviteOrLoginFirst of MyMetfoneBusinessService ");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn"))
                    || DataUtil.isNullObject(request.getWsRequest().get("type"))) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String type = request.getWsRequest().get("type").toString();
            if (DataUtil.isNullOrEmpty(isdn) || DataUtil.isNullOrEmpty(type)) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            isdn = DataUtil.fomatIsdn(isdn);
            String isdnTmp = "";
            if ("2".equals(type)) {
                if (DataUtil.isNullObject(request.getWsRequest().get("isdnTmp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnTmp").toString())) {
                    return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
                }
                isdnTmp = DataUtil.fomatIsdn(request.getWsRequest().get("isdnTmp").toString());
            }
            Exchange exchange = new Exchange();
            ExchMsg responseCall = null;
            PromotionConfig promotionConfig = dao.getPromotionByType(Long.parseLong(type), Constants.MY_METFONE_INVITE_REDEEM);
            if (DataUtil.isNullObject(promotionConfig)) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.result.confPromotuon.empty.", "my.metfone.result.confPromotuon.empty.", language);
            }
            Date dateCurr = new Date();
            if (DataUtil.addDate(dateCurr).getTime() <= DataUtil.addDate(promotionConfig.getStartTime()).getTime() && DataUtil.addDate(dateCurr).getTime() >= DataUtil.addDate(promotionConfig.getEndTime()).getTime()) {
                return responseUtil.response(Constants.MAX_TIME, "my.metfone.reddem.maxtime.err.", "my.metfone.reddem.maxtime.err.", language);
            }
            if (DataUtil.isNullObject(promotionConfig.getAmount()) && DataUtil.isNullOrEmpty(promotionConfig.getData())) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);
            }
            if (!DataUtil.isNullObject(promotionConfig.getAmount()) && !DataUtil.isNullOrEmpty(promotionConfig.getData())) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);
            }
            Date expriedActionRedeem = null;
            if (DataUtil.isNullOrEmpty(isdnTmp)) {
                expriedActionRedeem = apiGwDao.getExPriedActionRedeem(isdn, Integer.parseInt(promotionConfig.getValueAction().toString()), null);
            } else {
                expriedActionRedeem = apiGwDao.getExPriedActionRedeem(isdn, Integer.parseInt(promotionConfig.getValueAction().toString()), isdnTmp);
            }
            if (DataUtil.isNullObject(expriedActionRedeem)) {
                return responseUtil.response(Constants.ERROR_SYSTEM, "my.metfone.isdn.err.", "my.metfone.isdn.err.", language);

            }
            if (new Date().getTime() >= expriedActionRedeem.getTime()) {
                return responseUtil.response(Constants.MAX_TIME, "my.metfone.reddem.maxtime.err.", "my.metfone.reddem.maxtime.err.", language);

            }
            if (!dao.checkRedeemInviteOrLoginFirst(isdn, Long.parseLong(type), isdnTmp)) {
                return responseUtil.response(Constants.ERROR_REDEEM, "my.metfone.invite.redeem.err.", "my.metfone.invite.redeem.err.", language);
            }
            String promotion = "";
            String unitOffer = "";
            String promo = "";
            Integer addDay = promotionConfig.getValue();
            String expiredDate = "";
            String dateNotify = fm.format(DataUtil.addDate(dateCurr, addDay));
            String value = String.valueOf(promotionConfig.getValue());
            if (!DataUtil.isNullObject(promotionConfig.getAmount())) {
                promotion = promotionConfig.getAmount().toString();
                promo = promotionConfig.getAmount().toString();
                unitOffer = "USD";
            } else if (!DataUtil.isNullOrEmpty(promotionConfig.getData())) {
//                giá trị truyền vào là MB ==> nhân ra byte
                Double i = Double.parseDouble(promotionConfig.getData()) * 1024 * 1024;
                promotion = String.valueOf(i);
                expiredDate = dateStr.format(DataUtil.addDate(dateCurr, addDay));
                promo = promotionConfig.getData();
                unitOffer = "MB";
            }
            System.out.println("promotion :" + promotion);
            String error = "";
            String callPro = configUtils.getMessage(Constants.CALL_PRO, "OFF").trim();
            if ("ON".equals(callPro.toUpperCase())) {
                responseCall = WebServiceUtil.callProvisioning(exchange, isdn, promotion, promotionConfig.getBalance(), addDay, expiredDate, cmpreDao);
                if (DataUtil.isNullObject(responseCall)) {
                    return responseUtil.response(Constants.ERROR_REDEEM, "my.metfone.reddem.err.", "my.metfone.reddem.err.", language);
                }
                error = responseCall.getError();
            } else {
                //tat call tong dai de test thi fix mac dinh call thah cong
                error = "0";
            }
            Date endDate = DataUtil.addDate(new Date(), promotionConfig.getValue());
            String response = "";
            Long status = 0L;
            String content = "";
            if (DataUtil.isNullOrEmpty(error) || !"0".equals(error)) {
                return responseUtil.response(Constants.ERROR_REDEEM, "my.metfone.reddem.err.", "my.metfone.reddem.err.", language);
            } else {
                status = 1L;
                Long notifyId = null;
                String param = "";

                if ("1".equals(type)) {
                    notifyId = 4L;
                    param = "{1}" + ":" + promo + unitOffer;
                    content = configUtils.getMessage("my.metfone.login.redeem.sms.content", promo + unitOffer, value, null, null);
                } else if ("2".equals(type)) {
                    notifyId = 6L;
                    param = "{1}" + ":" + promo + unitOffer + ";" + "{2}" + ":" + dateNotify;
                    content = configUtils.getMessage("my.metfone.login.invite.redeem.sms.content", promo + unitOffer, isdnTmp, null, null);
                }
                insertSubPushNotify(isdn, notifyId, param);
            }
            insertRedeem(isdn, promotionConfig.getAmount(), promotionConfig.getType(), status, endDate, promotionConfig.getData(), isdnTmp);
            if (!DataUtil.isNullOrEmpty(content)) {
                insertMt(isdn, content);
            }
            return responseUtil.response(Constants.ERROR_SUCCESS, "my.metfone.reddem.succ.", "my.metfone.reddem.succ.", language);
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language);
        }
    }

    @Override
    public ResponseDto checkLogin(RequestBean request, String language
    ) {
        logger.info("Start checkLogin of MyMetfoneBusinessService ");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdns"))
                    || DataUtil.isNullObject(request.getWsRequest().get("ckeckPromotion"))) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            String isdnCkeckstr = request.getWsRequest().get("isdns").toString();
            logger.info("isdnCkeckstr : " + isdnCkeckstr);
            String checkPromotion = request.getWsRequest().get("ckeckPromotion").toString();
            String programCode = request.getWsRequest().containsKey("programCode") ? request.getWsRequest().get("programCode").toString() : "";
            if (DataUtil.isNullOrEmpty(isdnCkeckstr) || DataUtil.isNullOrEmpty(checkPromotion)) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            PromotionConfig promotionSign = dao.getPromotionByType(1L, Constants.MY_METFONE_INVITE_REDEEM);
            PromotionConfig promotionIvite = dao.getPromotionByType(2L, Constants.MY_METFONE_INVITE_REDEEM);
            PromotionConfig addPointSign = dao.getPromotionByType(3L, Constants.MY_METFONE_ADD_PONT);
            PromotionConfig addPointInvite = dao.getPromotionByType(4L, Constants.MY_METFONE_ADD_PONT);
            if (!DataUtil.isNullObject(promotionSign)
                    && (DataUtil.isNullObject(promotionSign.getAmount()) && DataUtil.isNullOrEmpty(promotionSign.getData()))) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);
            }
            if (!DataUtil.isNullObject(promotionIvite) && (DataUtil.isNullObject(promotionIvite.getAmount()) && DataUtil.isNullOrEmpty(promotionIvite.getData()))) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);
            }

            if (!DataUtil.isNullObject(promotionIvite)
                    && ((!DataUtil.isNullObject(promotionIvite.getAmount()) && !DataUtil.isNullOrEmpty(promotionIvite.getData())))) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);
            }
            if (!DataUtil.isNullObject(promotionSign) && (!DataUtil.isNullObject(promotionSign.getAmount()) && !DataUtil.isNullOrEmpty(promotionSign.getData()))) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);

            }
            String promotionS = "";
            String unitOfferS = "";
            if (!DataUtil.isNullObject(promotionSign)) {
                if (!DataUtil.isNullObject(promotionSign.getAmount())) {
                    promotionS = promotionSign.getAmount().toString();
                    unitOfferS = "USD";
                } else if (!DataUtil.isNullOrEmpty(promotionSign.getData())) {
                    promotionS = String.valueOf(promotionSign.getData());
                    unitOfferS = "MB";
                }
            }
            String promotionI = "";
            String unitOfferI = "";
            if (!DataUtil.isNullObject(promotionIvite)) {
                if (!DataUtil.isNullObject(promotionIvite.getAmount())) {
                    promotionI = promotionIvite.getAmount().toString();
                    unitOfferI = "USD";
                } else if (!DataUtil.isNullOrEmpty(promotionIvite.getData())) {
                    promotionI = String.valueOf(promotionIvite.getData());
                    unitOfferI = "MB";
                }
            }
            String[] isdnCkeck = isdnCkeckstr.split(",");

            List<ResultDto> resultList = new ArrayList<>();
            String response = "Success";
            ResponseDto resResult = new ResponseDto(Constants.ERROR_SUCCESS, response, resultList);
            smsService.connectServer();
            for (String isdn : isdnCkeck) {
                //to do
                ResultDto result = new ResultDto();
                isdn = DataUtil.fomatIsdn(isdn);
                boolean check = false;
                if ("false".equals(checkPromotion)) {
                    //bo sung load danh ba de invite tu game
                    if (DataUtil.isNullOrEmpty(programCode)) {
                        // load danh ba invite mymetfone
                        check = dao.checkRegisterMyMetfone(isdn);
                    } else {
                        // load danh ba cho game
                        check = dao.checkInviteGame(isdn, programCode);
                    }
                } else {
                    if (dao.checkRedeemInviteOrLoginFirst(isdn, 1L, null)) {
                        check = apiGwDao.checkLoginFirst(isdn);
                    }
                }
                result.setIsdn(isdn);
                if (check) {
                    result.setStatus(1L);
                } else {
                    result.setStatus(2L);
                }
                if ("true".equals(checkPromotion) && result.getStatus() == 1) {
                    //cập nhat vào invitelog cho trương hop user dc moi login lan dau voi user moi dau tien voi status login =1, các tuong hop duoc moi do user moi sau cap nhat lai status login = 2
                    InviteLogDTO inviteLog = dao.getIsdnSenderInviteMyMetfone(isdn);
                    if (!DataUtil.isNullObject(inviteLog) && !DataUtil.isNullOrEmpty(inviteLog.getIsdnSender())) {
                        dao.updateInvite(null, 1L, true, inviteLog.getId());
                        dao.updateInvite(isdn, 2L, false, inviteLog.getId());
                    }
                    Date dateCurr = new Date();
                    if (!DataUtil.isNullObject(promotionSign)
                            && DataUtil.addDate(dateCurr).getTime() >= DataUtil.addDate(promotionSign.getStartTime()).getTime()
                            && DataUtil.addDate(dateCurr).getTime() <= DataUtil.addDate(promotionSign.getEndTime()).getTime()) {
                        insertSubPushNotify(isdn, 7L, "{1}" + ":" + promotionS + unitOfferS + ";" + "{2}" + ":" + promotionSign.getValue());
                        result.setContent(configUtils.getMessage("my.metfone.popup.redeem.content." + language, promotionS + unitOfferS, promotionSign.getValue().toString(), null, null));
                        resResult.setType(1L);
                        String contentSms = configUtils.getMessage("my.metfone.login.content.sms", promotionS + unitOfferS, promotionSign.getValue().toString(), null, null);
                        smsService.sendSMS(isdn, contentSms);
                        //gui thog bao notify cho user invite
                        if (!DataUtil.isNullObject(promotionIvite) && DataUtil.addDate(dateCurr).getTime() >= DataUtil.addDate(promotionIvite.getStartTime()).getTime() && DataUtil.addDate(dateCurr).getTime() <= DataUtil.addDate(promotionIvite.getEndTime()).getTime()) {
                            if (!DataUtil.isNullObject(inviteLog) && !DataUtil.isNullOrEmpty(inviteLog.getIsdnSender())) {
                                insertSubPushNotify(inviteLog.getIsdnSender(), 5L, "{MONEY}" + ":" + promotionI + unitOfferI + ";" + "{EXPIRED}" + ":" + promotionIvite.getValue() + ";" + "{ISDN}:" + isdn);
                            }
                        }
                    } else {
                        logger.info("Het thoi gian khuyen mai redeem invite");
                        result.setStatus(2L);
                    }
                    //cong point cho user dang nhap lan dau
                    if (!DataUtil.isNullObject(addPointSign)
                            && DataUtil.addDate(dateCurr).getTime() >= DataUtil.addDate(addPointSign.getStartTime()).getTime()
                            && DataUtil.addDate(dateCurr).getTime() <= DataUtil.addDate(addPointSign.getEndTime()).getTime()) {
                        String error = addPoint(isdn, addPointSign.getValue().toString());
                        if ("0".equals(error)) {
                            String contentSmsAddpoint = configUtils.getMessage("my.metfone.login.content.sms.point", addPointSign.getValue().toString(), null, null, null);
                            smsService.sendSMS(isdn, contentSmsAddpoint);
                        }
                        //cong point cho user dang nhap lan dau
                        if (!DataUtil.isNullObject(addPointInvite)
                                && DataUtil.addDate(dateCurr).getTime() >= DataUtil.addDate(addPointInvite.getStartTime()).getTime()
                                && DataUtil.addDate(dateCurr).getTime() <= DataUtil.addDate(addPointInvite.getEndTime()).getTime()) {
                            if (!DataUtil.isNullObject(inviteLog) && !DataUtil.isNullOrEmpty(inviteLog.getIsdnSender())) {
                                String errorInvite = addPoint(inviteLog.getIsdnSender(), addPointInvite.getValue().toString());
                                if ("0".equals(errorInvite)) {
                                    String contentAddpoint = configUtils.getMessage("my.metfone.intvite.content.sms.point", addPointInvite.getValue().toString(), isdn, null, null);
                                    smsService.sendSMS(inviteLog.getIsdnSender(), contentAddpoint);
                                }
                            }
                        }
                    } else {
                        logger.info("Het thoi gian khuyen mai add point");
                    }

                    List<InviteGameDTO> inviteList = dao.getIsdnSenderGame(isdn);
                    if (!DataUtil.isNullOrEmpty(inviteList)) {
                        for (InviteGameDTO invite : inviteList) {
                            dao.updateInviteGame(null, 1L, true, invite.getId(), invite.getProgramCode());
                            dao.updateInviteGame(isdn, 2L, false, invite.getId(), invite.getProgramCode());
                        }
                    }
                }
                resultList.add(result);
            }
            logger.info("end checkLogin of BusinessController ");
            return resResult;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language);
        }
    }

    @Override
    public ResponseDto validateRedeemInviteOrLoginFirst(RequestBean request, String language
    ) {
        logger.info("Start validateRedeemInviteOrLoginFirst of MyMetfoneBusinessService ");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn"))
                    || DataUtil.isNullObject(request.getWsRequest().get("message"))) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String message = request.getWsRequest().get("message").toString();
            System.out.println("message : " + message);
            String isdnTmp = "";
            Long type = null;

            if (DataUtil.isNullOrEmpty(isdn) || DataUtil.isNullOrEmpty(message)) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.input.err.empty.", "my.metfone.input.err.empty.", language);
            }
            isdn = DataUtil.fomatIsdn(isdn);
            if (message.trim().startsWith("You are offered")) {
                type = 2L;
                isdnTmp = message.substring(message.indexOf("the") + 4, message.indexOf("to") - 1);
                if (DataUtil.isNullOrEmpty(isdnTmp)) {
                    logger.info("Notyfy login first : Welcome to myMetfone application, we offer you XX to use XX days. Please click REDEEM to claim it now ");
                    logger.info("Notyfy invite : You are offered XXXXXX valid XX day for successful invite the XXXXXX to use myMetfone application. Click “Redeem” to claim it now ");
                    return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.fomat.content.notify.", "my.metfone.fomat.content.notify.", language);
                }
                isdnTmp = DataUtil.fomatIsdn(isdnTmp);
            } else if (message.trim().startsWith("Welcome to myMetfone")) {
                type = 1L;
            } else {
                logger.info("Notyfy login first : Welcome to myMetfone application, we offer you XX to use XX days. Please click REDEEM to claim it now ");
                logger.info("Notyfy invite : You are offered XXXXXX valid XX day for successful invite the XXXXXX to use myMetfone application. Click “Redeem” to claim it now ");
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.fomat.content.notify.", "my.metfone.fomat.content.notify.", language);
            }

            PromotionConfig promotionConfig = dao.getPromotionByType(type, Constants.MY_METFONE_INVITE_REDEEM);
            if (DataUtil.isNullObject(promotionConfig)) {
                return responseUtil.response(Constants.PARAMETER_INVALID, "my.metfone.result.confPromotuon.empty.", "my.metfone.result.confPromotuon.empty.", language);

            }
            if (DataUtil.isNullObject(promotionConfig.getAmount()) && DataUtil.isNullOrEmpty(promotionConfig.getData())) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);
            }
            if (!DataUtil.isNullObject(promotionConfig.getAmount()) && !DataUtil.isNullOrEmpty(promotionConfig.getData())) {
                return responseUtil.response(Constants.ERROR_CONF, "my.metfone.promotion.conf.err.", "my.metfone.promotion.conf.err.", language);
            }
            Date dateCurr = new Date();
            if (DataUtil.addDate(dateCurr).getTime() < DataUtil.addDate(promotionConfig.getStartTime()).getTime() || DataUtil.addDate(dateCurr).getTime() > DataUtil.addDate(promotionConfig.getEndTime()).getTime()) {
                return responseUtil.response(Constants.MAX_TIME, "my.metfone.reddem.maxtime.err.", "my.metfone.reddem.maxtime.err.", language);
            }
            Date expriedActionRedeem = null;
            if (DataUtil.isNullOrEmpty(isdnTmp)) {
                expriedActionRedeem = apiGwDao.getExPriedActionRedeem(isdn, Integer.parseInt(promotionConfig.getValueAction().toString()), null);
            } else {
                expriedActionRedeem = apiGwDao.getExPriedActionRedeem(isdn, Integer.parseInt(promotionConfig.getValueAction().toString()), isdnTmp);
            }
            if (DataUtil.isNullObject(expriedActionRedeem)) {
                return responseUtil.response(Constants.ERROR_SYSTEM, "my.metfone.isdn.err.", "my.metfone.isdn.err.", language);
            }
            if (new Date().getTime() >= expriedActionRedeem.getTime()) {
                return responseUtil.response(Constants.MAX_TIME, "my.metfone.reddem.maxtime.err.", "my.metfone.reddem.maxtime.err.", language);
            }
            if (!dao.checkRedeemInviteOrLoginFirst(isdn, type, isdnTmp)) {
                return responseUtil.response(Constants.ERROR_REDEEM, "my.metfone.invite.redeem.err.", "my.metfone.invite.redeem.err.", language);
            }
            return new ResponseDto(Constants.ERROR_SUCCESS, "Success", message, type, isdnTmp.trim());
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language);
        }
    }

    /**
     * @param date
     * @param n
     * @return
     */
    private String toDateMsg(Date date, int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return sdf.format(cal.getTime());
    }

    /**
     * postionArrayList
     *
     * @param balanceNameLst
     * @param balanceValueLst
     * @return
     */
    //function get postion ArrayList
    private String postionArrayList(String[] balanceNameLst, String[] balanceValueLst) {
        String valueList = null;

        for (int i = 0; i < balanceNameLst.length; i++) {
            String element = balanceNameLst[i];
            String valueBalanceValueLst = balanceValueLst[i];
            if (element.equals("2000")) {
                valueList = valueBalanceValueLst;
                break;
            }
        }

        return valueList;
    }

    /**
     * InsertTransfer
     *
     * @param subID
     * @param msisdn
     * @param receiver
     * @param moneySenderBefore
     * @param moneyTransfer
     * @param moneySenderAfter
     * @param feeTransfer
     * @return
     */
    private int InsertTransfer(Long subID, String msisdn, String receiver, int moneySenderBefore, int moneyTransfer, int moneySenderAfter, Long feeTransfer) {
        try {
            int result = mkishareDao.insertMoneyTransferLog(logger, subID, msisdn, receiver, moneySenderBefore, moneyTransfer, moneySenderAfter, feeTransfer);
            return result;
        } catch (Exception ex) {
            logger.info("erro: " + ex.getMessage(), ex);
        }
        return 0;
    }

    /**
     * InsertMt
     *
     * @param msisdn
     * @param messae
     * @return
     */
    private int InsertMt(String msisdn, String messae) {
        try {
            String channel = configUtils.getMessage("CHANNEL", "113");
            int result = mkishareDao.insertMT(msisdn, messae, channel);
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return 0;
    }

    /**
     * InsertMt
     *
     * @param msisdn
     * @param messae
     * @return
     */
    private int insertMt(String msisdn, String messae) {
        try {
            String channel = configUtils.getMessage("MY_METFONE", "MyMetfone");
            int result = smsDao.insertMT(DataUtil.fomatIsdn855(msisdn), messae, channel);
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return 0;
    }

    /**
     * InsertMt
     *
     * @param msisdn
     * @param messae
     * @return
     */
    private int insertMt(String msisdn, String messae, String channel) {
        try {
            String channels = configUtils.getMessage(channel);
            int result = smsDao.insertMT(DataUtil.fomatIsdn855(msisdn), messae, channels);
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return 0;
    }

    /**
     * addDate
     *
     * @param date
     * @param addDay
     * @return
     */
    private Date addTime(Date date, int hour) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, hour);
//        c.set(Calendar.HOUR_OF_DAY, 23);
//        c.set(Calendar.MINUTE, 59);
//        c.set(Calendar.SECOND, 00);
//        c.set(Calendar.MILLISECOND, 00);
        return c.getTime();
    }

    /**
     * insertSubPushNotify
     *
     * @param isdn
     * @param notifyId
     * @param param
     * @throws Exception
     */
    public void insertSubPushNotify(String isdn, Long notifyId, String param) throws Exception {
        logger.info("Start insertSubPushNotify ");
        SubPushNotify subPushNotify = new SubPushNotify();
        subPushNotify.setIsdn(isdn);
        subPushNotify.setNotifyId(notifyId);
        subPushNotify.setStatus(1L);
        subPushNotify.setParam(param);
        subPushNotify.setCreateDate(new Date());
        Long xxx = apiGwDao.insert(subPushNotify);
        System.out.println("xxx ++++++++++++++++++++++++ " + xxx);
    }

    /**
     * insertRedeem
     *
     * @param isdn
     * @param amount
     * @param type
     * @param status
     * @param endDate
     * @param data
     * @param redeemTmp1
     */
    public void insertRedeem(String isdn, Double amount, Long type, Long status, Date endDate, String data, String redeemTmp1) throws Exception {
        logger.info("Start insertRedeem  ");
        Redeem redeem = new Redeem();
        redeem.setIsdn(isdn);
        redeem.setAmount(amount);
        redeem.setExpiredDate(endDate);
        redeem.setStatus(status);
        redeem.setCreateDate(new Date());
        redeem.setType(type);
        redeem.setData(data);
        redeem.setRedeemTmp1(redeemTmp1);
        dao.insert(redeem);
    }

    /**
     * insertLogSendMail
     *
     * @param dateRq
     * @param from
     * @param to
     * @param cc
     * @param rq
     * @param res
     * @param errorCode
     * @param fromAddr
     * @param programCode
     * @param subject
     * @param cusName
     * @param cusPhone
     * @param cusAddr
     * @param packageStr
     * @param charge
     * @param payAdvance
     * @throws Exception
     */
    public void insertLogSendMail(String dateRq, String from, String to,
            String cc, String rq, String res, String errorCode,
            String fromAddr, String programCode, String subject, String cusName,
            String cusPhone, String cusAddr, String packageStr, String charge, String payAdvance) throws Exception {
        logger.info("Start insertLogSendMail  ");
        Date date = null;
        if (!DataUtil.isNullOrEmpty(dateRq)) {
            date = DataUtil.convertStringToDate(dateRq, "dd/MM/yyyy HH:mm:ss");
        }
        //ghi log          }
        LogSendMail logMail = new LogSendMail();
        logMail.setSender(from);
        logMail.setRecievers(to);
        logMail.setCc(cc);
        logMail.setRequest(rq);
        logMail.setResponse(res);
        logMail.setErrorCode(errorCode);
        logMail.setEmail(fromAddr);
        logMail.setProgramCode(programCode);
        logMail.setSubject(subject);
        logMail.setCustName(cusName);
        logMail.setCustPhone(DataUtil.isNullOrEmpty(cusPhone) ? cusPhone : DataUtil.fomatIsdn(cusPhone));
        logMail.setCustAddr(cusAddr);
        logMail.setPackageStr(packageStr);
        logMail.setMonthlyCharge(charge);
        logMail.setPayAdvance(payAdvance);
        logMail.setRequestDate(DataUtil.isNullObject(date) ? new Date() : date);
        dao.insert(logMail);
    }

    /**
     * addPoint
     *
     * @param isdn
     * @param point
     * @return
     */
    public String addPoint(String isdn, String point) {
        logger.info("Start business changeLoyalty off LuckyLoyaltyGameServiceImpl");
        String error = "1";
        try {
            String pointId = configUtils.getMessage("POINT_ID", "1000001").trim();
            String transTypeId = configUtils.getMessage("TRANS_TYPE_ID", "1000024").trim();
            String callService = configUtils.getMessage("CALL_SERVICE", "OFF").trim().toUpperCase();
            if ("ON".equals(callService)) {
                logger.info("Start call API adjustAccountPoint ");
                Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_CHANGE_LOYALTY);
                if (ws == null) {
                    logger.info("Loi cau hinh url API ");
                    return error;
//                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
                }
                logger.error("Time start" + new Date());
                RequestDto rq = new RequestDto();
                rq.setIsdn(isdn);
                rq.setPointAmount(point);
                rq.setPointId(pointId);
                rq.setTransTypeId(transTypeId);
                String rqStr = CommonUtil.convertObjectToJsonString(rq);
                logger.info("Requesst send adjustAccountPoint: " + rqStr);
                BaseResponsesDto response = WebServiceUtil.callApiRest(ws.getUrl(), rqStr);
                if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    JSONObject json = new JSONObject(response.getMessageCode());
                    if ("000".equals(json.getString("code"))) {
                        error = "0";
                        return error;
                    }
                }
                return error;
            } else {
                logger.info("SERVICE CHUA DUOC MO - CALL_SERVICE = OFF ! MO SERVICE TRONG FILE CONFIG  >> PARAM CALL_SERVICE ");
                error = "0";
                return error;
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            error = "0";
            return error;
        }
    }

    /**
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkUpdateInfomation(RequestBean request, String language) {
        logger.info("Start business checkUpdateInfomation off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            BaseResponseBean bean;
            MetfoneBean metfoneBean = new MetfoneBean();
            bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "Notify content-", language);

            metfoneBean.setIsNeedUpdateInfo(UPDATED);
            if (cmpreDao.checkIsUpdatedInfoIsdn(isdn)) {
                metfoneBean.setIsNeedUpdateInfo(NOT_UPDATE_YET);
                metfoneBean.setContentMsg(responseUtil.getMessage("myMetfon.updateInfo.true." + language));
            }
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }

    }

    /**
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkIsNiceNumber(RequestBean request, String language) {
        logger.info("Start business checkIsNiceNumber off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            BaseResponseBean bean;
            MetfoneBean metfoneBean = new MetfoneBean();
            bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            metfoneBean.setStatus(0L);
            if (imDao.checkIsNiceNumber(isdn)) {
                metfoneBean.setStatus(1L);
                metfoneBean.setContentMsg(responseUtil.getMessage("myMetfon.nicesim.true." + language));
            }
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getComType
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getComType(RequestBean request, String language) {
        logger.info("Start business getComType off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getComType);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service getComType: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("isdn", isdn);
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    BaseResponseBean bean = null;
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
//                    String isRightInfor = response.getTextContent("/Envelope/Body/getComTypeResponse/return/isRightInfor");
                    String respCode = res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = res.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listcomtype")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listcomtype");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setCode(product.optString("code"));
                                        comPlaint.setCompTemplate(product.optString("comptemplate".toLowerCase()));
                                        comPlaint.setCompTypeId(product.optLong("comptypeid".toLowerCase()));
                                        comPlaint.setDescription(product.optString("description".toLowerCase()));
                                        comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                        comPlaint.setName(product.optString("name".toLowerCase()));
                                        comPlaint.setServiceType(product.optLong("servicetype".toLowerCase()));
                                        list.add(comPlaint);
                                    }
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                } else {
                                    ;
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setCode(product.optString("code".toLowerCase()));
                                    comPlaint.setCompTemplate(product.optString("comptemplate".toLowerCase()));
                                    comPlaint.setCompTypeId(product.optLong("comptypeid".toLowerCase()));
                                    comPlaint.setDescription(product.optString("description".toLowerCase()));
                                    comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                    comPlaint.setName(product.optString("name".toLowerCase()));
                                    comPlaint.setServiceType(product.optLong("servicetype".toLowerCase()));
                                    list.add(comPlaint);
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"));
//                        comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getComTypeCamID(RequestBean request, String language) {
        logger.info("Start business getComTypeCamID off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
//            Webservice ws = myvtgDao.getWebserviceByName(Constants.getComTypeCamID);
            Webservice ws = myvtgDao.getWebserviceByName(getComTypeCamID_1);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service getComTypeCamID: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("isdn", isdn);
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    BaseResponseBean bean = null;
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
//                    String isRightInfor = response.getTextContent("/Envelope/Body/getComTypeResponse/return/isRightInfor");
                    String respCode = response.getTextContent("/Envelope/Body/getComTypeCamIDResponse/return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = response.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listcomtype")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listcomtype");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setCode(product.optString("code"));
                                        comPlaint.setCompTemplate(product.optString("comptemplate".toLowerCase()));
                                        comPlaint.setCompTypeId(product.optLong("comptypeid".toLowerCase()));
                                        comPlaint.setDescription(product.optString("description".toLowerCase()));
                                        comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                        comPlaint.setName(product.optString("name".toLowerCase()));
                                        comPlaint.setServiceType(product.optLong("servicetype".toLowerCase()));
                                        list.add(comPlaint);
                                    }
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                } else {
                                    ;
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setCode(product.optString("code".toLowerCase()));
                                    comPlaint.setCompTemplate(product.optString("comptemplate".toLowerCase()));
                                    comPlaint.setCompTypeId(product.optLong("comptypeid".toLowerCase()));
                                    comPlaint.setDescription(product.optString("description".toLowerCase()));
                                    comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                    comPlaint.setName(product.optString("name".toLowerCase()));
                                    comPlaint.setServiceType(product.optLong("servicetype".toLowerCase()));
                                    list.add(comPlaint);
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getComTypeCamIDResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getComTypeCamIDResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getComTypeCamIDResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getComTypeResponse/return/errorDecription"));
//                        comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * submitComplaintMyMetfone
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean submitComplaintMyMetfone(RequestBean request, String language) {
        logger.info("Start business submitComplaintMyMetfone off MyMetfoneBusinessService");
        try {
//            if (DataUtil.isNullObject(request.getWsRequest().get("tokenId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("tokenId").toString())) {
//                logger.info("Error requesst : tokenId is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
//            }
            if (DataUtil.isNullObject(request.getWsRequest().get("compContent")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("compContent").toString())) {
                logger.info("Error request : compContent is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("compTypeId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("compTypeId").toString())) {
                logger.info("Error request : compTypeId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("complainerPhone")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("complainerPhone").toString())) {
                logger.info("Error request : complainerPhone is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("errorPhone")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("errorPhone").toString())) {
                logger.info("Error request : errorPhone is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("serviceTypeId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("serviceTypeId").toString())) {
                logger.info("Error request : serviceTypeId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.submitComplaintMyMetfone);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error request : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
//            String tokenId = request.getWsRequest().get("tokenId").toString();
            String compContent = request.getWsRequest().get("compContent").toString();
            String compTypeId = request.getWsRequest().get("compTypeId").toString();
            String image = DataUtil.isNullObject(request.getWsRequest().get("image")) ? "" : request.getWsRequest().get("image").toString();
            String acceptSourceId = DataUtil.isNullObject(request.getWsRequest().get("acceptSourceId")) ? "" : request.getWsRequest().get("acceptSourceId").toString();
            String complainerPhone = request.getWsRequest().get("complainerPhone").toString();
            String errorPhone = request.getWsRequest().get("errorPhone").toString();
            String serviceTypeId = request.getWsRequest().get("serviceTypeId").toString();
            String complainerAddress = DataUtil.isNullObject(request.getWsRequest().get("complainerAddress")) ? "" : request.getWsRequest().get("complainerAddress").toString();

            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service submitComplaintMyMetfone: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("deviceId", DataUtil.isNullObject(request.getWsRequest().get("tokenId")) ? "" : request.getWsRequest().get("tokenId").toString());
            params.put("compContent", compContent);
            params.put("compTypeId", compTypeId);
            params.put("serviceTypeId", serviceTypeId);
            params.put("complainerPhone", complainerPhone);
            params.put("errorPhone", errorPhone);
            params.put("image", image);
            params.put("acceptSourceId", acceptSourceId);
            params.put("complainerAddress", complainerAddress);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/submitComplaintMyMetfoneResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/submitComplaintMyMetfoneResponse/return/errorDecription");

                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    BaseResponseBean bean = responseUtil.responseBean(error, respStr, respStr);
                    return bean;
                } else {
                    return responseUtil.responseBean(error, res.getTextContent("/Envelope/Body/submitComplaintMyMetfoneResponse/return/errorDecription"), null);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getProcessList
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getProcessList(RequestBean request, String language) {
        logger.info("Start business getProcessList off MyMetfoneBusinessService");
        try {
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getProcessList);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service getProcessList: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
//                    String isRightInfor = response.getTextContent("/Envelope/Body/getProcessListResponse/return/isRightInfor");
                    String respCode = response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorCode");
                    BaseResponseBean bean = null;
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = response.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            //   resultProductOffer = resultProductOffer + "</result>";
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listprocess")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listprocess");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {

                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setParamId(product.optLong("paramid"));
                                        comPlaint.setParamCode(product.optString("paramcode"));
                                        list.add(comPlaint);
                                    }
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListProcess(list);
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setParamId(product.optLong("paramid"));
                                    comPlaint.setParamCode(product.optString("paramcode"));
                                    list.add(comPlaint);
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListProcess(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }

                    } else {
                        bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"));
//                        comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getProcessListCamID
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getProcessListCamID(RequestBean request, String language) {
        logger.info("Start business getProcessList off MyMetfoneBusinessService");
        try {
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getProcessList);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service getProcessList: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
//                    String isRightInfor = response.getTextContent("/Envelope/Body/getProcessListResponse/return/isRightInfor");
                    String respCode = response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorCode");
                    BaseResponseBean bean = null;
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = response.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            //   resultProductOffer = resultProductOffer + "</result>";
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listprocess")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listprocess");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {

                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setParamId(product.optLong("paramid"));
                                        if (Constants.paramIDReceived.equals(product.optLong("paramid"))) {
                                            comPlaint.setParamCode(Constants.paramCodeReceived);
                                        } else if (Constants.paramIDProcessing.equals(product.optLong("paramid"))) {
                                            comPlaint.setParamCode(Constants.paramCodeProcessing);
                                        } else if (Constants.paramIDResponded.equals(product.optLong("paramid"))) {
                                            comPlaint.setParamCode(Constants.paramCodeResponded);
                                        } else {
                                            comPlaint.setParamCode(Constants.paramCodeClosed);
                                        }

                                        list.add(comPlaint);
                                    }
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListProcess(list);
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setParamId(product.optLong("paramid"));
                                    comPlaint.setParamCode(product.optString("paramcode"));
                                    list.add(comPlaint);
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListProcess(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }

                    } else {
                        bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getProcessListResponse/return/errorDecription"));
//                        comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getComplaintHistory
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getComplaintHistory(RequestBean request, String language) {
        logger.info("Start business getComplaintHistory off MyMetfoneBusinessService");
        BaseResponseBean bean = null;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getComplaintHistory);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String complaintId = DataUtil.isNullObject(request.getWsRequest().get("complaintId")) ? "" : request.getWsRequest().get("complaintId").toString();
            String rate = DataUtil.isNullObject(request.getWsRequest().get("rate")) ? "" : request.getWsRequest().get("rate").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service getComplaintHistory: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("isdn", isdn);
            params.put("complaintId", DataUtil.isNullOrEmpty(complaintId) ? "" : Long.parseLong(complaintId));
            params.put("rate", DataUtil.isNullOrEmpty(rate) ? "" : rate);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
                    String respCode = res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = res.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            //   resultProductOffer = resultProductOffer + "</result>";
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listcomplainthistory")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listcomplainthistory");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setAcceptDate(product.optString("acceptdate".toLowerCase()));
                                        comPlaint.setCompContent(product.optString("compcontent".toLowerCase()));
                                        comPlaint.setComplainId(product.optLong("complainId".toLowerCase()));
                                        comPlaint.setPreResult(product.optString("preresult".toLowerCase()));
                                        comPlaint.setProLimitDate(product.optString("proLimitDate".toLowerCase()));
                                        comPlaint.setStatus(product.optLong("status".toLowerCase()));
                                        comPlaint.setResultContent(product.optString("resultContent".toLowerCase()));
                                        comPlaint.setStaffInfo(product.optString("staffInfo".toLowerCase()));
                                        comPlaint.setErrorPhone(product.optString("errorPhone".toLowerCase()));
                                        comPlaint.setComplainerPhone(product.optString("complainerPhone".toLowerCase()));
                                        comPlaint.setServiceTypeName(product.optString("serviceType".toLowerCase()));
                                        comPlaint.setGroupTypeName(product.optString("groupType".toLowerCase()));
                                        comPlaint.setStaffPhone(product.optString("staffPhone".toLowerCase()));
                                        comPlaint.setEndDate(product.optString("endDate".toLowerCase()));
                                        list.add(comPlaint);
                                    }
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComplaintHistory(list);
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setAcceptDate(product.optString("acceptdate".toLowerCase()));
                                    comPlaint.setCompContent(product.optString("compcontent".toLowerCase()));
                                    comPlaint.setComplainId(product.optLong("complainid".toLowerCase()));
                                    comPlaint.setPreResult(product.optString("preresult".toLowerCase()));
                                    comPlaint.setProLimitDate(product.optString("prolimitdate".toLowerCase()));
                                    comPlaint.setStatus(product.optLong("status".toLowerCase()));
                                    comPlaint.setResultContent(product.optString("resultContent".toLowerCase()));
                                    comPlaint.setStaffInfo(product.optString("staffInfo".toLowerCase()));
                                    comPlaint.setErrorPhone(product.optString("errorPhone".toLowerCase()));
                                    comPlaint.setComplainerPhone(product.optString("complainerPhone".toLowerCase()));
                                    comPlaint.setServiceTypeName(product.optString("serviceType".toLowerCase()));
                                    comPlaint.setGroupTypeName(product.optString("groupType".toLowerCase()));
                                    comPlaint.setStaffPhone(product.optString("staffPhone".toLowerCase()));
                                    comPlaint.setEndDate(product.optString("endDate".toLowerCase()));
                                    list.add(comPlaint);
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComplaintHistory(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                        comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * reopenComplain
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean reopenComplain(RequestBean request, String language) {
        logger.info("Start business reopenComplain off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("content")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("content").toString())) {
                logger.info("Error requesst : content is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("complaintId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("complaintId").toString())) {
                logger.info("Error requesst : complaintId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.reopenComplain);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String content = request.getWsRequest().get("content").toString();
            String complaintId = request.getWsRequest().get("complaintId").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service reopenComplain: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("isdn", isdn);
            params.put("content", content);
            params.put("complaintId", Long.parseLong(complaintId));
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String respCode = response.getTextContent("/Envelope/Body/reopenComplainResponse/return/errorCode");
                    BaseResponseBean bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/reopenComplainResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/reopenComplainResponse/return/errorDecription"));
//                    String isRightInfor = response.getTextContent("/Envelope/Body/reopenComplainResponse/return/isRightInfor");
//                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
//                    comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
//                    bean.setWsResponse(comPlaintMyMetfoneDTO);
                    return bean;
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * closeComplain
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean closeComplain(RequestBean request, String language) {
        logger.info("Start business closeComplain off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("complaintId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("complaintId").toString())) {
                logger.info("Error requesst : complaintId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.closeComplain);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String complaintId = request.getWsRequest().get("complaintId").toString().trim();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service closeComplain: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("isdn", isdn);
            params.put("complaintId", Long.parseLong(complaintId));
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/closeComplainResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String respCode = res.getTextContent("/Envelope/Body/closeComplainResponse/return/errorCode");
                    if ("0".equals(respCode)) {
                        apiGwDao.updateAccountNotification(isdn, complaintId);
                    }
                    BaseResponseBean bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/closeComplainResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/closeComplainResponse/return/errorDecription"));
//                    String isRightInfor = response.getTextContent("/Envelope/Body/closeComplainResponse/return/isRightInfor");
//                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
//                    comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
//                    bean.setWsResponse(comPlaintMyMetfoneDTO);
                    return bean;
                } else {
                    BaseResponseBean bean = responseUtil.responseBean(res.getTextContent("/Envelope/Body/closeComplainResponse/return/errorCode"), res.getTextContent("/Envelope/Body/closeComplainResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/closeComplainResponse/return/errorDecription"));
                    return bean;
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * rateComplain
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean rateComplaint(RequestBean request, String language) {
        logger.info("Start business rateComplaint off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("complaintId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("complaintId").toString())) {
                logger.info("Error requesst : complaintId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("rate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("rate").toString())) {
                logger.info("Error requesst : rate is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            Webservice ws = myvtgDao.getWebserviceByName(Constants.rateComplain);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString().trim();
            String complaintId = request.getWsRequest().get("complaintId").toString().trim();
            String rate = request.getWsRequest().get("rate").toString().trim();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            logger.info("Start time call service rateComplaint: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("isdn", isdn);
            params.put("complaintId", complaintId);
            params.put("rate", rate);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/rateComplainResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
//                    String isRightInfor = response.getTextContent("/Envelope/Body/rateComplainResponse/return/isRightInfor");
//                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
//                    comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
//                    bean.setWsResponse(comPlaintMyMetfoneDTO);
                    String respCode = res.getTextContent("/Envelope/Body/rateComplainResponse/return/errorCode");
                    BaseResponseBean bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/rateComplainResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/rateComplainResponse/return/errorDecription"));
                    return bean;
                } else {
                    return responseUtil.responseBean(res.getTextContent("/Envelope/Body/rateComplainResponse/return/errorCode"), res.getTextContent("/Envelope/Body/rateComplainResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/rateComplainResponse/return/errorDecription"), language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getTotalDonateCovid
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getTotalDonateCovid(RequestBean request, String language) {
        logger.info("Start business getTotalDonateCovid off MyMetfoneBusinessService");
        try {
            int exchangeRate = Integer.parseInt(configUtils.getMessage("EXCHANGE_RATE", "4000").trim());
            String currency = "";
            String currencyUS = configUtils.getMessage("CURRENCY_US", "$").trim();
            String currencyKH = configUtils.getMessage("CURRENCY_KHR", "R").trim();
            Double valueDonate = Double.parseDouble(configUtils.getMessage("VALUE_DONATE", "1000").trim());
            BaseResponseBean bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            MetfoneBean metfoneBean = new MetfoneBean();
            logger.info("Start business getTotalDonateCovid off MyMetfoneBusinessService");
            Double totalDonateMocha = promoReportAppDao.getTotalDonateMocha(valueDonate);
            logger.info("totalDonateMocha: " + totalDonateMocha);
            Double totalDonateEmoney = promoReportAppDao.getTotalDonateEmoney();
            logger.info("totalDonateEmoney: " + totalDonateEmoney);
            Double total = (totalDonateEmoney + totalDonateMocha);
            currency = currencyKH;
            //chuyen KHR==>USD
            String isExchangeUSD = configUtils.getMessage("IS_EXCHANGE_USD", "OFF").trim().toUpperCase();
            if ("ON".equals(isExchangeUSD)) {
                logger.info(" start exchange usd : Exchange rate= " + exchangeRate);
                total = total / exchangeRate;
                currency = currencyUS;
            }
            logger.info("total: " + total + currency);
            if (total >= 195398000) {
                logger.info("Vuot qua 195,398,000 ==> tra ve defaul 195,398,000  ");
                total = 195398000D;
            }
            metfoneBean.setTotalAmount(dfCurrency.format(total) + currency);
            metfoneBean.setLastUpdate(new Date());
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getDonateEmoney
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getDonateEmoney(RequestBean request, String language) {
        logger.info("Start business getDonateEmoney off MyMetfoneBusinessService");
        try {
            int maxResult = Integer.parseInt(configUtils.getMessage("MAX_RESULT_DONATE", "4").trim());
            String currency = configUtils.getMessage("CURRENCY_KHR", "R").toUpperCase().trim();
            BaseResponseBean bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            MetfoneBean metfoneBean = new MetfoneBean();
            List objs = promoReportAppDao.getDonateEmoney(maxResult, currency);
            metfoneBean.setLisDonateEmoney(objs);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getDonateMocha
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getDonateMocha(RequestBean request, String language) {
        logger.info("Start business getDonateMocha off MyMetfoneBusinessService");
        try {
            int maxResult = Integer.parseInt(configUtils.getMessage("MAX_RESULT_DONATE", "4").trim());
            String currency = configUtils.getMessage("CURRENCY_KHR", "R").toUpperCase().trim();
            BaseResponseBean bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            MetfoneBean metfoneBean = new MetfoneBean();
            List objs = promoReportAppDao.getDonateMocha(maxResult, currency);
            metfoneBean.setLisDonateMocha(objs);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * wsGetCategoryNews
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean wsGetCategoryNews(RequestBean request, String language) {
        logger.info("Start business wsGetCategoryNews off MyMetfoneBusinessService");
        try {
            BaseResponseBean bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            String pathImg = configUtils.getMessage("PATH_IMG", "").trim();
            MetfoneBean metfoneBean = new MetfoneBean();
            //lay danhmuc tin tuc

            List<Category> listCategory = dao.getCategory(null, null, 1L);
            List cateDetail = new ArrayList();
            if (DataUtil.isNullOrEmpty(listCategory)) {
                metfoneBean.setCategory(cateDetail);
            } else {
                //xu ly lay chitiet cua tung danh muc
                listCategory.stream().map((category) -> {
                    BaseGroupDTO dto = new BaseGroupDTO();
                    dto.setGroupId(category.getId().toString());
                    dto.setGroupName(category.getName());
                    List objs = dao.getCategoryDetail(language, category.getId(), pathImg);
                    dto.setListItem(DataUtil.isNullOrEmpty(objs) ? new ArrayList<>() : objs);
                    return dto;
                }).forEachOrdered((dto) -> {
                    cateDetail.add(dto);
                });
                metfoneBean.setCategory(cateDetail);
            }

            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }

    }

    /**
     * getCaptcha
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getCaptcha(RequestBean request, String language) {
        logger.info("Start business getCaptcha off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullOrEmpty(request.getUsername())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.Ishare.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("programCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("programCode").toString())) {
                logger.info("Error request : programCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.service.failed.", language);
            }
            String programCode = request.getWsRequest().get("programCode").toString();
            String isdn = request.getUsername();
            isdn = DataUtil.fomatIsdn(isdn);
            String captchaCode = DataUtil.rand(111, 999999);
            int expired = 0;
            expired = Integer.parseInt(configUtils.getMessage("EXPRIED_CAPTCHA", "180").trim());
            Captcha captcha = captcha = dao.getCaptcha(isdn, programCode);
            Date dateCurr = new Date();
            if (!DataUtil.isNullObject(captcha)) {
                captcha.setCreateDate(dateCurr);
                captcha.setExpiredTime(DataUtil.addSecond(dateCurr, expired));
                captcha.setStatus(0L);
                captcha.setUpdateDate(null);
                captcha.setCaptchaCode(captchaCode);
                dao.update(captcha);
//                result = dao.updatCaptcha(isdn, captchaCode, expired, true, programCode);
            } else {
                captcha = new Captcha();
                captcha.setCreateDate(dateCurr);
                captcha.setExpiredTime(DataUtil.addSecond(dateCurr, expired));
                captcha.setStatus(0L);
                captcha.setIsdn(isdn);
                captcha.setProgramCode(programCode);
                captcha.setCaptchaCode(captchaCode);
                captcha.setTotalError(0L);
                dao.insert(captcha);
                //result = dao.insertCaptcha(isdn, captchaCode, expired, programCode);
            }
            BaseResponseBean bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setCaptchaCode(captchaCode);
            metfoneBean.setTimeOut(String.valueOf(expired));
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * wsPayAdvance
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean wsPayAdvance(RequestBean request, String language) {
        logger.info("Start business wsPayAdvance off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getUsername())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("dataType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("dataType").toString())) {
                logger.info("Error requesst : dataType is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("data")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("data").toString())) {
                logger.info("Error requesst : data is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("acc")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("acc").toString())) {
                logger.info("Error requesst : des is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("type")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("type").toString())) {
                logger.info("Error requesst : type is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            String acc = request.getWsRequest().get("acc").toString().trim();
            String type = request.getWsRequest().get("type").toString().trim();
            String dataType = request.getWsRequest().get("dataType").toString().trim().toUpperCase();
            String isdn = "";
            if ("1".equals(type)) {
                isdn = DataUtil.fomatIsdn855(request.getUsername());
            } else {
                isdn = DataUtil.fomatIsdn855(acc);
            }
            String callPro = configUtils.getMessage("CALL_PRO");
            Integer expriedDay = 0;
            String balance = "";
            String value = request.getWsRequest().get("data").toString().trim();
            if (!"VALIDITY".equals(dataType)) {
                balance = configUtils.getMessage(dataType).trim();
                expriedDay = Integer.parseInt(configUtils.getMessage("EXPRIED_DATE_" + dataType, "0").trim());
            }
            String error = "";
            Date currDate = new Date();
            if ("ON".equals(callPro)) {
                Exchange exchange = new Exchange();
                ExchMsg responseCall = null;
                responseCall = WebServiceUtil.callProExchange(exchange, isdn, value, expriedDay, cmpreDao, dataType, balance);
                if (DataUtil.isNullObject(responseCall)) {
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                }
                error = responseCall.getError();
                logger.info("Kong call len tong dai ");
                error = "0";
            } else {
                logger.info("Tong dai dang tat- cau hinh trong file config ");
                error = "0";
            }
            String content = "";
            if (DataUtil.isNullOrEmpty(error) || !"0".equals(error)) {
                return responseUtil.responseBean(Constants.NOT_SUCCESS, DES_FAIL, "mymetfone.game.lucky.tele.trans.failed.", language);
            }
            //TODO: xu ly nghiep vu
            //nhan tin
            return responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", content);
        } catch (Exception e) {
            logger.info("Exception" + e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * checkAddMemberSabay
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkAddMemberSabay(RequestBean request, String language) {
        logger.info("Start business checkAddMemberSabay off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnMem")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnMem").toString())) {
                logger.info("Error requesst : isdnMem is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String[] isdnMem = request.getWsRequest().get("isdnMem").toString().trim().split(",");
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.checkAddMemberSabay);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service checkAddMemberSabay " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            //xử lý loop
            int count = 1;
            List resultProcess = new ArrayList();
            for (String str : isdnMem) {
                DetailResultDTO dto = new DetailResultDTO();
                String strFm = DataUtil.fomatIsdn(str);
                LinkedHashMap<String, Object> params = new LinkedHashMap<>();
                setRequestTickToxOrSabay(params, isdn, strFm, isdn, lang, ws.getUsername(), ws.getPassword());
                logger.info("Start time call service checkAddMemberSabay step " + count + " :" + dateFormat.format(new Date()));
                SoapWSResponse res = wsUtil.callWebService(params, true);
                logger.info("End time call service checkAddMemberSabay step " + count + " :" + dateFormat.format(new Date()));
                if (res == null) {
                    logger.info("Error system call service checkAddMemberSabay step " + count + " :" + dateFormat.format(new Date()));
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                } else {
                    String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                    if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                        String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                        SoapWSResponse rs = new SoapWSResponse(respStr);
                        if (DataUtil.isNullObject(rs)) {
                            logger.info("parse soap  error ");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                        }
                        String desPath = "/Envelope/Body/processResponse/return/errOcs";
                        String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                        ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, null, null);
                        dto.setIsdn(str);
                        logger.info("checkAddMemberSabay step " + count + " :" + str + ". ErrorCode : " + resultCall.getErrorCode());
                        if ("0".equals(resultCall.getErrorCode())) {
                            logger.info("checkAddMemberSabay success step " + count + " :" + str);
                            dto.setStatus(Constants.ERROR_CODE_0);
                            dto.setDesStatus(responseUtil.getMessage(DES_SUCC + language));
                        } else {
                            logger.info("checkAddMemberSabay error step " + count + " :" + str);
                            dto.setStatus(Constants.ERROR_PARAMETER_INVALID);
                            dto.setDesStatus(responseUtil.getMessage("myMetfone.checkAddMemberSabay.ERR." + resultCall.getErrorCode() + "." + language, responseUtil.getMessage("myMetfone.failed." + language)));
                        }
                    } else {
                        logger.info("call soap  error ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                }
                resultProcess.add(dto);
                count++;
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setListMember(resultProcess);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getListMemberSabay
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getListMemberSabay(RequestBean request, String language) {
        logger.info("Start business getListMemberSabay off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getListMemberSabay);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service getListMemberSabay " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            setRequestTickToxOrSabay(params, isdn, isdn, isdn, lang, ws.getUsername(), ws.getPassword());
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                //	return;
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse rs = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(rs)) {
                        logger.info("parse soap  error ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String desPath = "/Envelope/Body/processResponse/return/errOcs";
                    String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                    ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, "listMember", null);
                    MetfoneBean metfoneBean = new MetfoneBean();
                    String content = myvtgService.getAppParamByTypAndName(Constants.POLYCY_SABAY);
                    metfoneBean.setPolicy(content);
                    metfoneBean.setListMember(DataUtil.isNullOrEmpty(resultCall.getListItem()) ? new ArrayList<>() : resultCall.getListItem());
                    bean.setWsResponse(metfoneBean);
                    return bean;
                } else {
                    logger.info("call soap  error ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * addMemberSayBay
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean addMemberSayBay(RequestBean request, String language) {
        logger.info("Start business addMemberSayBay off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnMem")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnMem").toString())) {
                logger.info("Error requesst : isdnMem is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String[] isdnMem = request.getWsRequest().get("isdnMem").toString().trim().split(",");
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.addMemberSabay);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service addMemberSayBay " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            //xử lý loop
            int count = 1;
            int countSucc = 0;
            int countErr = 0;
            int countNoProcess = 0;
            String msgErr = "";
            List resultProcess = new ArrayList();
            for (String isdnStr : isdnMem) {
                DetailResultDTO dto = new DetailResultDTO();
                String replace = "CNTT@@##";
                isdnStr = isdnStr.replace("{member}", replace);
                String[] isdnArr = isdnStr.split(replace);

                if (isdnArr.length == 0) {
                    continue;
                }
                String isdnFm = DataUtil.fomatIsdn(isdnArr[0]);
                String name = "";
                if (isdnArr.length > 1) {
                    name = isdnArr[1];
                }
                if (count > 0) {
                    String isdnRq = isdnFm + replace + name;
                    LinkedHashMap<String, Object> params = new LinkedHashMap<>();
                    setRequestTickToxOrSabay(params, isdn, isdnRq, isdn, lang, ws.getUsername(), ws.getPassword());
                    logger.info("Start time call service addMemberSayBay step " + count + " :" + dateFormat.format(new Date()));
                    SoapWSResponse res = wsUtil.callWebService(params, true);
                    logger.info("End time call service addMemberSayBay step " + count + " :" + dateFormat.format(new Date()));
                    if (res == null) {
                        logger.info("Error system call service addMemberSayBay step " + count + " :" + dateFormat.format(new Date()));
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    } else {
                        String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                        if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                            String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                            SoapWSResponse rs = new SoapWSResponse(respStr);
                            if (DataUtil.isNullObject(rs)) {
                                logger.info("parse soap  error ");
                                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                            }
                            String desPath = "/Envelope/Body/processResponse/return/errOcs";
                            String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                            ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, null, null);
                            dto.setIsdn(isdnFm);
                            logger.info("addMemberSayBay step " + count + " :" + isdnFm + ". ErrorCode : " + resultCall.getErrorCode());
                            if ("0".equals(resultCall.getErrorCode())) {
                                countSucc = count;
                                logger.info("addMemberSayBay success step " + countSucc + " :" + isdnFm);
                                dto.setStatus(Constants.ERROR_CODE_0);
                                dto.setDesStatus(responseUtil.getMessage(DES_SUCC + language));
                                count++;
                            } else {
                                countErr++;
                                logger.info("addMemberSayBay error step " + countErr + " :" + isdnFm);
                                dto.setStatus(Constants.ERROR_PARAMETER_INVALID);
                                msgErr = responseUtil.getMessage("myMetfone.addMemberSayBay.ERR." + resultCall.getErrorCode() + "." + language, responseUtil.getMessage("myMetfone.failed." + language)).replace("{1}", isdnFm).replace("{2}", isdn);
                                dto.setDesStatus(msgErr);
                                count = 0;
                            }
                        } else {
                            logger.info("call soap  error ");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                        }
                    }
                } else {
                    countNoProcess++;
                    logger.info(" No addMemberSayBay step " + countNoProcess + " :" + isdnFm);
                    dto.setIsdn(isdnFm);
                    dto.setDesStatus("Chua xy ly do da co 1 ban ghi loi truoc do");
                    dto.setStatus(Constants.NOT_FOUND_DATA);
                }
                resultProcess.add(dto);
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setListMember(resultProcess);
            String msg = responseUtil.getMessage("myMetfone.addTickTox.sabay.msg." + language);
//            if (countSucc > 0) {
            String msg1 = String.format(responseUtil.getMessage("myMetfone.addTickTox.sabay.msg1." + language), String.valueOf(countSucc + "/" + isdnMem.length));
            msg += "\n" + msg1;
//            }
            if (countErr > 0) {
                String msg2 = String.format(responseUtil.getMessage("myMetfone.addTickTox.sabay.msg2." + language), msgErr);
                msg += "\n" + msg2;
            }
//            if (countNoProcess > 0) {
//                String msg3 = String.format(responseUtil.getMessage("myMetfone.addTickTox.sabay.msg3." + language), String.valueOf(countNoProcess));
//                msg += "\n" + msg3;
//            }
            bean.setUserMsg(msg);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * removeMemberSabay
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean removeMemberSabay(RequestBean request, String language) {
        logger.info("Start business removeMemberSabay off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnMem")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnMem").toString())) {
                logger.info("Error requesst : isdnMem is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String isdnMem = DataUtil.fomatIsdn(request.getWsRequest().get("isdnMem").toString().trim());
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.removeMemberSabay);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service removeMemberSabay " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            setRequestTickToxOrSabay(params, isdn, isdnMem, isdn, lang, ws.getUsername(), ws.getPassword());
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                logger.info("Error system call service removeMemberSabay " + dateFormat.format(new Date()));
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse rs = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(rs)) {
                        logger.info("parse soap  error ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String desPath = "/Envelope/Body/processResponse/return/errOcs";
                    String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                    ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, null, null);
                    if (!"0".equals(resultCall.getErrorCode())) {
                        bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                        bean.setMessage(responseUtil.getMessage(DES_FAIL + language));
                        bean.setUserMsg(responseUtil.getMessage("myMetfone.removeMemberProcess.ERR." + resultCall.getErrorCode() + "." + language, responseUtil.getMessage("myMetfone.failed." + language)).replace("{2}", isdn));
                    }
                } else {
                    logger.info("call soap  error ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * checkAddMemberTickTox
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkAddMemberTiktok(RequestBean request, String language) {
        logger.info("Start business checkAddMemberTiktok off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnMem")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnMem").toString())) {
                logger.info("Error requesst : isdnMem is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String[] isdnMem = request.getWsRequest().get("isdnMem").toString().trim().split(",");
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.checkAddMemberTickTox);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service checkAddMemberTiktok " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            //xử lý loop
            int count = 1;
            List resultProcess = new ArrayList();
            for (String str : isdnMem) {
                DetailResultDTO dto = new DetailResultDTO();
                String strFm = DataUtil.fomatIsdn(str);
                LinkedHashMap<String, Object> params = new LinkedHashMap<>();
                setRequestTickToxOrSabay(params, isdn, strFm, isdn, lang, ws.getUsername(), ws.getPassword());
                logger.info("Start time call service checkAddMemberTiktok step " + count + " :" + dateFormat.format(new Date()));
                SoapWSResponse res = wsUtil.callWebService(params, true);
                logger.info("End time call service checkAddMemberTiktok step " + count + " :" + dateFormat.format(new Date()));
                if (res == null) {
                    logger.info("Error system call service checkAddMemberTiktok step " + count + " :" + dateFormat.format(new Date()));
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                } else {
                    String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                    if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                        String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                        SoapWSResponse rs = new SoapWSResponse(respStr);
                        if (DataUtil.isNullObject(rs)) {
                            logger.info("parse soap  error ");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                        }

                        String desPath = "/Envelope/Body/processResponse/return/errOcs";
                        String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                        ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, null, null);
                        dto.setIsdn(str);
                        logger.info("checkAddMemberTiktok step " + count + " :" + str + ". ErrorCode : " + resultCall.getErrorCode());
                        if ("0".equals(resultCall.getErrorCode())) {
                            logger.info("checkAddMemberTiktok success step " + count + " :" + str);
                            dto.setStatus(Constants.ERROR_CODE_0);
                            dto.setDesStatus(responseUtil.getMessage(DES_SUCC + language));
                        } else {
                            logger.info("checkAddMemberTiktok error step " + count + " :" + str);
                            dto.setStatus(Constants.ERROR_PARAMETER_INVALID);
                            dto.setDesStatus(responseUtil.getMessage("myMetfone.checkAddMemberTickTox.ERR." + resultCall.getErrorCode() + "." + language, responseUtil.getMessage("myMetfone.failed." + language)));
                        }
                    } else {
                        logger.info("call soap  error ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                }
                resultProcess.add(dto);
                count++;
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setListMember(resultProcess);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getListMemberTickTox
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getListMemberTiktok(RequestBean request, String language) {
        logger.info("Start business getListMemberTiktok off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getListMemberTickTox);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service getListMemberTiktok " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            setRequestTickToxOrSabay(params, isdn, isdn, isdn, lang, ws.getUsername(), ws.getPassword());
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                //	return;
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse rs = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(rs)) {
                        logger.info("parse soap  error ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String desPath = "/Envelope/Body/processResponse/return/errOcs";
                    String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                    ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, "listMember", null);
                    MetfoneBean metfoneBean = new MetfoneBean();
                    String content = myvtgService.getAppParamByTypAndName(Constants.POLYCY_TIKTOK);
                    metfoneBean.setPolicy(content);
                    metfoneBean.setListMember(DataUtil.isNullOrEmpty(resultCall.getListItem()) ? new ArrayList<>() : resultCall.getListItem());
                    bean.setWsResponse(metfoneBean);
                    return bean;
                } else {
                    logger.info("call soap  error ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * addMemberTickTox
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean addMemberTiktok(RequestBean request, String language) {
        logger.info("Start business addMemberTiktok off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnMem")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnMem").toString())) {
                logger.info("Error requesst : isdnMem is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String[] isdnMem = request.getWsRequest().get("isdnMem").toString().trim().split(",");
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.addMemberTickTox);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service addMemberTiktok " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            //xử lý loop
            int count = 1;
            int countSucc = 0;
            int countErr = 0;
            int countNoProcess = 0;
            List resultProcess = new ArrayList();
            String msgErr = "";
            for (String isdnStr : isdnMem) {
                DetailResultDTO dto = new DetailResultDTO();
                String replace = "CNTT@@##";
                isdnStr = isdnStr.replace("{member}", replace);
                String[] isdnArr = isdnStr.split(replace);
                if (isdnArr.length <= 0) {
                    continue;
                }
                String isdnFm = DataUtil.fomatIsdn(isdnArr[0]);
                String name = "";
                if (isdnArr.length > 1) {
                    name = isdnArr[1];
                }
                if (count > 0) {

                    String isdnRq = isdnFm + replace + name;
                    LinkedHashMap<String, Object> params = new LinkedHashMap<>();
                    setRequestTickToxOrSabay(params, isdn, isdnRq, isdn, lang, ws.getUsername(), ws.getPassword());
                    logger.info("Start time call service addMemberTiktok step " + count + " :" + dateFormat.format(new Date()));
                    SoapWSResponse res = wsUtil.callWebService(params, true);
                    logger.info("End time call service addMemberTiktok step " + count + " :" + dateFormat.format(new Date()));
                    if (res == null) {
                        logger.info("Error system call service addMemberTiktok step " + count + " :" + dateFormat.format(new Date()));
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    } else {
                        String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                        if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                            String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                            SoapWSResponse rs = new SoapWSResponse(respStr);
                            if (DataUtil.isNullObject(rs)) {
                                logger.info("parse soap  error ");
                                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                            }
                            String desPath = "/Envelope/Body/processResponse/return/errOcs";
                            String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                            ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, null, null);
                            dto.setIsdn(isdnFm);
                            logger.info("addMemberTiktok step " + count + " :" + isdnFm + ". ErrorCode : " + resultCall.getErrorCode());
                            if ("0".equals(resultCall.getErrorCode())) {
                                countSucc = count;
                                logger.info("addMemberTiktok success step " + countSucc + " :" + isdnFm);
                                dto.setStatus(Constants.ERROR_CODE_0);
                                dto.setDesStatus(responseUtil.getMessage(DES_SUCC + language));
                                count++;
                            } else {
                                countErr++;
                                logger.info("addMemberTiktok error step " + countErr + " :" + isdnFm);
                                dto.setStatus(Constants.ERROR_PARAMETER_INVALID);
                                msgErr = responseUtil.getMessage("myMetfone.addMemberTickTox.ERR." + resultCall.getErrorCode() + "." + language, responseUtil.getMessage("myMetfone.failed." + language)).replace("{1}", isdnFm).replace("{2}", isdn);
                                dto.setDesStatus(msgErr);
                                count = 0;
                            }

                        } else {
                            logger.info("call soap  error ");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                        }
                    }
                } else {
                    countNoProcess++;
                    logger.info(" No addMemberTiktok  step " + countNoProcess + " :" + isdnFm);
                    dto.setIsdn(isdnFm);
                    dto.setDesStatus("Chua xy ly do da co 1 ban ghi loi truoc do");
                    dto.setStatus(Constants.NOT_FOUND_DATA);
                }
                resultProcess.add(dto);
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setListMember(resultProcess);
            String msg = responseUtil.getMessage("myMetfone.addTickTox.sabay.msg." + language);
            String msg1 = String.format(responseUtil.getMessage("myMetfone.addTickTox.sabay.msg1." + language), String.valueOf(countSucc + "/" + isdnMem.length));
            msg += "\n" + msg1;
//            }
            if (countErr > 0) {
                String msg2 = String.format(responseUtil.getMessage("myMetfone.addTickTox.sabay.msg2." + language), msgErr);
                msg += "\n" + msg2;
            }

//            if (countNoProcess > 0) {
//                String msg3 = String.format(responseUtil.getMessage("myMetfone.addTickTox.sabay.msg3." + language), String.valueOf(countNoProcess));
//                msg += "\n" + msg3;
//            }
            bean.setUserMsg(msg);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * removeMemberSabayTickTox
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean removeMemberSabayTiktok(RequestBean request, String language) {
        logger.info("Start business removeMemberSabayTiktok off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnMem")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnMem").toString())) {
                logger.info("Error requesst : isdnMem is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String isdnMem = DataUtil.fomatIsdn(request.getWsRequest().get("isdnMem").toString().trim());
            Long lang = Constants.english; //default english.
            if ("km".equals(language)) {
                lang = Constants.khmer;
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.removeMemberTickTox);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            logger.info("Start time call service removeMemberSabayTiktok " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            setRequestTickToxOrSabay(params, isdn, isdnMem, isdn, lang, ws.getUsername(), ws.getPassword());
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                logger.info("Error system call service removeMemberSabayTiktok " + dateFormat.format(new Date()));
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse rs = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(rs)) {
                        logger.info("parse soap  error ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String desPath = "/Envelope/Body/processResponse/return/errOcs";
                    String errCodePath = "/Envelope/Body/processResponse/return/errCode";
                    ResponseDto resultCall = getResponseDetail(rs, errCodePath, desPath, null, null);
                    if (!"0".equals(resultCall.getErrorCode())) {
                        bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                        bean.setMessage(responseUtil.getMessage(DES_FAIL + language));
                        bean.setUserMsg(responseUtil.getMessage("myMetfone.removeMemberProcess.ERR." + resultCall.getErrorCode() + "." + language, responseUtil.getMessage("myMetfone.failed." + language)).replace("{2}", isdn));
                    }
                } else {
                    logger.info("call soap  error ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getConsultant(RequestBean request, String language) {
        logger.info("Start business getConsultant off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getConsultant);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            logger.info("Start time call service getConsultant: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("isdn", isdn);
            SoapWSResponse res = wsUtil.callWebService(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(response)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                    List<ServiceGroupDTO> serviceGroupList = new ArrayList<>();

                    List<ConsultantDTO> services = new ArrayList<>();
                    ServiceGroupDTO serviceGroupDTO = new ServiceGroupDTO();
                    serviceGroupDTO.setServices(services);
                    serviceGroupList.add(serviceGroupDTO);
                    bean.setWsResponse(serviceGroupList);
                    String errCodePath = "/Envelope/Body/getConsultantResponse/return/errorCode";
                    String desPath = "/Envelope/Body/getConsultantResponse/return/description";
                    ResponseDto resultCall = getResponseDetail(response, errCodePath, desPath, "consultants", "Consultant");
                    String productCodes = "";
                    if (!DataUtil.isNullObject(resultCall) && !DataUtil.isNullOrEmpty(resultCall.getLstResult())) {
                        productCodes = resultCall.getLstResult().stream().filter((dto) -> (!DataUtil.isNullOrEmpty(dto.getProductCode()))).map((dto) -> dto.getProductCode() + ",").reduce(productCodes, String::concat);
                    }
                    logger.info("productCodes: " + productCodes);
                    if (!DataUtil.isNullOrEmpty(productCodes)) {
                        logger.info("start get getAllConsultant by productCode : " + productCodes);
                        services = dao.getAllConsultant(language, isdn, productCodes.substring(0, productCodes.length() - 1));
                        if (!DataUtil.isNullOrEmpty(services)) {
                            serviceGroupList = new ArrayList<>();
                            for (ConsultantDTO service : services) {
                                ServiceGroupDTO group = getGroupByCode(serviceGroupList, service.getGroupCode());
                                if (group == null) {
                                    group = new ServiceGroupDTO(service.getGroupName(), service.getGroupCode(), service);
                                    serviceGroupList.add(group);
                                } else {
                                    group.getServices().add(service);
                                }
                            }
                            if (!CommonUtil.isEmpty(serviceGroupList)) {
                                bean.setWsResponse(serviceGroupList);
                            }
                        }
                    }
                    return bean;
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getGroupByCode
     *
     * @param gList
     * @param code
     * @return
     */
    private ServiceGroupDTO getGroupByCode(List<ServiceGroupDTO> gList, String code) {
        for (ServiceGroupDTO g : gList) {
            if (g != null && g.getGroupCode() != null && g.getGroupCode().equalsIgnoreCase(code)) {
                return g;
            }
        }
        return null;
    }

    /**
     * setRequestTickToxOrSabay
     *
     * @param params
     * @param misdn
     * @param param
     * @param isdn
     * @param language
     * @param user
     * @param pass
     */
    private void setRequestTickToxOrSabay(LinkedHashMap<String, Object> params, String misdn, String param, String agent, Long language, String user, String pass) {
        Long requestId = Long.parseLong(DataUtil.rand(111, 999999999));
        params.put("username", user);
        params.put("password", pass);
        params.put("requestId", requestId);
        params.put("msisdn", misdn);
        params.put("command", Constants.command);
        params.put("param", param);
        params.put("agent", agent);
        params.put("action", Constants.action);
        params.put("sendSms", Constants.sendSMS);
    }

    /**
     * getResponseDetail
     *
     * @param rs
     * @param dto
     * @param pathResErrCode
     * @param pathDes
     * @param parseList
     * @return
     */
    private ResponseDto getResponseDetail(SoapWSResponse rs, String pathErrCode, String pathDes, String parseParent, String parseListChild) throws Exception {
        String des = rs.getTextContent(pathDes);
        String errCode = rs.getTextContent(pathErrCode);
        ResponseDto dto = new ResponseDto();
        dto.setErrorCode(errCode);
        dto.setDescription(des);
        if (Constants.ERROR_CODE_0.equals(errCode)
                || Constants.ERROR_CODE_00.equals(errCode)
                || Constants.ERROR_CODE_000.equals(errCode)) {
            Object dataResponse = null;
            String xmlResponse = rs.getResponse();
            if (!StringUtils.isEmpty(xmlResponse)) {
                logger.info(" xmlString:==================> " + xmlResponse);
                Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                String result = "";
                for (Element e : doc.select(RETURN_TAG)) {
                    result = result + e.toString();
                }
                JSONObject data = XML.toJSONObject(result);

                if (!DataUtil.isNullOrEmpty(parseParent) && !DataUtil.isNullOrEmpty(parseListChild)) {
                    if (data.getJSONObject(RETURN_TAG).has(parseParent.toLowerCase())
                            && data.getJSONObject(RETURN_TAG).getJSONObject(parseParent.toLowerCase()).has(parseListChild.toLowerCase())) {
                        dataResponse = data.getJSONObject(RETURN_TAG).getJSONObject(parseParent.toLowerCase()).get(parseListChild.toLowerCase());
                    }
                } else if (!DataUtil.isNullOrEmpty(parseParent) && DataUtil.isNullOrEmpty(parseListChild)) {
                    if (data.getJSONObject(RETURN_TAG).has(parseParent.toLowerCase())) {
                        dataResponse = data.getJSONObject(RETURN_TAG).get(parseParent.toLowerCase());
                    }
                }
                if (!DataUtil.isNullObject(dataResponse)) {
                    logger.info("dataObject" + dataResponse);
                    List<DetailResultDTO> listDetail = new ArrayList<>();
                    List<Object> listObj = new ArrayList<>();
                    if (dataResponse instanceof JSONArray) {
                        JSONArray objArr = (JSONArray) dataResponse;
                        int count = 0;
                        for (Object obj : objArr) {
                            count++;
                            JSONObject product = (JSONObject) obj;
                            logger.info("parse result to obj step " + count + ": " + product.toString());
                            DetailResultDTO detailDTO = parseObjRes(product);
                            if (!DataUtil.isNullObject(detailDTO)) {
                                listDetail.add(detailDTO);
                                listObj.add(detailDTO);
                            }
                        }
                    } else {
                        JSONObject product = (JSONObject) dataResponse;
                        DetailResultDTO detailDTO = parseObjRes(product);
                        if (!DataUtil.isNullObject(detailDTO)) {
                            listDetail.add(detailDTO);
                            listObj.add(detailDTO);
                        }
                    }
                    if (!DataUtil.isNullOrEmpty(listDetail) && !DataUtil.isNullOrEmpty(listObj)) {
                        dto.setListItem(listObj);
                        dto.setLstResult(listDetail);
                    }
                }
            }
        }
        return dto;
    }

    /**
     * parseObjRes
     *
     * @param product
     * @return
     * @throws Exception
     */
    private DetailResultDTO parseObjRes(JSONObject product) throws Exception {
        logger.info("Start parse : ");
        DetailResultDTO dto = new DetailResultDTO();
        //getConsultant
        dto.setProductCode(DataUtil.isNullOrEmpty(product.optString("productCode".toLowerCase())) ? null : product.optString("productCode".toLowerCase()));
//        dto.setProductName(DataUtil.isNullOrEmpty(product.optString("productName".toLowerCase())) ? null : product.optString("productName".toLowerCase()));
//        dto.setAdvice(DataUtil.isNullOrEmpty(product.optString("advice".toLowerCase())) ? null : product.optString("advice".toLowerCase()));
//        dto.setProductId(DataUtil.isNullOrZero(product.optLong("productId".toLowerCase())) ? null : product.optLong("productId".toLowerCase()));
//        dto.setStatusCustomer(DataUtil.isNullOrEmpty(product.optString("statusCustomer".toLowerCase())) ? null : product.optString("statusCustomer".toLowerCase()));
//        dto.setProgramName(DataUtil.isNullOrEmpty(product.optString("programName".toLowerCase())) ? null : product.optString("programName".toLowerCase()));
//        dto.setSyntaxRegister(DataUtil.isNullOrEmpty(product.optString("syntaxRegister".toLowerCase())) ? null : product.optString("syntaxRegister".toLowerCase()));
//        dto.setTypeConsultant(DataUtil.isNullOrEmpty(product.optString("typeConsultant".toLowerCase())) ? null : product.optString("typeConsultant".toLowerCase()));
//        dto.setChannel(DataUtil.isNullOrEmpty(product.optString("channel".toLowerCase())) ? null : product.optString("channel".toLowerCase()));
        //get member Sabay-ticktox
        dto.setRegMemId(DataUtil.isNullOrZero(product.optLong("regMemId".toLowerCase())) ? null : product.optLong("regMemId".toLowerCase()));
        dto.setIsdn(DataUtil.isNullOrEmpty(product.optString("isdn".toLowerCase())) ? null : product.optString("isdn".toLowerCase()));
        dto.setInsertDate(DataUtil.isNullOrEmpty(product.optString("insertDate".toLowerCase())) ? null : product.optString("insertDate".toLowerCase()));
        dto.setName(DataUtil.isNullOrEmpty(product.optString("name".toLowerCase())) ? null : product.optString("name".toLowerCase()));
//        DetailResultDTO dto = CommonUtil.convertJsonStringToObject(product.toString(), DetailResultDTO.class);
        //SubInfoForPayment
        dto.setTransferCurrency(DataUtil.isNullObject(product.optDouble("transferCurrency".toLowerCase())) ? null : product.optDouble("transferCurrency".toLowerCase()));
        dto.setCustomerName(DataUtil.isNullOrEmpty(product.optString("customerName".toLowerCase())) ? null : product.optString("customerName".toLowerCase()));
        dto.setPaymentAmount(DataUtil.isNullObject(product.optDouble("paymentAmount".toLowerCase())) ? null : product.optDouble("paymentAmount".toLowerCase()));
        dto.setContractIdInfor(DataUtil.isNullObject(product.optString("contractidinfor".toLowerCase())) ? null : product.optString("contractidinfor".toLowerCase()));
        dto.setPaymentCycle(DataUtil.isNullObject(product.optString("paymentCycle".toLowerCase())) ? null : product.optString("paymentCycle".toLowerCase()));
        return dto;
    }

    @Override
    public BaseResponseBean getNewsCovid19List(RequestBean request, String language) {
        logger.info("Start business getNewsCovid19List of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("pageNumber")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNumber").toString())) {
                logger.info("Error request : pageNumber is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String pageNumber = request.getWsRequest().get("pageNumber").toString().trim();
            Long totalNews = myvtgService.getTotalRecordNewsCovid();
            NewsCovid19Bean covidBean = new NewsCovid19Bean();
            covidBean.setTotalNews(totalNews);
            Long fromRecords;
            Long toRecords;
            try {
                Long pageNum = Long.parseLong(pageNumber);
                fromRecords = (pageNum - 1) * 10 + 1;
                toRecords = fromRecords + 9;
            } catch (NumberFormatException e) {
                logger.info("### PageNumber is not valid", e);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            List<NewsCovidDto> covidDtoList = myvtgService.getNewsCovid19List(fromRecords, toRecords);

            covidBean.setNewsCovid19DtoList(covidDtoList);

            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(covidBean);
            bean.setUserMsg("Success");
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean addNewsCovid19(RequestBean request, String language) {
        logger.info("Start business addNewsCovid19 of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("title")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("title").toString())) {
                logger.info("Error request : title is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("message")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("message").toString())) {
                logger.info("Error request : message is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("topic")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("topic").toString())) {
                logger.info("Error request : topic is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("notificationType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationType").toString())) {
                logger.info("Error request : notificationType is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("link")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("link").toString())) {
                logger.info("Error request : link is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("time")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("time").toString())) {
                logger.info("Error request : time is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String title = request.getWsRequest().get("title").toString().trim();
            String message = request.getWsRequest().get("message").toString().trim();
            String topic = request.getWsRequest().get("topic").toString().trim();
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();
            String link = request.getWsRequest().get("link").toString().trim();
            String timeStr = request.getWsRequest().get("time").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String notificationImage = "";
            if (request.getWsRequest().get("notificationImage") != null) {
                notificationImage = request.getWsRequest().get("notificationImage").toString().trim();
            }
            //Date createDate = DataUtil.convertStringToDate(time, "dd/MM/yyyy HH:mm:ss");
            int time = 0;
            try {
                time = Integer.parseInt(timeStr);
            } catch (NumberFormatException e) {
                logger.info("### Cannot parse time", e);
            }
            NewsCovid19Entity entity = new NewsCovid19Entity();
            entity.setTitle(title);
            entity.setMessage(message);
            entity.setTopic(topic);
            entity.setNotificationType(notificationType);
            entity.setNotificationImage(notificationImage);
            entity.setLink(link);
            entity.setTime(time);
            entity.setStatus(status);
            entity.setDescription("deactive");
            if ("1".equals(status)) {
                entity.setDescription("active");
            }

            NotificationResult result = myvtgService.addNewsCovid19(entity);

            BaseResponseBean bean = new BaseResponseBean();
            if (result.getResult() == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
                bean.setObject("");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
                bean.setObject("");
            }

            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateNewsCovid19(RequestBean request, String language) {
        logger.info("Start business updateNewsCovid19 of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
                logger.info("Error request : newsCovidId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("title")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("title").toString())) {
                logger.info("Error request : title is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("message")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("message").toString())) {
                logger.info("Error request : message is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("topic")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("topic").toString())) {
                logger.info("Error request : topic is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("notificationType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationType").toString())) {
                logger.info("Error request : notificationType is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("link")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("link").toString())) {
                logger.info("Error request : link is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("time")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("time").toString())) {
                logger.info("Error request : time is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String id = request.getWsRequest().get("notificationId").toString().trim();
            String title = request.getWsRequest().get("title").toString().trim();
            String message = request.getWsRequest().get("message").toString().trim();
            String topic = request.getWsRequest().get("topic").toString().trim();
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();
            String link = request.getWsRequest().get("link").toString().trim();
            String timeStr = request.getWsRequest().get("time").toString().trim();
            String notificationImage = "";
            String status = "0";
            String description = "deactive";
            if (request.getWsRequest().get("notificationImage") != null) {
                notificationImage = request.getWsRequest().get("notificationImage").toString().trim();
            }
            if (request.getWsRequest().get("status") != null) {
                status = request.getWsRequest().get("status").toString().trim();
            }
            if (request.getWsRequest().get("description") != null) {
                description = request.getWsRequest().get("description").toString().trim();
            }

            //Date createDate = DataUtil.convertStringToDate(time, "dd/MM/yyyy HH:mm:ss");
            int time = 0;
            try {
                time = Integer.parseInt(timeStr);
            } catch (NumberFormatException e) {
                logger.info("### Cannot parse time", e);
            }

            if ("1".equals(status)) {
                description = "active";
            }

            NewsCovid19Entity entity = new NewsCovid19Entity();
            entity.setNewsCovidId(id);
            entity.setTitle(title);
            entity.setMessage(message);
            entity.setTopic(topic);
            entity.setNotificationType(notificationType);
            entity.setNotificationImage(notificationImage);
            entity.setLink(link);
            entity.setTime(time);
            entity.setStatus(status);
            entity.setDescription(description);

            int result = myvtgService.updateNewsCovid19(entity);

            BaseResponseBean bean = new BaseResponseBean();
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
                bean.setObject(entity);
            }

            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean deleteNewsCovid19(RequestBean request, String language) {
        logger.info("Start business deleteNewCovid19 of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
                logger.info("Error request : notificationId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String id = request.getWsRequest().get("notificationId").toString().trim();

            NewsCovid19Entity entity = new NewsCovid19Entity();
            entity.setNewsCovidId(id);
            entity.setStatus("2");
            entity.setDescription("deleted");

            int result = myvtgService.deleteNewsCovid19(entity);

            BaseResponseBean bean = new BaseResponseBean();
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
            }

            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getDetailNewsCovid19(RequestBean request, String language) {
        logger.info("Start business getDetailNewCovid19 of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("newsCovidId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("newsCovidId").toString())) {
                logger.info("Error request : newsCovidId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String id = request.getWsRequest().get("newsCovidId").toString().trim();

            NewsCovid19Entity entity = new NewsCovid19Entity();
            entity.setNewsCovidId(id);
            NewsCovidDto result = myvtgService.getDetailNewsCovid19(entity);
            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");

            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * checkResgisterMyMetfone
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkResgisterMyMetfone(RequestBean request, String language) {
        logger.info("Start business checkResgisterMyMetfone of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdnCheck")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnCheck").toString())) {
                logger.info("Error request : isdnCheck is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String isdnCheck = DataUtil.fomatIsdn(request.getWsRequest().get("isdnCheck").toString().trim());
            BaseResponseBean bean;
            MetfoneBean metfoneBean = new MetfoneBean();
            bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            metfoneBean.setStatus(0L);
            if (!apiGwDao.checkResgister(isdnCheck)) {
                metfoneBean.setStatus(1L);
            }
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListCodeOnFiredBaseConsole(RequestBean request, String language) {
        logger.info("Start business getListCodeOnFiredBaseConsole of MyMetfoneBusinessService");
        List<CodeFiredbaseConsole> codeFbConsoleList = myvtgService.getListCodeFiredBaseConsole();
        BaseResponseBean bean = new BaseResponseBean();
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(codeFbConsoleList);
        bean.setUserMsg("Success");
        return bean;
    }

    @Override
    public BaseResponseBean getSubInfo(RequestBean request, String language) {
        logger.info("Start business getSubInfo off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            SubMb subMBByIsdn = cmpreDao.getSubMbByIsdn(isdn, null);
            BaseResponseBean bean;
            IsdnInfoBean infoBean = new IsdnInfoBean();
            bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            bean.setWsResponse(subMBByIsdn);
            return bean;
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListProvince(String language) {
        List<ObjectDTO> provinceList = myvtgDao.getListProvince();
        if (provinceList == null) {
            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.NOT_FOUND_DATA);
            bean.setMessage("NOT FOUND DATA");
            bean.setWsResponse(provinceList);
            return bean;
        }
        BaseResponseBean bean = new BaseResponseBean();
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(provinceList);
        return bean;
    }

    @Override
    public BaseResponseBean getListDistrict(String provinceCode, String language) {
        List<ObjectDTO> districtList = myvtgDao.getListDistrict(provinceCode);
        if (districtList == null) {
            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.NOT_FOUND_DATA);
            bean.setMessage("NOT FOUND DATA");
            bean.setWsResponse(districtList);
            return bean;
        }
        BaseResponseBean bean = new BaseResponseBean();
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(districtList);
        return bean;
    }

    @Override

    public BaseResponseBean getListCommune(String provinceCode, String districtCode, String language) {
        List<ObjectDTO> precinctList = myvtgDao.getListPrecinct(provinceCode, districtCode);
        if (precinctList == null) {
            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.NOT_FOUND_DATA);
            bean.setMessage("NOT FOUND DATA");
            bean.setWsResponse(precinctList);
            return bean;
        }
        BaseResponseBean bean = new BaseResponseBean();
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(precinctList);
        return bean;
    }

    @Override
    public BaseResponseBean getPackageInfor(Long month, String language) {
        logger.info("Start business getPackageInfor  MyMetfoneBusinessService");
        try {
            BaseResponseBean bean = new BaseResponseBean();
            File file = new File(getClass().getResource("/internet_package.json").getPath());
            if (language.equals("km")) {
                file = new File(getClass().getResource("/internet_package_km.json").getPath());
            }
            ObjectMapper mapper = new ObjectMapper();
            if (file.exists()) {
                PackageInternetDTO packageInternets = mapper.readValue(file, PackageInternetDTO.class);
                bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
                if (month == null) {
                    logger.error("Can not parse speed or payAdvance or name");
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                }
                // filter package with condition
                List<PackageInforDTO> lst = new ArrayList<>();
                for (PackageInforDTO e : packageInternets.getPackages()) {
                    if (e.getPayAdvance() == month) {
                        PackageInforDTO packageInfor = new PackageInforDTO();
                        packageInfor.setId(e.getId());
                        packageInfor.setDeposit(e.getDeposit());
                        packageInfor.setInstallation(e.getInstallation());
                        packageInfor.setPrice(e.getPrice());
                        packageInfor.setName(e.getName());
                        packageInfor.setModemWifi(e.getModemWifi());
                        packageInfor.setPayAdvance(e.getPayAdvance());
                        packageInfor.setSpeed(e.getSpeed());
                        packageInfor.setSpecialPromotion(e.getSpecialPromotion());
                        lst.add(packageInfor);
                    }
                }
                bean.setWsResponse(lst);
                return bean;
            } else {
                logger.error("can not find internet_package.json");
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage(environment.getProperty(FILE_NOT_FOUND));
                return bean;
            }
        } catch (Exception e) {
            logger.error("Exception: ", e);
            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage(e.getMessage());
            return bean;
        }
    }

    @Override
    public BaseResponseBean camIdInternetRegister(RequestBean request, String language, HttpServletRequest http) {
        logger.info("Start business camIdInternetRegister  MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            CamIdInternetRegisDTO camIdInternetRegis = new CamIdInternetRegisDTO();
            if (DataUtil.isNullObject(request.getWsRequest().get("name"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("name").toString())) {
                logger.info("Error request : name is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.username.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("phone"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("phone").toString())) {
                logger.info("Error request : phone is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.phone.empty.", language);
            }
//            validate phone
            String phoneNumber = request.getWsRequest().get("phone").toString();
            if (!DataUtil.isPhoneNumber(phoneNumber)) {
                logger.info("Error request : phone is invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.phone.invalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("district"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("district").toString())) {
                logger.info("Error request : district is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.district.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("province"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("province").toString())) {
                logger.info("Error request : province is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.province.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("commune"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("commune").toString())) {
                logger.info("Error request : commune is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.commune.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("package"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("package").toString())) {
                logger.info("Error request : package is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.package.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("village"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("village").toString())) {
                logger.info("Error request : village is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.village.empty.", language);
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("type"))
                    || request.getWsRequest().get("type").toString() != "") {
                logger.info("Error request : type is null ");
                if (DataUtil.isNullObject(request.getWsRequest().get("introductionCode"))
                        || DataUtil.isNullOrEmpty(request.getWsRequest().get("introductionCode").toString())) {
                    logger.info("Error request : introductionCode is null ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.ftth.introductionCode.empty.", language);
                }
                camIdInternetRegis.setIntroductionCode(request.getWsRequest().get("introductionCode").toString());
                camIdInternetRegis.setType(request.getWsRequest().get("type").toString());
            } else {
                camIdInternetRegis.setIntroductionCode("");
                camIdInternetRegis.setType("");
            }

            camIdInternetRegis.setName(request.getWsRequest().get("name").toString());
            camIdInternetRegis.setPhone(phoneNumber);
            if (!DataUtil.isNullObject(request.getWsRequest().get("email"))) {
                camIdInternetRegis.setMail(request.getWsRequest().get("email").toString());
            }
            String address = "";

            camIdInternetRegis.setProvinceCode(request.getWsRequest().get("province").toString());

            String province = myvtgDao.getProvinceByCode(request.getWsRequest().get("province").toString());
            String district = myvtgDao.getDistrictByCode(request.getWsRequest().get("province").toString(),
                    request.getWsRequest().get("district").toString());
            String commune = myvtgDao.getCommuneByCode(request.getWsRequest().get("province").toString(),
                    request.getWsRequest().get("district").toString(),
                    request.getWsRequest().get("commune").toString());
            address = province + ", "
                    + district + ", "
                    + commune + ", "
                    + request.getWsRequest().get("village").toString();

            camIdInternetRegis.setAddress(address);
            String ipAddress = http.getHeader("X-FORWARDED-FOR");   // proxy
            if (ipAddress == null) {
                ipAddress = http.getRemoteAddr();
            }
            camIdInternetRegis.setIp(ipAddress);
            PackageInforDTO packageInfor;
            String packageDetail = "";

            Long id = Long.parseLong(request.getWsRequest().get("package").toString());
            packageInfor = this.getPackageInternetById(id);
            packageDetail = "newLineMonthly fee:" + packageInfor.getPrice()
                    + "newLinePay advance:" + packageInfor.getPayAdvance() + " months"
                    + "newLineInstalation:" + packageInfor.getInstallation()
                    + "newLineDeposit:" + packageInfor.getDeposit()
                    + "newLineModem wifi:" + packageInfor.getModemWifi();

            camIdInternetRegis.setPackageDetail(packageDetail);
            camIdInternetRegis.setSpeed(request.getWsRequest().get("speed").toString() + "Mbps");
            return ftthService.camIdInternetRegister(camIdInternetRegis, language);
        } catch (Exception e) {
            logger.info("camIdInternetRegister Exception: ", e);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage("Error : " + e.getMessage());
            return bean;
        }
    }

    @Override
    public PackageInforDTO getPackageInternetById(Long id) {
        logger.info("Start business getPackageInternetById  MyMetfoneBusinessService");
        try {
            File file = new File(getClass().getResource("/internet_package.json").getPath());
            ObjectMapper mapper = new ObjectMapper();
            if (file.exists()) {
                PackageInternetDTO packageInternets = mapper.readValue(file, PackageInternetDTO.class);
                // filter package with condition
                PackageInforDTO packageInfor = new PackageInforDTO();
                for (PackageInforDTO e : packageInternets.getPackages()) {
                    if (e.getId() == id) {
                        packageInfor.setId(e.getId());
                        packageInfor.setDeposit(e.getDeposit());
                        packageInfor.setInstallation(e.getInstallation());
                        packageInfor.setPrice(e.getPrice());
                        packageInfor.setName(e.getName());
                        packageInfor.setModemWifi(e.getModemWifi());
                        packageInfor.setPayAdvance(e.getPayAdvance());
                        packageInfor.setSpecialPromotion(e.getSpecialPromotion());
                    }
                }
                return packageInfor;
            } else {
                logger.info("getPackageInternetById: can not find internet_package.json");
                return null;
            }
        } catch (Exception e) {
            logger.info("Start business getPackageInternetById  MyMetfoneBusinessService");
            return null;
        }
    }

    //anjav
    private ResponseDto getResponseDetailPayment(SoapWSResponse rs, String pathErrCode, String pathDes, String parseParent, String parseListChild, String isdn) throws Exception {
        String des = rs.getTextContent(pathDes);
        String errCode = rs.getTextContent(pathErrCode);
        ResponseDto dto = new ResponseDto();
        dto.setErrorCode(errCode);
        dto.setDescription(des);
        if (Constants.ERROR_CODE_0.equals(errCode)
                || Constants.ERROR_CODE_00.equals(errCode)
                || Constants.ERROR_CODE_000.equals(errCode)) {
            Object dataResponse = null;
            String xmlResponse = rs.getResponse();
            if (!StringUtils.isEmpty(xmlResponse)) {
                logger.info(" xmlString:==================> " + xmlResponse);
                Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                String result = "";
                for (Element e : doc.select(RETURN_TAG)) {
                    result = result + e.toString();
                }
                JSONObject data = XML.toJSONObject(result);

                if (!DataUtil.isNullOrEmpty(parseParent) && !DataUtil.isNullOrEmpty(parseListChild)) {
                    if (data.getJSONObject(RETURN_TAG).has(parseParent.toLowerCase())
                            && data.getJSONObject(RETURN_TAG).getJSONObject(parseParent.toLowerCase()).has(parseListChild.toLowerCase())) {
                        dataResponse = data.getJSONObject(RETURN_TAG).getJSONObject(parseParent.toLowerCase()).get(parseListChild.toLowerCase());
                    }
                } else if (!DataUtil.isNullOrEmpty(parseParent) && DataUtil.isNullOrEmpty(parseListChild)) {
                    if (data.getJSONObject(RETURN_TAG).has(parseParent.toLowerCase())) {
                        dataResponse = data.getJSONObject(RETURN_TAG).get(parseParent.toLowerCase());
                    }
                }

                //===========================================================
//                Object dataPayment = null;
//                if (!DataUtil.isNullOrEmpty("paymentInforCamID")) {
//                    if (data.getJSONObject(RETURN_TAG).has("paymentInforCamID".toLowerCase())) {
//                        dataPayment = data.getJSONObject(RETURN_TAG).get("paymentInforCamID".toLowerCase());
//                    }
//                }
                //===========================================================
                if (!DataUtil.isNullObject(dataResponse)) {
                    logger.info("dataObject" + dataResponse);
                    List<DetailResultDTO> listDetail = new ArrayList<>();
                    List<Object> listObj = new ArrayList<>();
                    if (dataResponse instanceof JSONArray) {
                        JSONArray objArr = (JSONArray) dataResponse;
                        int count = 0;
                        for (Object obj : objArr) {
                            count++;
                            JSONObject product = (JSONObject) obj;
                            logger.info("parse result to obj step " + count + ": " + product.toString());
                            DetailResultDTO detailDTO = parseObjRes(product);
                            detailDTO.setPhoneNumber(isdn);
                            if (!DataUtil.isNullObject(detailDTO)) {
                                listDetail.add(detailDTO);
                                listObj.add(detailDTO);
                            }
                        }
                    } else {
                        JSONObject product = (JSONObject) dataResponse;
                        DetailResultDTO detailDTO = parseObjRes(product);
                        detailDTO.setPhoneNumber(isdn);
                        if (!DataUtil.isNullObject(detailDTO)) {
                            listDetail.add(detailDTO);
                            listObj.add(detailDTO);
                        }
                    }
                    if (!DataUtil.isNullOrEmpty(listDetail) && !DataUtil.isNullOrEmpty(listObj)) {
                        dto.setListItem(listObj);
                        dto.setLstResult(listDetail);
                    }
                }
                //=========================================================================
//                if (!DataUtil.isNullObject(dataPayment)) {
//                    logger.info("dataPayment" + dataPayment);
//                    PaymentInforCamID paymentInforCamID = new PaymentInforCamID();
//
//                    if (dataPayment instanceof JSONArray) {
//                        JSONArray objArrPayment = (JSONArray) dataPayment;
//                        int count = 0;
//                        for (Object obj : objArrPayment) {
//                            count++;
//                            JSONObject product = (JSONObject) obj;
//                            logger.info("parse result to obj step " + count + ": " + product.toString());
//                            paymentInforCamID.setBalanceCamID(DataUtil.isNullObject(product.optDouble("balanceCamID")) ? "0" : product.optString("balanceCamID".toLowerCase()));
//                            paymentInforCamID.setBlockDateCamID(DataUtil.isNullObject(product.optDouble("blockDateCamID")) ? "" : product.optString("blockDateCamID".toLowerCase()));
////                            dto.getLstResult().get(0).setTransferCurrency(Double.parseDouble(paymentInforCamID.getBalanceCamID()));
////                            dto.getLstResult().get(0).setPaymentCycle(paymentInforCamID.getBlockDateCamID());
//                        }
//                    }else {
//                        JSONObject product = (JSONObject) dataPayment;
//                        paymentInforCamID.setBalanceCamID(DataUtil.isNullObject(product.optDouble("balanceCamID")) ? "0" : product.optString("balanceCamID".toLowerCase()));
//                        paymentInforCamID.setBlockDateCamID(DataUtil.isNullObject(product.optDouble("blockDateCamID")) ? "" : product.optString("blockDateCamID".toLowerCase()));
////                        dto.getLstResult().get(0).setTransferCurrency(Double.parseDouble(paymentInforCamID.getBalanceCamID()));
////                        dto.getLstResult().get(0).setPaymentCycle(paymentInforCamID.getBlockDateCamID());
//                    }
//                }
                //======================================================================
            }
        }
        return dto;
    }

    @Override
    public BaseResponseBean getAccountInfoForPayment(RequestBean request, String language) {
        logger.info("Start business getAccountInfoForPayment off MyMetfoneBusinessService");

        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.isdn.empty1.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName("getSubInfoForPayment");//ALTEK TMP
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error request : config ws invalid ");
                return responseUtil.responseBeanCompact(Constants.ERROR_CONFIG_WS, "myMetfone.getWebserviceByName.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            logger.info("Start time call service getSubInfoForPayment: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("isdn", isdn);
            params.put("token", ws.getParams());
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                logger.info("Error request : error callWebServicePaymemnt");
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(response)) {
                        return responseUtil.responseBeanCompact(Constants.ERROR_CONFIG_WS, "myMetfone.res.failed.", language);
                    }
                    BaseResponseBean bean = new BaseResponseBean();
                    String errCodePath = "/Envelope/Body/getSubInfoForPaymentResponse/return/errorCode";
                    String errDesPath = "/Envelope/Body/getSubInfoForPaymentResponse/return/errorDecription";
                    ResponseDto resultCall = getResponseDetailPayment(response, errCodePath, errDesPath, "service", null, isdn);
                    MetfoneBean metfoneBean = new MetfoneBean();
                    metfoneBean.setListMember(DataUtil.isNullOrEmpty(resultCall.getListItem()) ? new ArrayList<>() : resultCall.getListItem());
                    if (CollectionUtils.isEmpty(metfoneBean.getListMember())) {
                        bean.setErrorCode(resultCall.getErrorCode());
                        String mess = resultCall.getDescription();
                        if (language.equals("km")) {
                            mess = configUtils.getMessage("FIND_CONTRACT_PAYMENT_NOT_FOUND");
                            mess = mess.replace("@account_id", isdn);
                        }
                        bean.setMessage(mess);
                        return bean;
                    }
                    bean.setErrorCode(Constants.ERROR_SUCCESS);
                    bean.setMessage("Success");
                    bean.setWsResponse(metfoneBean.getListMember().get(0));
                    return bean;
                } else {
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("getAccountInfoForPayment error: ", e);
            return responseUtil.responseBeanCompact(Constants.ERROR_CODE_1, "myMetfone.getAccountInfo.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getPaymentHistoryById(RequestBean request, String language) {
        logger.info("Start business getNewsCovid19List of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.isdn.empty1.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("pageSize")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageSize").toString())) {
                logger.info("Error request : pageSize is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.pageSize.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("pageNum")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNum").toString())) {
                logger.info("Error request : pageNum is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.pageNum.empty.", language);
            }

            String toDate = (DataUtil.isNullObject(request.getWsRequest().get("toDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("toDate").toString())) ? DateTimeUtils.getSysDateTime() : (request.getWsRequest().get("toDate").toString());

            //set default khi khong co data fromDate tu request
            Date tDate = DateTimeUtils.convertStringToDateTime(toDate);
            Date d = DateTimeUtils.addNumDay(tDate, -90);
            String frDate = DateTimeUtils.convertDateTimeToString(d);

            String fromDate = (DataUtil.isNullObject(request.getWsRequest().get("fromDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fromDate").toString())) ? frDate : (request.getWsRequest().get("fromDate").toString());

            Date startDate = DateTimeUtils.convertStringToDateTime(fromDate);
            Date endDate = DateTimeUtils.convertStringToDateTime(toDate);
            startDate = AppUtil.getTimeStartOfDay(startDate.getTime());
            endDate = AppUtil.getTimeEndOfDay(endDate.getTime());
            if (startDate.getTime() > endDate.getTime()) {
                logger.info("Error requesst :  Max fromDate > toDate ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "ftth.fromDate.toDate.failed.", language);
            }

            String isdn = request.getWsRequest().get("isdn").toString().trim();
            String pageSize = request.getWsRequest().get("pageSize").toString().trim();
            String pageNum = request.getWsRequest().get("pageNum").toString().trim();
            Long totalPayments = myvtgService.getTotalRecordPaymentHistory(isdn, fromDate, toDate);
            PaymentHistoryBean paymentHistoryBean = new PaymentHistoryBean();
            paymentHistoryBean.setTotalPaymentHistorys(totalPayments);

            List<PaymentHistoryDTO> phdtos = myvtgService.getPaymentHistoryListById(isdn, Integer.valueOf(pageSize), Integer.valueOf(pageNum), fromDate, toDate);

            paymentHistoryBean.setPaymentHistoryList(phdtos);

            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(paymentHistoryBean);
            return bean;
        } catch (Exception e) {
            logger.error("Get payment history error: ", e);
            return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language);
        }
    }

    @Override
    public BaseResponseBean addPaymentHistory(RequestBean request, String language) {
        logger.info("Start business addPaymentHistory of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("amount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("amount").toString())) {
                logger.info("Error request : amount is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.amount.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("paymentDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("paymentDate").toString())) {
                logger.info("Error request : paymentDate is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.paymentDate.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("tid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("tid").toString())) {
                logger.info("Error request : tid is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.tid.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("paymentAccount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("paymentAccount").toString())) {
                logger.info("Error request : paymentAccount is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.paymentAccount.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("ftthAccount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("ftthAccount").toString())) {
                logger.info("Error request : ftthAccount is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.ftthAccount.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("contractIdInfo")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("contractIdInfo").toString())) {
                logger.info("Error request : contractIdInfo is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.contractIdInfo.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean();
            PaymentHistory entity = new PaymentHistory();
            String amount = request.getWsRequest().get("amount").toString().trim();
            String bankCode = DataUtil.isNullObject(request.getWsRequest().get("bankCode")) ? "" : request.getWsRequest().get("bankCode").toString().trim();
            String paymentAccount = request.getWsRequest().get("paymentAccount").toString().trim();
            String ftthType = DataUtil.isNullObject(request.getWsRequest().get("ftthType")) ? "internet" : request.getWsRequest().get("ftthType").toString();
            String tid = request.getWsRequest().get("tid").toString().trim();
            String ftthAccount = request.getWsRequest().get("ftthAccount").toString().trim();
            String ftthName = request.getWsRequest().get("ftthName").toString();
            String status = request.getWsRequest().get("status").toString().trim();
            String idBill = DataUtil.isNullObject(request.getWsRequest().get("idBill")) ? "" : request.getWsRequest().get("idBill").toString().trim();
            String currentBalance = DataUtil.isNullObject(request.getWsRequest().get("currentBalance")) ? "" : request.getWsRequest().get("currentBalance").toString().trim();
            String paymentDate = request.getWsRequest().get("paymentDate").toString().trim();
            String contractIdInfo = DataUtil.isNullObject(request.getWsRequest().get("contractIdInfo")) ? "" : request.getWsRequest().get("contractIdInfo").toString().trim();

            Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paymentDate);
            Object paymentHistory = new Object();
            paymentHistory = myvtgService.findPaymentHistoryByTid(Long.parseLong(tid));

            if (!DataUtil.isNullObject(paymentHistory) || !DataUtil.isNullOrEmpty((Collection<?>) paymentHistory)) {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("Failed: tid already exists");
                return bean;
            }
            entity.setAmount(new BigDecimal(amount));
            entity.setBankCode(bankCode);
            entity.setCreateDate(request.getStartTime());
            entity.setCurrentBalance(new BigDecimal(currentBalance));
            entity.setFtthAccount(ftthAccount);
            entity.setFtthName(ftthName);
            entity.setFtthType(ftthType);
            entity.setIdBill(idBill);
            entity.setStatus(Long.parseLong(status));
            entity.setPaymentDate(date1);
            entity.setTid(Long.parseLong(tid));
            entity.setPaymentAccount(paymentAccount);
            entity.setContractIdInfo(contractIdInfo);

            NotificationResult result = myvtgService.addPaymentHistory(entity);
            if (result.getResult() == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Add Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("DAO Add failed");
            }
            return bean;
        } catch (Exception e) {
            logger.error("Add payment history error: ", e);
            return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language);
        }
    }

    public BaseResponseBean updatePaymentHistory(RequestBean request, String language) {
        logger.info("Start business updatePaymentHistory of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("tid")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("tid").toString())) {
                logger.info("Error request : tid is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.tId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.empty.", language);
            }

            String tid = request.getWsRequest().get("tid").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            PaymentHistory entity = new PaymentHistory();

            entity.setStatus(Long.parseLong(status));
            entity.setTid(Long.parseLong(tid));

            Object paymentHistory = new Object();
            paymentHistory = myvtgService.findPaymentHistoryByTid(entity.getTid());
            BaseResponseBean bean = new BaseResponseBean();
            if (DataUtil.isNullObject(paymentHistory) || DataUtil.isNullOrEmpty((Collection<?>) paymentHistory)) {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Failed: tid doesn't exist");
                return bean;
            }
            int result = myvtgService.updatePaymentHistory(entity);
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Update Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("DAO Update fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("Update payment history error: ", e);
            return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language);
        }
    }

    public BaseResponseBean getSummaryPackage(String language) {
        logger.info("Start get SummaryPackage ");
        try {
            List<SummaryPackageDTO> results = dao.getSummaryPackage(language);
            if (results == null) {
                return responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language);
            }
            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(results);
            return bean;
        } catch (Exception e) {
            logger.error("Get SummaryPackage error: ", e);
            return responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean filterComplaint(RequestBean request, String language) {
        logger.info("Start business filterComplaint off MyMetfoneBusinessService");
        BaseResponseBean bean = null;

        try {
            if (DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : wsRequest is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : idUser is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String local = "";
            if (DataUtil.isNullObject(request.getWsRequest().get("language")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                local = Constants.LOCAL_LANGUAGE;
            } else {
                local = request.getWsRequest().get("language").toString();
            }
            boolean validateDateNull = false;
            if ((DataUtil.isNullObject(request.getWsRequest().get("fromDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fromDate").toString()))
                    && (DataUtil.isNullObject(request.getWsRequest().get("toDate")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("toDate").toString()))) {
                validateDateNull = true;
            }
            String toDate = validateDateNull ? DateTimeUtils.getSysDateTime() : (request.getWsRequest().get("toDate").toString());

            Date tDate = DateTimeUtils.convertStringToDate(toDate);
            Date d = DateTimeUtils.addNumDay(tDate, -30);
            String frDate = DateTimeUtils.convertDateTimeToString(d);

            String fromDate = validateDateNull ? frDate : (request.getWsRequest().get("fromDate").toString());

            Date startDate = DateTimeUtils.convertStringToDate(fromDate);
            Date endDate = DateTimeUtils.convertStringToDate(toDate);
            startDate = AppUtil.getTimeStartOfDay(startDate.getTime());
            endDate = AppUtil.getTimeEndOfDay(endDate.getTime());

            if (startDate.getTime() > endDate.getTime()) {
                logger.info("Error requesst :  Max fromDate > toDate ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "ftth.fromDate.toDate.failed.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getComplaintFilterList);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Integer totalComplaint = (DataUtil.isNullObject(request.getWsRequest().get("totalComplaint"))
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("totalComplaint").toString())) ? 200
                    : Integer.parseInt(request.getWsRequest().get("totalComplaint").toString());

            String isdn = request.getWsRequest().get("isdn").toString();
            String complaintId = DataUtil.isNullObject(request.getWsRequest().get("complaintId")) ? "" : request.getWsRequest().get("complaintId").toString();
            String rate = DataUtil.isNullObject(request.getWsRequest().get("rate")) ? "" : request.getWsRequest().get("rate").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            logger.info("Start time call service filterComplaint: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("isdn", isdn);
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
            params.put("complaintId", DataUtil.isNullOrEmpty(complaintId) ? "" : Long.parseLong(complaintId));
            params.put("countComplaint", totalComplaint);

            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            // truyen pargams qua ben kia su ly
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
                    String respCode = res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = res.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            //   resultProductOffer = resultProductOffer + "</result>";
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listcomplainthistory")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listcomplainthistory");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setAcceptDate(product.optString("acceptdate".toLowerCase()));
                                        comPlaint.setCompContent(product.optString("compcontent".toLowerCase()));
                                        comPlaint.setComplainId(product.optLong("complainId".toLowerCase()));
                                        comPlaint.setPreResult(product.optString("preresult".toLowerCase()));
                                        comPlaint.setProLimitDate(product.optString("proLimitDate".toLowerCase()));
                                        comPlaint.setStatus(product.optLong("status".toLowerCase()));
                                        comPlaint.setResultContent(product.optString("resultContent".toLowerCase()));
                                        comPlaint.setStaffInfo(product.optString("staffInfo".toLowerCase()));
                                        comPlaint.setErrorPhone(product.optString("errorPhone".toLowerCase()));
                                        comPlaint.setComplainerPhone(product.optString("complainerPhone".toLowerCase()));
                                        comPlaint.setServiceTypeName(product.optString("serviceType".toLowerCase()));
                                        comPlaint.setGroupTypeName(product.optString("groupType".toLowerCase()));
                                        comPlaint.setStaffPhone(product.optString("staffPhone".toLowerCase()));
                                        comPlaint.setEndDate(product.optString("endDate".toLowerCase()));
                                        list.add(comPlaint);
                                    }
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComplaintHistory(list);
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setAcceptDate(product.optString("acceptdate".toLowerCase()));
                                    comPlaint.setCompContent(product.optString("compcontent".toLowerCase()));
                                    comPlaint.setComplainId(product.optLong("complainid".toLowerCase()));
                                    comPlaint.setPreResult(product.optString("preresult".toLowerCase()));
                                    comPlaint.setProLimitDate(product.optString("prolimitdate".toLowerCase()));
                                    comPlaint.setStatus(product.optLong("status".toLowerCase()));
                                    comPlaint.setResultContent(product.optString("resultContent".toLowerCase()));
                                    comPlaint.setStaffInfo(product.optString("staffInfo".toLowerCase()));
                                    comPlaint.setErrorPhone(product.optString("errorPhone".toLowerCase()));
                                    comPlaint.setComplainerPhone(product.optString("complainerPhone".toLowerCase()));
                                    comPlaint.setServiceTypeName(product.optString("serviceType".toLowerCase()));
                                    comPlaint.setGroupTypeName(product.optString("groupType".toLowerCase()));
                                    comPlaint.setStaffPhone(product.optString("staffPhone".toLowerCase()));
                                    comPlaint.setEndDate(product.optString("endDate".toLowerCase()));
                                    list.add(comPlaint);
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setListComplaintHistory(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                        comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListAccountFtthByIsdn(RequestBean request, String language) {
        logger.info("Start business getListAccountFtthByIsdn of MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error request : isdn is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.isdn.empty1.", language);
            }
            List<String> listIsdn = (List) request.getWsRequest().get("isdn");
            Webservice ws = myvtgDao.getWebserviceByName("getListAccountFtthByIsdn");
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())) {
                logger.info("Error request : config ws invalid ");
                return responseUtil.responseBeanCompact(Constants.ERROR_CONFIG_WS, "myMetfone.getWebserviceByName.empty.", language);
            }
            BaseResponseBean bean = new BaseResponseBean();
            List<Object> results = new ArrayList<>();
            for (String isdn : listIsdn) {
                if (DataUtil.isNullObject(isdn) || DataUtil.isNullOrEmpty(isdn)) {
                    logger.info("Error request : isdn is null ");
                    return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.isdn.empty1.", language);
                }
                isdn = DataUtil.fomatIsdn(isdn.trim());
                logger.info("Start time call service getListAccountFtthByIsdn: " + dateFormat.format(new Date()));
                WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
                LinkedHashMap<String, Object> params = new LinkedHashMap<>();
                params.put("username", ws.getUsername());
                params.put("password", ws.getPassword());
                params.put("isdn", isdn);
                params.put("token", ws.getParams());
                SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
                if (res == null) {
                    logger.info("Error request : error callWebServicePaymemnt");
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                } else {
                    String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                    if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                        String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                        SoapWSResponse response = new SoapWSResponse(respStr);
                        if (DataUtil.isNullObject(response)) {
                            return responseUtil.responseBeanCompact(Constants.ERROR_CONFIG_WS, "myMetfone.res.failed.", language);
                        }
                        String errCodePath = "/Envelope/Body/getSubInfoForPaymentResponse/return/errorCode";
                        String errDesPath = "/Envelope/Body/getSubInfoForPaymentResponse/return/errorDecription";
                        ResponseDto resultCall = getResponseDetailPayment(response, errCodePath, errDesPath, "service", null, isdn);
                        MetfoneBean metfoneBean = new MetfoneBean();
                        metfoneBean.setListMember(DataUtil.isNullOrEmpty(resultCall.getListItem()) ? new ArrayList<>() : resultCall.getListItem());
                        if (CollectionUtils.isEmpty(metfoneBean.getListMember())) {
                            continue;
                        }
                        results.add(metfoneBean.getListMember().get(0));
                    } else {
                        return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                    }
                }
            }
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(results);
            return bean;

        } catch (Exception e) {
            logger.error("getListAccountFtthByIsdn error: ", e);
            return responseUtil.responseBeanCompact(Constants.ERROR_CODE_1, "myMetfone.getAccountInfo.failed.", language);
        }
    }

    //anjav start
    //lay danh sach complaint type cho Internet wifi
    @Override
    public BaseResponseBean getListComplaintType(RequestBean request, String language) {
        logger.info("Start business getListComplaintType off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getListComplaintType);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service getListComplaintType: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("isdn", isdn);
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    BaseResponseBean bean = null;
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
                    String respCode = res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = res.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listcomtype")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listcomtype");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setCode(product.optString("code"));
                                        comPlaint.setCompTemplate(product.optString("comptemplate".toLowerCase()));
                                        comPlaint.setCompTypeId(product.optLong("comptypeid".toLowerCase()));
                                        comPlaint.setDescription(product.optString("description".toLowerCase()));
                                        comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                        comPlaint.setName(product.optString("name".toLowerCase()));
                                        comPlaint.setServiceType(product.optLong("servicetype".toLowerCase()));
                                        list.add(comPlaint);
                                    }
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setCode(product.optString("code".toLowerCase()));
                                    comPlaint.setCompTemplate(product.optString("comptemplate".toLowerCase()));
                                    comPlaint.setCompTypeId(product.optLong("comptypeid".toLowerCase()));
                                    comPlaint.setDescription(product.optString("description".toLowerCase()));
                                    comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                    comPlaint.setName(product.optString("name".toLowerCase()));
                                    comPlaint.setServiceType(product.optLong("servicetype".toLowerCase()));
                                    list.add(comPlaint);
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorDecription"));
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorDecription"));
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorDecription"));
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(error, res.getTextContent("/Envelope/Body/getComTypeFtthResponse/return/errorDecription"), null);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListComplaintGroupType(RequestBean request, String language) {
        logger.info("Start business getListComplaintGroupType off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("parentId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("parentId").toString())) {
                logger.info("Error requesst : parentId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getListComplaintGroupType);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String parentId = request.getWsRequest().get("parentId").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service getListComplaintType: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("parentId", parentId);
            params.put("token", ws.getParams());
            params.put("locale", locale);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    BaseResponseBean bean = null;
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
                    String respCode = response.getTextContent("/Envelope/Body/getListComplaintGroupTypeResponse/return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = response.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("complaintgroup")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("complaintgroup");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                        comPlaint.setName(product.optString("name".toLowerCase()));
                                        list.add(comPlaint);
                                    }
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setGroupId(product.optLong("groupid".toLowerCase()));
                                    comPlaint.setName(product.optString("name".toLowerCase()));
                                    list.add(comPlaint);
                                    comPlaintMyMetfoneDTO.setListComType(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getListComplaintGroupTypeResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getListComplaintGroupTypeResponse/return/errorDecription"));
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getListComplaintGroupTypeResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getListComplaintGroupTypeResponse/return/errorDecription"));
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/getListComplaintGroupTypeResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/getListComplaintGroupTypeResponse/return/errorDecription"));
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean checkComplaintNotification(RequestBean request, String language) {
        logger.info("Start business checkComplaintNotification off MyMetfoneBusinessService");
        BaseResponseBean bean = null;
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.getComplaintConfirm);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = request.getWsRequest().get("isdn").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service checkComplaintNotification: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("isdn", isdn);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/getComplaintConfirmResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    ComPlaintMyMetfoneDTO comPlaintMyMetfoneDTO = new ComPlaintMyMetfoneDTO();
                    String respCode = res.getTextContent("/Envelope/Body/getComplaintConfirmResponse/return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = res.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            //   resultProductOffer = resultProductOffer + "</result>";
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listcomplainthistory")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listcomplainthistory");
                                logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                        comPlaint.setAcceptDate(product.optString("acceptdate".toLowerCase()));
                                        comPlaint.setCompContent(product.optString("compcontent".toLowerCase()));
                                        comPlaint.setComplainId(product.optLong("complainId".toLowerCase()));
                                        comPlaint.setPreResult(product.optString("preresult".toLowerCase()));
                                        comPlaint.setProLimitDate(product.optString("proLimitDate".toLowerCase()));
                                        comPlaint.setStatus(product.optLong("status".toLowerCase()));
                                        comPlaint.setResultContent(product.optString("resultContent".toLowerCase()));
                                        comPlaint.setStaffInfo(product.optString("staffInfo".toLowerCase()));
                                        comPlaint.setErrorPhone(product.optString("errorPhone".toLowerCase()));
                                        comPlaint.setComplainerPhone(product.optString("complainerPhone".toLowerCase()));
                                        comPlaint.setServiceTypeName(product.optString("serviceType".toLowerCase()));
                                        comPlaint.setGroupTypeName(product.optString("groupType".toLowerCase()));
                                        comPlaint.setStaffPhone(product.optString("staffPhone".toLowerCase()));
                                        comPlaint.setEndDate(product.optString("endDate".toLowerCase()));
                                        comPlaint.setServiceTypeId(product.optLong("serviceTypeId".toLowerCase()));
                                        comPlaint.setCompTypeId(product.optLong("compTypeId".toLowerCase()));
                                        comPlaint.setCompTemplate(product.optString("compTemplate".toLowerCase()));
                                        list.add(comPlaint);
                                    }
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setComplaintConfirm(list.get(0));
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ComPlaintMyMetfoneDetailDTO comPlaint = new ComPlaintMyMetfoneDetailDTO();
                                    comPlaint.setAcceptDate(product.optString("acceptdate".toLowerCase()));
                                    comPlaint.setCompContent(product.optString("compcontent".toLowerCase()));
                                    comPlaint.setComplainId(product.optLong("complainid".toLowerCase()));
                                    comPlaint.setPreResult(product.optString("preresult".toLowerCase()));
                                    comPlaint.setProLimitDate(product.optString("prolimitdate".toLowerCase()));
                                    comPlaint.setStatus(product.optLong("status".toLowerCase()));
                                    comPlaint.setResultContent(product.optString("resultContent".toLowerCase()));
                                    comPlaint.setStaffInfo(product.optString("staffInfo".toLowerCase()));
                                    comPlaint.setErrorPhone(product.optString("errorPhone".toLowerCase()));
                                    comPlaint.setComplainerPhone(product.optString("complainerPhone".toLowerCase()));
                                    comPlaint.setServiceTypeName(product.optString("serviceType".toLowerCase()));
                                    comPlaint.setGroupTypeName(product.optString("groupType".toLowerCase()));
                                    comPlaint.setStaffPhone(product.optString("staffPhone".toLowerCase()));
                                    comPlaint.setEndDate(product.optString("endDate".toLowerCase()));
                                    comPlaint.setServiceTypeId(product.optLong("serviceTypeId".toLowerCase()));
                                    comPlaint.setCompTypeId(product.optLong("compTypeId".toLowerCase()));
                                    comPlaint.setCompTemplate(product.optString("compTemplate".toLowerCase()));
                                    list.add(comPlaint);
//                                comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                    comPlaintMyMetfoneDTO.setComplaintConfirm(list.get(0));
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintConfirmResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                                bean.setWsResponse(comPlaintMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintConfirmResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                            comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                            bean.setWsResponse(comPlaintMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/getComplaintConfirmResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/getComplaintHistoryResponse/return/errorDecription"));
//                        comPlaintMyMetfoneDTO.setIsRightInfor(isRightInfor);
                        bean.setWsResponse(comPlaintMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateComplaint(RequestBean request, String language) {
        logger.info("Start business updateComplaint off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("compContent")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("compContent").toString())) {
                logger.info("Error requesst : compContent is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("complaintId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("complaintId").toString())) {
                logger.info("Error requesst : complaintId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            Webservice ws = myvtgDao.getWebserviceByName(Constants.updateComplaint);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String complaintId = request.getWsRequest().get("complaintId").toString().trim();
            String compContent = request.getWsRequest().get("compContent").toString();
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            //params = new LinkedHashMap<>();
            logger.info("Start time call service closeComplain: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("complaintId", Long.parseLong(complaintId));
            params.put("content", compContent);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/updateComplaintResponse/return/errorCode");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String respCode = res.getTextContent("/Envelope/Body/updateComplaintResponse/return/errorCode");
                    if ("0".equals(respCode)) {
                        apiGwDao.updateAccountNotification(isdn, complaintId);
                    }
                    BaseResponseBean bean = responseUtil.responseBean(respCode, res.getTextContent("/Envelope/Body/updateComplaintResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/updateComplaintResponse/return/errorDecription"));
                    return bean;
                } else {
                    BaseResponseBean bean = responseUtil.responseBean(res.getTextContent("/Envelope/Body/updateComplaintResponse/return/errorCode"), res.getTextContent("/Envelope/Body/updateComplaintResponse/return/errorDecription"), res.getTextContent("/Envelope/Body/updateComplaintResponse/return/errorDecription"));
                    return bean;
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    // anjav end
    @Override
    public BaseResponseBean ChangeCardAddNew(RequestBean request, String language) {
        logger.info("Start business ChangeCardAddNew of MyMetfoneBusinessService");
        try {

            Webservice ws = myvtgDao.getWebserviceByName(Constants.exChangeCardAddNewRequest);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if ((request.getWsRequest().get("user")) == null) {
                logger.info("Error request : user is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.user.empty.", null, language);
            }
            if ((request.getWsRequest().get("serial")) == null) {
                logger.info("Error request : serial is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.serial.empty.", null, language);
            }

            if ((request.getWsRequest().get("phoneNumber")) == null) {
                logger.info("Error request : phoneNumber is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.phoneNumber.empty.", null, language);
            }

            if ((request.getWsRequest().get("urlVideo").toString()) == null) {
                logger.info("Error request : urlVideo is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.urlVideo.empty.", null, language);
            }
            if ((request.getWsRequest().get("dataImage").toString()) == null) {
                logger.info("Error request : dataImage is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.dataImage.empty.", null, language);
            }
            String partner = "CAMID";
            String requestId = "0";
            String user = request.getWsRequest().get("user").toString();
            String serial = request.getWsRequest().get("serial").toString();
            String phoneNumber = request.getWsRequest().get("phoneNumber").toString();
            String urlVideo = request.getWsRequest().get("urlVideo").toString();
            String dataImage = request.getWsRequest().get("dataImage").toString();
            String method = request.getWsRequest().get("method").toString() == null ? "0" : request.getWsRequest().get("method").toString();

            String date = new SimpleDateFormat("ddMMyyyy").format(new Date());

            String FileName = "/" + date + "_" + serial + ".mp4";
            fileUploader.uploadFTPFileFromBase64(urlVideo, FileName);
            String url = environment.getProperty("ftp.new.path") + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + FileName;

            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            logger.info("Start time call service ChangeCardAddNew: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("wscode", ws.getWsName());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("partner", partner);
            params.put("requestId", requestId);
            params.put("user", user);
            params.put("serial", serial);
            params.put("phoneNumber", phoneNumber);
            params.put("urlVideo", url);
            params.put("dataImage", dataImage);
            params.put("method", method);
            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(response)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    String respCode = response.getTextContent("/Envelope/Body/exChangeCardAddNewRequestResponse/return/errorCode");
                    String errorDecription = response.getTextContent("/Envelope/Body/exChangeCardAddNewRequestResponse/return/errorDecription");
                    errorDecription = StringEscapeUtils.unescapeJava(errorDecription);
                    BaseResponseBean bean = responseUtil.responseBean(respCode, errorDecription, errorDecription);
                    return bean;
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean exChangeCardGetlist(RequestBean request, String language) {
        logger.info("Start business exChangeCardGetlist off MyMetfoneBusinessService");
        try {
            Webservice ws = myvtgDao.getWebserviceByName(Constants.exChangeCardGetlistRequest);
            if (ws == null || DataUtil.isNullOrEmpty(ws.getUsername())
                    || DataUtil.isNullOrEmpty(ws.getPassword())
                    || DataUtil.isNullOrEmpty(ws.getParams())) {
                logger.info("Error requesst : config ws invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            String locale = configUtils.getMessage("LOCALE_EN", "en_US").trim();
            if ("km".equals(language)) {
                locale = configUtils.getMessage("LOCALE_KH", "kh_KH").trim();
            }
            if ((request.getWsRequest().get("user")) == null) {
                logger.info("Error request : user is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.user.empty.", null, language);
            }
            if ((request.getWsRequest().get("status")) == null) {
                logger.info("Error request : status is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.status.empty.", null, language);
            }

            if ((request.getWsRequest().get("fromDate")) == null) {
                logger.info("Error request : fromDate is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.fromDate.empty.", null, language);
            }

            if ((request.getWsRequest().get("toDate").toString()) == null) {
                logger.info("Error request : toDate is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.toDate.empty.", null, language);
            }
            String partner = "CAMID";
            String user = request.getWsRequest().get("user").toString();
            String status = (((request.getWsRequest().get("status").toString())));
            String fromDate = request.getWsRequest().get("fromDate").toString();
            String toDate = request.getWsRequest().get("toDate").toString();
            //params = new LinkedHashMap<>();
            logger.info("Start time call service exChangeCardGetlist: " + dateFormat.format(new Date()));
            WebServiceUtil wsUtil = new WebServiceUtil(ws, environment);
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            params.put("username", ws.getUsername());
            params.put("password", ws.getPassword());
            params.put("wscode", ws.getWsName());
            params.put("token", ws.getParams());
            params.put("locale", locale);
            params.put("partner", partner);
            params.put("user", user);
            params.put("status", status);
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);

            SoapWSResponse res = wsUtil.callWebServicePaymemnt(params, true);
            if (res == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                String error = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/error");
                if (error != null && Constants.ERROR_CODE_0.equalsIgnoreCase(error)) {
                    String respStr = res.getTextContent("/Envelope/Body/gwOperationResponse/Result/original");
                    SoapWSResponse response = new SoapWSResponse(respStr);
                    if (DataUtil.isNullObject(res)) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                    }
                    BaseResponseBean bean = null;
                    ChangeDamagedCardMyMetfoneDTO changeDamagedCardMyMetfoneDTO = new ChangeDamagedCardMyMetfoneDTO();
                    String respCode = response.getTextContent("/Envelope/Body/exChangeCardGetlistRequestResponse /return/errorCode");
                    if (Constants.ERROR_CODE_0.equals(respCode)) {
                        Object dataResponse = null;
                        String xmlResponse = response.getResponse();
                        if (!StringUtils.isEmpty(xmlResponse)) {
                            // logger.info(" xmlString:==================> " + xmlResponse);
                            Document doc = Jsoup.parse(xmlResponse, "", Parser.xmlParser());
                            String result = "";
                            for (Element e : doc.select(RETURN_TAG)) {
                                result = result + e.toString();
                            }
                            JSONObject data = XML.toJSONObject(result);
                            if (data.getJSONObject(RETURN_TAG).has("listreqchangedamagedcard")) {
                                dataResponse = data.getJSONObject(RETURN_TAG).get("listreqchangedamagedcard");
                                // logger.info("dataObject" + dataResponse);
                                if (dataResponse instanceof JSONArray) {
                                    JSONArray objIsdn = (JSONArray) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    for (Object obj : objIsdn) {
                                        JSONObject product = (JSONObject) obj;
                                        ChangeCard changeCard = new ChangeCard();
                                        changeCard.setId(product.optLong("id".toLowerCase()));
                                        changeCard.setRequestDateStr(product.optString("requestDateStr".toLowerCase()));
                                        changeCard.setStatus(product.optString("status".toLowerCase()));
                                        changeCard.setStatusStr(product.optString("statusStr".toLowerCase()));
                                        changeCard.setPhoneNumber(product.optString("phoneNumber".toLowerCase()));
                                        changeCard.setSerial(product.optString("serial".toLowerCase()));
                                        changeCard.setUpdateUser(product.optString("updateUser".toLowerCase()));
                                        changeCard.setUpdateDateStr(product.optString("updateDateStr".toLowerCase()));
                                        changeCard.setUpdateReason(product.optString("updateReason".toLowerCase()));

                                        if (product.optString("urlVideo".toLowerCase()) != null || !product.optString("urlVideo".toLowerCase()).isEmpty()) {
                                            String url = product.optString("urlVideo".toLowerCase());
                                            FTPClient ftpClient = new FTPClient();
                                            String dataVideo = fileUploader.getRemoteFileFTP(url, ftpClient);
                                            changeCard.setUrlVideo(dataVideo);
                                        }
                                        changeCard.setDataImage(product.optString("dataImage".toLowerCase()));
                                        list.add(changeCard);
                                    }
                                    changeDamagedCardMyMetfoneDTO.setListChangeDamagedCard(list);
                                } else {
                                    JSONObject product = (JSONObject) dataResponse;
                                    List<Object> list = new ArrayList<>();
                                    ChangeCard changeCard = new ChangeCard();
                                    changeCard.setId(product.optLong("id".toLowerCase()));
                                    changeCard.setRequestDateStr(product.optString("requestDateStr".toLowerCase()));
                                    changeCard.setStatus(product.optString("status".toLowerCase()));
                                    changeCard.setStatusStr(product.optString("statusStr".toLowerCase()));
                                    changeCard.setPhoneNumber(product.optString("phoneNumber".toLowerCase()));
                                    changeCard.setSerial(product.optString("serial".toLowerCase()));
                                    changeCard.setUpdateUser(product.optString("updateUser".toLowerCase()));
                                    changeCard.setUpdateDateStr(product.optString("updateDateStr".toLowerCase()));
                                    changeCard.setUpdateReason(product.optString("updateReason".toLowerCase()));
                                    if (product.optString("urlVideo".toLowerCase()) != null || !product.optString("urlVideo".toLowerCase()).isEmpty()) {
                                        String url = product.optString("urlVideo".toLowerCase());
                                        FTPClient ftpClient = new FTPClient();
                                        String dataVideo = fileUploader.getRemoteFileFTP(url, ftpClient);
                                        changeCard.setUrlVideo(dataVideo);
                                    }
                                    changeCard.setDataImage(product.optString("dataImage".toLowerCase()));
                                    list.add(changeCard);
                                    changeDamagedCardMyMetfoneDTO.setListChangeDamagedCard(list);
                                }
                                bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
                                bean.setWsResponse(changeDamagedCardMyMetfoneDTO);
                                return bean;
                            } else {
                                bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/exChangeCardGetlistRequestResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/exChangeCardGetlistRequestResponse/return/errorDecription"));
                                bean.setWsResponse(changeDamagedCardMyMetfoneDTO);
                                return bean;
                            }
                        } else {
                            bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/exChangeCardGetlistRequestResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/exChangeCardGetlistRequestResponse/return/errorDecription"));
                            bean.setWsResponse(changeDamagedCardMyMetfoneDTO);
                            return bean;
                        }
                    } else {
                        bean = responseUtil.responseBean(respCode, response.getTextContent("/Envelope/Body/exChangeCardGetlistRequestResponse/return/errorDecription"), response.getTextContent("/Envelope/Body/exChangeCardGetlistRequestResponse/return/errorDecription"));
                        bean.setWsResponse(changeDamagedCardMyMetfoneDTO);
                        return bean;
                    }
                } else {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
                }
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }
}
