/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.AdvertisementDao;
import co.siten.myvtg.model.apigw.Advertisement;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;

/**
 * AdvertisementServiceImpl
 *
 * @author partner7
 */
@org.springframework.stereotype.Service("AdvertisementService")

@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "apigwtransaction")
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    AdvertisementDao dao;
    private static final Logger logger = Logger.getLogger(AdvertisementServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    private static final String DES_SUCC = "myMetfone.Ishare.des.succ.";

    @Override
    public BaseResponseBean saveInforPartnerAdvertisement(RequestBean request, String language) {
        logger.info("Start business saveInforPartnerAdvertisement off AdvertisementServiceImpl");
        Date currDate = new Date();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("partnerId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("partnerId").toString())) {
                logger.info("Error request : partnerId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.saveInfo.partnerId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("deviceId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceId").toString())) {
                logger.info("Error request : deviceId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.saveInfo.deviceId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("versionApp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("versionApp").toString())) {
                logger.info("Error request : versionApp is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.saveInfo.versionapp.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("versionDevice")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("versionDevice").toString())) {
                logger.info("Error request : versionDevice is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.saveInfo.versiondevice.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("deviceOS")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("deviceOS").toString())) {
                logger.info("Error request : deviceOS is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.saveInfo.deviceos.empty.", language);
            }
            if (request.getWsRequest().get("modelDevice") == null) {
                logger.info("Error request : modelDevice is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.service.failed.", language);
            }
            if (request.getWsRequest().get("camId") == null) {
                logger.info("Error request : camId is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.service.failed.", language);
            }
            String partnerId = request.getWsRequest().get("partnerId").toString();
            String deviceId = request.getWsRequest().get("deviceId").toString();
            String versionApp = request.getWsRequest().get("versionApp").toString();
            String versionDevice = request.getWsRequest().get("versionDevice").toString();
            String deviceOS = request.getWsRequest().get("deviceOS").toString();
            String modelDevice = request.getWsRequest().get("modelDevice").toString();
            String camId = request.getWsRequest().get("camId").toString();
            String type=request.getWsRequest().get("type").toString();
            //Validate infor type
            Long id = dao.getIdFromDeviceId(deviceId);
            if (id != null && id != 0L) {
                Advertisement advertisement = (Advertisement) dao.get(Advertisement.class, id);
                advertisement.setDeviceId(deviceId);
                advertisement.setVersionApp(versionApp);
                advertisement.setVersionDevice(versionDevice);
                advertisement.setDeviceOS(deviceOS);
                advertisement.setModelDevice(modelDevice);
                advertisement.setCamId(StringUtils.isNotEmpty(camId) ? Long.valueOf(camId) : null);
                advertisement.setUpdatedDate(new Date());
                
                dao.update(advertisement);
            } else {
                Advertisement advertisement = new Advertisement();
                advertisement.setPartnerId(partnerId);
                advertisement.setDeviceId(deviceId);
                advertisement.setVersionApp(versionApp);
                advertisement.setVersionDevice(versionDevice);
                advertisement.setDeviceOS(deviceOS);
                advertisement.setModelDevice(modelDevice);
                advertisement.setCamId(StringUtils.isNotEmpty(camId) ? Long.valueOf(camId) : null);
                advertisement.setCreatedDate(new Date());
                advertisement.setType(type);
                dao.insert(advertisement);
            }
            //if (id != null) {
            return responseUtil.responseBean(Constants.ERROR_SUCCESS, DES_SUCC, "myMetfone.saveInfo.partner.success.", language);
            //}
            //return responseUtil.responseBean(Constants.ERROR_SEND_SMS, DES_FAIL, "wsGetOtpIshare.fail.", language);

        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);

        }
    }

}
