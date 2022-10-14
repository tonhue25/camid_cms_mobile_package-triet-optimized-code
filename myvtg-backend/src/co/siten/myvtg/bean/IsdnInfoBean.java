/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;

/**
 *
 * @author buiquangdai
 */
@JsonInclude(Include.NON_NULL)
public class IsdnInfoBean {

    private String isdn;
    private String price;
    private String status;
    private Long type;
    private Long typeIsdn;
    private String orderedDate;
    private String expiredDate;
    private Double basic;
    private Double exchange;
    private Double freeMinute;
    private String data;
    private Double moneyUse;
    private String date;
    private Double monthlyFee;
    private Double registerFee;
    private String product;
    //API-Lucky
    private String sex;
    private Long loyalPoint;
    private String language;
    private String linkShare;
    private String luckyId;
    private Boolean isLucky;
    private String qrCode;
    private String value;
    private String fullName;
    private String avatar;
    private String code;
    private Long freePlayGame;

    //donate-covid
    private String amount;
    private String currency;
    private String sn;

    //info
    private String actStatus;
    private String serial;
    private Long subId;
    private Date endDatetime;
    private Date changeDatetime;
    private String productCode;
    private Date staDatetime;
    List<ProvisionBalanceBean> lstExchange;
    public IsdnInfoBean() {
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getTypeIsdn() {
        return typeIsdn;
    }

    public void setTypeIsdn(Long typeIsdn) {
        this.typeIsdn = typeIsdn;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getExchange() {
        return exchange;
    }

    public void setExchange(Double exchange) {
        this.exchange = exchange;
    }

    public Double getFreeMinute() {
        return freeMinute;
    }

    public void setFreeMinute(Double freeMinute) {
        this.freeMinute = freeMinute;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public IsdnInfoBean(String isdn, String price, String orderedDate, String expiredDate) {
        this.isdn = isdn;
        this.price = price;
        this.orderedDate = orderedDate;
        this.expiredDate = expiredDate;
    }

    public IsdnInfoBean(String isdn, String price, String status, Long type, Long typeIsdn) {
        this.isdn = isdn;
        this.price = price;
        this.status = status;
        this.type = type;
        this.typeIsdn = typeIsdn;
    }

    public IsdnInfoBean(Double basic, Double exchange, Double freeMinute, String data) {
        this.basic = basic;
        this.exchange = exchange;
        this.freeMinute = freeMinute;
        this.data = data;
    }

    public Double getMoneyUse() {
        return moneyUse;
    }

    public void setMoneyUse(Double moneyUse) {
        this.moneyUse = moneyUse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getLoyalPoint() {
        return loyalPoint;
    }

    public void setLoyalPoint(Long loyalPoint) {
        this.loyalPoint = loyalPoint;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLinkShare() {
        return linkShare;
    }

    public void setLinkShare(String linkShare) {
        this.linkShare = linkShare;
    }

    public String getLuckyId() {
        return luckyId;
    }

    public void setLuckyId(String luckyId) {
        this.luckyId = luckyId;
    }

    public Boolean getIsLucky() {
        return isLucky;
    }

    public void setIsLucky(Boolean isLucky) {
        this.isLucky = isLucky;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getFreePlayGame() {
        return freePlayGame;
    }

    public void setFreePlayGame(Long freePlayGame) {
        this.freePlayGame = freePlayGame;
    }

    public Double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(Double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public Double getRegisterFee() {
        return registerFee;
    }

    public void setRegisterFee(Double registerFee) {
        this.registerFee = registerFee;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public Date getChangeDatetime() {
        return changeDatetime;
    }

    public void setChangeDatetime(Date changeDatetime) {
        this.changeDatetime = changeDatetime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getStaDatetime() {
        return staDatetime;
    }

    public void setStaDatetime(Date staDatetime) {
        this.staDatetime = staDatetime;
    }

    public List<ProvisionBalanceBean> getLstExchange() {
        return lstExchange;
    }

    public void setLstExchange(List<ProvisionBalanceBean> lstExchange) {
        this.lstExchange = lstExchange;
    }

}
