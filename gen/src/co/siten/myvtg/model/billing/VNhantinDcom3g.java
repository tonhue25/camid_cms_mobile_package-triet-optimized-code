package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the V_NHANTIN_DCOM3G database table.
 * 
 */
@Entity
@Table(name="V_NHANTIN_DCOM3G")
@NamedQuery(name="VNhantinDcom3g.findAll", query="SELECT v FROM VNhantinDcom3g v")
public class VNhantinDcom3g implements Serializable {
	private static final long serialVersionUID = 1L;

	private String isdn;

	@Column(name="NOI_DUNG_TIN_NHAN")
	private String noiDungTinNhan;

	@Column(name="ROW_ID")
	private String rowId;

	public VNhantinDcom3g() {
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getNoiDungTinNhan() {
		return this.noiDungTinNhan;
	}

	public void setNoiDungTinNhan(String noiDungTinNhan) {
		this.noiDungTinNhan = noiDungTinNhan;
	}

	public String getRowId() {
		return this.rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

}