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
@Table(name = "CAPTCHA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Captcha.findAll", query = "SELECT c FROM Captcha c")
    , @NamedQuery(name = "Captcha.findById", query = "SELECT c FROM Captcha c WHERE c.id = :id")
    , @NamedQuery(name = "Captcha.findByIsdn", query = "SELECT c FROM Captcha c WHERE c.isdn = :isdn")
    , @NamedQuery(name = "Captcha.findByCaptchaCode", query = "SELECT c FROM Captcha c WHERE c.captchaCode = :captchaCode")
    , @NamedQuery(name = "Captcha.findByProgramCode", query = "SELECT c FROM Captcha c WHERE c.programCode = :programCode")
    , @NamedQuery(name = "Captcha.findByCreateDate", query = "SELECT c FROM Captcha c WHERE c.createDate = :createDate")
    , @NamedQuery(name = "Captcha.findByUpdateDate", query = "SELECT c FROM Captcha c WHERE c.updateDate = :updateDate")
    , @NamedQuery(name = "Captcha.findByExpiredDate", query = "SELECT c FROM Captcha c WHERE c.expiredTime = :expiredTime")})
public class Captcha implements Serializable {

    private static final long serialVersionUID = 1L;
    @SequenceGenerator(name = "CAPTCHA_SEQ_GENERATOR", sequenceName = "CAPTCHA_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAPTCHA_SEQ_GENERATOR")
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(min = 1, max = 20)
    @Column(name = "ISDN")
    private String isdn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CAPTCHA_CODE")
    private String captchaCode;
    @Size(max = 200)
    @Column(name = "PROGRAM_CODE")
    private String programCode;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "EXPIRED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredTime;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "TOTAL_ERROR")
    private Long totalError;

    public Captcha() {
    }

    public Captcha(Long id) {
        this.id = id;
    }

    public Captcha(Long id, String isdn, String captchaCode) {
        this.id = id;
        this.isdn = isdn;
        this.captchaCode = captchaCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
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

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getTotalError() {
        return totalError;
    }

    public void setTotalError(Long totalError) {
        this.totalError = totalError;
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
        if (!(object instanceof Captcha)) {
            return false;
        }
        Captcha other = (Captcha) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.Captcha[ id=" + id + " ]";
    }

}
