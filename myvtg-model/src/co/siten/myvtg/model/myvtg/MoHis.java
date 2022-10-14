package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the MO_HIS database table.
 * 
 */
@Entity
@Table(name = "MO_HIS")
@NamedQuery(name = "MoHis.findAll", query = "SELECT m FROM MoHis m")
public class MoHis implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer channel;

	@Column(name = "CHANNEL_TYPE")
	private Integer channelType;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	private String isdn;

	@Column(name = "PRE_STATE")
	private Integer preState;
	@Temporal(TemporalType.DATE)
	@Column(name = "PROCESS_TIME")
	private Date processTime;

	@Column(name = "\"STATE\"")
	private Integer state;

	@Column(name = "SUBSERVIVE_CODE")
	private String subserviveCode;

	private String syntax;

	public MoHis() {
	}

	public MoHis(FakeMo fm) {
		this.channel = fm.getChannel();
		this.channelType = fm.getChannelType();
		this.isdn = fm.getIsdn();
		this.preState = fm.getPreState();
		this.state = fm.getStatus();
		this.subserviveCode = fm.getSubserviveCode();
		this.syntax = fm.getSyntax();
		this.processTime= new Date();
	}

	public Integer getChannel() {
		return channel;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public String getId() {
		return id;
	}

	public String getIsdn() {
		return isdn;
	}

	public Integer getPreState() {
		return preState;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public Integer getState() {
		return state;
	}

	public String getSubserviveCode() {
		return subserviveCode;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public void setPreState(Integer preState) {
		this.preState = preState;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public void setSubserviveCode(String subserviveCode) {
		this.subserviveCode = subserviveCode;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

}