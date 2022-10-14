package co.siten.myvtg.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostageInfoBean {

	private BigDecimal monthlyFee;
	private BigDecimal basic;
	private BigDecimal prom;
	private BigDecimal callFee;
	private BigDecimal smsFee;
	private BigDecimal otherFee;
	private BigDecimal dataFee;
	private BigDecimal vasFee;
	private Long callRc;
	private Long smsRc;
	private Long otherRc;
	private Long dataRc;
	private Long vasRc;

	@JsonIgnore
	private BigDecimal basicBalance;
	@JsonIgnore
	private BigDecimal promBalance;
	@JsonIgnore
	private BigDecimal totalCharge;
	@JsonIgnore
	private Long count;

	public Long getCallRc() {
		return callRc;
	}

	public void setCallRc(Long callRc) {
		this.callRc = callRc;
	}

	public Long getSmsRc() {
		return smsRc;
	}

	public void setSmsRc(Long smsRc) {
		this.smsRc = smsRc;
	}

	public Long getOtherRc() {
		return otherRc;
	}

	public void setOtherRc(Long otherRc) {
		this.otherRc = otherRc;
	}

	
	
	public Long getDataRc() {
		return dataRc;
	}

	public void setDataRc(Long dataRc) {
		this.dataRc = dataRc;
	}

	public Long getVasRc() {
		return vasRc;
	}

	public void setVasRc(Long vasRc) {
		this.vasRc = vasRc;
	}

	@JsonIgnore
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@JsonIgnore
	public BigDecimal getTotalCharge() {
		if (totalCharge == null)
			this.totalCharge = BigDecimal.ZERO;
		return totalCharge;
	}

	public void setTotalCharge(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}
        
	@JsonIgnore
	public BigDecimal getBasicBalance() {
		if (basicBalance == null)
			basicBalance = BigDecimal.ZERO;
		return basicBalance;
	}

	@JsonIgnore
	public BigDecimal getPromBalance() {
		if (promBalance == null)
			promBalance = BigDecimal.ZERO;
		return promBalance;
	}

	public void setBasicBalance(BigDecimal basicBalance) {
		this.basicBalance = basicBalance;
	}

	public void setPromBalance(BigDecimal promBalance) {
		this.promBalance = promBalance;
	}

	public BigDecimal getMonthlyFee() {
		if (monthlyFee == null)
			monthlyFee = BigDecimal.ZERO;
		return monthlyFee;
	}

	public void setMonthlyFee(BigDecimal monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public BigDecimal getBasic() {
		if (basic == null)
			basic = BigDecimal.ZERO;
		return basic;
	}

	public void setBasic(BigDecimal basic) {
		this.basic = basic;
	}

	public BigDecimal getProm() {
		if (prom == null)
			prom = BigDecimal.ZERO;
		return prom;
	}

	public void setProm(BigDecimal prom) {
		this.prom = prom;
	}

	public BigDecimal getCallFee() {
		if (callFee == null)
			callFee = BigDecimal.ZERO;
		return callFee;
	}

	public void setCallFee(BigDecimal callFee) {
		this.callFee = callFee;
	}

	public BigDecimal getSmsFee() {
		if (smsFee == null)
			smsFee = BigDecimal.ZERO;
		return smsFee;
	}

	public void setSmsFee(BigDecimal smsFee) {
		this.smsFee = smsFee;
	}

	public BigDecimal getOtherFee() {
		if (otherFee == null)
			otherFee = BigDecimal.ZERO;
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}
        
        
        public BigDecimal getDataFee() {
        	if (dataFee == null)
        		dataFee = BigDecimal.ZERO;
		return dataFee;
	}

	public void setDataFee(BigDecimal dataFee) {
		this.dataFee = dataFee;
	}

	public BigDecimal getVasFee() {
	 	if (vasFee == null)
	 		vasFee = BigDecimal.ZERO;
		return vasFee;
	}

	public void setVasFee(BigDecimal vasFee) {
		this.vasFee = vasFee;
	}

		// constructors
        public PostageInfoBean() {
		// TODO Auto-generated constructor stub
	}
        
        public PostageInfoBean(BigDecimal totalCharge) {
		this.totalCharge = totalCharge;
	}
        
        public PostageInfoBean(Integer type, Long count, BigDecimal totalCharge) {
                switch(type) {
                    case 0:
                        this.callRc = count;
                        this.callFee = totalCharge;
                        break;
                        
                    case 1:
                        this.smsRc = count;
                        this.smsFee = totalCharge;
                        break;
                        
                    case 2:
                        this.otherRc = count;
                        this.otherFee = totalCharge;
                        break;  
                        
                    case 3:
                        this.dataRc = count;
                        this.dataFee = totalCharge;
                        break;  
                    case 4:
                        this.vasRc = count;
                        this.vasFee = totalCharge;
                        break;  
                }
		
	}
        
        public PostageInfoBean(BigDecimal basicBalance, BigDecimal promBalance) {
		this.basic = basicBalance;
		this.prom = promBalance;
	}

	public PostageInfoBean(BigDecimal basicBalance, BigDecimal promBalance, Long count) {
		this.basic = basicBalance;
		this.prom = promBalance;
		this.count= count;
	}

	public PostageInfoBean(String basicBalance, BigDecimal promBalance) {
		if (basicBalance != null)
			this.basic = new BigDecimal(basicBalance);
		this.prom = promBalance;
	}

	public PostageInfoBean(String basicBalance, String promBalance) {
		if (basicBalance != null)
			this.basic = new BigDecimal(basicBalance);
		if (promBalance != null)
			this.prom = new BigDecimal(promBalance);
	}

	

}
