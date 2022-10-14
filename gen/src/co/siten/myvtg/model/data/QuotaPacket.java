package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the QUOTA_PACKET database table.
 * 
 */
@Entity
@Table(name="QUOTA_PACKET")
@NamedQuery(name="QuotaPacket.findAll", query="SELECT q FROM QuotaPacket q")
public class QuotaPacket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BALANCE_ID")
	private String balanceId;

	private BigDecimal fee;

	private String name;

	@Column(name="\"QUOTA\"")
	private BigDecimal quota;

	private String syntax;

	public QuotaPacket() {
	}

	public String getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getQuota() {
		return this.quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

}