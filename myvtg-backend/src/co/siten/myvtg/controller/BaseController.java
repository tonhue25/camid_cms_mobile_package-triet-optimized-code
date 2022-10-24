package co.siten.myvtg.controller;

import co.siten.myvtg.bean.*;
import co.siten.myvtg.dto.ResponseDto;
import co.siten.myvtg.model.myvtg.Log;
import co.siten.myvtg.service.LogService;
import co.siten.myvtg.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author daibq
 *
 */
@Component
public class BaseController {

    private static final Logger logger = Logger.getLogger(BaseController.class);
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static final String DES_FAIL = "myMetfone.Ishare.des.fail.";
    public static final String DES_SUCC = "myMetfone.Ishare.des.succ.";
    public static final String BAD_REQUEST = "myMetfone.Ishare.bad.request.";
    public static DecimalFormat dfCurrency = new DecimalFormat("#.##");

    @Autowired
    LogService logService;

    @Autowired
    MessageSource messageSource;
    @Autowired
    MessageUtil messageUtil;

    @Autowired
    JwtUtils jwtUtils;

    public BaseController() {

    }

    protected ResponseEntity<ResponseBean> responseSuccess(RequestBean request, ResponseBean bean) {
        String userMsg = bean.getUserMsg();

        if (userMsg == null || userMsg.isEmpty()) {
            bean.setUserMsg(messageUtil.getMessageDefaultEmpty(request.getWsCode() + ".success"));
        }
        try {
            Log log = new Log(request, bean);
            logService.insertLog(log);
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage(), e);
        }
        return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseBean> responseSuccessNoLog(RequestBean request, ResponseBean bean) {
        String userMsg = bean.getUserMsg();

        if (userMsg == null || userMsg.isEmpty()) {
            bean.setUserMsg(messageUtil.getMessageDefaultEmpty(request.getWsCode() + ".success"));
        }
        try {
            Log log = new Log(request, bean);
            logService.insertLog(log);
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage(), e);
        }

//        Log log = new Log(request, bean);
//        logService.insertLog(log);
        return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseBean> responseSuccess(RequestBean request, ResponseBean bean, String language) {
        String userMsg = bean.getUserMsg();
        if (userMsg == null || userMsg.isEmpty()) {
            bean.setUserMsg(messageUtil.getMessage(request.getWsCode() + ".success." + language));
        }

        try {
            Log log = new Log(request, bean);
            logService.insertLog(log);
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage(), e);
        }
//        Log log = new Log(request, bean);
//        logService.insertLog(log);
        return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseBean> responseError(RequestBean request, Exception e, String language) {
        ResponseErrorBean bean = new ResponseErrorBean(e);
        bean.setUserMsg(messageUtil.getMessage(request.getWsCode() + ".fail." + language));
        try {
            Log log = new Log(request, bean);
            logService.insertLog(log);
            if (!DataUtil.isNullObject(log)) {
                bean.setLogId(log.getId());
            }
        } catch (Exception ex) {
            logger.info("Error: " + ex.getMessage(), ex);
        }
//
//        Log log = new Log(request, bean);
//        logService.insertLog(log);
//        bean.setLogId(log.getId());
        return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseBean> responseError(RequestBean request, Exception e) {
        ResponseErrorBean bean = new ResponseErrorBean(e);
        bean.setUserMsg(messageUtil.getMessageDefaultEmpty(request.getWsCode() + ".fail"));
        try {
            Log log = new Log(request, bean);
            logService.insertLog(log);
            if (!DataUtil.isNullObject(log)) {
                bean.setLogId(log.getId());
            }
        } catch (Exception ex) {
            logger.info("Error: " + ex.getMessage(), ex);
        }
        return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
    }

    /**
     *
     * daibq bo sung msg
     *
     * @param request
     * @param msg
     * @param errorMsg
     * @param error
     * @return
     */
    protected ResponseEntity<ResponseBean> responseError(RequestBean request, String msg, String errorMsg, String error) {
        ResponseErrorBean bean = new ResponseErrorBean(msg, errorMsg, error);
        try {
            Log log = new Log(request, bean);
            logService.insertLog(log);
            if (!DataUtil.isNullObject(log)) {
                bean.setLogId(log.getId());
            }
        } catch (Exception ex) {
            logger.info("Error: " + ex.getMessage(), ex);
        }
        return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseBean> responseErrorNoLog(RequestBean request, Exception e) {
        ResponseErrorBean bean = new ResponseErrorBean(e);
        bean.setUserMsg(messageUtil.getMessageDefaultEmpty(request.getWsCode() + ".fail"));

        return new ResponseEntity<ResponseBean>(bean, HttpStatus.OK);
    }

    protected boolean validateRequest(RequestBean request) {
        logger.info("start validateRequest");
        String apiKey = request.getApiKey();
        logger.info("apiKey " + apiKey);
        String username = request.getUsername();
        logger.info("username " + username);

        if (request.getWsRequest() == null) {
            return true;
        }
        Object isdn = request.getWsRequest().get("isdn");

        try {
            if (isdn != null && !CommonUtil.isEmpty(username)) {
                logger.info("start validateRequest username");
                if (!username.equals(isdn.toString())) {
                    logger.info("start validateRequest username is false");
                    return false;
                }
            }
            StackTraceElement stackTraceElements = Thread.currentThread().getStackTrace()[2];
            String decode = AES.decrypt(apiKey);
            logger.info("start decode API key " + decode);
            return stackTraceElements.getMethodName().trim().equals(decode.trim());
        } catch (Exception e) {
            logger.error("error", e);
            return false;
        }

    }

    /**
     * baseResponse
     *
     * @author daibq
     * @param request
     * @param bean
     * @return
     */
    protected ResponseEntity<BaseResponseBean> baseResponse(RequestBean request, BaseResponseBean bean) {
        if (request.getWsCode().equals("exChangeCardGetlist") || request.getWsCode().equals("ChangeCardAddNew")) {

        } else {
            logger.info("Response: " + CommonUtil.convertObjectToJsonString(bean));
            Log log = new Log(request, bean);
            logService.insertLog(log);
        }
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    /**
     *
     * @param key
     * @param language
     * @param country
     * @return
     */
    public String getValueByKeyLanguage(String key, String language, String country) {
        Locale locale = new Locale(language, country);
        return messageSource.getMessage(key, null, locale);
    }
    /**
     * baseResponse
     *
     * @author daibq
     * @param request
     * @param bean
     * @return
     */
    protected ResponseEntity<ResponseDto> baseResponse(RequestBean request, ResponseDto bean) {
        logger.info("Response: " + CommonUtil.convertObjectToJsonString(bean));
        try {
            Log log = new Log(request, bean);
            logService.insertLog(log);
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage(), e);
        }
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    /**
     * daibq baseResponse
     *
     * @param <T>
     * @param request
     * @param bean
     * @return
     */
    public <T extends Object> ResponseEntity<T> baseResponse(final T request, final T bean) {
        logger.info("Response: " + CommonUtil.convertObjectToJsonString(bean));

        try {
            Log log = new Log((Object[]) request, (Object[]) bean);
            logService.insertLog(log);
        } catch (Exception e) {
            logger.info("Error: " + e.getMessage(), e);
        }
        return new ResponseEntity<>(bean, HttpStatus.OK);
    }

    /**
     * duy th
     *
     * @param request
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @return
     */
    protected ResponseEntity<BaseResponseBean> baseResponse(RequestBean request, String errorCode, String description, String content, String language) {
        BaseResponseBean bean = new BaseResponseBean(errorCode, messageUtil.getMessage(description + language), messageUtil.getMessage(content + language));
        return baseResponse(request, bean);
    }

    /**
     * duyth for BaseRequest
     *
     * @param bean
     * @return
     */
    protected ResponseEntity<BaseResponseBean> baseResponse(BaseResponseBean bean) {
        logger.info("Response: " + CommonUtil.convertObjectToJsonString(bean));
        return new ResponseEntity<BaseResponseBean>(bean, HttpStatus.OK);
    }

    /**
     * duy th
     *
     * @param request
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @return
     */
    protected ResponseEntity<BaseResponseBean> baseResponse(BaseRequest request, String errorCode, String description, String content, String language) {
        BaseResponseBean bean = new BaseResponseBean(errorCode, messageUtil.getMessage(description + language), messageUtil.getMessage(content + language));
        return baseResponse(bean);
    }
    // end BaseRequest

    /**
     * duyth
     *
     * @param request
     * @param errorCode
     * @param description
     * @param content
     * @param language
     * @param object
     * @return
     */
    protected ResponseEntity<BaseResponseBean> baseResponse(RequestBean request, String errorCode, String description, String content, String language, Object object) {
        BaseResponseBean bean = new BaseResponseBean(errorCode, messageUtil.getMessage(description + language), messageUtil.getMessage(content + language));
        bean.setWsResponse(object);
        return baseResponse(request, bean);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    public  String authorizedToken(RequestBean requestBean){
        return DataUtil.isNullOrEmpty(jwtUtils.loadUserNameFormJwtToken(requestBean.getToken()))
                ? null : jwtUtils.loadUserNameFormJwtToken(requestBean.getToken());
    }
}
