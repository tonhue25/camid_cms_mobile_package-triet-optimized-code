/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "SHARE_FB")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShareFb.findAll", query = "SELECT s FROM ShareFb s")
    , @NamedQuery(name = "ShareFb.findById", query = "SELECT s FROM ShareFb s WHERE s.id = :id")
    , @NamedQuery(name = "ShareFb.findByIdFb", query = "SELECT s FROM ShareFb s WHERE s.idFb = :idFb")
    , @NamedQuery(name = "ShareFb.findByName", query = "SELECT s FROM ShareFb s WHERE s.name = :name")
    , @NamedQuery(name = "ShareFb.findByGameCode", query = "SELECT s FROM ShareFb s WHERE s.gameCode = :gameCode")
    , @NamedQuery(name = "ShareFb.findByCrateDate", query = "SELECT s FROM ShareFb s WHERE s.crateDate = :crateDate")
    , @NamedQuery(name = "ShareFb.findByUpdateDate", query = "SELECT s FROM ShareFb s WHERE s.updateDate = :updateDate")
    , @NamedQuery(name = "ShareFb.findByCreateBy", query = "SELECT s FROM ShareFb s WHERE s.createBy = :createBy")
    , @NamedQuery(name = "ShareFb.findByUpdateBy", query = "SELECT s FROM ShareFb s WHERE s.updateBy = :updateBy")
    , @NamedQuery(name = "ShareFb.findByStatus", query = "SELECT s FROM ShareFb s WHERE s.status = :status")
    , @NamedQuery(name = "ShareFb.findByIsdn", query = "SELECT s FROM ShareFb s WHERE s.isdn = :isdn")
    , @NamedQuery(name = "ShareFb.findByFreePlayGame", query = "SELECT s FROM ShareFb s WHERE s.freePlayGame = :freePlayGame")})
public class ShareFb implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "SHARE_FB_SEQ_GENERATOR", sequenceName = "SHARE_FB_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHARE_FB_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "ID_FB")
    private String idFb;
    @Column(name = "NAME")
    private String name;
    @Column(name = "GAME_CODE")
    private String gameCode;
    @Column(name = "CRATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date crateDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "ISDN")
    private String isdn;
    @Column(name = "FREE_PLAY_GAME")
    private Long freePlayGame;

    public ShareFb() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getFreePlayGame() {
        return freePlayGame;
    }

    public void setFreePlayGame(Long freePlayGame) {
        this.freePlayGame = freePlayGame;
    }

    public String getIdFb() {
        return idFb;
    }

    public void setIdFb(String idFb) {
        this.idFb = idFb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public Date getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(Date crateDate) {
        this.crateDate = crateDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
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
        if (!(object instanceof ShareFb)) {
            return false;
        }
        ShareFb other = (ShareFb) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.ShareFb[ id=" + id + " ]";
    }

}
