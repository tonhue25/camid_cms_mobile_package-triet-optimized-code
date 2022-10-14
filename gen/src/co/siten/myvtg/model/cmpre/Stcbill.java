package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the STCBILL database table.
 * 
 */
@Entity
@NamedQuery(name="Stcbill.findAll", query="SELECT s FROM Stcbill s")
public class Stcbill implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal id;

	private String msisdn;

	private String newstate;

	private String oldstate;

	private String operationtime;

	private String scpid;

	private BigDecimal statuspr;

	private BigDecimal updatecm;

	public Stcbill() {
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNewstate() {
		return this.newstate;
	}

	public void setNewstate(String newstate) {
		this.newstate = newstate;
	}

	public String getOldstate() {
		return this.oldstate;
	}

	public void setOldstate(String oldstate) {
		this.oldstate = oldstate;
	}

	public String getOperationtime() {
		return this.operationtime;
	}

	public void setOperationtime(String operationtime) {
		this.operationtime = operationtime;
	}

	public String getScpid() {
		return this.scpid;
	}

	public void setScpid(String scpid) {
		this.scpid = scpid;
	}

	public BigDecimal getStatuspr() {
		return this.statuspr;
	}

	public void setStatuspr(BigDecimal statuspr) {
		this.statuspr = statuspr;
	}

	public BigDecimal getUpdatecm() {
		return this.updatecm;
	}

	public void setUpdatecm(BigDecimal updatecm) {
		this.updatecm = updatecm;
	}

}