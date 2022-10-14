package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the NET_PAID_ERROR_DETAIL database table.
 * 
 */
@Entity
@Table(name="NET_PAID_ERROR_DETAIL")
@NamedQuery(name="NetPaidErrorDetail.findAll", query="SELECT n FROM NetPaidErrorDetail n")
public class NetPaidErrorDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal amount;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	@Column(name="CUST_SER_ID")
	private BigDecimal custSerId;

	private String description;

	private BigDecimal discount;

	@Column(name="ITEM_ID")
	private String itemId;

	@Column(name="PROMOTION_ID")
	private String promotionId;

	private String status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="USAGE_CHARGE_TAX")
	private BigDecimal usageChargeTax;

	public NetPaidErrorDetail() {
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public BigDecimal getCustSerId() {
		return this.custSerId;
	}

	public void setCustSerId(BigDecimal custSerId) {
		this.custSerId = custSerId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPromotionId() {
		return this.promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getUsageChargeTax() {
		return this.usageChargeTax;
	}

	public void setUsageChargeTax(BigDecimal usageChargeTax) {
		this.usageChargeTax = usageChargeTax;
	}

}