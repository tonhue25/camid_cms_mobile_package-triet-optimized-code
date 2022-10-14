package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the LOG_SYN_ACTIVATE_KIT database table.
 * 
 */
@Entity
@Table(name="LOG_SYN_ACTIVATE_KIT")
@NamedQuery(name="LogSynActivateKit.findAll", query="SELECT l FROM LogSynActivateKit l")
public class LogSynActivateKit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="ERROR_DESCRIPTION")
	private String errorDescription;

	@Column(name="LAST_UPDATE_TIME")
	private Object lastUpdateTime;

	@Column(name="PROCESS_COUNT")
	private BigDecimal processCount;

	@Column(name="PROCESS_TIME")
	private Object processTime;

	@Column(name="SALE_TYPE")
	private BigDecimal saleType;

	private String serial;

	private BigDecimal status;

	public LogSynActivateKit() {
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public String getErrorDescription() {
		return this.errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Object getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Object lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public BigDecimal getProcessCount() {
		return this.processCount;
	}

	public void setProcessCount(BigDecimal processCount) {
		this.processCount = processCount;
	}

	public Object getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Object processTime) {
		this.processTime = processTime;
	}

	public BigDecimal getSaleType() {
		return this.saleType;
	}

	public void setSaleType(BigDecimal saleType) {
		this.saleType = saleType;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}