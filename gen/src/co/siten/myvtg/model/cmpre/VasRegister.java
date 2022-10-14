package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the VAS_REGISTER database table.
 * 
 */
@Entity
@Table(name="VAS_REGISTER")
@NamedQuery(name="VasRegister.findAll", query="SELECT v FROM VasRegister v")
public class VasRegister implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_ID")
	private BigDecimal actionId;

	private String channel;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DATE_TIME")
	private Object dateTime;

	private String description;

	@Column(name="HOME_NUMER")
	private String homeNumer;

	private String ip;

	private String isdn;

	@Column(name="\"MONEY\"")
	private String money;

	@Column(name="NEW_OFFER_ID")
	private String newOfferId;

	@Column(name="NEW_PRODUCT_CODE")
	private String newProductCode;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="USER_ACT")
	private String userAct;

	@Column(name="VAS_CODE")
	private String vasCode;

	public VasRegister() {
	}

	public BigDecimal getActionId() {
		return this.actionId;
	}

	public void setActionId(BigDecimal actionId) {
		this.actionId = actionId;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Object getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Object dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHomeNumer() {
		return this.homeNumer;
	}

	public void setHomeNumer(String homeNumer) {
		this.homeNumer = homeNumer;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getMoney() {
		return this.money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getNewOfferId() {
		return this.newOfferId;
	}

	public void setNewOfferId(String newOfferId) {
		this.newOfferId = newOfferId;
	}

	public String getNewProductCode() {
		return this.newProductCode;
	}

	public void setNewProductCode(String newProductCode) {
		this.newProductCode = newProductCode;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getUserAct() {
		return this.userAct;
	}

	public void setUserAct(String userAct) {
		this.userAct = userAct;
	}

	public String getVasCode() {
		return this.vasCode;
	}

	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}

}