package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the V_CC_PRE_MB database table.
 * 
 */
@Entity
@Table(name="V_CC_PRE_MB")
@NamedQuery(name="VCcPreMb.findAll", query="SELECT v FROM VCcPreMb v")
public class VCcPreMb implements Serializable {
	private static final long serialVersionUID = 1L;

	private String actstatus;

	private String actstatuscode;

	private String address;

	private String addresscode;

	private BigDecimal boardid;

	private BigDecimal cableid;

	private BigDecimal cablelength;

	private BigDecimal contractid;

	private String contractno;

	private BigDecimal cusid;

	private String cusname;

	private String district;

	private BigDecimal dslam;

	private String email;

	private String fathername;

	private String installaddress;

	private String isdn;

	private String ismi;

	private String lastname;

	private String locationcode;

	private String locationname;

	private String mothername;

	private String passport;

	private BigDecimal port;

	private String precinct;

	private String province;

	private String serial;

	private String servicetypename;

	private BigDecimal status;

	private BigDecimal subid;

	private String tel;

	public VCcPreMb() {
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

	public BigDecimal getContractid() {
		return this.contractid;
	}

	public void setContractid(BigDecimal contractid) {
		this.contractid = contractid;
	}

	public String getContractno() {
		return this.contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public BigDecimal getCusid() {
		return this.cusid;
	}

	public void setCusid(BigDecimal cusid) {
		this.cusid = cusid;
	}

	public String getCusname() {
		return this.cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public BigDecimal getDslam() {
		return this.dslam;
	}

	public void setDslam(BigDecimal dslam) {
		this.dslam = dslam;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFathername() {
		return this.fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
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

	public String getIsmi() {
		return this.ismi;
	}

	public void setIsmi(String ismi) {
		this.ismi = ismi;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getMothername() {
		return this.mothername;
	}

	public void setMothername(String mothername) {
		this.mothername = mothername;
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

	public String getServicetypename() {
		return this.servicetypename;
	}

	public void setServicetypename(String servicetypename) {
		this.servicetypename = servicetypename;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getSubid() {
		return this.subid;
	}

	public void setSubid(BigDecimal subid) {
		this.subid = subid;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}