/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.ResponseSuccessBean;
import java.util.LinkedHashMap;

/**
 *
 * @author namdh1
 */

public interface CustomerService {

    
    public void wsSearchNumberToBuy(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String lang) throws Exception;
    public void wsDoLoginByGetCode(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String lang) throws Exception;
    public void wsVerifyByCode(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String lang) throws Exception;
    public void wsGetProfileByIsdn(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String lang) throws Exception;
    public void wsUpdateInfoByUser(String xmlParam, LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String lang) throws Exception;
    
    public void wsReserveNumber(LinkedHashMap<String, Object> params, ResponseSuccessBean bean, String lang) throws Exception;
}
