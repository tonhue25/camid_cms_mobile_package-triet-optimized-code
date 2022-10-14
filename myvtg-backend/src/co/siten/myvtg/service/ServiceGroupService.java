package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

public interface ServiceGroupService{

    BaseResponseBean getServiceGroups(RequestBean request, String language);

    BaseResponseBean addOrUpdateServiceGroup(RequestBean request, String language, String username);


    BaseResponseBean changeActive(Long id, String language,String username);

    BaseResponseBean delete(Long id, String language,String username);
}
