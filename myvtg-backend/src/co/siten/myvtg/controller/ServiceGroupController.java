package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.ServiceGroupService;
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
@RequestMapping("/api/${version}/service-group")
public class    ServiceGroupController extends BaseController {
    private static final Logger logger = Logger.getLogger(ServiceGroupController.class);
    @Autowired
    ServiceGroupService serviceGroupService;

    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "wsAddServiceGroup", method = RequestMethod.POST)
    public ResponseEntity<?> wsAddServiceGroup(@RequestBody RequestBean request) {
        logger.info("### Start wsAddServiceGroup API of ServiceGroupController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceGroupService.addOrUpdateServiceGroup(request, language, username);
            logger.info("### End wsAddServiceGroup API of ServiceGroupController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsUpdateServiceGroup", method = RequestMethod.POST)
    public ResponseEntity<?> wsUpdateServiceGroup(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateServiceGroup API of ServiceGroupController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceGroupService.addOrUpdateServiceGroup(request, language, username);
            logger.info("### End wsUpdateServiceGroup API of ServiceGroupController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsChangeActiveStatusServiceGroup", method = RequestMethod.POST)
    public ResponseEntity<?> wsChangeActiveStatusServiceGroup(@RequestBody RequestBean request) {
        logger.info("### Start wsChangeActiveStatusServiceGroup API of ServiceGroupController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.invalid.", language));
            }
            Long id = Long.parseLong(request.getWsRequest().get("id").toString());
            if (DataUtil.isNullOrEmpty(id)) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceGroupService.changeActive(id, language, username);
            logger.info("### End wsChangeActiveStatusServiceGroup API of ServiceGroupController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsDeleteServiceGroup", method = RequestMethod.POST)
    public ResponseEntity<?> wsDeleteServiceGroup(@RequestBody RequestBean request) {
        logger.info("### Start wsDeleteServiceGroup API of ServiceGroupController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.invalid.", language));
            }
            Long id = Long.parseLong(request.getWsRequest().get("id").toString());
            if (DataUtil.isNullOrEmpty(id)) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if(DataUtil.isNullObject(authorizedToken(request))){
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceGroupService.delete(id, language, username);
            logger.info("### End wsDeleteServiceGroup API of ServiceGroupController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsGetServiceGroups", method = RequestMethod.POST)
    public ResponseEntity<?> wsGetServiceGroups(@RequestBody RequestBean request) {
        logger.info("### Start wsGetServiceGroups API of ServiceGroupController");
        String language = "en";
        String username = "test";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.invalid.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if(DataUtil.isNullObject(authorizedToken(request))){
//                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
//            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = serviceGroupService.getServiceGroups(request, language);
            logger.info("### End wsGetServiceGroups API of ServiceGroupController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }
}
