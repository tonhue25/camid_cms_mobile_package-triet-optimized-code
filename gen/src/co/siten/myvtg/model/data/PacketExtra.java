package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PACKET_EXTRA database table.
 * 
 */
@Entity
@Table(name="PACKET_EXTRA")
@NamedQuery(name="PacketExtra.findAll", query="SELECT p FROM PacketExtra p")
public class PacketExtra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BALANCE_ID")
	private String balanceId;

	@Column(name="BALANCE_VALUE")
	private String balanceValue;

	@Column(name="DATA_NAME")
	private String dataName;

	private String expire;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	public PacketExtra() {
	}

	public String getBalanceId() {
		return this.balanceId;
	}

	public void setBalanceId(String balanceId) {
		this.balanceId = balanceId;
	}

	public String getBalanceValue() {
		return this.balanceValue;
	}

	public void setBalanceValue(String balanceValue) {
		this.balanceValue = balanceValue;
	}

	public String getDataName() {
		return this.dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public String getExpire() {
		return this.expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

}