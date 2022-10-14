package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CT_REMAIN_FILL_SAI database table.
 * 
 */
@Entity
@Table(name="CT_REMAIN_FILL_SAI")
@NamedQuery(name="CtRemainFillSai.findAll", query="SELECT c FROM CtRemainFillSai c")
public class CtRemainFillSai implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Column(name="AMOUNT_HANG")
	private BigDecimal amountHang;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_MODIFY")
	private Date lastModify;

	private BigDecimal status;

	public CtRemainFillSai() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmountHang() {
		return this.amountHang;
	}

	public void setAmountHang(BigDecimal amountHang) {
		this.amountHang = amountHang;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModify() {
		return this.lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}