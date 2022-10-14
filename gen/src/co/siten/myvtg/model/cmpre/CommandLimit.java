package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the COMMAND_LIMIT database table.
 * 
 */
@Entity
@Table(name="COMMAND_LIMIT")
@NamedQuery(name="CommandLimit.findAll", query="SELECT c FROM CommandLimit c")
public class CommandLimit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"COMMAND\"")
	private String command;

	private String description;

	public CommandLimit() {
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}