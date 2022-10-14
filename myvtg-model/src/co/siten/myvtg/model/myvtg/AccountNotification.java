package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ACCOUNT_NOTIFICATION")
@NamedQuery(name = "AccountNotification.findAll", query = "SELECT a FROM AccountNotification a")
public class AccountNotification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "ACCOUNT_NOTIFICATION_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "ISDN")
    private String isdn;

    @Column(name = "NOTIFICATION_ID")
    private Long notificationId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INSERT_TIME")
    private Date insertTime;

    @Column(name = "PARAMS")
    private String params;

    @Column(name = "IS_READ")
    private Integer isRead;

//    @Lob
//    @Column(name = "DATA")
//    private String data;

    public AccountNotification() {
    }

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

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }
    
}
