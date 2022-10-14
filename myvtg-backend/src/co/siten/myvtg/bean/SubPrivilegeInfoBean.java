package co.siten.myvtg.bean;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubPrivilegeInfoBean {
	private Integer rating;
	@JsonIgnore
	private String ratingString;
	private BigDecimal scoreRating;
	private BigDecimal score;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public BigDecimal getScoreRating() {
		return scoreRating;
	}

	public void setScoreRating(BigDecimal scoreRating) {
		this.scoreRating = scoreRating;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getRatingString() {
		return ratingString;
	}

	public void setRatingString(String ratingString) {
		this.ratingString = ratingString;
	}

	public SubPrivilegeInfoBean(String ratingString, BigDecimal scoreRating, BigDecimal score) {
		super();
		this.ratingString = ratingString;
		this.scoreRating = scoreRating;
		this.score = score;
		if (this.ratingString != null)
			this.rating = Integer.parseInt(ratingString);
	}

}
