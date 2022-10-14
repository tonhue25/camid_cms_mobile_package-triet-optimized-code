package co.siten.myvtg.service;

import co.siten.myvtg.bean.ResponseSuccessBean;

/**
 * 
 * @author thomc
 *
 */
public interface MasterDataService {
	public void wsGetHotNews(String isdn, int limit, String language, ResponseSuccessBean bean);

	public void wsGetJobs(String language, ResponseSuccessBean bean);
}
