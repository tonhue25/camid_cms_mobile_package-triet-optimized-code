package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ADD_ON database table.
 * 
 */
@Entity
@Table(name="ADD_ON")
@NamedQuery(name="AddOn.findAll", query="SELECT a FROM AddOn a")
public class AddOn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADD_DATA")
	private String addData;

	@Column(name="BALANCE_DATA_ID")
	private String balanceDataId;

	@Column(name="BALANCE_ID")
	private String balanceId;

	private String expire;

	@Column(name="EXTEND_CYCLE")
	private BigDecimal extendCycle;

	@Column(name="EXTEND_DATA")
	private BigDecimal extendData;

	@Column(name="EXTEND_FEE")
	private BigDecimal extendFee;

	private BigDecimal fee;

	@Column(name="GROUP_PACKET")
	private String groupPacket;

	@Column(name="LIST_ALLOW")
	private String listAllow;

	@Column(name="LIST_PRODUCT_CODE_ALLOW")
	private String listProductCodeAllow;

	private String name;

	@Column(name="NUM_BUY_CYCLE")
	private String numBuyCycle;

	@Column(name="PCRF_NAME")
	private String pcrfName;

	@Column(name="PRICE_PLAN")
	private String pricePlan;

	@Column(name="PROMOTION1_AMOUNT")
	private String promotion1Amount;

	@Column(name="PROMOTION1_ID")
	private String promotion1Id;

	@Column(name="PROMOTION2_AMOUNT")
	private String promotion2Amount;

	@Column(name="PROMOTION2_ID")
	private String promotion2Id;

	@Column(name="REFUSE_VAS")
	private String refuseVas;

	@Column(name="REGISTER_DAY")
	private String registerDay;

	private String syntax;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	@Column(name="VAS_CODE")
	private String vasCode;

	public AddOn() {
	}

	public String getAddData() {
		return this.addData;
	}

	public void setAddData(String addData) {
		this.addData = addData;
	}

	public String getBalanceDataId() {
		return this.balanceDataId;
	}

	public void setBalanceDataId(String balanceDataId) {
		this.balanceDataId = balanceDataId;
	}

	public String getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getExpire() {
		return this.expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public BigDecimal getExtendCycle() {
		return this.extendCycle;
	}

	public void setExtendCycle(BigDecimal extendCycle) {
		this.extendCycle = extendCycle;
	}

	public BigDecimal getExtendData() {
		return this.extendData;
	}

	public void setExtendData(BigDecimal extendData) {
		this.extendData = extendData;
	}

	public BigDecimal getExtendFee() {
		return this.extendFee;
	}

	public void setExtendFee(BigDecimal extendFee) {
		this.extendFee = extendFee;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getGroupPacket() {
		return this.groupPacket;
	}

	public void setGroupPacket(String groupPacket) {
		this.groupPacket = groupPacket;
	}

	public String getListAllow() {
		return this.listAllow;
	}

	public void setListAllow(String listAllow) {
		this.listAllow = listAllow;
	}

	public String getListProductCodeAllow() {
		return this.listProductCodeAllow;
	}

	public void setListProductCodeAllow(String listProductCodeAllow) {
		this.listProductCodeAllow = listProductCodeAllow;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumBuyCycle() {
		return this.numBuyCycle;
	}

	public void setNumBuyCycle(String numBuyCycle) {
		this.numBuyCycle = numBuyCycle;
	}

	public String getPcrfName() {
		return this.pcrfName;
	}

	public void setPcrfName(String pcrfName) {
		this.pcrfName = pcrfName;
	}

	public String getPricePlan() {
		return this.pricePlan;
	}

	public void setPricePlan(String pricePlan) {
		this.pricePlan = pricePlan;
	}

	public String getPromotion1Amount() {
		return this.promotion1Amount;
	}

	public void setPromotion1Amount(String promotion1Amount) {
		this.promotion1Amount = promotion1Amount;
	}

	public String getPromotion1Id() {
		return this.promotion1Id;
	}

	public void setPromotion1Id(String promotion1Id) {
		this.promotion1Id = promotion1Id;
	}

	public String getPromotion2Amount() {
		return this.promotion2Amount;
	}

	public void setPromotion2Amount(String promotion2Amount) {
		this.promotion2Amount = promotion2Amount;
	}

	public String getPromotion2Id() {
		return this.promotion2Id;
	}

	public void setPromotion2Id(String promotion2Id) {
		this.promotion2Id = promotion2Id;
	}

	public String getRefuseVas() {
		return this.refuseVas;
	}

	public void setRefuseVas(String refuseVas) {
		this.refuseVas = refuseVas;
	}

	public String getRegisterDay() {
		return this.registerDay;
	}

	public void setRegisterDay(String registerDay) {
		this.registerDay = registerDay;
	}

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	public String getVasCode() {
		return this.vasCode;
	}

	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}

}