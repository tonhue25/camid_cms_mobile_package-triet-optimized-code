package co.siten.myvtg.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author thomc
 *
 */
public class SubAccountInfoBean {
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date birthday;
	private String job;
	private String hobby;
	private String email;
	@JsonIgnore
	private Date lastSyncTime;

	public SubAccountInfoBean(String name, Date birthday, String job, String hobby, String email, Date lastSynTime) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.job = job;
		this.hobby = hobby;
		this.email = email;
		this.lastSyncTime = lastSynTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public Date getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

}
