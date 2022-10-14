package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class GetCallAccountDetailPreBean {
	private BigDecimal prePost;
	private BigDecimal debPost;
	private BigDecimal debPreMonthPost;

	public BigDecimal getPrePost() {
		return prePost;
	}

	public void setPrePost(BigDecimal prePost) {
		this.prePost = prePost;
	}

	public BigDecimal getDebPost() {
		return debPost;
	}

	public void setDebPost(BigDecimal debPost) {
		this.debPost = debPost;
	}

	public BigDecimal getDebPreMonthPost() {
		return debPreMonthPost;
	}

	public void setDebPreMonthPost(BigDecimal debPreMonthPost) {
		this.debPreMonthPost = debPreMonthPost;
	}

}
