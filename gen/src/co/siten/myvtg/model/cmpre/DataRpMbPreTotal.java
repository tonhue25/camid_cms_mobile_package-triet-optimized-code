package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DATA_RP_MB_PRE_TOTAL database table.
 * 
 */
@Entity
@Table(name="DATA_RP_MB_PRE_TOTAL")
@NamedQuery(name="DataRpMbPreTotal.findAll", query="SELECT d FROM DataRpMbPreTotal d")
public class DataRpMbPreTotal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COUNT_INACTIVE")
	private BigDecimal countInactive;

	@Column(name="COUNT_NOMAL")
	private BigDecimal countNomal;

	@Column(name="COUNT_ONE_BLOCK")
	private BigDecimal countOneBlock;

	@Column(name="COUNT_OVER_THREE_MON")
	private BigDecimal countOverThreeMon;

	@Column(name="COUNT_TWO_BLOCK")
	private BigDecimal countTwoBlock;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="TOTAL_SUB")
	private BigDecimal totalSub;

	public DataRpMbPreTotal() {
	}

	public BigDecimal getCountInactive() {
		return this.countInactive;
	}

	public void setCountInactive(BigDecimal countInactive) {
		this.countInactive = countInactive;
	}

	public BigDecimal getCountNomal() {
		return this.countNomal;
	}

	public void setCountNomal(BigDecimal countNomal) {
		this.countNomal = countNomal;
	}

	public BigDecimal getCountOneBlock() {
		return this.countOneBlock;
	}

	public void setCountOneBlock(BigDecimal countOneBlock) {
		this.countOneBlock = countOneBlock;
	}

	public BigDecimal getCountOverThreeMon() {
		return this.countOverThreeMon;
	}

	public void setCountOverThreeMon(BigDecimal countOverThreeMon) {
		this.countOverThreeMon = countOverThreeMon;
	}

	public BigDecimal getCountTwoBlock() {
		return this.countTwoBlock;
	}

	public void setCountTwoBlock(BigDecimal countTwoBlock) {
		this.countTwoBlock = countTwoBlock;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getTotalSub() {
		return this.totalSub;
	}

	public void setTotalSub(BigDecimal totalSub) {
		this.totalSub = totalSub;
	}

}