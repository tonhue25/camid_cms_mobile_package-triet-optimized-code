package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the CUST_REQUEST database table.
 * 
 */
@Entity
@Table(name="CUST_REQUEST")
@NamedQuery(name="CustRequest.findAll", query="SELECT c FROM CustRequest c")
public class CustRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="CHANEL_ID")
	private BigDecimal chanelId;

	@Column(name="CUST_ID")
	private BigDecimal custId;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	@Column(name="FINISH_DATE")
	private Object finishDate;

	private String reason;

	@Column(name="REQ_DATE")
	private Object reqDate;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	private BigDecimal status;

	public CustRequest() {
	}

	public BigDecimal getChanelId() {
		return this.chanelId;
	}

	public void setChanelId(BigDecimal chanelId) {
		this.chanelId = chanelId;
	}

	public BigDecimal getCustId() {
		return this.custId;
	}

	public void setCustId(BigDecimal custId) {
		this.custId = custId;
	}

	public BigDecimal getCustReqId() {
		return this.custReqId;
	}

	public void setCustReqId(BigDecimal custReqId) {
		this.custReqId = custReqId;
	}

	public Object getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Object finishDate) {
		this.finishDate = finishDate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Object getReqDate() {
		return this.reqDate;
	}

	public void setReqDate(Object reqDate) {
		this.reqDate = reqDate;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}