package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRINT_NOTICE_UNSENT database table.
 * 
 */
@Entity
@Table(name="PRINT_NOTICE_UNSENT")
@NamedQuery(name="PrintNoticeUnsent.findAll", query="SELECT p FROM PrintNoticeUnsent p")
public class PrintNoticeUnsent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CONTRACT_NO")
	private String contractNo;

	public PrintNoticeUnsent() {
	}

	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

}