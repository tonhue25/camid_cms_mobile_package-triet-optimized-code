package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MAP_SER_TYPE_PRODUCT database table.
 * 
 */
@Entity
@Table(name="MAP_SER_TYPE_PRODUCT")
@NamedQuery(name="MapSerTypeProduct.findAll", query="SELECT m FROM MapSerTypeProduct m")
public class MapSerTypeProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	private String code;

	private String name;

	@Column(name="OLD_CODE")
	private String oldCode;

	@Column(name="SER_TYPE")
	private String serType;

	public MapSerTypeProduct() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOldCode() {
		return this.oldCode;
	}

	public void setOldCode(String oldCode) {
		this.oldCode = oldCode;
	}

	public String getSerType() {
		return this.serType;
	}

	public void setSerType(String serType) {
		this.serType = serType;
	}

}