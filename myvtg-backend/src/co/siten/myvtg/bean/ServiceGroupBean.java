package co.siten.myvtg.bean;

import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * 
 * @author thomc
 *
 */
public class ServiceGroupBean {

	private String groupName;
	private String groupCode;
	
	private List<ServiceBean> services;

	public ServiceGroupBean(String groupName, String groupCode, ServiceBean service) {
		super();
		if (services == null)
			services = new Vector<>();
		services.add(service);
		this.groupName = groupName;
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public List<ServiceBean> getServices() {
		return services;
	}

	public void setServices(List<ServiceBean> services) {
		this.services = services;
	}

}
