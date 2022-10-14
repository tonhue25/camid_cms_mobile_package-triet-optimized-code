package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DATA_PACKET database table.
 * 
 */
@Entity
@Table(name="DATA_PACKET")
@NamedQuery(name="DataPacket.findAll", query="SELECT d FROM DataPacket d")
public class DataPacket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ALLOW_EXTEND")
	private BigDecimal allowExtend;

	@Column(name="AUTO_ADD_PACKET")
	private String autoAddPacket;

	@Column(name="AUTO_EXTEND")
	private BigDecimal autoExtend;

	@Column(name="CHECK_BAL")
	private BigDecimal checkBal;

	private String expire;

	private BigDecimal fee;

	@Column(name="FEE_EXTEND")
	private BigDecimal feeExtend;

	@Column(name="GROUP_BASE")
	private BigDecimal groupBase;

	@Column(name="GROUP_PACKET")
	private BigDecimal groupPacket;

	@Column(name="LIST_ALLOW")
	private String listAllow;

	private String name;

	@Column(name="PACKET_BASE")
	private String packetBase;

	@Column(name="PCRF_NAME")
	private String pcrfName;

	@Column(name="PRICE_OCS")
	private String priceOcs;

	@Column(name="REFUSE_VAS")
	private String refuseVas;

	@Column(name="RESTRICT_DATA")
	private String restrictData;

	private String syntax;

	@Column(name="TEMPLATE_HLR")
	private String templateHlr;

	@Column(name="TEMPLATE_RESTRICT")
	private String templateRestrict;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	@Column(name="VAS_CODE")
	private String vasCode;

	public DataPacket() {
	}

	public BigDecimal getAllowExtend() {
		return this.allowExtend;
	}

	public void setAllowExtend(BigDecimal allowExtend) {
		this.allowExtend = allowExtend;
	}

	public String getAutoAddPacket() {
		return this.autoAddPacket;
	}

	public void setAutoAddPacket(String autoAddPacket) {
		this.autoAddPacket = autoAddPacket;
	}

	public BigDecimal getAutoExtend() {
		return this.autoExtend;
	}

	public void setAutoExtend(BigDecimal autoExtend) {
		this.autoExtend = autoExtend;
	}

	public BigDecimal getCheckBal() {
		return this.checkBal;
	}

	public void setCheckBal(BigDecimal checkBal) {
		this.checkBal = checkBal;
	}

	public String getExpire() {
		return this.expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getFeeExtend() {
		return this.feeExtend;
	}

	public void setFeeExtend(BigDecimal feeExtend) {
		this.feeExtend = feeExtend;
	}

	public BigDecimal getGroupBase() {
		return this.groupBase;
	}

	public void setGroupBase(BigDecimal groupBase) {
		this.groupBase = groupBase;
	}

	public BigDecimal getGroupPacket() {
		return this.groupPacket;
	}

	public void setGroupPacket(BigDecimal groupPacket) {
		this.groupPacket = groupPacket;
	}

	public String getListAllow() {
		return this.listAllow;
	}

	public void setListAllow(String listAllow) {
		this.listAllow = listAllow;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPacketBase() {
		return this.packetBase;
	}

	public void setPacketBase(String packetBase) {
		this.packetBase = packetBase;
	}

	public String getPcrfName() {
		return this.pcrfName;
	}

	public void setPcrfName(String pcrfName) {
		this.pcrfName = pcrfName;
	}

	public String getPriceOcs() {
		return this.priceOcs;
	}

	public void setPriceOcs(String priceOcs) {
		this.priceOcs = priceOcs;
	}

	public String getRefuseVas() {
		return this.refuseVas;
	}

	public void setRefuseVas(String refuseVas) {
		this.refuseVas = refuseVas;
	}

	public String getRestrictData() {
		return this.restrictData;
	}

	public void setRestrictData(String restrictData) {
		this.restrictData = restrictData;
	}

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public String getTemplateHlr() {
		return this.templateHlr;
	}

	public void setTemplateHlr(String templateHlr) {
		this.templateHlr = templateHlr;
	}

	public String getTemplateRestrict() {
		return this.templateRestrict;
	}

	public void setTemplateRestrict(String templateRestrict) {
		this.templateRestrict = templateRestrict;
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