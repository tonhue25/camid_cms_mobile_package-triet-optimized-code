package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the V_CC_PRE_HP database table.
 * 
 */
@Entity
@Table(name="V_CC_PRE_HP")
@NamedQuery(name="VCcPreHp.findAll", query="SELECT v FROM VCcPreHp v")
public class VCcPreHp implements Serializable {
	private static final long serialVersionUID = 1L;

	private String actstatus;

	private String actstatuscode;

	private String address;

	private String addresscode;

	private BigDecimal boardid;

	private BigDecimal cableid;

	private BigDecimal cablelength;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String contractno;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	private String district;

	private BigDecimal dslamid;

	private String email;

	private String imsi;

	private String installaddress;

	private String isdn;

	private String locationcode;

	private String locationname;

	private String name;

	private String passport;

	private BigDecimal port;

	private String precinct;

	private String province;

	private String serial;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	private String subtypename;

	@Column(name="TEL_FAX")
	private String telFax;

	public VCcPreHp() {
	}

	public String getActstatus() {
		return this.actstatus;
	}

	public void setActstatus(String actstatus) {
		this.actstatus = actstatus;
	}

	public String getActstatuscode() {
		return this.actstatuscode;
	}

	public void setActstatuscode(String actstatuscode) {
		this.actstatuscode = actstatuscode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddresscode() {
		return this.addresscode;
	}

	public void setAddresscode(String addresscode) {
		this.addresscode = addresscode;
	}

	public BigDecimal getBoardid() {
		return this.boardid;
	}

	public void setBoardid(BigDecimal boardid) {
		this.boardid = boardid;
	}

	public BigDecimal getCableid() {
		return this.cableid;
	}

	public void setCableid(BigDecimal cableid) {
		this.cableid = cableid;
	}

	public BigDecimal getCablelength() {
		return this.cablelength;
	}

	public void setCablelength(BigDecimal cablelength) {
		this.cablelength = cablelength;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getContractno() {
		return this.contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public BigDecimal getDslamid() {
		return this.dslamid;
	}

	public void setDslamid(BigDecimal dslamid) {
		this.dslamid = dslamid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImsi() {
		return this.imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getInstalladdress() {
		return this.installaddress;
	}

	public void setInstalladdress(String installaddress) {
		this.installaddress = installaddress;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getLocationcode() {
		return this.locationcode;
	}

	public void setLocationcode(String locationcode) {
		this.locationcode = locationcode;
	}

	public String getLocationname() {
		return this.locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassport() {
		return this.passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public BigDecimal getPort() {
		return this.port;
	}

	public void setPort(BigDecimal port) {
		this.port = port;
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

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getSubtypename() {
		return this.subtypename;
	}

	public void setSubtypename(String subtypename) {
		this.subtypename = subtypename;
	}

	public String getTelFax() {
		return this.telFax;
	}

	public void setTelFax(String telFax) {
		this.telFax = telFax;
	}

}