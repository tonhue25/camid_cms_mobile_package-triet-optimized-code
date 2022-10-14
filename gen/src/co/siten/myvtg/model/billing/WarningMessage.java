package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the WARNING_MESSAGE database table.
 * 
 */
@Entity
@Table(name="WARNING_MESSAGE")
@NamedQuery(name="WarningMessage.findAll", query="SELECT w FROM WarningMessage w")
public class WarningMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="\"MESSAGE\"")
	private String message;

	@Temporal(TemporalType.DATE)
	@Column(name="SEND_DATE")
	private Date sendDate;

	private BigDecimal status;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="WARNING_MESSAGE_ID")
	private BigDecimal warningMessageId;

	public WarningMessage() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public BigDecimal getWarningMessageId() {
		return this.warningMessageId;
	}

	public void setWarningMessageId(BigDecimal warningMessageId) {
		this.warningMessageId = warningMessageId;
	}

}