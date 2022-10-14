/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "CATEGORY_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoryDetail.findAll", query = "SELECT c FROM CategoryDetail c")
    , @NamedQuery(name = "CategoryDetail.findById", query = "SELECT c FROM CategoryDetail c WHERE c.id = :id")
    , @NamedQuery(name = "CategoryDetail.findByCategoryId", query = "SELECT c FROM CategoryDetail c WHERE c.categoryId = :categoryId")
    , @NamedQuery(name = "CategoryDetail.findByTitle", query = "SELECT c FROM CategoryDetail c WHERE c.title = :title")
    , @NamedQuery(name = "CategoryDetail.findByDescription", query = "SELECT c FROM CategoryDetail c WHERE c.description = :description")
    , @NamedQuery(name = "CategoryDetail.findByLanguage", query = "SELECT c FROM CategoryDetail c WHERE c.language = :language")
    , @NamedQuery(name = "CategoryDetail.findByIcon", query = "SELECT c FROM CategoryDetail c WHERE c.icon = :icon")
    , @NamedQuery(name = "CategoryDetail.findByImg", query = "SELECT c FROM CategoryDetail c WHERE c.img = :img")
    , @NamedQuery(name = "CategoryDetail.findByLink", query = "SELECT c FROM CategoryDetail c WHERE c.link = :link")
    , @NamedQuery(name = "CategoryDetail.findByCreateDate", query = "SELECT c FROM CategoryDetail c WHERE c.createDate = :createDate")
    , @NamedQuery(name = "CategoryDetail.findByStartTime", query = "SELECT c FROM CategoryDetail c WHERE c.startTime = :startTime")
    , @NamedQuery(name = "CategoryDetail.findByEndTime", query = "SELECT c FROM CategoryDetail c WHERE c.endTime = :endTime")
    , @NamedQuery(name = "CategoryDetail.findByStatus", query = "SELECT c FROM CategoryDetail c WHERE c.status = :status")})
public class CategoryDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "CATEGORY_ID")
    private Long categoryId;
    @Column(name = "TITLE")
    private String title;
    @Size(max = 2000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Lob
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "LANGUAGE")
    private String language;
    @Column(name = "ICON")
    private String icon;
    @Column(name = "IMG")
    private String img;
    @Column(name = "LINK")
    private String link;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "GROUP_ID")
    private String groupId;

    public CategoryDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
        if (!(object instanceof CategoryDetail)) {
            return false;
        }
        CategoryDetail other = (CategoryDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.CategoryDetail[ id=" + id + " ]";
    }

}
