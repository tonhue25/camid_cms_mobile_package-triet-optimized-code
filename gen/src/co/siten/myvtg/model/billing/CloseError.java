package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CLOSE_ERROR database table.
 * 
 */
@Entity
@Table(name="CLOSE_ERROR")
@NamedQuery(name="CloseError.findAll", query="SELECT c FROM CloseError c")
public class CloseError implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CLOSE_ERROR_ID")
	private BigDecimal closeErrorId;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_TIME")
	private Date dateTime;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	private String machine;

	private String osuser;

	@Column(name="PROCEDURE_NAME")
	private String procedureName;

	@Column(name="\"PROGRAM\"")
	private String program;

	private BigDecimal reason;

	@Column(name="SESSION_ID")
	private String sessionId;

	private String terminal;

	private String username;

	public CloseError() {
	}

	public BigDecimal getCloseErrorId() {
		return this.closeErrorId;
	}

	public void setCloseErrorId(BigDecimal closeErrorId) {
		this.closeErrorId = closeErrorId;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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

	public String getMachine() {
		return this.machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getOsuser() {
		return this.osuser;
	}

	public void setOsuser(String osuser) {
		this.osuser = osuser;
	}

	public String getProcedureName() {
		return this.procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public String getProgram() {
		return this.program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public BigDecimal getReason() {
		return this.reason;
	}

	public void setReason(BigDecimal reason) {
		this.reason = reason;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getTerminal() {
		return this.terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}