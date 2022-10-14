package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "ACTION" database table.
 * 
 */
@Entity
@Table(name="\"ACTION\"")
@NamedQuery(name="Action.findAll", query="SELECT a FROM Action a")
public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_CODE")
	private String actionCode;

	@Column(name="ACTION_ID")
	private BigDecimal actionId;

	@Column(name="ACTION_MAPING")
	private String actionMaping;

	@Column(name="ACTION_NAME")
	private String actionName;

	@Column(name="ACTION_TYPE")
	private String actionType;

	@Column(name="CLASS_NAME")
	private String className;

	@Column(name="LAST_UPDATE_TIME")
	private Object lastUpdateTime;

	@Column(name="LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name="METHOD_NAME")
	private String methodName;

	private BigDecimal status;

	public Action() {
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public BigDecimal getActionId() {
		return this.actionId;
	}

	public void setActionId(BigDecimal actionId) {
		this.actionId = actionId;
	}

	public String getActionMaping() {
		return this.actionMaping;
	}

	public void setActionMaping(String actionMaping) {
		this.actionMaping = actionMaping;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionType() {
		return this.actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Object getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Object lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return this.lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}