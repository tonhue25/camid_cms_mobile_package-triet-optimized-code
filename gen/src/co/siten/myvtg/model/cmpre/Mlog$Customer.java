package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MLOG$_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="MLOG$_CUSTOMER")
@NamedQuery(name="Mlog$Customer.findAll", query="SELECT m FROM Mlog$Customer m")
public class Mlog$Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHANGE_VECTOR$$")
	private byte[] changeVector$$;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	private String dmltype$$;

	@Column(name="OLD_NEW$$")
	private String oldNew$$;

	private Object snaptime$$;

	public Mlog$Customer() {
	}

	public byte[] getChangeVector$$() {
		return this.changeVector$$;
	}

	public void setChangeVector$$(byte[] changeVector$$) {
		this.changeVector$$ = changeVector$$;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public String getDmltype$$() {
		return this.dmltype$$;
	}

	public void setDmltype$$(String dmltype$$) {
		this.dmltype$$ = dmltype$$;
	}

	public String getOldNew$$() {
		return this.oldNew$$;
	}

	public void setOldNew$$(String oldNew$$) {
		this.oldNew$$ = oldNew$$;
	}

	public Object getSnaptime$$() {
		return this.snaptime$$;
	}

	public void setSnaptime$$(Object snaptime$$) {
		this.snaptime$$ = snaptime$$;
	}

}