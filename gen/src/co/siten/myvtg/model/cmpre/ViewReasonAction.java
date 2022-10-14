package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the VIEW_REASON_ACTION database table.
 * 
 */
@Entity
@Table(name="VIEW_REASON_ACTION")
@NamedQuery(name="ViewReasonAction.findAll", query="SELECT v FROM ViewReasonAction v")
public class ViewReasonAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"ACTION\"")
	private String action;

	@Column(name="ACTION_CODE")
	private String actionCode;

	private String description;

	@Column(name="REASON_CODE")
	private String reasonCode;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="REASON_NAME")
	private String reasonName;

	@Column(name="REG_SPECIAL")
	private String regSpecial;

	@Column(name="REG_SPECIAL_TYPE")
	private String regSpecialType;

	@Column(name="REQ_PROFILE")
	private String reqProfile;

	private BigDecimal status;

	public ViewReasonAction() {
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonName() {
		return this.reasonName;
	}

	public void setReasonName(String reasonName) {
		this.reasonName = reasonName;
	}

	public String getRegSpecial() {
		return this.regSpecial;
	}

	public void setRegSpecial(String regSpecial) {
		this.regSpecial = regSpecial;
	}

	public String getRegSpecialType() {
		return this.regSpecialType;
	}

	public void setRegSpecialType(String regSpecialType) {
		this.regSpecialType = regSpecialType;
	}

	public String getReqProfile() {
		return this.reqProfile;
	}

	public void setReqProfile(String reqProfile) {
		this.reqProfile = reqProfile;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}