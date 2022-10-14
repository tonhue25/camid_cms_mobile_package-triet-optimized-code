package co.siten.myvtg.model.myvtg;


import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the "USERS_AUTHORITY" database table.
 * for function login cms esport
 */
@Entity
@Table(name = "\"USERS_AUTHORITY\"")
@NamedQuery(name = "UserAuthority.findAll", query = "SELECT ua FROM UserAuthority ua")
public class UserAuthority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "AUTHORITY_NAME")
    private String authorityName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }
}
