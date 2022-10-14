package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the COPY_IMUZIK database table.
 * 
 */
@Entity
@Table(name="COPY_IMUZIK")
@NamedQuery(name="CopyImuzik.findAll", query="SELECT c FROM CopyImuzik c")
public class CopyImuzik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="COST_CHARGED")
	private String costCharged;

	@Column(name="CP_CODE")
	private String cpCode;

	@Column(name="DESCRIPTION_ACTION")
	private String descriptionAction;

	private String isdn;

	@Column(name="MUSIC_CODE")
	private String musicCode;

	@Column(name="PAY_BY")
	private String payBy;

	private String stt;

	@Column(name="SUB_TYPE")
	private String subType;

	@Column(name="TIME_ACTION")
	private Object timeAction;

	@Column(name="TIME_CDR_CREATED")
	private Object timeCdrCreated;

	@Column(name="TYPE_ACTION")
	private String typeAction;

	@Column(name="TYPE_CHARGE")
	private String typeCharge;

	@Column(name="TYPE_COPY")
	private String typeCopy;

	public CopyImuzik() {
	}

	public String getCostCharged() {
		return this.costCharged;
	}

	public void setCostCharged(String costCharged) {
		this.costCharged = costCharged;
	}

	public String getCpCode() {
		return this.cpCode;
	}

	public void setCpCode(String cpCode) {
		this.cpCode = cpCode;
	}

	public String getDescriptionAction() {
		return this.descriptionAction;
	}

	public void setDescriptionAction(String descriptionAction) {
		this.descriptionAction = descriptionAction;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getMusicCode() {
		return this.musicCode;
	}

	public void setMusicCode(String musicCode) {
		this.musicCode = musicCode;
	}

	public String getPayBy() {
		return this.payBy;
	}

	public void setPayBy(String payBy) {
		this.payBy = payBy;
	}

	public String getStt() {
		return this.stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public Object getTimeAction() {
		return this.timeAction;
	}

	public void setTimeAction(Object timeAction) {
		this.timeAction = timeAction;
	}

	public Object getTimeCdrCreated() {
		return this.timeCdrCreated;
	}

	public void setTimeCdrCreated(Object timeCdrCreated) {
		this.timeCdrCreated = timeCdrCreated;
	}

	public String getTypeAction() {
		return this.typeAction;
	}

	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

	public String getTypeCharge() {
		return this.typeCharge;
	}

	public void setTypeCharge(String typeCharge) {
		this.typeCharge = typeCharge;
	}

	public String getTypeCopy() {
		return this.typeCopy;
	}

	public void setTypeCopy(String typeCopy) {
		this.typeCopy = typeCopy;
	}

}