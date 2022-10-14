/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseRequest;
import co.siten.myvtg.bean.BaseRequest.ObjectRequest;
import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.exception.MyMetfoneException;
import co.siten.myvtg.model.myvtg.Sub;
import co.siten.myvtg.service.SubService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;

/**
 *
 * @author duyth
 */
@RestController
@RequestMapping("/api/${version}/subs")
public class SubController extends BaseController {

    private static final Logger logger = Logger.getLogger(SubController.class);

    @Autowired
    SubService subService;
    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "/wsUpdateSubscriber", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateSubscriber(@RequestBody BaseRequest<Sub> request) {
        String language = Constants.DEFAULT_LANGUAGE;
        Sub sub = null;

        try {
            ObjectRequest<Sub> wsRequest = request.getWsRequest();

            if (wsRequest == null) {
                return baseResponse(request, Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language);
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));

            language = wsRequest.getLanguage() == null ? Constants.DEFAULT_LANGUAGE : wsRequest.getLanguage();
            sub = wsRequest.getParams().get("sub");

            if (sub == null) {
                return baseResponse(request, Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language);
            }

            if (DataUtil.isNullOrEmpty(sub.getIsdn())) {
                return baseResponse(request, Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language);
            } else {
                sub.setIsdn(
                        DataUtil.fomatIsdn(
                                sub.getIsdn()
                        )
                );
            }

            if (sub.getAvatar() != null) {
                byte[] avatar = Base64.decode(sub.getAvatar());
                if (avatar.length > 300 * 1024) { // 300Kb
                    sub.setAvatar("");
                    return baseResponse(request, Constants.MAX_IMAGE_SIZE, "myMetfone.max.image.size.", "myMetfone.max.image.size.", language);
                }
            }

            subService.updateSub(sub);

            sub.setAvatar("");
            return baseResponse(request, Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.socialNetwork.succ.", language);
        } catch (MyMetfoneException metfoneException) {
            if (sub != null) {
                sub.setAvatar("");
            }
            logger.info("Bad request: " + messageUtil.getMessage(metfoneException.getMessage() + language));
            return baseResponse(request, metfoneException.getErrorCode(), metfoneException.getMessage(), metfoneException.getUserMsg(), language);
        } catch (Exception e) {
            if (sub != null) {
                sub.setAvatar("");
            }
            logger.error("Exception: ", e);
            return baseResponse(request, Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @RequestMapping(value = "/wsGetAvatar", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetAvatar(@RequestBody RequestBean request) {
        String language = Constants.DEFAULT_LANGUAGE;

        try {
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (wsRequest == null) {
                return baseResponse(request, Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language);
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));

            language = wsRequest.containsKey("language") ? wsRequest.get("language").toString() : Constants.DEFAULT_LANGUAGE;
            String isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : "";

            if (DataUtil.isNullOrEmpty(isdn)) {
                return baseResponse(request, Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language);
            }

            isdn = DataUtil.fomatIsdn(isdn);

            String fileString = subService.getAvatar(isdn);
            LinkedHashMap<String, Object> subRes = new LinkedHashMap<>();
            subRes.put("avatar", fileString);

            return baseResponse(request, Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language, subRes);
        } catch (MyMetfoneException metfoneException) {
            logger.info("Bad request: " + messageUtil.getMessage(metfoneException.getMessage() + language));
            return baseResponse(request, metfoneException.getErrorCode(), metfoneException.getMessage(), metfoneException.getUserMsg(), language);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @RequestMapping(value = "/wsGetUserInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetUserInfo(@RequestBody RequestBean request) {
        String language = Constants.DEFAULT_LANGUAGE;
        try {
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (wsRequest == null) {
                return baseResponse(request, Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language);
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            String isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : "";

            Map<String, Object> userInfo = subService.getUserInfo(isdn);
            return baseResponse(request, Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.des.succ.", language, userInfo);

        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    @RequestMapping(value = "/wsSubscriberGetOTP", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSubscriberGetOTP(@RequestBody RequestBean request) {
        logger.info("### Start wsSubscriberGetOTP API of ServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = subService.getSubOTP(request, language);
            logger.info("### End wsSubscriberGetOTP API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsSubscriberCheckIsdn", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSubscriberCheckIsdn(@RequestBody RequestBean request) {
        return null;
    }

    @RequestMapping(value = "/wsSubscriberGetInforCusByIsdn", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSubscriberGetInforCusByIsdn(@RequestBody RequestBean request) {
        logger.info("### Start wsSubscriberGetInforCusByIsdn API of ServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = subService.getSubscriberInforCusByIsdn(request, language);
            logger.info("### End wsSubscriberGetInforCusByIsdn API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsSubscriberUpdateCustomerInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSubscriberUpdateCustomerInfo(@RequestBody RequestBean request) {
        logger.info("### Start wsSubscriberUpdateCustomerInfo API of ServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = subService.updateSubscriberCustomerInfo(request, language);
            logger.info("### End wsSubscriberUpdateCustomerInfo API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsDetectOCRFromImage", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsDetectOCRFromImage(@RequestBody RequestBean request){
        logger.info("### Start wsDetectOCRFromImage API of ServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = subService.detectOCRFromImage(request, language);
            logger.info("### End wsSubscriberUpdateCustomerInfo API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
    
        @RequestMapping(value = "/wsUpdateCusInfor", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateCusInfor(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateCusInfor API of ServiceController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            
            BaseResponseBean bean = subService.updateResponseCusInfor(request, language);
            logger.info("### End wsUpdateCusInfor API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
 
}

