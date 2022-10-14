package co.siten.myvtg.bean;
/**
 * 
 * @author thomc
 *
 */
public class PostageDetailInfoOtherBean {
	private String name;
	private String start_time;
	private long value;
	private long volume;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
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
