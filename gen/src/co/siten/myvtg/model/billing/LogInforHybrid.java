package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the LOG_INFOR_HYBRID database table.
 * 
 */
@Entity
@Table(name="LOG_INFOR_HYBRID")
@NamedQuery(name="LogInforHybrid.findAll", query="SELECT l FROM LogInforHybrid l")
public class LogInforHybrid implements Serializable {
	private static final long serialVersionUID = 1L;

	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_DATE")
	private Date createDate;

	private String status;

	public LogInforHybrid() {
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}