/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

/**
 *
 * @author buiquangdai
 */
public interface LoyaltyRoutingService {
     public BaseResponseBean loyaltyRoutingBusiness(RequestBean request, String language);

     public BaseResponseBean addDonatePackage(RequestBean request, String language);

     public BaseResponseBean editDonatePackage(RequestBean request, String language);

     public BaseResponseBean deleteDonatePackage(RequestBean request, String language);

     public BaseResponseBean searchDonatePackage(RequestBean request, String language);

     public BaseResponseBean getDonatePackage(RequestBean request, String language);

     public BaseResponseBean getAllDonatePackageWeb(RequestBean request, String language);

     public BaseResponseBean getAllDonatePackageApp(RequestBean request, String language);

     public BaseResponseBean addDonatePackagePrice(RequestBean request, String language);

     public BaseResponseBean editDonatePackagePrice(RequestBean request, String language);

     public BaseResponseBean deleteDonatePackagePrice(RequestBean request, String language);

     public BaseResponseBean getAllDonatePackagePrice(RequestBean request, String language);

     public BaseResponseBean addDiscount(RequestBean request, String language);

     public BaseResponseBean editDiscount(RequestBean request, String language);

     public BaseResponseBean deleteDiscount(RequestBean request, String language);

     public BaseResponseBean getDiscount(RequestBean request, String language);

     public BaseResponseBean getAllDiscount(RequestBean request, String language);

     public BaseResponseBean searchDiscount(RequestBean request, String language);

     public BaseResponseBean addEmoneyWallet(RequestBean request, String language);

     public BaseResponseBean editEmoneyWallet(RequestBean request, String language);

     public BaseResponseBean deleteEmoneyWallet(RequestBean request, String language);

     public BaseResponseBean searchEmoneyWallet(RequestBean request, String language);

     public BaseResponseBean getEmoneyWallet(RequestBean request, String language);

     public BaseResponseBean getAllEmoneyWallet(RequestBean request, String language);

     public BaseResponseBean donateGiftPackage(RequestBean request, String language);

     public BaseResponseBean searchDonateTransaction(RequestBean request, String language);

     public BaseResponseBean getDonateTransaction(RequestBean request, String language);

     public BaseResponseBean getAllDonateTransaction(RequestBean request, String language);

     public BaseResponseBean exportDonateTransaction(RequestBean request, String language);

     public BaseResponseBean addChannelDiscountDetail(RequestBean request, String language);

     public BaseResponseBean editOrAddChannel(RequestBean request, String language);

     public BaseResponseBean getListChannel(RequestBean request, String language);

     public BaseResponseBean getListPaymentMethod(RequestBean request, String language);

     public BaseResponseBean getUnitPaymentMethod(RequestBean request, String language);
     
     public BaseResponseBean getAllChannelDiscountDetail(RequestBean request, String language);
     
     public BaseResponseBean getChannelDetail(RequestBean request, String language);
     
     public BaseResponseBean addChannelCMS(RequestBean request, String language);
     
     public BaseResponseBean updateChannelCMS(RequestBean request, String language);
     
     public BaseResponseBean wsSearchChannel(RequestBean request, String language);
     
     public BaseResponseBean wsDeleteDiscountChannel(RequestBean request, String language);
}
