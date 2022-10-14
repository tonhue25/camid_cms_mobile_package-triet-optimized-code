package co.siten.myvtg.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.siten.myvtg.dto.BccsGwResponse;
import co.siten.myvtg.dto.UserDTO;
import co.siten.myvtg.model.myvtg.UserAuthority;
import co.siten.myvtg.model.myvtg.Users;
import org.apache.log4j.Logger;
import org.hibernate.cfg.JPAIndexHolder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.siten.myvtg.bean.AdvanceServiceBean;
import co.siten.myvtg.bean.AdvertBannerAppBean;
import co.siten.myvtg.bean.AdvertBannerBean;
import co.siten.myvtg.bean.ApplicationBean;
import co.siten.myvtg.bean.AreaBean;
import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.CareerBean;
import co.siten.myvtg.bean.ExperienceLink3G4GBean;
import co.siten.myvtg.bean.ExperienceLinkBean;
import co.siten.myvtg.bean.FunctionPageServiceBean;
import co.siten.myvtg.bean.GiftBean;
import co.siten.myvtg.bean.NewsBean;
import co.siten.myvtg.bean.NewsDetailBean;
import co.siten.myvtg.bean.PrivilegeRuleInfoBean;
import co.siten.myvtg.bean.PromotionBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.bean.ScoreConversionRate;
import co.siten.myvtg.bean.StoresBean;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.dao.ServiceDao;
import co.siten.myvtg.dao.SubDao;
import co.siten.myvtg.model.myvtg.Hobby;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.MessageUtil;

/**
 *
 * @author thomc
 *
 */
