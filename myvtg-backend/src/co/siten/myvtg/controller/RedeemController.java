/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.RedeemService;
import co.siten.myvtg.util.CommonUtil;
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
 * RedeemController
 *
 * @author partner7
 */
@RestController
@RequestMapping("/api/${version}/redeem")
public class RedeemController extends BaseController {

    private static final Logger logger = Logger.getLogger(RedeemController.class);
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    RedeemService service;

    @RequestMapping(value = "wsCheckShowPopupTet", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckShowPopupTet(@RequestBody RequestBean request) {
        logger.info("### Start wsCheckShowPopupTet API of RedeemController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkShowPopUpTet(request, language);
            logger.info("### End wsCheckShowPopupTet API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "wsCheckGetGiftTet", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckGetGiftTet(@RequestBody RequestBean request) {
        logger.info("### Start wsCheckGetGiftTet API of RedeemController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.checkGetGiftTet(request, language);
            logger.info("### End wsCheckGetGiftTet API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "wsRedeemGiftTet", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsRedeemGiftTet(@RequestBody RequestBean request) {
        logger.info("### Start wsRedeemGiftTet API of RedeemController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = service.redeemGiftTet(request, language);
            logger.info("### End wsRedeemGiftTet API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
   
}
