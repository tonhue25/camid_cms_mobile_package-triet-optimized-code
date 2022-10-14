/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.apigw;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ @NamedQuery(name = "AgUser.findAll", query = "select o from AgUser o") })
@Table(name = "AG_USER")
public class AgUser implements Serializable {
	private static final long serialVersionUID = 1L;

	public AgUser() {
	}
//
//	@OneToMany(mappedBy = "agUser", fetch = FetchType.LAZY)
//	private List<AgUserApp> agUserAppByAgUser;
//	@OneToMany(mappedBy = "agUser", fetch = FetchType.LAZY)
//	private List<AgUserRole> agUserRoleByAgUser;
	@Column(name = "REQUIRE_CHANGE", nullable = true)
	private Long requireChange;
	@Column(name = "EMAIL", length = 800, nullable = true)
	private String email;
	@Column(name = "PHONE", length = 200, nullable = true)
	private String phone;
	@Column(name = "CHANGED_TIME", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date changedTime;
	@Column(name = "REGISTERED_DATE", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date registeredDate;
	@Column(name = "STATUS", nullable = true)
	private Long status;
	@Id
	@Column(nullable = false)
	private String id;

//	@ManyToOne
//	@JoinColumn(name = "AG_DOMAIN_ID")
//	private AgDomain agDomain;

	@Column(name = "USER_NAME", length = 800, nullable = false)
	private String userName;
	@Column(name = "PASSWORD", length = 2000, nullable = false)
	private String password;
	@Column(name = "SALT_VALUE", length = 200, nullable = true)
	private String saltValue;
        @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_TIME", nullable = true)
        private Date lastLoginTime;
        
	@Column(name = "CLIENT_OS", nullable = true)
	private Long clientOS;
        

//	public List<AgUserApp> getAgUserAppByAgUser() {
//		return agUserAppByAgUser;
//	}
//
//	public void setAgUserAppByAgUser(List<AgUserApp> agUserApp) {
//		this.agUserAppByAgUser = agUserApp;
//	}
//
//	public AgUserApp addAgUserAppByAgUser(AgUserApp agUserApp) {
//		getAgUserAppByAgUser().add(agUserApp);
//		agUserApp.setAgUser(this);
//		return agUserApp;
//	}
//
//	public AgUserApp removeAgUserAppByAgUser(AgUserApp agUserApp) {
//		getAgUserAppByAgUser().remove(agUserApp);
//		agUserApp.setAgUser(null);
//		return agUserApp;
//	}
//
//	public List<AgUserRole> getAgUserRoleByAgUser() {
//		return agUserRoleByAgUser;
//	}
//
//	public void setAgUserRoleByAgUser(List<AgUserRole> agUserRole) {
//		this.agUserRoleByAgUser = agUserRole;
//	}
//
//	public AgUserRole addAgUserRoleByAgUser(AgUserRole agUserRole) {
//		getAgUserRoleByAgUser().add(agUserRole);
//		agUserRole.setAgUser(this);
//		return agUserRole;
//	}
//
//	public AgUserRole removeAgUserRoleByAgUser(AgUserRole agUserRole) {
//		getAgUserRoleByAgUser().remove(agUserRole);
//		agUserRole.setAgUser(null);
//		return agUserRole;
//	}

	public Long getRequireChange() {
		return requireChange;
	}

	public void setRequireChange(Long requireChange) {
		this.requireChange = requireChange;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getChangedTime() {
		return changedTime;
	}

	public void setChangedTime(Date changedTime) {
		this.changedTime = changedTime;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public AgDomain getAgDomain() {
//		return agDomain;
//	}
//
//	public void setAgDomain(AgDomain agDomain) {
//		this.agDomain = agDomain;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSaltValue() {
		return saltValue;
	}

	public void setSaltValue(String saltValue) {
		this.saltValue = saltValue;
	}

        public Date getLastLoginTime() {
            return lastLoginTime;
        }

        public void setLastLoginTime(Date lastLoginTime) {
            this.lastLoginTime = lastLoginTime;
        }

        public Long getClientOS() {
            return clientOS;
        }

        public void setClientOS(Long clientOS) {
            this.clientOS = clientOS;
        }

}

