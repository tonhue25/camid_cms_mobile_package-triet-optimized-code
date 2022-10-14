package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_CONTRACT_LIST_1800_PSTN database table.
 * 
 */
@Entity
@Table(name="BI_CONTRACT_LIST_1800_PSTN")
@NamedQuery(name="BiContractList1800Pstn.findAll", query="SELECT b FROM BiContractList1800Pstn b")
public class BiContractList1800Pstn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="CONTRACT_DATE")
	private Date contractDate;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="CONTRACT_TYPE")
	private String contractType;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_CONNECT")
	private Date firstConnect;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="\"MONTH\"")
	private Date month;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public BiContractList1800Pstn() {
	}

	public Date getContractDate() {
		return this.contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractType() {
		return this.contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getFirstConnect() {
		return this.firstConnect;
	}

	public void setFirstConnect(Date firstConnect) {
		this.firstConnect = firstConnect;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getMonth() {
		return this.month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}