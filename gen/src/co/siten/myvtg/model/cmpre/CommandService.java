package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the COMMAND_SERVICE database table.
 * 
 */
@Entity
@Table(name="COMMAND_SERVICE")
@NamedQuery(name="CommandService.findAll", query="SELECT c FROM CommandService c")
public class CommandService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"COMMAND\"")
	private String command;

	private BigDecimal id;

	private String isdn;

	@Column(name="\"PARAMETER\"")
	private String parameter;

	@Temporal(TemporalType.DATE)
	@Column(name="PROCESSS_DATETIME")
	private Date processsDatetime;

	@Column(name="REQUEST_ID")
	private BigDecimal requestId;

	private String response;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	@Column(name="TIME_SEND")
	private BigDecimal timeSend;

	@Column(name="\"TYPE\"")
	private String type;

	public CommandService() {
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Date getProcesssDatetime() {
		return this.processsDatetime;
	}

	public void setProcesssDatetime(Date processsDatetime) {
		this.processsDatetime = processsDatetime;
	}

	public BigDecimal getRequestId() {
		return this.requestId;
	}

	public void setRequestId(BigDecimal requestId) {
		this.requestId = requestId;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getTimeSend() {
		return this.timeSend;
	}

	public void setTimeSend(BigDecimal timeSend) {
		this.timeSend = timeSend;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}