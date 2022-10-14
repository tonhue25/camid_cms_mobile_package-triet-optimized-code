package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.ServiceOfService;
import co.siten.myvtg.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/${version}/service")
public class ServiceController extends BaseController {

    private static final Logger logger = Logger.getLogger(ServiceGroupController.class);
    @Autowired
    ServiceOfService serviceOfService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "wsAddService", method = RequestMethod.POST)
    public ResponseEntity<?> wsAddService(@RequestBody RequestBean request) {
        logger.info("### Start wsAddService API of ServiceController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceOfService.addOrUpdateService(request, language, username);
            logger.info("### End wsAddService API of ServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsUpdateService", method = RequestMethod.POST)
    public ResponseEntity<?> wsUpdateService(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateService API of ServiceController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceOfService.addOrUpdateService(request, language, username);
            logger.info("### End wsUpdateService API of ServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsChangeActiveStatusService", method = RequestMethod.POST)
    public ResponseEntity<?> wsChangeActiveStatusService(@RequestBody RequestBean request) {
        logger.info("### Start wsChangeActiveStatusService API of ServiceController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            Long id =  Long.parseLong(request.getWsRequest().get("id").toString());
            if (DataUtil.isNullOrEmpty(id)) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceOfService.changeActive(id, language, username);
            logger.info("### End wsChangeActiveStatusService API of ServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsDeleteService", method = RequestMethod.POST)
    public ResponseEntity<?> wsDeleteService(@RequestBody RequestBean request) {
        logger.info("### Start wsDeleteService API of ServiceController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            Long id =  Long.parseLong(request.getWsRequest().get("id").toString());
            if (DataUtil.isNullOrEmpty(id)) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceOfService.deleteService(id, language, username);
            logger.info("### End wsDeleteService API of ServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsGetServices", method = RequestMethod.POST)
    public ResponseEntity<?> wsGetServices(@RequestBody RequestBean request) {
        logger.info("### Start wsGetServices API of ServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if(DataUtil.isNullObject(authorizedToken(request))){
//                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
//            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceOfService.getServices(request, language);
            logger.info("### End wsGetServices API of ServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }
}
