package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.EmoneyService;
import co.siten.myvtg.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/${version}/emoney")
public class EmoneyController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(EmoneyController.class);
    @Autowired
    private EmoneyService service;
    @Autowired
    ConfigUtils configUtils;
    @Autowired
    ResponseUtil responseUtil;


    @RequestMapping(value = "/getAccountInfo", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> getAccountInfo(@RequestBody RequestBean request) {
        logger.info("#EmoneyController - getAccountInfo - start");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            if(language.equals("km") || language.equals("kh")){
                language = "km";
            }
            logger.info("#EmoneyController - getAccountInfo - request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.getAccountInfo(request, language);
            return baseResponse(request, baseResponseBean);

        } catch (Exception e) {
            logger.error("#EmoneyController - getAccountInfo - exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/confirmExchange", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> confirmExchane(@RequestBody RequestBean request) {
        logger.info("#EmoneyController - getAccountInfo - start");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            if(language.equals("km") || language.equals("kh")){
                language = "km";
            }
            logger.info("#EmoneyController - getAccountInfo - request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.confirm(request, language);
            return baseResponse(request, baseResponseBean);

        } catch (Exception e) {
            logger.error("#EmoneyController - getAccountInfo - exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/resendOtp", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> resendOtp(@RequestBody RequestBean request) {
        logger.info("#EmoneyController - getAccountInfo - start");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, BAD_REQUEST, language));
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            if(language.equals("km") || language.equals("kh")){
                language = "km";
            }
            logger.info("#EmoneyController - getAccountInfo - request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = service.resendOtp(request, language);
            return baseResponse(request, baseResponseBean);

        } catch (Exception e) {
            logger.error("#EmoneyController - getAccountInfo - exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language));
        }
    }


}
