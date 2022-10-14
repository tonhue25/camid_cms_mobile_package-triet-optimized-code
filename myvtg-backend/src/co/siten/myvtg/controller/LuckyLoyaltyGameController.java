/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.LuckyLoyaltyGameService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.ResponseUtil;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author buiquangdai
 */
@RestController
@RequestMapping("/api/${version}/luckyloyalty")
public class LuckyLoyaltyGameController extends BaseController {

    @Autowired
    LuckyLoyaltyGameService service;
    @Autowired
    ResponseUtil responseUtil;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MyMetfoneBusinessController.class);

    /**
     * wsLoginByToken
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsLoginByToken", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsLoginByToken(@RequestBody RequestBean request) {
        logger.info("Start wsLoginByToken API off LuckyLoyaltyGameController");
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
//            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            BaseResponseBean bean = service.loginByToken(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetAuthenkey
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetAuthenkey", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetAuthenkey(@RequestBody RequestBean request) {
        logger.info("Start wsGetAuthenkey API off LuckyLoyaltyGameController");
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
//            BaseResponseBean bean = new BaseResponseBean(Constants.ERROR_SUCCESS, "Success", "Success");
            BaseResponseBean bean = service.getAuthenkey(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsChangeLoyalty
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsChangeLoyalty", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsChangeLoyalty(@RequestBody RequestBean request) {
        logger.info("Start wsChangeLoyalty API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.changeLoyalty(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsChangeLoyalty
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsLogLuckyLoyalty", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsLogLuckyLoyalty(@RequestBody RequestBean request) {
        logger.info("Start wsLogLuckyLoyalty API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.logLuckyLoyalty(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsTelecomMunicationsAward
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsTelecomMunicationsAward", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsTelecomMunicationsAward(@RequestBody RequestBean request) {
        logger.info("Start wsTelecomMunicationsAward API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.telecomMunicationsAward(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }
    @RequestMapping(
        value = {"/wsTelecomMunicationsAwardForFriend"},
        produces = {"application/json"},
        method = {RequestMethod.POST}
    )
    public ResponseEntity<BaseResponseBean> wsTelecomMunicationsAwardForFriend(@RequestBody RequestBean request) {
        logger.info("Start wsTelecomMunicationsAwardForFriend API off LuckyLoyaltyController");
        String language = "en";

        try {
            if (!DataUtil.isNullObject(request) && !DataUtil.isNullOrEmpty(request.getWsRequest())) {
                if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                    language = request.getWsRequest().get("language").toString();
                }

                logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
                BaseResponseBean bean = this.service.telecomMunicationsAwardForFriend(request, language);
                return this.baseResponse(request, bean);
            } else {
                logger.info("Error requesst : null ");
                return this.baseResponse(request, this.responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
        } catch (Exception var4) {
            logger.error("Exception: ", var4);
            return this.baseResponse(request, this.responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsArtifactsAward
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsArtifactsAward", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsArtifactsAward(@RequestBody RequestBean request) {
        logger.info("Start wsArtifactsAward API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.artifactsAward(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGiftCategory
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGiftCategory", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGiftCategory(@RequestBody RequestBean request) {
        logger.info("Start wsGiftCategory API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.giftCategory(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsSaveFbinfo
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsSaveFbInfo", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSaveFbinfo(@RequestBody RequestBean request) {
        logger.info("Start wsSaveFbinfo API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.saveFbInfo(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetFbinfo
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetFbInfo", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetFbinfo(@RequestBody RequestBean request) {
        logger.info("Start wsGetFbinfo API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.getFbInfo(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsSaveFbinfo
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsShareFb", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsShareFb(@RequestBody RequestBean request) {
        logger.info("Start wsShareFb API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.saveShareFb(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetFbinfo
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetPointFreeFB", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetPointFreeFB(@RequestBody RequestBean request) {
        logger.info("Start wsGetFbinfo API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.getPointFreeFB(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetFbinfo
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetShop", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetShop(@RequestBody RequestBean request) {
        logger.info("Start wsGetShop API off LuckyLoyaltyGameController");
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
            BaseResponseBean bean = service.getShop(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsCheckUseGame
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckUseGame", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckUseGame(@RequestBody RequestBean request) {
        logger.info("Start wsCheckUseGame API off MyMetfoneController");
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
            BaseResponseBean bean = service.checkUseGame(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

}
