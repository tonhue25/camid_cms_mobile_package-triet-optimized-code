package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the KHOATD12_LOG_TEST_PK database table.
 * 
 */
@Entity
@Table(name="KHOATD12_LOG_TEST_PK")
@NamedQuery(name="Khoatd12LogTestPk.findAll", query="SELECT k FROM Khoatd12LogTestPk k")
public class Khoatd12LogTestPk implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="LOG_ID")
	private BigDecimal logId;

	@Column(name="LOG_NAME")
	private String logName;

	@Temporal(TemporalType.DATE)
	@Column(name="TIME_START")
	private Date timeStart;

	@Column(name="\"VALUES\"")
	private String values;

	public Khoatd12LogTestPk() {
	}

	public BigDecimal getLogId() {
		return this.logId;
	}

	public void setLogId(BigDecimal logId) {
		this.logId = logId;
	}

	public String getLogName() {
		return this.logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public Date getTimeStart() {
		return this.timeStart;
	}

	public void setTimeStart(Date timeStart) {
		this.timeStart = timeStart;
	}

	public String getValues() {
		return this.values;
	}

	public void setValues(String values) {
		this.values = values;
	}

}