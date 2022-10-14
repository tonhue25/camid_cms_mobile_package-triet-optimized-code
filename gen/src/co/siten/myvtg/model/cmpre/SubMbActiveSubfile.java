package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SUB_MB_ACTIVE_SUBFILE database table.
 * 
 */
@Entity
@Table(name="SUB_MB_ACTIVE_SUBFILE")
@NamedQuery(name="SubMbActiveSubfile.findAll", query="SELECT s FROM SubMbActiveSubfile s")
public class SubMbActiveSubfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="BONUS_STATUS")
	private BigDecimal bonusStatus;

	private String channel;

	@Column(name="CREATE_DATE")
	private Object createDate;

	private String isdn;

	@Column(name="PROCESS_DATE")
	private Object processDate;

	@Column(name="PROCESS_STATUS")
	private BigDecimal processStatus;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="USER_NAME")
	private String userName;

	public SubMbActiveSubfile() {
	}

	public BigDecimal getBonusStatus() {
		return this.bonusStatus;
	}

	public void setBonusStatus(BigDecimal bonusStatus) {
		this.bonusStatus = bonusStatus;
	}

	public String getChannel() {
		return this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Object getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Object createDate) {
		this.createDate = createDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Object getProcessDate() {
		return this.processDate;
	}

	public void setProcessDate(Object processDate) {
		this.processDate = processDate;
	}

	public BigDecimal getProcessStatus() {
		return this.processStatus;
	}

	public void setProcessStatus(BigDecimal processStatus) {
		this.processStatus = processStatus;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public BigDecimal getSubId() {
		return this.subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}