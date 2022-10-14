package co.siten.myvtg.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hibernate.result.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import co.siten.myvtg.model.myvtg.CmsLog;
import co.siten.myvtg.service.LogService;
import co.siten.myvtg.util.AES;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;

public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    @Autowired
    LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        long startTime = System.currentTimeMillis();
        logger.info(
                "Request URL::" + request.getRequestURL().toString() + ":: Start Time=" + System.currentTimeMillis());
        request.setAttribute("startTime", startTime);

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        String compareValue = "";
        if (method.getAnnotation(RequestMapping.class) != null) {
            String value = method.getAnnotation(RequestMapping.class).value()[0];
            value = value.substring(1, value.length());
            String methodStr = ((RequestMethod) method.getAnnotation(RequestMapping.class).method()[0]).name();
            // String output = AES.encrypt(value + "." + methodStr); //
            // encrypt

            long encrypteTime = (System.currentTimeMillis() - startTime);
            logger.info("Time to Encrypt::" + encrypteTime);
            compareValue = value + "." + methodStr;
        }

//		String decode = "";
//		try {
//			if (request instanceof MyvtgRequest) {
//				MyvtgRequest myvtgRequest = (MyvtgRequest) request;
//				decode = AES.decrypt(myvtgRequest.getRequestBean().getApiKey());
//			} else {
//				String apiKey = request.getParameter("apiKey");
//				decode = AES.decrypt(apiKey);
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//		logger.debug("Validate Request: " + decode + "-" + compareValue);
//		boolean r = decode.equalsIgnoreCase(compareValue);
        boolean r = true;
        if (!r) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return r;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        CmsLog log = new CmsLog();

        try {
            String apiKey = "";
            if (request instanceof MyvtgRequest) {
                MyvtgRequest myvtgRequest = (MyvtgRequest) request;
                apiKey = myvtgRequest.getRequestBean().getApiKey();
                log.setApiKey(apiKey);
                log.setErrorCode("");
                log.setInsertDate(CommonUtil.getCurrentTime());
                log.setMessage("");
                log.setRequest(CommonUtil.convertObjectToJsonString(myvtgRequest.getRequestBean()));
                log.setResponse("");
                log.setSessionId(myvtgRequest.getRequestBean().getSessionId().toString());
                log.setWsCode(myvtgRequest.getRequestBean().getWsCode());
                log.setStaTime(myvtgRequest.getRequestBean().getStartTime());
                log.setEndTime(CommonUtil.getCurrentTime());
                log.setShopCode(Constants.MYVTG);
                log.setStaffCode(Constants.MYVTG);
            } else {
                apiKey = request.getParameter("apiKey");
            }
            logService.insertCmsLog(log);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

}
