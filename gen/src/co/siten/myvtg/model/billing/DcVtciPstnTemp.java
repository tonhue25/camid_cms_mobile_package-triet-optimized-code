package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the DC_VTCI_PSTN_TEMP database table.
 * 
 */
@Entity
@Table(name="DC_VTCI_PSTN_TEMP")
@NamedQuery(name="DcVtciPstnTemp.findAll", query="SELECT d FROM DcVtciPstnTemp d")
public class DcVtciPstnTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DcVtciPstnTempPK id;

	@Column(name="BU_THIETBI")
	private BigDecimal buThietbi;

	@Column(name="BU_THUEBAO")
	private BigDecimal buThuebao;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Temporal(TemporalType.DATE)
	@Column(name="FIRST_CONNECT")
	private Date firstConnect;

	private String khuvuc;

	@Column(name="LOCATION_CODE")
	private String locationCode;

	private String province;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	@Column(name="TELECOM_SERVICE_ID")
	private BigDecimal telecomServiceId;

	public DcVtciPstnTemp() {
	}

	public DcVtciPstnTempPK getId() {
		return this.id;
	}

	public void setId(DcVtciPstnTempPK id) {
		this.id = id;
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

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public Date getFirstConnect() {
		return this.firstConnect;
	}

	public void setFirstConnect(Date firstConnect) {
		this.firstConnect = firstConnect;
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

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getTelecomServiceId() {
		return this.telecomServiceId;
	}

	public void setTelecomServiceId(BigDecimal telecomServiceId) {
		this.telecomServiceId = telecomServiceId;
	}

}