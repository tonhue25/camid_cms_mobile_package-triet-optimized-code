package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SCRIPT_FUNCTION database table.
 * 
 */
@Entity
@Table(name="SCRIPT_FUNCTION")
@NamedQuery(name="ScriptFunction.findAll", query="SELECT s FROM ScriptFunction s")
public class ScriptFunction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="FUNCTION_RUN_ID")
	private BigDecimal functionRunId;

	@Column(name="PARENT_FUNCTION_ID")
	private BigDecimal parentFunctionId;

	@Column(name="SCRIPT_FUNCTION_ID")
	private BigDecimal scriptFunctionId;

	@Column(name="SCRIPT_GROUP_ID")
	private BigDecimal scriptGroupId;

	@Column(name="\"SORT\"")
	private BigDecimal sort;

	public ScriptFunction() {
	}

	public BigDecimal getFunctionRunId() {
		return this.functionRunId;
	}

	public void setFunctionRunId(BigDecimal functionRunId) {
		this.functionRunId = functionRunId;
	}

	public BigDecimal getParentFunctionId() {
		return this.parentFunctionId;
	}

	public void setParentFunctionId(BigDecimal parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public BigDecimal getScriptFunctionId() {
		return this.scriptFunctionId;
	}

	public void setScriptFunctionId(BigDecimal scriptFunctionId) {
		this.scriptFunctionId = scriptFunctionId;
	}

	public BigDecimal getScriptGroupId() {
		return this.scriptGroupId;
	}

	public void setScriptGroupId(BigDecimal scriptGroupId) {
		this.scriptGroupId = scriptGroupId;
	}

	public BigDecimal getSort() {
		return this.sort;
	}

	public void setSort(BigDecimal sort) {
		this.sort = sort;
	}

}