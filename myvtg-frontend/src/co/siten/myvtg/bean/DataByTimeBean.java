package co.siten.myvtg.bean;

import java.util.Date;

public class DataByTimeBean {
	private Date time;
	private Long total;
	
	public DataByTimeBean(Date time, Long total) {
		super();
		this.time = time;
		this.total = total;
	}
	public Date getTime() {
		return time;
	}
	public Long getTotal() {
		return total;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
}
