package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.EmoneyData;
import co.siten.myvtg.bean.EmoneyResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.EmoneyDao;
import co.siten.myvtg.model.myvtg.EmoneyLog;
import co.siten.myvtg.util.*;
import com.google.gson.Gson;
import okhttp3.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.transaction.annotation.Transactional;


@Service
@PropertySource(value = {"classpath:config.properties"})
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class EmoneyServiceImpl implements EmoneyService {
    private static final Logger logger = Logger.getLogger(EmoneyServiceImpl.class);
    @Autowired
    private Environment environment;
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    private EmoneyDao emoneyDao;
    @Autowired
    MyvtgService myvtgService;
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";

    @Override
    public BaseResponseBean getAccountInfo(RequestBean request, String language) {

        if (DataUtil.isNullObject(request.getWsRequest().get("accountNo")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("accountNo").toString())) {
            logger.info("#getAccountInfo - accountNo is null ");
            this.insertLog(new Gson().toJson(request), "", "", "", Constants.ERROR_CODE_1, "acconutNo is null", "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.accountNo.", language);
        }
        String accountNo = request.getWsRequest().get("accountNo").toString();
        accountNo = DataUtil.fomatIsdn855(accountNo);
        //get token
        String username = environment.getProperty(Common.EMONEY_USERNAME);
        String password = environment.getProperty(Common.EMONEY_PASSWORD);
        Map<String, String> getTokenReq = new HashMap<>();
        getTokenReq.put("username", username);
        getTokenReq.put("password", password);
        try {
            logger.info("#getAccountInfo - get token ");
            EmoneyResponseBean responseBean = this.postRequest(getTokenReq, environment.getProperty(Common.EMONEY_URL_API_GET_TOKEN), "", "", accountNo, "en");
            if (!DataUtil.isNullObject(responseBean.getStatus()) && !DataUtil.isNullOrEmpty(responseBean.getStatus().getErrorCode())
                    && "00".equals(responseBean.getStatus().getErrorCode())) {
                // get account infor
                String token = responseBean.getData().getToken();
                Map<String, String> accountInfoReq = new HashMap<>();
                accountInfoReq.put("accountNo", accountNo);
                logger.info("#getAccountInfo - get account info ");
                EmoneyResponseBean accountInfoRes = this.postRequest(accountInfoReq, environment.getProperty(Common.EMONEY_URL_API_GET_ACCOUNT_INFO), token, "", accountNo, language);
                if (accountInfoRes != null && accountInfoRes.getStatus() != null) {
                    logger.info("#getAccountInfo - get account success ");
                    BaseResponseBean bean = new BaseResponseBean();
                    bean.setErrorCode(Constants.ERROR_SUCCESS);
                    bean.setMessage("Success");
                    bean.setWsResponse(accountInfoRes);
                    return bean;
                } else {
                    logger.info("#getAccountInfo - response getAccount is null ");
                    this.insertLog(new Gson().toJson(request), "", "", accountNo, Constants.ERROR_CODE_1, "Response getAccount is null", token);
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.fail.", language);
                }
            } else {
                logger.info("#getAccountInfo - response get token is null ");
                this.insertLog(new Gson().toJson(request), "", "", accountNo, Constants.ERROR_CODE_1, "Response get token is null", "");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.token.fail.", language);
            }
        } catch (Exception e) {
            logger.error("#getAccountInfo - exception:  ", e);
            this.insertLog(new Gson().toJson(request), "", "", accountNo, Constants.ERROR_CODE_1, e.getMessage(), "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.webServices.empty.", language);
        }
    }

    @Override
    public BaseResponseBean confirm(RequestBean request, String language) {
        if (DataUtil.isNullObject(request.getWsRequest().get("transId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("transId").toString())) {
            logger.info("#confirm -  transId is null ");
            this.insertLog(new Gson().toJson(request), "", "", "", Constants.ERROR_CODE_1, "transId is null", "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.transId.", language);
        }
        String transId = request.getWsRequest().get("transId").toString();
        if (DataUtil.isNullObject(request.getWsRequest().get("otp")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("otp").toString())) {
            logger.info("#confirm - otp is null ");
            this.insertLog(new Gson().toJson(request), "", transId, "", Constants.ERROR_CODE_1, "otp is null", "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.opt.", language);
        }
        String otp = request.getWsRequest().get("otp").toString();
        if (DataUtil.isNullObject(request.getWsRequest().get("accountNo")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("accountNo").toString())) {
            logger.info("#confirm - accountNo is null ");
            this.insertLog(new Gson().toJson(request), "", transId, "", Constants.ERROR_CODE_1, "accountNo is null", "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.accountNo.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("transCamId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("transCamId").toString())) {
            logger.info("#confirm - transCamId is null ");
            this.insertLog(new Gson().toJson(request), "", transId, "", Constants.ERROR_CODE_1, "transCamId is null", "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.transCamId.", language);
        }
        String accountNo = request.getWsRequest().get("accountNo").toString();
        String transCamId = request.getWsRequest().get("transCamId").toString();
        accountNo = DataUtil.fomatIsdn855(accountNo);
        //get token
        String username = environment.getProperty(Common.EMONEY_USERNAME);
        String password = environment.getProperty(Common.EMONEY_PASSWORD);
        Map<String, String> getTokenReq = new HashMap<>();
        getTokenReq.put("username", username);
        getTokenReq.put("password", password);
        try {
            logger.info("#confirm - getToken ");
            EmoneyResponseBean responseBean = this.postRequest(getTokenReq, environment.getProperty(Common.EMONEY_URL_API_GET_TOKEN), "", transId, accountNo, "en");
            if (!DataUtil.isNullObject(responseBean.getStatus()) && !DataUtil.isNullOrEmpty(responseBean.getStatus().getErrorCode())
            && "00".equals(responseBean.getStatus().getErrorCode())) {
                String token = responseBean.getData().getToken();
                Map<String, String> object = new HashMap<>();
                object.put("accountNo", accountNo);
                object.put("otp", otp);
                object.put("transId", transId);
                object.put("transCamId", transCamId);
                logger.info("#confirm - getConfirm ");
                EmoneyResponseBean confirmResponse = this.postRequest(object, environment.getProperty(Common.EMONEY_URL_API_GET_ACCOUNT_CONFIRM), token, transId, accountNo, language);
                if (confirmResponse != null && confirmResponse.getStatus() != null) {
                    logger.info("#confirm - get response confirm success ");
                    BaseResponseBean bean = new BaseResponseBean();
                    bean.setErrorCode(Constants.ERROR_CODE_0);
                    bean.setMessage("Success");
                    bean.setWsResponse(confirmResponse);
                    return bean;
                } else {
                    logger.info("#confirm - Response confirm is null ");
                    this.insertLog(new Gson().toJson(request), "", transId, accountNo, Constants.ERROR_CODE_1, "Response confirm is null", token);
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.confirm.fail.", language);
                }
            } else {
                logger.info("#confirm - response getToken is null ");
                this.insertLog(new Gson().toJson(request), "", transId, accountNo, Constants.ERROR_CODE_1, "Response getToken is null", "");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.token.fail.", language);
            }
        } catch (Exception e) {
            logger.error("#confirm - exception: ", e);
            this.insertLog(new Gson().toJson(request), "", "confirm", environment.getProperty(Common.EMONEY_URL_API_GET_ACCOUNT_CONFIRM), Constants.ERROR_CODE_1, e.getMessage(), "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.token.fail.", language);
        }
    }

    @Override
    public BaseResponseBean resendOtp(RequestBean request, String language) {
        if (DataUtil.isNullObject(request.getWsRequest().get("transId")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("transId").toString())) {
            logger.info("#resendOtp -  transId is null ");
            this.insertLog(new Gson().toJson(request), "", "", "", Constants.ERROR_CODE_1, "tranId is null", "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.transId.", language);
        }
        if (DataUtil.isNullObject(request.getWsRequest().get("accountNo")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("accountNo").toString())) {
            logger.info("#resendOtp -  accountNo is null ");
            this.insertLog(new Gson().toJson(request), "", "", "", Constants.ERROR_CODE_1, "accountNo is null", "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.account.accountNo.", language);
        }
        String transId = request.getWsRequest().get("transId").toString();
        String accountNo = request.getWsRequest().get("accountNo").toString();
        //get token
        String username = environment.getProperty(Common.EMONEY_USERNAME);
        String password = environment.getProperty(Common.EMONEY_PASSWORD);
        Map<String, String> getTokenReq = new HashMap<>();
        getTokenReq.put("username", username);
        getTokenReq.put("password", password);
        try {
            logger.info("#resendOtp - getToken");
            EmoneyResponseBean responseBean = this.postRequest(getTokenReq, environment.getProperty(Common.EMONEY_URL_API_GET_TOKEN), "", request.getWsRequest().get("transId").toString() , "", "en");
            if (!DataUtil.isNullObject(responseBean.getStatus()) && !DataUtil.isNullOrEmpty(responseBean.getStatus().getErrorCode())
            && "00".equals(responseBean.getStatus().getErrorCode())) {
                String token = responseBean.getData().getToken();
                Map<String, String> object = new HashMap<>();
                object.put("transId", transId);
                object.put("accountNo", accountNo);
                logger.info("#resendOtp - resendOtp ");
                EmoneyResponseBean resendOtpRes = this.postRequest(object, environment.getProperty(Common.EMONEY_URL_API_RESEND_OTP), token, transId, "", language);
                if (resendOtpRes != null && resendOtpRes.getStatus() != null) {
                    logger.info("#resendOtp - get response resendOtp successful ");
                    BaseResponseBean bean = new BaseResponseBean();
                    bean.setErrorCode(Constants.ERROR_CODE_0);
                    bean.setMessage("Success");
                    bean.setWsResponse(resendOtpRes);
                    return bean;
                } else {
                    logger.info("#resendOtp - Response resendOtp is null ");
                    this.insertLog(new Gson().toJson(request), "", transId, "", Constants.ERROR_CODE_1, "Response resendOtp is null", token);
                    return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.resend.otp.fail.", language);
                }
            } else {
                logger.info("#resendOtp - Response getToken is null ");
                this.insertLog(new Gson().toJson(request), "", transId, "", Constants.ERROR_CODE_1, "Response getToken is null", "");
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.token.fail.", language);
            }
        } catch (Exception e) {
            logger.error("#resendOtp - Am error occured: ", e);
            this.insertLog(new Gson().toJson(request), "", transId, "", Constants.ERROR_CODE_1, e.getMessage(), "");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "emoney.token.fail.", language);
        }
    }

    public EmoneyResponseBean postRequest(Object request, String apiUri, String token, String transId, String accountNo, String language) {
        logger.info("#EmoneyServiceImpl - Start");
        Response response = null;
        EmoneyResponseBean responseBean = null;
        try {
            String urlString = String.format("%s", apiUri);
            OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60000, TimeUnit.MILLISECONDS)
                    .readTimeout(60000, TimeUnit.MILLISECONDS)
                    .build();
            Gson gson = new Gson();
            //create request
            String reqContent = gson.toJson(request);

            logger.info("EmoneyServiceImpl.request: " + reqContent);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, reqContent);
            Request result = null;
            if (DataUtil.isNullOrEmpty(token)) {
                result = new Request.Builder()
                        .url(urlString)
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("language", language)
                        .build();
            } else {
                result = new Request.Builder()
                        .url(urlString)
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer " + token)
                        .addHeader("language", language)
                        .build();
            }
            response = client.newCall(result).execute();
            if (response.code() == 200) {
                responseBean = new Gson().fromJson(response.body().string(), EmoneyResponseBean.class);
                logger.info("EmoneyServiceImpl.response: " + new Gson().toJson(responseBean));
                this.insertLog(reqContent, new Gson().toJson(responseBean), transId, accountNo, responseBean.getStatus().getErrorCode(), apiUri, token);
            }
        } catch (Exception e) {
            logger.error("#EmoneyServiceImpl.exception:", e);
        } finally {
            if (response != null && response.body() != null) {
                response.body().close();
            }
        }
        return responseBean;
    }

    public void insertLog(String request, String response, String transId, String accountNo, String errorCode, String description, String token) {
        logger.info("insertLog - start");
        try {
            EmoneyLog emoneyLog = new EmoneyLog();
            emoneyLog.setErrorCode(errorCode);
            emoneyLog.setCreatedDate(new Date());
            emoneyLog.setAccountNo(accountNo);
            emoneyLog.setRequest(request);
            emoneyLog.setResponse(response);
            emoneyLog.setDescription(description);
            emoneyLog.setTransId(transId);
            emoneyLog.setToken(token);
            emoneyDao.persist(emoneyLog);
        } catch (Exception e) {
            logger.error("insertLog - exception:", e);
        }
        logger.info("insertLog - end");
    }
}
