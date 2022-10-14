package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SPECT_PRODUCT_LOG database table.
 * 
 */
@Entity
@Table(name="SPECT_PRODUCT_LOG")
@NamedQuery(name="SpectProductLog.findAll", query="SELECT s FROM SpectProductLog s")
public class SpectProductLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ACTION_CODE")
	private String actionCode;

	private String description;

	private String ip;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="ROW_ID")
	private BigDecimal rowId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="TIME_LOG")
	private Object timeLog;

	@Column(name="USER_CODE")
	private String userCode;

	@Column(name="USER_NAME")
	private String userName;

	public SpectProductLog() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionCode() {
		return this.actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getRowId() {
		return this.rowId;
	}

	public void setRowId(BigDecimal rowId) {
		this.rowId = rowId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Object getTimeLog() {
		return this.timeLog;
	}

	public void setTimeLog(Object timeLog) {
		this.timeLog = timeLog;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}