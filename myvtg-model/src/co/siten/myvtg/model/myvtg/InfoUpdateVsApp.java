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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author buiquangdai
 */
@Entity
@Table(name = "INFO_UPDATE_VS_APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InfoUpdateVsApp.findAll", query = "SELECT i FROM InfoUpdateVsApp i")
    , @NamedQuery(name = "InfoUpdateVsApp.findById", query = "SELECT i FROM InfoUpdateVsApp i WHERE i.id = :id")
    , @NamedQuery(name = "InfoUpdateVsApp.findByVersion", query = "SELECT i FROM InfoUpdateVsApp i WHERE i.version = :version")
    , @NamedQuery(name = "InfoUpdateVsApp.findByIsdn", query = "SELECT i FROM InfoUpdateVsApp i WHERE i.isdn = :isdn")
    , @NamedQuery(name = "InfoUpdateVsApp.findByStatus", query = "SELECT i FROM InfoUpdateVsApp i WHERE i.status = :status")
    , @NamedQuery(name = "InfoUpdateVsApp.findByCreateDate", query = "SELECT i FROM InfoUpdateVsApp i WHERE i.createDate = :createDate")
    , @NamedQuery(name = "InfoUpdateVsApp.findByUpdateDate", query = "SELECT i FROM InfoUpdateVsApp i WHERE i.updateDate = :updateDate")})
public class InfoUpdateVsApp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "INFO_UPDATE_VS_APP_SEQ_GENERATOR", sequenceName = "INFO_UPDATE_VS_APP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INFO_UPDATE_VS_APP_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Size(max = 100)
    @Column(name = "VERSION")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ISDN")
    private String isdn;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public InfoUpdateVsApp() {
    }

    public InfoUpdateVsApp(Long id) {
        this.id = id;
    }

    public InfoUpdateVsApp(Long id, String isdn) {
        this.id = id;
        this.isdn = isdn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InfoUpdateVsApp)) {
            return false;
        }
        InfoUpdateVsApp other = (InfoUpdateVsApp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.InfoUpdateVsApp[ id=" + id + " ]";
    }

}
