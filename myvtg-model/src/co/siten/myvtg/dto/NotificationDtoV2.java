/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import co.siten.myvtg.util.CommonUtil;
import java.util.Date;

/**
 * NotificationDtoV2
 *
 * @author phuonghc
 */
public class NotificationDtoV2 implements Comparable<NotificationDtoV2>{

    private Long notificationId;
    private String title;
    private String message;
    private String link;
    private String createDate;
    private String notificationImage;
    private String topic;
    private String notificationType;
    private String status;
    private String description;
    private String actionType;
    private int time;

    public NotificationDtoV2() {
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getNotificationImage() {
        return notificationImage;
    }

    public void setNotificationImage(String notificationImage) {
        this.notificationImage = notificationImage;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(NotificationDtoV2 o) {
        Date o1 = CommonUtil.convertFromStringToDate(getCreateDate());
        Date o2 = CommonUtil.convertFromStringToDate(o.getCreateDate());
        return -o1.compareTo(o2);
    }
}
