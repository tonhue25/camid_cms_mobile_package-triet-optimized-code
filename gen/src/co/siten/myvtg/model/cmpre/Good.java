package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GOOD database table.
 * 
 */
@Entity
@NamedQuery(name="Good.findAll", query="SELECT g FROM Good g")
public class Good implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHECK_ADD")
	private BigDecimal checkAdd;

	@Column(name="CHECK_AVAIABLE_QUANTITY")
	private String checkAvaiableQuantity;

	@Column(name="CHECK_HP")
	private String checkHp;

	@Column(name="CHECK_QUANTITY")
	private BigDecimal checkQuantity;

	@Column(name="CHECK_SERIAL")
	private BigDecimal checkSerial;

	private BigDecimal credit;

	private BigDecimal dept;

	@Column(name="EXPORT_STOCK")
	private BigDecimal exportStock;

	@Column(name="GOOD_CLASS")
	private BigDecimal goodClass;

	@Column(name="GOOD_CODE")
	private String goodCode;

	@Column(name="GOOD_DESCRIPTION")
	private String goodDescription;

	@Column(name="GOOD_DISTRIBUTE_TYPE")
	private String goodDistributeType;

	@Column(name="GOOD_ID")
	private BigDecimal goodId;

	@Column(name="GOOD_NAME")
	private String goodName;

	@Column(name="GOOD_SALE_TYPE")
	private String goodSaleType;

	@Column(name="GOOD_STATUS")
	private String goodStatus;

	@Column(name="GOOD_TYPE")
	private String goodType;

	@Column(name="GOOD_UNIT")
	private String goodUnit;

	private String note;

	@Column(name="PARENT_GOOD_ID")
	private BigDecimal parentGoodId;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="REQUIRE_PREFIX")
	private BigDecimal requirePrefix;

	@Column(name="SERIAL_LENGTH")
	private String serialLength;

	@Column(name="SERIAL_PATTERN")
	private String serialPattern;

	@Column(name="\"SERVICE\"")
	private String service;

	@Column(name="SRC_GOOD_ID")
	private BigDecimal srcGoodId;

	@Column(name="SUN_GOOD_CODE")
	private String sunGoodCode;

	private String t0;

	private String t1;

	private String t2;

	private String t3;

	private String t4;

	private String t5;

	private String t6;

	private String t7;

	private BigDecimal vat;

	public Good() {
	}

	public BigDecimal getCheckAdd() {
		return this.checkAdd;
	}

	public void setCheckAdd(BigDecimal checkAdd) {
		this.checkAdd = checkAdd;
	}

	public String getCheckAvaiableQuantity() {
		return this.checkAvaiableQuantity;
	}

	public void setCheckAvaiableQuantity(String checkAvaiableQuantity) {
		this.checkAvaiableQuantity = checkAvaiableQuantity;
	}

	public String getCheckHp() {
		return this.checkHp;
	}

	public void setCheckHp(String checkHp) {
		this.checkHp = checkHp;
	}

	public BigDecimal getCheckQuantity() {
		return this.checkQuantity;
	}

	public void setCheckQuantity(BigDecimal checkQuantity) {
		this.checkQuantity = checkQuantity;
	}

	public BigDecimal getCheckSerial() {
		return this.checkSerial;
	}

	public void setCheckSerial(BigDecimal checkSerial) {
		this.checkSerial = checkSerial;
	}

	public BigDecimal getCredit() {
		return this.credit;
	}

	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	public BigDecimal getDept() {
		return this.dept;
	}

	public void setDept(BigDecimal dept) {
		this.dept = dept;
	}

	public BigDecimal getExportStock() {
		return this.exportStock;
	}

	public void setExportStock(BigDecimal exportStock) {
		this.exportStock = exportStock;
	}

	public BigDecimal getGoodClass() {
		return this.goodClass;
	}

	public void setGoodClass(BigDecimal goodClass) {
		this.goodClass = goodClass;
	}

	public String getGoodCode() {
		return this.goodCode;
	}

	public void setGoodCode(String goodCode) {
		this.goodCode = goodCode;
	}

	public String getGoodDescription() {
		return this.goodDescription;
	}

	public void setGoodDescription(String goodDescription) {
		this.goodDescription = goodDescription;
	}

	public String getGoodDistributeType() {
		return this.goodDistributeType;
	}

	public void setGoodDistributeType(String goodDistributeType) {
		this.goodDistributeType = goodDistributeType;
	}

	public BigDecimal getGoodId() {
		return this.goodId;
	}

	public void setGoodId(BigDecimal goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return this.goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodSaleType() {
		return this.goodSaleType;
	}

	public void setGoodSaleType(String goodSaleType) {
		this.goodSaleType = goodSaleType;
	}

	public String getGoodStatus() {
		return this.goodStatus;
	}

	public void setGoodStatus(String goodStatus) {
		this.goodStatus = goodStatus;
	}

	public String getGoodType() {
		return this.goodType;
	}

	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}

	public String getGoodUnit() {
		return this.goodUnit;
	}

	public void setGoodUnit(String goodUnit) {
		this.goodUnit = goodUnit;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getParentGoodId() {
		return this.parentGoodId;
	}

	public void setParentGoodId(BigDecimal parentGoodId) {
		this.parentGoodId = parentGoodId;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public BigDecimal getRequirePrefix() {
		return this.requirePrefix;
	}

	public void setRequirePrefix(BigDecimal requirePrefix) {
		this.requirePrefix = requirePrefix;
	}

	public String getSerialLength() {
		return this.serialLength;
	}

	public void setSerialLength(String serialLength) {
		this.serialLength = serialLength;
	}

	public String getSerialPattern() {
		return this.serialPattern;
	}

	public void setSerialPattern(String serialPattern) {
		this.serialPattern = serialPattern;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public BigDecimal getSrcGoodId() {
		return this.srcGoodId;
	}

	public void setSrcGoodId(BigDecimal srcGoodId) {
		this.srcGoodId = srcGoodId;
	}

	public String getSunGoodCode() {
		return this.sunGoodCode;
	}

	public void setSunGoodCode(String sunGoodCode) {
		this.sunGoodCode = sunGoodCode;
	}

	public String getT0() {
		return this.t0;
	}

	public void setT0(String t0) {
		this.t0 = t0;
	}

	public String getT1() {
		return this.t1;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public String getT2() {
		return this.t2;
	}

	public void setT2(String t2) {
		this.t2 = t2;
	}

	public String getT3() {
		return this.t3;
	}

	public void setT3(String t3) {
		this.t3 = t3;
	}

	public String getT4() {
		return this.t4;
	}

	public void setT4(String t4) {
		this.t4 = t4;
	}

	public String getT5() {
		return this.t5;
	}

	public void setT5(String t5) {
		this.t5 = t5;
	}

	public String getT6() {
		return this.t6;
	}

	public void setT6(String t6) {
		this.t6 = t6;
	}

	public String getT7() {
		return this.t7;
	}

	public void setT7(String t7) {
		this.t7 = t7;
	}

	public BigDecimal getVat() {
		return this.vat;
	}

	public void setVat(BigDecimal vat) {
		this.vat = vat;
	}

}