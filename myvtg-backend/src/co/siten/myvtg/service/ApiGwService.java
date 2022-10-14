package co.siten.myvtg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import co.siten.myvtg.bean.CamIdNotificationBean;
import co.siten.myvtg.dao.ApiGwDao;

@org.springframework.stereotype.Service("ApigwService")
@Transactional(rollbackFor = Exception.class, value = "apigwtransaction")
public class ApiGwService {

    @Autowired
    ApiGwDao apigwDao;

    public List<CamIdNotificationBean> wsGetListCamIDNotification(String camid, Integer pageSize, Integer pageNum, boolean isClearAllBefore, boolean isBlockNotification, boolean isUnReadStatus, String osDevice, String deviceId) {	
        return apigwDao.wsGetListCamIDNotification(camid, pageSize, pageNum, isClearAllBefore, isBlockNotification, isUnReadStatus, osDevice, deviceId);	
    }

    public int wsUpdateIsReadCamIDNotification(Long notificationId, String camid) {
        return apigwDao.wsUpdateIsReadCamIDNotification(notificationId, camid);
    }

    public boolean clearAllCamIdNotification(String camid) {
        return apigwDao.clearAllCamIdNotification(camid);
    }

    public boolean isClearAllBefore(String camId) {
        return apigwDao.isClearAllBefore(camId);
    }

    public boolean isReadNotifyBefore(String camId, Long notifyId) {
        return apigwDao.isReadNotifyBefore(camId, notifyId);
    }

    public boolean blockReceiveCamidNotification(String camId, String blockReceive) {
        return apigwDao.blockReceiveCamidNotification(camId, blockReceive);
    }

    public boolean isBlockNotification(String camId) {
        return apigwDao.isBlockNotification(camId);
    }

    //14.08.2021 partner7 implement flow for account unknow
    public List<CamIdNotificationBean> wsGetListCamIDNotificationUnknow(String deviceId, Integer pageSize, Integer pageNum, boolean isClearAllBefore, boolean isUnReadStatus, boolean isBlock) {
        return apigwDao.wsGetListCamIDNotificationUnknow(deviceId, pageSize, pageNum, isClearAllBefore, isUnReadStatus, isBlock);
    }

    public boolean isReadNotifyBeforeUnknow(String deviceId, Long notifyId) {
        return apigwDao.isReadNotifyBeforeUnknow(deviceId, notifyId);
    }

    public int wsUpdateIsReadCamIDNotificationUnknow(Long notificationId, String deviceId) {
        return apigwDao.wsUpdateIsReadCamIDNotificationUnknow(notificationId, deviceId);
    }

    public boolean clearAllCamIdNotificationUnknow(String deviceId) {
        return apigwDao.clearAllCamIdNotificationUnknow(deviceId);
    }

    public boolean blockReceiveCamidNotificationUnknow(String deviceId, String blockReceive) {
        return apigwDao.blockReceiveCamidNotificationUnknow(deviceId, blockReceive);
    }

    public boolean isClearAllBeforeUnknow(String deviceId) {
        return apigwDao.isClearAllBeforeUnknow(deviceId);
    }

    public boolean isBlockNotificationUnknow(String deviceId) {
        return apigwDao.isBlockNotificationUnknow(deviceId);
    }

    //--
    public boolean updateCamIdStatus(String camid, String login, String deviceId,String lang,String versionApp) {
        return apigwDao.updateCamIdStatus(camid, login, deviceId, lang,versionApp);
    }
	
		    //-	
    public String getOSdevice(String camid, String deviceId){	
        return apigwDao.getOsDevice(camid, deviceId);	
    }
}
