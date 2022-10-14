package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the KIEMTOAN_NN database table.
 * 
 */
@Entity
@Table(name="KIEMTOAN_NN")
@NamedQuery(name="KiemtoanNn.findAll", query="SELECT k FROM KiemtoanNn k")
public class KiemtoanNn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CUST_ID")
	private String custId;

	private String des;

	private String isdn;

	private String name;

	@Column(name="SUB_ID")
	private String subId;

	private BigDecimal tien;

	public KiemtoanNn() {
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getSubId() {
		return this.subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public BigDecimal getTien() {
		return this.tien;
	}

	public void setTien(BigDecimal tien) {
		this.tien = tien;
	}

}