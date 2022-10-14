package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DC_VTCI_HPN_POST database table.
 * 
 */
@Entity
@Table(name="DC_VTCI_HPN_POST")
@NamedQuery(name="DcVtciHpnPost.findAll", query="SELECT d FROM DcVtciHpnPost d")
public class DcVtciHpnPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BU_THIETBI")
	private BigDecimal buThietbi;

	@Column(name="BU_THUEBAO")
	private BigDecimal buThuebao;

	@Column(name="BUS_TYPE")
	private String busType;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String isdn;

	private String khuvuc;

	@Column(name="LOCATION_CODE")
	private String locationCode;

	private String name;

	@Temporal(TemporalType.DATE)
	private Date ngaylay;

	private String note;

	private String province;

	@Column(name="REG_TYPE")
	private String regType;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	public DcVtciHpnPost() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBuThietbi() {
		return this.buThietbi;
	}

	public void setBuThietbi(BigDecimal buThietbi) {
		this.buThietbi = buThietbi;
	}

	public BigDecimal getBuThuebao() {
		return this.buThuebao;
	}

	public void setBuThuebao(BigDecimal buThuebao) {
		this.buThuebao = buThuebao;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getKhuvuc() {
		return this.khuvuc;
	}

	public void setKhuvuc(String khuvuc) {
		this.khuvuc = khuvuc;
	}

	public String getLocationCode() {
		return this.locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getNgaylay() {
		return this.ngaylay;
	}

	public void setNgaylay(Date ngaylay) {
		this.ngaylay = ngaylay;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getRegType() {
		return this.regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}