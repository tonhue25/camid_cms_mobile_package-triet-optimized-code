package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the STATE_CHANGE database table.
 * 
 */
@Entity
@Table(name="STATE_CHANGE")
@NamedQuery(name="StateChange.findAll", query="SELECT s FROM StateChange s")
public class StateChange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"INPUT\"")
	private String input;

	private BigDecimal priority;

	private BigDecimal state1;

	private BigDecimal state2;

	public StateChange() {
	}

	public String getInput() {
		return this.input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public BigDecimal getPriority() {
		return this.priority;
	}

	public void setPriority(BigDecimal priority) {
		this.priority = priority;
	}

	public BigDecimal getState1() {
		return this.state1;
	}

	public void setState1(BigDecimal state1) {
		this.state1 = state1;
	}

	public BigDecimal getState2() {
		return this.state2;
	}

	public void setState2(BigDecimal state2) {
		this.state2 = state2;
	}

}