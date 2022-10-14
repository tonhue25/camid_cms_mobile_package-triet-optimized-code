/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.model.smsWS.SmsWebService;
import co.siten.myvtg.model.smsWS.SmsWebServiceResponse;
import co.siten.myvtg.model.soapws.SoapWebServiceResponse;
import co.siten.myvtg.util.Common;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
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
import org.xml.sax.SAXException;

/**
 * SmsServiceGlobal
 * Using for sent sms to number not belong to Metfone company
 *
 * @author phuonghc
 */
@org.springframework.stereotype.Service("SmsServiceGlobal")
@PropertySource(value = {"classpath:sms.properties"})
public class SmsServiceGlobal {

    @Autowired
    private Environment environment;
    private static final Logger logger = Logger.getLogger(SmsServiceGlobal.class);

    public SmsServiceGlobal() {
    }

    private SmsWebService initSmsWebserviceParamCore() {
        logger.info("### Start get information of sms webservice");
        SmsWebService smsWebService = new SmsWebService();
        String wsCode = environment.getProperty(Common.URL_SMS_GLOBAL, "http://bizsms.metfone.com.kh:8804/bulkapi?wsdl");
        String userName = environment.getProperty(Common.USER_NAME_SMS_GLOBAL, "camid_api");
        String password = environment.getProperty(Common.PASSWORD_SMS_GLOBAL, "CaM@i2D$");
        String requestId = environment.getProperty(Common.REQUEST_ID, "1");
        String cpCode = environment.getProperty(Common.CP_CODE, "CAMID");
        String serviceId = environment.getProperty(Common.SERVICE_ID_GLOBAL, "CamID");
        String commandCode = environment.getProperty(Common.COMMAND_CODE, "bulksms");

        smsWebService.setUrl(wsCode);
        smsWebService.setUser(userName);
        smsWebService.setPassWord(password);
        smsWebService.setCpCode(cpCode);
        smsWebService.setRequestId(requestId);
        smsWebService.setServiceId(serviceId);
        smsWebService.setCommandCode(commandCode);
        return smsWebService;
    }

    public SmsWebServiceResponse callWebServiceSMSGlobal(String isdn, String content, String contentType) {
        SmsWebServiceResponse result = new SmsWebServiceResponse();
        SmsWebService smsWebService = initSmsWebserviceParamCore();
        smsWebService.setUserId(isdn);
        smsWebService.setReceiverId(isdn);
        smsWebService.setContent(content);
        smsWebService.setContentType(contentType);
        logger.info("### Start call webservice");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(smsWebService.getUrl());
        try {
            JSONObject json = new JSONObject();
            json.put("User", smsWebService.getUser());
            json.put("Password", smsWebService.getPassWord());
            json.put("CPCode", smsWebService.getCpCode());
            json.put("RequestID", smsWebService.getRequestId());
            json.put("UserID", smsWebService.getUserId());
            json.put("ReceiverID", smsWebService.getReceiverId());
            json.put("ServiceID", smsWebService.getServiceId());
            json.put("CommandCode", smsWebService.getCommandCode());
            json.put("Content", smsWebService.getContent());
            json.put("ContentType", smsWebService.getContentType());
            String xml = prepareXmlForRequest(XML.toString(json));
            logger.info("Xml send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.info("Cannot call to Webservice " + smsWebService.getUrl());
                return result;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            result = getInformationForResponseWS(contentReturn);

        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + smsWebService.getUrl(), ex);
        }
        return result;
    }

    private String prepareXmlForRequest(String paramRequests) {
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.bulkSms.ws/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<impl:wsCpMt>"
                + paramRequests
                + "</impl:wsCpMt>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        return request;
    }

    private SmsWebServiceResponse getInformationForResponseWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SmsWebServiceResponse smsWSResponse = new SmsWebServiceResponse();
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        String xPathMessage = environment.getProperty(Common.SMS_RESPONSE_MESSAGE, "/Envelope/Body/wsCpMtResponse/return/message");
        String xPathResult = environment.getProperty(Common.SMS_RESPONSE_RESULT, "/Envelope/Body/wsCpMtResponse/return/result");
        if (StringUtils.isEmpty(xPathMessage) || StringUtils.isEmpty(xPathResult)) {
            return smsWSResponse;
        }
        String message = soapWebServiceResponse.getTextContent(xPathMessage);
        String result = soapWebServiceResponse.getTextContent(xPathResult);
        smsWSResponse.setMessage(message);
        smsWSResponse.setResult(result);
        return smsWSResponse;
    }
}
