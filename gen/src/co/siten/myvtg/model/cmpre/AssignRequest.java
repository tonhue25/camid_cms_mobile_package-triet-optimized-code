package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ASSIGN_REQUEST database table.
 * 
 */
@Entity
@Table(name="ASSIGN_REQUEST")
@NamedQuery(name="AssignRequest.findAll", query="SELECT a FROM AssignRequest a")
public class AssignRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ASSIGN_REQ_ID")
	private BigDecimal assignReqId;

	@Column(name="CREATE_DATE")
	private Object createDate;

	@Column(name="CUST_REQ_ID")
	private BigDecimal custReqId;

	@Column(name="END_DATETIME")
	private Object endDatetime;

	@Column(name="OLD_ASSIGN_REQ_ID")
	private BigDecimal oldAssignReqId;

	@Column(name="SHOP_ID")
	private BigDecimal shopId;

	@Column(name="STA_DATETIME")
	private Object staDatetime;

	private BigDecimal status;

	@Column(name="USER_CREATE")
	private String userCreate;

	public AssignRequest() {
	}

	public BigDecimal getAssignReqId() {
		return this.assignReqId;
	}

	public void setAssignReqId(BigDecimal assignReqId) {
		this.assignReqId = assignReqId;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public BigDecimal getCustReqId() {
		return this.custReqId;
	}

	public void setCustReqId(BigDecimal custReqId) {
		this.custReqId = custReqId;
	}

	public Object getEndDatetime() {
		return this.endDatetime;
	}

	public void setEndDatetime(Object endDatetime) {
		this.endDatetime = endDatetime;
	}

	public BigDecimal getOldAssignReqId() {
		return this.oldAssignReqId;
	}

	public void setOldAssignReqId(BigDecimal oldAssignReqId) {
		this.oldAssignReqId = oldAssignReqId;
	}

	public BigDecimal getShopId() {
		return this.shopId;
	}

	public void setShopId(BigDecimal shopId) {
		this.shopId = shopId;
	}

	public Object getStaDatetime() {
		return this.staDatetime;
	}

	public void setStaDatetime(Object staDatetime) {
		this.staDatetime = staDatetime;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

}