package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MY_IMUZIK_SUMMARY database table.
 * 
 */
@Entity
@Table(name="MY_IMUZIK_SUMMARY")
@NamedQuery(name="MyImuzikSummary.findAll", query="SELECT m FROM MyImuzikSummary m")
public class MyImuzikSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	private BigDecimal ncount;

	@Column(name="SONG_ID")
	private String songId;

	@Column(name="SUM_CHARGE")
	private BigDecimal sumCharge;

	public MyImuzikSummary() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public BigDecimal getNcount() {
		return this.ncount;
	}

	public void setNcount(BigDecimal ncount) {
		this.ncount = ncount;
	}

	public String getSongId() {
		return this.songId;
	}

	public void setSongId(String songId) {
		this.songId = songId;
	}

	public BigDecimal getSumCharge() {
		return this.sumCharge;
	}

	public void setSumCharge(BigDecimal sumCharge) {
		this.sumCharge = sumCharge;
	}

}