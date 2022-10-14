/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

/**
 * PassiveNotifyService
 *
 * @author partner7
 */
public interface PassiveNotifyService {

    public BaseResponseBean getGames(RequestBean request, String language);

    public BaseResponseBean getGameCategories(RequestBean request, String language);
    
    public BaseResponseBean getGameCategoriesForCMS(RequestBean request, String language);
    
    public BaseResponseBean getGameById(RequestBean request, String language);
    
    public BaseResponseBean getGameCategoryById(RequestBean request, String language);
}
