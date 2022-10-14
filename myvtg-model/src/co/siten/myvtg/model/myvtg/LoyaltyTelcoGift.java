/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "LOYALTY_TELCO_GIFT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoyaltyTelcoGift.findAll", query = "SELECT l FROM LoyaltyTelcoGift l")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftId", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftId = :giftId")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftTitle", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftTitle = :giftTitle")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftTitleKm", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftTitleKm = :giftTitleKm")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftDesc", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftDesc = :giftDesc")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftDescKm", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftDescKm = :giftDescKm")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByImgUrl", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.imgUrl = :imgUrl")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByIconUrl", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.iconUrl = :iconUrl")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByImgUrlKm", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.imgUrlKm = :imgUrlKm")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByIconUrlKm", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.iconUrlKm = :iconUrlKm")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByStatus", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.status = :status")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByTransferType", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.transferType = :transferType")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByDataType", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.dataType = :dataType")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftTypeId", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftTypeId = :giftTypeId")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftOrder", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftOrder = :giftOrder")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftBlock", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftBlock = :giftBlock")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByExchangeValue", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.exchangeValue = :exchangeValue")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByGiftPoint", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.giftPoint = :giftPoint")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByExchangeUnit", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.exchangeUnit = :exchangeUnit")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByIsDelete", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.isDelete = :isDelete")
    , @NamedQuery(name = "LoyaltyTelcoGift.findByExchangeUnitKm", query = "SELECT l FROM LoyaltyTelcoGift l WHERE l.exchangeUnitKm = :exchangeUnitKm")})
public class LoyaltyTelcoGift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GIFT_ID")
    private String giftId;
    @Basic(optional = false)
    @Column(name = "GIFT_TITLE")
    private String giftTitle;
    @Column(name = "GIFT_TITLE_KM")
    private String giftTitleKm;
    @Basic(optional = false)
    @Column(name = "GIFT_DESC")
    private String giftDesc;
    @Column(name = "GIFT_DESC_KM")
    private String giftDescKm;
    @Column(name = "IMG_URL")
    private String imgUrl;
    @Column(name = "ICON_URL")
    private String iconUrl;
    @Column(name = "IMG_URL_KM")
    private String imgUrlKm;
    @Column(name = "ICON_URL_KM")
    private String iconUrlKm;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private Long status;
    @Basic(optional = false)
    @Column(name = "TRANSFER_TYPE")
    private String transferType;
    @Basic(optional = false)
    @Column(name = "DATA_TYPE")
    private String dataType;
    @Column(name = "GIFT_TYPE_ID")
    private String giftTypeId;
    @Column(name = "GIFT_ORDER")
    private Long giftOrder;
    @Column(name = "GIFT_BLOCK")
    private Short giftBlock;
    @Column(name = "EXCHANGE_VALUE")
    private Long exchangeValue;
    @Column(name = "GIFT_POINT")
    private Long giftPoint;
    @Column(name = "EXCHANGE_UNIT")
    private String exchangeUnit;
    @Column(name = "IS_DELETE")
    private Long isDelete;
    @Column(name = "EXCHANGE_UNIT_KM")
    private String exchangeUnitKm;

//    private String giftName;
    @Transient
    private Date expireDate;
    @Transient
    private Long quantity;

    public LoyaltyTelcoGift() {
    }

    public LoyaltyTelcoGift(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftTitle() {
        return giftTitle;
    }

    public void setGiftTitle(String giftTitle) {
        this.giftTitle = giftTitle;
    }

    public String getGiftTitleKm() {
        return giftTitleKm;
    }

    public void setGiftTitleKm(String giftTitleKm) {
        this.giftTitleKm = giftTitleKm;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public String getGiftDescKm() {
        return giftDescKm;
    }

    public void setGiftDescKm(String giftDescKm) {
        this.giftDescKm = giftDescKm;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImgUrlKm() {
        return imgUrlKm;
    }

    public void setImgUrlKm(String imgUrlKm) {
        this.imgUrlKm = imgUrlKm;
    }

    public String getIconUrlKm() {
        return iconUrlKm;
    }

    public void setIconUrlKm(String iconUrlKm) {
        this.iconUrlKm = iconUrlKm;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getGiftTypeId() {
        return giftTypeId;
    }

    public void setGiftTypeId(String giftTypeId) {
        this.giftTypeId = giftTypeId;
    }

    public Long getGiftOrder() {
        return giftOrder;
    }

    public void setGiftOrder(Long giftOrder) {
        this.giftOrder = giftOrder;
    }

    public Short getGiftBlock() {
        return giftBlock;
    }

    public void setGiftBlock(Short giftBlock) {
        this.giftBlock = giftBlock;
    }

    public Long getExchangeValue() {
        return exchangeValue;
    }

    public void setExchangeValue(Long exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    public Long getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(Long giftPoint) {
        this.giftPoint = giftPoint;
    }

    public String getExchangeUnit() {
        return exchangeUnit;
    }

    public void setExchangeUnit(String exchangeUnit) {
        this.exchangeUnit = exchangeUnit;
    }

    public Long getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Long isDelete) {
        this.isDelete = isDelete;
    }

    public String getExchangeUnitKm() {
        return exchangeUnitKm;
    }

    public void setExchangeUnitKm(String exchangeUnitKm) {
        this.exchangeUnitKm = exchangeUnitKm;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (giftId != null ? giftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoyaltyTelcoGift)) {
            return false;
        }
        LoyaltyTelcoGift other = (LoyaltyTelcoGift) object;
        if ((this.giftId == null && other.giftId != null) || (this.giftId != null && !this.giftId.equals(other.giftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.LoyaltyTelcoGift[ giftId=" + giftId + " ]";
    }

}
