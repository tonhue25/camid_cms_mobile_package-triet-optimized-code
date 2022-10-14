package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REG_SUB_INFO_LOG database table.
 * 
 */
@Entity
@Table(name="REG_SUB_INFO_LOG")
@NamedQuery(name="RegSubInfoLog.findAll", query="SELECT r FROM RegSubInfoLog r")
public class RegSubInfoLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="FAIL_RECORDS")
	private BigDecimal failRecords;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="REG_TYPE")
	private String regType;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SUCCESS_RECORDS")
	private BigDecimal successRecords;

	@Column(name="TOTAL_RECORDS")
	private BigDecimal totalRecords;

	@Column(name="UPLOAD_DATE")
	private Object uploadDate;

	@Column(name="USER_NAME")
	private String userName;

	public RegSubInfoLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getFailRecords() {
		return this.failRecords;
	}

	public void setFailRecords(BigDecimal failRecords) {
		this.failRecords = failRecords;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getSuccessRecords() {
		return this.successRecords;
	}

	public void setSuccessRecords(BigDecimal successRecords) {
		this.successRecords = successRecords;
	}

	public BigDecimal getTotalRecords() {
		return this.totalRecords;
	}

	public void setTotalRecords(BigDecimal totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Object getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(Object uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}