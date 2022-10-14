package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the UD_KS_MYANMAR database table.
 * 
 */
@Entity
@Table(name="UD_KS_MYANMAR")
@NamedQuery(name="UdKsMyanmar.findAll", query="SELECT u FROM UdKsMyanmar u")
public class UdKsMyanmar implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	private String isdn;

	private String name;

	private BigDecimal priority;

	private BigDecimal promotion;

	public UdKsMyanmar() {
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public BigDecimal getPromotion() {
		return this.promotion;
	}

	public void setPromotion(BigDecimal promotion) {
		this.promotion = promotion;
	}

}