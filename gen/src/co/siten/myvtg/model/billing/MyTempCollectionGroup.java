package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MY_TEMP_COLLECTION_GROUP database table.
 * 
 */
@Entity
@Table(name="MY_TEMP_COLLECTION_GROUP")
@NamedQuery(name="MyTempCollectionGroup.findAll", query="SELECT m FROM MyTempCollectionGroup m")
public class MyTempCollectionGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COLLECTION_GROUP_ID")
	private BigDecimal collectionGroupId;

	public MyTempCollectionGroup() {
	}

	public BigDecimal getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(BigDecimal collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
	}

}