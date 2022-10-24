package co.siten.myvtg.model.apigw;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "ACCOUNT")
@NamedQuery(name = "ACCOUNT.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private Long id;  //NUMBER(19,0)

    @Column(name = "CREATED_TIME")
    private Calendar createdTime; //TIMESTAMP(6)

    @Column(name = "STATE")
    private String state;   //VARCHAR2(255 CHAR)

    @Column(name = "UPDATED_TIME")
    private Calendar updatedTime; //TIMESTAMP(6)

    @Column(name = "ACCOUNT_ID")
    private String accountId;   //VARCHAR2(255 CHAR)

    @Column(name = "ADDRESS")
    private String address; //VARCHAR2(255 CHAR)

    @Column(name = "APPLEID")
    private String appleid; //VARCHAR2(255 CHAR)

    @Column(name = "AVATAR")
    private String avatar;  //VARCHAR2(4000 CHAR)

    @Column(name = "CARRIER")
    private String carrier; //VARCHAR2(255 CHAR)

    @Column(name = "CUST_ID")
    private String custId;  //VARCHAR2(255 CHAR)

    @Column(name = "DOB")
    private Calendar dob; //TIMESTAMP(6)

    @Column(name = "DISTRICT")
    private String district;    //VARCHAR2(255 CHAR)

    @Column(name = "EMAIL")
    private String email;   //VARCHAR2(255 CHAR)

    @Column(name = "FID")
    private String fid; //VARCHAR2(255 CHAR)

    @Column(name = "FIRST_NAME")
    private String firstName;   //VARCHAR2(255 CHAR)

    @Column(name = "GENDER")
    private Long gender;  //NUMBER(10,0)

    @Column(name = "GID")
    private String gid; //VARCHAR2(255 CHAR)

    @Column(name = "ID_NUMBER")
    private String idNumber;    //VARCHAR2(255 CHAR)

    @Column(name = "LAST_NAME")
    private String lastName;    //VARCHAR2(255 CHAR)

    @Column(name = "LOYALTY_ID")
    private String loyaltyId;   //VARCHAR2(255 CHAR)

    @Column(name = "NAME")
    private String name;    //VARCHAR2(255 CHAR)

    @Column(name = "NUMBER_CREATE_KYC_REQUEST")
    private Long numberCreateKycRequest;  //NUMBER(10,0)

    @Column(name = "PASSWORD")
    private String password;    //VARCHAR2(255 CHAR)

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber; //VARCHAR2(255 CHAR)

    @Column(name = "PROVINCE")
    private String province;    //VARCHAR2(255 CHAR)

    @Column(name = "SUB_ID")
    private String subId;   //VARCHAR2(255 CHAR)

    @Column(name = "USERNAME")
    private String username;    //VARCHAR2(255 CHAR)

    @Column(name = "VERIFIED")
    private String verified;    //VARCHAR2(255 CHAR)

    @Lob
    @Column(name = "IMG_AVATAR", columnDefinition = "BLOB")
    private Byte[] imgAvatar;   //BLOB

    @Column(name = "HASH")
    private String hash;    //VARCHAR2(255 CHAR)

    @Column(name = "CODE")
    private String code;    //VARCHAR2(255 CHAR)

    @Column(name = "INVITED_CODE")
    private String invitedCode; //VARCHAR2(255 CHAR)

    @Column(name = "INVITE_STATUS")
    private Long inviteStatus;    //NUMBER(10,0)

    @Column(name = "SIGNUP_METHOD")
    private String signupMethod;    //VARCHAR2(255 CHAR)

    @Column(name = "INVITED_CODE_TIME")
    private Calendar invitedCodeTime; //TIMESTAMP(6)

    @Column(name = "CONTACT")
    private String contact; //VARCHAR2(255 BYTE)

    @Column(name = "ACCOUNT_NAME")
    private String accountName; //VARCHAR2(255 BYTE)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Calendar createdTime) {
        this.createdTime = createdTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Calendar getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Calendar updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAppleid() {
        return appleid;
    }

    public void setAppleid(String appleid) {
        this.appleid = appleid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Calendar getDob() {
        return dob;
    }

    public void setDob(Calendar dob) {
        this.dob = dob;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoyaltyId() {
        return loyaltyId;
    }

    public void setLoyaltyId(String loyaltyId) {
        this.loyaltyId = loyaltyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberCreateKycRequest() {
        return numberCreateKycRequest;
    }

    public void setNumberCreateKycRequest(Long numberCreateKycRequest) {
        this.numberCreateKycRequest = numberCreateKycRequest;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public Long getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(Long inviteStatus) {
        this.inviteStatus = inviteStatus;
    }

    public String getSignupMethod() {
        return signupMethod;
    }

    public void setSignupMethod(String signupMethod) {
        this.signupMethod = signupMethod;
    }

    public Calendar getInvitedCodeTime() {
        return invitedCodeTime;
    }

    public Byte[] getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(Byte[] imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public void setInvitedCodeTime(Calendar invitedCodeTime) {
        this.invitedCodeTime = invitedCodeTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
