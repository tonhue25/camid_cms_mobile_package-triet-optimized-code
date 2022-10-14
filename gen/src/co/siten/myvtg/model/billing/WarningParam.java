package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the WARNING_PARAM database table.
 * 
 */
@Entity
@Table(name="WARNING_PARAM")
@NamedQuery(name="WarningParam.findAll", query="SELECT w FROM WarningParam w")
public class WarningParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="KEY_QUERY")
	private String keyQuery;

	@Column(name="OBJECT_NAME")
	private String objectName;

	@Column(name="RECEIVED_GROUP_ID")
	private BigDecimal receivedGroupId;

	private String status;

	@Column(name="WARING_PARAM_ID")
	private BigDecimal waringParamId;

	@Column(name="WARNING_TYPE")
	private BigDecimal warningType;

	public WarningParam() {
	}

	public String getKeyQuery() {
		return this.keyQuery;
	}

	public void setKeyQuery(String keyQuery) {
		this.keyQuery = keyQuery;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public BigDecimal getReceivedGroupId() {
		return this.receivedGroupId;
	}

	public void setReceivedGroupId(BigDecimal receivedGroupId) {
		this.receivedGroupId = receivedGroupId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getWaringParamId() {
		return this.waringParamId;
	}

	public void setWaringParamId(BigDecimal waringParamId) {
		this.waringParamId = waringParamId;
	}

	public BigDecimal getWarningType() {
		return this.warningType;
	}

	public void setWarningType(BigDecimal warningType) {
		this.warningType = warningType;
	}

}