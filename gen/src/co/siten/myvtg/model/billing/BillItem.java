package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BILL_ITEMS database table.
 * 
 */
@Entity
@Table(name="BILL_ITEMS")
@NamedQuery(name="BillItem.findAll", query="SELECT b FROM BillItem b")
public class BillItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ITEM_ID")
	private long itemId;

	private String code;

	@Column(name="COM_CHARGE")
	private BigDecimal comCharge;

	private String description;

	@Column(name="FEE_CHARGE")
	private BigDecimal feeCharge;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	private String name;

	@Column(name="ORDER_ITEM")
	private String orderItem;

	@Column(name="PRINT_DETAIL")
	private String printDetail;

	@Column(name="PRINT_GROUP_OBJECT_ID")
	private BigDecimal printGroupObjectId;

	private String status;

	private String tax;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public BillItem() {
	}

	public long getItemId() {
		return this.itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getComCharge() {
		return this.comCharge;
	}

	public void setComCharge(BigDecimal comCharge) {
		this.comCharge = comCharge;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getFeeCharge() {
		return this.feeCharge;
	}

	public void setFeeCharge(BigDecimal feeCharge) {
		this.feeCharge = feeCharge;
	}

	public BigDecimal getGroupItemId() {
		return this.groupItemId;
	}

	public void setGroupItemId(BigDecimal groupItemId) {
		this.groupItemId = groupItemId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderItem() {
		return this.orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public String getPrintDetail() {
		return this.printDetail;
	}

	public void setPrintDetail(String printDetail) {
		this.printDetail = printDetail;
	}

	public BigDecimal getPrintGroupObjectId() {
		return this.printGroupObjectId;
	}

	public void setPrintGroupObjectId(BigDecimal printGroupObjectId) {
		this.printGroupObjectId = printGroupObjectId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTax() {
		return this.tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

}