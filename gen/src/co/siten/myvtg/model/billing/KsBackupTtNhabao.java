package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the KS_BACKUP_TT_NHABAO database table.
 * 
 */
@Entity
@Table(name="KS_BACKUP_TT_NHABAO")
@NamedQuery(name="KsBackupTtNhabao.findAll", query="SELECT k FROM KsBackupTtNhabao k")
public class KsBackupTtNhabao implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	@Column(name="BC_ID")
	private BigDecimal bcId;

	@Column(name="BC_NAME")
	private String bcName;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	private String code;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String division;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	private String isdn;

	@Column(name="NEWSMAN_CODE")
	private String newsmanCode;

	@Column(name="REGISTER_USER")
	private String registerUser;

	@Column(name="SERVICE_TYPE")
	private BigDecimal serviceType;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	@Column(name="USER_CODE")
	private String userCode;

	public KsBackupTtNhabao() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getBcId() {
		return this.bcId;
	}

	public void setBcId(BigDecimal bcId) {
		this.bcId = bcId;
	}

	public String getBcName() {
		return this.bcName;
	}

	public void setBcName(String bcName) {
		this.bcName = bcName;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDivision() {
		return this.division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getNewsmanCode() {
		return this.newsmanCode;
	}

	public void setNewsmanCode(String newsmanCode) {
		this.newsmanCode = newsmanCode;
	}

	public String getRegisterUser() {
		return this.registerUser;
	}

	public void setRegisterUser(String registerUser) {
		this.registerUser = registerUser;
	}

	public BigDecimal getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(BigDecimal serviceType) {
		this.serviceType = serviceType;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}