package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CHANNEL")
public class Channel implements Serializable {

    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "CHANNEL_SEQ_GENERATOR", sequenceName = "CHANNEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHANNEL_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "CHANNEL_NAME")
    private String channelName;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @Column(name = "CAM_ID")
    private Long camId;
    @Column(name = "MSISDN")
    private String msisdn;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "COMMENT")
    private String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE")
    private Date createDate;
    @Column(name = "BANNER_URL")
    private String bannerUrl;
    @Column(name = "COMMENT_KH")
    private String commentKh;

    public Channel() {
    }

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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getCommentKh() {
        return commentKh;
    }

    public void setCommentKh(String commentKh) {
        this.commentKh = commentKh;
    }

}
