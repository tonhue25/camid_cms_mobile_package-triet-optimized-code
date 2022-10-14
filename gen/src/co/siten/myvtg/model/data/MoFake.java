package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MO_FAKE database table.
 * 
 */
@Entity
@Table(name="MO_FAKE")
@NamedQuery(name="MoFake.findAll", query="SELECT m FROM MoFake m")
public class MoFake implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTION_TYPE")
	private BigDecimal actionType;

	private String bizid;

	private String channel;

	@Column(name="\"COMMAND\"")
	private String command;

	@Column(name="\"INPUT\"")
	private String input;

	private String param;

	private String syntax;

	public MoFake() {
	}

	public BigDecimal getActionType() {
		return this.actionType;
	}

	public void setActionType(BigDecimal actionType) {
		this.actionType = actionType;
	}

	public String getBizid() {
		return this.bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
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

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

}