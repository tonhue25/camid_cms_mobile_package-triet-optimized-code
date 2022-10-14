package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.siten.myvtg.config.Config;

/**
 * The persistent class for the SUB_ISDN_MB database table.
 * 
 */
@Entity
@Table(name = "SUB_ISDN_MB")
@NamedQuery(name = "SubIsdnMb.findAll", query = "SELECT s FROM SubIsdnMb s")
public class SubIsdnMb implements Serializable {
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATETIME")
	private Date endDatetime;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = Config.SEQUENCE_CM_PRE_SUB_ISDN_MB, allocationSize = 1)
	@Column(nullable = false)
	private BigDecimal id;

	@Column(nullable = false, length = 10)
	private String isdn;
	@Temporal(TemporalType.DATE)
	@Column(name = "STA_DATETIME", nullable = false)
	private Date staDatetime;

	@Column(nullable = false, precision = 1)
	private BigDecimal status;

	@Column(name = "SUB_ID", nullable = false, precision = 10)
	private BigDecimal subId;

	public SubIsdnMb() {
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public BigDecimal getId() {
		return id;
	}

	public String getIsdn() {
		return isdn;
	}

	public Date getStaDatetime() {
		return staDatetime;
	}

	public BigDecimal getStatus() {
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

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public void setStaDatetime(Date staDatetime) {
		this.staDatetime = staDatetime;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

}