package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MIG_MAPPING database table.
 * 
 */
@Entity
@Table(name="MIG_MAPPING")
@NamedQuery(name="MigMapping.findAll", query="SELECT m FROM MigMapping m")
public class MigMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	private String description;

	@Column(name="GROUP_TYPE")
	private String groupType;

	@Column(name="NEW_VALUE")
	private String newValue;

	@Column(name="OLD_VALUE")
	private String oldValue;

	private String status;

	@Column(name="\"TYPE\"")
	private String type;

	public MigMapping() {
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getNewValue() {
		return this.newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOldValue() {
		return this.oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}