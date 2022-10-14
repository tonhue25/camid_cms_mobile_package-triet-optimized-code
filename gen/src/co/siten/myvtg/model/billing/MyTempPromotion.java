package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MY_TEMP_PROMOTION database table.
 * 
 */
@Entity
@Table(name="MY_TEMP_PROMOTION")
@NamedQuery(name="MyTempPromotion.findAll", query="SELECT m FROM MyTempPromotion m")
public class MyTempPromotion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	@Column(name="ATTCHED_FILE")
	private byte[] attchedFile;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="FILE_NAME")
	private String fileName;

	@Column(name="\"LOCAL\"")
	private BigDecimal local;

	private String name;

	@Column(name="PROCEDURE_ID")
	private BigDecimal procedureId;

	@Column(name="PRODUCT_ID")
	private BigDecimal productId;

	@Column(name="PROM_PROGRAM_CODE")
	private String promProgramCode;

	@Column(name="PROM_TYPE")
	private String promType;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private String type;

	public MyTempPromotion() {
	}

	public byte[] getAttchedFile() {
		return this.attchedFile;
	}

	public void setAttchedFile(byte[] attchedFile) {
		this.attchedFile = attchedFile;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getLocal() {
		return this.local;
	}

	public void setLocal(BigDecimal local) {
		this.local = local;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getProcedureId() {
		return this.procedureId;
	}

	public void setProcedureId(BigDecimal procedureId) {
		this.procedureId = procedureId;
	}

	public BigDecimal getProductId() {
		return this.productId;
	}

	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}

	public String getPromProgramCode() {
		return this.promProgramCode;
	}

	public void setPromProgramCode(String promProgramCode) {
		this.promProgramCode = promProgramCode;
	}

	public String getPromType() {
		return this.promType;
	}

	public void setPromType(String promType) {
		this.promType = promType;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}