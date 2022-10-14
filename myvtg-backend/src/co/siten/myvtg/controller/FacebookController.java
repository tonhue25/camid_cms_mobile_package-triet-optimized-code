/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseBean;
import co.siten.myvtg.bean.ResponseErrorBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.service.Account2Service;
import co.siten.myvtg.service.AccountService;
import co.siten.myvtg.service.MasterDataService;
import co.siten.myvtg.service.SubInfoService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.CommonUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
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

/**
 *
 * @author kiennt88
 */
@RestController
@RequestMapping("/api/${version}/facebook")
@PropertySource(value = {"classpath:api.properties"})
public class FacebookController extends BaseController {

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

        /**
     * wsGetAccountInfo
     *
     * @author daibq
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkSubInfo", method = RequestMethod.POST)
    public ResponseEntity<BaseResponseBean> wsCheckSubInfo(@RequestBody RequestBean request) {
        logger.info("Start checkSubInfo API off ");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("Error requesst : null ");
                return baseResponse(request, responseBean(Constants.ERROR_PARAMETER_INVALID, "myMetfone.checkSubInfo.des.fail.", "myMetfone.checkSubInfo.bad.request.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = accountService.checkSubInfo(request, language);
            return baseResponse(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return baseResponse(request, responseBean(Constants.ERROR_SYSTEM, "checkSubInfo.fail.", "checkSubInfo.fail.", language));
        }
    }
    
    @RequestMapping(value = "/wsDoActionFacebookService", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoActionService(@RequestBody RequestBean request) {
        try {
//            if (!validateRequest(request)) {
//                return new ResponseEntity<ResponseBean>(new ResponseErrorBean(), HttpStatus.UNAUTHORIZED);
//            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            ResponseEntity<ResponseBean> devMode = getDevMode(request, bean);

            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            String isdn = wsRequest.get("isdn").toString();
            isdn = CommonUtils.getIsdn(isdn);
            String serviceCode = wsRequest.get("serviceCode").toString();
            int action = Integer.parseInt(wsRequest.get("actionType").toString());
            if (devMode != null && (action != Constants.ACTION_TYPE_CANCEL)
                    && (action != Constants.ACTION_TYPE_REGISTER)) {
                return devMode;
            }
            LinkedHashMap<String, Object> params = ((LinkedHashMap<String, Object>) wsRequest.get("params"));
            accountService.wsDoActionService(isdn, serviceCode, action, params, bean);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
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
    
      private ResponseEntity<ResponseBean> getDevMode(RequestBean request, ResponseSuccessBean bean) {
        if ("1".equals(environment.getRequiredProperty(Constants.DEVELOPER_MODE))) {
            return responseSuccess(request, bean);
        }
        return null;
    }
}
