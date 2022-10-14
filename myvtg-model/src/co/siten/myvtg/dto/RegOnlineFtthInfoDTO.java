package co.siten.myvtg.dto;


import java.util.List;

public class RegOnlineFtthInfoDTO {
    private String address;
    private String customerMail;
    private String customerName;
    private String customerPhone;
    private String id;
    private String province;
    private String speed;
    private String status;
    private String color;
    private String createDateStr;
    private String statusDes;
    private String monthFee;
    private String payAdvance;
    private String instalation;
    private String deposit;
    private String modemWifi;
    private String reason;
    private String assignShop;
    private String assignStaff;
    private String code; // instruction code
    private String overtime;
    private String account;
    private List<RegOnlineHisDTO> regOnlineHis;

    public RegOnlineFtthInfoDTO() {
    }

    public List<RegOnlineHisDTO> getRegOnlineHis() {
        return regOnlineHis;
    }

    public void setRegOnlineHis(List<RegOnlineHisDTO> regOnlineHis) {
        this.regOnlineHis = regOnlineHis;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account.substring(0,account.length() - 4 ) + "****";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCreateDateStr() {
        return createDateStr;
    }

    public void setCreateDateStr(String createDateStr) {
        this.createDateStr = createDateStr;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public String getMonthFee() {
        return monthFee;
    }

    public void setMonthFee(String monthFee) {
        this.monthFee = monthFee;
    }

    public String getPayAdvance() {
        return payAdvance;
    }

    public void setPayAdvance(String payAdvance) {
        this.payAdvance = payAdvance;
    }

    public String getInstalation() {
        return instalation;
    }

    public void setInstalation(String instalation) {
        this.instalation = instalation;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getModemWifi() {
        return modemWifi;
    }

    public void setModemWifi(String modemWifi) {
        this.modemWifi = modemWifi;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAssignShop() {
        return assignShop;
    }

    public void setAssignShop(String assignShop) {
        this.assignShop = assignShop;
    }

    public String getAssignStaff() {
        return assignStaff;
    }

    public void setAssignStaff(String assignStaff) {
        this.assignStaff = assignStaff;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }
}
