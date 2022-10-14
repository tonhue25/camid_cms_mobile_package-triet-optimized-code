package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DONATE_TRANSACTION")
public class DonateTransaction implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "DONATE_TRANSACTION_SEQ_GENERATOR", sequenceName = "DONATE_TRANSACTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DONATE_TRANSACTION_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "DONATE_CUSTOMER_NAME")
    private String donateCustomerName;
    @Column(name = "DONATE_USER_ID")
    private Long donateUserId;
    @Column(name = "DONATE_USER_MSISDN")
    private String donateUserMsisdn;
    @Column(name = "STREAMER_CHANNEL_NAME")
    private String streamerChannelName;
    @Column(name = "STREAMER_USER_ID")
    private Long streamerUserId;
    @Column(name = "STREAMER_CHANNEL_ID")
    private Long streamerChannelId;
    @Column(name = "DONATE_PACKAGE_CODE")
    private String donatePackageCode;
    @Column(name = "COIN")
    private Integer coin;
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
    @Column(name = "CHANNEL_DISCOUNT_DETAIL_ID")
    private Long channelDiscountDetailId;
    @Column(name = "DISCOUNT_VALUE")
    private Float discountValue;
    @Column(name = "DISCOUNT_UNIT")
    private String discountUnit;
    @Column(name = "TRANS_AMOUNT")
    private Float transAmount;
    @Column(name = "DISCOUNT_AMOUNT")
    private Float discountAmount;
    @Column(name = "TOTAL_AMOUNT")
    private Float totalAmount;
    @Column(name = "TRANS_UNIT")
    private String transUnit;
    @Column(name = "PROCESS_STATUS")
    private Integer processStatus;
    @Column(name = "PROCESS_SYNC_DESC")
    private String processSyncDesc;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROCESS_TIME")
    private Date procesTime;
    @Column(name = "PROCESS_DESC")
    private String processDesc;
    @Column(name = "WITHDRAW_ID")
    private Long withDrawId;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "STATUS_DESC")
    private String statusDesc;
    @Column(name = "COMMENT_CUSTOMER")
    private String commentCustomer;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private Date updatedDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonateCustomerName() {
        return donateCustomerName;
    }

    public void setDonateCustomerName(String donateCustomerName) {
        this.donateCustomerName = donateCustomerName;
    }

    public Long getDonateUserId() {
        return donateUserId;
    }

    public void setDonateUserId(Long donateUserId) {
        this.donateUserId = donateUserId;
    }

    public String getDonateUserMsisdn() {
        return donateUserMsisdn;
    }

    public void setDonateUserMsisdn(String donateUserMsisdn) {
        this.donateUserMsisdn = donateUserMsisdn;
    }

    public String getStreamerChannelName() {
        return streamerChannelName;
    }

    public void setStreamerChannelName(String streamerChannelName) {
        this.streamerChannelName = streamerChannelName;
    }

    public Long getStreamerUserId() {
        return streamerUserId;
    }

    public void setStreamerUserId(Long streamerUserId) {
        this.streamerUserId = streamerUserId;
    }

    public Long getStreamerChannelId() {
        return streamerChannelId;
    }

    public void setStreamerChannelId(Long streamerChannelId) {
        this.streamerChannelId = streamerChannelId;
    }

    public String getDonatePackageCode() {
        return donatePackageCode;
    }

    public void setDonatePackageCode(String donatePackageCode) {
        this.donatePackageCode = donatePackageCode;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Long getChannelDiscountDetailId() {
        return channelDiscountDetailId;
    }

    public void setChannelDiscountDetailId(Long channelDiscountDetailId) {
        this.channelDiscountDetailId = channelDiscountDetailId;
    }

    public Float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Float discountValue) {
        this.discountValue = discountValue;
    }

    public String getDiscountUnit() {
        return discountUnit;
    }

    public void setDiscountUnit(String discountUnit) {
        this.discountUnit = discountUnit;
    }

    public Float getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Float transAmount) {
        this.transAmount = transAmount;
    }

    public Float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransUnit() {
        return transUnit;
    }

    public void setTransUnit(String transUnit) {
        this.transUnit = transUnit;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getProcessSyncDesc() {
        return processSyncDesc;
    }

    public void setProcessSyncDesc(String processSyncDesc) {
        this.processSyncDesc = processSyncDesc;
    }

    public Date getProcesTime() {
        return procesTime;
    }

    public void setProcesTime(Date procesTime) {
        this.procesTime = procesTime;
    }

    public String getProcessDesc() {
        return processDesc;
    }

    public void setProcessDesc(String processDesc) {
        this.processDesc = processDesc;
    }

    public Long getWithDrawId() {
        return withDrawId;
    }

    public void setWithDrawId(Long withDrawId) {
        this.withDrawId = withDrawId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getCommentCustomer() {
        return commentCustomer;
    }

    public void setCommentCustomer(String commentCustomer) {
        this.commentCustomer = commentCustomer;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
