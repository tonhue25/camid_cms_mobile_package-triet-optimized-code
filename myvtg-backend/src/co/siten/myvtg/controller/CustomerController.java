package co.siten.myvtg.controller;

import java.util.LinkedHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseBean;
import co.siten.myvtg.bean.ResponseErrorBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.service.CustomerService;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;

/**
 *
 * @author thomc
 *
 */
@RestController
@RequestMapping("/api/${version}/accounts")
public class CustomerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;
    @Autowired
    ResponseUtil responseUtil;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsUpdateCustomerInfo", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsUpdateCustomerInfo(@RequestBody RequestBean request) {
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();

            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            JSONObject json = new JSONObject(wsRequest);
            // Bat dau convert to xml from json
            String xmlParam = XML.toString(json);

            LinkedHashMap<String, Object> params = null;
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.DEFAULT_LANGUAGE;

            customerService.wsUpdateInfoByUser(xmlParam, params, bean, language);

            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseErrorNoLog(request, e);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsSearchNiceNumber", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsSearchNiceNumber(@RequestBody RequestBean request) {
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.DEFAULT_LANGUAGE;
            String fromPrice = wsRequest.get("fromPrice") != null ? wsRequest.get("fromPrice").toString() : "0";
            String toPrice = wsRequest.get("toPrice") != null ? wsRequest.get("toPrice").toString() : "100";

            String typeNumber = wsRequest.get("typeNumber") != null ? wsRequest.get("typeNumber").toString() : "0";
            String conditionNumber = wsRequest.get("conditionNumber") != null ? wsRequest.get("conditionNumber").toString() : "";
            String numberRecord = wsRequest.get("numberRecord") != null ? wsRequest.get("numberRecord").toString() : "10";

            params.put("language", language);
            params.put("fromPrice", fromPrice);
            params.put("toPrice", toPrice);
            params.put("typeNumber", typeNumber);
            params.put("conditionNumber", conditionNumber);
            params.put("numberRecord", numberRecord);
            customerService.wsSearchNumberToBuy(params, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsDoLoginByGetCode", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsDoLoginByGetCode(@RequestBody RequestBean request) {
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.DEFAULT_LANGUAGE;
            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";

            params.put("isdn", isdn);

            customerService.wsDoLoginByGetCode(params, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsVerifyByCode", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsVerifyByCode(@RequestBody RequestBean request) {
        try {

            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.DEFAULT_LANGUAGE;
            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";
            String code = wsRequest.get("code") != null ? wsRequest.get("code").toString() : "";
            String token = wsRequest.get("token") != null ? wsRequest.get("token").toString() : "";

            params.put("isdn", isdn);
            params.put("code", code);
            params.put("token", token);

            customerService.wsVerifyByCode(params, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsGetProfileByIsdn", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGetProfileByIsdn(@RequestBody RequestBean request) {
        logger.info("Start wsGetProfileByIsdn API off CustomerController");
        try {
            String language = "en";
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            if (!DataUtil.isNullObject(wsRequest.get("language")) && !DataUtil.isNullOrEmpty(wsRequest.get("language").toString())) {
                language = request.getWsRequest().get("language").toString();
            }
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
//            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.DEFAULT_LANGUAGE;
//            String isdn = wsRequest.get("isdn") != null ? wsRequest.get("isdn").toString() : "";
            if (DataUtil.isNullOrEmpty(request.getUsername())) {
                logger.info("Error requesst : user name is null ");
                return new ResponseEntity<>(new ResponseErrorBean("Failed", responseUtil.getMessage("myMetfone.Ishare.isdn.empty." + language)), HttpStatus.OK);
            }
            String isdn = request.getUsername();
            String token = wsRequest.get("token") != null ? wsRequest.get("token").toString() : "";

            params.put("isdn", isdn);
            params.put("token", token);
            customerService.wsGetProfileByIsdn(params, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/wsReserveNumber", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsReserveNumber(@RequestBody RequestBean request) {
        try {
            ResponseSuccessBean bean = new ResponseSuccessBean();
            LinkedHashMap<String, Object> wsRequest = request.getWsRequest();
            LinkedHashMap<String, Object> params = new LinkedHashMap<>();
            String language = wsRequest.get("language") != null ? wsRequest.get("language").toString() : Constants.DEFAULT_LANGUAGE;
            String isdnFrm = wsRequest.get("isdnFrm") != null ? wsRequest.get("isdnFrm").toString() : "";
            String customerIsdn = wsRequest.get("customerIsdn") != null ? wsRequest.get("customerIsdn").toString() : "";

            params.put("isdnFrm", isdnFrm);
            params.put("customerIsdn", customerIsdn);

            customerService.wsReserveNumber(params, bean, language);
            return responseSuccess(request, bean);
        } catch (Exception e) {
            logger.error("Exception: ", e);
            return responseError(request, e);
        }
    }
}
