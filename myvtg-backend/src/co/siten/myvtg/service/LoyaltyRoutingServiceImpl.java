/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.DonateTransactionBean;
import co.siten.myvtg.bean.EmoneyWalletBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.config.Config;
import co.siten.myvtg.dao.MyMetfoneBusinesDao;
import co.siten.myvtg.dao.CmpreDao;
import co.siten.myvtg.dto.*;
import co.siten.myvtg.model.myvtg.*;
import co.siten.myvtg.model.myvtg.Discount;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.DateTimeUtils;
import co.siten.myvtg.util.DonatePaymentMethodEnum;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.ResponseUtil;
import co.siten.myvtg.util.WebServiceUtil;
import com.viettel.common.ExchMsg;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author buiquangdai
 */
@org.springframework.stereotype.Service("LoyaltyRoutingService")
@PropertySource(value = {"classpath:config.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class LoyaltyRoutingServiceImpl implements LoyaltyRoutingService {

    private static final Logger logger = Logger.getLogger(LoyaltyRoutingServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    private static final String DES_SUCC = "myMetfone.Ishare.des.succ.";
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
    public static final String REGEX = "\\d+";

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_INACTIVE = 0;
    public static final Integer STATUS_ALL = -1;

    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    ConfigUtils configUtils;
    @Autowired
    MyvtgService myvtgService;
    @Autowired
    MyMetfoneBusinesDao myMetfoneBusinesDao;
    @Autowired
    MessageUtil messageUtil;
    @Autowired
    CmpreDao dao;
    @Autowired
    Environment environment;

    /**
     * looyaltyRoutingBusiness
     *
     * @param request
     * @param language
     * @return
     */
    @Override
    public BaseResponseBean loyaltyRoutingBusiness(RequestBean request, String language) {
        logger.info("Start business changeLoyalty off LuckyLoyaltyGameServiceImpl");
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("key")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("key").toString())) {
                logger.info("Error requesst : key is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("value")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("value").toString())) {
                logger.info("Error requesst : value is null ");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "mymetfone.game.point.empty.", language);
            }
            String key = request.getWsRequest().get("key").toString().trim();
            JSONObject jsonbean = new JSONObject(request);

            Webservice ws = myvtgService.getWS(key);
            if (ws == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
            }
            logger.info("Time start" + new Date());

            BaseResponsesDto response = null;
            Object rq = jsonbean.getJSONObject("wsRequest").get("value");
            logger.info("Requesst send API : " + key + " || " + "request: " + rq.toString());
            if (!DataUtil.isNullOrEmpty(ws.getMethod()) && "GET".equals(ws.getMethod().trim().toUpperCase())) {
                Map<String, Object> rqMap = DataUtil.convertJsonStringToHashMap(rq.toString());
                if (DataUtil.isNullOrEmpty(rqMap)) {
                    return responseUtil.responseBean(Constants.ERROR_SYSTEM, "Connection fail: Maping request error: " + rq.toString(), "myMetfone.failed.", language);
                }
                response = WebServiceUtil.callApiGet(ws.getUrl(), rqMap);
            } else {
                response = WebServiceUtil.callApiRest(ws.getUrl(), rq.toString());
            }
            if (!DataUtil.isNullObject(response) && response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                Object object = DataUtil.convertJsonStringToObject(response.getMessageCode(), Object.class);
                BaseResponseBean bean = new BaseResponseBean();
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setUserMsg(messageUtil.getMessage(DES_SUCC + language));
                bean.setWsResponse(object);
                return bean;
            } else {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, "Connection fail: url " + ws.getUrl() + "ErrorCode: " + response.getStatusCode().toString(), "myMetfone.failed.", language);
            }
        } catch (Exception e) {
            logger.error("parse description error: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean addDonatePackage(RequestBean request, String language) {
        logger.info("Start business addDonatePackage of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("code")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("code").toString())) {
                logger.info("Error request : code is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.code.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("name")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("name").toString())) {
                logger.info("Error request : name is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.name.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("title")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("title").toString())) {
                logger.info("Error request : title is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.title.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("titleKH")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("titleKH").toString())) {
                logger.info("Error request : titleKH is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.titleKH.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("coin")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("coin").toString())) {
                logger.info("Error request : coin is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.coin.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("iconUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("iconUrl").toString())) {
                logger.info("Error request : iconUrl is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.iconUrl.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("description")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("description").toString())) {
                logger.info("Error request : description is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.description.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("descriptionKH")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("descriptionKH").toString())) {
                logger.info("Error request : descriptionKH is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.descriptionKH.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            Long id = myMetfoneBusinesDao.getSequence(Config.SEQUENCE_MYMETFONE_DONATE_PACKAGE);
            String code = request.getWsRequest().get("code").toString().trim();
            String name = request.getWsRequest().get("name").toString().trim();
            String title = "title_" + id;
            String titleValue = request.getWsRequest().get("title").toString().trim();
            String titleKHValue = request.getWsRequest().get("titleKH").toString().trim();
            String coin = request.getWsRequest().get("coin").toString().trim();
            String iconUrl = request.getWsRequest().get("iconUrl").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String description = "description_" + id;
            String descriptionValue = request.getWsRequest().get("description").toString().trim();
            String descriptionKHValue = request.getWsRequest().get("descriptionKH").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Integer.parseInt(coin);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<Object> donatePackages = myMetfoneBusinesDao.findDonatePackageByCode(null, code);
            if (donatePackages == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (!donatePackages.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackage.exists.", language);
            }

            DonatePackage donatePackage = new DonatePackage();
            donatePackage.setId(id);
            donatePackage.setCode(code);
            donatePackage.setName(name);
            donatePackage.setTitle(title);
            donatePackage.setCoin(Integer.parseInt(coin));
            donatePackage.setIconUrl(iconUrl);
            donatePackage.setStatus(Integer.parseInt(status));
            donatePackage.setDescription(description);
            donatePackage.setCreatedBy(createdBy);
            donatePackage.setUpdatedBy(createdBy);
            ConfigDonate titleEn = new ConfigDonate();
            titleEn.setParamName("DONATE_PACKAGE");
            titleEn.setParamKey(title + "_en");
            titleEn.setPramValue(titleValue);
            titleEn.setStatus(1);
            ConfigDonate titleKh = new ConfigDonate();
            titleKh.setParamName("DONATE_PACKAGE");
            titleKh.setParamKey(title + "_kh");
            titleKh.setPramValue(titleKHValue);
            titleKh.setStatus(1);
            ConfigDonate descriptionEn = new ConfigDonate();
            descriptionEn.setParamName("DONATE_PACKAGE");
            descriptionEn.setParamKey(description + "_en");
            descriptionEn.setPramValue(descriptionValue);
            descriptionEn.setStatus(1);
            ConfigDonate descriptionKh = new ConfigDonate();
            descriptionKh.setParamName("DONATE_PACKAGE");
            descriptionKh.setParamKey(description + "_kh");
            descriptionKh.setPramValue(descriptionKHValue);
            descriptionKh.setStatus(1);
            List<ConfigDonate> configDonates = Arrays.asList(titleEn, titleKh, descriptionEn, descriptionKh);

            Integer resultDonatePackage = myMetfoneBusinesDao.addDonatePackage(donatePackage);
            Integer resultConfigDonate = myMetfoneBusinesDao.addConfigDonate(configDonates);

            Map<String, Long> data = new HashMap<String, Long>();
            data.put("idDonatePackage", id);
            if (resultDonatePackage == 1 && resultConfigDonate == 4) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(data);
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while add new donate package: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean editDonatePackage(RequestBean request, String language) {
        logger.info("Start business editDonatePackage of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("code")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("code").toString())) {
                logger.info("Error request : code is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.code.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("name")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("name").toString())) {
                logger.info("Error request : name is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.name.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("title")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("title").toString())) {
                logger.info("Error request : title is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.title.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("titleKH")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("titleKH").toString())) {
                logger.info("Error request : titleKH is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.titleKH.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("coin")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("coin").toString())) {
                logger.info("Error request : coin is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.coin.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("iconUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("iconUrl").toString())) {
                logger.info("Error request : iconUrl is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.iconUrl.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("description")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("description").toString())) {
                logger.info("Error request : description is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.description.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("descriptionKH")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("descriptionKH").toString())) {
                logger.info("Error request : descriptionKH is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackage.descriptionKH.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String code = request.getWsRequest().get("code").toString().trim();
            String name = request.getWsRequest().get("name").toString().trim();
            String title = "title_" + id;
            String titleValue = request.getWsRequest().get("title").toString().trim();
            String titleKHValue = request.getWsRequest().get("titleKH").toString().trim();
            String coin = request.getWsRequest().get("coin").toString().trim();
            String iconUrl = request.getWsRequest().get("iconUrl").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String description = "description_" + id;
            String descriptionValue = request.getWsRequest().get("description").toString().trim();
            String descriptionKHValue = request.getWsRequest().get("descriptionKH").toString().trim();
            String updatedBy = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
                Integer.parseInt(coin);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(updatedBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonatePackageWebDTO> donatePackageWebDTOS = myMetfoneBusinesDao.findDonatePackageById(Long.parseLong(id));
            if (donatePackageWebDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (donatePackageWebDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackage.notFound.", language);
            }

            DonatePackage donatePackage = new DonatePackage();
            donatePackage.setId(Long.parseLong(id));
            donatePackage.setName(name);
            donatePackage.setCode(code);
            donatePackage.setTitle(title);
            donatePackage.setCoin(Integer.parseInt(coin));
            donatePackage.setIconUrl(iconUrl);
            donatePackage.setStatus(Integer.parseInt(status));
            donatePackage.setDescription(description);
            donatePackage.setUpdatedBy(updatedBy);

            List<Object> donatePackageOld = myMetfoneBusinesDao.findDonatePackageByCode(donatePackage.getId(), donatePackage.getCode());
            if (donatePackageOld == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (!donatePackageOld.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackage.exists.", language);
            }

            List<Object> titleEnOld = myMetfoneBusinesDao.findConfigDonateByPramKey(title + "_en");
            List<Object> titleKhOld = myMetfoneBusinesDao.findConfigDonateByPramKey(title + "_kh");
            List<Object> descriptionEnOld = myMetfoneBusinesDao.findConfigDonateByPramKey(description + "_en");
            List<Object> descriptionKhOld = myMetfoneBusinesDao.findConfigDonateByPramKey(description + "_kh");
            if (titleEnOld == null || titleKhOld == null || descriptionEnOld == null || descriptionKhOld == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (titleEnOld.isEmpty() || titleKhOld.isEmpty() || descriptionEnOld.isEmpty() || descriptionKhOld.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.configDonate.notFound.", language);
            }
            ConfigDonate titleEn = new ConfigDonate();
            titleEn.setParamName("DONATE_PACKAGE");
            titleEn.setParamKey(title + "_en");
            titleEn.setPramValue(titleValue);
            titleEn.setStatus(1);
            ConfigDonate titleKh = new ConfigDonate();
            titleKh.setParamName("DONATE_PACKAGE");
            titleKh.setParamKey(title + "_kh");
            titleKh.setPramValue(titleKHValue);
            titleKh.setStatus(1);
            ConfigDonate descriptionEn = new ConfigDonate();
            descriptionEn.setParamName("DONATE_PACKAGE");
            descriptionEn.setParamKey(description + "_en");
            descriptionEn.setPramValue(descriptionValue);
            descriptionEn.setStatus(1);
            ConfigDonate descriptionKh = new ConfigDonate();
            descriptionKh.setParamName("DONATE_PACKAGE");
            descriptionKh.setParamKey(description + "_kh");
            descriptionKh.setPramValue(descriptionKHValue);
            descriptionKh.setStatus(1);
            List<ConfigDonate> configDonates = Arrays.asList(titleEn, titleKh, descriptionEn, descriptionKh);

            Integer resultDonateGiftPackage = myMetfoneBusinesDao.editDonatePackage(donatePackage);
            Integer resultConfigDonate = myMetfoneBusinesDao.editConfigDonate(configDonates);

            if (resultDonateGiftPackage == 1 && resultConfigDonate == 4) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while edit donate package: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean deleteDonatePackage(RequestBean request, String language) {
        logger.info("Start business deleteDonatePackage of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonatePackageWebDTO> donatePackageWebDTOS = myMetfoneBusinesDao.findDonatePackageById(Long.parseLong(id));
            if (donatePackageWebDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (donatePackageWebDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackage.notFound.", language);
            }

            Integer result = myMetfoneBusinesDao.deleteDonatePackage(Long.parseLong(id));

            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while delete donate package: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean searchDonatePackage(RequestBean request, String language) {
        logger.info("Start business searchDonatePackage of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String search = null;
            Integer searchFor = null;
            Integer status = null;
            if (DataUtil.isNullObject(request.getWsRequest().get("searchFor")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("searchFor").toString())) {
                logger.info("Error request : searchFor is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.searchFor.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (request.getWsRequest().get("search") == null) {
                logger.info("Error request : search is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.search.", language);
            }

            try {
                searchFor = Integer.parseInt(request.getWsRequest().get("searchFor").toString().trim());
                status = Integer.parseInt(request.getWsRequest().get("status").toString().trim());
                search = request.getWsRequest().get("search").toString().trim();
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            String account = request.getWsRequest().get("account").toString().trim();

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            if (!Objects.equals(STATUS_ACTIVE, status) && !Objects.equals(STATUS_INACTIVE, status) && !Objects.equals(STATUS_ALL, status)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<DonatePackageDTO> result = myMetfoneBusinesDao.searchDonatePackage(search, searchFor, status);
            bean.setUserMsg("Successfully!");
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setWsResponse(result);
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while search donate package: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getDonatePackage(RequestBean request, String language) {
        logger.info("Start business getDonatePackage of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonatePackageWebDTO> donatePackageWebDTOS = myMetfoneBusinesDao.findDonatePackageById(Long.parseLong(id));
            if (donatePackageWebDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (donatePackageWebDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackage.notFound.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donatePackageWebDTOS.get(0));
                return bean;
            }
        } catch (Exception e) {
            logger.error("An error occured while get donate package: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getAllDonatePackageWeb(RequestBean request, String language) {
        logger.info("Start business getAllDonatePackage of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String account = request.getWsRequest().get("account").toString().trim();

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonatePackageWebDTO> donatePackageWebDTOS = myMetfoneBusinesDao.getAllDonatePackageWeb();
            if (donatePackageWebDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donatePackageWebDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get all donate package: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getAllDonatePackageApp(RequestBean request, String language) {
        logger.info("Start business getAllDonatePackageApp of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            List<DonatePackageAppDTO> donatePackageAppDTOS = myMetfoneBusinesDao.getAllDonatePackageApp(language);
            if (donatePackageAppDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donatePackageAppDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get all donate package for app: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean addDonatePackagePrice(RequestBean request, String language) {
        logger.info("Start business addDonatePackagePrice of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("donatePackageId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("donatePackageId").toString())) {
                logger.info("Error request : donatePackageId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.donatePackageId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("paymentMethod")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("paymentMethod").toString())) {
                logger.info("Error request : paymentMethod is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.paymentMethod.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("price")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("price").toString())) {
                logger.info("Error request : price is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.price.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("unit")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("unit").toString())) {
                logger.info("Error request : unit is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.unit.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String donatePackageId = request.getWsRequest().get("donatePackageId").toString().trim();
            String paymentMethod = request.getWsRequest().get("paymentMethod").toString().trim();
            String price = request.getWsRequest().get("price").toString().trim();
            String unit = request.getWsRequest().get("unit").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(donatePackageId);
                Float.parseFloat(price);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            //validate donate package with paymentmethod available
            boolean isDonatePackagePriceAvailable = myMetfoneBusinesDao.checkDonatePackageWithMethodExisted(donatePackageId, paymentMethod, null);
            if (isDonatePackagePriceAvailable) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackagePrice.exists.", language);
            }

            isDonatePackagePriceAvailable = myMetfoneBusinesDao.isValidInput(Long.valueOf(donatePackageId), "DONATE_PACKAGE");
            if (!isDonatePackagePriceAvailable) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackage.notFound.", language);
            }

            //validate paymentMethod must be matched with Unit
            try {
                DonatePaymentMethodEnum paymentMethods = DonatePaymentMethodEnum.valueOf(paymentMethod);
                if (!paymentMethods.getUnit().equals(unit)) {
                    logger.info("### unit not match with paymentMethod....");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donate.unit.nomatch.", language);
                }
            } catch (Exception e) {
                logger.info("### paymentMethod invalid....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackagePrice.paymentMethod.empty.", language);
            }

            DonatePackagePrice donatePackagePrice = new DonatePackagePrice();
            Long id = myMetfoneBusinesDao.getSequence(Config.SEQUENCE_MYMETFONE_DONATE_PACKAGE_PRICE);
            donatePackagePrice.setId(id);
            donatePackagePrice.setDonatePackageId(Long.parseLong(donatePackageId));
            donatePackagePrice.setPaymentMethod(paymentMethod);
            donatePackagePrice.setPrice(Float.parseFloat(price));
            donatePackagePrice.setUnit(unit);
            donatePackagePrice.setStatus(Integer.parseInt(status));
            donatePackagePrice.setCreatedBy(createdBy);
            donatePackagePrice.setUpdatedBy(createdBy);

            Integer resultDonatePackagePrice = myMetfoneBusinesDao.addDonatePackagePrice(donatePackagePrice);

            if (resultDonatePackagePrice == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while add new donate package price: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean editDonatePackagePrice(RequestBean request, String language) {
        logger.info("Start business editDonatePackagePrice of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("donatePackageId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("donatePackageId").toString())) {
                logger.info("Error request : donatePackageId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.donatePackageId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("paymentMethod")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("paymentMethod").toString())) {
                logger.info("Error request : paymentMethod is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.paymentMethod.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("price")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("price").toString())) {
                logger.info("Error request : price is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.price.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("unit")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("unit").toString())) {
                logger.info("Error request : unit is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.unit.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String donatePackageId = request.getWsRequest().get("donatePackageId").toString().trim();
            String paymentMethod = request.getWsRequest().get("paymentMethod").toString().trim();
            String price = request.getWsRequest().get("price").toString().trim();
            String unit = request.getWsRequest().get("unit").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
                Long.parseLong(donatePackageId);
                Float.parseFloat(price);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            //check paymentMethod matched with Unit
            //validate paymentMethod must be matched with Unit
            try {
                DonatePaymentMethodEnum paymentMethods = DonatePaymentMethodEnum.valueOf(paymentMethod);
                if (!paymentMethods.getUnit().equals(unit)) {
                    logger.info("### unit not match with paymentMethod....");
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donate.unit.nomatch.", language);
                }
            } catch (Exception e) {
                logger.info("### paymentMethod invalid....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackagePrice.paymentMethod.empty.", language);
            }

            //validate donate package with paymentmethod available
            boolean isDonatePackagePriceAvailable = myMetfoneBusinesDao.checkDonatePackageWithMethodExisted(donatePackageId, paymentMethod, id);
            if (isDonatePackagePriceAvailable) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackagePrice.exists.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonatePackagePriceDTO> donatePackagePriceDTOS = myMetfoneBusinesDao.findDonatePackagePriceById(Long.parseLong(id));
            if (donatePackagePriceDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (donatePackagePriceDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackagePrice.notFound.", language);
            }
            DonatePackagePrice donatePackagePrice = new DonatePackagePrice();
            donatePackagePrice.setId(Long.parseLong(id));
            donatePackagePrice.setDonatePackageId(Long.parseLong(donatePackageId));
            donatePackagePrice.setPaymentMethod(paymentMethod);
            donatePackagePrice.setPrice(Float.parseFloat(price));
            donatePackagePrice.setUnit(unit);
            donatePackagePrice.setStatus(Integer.parseInt(status));
            donatePackagePrice.setUpdatedBy(createdBy);
            Integer resultDonatePackagePrice = myMetfoneBusinesDao.editDonatePackagePrice(donatePackagePrice);
            if (resultDonatePackagePrice == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while edit donate package price: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean deleteDonatePackagePrice(RequestBean request, String language) {
        logger.info("Start business deleteDonatePackagePrice of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("donatePackageId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("donatePackageId").toString())) {
                logger.info("Error request : donatePackageId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.donatePackageId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String donatePackageId = request.getWsRequest().get("donatePackageId").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
                Long.parseLong(donatePackageId);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number...." + id + "||" + donatePackageId);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonatePackagePriceDTO> donatePackagePriceDTOS = myMetfoneBusinesDao.findDonatePackagePriceById(Long.parseLong(id));
            if (donatePackagePriceDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (donatePackagePriceDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donatePackagePrice.notFound.", language);
            }

            Integer result = myMetfoneBusinesDao.deleteDonatePackagePrice(Long.parseLong(id), Long.parseLong(donatePackageId));
            if (result == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while delete donate package price: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getAllDonatePackagePrice(RequestBean request, String language) {
        logger.info("Start business getAllDonatePackagePrice of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("donatePackageId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("donatePackageId").toString())) {
                logger.info("Error request : donatePackageId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.donatePackageId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String donatePackageId = request.getWsRequest().get("donatePackageId").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(request.getWsRequest().get("donatePackageId").toString().trim());
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonatePackagePriceDTO> donatePackagePriceDTOS = myMetfoneBusinesDao.getAllDonatePackagePrice(Long.parseLong(donatePackageId), account);
            if (donatePackagePriceDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donatePackagePriceDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get all donate package price: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean addDiscount(RequestBean request, String language) {
        logger.info("Start business addDiscount of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("code")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("code").toString())) {
                logger.info("Error request : code is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.code.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("discount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("discount").toString())) {
                logger.info("Error request : discount is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.discount.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("unit")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("unit").toString())) {
                logger.info("Error request : unit is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.unit.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("description")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("description").toString())) {
                logger.info("Error request : description is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.description.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String code = request.getWsRequest().get("code").toString().trim();
            String discountValue = request.getWsRequest().get("discount").toString().trim();
            String unit = request.getWsRequest().get("unit").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String description = request.getWsRequest().get("description").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Float.parseFloat(discountValue);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DiscountDTO> discounts = myMetfoneBusinesDao.findDiscountByCode(null, code);
            if (discounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (!discounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.discount.exists.", language);
            }

            Discount discount = new Discount();
            Long id = myMetfoneBusinesDao.getSequence(Config.SEQUENCE_MYMETFONE_DISCOUNT);
            discount.setId(id);
            discount.setCode(code);
            discount.setDiscount(Float.parseFloat(discountValue));
            discount.setUnit(unit);
            discount.setStatus(Integer.parseInt(status));
            discount.setDescription(description);
            discount.setCreatedBy(createdBy);
            discount.setUpdatedBy(createdBy);
            Integer resultDiscount = myMetfoneBusinesDao.addDiscount(discount);
            if (resultDiscount == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setUserMsg("Success");
                bean.setMessage("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while add new discount: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean editDiscount(RequestBean request, String language) {
        logger.info("Start business editDiscount of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("code")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("code").toString())) {
                logger.info("Error request : code is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.code.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("discount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("discount").toString())) {
                logger.info("Error request : discount is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.discount.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("unit")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("unit").toString())) {
                logger.info("Error request : unit is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.unit.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("description")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("description").toString())) {
                logger.info("Error request : description is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.discount.description.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String code = request.getWsRequest().get("code").toString().trim();
            String discountValue = request.getWsRequest().get("discount").toString().trim();
            String unit = request.getWsRequest().get("unit").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String description = request.getWsRequest().get("description").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
                Float.parseFloat(discountValue);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DiscountDTO> discountDTOS = myMetfoneBusinesDao.findDiscountById(Long.parseLong(id));
            if (discountDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (discountDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.discount.notFound.", language);
            }

            Discount discount = new Discount();
            discount.setId(Long.parseLong(id));
            discount.setCode(code);
            discount.setDiscount(Float.parseFloat(discountValue));
            discount.setUnit(unit);
            discount.setStatus(Integer.parseInt(status));
            discount.setDescription(description);
            discount.setCreatedBy(createdBy);
            discount.setUpdatedBy(createdBy);

            List<DiscountDTO> discounts = myMetfoneBusinesDao.findDiscountByCode(discount.getId(), discount.getCode());
            if (discounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (!discounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.discount.exists.", language);
            }

            Integer resultDiscount = myMetfoneBusinesDao.editDiscount(discount);
            if (resultDiscount == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while edit discount: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean deleteDiscount(RequestBean request, String language) {
        logger.info("Start business deleteDiscount of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number...." + id);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DiscountDTO> discountDTOS = myMetfoneBusinesDao.findDiscountById(Long.parseLong(id));
            if (discountDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (discountDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.discount.notFound.", language);
            }

            //Check if 
            Integer resultDiscount = myMetfoneBusinesDao.deleteDiscount(Long.parseLong(id));
            if (resultDiscount == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while delete discount: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getDiscount(RequestBean request, String language) {
        logger.info("Start business getDiscount of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DiscountDTO> discountDTOS = myMetfoneBusinesDao.findDiscountById(Long.parseLong(id));
            if (discountDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (discountDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.discount.notFound.", language);
            } else {
                bean.setErrorCode(Constants.SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(discountDTOS.get(0));
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get discount: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getAllDiscount(RequestBean request, String language) {
        logger.info("Start business getAllDiscount of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            String account = request.getWsRequest().get("account").toString().trim();

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DiscountDTO> discountDTOS = myMetfoneBusinesDao.getAllDiscount();
            if (discountDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(discountDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get all discount: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean searchDiscount(RequestBean request, String language) {
        logger.info("Start business searchDiscount of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String search = null;
            String searchFor = null;
            String status = null;
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String account = request.getWsRequest().get("account").toString().trim();

            if (DataUtil.isNullObject(request.getWsRequest().get("searchFor")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("searchFor").toString())) {
                logger.info("Error request : searchFor is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.searchFor.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (request.getWsRequest().get("search") == null) {
                logger.info("Error request : search is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.search.", language);
            }

            Integer searchForValue = null;
            Integer statusValue = null;
            try {
                searchForValue = Integer.parseInt(request.getWsRequest().get("searchFor").toString().trim());
                statusValue = Integer.parseInt(request.getWsRequest().get("status").toString().trim());
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }
            search = request.getWsRequest().get("search").toString();

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            if (!Objects.equals(STATUS_ACTIVE, statusValue) && !Objects.equals(STATUS_INACTIVE, statusValue) && !Objects.equals(STATUS_ALL, statusValue)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<DiscountDTO> discountDTOS = myMetfoneBusinesDao.searchDiscount(search, searchForValue, statusValue);

            if (discountDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(discountDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while search discount: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean addEmoneyWallet(RequestBean request, String language) {
        logger.info("Start business addEmoneyWallet of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("customerName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("customerName").toString())) {
                logger.info("Error request : customerName is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.emoneyWallet.customerName.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("camId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camId").toString())) {
                logger.info("Error request : camId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.emoneyWallet.camId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("emoneyAccount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("emoneyAccount").toString())) {
                logger.info("Error request : emoneyAccount is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.emoneyWallet.emoneyAccount.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String customerName = request.getWsRequest().get("customerName").toString().trim();
            String camId = request.getWsRequest().get("camId").toString().trim();
            String emoneyAccount = request.getWsRequest().get("emoneyAccount").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(camId);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            if (!createdBy.matches(REGEX)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.nonnum.", language);
            }
            if (!emoneyAccount.matches(REGEX)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.emoneyaccount.nonnum.", language);
            }
            if (!camId.matches(REGEX)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.camid.nonnum.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<EmoneyWalletDTO> emoneyWalletDTOS = myMetfoneBusinesDao.findEmoneyWalletByEmoneyAccount(null, emoneyAccount);

            if (emoneyWalletDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (!emoneyWalletDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.emoneyWallet.exists.", language);
            }

            //check camId available in db => show error
            boolean isCamidExist = myMetfoneBusinesDao.isCamIdExistInEmoneyWallet(camId);
            if (isCamidExist) {
                logger.info("Error request : Camid available in db");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.camid.", language);
            }

            EmoneyWallet emoneyWallet = new EmoneyWallet();
            Long id = myMetfoneBusinesDao.getSequence(Config.SEQUENCE_MYMETFONE_EMONEY_WALLET);
            emoneyWallet.setId(id);
            emoneyWallet.setCustomerName(customerName);
            emoneyWallet.setCamId(Long.parseLong(camId));
            emoneyWallet.setEmoneyAccount(emoneyAccount);
            emoneyWallet.setStatus(Integer.parseInt(status));
            emoneyWallet.setCreatedBy(createdBy);
            emoneyWallet.setUpdatedBy(createdBy);

            Integer resultEmoneyWallet = myMetfoneBusinesDao.addEmoneyWallet(emoneyWallet);
            if (resultEmoneyWallet == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while add new emoney wallet: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean editEmoneyWallet(RequestBean request, String language) {
        logger.info("Start business editEmoneyWallet of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("customerName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("customerName").toString())) {
                logger.info("Error request : customerName is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.emoneyWallet.customerName.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("camId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camId").toString())) {
                logger.info("Error request : camId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.emoneyWallet.camId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("emoneyAccount")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("emoneyAccount").toString())) {
                logger.info("Error request : emoneyAccount is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.emoneyWallet.emoneyAccount.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String customerName = request.getWsRequest().get("customerName").toString().trim();
            String camId = request.getWsRequest().get("camId").toString().trim();
            String emoneyAccount = request.getWsRequest().get("emoneyAccount").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
                Long.parseLong(camId);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<EmoneyWalletDTO> emoneyWalletDTOS = myMetfoneBusinesDao.findEmoneyWalletById(Long.parseLong(id));
            if (emoneyWalletDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (emoneyWalletDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.emoneyWallet.notFound.", language);
            }

            EmoneyWallet emoneyWallet = new EmoneyWallet();
            emoneyWallet.setId(Long.parseLong(id));
            emoneyWallet.setCustomerName(customerName);
            emoneyWallet.setCamId(Long.parseLong(camId));
            emoneyWallet.setEmoneyAccount(emoneyAccount);
            emoneyWallet.setStatus(Integer.parseInt(status));
            emoneyWallet.setUpdatedBy(createdBy);

            List<EmoneyWalletDTO> emoneyWallets = myMetfoneBusinesDao.findEmoneyWalletByEmoneyAccount(emoneyWallet.getId(), emoneyWallet.getEmoneyAccount());

            if (emoneyWallets == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (!emoneyWallets.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.emoneyWallet.exists.", language);
            }

            Integer resultEmoneyWallet = myMetfoneBusinesDao.editEmoneyWallet(emoneyWallet);
            if (resultEmoneyWallet == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while edit emoney wallet: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean deleteEmoneyWallet(RequestBean request, String language) {
        logger.info("Start business deleteEmoneyWallet of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<EmoneyWalletDTO> emoneyWalletDTOS = myMetfoneBusinesDao.findEmoneyWalletById(Long.parseLong(id));
            if (emoneyWalletDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (emoneyWalletDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.emoneyWallet.notFound.", language);
            }

            Integer resultEmoneyWallet = myMetfoneBusinesDao.deleteEmoneyWallet(Long.parseLong(id));
            if (resultEmoneyWallet == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while delete emoney wallet: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean searchEmoneyWallet(RequestBean request, String language) {
        logger.info("Start business searchEmoneyWallet of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String search = null;
            String searchFor = null;
            String status = null;
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            if (DataUtil.isNullObject(request.getWsRequest().get("searchFor")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("searchFor").toString())) {
                logger.info("Error request : searchFor is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.searchFor.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (request.getWsRequest().get("search") == null) {
                logger.info("Error request : search is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.search.", language);
            }

            String account = request.getWsRequest().get("account").toString().trim();
            search = request.getWsRequest().get("search").toString().trim();
            searchFor = request.getWsRequest().get("searchFor").toString().trim();
            status = request.getWsRequest().get("status").toString().trim();

            Integer searchForValue = null;
            Integer statusValue = null;
            try {
                if (searchFor != null) {
                    searchForValue = Integer.parseInt(request.getWsRequest().get("searchFor").toString().trim());
                }
                if (status != null) {
                    statusValue = Integer.parseInt(request.getWsRequest().get("status").toString().trim());
                }
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            if (!Objects.equals(STATUS_ACTIVE, statusValue) && !Objects.equals(STATUS_INACTIVE, statusValue) && !Objects.equals(STATUS_ALL, statusValue)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<EmoneyWalletDTO> emoneyWalletDTOS = myMetfoneBusinesDao.searchEmoneyWallet(search, searchForValue, statusValue);

            if (emoneyWalletDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(emoneyWalletDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while search emoney wallet: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getEmoneyWallet(RequestBean request, String language) {
        logger.info("Start business getEmoneyWallet of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<EmoneyWalletDTO> emoneyWalletDTOS = myMetfoneBusinesDao.findEmoneyWalletById(Long.parseLong(id));

            if (emoneyWalletDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (emoneyWalletDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.emoneyWallet.notFound.", language);
            } else {
                bean.setErrorCode(Constants.SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(emoneyWalletDTOS.get(0));
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get emoney wallet: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getAllEmoneyWallet(RequestBean request, String language) {
        logger.info("Start business getAllEmoneyWallet of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("pageSize")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageSize").toString())) {
                logger.info("Error request : pageSize is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.pageSize.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("pageNum")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNum").toString())) {
                logger.info("Error request : pageNum is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.pageNum.empty.", language);
            }

            String pageSize = request.getWsRequest().get("pageSize").toString().trim();
            String pageNum = request.getWsRequest().get("pageNum").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Integer.parseInt(pageSize);
                Integer.parseInt(pageNum);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<EmoneyWalletDTO> emoneyWalletDTOS = myMetfoneBusinesDao.getAllEmoneyWallet(Integer.parseInt(pageSize), Integer.parseInt(pageNum));
            Long totalRecords = myMetfoneBusinesDao.countTotalEmoneyWallet();
            EmoneyWalletBean emoneyWalletBean = new EmoneyWalletBean();
            if (emoneyWalletDTOS == null || totalRecords == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                emoneyWalletBean.setEmoneyWalletList(emoneyWalletDTOS);
                emoneyWalletBean.setTotalRecords(totalRecords);
                bean.setErrorCode(Constants.SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(emoneyWalletBean);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get all emoney wallet: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean donateGiftPackage(RequestBean request, String language) {
        logger.info("Start business donateGiftPackage of LoyaltyRoutingServiceImpl");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("donatePackageId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("donatePackageId").toString())) {
                logger.info("Error request : donatePackageId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.donatePackageId.empty.", language);
            }
            if (request.getWsRequest().get("camId") == null) {
                logger.info("Error request : camId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "camId.camid.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("channelId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelId").toString())) {
                logger.info("Error request : channelId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.channelId.empty.", language);
            }
            if (request.getWsRequest().get("customerName") == null) {
                logger.info("Error request : Customer_name is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.emoneyWallet.customerName.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("channelName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelName").toString())) {
                logger.info("Error request : Channel_name is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.channelName.empty.", language);
            }
            if (request.getWsRequest().get("phoneNumber") == null) {
                logger.info("Error request : phoneNumber is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.isdn.empty.", language);
            }
            if (request.getWsRequest().get("otp") == null) {
                logger.info("Error request : otp is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.otp.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("paymentMethod")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("paymentMethod").toString())) {
                logger.info("Error request : paymentMethod is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.paymentMethod.", language);
            }
            if (null == (request.getWsRequest().get("comment"))) {
                logger.info("Error request : comment is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.comment.", language);
            }

            //Check OTP valid
            String otp = request.getWsRequest().get("otp").toString();
            String service = "wsGetOTPDonate";
            String paymentMethod = request.getWsRequest().get("paymentMethod").toString();
            //Validate input
            String donateGiftPackageID = request.getWsRequest().get("donatePackageId").toString();
            String camId = request.getWsRequest().get("camId").toString();
            String comment = request.getWsRequest().get("comment").toString();
            Long channelId = Long.valueOf(request.getWsRequest().get("channelId").toString());
            String channelName = request.getWsRequest().get("channelName").toString();
            String customerName = request.getWsRequest().get("customerName").toString();
            boolean inputValid = myMetfoneBusinesDao.isValidInput(Long.valueOf(donateGiftPackageID), "DONATE_PACKAGE");
            if (!inputValid) {
                logger.info("Error request : donateGiftPackageID not found");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donatePackagePrice.donatePackageId.empty.", language);
            }
            inputValid = myMetfoneBusinesDao.isValidInput(channelId, "CHANNEL");
            if (!inputValid) {
                logger.info("Error request : channel_id not found");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.channelId.empty.", language);
            }
            //inputValid = myMetfoneBusinesDao.isValidInput(Long.valueOf(camId), "ACCOUNT");
//            if (!inputValid) {
//                logger.info("Error request : cam_id not found");
//                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "camId.camid.empty.", language);
//            }
            Long lockTime = Long.valueOf(environment.getProperty("LOCK_TIME", "5"));
            Long lockSecond = Long.valueOf(environment.getProperty("LOCK_SECOND", "600"));

            Double price = myMetfoneBusinesDao.getPriceOfDonatePackageId(donateGiftPackageID, paymentMethod);
            //save log
            Map<String, String> mappingData = myMetfoneBusinesDao.getBonusInformationForSaveLogDonate(Long.valueOf(donateGiftPackageID), paymentMethod);
            Double coin = StringUtils.isNotEmpty(mappingData.get("coin")) ? Double.valueOf(mappingData.get("coin")) : 0L;
            Double coinAfterDiscount = myMetfoneBusinesDao.calculateTotalPriceAfterDiscount(coin, channelId);
            Long status = 0L;
            String description = "";
            String phoneNumber = request.getWsRequest().get("phoneNumber").toString();
            phoneNumber = DataUtil.fomatIsdn(phoneNumber);
            if (Constants.UNIT_METFONE.equals(paymentMethod)) {

                if (StringUtils.isEmpty(phoneNumber)) {
                    logger.info("Error request : phoneNumber is null or empty");
                    return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.isdn.empty.", language);
                }

                Otp otpObj = myMetfoneBusinesDao.getOtpByIsdnAndService(phoneNumber, service, null);
                //Check if phoneNumber is locking => error
                if (DataUtil.isNullObject(otpObj) || otpObj.getLockUntil() != null && otpObj.getLockUntil().after(new Date())) {
                    //lock this isdn
                    if (!DataUtil.isNullObject(otpObj)) {
                        logger.info("Error request : phoneNumber input wrong Otp more than 5 times");
                        bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                        bean.setMessage("Fail");
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("description", "request.over.otp.fail");
                        data.put("remainTime", "" + DateTimeUtils.secondBetween2Dates(new Date(), otpObj.getLockUntil()));
                        bean.setWsResponse(data);
                        return bean;
                    } else {
                        bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                        bean.setMessage("Fail");
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("description", "myMetfone.Ishare.otp.err");
                        bean.setWsResponse(data);
                        return bean;
                    }
                }
                otpObj = myMetfoneBusinesDao.getOtpByIsdnAndService(phoneNumber, service, otp);
                //Check otp input
                if (DataUtil.isNullObject(otpObj) || otpObj.getStatus() == 1) {
                    if (!DataUtil.isNullObject(otpObj) && otpObj.getStatus() == 1) {
                        //OTP is used
                        logger.info("Error request : OTP is used");
                        bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                        bean.setMessage("Fail");
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("description", "request.otp.is.used");
                        bean.setWsResponse(data);
                        return bean;
                    }

                    Long totalFail = myMetfoneBusinesDao.getTotalTimeFailOTP(phoneNumber, service);
                    if (totalFail >= lockTime) {
                        //lock this isdn
                        myMetfoneBusinesDao.lockIsdnWrongOtpSeveralTime(phoneNumber, service, lockSecond);
                        logger.info("Error request : phoneNumber input wrong Otp more than 5 times");
                        bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                        bean.setMessage("Fail");
                        Map<String, String> data = new HashMap<String, String>();
                        data.put("description", "request.over.otp.fail");
                        data.put("remainTime", "600");
                        bean.setWsResponse(data);
                        return bean;
                    } else {
                        myMetfoneBusinesDao.updateTotalTimeConfirmWrongOTP(phoneNumber, service, false);
                    }
                    logger.info("Error requesst : otp err ");
                    bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                    bean.setMessage("Fail");
                    Map<String, String> data = new HashMap<String, String>();
                    data.put("description", "myMetfone.Ishare.otp.err");
                    bean.setWsResponse(data);
                    return bean;
                }
                // OTP is okay => reset total_fail to 0 and set this OTP is used
                myMetfoneBusinesDao.updateTotalTimeConfirmWrongOTP(phoneNumber, service, true);

                //Check metfone's isdn
                String listIsdnMetfone = "97,88,71,31,60,66,67,68,90";
                String subIsdn = phoneNumber.substring(0, 2);
                if (!listIsdnMetfone.contains(subIsdn)) {
                    logger.info("Error request : phoneNumer is not belong to Metfone");
                    return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.isdn.empty.", language);
                }

                //Check call system Metfone real
                ExchMsg response = WebServiceUtil.chargeMoneyExchange(DataUtil.fomatIsdn855(phoneNumber), price, dao);
                if ("0".equals(response.getError())) {
                    status = 1L;
                } else {
                    status = 0L;
                }

                description = response.getDescription();
                if ("BALANCE_NOT_ENOUGH".equals(description)) {
                    description = "err.not.enough.balance";
                }

            }
            if (Constants.UNIT_POINT.equals(paymentMethod)) {
                if (StringUtils.isEmpty(camId)) {
                    logger.info("Error request : camId is null or empty");
                    return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "camId.camid.empty.", language);
                }
                logger.info("Start call API adjustAccountPoint ");
                Webservice ws = myvtgService.getWS("wsLoyaltyPointCustIds");
                if (ws == null) {
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
                }
                logger.info("Requesst send getAccountPointInfo");
                String url = ws.getUrl() + camId;
                BaseResponsesDto response = WebServiceUtil.callApiGet(url);
                ws = myvtgService.getWS(Constants.WEBSERVICE_CHANGE_LOYALTY);
                double sumPoint = 0D;
                Long currDate = new Date().getTime();
                RequestDto rq = new RequestDto();
                if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    JSONObject jsonObj = new JSONObject(response.getMessageCode());
                    if ("000".equals(jsonObj.getString("code")) && response.getMessageCode().contains("listAccountPoint")) {
                        InforAccountPoint infoBean = CommonUtil.convertJsonStringToObject(jsonObj.toString(), InforAccountPoint.class);
                        sumPoint = infoBean.getListAccountPoint().stream().filter((accountPoint) -> (accountPoint.getPointExpireDate().getTime() > currDate && "2".equals(accountPoint.getPointType()))).map((accountPoint) -> accountPoint.getPointValue()).reduce(sumPoint, (accumulator, _item) -> accumulator + _item);
                    }
                }

                if (sumPoint < 0D || sumPoint < price) {
                    logger.info("Error request : Isdn not enough point to donate");
                    status = 0L;
                    description = "err.not.enough.point";
                } else {
                    rq.setCustId(Long.valueOf(camId));
                    rq.setPointAmount("-" + price);
                    rq.setTransTypeId("1000001");
                    rq.setPointId("1000001");
                    rq.setTransId("12");
                    String rqStr = CommonUtil.convertObjectToJsonString(rq);
                    logger.info("Request send adjustAccountPoint: " + rqStr);
                    response = WebServiceUtil.callApiRest(ws.getUrl(), rqStr);
                    if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                        JSONObject json = new JSONObject(response.getMessageCode());
                        if ("000".equals(json.getString("code"))) {
                            status = 1L;
                            logger.info("### Call Loyalty donate success");
                        }
                        description = json.getString("message");
                    }
                }
            }
            Long chanelDiscountDetailId = myMetfoneBusinesDao.getChannelDiscountDetailIdForSaveLogDonate(channelId);
            List<Long> totalDiscountValue = myMetfoneBusinesDao.getTotalDiscountOfChannelFromId(channelId);
            //Long totalDiscount = totalDiscountValue.stream().reduce(0L, (acc, item) -> acc + item);
            //sumPoint = infoBean.getListAccountPoint().stream().filter((accountPoint) -> 
            //(accountPoint.getPointExpireDate().getTime() > currDate && "2".equals(accountPoint.getPointType())))
            //.map((accountPoint) -> accountPoint.getPointValue()).reduce(sumPoint, (accumulator, _item) -> accumulator + _item);
            String discountValueList = totalDiscountValue.stream()
                    .map(n -> String.valueOf(n))
                    .collect(Collectors.joining("&"));
            //
            //Save log
            myMetfoneBusinesDao.saveLogAfterDonate(customerName, phoneNumber, camId, mappingData.get("coin") != null ? mappingData.get("coin") : "",
                    coin, mappingData.get("code") != null ? mappingData.get("code") : "", mappingData.get("unit") != null ? mappingData.get("unit") : "",
                    coinAfterDiscount, channelName, channelId, status, comment, paymentMethod, chanelDiscountDetailId, discountValueList);

            if (1L == status) {
                //Get thanks message
                String thankMessage = myMetfoneBusinesDao.getThankMessage(channelId, language);
                thankMessage = thankMessage.replace("{{user}}", customerName);
                thankMessage = thankMessage.replace("{{coin}}", coin.toString());

                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                Map<String, String> data = new HashMap<String, String>();
                data.put("description", description);
                data.put("thankMessage", thankMessage);
                bean.setWsResponse(data);
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                Map<String, String> data = new HashMap<String, String>();
                data.put("description", description);
                bean.setWsResponse(data);
            }

            return bean;
        } catch (NumberFormatException e) {
            logger.error("### An error occured while donateGiftPackage: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        } catch (Exception ex) {
            logger.error("### An error occured while donateGiftPackage: ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);

        }
    }

    @Override
    public BaseResponseBean searchDonateTransaction(RequestBean request, String language) {
        logger.info("Start business searchDonateTransaction of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String search = "";
            String searchFor = null;
            String status = null;
            String from = null;
            String to = null;

            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("searchFor")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("searchFor").toString())) {
                logger.info("Error request : searchFor is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.searchFor.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (request.getWsRequest().get("search") == null) {
                logger.info("Error request : search is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.search.", language);
            }

            if (request.getWsRequest().get("from") == null) {
                logger.info("Error request : from is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.fromDate.empty.", language);
            }

            if (request.getWsRequest().get("to") == null) {
                logger.info("Error request : to is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.changeCard.toDate.empty.", language);
            }

            String account = request.getWsRequest().get("account").toString().trim();
            searchFor = request.getWsRequest().get("searchFor").toString().trim();
            search = request.getWsRequest().get("search").toString().trim();
            status = request.getWsRequest().get("status").toString().trim();
            from = request.getWsRequest().get("from").toString().trim();
            to = request.getWsRequest().get("to").toString().trim();

            Integer searchForValue = null;
            Integer statusValue = null;
            try {
                if (searchFor != null) {
                    searchForValue = Integer.parseInt(request.getWsRequest().get("searchFor").toString().trim());
                }
                if (status != null) {
                    statusValue = Integer.parseInt(request.getWsRequest().get("status").toString().trim());
                }
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (from != null && to != null) {
                    dateFormat.parse(from);
                    dateFormat.parse(to);
                }
            } catch (java.text.ParseException e) {
                logger.info("### Cannot parse string to date", e);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.parseStringToDate.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            if (!Objects.equals(STATUS_ACTIVE, statusValue) && !Objects.equals(STATUS_INACTIVE, statusValue) && !Objects.equals(STATUS_ALL, statusValue)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<DonateTransactionDTO> donateTransactionDTOS = myMetfoneBusinesDao.searchDonateTransaction(search, searchForValue, statusValue, from, to);

            if (donateTransactionDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donateTransactionDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while search donate transaction: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getDonateTransaction(RequestBean request, String language) {
        logger.info("Start business getDonateTransaction of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(id);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonateTransactionDTO> donateTransactionDTOS = myMetfoneBusinesDao.findDonateTransactionById(Long.parseLong(id));

            if (donateTransactionDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (donateTransactionDTOS.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.donateTransaction.notFound.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donateTransactionDTOS.get(0));
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get donate transaction: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getAllDonateTransaction(RequestBean request, String language) {
        logger.info("Start business getDonateTransaction of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("pageSize")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageSize").toString())) {
                logger.info("Error request : pageSize is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.pageSize.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("pageNum")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("pageNum").toString())) {
                logger.info("Error request : pageNum is null ");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.pageNum.empty.", language);
            }

            String pageSize = request.getWsRequest().get("pageSize").toString().trim();
            String pageNum = request.getWsRequest().get("pageNum").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();

            try {
                Integer.parseInt(pageSize);
                Integer.parseInt(pageNum);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonateTransactionDTO> donateTransactionDTOS = myMetfoneBusinesDao.getAllDonateTransaction(Integer.parseInt(pageSize), Integer.parseInt(pageNum));
            Long totalRecords = myMetfoneBusinesDao.countTotalDonateTransaction();
            DonateTransactionBean donateTransactionBean = new DonateTransactionBean();
            if (donateTransactionDTOS == null || totalRecords == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                donateTransactionBean.setDonateTransactionList(donateTransactionDTOS);
                donateTransactionBean.setTotalRecords(totalRecords);
                bean.setErrorCode(Constants.SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donateTransactionBean);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get all donate transaction: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean exportDonateTransaction(RequestBean request, String language) {
        logger.info("Start business exportDonateTransaction of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String search = null;
            String searchFor = null;
            String status = null;
            String from = null;
            String to = null;
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("searchFor")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("searchFor").toString())) {
                logger.info("Error request : searchFor is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.searchFor.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (request.getWsRequest().get("search") == null) {
                logger.info("Error request : search is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.search.", language);
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("from")) || !DataUtil.isNullOrEmpty(request.getWsRequest().get("from").toString())) {
                from = request.getWsRequest().get("from").toString().trim();
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("to")) || !DataUtil.isNullOrEmpty(request.getWsRequest().get("to").toString())) {
                to = request.getWsRequest().get("to").toString().trim();
            }

            String account = request.getWsRequest().get("account").toString().trim();
            search = request.getWsRequest().get("search").toString().trim();
            searchFor = request.getWsRequest().get("searchFor").toString().trim();
            status = request.getWsRequest().get("status").toString().trim();

            Integer searchForValue = null;
            Integer statusValue = null;
            try {
                if (searchFor != null) {
                    searchForValue = Integer.parseInt(request.getWsRequest().get("searchFor").toString().trim());
                }
                if (status != null) {
                    statusValue = Integer.parseInt(request.getWsRequest().get("status").toString().trim());
                }
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (from != null && to != null) {
                    dateFormat.parse(from);
                    dateFormat.parse(to);
                }
            } catch (java.text.ParseException e) {
                logger.info("### Cannot parse string to date", e);
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.parseStringToDate.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<DonateTransactionDTO> donateTransactionDTOS = myMetfoneBusinesDao.searchDonateTransaction(search, searchForValue, statusValue, from, to);

            if (donateTransactionDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(donateTransactionDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while export donate transaction: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean addChannelDiscountDetail(RequestBean request, String language) {
        logger.info("Start business addChannelDiscountDetail of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("channelId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelId").toString())) {
                logger.info("Error request : channelId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.channelId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("discountId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("discountId").toString())) {
                logger.info("Error request : discountId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.discountId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                logger.info("Error request : status is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.status.emptyOriInvalid.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }

            String channelId = request.getWsRequest().get("channelId").toString().trim();
            String discountId = request.getWsRequest().get("discountId").toString().trim();
            String status = request.getWsRequest().get("status").toString().trim();
            String createdBy = request.getWsRequest().get("account").toString().trim();

            try {
                Long.parseLong(channelId);
                Long.parseLong(discountId);
                Integer.parseInt(status);
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            if (Integer.parseInt(status) != 1 && Integer.parseInt(status) != 0) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.status.emptyOriInvalid.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(createdBy);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<Object> channelDiscountDetails = myMetfoneBusinesDao.findChannelDiscountDetail(null, Long.parseLong(channelId), Long.parseLong(discountId));

            if (channelDiscountDetails == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (!channelDiscountDetails.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.channelDiscountDetail.exists.", language);
            }

            ChannelDiscountDetail channelDiscountDetail = new ChannelDiscountDetail();
            Long id = myMetfoneBusinesDao.getSequence(Config.SEQUENCE_MYMETFONE_CHANNEL_DISCOUNT_DETAIL);
            channelDiscountDetail.setId(id);
            channelDiscountDetail.setChannelId(Long.parseLong(channelId));
            channelDiscountDetail.setDiscountId(Long.parseLong(discountId));
            channelDiscountDetail.setStatus(Integer.parseInt(status));
            channelDiscountDetail.setCreatedBy(createdBy);
            channelDiscountDetail.setUpdatedBy(createdBy);

            Integer resultChannelDiscountDetail = myMetfoneBusinesDao.addChannelDiscountDetail(channelDiscountDetail);
            if (resultChannelDiscountDetail == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while add new channel discount detail: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean editOrAddChannel(RequestBean request, String language) {
        logger.info("Start business editOrAddChannel of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String imageUrl = null;
            String camId = null;
            String msisdn = null;
            String bannerUrl = null;
            String comment = null;
            String commentKh = null;
            String status = null;

            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.id.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("channelName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelName").toString())) {
                logger.info("Error request : channelName is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.channelName.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("imageUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("imageUrl").toString())) {
                imageUrl = "";
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("camId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camId").toString())) {
                camId = "";
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("msisdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("msisdn").toString())) {
                msisdn = "";
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("bannerUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("bannerUrl").toString())) {
                bannerUrl = "";
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("comment")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("comment").toString())) {
                comment = "";
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("commentKh")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("commentKh").toString())) {
                commentKh = "";
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("status")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                status = "";
            }

            String id = request.getWsRequest().get("id").toString();
            String channelName = request.getWsRequest().get("channelName").toString().trim();
            if (imageUrl == null) {
                imageUrl = request.getWsRequest().get("imageUrl").toString().trim();
            }
            if (camId == null) {
                camId = request.getWsRequest().get("camId").toString().trim();
            }
            if (msisdn == null) {
                msisdn = request.getWsRequest().get("msisdn").toString().trim();
            }
            if (bannerUrl == null) {
                bannerUrl = request.getWsRequest().get("bannerUrl").toString().trim();
            }
            if (comment == null) {
                comment = request.getWsRequest().get("comment").toString().trim();
            }
            if (commentKh == null) {
                commentKh = request.getWsRequest().get("commentKh").toString().trim();
            }
            if (status == null) {
                status = request.getWsRequest().get("status").toString().trim();
            }

            try {
                if (StringUtils.isNotEmpty(id)) {
                    Long.parseLong(id);
                    if (camId != null && !camId.isEmpty()) {
                        Long.parseLong(camId);
                        Long.parseLong(status);
                    }
                }
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }

            Channel channel = new Channel();
            channel.setId(Long.parseLong(id));
            channel.setChannelName(channelName);
            channel.setImageUrl(imageUrl);
            if (camId != null && !camId.isEmpty()) {
                channel.setCamId(Long.parseLong(camId));
            }
            channel.setMsisdn(msisdn);
            channel.setStatus(StringUtils.isNotEmpty(status) ? Long.valueOf(status) : null);
            channel.setComment(comment);
            channel.setBannerUrl(bannerUrl);
            channel.setCommentKh(commentKh);

            Integer resultChannel = null;
            List<ChannelDTO> channelDTOS = myMetfoneBusinesDao.findChannelById(Long.parseLong(id));
            if (channelDTOS == null || channelDTOS.size() == 0) {
                resultChannel = myMetfoneBusinesDao.addChannel(channel);
            } else {
                resultChannel = myMetfoneBusinesDao.editChannel(channel);
            }
            if (resultChannel == 1) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while edit or add channel: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListChannel(RequestBean request, String language) {
        logger.info("Start business getListChannel of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            List<ChannelDTO> channelDTOS = myMetfoneBusinesDao.getListChannel();
            if (channelDTOS == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(channelDTOS);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get list channel: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getListPaymentMethod(RequestBean request, String language) {
        logger.info("Start business getListPaymentMethod of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            List<String> units = myMetfoneBusinesDao.getListPaymentMethod();
            if (units == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(units);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get list payment method: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean getUnitPaymentMethod(RequestBean request, String language) {
        logger.info("Start business getUnitPaymentMethod of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("paymentMethod")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("paymentMethod").toString())) {
                logger.info("Error request : paymentMethod is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.configDonate.paymentMethod.empty.", language);
            }
            String paymentMethod = request.getWsRequest().get("paymentMethod").toString().trim();
            List<String> units = myMetfoneBusinesDao.findUnitPaymentMethod(paymentMethod);
            if (units == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (units.isEmpty()) {
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.configDonate.unit.notFound.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(units.get(0));
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while get unit by payment method: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    public BaseResponseBean getAllChannelDiscountDetail(RequestBean request, String language) {
        logger.info("Start business getAllChannelDiscountDetail of LoyaltyRoutingServiceImpl");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("channelId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelId").toString())) {
                logger.info("Error request : paymentMethod is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.configDonate.paymentMethod.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : paymentMethod is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.configDonate.paymentMethod.empty.", language);
            }

            String channelId = request.getWsRequest().get("channelId").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();
            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }
            List<ChannelDiscountDetailDTO> channelDiscountDetails = myMetfoneBusinesDao.getAllChannelDiscountDetail(channelId);
            Map<String, Object> dataMap = new HashMap<>();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setUserMsg("Success");
            dataMap.put("total", channelDiscountDetails == null ? 0 : channelDiscountDetails.size());
            dataMap.put("channelDiscountDetails", channelDiscountDetails);
            bean.setWsResponse(dataMap);
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while getAllChannelDiscountDetail: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    public BaseResponseBean getChannelDetail(RequestBean request, String language) {
        logger.info("Start business getChannelDetail of LoyaltyRoutingServiceImpl");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("channelId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelId").toString())) {
                logger.info("Error request : channelId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.channelId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donateTransaction.account.empty.sss", language);
            }

            String channelId = request.getWsRequest().get("channelId").toString().trim();
            String account = request.getWsRequest().get("account").toString().trim();
            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }
            List<ChannelDTO> channelDiscountDetails = myMetfoneBusinesDao.getChannelDetail(channelId);
            Map<String, Object> dataMap = new HashMap<>();
            bean.setErrorCode(Constants.ERROR_SUCCESS);
            bean.setMessage("Success");
            bean.setUserMsg("Success");
            dataMap.put("total", channelDiscountDetails == null ? 0 : channelDiscountDetails.size());
            dataMap.put("channelDiscountDetails", channelDiscountDetails);
            bean.setWsResponse(dataMap);
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while getChannelDetail: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean addChannelCMS(RequestBean request, String language) {
        logger.info("Start business addChannelCMS of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String imageUrl = null;
            String camId = null;
            String msisdn = null;
            String bannerUrl = null;
            String comment = null;
            String commentKh = null;

            if (DataUtil.isNullObject(request.getWsRequest().get("channelName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelName").toString())) {
                logger.info("Error request : channelName is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.channelName.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("imageUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("imageUrl").toString())) {
                logger.info("Error request : imageUrl is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.imageUrl.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("camId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camId").toString())) {
                logger.info("Error request : camId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.camId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("msisdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("msisdn").toString())) {
                logger.info("Error request : msisdn is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.msisdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("bannerUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("bannerUrl").toString())) {
                logger.info("Error request : bannerUrl is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.bannerUrl.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("comment")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("comment").toString())) {
                logger.info("Error request : comment is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.comment.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("commentKh")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("commentKh").toString())) {
                logger.info("Error request : commentKh is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.comment.", language);
            }

            String channelName = request.getWsRequest().get("channelName").toString().trim();
            if (imageUrl == null) {
                imageUrl = request.getWsRequest().get("imageUrl").toString().trim();
            }
            if (camId == null) {
                camId = request.getWsRequest().get("camId").toString().trim();
            }
            if (msisdn == null) {
                msisdn = request.getWsRequest().get("msisdn").toString().trim();
            }
            if (bannerUrl == null) {
                bannerUrl = request.getWsRequest().get("bannerUrl").toString().trim();
            }
            if (comment == null) {
                comment = request.getWsRequest().get("comment").toString().trim();
            }
            if (commentKh == null) {
                commentKh = request.getWsRequest().get("commentKh").toString().trim();
            }

            Channel channel = new Channel();
            channel.setId(myMetfoneBusinesDao.getSequence("CHANNEL_SEQ"));
            channel.setChannelName(channelName);
            channel.setImageUrl(imageUrl);
            if (StringUtils.isNotEmpty(camId)) {
                channel.setCamId(Long.parseLong(camId));
            }
            channel.setMsisdn(msisdn);
            channel.setStatus(1L);
            channel.setBannerUrl(bannerUrl);
            channel.setComment(comment);
            channel.setCommentKh(commentKh);
            channel.setCreateDate(new Date());

            int result = myMetfoneBusinesDao.addChannel(channel);
            if (result > 0) {
                // Sync to another system
                /*http://uploadcamid.metfone.com.kh/camidApiService/app/createChannel?
                clientType=ios&
                channelId=197575&
                channelDesc&
                createdDate=2021%2F11%2F11 04%3A47%3A53&
                about&
                msisdn=974838938&
                channelName=KOY KOY Leo coi&
                userId=1000439752&
                bannerChannel=http%3A%2F%2Fmedia1camid.metfone.com.kh%2Fesport%2Fimages%2Fchannel%2Fbanner%2Fbanner_default%2F10.jpg&
                aboutTitle=what the favorite &
                avatarChannel&
                aboutImg
                 */
//                Map<String, Object> paramsMap = CommonUtils.buildRequestForGetHttp("", "", channel.getMsisdn(), channel.getChannelName(),
//                        channel.getCamId(), channel.getBannerUrl(), "", channel.getImageUrl(), "");
//                BaseResponsesDto response = WebServiceUtil.callApiPostWithParamURI("http://uploadcamid.metfone.com.kh/camidApiService/app/createChannel", paramsMap);
                BaseResponsesDto response = new BaseResponsesDto(200, "OK");
                if (HttpURLConnection.HTTP_OK != response.getStatusCode()) {
                    bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                    bean.setMessage("Fail");
                    bean.setUserMsg("Fail");
                } else {
                    bean.setErrorCode(Constants.ERROR_SUCCESS);
                    bean.setMessage("Success");
                    bean.setUserMsg("Success");
                }
                return bean;
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
                return bean;
            }

        } catch (Exception e) {
            logger.error("An error occured while edit or add channel: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean updateChannelCMS(RequestBean request, String language) {
        logger.info("Start business updateChannelCMS of MyMetfoneBusinessService");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String imageUrl = null;
            String camId = null;
            String msisdn = null;
            String bannerUrl = null;
            String comment = null;
            String commentKh = null;

            if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
                logger.info("Error request : id is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.channelId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("channelName")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelName").toString())) {
                logger.info("Error request : channelName is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.channelName.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("imageUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("imageUrl").toString())) {
                logger.info("Error request : imageUrl is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.imageUrl.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("camId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("camId").toString())) {
                logger.info("Error request : camId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.camId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("msisdn")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("msisdn").toString())) {
                logger.info("Error request : msisdn is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channel.msisdn.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("bannerUrl")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("bannerUrl").toString())) {
                logger.info("Error request : bannerUrl is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.bannerUrl.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("comment")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("comment").toString())) {
                logger.info("Error request : comment is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.comment.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("commentKh")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("commentKh").toString())) {
                logger.info("Error request : commentKh is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.donate.comment.", language);
            }

            String id = request.getWsRequest().get("id").toString().trim();
            String channelName = request.getWsRequest().get("channelName").toString().trim();
            if (imageUrl == null) {
                imageUrl = request.getWsRequest().get("imageUrl").toString().trim();
            }
            if (camId == null) {
                camId = request.getWsRequest().get("camId").toString().trim();
            }
            if (msisdn == null) {
                msisdn = request.getWsRequest().get("msisdn").toString().trim();
            }
            if (bannerUrl == null) {
                bannerUrl = request.getWsRequest().get("bannerUrl").toString().trim();
            }
            if (comment == null) {
                comment = request.getWsRequest().get("comment").toString().trim();
            }
            if (commentKh == null) {
                commentKh = request.getWsRequest().get("commentKh").toString().trim();
            }
            Channel channel = new Channel();
            channel.setId(Long.valueOf(id));
            channel.setChannelName(channelName);
            channel.setImageUrl(imageUrl);
            if (StringUtils.isNotEmpty(camId)) {
                channel.setCamId(Long.parseLong(camId));
            }
            channel.setMsisdn(msisdn);
            channel.setStatus(1L);
            channel.setBannerUrl(bannerUrl);
            channel.setComment(comment);
            channel.setCommentKh(commentKh);
            channel.setCreateDate(new Date());

            int result = myMetfoneBusinesDao.editChannel(channel);
            if (result > 0) {
                // Sync to another system
                /*http://uploadcamid.metfone.com.kh/camidApiService/app/createChannel?
                clientType=ios&
                channelId=197575&
                channelDesc&
                createdDate=2021%2F11%2F11 04%3A47%3A53&
                about&
                msisdn=974838938&
                channelName=KOY KOY Leo coi&
                userId=1000439752&
                bannerChannel=http%3A%2F%2Fmedia1camid.metfone.com.kh%2Fesport%2Fimages%2Fchannel%2Fbanner%2Fbanner_default%2F10.jpg&
                aboutTitle=what the favorite &
                avatarChannel&
                aboutImg
                 */
//                Map<String, Object> paramsMap = CommonUtils.buildRequestForGetHttp(channel.getId(), "", channel.getMsisdn(), channel.getChannelName(),
//                        channel.getCamId(), channel.getBannerUrl(), "", channel.getImageUrl(), "");
//                BaseResponsesDto response = WebServiceUtil.callApiPostWithParamURI("http://uploadcamid.metfone.com.kh/camidApiService/app/createChannel", paramsMap);
                BaseResponsesDto response = new BaseResponsesDto(200, "OK");
                if (HttpURLConnection.HTTP_OK != response.getStatusCode()) {
                    bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                    bean.setMessage("Fail");
                    bean.setUserMsg("Fail");
                } else {
                    bean.setErrorCode(Constants.ERROR_SUCCESS);
                    bean.setMessage("Success");
                    bean.setUserMsg("Success");
                }
                return bean;
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
                return bean;
            }

        } catch (Exception e) {
            logger.error("An error occured while edit or add channel: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    @Override
    public BaseResponseBean wsSearchChannel(RequestBean request, String language) {
        logger.info("Start business wsSearchChannel of LoyaltyRoutingServiceImpl");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String search = "";
            String searchFor = null;
            String status = null;
            String account = null;
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("search")) || !DataUtil.isNullOrEmpty(request.getWsRequest().get("search").toString())) {
                search = request.getWsRequest().get("search").toString().trim();
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("searchFor")) || !DataUtil.isNullOrEmpty(request.getWsRequest().get("searchFor").toString())) {
                searchFor = request.getWsRequest().get("searchFor").toString().trim();
            }
            if (!DataUtil.isNullObject(request.getWsRequest().get("status")) || !DataUtil.isNullOrEmpty(request.getWsRequest().get("status").toString())) {
                status = request.getWsRequest().get("status").toString().trim();
            }

            Long searchForValue = null;
            Long statusValue = null;
            try {
                if (searchFor != null) {
                    searchForValue = Long.parseLong(request.getWsRequest().get("searchFor").toString().trim());
                }
                if (status != null) {
                    statusValue = Long.parseLong(request.getWsRequest().get("status").toString().trim());
                }
            } catch (NumberFormatException exception) {
                logger.info("### Value is not a number....");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.not.number.", language);
            }
            if (account == null) {
                account = request.getWsRequest().get("account").toString();
            }
            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            List<ChannelDTO> channelDTOs = myMetfoneBusinesDao.searchChannel(statusValue, searchForValue, search);

            if (channelDTOs == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
                bean.setWsResponse(channelDTOs);
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while wsSearchChannel: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }

    public BaseResponseBean wsDeleteDiscountChannel(RequestBean request, String language) {
        logger.info("Start business wsDeleteDiscountChannel of LoyaltyRoutingServiceImpl");
        BaseResponseBean bean = new BaseResponseBean();
        try {
            if (DataUtil.isNullObject(request.getWsRequest().get("account")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("account").toString())) {
                logger.info("Error request : account is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.account.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("channelId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("channelId").toString())) {
                logger.info("Error request : channelId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.channelId.empty.", language);
            }
            if (DataUtil.isNullObject(request.getWsRequest().get("discountId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("discountId").toString())) {
                logger.info("Error request : discountId is null or empty");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.discountId.empty.", language);
            }

            String account = request.getWsRequest().get("account").toString();
            String channelId = request.getWsRequest().get("channelId").toString();
            String discountId = request.getWsRequest().get("discountId").toString();

            if (!NumberUtils.isNumber(channelId)) {
                logger.info("Error request : channeId invalid");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.channelId.empty.", language);
            }

            if (!NumberUtils.isNumber(discountId)) {
                logger.info("Error request : discountId invalid");
                return responseUtil.responseBeanCompact(Constants.ERROR_PARAMETER_INVALID, "myMetfone.channelDiscountDetail.discountId.empty.", language);
            }

            List<String> accounts = myMetfoneBusinesDao.findUser(account);
            if (accounts == null) {
                return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
            } else if (accounts.isEmpty()) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.account.notFound.", language);
            }

            boolean isDeleteSuccess = myMetfoneBusinesDao.deleteDiscountChannel(channelId, discountId, account);
            if (isDeleteSuccess) {
                bean.setErrorCode(Constants.ERROR_SUCCESS);
                bean.setMessage("Success");
                bean.setUserMsg("Success");
            } else {
                bean.setErrorCode(Constants.ERROR_PARAMETER_INVALID);
                bean.setMessage("Fail");
                bean.setUserMsg("Fail");
            }
            return bean;
        } catch (Exception e) {
            logger.error("An error occured while wsDeleteDiscountChannel: ", e);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "myMetfone.failed.", language);
        }
    }
}
