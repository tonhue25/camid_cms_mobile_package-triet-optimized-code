package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SUBSCRIBER database table.
 * 
 */
@Embeddable
public class SubscriberPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="SUB_SERVICE_CODE")
	private String subServiceCode;

	public SubscriberPK() {
	}
	public String getIsdn() {
		return this.isdn;
	}
	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}
	public String getSubServiceCode() {
		return this.subServiceCode;
	}
	public void setSubServiceCode(String subServiceCode) {
		this.subServiceCode = subServiceCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SubscriberPK)) {
			return false;
		}
		SubscriberPK castOther = (SubscriberPK)other;
		return 
			this.isdn.equals(castOther.isdn)
			&& this.subServiceCode.equals(castOther.subServiceCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.isdn.hashCode();
		hash = hash * prime + this.subServiceCode.hashCode();
		
		return hash;
	}
}