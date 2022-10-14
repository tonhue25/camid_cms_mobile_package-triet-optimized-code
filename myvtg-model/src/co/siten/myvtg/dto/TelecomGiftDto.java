/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;


/**
 *
 * @author daibq
 */
public class TelecomGiftDto {

    private String giftTypeId;
    private String giftTitle;
    private String giftTitleLc;
    private String giftDesc;
    private String giftDescLc;
    private Long giftPoint;
    private String iconUrl;
    private String imageUrl;
    private String iconUrlLc;
    private String imageUrlLc;
    private String transferType;
    private String dataType;
    private String giftId;
    private long giftBlock;
    private String exchangeUnit;
    private String exchangeUnitLc;
    private Double exchangeValue;
    private int giftOrder;
    private int status;

    public String getGiftTypeId() {
        return giftTypeId;
    }

    public void setGiftTypeId(String giftTypeId) {
        this.giftTypeId = giftTypeId;
    }

    public TelecomGiftDto() {
    }

    public TelecomGiftDto(String giftTypeId, String giftTitle, String giftDesc, Long giftPoint, String iconUrl, String imageUrl, String transferType, String dataType, String giftId, long giftBlock, String exchangeUnit, Double exchangeValue) {
        this.giftTypeId = giftTypeId;
        this.giftTitle = giftTitle;
        this.giftDesc = giftDesc;
        this.giftPoint = giftPoint;
        this.iconUrl = iconUrl;
        this.imageUrl = imageUrl;
        this.transferType = transferType;
        this.dataType = dataType;
        this.giftId = giftId;
        this.giftBlock = giftBlock;
        this.exchangeUnit = exchangeUnit;
        this.exchangeValue = exchangeValue;
    }

    public String getGiftTitle() {
        return giftTitle;
    }

    public void setGiftTitle(String giftTitle) {
        this.giftTitle = giftTitle;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public Long getGiftPoint() {
        return giftPoint;
    }

    public void setGiftPoint(Long giftPoint) {
        this.giftPoint = giftPoint;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public long getGiftBlock() {
        return giftBlock;
    }

    public void setGiftBlock(long giftBlock) {
        this.giftBlock = giftBlock;
    }

    public String getExchangeUnit() {
        return exchangeUnit;
    }

    public void setExchangeUnit(String exchangeUnit) {
        this.exchangeUnit = exchangeUnit;
    }

    public Double getExchangeValue() {
        return exchangeValue;
    }

    public void setExchangeValue(Double exchangeValue) {
        this.exchangeValue = exchangeValue;
    }

    public String getGiftTitleLc() {
        return giftTitleLc;
    }

    public void setGiftTitleLc(String giftTitleLc) {
        this.giftTitleLc = giftTitleLc;
    }

    public String getGiftDescLc() {
        return giftDescLc;
    }

    public void setGiftDescLc(String giftDescLc) {
        this.giftDescLc = giftDescLc;
    }

    public String getIconUrlLc() {
        return iconUrlLc;
    }

    public void setIconUrlLc(String iconUrlLc) {
        this.iconUrlLc = iconUrlLc;
    }

    public String getImageUrlLc() {
        return imageUrlLc;
    }

    public void setImageUrlLc(String imageUrlLc) {
        this.imageUrlLc = imageUrlLc;
    }

    public String getExchangeUnitLc() {
        return exchangeUnitLc;
    }

    public void setExchangeUnitLc(String exchangeUnitLc) {
        this.exchangeUnitLc = exchangeUnitLc;
    }

    public int getGiftOrder() {
        return giftOrder;
    }

    public void setGiftOrder(int giftOrder) {
        this.giftOrder = giftOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
 
    

}
