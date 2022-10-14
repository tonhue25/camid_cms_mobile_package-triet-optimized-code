package co.siten.myvtg.bean;

import java.util.ArrayList;

public class AdvertBannerAppBean {
	private ArrayList<AdvertBannerBean> adBanner;
	private ArrayList<ApplicationBean> apps;
	public ArrayList<AdvertBannerBean> getAdBanner() {
		return adBanner;
	}
	public void setAdBanner(ArrayList<AdvertBannerBean> adBanner) {
		this.adBanner = adBanner;
	}
	public ArrayList<ApplicationBean> getApps() {
		return apps;
	}
	public void setApps(ArrayList<ApplicationBean> apps) {
		this.apps = apps;
	}
	public AdvertBannerAppBean(ArrayList<AdvertBannerBean> adBanner, ArrayList<ApplicationBean> apps) {
		super();
		this.adBanner = adBanner;
		this.apps = apps;
	}
	

}
