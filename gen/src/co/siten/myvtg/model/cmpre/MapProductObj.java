package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MAP_PRODUCT_OBJ database table.
 * 
 */
@Entity
@Table(name="MAP_PRODUCT_OBJ")
@NamedQuery(name="MapProductObj.findAll", query="SELECT m FROM MapProductObj m")
public class MapProductObj implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MAP_PRODUCT_OBJ_ID")
	private long mapProductObjId;

	@Column(name="DB_TYPE")
	private BigDecimal dbType;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="ISDN_ENABLED")
	private BigDecimal isdnEnabled;

	@Column(name="OBJECT_ID")
	private BigDecimal objectId;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="START_DATETIME")
	private Object startDatetime;

	private BigDecimal status;

	public MapProductObj() {
	}

	public long getMapProductObjId() {
		return this.mapProductObjId;
	}

	public void setMapProductObjId(long mapProductObjId) {
		this.mapProductObjId = mapProductObjId;
	}

	public BigDecimal getDbType() {
		return this.dbType;
	}

	public void setDbType(BigDecimal dbType) {
		this.dbType = dbType;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getIsdnEnabled() {
		return this.isdnEnabled;
	}

	public void setIsdnEnabled(BigDecimal isdnEnabled) {
		this.isdnEnabled = isdnEnabled;
	}

	public BigDecimal getObjectId() {
		return this.objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Object getStartDatetime() {
		return this.startDatetime;
	}

	public void setStartDatetime(Object startDatetime) {
		this.startDatetime = startDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}