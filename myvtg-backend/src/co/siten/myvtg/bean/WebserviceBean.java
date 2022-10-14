package co.siten.myvtg.bean;

import co.siten.myvtg.model.myvtg.Action;
import co.siten.myvtg.model.myvtg.Webservice;

public class WebserviceBean {
	private Webservice webservice;
	private Action action;
	
	public WebserviceBean(Webservice webservice, Action action) {
		super();
		this.webservice = webservice;
		this.action = action;
	}

	public Webservice getWebservice() {
		return webservice;
	}

	public Action getAction() {
		return action;
	}

	public void setWebservice(Webservice webservice) {
		this.webservice = webservice;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
