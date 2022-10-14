package co.siten.myvtg.dto;

public class ChannelDTO {

    private Long id;
    private String channelName;
    private String imageUrl;
    private Long camId;
    private String msisdn;
    private Integer status;
    private String comment;
    private String createdDate;
    private String bannerUrl;
    private Long emoneyAccountId;
    private String emoneyAccount;
    private String commentKh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCamId() {
        return camId;
    }

    public void setCamId(Long camId) {
        this.camId = camId;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getEmoneyAccount() {
        return emoneyAccount;
    }

    public void setEmoneyAccount(String emoneyAccount) {
        this.emoneyAccount = emoneyAccount;
    }

    public String getCommentKh() {
        return commentKh;
    }

    public void setCommentKh(String commentKh) {
        this.commentKh = commentKh;
    }

    public Long getEmoneyAccountId() {
        return emoneyAccountId;
    }

    public void setEmoneyAccountId(Long emoneyAccountId) {
        this.emoneyAccountId = emoneyAccountId;
    }

}
