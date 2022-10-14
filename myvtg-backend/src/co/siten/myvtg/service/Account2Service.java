package co.siten.myvtg.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import co.siten.myvtg.bean.AreaBean;
import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.NewsBean;
import co.siten.myvtg.bean.NewsDetailBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.model.myvtg.Hobby;

/**
 *
 * @author thomc
 *
 */
public interface Account2Service {

    public List<Hobby> wsGetHobbies(String language, ResponseSuccessBean bean);

    public void wsUpdateSubInfo(String isdn, BigDecimal jobId, BigDecimal hobbyId, String email,
            ResponseSuccessBean bean);

    public void wsUpdateSubInfo(String isdn, BigDecimal jobId, BigDecimal hobbyId, String email, String avatar,
            ResponseSuccessBean bean);

    public void wsGetNearestStores(BigDecimal longitude, BigDecimal latitude, ResponseSuccessBean bean);

    public List<AreaBean> wsGetProvinces(ResponseSuccessBean bean);

    public List<AreaBean> wsGetDistricts(String provinceId, ResponseSuccessBean bean);

    public List<AreaBean> wsGetPrecincts(String provinceId, String districtId, ResponseSuccessBean bean);

    public void wsFindStoreByAddr(BigDecimal longitude, BigDecimal latitude, String provinceId, String districtId,
            ResponseSuccessBean bean);

    public NewsDetailBean wsGetNewsDetail(String newsId, ResponseSuccessBean bean);

    public List<NewsBean> wsGetNews(String language, Integer pageSize, Integer pageNum, ResponseSuccessBean bean);

    public void wsGetExperienceLink3G4G(String language, Integer adBannerLimit, Integer expLimit,
            ResponseSuccessBean bean);

    public void wsGetAllApps(String language, Integer limit, Integer bannerLimit, ResponseSuccessBean bean);

    public void wsGetCareers(String language, ResponseSuccessBean bean);

    public void wsGetNewPromotions(String language, Integer pageSize, Integer pageNum, ResponseSuccessBean bean);

    public void wsGetPromotionInfo(String language, String packageCode, ResponseSuccessBean bean);

    public Object getAppParam(String name);

    public void wsGetChangePostageRule(ResponseSuccessBean bean);

    public void wsGetSubPrivilegeInfo(String isdn, ResponseSuccessBean bean);

    public void wsGetGiftsByProvince(Integer provinceId, ResponseSuccessBean bean);

    public void wsGetPrivilegeRuleInfo(ResponseSuccessBean bean);

    public void wsDoExchangeGift(String isdn, String subType, String giftCode, Integer provinceId, Integer districtId,
            Integer precinctId, String addr, Date time, BigDecimal markExchange, ResponseSuccessBean bean);

    public void wsGetAdvancedServiceInfo(String isdn, String language, String serviceCode,
            ResponseSuccessBean bean);

    public void wsGetServiceFuntionPage(String language, String serviceCode, String functionPageCode,
            ResponseSuccessBean bean);

    public List<AreaBean> wsGetProvinces(String language, ResponseSuccessBean bean);

    public List<AreaBean> wsGetDistricts(String language, String provinceId, ResponseSuccessBean bean);

    public BaseResponseBean updateSocialNetwork(RequestBean request, String language);

    public BaseResponseBean wsLoginCMSEsport(RequestBean request, String language);
}
