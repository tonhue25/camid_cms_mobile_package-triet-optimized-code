package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.SubService;
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

@RestController
@RequestMapping("/api/${version}/sub-service")
public class SubServiceController extends BaseController {

    private static final Logger logger = Logger.getLogger(SubServiceController.class);
    @Autowired
    SubService subService;

    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "wsGetSubServices", method = RequestMethod.POST)
    public ResponseEntity<?> wsGetSubServices(@RequestBody RequestBean request) {
        logger.info("### Start wsGetSubServices API of SubServiceController");
        String language = "en";
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
            BaseResponseBean bean = subService.getSubServices(request, language);
            logger.info("### End wsGetSubServices API of SubServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsAddSubService", method = RequestMethod.POST)
    public ResponseEntity<?> wsAddSubServices(@RequestBody RequestBean request) {
        logger.info("### Start wsAddSubServices API of SubServiceController");
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
            BaseResponseBean bean = subService.addOrUpdateSubService(request, language, username);
            logger.info("### End wsAddSubServices API of SubServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsUpdateSubService", method = RequestMethod.POST)
    public ResponseEntity<?> wsUpdateSubService(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateSubService API of SubServiceController");
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
            BaseResponseBean bean = subService.addOrUpdateSubService(request, language, username);
            logger.info("### End wsUpdateSubService API of SubServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsChangeActiveStatusSubService", method = RequestMethod.POST)
    public ResponseEntity<?>wsChangeActiveStatusSubService(@RequestBody RequestBean request) {
        logger.info("### Start wsChangeActiveStatusSubService API of SubServiceController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            Long id = Long.parseLong(request.getWsRequest().get("id").toString());
            if (DataUtil.isNullOrEmpty(id)) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = subService.changeActive(id, language, username);
            logger.info("### End wsChangeActiveStatusSubService API of SubServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }

    }

    @RequestMapping(value = "wsDeleteSubService", method = RequestMethod.POST)
    public ResponseEntity<?>wsDeleteSubService(@RequestBody RequestBean request) {
        logger.info("### Start wsDeleteSubService API of SubServiceController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            Long id = Long.parseLong(request.getWsRequest().get("id").toString());
            if (DataUtil.isNullOrEmpty(id)) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = subService.delete(id, language, username);
            logger.info("### End wsDeleteSubService API of SubServiceController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }
}
