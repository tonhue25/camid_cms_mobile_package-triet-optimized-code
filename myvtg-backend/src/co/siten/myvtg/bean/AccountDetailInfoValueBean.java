package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;

public class AccountDetailInfoValueBean {
	private String title;
	private String value;
	private String exp;
	@JsonIgnore
	private Integer order;
	@JsonIgnore
	private String billStatus;
        
        private BigDecimal realValue;
	private Integer balanceId;
	private int accType;
        private Date expireTime;
        
        private String balanceName;
        
        private String unitLabel;

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public AccountDetailInfoValueBean() {
		super();
	}

	public AccountDetailInfoValueBean(String title, String value, String exp) {
		super();
		this.title = title;
		this.value = value;
		this.exp = exp;
	}

	public String getTitle() {
		if (title != null)
			title = title.trim();
//		if (title != null && billStatus != null)
//			return title + " - " + billStatus;
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

        public BigDecimal getRealValue() {
            return realValue;
        }

        public void setRealValue(BigDecimal realValue) {
            this.realValue = realValue;
        }        

        public Integer getBalanceId() {
            return balanceId;
        }

        public void setBalanceId(Integer balanceId) {
            this.balanceId = balanceId;
        }

        public int getAccType() {
            return accType;
        }

        public void setAccType(int accType) {
            this.accType = accType;
        }

        public Date getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(Date expireTime) {
            this.expireTime = expireTime;
        }

        public String getBalanceName() {
            return balanceName;
        }

        public void setBalanceName(String balanceName) {
            this.balanceName = balanceName;
        }

    public String getUnitLabel() {
        return unitLabel;
    }

    public void setUnitLabel(String unitLabel) {
        this.unitLabel = unitLabel;
    }
          

}
