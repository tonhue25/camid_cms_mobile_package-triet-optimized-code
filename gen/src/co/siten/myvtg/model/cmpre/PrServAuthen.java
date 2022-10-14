package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PR_SERV_AUTHEN database table.
 * 
 */
@Entity
@Table(name="PR_SERV_AUTHEN")
@NamedQuery(name="PrServAuthen.findAll", query="SELECT p FROM PrServAuthen p")
public class PrServAuthen implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="IP_COMMING")
	private String ipComming;

	private String pass;

	private String username;

	public PrServAuthen() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIpComming() {
		return this.ipComming;
	}

	public void setIpComming(String ipComming) {
		this.ipComming = ipComming;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}