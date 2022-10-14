/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * NewsCovid19Entity
 *
 * @author phuonghc
 */
@Entity
@Table(name = "NEWS_COVIDS")
public class NewsCovid19Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "NEWS_COVID_ID")
    private String newsCovidId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "LINK")
    private String link;

    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "NOTIFICATION_IMAGE")
    private String notificationImage;

    @Column(name = "TOPIC")
    private String topic;

    @Column(name = "NOTIFICATION_TYPE")
    private String notificationType;
    
    @Column(name = "STATUS")
    private String status;
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "TIME")
    private int time;

    public NewsCovid19Entity() {
    }

    public String getNewsCovidId() {
        return newsCovidId;
    }

    public void setNewsCovidId(String newsCovidId) {
        this.newsCovidId = newsCovidId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    
}
