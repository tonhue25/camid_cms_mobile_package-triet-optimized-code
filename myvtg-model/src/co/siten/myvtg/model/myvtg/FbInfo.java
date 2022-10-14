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
@Table(name = "FB_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FbInfo.findAll", query = "SELECT f FROM FbInfo f")
    , @NamedQuery(name = "FbInfo.findById", query = "SELECT f FROM FbInfo f WHERE f.id = :id")
    , @NamedQuery(name = "FbInfo.findByName", query = "SELECT f FROM FbInfo f WHERE f.name = :name")
    , @NamedQuery(name = "FbInfo.findByGameCode", query = "SELECT f FROM FbInfo f WHERE f.gameCode = :gameCode")
    , @NamedQuery(name = "FbInfo.findByCrateDate", query = "SELECT f FROM FbInfo f WHERE f.crateDate = :crateDate")
    , @NamedQuery(name = "FbInfo.findByUpdateDate", query = "SELECT f FROM FbInfo f WHERE f.updateDate = :updateDate")
    , @NamedQuery(name = "FbInfo.findByCreateBy", query = "SELECT f FROM FbInfo f WHERE f.createBy = :createBy")
    , @NamedQuery(name = "FbInfo.findByUpdateBy", query = "SELECT f FROM FbInfo f WHERE f.updateBy = :updateBy")
    , @NamedQuery(name = "FbInfo.findByStatus", query = "SELECT f FROM FbInfo f WHERE f.status = :status")
    , @NamedQuery(name = "FbInfo.findByIsdn", query = "SELECT f FROM FbInfo f WHERE f.isdn = :isdn")})
public class FbInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "GAME_CODE")
    private String gameCode;
    @Column(name = "CRATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crateDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "CREATE_BY")
    private String createBy;
    @Column(name = "UPDATE_BY")
    private String updateBy;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "ISDN")
    private String isdn;

    public FbInfo() {
    }

    public FbInfo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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
        if (!(object instanceof FbInfo)) {
            return false;
        }
        FbInfo other = (FbInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "genmodel.FbInfo[ id=" + id + " ]";
    }
    
}
