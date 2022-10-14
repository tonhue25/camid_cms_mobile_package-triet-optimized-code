/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

/**
 *
 * @author kiennt88
 */
public class CamIdNotificationBean {

    private long notifyId;
    private String type;
    private String link;
    private int readStatus;
    private String iconUrl;
    private String time;
    private String titleEn;
    private String titleKh;
    private String valueEn;
    private String valueKh;
    private String actionType;
    private String buttonTitle;
    private String actionObjectId;
    private String featureImage;
    private String videoUrl;
    
	//bo sung cho subid = passivetype
    private String subId;
    private String passiveTypeId;
    
    private Long isExchange;
    private String idMessage;
    public CamIdNotificationBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(long notifyId) {
        this.notifyId = notifyId;
    }

    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleKh() {
        return titleKh;
    }

    public void setTitleKh(String titleKh) {
        this.titleKh = titleKh;
    }

    public String getValueEn() {
        return valueEn;
    }

    public void setValueEn(String valueEn) {
        this.valueEn = valueEn;
    }

    public String getValueKh() {
        return valueKh;
    }

    public void setValueKh(String valueKh) {
        this.valueKh = valueKh;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    public String getActionObjectId() {
        return actionObjectId;
    }

    public void setActionObjectId(String actionObjectId) {
        this.actionObjectId = actionObjectId;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(Long isExchange) {
        this.isExchange = isExchange;
    }
	
	public String getSubId() {
        return subId;
    }
    public void setSubId(String subId) {
        this.subId = subId;
    }
    public String getPassiveTypeId() {
        return passiveTypeId;
    }
    public void setPassiveTypeId(String passiveTypeId) {
        this.passiveTypeId = passiveTypeId;
    }

    public String getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }



}
