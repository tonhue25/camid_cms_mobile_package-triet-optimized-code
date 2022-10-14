package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the "STATE" database table.
 * 
 */
@Entity
@Table(name="\"STATE\"")
@NamedQuery(name="State.findAll", query="SELECT s FROM State s")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BIZ_ID")
	private BigDecimal bizId;

	private BigDecimal charset;

	private String content;

	@Column(name="CP_ID")
	private BigDecimal cpId;

	@Column(name="DEFAULT_NEXT_STATE")
	private BigDecimal defaultNextState;

	private BigDecimal encrypted;

	private BigDecimal height;

	private BigDecimal id;

	@Column(name="\"TIMEOUT\"")
	private BigDecimal timeout;

	@Column(name="\"TYPE\"")
	private BigDecimal type;

	private BigDecimal width;

	private BigDecimal x;

	private BigDecimal y;

	public State() {
	}

	public BigDecimal getBizId() {
		return this.bizId;
	}

	public void setBizId(BigDecimal bizId) {
		this.bizId = bizId;
	}

	public BigDecimal getCharset() {
		return this.charset;
	}

	public void setCharset(BigDecimal charset) {
		this.charset = charset;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getCpId() {
		return this.cpId;
	}

	public void setCpId(BigDecimal cpId) {
		this.cpId = cpId;
	}

	public BigDecimal getDefaultNextState() {
		return this.defaultNextState;
	}

	public void setDefaultNextState(BigDecimal defaultNextState) {
		this.defaultNextState = defaultNextState;
	}

	public BigDecimal getEncrypted() {
		return this.encrypted;
	}

	public void setEncrypted(BigDecimal encrypted) {
		this.encrypted = encrypted;
	}

	public BigDecimal getHeight() {
		return this.height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getTimeout() {
		return this.timeout;
	}

	public void setTimeout(BigDecimal timeout) {
		this.timeout = timeout;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getX() {
		return this.x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return this.y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

}