package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;

import co.siten.myvtg.config.Config;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the MO database table.
 * 
 */
@Entity
@Table(name = "MO")
@NamedQuery(name = "Mo.findAll", query = "SELECT m FROM Mo m")
public class Mo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = Config.SEQUENCE_DATA_MO, allocationSize = 1)
	@Column(name = "MO_ID")
	private long moId;

	@Column(name = "ACTION_TYPE")
	private Integer actionType;

	private String channel;

	@Column(name = "\"COMMAND\"")
	private String command;

	private String msisdn;

	private String param;

	@Temporal(TemporalType.DATE)
	@Column(name = "RECEIVE_TIME")
	private Date receiveTime;

	public Mo() {
	}

	public long getMoId() {
		return this.moId;
	}

	public void setMoId(long moId) {
		this.moId = moId;
	}

	public Integer getActionType() {
		return this.actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

}