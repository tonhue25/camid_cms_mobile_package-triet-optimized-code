package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.AuthService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${version}/auth")
public class AuthController extends BaseController{

    private static final Logger logger = Logger.getLogger(ServiceGroupController.class);
    @Autowired
    AuthService authService;

    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "wsLogin", method = RequestMethod.POST)
    public ResponseEntity<?> wsLogin(@RequestBody RequestBean request) {
        logger.info("### Start wsLogin API of authController");
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                logger.info("### Error request: null request");
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            logger.info("Request: " + CommonUtil.convertObjectToJsonString(request));
            BaseResponseBean bean = authService.signIn(request, language);
            logger.info("### End wsLogin API of authController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }
}
