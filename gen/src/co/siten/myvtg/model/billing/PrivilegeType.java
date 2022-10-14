package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PRIVILEGE_TYPE database table.
 * 
 */
@Entity
@Table(name="PRIVILEGE_TYPE")
@NamedQuery(name="PrivilegeType.findAll", query="SELECT p FROM PrivilegeType p")
public class PrivilegeType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="PRIVILEGE_NAME")
	private String privilegeName;

	@Column(name="PRIVILEGE_TYPE")
	private String privilegeType;

	public PrivilegeType() {
	}

	public String getPrivilegeName() {
		return this.privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeType() {
		return this.privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

}