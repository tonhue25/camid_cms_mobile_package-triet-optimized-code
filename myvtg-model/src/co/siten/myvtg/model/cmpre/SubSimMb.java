package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;

import co.siten.myvtg.config.Config;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the SUB_SIM_MB database table.
 * 
 */
@Entity
@Table(name = "SUB_SIM_MB")
@NamedQuery(name = "SubSimMb.findAll", query = "SELECT s FROM SubSimMb s")
public class SubSimMb implements Serializable {
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATETIME")
	private Date endDatetime;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = Config.SEQUENCE_CM_PRE_SUB_SIM_MB, allocationSize = 1)
	private BigDecimal id;

	private String imsi;

	private String serial;
	@Temporal(TemporalType.DATE)
	@Column(name = "STA_DATETIME")
	private Date staDatetime;

	private Integer status;

	@Column(name = "SUB_ID")
	private BigDecimal subId;

	public SubSimMb() {
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public BigDecimal getId() {
		return id;
	}

	public String getImsi() {
		return imsi;
	}

	public String getSerial() {
		return serial;
	}

	public Date getStaDatetime() {
		return staDatetime;
	}

	public Integer getStatus() {
		return status;
	}

	public BigDecimal getSubId() {
		return subId;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}