package co.siten.myvtg.dto;

import java.math.BigInteger;


public class UserInfo {
    private BigInteger userId;
    private int userRight;
    private String username;
    private String password;
    private int status;
    private String email;
    private String cellPhone;
    private int gender;
    private String identityCard;
    private String fullName;
    private String userTypeId;
    private String createDate;
    private int managerId;
    private int passWordChange;
    private String profileId;
    private String lastResetPassword;
    private String ip;
    private int deptId;
    private String deptLevel;
    private int postId;
    private String deptName;
    private int ignoreCheckIp;
    private int checkValidTime;
    private String startTimeToChangePassword;
    private String ipLan;
    private int checkIp;
    private int checkIpLan;
    private int useSalt;
    private int loginFailAllow;
    private int temporaryLockTime;
    private int maxTmpLockADay;
    private int passwordValidTime;
    private int userValidTime;
    private int allowMultiIpLogin;
    private int allowLoginTimeStart;
    private int allowLoginTimeEnd;
    private int id;
    private String name;
    private int needChangePassword;
    private int timeToChangePassword;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userRight=" + userRight +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", gender=" + gender +
                ", identityCard='" + identityCard + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userTypeId='" + userTypeId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", managerId=" + managerId +
                ", passWordChange=" + passWordChange +
                ", profileId='" + profileId + '\'' +
                ", lastResetPassword='" + lastResetPassword + '\'' +
                ", ip='" + ip + '\'' +
                ", deptId=" + deptId +
                ", deptLevel='" + deptLevel + '\'' +
                ", postId=" + postId +
                ", deptName='" + deptName + '\'' +
                ", ignoreCheckIp=" + ignoreCheckIp +
                ", checkValidTime=" + checkValidTime +
                ", startTimeToChangePassword='" + startTimeToChangePassword + '\'' +
                ", ipLan='" + ipLan + '\'' +
                ", checkIp=" + checkIp +
                ", checkIpLan=" + checkIpLan +
                ", useSalt=" + useSalt +
                ", loginFailAllow=" + loginFailAllow +
                ", temporaryLockTime=" + temporaryLockTime +
                ", maxTmpLockADay=" + maxTmpLockADay +
                ", passwordValidTime=" + passwordValidTime +
                ", userValidTime=" + userValidTime +
                ", allowMultiIpLogin=" + allowMultiIpLogin +
                ", allowLoginTimeStart=" + allowLoginTimeStart +
                ", allowLoginTimeEnd=" + allowLoginTimeEnd +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", needChangePassword=" + needChangePassword +
                ", timeToChangePassword=" + timeToChangePassword +
                '}';
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public int getUserRight() {
        return userRight;
    }

    public void setUserRight(int userRight) {
        this.userRight = userRight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getPassWordChange() {
        return passWordChange;
    }

    public void setPassWordChange(int passWordChange) {
        this.passWordChange = passWordChange;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getLastResetPassword() {
        return lastResetPassword;
    }

    public void setLastResetPassword(String lastResetPassword) {
        this.lastResetPassword = lastResetPassword;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
        this.deptLevel = deptLevel;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getIgnoreCheckIp() {
        return ignoreCheckIp;
    }

    public void setIgnoreCheckIp(int ignoreCheckIp) {
        this.ignoreCheckIp = ignoreCheckIp;
    }

    public int getCheckValidTime() {
        return checkValidTime;
    }

    public void setCheckValidTime(int checkValidTime) {
        this.checkValidTime = checkValidTime;
    }

    public String getStartTimeToChangePassword() {
        return startTimeToChangePassword;
    }

    public void setStartTimeToChangePassword(String startTimeToChangePassword) {
        this.startTimeToChangePassword = startTimeToChangePassword;
    }

    public String getIpLan() {
        return ipLan;
    }

    public void setIpLan(String ipLan) {
        this.ipLan = ipLan;
    }

    public int getCheckIp() {
        return checkIp;
    }

    public void setCheckIp(int checkIp) {
        this.checkIp = checkIp;
    }

    public int getCheckIpLan() {
        return checkIpLan;
    }

    public void setCheckIpLan(int checkIpLan) {
        this.checkIpLan = checkIpLan;
    }

    public int getUseSalt() {
        return useSalt;
    }

    public void setUseSalt(int useSalt) {
        this.useSalt = useSalt;
    }

    public int getLoginFailAllow() {
        return loginFailAllow;
    }

    public void setLoginFailAllow(int loginFailAllow) {
        this.loginFailAllow = loginFailAllow;
    }

    public int getTemporaryLockTime() {
        return temporaryLockTime;
    }

    public void setTemporaryLockTime(int temporaryLockTime) {
        this.temporaryLockTime = temporaryLockTime;
    }

    public int getMaxTmpLockADay() {
        return maxTmpLockADay;
    }

    public void setMaxTmpLockADay(int maxTmpLockADay) {
        this.maxTmpLockADay = maxTmpLockADay;
    }

    public int getPasswordValidTime() {
        return passwordValidTime;
    }

    public void setPasswordValidTime(int passwordValidTime) {
        this.passwordValidTime = passwordValidTime;
    }

    public int getUserValidTime() {
        return userValidTime;
    }

    public void setUserValidTime(int userValidTime) {
        this.userValidTime = userValidTime;
    }

    public int getAllowMultiIpLogin() {
        return allowMultiIpLogin;
    }

    public void setAllowMultiIpLogin(int allowMultiIpLogin) {
        this.allowMultiIpLogin = allowMultiIpLogin;
    }

    public int getAllowLoginTimeStart() {
        return allowLoginTimeStart;
    }

    public void setAllowLoginTimeStart(int allowLoginTimeStart) {
        this.allowLoginTimeStart = allowLoginTimeStart;
    }

    public int getAllowLoginTimeEnd() {
        return allowLoginTimeEnd;
    }

    public void setAllowLoginTimeEnd(int allowLoginTimeEnd) {
        this.allowLoginTimeEnd = allowLoginTimeEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeedChangePassword() {
        return needChangePassword;
    }

    public void setNeedChangePassword(int needChangePassword) {
        this.needChangePassword = needChangePassword;
    }

    public int getTimeToChangePassword() {
        return timeToChangePassword;
    }

    public void setTimeToChangePassword(int timeToChangePassword) {
        this.timeToChangePassword = timeToChangePassword;
    }
}
