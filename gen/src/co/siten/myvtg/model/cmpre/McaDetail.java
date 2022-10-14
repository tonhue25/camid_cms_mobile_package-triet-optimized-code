package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the MCA_DETAIL database table.
 * 
 */
@Entity
@Table(name="MCA_DETAIL")
@NamedQuery(name="McaDetail.findAll", query="SELECT m FROM McaDetail m")
public class McaDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private String called;

	private String calling;

	private String content;

	private BigDecimal id;

	@Column(name="SEND_TIME")
	private Object sendTime;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	public McaDetail() {
	}

	public String getCalled() {
		return this.called;
	}

	public void setCalled(String called) {
		this.called = called;
	}

	public String getCalling() {
		return this.calling;
	}

	public void setCalling(String calling) {
		this.calling = calling;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Object getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Object sendTime) {
		this.sendTime = sendTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}