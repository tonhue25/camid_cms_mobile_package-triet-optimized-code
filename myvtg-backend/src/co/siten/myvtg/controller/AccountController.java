package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseBean;
import co.siten.myvtg.bean.ResponseErrorBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.exception.CallApiCancelSubscriberException;
import co.siten.myvtg.exception.CallApiDausoException;
import co.siten.myvtg.model.myvtg.TransactionError;
import co.siten.myvtg.service.Account2Service;
import co.siten.myvtg.service.AccountService;
import co.siten.myvtg.service.MasterDataService;
import co.siten.myvtg.service.SubInfoService;
import co.siten.myvtg.util.AppUtil;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.CommonUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;

/**
 *
 * @author thomc
 *
 */
@RestController
@RequestMapping("/api/${version}/accounts")
@PropertySource(value = {"classpath:api.properties"})
public class AccountController extends BaseController {

    private static final Logger logger = Logger.getLogger(AccountController.class);
    @Autowired
    private Environment environment;
    @Autowired
    AccountService accountService;

    @Autowired
    SubInfoService subInfoService;

    @Autowired
    Account2Service account2Service;

    @Autowired
    MasterDataService masterDataService;

    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "/wsGetJobs", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetJobs(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            masterDataService.wsGetJobs(language, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetSubInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetSubInfo(@RequestBody RequestBean request) {
        logger.info("Start wsGetSubInfo API off AccountController");
        try {
//            if (!validateRequest(request)) {
//                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
//            }
            String language = "en";
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (!DataUtil.isNullObject(wsRequest.get("language")) && !DataUtil.isNullOrEmpty(wsRequest.get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            if (DataUtil.isNullOrEmpty(request.getUsername())) {
                logger.info("Error requesst : user name is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.Ishare.isdn.empty." + language)), HttpStatus.OK);
            }
            String isdn = request.getUsername();
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            accountService.wsGetSubInfo(isdn, subType, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }

    }

    @RequestMapping(value = "/wsGetPostageDetailInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetPostageDetailInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            HashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            Date startDate = AppUtil
                    .getTimeStartOfDay(Double.valueOf(wsRequest.get("startDate").toString()).longValue());
            Date endDate = AppUtil.getTimeEndOfDay(Double.valueOf(wsRequest.get("endDate").toString()).longValue());

            logger.info("startdate" + startDate.toString());
            logger.info("enddate" + endDate.toString());
            int postType = Integer.parseInt(wsRequest.get("postType").toString());
            int size = Integer.parseInt(wsRequest.get("pageSize") == null ? "0" : wsRequest.get("pageSize").toString());
            int page = Integer.parseInt(wsRequest.get("pageNum") == null ? "0" : wsRequest.get("pageNum").toString());
            String order = wsRequest.get("sort").toString();

            accountService.wsGetPostageDetailInfo(environment, isdn, subType, startDate, endDate, postType, size, page,
                    order, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    /**
     *
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetServices", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetServices(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            accountService.wsGetServices(isdn, language, bean);

            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    @RequestMapping(value = "/wsGetSubMainInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetSubMainInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            subInfoService.wsGetSubMainInfo(isdn, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    @RequestMapping(value = "/wsGetSubAccountInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetSubAccountInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            Object language = wsRequest.get("language");
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            accountService.wsGetSubAccountInfo(isdn, subType, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetHotNews", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetHotNews(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int limit = Integer.parseInt(wsRequest.get("limit").toString());
            masterDataService.wsGetHotNews(isdn, limit, language, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    @RequestMapping(value = "/wsGetCallAccountDetail", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetCallAccountDetail(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            accountService.wsGetCallAccountDetail(isdn, subType, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetAccountsDetail", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetAccountsDetail(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            Object language = wsRequest.get("language");
            accountService.wsGetAccountsDetail(isdn, language, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetAccountsDetailNew", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetAccountsDetailNew(@RequestBody RequestBean request) {
        try {
            // if (!validateRequest(request))
            // return new ResponseEntity<ResponseBean>(new ResponseErrorBean(),
            // HttpStatus.UNAUTHORIZED);
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            Object language = wsRequest.get("language");
            accountService.wsGetAccountsDetail(isdn, language, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetAccountsOcsDetail", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetAccountsOcsDetail(@RequestBody RequestBean request) {
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            Object language = wsRequest.get("language");
            accountService.wsGetAccountsDetail(isdn, language, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetDataAccountDetail", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetDataAccountDetail(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            Object language = wsRequest.get("language");

            accountService.wsGetDataAccountDetail(isdn, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetServiceInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetServiceInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String serviceCode = wsRequest.get("serviceCode").toString();
            if (serviceCode.equals(Constants.BLOCK_GOING_CALL)) {
                Integer subType = Integer.parseInt(wsRequest.get("subType").toString());
                accountService.wsGetServiceInfo(isdn, language, serviceCode, subType, bean);
            } else {
                accountService.wsGetServiceInfo(isdn, language, serviceCode, bean);
            }
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetCurrentUsedSubServices", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetCurrentUsedSubServices(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            accountService.wsGetCurrentUsedSubServices(isdn, language, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoRecharge", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoRecharge(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";
            isdn = CommonUtils.getIsdn(isdn);
            String desIsdn = wsRequest.get("desIsdn") != null ? wsRequest.get("desIsdn").toString() : "";
            if (!isdn.isEmpty() && isdn.startsWith("0")) {
                isdn = isdn.replaceFirst("0", "");
            }
            if (!desIsdn.isEmpty() && desIsdn.startsWith("0")) {
                desIsdn = desIsdn.replaceFirst("0", "");
            }

            String serial = wsRequest.get("serial").toString();
            String isCheckSub = "1";
            Object objCheckSub = wsRequest.get("isCheckSub");
            if (objCheckSub != null) {
                isCheckSub = objCheckSub.toString();
            }
            String lagnuage = wsRequest.get("lagnuage") != null ? wsRequest.get("lagnuage").toString() : "en";
            accountService.wsDoRecharge(isCheckSub, isdn, desIsdn, lagnuage, serial, bean);

            if (Constants.ERROR_CODE_0.equalsIgnoreCase(bean.getErrorCode())) {
                return responseSuccess(request, bean);
            } else {
                return responseError(request, new Exception(bean.getMessage()));
            }

        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetDataVolumeLevelToBuy", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetDataVolumeLevelToBuy(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String dataPackageCode = wsRequest.get("packageCode").toString();

            accountService.wsGetDataVolumeLevelToBuy(isdn, dataPackageCode, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetDataPackageInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetDataPackageInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String packageCode = wsRequest.get("packageCode").toString();

            accountService.wsGetDataPackageInfo(isdn, packageCode, language, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    private ResponseEntity<ResponseBean> getDevMode(RequestBean request, ResponseSuccessBean bean) {
        if ("1".equals(environment.getRequiredProperty(Constants.DEVELOPER_MODE))) {
            return responseSuccess(request, bean);
        }
        return null;
    }

    @RequestMapping(value = "/wsDoBuyData", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoBuyData(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }

            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String packageCode = wsRequest.get("packageCode").toString();
            Long price = Long.parseLong(wsRequest.get("price").toString());
            Long volume = Long.parseLong(wsRequest.get("volume").toString());
            accountService.wsDoBuyData(isdn, packageCode, null, price, volume, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetPostageInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetPostageInfo(@RequestBody RequestBean request) {
        try {
//            if (!validateRequest(request)) {
//                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
//            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int subType = Integer.parseInt(wsRequest.get("subType").toString());

            Date startDate = AppUtil
                    .getTimeStartOfDay(Double.valueOf(wsRequest.get("startDate").toString()).longValue());

            Date endDate = AppUtil.getTimeEndOfDay(Double.valueOf(wsRequest.get("endDate").toString()).longValue());
            if (DataUtil.addTime(endDate, -15, null, null, null, 5).getTime() > startDate.getTime()) {
                return responseError(request, "Fail", messageUtil.getMessage("wsGetPostageInfo.fromdate.todate.err." + "en"), Constants.ERROR_PARAMETER_INVALID);
            }

            logger.info("startdate" + startDate.toString());
            logger.info("enddate" + endDate.toString());
            String type = wsRequest.get("type").toString();
            accountService.wsGetPostageInfo(isdn, subType, startDate, endDate, type, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsFindIsdnToBuy", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsFindIsdnToBuy(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = "";
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            int pageSize = Integer.parseInt((wsRequest.get("pageSize").toString()));
            int pageNum = Integer.parseInt((wsRequest.get("pageNum").toString()));
            BigDecimal minPrice = null;
            if (wsRequest.get("minPrice") != null && !"null".equalsIgnoreCase(wsRequest.get("minPrice").toString())) {
                minPrice = new BigDecimal((wsRequest.get("minPrice").toString()));
            }
            BigDecimal maxPrice = null;
            if (wsRequest.get("maxPrice") != null && !"null".equalsIgnoreCase(wsRequest.get("maxPrice").toString())) {
                maxPrice = new BigDecimal((wsRequest.get("maxPrice").toString()));
            }

            String isdnPattern = null;
            if (wsRequest.get("isdnPattern") != null
                    && !"null".equalsIgnoreCase(wsRequest.get("isdnPattern").toString())) {
                isdnPattern = wsRequest.get("isdnPattern").toString();
            }
            accountService.wsFindIsdnToBuy(subType, pageSize, pageNum, language, minPrice, maxPrice, isdnPattern, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetCurrentUsedServices", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetCurrentUsedServices(@RequestBody RequestBean request) {
        try {
//            if (!validateRequest(request)) {
//                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
//            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = null;
            String isdn = null;
            if (wsRequest != null) {
                isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : null;
                isdn = CommonUtils.getIsdn(isdn);
                language = wsRequest.get("language").toString();
            }
            accountService.wsGetCurrentUsedServices(language, isdn, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoActionService", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoActionService(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);

            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String serviceCode = wsRequest.get("serviceCode").toString();
            int action = Integer.parseInt(wsRequest.get("actionType").toString());
            LinkedHashMap<String, Object> params = ((LinkedHashMap<String, Object>) wsRequest.get("params"));
            if (devMode != null && (action != Constants.ACTION_TYPE_CANCEL)
                    && (action != Constants.ACTION_TYPE_REGISTER)) {
                return devMode;
            }
            accountService.wsDoActionService(isdn, serviceCode, action, params, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoActionServiceNew", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoActionServiceNew(@RequestBody RequestBean request) {
        try {
//            if (!validateRequest(request)) {
//                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
//            }
            logger.info("WsCode: wsDoActionServiceNew");
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String serviceCode = wsRequest.get("serviceCode").toString();
            int action = Integer.parseInt(wsRequest.get("actionType").toString());
            LinkedHashMap<String, Object> params = ((LinkedHashMap<String, Object>) wsRequest.get("params"));
            if (devMode != null && (action != Constants.ACTION_TYPE_CANCEL)
                    && (action != Constants.ACTION_TYPE_REGISTER)) {
                return devMode;
            }
           accountService.wsDoActionServiceByOTP(isdn, serviceCode, action, params, bean,request);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsRegisterBuyIsdn", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsRegisterBuyIsdn(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);

            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            String isdnToBuy = wsRequest.get("isdnToBuy").toString();
            isdn = CommonUtils.getIsdn(isdn);
            isdnToBuy = CommonUtils.getIsdn(isdnToBuy);
            accountService.wsRegisterBuyIsdn(isdn, isdnToBuy, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoBuyIsdn", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoBuyIsdn(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            String newIsdn = wsRequest.get("isdnToBuy").toString();
            String serialOfKit = wsRequest.get("serialOfKit").toString();
            String isdnOfKit = wsRequest.get("isdnOfKit").toString();
            BigDecimal price = new BigDecimal(wsRequest.get("price").toString());
            try {
                accountService.wsDoBuyIsdn(isdn, subType, newIsdn, serialOfKit, isdnOfKit, price, bean);
            } catch (CallApiCancelSubscriberException e) {
                try {
                    accountService.callApiChangeAccountBalance(subType, price, isdn);
                } catch (Exception e2) {
                    logger.error(e2.getMessage(), e2);
                }

                ResponseEntity r = (ResponseEntity) responseError(request, e);

                TransactionError te = new TransactionError(request, e,
                        ((ResponseEntity<ResponseErrorBean>) r).getBody().getLogId());
                logService.insertTransactionLog(te);
                return r;

            } catch (CallApiDausoException e) {
                try {
                    accountService.callApiChangeAccountBalance(subType, price, isdn);
                    accountService.activeSubcriber(isdn, subType, e.getSerial(), isdn, e.getSubMb());
                } catch (Exception e2) {
                    logger.error(e2.getMessage(), e2);
                }

                ResponseEntity r = (ResponseEntity) responseError(request, e);

                TransactionError te = new TransactionError(request, e,
                        ((ResponseEntity<ResponseErrorBean>) r).getBody().getLogId());
                logService.insertTransactionLog(te);
                return r;
            }

            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return responseError(request, e);
        }
    }

    private ResponseEntity<ResponseBean> doExchangeIsdn(RequestBean request) {
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            BigDecimal price = new BigDecimal(wsRequest.get("price").toString());
            String newIsdn = wsRequest.get("newIsdn").toString();
            newIsdn = CommonUtils.getIsdn(newIsdn);
            try {
                accountService.wsDoExchangeIsdn(isdn, newIsdn, subType, price, bean);
            } catch (CallApiCancelSubscriberException e) {
                accountService.callApiChangeAccountBalance(subType, price, isdn);
                ResponseEntity r = (ResponseEntity) responseError(request, e);

                TransactionError te = new TransactionError(request, e,
                        ((ResponseEntity<ResponseErrorBean>) r).getBody().getLogId());
                logService.insertTransactionLog(te);
                return r;
            } catch (CallApiDausoException e) {
                accountService.callApiChangeAccountBalance(subType, price, isdn);
                accountService.activeSubcriber(isdn, subType, e.getSerial(), isdn, e.getSubMb());
                ResponseEntity r = (ResponseEntity) responseError(request, e);

                TransactionError te = new TransactionError(request, e,
                        ((ResponseEntity<ResponseErrorBean>) r).getBody().getLogId());
                logService.insertTransactionLog(te);
                return r;
            }

            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoExchangeIsdn", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoExchangeIsdn(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            return doExchangeIsdn(request);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoRestoreIsdn", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoRestoreIsdn(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            return doExchangeIsdn(request);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoChangeSIM", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoChangeSIM(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            // int subType =
            // Integer.parseInt(wsRequest.get("subType").toString());
            // String newIsdn = wsRequest.get("newIsdn").toString();
            String serialOfKit = wsRequest.get("serialOfKit").toString();
            // String isdnOfKit = wsRequest.get("isdnOfKit").toString();
            String language = wsRequest.get("language").toString();

            accountService.wsDoChangeSIM(language, isdn, 1, "", serialOfKit, "", bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoLockCallGoIsdn", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoLockCallGoIsdn(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            accountService.wsDoLockCallGoIsdn(isdn, subType, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoExchangePostage", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoExchangePostage(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            BigDecimal exchangedMark = new BigDecimal(wsRequest.get("exchanged_mark").toString());
            BigDecimal money = new BigDecimal(wsRequest.get("money").toString());
            accountService.wsDoExchangePostage(isdn, subType, exchangedMark, money, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetAppConfig", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetAppConfig(@RequestBody RequestBean request) {
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            Object version = wsRequest.get("version");
            accountService.wsGetAppConfig(bean, version);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    /**
     * @namdh1 add 20180328 add for detachIP
     * @param isdn
     * @param serviceCode
     * @param actionType
     * @param params
     * @param preState
     * @param bean
     */
    @RequestMapping(value = "/wsDetachIP", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDetachIP(@RequestBody RequestBean request) {
        try {
            /*
             * if (!validateRequest(request)) return new
             * ResponseEntity<ResponseBean>(new ResponseErrorBean(),
             * HttpStatus.UNAUTHORIZED);
             */

            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);

            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String ip = wsRequest.get("ip").toString();
            // String serviceCode = wsRequest.get("serviceCode").toString();
            String serviceCode = Constants.WS_DETACH_IP;

            if (devMode != null) {
                return devMode;
            }
            Object paramsObj = wsRequest.get("params");
            String paramsStr = "";
            if (paramsObj != null) {
                paramsStr = (String) paramsObj;
            }

            LinkedHashMap<String, Object> params = null;
            if (!paramsStr.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                params = (LinkedHashMap<String, Object>) mapper.readValue(paramsStr,
                        new TypeReference<LinkedHashMap<String, Object>>() {
                        });
            }
            // System.out.println("params"+ params);
            accountService.wsDetachIpService(ip, serviceCode, params, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    @RequestMapping(value = "/wsTopUp", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsTopUp(@RequestBody RequestBean request) {
        logger.info("Start wsTopUp ");
        String language = "en";
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";
            String desIsdn = wsRequest.get("desIsdn") != null ? wsRequest.get("desIsdn").toString() : "";
            String serial = wsRequest.get("serial") != null ? wsRequest.get("serial").toString() : "";
            String programCode = wsRequest.get("programCode") != null ? wsRequest.get("programCode").toString() : "";
            String captcha = wsRequest.get("captchaCode") != null ? wsRequest.get("captchaCode").toString() : "";
            String isCheckSub = "1";
            Object objCheckSub = wsRequest.get("isCheckSub");
            if (objCheckSub != null) {
                isCheckSub = objCheckSub.toString();
            }

            language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : "en";
            serial = serial.trim().replaceAll("\n", "").replaceAll("\r", "");

            accountService.wsDoRechargeCard(isCheckSub, isdn, desIsdn, serial, language, bean, programCode, captcha);
            // logger.info("bean: " + bean.getErrorCode() + bean.getMessage());
            if (Constants.ERROR_CODE_0.equalsIgnoreCase(bean.getErrorCode())) {
                return responseSuccess(request, bean);
            } else {
                return responseSuccess(request, bean);
            }

        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e.getMessage(), messageUtil.getMessage("myMetfone.Ishare.des.fail." + language), Constants.ERROR_SYSTEM);
        }
    }

    @RequestMapping(value = "/wsGetSubAccountInfoNew", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetSubAccountInfoNew(@RequestBody RequestBean request) {
        try {
            // if (!validateRequest(request))
            // return new ResponseEntity<ResponseBean>(new ResponseErrorBean(),
            // HttpStatus.UNAUTHORIZED);
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            Object language = wsRequest.get("language");
            int subType = Integer.parseInt(wsRequest.get("subType").toString());
            accountService.wsGetSubAccountInfo(isdn, subType, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

//    /**
//     * check version to force update new version
//     *
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/wsCheckForceUpdateApp", method = RequestMethod.POST)
//    public ResponseEntity<ResponseBean> wsCheckForceUpdateApp(@RequestBody RequestBean request) {
//        try {
//            ResponseSuccessBean bean = new ResponseSuccessBean();
//            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
//            Object version = wsRequest.get("version");
//
//            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : "en";
//            accountService.wsCheckForceUpdateApp(bean, language, version);
//            return responseSuccess(request, bean);
//        } catch (Exception e) {
//            logger.error("Exception: ", e);
//            return responseError(request, e);
//        }
//    }
    /**
     * check version to force update new version
     *
     * @author daibq
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsCheckForceUpdateApp", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsCheckForceUpdateApp(@RequestBody RequestBean request) {
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            Object version = wsRequest.get("version");
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : "en";
            if (language.equals("vi")) {
                language = "en";
            }
            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";

            String versionApp = wsRequest.get("versionApp") != null ? wsRequest.get("versionApp").toString() : "";
            isdn = CommonUtils.getIsdn(isdn);
            accountService.wsCheckForceUpdateApp(bean, language, version, isdn, versionApp);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsLogApp", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsLogApp(@RequestBody RequestBean request) {
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : "en";
            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";
            if (isdn.equals("isdn")) {
                isdn = "";
            }
            String wsCode = wsRequest.get("wsCode") != null ? wsRequest.get("wsCode").toString() : "";
            isdn = CommonUtils.getIsdn(isdn);
            request.setWsCode(wsCode);
            request.setUsername(isdn);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetServiceDetail", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetServiceDetail(@RequestBody RequestBean request) {
        try {
//            if (!validateRequest(request)) {
//                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
//            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String language = wsRequest.get("language").toString();
            String serviceCode = wsRequest.get("serviceCode").toString();

            accountService.wsGetServiceDetail(language, isdn, serviceCode, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetAllDataPackages", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetAllDataPackages(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = null;
            String isdn = null;
            if (wsRequest != null) {
                language = wsRequest.get("language").toString();
                isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : null;
                isdn = CommonUtils.getIsdn(isdn);
            }
            accountService.wsGetAllDataPackages(language, isdn, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseErrorNoLog(request, e);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetServicesByGroup", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetServicesByGroup(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = null;
            String isdn = null;
            String serviceGroupId = null;
            if (wsRequest != null) {
                language = wsRequest.get("language").toString();
                isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : null;
                isdn = CommonUtils.getIsdn(isdn);
                serviceGroupId = wsRequest.containsKey("serviceGroupId") ? wsRequest.get("serviceGroupId").toString()
                        : null;
            }
            String validity = messageUtil.getMessage("camId.validity." + language);
            accountService.wsGetServicesByGroup(language, isdn, serviceGroupId, bean, validity);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);
            return responseError(request, e);
        }
    }

    /**
     * wsHistoryTopup
     *
     * @author daibq
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsHistoryTopup", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsHistoryTopup(@RequestBody RequestBean request) {
        logger.info("Start wsHistoryTopup API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsHistoryTopup(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsHistoryTopUpNew
     *
     * @author yaangvu
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsHistoryTopUpNew", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsHistoryTopUpNew(@RequestBody RequestBean request) {
        logger.info("Start wsHistoryTopUpNew API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsHistoryTopUpNew(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsHistoryTopUpNew
     *
     * @author yaangvu
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsHistoryCharge", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsHistoryCharge(@RequestBody RequestBean request) {
        logger.info("Start wsHistoryCharge API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));

            BaseResponseBean bean = accountService.wsHistoryCharge(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsHistoryChargeDetail
     *
     * @author yaangvu
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsHistoryChargeDetail", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsHistoryChargeDetail(@RequestBody RequestBean request) {
        logger.info("Start wsHistoryChargeDetail API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsHistoryChargeDetail(request, language);

            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    /**
     * wsGetAccountInfo
     *
     * @author daibq
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsAccountInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetAccountInfo(@RequestBody RequestBean request) {
        logger.info("Start wsAccountInfo API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsGetAccountInfo(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetAccountsExchangeDetail", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetAccountsExchangeDetail(@RequestBody RequestBean request) {
        logger.info("Start wsGetAccountsExchangeDetail API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsGetAccountExchangeInfo(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    /**
     * responseBean
     *
     * @author daibq
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @return
     */
    private BaseResponseBean responseBean(String errorCode, String description, String content, String language) {
        return new BaseResponseBean(errorCode, messageUtil.getMessage(description + language), messageUtil.getMessage(content + language));
    }

    /**
     * wsGetListCamIDNotification
     *
     * @author daibq
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetListCamIDNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetCamIDNotification(@RequestBody RequestBean request) {
        logger.info("Start wsGetCamIDNotification API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsGetListCamIDNotification(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    /**
     * wsUpdateIsReadCamIDNotification(testing now)
     *
     * @author daibq
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsUpdateIsReadCamIDNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateIsReadCamIDNotification(@RequestBody RequestBean request) {
        logger.info("Start wsUpdateIsReadCamIDNotification API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : request null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsUpdateIsReadCamIDNotification(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetCurrentUsedServicesExpired", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetCurrentUsedServicesExpired(@RequestBody RequestBean request) {
        logger.info("Start wsGetCurrentUsedServicesExpired API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (wsRequest != null) {
                language = wsRequest.get("language").toString();
            }
            BaseResponseBean bean = accountService.wsGetCurrentUsedServicesExpired(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsClearAllCamIdNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsClearAllCamIdNotification(@RequestBody RequestBean request) {
        logger.info("Start wsClearAllCamIdNotification API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsClearAllCamIdNotification(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsControlCamIdNotification", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsSetupCamIdNotification(@RequestBody RequestBean request) {
        logger.info("Start wsSetupCamIdNotification API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsControlCamIdNotification(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsGetCamIdNotificationStatus", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsGetCamIdNotificationStatus(@RequestBody RequestBean request) {
        logger.info("### Start wsGetCamIdNotificationStatus API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.getCamIdNotificationStatus(request, language);
            logger.info("### End wsGetFilmInfoByName API of AccountController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    //14.08.2021
    @RequestMapping(value = "/wsUpdateCamIdAccessStatus", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateCamIdAccessStatus(@RequestBody RequestBean request) {
        logger.info("### Start wsUpdateCamIdAccessStatus API of AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            if (request.getWsRequest().containsKey("language") && StringUtils.isNotEmpty(request.getWsRequest().get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.updateCamIdAccessStatus(request, language);
            logger.info("### End wsUpdateCamIdAccessStatus API of AccountController");
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "wsTransferIshare.fail.", "wsTransferIshare.fail.", language));
        }
    }

    @RequestMapping(value = "/wsHistoryTopUpNewByDate", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsHistoryTopUpNewByDate(@RequestBody RequestBean request) {
        logger.info("Start wsHistoryTopUpNewwsHistoryTopUpNewByDate API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsHistoryTopUpNewByDate(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }
    @RequestMapping(value = "/wsHistoryTopUpLucky", produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsHistoryTopUpLucky(@RequestBody RequestBean request) {
        logger.info("Start wsHistoryTopUpLucky API off AccountController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.des.fail.", "myMetfone.Ishare.bad.request.", language));
            }
//            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.wsHistoryTopUpLucky(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.Ishare.des.fail.", "myMetfone.failed.", language));
        }
    }

    @RequestMapping(value = "/wsTopUpLucky", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsTopUpLucky(@RequestBody RequestBean request) {
        logger.info("Start wsTopUpLucky ");
        String language = "en";
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);
            if (devMode != null) {
                return devMode;
            }
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";
            String desIsdn = wsRequest.get("phoneNumberTopup") != null ? wsRequest.get("phoneNumberTopup").toString() : "";
            String serial = wsRequest.get("pinCode") != null ? wsRequest.get("pinCode").toString() : "";
            String programCode = wsRequest.get("programCode") != null ? wsRequest.get("programCode").toString() : "";
            String captcha = wsRequest.get("captchaCode") != null ? wsRequest.get("captchaCode").toString() : "";
            String userId = wsRequest.get("userId") != null ? wsRequest.get("userId").toString() : "";

            String isCheckSub = "1";
            Object objCheckSub = wsRequest.get("isCheckSub");
            if (objCheckSub != null) {
                isCheckSub = objCheckSub.toString();
            }

            language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : "en";
            serial = serial.trim().replaceAll("\n", "").replaceAll("\r", "");
            isdn = isdn.trim().replaceAll("[^0-9]", "");
            desIsdn = desIsdn.trim().replaceAll("[^0-9]", "");
            accountService.wsDoRechargeCardLucky(isCheckSub, DataUtil.fomatIsdn(isdn), DataUtil.fomatIsdn(desIsdn), serial, language, bean, programCode, captcha, userId);
            if (Constants.ERROR_CODE_0.equalsIgnoreCase(bean.getErrorCode())) {
                return responseSuccess(request, bean);
            } else {
                return responseSuccess(request, bean);
            }

        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e.getMessage(), messageUtil.getMessage("myMetfone.Ishare.des.fail." + language), Constants.ERROR_SYSTEM);
        }
    }
}
