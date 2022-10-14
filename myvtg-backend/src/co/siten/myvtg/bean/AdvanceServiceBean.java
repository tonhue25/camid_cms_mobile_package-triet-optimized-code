package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AdvanceServiceBean {
	private String servicePage;
	@JsonIgnore
	private Integer state;

	public String getServicePage() {
		return servicePage;
	}

	public void setServicePage(String servicePage) {
		this.servicePage = servicePage;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public AdvanceServiceBean(String servicePage, Integer state) {
		super();
		this.servicePage = servicePage;
		this.state = state;
	}

}
