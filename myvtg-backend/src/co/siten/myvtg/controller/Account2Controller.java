package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseBean;
import co.siten.myvtg.bean.ResponseErrorBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.config.AES;
import co.siten.myvtg.service.Account2Service;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.InternetAddress;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *
 * @author thomc
 *
 */
@RestController
@RequestMapping("/api/${version}/accounts")
public class Account2Controller extends BaseController {

    private static final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(Account2Controller.class);
    private static final Logger logger = LoggerFactory.getLogger(Account2Controller.class);
    @Autowired
    Account2Service account2Service;

    @RequestMapping(value = "/wsGetHobbies", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetHobbies(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            account2Service.wsGetHobbies(language, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsUpdateSubInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsUpdateSubInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            BigDecimal jobId = new BigDecimal(wsRequest.get("jobId").toString());
            BigDecimal hobbyId = new BigDecimal(wsRequest.get("hobbyId").toString());
            String email = wsRequest.get("email") != null ? wsRequest.get("email").toString() : "";
            /*
                        if(email!=null && email!=""){
                            	InternetAddress emailAddr = new InternetAddress(email);
                                emailAddr.validate();
                        }
             */

            account2Service.wsUpdateSubInfo(isdn, jobId, hobbyId, email, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsUpdateSubInfoNew", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsUpdateSubInfoNew(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            BigDecimal jobId = new BigDecimal(wsRequest.get("jobId").toString());
            BigDecimal hobbyId = new BigDecimal(wsRequest.get("hobbyId").toString());
            String email = wsRequest.get("email") != null ? wsRequest.get("email").toString() : "";
            String avatar = wsRequest.get("avatar") != null ? wsRequest.get("avatar").toString() : "";
            /*
                        if(email!=null && email!=""){
                            	InternetAddress emailAddr = new InternetAddress(email);
                                emailAddr.validate();
                        }
             */

            account2Service.wsUpdateSubInfo(isdn, jobId, hobbyId, email, avatar, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("", e);
            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoSendEmail", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoSendEmail(@RequestBody RequestBean request) {
        try {
//			if (!validateRequest(request))
//				return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);

            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.containsKey("language") ? wsRequest.get("language").toString() : Constants.LOCAL_LANGUAGE;
            String isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : "";
            String fromAddr = wsRequest.containsKey("fromAddr") ? wsRequest.get("fromAddr").toString() : "";
            String sentSub = wsRequest.containsKey("subject") ? wsRequest.get("subject").toString() : "Feedback";

            String subject = "[MyMetfone] – " + sentSub;

            String content = wsRequest.get("content").toString();
            String attachmentBase64 = wsRequest.containsKey("attachment") ? wsRequest.get("attachment").toString() : "";
            String filename = "attachFile"; // wsRequest.get("fileName").toString();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            StringBuilder contentSend = new StringBuilder();
            // get app param for email
            String userName = (String) account2Service.getAppParam(Constants.KEY_EMAIL);
            String cc_em_pwd = (String) account2Service.getAppParam(Constants.KEY_PASSWORD);
            String cc_em_host = (String) account2Service.getAppParam(Constants.KEY_HOST);
            String cc_em_port = (String) account2Service.getAppParam(Constants.KEY_PORT);
            String cc_em_auth = (String) account2Service.getAppParam(Constants.KEY_AUTH);
            String cc_em_ssl = (String) account2Service.getAppParam(Constants.KEY_SSL);

            String cc_em_content = messageUtil.getMessage("Email.Content." + language);
            cc_em_content = cc_em_content.replaceAll("%isdn%", isdn).replaceAll("%email%", fromAddr + "\n").replaceAll("%date%", dateFormat.format(new Date()) + "\n\n").replaceAll("%content%", content);
            contentSend.append(cc_em_content);
            contentSend.append(content);
            InternetAddress emailAddr = new InternetAddress(fromAddr);
            emailAddr.validate();
            System.out.println("namdh1 says: =======start send email==========>");
            logger.info("namdh1 says =======start send email==========>");
            CommonUtil.sendMail(userName, cc_em_pwd, userName, userName, cc_em_host, cc_em_port, subject,
                    contentSend.toString(), attachmentBase64, filename, cc_em_auth, cc_em_ssl);

            return responseSuccessNoLog(request, bean);

        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetProvinces", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetProvinces(@RequestBody RequestBean request) {
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();

            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.LOCAL_LANGUAGE;
            account2Service.wsGetProvinces(language, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetDistricts", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetDistricts(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String provinceId = null;
            if (wsRequest != null && wsRequest.containsKey("provinceId")) {
                provinceId = wsRequest.get("provinceId").toString();
            }

            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.LOCAL_LANGUAGE;
            account2Service.wsGetDistricts(language, provinceId, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetPrecincts", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetPrecincts(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String provinceId = null;
            String districtId = null;
            if (wsRequest != null && wsRequest.containsKey("provinceId")) {
                provinceId = wsRequest.get("provinceId").toString();
            }
            if (wsRequest != null && wsRequest.containsKey("districtId")) {
                districtId = wsRequest.get("districtId").toString();
            }
            account2Service.wsGetPrecincts(provinceId, districtId, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetNearestStores", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetNearestStores(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            BigDecimal longitude = new BigDecimal(wsRequest.get("longitude").toString());
            BigDecimal latitude = new BigDecimal(wsRequest.get("latitude").toString());
            account2Service.wsGetNearestStores(longitude, latitude, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsFindStoreByAddr", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsFindStoreByAddr(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            BigDecimal longitude = new BigDecimal(wsRequest.get("longitude").toString());
            BigDecimal latitude = new BigDecimal(wsRequest.get("latitude").toString());

            String provinceId = wsRequest.get("provinceId").toString();
            Object dId = wsRequest.get("districtId");
            String districtId = dId == null ? null : dId.toString();
            account2Service.wsFindStoreByAddr(longitude, latitude, provinceId, districtId, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetNewsDetail", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetNewsDetail(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String newsId = wsRequest.get("newsId").toString();
            account2Service.wsGetNewsDetail(newsId, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetNews", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetNews(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = null;
            Integer pageSize = 0;
            Integer pageNum = 1;
            if (wsRequest != null) {
                language = wsRequest.get("language").toString();
                Object ps = wsRequest.get("pageSize");
                Object pn = wsRequest.get("pageNum");
                if (ps != null) {
                    pageSize = Integer.parseInt(ps.toString());
                }
                if (pn != null) {
                    pageNum = Integer.parseInt(pn.toString());
                }
            }
            account2Service.wsGetNews(language, pageSize, pageNum, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetExperienceLink3G4G", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetExperienceLink3G4G(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            Integer bannerLimit = 5;
            Integer linkLimit = 20;
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            account2Service.wsGetExperienceLink3G4G(language, bannerLimit, linkLimit, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetAllApps", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetAllApps(@RequestBody RequestBean request) {
        try {
//            if (!validateRequest(request)) {
//                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
//            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            Integer limit = 20;
            Integer bannerLimit = 5;
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            account2Service.wsGetAllApps(language, limit, bannerLimit, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseErrorNoLog(request, e);
        }
    }

    @RequestMapping(value = "/wsGetCareers", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetCareers(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            account2Service.wsGetCareers(language, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetNewPromotions", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetNewPromotions(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = null;
            Integer pageSize = 0;
            Integer pageNum = 1;
            if (wsRequest != null) {
                language = wsRequest.get("language").toString();
                Object ps = wsRequest.get("pageSize");
                Object pn = wsRequest.get("pageNum");
                if (ps != null) {
                    pageSize = Integer.parseInt(ps.toString());
                }
                if (pn != null) {
                    pageNum = Integer.parseInt(pn.toString());
                }
            }
            account2Service.wsGetNewPromotions(language, pageSize, pageNum, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetPromotionInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetPromotionInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String language = wsRequest.get("language").toString();
            String packageCode = wsRequest.get("packageCode").toString();

            account2Service.wsGetPromotionInfo(language, packageCode, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetChangePostageRule", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetChangePostageRule(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            account2Service.wsGetChangePostageRule(bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetSubPrivilegeInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetSubPrivilegeInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            account2Service.wsGetSubPrivilegeInfo(isdn, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetGiftsByProvince", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetGiftsByProvince(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            Integer provinceId = null;
            if (wsRequest != null) {
                provinceId = wsRequest.containsKey("provinceId")
                        ? Integer.parseInt(wsRequest.get("provinceId").toString()) : null;
            }
            account2Service.wsGetGiftsByProvince(provinceId, bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetPrivilegeRuleInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetPrivilegeRuleInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            account2Service.wsGetPrivilegeRuleInfo(bean);
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsDoExchangeGift", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoExchangeGift(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (wsRequest != null) {
                Integer provinceId = wsRequest.containsKey("provinceId")
                        ? Integer.parseInt(wsRequest.get("provinceId").toString()) : null;
                Integer districtId = wsRequest.containsKey("districtId")
                        ? Integer.parseInt(wsRequest.get("districtId").toString()) : null;
                Integer precinctId = wsRequest.containsKey("precinctId")
                        ? Integer.parseInt(wsRequest.get("precinctId").toString()) : null;
                String isdn = wsRequest.containsKey("idsn") ? wsRequest.get("idsn").toString() : null;
                String subType = wsRequest.containsKey("subType") ? wsRequest.get("subType").toString() : null;
                String giftCode = wsRequest.containsKey("giftCode") ? wsRequest.get("giftCode").toString() : null;
                String addr = wsRequest.containsKey("addr") ? wsRequest.get("addr").toString() : null;
                Long timeLong = wsRequest.containsKey("time") ? Long.parseLong(wsRequest.get("time").toString()) : null;
                BigDecimal exchangedMark = wsRequest.containsKey("exchangedMark")
                        ? new BigDecimal(wsRequest.get("exchangedMark").toString()) : null;

                account2Service.wsDoExchangeGift(isdn, subType, giftCode, provinceId, districtId, precinctId, addr,
                        timeLong != null ? new Date(timeLong) : null, exchangedMark, bean);
            }
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetAdvancedServiceInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetAdvancedServiceInfo(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (wsRequest != null) {
                String isdn = wsRequest.containsKey("isdn") ? wsRequest.get("isdn").toString() : null;
                String language = wsRequest.containsKey("language") ? wsRequest.get("language").toString() : null;
                String serviceCode = wsRequest.containsKey("serviceCode") ? wsRequest.get("serviceCode").toString()
                        : null;
                account2Service.wsGetAdvancedServiceInfo(isdn, language, serviceCode, bean);
            }
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);

            return responseError(request, e);
        }
    }

    @RequestMapping(value = "/wsGetServiceFuntionPage", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetServiceFuntionPage(@RequestBody RequestBean request) {
        try {
            if (!validateRequest(request)) {
                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (wsRequest != null) {
                String language = wsRequest.containsKey("language") ? wsRequest.get("language").toString() : null;
                String serviceCode = wsRequest.containsKey("serviceCode") ? wsRequest.get("serviceCode").toString()
                        : null;
                String functionPageCode = wsRequest.containsKey("functionPageCode")
                        ? wsRequest.get("functionPageCode").toString() : null;
                account2Service.wsGetServiceFuntionPage(language, serviceCode, functionPageCode, bean);
            }
            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);
            return responseError(request, e);
        }
    }

    /**
     * @author daibq
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsUpdateSocialNetwork", produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsUpdateSocialNetwork(@RequestBody RequestBean request) {
        log4j.info("Start wsUpdateSocialNetwork API off Account2Controller");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Requesst: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = account2Service.updateSocialNetwork(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language));
        }

    }
    /**
     * Login to cms
     * @author VuDoan altek
     * @param request
     * @return user information and token and session id
     */
    @RequestMapping(value = "/wsLoginCMSEsport", produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsLoginCMSEsport(@RequestBody RequestBean request) {
        String language = "en";
        try {
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error request : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.Ishare.bad.request.", "myMetfone.Ishare.bad.request.", language));
            }
            if(DataUtil.isNullOrEmpty(request.getWsRequest().get("username").toString())
                    || DataUtil.isNullOrEmpty(request.getWsRequest().get("password").toString())){
                logger.info("wsLoginCMSEsport.exception username is empty or null");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "cms.login.param.invalid.", "cms.login.param.invalid.", language));
            }
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean baseResponseBean = account2Service.wsLoginCMSEsport(request, language);
            return baseResponse(request, baseResponseBean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "myMetfone.failed.", "myMetfone.failed.", language));
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
