package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAP_BILLING_GOODS_NEW database table.
 * 
 */
@Entity
@Table(name="MAP_BILLING_GOODS_NEW")
@NamedQuery(name="MapBillingGoodsNew.findAll", query="SELECT m FROM MapBillingGoodsNew m")
public class MapBillingGoodsNew implements Serializable {
	private static final long serialVersionUID = 1L;

	private String app;

	private BigDecimal channel;

	@Column(name="CHECK_STOCK")
	private String checkStock;

	@Column(name="FROM_DATE")
	private Object fromDate;

	@Column(name="GOODS_CODE")
	private String goodsCode;

	@Column(name="MAP_ID")
	private BigDecimal mapId;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SIM_TYPE")
	private BigDecimal simType;

	private BigDecimal status;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="TO_DATE")
	private Object toDate;

	public MapBillingGoodsNew() {
	}

	public String getApp() {
		return this.app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public BigDecimal getChannel() {
		return this.channel;
	}

	public void setChannel(BigDecimal channel) {
		this.channel = channel;
	}

	public String getCheckStock() {
		return this.checkStock;
	}

	public void setCheckStock(String checkStock) {
		this.checkStock = checkStock;
	}

	public Object getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Object fromDate) {
		this.fromDate = fromDate;
	}

	public String getGoodsCode() {
		return this.goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public BigDecimal getMapId() {
		return this.mapId;
	}

	public void setMapId(BigDecimal mapId) {
		this.mapId = mapId;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public BigDecimal getSimType() {
		return this.simType;
	}

	public void setSimType(BigDecimal simType) {
		this.simType = simType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Object getToDate() {
		return this.toDate;
	}

	public void setToDate(Object toDate) {
		this.toDate = toDate;
	}

}