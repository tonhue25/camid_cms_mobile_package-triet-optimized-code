package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SUB_COMMITMENT_PSTN database table.
 * 
 */
@Entity
@Table(name="SUB_COMMITMENT_PSTN")
@NamedQuery(name="SubCommitmentPstn.findAll", query="SELECT s FROM SubCommitmentPstn s")
public class SubCommitmentPstn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SUB_COMMITMENT_PSTN_ID")
	private long subCommitmentPstnId;

	@Column(name="COMMITMENT_DETAIL_ID")
	private BigDecimal commitmentDetailId;

	private BigDecimal deporsit;

	@Column(name="MIN_USE")
	private BigDecimal minUse;

	private String notes;

	@Column(name="NUM_MONTH")
	private BigDecimal numMonth;

	@Column(name="NUM_MONTH_CONTRACT")
	private BigDecimal numMonthContract;

	@Column(name="NUM_MONTH_LOWER_CONTRACT")
	private BigDecimal numMonthLowerContract;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private BigDecimal status;

	@Column(name="STATUS_ADJ")
	private BigDecimal statusAdj;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="\"TYPE\"")
	private String type;

	public SubCommitmentPstn() {
	}

	public long getSubCommitmentPstnId() {
		return this.subCommitmentPstnId;
	}

	public void setSubCommitmentPstnId(long subCommitmentPstnId) {
		this.subCommitmentPstnId = subCommitmentPstnId;
	}

	public BigDecimal getCommitmentDetailId() {
		return this.commitmentDetailId;
	}

	public void setCommitmentDetailId(BigDecimal commitmentDetailId) {
		this.commitmentDetailId = commitmentDetailId;
	}

	public BigDecimal getDeporsit() {
		return this.deporsit;
	}

	public void setDeporsit(BigDecimal deporsit) {
		this.deporsit = deporsit;
	}

	public BigDecimal getMinUse() {
		return this.minUse;
	}

	public void setMinUse(BigDecimal minUse) {
		this.minUse = minUse;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getNumMonth() {
		return this.numMonth;
	}

	public void setNumMonth(BigDecimal numMonth) {
		this.numMonth = numMonth;
	}

	public BigDecimal getNumMonthContract() {
		return this.numMonthContract;
	}

	public void setNumMonthContract(BigDecimal numMonthContract) {
		this.numMonthContract = numMonthContract;
	}

	public BigDecimal getNumMonthLowerContract() {
		return this.numMonthLowerContract;
	}

	public void setNumMonthLowerContract(BigDecimal numMonthLowerContract) {
		this.numMonthLowerContract = numMonthLowerContract;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getStatusAdj() {
		return this.statusAdj;
	}

	public void setStatusAdj(BigDecimal statusAdj) {
		this.statusAdj = statusAdj;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}