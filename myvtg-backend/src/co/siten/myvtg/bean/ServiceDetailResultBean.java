package co.siten.myvtg.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ServiceDetailResultBean {

    private String name;
    private String code;
    private String fullDes;
    private String imgDesUrl;
    private String webLink;
    private BigDecimal price;
    private Integer isRegisterAble;
    private String shortDes;
    private String validity;
    private Integer autoRenew;
    private Integer serviceType;
    private String condition;
    private Integer showSubService;

    private ArrayList<SubServiceDetailBean> packages;

    public Integer getShowSubService() {
        return showSubService;
    }

    public void setShowSubService(Integer showSubService) {
        this.showSubService = showSubService;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Integer autoRenew) {
        this.autoRenew = autoRenew;
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

    public Integer getIsRegisterAble() {
        return isRegisterAble;
    }

    public void setIsRegisterAble(Integer isRegisterAble) {
        this.isRegisterAble = isRegisterAble;
    }

    public List<SubServiceDetailBean> getPackages() {
        return packages;
    }

    public void setPackages(ArrayList<SubServiceDetailBean> packages) {
        this.packages = packages;
    }

    public String getShortDes() {
        return shortDes;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public ServiceDetailResultBean() {
        super();
    }

    public ServiceDetailResultBean(String name, String code, String fullDes, String imgDesUrl, String webLink,
                                   BigDecimal price, Integer isRegisterAble) {
        super();
        this.name = name;
        this.code = code;
        this.fullDes = fullDes;
        this.imgDesUrl = imgDesUrl;
        this.webLink = webLink;
        this.price = price;
        this.isRegisterAble = isRegisterAble;
    }

    public ServiceDetailResultBean(String name, String code, String fullDes, String imgDesUrl, String webLink,
                                   BigDecimal price, Integer isRegisterAble, String shortDes, String validity) {
        super();
        this.name = name;
        this.code = code;
        this.fullDes = fullDes;
        this.imgDesUrl = imgDesUrl;
        this.webLink = webLink;
        this.price = price;
        this.isRegisterAble = isRegisterAble;
        this.shortDes = shortDes;
        this.validity = validity;
    }
    public ServiceDetailResultBean(String name, String code, String fullDes, String imgDesUrl, String webLink,
                                   BigDecimal price, Integer isRegisterAble, String shortDes, String validity, Integer autoRenew, Integer serviceType, Integer showSubService) {
        super();
        this.name = name;
        this.code = code;
        this.fullDes = fullDes.split("Condition:")[0];
        this.imgDesUrl = imgDesUrl;
        this.webLink = webLink;
        this.price = price;
        this.isRegisterAble = isRegisterAble;
        this.shortDes = shortDes;
        this.validity = validity;
        this.autoRenew = autoRenew;
        this.serviceType = serviceType;
        if(fullDes.split("Condition:").length>1){
            condition = fullDes.split("Condition:")[1].trim();
        }
        this.showSubService = showSubService;
    }
}
