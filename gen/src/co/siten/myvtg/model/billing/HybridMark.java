package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the HYBRID_MARK database table.
 * 
 */
@Entity
@Table(name="HYBRID_MARK")
@NamedQuery(name="HybridMark.findAll", query="SELECT h FROM HybridMark h")
public class HybridMark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="GROUP_ID")
	private BigDecimal groupId;

	private String isdn;

	@Temporal(TemporalType.DATE)
	@Column(name="ISSUE_DATE")
	private Date issueDate;

	public HybridMark() {
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

}