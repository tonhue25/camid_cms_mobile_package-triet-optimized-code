package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "COMMAND" database table.
 * 
 */
@Entity
@Table(name="\"COMMAND\"")
@NamedQuery(name="Command.findAll", query="SELECT c FROM Command c")
public class Command implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COMMAND_ID")
	private long commandId;

	private String channel;

	@Column(name="COMMAND_CODE")
	private String commandCode;

	@Column(name="PARAM_SEPARATE")
	private String paramSeparate;

	public Command() {
	}

	public long getCommandId() {
		return this.commandId;
	}

	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCommandCode() {
		return this.commandCode;
	}

	public void setCommandCode(String commandCode) {
		this.commandCode = commandCode;
	}

	public String getParamSeparate() {
		return this.paramSeparate;
	}

	public void setParamSeparate(String paramSeparate) {
		this.paramSeparate = paramSeparate;
	}

}