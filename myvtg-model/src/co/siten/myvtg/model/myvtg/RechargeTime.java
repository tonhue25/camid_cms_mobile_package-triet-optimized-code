package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the RECHARGE_TIMES database table.
 * 
 */
@Entity
@Table(name="RECHARGE_TIMES")
@NamedQuery(name="RechargeTime.findAll", query="SELECT r FROM RechargeTime r")
public class RechargeTime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "RECHARGE_TIMES_SEQ", allocationSize = 1)
	private BigDecimal id;
	@Column(name="\"COUNT\"")
	private Integer count;

	@Temporal(TemporalType.DATE)
	private Date datetime;

	private String msisdn;

	public RechargeTime() {
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Date getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

}