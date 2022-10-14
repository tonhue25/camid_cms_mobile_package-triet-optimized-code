package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the KS_USER database table.
 * 
 */
@Entity
@Table(name="KS_USER")
@NamedQuery(name="KsUser.findAll", query="SELECT k FROM KsUser k")
public class KsUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;

	@Column(name="FULL_NAME")
	private String fullName;

	@Column(name="KS_USER_ID")
	private BigDecimal ksUserId;

	private String mobile;

	@Column(name="MODE_USER")
	private BigDecimal modeUser;

	@Column(name="USER_NAME")
	private String userName;

	public KsUser() {
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public BigDecimal getKsUserId() {
		return this.ksUserId;
	}

	public void setKsUserId(BigDecimal ksUserId) {
		this.ksUserId = ksUserId;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getModeUser() {
		return this.modeUser;
	}

	public void setModeUser(BigDecimal modeUser) {
		this.modeUser = modeUser;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}