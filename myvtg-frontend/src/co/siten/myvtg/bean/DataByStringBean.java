package co.siten.myvtg.bean;

public class DataByStringBean {
	private Integer key;
	private Long total;
	
	
	public DataByStringBean(Integer key, Long total) {
		super();
		this.key = key;
		this.total = total;
	}
	public Integer getKey() {
		return key;
	}
	public Long getTotal() {
		return total;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	
}
