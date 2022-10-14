package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.dto.*;
import co.siten.myvtg.model.ftthWS.WSConfig;
import co.siten.myvtg.model.smsWS.SmsWebServiceResponse;
import co.siten.myvtg.model.soapws.SoapWebServiceResponse;
import co.siten.myvtg.util.Common;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@PropertySource(value = {"classpath:config.properties"})
public class FTTHService {
    @Autowired
    private Environment environment;
    private static final Logger logger = Logger.getLogger(FTTHService.class);
    @Autowired
    SmsService smsService;
    public FTTHService() {
    }


    private WSConfig initWSConfig() {
        logger.info("init config ws ftth");
        WSConfig wsConfig = new WSConfig();
        String url = "http://10.79.120.21:8582/CM_WS/CmWS";
        String token ="c1u1o1n1g143045ef95bb959ab2448f9072c086c90d01a4";
        String source = environment.getProperty(Common.SOURCE_WS_GLOBAL,"WMETFONE");
        wsConfig.setUrl(url);
        wsConfig.setToken(token);
        wsConfig.setSource(source);
        return wsConfig;
    }

    private String prepareXmlForRequest(String paramRequests) {
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cm.bccs.viettel.com/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                    + paramRequests
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        return request;
    }

    public BaseResponseBean camIdInternetRegister(CamIdInternetRegisDTO camIdInternetRegis, String lang){
        WSConfig wsConfig = initWSConfig();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(wsConfig.getUrl());
        BaseResponseBean bean = new BaseResponseBean();

        try {
            JSONObject json = new JSONObject();
            json.put("address", camIdInternetRegis.getAddress());
            json.put("customerMail", camIdInternetRegis.getMail());
            json.put("customerName", camIdInternetRegis.getName());
            json.put("customerPhone", camIdInternetRegis.getPhone());
            json.put("ip", camIdInternetRegis.getIp());
            if(!DataUtil.isNullOrEmpty(camIdInternetRegis.getType()) && camIdInternetRegis.getType().equalsIgnoreCase("agent")){
                json.put("type", 1); // agent
            } else if (!DataUtil.isNullOrEmpty(camIdInternetRegis.getType()) && camIdInternetRegis.getType().equalsIgnoreCase("staff")) {
                json.put("type", 2); // staff
            } else {
                json.put("type", "");
            }
            if(camIdInternetRegis.getType() != ""){
                json.put("introductionCode", camIdInternetRegis.getIntroductionCode()); // isdn of agent or staff
            }else {
                json.put("introductionCode", ""); // isdn of agent or staff
            }
            json.put("packageDetail", camIdInternetRegis.getPackageDetail());
            json.put("province", camIdInternetRegis.getProvinceCode());
            json.put("speed", camIdInternetRegis.getSpeed());
//            json.put("isAutoAssign", "");

            StringBuilder xmlRequest = new StringBuilder();
            // create xml request
            xmlRequest.append("<ws:regOnlineFtth>")
                        .append("<token>" + wsConfig.getToken() + "</token>")
                        .append("<locale>en_US</locale>")
                        .append("<input>")
                            .append(XML.toString(json))
                        .append("</input>")
                      .append("</ws:regOnlineFtth>");
            String xml = prepareXmlForRequest(xmlRequest.toString());
            logger.info("Xml send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.error("Call to Webservice " + wsConfig.getUrl() + " fail");
                bean.setErrorCode(Constants.ERROR_CODE_1);
                bean.setMessage("Register fail");
                return  bean;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            SmsWebServiceResponse result = getInformationForResponseWS(contentReturn);
          if( Integer.valueOf(result.getResult()) != 0) {
              logger.info("camIdInternetRegister with error code : " + result.getResult());
              bean.setErrorCode(Constants.ERROR_CODE_1);
              bean.setMessage(result.getErrorDecription());
              return bean;
          }
            logger.info("camIdInternetRegister with error code : " + result.getResult());

            bean.setErrorCode(Constants.ERROR_CODE_0);

            String messageRegSuccess = environment.getProperty(Common.MESSAGE_REGISTER_SUCCESS)
                    .replace("%name",camIdInternetRegis.getName())
                    .replace("%requestId",result.getRequestId());
            String messTitle = environment.getProperty(Common.TITLE_REGISTER_SUCCESS);

            if("km".equals(lang)){
                messageRegSuccess = environment.getProperty(Common.MESSAGE_REGISTER_SUCCESS_KM)
                        .replace("%name",camIdInternetRegis.getName())
                        .replace("%requestId",result.getRequestId());

                messTitle = environment.getProperty(Common.TITLE_REGISTER_SUCCESS_KM);
            }
            bean.setUserMsg(messTitle); // title
            bean.setMessage(messageRegSuccess); // message in design
            bean.setWsResponse(new CamIdRegisterResponseDTO(result.getRequestId()));
            return bean;
        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + wsConfig.getUrl(), ex);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage("Register exception: " + ex.getMessage());
            return  bean;
        }
    }

