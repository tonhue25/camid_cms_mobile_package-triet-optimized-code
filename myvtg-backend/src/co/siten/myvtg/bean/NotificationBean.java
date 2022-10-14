package co.siten.myvtg.bean;

import co.siten.myvtg.util.CommonUtil;
import java.util.Date;

public class NotificationBean implements Comparable<NotificationBean> {

    private Long accNotificationId;
    private String message;
    private Long notificationId;
    private String receivedTime;
    private Date insertTime;
    private String title;
    private String serviceCode;
    private String serviceName;
    private Date startTime;
    private Date endTime;
    private Integer isRead;
    private String iconUrl;
    private String imageUrl;
    private String notificationType;
    private String params;
    //daibq bo sung
    private String data;
    //phuonghc 25062020
    private String actionType;

    public NotificationBean() {
    }

    public NotificationBean(Long accNotificationId, String message, Long notificationId, String title,
            String serviceCode, String serviceName, Date startTime, Date endTime, Integer isRead,
            String iconUrl, String imageUrl, String notificationType, Date insertTime, String params) {
        this.accNotificationId = accNotificationId;
        this.message = message;
        this.notificationId = notificationId;
        this.title = title;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isRead = isRead;
        this.iconUrl = iconUrl;
        this.imageUrl = imageUrl;
        this.notificationType = notificationType;
        this.insertTime = insertTime;
        this.params = params;
    }

    /**
     * @author daibq
     * @param accNotificationId
     * @param message
     * @param notificationId
     * @param receivedTime
     * @param insertTime
     * @param title
     * @param serviceCode
     * @param serviceName
     * @param startTime
     * @param endTime
     * @param isRead
     * @param iconUrl
     * @param imageUrl
     * @param notificationType
     * @param params
     * @param data
     */
    public NotificationBean(Long accNotificationId, String message, Long notificationId, String title,
            String serviceCode, String serviceName, Date startTime, Date endTime, Integer isRead,
            String iconUrl, String imageUrl, String notificationType, Date insertTime, String params, String data) {
        this.accNotificationId = accNotificationId;
        this.message = message;
        this.notificationId = notificationId;
        this.title = title;
        this.serviceCode = serviceCode;
        this.serviceName = serviceName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isRead = isRead;
        this.iconUrl = iconUrl;
        this.imageUrl = imageUrl;
        this.notificationType = notificationType;
        this.insertTime = insertTime;
        this.params = params;
        this.data = data;
    }

    public Long getAccNotificationId() {
        return accNotificationId;
    }

    public void setAccNotificationId(Long accNotificationId) {
        this.accNotificationId = accNotificationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getReceivedTime() {
        return receivedTime;
    }

    public void setReceivedTime(String receivedTime) {
        this.receivedTime = receivedTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    @Override
    public int compareTo(NotificationBean o) {
        return -getInsertTime().compareTo(o.getInsertTime());
    }
}
