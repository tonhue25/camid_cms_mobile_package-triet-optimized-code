/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.CamIdNotificationCMSBean;
import co.siten.myvtg.bean.CamIdActionTypeBean;
import co.siten.myvtg.bean.CamIdPassiveTypeBean;
import co.siten.myvtg.bean.CamIdRewardDetailBean;
import co.siten.myvtg.bean.NotificationBean;
import co.siten.myvtg.bean.NotificationBeanV2;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.dao.ApiGwDao;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.dao.TransactionLogDao;
import co.siten.myvtg.dto.BaseResponsesDto;
import co.siten.myvtg.dto.NewsCovidDto;
import co.siten.myvtg.dto.NotificationAccountDtoV2;
import co.siten.myvtg.dto.NotificationDtoV2;
import co.siten.myvtg.dto.NotificationDtoV2Expand;
import co.siten.myvtg.dto.NotificationResult;
import co.siten.myvtg.model.apigw.AccountNotification;
import co.siten.myvtg.model.apigw.CamIdDeviceToken;
import co.siten.myvtg.model.apigw.DeviceToken;
import co.siten.myvtg.model.apigw.Notification;
import co.siten.myvtg.model.apigw.NotificationLogClick;
import co.siten.myvtg.model.myvtg.NewsCovid19Entity;

import co.siten.myvtg.util.AppUtil;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.DateTimeUtils;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.ResponseUtil;
import co.siten.myvtg.util.WebServiceUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.CollectionUtils;

@org.springframework.stereotype.Service("NotifyService")

@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")

