package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.IsdnInfoBean;
import co.siten.myvtg.bean.MetfoneBean;
import co.siten.myvtg.bean.StoresBean;
import co.siten.myvtg.dao.BankPlusDao;
import co.siten.myvtg.dao.CmpreDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.dao.MyMetfoneBusinesDao;
import co.siten.myvtg.dao.SmsDao;
import co.siten.myvtg.dto.BaseResponsesDto;
import co.siten.myvtg.dto.EmoneyRequest;
import co.siten.myvtg.dto.GiftGroupDTO;
import co.siten.myvtg.dto.InforAccountPoint;
import co.siten.myvtg.dto.InvoiceOuput;
import co.siten.myvtg.dto.RequestDto;
import co.siten.myvtg.dto.SubMbDto;
import co.siten.myvtg.model.myvtg.AuthenLoginGame;
import co.siten.myvtg.model.myvtg.ExchangeScoreHistory;
import co.siten.myvtg.model.myvtg.ExchangeTelecomGiftHistory;
import co.siten.myvtg.model.myvtg.FbInfo;
import co.siten.myvtg.model.myvtg.Gift1;
import co.siten.myvtg.model.myvtg.GiftType;
import co.siten.myvtg.model.myvtg.InviteGame;
import co.siten.myvtg.model.myvtg.LogEmoneyGame;
import co.siten.myvtg.model.myvtg.LogGaming;
import co.siten.myvtg.model.myvtg.LoyaltyTelcoGift;
import co.siten.myvtg.model.myvtg.ShareFb;
import co.siten.myvtg.model.myvtg.Webservice;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import co.siten.myvtg.util.WebServiceUtil;
import static co.siten.myvtg.util.WebServiceUtil.log;
import com.viettel.common.ExchMsg;
import com.vtc.provisioning.client.Exchange;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author daibq
 *
 */
@org.springframework.stereotype.Service("LuckyLoyaltyGameService")

