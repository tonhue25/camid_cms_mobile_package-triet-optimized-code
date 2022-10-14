/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "EXCHANGE_SCORE_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExchangeScoreHistory.findAll", query = "SELECT e FROM ExchangeScoreHistory e")
    , @NamedQuery(name = "ExchangeScoreHistory.findByIsdn", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.isdn = :isdn")
    , @NamedQuery(name = "ExchangeScoreHistory.findByGiftId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.giftId = :giftId")
    , @NamedQuery(name = "ExchangeScoreHistory.findByDateTime", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.dateTime = :dateTime")
    , @NamedQuery(name = "ExchangeScoreHistory.findByScore", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.score = :score")
    , @NamedQuery(name = "ExchangeScoreHistory.findByPrice", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.price = :price")
    , @NamedQuery(name = "ExchangeScoreHistory.findByExchangeType", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.exchangeType = :exchangeType")
    , @NamedQuery(name = "ExchangeScoreHistory.findByStatus", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.status = :status")
    , @NamedQuery(name = "ExchangeScoreHistory.findByReasonId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.reasonId = :reasonId")
    , @NamedQuery(name = "ExchangeScoreHistory.findByLockDateTime", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.lockDateTime = :lockDateTime")
    , @NamedQuery(name = "ExchangeScoreHistory.findByCityId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.cityId = :cityId")
    , @NamedQuery(name = "ExchangeScoreHistory.findByDistrictId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.districtId = :districtId")
    , @NamedQuery(name = "ExchangeScoreHistory.findByVillageId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.villageId = :villageId")
    , @NamedQuery(name = "ExchangeScoreHistory.findByReceiveDate", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.receiveDate = :receiveDate")
    , @NamedQuery(name = "ExchangeScoreHistory.findByReceivePhone", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.receivePhone = :receivePhone")
    , @NamedQuery(name = "ExchangeScoreHistory.findByReceiveAddress", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.receiveAddress = :receiveAddress")
    , @NamedQuery(name = "ExchangeScoreHistory.findByExchangeCode", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.exchangeCode = :exchangeCode")
    , @NamedQuery(name = "ExchangeScoreHistory.findByUpdatedBy", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.updatedBy = :updatedBy")
    , @NamedQuery(name = "ExchangeScoreHistory.findByUpdatedTime", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.updatedTime = :updatedTime")
    , @NamedQuery(name = "ExchangeScoreHistory.findByAddTimeExpire", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.addTimeExpire = :addTimeExpire")
    , @NamedQuery(name = "ExchangeScoreHistory.findBySubId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.subId = :subId")
    , @NamedQuery(name = "ExchangeScoreHistory.findBySubType", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.subType = :subType")
    , @NamedQuery(name = "ExchangeScoreHistory.findByExchangeDate", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.exchangeDate = :exchangeDate")
    , @NamedQuery(name = "ExchangeScoreHistory.findByExchangeScoreHistoryId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.exchangeScoreHistoryId = :exchangeScoreHistoryId")
    , @NamedQuery(name = "ExchangeScoreHistory.findByStoreId", query = "SELECT e FROM ExchangeScoreHistory e WHERE e.storeId = :storeId")})
public class ExchangeScoreHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EXCHANGE_SCORE_HISTORY_ID")
    private String exchangeScoreHistoryId;
    @Column(name = "ISDN")
    private String isdn;
    @Column(name = "GIFT_ID")
    private String giftId;
    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;
    @Column(name = "SCORE")
    private Long score;
    @Column(name = "PRICE")
    private Long price;
    @Column(name = "EXCHANGE_TYPE")
    private String exchangeType;
    @Column(name = "STATUS")
    private Short status;
    @Column(name = "REASON_ID")
    private String reasonId;
    @Column(name = "LOCK_DATE_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockDateTime;
    @Column(name = "CITY_ID")
    private String cityId;
    @Column(name = "DISTRICT_ID")
    private String districtId;
    @Column(name = "VILLAGE_ID")
    private String villageId;
    @Column(name = "RECEIVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receiveDate;
    @Column(name = "RECEIVE_PHONE")
    private String receivePhone;
    @Column(name = "RECEIVE_ADDRESS")
    private String receiveAddress;
    @Column(name = "EXCHANGE_CODE")
    private String exchangeCode;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
    @Column(name = "ADD_TIME_EXPIRE")
    private Long addTimeExpire;
    @Column(name = "SUB_ID")
    private Long subId;
    @Column(name = "SUB_TYPE")
    private Short subType;
    @Column(name = "EXCHANGE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exchangeDate;
    @Column(name = "STORE_ID")
    private String storeId;
    @Column(name = "PROGRAM_CODE")
    private String programCode;
    @Column(name = "GIFT_QR_CODE")
    private String gifrQrCode;

    public ExchangeScoreHistory() {
    }

    public ExchangeScoreHistory(String exchangeScoreHistoryId) {
        this.exchangeScoreHistoryId = exchangeScoreHistoryId;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getReasonId() {
        return reasonId;
    }

    public void setReasonId(String reasonId) {
        this.reasonId = reasonId;
    }

    public Date getLockDateTime() {
        return lockDateTime;
    }

    public void setLockDateTime(Date lockDateTime) {
        this.lockDateTime = lockDateTime;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Long getSubId() {
        return subId;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public Short getSubType() {
        return subType;
    }

    public void setSubType(Short subType) {
        this.subType = subType;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getExchangeScoreHistoryId() {
        return exchangeScoreHistoryId;
    }

    public void setExchangeScoreHistoryId(String exchangeScoreHistoryId) {
        this.exchangeScoreHistoryId = exchangeScoreHistoryId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getAddTimeExpire() {
        return addTimeExpire;
    }

    public void setAddTimeExpire(Long addTimeExpire) {
        this.addTimeExpire = addTimeExpire;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getGifrQrCode() {
        return gifrQrCode;
    }

    public void setGifrQrCode(String gifrQrCode) {
        this.gifrQrCode = gifrQrCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exchangeScoreHistoryId != null ? exchangeScoreHistoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExchangeScoreHistory)) {
            return false;
        }
        ExchangeScoreHistory other = (ExchangeScoreHistory) object;
        if ((this.exchangeScoreHistoryId == null && other.exchangeScoreHistoryId != null) || (this.exchangeScoreHistoryId != null && !this.exchangeScoreHistoryId.equals(other.exchangeScoreHistoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.ExchangeScoreHistory[ exchangeScoreHistoryId=" + exchangeScoreHistoryId + " ]";
    }

}
