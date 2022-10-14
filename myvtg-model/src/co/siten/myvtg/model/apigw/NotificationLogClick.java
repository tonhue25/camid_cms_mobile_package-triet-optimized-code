package co.siten.myvtg.model.apigw;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "NOTIFICATION_LOG_CLICK")
@NamedQuery(name = "NotificationLogClick.findAll", query = "SELECT a FROM NotificationLogClick a")
public class NotificationLogClick implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "NOTIFICATION_LOG_CLICK_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "ISDN")
    private String isdn;

    @Column(name = "NOTIFICATION_ID")
    private Long notificationId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    public NotificationLogClick() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }


    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }



}
