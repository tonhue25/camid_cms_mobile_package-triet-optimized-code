package co.siten.myvtg.service;

import co.siten.myvtg.bean.ResponseSuccessBean;

public interface SubInfoService {
	public void wsGetSubMainInfo(String isdn, ResponseSuccessBean bean) throws Exception;
}
