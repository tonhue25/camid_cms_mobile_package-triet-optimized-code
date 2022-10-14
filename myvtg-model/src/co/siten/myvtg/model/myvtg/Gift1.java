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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "GIFT")
@XmlRootElement
public class Gift1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GIFT_ID")
    private String giftId;
    @Column(name = "REASON_ID")
    private String reasonId;
    @Column(name = "PARTNER_ID")
    private String partnerId;
    @Column(name = "GIFT_NAME")
    private String giftName;
    @Column(name = "GIFT_TYPE_ID")
    private String giftTypeId;
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "SCORE")
    private Long score;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "NOTE")
    private String note;
    @Column(name = "IMAGE_LIST")
    private String imageList;
    @Column(name = "STORE_LIST")
    private String storeList;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "GIFT_CODE")
    private String giftCode;
    @Column(name = "GIFT_GROUP_ID")
    private String giftGroupId;
    @Column(name = "GIFT_LEVEL")
    private Long giftLevel;
    @Column(name = "GIFT_NAME_LC")
    private String giftNameLc;
    @Column(name = "DESCRIPTION_LC")
    private String descriptionLc;
    @Column(name = "IMAGE_LC")
    private String imageLc;
    @Column(name = "QUANTITY")
    private Long quantity;
    @Column(name = "GIFT_ORDER")
    private Integer giftOrder;
    @Column(name = "SHORT_DESC")
    private String shortDesc;
    @Column(name = "SHORT_DESC_LC")
    private String shortDescLc;
    @Column(name = "IS_HOT")
    private Long isHot;
    @Column(name = "IS_RECOMMEND_GIFT")
    private Long isRecommendGift;
    @Column(name = "DISCOUNT_RATE")
    private String discountRate;
    @Column(name = "RANKING_LIST")
    private String rankingList;
    @Column(name = "IS_EMONEY")
    private Long isEmoney;
    @Column(name = "IS_PROMOTION")
    private Long isPromotion;

    public Gift1() {
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftTypeId() {
        return giftTypeId;
    }

    public void setGiftTypeId(String giftTypeId) {
        this.giftTypeId = giftTypeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getStoreList() {
        return storeList;
    }

    public void setStoreList(String storeList) {
        this.storeList = storeList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode;
    }

    public String getGiftGroupId() {
        return giftGroupId;
    }

    public void setGiftGroupId(String giftGroupId) {
        this.giftGroupId = giftGroupId;
    }

    public String getGiftNameLc() {
        return giftNameLc;
    }

    public void setGiftNameLc(String giftNameLc) {
        this.giftNameLc = giftNameLc;
    }

    public String getDescriptionLc() {
        return descriptionLc;
    }

    public void setDescriptionLc(String descriptionLc) {
        this.descriptionLc = descriptionLc;
    }

    public String getImageLc() {
        return imageLc;
    }

    public void setImageLc(String imageLc) {
        this.imageLc = imageLc;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Integer getGiftOrder() {
        return giftOrder;
    }

    public void setGiftOrder(Integer giftOrder) {
        this.giftOrder = giftOrder;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortDescLc() {
        return shortDescLc;
    }

    public void setShortDescLc(String shortDescLc) {
        this.shortDescLc = shortDescLc;
    }

    public String getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(String discountRate) {
        this.discountRate = discountRate;
    }

    public String getRankingList() {
        return rankingList;
    }

    public void setRankingList(String rankingList) {
        this.rankingList = rankingList;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getGiftLevel() {
        return giftLevel;
    }

    public void setGiftLevel(Long giftLevel) {
        this.giftLevel = giftLevel;
    }

    public Long getIsHot() {
        return isHot;
    }

    public void setIsHot(Long isHot) {
        this.isHot = isHot;
    }

    public Long getIsRecommendGift() {
        return isRecommendGift;
    }

    public void setIsRecommendGift(Long isRecommendGift) {
        this.isRecommendGift = isRecommendGift;
    }

    public Long getIsEmoney() {
        return isEmoney;
    }

    public void setIsEmoney(Long isEmoney) {
        this.isEmoney = isEmoney;
    }

    public Long getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(Long isPromotion) {
        this.isPromotion = isPromotion;
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
        if (!(object instanceof Gift1)) {
            return false;
        }
        Gift1 other = (Gift1) object;
        if ((this.giftId == null && other.giftId != null) || (this.giftId != null && !this.giftId.equals(other.giftId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.Gift[ giftId=" + giftId + " ]";
    }

}
