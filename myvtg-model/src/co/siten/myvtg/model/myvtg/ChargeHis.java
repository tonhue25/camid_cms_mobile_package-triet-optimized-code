package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the CHARGE_HIS database table.
 * 
 */
@Entity
@Table(name="CHARGE_HIS")
@NamedQuery(name="ChargeHis.findAll", query="SELECT c FROM ChargeHis c")
public class ChargeHis implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "CHARGE_HIS_SEQ", allocationSize = 1)
	@Column(name="CHARGE_HIS_ID")
	private BigDecimal chargeHisId;

	private BigDecimal fee;

	private String msisdn;

	@Column(name="PIN_NUMBER")
	private String pinNumber;

	@Column(name="REFILL_AMOUNT")
	private BigDecimal refillAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="REFILL_DATE")
	private Date refillDate;

	@Column(name="REFILL_ISDN")
	private String refillIsdn;

	@Column(name="SERI_NUMBER")
	private String seriNumber;

	@Column(name="SERVICE_ID")
	private String serviceId;

	@Column(name="USER_ID")
	private BigDecimal userId;
        
        @Column(name="RESULT")
        private String result;
        
        @Column(name="TRANSACTION_ID")
        private BigDecimal transactionLogId;
        
        @Column(name="RESULT_CODE")
        private Integer resultCode;

	public ChargeHis() {
	}

	public BigDecimal getChargeHisId() {
		return this.chargeHisId;
	}

	public void setChargeHisId(BigDecimal chargeHisId) {
		this.chargeHisId = chargeHisId;
	}

	public BigDecimal getFee() {
		return this.fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getPinNumber() {
		return this.pinNumber;
	}

	public void setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
	}

	public BigDecimal getRefillAmount() {
		return this.refillAmount;
	}

	public void setRefillAmount(BigDecimal refillAmount) {
		this.refillAmount = refillAmount;
	}

	public Date getRefillDate() {
		return this.refillDate;
	}

	public void setRefillDate(Date refillDate) {
		this.refillDate = refillDate;
	}

	public String getRefillIsdn() {
		return this.refillIsdn;
	}

	public void setRefillIsdn(String refillIsdn) {
		this.refillIsdn = refillIsdn;
	}

	public String getSeriNumber() {
		return this.seriNumber;
	}

	public void setSeriNumber(String seriNumber) {
		this.seriNumber = seriNumber;
	}

	public String getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

        public String getResult() {
                return result;
        }

        public void setResult(String result) {
                this.result = result;
        }

    public BigDecimal getTransactionLogId() {
        return transactionLogId;
    }

    public void setTransactionLogId(BigDecimal transactionLogId) {
        this.transactionLogId = transactionLogId;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }
}