package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseBean;
import co.siten.myvtg.bean.ResponseErrorBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.service.NotifyService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/${version}/accounts")
public class NotifyServiceController extends BaseController {

    private static final String BAD_REQUEST = "myMetfone.Ishare.bad.request.";
    private static final Logger logger = LoggerFactory.getLogger(NotifyServiceController.class);
    private static final int NOTIFICATION_TYPE = 12;
    @Autowired
    NotifyService notifyService;
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    ConfigUtils configUtils;
    private static final String LANGUAGE = "language";
    private static final String ISDN = "isdn";

    /**
     * Update device token to push notification
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsUpdateDeviceToken", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsUpdateDeviceToken(@RequestBody RequestBean request) {
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();

            String isdn = wsRequest.get(ISDN).toString();
            String token = wsRequest.get("token").toString();
            String os = wsRequest.get("os").toString();
            notifyService.wsUpdateDeviceToken(bean, isdn, token, Integer.parseInt(os));
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    /**
     * Tick notification is read
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsLogClickNotification", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsLogClickNotification(@RequestBody RequestBean request) {
        //daibq fix lại ham
        logger.info("Start business wsLogClickNotification of MyMetfoneBusinessService");
        String language = "en";
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage(BAD_REQUEST + language)), HttpStatus.OK);
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("language")) && !DataUtil.isNullOrEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.webServices.empty." + language)), HttpStatus.OK);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
                logger.info("Error requesst : notificationId is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.webServices.empty." + language)), HttpStatus.OK);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("notificationType"))) {
                logger.info("Error requesst : notificationId is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.webServices.empty." + language)), HttpStatus.OK);
            }
            //phuonghc if notification type = 12 => insert notification is read
            int notifyType = 0;
            try {
                notifyType = Integer.valueOf(request.getWsRequest().get("notificationType").toString());
            } catch (NumberFormatException ne) {
                logger.info("notificationType is not available, " + ne.getMessage());
            }
            if (NOTIFICATION_TYPE == notifyType) {
                logger.info("Start insert for notification");
                BaseResponseBean result = notifyService.saveLogAfterReadNotification(request, language);
                bean.setWsResponse(result);
                return responseSuccess(request, bean);
            } else {
                //daibq fix lại ham
                Long notificationId = Long.parseLong(wsRequest.get("notificationId").toString().trim());
                String accNotificationId = DataUtil.isNullObject(wsRequest.get("accNotificationId")) ? "" : wsRequest.get("accNotificationId").toString().trim();
                String isdn = wsRequest.get(ISDN).toString();
                String notificationIdStr = configUtils.getMessage("NOTIFY_COMPLAINT", "9").trim();
                if (notificationId.toString().equals(notificationIdStr) && !DataUtil.isNullOrEmpty(accNotificationId)) {
                    notifyService.logClickNotification(bean, isdn, notificationId, Long.parseLong(accNotificationId));
                } else {
                    notifyService.logClickNotification(bean, isdn, notificationId, null);
                }
                return responseSuccess(request, bean);
            }
        } catch (NumberFormatException e) {
            logger.error("wsLogClickNotification: ", e);
            return responseError(request, e);
        }
    }
//    @RequestMapping(value = "/wsLogActionNotification", method = RequestMethod.POST)
//    public ResponseEntity<ResponseBean> wsLogActionNotification(@RequestBody RequestBean request) {
//        try {
//            ResponseSuccessBean bean = new ResponseSuccessBean();
//            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
//
//            String isdn = wsRequest.get(ISDN).toString();
//            String actionValue = wsRequest.get("actionValue").toString();
//            Long notificationId = null;
//            Object pn = wsRequest.get("notificationId");
//            if (pn != null) {
//                notificationId = Long.parseLong(pn.toString());
//            }
//
//            notifyService.logActionNotification(bean, isdn, actionValue, notificationId);
//            return responseSuccess(request, bean);
//        } catch (NumberFormatException e) {
//            logger.error("wsLogActionNotification: ", e);
//            return responseError(request, e);
//        }
//    }

    /**
     * Get list notification of isdn
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetListNotificationByIsdn", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetListNotificationByIsdn(@RequestBody RequestBean request) {
        try {
//			if (!validateRequest(request))
//				return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = null;
            Integer pageSize = 0, pageNum = 1;
            if (wsRequest != null) {
                isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : null;
                Object ps = wsRequest.get("pageSize");
                Object pn = wsRequest.get("pageNum");
                if (ps != null) {
                    pageSize = Integer.parseInt(ps.toString());
                    pageNum = Integer.parseInt(pn.toString());
                }
            }

            notifyService.wsGetListNotificationByIsdn(isdn, pageSize, pageNum, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("", e);
            return responseError(request, e);
        }
    }

    //phuonghc 22062020 send notification via CMS web, directly
    @RequestMapping(value = "/wsGetListNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListNotification(@RequestBody RequestBean request) {
        logger.info("Start wsGetListNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.getListNotificationV2(request, LANGUAGE);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    //phuonghc 23062020 support management for CMS web
    @RequestMapping(value = "/wsAddNewNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsAddNewNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsAddNewNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.addNewNotificationV2(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsUpdateNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateNofitication API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.updateNotifiationV2(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsDeleteNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsDeleteNofitication(@RequestBody RequestBean request) {
        logger.info("### Start wsDeleteNofitication API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.deleteNotifiationV2(request, language);
            logger.info("### End wsDeleteNofitication API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetDetailNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetDetailNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsGetDetailNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null wsRequest");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.getDetailNotification(request, language);
            logger.info("### End wsGetDetailNotification API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    //phuonghc 07102020 support management for CMS web CamID
    @RequestMapping(value = "/wsGetAllCamIdNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetAllCamIdNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsGetAllCamIdNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.getAllCamIdNotification(request, language);
            logger.info("### End wsAddNewCamIdNotification API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsAddNewCamIdNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsAddNewCamIdNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsAddNewCamIdNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.addNewCamIdNotification(request, language);
            logger.info("### End wsAddNewCamIdNotification API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsSendNewCamIdNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSendNewCamIdNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsSendNewCamIdNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.sendNewCamIdNotification(request, language);
            logger.info("### End wsSendNewCamIdNotification API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsUpdateCamIdNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateCamIdNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateCamIdNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.updateCamIdNotification(request, language);
            logger.info("### End wsUpdateCamIdNotification API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsDeleteCamIdNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsDeleteCamIdNotification(@RequestBody RequestBean request) {
        logger.info("### Start wsDeleteCamIdNotification API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.deleteCamIdNotification(request, language);
            logger.info("### End wsDeleteCamIdNotification API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetCamIdActionType", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetCamIdActionType(@RequestBody RequestBean request) {
        logger.info("### Start wsGetCamIdActionType API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.getCamIdActionType(language);
            logger.info("### End wsGetCamIdNotificationType API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsUpdateCamIdDeviceToken", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsUpdateCamIdDeviceToken(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateCamIdDeviceToken API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage(BAD_REQUEST + language)), HttpStatus.OK);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                logger.info("Error request : deviceId is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.webServices.empty." + language)), HttpStatus.OK);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("token")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("token").toString())) {
                logger.info("Error request : token is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.webServices.empty." + language)), HttpStatus.OK);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("os")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("os").toString())) {
                logger.info("Error request : os is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.webServices.empty." + language)), HttpStatus.OK);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();

            String camId = wsRequest.get("camId").toString();
            String token = wsRequest.get("token").toString();
            String os = wsRequest.get("os").toString();
            String deviceId = wsRequest.get("deviceId").toString();
            String lang = wsRequest.get("language").toString();
            String versionApp = wsRequest.get("versionApp") == null ? "" : wsRequest.get("versionApp").toString();
            notifyService.wsUpdateCamIdDeviceToken(bean, camId, token, Integer.parseInt(os), deviceId, lang, versionApp);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    @RequestMapping(value = "/wsGetCamIdDeviceToken", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetCamIDeviceToken(@RequestBody RequestBean request) {
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String camId = wsRequest.get("camId").toString();
            String deviceId = wsRequest.get("deviceId").toString();
            notifyService.wsGetCamIDeviceToken(bean, camId, deviceId);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    @RequestMapping(value = "/wsGetCamIdNotificationById", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetCamIdNotificationById(@RequestBody RequestBean request) {
        logger.info("### Start wsGetCamIdNotificationById API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.getCamIdNotificationById(request, language);
            logger.info("### End wsGetCamIdNotificationById API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsSendSMSInternal", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSendSMSForgotPassword(@RequestBody RequestBean request) {
        logger.info("### Start wsSendSMSInternal API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.sendSMSInternal(request, language);
            logger.info("### End wsSendSMSCamID API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "wsGetFilmInfoByName", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetFilmInfoByName(@RequestBody RequestBean request) {
        logger.info("### Start wsGetFilmInfoByName API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.getFilmInfoByName(request, language);
            logger.info("### End wsGetFilmInfoByName API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    // partner7 -- 14.08.2021
    @RequestMapping(value = "/wsGetRewardDetail", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetRewardDetail(@RequestBody RequestBean request) {
        logger.info("### Start wsGetRewardDetail API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = notifyService.getRewardDetail(request, language);
            logger.info("### End wsGetRewardDetail API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    //23/02/2022 Passive-notify	
    @RequestMapping(value = "/wsGetListPassiveType", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListPassiveType(@RequestBody RequestBean request) {
        logger.info("### Start wsGetListPassiveType API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));

            BaseResponseBean bean = notifyService.getListPassiveType(request, language);

            logger.info("### End wsGetRewardDetail API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetListPassiveControl", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetListPassiveControl(@RequestBody RequestBean request) {
        logger.info("### Start wsGetListPassiveControl API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));

            BaseResponseBean bean = notifyService.getListPassiveControl(request, language);

            logger.info("### End wsGetListPassiveControl API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsUpdatePassiveControl", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdatePassiveControl(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdatePassiveControl API of NotificationController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));

            BaseResponseBean bean = notifyService.updatePassiveControl(request, language);

            logger.info("### End wsUpdatePassiveControl API of NotificationController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }
}
