package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CDR database table.
 * 
 */
@Entity
@NamedQuery(name="Cdr.findAll", query="SELECT c FROM Cdr c")
public class Cdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_ID")
	private String actionId;

	private String backup1;

	private String backup2;

	private String backup3;

	@Column(name="CDR_ID")
	private BigDecimal cdrId;

	private String channel;

	@Column(name="CONTRACT_ID")
	private String contractId;

	private String description;

	@Column(name="FILE_TYPE_ID")
	private BigDecimal fileTypeId;

	private String homenumber;

	private String ip;

	@Column(name="\"MONEY\"")
	private String money;

	private String msisdn;

	private String newofferid;

	private String newproductcode;

	@Temporal(TemporalType.DATE)
	@Column(name="REQ_DATE")
	private Date reqDate;

	private String sender;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private String subId;

	@Column(name="VAS_CODE")
	private String vasCode;

	public Cdr() {
	}

	public String getActionId() {
		return this.actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}

	public String getBackup1() {
		return this.backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return this.backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return this.backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public BigDecimal getCdrId() {
		return this.cdrId;
	}

	public void setCdrId(BigDecimal cdrId) {
		this.cdrId = cdrId;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getFileTypeId() {
		return this.fileTypeId;
	}

	public void setFileTypeId(BigDecimal fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	public String getHomenumber() {
		return this.homenumber;
	}

	public void setHomenumber(String homenumber) {
		this.homenumber = homenumber;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNewofferid() {
		return this.newofferid;
	}

	public void setNewofferid(String newofferid) {
		this.newofferid = newofferid;
	}

	public String getNewproductcode() {
		return this.newproductcode;
	}

	public void setNewproductcode(String newproductcode) {
		this.newproductcode = newproductcode;
	}

	public Date getReqDate() {
		return this.reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getVasCode() {
		return this.vasCode;
	}

	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}

}