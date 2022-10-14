package co.siten.myvtg.model.data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MO_HIS database table.
 * 
 */
@Entity
@Table(name="MO_HIS")
@NamedQuery(name="MoHi.findAll", query="SELECT m FROM MoHi m")
public class MoHi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MO_HIS_ID")
	private long moHisId;

	@Column(name="ACTION_TYPE")
	private BigDecimal actionType;

	private String channel;

	@Column(name="CLUSTER_NAME")
	private String clusterName;

	@Column(name="\"COMMAND\"")
	private String command;

	@Column(name="ERR_CODE")
	private String errCode;

	@Column(name="ERR_OCS")
	private String errOcs;

	private BigDecimal fee;

	private String msisdn;

	@Column(name="NODE_NAME")
	private String nodeName;

	private String param;

	@Temporal(TemporalType.DATE)
	@Column(name="PROCESS_TIME")
	private Date processTime;

	@Column(name="PRODUCT_CODE")
	private String productCode;

	@Column(name="RECEIVE_MSISDN")
	private String receiveMsisdn;

	@Temporal(TemporalType.DATE)
	@Column(name="RECEIVE_TIME")
	private Date receiveTime;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="SUB_TYPE")
	private BigDecimal subType;

	public MoHi() {
	}

	public long getMoHisId() {
		return this.moHisId;
	}

	public void setMoHisId(long moHisId) {
		this.moHisId = moHisId;
	}

	public BigDecimal getActionType() {
		return this.actionType;
	}

	public void setActionType(BigDecimal actionType) {
		this.actionType = actionType;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getClusterName() {
		return this.clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrOcs() {
		return this.errOcs;
	}

	public void setErrOcs(String errOcs) {
		this.errOcs = errOcs;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getParam() {
		return this.param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Date getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getReceiveMsisdn() {
		return this.receiveMsisdn;
	}

	public void setReceiveMsisdn(String receiveMsisdn) {
		this.receiveMsisdn = receiveMsisdn;
	}

	public Date getReceiveTime() {
		return this.receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public BigDecimal getSubType() {
		return this.subType;
	}

	public void setSubType(BigDecimal subType) {
		this.subType = subType;
	}

}