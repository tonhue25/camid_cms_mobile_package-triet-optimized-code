package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DB_LOGIN_LOG database table.
 * 
 */
@Entity
@Table(name="DB_LOGIN_LOG")
@NamedQuery(name="DbLoginLog.findAll", query="SELECT d FROM DbLoginLog d")
public class DbLoginLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Object datetime;

	private String ip;

	@Column(name="OS_USER")
	private String osUser;

	@Column(name="USER_NAME")
	private String userName;

	public DbLoginLog() {
	}

	public Object getDatetime() {
		return this.datetime;
	}

	public void setDatetime(Object datetime) {
		this.datetime = datetime;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOsUser() {
		return this.osUser;
	}

	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}