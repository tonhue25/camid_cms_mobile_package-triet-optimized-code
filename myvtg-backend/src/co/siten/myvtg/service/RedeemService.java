/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

/**
 * RedeemService
 *
 * @author partner7
 */
public interface RedeemService {

    public BaseResponseBean checkShowPopUpTet(RequestBean request, String language);

    public BaseResponseBean checkGetGiftTet(RequestBean request, String language);

    public BaseResponseBean redeemGiftTet(RequestBean request, String language);
}
