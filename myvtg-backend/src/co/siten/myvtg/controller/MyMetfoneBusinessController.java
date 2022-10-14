
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.MetfoneBean;
import co.siten.myvtg.dto.ResponseDto;
import co.siten.myvtg.service.FTTHService;
import co.siten.myvtg.service.MyMetfoneBusinessService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.viettel.rsa.Utils.isNullOrEmpty;

/**
 * @author buiquangdai
 */
@RestController
@RequestMapping("/api/${version}/mymetfone")
public class MyMetfoneBusinessController extends BaseController {

    @Autowired
    MyMetfoneBusinessService service;
    @Autowired
    ConfigUtils configUtils;
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    FTTHService ftthService;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyMetfoneBusinessController.class);
    private static final String BAD_REQUEST = "myMetfone.Ishare.bad.request.";

    /**
     * wsGetOTPByService hàm láy otp chung cho từng nghiệp vụ
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetOTPByService", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetOTPByService(@RequestBody RequestBean request) {
        logger.info("Start wsGetOTP API off IShareController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();//[en]
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            /*format []*/
            if (language.contains("[")) {
                language = language.replace("[", "");
                language = language.replace("]", "");
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.getOTPByService(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }

    /**
     * wsGetListPrefixNumber
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetListPrefixNumber", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListPrefixNumber(@RequestBody RequestBean request) {
        logger.info("Start wsGetListPrefixNumber API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setPrefix(configUtils.getMessage("PREFIX", "88,97,71,31,60,66,67,68,90"));
            bean.setWsResponse(metfoneBean);
            return baseResponse(request, bean);

        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsSearchNumberToBuy
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsSearchNumberToBuy", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSearchNumberToBuy(@RequestBody RequestBean request) {
        logger.info("Start wsSearchNumberToBuy API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListNumberToBuy(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetListOrderNumberHistory
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetListOrderNumberHistory", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListOrderNumberHistory(@RequestBody RequestBean request) {
        logger.info("Start wsGetListPrefixNumber API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListOrderNumberHistory(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    /**
     * wsChangeIsdnKeepSim
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsChangeIsdnKeepSim", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsChangeIsdnKeepSim(@RequestBody RequestBean request) {
        logger.info("Start wsChangeIsdnKeepSim API off MyMetfoneController");

        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
//            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Content");
            BaseResponseBean bean = service.changeIsdnKeepSim(request, language);
            logger.info("Start response service lockIsdnByCus: " + dateFormat.format(new Date()));
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsChangeIsdnKeepSimTest", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsChangeIsdnKeepSimTest(@RequestBody RequestBean request) {
        logger.info("Start wsChangeIsdnKeepSim API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) || !DataUtil.isNullObject(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
//            BaseResponseBean bean = service.changeIsdnKeepSim(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsLockIsdnToBuy
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsLockIsdnToBuy", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsLockIsdnToBuy(@RequestBody RequestBean request) {
        logger.info("Start wsLockIsdnToBuy API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
//            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "The code has been sent to you via SMS, valid till " + dateFormat.format(addTime(new Date(), 24)));
            BaseResponseBean bean = service.lockIsdnToBuy(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    /**
     * wsGetOTPKeepChangeSim chi danh rieng cho ngiep vu KeepChangeSim
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetOtpMetfone", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetOTPKeepChangeSim(@RequestBody RequestBean request) {
        logger.info("Start wsGetOtpMetfone API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                String tempLanguage = request.getWsRequest().get("language").toString();
                if ("en".equals(tempLanguage) || "km".equals(tempLanguage)) {
                    language = tempLanguage;
                }
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.getOTPKeepChangeSim(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }

    /**
     * wsCheckPackageByISDN
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckPackageByISDN", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckPackageByISDN(@RequestBody RequestBean request) {
        logger.info("Start wsCheckPackageByISDN API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));

            BaseResponseBean baseResponseBean = service.checkPackageByISDN(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }

    /**
     * wsChargeHisInMonth
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsChargeHisInMonth", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> chargeHisInMonth(@RequestBody RequestBean request) {
        logger.info("Start chargeHisInMonth API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.chargeHisInMonth(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }

    /**
     * wsConfirmOTP
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsConfirmOTP", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsConfirmOTP(@RequestBody RequestBean request) {
        logger.info("Start wsConfirmOTP API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.confirmOTP(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }

    /**
     * wsSyncIsdnInfo
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsSyncIsdnInfo", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSyncIsdnInfo(@RequestBody RequestBean request) {
        logger.info("Start wsSyncIsdnInfo API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.syncIsdnInfo(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }

    /**
     * wsForgotPassword
     *
     * @param request
     * @return
     * @author daibq
     */
    @RequestMapping(value = "/wsForgotPassword", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsForgotPassword(@RequestBody RequestBean request) {
        logger.info("Start wsForgotPassword API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.wsForgotPassword(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetServicesByIsdn
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetAllUserServicesByIsdn", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetServicesByIsdn(@RequestBody RequestBean request) {
        logger.info("Start wsGetAllUserServicesByIsdn API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getAllUserServicesByIsdn(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckMetfoneCareByIsdn
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckMetfoneCareByIsdn", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckUseMetfoneCare(@RequestBody RequestBean request) {
        logger.info("Start wsCheckUseMetfoneCare API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if (DataUtil.isNullOrEmpty(language)) {
//                language = "en";
//            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkUseMetfoneCare(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsSendEmail
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsSendEmail", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSendEmail(@RequestBody RequestBean request) {
        logger.info("Start wsSendEmail API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.sendEmail(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

//    /**
//     * wsInviteViaSMS
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/wsInviteViaSMS", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
//    public ResponseEntity<ResponseDto> inviteUseMetfone(@RequestBody RequestBean request) {
//        logger.info("Start inviteUseMetfone API off MyMetfoneController");
//        String language = "en";
//        try {
//            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
//                logger.info("Error requesst : null ");
//                return baseResponse(request, responseUtil.response(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
//            }
//            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
//                language = request.getWsRequest().get("language").toString();
//            }
//            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
//            ResponseDto bean = service.inviteUseMetfone(request, language);
//            return baseResponse(request, bean);
//        } catch (Exception e) {
//            logger.error("Exception: ", e);
//            return baseResponse(request, responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
//        }
//    }

    /**
     * wsInviteViaSMSTest
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsInviteViaSMS", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> inviteUseMetfone(@RequestBody RequestBean request) {
        logger.info("Start wsInviteViaSMS API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.response(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            ResponseDto bean = service.inviteUseMetfone(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsDoRedeem
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsDoRedeem", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> wsDoRedeem(@RequestBody RequestBean request) {
        logger.info("Start wsDoRedeem API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.response(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            ResponseDto bean = service.doRedeemInviteOrLoginFirst(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckLogin
     *
     * @param request
     * @return
     */
//    @RequestMapping(value = "/wsCheckLogin", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
//    public ResponseEntity<ResponseDto> wsCheckLogin(@RequestBody RequestBean request) {
//        logger.info("Start wsCheckLogin API off MyMetfoneController");
//        String language = "en";
//        try {
//            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
//                logger.info("Error requesst : null ");
//                return baseResponse(request, responseUtil.response(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
//            }
//            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
//                language = request.getWsRequest().get("language").toString();
//            }
//            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
//            ResponseDto bean = service.checkLogin(request, language);
//            return baseResponse(request, bean);
//        } catch (Exception e) {
//            logger.error("Exception: ", e);
//            return baseResponse(request, responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
//        }
//    }

    /**
     * wsCheckLoginTest
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckLogin", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> wsCheckLogin(@RequestBody RequestBean request) {
        logger.info("Start wsCheckLogin API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.response(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            ResponseDto bean = service.checkLogin(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsValidateRedeem
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsValidateRedeem", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> wsValidateRedeem(@RequestBody RequestBean request) {
        logger.info("Start wsValidateRedeem API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.response(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            ResponseDto bean = service.validateRedeemInviteOrLoginFirst(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.response(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckUpdateInfomation
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckUpdateInfomation", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckUpdateInfomation(@RequestBody RequestBean request) {
        logger.info("Start wsCheckUpdateInfomation API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkUpdateInfomation(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckUpdateInfomation
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckIsNiceNumber", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckIsNiceNumber(@RequestBody RequestBean request) {
        logger.info("Start wsCheckIsNiceNumber API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkIsNiceNumber(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetComType
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetComType", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getComType(@RequestBody RequestBean request) {
        logger.info("Start wsGetComType API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getComType(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }


    @RequestMapping(value = "/wsGetComTypeCamID", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getComTypeCamID(@RequestBody RequestBean request) {
        logger.info("Start wsGetComType API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getComTypeCamID(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetComType
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsSubmitComplaintMyMetfone", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSubmitComplaintMyMetfone(@RequestBody RequestBean request) {
        logger.info("Start wsSubmitComplaintMyMetfone API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.submitComplaintMyMetfone(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetProcessList
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetProcessList", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetProcessList(@RequestBody RequestBean request) {
        logger.info("Start wsGetProcessList API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getProcessList(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetProcessListCamID
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetProcessListCamID", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetProcessListCamID(@RequestBody RequestBean request) {
        logger.info("Start wsGetProcessList API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getProcessListCamID(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetComplaintHistory
     *
     * @param request
     * @return
     */
//    @RequestMapping(value = "/wsGetComplaintHistory", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
//    public ResponseEntity<BaseResponseBean> wsGetComplaintHistory(@RequestBody RequestBean request) {
//        logger.info("Start wsGetComplaintHistory API off MyMetfoneController");
//        String language = "en";
//        try {
//            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
//                logger.info("Error requesst : null ");
//                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
//            }
//            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
//                language = request.getWsRequest().get("language").toString();
//            }
//            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
//            BaseResponseBean bean = service.getComplaintHistory(request, language);
//            return baseResponse(request, bean);
//        } catch (Exception e) {
//            logger.error("Exception: ", e);
//            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
//        }
//    }

    /**
     * wsReopenComplain
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsReopenComplain", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsReopenComplain(@RequestBody RequestBean request) {
        logger.info("Start wsReopenComplain API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.reopenComplain(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCloseComplain
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCloseComplain", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCloseComplain(@RequestBody RequestBean request) {
        logger.info("Start wsCloseComplain API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.closeComplain(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsRateComplain
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsRateComplain", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsRateComplain(@RequestBody RequestBean request) {
        logger.info("Start wsRateComplain API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.rateComplaint(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsTotalAmount
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsTotalDonateCovid", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsTotalAmountCovid(@RequestBody RequestBean request) {
        logger.info("Start wsTotalDonateCovid API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getTotalDonateCovid(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetDonateEmoney
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetDonateEmoney", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetPhoneRegisterEmoney(@RequestBody RequestBean request) {
        logger.info("Start wsGetDonateEmoney API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getDonateEmoney(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetDonateMocha
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetDonateMocha", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetPhoneRegisterMocha(@RequestBody RequestBean request) {
        logger.info("Start wsGetDonateMocha API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getDonateMocha(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetNews
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetCategoryNews", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetCategoryNews(@RequestBody RequestBean request) {
        logger.info("Start wsGetCategoryNews API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.wsGetCategoryNews(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetCaptcha
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetCaptcha", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetCaptcha(@RequestBody RequestBean request) {
        logger.info("Start wsGetCaptcha API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getCaptcha(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckDisplaySabayAndTickTox
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckDisplaySabayAndTiktok", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckDisplaySabayAndTiktok(@RequestBody RequestBean request) {
        logger.info("Start wsCheckDisplaySabayAndTiktok API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("productCode")) && DataUtil.isNullOrEmpty(request.getWsRequest().get("productCode").toString())) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.service.failed.", language));
            }
            String productCode = request.getWsRequest().get("productCode").toString();
            String tickTox = configUtils.getMessage("TICKTOX", "KIT_TT").trim().toUpperCase();
            String sabay = configUtils.getMessage("SABAY", "M4WK").trim().toUpperCase();
            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            MetfoneBean metfoneBean = new MetfoneBean();
            metfoneBean.setIsSabay(0L);
            metfoneBean.setIsTiktok(0L);
            if (tickTox.contains(productCode.trim().toUpperCase())) {
                metfoneBean.setIsTiktok(1L);
            }
            if (sabay.contains(productCode.trim().toUpperCase())) {
                metfoneBean.setIsSabay(1L);
            }
            bean.setWsResponse(metfoneBean);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetListMemberSabay
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetListMemberSabay", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListMemberSabay(@RequestBody RequestBean request) {
        logger.info("Start wsGetListMemberSabay API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListMemberSabay(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckAddMemberSabay
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckAddMemberSabay", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckAddMemberSabay(@RequestBody RequestBean request) {
        logger.info("Start wsCheckAddMemberSabay API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkAddMemberSabay(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsAddMemberSabay
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsAddMemberSabay", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsAddMemberSabay(@RequestBody RequestBean request) {
        logger.info("Start wsAddMemberSabay API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.addMemberSayBay(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsRemoveMemberSabay
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsRemoveMemberSabay", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsRemoveMemberSabay(@RequestBody RequestBean request) {
        logger.info("Start wsRemoveMemberSabay API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.removeMemberSabay(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetListMemberTickTox
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetListMemberTiktok", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListMemberTiktok(@RequestBody RequestBean request) {
        logger.info("Start wsGetListMemberTiktok API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListMemberTiktok(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckAddMemberTickTox
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckAddMemberTiktok", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckAddMemberTiktok(@RequestBody RequestBean request) {
        logger.info("Start wsCheckAddMemberTiktok API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkAddMemberTiktok(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsAddMemberTickTox
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsAddMemberTiktok", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsAddMemberTickTox(@RequestBody RequestBean request) {
        logger.info("Start wsAddMemberTiktok API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.addMemberTiktok(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsRemoveMemberTickTox
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsRemoveMemberTiktok", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsRemoveMemberTickTox(@RequestBody RequestBean request) {
        logger.info("Start wsRemoveMemberTiktok API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.removeMemberSabayTiktok(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsRemoveMemberTickTox
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetConsultant", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetConsultant(@RequestBody RequestBean request) {
        logger.info("Start wsGetConsultant API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getConsultant(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsPayAdvance
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsPayAdvance", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsPayAdvance(@RequestBody RequestBean request) {
        logger.info("Start wsPayAdvance API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.wsPayAdvance(request, language);
            return new ResponseEntity<>(bean, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return new ResponseEntity<>(responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/wsGetListNewsCovid", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListNewsCovid(@RequestBody RequestBean request) {
        logger.info("Start wsListNewsCovid API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getNewsCovid19List(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsAddNewsCovid", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsAddNewsCovid(@RequestBody RequestBean request) {
        logger.info("Start wsAddNewCovid API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.addNewsCovid19(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsUpdateNewsCovid", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateNewsCovid(@RequestBody RequestBean request) {
        logger.info("Start wsUpdateNewsCovid API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.updateNewsCovid19(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsDeleteNewsCovid", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsDeleteNewsCovid(@RequestBody RequestBean request) {
        logger.info("Start wsDeleteNewsCovid API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.deleteNewsCovid19(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetDetailNewsCovid", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetDetailNewsCovid(@RequestBody RequestBean request) {
        logger.info("Start wsGetDetailNewsCovid API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getDetailNewsCovid19(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    /**
     * wsCheckResgisterMyMetfone
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckResgisterMyMetfone", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckResgisterMyMetfone(@RequestBody RequestBean request) {
        logger.info("Start wsCheckResgisterMyMetfone API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkResgisterMyMetfone(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    //phuonghc 22062020
    @RequestMapping(value = "/wsGetListCodeOnFiredBaseConsole", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListCodeOnFiredBaseConsole(@RequestBody RequestBean request) {
        logger.info("Start wsGetListCodeOnFiredBaseConsole API of NotifyServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListCodeOnFiredBaseConsole(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    /**
     * wsCheckResgisterMyMetfone
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetSubPreInfo", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetSubInfo(@RequestBody RequestBean request) {
        logger.info("Start wsGetSubPreInfo API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getSubInfo(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    /**
     * getListProvince
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getListProvince", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getListProvince(@RequestBody RequestBean request) {
        logger.info("Start getListProvince API of NotifyServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListProvince(language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    /**
     * getListDistrict
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getListDistrict", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getListDistrict(@RequestBody RequestBean request) {
        logger.info("Start getListDistrict API of MyMetfoneBusinessController");
        String language = "en";
        String provinCode;
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if (isNullOrEmpty(request.getWsRequest().get("provinceCode"))) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.FTTH.province.fail.", null, language));
            }
            provinCode = request.getWsRequest().containsKey("provinceCode") ? request.getWsRequest().get("provinceCode").toString() : null;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListDistrict(provinCode, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    /**
     * getListPrecinct
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getListPrecinct", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getListPrecinct(@RequestBody RequestBean request) {
        logger.info("Start getListPrecinct API of MyMetfoneBusinessController");
        String language = "en";
        String provinCode;
        String districtCode;
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if (isNullOrEmpty(request.getWsRequest().get("provinceCode")) && isNullOrEmpty(request.getWsRequest().get("districtCode"))) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.FTTH.all.fail.", null, language));
            }
            if (isNullOrEmpty(request.getWsRequest().get("provinceCode"))) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.FTTH.province.fail.", null, language));
            }
            if (isNullOrEmpty(request.getWsRequest().get("districtCode"))) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.FTTH.district.fail.", null, language));
            }

            provinCode = request.getWsRequest().containsKey("provinceCode") ? request.getWsRequest().get("provinceCode").toString() : null;
            districtCode = request.getWsRequest().containsKey("districtCode") ? request.getWsRequest().get("districtCode").toString() : null;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListCommune(provinCode, districtCode, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    /*==================================VuDoan.altek============================================*/

    /**
     * getPackageInfor
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPackageInfor", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getPackageInfor(@RequestBody RequestBean request) {
        logger.info("Start getPackageInfor API of MyMetfoneBusinessController");
        String language = "en";
        Long speed;
        Long month;
        String packageInfo;
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            month = request.getWsRequest().containsKey("month") ? Long.parseLong(request.getWsRequest().get("month").toString()) : null;
            if (DataUtil.isNullObject(month)) {
                logger.info("Error month is null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getPackageInfor(month, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    /**
     * camIdInternetRegister
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/camIdRegisterInternet", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> camIdInternetRegister(@RequestBody RequestBean request, HttpServletRequest http) {
        logger.info("Start camIdRegisterInternet API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.camIdInternetRegister(request, language, http);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsGetAccountInfoForPayment", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetAccountInfoForPayment(@RequestBody RequestBean request) {
        logger.info("### Start wsGetAccountInfoForPayment API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getAccountInfoForPayment(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsGetPaymentHistory", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetPaymentHistory(@RequestBody RequestBean request) {
        logger.info("Start wsGetPaymentHistory API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getPaymentHistoryById(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsAddPaymentHistory", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsAddPaymentHistory(@RequestBody RequestBean request) {
        logger.info("Start wsAddPaymentHistory API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.addPaymentHistory(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsUpdatePaymentHistory", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdatePaymentHistory(@RequestBody RequestBean request) {
        logger.info("Start wsUpdatePaymentHistory API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.updatePaymentHistory(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }

    }

    @RequestMapping(value = "/getSummaryPackage", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getSummaryPackageest(@RequestBody RequestBean request) {
        logger.info("Start getSummaryPackage");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            BaseResponseBean bean = service.getSummaryPackage(language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));

        }
    }

    /**
     * forgetRequest
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/forgetRequest", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> forgetRequest(@RequestBody RequestBean request) {
        logger.info("Start forgetRequest API of MyMetfoneBusinessController");
        String language = "en";
        String isdn;

        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if (isNullOrEmpty(request.getWsRequest().get("isdn"))) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.FTTH.forget.fail.", null, language));
            }
            isdn = request.getWsRequest().containsKey("isdn") ? request.getWsRequest().get("isdn").toString() : null;
            if (DataUtil.isNullObject(isdn)) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.FTTH.forget.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));


            isdn = DataUtil.fomatIsdn(isdn);
            BaseResponseBean bean = ftthService.forgetRequest(isdn, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }


    /**
     * searchFTTHRequest
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/searchFTTHRequest", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> searchFTTHRequest(@RequestBody RequestBean request) {
        logger.info("Start searchFTTHRequest API of MyMetfoneBusinessController");
        String language = "en";
        String requestId;
        String isdn;

        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            requestId = request.getWsRequest().containsKey("requestId") ? request.getWsRequest().get("requestId").toString() : null;
            isdn = request.getWsRequest().containsKey("isdn") ? request.getWsRequest().get("isdn").toString() : null;
            if (DataUtil.isNullObject(requestId) && DataUtil.isNullObject(isdn)) {
                logger.info("Request  is null");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.ftth.requestId.empty.", "myMetfone.Ishare.bad.request.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = null;
            if (requestId != null) {
                bean = ftthService.searchFTTHRequest(requestId, language);
            } else if (isdn != null) {
                bean = ftthService.forgetRequest(isdn, language);
            }
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.des.fail.", language));
        }
    }

    //anjav
    @RequestMapping(value = "/wsGetComplaintHistory", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSearchComplaintHistory(@RequestBody RequestBean request) {
        logger.info("Start wsSearchComplaintHistory API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.filterComplaint(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsGetListAccountFtth", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListAccountFtth(@RequestBody RequestBean request) {
        logger.info("### Start wsGetAccountInfoForPayment API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.param.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListAccountFtthByIsdn(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    //anjav start
    @RequestMapping(value = "/wsGetListComplaintType", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListComplaintType(@RequestBody RequestBean request) {
        logger.info("Start wsGetListCompByGroupId API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListComplaintType(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsGetListComplaintGroupType", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListComplaintGroupType(@RequestBody RequestBean request) {
        logger.info("Start wsGetListComplaintGroupType API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getListComplaintGroupType(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }
    @RequestMapping(value = "/wsCheckComplaintNotification", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckComplaintNotification(@RequestBody RequestBean request) {
        logger.info("Start wsCheckComplaintNotification API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkComplaintNotification(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }
    @RequestMapping(value = "/wsUpdateComplaint", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateComplaint(@RequestBody RequestBean request) {
        logger.info("Start wsCheckComplaintNotification API off MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.updateComplaint(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }
    //anjav end

    /**
     * ChangeCardAddNew
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/ChangeCardAddNew", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> ChangeCardAddNew(@RequestBody RequestBean request) {
        logger.info("Start ChangeCardAddNewRequest API of MyMetfoneBusinessController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            // logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.ChangeCardAddNew(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBeanCompact(Constants.ERROR_SYSTEM, "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/exChangeCardGetlist", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> exChangeCardGetlist(@RequestBody RequestBean request) {
        logger.info("Start exChangeCardGetlist API of MyMetfoneController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.exChangeCardGetlist(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }
}
