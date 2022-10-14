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
@Table(name = "INVITE_GAME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InviteGame.findAll", query = "SELECT i FROM InviteGame i")
    , @NamedQuery(name = "InviteGame.findById", query = "SELECT i FROM InviteGame i WHERE i.id = :id")
    , @NamedQuery(name = "InviteGame.findByIsdnSender", query = "SELECT i FROM InviteGame i WHERE i.isdnSender = :isdnSender")
    , @NamedQuery(name = "InviteGame.findByIsdnReciver", query = "SELECT i FROM InviteGame i WHERE i.isdnReciver = :isdnReciver")
    , @NamedQuery(name = "InviteGame.findByCreateDate", query = "SELECT i FROM InviteGame i WHERE i.createDate = :createDate")
    , @NamedQuery(name = "InviteGame.findByContent", query = "SELECT i FROM InviteGame i WHERE i.content = :content")
    , @NamedQuery(name = "InviteGame.findByStatus", query = "SELECT i FROM InviteGame i WHERE i.status = :status")
    , @NamedQuery(name = "InviteGame.findByDescription", query = "SELECT i FROM InviteGame i WHERE i.description = :description")
    , @NamedQuery(name = "InviteGame.findByStatusLogin", query = "SELECT i FROM InviteGame i WHERE i.statusLogin = :statusLogin")
    , @NamedQuery(name = "InviteGame.findByProgramCode", query = "SELECT i FROM InviteGame i WHERE i.programCode = :programCode")
    , @NamedQuery(name = "InviteGame.findByFreePlayGame", query = "SELECT i FROM InviteGame i WHERE i.freePlayGame = :freePlayGame")
    , @NamedQuery(name = "InviteGame.findByUpdateDate", query = "SELECT i FROM InviteGame i WHERE i.updateDate = :updateDate")})
public class InviteGame implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "INVITE_GAME_SEQ_GENERATOR", sequenceName = "INVITE_GAME_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVITE_GAME_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ISDN_SENDER")
    private String isdnSender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ISDN_RECIVER")
    private String isdnReciver;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Size(max = 2000)
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "STATUS")
    private Long status;
    @Size(max = 1000)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "STATUS_LOGIN")
    private Long statusLogin;
    @Size(max = 200)
    @Column(name = "PROGRAM_CODE")
    private String programCode;
    @Column(name = "FREE_PLAY_GAME")
    private Long freePlayGame;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    public InviteGame() {
    }

    public InviteGame(Long id) {
        this.id = id;
    }

    public InviteGame(Long id, String isdnSender, String isdnReciver) {
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

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Long getFreePlayGame() {
        return freePlayGame;
    }

    public void setFreePlayGame(Long freePlayGame) {
        this.freePlayGame = freePlayGame;
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
        if (!(object instanceof InviteGame)) {
            return false;
        }
        InviteGame other = (InviteGame) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.InviteGame[ id=" + id + " ]";
    }

}
