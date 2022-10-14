/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * CamIdNotificationCMSBean
 *
 * @author phuonghc
 */
public class CamIdNotificationCMSBean {

    private Long notifyId;
    private String notificationType;
    private Long actionTypeId;
    private String link;
    private String buttonTitle;
    private String titleEn;
    private String titleKh;
    private String messageEn;
    private String messageKh;
    private long time;
    private long interval;
    private String subId;
    private String schedule;
    private String languageOfNotify;
    private String featureImage;
    private long status;
    private String iconUrl;
    /*Get only*/
    private String actionType;
    private String createDate;
    private String updateDate;
    /*add param pathFileSubId - 24122020*/
    private String pathFileSubId;
    /*add param actionObjectId - 28122020*/
    private String actionObjectId;
    /*add param isExchange - 06012021*/
    private String videoUrl;
    // Applied only for metfone_service
    private Long isExchange;
    //add param passiveTypeId, osDevice - 24022022	
    private Long passiveTypeId;
    private Long osDevice;
    private String passiveRange;
    private String passiveTimeFrom;
    private String passiveTimeTo;

    @JsonIgnore
    private String testPhones;

    private String listTestPhone;

    private long testStatus;

    private Long priority;
    private String channelID;
    private String safeChildrenType;
    private String device_id;
    private String lat;
    private String lng;
    private String criticalSound;
    private String nameSound;
    private String volumeSound;
    private String OS_DEVICE;
    private String isMessage;

    public CamIdNotificationCMSBean() {
    }

    public String getOS_DEVICE() {
        return OS_DEVICE;
    }

    public void setOS_DEVICE(String OS_DEVICE) {
        this.OS_DEVICE = OS_DEVICE;
    }

    public String getIsMessage() {
        return isMessage;
    }

    public void setIsMessage(String isMessage) {
        this.isMessage = isMessage;
    }

    public Long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Long notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Long getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(Long actionTypeId) {
        this.actionTypeId = actionTypeId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleKh() {
        return titleKh;
    }

    public void setTitleKh(String titleKh) {
        this.titleKh = titleKh;
    }

    public String getMessageEn() {
        return messageEn;
    }

    public void setMessageEn(String messageEn) {
        this.messageEn = messageEn;
    }

    public String getMessageKh() {
        return messageKh;
    }

    public void setMessageKh(String messageKh) {
        this.messageKh = messageKh;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getLanguageOfNotify() {
        return languageOfNotify;
    }

    public void setLanguageOfNotify(String languageOfNotify) {
        this.languageOfNotify = languageOfNotify;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getPathFileSubId() {
        return pathFileSubId;
    }

    public void setPathFileSubId(String pathFileSubId) {
        this.pathFileSubId = pathFileSubId;
    }

    public String getActionObjectId() {
        return actionObjectId;
    }

    public void setActionObjectId(String actionObjectId) {
        this.actionObjectId = actionObjectId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(Long isExchange) {
        this.isExchange = isExchange;
    }

    public Long getPassiveTypeId() {
        return passiveTypeId;
    }

    public void setPassiveTypeId(Long passiveTypeId) {
        this.passiveTypeId = passiveTypeId;
    }

    public Long getOsDevice() {
        return osDevice;
    }

    public void setOsDevice(Long osDevice) {
        this.osDevice = osDevice;
    }

    public String getPassiveRange() {
        return passiveRange;
    }

    public void setPassiveRange(String passiveRange) {
        this.passiveRange = passiveRange;
    }

    public String getPassiveTimeFrom() {
        return passiveTimeFrom;
    }

    public void setPassiveTimeFrom(String passiveTimeFrom) {
        this.passiveTimeFrom = passiveTimeFrom;
    }

    public String getPassiveTimeTo() {
        return passiveTimeTo;
    }

    public void setPassiveTimeTo(String passiveTimeTo) {
        this.passiveTimeTo = passiveTimeTo;
    }

    public String getTestPhones() {
        return testPhones;
    }

    public void setTestPhones(String testPhones) {
        this.testPhones = testPhones;
    }

    public String getListTestPhone() {
        return listTestPhone;
    }

    public void setListTestPhone(String listTestPhone) {
        this.listTestPhone = listTestPhone;
    }

    public long getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(long testStatus) {
        this.testStatus = testStatus;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getSafeChildrenType() {
        return safeChildrenType;
    }

    public void setSafeChildrenType(String safeChildrenType) {
        this.safeChildrenType = safeChildrenType;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCriticalSound() {
        return criticalSound;
    }

    public void setCriticalSound(String criticalSound) {
        this.criticalSound = criticalSound;
    }

    public String getNameSound() {
        return nameSound;
    }

    public void setNameSound(String nameSound) {
        this.nameSound = nameSound;
    }

    public String getVolumeSound() {
        return volumeSound;
    }

    public void setVolumeSound(String volumeSound) {
        this.volumeSound = volumeSound;
    }

}