public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private Environment environment;
    @Autowired
    MessageUtil messageUtil;

    @Autowired
    MyvtgMasterDataDao myvtgDao;

    @Autowired
    TransactionLogDao transactionLogDao;

    @Autowired
    ApiGwDao apiGwDao;

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    MyvtgService myvtgService;

    @Autowired
    SmsService smsService;

    private static final Logger logger = Logger.getLogger(NotifyServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    private static final String NOTIFICATION_TYPE = "12";
    private static final String NOTIFICATION_CAMID_TYPE_IS_NONE = "none";
    private static final String NOTIFICATION_CAMID_TYPE_IS_LINK = "link";
    private static final String NOTIFICATION_CAMID_TYPE_IS_ACTION_TYPE = "action_type";
    private static final String METFONE_SERVICE = "9";
    private static final String BUTTON_TITLE_PLAY_NOW = "play_now";
    private static final String BUTTON_TITLE_GET_NOW = "get_now";
    private static final String BUTTON_TITLE_LOGIN_NOW = "login_now";
    private static final String BUTTON_TITLE_SIGN_UP_NOW = "sign_up_now";
    private static final String BUTTON_TITLE_VIEW_CHALLENGE = "view_challenge";
    private static final String BUTTON_TITLE_MORE_INFOR = "more_infor";
    private static final String BUTTON_TITLE_TRY_IT_OUT = "try_it_out";
    private static final String BUTTON_TITLE_BOOK_NOW = "book_now";
    private static final String BUTTON_TITLE_UPDATE_NOW = "update_now";
    private static final Long LOGIN_NOW_TYPE_IN_DB = 16L;
    private static final Long SIGN_UP_NOW_TYPE_IN_DB = 17L;
    private static final Long PASSIVE_TYPE_IN_DB = 18L;
    private static final Long UPDATE_PHONE_NUMBER_IN_DB = 19L;
    private static final Long UPDATE_INFORMATION_IN_DB = 24L;

    @Override
    public void wsUpdateDeviceToken(ResponseSuccessBean bean, String isdn, String token, int OperatingSystem)
            throws Exception {
        try {
            DeviceToken deviceToken = apiGwDao.getDeviceTokenByIsdn(isdn);
            // Update token with isdn
            if (deviceToken != null) {
                if (deviceToken.getToken() != null && !deviceToken.getToken().equals(token)) {

                    deviceToken.setToken(token);
                    // deviceToken.setCreateDate(CommonUtil.getCurrentTime());
                    deviceToken.setOperatingSystem(OperatingSystem);
                    deviceToken.setLastUpdate(CommonUtil.getCurrentTime());
                    apiGwDao.update(deviceToken);
                } else {
                    deviceToken.setToken(token);
                    // deviceToken.setCreateDate(CommonUtil.getCurrentTime());
                    deviceToken.setOperatingSystem(OperatingSystem);
                    deviceToken.setLastUpdate(CommonUtil.getCurrentTime());
                    apiGwDao.update(deviceToken);
                }
            } else {
                deviceToken = new DeviceToken();
                deviceToken.setIsdn(isdn);
                deviceToken.setToken(token);
                deviceToken.setCreateDate(CommonUtil.getCurrentTime());
                deviceToken.setLastUpdate(CommonUtil.getCurrentTime());
                deviceToken.setOperatingSystem(OperatingSystem);
                apiGwDao.persist(deviceToken);
            }
            logger.info("Update device token successfully" + token);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setUserMsg("Update device token successfully!" + token);
        } catch (Exception e) {
            logger.error("Update device token unsuccessfully", e);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setUserMsg("Update device token unsuccessfully!");
        }
    }

    @Override
    public void logClickNotification(ResponseSuccessBean bean, String isdn, Long notificationId, Long accNotificationId) {
        logger.info("Start business logClickNotification of MyMetfoneBusinessService");
        Notification noti = apiGwDao.findNotificationById(notificationId);
        if (noti != null) {
            NotificationLogClick click = new NotificationLogClick();
            click.setCreatedTime(CommonUtil.getCurrentTime());
            click.setIsdn(isdn);
            click.setNotificationId(notificationId);
            apiGwDao.persist(click);
            //daibq bo dung update click notify complaint
            List<AccountNotification> accnotiList = null;
            if (!DataUtil.isNullObject(accNotificationId)) {
                accnotiList = apiGwDao.getListAccountNotifyByIsdnAndNoti(isdn, notificationId, accNotificationId);
                if (!DataUtil.isNullOrEmpty(accnotiList)) {
                    AccountNotification accountNotification = accnotiList.get(0);
                    accountNotification.setIsRead(1);
                    apiGwDao.update(accountNotification);
                }
            } else {
                accnotiList = apiGwDao.getListAccountNotifyByIsdnAndNoti(isdn, notificationId);
                if (!CommonUtil.isEmpty(accnotiList)) {
                    accnotiList.forEach(acc -> {
                        acc.setIsRead(1);
                        apiGwDao.update(acc);
                    });
                }
            }
        } else {
            bean.setErrorCode(Constants.ERROR_CODE_1);
        }

    }

//    @Override
//    public void logActionNotification(ResponseSuccessBean bean, String isdn, String actionValue, Long notificationId) {
//        Notification noti = myvtgDao.findNotificationById(notificationId);
//        if (noti != null) {
//            NotificationLogAction action = new NotificationLogAction();
//            action.setCreatedTime(CommonUtil.getCurrentTime());
//            action.setIsdn(isdn);
//            action.setNotificationId(notificationId);
//            action.setActionValue(actionValue);
//            myvtgDao.persist(action);
//        } else {
//            bean.setErrorCode(Constants.ERROR_CODE_1);
//        }
//    }
    private String getCountryCode() {
        return environment.getRequiredProperty("COUNTRY_CODE");
    }

//    @Override
//    public void wsGetListNotificationByIsdn(String isdn, Integer pageSize, Integer pageNum, ResponseSuccessBean bean) {
//        List<NotificationBean> notfication = myvtgDao.getListNotificationByIsdn(isdn, pageSize, pageNum);
//        if (notfication == null || notfication.isEmpty())
//            notfication = null;
//        else {
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
//             
//                SimpleDateFormat dateFormat = getDateFormatForAccountDetail();
//                for (NotificationBean noti : notfication) {
//                    if (noti.getParams()!= null && !noti.getParams().trim().isEmpty())
//                    {
//                        Map<String, String> params = mapper.readValue(noti.getParams(), Map.class);
//                        for (String key: params.keySet()) {
//                            noti.setMessage(noti.getMessage().replace("@" + key, params.get(key)));
//                        }
//                    }
//                    noti.setReceivedTime(dateFormat.format(noti.getInsertTime()));
//                }
//            } catch (Exception e) {
//                logger.error("Json Parse Error: ", e);
//            }
//        }
//        bean.setWsResponse(notfication);
//    }
    @Override
    public void wsGetListNotificationByIsdn(String isdn, Integer pageSize, Integer pageNum, ResponseSuccessBean bean) {
        logger.info("Start business wsGetListNotificationByIsdn off NotifyServiceImpl");
        List<NotificationBean> notification = apiGwDao.getListNotificationByIsdn(isdn, 0, null);
        logger.info("List<NotificationBean> :" + notification.size());
        if (notification.isEmpty()) {
            notification = new ArrayList<>();
        } else {
            SimpleDateFormat dateFormat = getDateFormatForAccountDetail();
            notification.forEach(noti -> {
                noti.setReceivedTime(dateFormat.format(noti.getInsertTime()));
                replaceNotifyMsg(noti);

            });
        }
        //phuonghc bonus list of notification send from CMS version 2
        int firstIndex = (pageNum - 1) * pageSize;
        int lastIndex = (pageNum) * pageSize - 1;
        List<NotificationDtoV2> notificationVersion2 = apiGwDao.getAllActiveListNotificationVersion2(null, null);
        logger.info("List<NotificationBean> :" + notificationVersion2.size());
        List<NotificationDtoV2Expand> notificationVersion2Expands = bonusInformationForNotification(notificationVersion2, isdn);
        List<NotificationBean> nofifybean = convertNotification(notificationVersion2Expands);
        notification.addAll(nofifybean);
        Collections.sort(notification);
        if (notification.isEmpty() || notification.size() < firstIndex) {
            bean.setWsResponse(new ArrayList<>());
        } else if (notification.size() > lastIndex) {
            bean.setWsResponse(notification.subList(firstIndex, lastIndex + 1));
        } else if (notification.size() == lastIndex) {
            bean.setWsResponse(notification.subList(firstIndex, lastIndex));
        } else if (notification.size() <= lastIndex && notification.size() > firstIndex) {
            bean.setWsResponse(notification.subList(firstIndex, notification.size()));
        }
    }

    public void replaceNotifyMsg(NotificationBean noti) {
        String params = noti.getParams();
        if (params != null && !"".equals(params.trim())) {
            JSONParser jsonParser = new JSONParser();
            try {
                JSONArray jsArray = (JSONArray) jsonParser.parse(params);
                for (int i = 0; i < jsArray.size(); i++) {
                    JSONObject obj = (JSONObject) jsArray.get(i);
                    String param = (String) obj.get("param");
                    String value = (String) obj.get("value");
                    String message = noti.getMessage();
                    if (!DataUtil.isNullOrEmpty(param) && !DataUtil.isNullOrEmpty(value)) {
                        message = message.replace(param, value);
                    }
                    noti.setMessage(message);

                }
            } catch (ParseException ex) {
                logger.error("replaceNotifyMsg Err:" + ex.getMessage(), ex);
            }
        }

    }

    private SimpleDateFormat getDateFormatForAccountDetail() {
        String formatShow = environment.getProperty("pro_date_format_accountdetail_show");
        if (formatShow != null) {
            return AppUtil.getDateFormat("HH:mm - " + formatShow);
        } else {
            return AppUtil.getDateFormat("HH:mm - " + "dd/MM/yyyy");
        }
    }

    @Override
    public BaseResponseBean getListNotificationV2(RequestBean request, String language) {
        logger.info("Start business getListNotificationV2 of NotificationController");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("pageNumber")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNumber").toString())) {
                logger.info("Error request : pageNumber is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            String pageNumber = request.getWsRequest().get("pageNumber").toString().trim();
            Long totalNews = apiGwDao.getTotalRecordNotificationVersion2() + myvtgService.getTotalRecordNewsCovid();
            NotificationBeanV2 notificationBeanV2 = new NotificationBeanV2();
            notificationBeanV2.setTotalNews(totalNews);
            Long fromRecords;
            Long toRecords;
            try {
                Long pageNum = Long.parseLong(pageNumber);
                fromRecords = (pageNum - 1) * 10 + 1;
                toRecords = fromRecords + 9;
            } catch (NumberFormatException ne) {
                logger.info("### PageNumber is not valid", ne);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }

            List<NotificationDtoV2> notificationV2List = apiGwDao.getAllListNotificationVersion2(fromRecords, toRecords);

            //get data covid-19 
            List<NewsCovidDto> covid19 = myvtgService.getNewsCovid19List(fromRecords, toRecords);
            List<NotificationDtoV2> covid19Converted = convertCovid19ToNotification(covid19);
            notificationV2List.addAll(covid19Converted);

            Collections.sort(notificationV2List);

            if (!CollectionUtils.isEmpty(notificationV2List) && notificationV2List.size() > 10) {
                notificationBeanV2.setNotificationList(notificationV2List.subList(0, 10));
            } else {
                notificationBeanV2.setNotificationList(notificationV2List);
            }

            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(notificationBeanV2);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.info("### PageNumber is not valid", e);
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
    }

    @Override
    public BaseResponseBean addNewNotificationV2(RequestBean request, String language) {
        logger.info("Start business addNewNotificationV2 of NotificationController");
        BaseResponseBean bean = checkValidInformation(request, language);
        if (bean != null) {
            return bean;
        }
        try {
            String title = request.getWsRequest().get("title").toString().trim();
            String message = request.getWsRequest().get("message").toString().trim();
            String topic = request.getWsRequest().get("topic").toString().trim();
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();
            String link = request.getWsRequest().get("link").toString().trim();
            String timeStr = request.getWsRequest().get("time").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String actionType = "";
            if (NOTIFICATION_TYPE.equals(notificationType)) {
                actionType = request.getWsRequest().get("actionType").toString().trim();
            }
            String notificationImage = "";
            if (request.getWsRequest().get("notificationImage") != null) {
                notificationImage = request.getWsRequest().get("notificationImage").toString().trim();
            }
            int time = 0;
            try {
                time = Integer.parseInt(timeStr);
            } catch (NumberFormatException e) {
                logger.info("### Cannot parse time", e);
            }
            NotificationResult result = new NotificationResult();
            bean = new BaseResponseBean();
            if ("8".equals(notificationType) && StringUtils.isEmpty(actionType)) {
                NewsCovid19Entity entity = new NewsCovid19Entity();
                entity.setTitle(title);
                entity.setMessage(message);
                entity.setTopic(topic);
                entity.setNotificationType(notificationType);
                entity.setNotificationImage(notificationImage);
                entity.setLink(link);
                entity.setTime(time);
                entity.setStatus(status);
                entity.setDescription("deactive");
                if ("1".equals(status)) {
                    entity.setDescription("active");
                }

                result = myvtgService.addNewsCovid19(entity);
                entity.setNewsCovidId(result.getNotificationId().toString());
                List<NotificationDtoV2> converted = convertCovid119DtoToNotfication(Arrays.asList(entity));
                bean.setObject(converted.get(0));
            } else {
                NotificationDtoV2 notificationDto = new NotificationDtoV2();
                notificationDto.setTitle(title);
                notificationDto.setMessage(message);
                notificationDto.setTopic(topic);
                notificationDto.setNotificationType(notificationType);
                notificationDto.setNotificationImage(notificationImage);
                notificationDto.setLink(link);
                notificationDto.setTime(time);
                notificationDto.setActionType(actionType);
                notificationDto.setStatus(status);
                notificationDto.setDescription("deactive");
                if ("1".equals(status)) {
                    notificationDto.setDescription("active");
                }

                result = apiGwDao.addNewNotificationVersion2(notificationDto);
                notificationDto.setNotificationId(result.getNotificationId());
                bean.setObject(notificationDto);
            }
            if (result.getResult() == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result.getResult());
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result.getResult());
                bean.setUserMsg("Not success");
            }
            return bean;

        } catch (NullPointerException ne) {
            logger.error("parse description error: ", ne);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        } catch (Exception e) {
            logger.error("An error occured while add new notification: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateIsReadNotificationV2(RequestBean request, String language) {
        logger.info("Start business updateIsReadNotificationV2 of NotificationController");
        if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
            logger.info("Error request : notificationId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationType").toString())) {
            logger.info("Error request : notificationType is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
        int result = apiGwDao.updateIsReadNotificationV2(request.getWsRequest().get("isdn").toString().trim(), request.getWsRequest().get("notificationId").toString().trim(), request.getWsRequest().get("notificationType").toString().trim());
        BaseResponseBean bean = new BaseResponseBean();
        if (result == 1) {
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
        } else {
            bean.setErrorCode(Constants.NOT_SUCCESS);
            bean.setMessage("NOT_SUCCESS");
            bean.setWsResponse(result);
            bean.setUserMsg("Not success");
        }

        return bean;

    }

    @Override
    public BaseResponseBean updateNotifiationV2(RequestBean request, String language) {
        logger.info("Start business updateNotifiationV2 of NotificationController");
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
            logger.info("Error request : notificationId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
        BaseResponseBean bean = checkValidInformation(request, language);
        if (bean != null) {
            return bean;
        }
        try {
            String notificationId = request.getWsRequest().get("notificationId").toString().trim();
            String title = request.getWsRequest().get("title").toString().trim();
            String message = request.getWsRequest().get("message").toString().trim();
            String topic = request.getWsRequest().get("topic").toString().trim();
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();
            String link = request.getWsRequest().get("link").toString().trim();
            String timeStr = request.getWsRequest().get("time").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String actionType = "";
            if (NOTIFICATION_TYPE.equals(notificationType)) {
                actionType = request.getWsRequest().get("actionType").toString().trim();
            }
            String notificationImage = "";
            if (request.getWsRequest().get("notificationImage") != null) {
                notificationImage = request.getWsRequest().get("notificationImage").toString().trim();
            }
            int time = 0;
            try {
                time = Integer.parseInt(timeStr);
            } catch (NumberFormatException e) {
                logger.info("### Cannot parse time", e);
            }
            bean = new BaseResponseBean();
            int result;
            if ("8".equals(notificationType) && StringUtils.isEmpty(actionType)) {
                NewsCovid19Entity entity = new NewsCovid19Entity();
                entity.setNewsCovidId(notificationId);
                entity.setTitle(title);
                entity.setMessage(message);
                entity.setTopic(topic);
                entity.setNotificationType(notificationType);
                entity.setNotificationImage(notificationImage);
                entity.setLink(link);
                entity.setTime(time);
                entity.setStatus(status);
                entity.setDescription("deactive");
                if ("1".equals(status)) {
                    entity.setDescription("active");
                }

                result = myvtgService.updateNewsCovid19(entity);
                List<NotificationDtoV2> converted = convertCovid119DtoToNotfication(Arrays.asList(entity));
                bean.setObject(converted.get(0));
            } else {
                NotificationDtoV2 notifiDtoUpdate = new NotificationDtoV2();
                notifiDtoUpdate.setNotificationId(Long.valueOf(notificationId));
                notifiDtoUpdate.setTitle(title);
                notifiDtoUpdate.setMessage(message);
                notifiDtoUpdate.setTopic(topic);
                notifiDtoUpdate.setNotificationType(notificationType);
                notifiDtoUpdate.setNotificationImage(notificationImage);
                notifiDtoUpdate.setLink(link);
                notifiDtoUpdate.setTime(time);
                notifiDtoUpdate.setStatus(status);
                notifiDtoUpdate.setActionType(actionType);
                notifiDtoUpdate.setDescription("deactive");
                if ("1".equals(status)) {
                    notifiDtoUpdate.setDescription("active");
                }

                result = apiGwDao.updateNotificationVersion2(notifiDtoUpdate);
                bean.setObject(notifiDtoUpdate);
            }
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
            }
            return bean;

        } catch (NullPointerException ne) {
            logger.error("parse description error: ", ne);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        } catch (Exception e) {
            logger.error("An error occured while add new notification: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean deleteNotifiationV2(RequestBean request, String language) {
        logger.info("Start business deleteNotifiationV2 of NotifyServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
            logger.info("Error request : notificationId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationType").toString())) {
            logger.info("Error request : notificationType is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        try {
            String id = request.getWsRequest().get("notificationId").toString().trim();
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();
            NotificationDtoV2 notificationDel = new NotificationDtoV2();
            notificationDel.setNotificationId(Long.valueOf(id));
            notificationDel.setStatus("2");
            notificationDel.setDescription("deleted");

            BaseResponseBean bean = new BaseResponseBean();
            int result;
            if ("8".equals(notificationType)) {
                NewsCovid19Entity entity = new NewsCovid19Entity();
                entity.setNewsCovidId(id);
                entity.setStatus("2");
                entity.setDescription("deleted");

                result = myvtgService.deleteNewsCovid19(entity);
            } else {
                result = apiGwDao.deleteNotificationVersion2(notificationDel);
            }
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
            }
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean saveLogAfterReadNotification(RequestBean request, String language) {
        logger.info("Start business saveLogAfterReadNotification of NotifyServiceImpl");
        try {
            String notificationId = request.getWsRequest().get("notificationId").toString().trim();
            String isdn = request.getWsRequest().get("isdn").toString().trim();
            BaseResponseBean bean = new BaseResponseBean();
            if (isReadAlready(isdn, Long.valueOf(notificationId))) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                int result = apiGwDao.saveLogAfterSendNotification(isdn, notificationId);
                if (result == 1) {
                    bean.setErrorCode(Constants.ERROR_SUCCESS);
                    bean.setMessage("Success");
                    bean.setWsResponse(result);
                    bean.setUserMsg("Success");
                } else {
                    bean.setErrorCode(Constants.NOT_SUCCESS);
                    bean.setMessage("NOT_SUCCESS");
                    bean.setWsResponse(result);
                    bean.setUserMsg("Not success");
                }
            }
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getDetailNotification(RequestBean request, String language) {
        logger.info("Start business getDetailNotification of NotifyServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
            logger.info("Error request : notificationId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("notificationType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationType").toString())) {
            logger.info("Error request : notificationId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        try {
            BaseResponseBean bean = new BaseResponseBean();
            String notificationId = request.getWsRequest().get("notificationId").toString().trim();
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();

            if ("8".equals(notificationType)) {
                NewsCovid19Entity entity = new NewsCovid19Entity();
                entity.setNewsCovidId(notificationId);
                NewsCovidDto newCovidDto = myvtgService.getDetailNewsCovid19(entity);
                List<NotificationDtoV2> dto = convertCovid19ToNotification(Arrays.asList(newCovidDto));
                bean.setWsResponse(dto.get(0));
            } else {
                NotificationDtoV2 result = apiGwDao.getDetailNotificationVersion2(notificationId);
                bean.setWsResponse(result);
            }
            bean.setErrorCode(Constants.SUCCESS);
            bean.setMessage("SUCCESS");
            bean.setUserMsg("Success");
            return bean;
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    /*
     This API provide add notification global and personal.
     if global, ownerIsdn will empty => all user will get this notification
     if personal, ownerIsdn's value is isdn of customer and only this customer receive this notification
     */
    @Override
    public BaseResponseBean addNewCamIdNotification(RequestBean request, String language) {
        logger.info("### Start business addNewCamIDNotification of NotificationService");
        BaseResponseBean bean = this.checkValidCamIdNotify(request, language);
        if (bean != null) {
            return bean;
        }
        try {
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();
            String time = request.getWsRequest().get("time").toString().trim();
            Long interval = 0L;
            if (Integer.valueOf(time) > 1) {
                if (DataUtil.isNullObject(request.getWsRequest().get("interval")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("interval").toString())) {
                    logger.info("Error request : interval is null ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.interval.empty.", language);
                }
                interval = Long.valueOf(request.getWsRequest().get("interval").toString());
            }
            String pathFileSubId = request.getWsRequest().get("pathFileSubId").toString().trim();
            String subId = request.getWsRequest().get("subId").toString().trim();
            String languageOfNotify = request.getWsRequest().get("languageOfNotify").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String messageEn = DataUtil.isNullObject(request.getWsRequest().get("messageEn")) ? Constants.EMPTY_STRING : request.getWsRequest().get("messageEn").toString();
            String messageKh = DataUtil.isNullObject(request.getWsRequest().get("messageKh")) ? Constants.EMPTY_STRING : request.getWsRequest().get("messageKh").toString();
            String titleEn = DataUtil.isNullObject(request.getWsRequest().get("titleEn")) ? Constants.EMPTY_STRING : request.getWsRequest().get("titleEn").toString();
            String titleKh = DataUtil.isNullObject(request.getWsRequest().get("titleKh")) ? Constants.EMPTY_STRING : request.getWsRequest().get("titleKh").toString();
            String schedule = DataUtil.isNullObject(request.getWsRequest().get("schedule")) ? Constants.EMPTY_STRING : request.getWsRequest().get("schedule").toString();
            String featureImage = DataUtil.isNullObject(request.getWsRequest().get("featureImage")) ? Constants.EMPTY_STRING : request.getWsRequest().get("featureImage").toString();
            String videoUrl = DataUtil.isNullObject(request.getWsRequest().get("videoUrl")) ? Constants.EMPTY_STRING : request.getWsRequest().get("videoUrl").toString();
            String isExchange = DataUtil.isNullObject(request.getWsRequest().get("isExchange")) ? Constants.EMPTY_STRING : request.getWsRequest().get("isExchange").toString();
            String iconUrl = DataUtil.isNullObject(request.getWsRequest().get("iconUrl")) ? Constants.EMPTY_STRING : request.getWsRequest().get("iconUrl").toString();
            String listTestPhone = DataUtil.isNullObject(request.getWsRequest().get("listTestPhone")) ? Constants.EMPTY_STRING : request.getWsRequest().get("listTestPhone").toString();
            String osDeviceStr = DataUtil.isNullObject(request.getWsRequest().get("osDevice")) ? Constants.EMPTY_STRING : request.getWsRequest().get("osDevice").toString();
            String passiveTypeIdStr = DataUtil.isNullObject(request.getWsRequest().get("passiveTypeId")) ? Constants.EMPTY_STRING : request.getWsRequest().get("passiveTypeId").toString();
            String passiveTimeFrom = DataUtil.isNullObject(request.getWsRequest().get("passiveTimeFrom")) ? Constants.EMPTY_STRING : request.getWsRequest().get("passiveTimeFrom").toString();
            String passiveTimeTo = DataUtil.isNullObject(request.getWsRequest().get("passiveTimeTo")) ? Constants.EMPTY_STRING : request.getWsRequest().get("passiveTimeTo").toString();
            String testStatus = "1";
            testStatus = DataUtil.isNullObject(request.getWsRequest().get("testStatus")) ? testStatus : request.getWsRequest().get("testStatus").toString();

            logger.info(passiveTypeIdStr + "||" + passiveTimeFrom + "||" + passiveTimeTo);

            if (StringUtils.isEmpty(messageEn) && languageOfNotify.contains("en") || StringUtils.isEmpty(messageKh) && languageOfNotify.contains("kh")) {
                logger.info("Error request : Message not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.message.not.match.language.", language);
            }

            if (StringUtils.isEmpty(titleEn) && languageOfNotify.contains("en") || StringUtils.isEmpty(titleKh) && languageOfNotify.contains("kh")) {
                logger.info("Error request : title not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.not.match.language.", language);
            }

            if (StringUtils.isNotEmpty(messageEn) && !languageOfNotify.contains("en") || StringUtils.isNotEmpty(messageKh) && !languageOfNotify.contains("kh")) {
                logger.info("Error request : Message not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.message.not.match.language.", language);
            }

            if (StringUtils.isNotEmpty(titleEn) && !languageOfNotify.contains("en") || StringUtils.isNotEmpty(titleKh) && !languageOfNotify.contains("kh")) {
                logger.info("Error request : title not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.not.match.language.", language);
            }

            if (StringUtils.isEmpty(osDeviceStr)) {
                logger.info("Error request : osDevice is invalid!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.os.device.empty.", language);
            }

            //24/02/2022 Check notify_passive
            Long osDevice = 0L;
            Long passiveTypeId = 0L;

            if ("notify_passive".equals(subId)) {
                if (!StringUtils.isNotEmpty(passiveTypeIdStr)) {
                    logger.info("Error request : title not match with language choosen!");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.not.match.language.", language);
                }

                try {
                    passiveTypeId = Long.parseLong(passiveTypeIdStr);
                } catch (NumberFormatException e) {
                    logger.info("Error request : passive type id empty with subId= notify_passive!");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.passivetypeid.empty.", language);
                }
                //Validate passive Range
                bean = checkPassiveRange(passiveTimeFrom, language);
                if (bean != null) {
                    return bean;
                }
                bean = checkPassiveRange(passiveTimeTo, language);
                if (bean != null) {
                    return bean;
                }
            }

            try {
                osDevice = Long.parseLong(osDeviceStr);
            } catch (NumberFormatException e) {
                logger.info("Error request : Os Not a number!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.passivetypeid.empty.", language);
            }

            bean = checkValidLength(messageEn, messageKh, titleEn, titleKh, language);
            if (bean != null) {
                return bean;
            }

            bean = new BaseResponseBean();
            /*Get common information*/
            CamIdNotificationCMSBean camIdNotifyCMS = new CamIdNotificationCMSBean();
            camIdNotifyCMS.setNotifyId(null);
            camIdNotifyCMS.setNotificationType(notificationType);
            camIdNotifyCMS.setTitleEn(titleEn);
            camIdNotifyCMS.setTitleKh(titleKh);
            camIdNotifyCMS.setMessageEn(messageEn);
            camIdNotifyCMS.setMessageKh(messageKh);
            camIdNotifyCMS.setTime(Integer.valueOf(time));
            camIdNotifyCMS.setSchedule(schedule);
            camIdNotifyCMS.setSubId(subId);
            camIdNotifyCMS.setLanguageOfNotify(languageOfNotify);
            camIdNotifyCMS.setStatus(Long.valueOf(status));
            camIdNotifyCMS.setFeatureImage(featureImage);
            camIdNotifyCMS.setInterval(interval);
            camIdNotifyCMS.setPathFileSubId(pathFileSubId);
            camIdNotifyCMS.setVideoUrl(videoUrl);
            camIdNotifyCMS.setIsExchange(StringUtils.isNotEmpty(isExchange) ? Long.parseLong(isExchange) : null);
            camIdNotifyCMS.setIconUrl(iconUrl);
            camIdNotifyCMS.setTestPhones(listTestPhone);
            camIdNotifyCMS.setTestStatus(Long.valueOf(testStatus));
            camIdNotifyCMS.setOsDevice(osDevice);
            if (StringUtils.isNotEmpty(passiveTypeIdStr)) {
                camIdNotifyCMS.setPassiveTypeId(passiveTypeId);
                camIdNotifyCMS.setPassiveRange(passiveTimeFrom + "-" + passiveTimeTo);
            }


            /*Get special information each case*/
            switch (notificationType.toUpperCase()) {
                case Constants.CAMID_NOTIFY_NONE: {
                    break;
                }
                case Constants.CAMID_NOTIFY_ACTION_TYPE: {
                    String actionObjectId = DataUtil.isNullObject(request.getWsRequest().get("actionObjectId")) ? Constants.EMPTY_STRING : request.getWsRequest().get("actionObjectId").toString();
                    String actionTypeIdStr = DataUtil.isNullObject(request.getWsRequest().get("actionTypeId")) ? Constants.EMPTY_STRING : request.getWsRequest().get("actionTypeId").toString();
                    String buttonTitle = DataUtil.isNullObject(request.getWsRequest().get("buttonTitle")) ? Constants.EMPTY_STRING : request.getWsRequest().get("buttonTitle").toString();
                    Long actionType = 0L;
                    if (StringUtils.isEmpty(actionTypeIdStr) || StringUtils.isEmpty(buttonTitle)) {
                        logger.info("### Error request : Missing actionType or buttonType!");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.missing.", language);
                    }

                    if (METFONE_SERVICE.equals(actionTypeIdStr) && camIdNotifyCMS.getIsExchange() == null) {
                        logger.info("### Error request : Missing isExchange for Metfone_service");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.metfoneService.missing.isExchange.", language);
                    }
                    if (!METFONE_SERVICE.equals(actionTypeIdStr) && camIdNotifyCMS.getIsExchange() != null) {
                        logger.info("### Error request : Has isExchange for another case not Metfone_service");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.another.has.isExchange.", language);
                    }
                    try {
                        actionType = Long.valueOf(actionTypeIdStr);
                    } catch (NumberFormatException ne) {
                        logger.info("### ActionType is not a number: " + ne.getMessage());
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.not.number.", language);
                    }
                    if (!BUTTON_TITLE_PLAY_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_GET_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_LOGIN_NOW.equalsIgnoreCase(buttonTitle)
                            && !BUTTON_TITLE_SIGN_UP_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_VIEW_CHALLENGE.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_MORE_INFOR.equalsIgnoreCase(buttonTitle)
                            && !BUTTON_TITLE_TRY_IT_OUT.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_BOOK_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_UPDATE_NOW.equalsIgnoreCase(buttonTitle)) {
                        logger.info("### Button title is not get_now nor play_now nor login_now nor sign_up_now and some more");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.missing.", language);
                    }

                    if (BUTTON_TITLE_LOGIN_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!LOGIN_NOW_TYPE_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatch.", language);
                        }
                    }

                    if (BUTTON_TITLE_SIGN_UP_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!SIGN_UP_NOW_TYPE_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatch.", language);
                        }
                    }

                    if (BUTTON_TITLE_VIEW_CHALLENGE.equalsIgnoreCase(buttonTitle) || BUTTON_TITLE_MORE_INFOR.equalsIgnoreCase(buttonTitle)
                            || BUTTON_TITLE_TRY_IT_OUT.equalsIgnoreCase(buttonTitle) || BUTTON_TITLE_BOOK_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!PASSIVE_TYPE_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatchV2.", language);
                        }
                    }

                    if (BUTTON_TITLE_UPDATE_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!UPDATE_PHONE_NUMBER_IN_DB.equals(actionType) && !UPDATE_INFORMATION_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatch.", language);

                        }
                    }

                    camIdNotifyCMS.setActionTypeId(actionType);
                    camIdNotifyCMS.setButtonTitle(buttonTitle);
                    camIdNotifyCMS.setActionObjectId(actionObjectId);
                    break;
                }
                case Constants.CAMID_NOTIFY_LINK: {
                    String link = DataUtil.isNullObject(request.getWsRequest().get("link")) ? Constants.EMPTY_STRING : request.getWsRequest().get("link").toString();
                    /*validate link*/
                    String linkLength = environment.getProperty("camid.length.link");
                    if (link.length() >= Integer.valueOf(linkLength)) {
                        logger.info("Error request : link over length ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.link.over.length.", language);
                    }

                    String buttonTitle = DataUtil.isNullObject(request.getWsRequest().get("buttonTitle")) ? Constants.EMPTY_STRING : request.getWsRequest().get("buttonTitle").toString();
//                    if (!BUTTON_TITLE_PLAY_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_GET_NOW.equalsIgnoreCase(buttonTitle)) {
//                        logger.info("### Button title is not get_now nor play_now");
//                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.missing.", language);
//                    }
                    camIdNotifyCMS.setLink(link);
                    camIdNotifyCMS.setButtonTitle(buttonTitle);
                    break;
                }
                default: {
                    logger.info("Error request : Not match any notifycation type!");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.notifyType.not.match.", language);
                }
            }

            NotificationResult result = apiGwDao.addNewCamIdNotification(camIdNotifyCMS);
            if (result.getResult() == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
            }
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while add new notification: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean sendNewCamIdNotification(RequestBean request, String language) {
        logger.info("### Start business sendNewCamIdNotification of NotificationService");
        BaseResponseBean bean = this.checkValidCamIdNotifyApi(request, language);
        if (bean != null) {
            return bean;
        }
        try {

            String languageOfNotify = request.getWsRequest().get("languageOfNotify").toString().trim();
            String messageEn = DataUtil.isNullObject(request.getWsRequest().get("messageEn")) ? "" : request.getWsRequest().get("messageEn").toString();
            String messageKh = DataUtil.isNullObject(request.getWsRequest().get("messageKh")) ? "" : request.getWsRequest().get("messageKh").toString();
            String titleEn = DataUtil.isNullObject(request.getWsRequest().get("titleEn")) ? "" : request.getWsRequest().get("titleEn").toString();
            String titleKh = DataUtil.isNullObject(request.getWsRequest().get("titleKh")) ? "" : request.getWsRequest().get("titleKh").toString();
            String schedule = DataUtil.isNullObject(request.getWsRequest().get("schedule")) ? "" : request.getWsRequest().get("schedule").toString();
            String iconUrl = DataUtil.isNullObject(request.getWsRequest().get("iconUrl")) ? "" : request.getWsRequest().get("iconUrl").toString();
            String listTestPhone = DataUtil.isNullObject(request.getWsRequest().get("phoneNumber")) ? "" : request.getWsRequest().get("phoneNumber").toString();
            String priority = DataUtil.isNullObject(request.getWsRequest().get("priority")) ? "" : request.getWsRequest().get("priority").toString();
            String channelID = DataUtil.isNullObject(request.getWsRequest().get("channelID")) ? "" : request.getWsRequest().get("channelID").toString();
            String safeChildrenType = DataUtil.isNullObject(request.getWsRequest().get("safeChildrenType")) ? "" : request.getWsRequest().get("safeChildrenType").toString();
            String device_id = DataUtil.isNullObject(request.getWsRequest().get("device_id")) ? "" : request.getWsRequest().get("device_id").toString();
            String lat = DataUtil.isNullObject(request.getWsRequest().get("lat")) ? "" : request.getWsRequest().get("lat").toString();
            String lng = DataUtil.isNullObject(request.getWsRequest().get("lng")) ? "" : request.getWsRequest().get("lng").toString();
            String criticalSound = DataUtil.isNullObject(request.getWsRequest().get("criticalSound")) ? "" : request.getWsRequest().get("criticalSound").toString();
            String nameSound = DataUtil.isNullObject(request.getWsRequest().get("nameSound")) ? "" : request.getWsRequest().get("nameSound").toString();
            String volumeSound = DataUtil.isNullObject(request.getWsRequest().get("volumeSound")) ? "" : request.getWsRequest().get("volumeSound").toString();
            String isMessage = DataUtil.isNullObject(request.getWsRequest().get("isMessage")) ? "" : request.getWsRequest().get("isMessage").toString();
            bean = this.checkValidLength(messageEn, messageKh, titleEn, titleKh, language);
            if (bean != null) {
                return bean;
            } else {
                bean = new BaseResponseBean();
                CamIdNotificationCMSBean camIdNotifyCMS = new CamIdNotificationCMSBean();
                camIdNotifyCMS.setNotifyId((Long) null);
                camIdNotifyCMS.setNotificationType("none");
                camIdNotifyCMS.setTitleEn(titleEn);
                camIdNotifyCMS.setTitleKh(titleKh);
                camIdNotifyCMS.setMessageEn(messageEn);
                camIdNotifyCMS.setMessageKh(messageKh);
                camIdNotifyCMS.setSchedule(schedule);
                camIdNotifyCMS.setSubId("api");
                camIdNotifyCMS.setLanguageOfNotify(languageOfNotify);
                camIdNotifyCMS.setStatus(5L);
                camIdNotifyCMS.setIconUrl(iconUrl);
                camIdNotifyCMS.setTestPhones(listTestPhone);
                camIdNotifyCMS.setTestStatus(1L);
                camIdNotifyCMS.setPriority(Long.valueOf(priority));
                camIdNotifyCMS.setChannelID(channelID);
                camIdNotifyCMS.setSafeChildrenType(safeChildrenType);
                camIdNotifyCMS.setDevice_id(device_id);
                camIdNotifyCMS.setOS_DEVICE("2");
                camIdNotifyCMS.setLat(lat);
                camIdNotifyCMS.setLng(lng);
                camIdNotifyCMS.setCriticalSound(criticalSound);
                camIdNotifyCMS.setNameSound(nameSound);
                camIdNotifyCMS.setVolumeSound(volumeSound);
                camIdNotifyCMS.setIsMessage(isMessage);
                camIdNotifyCMS.setActionTypeId(21L);
                camIdNotifyCMS.setButtonTitle("Get now");
                NotificationResult result = this.apiGwDao.addNewCamIdNotificationSend(camIdNotifyCMS);
                if (result.getResult() == 1) {
                    bean.setErrorCode(Constants.ERROR_SUCCESS);
                    bean.setMessage("Success");
                    bean.setWsResponse(result);
                    bean.setUserMsg("Success");
                } else {
                    bean.setErrorCode(Constants.NOT_SUCCESS);
                    bean.setMessage("NOT_SUCCESS");
                    bean.setWsResponse(result);
                    bean.setUserMsg("Not success");
                }

                return bean;
            }

        } catch (Exception e) {
            logger.error("An error occured while add new notification: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateCamIdNotification(RequestBean request, String language) {
        logger.info("### Start business updateCamIDNotification of NotificationService");
        if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
            logger.info("Error request : id is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.id.empty.", language);
        }
        BaseResponseBean bean = checkValidCamIdNotify(request, language);
        if (bean != null) {
            return bean;
        }
        try {
            String id = request.getWsRequest().get("id").toString().trim();
            String notificationType = request.getWsRequest().get("notificationType").toString().trim();
            String time = request.getWsRequest().get("time").toString().trim();
            Long interval = 0L;
            if (Integer.valueOf(time) > 1) {
                if (DataUtil.isNullObject(request.getWsRequest().get("interval")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("interval").toString())) {
                    logger.info("Error request : interval is null ");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.interval.empty.", language);
                }
                interval = Long.valueOf(request.getWsRequest().get("interval").toString());
            }
            String pathFileSubId = request.getWsRequest().get("pathFileSubId").toString().trim();
            String subId = request.getWsRequest().get("subId").toString().trim();
            String languageOfNotify = request.getWsRequest().get("languageOfNotify").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String messageEn = DataUtil.isNullObject(request.getWsRequest().get("messageEn")) ? Constants.EMPTY_STRING : request.getWsRequest().get("messageEn").toString();
            String messageKh = DataUtil.isNullObject(request.getWsRequest().get("messageKh")) ? Constants.EMPTY_STRING : request.getWsRequest().get("messageKh").toString();
            String titleEn = DataUtil.isNullObject(request.getWsRequest().get("titleEn")) ? Constants.EMPTY_STRING : request.getWsRequest().get("titleEn").toString();
            String titleKh = DataUtil.isNullObject(request.getWsRequest().get("titleKh")) ? Constants.EMPTY_STRING : request.getWsRequest().get("titleKh").toString();
            String schedule = DataUtil.isNullObject(request.getWsRequest().get("schedule")) ? Constants.EMPTY_STRING : request.getWsRequest().get("schedule").toString();
            String featureImage = DataUtil.isNullObject(request.getWsRequest().get("featureImage")) ? Constants.EMPTY_STRING : request.getWsRequest().get("featureImage").toString();
            String videoUrl = DataUtil.isNullObject(request.getWsRequest().get("videoUrl")) ? Constants.EMPTY_STRING : request.getWsRequest().get("videoUrl").toString();
            String isExchange = DataUtil.isNullObject(request.getWsRequest().get("isExchange")) ? Constants.EMPTY_STRING : request.getWsRequest().get("isExchange").toString();
            String iconUrl = DataUtil.isNullObject(request.getWsRequest().get("iconUrl")) ? Constants.EMPTY_STRING : request.getWsRequest().get("iconUrl").toString();
            String listTestPhone = DataUtil.isNullObject(request.getWsRequest().get("listTestPhone")) ? Constants.EMPTY_STRING : request.getWsRequest().get("listTestPhone").toString();
            String osDeviceStr = DataUtil.isNullObject(request.getWsRequest().get("osDevice")) ? Constants.EMPTY_STRING : request.getWsRequest().get("osDevice").toString();
            String passiveTypeIdStr = DataUtil.isNullObject(request.getWsRequest().get("passiveTypeId")) ? Constants.EMPTY_STRING : request.getWsRequest().get("passiveTypeId").toString();
            String passiveTypeFrom = DataUtil.isNullObject(request.getWsRequest().get("passiveTimeFrom")) ? Constants.EMPTY_STRING : request.getWsRequest().get("passiveTimeFrom").toString();
            String passiveTypeTo = DataUtil.isNullObject(request.getWsRequest().get("passiveTimeTo")) ? Constants.EMPTY_STRING : request.getWsRequest().get("passiveTimeTo").toString();
            String testStatus = "1";
            testStatus = DataUtil.isNullObject(request.getWsRequest().get("testStatus")) ? testStatus : request.getWsRequest().get("testStatus").toString();

            if (StringUtils.isEmpty(messageEn) && languageOfNotify.contains("en") || StringUtils.isEmpty(messageKh) && languageOfNotify.contains("kh")) {
                logger.info("Error request : Message not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.message.not.match.language.", language);
            }

            if (StringUtils.isEmpty(titleEn) && languageOfNotify.contains("en") || StringUtils.isEmpty(titleKh) && languageOfNotify.contains("kh")) {
                logger.info("Error request : title not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.not.match.language.", language);
            }

            if (StringUtils.isNotEmpty(messageEn) && !languageOfNotify.contains("en") || StringUtils.isNotEmpty(messageKh) && !languageOfNotify.contains("kh")) {
                logger.info("Error request : Message not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.message.not.match.language.", language);
            }

            if (StringUtils.isNotEmpty(titleEn) && !languageOfNotify.contains("en") || StringUtils.isNotEmpty(titleKh) && !languageOfNotify.contains("kh")) {
                logger.info("Error request : title not match with language choosen!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.not.match.language.", language);
            }

            if (StringUtils.isEmpty(osDeviceStr)) {
                logger.info("Error request : osDevice is invalid!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.os.device.empty.", language);
            }

            //24/02/2022 Check notify_passive
            Long osDevice = 0L;
            Long passiveTypeId = 0L;

            if ("notify_passive".equals(subId)) {
                if (!StringUtils.isNotEmpty(passiveTypeIdStr)) {
                    logger.info("Error request : title not match with language choosen!");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.passivetypeid.empty.", language);
                }

                try {
                    passiveTypeId = Long.parseLong(passiveTypeIdStr);
                } catch (NumberFormatException e) {
                    logger.info("Error request : passive type id empty with subId= notify_passive!");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.passivetypeid.empty.", language);
                }

                //Validate passive Range
                bean = checkPassiveRange(passiveTypeFrom, language);
                if (bean != null) {
                    return bean;
                }
                bean = checkPassiveRange(passiveTypeTo, language);
                if (bean != null) {
                    return bean;
                }
            }

            try {
                osDevice = Long.parseLong(osDeviceStr);
            } catch (NumberFormatException e) {
                logger.info("Error request : Os Not a number!");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.passivetypeid.empty.", language);
            }

            bean = checkValidLength(messageEn, messageKh, titleEn, titleKh, language);
            if (bean != null) {
                return bean;
            }
            bean = new BaseResponseBean();
            /*Get common information*/
            CamIdNotificationCMSBean camIdNotifyCMS = new CamIdNotificationCMSBean();
            camIdNotifyCMS.setNotifyId(Long.valueOf(id));
            camIdNotifyCMS.setNotificationType(notificationType);
            camIdNotifyCMS.setTitleEn(titleEn);
            camIdNotifyCMS.setTitleKh(titleKh);
            camIdNotifyCMS.setMessageEn(messageEn);
            camIdNotifyCMS.setMessageKh(messageKh);
            camIdNotifyCMS.setTime(Integer.valueOf(time));
            camIdNotifyCMS.setSchedule(schedule);
            camIdNotifyCMS.setSubId(subId);
            camIdNotifyCMS.setLanguageOfNotify(languageOfNotify);
            camIdNotifyCMS.setStatus(Long.valueOf(status));
            camIdNotifyCMS.setFeatureImage(featureImage);
            camIdNotifyCMS.setInterval(interval);
            camIdNotifyCMS.setPathFileSubId(pathFileSubId);
            camIdNotifyCMS.setVideoUrl(videoUrl);
            camIdNotifyCMS.setIsExchange(StringUtils.isNotEmpty(isExchange) ? Long.parseLong(isExchange) : null);
            camIdNotifyCMS.setIconUrl(iconUrl);
            camIdNotifyCMS.setTestPhones(listTestPhone);
            camIdNotifyCMS.setTestStatus(Long.valueOf(testStatus));
            camIdNotifyCMS.setOsDevice(osDevice);
            if (StringUtils.isNotEmpty(passiveTypeIdStr)) {
                camIdNotifyCMS.setPassiveTypeId(passiveTypeId);
                camIdNotifyCMS.setPassiveRange(passiveTypeFrom + "-" + passiveTypeTo);
            }
            /*Get special information each case*/
            switch (notificationType.toUpperCase()) {
                case Constants.CAMID_NOTIFY_NONE: {
                    break;
                }
                case Constants.CAMID_NOTIFY_ACTION_TYPE: {
                    String actionObjectId = DataUtil.isNullObject(request.getWsRequest().get("actionObjectId")) ? Constants.EMPTY_STRING : request.getWsRequest().get("actionObjectId").toString();
                    String actionTypeStr = DataUtil.isNullObject(request.getWsRequest().get("actionTypeId")) ? Constants.EMPTY_STRING : request.getWsRequest().get("actionTypeId").toString();
                    String buttonTitle = DataUtil.isNullObject(request.getWsRequest().get("buttonTitle")) ? Constants.EMPTY_STRING : request.getWsRequest().get("buttonTitle").toString();
                    Long actionType = 0L;
                    if (StringUtils.isEmpty(actionTypeStr) || StringUtils.isEmpty(buttonTitle)) {
                        logger.info("### Error request : Missing actionType or buttonTitle!");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.missing.", language);
                    }

                    if (METFONE_SERVICE.equals(actionTypeStr) && camIdNotifyCMS.getIsExchange() == null) {
                        logger.info("### Error request : Missing isExchange for Metfone_service");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.metfoneService.missing.isExchange.", language);
                    }
                    if (!METFONE_SERVICE.equals(actionTypeStr) && camIdNotifyCMS.getIsExchange() != null) {
                        logger.info("### Error request : Has isExchange for another case not Metfone_service");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.another.has.isExchange.", language);
                    }

                    try {
                        actionType = Long.valueOf(actionTypeStr);
                    } catch (NumberFormatException ne) {
                        logger.info("### ActionType is not a number: " + ne.getMessage());
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.not.number.", language);
                    }

                    if (!BUTTON_TITLE_PLAY_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_GET_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_LOGIN_NOW.equalsIgnoreCase(buttonTitle)
                            && !BUTTON_TITLE_SIGN_UP_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_VIEW_CHALLENGE.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_MORE_INFOR.equalsIgnoreCase(buttonTitle)
                            && !BUTTON_TITLE_TRY_IT_OUT.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_BOOK_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_UPDATE_NOW.equalsIgnoreCase(buttonTitle)) {
                        logger.info("### Button title is not get_now nor play_now nor login_now nor sign_up_now");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.missing.", language);
                    }
//                  
                    if (BUTTON_TITLE_LOGIN_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!LOGIN_NOW_TYPE_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatch.", language);
                        }
                    }

                    if (BUTTON_TITLE_SIGN_UP_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!SIGN_UP_NOW_TYPE_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatch.", language);
                        }
                    }

                    if (BUTTON_TITLE_VIEW_CHALLENGE.equalsIgnoreCase(buttonTitle) || BUTTON_TITLE_MORE_INFOR.equalsIgnoreCase(buttonTitle)
                            || BUTTON_TITLE_TRY_IT_OUT.equalsIgnoreCase(buttonTitle) || BUTTON_TITLE_BOOK_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!PASSIVE_TYPE_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatch.", language);
                        }
                    }

                    if (BUTTON_TITLE_UPDATE_NOW.equalsIgnoreCase(buttonTitle)) {
                        if (!UPDATE_PHONE_NUMBER_IN_DB.equals(actionType) && !UPDATE_INFORMATION_IN_DB.equals(actionType) || !"".equals(actionObjectId)) {
                            logger.info("### Button title and actiontype not match");
                            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.notMatch.", language);

                        }
                    }

                    camIdNotifyCMS.setActionTypeId(actionType);
                    camIdNotifyCMS.setButtonTitle(buttonTitle);
                    camIdNotifyCMS.setActionObjectId(actionObjectId);
                    break;
                }
                case Constants.CAMID_NOTIFY_LINK: {
                    String link = DataUtil.isNullObject(request.getWsRequest().get("link")) ? Constants.EMPTY_STRING : request.getWsRequest().get("link").toString();
                    /*validate link*/
                    String linkLength = environment.getProperty("camid.length.link");
                    if (link.length() >= Integer.valueOf(linkLength)) {
                        logger.info("Error request : link over length ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.link.over.length.", language);
                    }

                    String buttonTitle = DataUtil.isNullObject(request.getWsRequest().get("buttonTitle")) ? Constants.EMPTY_STRING : request.getWsRequest().get("buttonTitle").toString();
//                    if (!BUTTON_TITLE_PLAY_NOW.equalsIgnoreCase(buttonTitle) && !BUTTON_TITLE_GET_NOW.equalsIgnoreCase(buttonTitle)) {
//                        logger.info("### Button title is not get_now nor play_now");
//                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.buttonType.missing.", language);
//                    }
                    camIdNotifyCMS.setLink(link);
                    camIdNotifyCMS.setButtonTitle(buttonTitle);
                    break;
                }
                default: {
                    logger.info("Error request : Not match any notifycation type!");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.notifyType.not.match.", language);
                }
            }

            int result = apiGwDao.updateCamIdNotification(camIdNotifyCMS);
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
            }
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while update notification: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean deleteCamIdNotification(RequestBean request, String language) {
        logger.info("### Start business deleteCamIDNotification of NotificationService");
        if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
            logger.info("Error request : id is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.id.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String id = request.getWsRequest().get("id").toString().trim();
            int result = apiGwDao.deleteCamIdNotification(Long.valueOf(id));
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.NOT_SUCCESS);
                bean.setMessage("NOT_SUCCESS");
                bean.setWsResponse(result);
                bean.setUserMsg("Not success");
            }
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while delete notification: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getCamIdActionType(String language) {
        logger.info("### Start business getCamIdActionType of NotificationService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            List<CamIdActionTypeBean> result = apiGwDao.getListCamIdNotificationType(Constants.CAMID_NOTIFY_ACTION_TYPE);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while get camIdNotificationType: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getAllCamIdNotification(RequestBean request, String language) {
        logger.info("### Start business getAllCamIdNotification of NotificationService");
        int pageNumber = 1;
        int pageSize = 10;

        if (DataUtil.isNullObject(request.getWsRequest().get("pageNumber")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNumber").toString())) {
            logger.info("Error request : pageNumber is null, get default is first page");
        }

        String status = DataUtil.isNullObject(request.getWsRequest().get("status")) ? Constants.EMPTY_STRING : request.getWsRequest().get("status").toString();

        Date from = DataUtil.isNullObject(request.getWsRequest().get("from")) ? null : new Date(Long.parseLong(request.getWsRequest().get("from").toString()));

        Date to = DataUtil.isNullObject(request.getWsRequest().get("to")) ? null : new Date(Long.parseLong(request.getWsRequest().get("to").toString()));

        String keyWord = DataUtil.isNullObject(request.getWsRequest().get("keyword")) ? Constants.EMPTY_STRING : request.getWsRequest().get("keyword").toString().toUpperCase();

        try {
            pageNumber = Integer.valueOf(request.getWsRequest().get("pageNumber").toString());
            pageSize = DataUtil.isNullObject(request.getWsRequest().get("page_size")) ? pageSize : Integer.valueOf(request.getWsRequest().get("page_size").toString());
        } catch (NumberFormatException ne) {
            logger.error("### PageNumber is not a number....");
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "camId.pageNumer.not.number.", language);
        }

        BaseResponseBean bean = new BaseResponseBean();
        try {
            List<CamIdNotificationCMSBean> resultAll = apiGwDao.getAllCamIdNotification(0, pageSize, from, to, status, keyWord);
            List<CamIdNotificationCMSBean> result = apiGwDao.getAllCamIdNotification(pageNumber, pageSize, from, to, status, keyWord);
            int totalCamidNotifyRecord = DataUtil.isNullOrEmpty(resultAll) ? 0 : resultAll.size();
            Map<String, Object> finalResult = new HashMap<String, Object>();
            finalResult.put("totalRecord", totalCamidNotifyRecord);
            finalResult.put("notifyList", result);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(finalResult);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while get camIdNotificationType: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public void wsUpdateCamIdDeviceToken(ResponseSuccessBean bean, String camId, String token, int os, String deviceId, String language, String versionApp) {
        //Validate camid when client send default value
        if ("0".equals(camId)) {
            camId = "";
        }
        try {
            CamIdDeviceToken deviceToken = apiGwDao.getCamIdDeviceTokenByCamId("", deviceId);
            if (deviceToken != null) {
                deviceToken.setToken(token);
                deviceToken.setOperatingSystem(os);
                deviceToken.setLastUpdate(CommonUtil.getCurrentTime());
                deviceToken.setStatus(1);
                deviceToken.setVersionApp(versionApp == null ? "" : versionApp);
                if (StringUtils.isNotEmpty(camId)) {
                    deviceToken.setCamId(camId);
                }
                deviceToken.setLang(language);
                apiGwDao.update(deviceToken);
            } else {

//                deviceToken = new CamIdDeviceToken();
//                if (StringUtils.isNotEmpty(camId)) {
//                    deviceToken.setCamId(camId);
//                }
//                deviceToken.setDeviceId(deviceId);
//                deviceToken.setToken(token);
//                deviceToken.setCreateDate(CommonUtil.getCurrentTime());
//                deviceToken.setLastUpdate(CommonUtil.getCurrentTime());
//                deviceToken.setOperatingSystem(os);
//                deviceToken.setLang(language);
                apiGwDao.addNewDeviceToken(camId, deviceId, token, os, language, versionApp);
            }
            logger.info("Update device token successfully" + token);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setUserMsg("Update device token successfully!" + token);
        } catch (Exception e) {
            logger.error("Update device token unsuccessfully", e);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setUserMsg("Update device token unsuccessfully!");
        }
    }

    @Override
    public void wsGetCamIDeviceToken(ResponseSuccessBean bean, String camId, String deviceId) {
        try {
            CamIdDeviceToken deviceToken = apiGwDao.getCamIdDeviceTokenByCamId(camId, deviceId);
            bean.setWsResponse(deviceToken);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setUserMsg("Successful");
        } catch (Exception e) {
            logger.error("### An error occured while get FCM token by camid or deviceId", e);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setUserMsg("Unsuccessful!");
        }
    }

    @Override
    public BaseResponseBean getCamIdNotificationById(RequestBean request, String language) {
        logger.info("Start business getCamIdNotificationById of NotifyServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationId").toString())) {
            logger.info("Error request : notificationId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
        try {
            BaseResponseBean bean = new BaseResponseBean();
            String notificationId = request.getWsRequest().get("notificationId").toString().trim();

            CamIdNotificationCMSBean camIdNotificationCMSBean = apiGwDao.getCamIdNotificationById(notificationId);

            //Modify for CMS
            String passiveTime = camIdNotificationCMSBean.getPassiveRange();
            if (StringUtils.isNotEmpty(camIdNotificationCMSBean.getPassiveRange())) {
                String[] rangeSplit = passiveTime.split("-", -1);
                camIdNotificationCMSBean.setPassiveTimeFrom(rangeSplit[0]);
                camIdNotificationCMSBean.setPassiveTimeTo(rangeSplit[1]);
            }
            bean.setWsResponse(camIdNotificationCMSBean);
            bean.setErrorCode(Constants.SUCCESS);
            bean.setMessage("SUCCESS");
            bean.setUserMsg("Success");
            return bean;
        } catch (Exception e) {
            logger.error("### parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean sendSMSInternal(RequestBean request, String language) {
        logger.info("Start business sendSMSInternal of NotifyServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
            logger.info("Error request : isdn is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("content")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("content").toString())) {
            logger.info("Error request : content is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("senderName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("senderName").toString())) {
            logger.info("Error request : senderName is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
        try {
            BaseResponseBean bean = new BaseResponseBean();
            String isdn = request.getWsRequest().get("isdn").toString().trim();
            String content = request.getWsRequest().get("content").toString().trim();
            String senderName = request.getWsRequest().get("senderName").toString().trim();

            /*isdn's metfone*/
            smsService.connectServer();
            int statusSend = smsService.sendSMS(isdn, content, senderName);
            if (statusSend == 0) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("SUCCESS");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage("FAIL");
                bean.setUserMsg("Fail");
            }

            return bean;
        } catch (Exception e) {
            logger.error("### parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getFilmInfoByName(RequestBean request, String language) {
        logger.info("Start business getFilmInfoByName of NotifyServiceImpl");
        if (DataUtil.isNullObject(request.getWsRequest().get("filmName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("filmName").toString())) {
            logger.info("Error request : filmName is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.filmName.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String filmName = request.getWsRequest().get("filmName").toString().trim();
            String url = "http://36.37.242.22/solr/phimkeeng/select?qt=search&device_id=&Platform=Mocha&fl=*%2Cscore&start=0&fq=isDisplayOnMocha%3A1&sort=score+desc&client_type=Android&rows=20&version=4.0.19&revision=15252&q=" + filmName + "&clientType=Android&msisdn=0111111111&wt=json";
            BaseResponsesDto dto = WebServiceUtil.callApiGet(url);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setWsResponse(dto);
            bean.setUserMsg("Successful");
        } catch (Exception e) {
            logger.error("### parse description error: ", e);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setUserMsg("Unsuccessful!");
        }
        return bean;
    }

    @Override
    public BaseResponseBean getRewardDetail(RequestBean request, String language) {
        logger.info("### Start business getRewardDetail of NotificationService");
        if (DataUtil.isNullObject(request.getWsRequest().get("rewardName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("rewardName").toString())) {
            logger.info("Error request : rewardName is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.reward.detail.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String rewardDetail = request.getWsRequest().get("rewardName").toString();
            List<CamIdRewardDetailBean> result = apiGwDao.getListRewardDetail(rewardDetail);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
            return bean;

        } catch (Exception e) {
            logger.error("An error occured while get getRewardDetail: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListPassiveType(RequestBean request, String language) {
        logger.info("### Start business getListPassiveType of NotificationService");
        if (DataUtil.isNullObject(request.getWsRequest().get("pageNumber")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNumber").toString())) {
            logger.info("Error request : pageNumber is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.pageNumer.not.number.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            Long pageNumber = 0L;
            try {
                pageNumber = Long.parseLong(request.getWsRequest().get("pageNumber").toString());
            } catch (NumberFormatException ne) {
                logger.info("Error request : pageNumber is not a number ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.pageNumer.not.number.", language);
            }
            List<CamIdPassiveTypeBean> result = apiGwDao.getListPassiveType(pageNumber);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get getListPassiveType: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListPassiveControl(RequestBean request, String language) {
        logger.info("### Start business getListPassiveControl of NotificationService");
        if (DataUtil.isNullObject(request.getWsRequest().get("pageNumber")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNumber").toString())) {
            logger.info("Error request : pageNumber is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.pageNumer.not.number.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            Long pageNumber = 0L;
            try {
                pageNumber = Long.parseLong(request.getWsRequest().get("pageNumber").toString());
            } catch (NumberFormatException ne) {
                logger.info("Error request : pageNumber is not a number ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.pageNumer.not.number.", language);
            }
            List<CamIdPassiveTypeBean> result = apiGwDao.getListPassiveTypeControl(pageNumber);
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setWsResponse(result);
            bean.setUserMsg("Success");
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get getListPassiveControl: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updatePassiveControl(RequestBean request, String language) {
        logger.info("### Start business updatePassiveControl of NotificationService");
        if (DataUtil.isNullObject(request.getWsRequest().get("passiveTypeId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("passiveTypeId").toString())) {
            logger.info("Error request : passiveTypeId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.id.not.number.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
            logger.info("Error request : status is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.status.empty.", language);
        }
        BaseResponseBean bean = new BaseResponseBean();
        try {
            Long passiveTypeId = null;
            Long status = null;
            try {
                passiveTypeId = Long.parseLong(request.getWsRequest().get("passiveTypeId").toString());
                status = Long.parseLong(request.getWsRequest().get("status").toString());
            } catch (NumberFormatException ne) {
                logger.info("Error request : passiveTypeId or status is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }
            boolean result = apiGwDao.updatePassiveTypeControl(passiveTypeId, status);
            if (result) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setWsResponse(result);
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage("Fail");
                bean.setWsResponse(result);
                bean.setUserMsg("Update State fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get updatePassiveControl: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    //--------------------------------------
    //--------------------------------------
    //--------------------------------------
    private BaseResponseBean checkValidInformation(RequestBean request, String language) {

        if (DataUtil.isNullObject(request.getWsRequest().get("title")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("title").toString())) {
            logger.info("Error request : title is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("message")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("message").toString())) {
            logger.info("Error request : message is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("topic")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("topic").toString())) {
            logger.info("Error request : topic is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("notificationType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationType").toString())) {
            logger.info("Error request : notificationType is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("link")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("link").toString())) {
            logger.info("Error request : link is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("time")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("time").toString())) {
            logger.info("Error request : time is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (!"8".equals(request.getWsRequest().get("notificationType").toString()) && DataUtil.isNullObject(request.getWsRequest().get("actionType"))) {
            logger.info("Error request : actionType is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
            logger.info("Error request : status is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }

        if ("0".equals(DataUtil.isNullObject(request.getWsRequest().get("status")))) {
            logger.info("Error request : status is 0 mean removed, ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.statusIsZero.empty.", language);
        }

        return null;
    }

    private List<NotificationBean> convertNotification(List<NotificationDtoV2Expand> dtoList) {
        List<NotificationBean> listBean = new ArrayList<>();
        dtoList.forEach(e -> {
            NotificationBean bean = new NotificationBean();
            bean.setAccNotificationId(e.getAccountNotifyId());
            bean.setData(null);
            bean.setEndTime(null);
            bean.setIconUrl(e.getNotificationImage());
            bean.setImageUrl(e.getNotificationImage());
            bean.setInsertTime(DateTimeUtils.convertStringToDateTime(e.getCreateDate()));
            bean.setIsRead(e.getIsRead());
            bean.setMessage(e.getMessage());
            bean.setNotificationId(e.getNotificationId());
            bean.setNotificationType(e.getNotificationType());
            bean.setParams(null);
            bean.setReceivedTime(e.getCreateDate());
            bean.setServiceCode(e.getLink());
            bean.setServiceName(e.getTopic());
            bean.setStartTime(null);
            bean.setTitle(e.getTitle());
            bean.setActionType(e.getActionType());
            listBean.add(bean);
        });
        //link -> service_code, topic -> service_name, description -> code, time ->is_save
        return listBean;
    }

    private List<NotificationDtoV2> convertCovid19ToNotification(List<NewsCovidDto> newsCovidDtoList) {
        List<NotificationDtoV2> result = new ArrayList<>();
        newsCovidDtoList.stream().map((element) -> {
            NotificationDtoV2 entity = new NotificationDtoV2();
            entity.setNotificationId(Long.valueOf(element.getNewsCovidId()));
            entity.setTitle(element.getTitle());
            entity.setMessage(element.getMessage());
            entity.setLink(element.getLink());
            entity.setCreateDate(element.getCreateDate());
            entity.setNotificationImage(element.getNotificationImage());
            entity.setTopic(element.getTopic());
            entity.setNotificationType(element.getNotificationType());
            entity.setStatus(element.getStatus());
            entity.setDescription(element.getDescription());
            entity.setActionType(null);
            entity.setTime(element.getTime());
            return entity;
        }).forEachOrdered((entity) -> {
            result.add(entity);
        });
        return result;
    }

    private List<NotificationDtoV2> convertCovid119DtoToNotfication(List<NewsCovid19Entity> entity) {
        List<NotificationDtoV2> result = new ArrayList<>();
        entity.stream().map((element) -> {
            NotificationDtoV2 dto = new NotificationDtoV2();
            dto.setNotificationId(Long.valueOf(element.getNewsCovidId()));
            dto.setTitle(element.getTitle());
            dto.setMessage(element.getMessage());
            dto.setLink(element.getLink());
            dto.setCreateDate(null);
            dto.setNotificationImage(element.getNotificationImage());
            dto.setTopic(element.getTopic());
            dto.setNotificationType(element.getNotificationType());
            dto.setStatus(element.getStatus());
            dto.setDescription(element.getDescription());
            dto.setActionType(null);
            dto.setTime(element.getTime());
            return dto;
        }).forEachOrdered((dto) -> {
            result.add(dto);
        });
        return result;
    }

    private List<NotificationDtoV2Expand> bonusInformationForNotification(List<NotificationDtoV2> notificationDtoV2List, String isdn) {
        List<NotificationDtoV2Expand> result = new ArrayList<>();
        notificationDtoV2List.stream().map((element) -> {
            NotificationAccountDtoV2 notifiAccDtoV2 = apiGwDao.findIsReadNotificationByIsdn(isdn, element.getNotificationId());
            NotificationDtoV2Expand entity = new NotificationDtoV2Expand();
            entity.setAccountNotifyId(notifiAccDtoV2.getAccountNotifyId());
            entity.setActionType(element.getActionType());
            entity.setCreateDate(element.getCreateDate());
            entity.setDescription(element.getDescription());
            entity.setIsRead(notifiAccDtoV2.getIsRead());
            entity.setLink(element.getLink());
            entity.setMessage(element.getMessage());
            entity.setNotificationId(element.getNotificationId());
            entity.setNotificationImage(element.getNotificationImage());
            entity.setNotificationType(element.getNotificationType());
            entity.setStatus(element.getStatus());
            entity.setTime(element.getTime());
            entity.setTitle(element.getTitle());
            entity.setTopic(element.getTopic());
            return entity;
        }).forEachOrdered((entity) -> {
            result.add(entity);
        });
        return result;
    }

    private boolean isReadAlready(String isdn, Long notificationId) {
        NotificationAccountDtoV2 dto = apiGwDao.findIsReadNotificationByIsdn(isdn, notificationId);
        if (dto.getAccountNotifyId() != null) {
            return true;
        }
        return false;
    }

    private BaseResponseBean checkValidCamIdNotifyApi(RequestBean request, String language) {
        if (DataUtil.isNullObject(request.getWsRequest().get("titleEn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("titleEn").toString())) {
            if (DataUtil.isNullObject(request.getWsRequest().get("titleKh")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("titleEn").toString())) {
                logger.info("Error request : both titles is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.empty.", language);
            }
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("iconUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("iconUrl").toString())) {
            logger.info("Error request : IconUrl is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.iconurl.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("priority")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("priority").toString())) {
            logger.info("Error request : priority is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.iconurl.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("channelID")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelID").toString())) {
            logger.info("Error request : channelID is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.iconurl.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("safeChildrenType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("safeChildrenType").toString())) {
            logger.info("Error request : safeChildrenType is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.iconurl.empty.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("device_id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("device_id").toString())) {
            logger.info("Error request : device_id is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.iconurl.empty.", language);
        }
        return null;
    }

    private BaseResponseBean checkValidCamIdNotify(RequestBean request, String language) {
        if (DataUtil.isNullObject(request.getWsRequest().get("notificationType")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("notificationType").toString())) {
            logger.info("Error request : notificationType is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.notificationType.empty.", language);
        }

        if (NOTIFICATION_CAMID_TYPE_IS_NONE.equalsIgnoreCase(request.getWsRequest().get("notificationType").toString())) {
            if (!DataUtil.isNullOrEmpty(request.getWsRequest().get("actionTypeId").toString()) || !DataUtil.isNullOrEmpty(request.getWsRequest().get("link").toString()) || !DataUtil.isNullOrEmpty(request.getWsRequest().get("buttonTitle").toString())) {
                logger.info("Error request : notificationType is none, actionTypeId or link or buttonTitle is not allow! ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionTypeId.link.buttonTitle.not.allow.", language);
            }
        }

        if (NOTIFICATION_CAMID_TYPE_IS_LINK.equalsIgnoreCase(request.getWsRequest().get("notificationType").toString())) {
            if (DataUtil.isNullOrEmpty(request.getWsRequest().get("link").toString()) || DataUtil.isNullOrEmpty(request.getWsRequest().get("buttonTitle").toString())) {
                logger.info("Error request : notificationType is link, link or buttonTitle is not empty! ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.link.buttonTitle.not.empty.", language);
            }
            if (!DataUtil.isNullOrEmpty(request.getWsRequest().get("actionTypeId").toString())) {
                logger.info("Error request : notificationType is link, actionTypeId is not allow! ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.link.then.actionTypeId.not.allow.", language);
            }
        }

        if (NOTIFICATION_CAMID_TYPE_IS_ACTION_TYPE.equalsIgnoreCase(request.getWsRequest().get("notificationType").toString())) {
            if (DataUtil.isNullOrEmpty(request.getWsRequest().get("actionTypeId").toString()) || DataUtil.isNullOrEmpty(request.getWsRequest().get("buttonTitle").toString())) {
                logger.info("Error request : notificationType is action_type, actionTypeId or buttonTitle is not empty! ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionTypeId.buttonTitle.not.empty.", language);
            }
            if (!DataUtil.isNullOrEmpty(request.getWsRequest().get("link").toString())) {
                logger.info("Error request : notificationType is action_type, link is not allow! ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.actionType.then.link.not.allow.", language);
            }
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("time")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("time").toString())) {
            logger.info("Error request : time is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.time.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("titleEn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("titleEn").toString())) {
            if (DataUtil.isNullObject(request.getWsRequest().get("titleKh")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("titleEn").toString())) {
                logger.info("Error request : both titles is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.empty.", language);
            }
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("subId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("subId").toString())) {
            logger.info("Error request : subId is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.subId.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
            logger.info("Error request : status is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.status.empty.", language);
        }

        if (DataUtil.isNullObject(request.getWsRequest().get("iconUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("iconUrl").toString())) {
            logger.info("Error request : IconUrl is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.iconurl.empty.", language);
        }
        return null;
    }

    private BaseResponseBean checkValidLength(String messageEn, String messageKh, String titleEn, String titleKh, String language) {
        //String messageLength = environment.getProperty("camid.length.message");
        String titleLength = environment.getProperty("camid.length.title");

//        if (messageEn.length() >= Integer.valueOf(messageLength) || messageKh.length() >= Integer.valueOf(messageLength)) {
//            logger.info("Error request : message over length ");
//            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.message.over.length.", language);
//        }
        if (titleEn.length() >= Integer.valueOf(titleLength) || titleKh.length() >= Integer.valueOf(titleLength)) {
            logger.info("Error request : title over length ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camId.title.over.length.", language);
        }
        return null;
    }

    private BaseResponseBean checkPassiveRange(String input, String language) {
        String[] valueSplit = input.split(":", -1);
        if (valueSplit.length != 3) {
            logger.info("Error request : passive range invalid ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.passive.range.out.", language);
        }
        boolean isHour = true;
        for (String ele : valueSplit) {
            try {
                Long eleL = Long.parseLong(ele);
                if (isHour) {
                    if (0L > eleL && eleL > 23L) {
                        logger.info("Error request : passive range invalid, number < 0 or number > 23 ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.passive.range.out.", language);
                    }
                    isHour = false;
                } else {
                    if (0L > eleL && eleL > 59L) {
                        logger.info("Error request : passive range invalid, number < 0 or number > 59 ");
                        return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.passive.range.out.", language);
                    }
                }
            } catch (NumberFormatException ne) {
                logger.info("Error request : passive range invalid, not a number ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "camid.passive.range.out.", language);
            }
        }
        return null;
    }
}