@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class LuckyLoyaltyGameServiceImpl implements LuckyLoyaltyGameService {

    @Autowired
    ConfigUtils configUtils;
    @Autowired
    MyMetfoneBusinesDao dao;
    @Autowired
    CmpreDao cmpreDao;
    @Autowired
    MyvtgService myvtgService;
    @Autowired
    SmsDao smsDao;
    @Autowired
    SubService subService;
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    BankPlusDao bankPlusDao;
    @Autowired
    SmsService smsService;
    private static final Logger logger = Logger.getLogger(LuckyLoyaltyGameServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    private static final String DES_SUCC = "myMetfone.Ishare.des.succ.";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * wsChangeLoyalty
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean changeLoyalty(RequestBean request, String language) {
        logger.info("Start business changeLoyalty off LuckyLoyaltyGameServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("point")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("point").toString())) {
                logger.info("Error requesst : pointAmount is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.point.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("spins")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("spins").toString())) {
                logger.info("Error requesst : spins is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.spins.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String point = request.getWsRequest().get("point").toString().trim();
            String spins = request.getWsRequest().get("spins").toString().trim();
            Integer maxPoint = Integer.parseInt(configUtils.getMessage("MAX_POINT", "50").trim());
            double pointAmount = 0D;
            try {
                pointAmount = Double.parseDouble(point);
                if ((int) pointAmount > maxPoint) {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "metfone.maxpoint.error.", language, String.valueOf(maxPoint), null, null, null);
                }
            } catch (Exception e) {
                logger.info("Error pointAmount invalid : " + e);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.point.empty.", language);
            }
            double spinsD = 0D;
            try {
                spinsD = Double.parseDouble(spins);
            } catch (Exception e) {
                logger.info("Error spins invalid : " + e);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.spins.empty.", language);
            }
            String pointId = configUtils.getMessage("POINT_ID", "1000001").trim();
            String transTypeId = configUtils.getMessage("TRANS_TYPE_ID", "1000024").trim();
            String callService = configUtils.getMessage("CALL_SERVICE", "OFF").trim().toUpperCase();
            if ("ON".equals(callService)) {
                logger.info("Start call API adjustAccountPoint ");
                Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_CHANGE_LOYALTY);
                if (ws == null) {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
                }
                logger.error("Time start" + new Date());
                RequestDto rq = new RequestDto();
                rq.setIsdn(isdn);
                rq.setPointAmount("-" + String.valueOf((int) pointAmount));
                rq.setPointId(pointId);
                rq.setTransTypeId(transTypeId);
                String rqStr = CommonUtil.convertObjectToJsonString(rq);
                logger.info("Requesst send adjustAccountPoint: " + rqStr);
                BaseResponsesDto response = WebServiceUtil.callApiRest(ws.getUrl(), rqStr);
                String content = "";
                smsService.connectServer();
                if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    JSONObject json = new JSONObject(response.getMessageCode());
                    if ("000".equals(json.getString("code"))) {
                        double totalSpins = (int) pointAmount * spinsD;
                        content = responseUtil.getMessage("myMetfone.redeem.point.success.sms." + language, point, String.valueOf((int) totalSpins), null, null);
                        int result = smsService.sendUnicode(isdn, content);
//                        int result = insertMt(isdn, content);
                        logger.info("smsService : " + result);
                        return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "myMetfone.redeem.point.succ.", language, String.valueOf((int) totalSpins), null, null, null);
                    }
                    if ("010".equals(json.getString("code"))) {
                        content = responseUtil.getMessage("myMetfone.redeem.point.not.enough.sms." + language);
                        int result = smsService.sendUnicode(isdn, content);
//                        int result = insertMt(isdn, content);
                        logger.info("smsService : " + result);
                        String msg = responseUtil.getMessage("myMetfone.redeem.point.failed." + language) + "\n";
                        msg += responseUtil.getMessage("myMetfone.redeem.point.failed1." + language);
                        return responseUtil.responseBean(Constants.ERROR_SUCCESS, responseUtil.getMessage(DES_FAIL + language), msg);
                    }
                    content = responseUtil.getMessage("myMetfone.redeem.point.failed.sms." + language);
                    int result = smsService.sendUnicode(isdn, content);
//                        int result = insertMt(isdn, content);
                    logger.info("smsService : " + result);
                    return new BaseResponseBean(Constants.ERROR_CHANGE_LOYALTY, responseUtil.getMessage(DES_FAIL + language), content);
                }
                content = responseUtil.getMessage("myMetfone.redeem.point.error.system.sms." + language);
                int result = smsService.sendUnicode(isdn, content);
//                        int result = insertMt(isdn, content);
                logger.info("smsService : " + result);
                return responseUtil.responseBean(response.getStatusCode().toString(), responseUtil.getMessage(DES_FAIL + language), content);
            } else {
                logger.info("SERVICE CHUA DUOC MO - CALL_SERVICE = OFF ! MO SERVICE TRONG FILE CONFIG  >> PARAM CALL_SERVICE ");
                double totalSpinstest = (int) pointAmount * spinsD;
                String content = responseUtil.getMessage("myMetfone.redeem.point.success.sms." + language, point, String.valueOf((int) totalSpinstest), null, null);
                int result = smsService.sendUnicode(isdn, content);
//                        int result = insertMt(isdn, content);
                logger.info("smsService : " + result);
                return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "myMetfone.redeem.point.succ.", language, String.valueOf((int) totalSpinstest), null, null, null);
            }

        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean logLuckyLoyalty(RequestBean request, String language) {
        logger.info("Start business logLuckyLoyalty off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("authenkey")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("authenkey").toString())) {
                logger.info("Error requesst : authenkey is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("type")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("type").toString())) {
                logger.info("Error requesst : type is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.type.empty.", language);
            }
            String dataType = "";
            dataType = request.getWsRequest().containsKey("dataType") && !DataUtil.isNullOrEmpty(request.getWsRequest().get("dataType").toString()) ? request.getWsRequest().get("dataType").toString().trim() : dataType;
            String authenkey = request.getWsRequest().get("authenkey").toString().trim();

            //check authenkey
            AuthenLoginGame authen = dao.getIsdnInfoByAuthenKey(authenkey, true);
            if (DataUtil.isNullObject(authen)) {
                logger.info("Error requesst : authenkey is invalid or no loginByToken ");
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            //check isdn
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            if (!authen.getIsdn().equals(isdn)) {
                logger.info("Error requesst : isdn is invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            String type = request.getWsRequest().get("type").toString().trim();
            String giftId = "";
            giftId = request.getWsRequest().containsKey("giftId") && !DataUtil.isNullOrEmpty(request.getWsRequest().get("giftId").toString()) ? request.getWsRequest().get("giftId").toString() : giftId;
            String value = null;
            String name = null;
            if ("1".equals(type)) {
                if (DataUtil.isNullOrEmpty(dataType)) {
                    logger.info("Error requesst : dataType is null ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.dataType.empty.", language);
                }
                if (DataUtil.isNullOrEmpty(giftId)) {
                    logger.info("Error requesst : giftId is null ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.empty.", language);
                }
                String[] giftArr = giftId.split("_");

                List<LoyaltyTelcoGift> lst = dao.getLoyaltyTelcoGift(giftArr[0].trim(), dataType);
                if (DataUtil.isNullOrEmpty(lst)) {
                    logger.info("Error requesst : vien thong khong ton tai gift:  " + giftId);
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.datatype.empty.", language);
                }
                if (giftArr.length > 2) {
                    value = giftArr[2].trim();
                } else {
                    value = lst.get(0).getExchangeValue().toString();
                }
                name = lst.get(0).getGiftTitle();
            }
            if ("2".equals(type)) {
                if (DataUtil.isNullOrEmpty(dataType)) {
                    logger.info("Error requesst : dataType is null ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.dataType.empty.", language);
                }
                if (DataUtil.isNullOrEmpty(giftId)) {
                    logger.info("Error requesst : giftId is null ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.empty.", language);
                }
                List<Gift1> list = dao.getGift(giftId, dataType);
                if (DataUtil.isNullOrEmpty(list)) {
                    logger.info("Error requesst : hien vat khong ton tai gift:  " + giftId);
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.datatype.empty.", language);
                }
                value = list.get(0).getGiftCode();
                name = list.get(0).getGiftName();
            }
            LogGaming logGaming = new LogGaming();
            if (!DataUtil.isNullOrEmpty(giftId)) {
                logGaming.setGiftId(giftId);
            }
            if (!DataUtil.isNullOrEmpty(dataType)) {
                logGaming.setDataType(dataType.trim().toUpperCase());
            }
            logGaming.setId(DataUtil.randomUUID());
            logGaming.setAuthenkey(authenkey);
            logGaming.setIsdn(isdn);
            logGaming.setGameCode(authen.getGameCode());
            logGaming.setStatus(0L);
            logGaming.setType(type);
            logGaming.setValue(value);
            logGaming.setName(name);
            logGaming.setCreateDate(new Date());
            String id = dao.save(logGaming);
            String luckyId = "";
            boolean isLucky = false;
            if (!"0".equals(type) && !DataUtil.isNullOrEmpty(giftId)) {
                isLucky = true;
                luckyId = id;// todo update log
            }
            IsdnInfoBean isdnInfoBean = new IsdnInfoBean();
//            isdnInfoBean.setIsdn(isdn);
            isdnInfoBean.setLuckyId(luckyId);
            isdnInfoBean.setIsLucky(isLucky);
            isdnInfoBean.setType(Long.parseLong(type));
//            isdnInfoBean.setValue(value);
            bean.setWsResponse(isdnInfoBean);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * telecomMunicationsAward
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean telecomMunicationsAward(RequestBean request, String language) {
        logger.info("Start business telecomMunicationsAward off LuckyLoyaltyGameServiceImpl");
        try {
//            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Notification content");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("authenkey")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("authenkey").toString())) {
                logger.info("Error requesst : authenkey is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("type")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("type").toString())) {
                logger.info("Error requesst : type is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.type.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("giftId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("giftId").toString())) {
                logger.info("Error requesst : value is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("luckyId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("luckyId").toString())) {
                logger.info("Error requesst : luckyId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.luckyId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("dataType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("dataType").toString())) {
                logger.info("Error requesst : dataType is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.dataType.empty.", language);
            }
            String dataType = request.getWsRequest().get("dataType").toString().trim().toUpperCase();
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String luckyId = request.getWsRequest().get("luckyId").toString().trim();
            LogGaming logGaming = dao.getLogLuckGame(isdn, luckyId);
            if (DataUtil.isNullObject(logGaming)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.isdn.luckyid.empty.", language);
            }
            String authenkey = request.getWsRequest().get("authenkey").toString().trim();
            if (!authenkey.equals(logGaming.getAuthenkey())) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            String type = request.getWsRequest().get("type").toString().trim();
            String giftId = request.getWsRequest().get("giftId").toString().trim();
            if (!giftId.trim().equals(logGaming.getGiftId().trim()) || !logGaming.getType().equals(type) || DataUtil.isNullOrEmpty(logGaming.getValue())) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.type.empty.", language);
            }
            if (!logGaming.getDataType().equals(dataType)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.dataType.empty.", language);
            }
            // kiêm tra dịch vụ để cong tiền
//            List<LoyaltyTelcoGift> lst = dao.getLoyaltyTelcoGift(giftId, dataType);
//            if (DataUtil.isNullOrEmpty(lst)) {
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.datatype.empty.", language);
//            }
//            LoyaltyTelcoGift loyaltyTelcoGift = lst.get(0);
            String value = logGaming.getValue();
            String callPro = configUtils.getMessage("CALL_PRO");
//            Integer addDay = Integer.parseInt(configUtils.getMessage("ADD_DAY").trim());
            Integer expriedDay = 0;
            String balance = "";
            if (!"VALIDITY".equals(dataType)) {
                balance = configUtils.getMessage(dataType).trim();
                expriedDay = Integer.parseInt(configUtils.getMessage("EXPRIED_DATE_" + dataType).trim());
            }
            String error = "";
            Date currDate = new Date();
            if ("ON".equals(callPro)) {
                Exchange exchange = new Exchange();
                ExchMsg responseCall = null;
                responseCall = WebServiceUtil.callProExchange(exchange, isdn, value, expriedDay, cmpreDao, dataType, balance);
                if (DataUtil.isNullObject(responseCall)) {
                    logGaming.setStatus(3L);
                    logGaming.setUpdateDate(currDate);
                    dao.update(logGaming);
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                }
                error = responseCall.getError();
                logger.info("Kong call len tong dai ");
                error = "0";
            } else {
                //tat call tong dai de test thi fix mac dinh call thah cong
                logger.info("Tong dai dang tat- cau hinh trong file config ");
                error = "0";
            }
            String content = "";
            if (DataUtil.isNullOrEmpty(error) || !"0".equals(error)) {
                logGaming.setStatus(3L);
                logGaming.setUpdateDate(currDate);
                dao.update(logGaming);
                return responseUtil.responseBean(Constants.NOT_SUCCESS, DES_FAIL, "mymetfone.game.lucky.tele.trans.failed.", language);
            } else {
                if (!DataUtil.isNullOrEmpty(dataType)) {
                    switch (dataType) {
                        case "SMS":
                            log.info("ServiceA : SMS");
                            content = responseUtil.getMessage("myMetfone.game.lucky.tele.sms." + language, logGaming.getName(), null, null, null);
                            break;
                        case "MINUTE":
                            log.info("ServiceA : MINUTE");
                            content = responseUtil.getMessage("myMetfone.game.lucky.tele.sms." + language, logGaming.getName(), null, null, null);
                            break;
                        case "DATA":
                            log.info("ServiceA : DATA");
                            content = responseUtil.getMessage("myMetfone.game.lucky.tele.sms.data." + language, String.valueOf(Double.parseDouble(value) / 1024), expriedDay.toString(), null, null);
                            break;
                        case "VALIDITY":
                            content = responseUtil.getMessage("myMetfone.game.lucky.tele.sms." + language, logGaming.getName(), null, null, null);
                            break;
                        case "CHARGE":
                            log.info("ServiceA : CHARGE");
                            content = responseUtil.getMessage("myMetfone.game.lucky.tele.sms.balance." + language, value, expriedDay.toString(), null, null);
                            break;
                        case "BALANCE":
                            log.info("ServiceA : BALANCE");
                            content = responseUtil.getMessage("myMetfone.game.lucky.tele.sms.balance." + language, value, expriedDay.toString(), null, null);
                            break;
                        default:
                            break;
                    }
                }
                if (!DataUtil.isNullOrEmpty(content)) {
                    smsService.connectServer();
                    smsService.sendUnicode(isdn, content);
                }
//                int statusSms = insertMt(isdn, content);
                String save = configUtils.getMessage("SAVE", "ON").trim().toUpperCase();
                if ("ON".equals(save)) {
                    //Luu log ExchangeTelecomGiftHistory
                    ExchangeTelecomGiftHistory exchangeTelecomGiftHistory = new ExchangeTelecomGiftHistory();
                    exchangeTelecomGiftHistory.setId(DataUtil.randomUUID());
                    exchangeTelecomGiftHistory.setGiftId(giftId);
                    exchangeTelecomGiftHistory.setIsdn(isdn);
                    exchangeTelecomGiftHistory.setProgramCode(logGaming.getGameCode());
                    exchangeTelecomGiftHistory.setDateTime(currDate);
                    exchangeTelecomGiftHistory.setExchangeDate(currDate);
                    exchangeTelecomGiftHistory.setReceiveDate(currDate);
                    String id = dao.save(exchangeTelecomGiftHistory);
                }

//                if (DataUtil.isNullOrEmpty(id)) {
//                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
//                }
                //Luu log logGaming
                logGaming.setStatus(1L);
                logGaming.setUpdateDate(currDate);
                dao.update(logGaming);
            }
            return responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", content);
        } catch (Exception e) {
            logger.info("Exception" + e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }
    public BaseResponseBean telecomMunicationsAwardForFriend(RequestBean request, String language) {
        logger.info("Start business telecomMunicationsAwardForFriend off LuckyLoyalty");

        try {
            if (!DataUtil.isNullObject(request.getWsRequest().get("isdn")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                if (!DataUtil.isNullObject(request.getWsRequest().get("isdnDest")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("isdnDest").toString())) {
                    if (!DataUtil.isNullObject(request.getWsRequest().get("dataType")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("dataType").toString())) {
                        if (!DataUtil.isNullObject(request.getWsRequest().get("value")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("value").toString())) {
                            String dataType = request.getWsRequest().get("dataType").toString().trim().toUpperCase();
                            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
                            String value = request.getWsRequest().get("value").toString().trim();
                            String amountPoint = request.getWsRequest().get("amountPoin").toString().trim();
                            String isdnDest = DataUtil.fomatIsdn(request.getWsRequest().get("isdnDest").toString().trim());
                            String callPro = this.configUtils.getMessage("CALL_PRO");
                            Integer expriedDay = 7;
                            String balance = "";
                            if (!"VALIDITY".equals(dataType)) {
                                balance = this.configUtils.getMessage(dataType).trim();
                            }

                            String error = "";
                            new Date();
                            if ("ON".equals(callPro)) {
                                Exchange exchange = new Exchange();
                                ExchMsg responseCall = null;
                                responseCall = WebServiceUtil.callProExchange(exchange, isdnDest, value, expriedDay, this.cmpreDao, dataType, balance);
                                if (DataUtil.isNullObject(responseCall)) {
                                    return this.responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
                                }

                                error = responseCall.getError();
                                logger.info("Kong call len tong dai ");
                                error = "0";
                            } else {
                                logger.info("Tong dai dang tat- cau hinh trong file config ");
                                error = "0";
                            }

                            String content = "";
                            String content2 = "";
                            if (!DataUtil.isNullOrEmpty(error) && "0".equals(error)) {
                                if (!DataUtil.isNullOrEmpty(dataType)) {
                                    byte var16 = -1;
                                    switch(dataType.hashCode()) {
                                    case 2090922:
                                        if (dataType.equals("DATA")) {
                                            var16 = 0;
                                        }
                                        break;
                                    case 1986664116:
                                        if (dataType.equals("CHARGE")) {
                                            var16 = 1;
                                        }
                                    }

                                    switch(var16) {
                                    case 0:
                                        WebServiceUtil.log.info("ServiceA : DATA");
                                        content = this.responseUtil.getMessage("SOURCE_ISDN_MESSAGE." + language, amountPoint, value + " MB", isdnDest, (String)null);
                                        content2 = this.responseUtil.getMessage("DEST_ISDN_MESSAGE." + language, value + " MB", isdn, (String)null, (String)null);
                                        break;
                                    case 1:
                                        WebServiceUtil.log.info("ServiceA : CHARGE");
                                        content = this.responseUtil.getMessage("SOURCE_ISDN_MESSAGE." + language, amountPoint, value + " USD", isdnDest, (String)null);
                                        content2 = this.responseUtil.getMessage("DEST_ISDN_MESSAGE." + language, value + " USD", isdn, (String)null, (String)null);
                                    }
                                }

                                if (!DataUtil.isNullOrEmpty(content)) {
                                    this.smsService.connectServer();
                                    this.smsService.sendUnicode(isdn, content);
                                    this.smsService.sendUnicode(isdnDest, content2);
                                }

                                return this.responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", content);
                            } else {
                                return this.responseUtil.responseBean(Constants.NOT_SUCCESS, "myMetfone.Ishare.des.fail.", "mymetfone.game.lucky.tele.trans.failed.", language);
                            }
                        } else {
                            logger.info("Error requesst : value is null ");
                            return this.responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "mymetfone.game.dataType.empty.", language);
                        }
                    } else {
                        logger.info("Error requesst : dataType is null ");
                        return this.responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "mymetfone.game.dataType.empty.", language);
                    }
                } else {
                    logger.info("Error requesst : isdnDest is null ");
                    return this.responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.isdn.empty.", language);
                }
            } else {
                logger.info("Error requesst : isdn is null ");
                return this.responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.isdn.empty.", language);
            }
        } catch (Exception var17) {
            logger.info("Exception" + var17);
            return this.responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }
    /**
     * artifactsAward
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean artifactsAward(RequestBean request, String language) {
        logger.info("Start business artifactsAward off LuckyLoyaltyGameServiceImpl");
        try {
//            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Notification content");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("authenkey")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("authenkey").toString())) {
                logger.info("Error requesst : authenkey is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("type")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("type").toString())) {
                logger.info("Error requesst : type is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.type.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("giftId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("giftId").toString())) {
                logger.info("Error requesst : giftId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("luckyId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("luckyId").toString())) {
                logger.info("Error requesst : luckyId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.luckyId.empty.", language);
            }
//            if (DataUtil.isNullObject(request.getWsRequest().get("shopId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("shopId").toString())) {
//                logger.info("Error requesst : shopId is null ");
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "shopId is null-", language);
//            }
            if (DataUtil.isNullObject(request.getWsRequest().get("dataType")) || DataUtil.isNullObject(request.getWsRequest().get("dataType").toString())) {
                logger.info("Error requesst : dataType is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.dataType.empty.", language);
            }
            String dataType = request.getWsRequest().get("dataType").toString().trim();
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String luckyId = request.getWsRequest().get("luckyId").toString().trim();
            LogGaming logGaming = dao.getLogLuckGame(isdn, luckyId);
            if (DataUtil.isNullObject(logGaming)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.isdn.luckyid.empty.", language);
            }
            String authenkey = request.getWsRequest().get("authenkey").toString().trim();
            if (!authenkey.equals(logGaming.getAuthenkey())) {
                logger.info("Error requesst : authenkey invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            String type = request.getWsRequest().get("type").toString().trim();
            String giftId = request.getWsRequest().get("giftId").toString().trim();
            if (!giftId.trim().equals(logGaming.getGiftId().trim()) || !logGaming.getType().equals(type) || DataUtil.isNullOrEmpty(logGaming.getValue())) {
                logger.info("Error requesst : giftId or type invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.datatype.empty.", language);
            }

            List<Gift1> list = dao.getGift(giftId, dataType);
            if (DataUtil.isNullOrEmpty(list)) {
                logger.info("Error requesst : giftId or dataType  invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.giftid.datatype.empty.", language);
            }
            if (!logGaming.getDataType().equals(dataType.trim().toUpperCase())) {
                logger.info("Error requesst : dataType invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.dataType.empty.", language);
            }

            Gift1 gift = list.get(0);
            if (DataUtil.isNullObject(gift.getQuantity()) || gift.getQuantity() < 1) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "NOT_ENOUGH_GIFT.", language);
            }
            List<GiftType> listGiftType = dao.getGiftTypeById(gift.getGiftTypeId());
            if (DataUtil.isNullOrEmpty(listGiftType)) {
                logger.info("Error requesst : gift.getGiftTypeId  invalid ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.parent.gift.empty.", language);
            }
            GiftType giftType = listGiftType.get(0);
//            String shopId ="";
//            shopId = request.getWsRequest().containsKey("shopId")? request.getWsRequest().get("shopId").toString();
            String msg = "";
            String content = "";
            String contentPromotion = "";
            String exchangeCode = "";
            String generateQrCode = "";
            String errorMsg = "Success";
            String errorCode = Constants.ERROR_SUCCESS;
            BaseResponseBean bean = null;
            smsService.connectServer();
            Date currDate = new Date();
            contentPromotion = responseUtil.getMessage("mymetfone.game.success.promotion." + language);
            String save = configUtils.getMessage("SAVE", "ON").trim().toUpperCase();
            if (!DataUtil.isNullObject(gift.getIsEmoney()) && gift.getIsEmoney() == 1) {
                InvoiceOuput ioCreate = null;
                InvoiceOuput ioConfirm = null;
                BaseResponsesDto response = null;
                Webservice ws = null;
                String key = configUtils.getMessage("AUTHORIZATION").trim();
                String currCode = configUtils.getMessage("CURR_CODE").trim();
                String des = configUtils.getMessage("CONTENT").trim();
                String pin = configUtils.getMessage("PIN").trim();
                String accountReceiver = DataUtil.fomatIsdn855(isdn);
                boolean checkWallet = bankPlusDao.checkWallet(accountReceiver);
                Long idLogEmoney = dao.getSequence("LOG_EMONEY_GAME_SEQ");

                LogEmoneyGame logEmoneyGame = new LogEmoneyGame();
                logEmoneyGame.setId(idLogEmoney);
                logEmoneyGame.setLuckyId(luckyId);
                logEmoneyGame.setCurrCode(currCode);
                logEmoneyGame.setProgramCode(logGaming.getGameCode());
                logEmoneyGame.setAccountReceiver(accountReceiver);
//                logEmoneyGame.setAccount(account);
                logEmoneyGame.setCreareDate(currDate);
                logEmoneyGame.setAmount(Double.parseDouble(gift.getDiscountRate()));
                if (!checkWallet) {
                    logger.info("Error check acount emoney: Not account emoney");
                    String exprired = configUtils.getMessage("EXPRIED_DATE_PROCESS").trim();
                    Date expriredDate = DataUtil.addDate(currDate, Integer.parseInt(exprired));
                    logEmoneyGame.setStatus(0L);
                    logEmoneyGame.setError(Constants.NOT_ACCOUNT_EMONEY);
                    logEmoneyGame.setExpriredDate(expriredDate);
                    logEmoneyGame.setDescription("Not account emoney");
                    msg = responseUtil.getMessage("mymetfone.game.not.account.emoney." + language);
                    msg = String.format(msg, gift.getDiscountRate(), dateFormat.format(expriredDate));
                    content = responseUtil.getMessage("mymetfone.game.not.account.sms.emoney." + language);
                    content = String.format(content, gift.getDiscountRate(), dateFormat.format(expriredDate));
                    bean = responseUtil.responseBean(Constants.NOT_ACCOUNT_EMONEY, "Fail", msg);
                    dao.insert(logEmoneyGame);
                    logger.info("Start update gift (tru kho) ");
                    gift.setQuantity(gift.getQuantity() - 1L);
                    dao.update(gift);
                    if (!DataUtil.isNullOrEmpty(content)) {
                        smsService.sendUnicode(isdn, content);
//                        insertMt(isdn, content);
                    }
                    return bean;
                } else {
                    logger.info("Start call API wsCreateInvoiceEmoney ");
                    ws = myvtgService.getWS(Constants.WEBSERVICE_CREATE_INVOICE);
                    if (ws == null) {
                        logger.info("Error call API wsCreateInvoiceEmoney : Error config ws wsCreateInvoiceEmoney");
                        logEmoneyGame.setStatus(4L);
                        logEmoneyGame.setError(Constants.ERROR_CONFIG_WS);
                        logEmoneyGame.setDescription("Error call API wsCreateInvoiceEmoney : Error config ws wsCreateInvoiceEmoney");
                        dao.insert(logEmoneyGame);
                        return responseUtil.responseBean(Constants.ERROR_CONFIG_WS, DES_FAIL, "myMetfone.webServices.empty.", language);
                    }
                    EmoneyRequest emoneyRequest = new EmoneyRequest();
                    emoneyRequest.setPaymentType(Constants.TRANSFER);
                    emoneyRequest.setContent(des);
                    emoneyRequest.setTransAmount(Double.parseDouble(gift.getDiscountRate()));
                    Long refId = new Date().getTime() + idLogEmoney;
                    if (refId.toString().length() > 25) {
                        refId = Long.valueOf(refId.toString().substring(0, 24));
                    }
                    emoneyRequest.setRefId(refId.toString());
                    emoneyRequest.setCurrency(currCode);
                    emoneyRequest.setCustomerPhoneNumber(accountReceiver);
//                    emoneyRequest.setCcyAcceptPayment(currCode);
                    String rqStr = CommonUtil.convertObjectToJsonString(emoneyRequest);
                    logger.info("Requesst send wsCreateInvoiceEmoney: " + rqStr);
                    logger.error("Time start" + new Date());
                    response = WebServiceUtil.callApiEmoney(ws.getUrl(), rqStr, key, language);
                    if (HttpURLConnection.HTTP_OK != response.getStatusCode()) {
                        logger.info("Error call API wsCreateInvoiceEmoney :" + response.getStatusCode());
                        logEmoneyGame.setStatus(2L);
                        logEmoneyGame.setRequest(rqStr);
                        logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                        logEmoneyGame.setError(response.getStatusCode().toString());
                        logEmoneyGame.setDescription("Error call API wsCreateInvoiceEmoney :" + response.getStatusCode());
                        dao.insert(logEmoneyGame);
                        return responseUtil.responseBean(response.getStatusCode().toString(), DES_FAIL, "myMetfone.failed.", language);
                    }
                    ioCreate = CommonUtil.convertJsonStringToObject(response.getMessageCode(), InvoiceOuput.class);
                    if (DataUtil.isNullObject(ioCreate)) {
                        logger.info("Error call API wsCreateInvoiceEmoney : InvoiceOuput is null");
                        logEmoneyGame.setStatus(2L);
                        logEmoneyGame.setRequest(rqStr);
                        logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                        logEmoneyGame.setError(Constants.ERR_TRANS_EMONEY);
                        logEmoneyGame.setDescription("Error call API wsCreateInvoiceEmoney : InvoiceOuput is null");
                        dao.insert(logEmoneyGame);
                        return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, DES_FAIL, "myMetfone.failed.", language);
                    }

                    if (!Constants.ERROR_SUCCESS.equals(ioCreate.getStatus().toString())) {
                        logger.info("Error call API wsCreateInvoiceEmoney : ioCreate.getStatus()<> 0");
                        logEmoneyGame.setStatus(2L);
                        logEmoneyGame.setRequest(rqStr);
                        logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                        logEmoneyGame.setError(ioCreate.getStatus().toString());
                        logEmoneyGame.setDescription(ioCreate.getMessage());
                        dao.insert(logEmoneyGame);
                        return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, ioCreate.getCode(), ioCreate.getMessage());
                    } else {
                        if (Objects.equals(Constants.STATUS_PAID, ioCreate.getTxDetail().getStatus())) {
                            logger.info("Error call API wsCreateInvoiceEmoney : Transaction has been paid");
                            logEmoneyGame.setStatus(2L);
                            logEmoneyGame.setRequest(rqStr);
                            logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                            logEmoneyGame.setError(Constants.ERR_TRANS_EMONEY);
                            logEmoneyGame.setDescription("Transaction has been paid");
                            dao.insert(logEmoneyGame);
                            return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, "Fail", "Transaction has been paid");
                        }
                        if (Objects.equals(Constants.STATUS_PAYMENT_ERR, ioCreate.getTxDetail().getStatus())) {
                            logger.info("Error call API wsCreateInvoiceEmoney : Transaction error");
                            logEmoneyGame.setStatus(2L);
                            logEmoneyGame.setRequest(rqStr);
                            logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                            logEmoneyGame.setError(Constants.ERR_TRANS_EMONEY);
                            logEmoneyGame.setDescription("Transaction fail");
                            dao.insert(logEmoneyGame);
                            return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, "Fail", "Transaction fail");
                        }
                        if (Constants.STATUS_PAYMENT_WAIT.contains(ioCreate.getTxDetail().getStatus().toString())) {
                            logger.info("Start call API wsConfirmPayEmoney ");
                            ws = myvtgService.getWS(Constants.WEBSERVICE_CF_PAY_EMONEY);
                            if (ws == null) {
                                logger.info("Error call API wsConfirmPayEmoney : Error config ws wsConfirmPayEmoney");
                                logEmoneyGame.setStatus(4L);
                                logEmoneyGame.setError(Constants.ERROR_CONFIG_WS);
                                logEmoneyGame.setDescription("Error call API wsConfirmPayEmoney : Error config ws wsConfirmPayEmoney");
                                dao.insert(logEmoneyGame);
                                return responseUtil.responseBean(Constants.ERROR_CONFIG_WS, DES_FAIL, "myMetfone.webServices.empty.", language);
                            }
                            //giai ma token
                            String txPaymentTokenId = ioCreate.getTxDetail().getTxPaymentTokenId();
                            String decrypt = DataUtil.decryptRSA(txPaymentTokenId, configUtils.getMessage("emoney.private.key"));
                            logger.info("private_key: " + configUtils.getMessage("emoney.private.key"));
                            //ma hoa lại+pin
                            // 
                            String encrypt = DataUtil.encryptRSA(decrypt + "|" + pin, configUtils.getMessage("emoney.public.key"));
                            logger.info("public_key: " + configUtils.getMessage("emoney.public.key"));
                            EmoneyRequest emoneyRq = new EmoneyRequest();
                            emoneyRq.setTxPaymentTokenId(encrypt);
                            String rq = CommonUtil.convertObjectToJsonString(emoneyRq);
                            logger.info("Requesst send wsCreateInvoiceEmoney: " + rq);
                            logger.error("Time start" + new Date());
                            response = WebServiceUtil.callApiEmoney(ws.getUrl(), rq, key, language);
                            if (HttpURLConnection.HTTP_OK != response.getStatusCode()) {
                                logger.info("Error call API wsConfirmPayEmoney : " + response.getStatusCode());
                                logEmoneyGame.setStatus(2L);
                                logEmoneyGame.setRequest(rq);
                                logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                                logEmoneyGame.setError(response.getStatusCode().toString());
                                logEmoneyGame.setDescription("Error call API wsConfirmPayEmoney : " + response.getStatusCode());
                                dao.insert(logEmoneyGame);
                                return responseUtil.responseBean(response.getStatusCode().toString(), DES_FAIL, "myMetfone.failed.", language);
                            }
                            ioConfirm = CommonUtil.convertJsonStringToObject(response.getMessageCode(), InvoiceOuput.class);
                            if (DataUtil.isNullObject(ioConfirm)) {
                                logger.info("Error call API wsConfirmPayEmoney InvoiceOuput is null  ");
                                logEmoneyGame.setStatus(2L);
                                logEmoneyGame.setRequest(rq);
                                logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                                logEmoneyGame.setError(Constants.ERR_TRANS_EMONEY);
                                logEmoneyGame.setDescription("Error call API wsConfirmPayEmoney InvoiceOuput is null");
                                dao.insert(logEmoneyGame);
                                return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, DES_FAIL, "myMetfone.failed.", language);
                            }
                            if (!Constants.ERROR_SUCCESS.equals(ioConfirm.getStatus().toString())) {
                                logger.info("Error call API wsConfirmPayEmoney : ioConfirm.getStatus()<>0  ");
                                logEmoneyGame.setStatus(2L);
                                logEmoneyGame.setRequest(rq);
                                logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                                logEmoneyGame.setError(ioConfirm.getStatus().toString());
                                logEmoneyGame.setDescription(ioConfirm.getMessage());
                                dao.insert(logEmoneyGame);
                                return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, ioConfirm.getCode(), ioConfirm.getMessage());
                            } else {
                                if (Objects.equals(Constants.STATUS_PAID, ioConfirm.getTxDetail().getStatus())) {
                                    content = responseUtil.getMessage("mymetfone.game.success.emoney." + language);
                                    content = String.format(content, gift.getDiscountRate());
                                    logger.info("Transfer success :");
                                    logger.info("Start insert logEmoneyGame");
                                    logEmoneyGame.setStatus(1L);
                                    logEmoneyGame.setRequest(rq);
                                    logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                                    logEmoneyGame.setError(ioConfirm.getStatus().toString());
                                    logEmoneyGame.setDescription(ioConfirm.getMessage());
                                    logEmoneyGame.setPaidTid(ioConfirm.getTxDetail().getPaidTid());
                                    logEmoneyGame.setRefId(ioConfirm.getTxDetail().getRefId());
                                    dao.insert(logEmoneyGame);
                                    //Luu ExchangeScoreHistory

                                    if ("ON".equals(save)) {
                                        logger.info("Start insert exchangeScoreHistory");
                                        ExchangeScoreHistory exchangeScoreHistory = new ExchangeScoreHistory();
                                        exchangeScoreHistory.setGiftId(giftId);
                                        exchangeScoreHistory.setIsdn(isdn);
                                        exchangeScoreHistory.setExchangeScoreHistoryId(DataUtil.randomUUID());
                                        exchangeScoreHistory.setExchangeCode(exchangeCode);
                                        exchangeScoreHistory.setGifrQrCode(generateQrCode);
                                        exchangeScoreHistory.setProgramCode(logGaming.getGameCode());
                                        exchangeScoreHistory.setDateTime(currDate);
                                        exchangeScoreHistory.setExchangeDate(currDate);
                                        String id = dao.save(exchangeScoreHistory);
                                    }

                                    //cap nhat log game
                                    logger.info("Start update status logGaming");
                                    logGaming.setStatus(1L);
                                    logGaming.setUpdateDate(currDate);
                                    dao.update(logGaming);
                                    // thưc hiện trừ kho 
                                    logger.info("Start update gift (tru kho) ");
                                    gift.setQuantity(gift.getQuantity() - 1L);
                                    dao.update(gift);
                                    return responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", content);
                                } else {
                                    logger.info("Transfer fail : status<>3");
                                    logEmoneyGame.setStatus(2L);
                                    logEmoneyGame.setRequest(rq);
                                    logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                                    logEmoneyGame.setError(Constants.ERR_TRANS_EMONEY);
                                    logEmoneyGame.setDescription("Transfer fail : status<>3");
                                    logEmoneyGame.setPaidTid(ioConfirm.getTxDetail().getPaidTid());
                                    logEmoneyGame.setRefId(ioConfirm.getTxDetail().getRefId());
                                    dao.insert(logEmoneyGame);
                                    return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, "Fail", "Transaction fail");
                                }
                            }
                        } else {
                            logEmoneyGame.setStatus(4L);
                            logEmoneyGame.setError(Constants.ERR_TRANS_EMONEY);
                            logEmoneyGame.setRequest(rqStr);
                            logEmoneyGame.setResponse(CommonUtil.convertObjectToJsonString(response));
                            logEmoneyGame.setDescription("Error trans emoney : status <> 1,2,3");
                            dao.insert(logEmoneyGame);
                            return responseUtil.responseBean(Constants.ERR_TRANS_EMONEY, "Fail", "Transaction fail", language);
                        }
                    }
                }
            } else if (!DataUtil.isNullObject(gift.getIsEmoney()) && (gift.getIsPromotion() == 1 || gift.getIsPromotion() == 2)) {
                //todo thực hiện nhắn tin cho khách hang
                contentPromotion = String.format(contentPromotion, gift.getGiftName());
                if (!DataUtil.isNullOrEmpty(contentPromotion)) {
                    smsService.sendUnicode(isdn, contentPromotion);
                }
                if ("ON".equals(save)) {
                    logger.info("Start insert exchangeScoreHistory");
                    ExchangeScoreHistory exchangeScoreHistory = new ExchangeScoreHistory();
                    exchangeScoreHistory.setGiftId(giftId);
                    exchangeScoreHistory.setIsdn(isdn);
                    exchangeScoreHistory.setExchangeScoreHistoryId(DataUtil.randomUUID());
                    exchangeScoreHistory.setExchangeCode(exchangeCode);
                    exchangeScoreHistory.setGifrQrCode(generateQrCode);
                    exchangeScoreHistory.setProgramCode(logGaming.getGameCode());
                    exchangeScoreHistory.setDateTime(currDate);
                    exchangeScoreHistory.setExchangeDate(currDate);
                    String id = dao.save(exchangeScoreHistory);
                }

                //cap nhat log game
                logger.info("Start update status logGaming");
                logGaming.setStatus(1L);
                logGaming.setUpdateDate(currDate);
                dao.update(logGaming);
                // thưc hiện trừ kho 
                logger.info("Start update gift (tru kho) ");
                gift.setQuantity(gift.getQuantity() - 1L);
                dao.update(gift);
                return responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", contentPromotion);
            } else if (!DataUtil.isNullObject(gift.getIsEmoney()) && gift.getIsPromotion() == 3) {
                String pointId = configUtils.getMessage("POINT_ID", "1000001").trim();
                String transTypeId = configUtils.getMessage("TRANS_TYPE_ID", "1000024").trim();
                String callService = configUtils.getMessage("CALL_SERVICE", "OFF").trim().toUpperCase();
                if ("ON".equals(callService)) {
                    logger.info("Start call API adjustAccountPoint ");
                    Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_CHANGE_LOYALTY);
                    if (ws == null) {
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
                    }
                    logger.error("Time start" + new Date());
                    RequestDto rq = new RequestDto();
                    rq.setIsdn(isdn);
                    rq.setPointAmount(gift.getDiscountRate());
                    rq.setPointId(pointId);
                    rq.setTransTypeId(transTypeId);
                    String rqStr = CommonUtil.convertObjectToJsonString(rq);
                    logger.info("Requesst send adjustAccountPoint: " + rqStr);
                    BaseResponsesDto response = WebServiceUtil.callApiRest(ws.getUrl(), rqStr);
                    if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                        JSONObject json = new JSONObject(response.getMessageCode());
                        if ("000".equals(json.getString("code"))) {
                            contentPromotion = String.format(contentPromotion, gift.getGiftName());
                            if (!DataUtil.isNullOrEmpty(contentPromotion)) {
                                smsService.sendUnicode(isdn, contentPromotion);
                            }
                            if ("ON".equals(save)) {
                                logger.info("Start insert exchangeScoreHistory");
                                ExchangeScoreHistory exchangeScoreHistory = new ExchangeScoreHistory();
                                exchangeScoreHistory.setGiftId(giftId);
                                exchangeScoreHistory.setIsdn(isdn);
                                exchangeScoreHistory.setExchangeScoreHistoryId(DataUtil.randomUUID());
                                exchangeScoreHistory.setExchangeCode(exchangeCode);
                                exchangeScoreHistory.setGifrQrCode(generateQrCode);
                                exchangeScoreHistory.setProgramCode(logGaming.getGameCode());
                                exchangeScoreHistory.setDateTime(currDate);
                                exchangeScoreHistory.setExchangeDate(currDate);
                                String id = dao.save(exchangeScoreHistory);
                            }

                            //cap nhat log game
                            logger.info("Start update status logGaming");
                            logGaming.setStatus(1L);
                            logGaming.setUpdateDate(currDate);
                            dao.update(logGaming);
                            // thưc hiện trừ kho 
                            logger.info("Start update gift (tru kho) ");
                            gift.setQuantity(gift.getQuantity() - 1L);
                            dao.update(gift);
                            return responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", contentPromotion);
                        } else {
                            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                        }
                    }
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                } else {
                    logger.info("SERVICE CHUA DUOC MO - CALL_SERVICE = OFF ! MO SERVICE TRONG FILE CONFIG  >> PARAM CALL_SERVICE ");
                    contentPromotion = String.format(contentPromotion, gift.getGiftName());
                    if (!DataUtil.isNullOrEmpty(contentPromotion)) {
                        smsService.sendUnicode(isdn, contentPromotion);
                    }
                    return responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", contentPromotion);
                }
            } else {
                String coupon = configUtils.getMessage("COUPON").trim().toUpperCase();
                String bigGift = configUtils.getMessage("BIG_GIFT").trim().toUpperCase();
                String special = configUtils.getMessage("SPAECIAL").trim().toUpperCase();
                if (special.equals(giftType.getGiftTypeName().trim().toUpperCase())) {
                    msg = responseUtil.getMessage("myMetfone.game.lucky.specialgift." + language);
                    content = responseUtil.getMessage("myMetfone.game.lucky.specialgift.sms." + language);
                } else if (coupon.contains(giftType.getGiftTypeName().trim().toUpperCase())) {
                    msg = responseUtil.getMessage("myMetfone.game.lucky.coupon." + language, gift.getGiftName(), null, null, null);
                    content = responseUtil.getMessage("myMetfone.game.lucky.coupon.sms." + language, gift.getGiftName(), null, null, null);
                } else if (bigGift.contains(giftType.getGiftTypeName().trim().toUpperCase())) {
                    msg = responseUtil.getMessage("myMetfone.game.lucky.biggift." + language, gift.getGiftName(), null, null, null);
                    content = responseUtil.getMessage("myMetfone.game.lucky.biggift.sms." + language, gift.getGiftName(), null, null, null);
                }
                exchangeCode = DataUtil.createOrderCode();
                generateQrCode = DataUtil.generateQrCode(exchangeCode);
                if (DataUtil.isNullOrEmpty(generateQrCode)) {
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
                }
                //todo thực hiện nhắn tin cho khách hang
                if (!DataUtil.isNullOrEmpty(content)) {
                    smsService.sendUnicode(isdn, content);
                }
                if ("ON".equals(save)) {
                    logger.info("Start insert exchangeScoreHistory");
                    ExchangeScoreHistory exchangeScoreHistory = new ExchangeScoreHistory();
                    exchangeScoreHistory.setGiftId(giftId);
                    exchangeScoreHistory.setIsdn(isdn);
                    exchangeScoreHistory.setExchangeScoreHistoryId(DataUtil.randomUUID());
                    exchangeScoreHistory.setExchangeCode(exchangeCode);
                    exchangeScoreHistory.setGifrQrCode(generateQrCode);
                    exchangeScoreHistory.setProgramCode(logGaming.getGameCode());
                    exchangeScoreHistory.setDateTime(currDate);
                    exchangeScoreHistory.setExchangeDate(currDate);
                    String id = dao.save(exchangeScoreHistory);
                }
//            if (DataUtil.isNullOrEmpty(id)) {
//                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
//            }
                logGaming.setStatus(1L);
                logGaming.setUpdateDate(currDate);
                dao.update(logGaming);
                // thưc hiện trừ kho 
                gift.setQuantity(gift.getQuantity() - 1L);
                dao.update(gift);
                IsdnInfoBean infoBean = new IsdnInfoBean();
                infoBean.setCode(exchangeCode);
                infoBean.setQrCode(generateQrCode);
                bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, "Success", msg);
                bean.setWsResponse(infoBean);
                return bean;
            }
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean giftCategory(RequestBean request, String language
    ) {
        logger.info("Start business giftCategory off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
//            List<GiftType> list = dao.getGiftType("'COUPON','SPAECIAL_GIFT','BIG_GIFT'");
//            List<Object> listObj = new ArrayList<>();
//            
//            if (!DataUtil.isNullOrEmpty(list)) {
//                for (GiftType giftType : list) {
//                    List<Object> listGift = dao.getListGift(giftType.getId());
//                    GiftGroupDTO groupDto = new GiftGroupDTO();
//                    groupDto.setGroupId(giftType.getId());
//                    groupDto.setGroupName(giftType.getGiftTypeName());
//                    groupDto.setListItem(listGift);
//                    listObj.add(groupDto);
//                }
//            }
            String spaecialGift = configUtils.getMessage("SPAECIAL").trim();
            String coupon = configUtils.getMessage("COUPON").trim();
            String bigGift = configUtils.getMessage("BIG_GIFT").trim();
//            String telecom = configUtils.getMessage("TELECOM").trim();
            String typeGame = configUtils.getMessage("TYPE_LUCKY_GAME").trim();
            String spaecialGiftTypeId = configUtils.getMessage("SPAECIAL_ID").trim();
            String couponTypeId = configUtils.getMessage("COUPON_ID").trim();
            String bigGiftTypeId = configUtils.getMessage("BIG_GIFT_ID").trim();
            String telecomTypeId = configUtils.getMessage("TELECOM_ID").trim();
            String emoneyTypeId = configUtils.getMessage("EMONEY_ID").trim();
            String promotionId = configUtils.getMessage("PROMOTION_ID").trim();
            List<Object> listObj = new ArrayList<>();
            //Lấy giai dac biet 
            GiftGroupDTO spaecial = new GiftGroupDTO();
            spaecial.setGroupId(spaecialGiftTypeId);
            spaecial.setGroupName(spaecialGift);
            List<Object> listSpaecialGift = dao.getListGiftForLuckyGame(typeGame, spaecialGiftTypeId);
            spaecial.setListItem(listSpaecialGift);
            listObj.add(spaecial);

            //Lấy giai coupon 
            GiftGroupDTO couponGroup = new GiftGroupDTO();
            couponGroup.setGroupId(couponTypeId);
            couponGroup.setGroupName(coupon);
            List<Object> listCoupon = dao.getListGiftForLuckyGame(typeGame, couponTypeId);
            couponGroup.setListItem(listCoupon);
            listObj.add(couponGroup);

            //Lấy giai hien vat 
            GiftGroupDTO bigGiftGroup = new GiftGroupDTO();
            bigGiftGroup.setGroupId(bigGiftTypeId);
            bigGiftGroup.setGroupName(bigGift);
            List<Object> listBigGift = dao.getListGiftForLuckyGame(typeGame, bigGiftTypeId);
            bigGiftGroup.setListItem(listBigGift);
            listObj.add(bigGiftGroup);

            //Lấy giai emoney 
            List<GiftType> listGiftType = dao.getListGiftTypeForGame(typeGame, emoneyTypeId);
            if (!DataUtil.isNullOrEmpty(listGiftType)) {
                for (GiftType giftType : listGiftType) {
                    GiftGroupDTO eMoneyGroup = new GiftGroupDTO();
                    eMoneyGroup.setGroupId(giftType.getId());
                    eMoneyGroup.setGroupName(giftType.getGiftTypeName());
                    List<Object> listeMoneyGroup = dao.getListGiftByGiftTypeId(giftType.getId());
                    if (!DataUtil.isNullOrEmpty(listeMoneyGroup)) {
                        eMoneyGroup.setListItem(listeMoneyGroup);
//                        eMoneyGroup.setListItem(listeMoneyGroup);
                    }
                    listObj.add(eMoneyGroup);
                }
            }
            //Lấy giải spin va point
            List<GiftType> listGiftTypePromotion = dao.getListGiftTypeForGame(typeGame, promotionId);
            if (!DataUtil.isNullOrEmpty(listGiftTypePromotion)) {
                for (GiftType giftType : listGiftTypePromotion) {
                    GiftGroupDTO promotion = new GiftGroupDTO();
                    promotion.setGroupId(giftType.getId());
                    promotion.setGroupName(giftType.getGiftTypeName());
                    List<Object> listPromotion = dao.getListGiftByGiftTypeId(giftType.getId());
                    if (!DataUtil.isNullOrEmpty(listPromotion)) {
                        promotion.setListItem(listPromotion);
//                        eMoneyGroup.setListItem(listeMoneyGroup);
                    }
                    listObj.add(promotion);
                }
            }

            //Lay giai vien thong
            List<LoyaltyTelcoGift> loyaltyTelcoGifts = dao.getLoyaltyTelcoGiftForLuckyGame(typeGame, telecomTypeId);
            List loyaltyTelcoGiftList = new ArrayList<>();
            if (!DataUtil.isNullOrEmpty(loyaltyTelcoGifts)) {
                Long quantity = Long.parseLong(configUtils.getMessage("MAX_QUANTITY", "1000").trim());
                Date expriedDate = DataUtil.addTime(new Date(), Integer.parseInt(configUtils.getMessage("EXPRIED_DATE").trim()), null, null, null, 0);
                int blockGift = Integer.parseInt(configUtils.getMessage("GIFTBLOCK", "0").trim());
                loyaltyTelcoGifts.forEach((object) -> {
                    LoyaltyTelcoGift loyaltyTelco = new LoyaltyTelcoGift();
                    BeanUtils.copyProperties(object, loyaltyTelco);
                    loyaltyTelco.setQuantity(quantity);
                    loyaltyTelco.setExpireDate(expriedDate);
                    loyaltyTelco.setGiftId(loyaltyTelco.getGiftId() + "_" + new Date().getTime());
                    loyaltyTelcoGiftList.add(loyaltyTelco);
                    if (object.getGiftBlock() >= 2) {
                        Long value = loyaltyTelco.getExchangeValue();
                        for (int i = 1; i <= blockGift; i++) {
                            LoyaltyTelcoGift loyaltyTelcoGift = new LoyaltyTelcoGift();
                            BeanUtils.copyProperties(loyaltyTelco, loyaltyTelcoGift);
                            value += loyaltyTelcoGift.getExchangeValue();
                            loyaltyTelcoGift.setGiftId(loyaltyTelcoGift.getGiftId() + "_" + value.toString());
                            loyaltyTelcoGift.setExchangeValue(value);
                            loyaltyTelcoGiftList.add(loyaltyTelcoGift);
                        }
                    }
                });//                for (LoyaltyTelcoGift object : loyaltyTelcoGifts) {
//                    LoyaltyTelcoGift loyaltyTelco = new LoyaltyTelcoGift();
//                    BeanUtils.copyProperties(object, loyaltyTelco);
//                    loyaltyTelco.setQuantity(quantity);
//                    loyaltyTelco.setExpireDate(expriedDate);
//                    loyaltyTelco.setGiftId(loyaltyTelco.getGiftId() + "_" + new Date().getTime());
//                    loyaltyTelcoGiftList.add(loyaltyTelco);
//                    if (object.getGiftBlock() >= 2) {
//                        Long value = loyaltyTelco.getExchangeValue();
//                        for (int i = 1; i <= blockGift; i++) {
//                            LoyaltyTelcoGift loyaltyTelcoGift = new LoyaltyTelcoGift();
//                            BeanUtils.copyProperties(loyaltyTelco, loyaltyTelcoGift);
//                            value += loyaltyTelcoGift.getExchangeValue();
//                            loyaltyTelcoGift.setGiftId(loyaltyTelcoGift.getGiftId() + "_" + value.toString());
//                            loyaltyTelcoGift.setExchangeValue(value);
//                            loyaltyTelcoGiftList.add(loyaltyTelcoGift);
//                        }
//
//                    }
//                }
            }

            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setListGift(listObj);
            metfoneBean.setListLoyaltyTelcoGift(loyaltyTelcoGiftList);
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getFbInfo
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getFbInfo(RequestBean request, String language
    ) {
        logger.info("Start business getFbInfo off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("gameCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("gameCode").toString())) {
                logger.info("Error requesst : gameCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.gameCode.empty.", language);
            }

            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String gameCode = request.getWsRequest().get("gameCode").toString().trim();
            String fbId = "";
            fbId = request.getWsRequest().containsKey("fbId") ? request.getWsRequest().get("fbId").toString().trim() : fbId;
            List<Object> list = dao.getFbInfo(isdn, gameCode, fbId, true);
            if (DataUtil.isNullOrEmpty(list)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "mymetfone.game.notFound.empty.", language);
            }
            bean.setWsResponse(list);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * saveFbInfo
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean saveFbInfo(RequestBean request, String language
    ) {
        logger.info("Start business saveFbInfo off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("gameCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("gameCode").toString())) {
                logger.info("Error requesst : gameCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.gameCode.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("fbId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fbId").toString())) {
                logger.info("Error requesst : fbId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.fbId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("fbName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fbName").toString())) {
                logger.info("Error requesst : fbName is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.fbName.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String gameCode = request.getWsRequest().get("gameCode").toString().trim();
            String fbId = request.getWsRequest().get("fbId").toString().trim();
            String fbName = request.getWsRequest().get("fbName").toString().trim();
            FbInfo fbInfo = null;
            List<Object> list = dao.getFbInfo(isdn, gameCode, fbId, false);
            if (DataUtil.isNullOrEmpty(list)) {
                fbInfo = new FbInfo();
                fbInfo.setId(fbId);
                fbInfo.setGameCode(gameCode);
                fbInfo.setName(fbName);
                fbInfo.setIsdn(isdn);
                fbInfo.setStatus(1L);
                fbInfo.setCrateDate(new Date());
                dao.persist(fbInfo);
            } else {
                fbInfo = (FbInfo) list.get(0);
                fbInfo.setGameCode(gameCode);
                fbInfo.setName(fbName);
                fbInfo.setIsdn(isdn);
                fbInfo.setStatus(1L);
                fbInfo.setUpdateDate(new Date());
                dao.update(fbInfo);
            }
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getShop
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getShop(RequestBean request, String language
    ) {
        logger.info("Start business saveFbInfo off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            List<StoresBean> storesBeans = myvtgService.wsGetNearestStores(null, null);
            bean.setWsResponse(storesBeans);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * getAuthenkey
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean getAuthenkey(RequestBean request, String language
    ) {
        logger.info("Start business getAuthenkey off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("gameCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("gameCode").toString())) {
                logger.info("Error requesst : gameCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.gameCode.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String gameCode = request.getWsRequest().get("gameCode").toString().trim();
            String key = UUID.randomUUID().toString();
            AuthenLoginGame authen = new AuthenLoginGame();
            authen.setAuthenkey(key);
            authen.setIsdn(isdn);
            authen.setGameCode(gameCode);
            authen.setStatus(0L);
            authen.setCreateDate(new Date());
            authen.setLanguage(language);
            String authenkey = dao.save(authen);
            if (DataUtil.isNullOrEmpty(authenkey)) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            }
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setAuthenkey(authenkey);
            metfoneBean.setLinkGame(configUtils.getMessage("LINK_GAME"));
            metfoneBean.setFbShare(responseUtil.getMessage("mymetfone.shareFb.content." + language));
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * loginByToken
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean loginByToken(RequestBean request, String language
    ) {
        logger.info("Start loginByToken loginByToken off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            if (DataUtil.isNullObject(request.getWsRequest().get("authenkey")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("authenkey").toString())) {
                logger.info("Error requesst : authenkey is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            String authenkey = request.getWsRequest().get("authenkey").toString().trim();
            AuthenLoginGame authen = null;
            authen = dao.getIsdnInfoByAuthenKey(authenkey, false);
            // 
            if (DataUtil.isNullObject(authen) && !"88c46931-2d67-4662-90c1-cdb417e5aa2c".equals(authenkey)) {
                logger.info("Error requesst : authenkey is invalid ");
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            //fix by pass so a nhan de test
            if ("88c46931-2d67-4662-90c1-cdb417e5aa2c".equals(authenkey)) {
                authen = new AuthenLoginGame();
                authen.setIsdn("716028228");
                authen.setGameCode("LUCKY_LOYALTY");
            }

            SubMbDto dto = cmpreDao.getCustByIsdn(authen.getIsdn());
            if (DataUtil.isNullObject(dto)) {
                logger.info("Error requesst : authenkey is invalid (khong tim tháy thong tin thue bao với isdn tuong ung) ");
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "mymetfone.game.authenkey.empty.", language);
            }
            logger.info("Start call API adjustAccountPoint ");
            Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_LOYALTY_POINT);
            if (ws == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            logger.info("Requesst send getAccountPointInfo");
            String url = ws.getUrl() + authen.getIsdn();
            BaseResponsesDto response = WebServiceUtil.callApiGet(url);
            double sumPoint = 0D;
            Long currDate = new Date().getTime();
            if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                JSONObject jsonObj = new JSONObject(response.getMessageCode());
                if ("000".equals(jsonObj.getString("code")) && response.getMessageCode().contains("listAccountPoint")) {
                    InforAccountPoint infoBean = CommonUtil.convertJsonStringToObject(jsonObj.toString(), InforAccountPoint.class);
                    sumPoint = infoBean.getListAccountPoint().stream().filter((accountPoint) -> (accountPoint.getPointExpireDate().getTime() > currDate && "2".equals(accountPoint.getPointType()))).map((accountPoint) -> accountPoint.getPointValue()).reduce(sumPoint, (accumulator, _item) -> accumulator + _item);
                }
            }
            if (!"88c46931-2d67-4662-90c1-cdb417e5aa2c".equals(authenkey)) {
                authen.setStatus(1L);
                authen.setUpdateDate(new Date());
                dao.update(authen);
            }

            List<InviteGame> list = dao.getInviteGames(authen.getIsdn(), authen.getGameCode());
            Long freePlayGame = 0l;
            if (!DataUtil.isNullOrEmpty(list)) {
                Integer freePlay = Integer.parseInt(configUtils.getMessage("FREE_PLAY_GAME_INVITE", "2").trim());
                freePlayGame = (long) (list.size() * freePlay);
                list.stream().map((inviteGame) -> {
                    inviteGame.setFreePlayGame((long) freePlay);
                    return inviteGame;
                }).map((inviteGame) -> {
                    inviteGame.setUpdateDate(new Date());
                    return inviteGame;
                }).forEachOrdered((inviteGame) -> {
                    dao.update(inviteGame);
                });
            }

            List<ShareFb> listShare = dao.getFreePlayGame(authen.getIsdn(), authen.getGameCode(), Integer.parseInt(configUtils.getMessage("MAX_FREE_FB", "3").trim()));
            if (!DataUtil.isNullOrEmpty(listShare)) {
                Integer freePlayShare = Integer.parseInt(configUtils.getMessage("FREE_PLAY_GAME_FB", "1").trim());
                freePlayGame += (long) (list.size() * freePlayShare);
                listShare.stream().map((shareFb) -> {
                    shareFb.setFreePlayGame((long) freePlayShare);
                    return shareFb;
                }).map((shareFb) -> {
                    shareFb.setUpdateDate(new Date());
                    return shareFb;
                }).forEachOrdered((shareFb) -> {
                    dao.update(shareFb);
                });
            }
            String avatar = subService.getAvatar(authen.getIsdn());
            IsdnInfoBean info = new IsdnInfoBean();
            info.setIsdn(authen.getIsdn());
            info.setSex("M".equals(dto.getSex()) ? "Male" : "Female");
            info.setLoyalPoint((long) sumPoint);
            info.setLanguage(DataUtil.isNullOrEmpty(authen.getLanguage()) ? "en" : authen.getLanguage());
            info.setLinkShare(configUtils.getMessage("SHARE_GAME"));
            info.setFullName(dto.getCustName());
            info.setAvatar(DataUtil.isNullOrEmpty(avatar) ? "" : avatar);
            info.setFreePlayGame(freePlayGame);
            bean.setWsResponse(info);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getPointFreeFB(RequestBean request, String language
    ) {
        logger.info("Start business getPointFreeFB off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("gameCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("gameCode").toString())) {
                logger.info("Error requesst : gameCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.gameCode.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String gameCode = request.getWsRequest().get("gameCode").toString().trim();
            List<ShareFb> list = dao.getFreePlayGame(isdn, gameCode, Integer.parseInt(configUtils.getMessage("MAX_FREE_FB", "3").trim()));
            Long freePlayGame = 0l;
            if (!DataUtil.isNullOrEmpty(list)) {
                Integer freePlay = Integer.parseInt(configUtils.getMessage("FREE_PLAY_GAME_FB", "1").trim());
                freePlayGame = (long) (list.size() * freePlay);
                list.stream().map((shareFb) -> {
                    shareFb.setFreePlayGame((long) freePlay);
                    return shareFb;
                }).map((shareFb) -> {
                    shareFb.setUpdateDate(new Date());
                    return shareFb;
                }).forEachOrdered((shareFb) -> {
                    dao.update(shareFb);
                });
            }
            IsdnInfoBean info = new IsdnInfoBean();
            info.setFreePlayGame(freePlayGame);
            bean.setWsResponse(info);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean saveShareFb(RequestBean request, String language
    ) {
        logger.info("Start business saveShareFb off LuckyLoyaltyGameServiceImpl");
        try {
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("gameCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("gameCode").toString())) {
                logger.info("Error requesst : gameCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.gameCode.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("fbId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fbId").toString())) {
                logger.info("Error requesst : fbId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.fbId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("fbName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("fbName").toString())) {
                logger.info("Error requesst : fbName is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.fbName.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString().trim());
            String gameCode = request.getWsRequest().get("gameCode").toString().trim();
            String fbId = request.getWsRequest().get("fbId").toString().trim();
            String fbName = request.getWsRequest().get("fbName").toString().trim();
            ShareFb shareFb = new ShareFb();
            shareFb.setIdFb(fbId);
            shareFb.setGameCode(gameCode);
            shareFb.setName(fbName);
            shareFb.setIsdn(isdn);
            shareFb.setStatus(1L);
            shareFb.setCrateDate(new Date());
            dao.insert(shareFb);
            return bean;
        } catch (Exception e) {
            logger.info("Exception" + e);
            // todo update log
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /**
     * Check phan quyen vao test hoac public cac chuong trinh theo programCode
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean checkUseGame(RequestBean request, String language
    ) {
        logger.info("Start business checkUserGame off MyMetfoneBusinessService");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("programCode")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("programCode").toString())) {
                logger.info("Error requesst : programCode is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "metfone.programcode.error.", language);
            }
            String isdn = DataUtil.fomatIsdn855(request.getWsRequest().get("isdn").toString());
            String programCode = request.getWsRequest().get("programCode").toString().trim().toUpperCase();
            BaseResponseBean bean = null;
            MetfoneBean metfoneBean = new MetfoneBean();
            String programGame = configUtils.getMessage("PROGRAM_GAME", "LUCKY_LOYALTY").trim().toUpperCase();
            String programComplaint = configUtils.getMessage("PROGRAM_COMPLAINT", "COMPLAINT").trim().toUpperCase();
            bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, DES_SUCC, language);
            metfoneBean.setPermission(1L);
            if (programCode.equals(programGame)) {
                String checkTestGame = myvtgService.getAppParam(Constants.OPEN_TEST_GAME).trim().toUpperCase();
                logger.info("OPEN_TEST_GAME:" + checkTestGame);
                if ("ON".equals(checkTestGame)) {
                    if (!dao.checkUseProgramMyMetfone(isdn, programCode)) {
                        bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_FAIL, "metfone.no.permission.", language);
                        metfoneBean.setPermission(0L);
                    }
                }
            } else if (programCode.equals(programComplaint)) {
                String checkTestComplaint = myvtgService.getAppParam(Constants.OPEN_TEST_COMPLAINT).trim().toUpperCase();
                logger.info("OPEN_TEST_COMPLAINT:" + checkTestComplaint);
                if ("ON".equals(checkTestComplaint)) {
                    if (!dao.checkUseProgramMyMetfone(isdn, programCode)) {
                        bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_FAIL, DES_FAIL, language);
                        metfoneBean.setPermission(0L);
                    }
                }
            } else {
                logger.info("programcode khong thuoc cau hinh cho phep.Current input programCode :" + programCode);
                bean = responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_FAIL, "metfone.programcode.error.", language);
                metfoneBean.setPermission(0L);
            }
            bean.setWsResponse(metfoneBean);
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
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
            String channel = configUtils.getMessage("MY_METFONE");
            int result = smsDao.insertMT(DataUtil.fomatIsdn855(msisdn), messae, channel);
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return 0;
    }
}
