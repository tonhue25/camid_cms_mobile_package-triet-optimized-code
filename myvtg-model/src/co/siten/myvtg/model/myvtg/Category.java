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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
    , @NamedQuery(name = "Category.findById", query = "SELECT c FROM Category c WHERE c.id = :id")
    , @NamedQuery(name = "Category.findByCreateDate", query = "SELECT c FROM Category c WHERE c.createDate = :createDate")
    , @NamedQuery(name = "Category.findByCreateUser", query = "SELECT c FROM Category c WHERE c.createUser = :createUser")
    , @NamedQuery(name = "Category.findByStatus", query = "SELECT c FROM Category c WHERE c.status = :status")
    , @NamedQuery(name = "Category.findByImage", query = "SELECT c FROM Category c WHERE c.image = :image")
    , @NamedQuery(name = "Category.findByIshot", query = "SELECT c FROM Category c WHERE c.ishot = :ishot")
    , @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")
    , @NamedQuery(name = "Category.findByIsNews", query = "SELECT c FROM Category c WHERE c.isNews = :isNews")})
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Size(max = 50)
    @Column(name = "CREATE_USER")
    private String createUser;
    @Column(name = "STATUS")
    private Long status;
    @Size(max = 255)
    @Column(name = "IMAGE")
    private String image;
    @Column(name = "ISHOT")
    private Long ishot;
    @Size(max = 200)
    @Column(name = "NAME")
    private String name;
    @Column(name = "IS_NEWS")
    private Long isNews;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getIshot() {
        return ishot;
    }

    public void setIshot(Long ishot) {
        this.ishot = ishot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIsNews() {
        return isNews;
    }

    public void setIsNews(Long isNews) {
        this.isNews = isNews;
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
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.Category[ id=" + id + " ]";
    }
    
}
