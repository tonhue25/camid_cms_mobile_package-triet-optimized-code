package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ACCOUNT_HISTORY database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_HISTORY")
@NamedQuery(name="AccountHistory.findAll", query="SELECT a FROM AccountHistory a")
public class AccountHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountHistoryPK id;

	@Column(name="ACCOUNT_TYPE")
	private String accountType;

	private BigDecimal amount;

	@Column(name="\"BLOCK\"")
	private BigDecimal block;

	private String description;

	private BigDecimal duration;

	@Column(name="EXPIRE_DATE")
	private Object expireDate;

	private String isdn;

	@Column(name="REASON_ID")
	private BigDecimal reasonId;

	@Column(name="SHOP_CODE")
	private String shopCode;

	@Column(name="USER_NAME")
	private String userName;

	public AccountHistory() {
	}

	public AccountHistoryPK getId() {
		return this.id;
	}

	public void setId(AccountHistoryPK id) {
		this.id = id;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBlock() {
		return this.block;
	}

	public void setBlock(BigDecimal block) {
		this.block = block;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDuration() {
		return this.duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public Object getExpireDate() {
		return this.expireDate;
	}

	public void setExpireDate(Object expireDate) {
		this.expireDate = expireDate;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public BigDecimal getReasonId() {
		return this.reasonId;
	}

	public void setReasonId(BigDecimal reasonId) {
		this.reasonId = reasonId;
	}

	public String getShopCode() {
		return this.shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}