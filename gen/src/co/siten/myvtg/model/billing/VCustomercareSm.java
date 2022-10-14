package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the V_CUSTOMERCARE_SMS database table.
 * 
 */
@Entity
@Table(name="V_CUSTOMERCARE_SMS")
@NamedQuery(name="VCustomercareSm.findAll", query="SELECT v FROM VCustomercareSm v")
public class VCustomercareSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE_FROM")
	private Date billCycleFrom;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_DATETIME")
	private Date billDatetime;

	@Column(name="CALLED_NUMBER")
	private String calledNumber;

	@Column(name="CALLING_NUMBER")
	private String callingNumber;

	@Column(name="CELL_ID")
	private String cellId;

	@Column(name="CHARGING_ITEM_ID")
	private BigDecimal chargingItemId;

	@Column(name="DOM_CHARGE")
	private BigDecimal domCharge;

	private BigDecimal duration;

	@Column(name="INT_CHARGE")
	private BigDecimal intCharge;

	@Column(name="ITEM_NAME")
	private String itemName;

	@Column(name="PO_CODE")
	private String poCode;

	@Column(name="SER_CHARGE")
	private BigDecimal serCharge;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="\"TYPE\"")
	private String type;

	private String vplmn;

	public VCustomercareSm() {
	}

	public Date getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(Date billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public Date getBillDatetime() {
		return this.billDatetime;
	}

	public void setBillDatetime(Date billDatetime) {
		this.billDatetime = billDatetime;
	}

	public String getCalledNumber() {
		return this.calledNumber;
	}

	public void setCalledNumber(String calledNumber) {
		this.calledNumber = calledNumber;
	}

	public String getCallingNumber() {
		return this.callingNumber;
	}

	public void setCallingNumber(String callingNumber) {
		this.callingNumber = callingNumber;
	}

	public String getCellId() {
		return this.cellId;
	}

	public void setCellId(String cellId) {
		this.cellId = cellId;
	}

	public BigDecimal getChargingItemId() {
		return this.chargingItemId;
	}

	public void setChargingItemId(BigDecimal chargingItemId) {
		this.chargingItemId = chargingItemId;
	}

	public BigDecimal getDomCharge() {
		return this.domCharge;
	}

	public void setDomCharge(BigDecimal domCharge) {
		this.domCharge = domCharge;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getIntCharge() {
		return this.intCharge;
	}

	public void setIntCharge(BigDecimal intCharge) {
		this.intCharge = intCharge;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPoCode() {
		return this.poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public BigDecimal getSerCharge() {
		return this.serCharge;
	}

	public void setSerCharge(BigDecimal serCharge) {
		this.serCharge = serCharge;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVplmn() {
		return this.vplmn;
	}

	public void setVplmn(String vplmn) {
		this.vplmn = vplmn;
	}

}