package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the BI_SUB_PROMOTION_DETAIL database table.
 * 
 */
@Entity
@Table(name="BI_SUB_PROMOTION_DETAIL")
@NamedQuery(name="BiSubPromotionDetail.findAll", query="SELECT b FROM BiSubPromotionDetail b")
public class BiSubPromotionDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BiSubPromotionDetailPK id;

	private BigDecimal amount;

	private String closed;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	@Column(name="PROM_TYPE")
	private String promType;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATETIME")
	private Date staDatetime;

	public BiSubPromotionDetail() {
	}

	public BiSubPromotionDetailPK getId() {
		return this.id;
	}

	public void setId(BiSubPromotionDetailPK id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getClosed() {
		return this.closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getPromType() {
		return this.promType;
	}

	public void setPromType(String promType) {
		this.promType = promType;
	}

	public Date getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

}