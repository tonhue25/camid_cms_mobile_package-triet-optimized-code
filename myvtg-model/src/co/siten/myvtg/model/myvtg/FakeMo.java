package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
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
 * The persistent class for the FAKE_MO database table.
 * 
 */
@Entity
@Table(name = "FAKE_MO")
@NamedQuery(name = "FakeMo.findAll", query = "SELECT f FROM FakeMo f")
public class FakeMo implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)")
	@Id
	private String id;

	private Integer channel;

	@Column(name = "CHANNEL_TYPE")
	private Integer channelType;
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	private String isdn;

	@Column(name = "PRE_STATE")
	private Integer preState;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "SUBSERVIVE_CODE")
	private String subserviveCode;

	@Column(name = "PROCESS_TIME")
	private Long processTime;
	
	@Column(name = "WS_ID")
	private String wsId;

	private String syntax;

	public FakeMo() {
	}
	
	

	public String getWsId() {
		return wsId;
	}



	public void setWsId(String wsId) {
		this.wsId = wsId;
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Long processTime) {
		this.processTime = processTime;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getChannel() {
		return channel;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getIsdn() {
		return isdn;
	}

	public Integer getPreState() {
		return preState;
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

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public void setPreState(Integer preState) {
		this.preState = preState;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public String getSubserviveCode() {
		return subserviveCode;
	}

	public void setSubserviveCode(String subserviveCode) {
		this.subserviveCode = subserviveCode;
	}

}