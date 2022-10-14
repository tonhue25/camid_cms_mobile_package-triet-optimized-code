package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the "ACTION" database table.
 * 
 */
@Entity
@Table(name = "\"ACTION\"")
@NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a")
public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ActionPK id;
	
	@Transient
	public ActionPK newId;
	

	private Integer channel;

	@Column(name = "CREATED_BY")
	private String createdBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	@Column(name = "LAST_UPDATED_BY")
	private String lastUpdatedBy;
	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_UPDATED_TIME")
	private Date lastUpdatedTime;

	private String name;

	private BigDecimal params;

	private Integer status;

	private String syntax;

	@Column(name = "WS_ID")
	private String wsId;
	
	@Column(name = "MO_ACTION_TYPE")
	private Integer moActionType;
	
	

	public Integer getMoActionType() {
		return moActionType;
	}

	public void setMoActionType(Integer moActionType) {
		this.moActionType = moActionType;
	}

	public Action() {
	}

	public ActionPK getId() {
		return this.id;
	}

	public void setId(ActionPK id) {
		this.id = id;
	}

	public Integer getChannel() {
		return channel;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getParams() {
		return params;
	}

	public Integer getStatus() {
		return status;
	}

	public String getSyntax() {
		return syntax;
	}

	public String getWsId() {
		return wsId;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParams(BigDecimal params) {
		this.params = params;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public void setWsId(String wsId) {
		this.wsId = wsId;
	}

}