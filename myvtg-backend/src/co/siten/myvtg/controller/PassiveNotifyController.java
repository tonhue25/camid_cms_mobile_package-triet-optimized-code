/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.PassiveNotifyService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * PassiveNotifyController
 *
 * @author partner7
 */
@RestController
@RequestMapping("/api/${version}/accounts")
public class PassiveNotifyController extends BaseController {

    private static final Logger logger = Logger.getLogger(PassiveNotifyController.class);
    @Autowired
    PassiveNotifyService service;
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    ConfigUtils configUtils;
    private static final String LANGUAGE = "language";

    @RequestMapping(value = "/wsGetListGames", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListNotification(@RequestBody RequestBean request) {
        logger.info("Start wsGetListGames API of PassiveNotifyController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getGames(request, LANGUAGE);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetListGameCategory", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListGameCategory(@RequestBody RequestBean request) {
        logger.info("Start wsGetListGameCategor API of PassiveNotifyController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getGameCategories(request, LANGUAGE);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
    
     @RequestMapping(value = "/wsGetListGameCategoryForCMS", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListGameCategoryForCMS(@RequestBody RequestBean request) {
        logger.info("Start wsGetListGameCategoryForCMS API of PassiveNotifyController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getGameCategoriesForCMS(request, LANGUAGE);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
    
    @RequestMapping(value = "wsGetGameById", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetGameById(@RequestBody RequestBean request){
        logger.info("Start wsGetGameById API of PassiveNotifyController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getGameById(request, LANGUAGE);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
    
    @RequestMapping(value = "wsGetGameCategoryById", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetGameCategoryById(@RequestBody RequestBean request){
        logger.info("Start wsGetGameCategoryById API of PassiveNotifyController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.getGameCategoryById(request, LANGUAGE);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
}

