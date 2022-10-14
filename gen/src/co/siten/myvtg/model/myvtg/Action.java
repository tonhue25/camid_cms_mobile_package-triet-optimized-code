package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the "ACTION" database table.
 * 
 */
@Entity
@Table(name="\"ACTION\"")
@NamedQuery(name="Action.findAll", query="SELECT a FROM Action a")
public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActionPK id;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_TIME")
	private Timestamp createdTime;

	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;

	@Column(name="LAST_UPDATED_TIME")
	private Timestamp lastUpdatedTime;

	private String name;

	@Column(name="NUM_PARAMS")
	private BigDecimal numParams;

	@Column(name="SHORT_CODE")
	private BigDecimal shortCode;

	private BigDecimal status;

	private String syntax;

	@Column(name="WS_ID")
	private String wsId;

	public Action() {
	}

	public ActionPK getId() {
		return this.id;
	}

	public void setId(ActionPK id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedTime() {
		return this.lastUpdatedTime;
	}

	public void setLastUpdatedTime(Timestamp lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getNumParams() {
		return this.numParams;
	}

	public void setNumParams(BigDecimal numParams) {
		this.numParams = numParams;
	}

	public BigDecimal getShortCode() {
		return this.shortCode;
	}

	public void setShortCode(BigDecimal shortCode) {
		this.shortCode = shortCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public String getWsId() {
		return this.wsId;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

}