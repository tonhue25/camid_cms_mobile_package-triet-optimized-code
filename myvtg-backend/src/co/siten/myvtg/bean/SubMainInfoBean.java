package co.siten.myvtg.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author thomc
 *
 */
public class SubMainInfoBean {
	private String name;
	private int subType;
	private String language;
	private String avatar_url;
	private Long subId=0L;
	@JsonIgnore
	private Date lastSyncTime;
        
        private Date birthDate;

	public SubMainInfoBean(String name, int subType, String language, String avatar_url, Long subId,
			Date lastSyncTime) {
		super();
		this.name = name;
		this.subType = subType;
		this.language = language;
		this.avatar_url = avatar_url;
		this.subId = subId;
		this.lastSyncTime = lastSyncTime;
	}
        
        public SubMainInfoBean(String name, int subType, Long subId
			) {
		super();
		this.name = name;
		this.subType = subType;
		this.language = language;
		this.subId = subId;
		
	}

	public Date getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(Date lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubType() {
		return subType;
	}

	public void setSubType(int subType) {
		this.subType = subType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
        
         public SubMainInfoBean(String name, int subType, Long subId,Date birthDate
			) {
		super();
		this.name = name;
		this.subType = subType;	
                this.subId = subId;
                this.birthDate = birthDate;
	}
         
            public Date getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

}
