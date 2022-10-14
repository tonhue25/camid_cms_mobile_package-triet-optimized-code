package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the VOUBILL database table.
 * 
 */
@Entity
@NamedQuery(name="Voubill.findAll", query="SELECT v FROM Voubill v")
public class Voubill implements Serializable {
	private static final long serialVersionUID = 1L;

	private String batchnumber;

	private BigDecimal blockchange;

	private String cardnumber;

	private String celliid;

	private BigDecimal extrafreeminute2;

	private BigDecimal extrafreeminute3;

	private BigDecimal extrafreemms;

	private BigDecimal extrafreesm2;

	private BigDecimal extrafreesm3;

	private BigDecimal extrafreevolume;

	private BigDecimal extravideominute;

	private BigDecimal facevalue;

	private BigDecimal id;

	private BigDecimal ivrchgtimesnew;

	private BigDecimal msisdn;

	private String newactivestop;

	private BigDecimal newbalance;

	private String operatedby;

	private String previousactivestop;

	private BigDecimal previousbalance;

	private BigDecimal prmminute;

	private BigDecimal prmsm;

	private BigDecimal rechargetax;

	private String rechargetype;

	private BigDecimal regecardserviceclass;

	private BigDecimal rrmpoint;

	private String serialnumber;

	private BigDecimal subscriberclassofservice;

	private String tradetime;

	private BigDecimal validityadded;

	private BigDecimal validityperiod;

	private BigDecimal valueadded;

	private BigDecimal zoneid;

	public Voubill() {
	}

	public String getBatchnumber() {
		return this.batchnumber;
	}

	public void setBatchnumber(String batchnumber) {
		this.batchnumber = batchnumber;
	}

	public BigDecimal getBlockchange() {
		return this.blockchange;
	}

	public void setBlockchange(BigDecimal blockchange) {
		this.blockchange = blockchange;
	}

	public String getCardnumber() {
		return this.cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getCelliid() {
		return this.celliid;
	}

	public void setCelliid(String celliid) {
		this.celliid = celliid;
	}

	public BigDecimal getExtrafreeminute2() {
		return this.extrafreeminute2;
	}

	public void setExtrafreeminute2(BigDecimal extrafreeminute2) {
		this.extrafreeminute2 = extrafreeminute2;
	}

	public BigDecimal getExtrafreeminute3() {
		return this.extrafreeminute3;
	}

	public void setExtrafreeminute3(BigDecimal extrafreeminute3) {
		this.extrafreeminute3 = extrafreeminute3;
	}

	public BigDecimal getExtrafreemms() {
		return this.extrafreemms;
	}

	public void setExtrafreemms(BigDecimal extrafreemms) {
		this.extrafreemms = extrafreemms;
	}

	public BigDecimal getExtrafreesm2() {
		return this.extrafreesm2;
	}

	public void setExtrafreesm2(BigDecimal extrafreesm2) {
		this.extrafreesm2 = extrafreesm2;
	}

	public BigDecimal getExtrafreesm3() {
		return this.extrafreesm3;
	}

	public void setExtrafreesm3(BigDecimal extrafreesm3) {
		this.extrafreesm3 = extrafreesm3;
	}

	public BigDecimal getExtrafreevolume() {
		return this.extrafreevolume;
	}

	public void setExtrafreevolume(BigDecimal extrafreevolume) {
		this.extrafreevolume = extrafreevolume;
	}

	public BigDecimal getExtravideominute() {
		return this.extravideominute;
	}

	public void setExtravideominute(BigDecimal extravideominute) {
		this.extravideominute = extravideominute;
	}

	public BigDecimal getFacevalue() {
		return this.facevalue;
	}

	public void setFacevalue(BigDecimal facevalue) {
		this.facevalue = facevalue;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIvrchgtimesnew() {
		return this.ivrchgtimesnew;
	}

	public void setIvrchgtimesnew(BigDecimal ivrchgtimesnew) {
		this.ivrchgtimesnew = ivrchgtimesnew;
	}

	public BigDecimal getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(BigDecimal msisdn) {
		this.msisdn = msisdn;
	}

	public String getNewactivestop() {
		return this.newactivestop;
	}

	public void setNewactivestop(String newactivestop) {
		this.newactivestop = newactivestop;
	}

	public BigDecimal getNewbalance() {
		return this.newbalance;
	}

	public void setNewbalance(BigDecimal newbalance) {
		this.newbalance = newbalance;
	}

	public String getOperatedby() {
		return this.operatedby;
	}

	public void setOperatedby(String operatedby) {
		this.operatedby = operatedby;
	}

	public String getPreviousactivestop() {
		return this.previousactivestop;
	}

	public void setPreviousactivestop(String previousactivestop) {
		this.previousactivestop = previousactivestop;
	}

	public BigDecimal getPreviousbalance() {
		return this.previousbalance;
	}

	public void setPreviousbalance(BigDecimal previousbalance) {
		this.previousbalance = previousbalance;
	}

	public BigDecimal getPrmminute() {
		return this.prmminute;
	}

	public void setPrmminute(BigDecimal prmminute) {
		this.prmminute = prmminute;
	}

	public BigDecimal getPrmsm() {
		return this.prmsm;
	}

	public void setPrmsm(BigDecimal prmsm) {
		this.prmsm = prmsm;
	}

	public BigDecimal getRechargetax() {
		return this.rechargetax;
	}

	public void setRechargetax(BigDecimal rechargetax) {
		this.rechargetax = rechargetax;
	}

	public String getRechargetype() {
		return this.rechargetype;
	}

	public void setRechargetype(String rechargetype) {
		this.rechargetype = rechargetype;
	}

	public BigDecimal getRegecardserviceclass() {
		return this.regecardserviceclass;
	}

	public void setRegecardserviceclass(BigDecimal regecardserviceclass) {
		this.regecardserviceclass = regecardserviceclass;
	}

	public BigDecimal getRrmpoint() {
		return this.rrmpoint;
	}

	public void setRrmpoint(BigDecimal rrmpoint) {
		this.rrmpoint = rrmpoint;
	}

	public String getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public BigDecimal getSubscriberclassofservice() {
		return this.subscriberclassofservice;
	}

	public void setSubscriberclassofservice(BigDecimal subscriberclassofservice) {
		this.subscriberclassofservice = subscriberclassofservice;
	}

	public String getTradetime() {
		return this.tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public BigDecimal getValidityadded() {
		return this.validityadded;
	}

	public void setValidityadded(BigDecimal validityadded) {
		this.validityadded = validityadded;
	}

	public BigDecimal getValidityperiod() {
		return this.validityperiod;
	}

	public void setValidityperiod(BigDecimal validityperiod) {
		this.validityperiod = validityperiod;
	}

	public BigDecimal getValueadded() {
		return this.valueadded;
	}

	public void setValueadded(BigDecimal valueadded) {
		this.valueadded = valueadded;
	}

	public BigDecimal getZoneid() {
		return this.zoneid;
	}

	public void setZoneid(BigDecimal zoneid) {
		this.zoneid = zoneid;
	}

}