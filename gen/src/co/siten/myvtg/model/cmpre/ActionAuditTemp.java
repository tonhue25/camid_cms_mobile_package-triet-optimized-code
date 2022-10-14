package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACTION_AUDIT_TEMP database table.
 * 
 */
@Entity
@Table(name="ACTION_AUDIT_TEMP")
@NamedQuery(name="ActionAuditTemp.findAll", query="SELECT a FROM ActionAuditTemp a")
public class ActionAuditTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ACTION_AUDIT_ID")
	private long actionAuditId;

	@Column(name="ACTION_CODE")
	private String actionCode;

	private String description;

	private String ip;

	@Column(name="ISSUE_DATETIME")
	private Object issueDatetime;

	@Column(name="PK_ID")
	private BigDecimal pkId;

	@Column(name="PK_TYPE")
	private String pkType;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="USER_NAME")
	private String userName;

	private BigDecimal valid;

	public ActionAuditTemp() {
	}

	public long getActionAuditId() {
		return this.actionAuditId;
	}

	public void setActionAuditId(long actionAuditId) {
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

	public Object getIssueDatetime() {
		return this.issueDatetime;
	}

	public void setIssueDatetime(Object issueDatetime) {
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