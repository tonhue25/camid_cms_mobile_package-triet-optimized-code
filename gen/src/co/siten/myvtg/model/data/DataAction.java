package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DATA_ACTION database table.
 * 
 */
@Entity
@Table(name="DATA_ACTION")
@NamedQuery(name="DataAction.findAll", query="SELECT d FROM DataAction d")
public class DataAction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"ACTION\"")
	private BigDecimal action;

	@Column(name="CURRENT_DATA")
	private String currentData;

	@Column(name="FOLLOW_DATA")
	private String followData;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="RESET_EXTRA")
	private BigDecimal resetExtra;

	public DataAction() {
	}

	public BigDecimal getAction() {
		return this.action;
	}

	public void setAction(BigDecimal action) {
		this.action = action;
	}

	public String getCurrentData() {
		return this.currentData;
	}

	public void setCurrentData(String currentData) {
		this.currentData = currentData;
	}

	public String getFollowData() {
		return this.followData;
	}

	public void setFollowData(String followData) {
		this.followData = followData;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getResetExtra() {
		return this.resetExtra;
	}

	public void setResetExtra(BigDecimal resetExtra) {
		this.resetExtra = resetExtra;
	}

}