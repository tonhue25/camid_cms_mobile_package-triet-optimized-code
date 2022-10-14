package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SYNTHETIC_PROCESS_PARAM database table.
 * 
 */
@Entity
@Table(name="SYNTHETIC_PROCESS_PARAM")
@NamedQuery(name="SyntheticProcessParam.findAll", query="SELECT s FROM SyntheticProcessParam s")
public class SyntheticProcessParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="END_TIME")
	private Date endTime;

	@Column(name="IS_DELETE")
	private BigDecimal isDelete;

	@Column(name="NUMBER_COMMITED_ROW")
	private BigDecimal numberCommitedRow;

	@Column(name="NUMBER_ROW_COMMIT")
	private BigDecimal numberRowCommit;

	@Column(name="PROCESS_MANAGER_CODE")
	private String processManagerCode;

	@Temporal(TemporalType.DATE)
	@Column(name="START_TIME")
	private Date startTime;

	@Column(name="SYN_ID")
	private BigDecimal synId;

	@Column(name="TABLE_NAME")
	private String tableName;

	public SyntheticProcessParam() {
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(BigDecimal isDelete) {
		this.isDelete = isDelete;
	}

	public BigDecimal getNumberCommitedRow() {
		return this.numberCommitedRow;
	}

	public void setNumberCommitedRow(BigDecimal numberCommitedRow) {
		this.numberCommitedRow = numberCommitedRow;
	}

	public BigDecimal getNumberRowCommit() {
		return this.numberRowCommit;
	}

	public void setNumberRowCommit(BigDecimal numberRowCommit) {
		this.numberRowCommit = numberRowCommit;
	}

	public String getProcessManagerCode() {
		return this.processManagerCode;
	}

	public void setProcessManagerCode(String processManagerCode) {
		this.processManagerCode = processManagerCode;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public BigDecimal getSynId() {
		return this.synId;
	}

	public void setSynId(BigDecimal synId) {
		this.synId = synId;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}