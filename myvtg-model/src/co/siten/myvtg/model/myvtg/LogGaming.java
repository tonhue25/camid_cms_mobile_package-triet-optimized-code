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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "LOG_GAMING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LogGaming.findAll", query = "SELECT l FROM LogGaming l")
    , @NamedQuery(name = "LogGaming.findByIsdn", query = "SELECT l FROM LogGaming l WHERE l.isdn = :isdn")
    , @NamedQuery(name = "LogGaming.findByGameCode", query = "SELECT l FROM LogGaming l WHERE l.gameCode = :gameCode")
    , @NamedQuery(name = "LogGaming.findByGiftId", query = "SELECT l FROM LogGaming l WHERE l.giftId = :giftId")
    , @NamedQuery(name = "LogGaming.findByType", query = "SELECT l FROM LogGaming l WHERE l.type = :type")
    , @NamedQuery(name = "LogGaming.findByStatus", query = "SELECT l FROM LogGaming l WHERE l.status = :status")
    , @NamedQuery(name = "LogGaming.findByAuthenkey", query = "SELECT l FROM LogGaming l WHERE l.authenkey = :authenkey")
    , @NamedQuery(name = "LogGaming.findByCreateDate", query = "SELECT l FROM LogGaming l WHERE l.createDate = :createDate")
    , @NamedQuery(name = "LogGaming.findByUpdateDate", query = "SELECT l FROM LogGaming l WHERE l.updateDate = :updateDate")
    , @NamedQuery(name = "LogGaming.findById", query = "SELECT l FROM LogGaming l WHERE l.id = :id")})
public class LogGaming implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
//    @SequenceGenerator(name = "LOG_GAMING_SEQ_GENERATOR", sequenceName = "LOG_GAMING_SEQ", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_GAMING_SEQ_GENERATOR")
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @Column(name = "ISDN")
    private String isdn;
    @Basic(optional = false)
    @Column(name = "GAME_CODE")
    private String gameCode;
    @Column(name = "GIFT_ID")
    private String giftId;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "AUTHENKEY")
    private String authenkey;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    @Column(name = "DATA_TYPE")
    private String dataType;
    @Column(name = "VALUE")
    private String value;
    @Column(name = "NAME")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    public LogGaming() {
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthenkey() {
        return authenkey;
    }

    public void setAuthenkey(String authenkey) {
        this.authenkey = authenkey;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LogGaming)) {
            return false;
        }
        LogGaming other = (LogGaming) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.LogGaming[ id=" + id + " ]";
    }

}
