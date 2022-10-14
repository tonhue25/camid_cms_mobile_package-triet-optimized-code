package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BANK database table.
 * 
 */
@Entity
@NamedQuery(name="Bank.findAll", query="SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACCOUNT_NO")
	private String accountNo;

	@Column(name="ACCOUNT_USD_NO")
	private String accountUsdNo;

	private String address;

	private String name;

	@Column(name="NAME_ENGLISH")
	private String nameEnglish;

	private String province;

	@Column(name="PROVINCE_TEL")
	private String provinceTel;

	public Bank() {
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountUsdNo() {
		return this.accountUsdNo;
	}

	public void setAccountUsdNo(String accountUsdNo) {
		this.accountUsdNo = accountUsdNo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameEnglish() {
		return this.nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceTel() {
		return this.provinceTel;
	}

	public void setProvinceTel(String provinceTel) {
		this.provinceTel = provinceTel;
	}

}