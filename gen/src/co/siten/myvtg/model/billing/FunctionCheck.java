package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the FUNCTION_CHECK database table.
 * 
 */
@Entity
@Table(name="FUNCTION_CHECK")
@NamedQuery(name="FunctionCheck.findAll", query="SELECT f FROM FunctionCheck f")
public class FunctionCheck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CHECK")
	private Date dateCheck;

	@Lob
	@Column(name="FUNCTION_CHECK_BODY")
	private String functionCheckBody;

	@Column(name="FUNCTION_CHECK_ID")
	private BigDecimal functionCheckId;

	@Column(name="FUNCTION_CHECK_NAME")
	private String functionCheckName;

	@Column(name="FUNCTION_RUN_ID")
	private BigDecimal functionRunId;

	@Column(name="STATUS_CHECK")
	private BigDecimal statusCheck;

	@Column(name="USER_ID")
	private String userId;

	public FunctionCheck() {
	}

	public Date getDateCheck() {
		return this.dateCheck;
	}

	public void setDateCheck(Date dateCheck) {
		this.dateCheck = dateCheck;
	}

	public String getFunctionCheckBody() {
		return this.functionCheckBody;
	}

	public void setFunctionCheckBody(String functionCheckBody) {
		this.functionCheckBody = functionCheckBody;
	}

	public BigDecimal getFunctionCheckId() {
		return this.functionCheckId;
	}

	public void setFunctionCheckId(BigDecimal functionCheckId) {
		this.functionCheckId = functionCheckId;
	}

	public String getFunctionCheckName() {
		return this.functionCheckName;
	}

	public void setFunctionCheckName(String functionCheckName) {
		this.functionCheckName = functionCheckName;
	}

	public BigDecimal getFunctionRunId() {
		return this.functionRunId;
	}

	public void setFunctionRunId(BigDecimal functionRunId) {
		this.functionRunId = functionRunId;
	}

	public BigDecimal getStatusCheck() {
		return this.statusCheck;
	}

	public void setStatusCheck(BigDecimal statusCheck) {
		this.statusCheck = statusCheck;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}