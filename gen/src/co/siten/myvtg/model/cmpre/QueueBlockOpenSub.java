package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the QUEUE_BLOCK_OPEN_SUB database table.
 * 
 */
@Entity
@Table(name="QUEUE_BLOCK_OPEN_SUB")
@NamedQuery(name="QueueBlockOpenSub.findAll", query="SELECT q FROM QueueBlockOpenSub q")
public class QueueBlockOpenSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ACT_STATUS")
	private String actStatus;

	@Column(name="CREATE_DATE")
	private Object createDate;

	private String description;

	private String ip;

	private String isdn;

	@Column(name="NUM_RETRY")
	private BigDecimal numRetry;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="STAFF_CODE")
	private String staffCode;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public QueueBlockOpenSub() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActStatus() {
		return this.actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public BigDecimal getNumRetry() {
		return this.numRetry;
	}

	public void setNumRetry(BigDecimal numRetry) {
		this.numRetry = numRetry;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}