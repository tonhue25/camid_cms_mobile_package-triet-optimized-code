package co.siten.myvtg.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ProvisionOtherBean {
	private String name;
        private String value;
	private Date expiredDate;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

        public String getValue() {
                return value;
        }

        public void setValue(String value) {
                this.value = value;
        }
}
