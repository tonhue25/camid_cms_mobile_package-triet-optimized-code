/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daibq
 */
public class GiftDto {

    private String giftId;
    private String reasonId;
    private String partnerId = "0";
    private String partnerName = "";
    private String giftName;
    private String giftNameLc;
    private String giftTypeId;
    private String image;
    private String imageLc;
    private String price;
    private String score;
    private Date startDate;
    private Date expireDate;
    private String status;
    private Date updatedTime;
    private String updatedBy;
    private String note;
    private String imageList;
    private String description;
    private String descriptionLc;
    private String storeList;
//    private List<StoreDto> storeListDetail;
    private String giftCode;
    private String giftGroupId;
    private String giftLevel;
    private Integer quantity;
    private String webSite;
    private Integer giftOrder = 0;
    private String shortDesc;
    private String shortDescLc;
    private Integer isHot;
    private Integer isRecommentGift;
    private String discountRate="";
    private String rankingList;
//    private List<RankingDto> rankingListDetail;
    
    
    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
    public String getImageLc() {
        return imageLc;
    }

    public void setImageLc(String imageLc) {
        this.imageLc = imageLc;
    }

    public String getGiftNameLc() {
        return this.giftNameLc;
    }

    public void setGiftNameLc(String giftNameLc) {
        this.giftNameLc = giftNameLc;
    }

    public String getDescriptionLc() {
        return this.descriptionLc;
    }

    public void setDescriptionLc(String descriptionLc) {
        this.descriptionLc = descriptionLc;
    }

    public String getPartnerName() {
        return this.partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getGiftLevel() {
        return this.giftLevel;
    }

    public void setGiftLevel(String giftLevel) {
        this.giftLevel = giftLevel;
    }

    public String getGiftId() {
        return this.giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getReasonId() {
        return this.reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public String getPartnerId() {
        return this.partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getGiftName() {
        return this.giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftTypeId() {
        return this.giftTypeId;
    }

    public void setGiftTypeId(String giftTypeId) {
        this.giftTypeId = giftTypeId;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getGiftCode() {
        return this.giftCode;
    }

    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode;
    }

    public String getGiftGroupId() {
        return this.giftGroupId;
    }

    public void setGiftGroupId(String giftGroupId) {
        this.giftGroupId = giftGroupId;
    }

//    public List<StoreDto> getStoreListDetail() {
//        return this.storeListDetail;
//    }
//
//    public void setStoreListDetail(List<StoreDto> storeListDetail) {
//        this.storeListDetail = storeListDetail;
//    }


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

//    public List<RankingDto> getRankingListDetail() {
//        return rankingListDetail;
//    }
//
//    public void setRankingListDetail(List<RankingDto> rankingListDetail) {
//        this.rankingListDetail = rankingListDetail;
//    }

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

    public String getRankingList() {
        return rankingList;
    }

    public void setRankingList(String rankingList) {
        this.rankingList = rankingList;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getGiftOrder() {
        return giftOrder;
    }

    public void setGiftOrder(Integer giftOrder) {
        this.giftOrder = giftOrder;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsRecommentGift() {
        return isRecommentGift;
    }

    public void setIsRecommentGift(Integer isRecommentGift) {
        this.isRecommentGift = isRecommentGift;
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
  

}
