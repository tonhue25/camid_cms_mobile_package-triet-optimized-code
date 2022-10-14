package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class ServiceDetailBean {

    private String name;
    private String code;
    private String shortDes;
    private String fullDes;
    private String imgDesUrl;
    private String webLink;
    private BigDecimal price;
    private String subName;
    private String subCode;
    private String subShortDes;
    private String subFullDes;
    private String subIconUrl;
    private BigDecimal subPrice;
    private String unit;
    private Integer state;
    private Integer isRegisterAble;
    private String validity;
    private Integer serviceType;
    private Integer showSubService;
    private Integer autoRenew;

    public Integer getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Integer autoRenew) {
        this.autoRenew = autoRenew;
    }

    public Integer getShowSubService() {
        return showSubService;
    }

    public void setShowSubService(Integer showSubService) {
        this.showSubService = showSubService;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
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

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubShortDes() {
        return subShortDes;
    }

    public void setSubShortDes(String subShortDes) {
        this.subShortDes = subShortDes;
    }

    public String getSubFullDes() {
        return subFullDes;
    }

    public void setSubFullDes(String subFullDes) {
        this.subFullDes = subFullDes;
    }

    public String getSubIconUrl() {
        return subIconUrl;
    }

    public void setSubIconUrl(String subIconUrl) {
        this.subIconUrl = subIconUrl;
    }

    public BigDecimal getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(BigDecimal subPrice) {
        this.subPrice = subPrice;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getIsRegisterAble() {
        return isRegisterAble;
    }

    public void setIsRegisterAble(Integer isRegisterAble) {
        this.isRegisterAble = isRegisterAble;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public ServiceDetailBean(String name, String code, String fullDes, String imgDesUrl, String webLink,
                             BigDecimal price, Integer isRegisable, String subName, String subCode, String subShortDes, String subFullDes, String subIconUrl,
                             BigDecimal subPrice, String unit, Integer state) {
        super();
        this.name = name;
        this.code = code;
        this.fullDes = fullDes;
        this.imgDesUrl = imgDesUrl;
        this.webLink = webLink;
        this.price = price;
        this.isRegisterAble = isRegisable;
        this.subName = subName;
        this.subCode = subCode;
        this.subShortDes = subShortDes;
        this.subFullDes = subFullDes;
        this.subIconUrl = subIconUrl;
        this.subPrice = subPrice;
        this.unit = unit;
        this.state = state;
    }

    public ServiceDetailBean(String name, String code, String shortDes, String fullDes, String imgDesUrl, String webLink,
                             BigDecimal price, Integer isRegisable, String subName, String subCode, String subShortDes, String subFullDes, String subIconUrl,
                             BigDecimal subPrice, String unit, Integer state, String validity, Integer serviceType, Integer showSubService, Integer autoRenew) {
        super();
        this.name = name;
        this.code = code;
        this.fullDes = fullDes;
        this.imgDesUrl = imgDesUrl;
        this.webLink = webLink;
        this.price = price;
        this.isRegisterAble = isRegisable;
        this.subName = subName;
        this.subCode = subCode;
        this.subShortDes = subShortDes;
        this.subFullDes = subFullDes;
        this.subIconUrl = subIconUrl;
        this.subPrice = subPrice;
        this.unit = unit;
        this.state = state;
        this.validity = validity;
        this.shortDes = shortDes;
        this.serviceType = serviceType;
        this.showSubService = showSubService;
        this.autoRenew = autoRenew;
    }

    public ServiceDetailBean(String name, String code, String shortDes, String fullDes, String imgDesUrl, String webLink,
                             BigDecimal price, Integer isRegisable, String subName, String subCode, String subShortDes, String subFullDes, String subIconUrl,
                             BigDecimal subPrice, String unit, Integer state, String validity, Integer serviceType, Integer showSubService) {
        super();
        this.name = name;
        this.code = code;
        this.fullDes = fullDes;
        this.imgDesUrl = imgDesUrl;
        this.webLink = webLink;
        this.price = price;
        this.isRegisterAble = isRegisable;
        this.subName = subName;
        this.subCode = subCode;
        this.subShortDes = subShortDes;
        this.subFullDes = subFullDes;
        this.subIconUrl = subIconUrl;
        this.subPrice = subPrice;
        this.unit = unit;
        this.state = state;
        this.validity = validity;
        this.shortDes = shortDes;
        this.serviceType = serviceType;
        this.showSubService = showSubService;
    }
}
