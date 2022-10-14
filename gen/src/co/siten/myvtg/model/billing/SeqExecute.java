package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SEQ_EXECUTE database table.
 * 
 */
@Entity
@Table(name="SEQ_EXECUTE")
@NamedQuery(name="SeqExecute.findAll", query="SELECT s FROM SeqExecute s")
public class SeqExecute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="NOW_EXECUTE")
	private BigDecimal nowExecute;

	public SeqExecute() {
	}

	public BigDecimal getNowExecute() {
		return this.nowExecute;
	}

	public void setNowExecute(BigDecimal nowExecute) {
		this.nowExecute = nowExecute;
	}

}