    private SmsWebServiceResponse getInformationForResponseWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SmsWebServiceResponse smsWSResponse = new SmsWebServiceResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathMessage = environment.getProperty(Common.MESSAGE_WS_RESPONSE, "/Envelope/Body/regOnlineFtthResponse/return/message");
        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_WS_RESPONSE, "/Envelope/Body/regOnlineFtthResponse/return/errorCode");
        String xPathErrorDecription = "/Envelope/Body/regOnlineFtthResponse/return/errorDecription";
        String xPathRequestId = "/Envelope/Body/regOnlineFtthResponse/return/transactionId";
        if (StringUtils.isEmpty(xPathMessage) || StringUtils.isEmpty(xPathErrorCode)) {
            return smsWSResponse;
        }
        String message = soapWebServiceResponse.getTextContent(xPathMessage);
        String result = soapWebServiceResponse.getTextContent(xPathErrorCode);
        String errorDecription = soapWebServiceResponse.getTextContent(xPathErrorDecription);
        String requestId = soapWebServiceResponse.getTextContent(xPathRequestId);
        smsWSResponse.setMessage(message);
        smsWSResponse.setResult(result);
        smsWSResponse.setErrorDecription(errorDecription);
        smsWSResponse.setRequestId(requestId);
        return smsWSResponse;
    }

    public BaseResponseBean forgetRequest(String isdn, String language){
        WSConfig wsConfig = initWSConfig();
        ForgetRequestDTO forgetRequest = new ForgetRequestDTO();
        forgetRequest.setIsdn(isdn);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(wsConfig.getUrl());
        BaseResponseBean bean = new BaseResponseBean();
        try {
            String locale ="en_US";
            if("km".equals(language)){
                locale = "kh_KH";
            }
            JSONObject json = new JSONObject();
            json.put("isdn", forgetRequest.getIsdn());
            StringBuilder xmlRequest = new StringBuilder();
            // create xml request
            xmlRequest.append("<ws:getRequestIdFromIsdn>")
                    .append("<token>" + wsConfig.getToken() + "</token>")
                    .append("<source>" + wsConfig.getSource() + "</source>")
                    .append("<locale>"+ locale +"</locale>")
                    .append(XML.toString(json))
                    .append("</ws:getRequestIdFromIsdn>");
            String xml = prepareXmlForRequest(xmlRequest.toString());
            logger.info("Xml send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.error("Call to Webservice " + wsConfig.getUrl() + " fail");
                bean.setErrorCode(Constants.ERROR_CODE_1);
                String errMess = environment.getProperty(Common.SERVER_ERROR);
                if("km".equals(language)){
                    errMess = environment.getProperty(Common.SERVER_ERROR_KM);
                }
                bean.setMessage(errMess);
                return  bean;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            SmsWebServiceResponse result = getInformationForRequestWS(contentReturn);
            if( Integer.valueOf(result.getResult()) != 0) {
                logger.info("forgetRequest with error code : " + result.getResult());
                bean.setErrorCode(Constants.ERROR_CODE_1);
                String errMess = environment.getProperty(Common.SEARCH_REQUEST_FTTH_NOT_FOUND);
                if("km".equals(language)){
                    errMess = environment.getProperty(Common.SEARCH_REQUEST_FTTH_NOT_FOUND_KM);
                }
                bean.setMessage(errMess);
                return bean;
            } else {
                logger.info("forgetRequest with error code : " + result.getResult());
                bean = searchFTTHRequest(result.getRequestId(),language);
                return bean;
            }
        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + wsConfig.getUrl(), ex);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage("forgetRequest exception: " + ex.getMessage());
            return  bean;
        }
    }
    private SmsWebServiceResponse getInformationForRequestWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SmsWebServiceResponse smsWSResponse = new SmsWebServiceResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathMessage = environment.getProperty(Common.MESSAGE_WS_RESPONSE_FORGET, "/Envelope/Body/getRequestIdFromIsdnResponse/return/messageCode");
        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_WS_RESPONSE_FORGET, "/Envelope/Body/getRequestIdFromIsdnResponse/return/errorCode");
        String xPathErrorDecription = "/Envelope/Body/getRequestIdFromIsdnResponse/return/errorDecription";
        String xPathRequestId = "/Envelope/Body/getRequestIdFromIsdnResponse/return/transactionId";
        if (StringUtils.isEmpty(xPathMessage) || StringUtils.isEmpty(xPathErrorCode)) {
            return smsWSResponse;
        }
        String message = soapWebServiceResponse.getTextContent(xPathMessage);
        String result = soapWebServiceResponse.getTextContent(xPathErrorCode);
        String errorDecription = soapWebServiceResponse.getTextContent(xPathErrorDecription);
        String requestId = soapWebServiceResponse.getTextContent(xPathRequestId);
        smsWSResponse.setMessage(message);
        smsWSResponse.setResult(result);
        smsWSResponse.setErrorDecription(errorDecription);
        smsWSResponse.setRequestId(requestId);
        return smsWSResponse;
    }

    public BaseResponseBean searchFTTHRequest(String requestId, String lang){
        logger.info("Start searchFTTHRequest with request id is: " + requestId);
        WSConfig wsConfig = initWSConfig();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(wsConfig.getUrl());
        BaseResponseBean bean = new BaseResponseBean();
        try {
            JSONObject json = new JSONObject();
            json.put("requestId", requestId);
            if("en".equals(lang)){
                json.put("locale", "en_US");
            }else {
                json.put("locale", "kh_KH");
            }

            json.put("source", wsConfig.getSource());

            StringBuilder xmlRequest = new StringBuilder();
            // create xml request
            xmlRequest.append("<ws:getInfoRegOnlineFtth>")
                    .append("<token>" + wsConfig.getToken() + "</token>")
                    .append(XML.toString(json))
                    .append("</ws:getInfoRegOnlineFtth>");
            String xml = prepareXmlForRequest(xmlRequest.toString());
            logger.info("Xml send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.error("Call to Webservice " + wsConfig.getUrl() + " fail");
                bean.setErrorCode(Constants.ERROR_CODE_1);
                String errMess = environment.getProperty(Common.SERVER_ERROR);
                if("km".equals(lang)){
                    errMess = environment.getProperty(Common.SERVER_ERROR_KM);
                }
                bean.setMessage(errMess);
                return  bean;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            SmsWebServiceResponse result = getResponseSearchFtth(contentReturn);
            if( Integer.valueOf(result.getResult()) != 0) {
                logger.info("searchFTTHRequest with error code : " + result.getResult());
                bean.setErrorCode(Constants.ERROR_CODE_1);
                String errMess = environment.getProperty(Common.SEARCH_REQUEST_FTTH_NOT_FOUND);
                if("km".equals(lang)){
                    errMess = environment.getProperty(Common.SEARCH_REQUEST_FTTH_NOT_FOUND_KM);
                }
                bean.setMessage(errMess);
                return bean;
            }
            // to do convert xml to object
            logger.info("searchFTTHRequest with error code : " + result.getResult());
            logger.info("searchFTTHRequest response : " + contentReturn);
            SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
            RegOnlineFtthInfoDTO regOnlineFtthInfoDTO = soapWebServiceResponse.getRegOnlineFtthInfo();
            List<RegOnlineHisDTO> regOnlineHisList = getRegOnlineHis(requestId, regOnlineFtthInfoDTO.getAssignStaff());
            if(regOnlineHisList != null && regOnlineHisList.size() > 0 ){
                regOnlineFtthInfoDTO.setRegOnlineHis(regOnlineHisList);
            }
            bean.setErrorCode(Constants.ERROR_CODE_0);
            bean.setMessage("Success");
            bean.setWsResponse(regOnlineFtthInfoDTO);
            return bean;
        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + wsConfig.getUrl(), ex);
            bean.setErrorCode(Constants.ERROR_CODE_1);
            bean.setMessage("searchFTTHRequest exception: " + ex.getMessage());
            return  bean;
        }
    }

    private SmsWebServiceResponse getResponseSearchFtth(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SmsWebServiceResponse smsWSResponse = new SmsWebServiceResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathErrorCode = environment.getProperty(Common.ERROR_CODE_SEARCH_RESPONSE, "/Envelope/Body/getInfoRegOnlineFtthResponse/return/errorCode");
        String xPathErrorDecription = environment.getProperty(Common.MESSAGE_SEARCH_RESPONSE, "/Envelope/Body/getInfoRegOnlineFtthResponse/return/errorDecription");
        if (StringUtils.isEmpty(xPathErrorDecription) && StringUtils.isEmpty(xPathErrorCode)) {
            return smsWSResponse;
        }
        String result = soapWebServiceResponse.getTextContent(xPathErrorCode);
        String errorDecription = soapWebServiceResponse.getTextContent(xPathErrorDecription);
        smsWSResponse.setResult(result);
        smsWSResponse.setErrorDecription(errorDecription);
        return smsWSResponse;
    }

    // get register online history
    public List<RegOnlineHisDTO> getRegOnlineHis(String requestId, String staffCode){
        logger.info("Start getRegOnlineHis with request id is: " + requestId + " and staff code : " + staffCode);
        WSConfig wsConfig = initWSConfig();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(wsConfig.getUrl());
        BaseResponseBean bean = new BaseResponseBean();
        try {
            JSONObject json = new JSONObject();
            json.put("requestId", requestId);
            json.put("locale", "en_US");
            json.put("staffCode", staffCode);

            StringBuilder xmlRequest = new StringBuilder();
            // create xml request
            xmlRequest.append("<ws:getRegOnlineHis>")
                    .append("<token>" + wsConfig.getToken() + "</token>")
                    .append(XML.toString(json))
                    .append("</ws:getRegOnlineHis>");
            String xml = prepareXmlForRequest(xmlRequest.toString());
            logger.info("Xml send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.error("Call to Webservice " + wsConfig.getUrl() + " fail");
                return  null;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            SmsWebServiceResponse result = getResponseRegOnlineHis(contentReturn);
            if( Integer.valueOf(result.getResult()) != 0) {
                logger.info("getRegOnlineHis with error code : " + result.getResult());
                return  null;
            }
            // to do convert xml to object
            logger.info("getRegOnlineHis with error code : " + result.getResult());
            logger.info("getRegOnlineHis response : " + contentReturn);
            SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
           return soapWebServiceResponse.getRegOnlineHis();
        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + wsConfig.getUrl(), ex);
            return  null;
        }
    }

    private SmsWebServiceResponse getResponseRegOnlineHis(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SmsWebServiceResponse smsWSResponse = new SmsWebServiceResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathErrorCode = "/Envelope/Body/getRegOnlineHisResponse/return/errorCode";
        String xPathErrorDecription = "/Envelope/Body/getRegOnlineHisResponse/return/errorDecription";
        if (StringUtils.isEmpty(xPathErrorDecription) && StringUtils.isEmpty(xPathErrorCode)) {
            return smsWSResponse;
        }
        String result = soapWebServiceResponse.getTextContent(xPathErrorCode);
        String errorDecription = soapWebServiceResponse.getTextContent(xPathErrorDecription);
        smsWSResponse.setResult(result);
        smsWSResponse.setErrorDecription(errorDecription);
        return smsWSResponse;
    }
}


