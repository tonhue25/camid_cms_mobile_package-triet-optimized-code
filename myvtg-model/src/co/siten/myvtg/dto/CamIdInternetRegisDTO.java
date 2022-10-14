package co.siten.myvtg.dto;

public class CamIdInternetRegisDTO {
    private String address;
    private String mail;
    private String name;
    private String phone;
    private String ip;
    private String packageDetail;
    private String provinceCode;
    // 10 or  12 Mpm
    private String speed;
    // basic , business or family
    private String type;
    private String isAutoAssign;
    private String introductionCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPackageDetail() {
        return packageDetail;
    }

    public void setPackageDetail(String packageDetail) {
        this.packageDetail = packageDetail;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsAutoAssign() {
        return isAutoAssign;
    }

    public void setIsAutoAssign(String isAutoAssign) {
        this.isAutoAssign = isAutoAssign;
    }

    public String getIntroductionCode() {
        return introductionCode;
    }

    public void setIntroductionCode(String introductionCode) {
        this.introductionCode = introductionCode;
    }
}
