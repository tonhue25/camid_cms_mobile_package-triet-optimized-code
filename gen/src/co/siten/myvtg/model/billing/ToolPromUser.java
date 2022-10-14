package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the TOOL_PROM_USER database table.
 * 
 */
@Entity
@Table(name="TOOL_PROM_USER")
@NamedQuery(name="ToolPromUser.findAll", query="SELECT t FROM ToolPromUser t")
public class ToolPromUser implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;

	@Column(name="FULL_NAME")
	private String fullName;

	@Column(name="LANGUAGE_ID")
	private BigDecimal languageId;

	private String mobile;

	@Column(name="MODE_USER")
	private BigDecimal modeUser;

	@Column(name="USER_ID")
	private BigDecimal userId;

	@Column(name="USER_NAME")
	private String userName;

	public ToolPromUser() {
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

	public BigDecimal getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(BigDecimal languageId) {
		this.languageId = languageId;
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

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}