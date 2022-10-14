package co.siten.myvtg.bean;

import java.math.BigDecimal;
import java.util.Date;

public class CurrentUsedServiceDetailBean {
    private String name;
    private String code;
    private String shortDes;
    private String fullDes;
    private String imgDesUrl;
    private BigDecimal price;
    private String unit;
    private String validity;
    private Integer serviceType;
    private Date expired;
    private String dataRemaining;
    private String smsRemaining;
    private String moneyRemaining;
    private String voiceRemaining;

    public CurrentUsedServiceDetailBean(String name, String code, String shortDes, String fullDes, String imgDesUrl, BigDecimal price, String unit, String validity, Integer serviceType) {
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.fullDes = fullDes;
        this.imgDesUrl = imgDesUrl;
        this.price = price;
        this.unit = unit;
        this.validity = validity;
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getFullDes() {
        return fullDes;
    }

    public void setFullDes(String fullDes) {
        this.fullDes = fullDes;
    }

    public String getImgDesUrl() {
        return imgDesUrl;
    }

    public void setImgDesUrl(String imgDesUrl) {
        this.imgDesUrl = imgDesUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public String getDataRemaining() {
        return dataRemaining;
    }

    public void setDataRemaining(String dataRemaining) {
        this.dataRemaining = dataRemaining;
    }

    public String getSmsRemaining() {
        return smsRemaining;
    }

    public void setSmsRemaining(String smsRemaining) {
        this.smsRemaining = smsRemaining;
    }

    public String getMoneyRemaining() {
        return moneyRemaining;
    }

    public void setMoneyRemaining(String moneyRemaining) {
        this.moneyRemaining = moneyRemaining;
    }

    public String getVoiceRemaining() {
        return voiceRemaining;
    }

    public void setVoiceRemaining(String voiceRemaining) {
        this.voiceRemaining = voiceRemaining;
    }
}
