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
@Table(name = "AUTHEN_LOGIN_GAME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuthenLoginGame.findAll", query = "SELECT a FROM AuthenLoginGame a")
    , @NamedQuery(name = "AuthenLoginGame.findByAuthenkey", query = "SELECT a FROM AuthenLoginGame a WHERE a.authenkey = :authenkey")
    , @NamedQuery(name = "AuthenLoginGame.findByIsdn", query = "SELECT a FROM AuthenLoginGame a WHERE a.isdn = :isdn")
    , @NamedQuery(name = "AuthenLoginGame.findByGameCode", query = "SELECT a FROM AuthenLoginGame a WHERE a.gameCode = :gameCode")
    , @NamedQuery(name = "AuthenLoginGame.findByStatus", query = "SELECT a FROM AuthenLoginGame a WHERE a.status = :status")
    , @NamedQuery(name = "AuthenLoginGame.findByCreateDate", query = "SELECT a FROM AuthenLoginGame a WHERE a.createDate = :createDate")
    , @NamedQuery(name = "AuthenLoginGame.findByCreateBy", query = "SELECT a FROM AuthenLoginGame a WHERE a.createBy = :createBy")
    , @NamedQuery(name = "AuthenLoginGame.findByUpdateBy", query = "SELECT a FROM AuthenLoginGame a WHERE a.updateBy = :updateBy")
    , @NamedQuery(name = "AuthenLoginGame.findByUpdateDate", query = "SELECT a FROM AuthenLoginGame a WHERE a.updateDate = :updateDate")})
public class AuthenLoginGame implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AUTHENKEY")
    private String authenkey;
    @Basic(optional = false)
    @Column(name = "ISDN")
    private String isdn;
    @Basic(optional = false)
    @Column(name = "GAME_CODE")
    private String gameCode;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public AuthenLoginGame() {
    }

    public AuthenLoginGame(String authenkey) {
        this.authenkey = authenkey;
    }

    public AuthenLoginGame(String authenkey, String isdn, String gameCode) {
        this.authenkey = authenkey;
        this.isdn = isdn;
        this.gameCode = gameCode;
    }

    public String getAuthenkey() {
        return authenkey;
    }

    public void setAuthenkey(String authenkey) {
        this.authenkey = authenkey;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authenkey != null ? authenkey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthenLoginGame)) {
            return false;
        }
        AuthenLoginGame other = (AuthenLoginGame) object;
        if ((this.authenkey == null && other.authenkey != null) || (this.authenkey != null && !this.authenkey.equals(other.authenkey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.AuthenLoginGame[ authenkey=" + authenkey + " ]";
    }

}