@org.springframework.stereotype.Service("Account2Service")
@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class Account2ServiceImpl implements Account2Service {

    private static final Logger logger = Logger.getLogger(Account2ServiceImpl.class);
    @Autowired
    private Environment environment;
    @Autowired
    MyvtgMasterDataDao myvtgDao;
    @Autowired
    MessageUtil messageUtil;

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    SubDao subDao;

    @Autowired
    private ApiGwServiceGlobal apiGwServiceGlobal;

    @Override
    public List<Hobby> wsGetHobbies(String language, ResponseSuccessBean bean) {
        List<Hobby> hobbies = myvtgDao.getAllHobbies(language);
        if (!hobbies.isEmpty()) {
            bean.setWsResponse(myvtgDao.getAllHobbies(language));
        } else {
            bean.setWsResponse(null);
        }
        return null;
    }

    @Override
    public void wsUpdateSubInfo(String isdn, BigDecimal jobId, BigDecimal hobbyId, String email,
            ResponseSuccessBean bean) {
        if (jobId == null && hobbyId == null) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            return;
        }

        boolean result = myvtgDao.updateSubInfo(isdn, jobId == null ? BigDecimal.ZERO : jobId,
                hobbyId == null ? BigDecimal.ZERO : hobbyId, email == null ? "" : email);
        if (result) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
        } else {
            bean.setErrorCode(Constants.ERROR_CODE_1);
        }
    }

    @Override
    public void wsUpdateSubInfo(String isdn, BigDecimal jobId, BigDecimal hobbyId, String email, String avatar,
            ResponseSuccessBean bean) {
        if (jobId == null && hobbyId == null) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
            return;
        }

        boolean result = myvtgDao.updateSubInfo(isdn, jobId == null ? BigDecimal.ZERO : jobId,
                hobbyId == null ? BigDecimal.ZERO : hobbyId, email == null ? "" : email, avatar == null ? "" : avatar);
        if (result) {
            bean.setErrorCode(Constants.ERROR_CODE_0);
        } else {
            bean.setErrorCode(Constants.ERROR_CODE_1);
        }
    }

    @Override
    public void wsGetNearestStores(BigDecimal longitude, BigDecimal latitude, ResponseSuccessBean bean) {
        List<StoresBean> stores = myvtgDao.getNearestStores(longitude, latitude, 10f, false);
        if (!stores.isEmpty()) {
            stores.forEach((store) -> {
                String formattedIsdn = DataUtil.formatIsdn0With3Diditals(store.getIsdn());
                store.setIsdn(formattedIsdn);
            });
            bean.setWsResponse(stores);
        } else {
            bean.setWsResponse(null);
        }
    }

    @Override
    public List<AreaBean> wsGetProvinces(ResponseSuccessBean bean) {
        List<AreaBean> provinces = myvtgDao.getProvinces();
        if (!provinces.isEmpty()) {
            bean.setWsResponse(provinces);
        } else {
            bean.setWsResponse(null);
        }
        return provinces;
    }

    @Override
    public List<AreaBean> wsGetDistricts(String provinceId, ResponseSuccessBean bean) {
        List<AreaBean> districts = myvtgDao.getDistricts(provinceId);
        if (!districts.isEmpty()) {
            bean.setWsResponse(districts);
        } else {
            bean.setWsResponse(null);
        }
        return districts;
    }

    @Override
    public List<AreaBean> wsGetPrecincts(String provinceId, String districtId, ResponseSuccessBean bean) {
        List<AreaBean> precincts = myvtgDao.getPrecincts(provinceId, districtId);
        if (!precincts.isEmpty()) {
            bean.setWsResponse(precincts);
        } else {
            bean.setWsResponse(null);
        }
        return precincts;
    }

    @Override
    public void wsFindStoreByAddr(BigDecimal longitude, BigDecimal latitude, String provinceId, String districtId,
            ResponseSuccessBean bean) {

        try {
            // AreaLongLatBean province = myvtgDao.findProvinceById(provinceId);
            List<StoresBean> stores = myvtgDao.findStoreByAddr(longitude, latitude, provinceId, districtId, true);

            if (!stores.isEmpty()) {
                stores.forEach((store) -> {
                    String formattedIsdn = DataUtil.formatIsdn0With3Diditals(store.getIsdn());
                    store.setIsdn(formattedIsdn);
                });
                bean.setWsResponse(stores);
            } else {
                bean.setWsResponse(null);
            }
        } catch (Exception e) {
            logger.error("error", e);
            bean.setWsResponse(null);
        }
    }

    @Override
    public NewsDetailBean wsGetNewsDetail(String newsId, ResponseSuccessBean bean) {
        NewsDetailBean news = myvtgDao.getNewsDetail(newsId);
        bean.setWsResponse(news);
        return news;
    }

    @Override
    public List<NewsBean> wsGetNews(String language, Integer pageSize, Integer pageNum, ResponseSuccessBean bean) {
        List<NewsBean> news = myvtgDao.getNews(language, pageSize, pageNum);
        if (news.isEmpty()) {
            news = null;
        }
        bean.setWsResponse(news);
        return news;
    }

    @Override
    public void wsGetExperienceLink3G4G(String language, Integer adBannerLimit, Integer expLimit,
            ResponseSuccessBean bean) {
        ArrayList<ExperienceLinkBean> links = myvtgDao.getTopExperienceLink(language, expLimit);
        ArrayList<AdvertBannerBean> adBanner = myvtgDao.getTopAdvertBanner(language, adBannerLimit);
        if (!links.isEmpty() || !adBanner.isEmpty()) {
            ExperienceLink3G4GBean ex = new ExperienceLink3G4GBean(adBanner, links);
            bean.setWsResponse(ex);
        } else {
            bean.setWsResponse(null);
        }
    }

    @Override
    public void wsGetAllApps(String language, Integer limit, Integer bannerLimit, ResponseSuccessBean bean) {
        ArrayList<ApplicationBean> apps = myvtgDao.getAllApps(language, limit);
        ArrayList<AdvertBannerBean> adBanner = myvtgDao.getTopAdvertBanner(language, bannerLimit);
        if (!apps.isEmpty() || !adBanner.isEmpty()) {
            AdvertBannerAppBean ex = new AdvertBannerAppBean(adBanner, apps);
            bean.setWsResponse(ex);
        } else {
            bean.setWsResponse(null);
        }
    }

    // @Override
    // public void wsGetAllDataPackages(String language, String isdn,
    // ResponseSuccessBean bean) {
    // Sub sub = subDao.findByIsdn(isdn);
    // if (sub == null) {
    // bean.setWsResponse(null);
    // return;
    // }
    //
    // List<ServiceBean> services = myvtgDao.getAllDataPackages(language,
    // sub.getIsdn(),
    // getServiceTypesFromConfig(sub.getSubType(), sub.getSimType()));
    // List<ServiceBean> processingServices =
    // myvtgDao.getAllProcessingDataPackages(language, sub.getIsdn(),
    // getServiceTypesFromConfig(sub.getSubType(), sub.getSimType()));
    // if (processingServices != null && !processingServices.isEmpty()) {
    // for (ServiceBean service : processingServices) {
    // service.setState(Constants.SUBCRIBER_STATE_PENDDING);
    // }
    // services.addAll(processingServices);
    // }
    // if (services.isEmpty()) {
    // bean.setWsResponse(null);
    // return;
    // }
    //
    // // TODO: this function for filter with extra conditional (customize to
    // // local)
    // List<ServiceBean> serviceFiltered = filterPackages(sub, services);
    // if (CommonUtil.isEmpty(serviceFiltered)) {
    // bean.setWsResponse(null);
    // } else {
    // bean.setWsResponse(serviceFiltered);
    // }
    //
    // }
    private String getServiceTypesFromConfig(int subType, int simType) {

        if (subType == Constants.SUBTYPE_TRATRUOC && simType == Constants.SIM_TYPE_3G) {
            return (String) myvtgDao.getAppParam(Constants.SERVICE_TYPES_PREPAID_AND_3G);
        } else if (subType == Constants.SUBTYPE_TRATRUOC && simType == Constants.SIM_TYPE_4G) {
            return (String) myvtgDao.getAppParam(Constants.SERVICE_TYPES_PREPAID_AND_4G);
        } else if (subType == Constants.SUBTYPE_TRASAU && simType == Constants.SIM_TYPE_3G) {
            return (String) myvtgDao.getAppParam(Constants.SERVICE_TYPES_POSPAID_AND_3G);
        } else if (subType == Constants.SUBTYPE_TRASAU && simType == Constants.SIM_TYPE_4G) {
            return (String) myvtgDao.getAppParam(Constants.SERVICE_TYPES_POSPAID_AND_4G);
        }

        return "()";
    }

    // @Override
    // public void wsGetServicesByGroup(String language, String isdn, String
    // serviceGroupCode, ResponseSuccessBean bean) {
    // List<ServiceBean> services = myvtgDao.getServicesByGroup(language, isdn,
    // serviceGroupCode);
    // List<ServiceBean> processingServices =
    // myvtgDao.getProcessingServicesByGroup(language, isdn, serviceGroupCode);
    // if (processingServices != null && !processingServices.isEmpty()) {
    // for (ServiceBean service : processingServices) {
    // service.setState(Constants.SUBCRIBER_STATE_PENDDING);
    // }
    //
    // services.addAll(processingServices);
    // }
    // if (services.isEmpty())
    // services = null;
    // bean.setWsResponse(services);
    // }
    @Override
    public void wsGetCareers(String language, ResponseSuccessBean bean) {
        List<CareerBean> careers = myvtgDao.getCareers(language);
        if (careers.isEmpty()) {
            careers = null;
        }
        bean.setWsResponse(careers);
    }

    @Override
    public void wsGetNewPromotions(String language, Integer pageSize, Integer pageNum, ResponseSuccessBean bean) {
        List<PromotionBean> promotions = myvtgDao.getNewPromotions(language, pageSize, pageNum);
        if (promotions.isEmpty()) {
            promotions = null;
        }
        bean.setWsResponse(promotions);
    }

    @Override
    public void wsGetPromotionInfo(String language, String packageCode, ResponseSuccessBean bean) {
        bean.setWsResponse(myvtgDao.getPromotionInfo(language, packageCode));
    }

    @Override
    public Object getAppParam(String name) {
        return myvtgDao.getAppParam(name);
    }

    @Override
    public void wsGetChangePostageRule(ResponseSuccessBean bean) {
        String pri_change_post_rule = (String) myvtgDao.getAppParam(Constants.SCORE_CONVERSION_RULE);
        ScoreConversionRate pcr = null;
        if (pri_change_post_rule != null) {
            String[] parts = pri_change_post_rule.split("/");
            Integer score = Integer.parseInt(parts[0]);
            BigDecimal money = new BigDecimal(parts[1]);
            pcr = new ScoreConversionRate();
            pcr.setMoney(money);
            pcr.setScore(score);
        }
        bean.setWsResponse(pcr);
    }

    @Override
    public void wsGetSubPrivilegeInfo(String isdn, ResponseSuccessBean bean) {
        Long subId = subDao.getSubIdByIsdn(isdn);
        bean.setWsResponse(myvtgDao.getSubPrivilegeInfo(isdn, subId));
    }

    @Override
    public void wsGetGiftsByProvince(Integer provinceId, ResponseSuccessBean bean) {
        List<GiftBean> gifts = myvtgDao.getGiftsByProvince(provinceId);
        if (gifts.isEmpty()) {
            gifts = null;
        }
        bean.setWsResponse(gifts);
    }

    @Override
    public void wsGetPrivilegeRuleInfo(ResponseSuccessBean bean) {
        String privilegeRuleIntro = (String) myvtgDao.getAppParam(Constants.PRIVILEGE_RULE_INTRO);
        ObjectMapper mapper = new ObjectMapper();
        try {
            PrivilegeRuleInfoBean obj = mapper.readValue(privilegeRuleIntro, PrivilegeRuleInfoBean.class);
            obj.setContent(CommonUtil.escapeHTML(obj.getContent()));
            bean.setWsResponse(obj);
        } catch (Exception ex) {
            logger.error("", ex);
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setWsResponse(null);
        }
    }

    @Override
    public void wsDoExchangeGift(String isdn, String subType, String giftCode, Integer provinceId, Integer districtId,
            Integer precinctId, String addr, Date time, BigDecimal markExchange, ResponseSuccessBean bean) {
        Long subId = subDao.getSubIdByIsdn(isdn);

        Integer result = myvtgDao.doExchangeGift(new BigDecimal(subId), isdn, subType, giftCode, provinceId, districtId,
                precinctId, addr, time, markExchange);
        if (result == 0) {
            bean.setErrorCode("0");
        } else if (result == 1) {
            bean.setErrorCode("2");
            bean.setMessage(Constants.GIFT_CODE_INVALID);
        } else if (result == 2) {
            bean.setErrorCode("2");
            bean.setMessage(Constants.NOT_ENOUGH_MARK_EXCHANGE);
        } else if (result == 3) {
            bean.setErrorCode("2");
            bean.setMessage(Constants.SUBID_INVALID);
        }
    }

    /**
     *
     */
    @Override
    public void wsGetAdvancedServiceInfo(String isdn, String language, String serviceCode, ResponseSuccessBean bean) {
        AdvanceServiceBean abb = serviceDao.getAdvancedServiceInfo(language, isdn, serviceCode);
        // scr.state = 0 → Thực hiện replace chuỗi “@unsubcriber” bằng giá trị
        // “none” và “@subcriber” bằng chuỗi “true”
        String servicePage = "";
        if (abb != null && abb.getState().equals(0) && (!CommonUtil.isEmpty(abb.getServicePage()))) {
            servicePage = abb.getServicePage().replaceAll("@unsubcriber", "none");
            servicePage = servicePage.replaceAll("@subcriber", "true");
        }
        // scr.state = 1 → Thực hiện replace chuỗi “@subcriber” bằng giá trị
        // “none” và “@unsubcriber” bằng chuỗi “true”
        if (abb != null && abb.getState().equals(1)) {
            if (!CommonUtil.isEmpty(abb.getServicePage())) {
                servicePage = abb.getServicePage().replaceAll("@subcriber", "none");
                servicePage = servicePage.replaceAll("@unsubcriber", "true");
            }
            abb.setServicePage(servicePage);
        }

        bean.setWsResponse(abb);
    }

    @Override
    public void wsGetServiceFuntionPage(String language, String serviceCode, String functionPageCode,
            ResponseSuccessBean bean) {

        FunctionPageServiceBean functionPageBean = myvtgDao.getServiceFuntionPage(language, serviceCode,
                functionPageCode);
        bean.setWsResponse(functionPageBean);
    }

    @Override
    public List<AreaBean> wsGetProvinces(String language, ResponseSuccessBean bean) {
        List<AreaBean> provinces = myvtgDao.getProvinces(language);
        if (!provinces.isEmpty()) {
            bean.setWsResponse(provinces);
        } else {
            bean.setWsResponse(null);
        }
        return provinces;
    }

    @Override
    public List<AreaBean> wsGetDistricts(String language, String provinceId, ResponseSuccessBean bean) {
        List<AreaBean> districts = myvtgDao.getDistricts(provinceId, language);
        if (!districts.isEmpty()) {
            bean.setWsResponse(districts);
        } else {
            bean.setWsResponse(null);
        }
        return districts;
    }

    /**
     * daibq
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean updateSocialNetwork(RequestBean request, String language) {
        logger.info("Start business updateSocialNetwork off Account2ServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("isdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("isdn").toString())) {
                logger.info("Error requesst : isdn is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.isdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("socialNetwork")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("socialNetwork").toString())) {
                logger.info("Error requesst : socialNetwork is null ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.socialNetwork.empty.", language);
            }
            String isdn = DataUtil.fomatIsdn(request.getWsRequest().get("isdn").toString());
            String socialNetwork = request.getWsRequest().get("socialNetwork").toString();
            if (socialNetwork.length() > 1000) {
                logger.info("Error requesst : socialNetwork max length > 1000 ");
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.socialNetwork.length.", language);
            }
            if (myvtgDao.updateSocialNetwork(isdn, socialNetwork)) {
                return responseBean(Constants.ERROR_SUCCESS, "myMetfone.Ishare.des.succ.", "myMetfone.Ishare.socialNetwork.succ.", language);
            }
            return responseBean(Constants.ERROR_SEND_SMS, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.socialNetwork.failed.", language);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language);
        }
    }

    /**
     * VuDoan altek
     * login to cms esport
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean wsLoginCMSEsport(RequestBean request, String language) {
        try {
            String username = request.getWsRequest().get("username").toString();
            username =DataUtil.formatIsdn0(username);
            String password = request.getWsRequest().get("password").toString();
            Users user = myvtgDao.getUserByPhone(username, password);
            if(DataUtil.isNullObject(user)){
                return responseBean(Constants.ERROR_PARAMETER_INVALID, "cms.login.param.invalid.", "cms.login.param.invalid.", language);
            }
            ModelMapper mapper = new ModelMapper();
            UserDTO userDTO = mapper.map(user, UserDTO.class);
            List<String> list = new ArrayList<>();
            //get user authority by user id
            List<UserAuthority> userAuthorities = myvtgDao.getUserAuthor(user.getId());
            if(userAuthorities != null || userAuthorities.size() > 0){
                for(UserAuthority au : userAuthorities){
                    list.add(au.getAuthorityName());
                }
                userDTO.setUserAuthoritys(list);
            }
            // call to bccs gw and get token and session id
//            BccsGwResponse  bccsGwResponse = apiGwServiceGlobal.postRequest(username);
//            if(DataUtil.isNullObject(bccsGwResponse) || DataUtil.isNullOrEmpty(bccsGwResponse.getToken())){
//                userDTO.setToken("");
//                userDTO.setSessionId("");
//            } else {
//                userDTO.setToken(bccsGwResponse.getToken());
//                userDTO.setSessionId(bccsGwResponse.getSessionId());
//            }
            userDTO.setToken("FKZN3nX/lWgyDk2X2LNmUQ==");
            userDTO.setSessionId("880edcd1-ad30-43e8-a9ac-8f61dd99de4f");
            BaseResponseBean bean = new BaseResponseBean();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setUserMsg("Successfully!");
            bean.setWsResponse(userDTO);
            return bean;
        } catch (Exception e){
            logger.error("Exception: ", e);
            return responseBean(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language);
        }
    }

    /**
     * responseBean
     *
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @return
     */
    private BaseResponseBean responseBean(String errorCode, String description, String content, String language) {
        return new BaseResponseBean(errorCode, messageUtil.getMessage(description + language), messageUtil.getMessage(content + language));
    }
}
