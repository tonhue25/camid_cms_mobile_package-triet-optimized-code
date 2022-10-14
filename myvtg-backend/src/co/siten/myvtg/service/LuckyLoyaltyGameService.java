package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

/**
 *
 * @author daibq
 *
 */
public interface LuckyLoyaltyGameService {

    public BaseResponseBean getAuthenkey(RequestBean request, String language);

    public BaseResponseBean loginByToken(RequestBean request, String language);

    public BaseResponseBean changeLoyalty(RequestBean request, String language);

    public BaseResponseBean logLuckyLoyalty(RequestBean request, String language);

    public BaseResponseBean telecomMunicationsAward(RequestBean request, String language);

    public BaseResponseBean artifactsAward(RequestBean request, String language);

    public BaseResponseBean giftCategory(RequestBean request, String language);

    public BaseResponseBean getFbInfo(RequestBean request, String language);

    public BaseResponseBean saveFbInfo(RequestBean request, String language);

    public BaseResponseBean getShop(RequestBean request, String language);

    public BaseResponseBean getPointFreeFB(RequestBean request, String language);

    public BaseResponseBean saveShareFb(RequestBean request, String language);
    
    public BaseResponseBean checkUseGame(RequestBean request, String language);
    public BaseResponseBean telecomMunicationsAwardForFriend(RequestBean var1, String var2);
}
