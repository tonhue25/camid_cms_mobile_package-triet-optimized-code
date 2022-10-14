package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the FUNCTION_RUN database table.
 * 
 */
@Entity
@Table(name="FUNCTION_RUN")
@NamedQuery(name="FunctionRun.findAll", query="SELECT f FROM FunctionRun f")
public class FunctionRun implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	@Column(name="\"BODY\"")
	private String body;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_CHECK")
	private Date dateCheck;

	@Column(name="FUNCTION_RUN_ID")
	private BigDecimal functionRunId;

	private String name;

	@Column(name="PACKAGE_NAME")
	private String packageName;

	private BigDecimal status;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	public FunctionRun() {
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getDateCheck() {
		return this.dateCheck;
	}

	public void setDateCheck(Date dateCheck) {
		this.dateCheck = dateCheck;
	}

	public BigDecimal getFunctionRunId() {
		return this.functionRunId;
	}

	public void setFunctionRunId(BigDecimal functionRunId) {
		this.functionRunId = functionRunId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

}