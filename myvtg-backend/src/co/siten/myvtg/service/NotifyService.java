package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseSuccessBean;

public interface NotifyService {

    public void wsUpdateDeviceToken(ResponseSuccessBean bean, String isdn, String token, int os) throws Exception;

    public void logClickNotification(ResponseSuccessBean bean, String isdn, Long notificationId, Long accNotificationId);

//    public void logActionNotification(ResponseSuccessBean bean, String isdn, String actionValue,
//                                      Long notificationId);
    public void wsGetListNotificationByIsdn(String isdn, Integer pageSize, Integer pageNum, ResponseSuccessBean bean);

    public BaseResponseBean getListNotificationV2(RequestBean request, String language);

    public BaseResponseBean addNewNotificationV2(RequestBean request, String language);

    public BaseResponseBean updateNotifiationV2(RequestBean request, String language);

    public BaseResponseBean deleteNotifiationV2(RequestBean request, String language);

    public BaseResponseBean updateIsReadNotificationV2(RequestBean request, String language);

    public BaseResponseBean saveLogAfterReadNotification(RequestBean request, String language);

    public BaseResponseBean getDetailNotification(RequestBean request, String language);

    //phuonghc - 8 new apis
    public BaseResponseBean getAllCamIdNotification(RequestBean request, String language);

    public BaseResponseBean addNewCamIdNotification(RequestBean request, String language);
    
    public BaseResponseBean sendNewCamIdNotification(RequestBean request, String language);

    public BaseResponseBean updateCamIdNotification(RequestBean request, String language);

    public BaseResponseBean deleteCamIdNotification(RequestBean request, String language);
    
    public BaseResponseBean getCamIdActionType(String language);
    
    public BaseResponseBean getRewardDetail(RequestBean bean, String language);

    public void wsUpdateCamIdDeviceToken(ResponseSuccessBean bean, String camId, String token, int os, String deviceId,String lang,String versionApp);
    
    public void wsGetCamIDeviceToken(ResponseSuccessBean bean, String camId, String deviceId);
    
    public BaseResponseBean getCamIdNotificationById(RequestBean request, String language);
    
    public BaseResponseBean sendSMSInternal(RequestBean request, String language);
    
    public BaseResponseBean getFilmInfoByName(RequestBean request, String language);
	
	public BaseResponseBean getListPassiveType(RequestBean request, String language);	
    	
    public BaseResponseBean getListPassiveControl(RequestBean request, String language);	
    	
    public BaseResponseBean updatePassiveControl(RequestBean request, String language);
}
