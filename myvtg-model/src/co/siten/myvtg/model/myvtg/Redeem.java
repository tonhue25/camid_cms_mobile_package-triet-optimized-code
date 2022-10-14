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
@Table(name = "REDEEM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Redeem.findAll", query = "SELECT r FROM Redeem r")
    , @NamedQuery(name = "Redeem.findById", query = "SELECT r FROM Redeem r WHERE r.id = :id")
    , @NamedQuery(name = "Redeem.findByIsdn", query = "SELECT r FROM Redeem r WHERE r.isdn = :isdn")
    , @NamedQuery(name = "Redeem.findByAmount", query = "SELECT r FROM Redeem r WHERE r.amount = :amount")
    , @NamedQuery(name = "Redeem.findByExpiredDate", query = "SELECT r FROM Redeem r WHERE r.expiredDate = :expiredDate")
    , @NamedQuery(name = "Redeem.findByStatus", query = "SELECT r FROM Redeem r WHERE r.status = :status")
    , @NamedQuery(name = "Redeem.findByCreateDate", query = "SELECT r FROM Redeem r WHERE r.createDate = :createDate")
    , @NamedQuery(name = "Redeem.findByType", query = "SELECT r FROM Redeem r WHERE r.type = :type")
    , @NamedQuery(name = "Redeem.findByData", query = "SELECT r FROM Redeem r WHERE r.data = :data")
    , @NamedQuery(name = "Redeem.findByRedeemTmp1", query = "SELECT r FROM Redeem r WHERE r.redeemTmp1 = :redeemTmp1")})
public class Redeem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "REDEEM_SEQ_GENERATOR", sequenceName = "REDEEM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REDEEM_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Size(max = 20)
    @Column(name = "ISDN")
    private String isdn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "EXPIRED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "TYPE")
    private Long type;
    @Size(max = 100)
    @Column(name = "DATA")
    private String data;
    @Size(max = 500)
    @Column(name = "REDEEM_TMP_1")
    private String redeemTmp1;

    public Redeem() {
    }

    public Redeem(Long id) {
        this.id = id;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRedeemTmp1() {
        return redeemTmp1;
    }

    public void setRedeemTmp1(String redeemTmp1) {
        this.redeemTmp1 = redeemTmp1;
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
        if (!(object instanceof Redeem)) {
            return false;
        }
        Redeem other = (Redeem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.Redeem[ id=" + id + " ]";
    }

}
