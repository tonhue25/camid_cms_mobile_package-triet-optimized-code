/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author buiquangdai
 */
@JsonInclude(Include.NON_NULL)
public class CategoryDetailDTO implements Serializable {

    private Long id;
    private Long categoryId;
    private String title;
    private String description;
    private String content;
    private String language;
    private String icon;
    private String img;
    private String link;
    private Date createDate;
    private Date startTime;
    private Date endTime;
    private Long status;
    private String pathImg;
    private String groupId;

    public CategoryDetailDTO() {
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

    public String getPathImg() {
        return pathImg;
    }

    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
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
        if (!(object instanceof CategoryDetailDTO)) {
            return false;
        }
        CategoryDetailDTO other = (CategoryDetailDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.CategoryDetail[ id=" + id + " ]";
    }

    public CategoryDetailDTO(Long id, String title, String description, String icon, String img, String link, Date startTime, String pathImg) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.img = pathImg + img;
        this.link = link;
        this.startTime = startTime;
    }

    public CategoryDetailDTO(Long id, String title, String description, String icon, String img, String link, Date startTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.img = img;
        this.link = link;
        this.startTime = startTime;
    }

}
