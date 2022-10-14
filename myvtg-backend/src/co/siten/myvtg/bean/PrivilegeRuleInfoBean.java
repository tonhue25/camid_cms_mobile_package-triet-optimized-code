package co.siten.myvtg.bean;

public class PrivilegeRuleInfoBean {
	private String imgDesUrl;
	private String content;
	
	
	public PrivilegeRuleInfoBean() {
	}
	public String getImgDesUrl() {
		return imgDesUrl;
	}
	public void setImgDesUrl(String imgDesUrl) {
		this.imgDesUrl = imgDesUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public PrivilegeRuleInfoBean(String imgDesUrl, String content) {
		this.imgDesUrl = imgDesUrl;
		this.content = content;
	}
	

}
