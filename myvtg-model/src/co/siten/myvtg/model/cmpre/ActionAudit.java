package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the ACTION_AUDIT database table.
 * 
 */
@Entity
@Table(name="ACTION_AUDIT")
@NamedQuery(name="ActionAudit.findAll", query="SELECT a FROM co.siten.myvtg.model.cmpre.ActionAudit a")
public class ActionAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_AUDIT_ID", nullable=false, precision=20)
	private BigDecimal actionAuditId;

	@Column(name="ACTION_CODE", length=10)
	private String actionCode;

	@Column(length=800)
	private String description;

	@Column(length=20)
	private String ip;

	@Column(name="ISSUE_DATETIME", nullable=false)
	private Date issueDatetime;

	@Column(name="PK_ID", nullable=false, precision=10)
	private BigDecimal pkId;

	@Column(name="PK_TYPE", length=1)
	private String pkType;

	@Column(name="REASON_ID", precision=10)
	private BigDecimal reasonId;

	@Column(name="SHOP_CODE", length=50)
	private String shopCode;

	@Column(name="USER_NAME", nullable=false, length=50)
	private String userName;

	@Column(precision=1)
	private BigDecimal valid;

	public ActionAudit() {
	}

	public BigDecimal getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(BigDecimal actionAuditId) {
		this.actionAuditId = actionAuditId;
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

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Date issueDatetime) {
		this.issueDatetime = issueDatetime;
	}

	public BigDecimal getPkId() {
		return this.pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getPkType() {
		return this.pkType;
	}

	public void setPkType(String pkType) {
		this.pkType = pkType;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getValid() {
		return this.valid;
	}

	public void setValid(BigDecimal valid) {
		this.valid = valid;
	}

}