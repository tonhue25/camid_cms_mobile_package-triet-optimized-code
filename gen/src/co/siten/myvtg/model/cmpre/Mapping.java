package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAPPING database table.
 * 
 */
@Entity
@NamedQuery(name="Mapping.findAll", query="SELECT m FROM Mapping m")
public class Mapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="ACTION_NAME")
	private String actionName;

	@Column(name="CHANGE_DATETIME")
	private Object changeDatetime;

	private BigDecimal channel;

	@Column(name="CREATE_DATETIME")
	private Object createDatetime;

	@Column(name="EFFECT_DATE")
	private Object effectDate;

	@Column(name="END_DATE")
	private Object endDate;

	private BigDecimal id;

	private String ip;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="PRODUCT_NAME")
	private String productName;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REASON_NAME")
	private String reasonName;

	@Column(name="SALE_SERVICE_CODE")
	private String saleServiceCode;

	@Column(name="SALE_SERVICE_ID")
	private BigDecimal saleServiceId;

	@Column(name="SALE_SERVICE_NAME")
	private String saleServiceName;

	private BigDecimal status;

	@Column(name="TEL_SERVICE_ID")
	private BigDecimal telServiceId;

	@Column(name="USER_CREATE")
	private String userCreate;

	@Column(name="USER_UPDATE")
	private String userUpdate;

	private String vas;

	@Column(name="VAS_NAME")
	private String vasName;

	public Mapping() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Object getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Object changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public BigDecimal getChannel() {
		return this.channel;
	}

	public void setChannel(BigDecimal channel) {
		this.channel = channel;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Object getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Object effectDate) {
		this.effectDate = effectDate;
	}

	public Object getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Object endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonName() {
		return this.reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getSaleServiceCode() {
		return this.saleServiceCode;
	}

	public void setSaleServiceCode(String saleServiceCode) {
		this.saleServiceCode = saleServiceCode;
	}

	public BigDecimal getSaleServiceId() {
		return this.saleServiceId;
	}

	public void setSaleServiceId(BigDecimal saleServiceId) {
		this.saleServiceId = saleServiceId;
	}

	public String getSaleServiceName() {
		return this.saleServiceName;
	}

	public void setSaleServiceName(String saleServiceName) {
		this.saleServiceName = saleServiceName;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTelServiceId() {
		return this.telServiceId;
	}

	public void setTelServiceId(BigDecimal telServiceId) {
		this.telServiceId = telServiceId;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public String getUserUpdate() {
		return this.userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public String getVas() {
		return this.vas;
	}

	public void setVas(String vas) {
		this.vas = vas;
	}

	public String getVasName() {
		return this.vasName;
	}

	public void setVasName(String vasName) {
		this.vasName = vasName;
	}

}