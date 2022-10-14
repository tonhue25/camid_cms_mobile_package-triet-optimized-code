/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "GIFT_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GiftType.findAll", query = "SELECT g FROM GiftType g")
    , @NamedQuery(name = "GiftType.findById", query = "SELECT g FROM GiftType g WHERE g.id = :id")
    , @NamedQuery(name = "GiftType.findByGiftTypeName", query = "SELECT g FROM GiftType g WHERE g.giftTypeName = :giftTypeName")
    , @NamedQuery(name = "GiftType.findByDescription", query = "SELECT g FROM GiftType g WHERE g.description = :description")
    , @NamedQuery(name = "GiftType.findByStatus", query = "SELECT g FROM GiftType g WHERE g.status = :status")
    , @NamedQuery(name = "GiftType.findByGiftTypeOrder", query = "SELECT g FROM GiftType g WHERE g.giftTypeOrder = :giftTypeOrder")
    , @NamedQuery(name = "GiftType.findByLastUpdatedTime", query = "SELECT g FROM GiftType g WHERE g.lastUpdatedTime = :lastUpdatedTime")
    , @NamedQuery(name = "GiftType.findByCreatedBy", query = "SELECT g FROM GiftType g WHERE g.createdBy = :createdBy")
    , @NamedQuery(name = "GiftType.findByCreatedTime", query = "SELECT g FROM GiftType g WHERE g.createdTime = :createdTime")
    , @NamedQuery(name = "GiftType.findByLastUpdatedBy", query = "SELECT g FROM GiftType g WHERE g.lastUpdatedBy = :lastUpdatedBy")
    , @NamedQuery(name = "GiftType.findByIconUrl", query = "SELECT g FROM GiftType g WHERE g.iconUrl = :iconUrl")
    , @NamedQuery(name = "GiftType.findByIsShortCut", query = "SELECT g FROM GiftType g WHERE g.isShortCut = :isShortCut")
    , @NamedQuery(name = "GiftType.findByGiftTypeNameLc", query = "SELECT g FROM GiftType g WHERE g.giftTypeNameLc = :giftTypeNameLc")
    , @NamedQuery(name = "GiftType.findByPartner", query = "SELECT g FROM GiftType g WHERE g.partner = :partner")
    , @NamedQuery(name = "GiftType.findByIsType", query = "SELECT g FROM GiftType g WHERE g.isType = :isType")
    , @NamedQuery(name = "GiftType.findByGiftTypeCode", query = "SELECT g FROM GiftType g WHERE g.giftTypeCode = :giftTypeCode")})
public class GiftType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "GIFT_TYPE_NAME")
    private String giftTypeName;
    @Size(max = 4000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS")
    private Short status;
    @Column(name = "GIFT_TYPE_ORDER")
    private Short giftTypeOrder;
    @Column(name = "LAST_UPDATED_TIME")
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedTime;
    @Size(max = 100)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.DATE)
    private Date createdTime;
    @Size(max = 150)
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Size(max = 300)
    @Column(name = "ICON_URL")
    private String iconUrl;
    @Column(name = "IS_SHORT_CUT")
    private Short isShortCut;
    @Size(max = 200)
    @Column(name = "GIFT_TYPE_NAME_LC")
    private String giftTypeNameLc;
    @Size(max = 200)
    @Column(name = "PARTNER")
    private String partner;
    @Column(name = "IS_TYPE")
    private Short isType;
    @Size(max = 200)
    @Column(name = "GIFT_TYPE_CODE")
    private String giftTypeCode;

    public GiftType() {
    }

    public GiftType(String id) {
        this.id = id;
    }

    public GiftType(String id, String giftTypeName) {
        this.id = id;
        this.giftTypeName = giftTypeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGiftTypeName() {
        return giftTypeName;
    }

    public void setGiftTypeName(String giftTypeName) {
        this.giftTypeName = giftTypeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getGiftTypeOrder() {
        return giftTypeOrder;
    }

    public void setGiftTypeOrder(Short giftTypeOrder) {
        this.giftTypeOrder = giftTypeOrder;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Short getIsShortCut() {
        return isShortCut;
    }

    public void setIsShortCut(Short isShortCut) {
        this.isShortCut = isShortCut;
    }

    public String getGiftTypeNameLc() {
        return giftTypeNameLc;
    }

    public void setGiftTypeNameLc(String giftTypeNameLc) {
        this.giftTypeNameLc = giftTypeNameLc;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Short getIsType() {
        return isType;
    }

    public void setIsType(Short isType) {
        this.isType = isType;
    }

    public String getGiftTypeCode() {
        return giftTypeCode;
    }

    public void setGiftTypeCode(String giftTypeCode) {
        this.giftTypeCode = giftTypeCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GiftType)) {
            return false;
        }
        GiftType other = (GiftType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.service.GiftType[ id=" + id + " ]";
    }
    
}
