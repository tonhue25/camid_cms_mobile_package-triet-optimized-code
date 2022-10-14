package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the FAMILY_NUMBER_HP database table.
 * 
 */
@Entity
@Table(name="FAMILY_NUMBER_HP")
@NamedQuery(name="FamilyNumberHp.findAll", query="SELECT f FROM FamilyNumberHp f")
public class FamilyNumberHp implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FamilyNumberHpPK id;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="SHOP_CODE")
	private String shopCode;

	private BigDecimal status;

	@Column(name="USER_NAME")
	private String userName;

	public FamilyNumberHp() {
	}

	public FamilyNumberHpPK getId() {
		return this.id;
	}

	public void setId(FamilyNumberHpPK id) {
		this.id = id;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}