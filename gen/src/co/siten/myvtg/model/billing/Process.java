package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PROCESS database table.
 * 
 */
@Entity
@NamedQuery(name="Process.findAll", query="SELECT p FROM Process p")
public class Process implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal active;

	private String description;

	@Column(name="EXPORT_TABLE_ID")
	private BigDecimal exportTableId;

	@Column(name="LOCAL_STORE")
	private String localStore;

	@Column(name="LOCAL_TEMP")
	private String localTemp;

	@Column(name="MIN_ROWS")
	private BigDecimal minRows;

	@Column(name="NUM_THREAD")
	private BigDecimal numThread;

	@Column(name="NUMBER_ROWS")
	private BigDecimal numberRows;

	@Column(name="PROCESS_ID")
	private BigDecimal processId;

	@Column(name="PROCESS_NAME")
	private String processName;

	@Column(name="SERVICE_ID")
	private BigDecimal serviceId;

	@Column(name="WAIT_TIME")
	private BigDecimal waitTime;

	public Process() {
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getExportTableId() {
		return this.exportTableId;
	}

	public void setExportTableId(BigDecimal exportTableId) {
		this.exportTableId = exportTableId;
	}

	public String getLocalStore() {
		return this.localStore;
	}

	public void setLocalStore(String localStore) {
		this.localStore = localStore;
	}

	public String getLocalTemp() {
		return this.localTemp;
	}

	public void setLocalTemp(String localTemp) {
		this.localTemp = localTemp;
	}

	public BigDecimal getMinRows() {
		return this.minRows;
	}

	public void setMinRows(BigDecimal minRows) {
		this.minRows = minRows;
	}

	public BigDecimal getNumThread() {
		return this.numThread;
	}

	public void setNumThread(BigDecimal numThread) {
		this.numThread = numThread;
	}

	public BigDecimal getNumberRows() {
		return this.numberRows;
	}

	public void setNumberRows(BigDecimal numberRows) {
		this.numberRows = numberRows;
	}

	public BigDecimal getProcessId() {
		return this.processId;
	}

	public void setProcessId(BigDecimal processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public BigDecimal getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(BigDecimal serviceId) {
		this.serviceId = serviceId;
	}

	public BigDecimal getWaitTime() {
		return this.waitTime;
	}

	public void setWaitTime(BigDecimal waitTime) {
		this.waitTime = waitTime;
	}

}