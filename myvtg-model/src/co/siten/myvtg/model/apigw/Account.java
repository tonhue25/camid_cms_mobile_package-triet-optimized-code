package co.siten.myvtg.model.apigw;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({ @NamedQuery(name = "Account.findAll", query = "select a from Account a") })
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATE")
    private String state;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
