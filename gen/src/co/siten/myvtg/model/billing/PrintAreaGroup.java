package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PRINT_AREA_GROUP database table.
 * 
 */
@Entity
@Table(name="PRINT_AREA_GROUP")
@NamedQuery(name="PrintAreaGroup.findAll", query="SELECT p FROM PrintAreaGroup p")
public class PrintAreaGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COLLECTION_GROUP_ID")
	private BigDecimal collectionGroupId;

	@Temporal(TemporalType.DATE)
	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="PRINT_AREA_ID")
	private BigDecimal printAreaId;

	@Temporal(TemporalType.DATE)
	@Column(name="STA_DATE")
	private Date staDate;

	public PrintAreaGroup() {
	}

	public BigDecimal getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(BigDecimal collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPrintAreaId() {
		return this.printAreaId;
	}

	public void setPrintAreaId(BigDecimal printAreaId) {
		this.printAreaId = printAreaId;
	}

	public Date getStaDate() {
		return this.staDate;
	}

	public void setStaDate(Date staDate) {
		this.staDate = staDate;
	}

}