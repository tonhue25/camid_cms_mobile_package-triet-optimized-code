/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.CmpreDao;
import co.siten.myvtg.dao.RedeemDao;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import co.siten.myvtg.util.WebServiceUtil;
import com.viettel.common.ExchMsg;
import com.vtc.provisioning.client.Exchange;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * RedeemServiceImpl
 *
 * @author partner7
 */
@Service("RedeemService")
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
@PropertySource(value = {"classpath:config.properties"})
public class RedeemServiceImpl implements RedeemService {

    private static final Logger logger = Logger.getLogger(RedeemServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    private static final Long NOT_EXIST = -1L;
    private static final Long REDEEM_ALREADY = 1L;
    private static final Long NOT_REDEEM = 0L;

    @Autowired
    private Environment environment;

    @Autowired
    private CmpreDao cmpre2Dao;

    @Autowired
    private RedeemDao dao;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    ConfigUtils configUtils;

    @Autowired
    SmsService smsService;

    @Override
    public BaseResponseBean checkShowPopUpTet(RequestBean request, String language) {
        logger.info("### Start business checkShowPopUpTet of RedeemServiceImpl");
        if (request.getWsRequest().get("camid") == null) {
            logger.info("Error request : camid is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.camid.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
            logger.info("Error request : deviceId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.saveInfo.deviceId.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        String camid = request.getWsRequest().get("camid").toString();
        //Validate for Android, sometime Android sent camid = 0 instead of empty, =))))))) lol
        if ("0".equals(camid)) {
            camid = "";
        }
        String deviceId = request.getWsRequest().get("deviceId").toString().trim();

        try {
            // Check events available
            boolean isShowPopup = cmpre2Dao.checkServiceApParamIsWorking("TET_2022");
            if (isShowPopup) {
                //Check isdn first time login app?
                //If true then show
                isShowPopup = dao.isFirstTimeOpenApp(camid, deviceId);
                //if this is first time open app
                if (isShowPopup) {
                    //Save this record, to check for another time open app again
                    Long id = dao.getSequence("CAMID_FIRST_TIME_OPEN_SEQ");
                    dao.saveEventFirstTimeOpenApp(id, camid, deviceId);
                }
            }

            Map<String, Boolean> getGiftMap = new HashMap<>();
            getGiftMap.put("isShowPopupTet", isShowPopup);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setUserMsg("Success");
            bean.setWsResponse(getGiftMap);
            logger.info("### End business checkShowPopUpTet of RedeemServiceImpl");
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while checkShowPopUpTet ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean checkGetGiftTet(RequestBean request, String language) {
        logger.info("### Start business checkGetGiftTet of RedeemServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
        }
        if (request.getWsRequest().get("camid") == null) {
            logger.info("Error request : camid is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.camid.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
            logger.info("Error request : deviceId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.saveInfo.deviceId.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        String isdn = request.getWsRequest().get("isdn").toString().trim();
        isdn = DataUtil.fomatIsdn(isdn);
        String camid = request.getWsRequest().get("camid").toString();
        String deviceId = request.getWsRequest().get("deviceId").toString().trim();
        String listIsdnMetfone = environment.getProperty("METFONE_ISDN", "97,88,71,31,60,66,67,68,90");
        boolean isMetfoneNumber = false;
        //Check metfone isdn
        if (!StringUtils.isEmpty(listIsdnMetfone)) {
            String subIsdn = isdn.substring(0, 2);
            if (listIsdnMetfone.contains(subIsdn)) {
                /*isdn's metfone*/
                isMetfoneNumber = true;
            }
        }
        Map<String, Object> getGiftMap = new HashMap<>();
        if (!isMetfoneNumber) {
            getGiftMap.put("errorCode", Constants.ERROR_PARAMETER_INVALID);
            getGiftMap.put("messageCode", environment.getProperty("camid.tet.event.2022.notMetfone." + language));

            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Fail");
            bean.setUserMsg("Fail");
            bean.setWsResponse(getGiftMap);
            logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
            return bean;
        }
        //Validate for Android, sometime Android sent camid = 0 instead of empty, =))))))) lol
        if ("0".equals(camid)) {
            camid = "";
        }
        try {
            Long statusRedeem = dao.isCheckRedeemAlreadyWithCamidAndIsdn(isdn, camid);
            if (NOT_EXIST.equals(statusRedeem)) {
                Long isdnExistTime = dao.checkIsdnExistInDb(isdn);
                if (isdnExistTime == 0L) {
                    //if not redeem yet => insert record to prepare redeem,
                    Long id = dao.getSequence("CAMID_REDEEM_GIFT_TET_SEQ");
                    dao.savePrepareRedeem(id, isdn, camid, deviceId);
                }
            }
            if (NOT_REDEEM.equals(statusRedeem)) {
                dao.updatePrepareRedeem(isdn, camid, deviceId);
            }
            getGiftMap.put("errorCode", 0);
            getGiftMap.put("isGetGift", (REDEEM_ALREADY.equals(statusRedeem)));
            getGiftMap.put("messageCode", REDEEM_ALREADY.equals(statusRedeem) ? environment.getProperty("camid.tet.event.2022.redeem.hoi." + language) : "Success");
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setUserMsg("Success");
            bean.setWsResponse(getGiftMap);
            logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while checkShowPopUpTet ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean redeemGiftTet(RequestBean request, String language) {
        logger.info("### Start business redeemGiftTet of RedeemServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.isdn.empty.", language);
        }
        String isdn = request.getWsRequest().get("isdn").toString().trim();
        isdn = DataUtil.fomatIsdn(isdn);
        BaseResponseBean bean = new BaseResponseBean();
        Map<String, Object> response = new HashMap<>();
        try {

            //check event is working
            boolean isEventWorking = cmpre2Dao.checkServiceApParamIsWorking("TET_2022");
            if (!isEventWorking) {
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.tet.event.2022.redeem.off.", language);
                response.put("errorCode", Constants.ERROR_PARAMETER_INVALID);
                response.put("messageCode", environment.getProperty("camid.tet.event.2022.redeem.off." + language));

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
                bean.setWsResponse(response);
                logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
                return bean;
            }
            // check again if this isdn redeem already
            Long statusRedeem = dao.isCheckRedeemAlready(isdn);
            if (REDEEM_ALREADY.equals(statusRedeem)) {
//                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.tet.event.2022.redeem.hoi.", language);
                response.put("errorCode", Constants.ERROR_PARAMETER_INVALID);
                response.put("messageCode", environment.getProperty("camid.tet.event.2022.redeem.hoi." + language));

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
                bean.setWsResponse(response);
                logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
                return bean;
            }
            if (NOT_EXIST.equals(statusRedeem)) {
                response.put("errorCode", Constants.ERROR_PARAMETER_INVALID);
                response.put("messageCode", environment.getProperty("camid.tet.event.2022.notExist." + language));

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
                bean.setWsResponse(response);
                logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
                return bean;
            }

            String listIsdnMetfone = environment.getProperty("METFONE_ISDN", "97,88,71,31,60,66,67,68,90");
            boolean isMetfoneNumber = false;
            if (!StringUtils.isEmpty(listIsdnMetfone)) {
                String subIsdn = isdn.substring(0, 2);
                if (listIsdnMetfone.contains(subIsdn)) {
                    /*isdn's metfone*/
                    isMetfoneNumber = true;
                }
            }

            if (!isMetfoneNumber) {
                response.put("errorCode", Constants.ERROR_PARAMETER_INVALID);
                response.put("messageCode", environment.getProperty("camid.tet.event.2022.notExist." + language));

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
                bean.setWsResponse(response);
                logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
                return bean;
            }

            //Call provisioning
            String numberOfMB = configUtils.getMessage("TET_2022_MB");
            String numberOfDay = configUtils.getMessage("TET_2022_DAY");
            String balance = configUtils.getMessage("TET_2002_BALANCE");
            String type = configUtils.getMessage("TET_2022_TYPE");
            String error = "";
            Exchange exchange = new Exchange();
            ExchMsg responseCall = null;
            responseCall = WebServiceUtil.callProExchange(exchange, isdn, numberOfMB, Integer.valueOf(numberOfDay), cmpre2Dao, type, balance);
            if (DataUtil.isNullObject(responseCall)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            }
            error = responseCall.getError();
            logger.info("### Call provisioning with error return= " + error);
            if ("0".equals(error) || "000".equals(error)) {
                dao.isSaveLogSuccess(isdn, 1L);

                response.put("errorCode", Constants.ERROR_SUCCESS);
                response.put("messageCode", environment.getProperty("camid.tet.event.2022." + language));

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(response);

                //Send SMS for customer
                logger.info("### Start send SMS for Client redeem success");
                smsService.connectServer();
                String content = responseUtil.getMessage("camid.tet.event.2022." + language, null, null, null, null);
                smsService.sendUnicode(isdn, content);

                logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
                return bean;
            } else {
                //Save log after bonus  fail
                dao.isSaveLogSuccess(isdn, 0L);

                response.put("errorCode", Constants.ERROR_PARAMETER_INVALID);
                response.put("messageCode", environment.getProperty("camid.tet.event.2022.error." + language));

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
                bean.setWsResponse(response);
                logger.info("### End business checkGetGiftTet of RedeemServiceImpl");
                return bean;
            }
        } catch (Exception e) {
            logger.error("An error occured while checkShowPopUpTet ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

}
