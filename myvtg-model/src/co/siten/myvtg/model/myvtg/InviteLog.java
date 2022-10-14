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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "INVITE_LOG")
@XmlRootElement
public class InviteLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "INVITE_LOG_SEQ_GENERATOR", sequenceName = "INVITE_LOG_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVITE_LOG_SEQ_GENERATOR")
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ISDN_SENDER")
    private String isdnSender;
    @Basic(optional = false)
    @Column(name = "ISDN_RECIVER")
    private String isdnReciver;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS_LOGIN")
    private Long statusLogin;

    public InviteLog() {
    }

    public InviteLog(Long id) {
        this.id = id;
    }

    public InviteLog(Long id, String isdnSender, String isdnReciver) {
        this.id = id;
        this.isdnSender = isdnSender;
        this.isdnReciver = isdnReciver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsdnSender() {
        return isdnSender;
    }

    public void setIsdnSender(String isdnSender) {
        this.isdnSender = isdnSender;
    }

    public String getIsdnReciver() {
        return isdnReciver;
    }

    public void setIsdnReciver(String isdnReciver) {
        this.isdnReciver = isdnReciver;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStatusLogin() {
        return statusLogin;
    }

    public void setStatusLogin(Long statusLogin) {
        this.statusLogin = statusLogin;
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
        if (!(object instanceof InviteLog)) {
            return false;
        }
        InviteLog other = (InviteLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mymetfone.InviteLog[ id=" + id + " ]";
    }

}
