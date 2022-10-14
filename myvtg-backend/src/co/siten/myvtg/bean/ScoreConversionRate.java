package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class ScoreConversionRate {
	private Integer score;
	private BigDecimal money;
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public ScoreConversionRate() {
		super();
	}
	public ScoreConversionRate(Integer score, BigDecimal money) {
		super();
		this.score = score;
		this.money = money;
	}
	
}
