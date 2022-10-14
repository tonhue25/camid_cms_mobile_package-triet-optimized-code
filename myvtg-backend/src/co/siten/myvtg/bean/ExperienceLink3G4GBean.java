package co.siten.myvtg.bean;

import java.util.ArrayList;

public class ExperienceLink3G4GBean {
	private ArrayList<AdvertBannerBean> adBanner;
	private ArrayList<ExperienceLinkBean> links;

	public ExperienceLink3G4GBean(ArrayList<AdvertBannerBean> adBanner, ArrayList<ExperienceLinkBean> links) {
		super();
		if (!adBanner.isEmpty())
			this.adBanner = adBanner;
		else
			this.adBanner = null;
		if (!links.isEmpty())
			this.links = links;
		else
			this.links = null;
	}

	public ArrayList<AdvertBannerBean> getAdBanner() {
		return adBanner;
	}

	public void setAdBanner(ArrayList<AdvertBannerBean> adBanner) {
		this.adBanner = adBanner;
	}

	public ArrayList<ExperienceLinkBean> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<ExperienceLinkBean> links) {
		this.links = links;
	}

}
