package co.siten.myvtg.service;


import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

public interface ServiceOfService  {

    BaseResponseBean getServices(RequestBean request, String language);

    BaseResponseBean addOrUpdateService(RequestBean request, String language, String username);

    BaseResponseBean changeActive(Long id, String language,String username);

    BaseResponseBean deleteService(Long id, String language,String username);
}
