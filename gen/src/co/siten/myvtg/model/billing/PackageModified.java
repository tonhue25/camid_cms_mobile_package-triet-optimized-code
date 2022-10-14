package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PACKAGE_MODIFIED database table.
 * 
 */
@Entity
@Table(name="PACKAGE_MODIFIED")
@NamedQuery(name="PackageModified.findAll", query="SELECT p FROM PackageModified p")
public class PackageModified implements Serializable {
	private static final long serialVersionUID = 1L;

	@Lob
	@Column(name="\"BODY\"")
	private String body;

	@Temporal(TemporalType.DATE)
	@Column(name="CHANGE_DATETIME")
	private Date changeDatetime;

	@Column(name="PACKAGE_MODIFIED_ID")
	private BigDecimal packageModifiedId;

	@Column(name="PACKAGE_NAME")
	private String packageName;

	@Lob
	private String spec;

	@Column(name="USER_ID")
	private String userId;

	public PackageModified() {
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getChangeDatetime() {
		return this.changeDatetime;
	}

	public void setChangeDatetime(Date changeDatetime) {
		this.changeDatetime = changeDatetime;
	}

	public BigDecimal getPackageModifiedId() {
		return this.packageModifiedId;
	}

	public void setPackageModifiedId(BigDecimal packageModifiedId) {
		this.packageModifiedId = packageModifiedId;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getSpec() {
		return this.spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}