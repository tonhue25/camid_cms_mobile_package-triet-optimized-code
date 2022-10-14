package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the "ACTION" database table.
 * 
 */
@Embeddable
public class ActionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SUB_SERVICE_CODE")
	private String subServiceCode;

	@Column(name="ACTION_TYPE")
	private Integer actionType;

	@Column(name="CHANNEL_TYPE")
	private Integer channelType;

	public ActionPK() {
	}
	public String getSubServiceCode() {
		return this.subServiceCode;
	}
	public void setSubServiceCode(String subServiceCode) {
		this.subServiceCode = subServiceCode;
	}
	
	

	public Integer getActionType() {
		return actionType;
	}
	public Integer getChannelType() {
		return channelType;
	}
	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ActionPK)) {
			return false;
		}
		ActionPK castOther = (ActionPK)other;
		return 
			this.subServiceCode.equals(castOther.subServiceCode)
			&& (this.actionType == castOther.actionType)
			&& (this.channelType == castOther.channelType);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.subServiceCode.hashCode();
		hash = hash * prime + ((int) (this.actionType ^ (this.actionType >>> 32)));
		hash = hash * prime + ((int) (this.channelType ^ (this.channelType >>> 32)));
		
		return hash;
	}
}