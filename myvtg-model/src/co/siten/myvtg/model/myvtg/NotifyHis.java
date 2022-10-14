package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "NOTIFY_HIS")
@NamedQuery(name = "NotifyHis.findAll", query = "SELECT m FROM NotifyHis m")
public class NotifyHis implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(sequenceName = "NOTIFY_HIS_SEQ", allocationSize = 1, name = "SEQ")
    @Column(name = "ID")
    private long id;

    @Column(name = "NOTIFICATION_ID")
    private Long notificationId;

    private String isdn;

    private String data;

    @Column(name = "INSERT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date insertTime;

    @Column(name = "IS_TEST")
    private Integer is_test;

    @Column(name = "FCM_RESPONSE")
    private String fcmResponse;

    @Column(name = "PUSH_ERROR_CODE")
    private String pushErrorCode;

    public NotifyHis() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getIs_test() {
        return is_test;
    }

    public void setIs_test(Integer is_test) {
        this.is_test = is_test;
    }

    public String getFcmResponse() {
        return fcmResponse;
    }

    public void setFcmResponse(String fcmResponse) {
        this.fcmResponse = fcmResponse;
    }

    public String getPushErrorCode() {
        return pushErrorCode;
    }

    public void setPushErrorCode(String pushErrorCode) {
        this.pushErrorCode = pushErrorCode;
    }

}
