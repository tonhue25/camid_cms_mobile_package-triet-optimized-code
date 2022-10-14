package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author thomc
 *
 */
public class PostageDetailInfoBean {
	private String isdn;
	private Integer direction;
	@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Date start_time;
	private BigDecimal duration;
	private BigDecimal value;
	private int total;
	
	

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PostageDetailInfoBean(String isdn, Integer direction, Date start_time, Integer duration,
			BigDecimal basicBalance, BigDecimal prom) {
		super();
		this.isdn = isdn;
		if (direction == null)
			direction = 0;
		this.direction = direction;
		this.start_time = start_time;
		if(duration==null)
			duration =0;
		this.duration = new BigDecimal(duration);
		if (basicBalance == null)
			basicBalance = BigDecimal.ZERO;
		if (prom == null) {
			this.value = basicBalance;
		} else {
			this.value = basicBalance.add(prom);
		}

	}

	public PostageDetailInfoBean(String isdn, Integer direction, Date start_time, BigDecimal duration,
			BigDecimal basicBalance, BigDecimal prom) {
		super();
		this.isdn = isdn;
		if (direction == null)
			direction = 0;
		this.direction = direction;
		this.start_time = start_time;
		if (duration == null)
			duration = BigDecimal.ZERO;
		this.duration = duration;
		if (basicBalance == null)
			basicBalance = BigDecimal.ZERO;
		if (prom == null) {
			this.value = basicBalance;
		} else {
			this.value = basicBalance.add(prom);
		}

	}

	public PostageDetailInfoBean(String isdn, Integer direction, Date start_time, BigDecimal duration,
			BigDecimal totCharge) {
		super();
		this.isdn = isdn;
		if (direction == null)
			direction = 0;
		this.direction = direction;
		this.start_time = start_time;
		if (duration == null)
			duration = BigDecimal.ZERO;
		this.duration = duration;
		if (totCharge == null)
			this.value = BigDecimal.ZERO;
		else
			this.value = totCharge;

	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public String getIsdn() {
		return isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
