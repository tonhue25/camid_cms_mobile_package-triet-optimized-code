package co.siten.myvtg.model.myvtg;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the SUB_SERVICE database table.
 *
 */
@Entity
@Table(name = "SUB_SERVICE")
@NamedQuery(name = "SubServiceA.findAll", query = "SELECT s FROM SubServiceA s")
public class SubServiceA implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    @Id
    private String id;

    private Integer approved;

    @Column(name = "CHANNEL_TYPE")
    private Integer channelType;

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

    @Column(name = "\"LANGUAGE\"")
    private String language;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATED_TIME")
    private Date lastUpdatedTime;

    private String name;

    private BigDecimal price;

    @Column(name = "SERVICE_ID")
    private String serviceId;

    @Transient
    private String serviceName;

    @Column(name = "SHORT_DES")
    private String shortDes;

    private Integer status;

    @Column(name = "\"TYPE\"")
    private String type;

    @Column(name = "SUB_SERVICE_ORDER")
    private Integer subServiceOrder;

    @Column(name = "VALIDITY")
    private String validity;

    @Column(name = "FORMAT_CODE")
    private String formatCode;

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

    private String unit;

    private BigDecimal volume;

    public SubServiceA() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getApproved() {
        return approved;
    }

    public Integer getChannelType() {
        return channelType;
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

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getShortDes() {
        return shortDes;
    }

    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
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

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setShortDes(String shortDes) {
        this.shortDes = shortDes;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Integer getSubServiceOrder() {
        return subServiceOrder;
    }

    public void setSubServiceOrder(Integer subServiceOrder) {
        this.subServiceOrder = subServiceOrder;
    }

}
