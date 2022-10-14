package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACTIVE_CELL database table.
 * 
 */
@Entity
@Table(name="ACTIVE_CELL")
@NamedQuery(name="ActiveCell.findAll", query="SELECT a FROM ActiveCell a")
public class ActiveCell implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal cell;

	@Column(name="DATE_CHANGE")
	private Object dateChange;

	private Object datetime;

	private String imsi;

	private String isdn;

	private String product;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public ActiveCell() {
	}

	public BigDecimal getCell() {
		return this.cell;
	}

	public void setCell(BigDecimal cell) {
		this.cell = cell;
	}

	public Object getDateChange() {
		return this.dateChange;
	}

	public void setDateChange(Object dateChange) {
		this.dateChange = dateChange;
	}

	public Object getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Object datetime) {
		this.datetime = datetime;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}