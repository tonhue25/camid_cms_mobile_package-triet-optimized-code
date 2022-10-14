package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PRINT_INFORM_SMS database table.
 * 
 */
@Entity
@Table(name="PRINT_INFORM_SMS")
@NamedQuery(name="PrintInformSm.findAll", query="SELECT p FROM PrintInformSm p")
public class PrintInformSm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	@Column(name="CHARGE_REPORT_ID")
	private BigDecimal chargeReportId;

	private String content;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="SEND_TIME")
	private Date sendTime;

	@Column(name="SMS_LANGUAGE")
	private String smsLanguage;

	private BigDecimal status;

	@Column(name="TO_SENDER")
	private String toSender;

	public PrintInformSm() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getChargeReportId() {
		return this.chargeReportId;
	}

	public void setChargeReportId(BigDecimal chargeReportId) {
		this.chargeReportId = chargeReportId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getSmsLanguage() {
		return this.smsLanguage;
	}

	public void setSmsLanguage(String smsLanguage) {
		this.smsLanguage = smsLanguage;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getToSender() {
		return this.toSender;
	}

	public void setToSender(String toSender) {
		this.toSender = toSender;
	}

}