package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PSTN_CHAN_SUB_TEMP database table.
 * 
 */
@Entity
@Table(name="PSTN_CHAN_SUB_TEMP")
@NamedQuery(name="PstnChanSubTemp.findAll", query="SELECT p FROM PstnChanSubTemp p")
public class PstnChanSubTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="DIEU_CHINH")
	private BigDecimal dieuChinh;

	@Column(name="NO_DAU_KY")
	private BigDecimal noDauKy;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="THANH_TOAN")
	private BigDecimal thanhToan;

	@Column(name="TONG_NO")
	private BigDecimal tongNo;

	public PstnChanSubTemp() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getDieuChinh() {
		return this.dieuChinh;
	}

	public void setDieuChinh(BigDecimal dieuChinh) {
		this.dieuChinh = dieuChinh;
	}

	public BigDecimal getNoDauKy() {
		return this.noDauKy;
	}

	public void setNoDauKy(BigDecimal noDauKy) {
		this.noDauKy = noDauKy;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getThanhToan() {
		return this.thanhToan;
	}

	public void setThanhToan(BigDecimal thanhToan) {
		this.thanhToan = thanhToan;
	}

	public BigDecimal getTongNo() {
		return this.tongNo;
	}

	public void setTongNo(BigDecimal tongNo) {
		this.tongNo = tongNo;
	}

}