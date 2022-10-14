package co.siten.myvtg.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ProvisionCallBean {
	private BigDecimal money;
	private Date expiredDate;

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

}
