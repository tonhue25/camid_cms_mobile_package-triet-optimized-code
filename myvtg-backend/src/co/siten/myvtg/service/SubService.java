/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.model.myvtg.Sub;
import java.util.Map;

/**
 *
 * @author duyth
 */
public interface SubService {

    public String getAvatar(String isdn) throws Exception;

    public void updateSub(Sub s) throws Exception;
    
    public Map<String, Object> getUserInfo(String isdn);
    
    //partner 7 middle gate call to BCCS
    public BaseResponseBean getSubOTP(RequestBean request, String language);
    
    public BaseResponseBean checkSubscriberIsdn(RequestBean request, String language);
    
    public BaseResponseBean getSubscriberInforCusByIsdn(RequestBean request, String language);
    
    public BaseResponseBean updateSubscriberCustomerInfo(RequestBean request, String language);
    
    public BaseResponseBean detectOCRFromImage(RequestBean request, String language);
        
    public BaseResponseBean updateResponseCusInfor(RequestBean request, String language);

    /*-----------------------*/
    BaseResponseBean getSubServices(RequestBean request, String language);

    BaseResponseBean addOrUpdateSubService(RequestBean request, String language, String username);


    BaseResponseBean changeActive(Long id, String language,String username);

    BaseResponseBean delete(Long id, String language,String username);
    
}
