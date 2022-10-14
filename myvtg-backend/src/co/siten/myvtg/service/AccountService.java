package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseObj;
import co.siten.myvtg.bean.ResponseSuccessBean;
import org.springframework.core.env.Environment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *
 * @author thomc
 *
 */
public interface AccountService {

    public void wsGetSubInfo(String isdn, int subType, ResponseSuccessBean bean) throws Exception;

    public void wsGetPostageDetailInfo(Environment environment, String isdn, int subType, Date startDate, Date endDate,
            int postType, int size, int page, String order, ResponseSuccessBean bean) throws Exception;

    public void wsGetSubAccountInfo(String isdn, int subType, ResponseSuccessBean bean, Object language);

    public void wsGetCallAccountDetail(String isdn, int subType, ResponseSuccessBean bean);

    public void wsGetDataAccountDetail(String isdn, ResponseSuccessBean bean, Object language);

    public void wsGetServiceInfo(String isdn, String language, String serviceCode, ResponseSuccessBean bean);

    public void wsGetCurrentUsedSubServices(String isdn, String language, ResponseSuccessBean bean);

    public void wsDoRecharge(String isCheckSub, String isdn, String desIsdn, String serial, String language,
            ResponseSuccessBean bean) throws Exception;

    public void wsDoRechargeCard(String isCheckSub, String isdn, String desIsdn, String serial, String language,
            ResponseSuccessBean bean, String programCode,String captchaCode) throws Exception;

    public void wsGetDataVolumeLevelToBuy(String isdn, String dataPackageCode, ResponseSuccessBean bean);

    public void wsGetDataPackageInfo(String isdn, String packageCode, String lang, ResponseSuccessBean bean);

    public void wsGetPostageInfo(String isdn, int subType, Date startDate, Date endDate, String type,
            ResponseSuccessBean bean);

    public void wsFindIsdnToBuy(int subType, int pageSize, int pageNum, String language, BigDecimal minPrice,
            BigDecimal maxPrice, String isdnPattern, ResponseSuccessBean bean);

    public void wsDoActionService(String isdn, String serviceCode, int action, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean) throws Exception;
    
    public void wsDoActionServiceByOTP(String isdn, String serviceCode, int action, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean,RequestBean request) throws Exception;

    public void wsRegisterBuyIsdn(String isdn, String isdnToBuy, ResponseSuccessBean bean);

    public void wsDoBuyIsdn(String isdn, int subType, String newIsdn, String serial, String isdnOfKit, BigDecimal price,
            ResponseSuccessBean bean) throws Exception;

    public void wsDoExchangeIsdn(String isdn, String newIsdn, int subType, BigDecimal price, ResponseSuccessBean bean)
            throws Exception;

    public void wsDoChangeSIM(String language, String isdn, int subType, String newIsdn, String serial,
            String isdnOfKit, ResponseSuccessBean bean) throws Exception;

    public void wsDoLockCallGoIsdn(String isdn, int subType, ResponseSuccessBean bean) throws Exception;

    public ResponseObj callApiChangeAccountBalance(int subType, BigDecimal price, String msisdn) throws Exception;

    public void activeSubcriber(String isdn, int subType, String serialOfKit, String isdnOfKit, Object subMb)
            throws Exception;

    public void wsDoExchangePostage(String isdn, int subType, BigDecimal exchangedMark, BigDecimal money,
            ResponseSuccessBean bean) throws Exception;

    public void wsGetAppConfig(ResponseSuccessBean bean, Object version) throws Exception;

    public void wsDoBuyData(String isdn, String packageCode, Integer subType, Long price, Long volume,
            ResponseSuccessBean bean) throws Exception;

    public void wsGetAccountsDetail(String isdn, Object language, ResponseSuccessBean bean) throws Exception;
    
    public void wsGetAccountsExchangeDetail(String isdn, Object language, ResponseSuccessBean bean) throws Exception;

    public void wsDetachIpService(String ip, String serviceCode, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean) throws Exception;

//    public void wsCheckForceUpdateApp(ResponseSuccessBean bean, String language, Object version) throws Exception;
    public void wsCheckForceUpdateApp(ResponseSuccessBean bean, String language, Object version, String isdn,String versionApp) throws Exception;

    public void wsGetCurrentUsedServices(String language, String isdn, ResponseSuccessBean bean);

    public void wsGetServices(String isdn, String language, ResponseSuccessBean bean);

    public void wsGetServiceDetail(String language, String isdn, String serviceCode, ResponseSuccessBean bean);

    public void wsGetServicesByGroup(String language, String isdn, String serviceGroupCode, ResponseSuccessBean bean, String validity);

    public void wsGetAllDataPackages(String language, String isdn, ResponseSuccessBean bean);

    public void wsGetServiceInfo(String isdn, String language, String serviceCode, Integer subType,
            ResponseSuccessBean bean);

    public void wsUpdateCustomerInfo(String xmlParam, String serviceCode, LinkedHashMap<String, Object> params,
            ResponseSuccessBean bean, String language) throws Exception;

    public void wsDoCallWebservice(LinkedHashMap<String, Object> params, String serviceCode, String language,
            ResponseSuccessBean bean) throws Exception;

    //daibq bo sung
    public BaseResponseBean wsHistoryTopup(RequestBean request, String language);

    //YaangVu update api History Top up
    public BaseResponseBean wsHistoryTopUpNew(RequestBean request, String language);
    
    //YaangVu update api History Charge
    public BaseResponseBean wsHistoryCharge(RequestBean request, String language);
    
    //YaangVu update api History Charge Detail
    public BaseResponseBean wsHistoryChargeDetail(RequestBean request, String language);

    public BaseResponseBean wsGetAccountInfo(RequestBean request, String language);
    
    public BaseResponseBean wsGetAccountExchangeInfo(RequestBean request, String language);
    // get Notification API for CamID
    public BaseResponseBean wsGetListCamIDNotification(RequestBean request, String language);
   //update read status by Notification API for CamID
    public BaseResponseBean wsUpdateIsReadCamIDNotification(RequestBean request, String language);
    //get service expired
    public BaseResponseBean wsGetCurrentUsedServicesExpired(RequestBean request, String language);
    //hop create API clean all notification from app CamId
    public BaseResponseBean wsClearAllCamIdNotification(RequestBean request, String language);
    //hop create API on/off camid notification
    public BaseResponseBean wsControlCamIdNotification(RequestBean request, String language);
    //hop create API get notification status
    public BaseResponseBean getCamIdNotificationStatus(RequestBean request, String language);
    
    public BaseResponseBean checkSubInfo(RequestBean request, String language);
     //partner7 - 1 api(s) -- 14.08.2021
    public BaseResponseBean updateCamIdAccessStatus(RequestBean request, String language);

    public BaseResponseBean wsHistoryTopUpNewByDate(RequestBean request, String language);

    public BaseResponseBean wsHistoryTopUpLucky(RequestBean request, String language);

    public void wsDoRechargeCardLucky(String isCheckSub, String isdn, String desIsdn, String serial, String language,
                                      ResponseSuccessBean bean, String programCode,String captchaCode, String userId) throws Exception;
}
