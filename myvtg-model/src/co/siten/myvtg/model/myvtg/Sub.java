package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the SUB database table.
 *
 */
@Entity
@NamedQuery(name = "Sub.findAll", query = "SELECT s FROM Sub s")
public class Sub implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Column(name = "AVATAR_URL")
    private String avatarUrl;
    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "DEVICE_NAME")
    private String deviceName;

    @Column(name = "SIM_TYPE")
    private Integer simType;

    private String email;

    private Integer gender;

    @Column(name = "HOBBY_ID")
    private Long hobbyId;

    private String isdn;

    @Column(name = "JOB_ID")
    private Long jobId;

    @Column(name = "\"LANGUAGE\"")
    private String language;
    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_ACTIVE_TIME")
    private Date lastActiveTime;

    @Column(name = "LAST_SYNC_TIME")
    private Date lastSyncTime;

    private String name;

    @Column(name = "OS_TYPE")
    private Integer osType;

    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Temporal(TemporalType.DATE)
    @Column(name = "REGISTER_TIME")
    private Date registerTime;

    private Integer status;

    @Column(name = "SUB_ID")
    private Long subId;

    @Column(name = "SUB_TYPE")
    private Integer subType;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "SOCIAL_NETWORK")
    private String socialNetwork;

    @Transient
    private Date ltExpireDate;

    @Lob
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLtExpireDate() {
        return ltExpireDate;
    }

    public void setLtExpireDate(Date ltExpireDate) {
        this.ltExpireDate = ltExpireDate;
    }

    public Sub() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getSimType() {
        return simType;
    }

    public void setSimType(Integer simType) {
        this.simType = simType;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getGender() {
        return gender;
    }

    public Long getHobbyId() {
        return hobbyId;
    }

    public String getIsdn() {
        return isdn;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getLanguage() {
        return language;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public Date getLastSyncTime() {
        return lastSyncTime;
    }

    public String getName() {
        return name;
    }

    public Integer getOsType() {
        return osType;
    }

    public String getProductCode() {
        return productCode;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public Integer getStatus() {
        return status;
    }

    public Long getSubId() {
        return subId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public void setLastSyncTime(Date lastSyncTime) {
        this.lastSyncTime = lastSyncTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public void copy(Sub sub) {
        avatarUrl = sub.getAvatarUrl() != null ? sub.getAvatarUrl() : avatarUrl;
        birthDate = sub.getBirthDate() != null ? sub.getBirthDate() : birthDate;
        deviceId = sub.getDeviceId() != null ? sub.getDeviceId() : deviceId;
        deviceName = sub.getDeviceName() != null ? sub.getDeviceName() : deviceName;
        simType = sub.getSimType() != null ? sub.getSimType() : simType;
        email = sub.getEmail() != null ? sub.getEmail() : email;
        gender = sub.getGender() != null ? sub.getGender() : gender;
        hobbyId = sub.getHobbyId() != null ? sub.getHobbyId() : hobbyId;
        jobId = sub.getJobId() != null ? sub.getJobId() : jobId;
        language = sub.getLanguage() != null ? sub.getLanguage() : language;
        lastActiveTime = sub.getLastActiveTime() != null ? sub.getLastActiveTime() : lastActiveTime;
        lastSyncTime = sub.getLastSyncTime() != null ? sub.getLastSyncTime() : lastSyncTime;
        name = sub.getName() != null ? sub.getName() : name;
        osType = sub.getOsType() != null ? sub.getOsType() : osType;
        productCode = sub.getProductCode() != null ? sub.getProductCode() : productCode;
        status = sub.getStatus() != null ? sub.getStatus() : status;
        subId = sub.getSubId() != null ? sub.getSubId() : subId;
        subType = sub.getSubType() != null ? sub.getSubType() : subType;
        productName = sub.getProductName() != null ? sub.getProductName() : productName;
        socialNetwork = sub.getSocialNetwork() != null ? sub.getSocialNetwork() : socialNetwork;

//        try {
//            Field[] fields = Sub.class.getDeclaredFields();
//            for (Field field : fields) {
//                Object value = field.get(sub);
//                if (value != null) {
//                    field.set(this, value);
//                }
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e2) {
//            e2.printStackTrace();
//        }
    }
}
