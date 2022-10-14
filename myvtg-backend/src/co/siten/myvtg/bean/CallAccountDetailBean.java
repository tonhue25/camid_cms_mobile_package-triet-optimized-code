package co.siten.myvtg.bean;

import java.util.List;

public class CallAccountDetailBean {
	private ProvisionCallBean mainAcc;
	private ProvisionCallBean promAcc;
	private List<ProvisionOtherBean> others;

	public ProvisionCallBean getMainAcc() {
		return mainAcc;
	}

	public void setMainAcc(ProvisionCallBean mainAcc) {
		this.mainAcc = mainAcc;
	}

	public ProvisionCallBean getPromAcc() {
		return promAcc;
	}

	public void setPromAcc(ProvisionCallBean promAcc) {
		this.promAcc = promAcc;
	}

	public List<ProvisionOtherBean> getOthers() {
		return others;
	}

	public void setOthers(List<ProvisionOtherBean> others) {
		this.others = others;
	}

}
