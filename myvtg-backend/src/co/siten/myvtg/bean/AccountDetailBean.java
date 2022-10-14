package co.siten.myvtg.bean;

import java.util.List;

public class AccountDetailBean {
	private String title;
	private List<AccountDetailValueBean> values;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AccountDetailValueBean> getValues() {
		return values;
	}

	public void setValues(List<AccountDetailValueBean> values) {
		this.values = values;
	}

}
