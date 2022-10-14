package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_OFFER_SUB_ITEM_TEMP database table.
 * 
 */
@Entity
@Table(name="BI_OFFER_SUB_ITEM_TEMP")
@NamedQuery(name="BiOfferSubItemTemp.findAll", query="SELECT b FROM BiOfferSubItemTemp b")
public class BiOfferSubItemTemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="OFFER_SUB_ITEM_ID")
	private long offerSubItemId;

	@Column(name="CONTRACT_OFFER_ID")
	private BigDecimal contractOfferId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATETIME")
	private Date endDatetime;

	@Column(name="SERVICE_ID")
	private String serviceId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	private BigDecimal status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	public BiOfferSubItemTemp() {
	}

	public long getOfferSubItemId() {
		return this.offerSubItemId;
	}

	public void setOfferSubItemId(long offerSubItemId) {
		this.offerSubItemId = offerSubItemId;
	}

	public BigDecimal getContractOfferId() {
		return this.contractOfferId;
	}

	public void setContractOfferId(BigDecimal contractOfferId) {
		this.contractOfferId = contractOfferId;
	}

	public Date getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
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

}