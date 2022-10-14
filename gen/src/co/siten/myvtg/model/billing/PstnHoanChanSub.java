package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PSTN_HOAN_CHAN_SUB database table.
 * 
 */
@Entity
@Table(name="PSTN_HOAN_CHAN_SUB")
@NamedQuery(name="PstnHoanChanSub.findAll", query="SELECT p FROM PstnHoanChanSub p")
public class PstnHoanChanSub implements Serializable {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	private String cn;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String description;

	@Column(name="DICH_VU")
	private String dichVu;

	@Column(name="DIEU_CHINH")
	private BigDecimal dieuChinh;

	private String district;

	private String isdn;

	@Column(name="LOAI_CHAN")
	private String loaiChan;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="NGAY_CHAN")
	private Date ngayChan;

	@Column(name="NO_DAU_KY")
	private BigDecimal noDauKy;

	@Column(name="PAY_METHOD_CODE")
	private String payMethodCode;

	private String precinct;

	private String province;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="THANH_TOAN")
	private BigDecimal thanhToan;

	@Column(name="TONG_NO")
	private BigDecimal tongNo;

	public PstnHoanChanSub() {
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public String getCn() {
		return this.cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDichVu() {
		return this.dichVu;
	}

	public void setDichVu(String dichVu) {
		this.dichVu = dichVu;
	}

	public BigDecimal getDieuChinh() {
		return this.dieuChinh;
	}

	public void setDieuChinh(BigDecimal dieuChinh) {
		this.dieuChinh = dieuChinh;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getLoaiChan() {
		return this.loaiChan;
	}

	public void setLoaiChan(String loaiChan) {
		this.loaiChan = loaiChan;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getNgayChan() {
		return this.ngayChan;
	}

	public void setNgayChan(Date ngayChan) {
		this.ngayChan = ngayChan;
	}

	public BigDecimal getNoDauKy() {
		return this.noDauKy;
	}

	public void setNoDauKy(BigDecimal noDauKy) {
		this.noDauKy = noDauKy;
	}

	public String getPayMethodCode() {
		return this.payMethodCode;
	}

	public void setPayMethodCode(String payMethodCode) {
		this.payMethodCode = payMethodCode;
	}

	public String getPrecinct() {
		return this.precinct;
	}

	public void setPrecinct(String precinct) {
		this.precinct = precinct;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
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