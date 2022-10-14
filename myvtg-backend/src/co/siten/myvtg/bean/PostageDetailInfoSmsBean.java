package co.siten.myvtg.bean;
/**
 * 
 * @author thomc
 *
 */
public class PostageDetailInfoSmsBean {
	private String isdn;
	private String start_time;
	private long value;

	public String getIsdn() {
		return isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

}
