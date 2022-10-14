/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phuonghc
 */
@Entity
@Table(name = "CODE_FIREDBASE_CONSOLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CodeFiredbaseConsole.findAll", query = "SELECT c FROM CodeFiredbaseConsole c")
})
public class CodeFiredbaseConsole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 150)
    @Column(name = "CONTENT")
    private String content;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 500)
    @Column(name = "CODE_ON_FB_CONSOLE")
    private String codeOnFbConsole;

    public CodeFiredbaseConsole() {
    }

    public CodeFiredbaseConsole(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeOnFbConsole() {
        return codeOnFbConsole;
    }

    public void setCodeOnFbConsole(String codeOnFbConsole) {
        this.codeOnFbConsole = codeOnFbConsole;
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
        if (!(object instanceof CodeFiredbaseConsole)) {
            return false;
        }
        CodeFiredbaseConsole other = (CodeFiredbaseConsole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.siten.myvtg.model.myvtg.CodeFiredbaseConsole[ id=" + id + " ]";
    }

}
