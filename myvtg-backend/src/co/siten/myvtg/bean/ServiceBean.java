package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
/**
 *
 * @author thomc
 *
 */
public class ServiceBean {

    @JsonIgnore
    private String groupCode;
    @JsonIgnore
    private String groupName;

    private String name;
    private String code;
    private String shortDes;
    private String iconUrl;
    private Integer isMultPlan;
    private Integer isRegisterAble;
    private Integer state = 1; // default value: Registered
    private String validity;
    private String autoRenew;
    private Object expired;
    @JsonIgnore
    private Date expiredDate;
    private BigDecimal price;
    private String currency;
    @JsonIgnore
    private String subCode;

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public ServiceBean(String groupName, String groupCode, String name, String code, String shortDes, String iconUrl,
                       Integer isMultPlan) {
        super();
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isMultPlan = isMultPlan;
    }

    public ServiceBean(String name, String code, String shortDes, Integer isRegisable, String iconUrl, Integer isMultPlan) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isRegisterAble = isRegisable;
        this.isMultPlan = isMultPlan;
    }

    public ServiceBean(String name, String code, String shortDes, Integer isRegisable, String iconUrl, Integer isMultPlan, Integer state) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isRegisterAble = isRegisable;
        this.isMultPlan = isMultPlan;
        this.state = state;
    }

    public ServiceBean(String name, String code, String shortDes, Integer isRegisable, String iconUrl, Integer isMultPlan, Integer state, String validity) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isRegisterAble = isRegisable;
        this.isMultPlan = isMultPlan;
        this.state = state;
        this.validity = validity;
    }

    public ServiceBean(String name, String code, String shortDes, Integer isRegisable, String iconUrl, Integer isMultPlan, Integer state, String validity, String currency, BigDecimal price) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isRegisterAble = isRegisable;
        this.isMultPlan = isMultPlan;
        this.state = state;
        this.validity = validity;
        this.currency = currency;
        if(null != price){
            this.price = price.setScale(3, RoundingMode.HALF_DOWN);
        }else{
            this.price = price;
        }
    }

    public ServiceBean(String name, String code, String shortDes, Integer isRegisable, String iconUrl, Integer isMultPlan, Integer state, String validity, String autoRenew) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isRegisterAble = isRegisable;
        this.isMultPlan = isMultPlan;
        this.state = state;
        this.validity = validity;
        this.autoRenew = autoRenew;
    }

    public ServiceBean(String name, String code, String shortDes, String iconUrl) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
    }

    public ServiceBean(String name, String code, String shortDes, String iconUrl, String validity, String autoRenew) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.validity = validity;
        this.autoRenew = autoRenew;
    }

    public ServiceBean(String name, String code, String shortDes, String iconUrl, Integer state) {
        super();
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.state = state;
    }

    public ServiceBean(String groupName, String groupCode, String name, String code, String shortDes, String iconUrl,
                       Integer isMultPlan, String currency, BigDecimal price, String validity) {
        super();
        this.groupCode = groupCode;
        this.groupName = groupName;
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.isMultPlan = isMultPlan;
        this.currency = currency;
        if(null != price){
            this.price = price.setScale(3, RoundingMode.HALF_DOWN);
        }else{
            this.price = price;
        }
        this.validity = validity;
    }

    public ServiceBean(String name, String code, String shortDes, String iconUrl, String validity, String currency, BigDecimal price, String autoRenew, String subCode) {
        this.name = name;
        this.code = code;
        this.shortDes = shortDes;
        this.iconUrl = iconUrl;
        this.validity = validity;
        this.price = price;
        this.currency = currency;
        this.autoRenew = autoRenew;
        this.subCode = subCode;
    }

    public Integer getIsRegisterAble() {
        return isRegisterAble;
    }

    public void setIsRegisterAble(Integer isRegisterAble) {
        this.isRegisterAble = isRegisterAble;
    }

    @JsonIgnore
    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @JsonIgnore
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getIsMultPlan() {
        return isMultPlan;
    }

    public void setIsMultPlan(Integer isMultPlan) {
        this.isMultPlan = isMultPlan;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(String autoRenew) {
        this.autoRenew = autoRenew;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Object getExpired() {
        return expired;
    }

    public void setExpired(Object expired) {
        this.expired = expired;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}
