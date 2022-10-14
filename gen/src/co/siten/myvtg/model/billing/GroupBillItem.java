package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GROUP_BILL_ITEMS database table.
 * 
 */
@Entity
@Table(name="GROUP_BILL_ITEMS")
@NamedQuery(name="GroupBillItem.findAll", query="SELECT g FROM GroupBillItem g")
public class GroupBillItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String description;

	@Column(name="GROUP_ITEM_ID")
	private BigDecimal groupItemId;

	private String name;

	@Column(name="ORDER_GROUP_ITEM")
	private String orderGroupItem;

	@Column(name="PRINT_DETAIL")
	private String printDetail;

	@Column(name="PROM_CHARGE")
	private BigDecimal promCharge;

	private String status;

	private String tax;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public GroupBillItem() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getOrderGroupItem() {
		return this.orderGroupItem;
	}

	public void setOrderGroupItem(String orderGroupItem) {
		this.orderGroupItem = orderGroupItem;
	}

	public String getPrintDetail() {
		return this.printDetail;
	}

	public void setPrintDetail(String printDetail) {
		this.printDetail = printDetail;
	}

	public BigDecimal getPromCharge() {
		return this.promCharge;
	}

	public void setPromCharge(BigDecimal promCharge) {
		this.promCharge = promCharge;
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