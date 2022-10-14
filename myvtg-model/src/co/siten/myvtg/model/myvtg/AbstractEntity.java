package co.siten.myvtg.model.myvtg;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public class AbstractEntity {
    public AbstractEntity(Integer status, String createdBy, Date createdTime, String lastUpdatedBy, Date lastUpdatedTime) {
        this.status = status;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public AbstractEntity(){}

    @Column(name = "STATUS", columnDefinition = "integer default 1", nullable = false)
    private Integer status;

    @Column(name="CREATED_BY", length = 256)
    private String createdBy;

    @Column(name="CREATED_TIME")
    @Temporal(TemporalType.DATE)
    private Date createdTime;

    @Column(name="LAST_UPDATED_BY", length = 256)
    private String lastUpdatedBy;

    @Column(name="LAST_UPDATED_TIME")
    @Temporal(TemporalType.DATE)
    private Date lastUpdatedTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
