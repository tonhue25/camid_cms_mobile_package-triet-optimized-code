package co.siten.myvtg.model.myvtg;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the "SERVICE" database table.
 *
 */
@Entity
@Table(name = "\"SERVICE\"")
@NamedQuery(name = "ServiceA.findAll", query = "SELECT s FROM ServiceA s")
public class ServiceA implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    @Column(name = "ACTION_TYPE")
    private Integer actionType;

    private Integer approved;

    private String code;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "FULL_DES")
    private String fullDes;

    @Column(name = "ICON_URL")
    private String iconUrl;

    @Column(name = "IMG_DES_URL")
    private String imgDesUrl;

    @Column(name = "\"LANGUAGE\"")
    private String language;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATED_TIME")
    private Date lastUpdatedTime;

    private String name;

    private BigDecimal price;

    @Column(name = "SERVICE_GROUP_ID")
    private String serviceGroupId;

    @Transient
    private String serviceGroupName;

    @Column(name = "SERVICE_TYPE")
    private Integer serviceType;

    @Column(name = "SHORT_CODE")
    private Integer shortCode;

    @Column(name = "SHORT_DES")
    private String shortDes;

    private Integer status;

    @Column(name = "WEB_LINK")
    private String webLink;

    @Column(name = "IS_MULT_PLAN")
    private Integer isMultPlan;

    @Column(name = "SERVICE_PAGE")
    private String servicePage;

    @Column(name = "SERVICE_ORDER")
    private Integer serviceOrder;

    @Column(name = "VALIDITY")
    private String validity;

    @Column(name = "FORMAT_CODE")
    private String formatCode;

    @Column(name = "UNIT")
    private String unit;

    @Column(name = "SHOW_SUB_SERVICE")
    private Integer showSubService;

    @Column(name = "NAME_KH",nullable = false, length = 200)
    private String nameKh;
    @Column(name = "SHORT_DES_KH", length = 800)
    private String shortDesKh;
    @Column(name = "FULL_DES_KH", length = 4000)
    private String fullDesKh;
    @Column(name = "RECOMMEND")
    private int recommend;

    public String getNameKh() {
        return nameKh;
    }

    public void setNameKh(String nameKh) {
        this.nameKh = nameKh;
    }

    public String getShortDesKh() {
        return shortDesKh;
    }

    public void setShortDesKh(String shortDesKh) {
        this.shortDesKh = shortDesKh;
    }

    public String getFullDesKh() {
        return fullDesKh;
    }

    public void setFullDesKh(String fullDesKh) {
        this.fullDesKh = fullDesKh;
    }


    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public Integer getShowSubService() {
        return showSubService;
    }

    public void setShowSubService(Integer showSubService) {
        this.showSubService = showSubService;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Integer getIsMultPlan() {
        return isMultPlan;
    }

    public void setIsMultPlan(Integer isMultPlan) {
        this.isMultPlan = isMultPlan;
    }

    public ServiceA() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getActionType() {
        return actionType;
    }

    public Integer getApproved() {
        return approved;
    }

    public String getCode() {
        return code;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getFullDes() {
        return fullDes;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getImgDesUrl() {
        return imgDesUrl;
    }

    public String getLanguage() {
        return language;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getServiceGroupId() {
        return serviceGroupId;
    }

    public String getServiceGroupName() {
        return serviceGroupName;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public Integer getShortCode() {
        return shortCode;
    }

    public String getShortDes() {
        return shortDes;
    }

    public Integer getStatus() {
        return status;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setFullDes(String fullDes) {
        this.fullDes = fullDes;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setImgDesUrl(String imgDesUrl) {
        this.imgDesUrl = imgDesUrl;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setServiceGroupId(String serviceGroupId) {
        this.serviceGroupId = serviceGroupId;
    }

    public void setServiceGroupName(String serviceGroupName) {
        this.serviceGroupName = serviceGroupName;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public void setShortCode(Integer shortCode) {
        this.shortCode = shortCode;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getServicePage() {
        return servicePage;
    }

    public void setServicePage(String servicePage) {
        this.servicePage = servicePage;
    }

    public Integer getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(Integer serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

}
