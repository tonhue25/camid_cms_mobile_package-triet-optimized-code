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
@Table(name = "EXCHANGE_TELECOM_GIFT_HISTORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExchangeTelecomGiftHistory.findAll", query = "SELECT e FROM ExchangeTelecomGiftHistory e")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByIsdn", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.isdn = :isdn")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByGiftId", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.giftId = :giftId")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByDateTime", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.dateTime = :dateTime")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByScore", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.score = :score")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByPrice", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.price = :price")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByExchangeType", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.exchangeType = :exchangeType")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByStatus", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.status = :status")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByReceiveDate", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.receiveDate = :receiveDate")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByReceivePhone", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.receivePhone = :receivePhone")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByReceiveAddress", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.receiveAddress = :receiveAddress")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByExchangeCode", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.exchangeCode = :exchangeCode")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByUpdatedBy", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.updatedBy = :updatedBy")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByUpdatedTime", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.updatedTime = :updatedTime")
    , @NamedQuery(name = "ExchangeTelecomGiftHistory.findByExchangeDate", query = "SELECT e FROM ExchangeTelecomGiftHistory e WHERE e.exchangeDate = :exchangeDate")})
public class ExchangeTelecomGiftHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "ISDN")
    private String isdn;
    @Column(name = "GIFT_ID")
    private String giftId;
    @Column(name = "DATE_TIME")
    @Temporal(TemporalType.DATE)
    private Date dateTime;
    @Column(name = "SCORE")
    private BigInteger score;
    @Column(name = "PRICE")
    private BigInteger price;
    @Column(name = "EXCHANGE_TYPE")
    private String exchangeType;
    @Column(name = "STATUS")
    private Short status;
    @Column(name = "RECEIVE_DATE")
    @Temporal(TemporalType.DATE)
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
    @Temporal(TemporalType.DATE)
    private Date updatedTime;
    @Basic(optional = false)
    @Column(name = "EXCHANGE_DATE")
    @Temporal(TemporalType.DATE)
    private Date exchangeDate;
    @Column(name = "PROGRAM_CODE")
    private String programCode;

    public ExchangeTelecomGiftHistory() {
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

    public BigInteger getScore() {
        return score;
    }

    public void setScore(BigInteger score) {
        this.score = score;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
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

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
//    public String getExchangeDate() {
//        return exchangeDate;
//    }
//
//    public void setExchangeDate(String exchangeDate) {
//        this.exchangeDate = exchangeDate;
//    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exchangeDate != null ? exchangeDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExchangeTelecomGiftHistory)) {
            return false;
        }
        ExchangeTelecomGiftHistory other = (ExchangeTelecomGiftHistory) object;
        if ((this.exchangeDate == null && other.exchangeDate != null) || (this.exchangeDate != null && !this.exchangeDate.equals(other.exchangeDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.ExchangeTelecomGiftHistory[ exchangeDate=" + exchangeDate + " ]";
    }

}
