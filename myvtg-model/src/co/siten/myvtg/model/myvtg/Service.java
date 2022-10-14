package co.siten.myvtg.model.myvtg;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "SERVICE_BK_20221608")
@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
public class Service extends AbstractEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "SERVICE_BK_20221608_SEQ", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "CODE", nullable = false, length = 30)
    private String code;

    @Column(name = "NAME",nullable = false, length = 200)
    private String name;

    @Column(name = "NAME_KH",nullable = false, length = 200)
    private String nameKh;

    @Column(name = "SERVICE_GROUP_ID")
    private Long serviceGroupId;

    @Column(name = "SHORT_DES", length = 800)
    private String shortDes;

    @Column(name = "FULL_DES", length = 4000)
    private String fullDes;

    @Column(name = "SHORT_DES_KH", length = 800)
    private String shortDesKh;

    @Column(name = "FULL_DES_KH", length = 4000)
    private String fullDesKh;

    @Column(name = "ICON_URL", length = 200)
    private String iconUrl;

    @Column(name = "IMG_DES_URL",length = 200)
    private String imgDesUrl;

    @Column(name = "WEB_LINK", length = 200)
    private String webLink;

    @Column(name = "SHORT_CODE")
    private Integer shortCode;

    @Column(name = "ACTION_TYPE")
    private Integer actionType;

    @Column(name = "SERVICE_TYPE")
    private Integer serviceType;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IS_MULT_PLAN")
    private Integer isMultPlan;

    @Lob
    @Column(name = "SERVICE_PAGE")
    private String servicePage;

    @Column(name = "SERVICE_ORDER")
    private Integer serviceOrder;

    @Column(name = "VALIDITY")
    private String validity;

    @Column(name = "FORMAT_CODE", length = 4000)
    private String formatCode;

    @Column(name = "UNIT", length = 20)
    private String unit;

    @Column(name = "SHOW_SUB_SERVICE")
    private Integer showSubService;

    @Column(name = "RECOMMEND")
    private Integer recommend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKh() {
        return nameKh;
    }

    public void setNameKh(String nameKh) {
        this.nameKh = nameKh;
    }

    public Long getServiceGroupId() {
        return serviceGroupId;
    }

    public void setServiceGroupId(Long serviceGroupId) {
        this.serviceGroupId = serviceGroupId;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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

    public Integer getShortCode() {
        return shortCode;
    }

    public void setShortCode(Integer shortCode) {
        this.shortCode = shortCode;
    }

    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getIsMultPlan() {
        return isMultPlan;
    }

    public void setIsMultPlan(Integer isMultPlan) {
        this.isMultPlan = isMultPlan;
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

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getFormatCode() {
        return formatCode;
    }

    public void setFormatCode(String formatCode) {
        this.formatCode = formatCode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getShowSubService() {
        return showSubService;
    }

    public void setShowSubService(Integer showSubService) {
        this.showSubService = showSubService;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }
}
