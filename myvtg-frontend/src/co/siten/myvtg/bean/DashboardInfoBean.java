package co.siten.myvtg.bean;

import java.util.List;

public class DashboardInfoBean {
	private Long subTotal;
	private Long newSubTotal;
	private Long activeSubTotal;
	private Long errorTotal;
	private List<DataByTimeBean> subList;
	private List<DataByTimeBean> requestList;
	private List<DataByStringBean> osList;

	public Long getSubTotal() {
		return subTotal;
	}

	public Long getNewSubTotal() {
		return newSubTotal;
	}

	public Long getActiveSubTotal() {
		return activeSubTotal;
	}

	public Long getErrorTotal() {
		return errorTotal;
	}

	public List<DataByTimeBean> getSubList() {
		return subList;
	}

	public List<DataByTimeBean> getRequestList() {
		return requestList;
	}

	public List<DataByStringBean> getOsList() {
		return osList;
	}

	public void setSubTotal(Long subTotal) {
		this.subTotal = subTotal;
	}

	public void setNewSubTotal(Long newSubTotal) {
		this.newSubTotal = newSubTotal;
	}

	public void setActiveSubTotal(Long activeSubTotal) {
		this.activeSubTotal = activeSubTotal;
	}

	public void setErrorTotal(Long errorTotal) {
		this.errorTotal = errorTotal;
	}

	public void setSubList(List<DataByTimeBean> subList) {
		this.subList = subList;
	}

	public void setRequestList(List<DataByTimeBean> requestList) {
		this.requestList = requestList;
	}

	public void setOsList(List<DataByStringBean> osList) {
		this.osList = osList;
	}

}
