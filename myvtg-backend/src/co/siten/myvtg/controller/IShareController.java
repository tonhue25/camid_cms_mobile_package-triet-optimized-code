/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.MetfoneBean;
import co.siten.myvtg.service.MyMetfoneBusinessService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.ResponseUtil;
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
@RequestMapping("/api/${version}/ishare")
public class IShareController extends BaseController {

    @Autowired
    MyMetfoneBusinessService service;
    @Autowired
    ResponseUtil responseUtil;
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(IShareController.class);
    private static final String BAD_REQUEST = "myMetfone.Ishare.bad.request.";
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    private static final String DES_SUCC = "myMetfone.Ishare.des.succ.";

    /**
     * wsGetOtpIshare chi danh rieng cho nghiep vu ishare
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetOtpIshare", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetOTPIshare(@RequestBody RequestBean request) {
        logger.info("Start wsGetOTP API off IShareController");
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
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.getOTPIshare(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsGetOtpIshare.fail.", language));
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsTransferIshare", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsTransferIshare(@RequestBody RequestBean request) {
        logger.info("Start wsTransferIshare API off IShareController");
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
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.ishareTranfer(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "wsTransferIshare.fail.", language));
        }
    }

}
