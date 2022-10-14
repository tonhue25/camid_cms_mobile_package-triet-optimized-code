package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PRODUCT_SPEC_INFO database table.
 * 
 */
@Entity
@Table(name="PRODUCT_SPEC_INFO")
@NamedQuery(name="ProductSpecInfo.findAll", query="SELECT p FROM ProductSpecInfo p")
public class ProductSpecInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="CREATE_DATETIME")
	private Object createDatetime;

	@Column(name="DB_TYPE")
	private BigDecimal dbType;

	@Column(name="DEPARTMENT_CODE")
	private String departmentCode;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="ID_NO")
	private String idNo;

	private String isdn;

	@Column(name="LOGIN_NAME")
	private String loginName;

	@Column(name="NATION_CODE")
	private String nationCode;

	@Column(name="OBJECT_CODE")
	private String objectCode;

	@Column(name="ORDER_NUMBER")
	private String orderNumber;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="REGISTER_NAME")
	private String registerName;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SPEC_PROMOTION_CODE")
	private String specPromotionCode;

	@Column(name="START_DATETIME")
	private Object startDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public ProductSpecInfo() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Object getCreateDatetime() {
		return this.createDatetime;
	}

	public void setCreateDatetime(Object createDatetime) {
		this.createDatetime = createDatetime;
	}

	public BigDecimal getDbType() {
		return this.dbType;
	}

	public void setDbType(BigDecimal dbType) {
		this.dbType = dbType;
	}

	public String getDepartmentCode() {
		return this.departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNationCode() {
		return this.nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public String getOrderNumber() {
		return this.orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getRegisterName() {
		return this.registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSpecPromotionCode() {
		return this.specPromotionCode;
	}

	public void setSpecPromotionCode(String specPromotionCode) {
		this.specPromotionCode = specPromotionCode;
	}

	public Object getStartDatetime() {
		return this.startDatetime;
	}

	public void setStartDatetime(Object startDatetime) {
		this.startDatetime = startDatetime;
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