package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ADJUSTMENT_ERROR_LOG_DETAIL database table.
 * 
 */
@Entity
@Table(name="ADJUSTMENT_ERROR_LOG_DETAIL")
@NamedQuery(name="AdjustmentErrorLogDetail.findAll", query="SELECT a FROM AdjustmentErrorLogDetail a")
public class AdjustmentErrorLogDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADJUSTMENT_FILE_ID")
	private BigDecimal adjustmentFileId;

	private BigDecimal amount;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

	private String description;

	@Column(name="ERROR_LINE_POSITION")
	private BigDecimal errorLinePosition;

	@Column(name="ERROR_LOG_DETAIL_ID")
	private BigDecimal errorLogDetailId;

	private String isdn;

	public AdjustmentErrorLogDetail() {
	}

	public BigDecimal getAdjustmentFileId() {
		return this.adjustmentFileId;
	}

	public void setAdjustmentFileId(BigDecimal adjustmentFileId) {
		this.adjustmentFileId = adjustmentFileId;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getErrorLinePosition() {
		return this.errorLinePosition;
	}

	public void setErrorLinePosition(BigDecimal errorLinePosition) {
		this.errorLinePosition = errorLinePosition;
	}

	public BigDecimal getErrorLogDetailId() {
		return this.errorLogDetailId;
	}

	public void setErrorLogDetailId(BigDecimal errorLogDetailId) {
		this.errorLogDetailId = errorLogDetailId;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

}