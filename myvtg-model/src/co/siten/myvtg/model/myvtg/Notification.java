package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "NOTIFICATION")
@NamedQuery(name = "Notification.findAll", query = "SELECT a FROM Notification a")

public class Notification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(sequenceName = "NOTIFICATION_SEQ", allocationSize = 1, name = "SEQ")
	@Column(name = "ID")
	private long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "MESSAGE")
	private String message;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TIME")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "STATUS")
	private Integer status = 0;

	@Column(name = "TEST_STATUS")
	private Integer test_status = 0;

	@Column(name = "LIST_TEST_PHONE")
	private String listTestPhone;

	@Column(name = "LAST_DEVICE_TOKEN_ID")
	private String lastDeviceTokenId;

	@Column(name = "NOTIFICATION_TYPE")
	private String notificationType;

	@Column(name = "SERVICE_CODE")
	private String serviceCode;

	@Column(name = "ICON")
	private String icon;

	@Column(name = "SERVICE_NAME")
	private String serviceName;

	@Column(name = "IS_SAVE")
	private Integer isSave;

	@Column(name = "IS_CONTENT_FROM_CAMPAIGN")
	private Integer contentFromCampaign;

	@Column(name = "code")
	private String code;

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name = "NOTIFY_PROGRAM_TYPE")
	private Integer notificationProgramType;

	@Column(name = "IMAGE")
	private String image;

	public Notification() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTest_status() {
		return test_status;
	}

	public void setTest_status(Integer test_status) {
		this.test_status = test_status;
	}

	public String getListTestPhone() {
		return listTestPhone;
	}

	public void setListTestPhone(String listTestPhone) {
		this.listTestPhone = listTestPhone;
	}

	public String getLastDeviceTokenId() {
		return lastDeviceTokenId;
	}

	public void setLastDeviceTokenId(String lastDeviceTokenId) {
		this.lastDeviceTokenId = lastDeviceTokenId;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Integer getIsSave() {
		return isSave;
	}

	public void setIsSave(Integer isSave) {
		this.isSave = isSave;
	}

	public Integer getContentFromCampaign() {
		return contentFromCampaign;
	}

	public void setContentFromCampaign(Integer contentFromCampaign) {
		this.contentFromCampaign = contentFromCampaign;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Integer getNotificationProgramType() {
		return notificationProgramType;
	}

	public void setNotificationProgramType(Integer notificationProgramType) {
		this.notificationProgramType = notificationProgramType;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
