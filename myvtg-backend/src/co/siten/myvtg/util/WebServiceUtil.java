package co.siten.myvtg.util;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPException;
import org.apache.http.client.methods.HttpPost;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import co.siten.myvtg.bean.ResponseObj;
import co.siten.myvtg.dao.CmpreDao;
import co.siten.myvtg.dto.BaseResponsesDto;
import co.siten.myvtg.model.myvtg.TransactionLog;
import co.siten.myvtg.model.myvtg.Webservice;
import com.google.gson.Gson;
import com.viettel.common.ExchMsg;
import com.viettel.common.ViettelService;
import com.vtc.provisioning.client.Exchange;
import com.vtc.provisioning.client.Service;
import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import javax.net.ssl.TrustManager;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/**
 * Created by ThoMC on 15/05/2017.
 */
public class WebServiceUtil {

    private Environment environment;
    private Logger logger;
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");
    private int connectTimeout;
    private int recvTimeout;
    private HttpClient httpclient;
    private long timeStart;
    private long timeExecute;
    private Webservice wsObject;
    private TransactionLog transLog = new TransactionLog();
    public static SimpleDateFormat datefm = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static Integer CONNECT_TIMEOUT;
    public static Integer RECEIVE_TIMEOUT;
    public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    public static SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");

    public static Logger log = Logger.getLogger("WebServiceUtil");

    private void initConnection() {
        if (httpclient != null) {
            HttpConnectionManager conMgr = httpclient.getHttpConnectionManager();
            HttpConnectionManagerParams conPars = conMgr.getParams();
            conPars.setConnectionTimeout(connectTimeout);
            conPars.setSoTimeout(recvTimeout);
        }
    }

    public WebServiceUtil(Webservice wsObject, Environment environment) {
        this.wsObject = wsObject;
        this.environment = environment;
        try {
            logger = Logger.getLogger("WebServiceUtil");

            connectTimeout = 30000;
            recvTimeout = 60000;
            httpclient = new HttpClient();
            initConnection();
            logger.info("CONNECT_TIMEOUT: " + connectTimeout);
            logger.info("RECEIVE_TIMEOUT: " + recvTimeout);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(WebServiceUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ResponseObj parseResult(String response, String webservice) throws SOAPException {
        ResponseObj result = new ResponseObj();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(response));
            Document doc = builder.parse(is);

            // if
            // (webservice.equalsIgnoreCase(Constants.WEBSERVICE_NAME_SENDMT)) {
            String code = doc.getFirstChild().getTextContent().trim();
            if (Constants.ERROR_CODE_0.equalsIgnoreCase(code)) {
                result.setErrCode(Constants.ERROR_CODE_0);
                result.setResponseStr("success");
            } else {
                result.setErrCode(Constants.ERROR_CODE_1);
                result.setResponseStr("fail");
            }

            //
            // } else {
            //
            // String code =
            // doc.getElementsByTagName("code").item(0).getNodeValue();
            // if (Constants.ERROR_CODE_1.equalsIgnoreCase(code)) {
            // result.setErrCode(Constants.ERROR_CODE_0);
            // } else {
            // result.setErrCode(Constants.ERROR_CODE_1);
            // }
            //
            // result.setResponseStr(doc.getElementsByTagName("message").item(0).getNodeValue());
            // }
        } catch (Exception ex) {
            logger.error("", ex);
            result.setErrCode(Constants.ERROR_CODE_1);
        }

        return result;
    }

    public SoapWSResponse callWebService(LinkedHashMap<String, Object> params, Boolean enableRetry) {
        try {
            return iCallWebService(params);
        } catch (Exception ex) {
            if (enableRetry) {
                logger.error("\r\nCalling webservice found an exception ", ex);
                logger.error("\r\n Now retry again");
                try {
                    return iCallWebService(params);
                } catch (Exception e) {
                    logger.error("\r\nCalling webservice found an exception ", e);
                    transLog.setResponse("Calling webservice found an exception: " + e.getMessage());
                    return null;
                }
            } else {
                logger.error("\r\nCalling webservice found an exception ", ex);
                transLog.setResponse("Calling webservice found an exception: " + ex.getMessage());
                return null;
            }
        }
    }

    private SoapWSResponse iCallWebService(LinkedHashMap<String, Object> params) throws Exception {

        try {
            String webserviceName = wsObject.getWsName();
            PostMethod post = new PostMethod(wsObject.getUrl());
            // replace params
            String requestBody = wsObject.getRawXml();
            if (params != null) {
                for (String param : params.keySet()) {
                    requestBody = requestBody.replace("@" + param, params.get(param).toString());
                }
            }

            RequestEntity entity = new StringRequestEntity(requestBody, "text/xml", "UTF-8");
            post.setRequestEntity(entity);
            post.setRequestHeader("SOAPAction", webserviceName);

            String responseBody = "";
            timeStart = System.currentTimeMillis();

            try {
                transLog.setRequest(requestBody);
                transLog.setRequestTime(CommonUtil.getCurrentTime());
                logger.info(requestBody);
                logger.info("Step1 - Start send request service : " + datefm.format(new Date()));
                httpclient.executeMethod(post);
                transLog.setResponseTime(CommonUtil.getCurrentTime());

                responseBody = post.getResponseBodyAsString();
            } catch (IOException ex) { // connection reset
                logger.error("ExecuteMethodException", ex);
                if (!ex.getMessage().contains("Connection reset")) {
                    // ignore, don't retry

                    transLog.setExtraInfo(ex.getMessage());
                    throw ex;
                }
                logger.warn(
                        "Connection reset during process of sending request " + ex.getMessage() + ", resend request");
                // recreate request
                httpclient = new HttpClient();

                initConnection();
                // send request
                httpclient.executeMethod(post);
                responseBody = post.getResponseBodyAsString();
                return null;
            }

            logger.info(responseBody);
            SoapWSResponse response = new SoapWSResponse(responseBody);
            if (wsObject.getRspCodeSucc() != null && wsObject.getRspCodeSucc()
                    .equalsIgnoreCase(response.getTextContent(wsObject.getXpathResponseCode()))) {
                response.setErrCode(Constants.ERROR_CODE_0);
            } else {
                //response.setErrCode(Constants.ERROR_CODE_1);
                response.setErrCode(response.getTextContent(wsObject.getXpathResponseCode()));
                //response.setErrCode(response.getTextContent(wsObject.getXpathResponseCode()));
            }
            transLog.setErrorCode(response.getErrCode());

            timeExecute = System.currentTimeMillis() - timeStart;
            logger.info("Time to iCallWebService is " + timeExecute + "ms");
            transLog.setResponse(responseBody);
            logger.info("Step2- Start response service : " + datefm.format(new Date()));
            return response;
        } catch (Exception ex) {
            logger.error("", ex);
            transLog.setExtraInfo(ex.getMessage());
            return null;
            //throw new Exception(ex.getMessage());

        }
    }

    public TransactionLog getTransLog() {
        return transLog;
    }

    public void setTransLog(TransactionLog transLog) {
        this.transLog = transLog;
    }

    public ResponseObj callWebServiceSms(LinkedHashMap<String, Object> params, boolean b) {
        SoapWSResponse response = callWebService(params, true);
        try {
            return parseResult(response.getWSResponse(), "");
        } catch (Exception e) {
            logger.error("parseResultError", e);
            return null;
        }

    }

    //daibq bo sung
    /**
     * BaseResponsesDto
     *
     * @author daibq
     * @param strUrl
     * @param jsonParam
     * @param authorization
     * @param language
     * @return
     * @throws java.lang.Exception
     */
    public static BaseResponsesDto callApiEmoney(String strUrl, String jsonParam, String authorization, String language) throws Exception {
        log.info("Start call callApiEmoney ");
        try {
            log.info("Step1 - Start send request service : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            HttpPost post = new HttpPost(strUrl);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            post.setHeader("Authorization", authorization);
            post.setHeader("e-language", language);
            post.setEntity(new StringEntity(jsonParam, "UTF-8"));
            HttpContext httpContext = HttpClientContext.create();
            CloseableHttpResponse response = getHttpClient(strUrl).execute(post, httpContext);
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callApiEmoney is " + timeExecute + "ms");
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("ErrorCode : " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return new BaseResponsesDto(responseCode, "System error! please try again");
            } else {
                String result = EntityUtils.toString(response.getEntity());
                log.info("Response : " + result);
                log.info("Step2- Start response service : " + datefm.format(new Date()));
                return new BaseResponsesDto(responseCode, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * methodGETApi
     *
     * @author daibq
     * @param strUrl
     * @return
     * @throws Exception
     */
    public static BaseResponsesDto callApiGet(String strUrl) {
        log.info("Start call callApiGet ");
        try {
            log.info("Step1 - Start send request service : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            HttpGet get = new HttpGet(strUrl);
            HttpContext httpContext = HttpClientContext.create();
            CloseableHttpResponse response = getHttpClient(strUrl).execute(get, httpContext);
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callApiGet is " + timeExecute + "ms");
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("ErrorCode : " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return new BaseResponsesDto(responseCode, "System error! please try again");
            } else {
                String result = EntityUtils.toString(response.getEntity());
                log.info("Response : " + result);
                log.info("Step2- Start response service : " + datefm.format(new Date()));
                return new BaseResponsesDto(responseCode, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * BaseResponsesDto
     *
     * @author daibq
     * @param strUrl
     * @param rq
     * @return
     */
    public static BaseResponsesDto callApiGet(String strUrl, Map<String, Object> rq) {
        log.info("Start call callApiGet ");
        try {
            log.info("Step1 - Start send request service : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            List<NameValuePair> params = new ArrayList<>();
            rq.entrySet().forEach((entry) -> {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            });
            String paramString = URLEncodedUtils.format(params, "utf-8");
            strUrl += "?" + paramString;
            log.info(" Send request: " + strUrl);
            HttpGet get = new HttpGet(strUrl);
            HttpContext httpContext = HttpClientContext.create();
            CloseableHttpResponse response = getHttpClient(strUrl).execute(get, httpContext);
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callApiGet is " + timeExecute + "ms");
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("ErrorCode : " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return new BaseResponsesDto(responseCode, "System error! please try again");
            } else {
                String result = EntityUtils.toString(response.getEntity());
                log.info("Response : " + result);
                log.info("Step2- Start response service : " + datefm.format(new Date()));
                return new BaseResponsesDto(responseCode, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * BaseResponsesDto
     *
     * @author daibq
     * @param strUrl
     * @param jsonParam
     * @return
     * @throws Exception
     */
    public static BaseResponsesDto callApiRest(String strUrl, String jsonParam) {
        log.info("Start call callApiPost ");
        try {
            log.info("Step1 - Start send request service : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            HttpPost post = new HttpPost(strUrl);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(jsonParam, "UTF-8"));
            HttpContext httpContext = HttpClientContext.create();
            CloseableHttpResponse response = getHttpClient(strUrl).execute(post, httpContext);
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callApiPost is " + timeExecute + "ms");
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("ErrorCode : " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return new BaseResponsesDto(responseCode, "System error! please try again");
            } else {
                String result = EntityUtils.toString(response.getEntity());
                log.info("Response : " + result);
                log.info("Step2- Start response service : " + datefm.format(new Date()));
                return new BaseResponsesDto(responseCode, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * getHttpClient
     *
     * @author daibq
     * @param url
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static CloseableHttpClient getHttpClient(String url)
            throws KeyManagementException, NoSuchAlgorithmException {

        Registry<ConnectionSocketFactory> socketFactoryRegistry
                = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("https",
                                new SSLConnectionSocketFactory(getSslContextAll(), getHostNameVerifierAll()))
                        .register("http", new PlainConnectionSocketFactory()).build();
        PoolingHttpClientConnectionManager cm
                = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(200);
        cm.setDefaultMaxPerRoute(50);

        RequestConfig config = RequestConfig.custom().setConnectTimeout(30000) // connect timeout
                .setConnectionRequestTimeout(60000) // getting connection from connection manager timeout
                .setSocketTimeout(2 * 1000 * 60).build();

        HttpClientBuilder builder
                = HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(cm);
        return builder.build();
    }

    /**
     * getSslContextAll
     *
     * @author daibq
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static SSLContext getSslContextAll()
            throws KeyManagementException, NoSuchAlgorithmException {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};
//        SSLContext sc = SSLContext.getInstance("TLSv1");
//        System.setProperty("https.protocols", "TLSv1");//Java 8
        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        KeyManager[] keymanagers = null;
        sc.init(keymanagers, trustAllCerts, new java.security.SecureRandom());
        return sc;
    }

    /**
     * @author daibq getHostNameVerifierAll
     *
     * @return
     */
    private static HostnameVerifier getHostNameVerifierAll() {
        return new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        };
    }

    /**
     * @author daibq
     * @param request
     * @return
     * @throws Exception
     */
    public static ViettelService callProService(ViettelService request) throws Exception {
        ViettelService response = new ViettelService();
        try {
            log.info("Step1 - Start send request to Provisioning service : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            Service service = new Service();
            response = service.send(request);
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callProService is " + timeExecute + "ms");
            log.info("Step2- Start response from Provisioning service : " + datefm.format(new Date()));
        } catch (Exception e) {
            log.error("Erro " + e.getMessage(), e);
        }
        return response;
    }

    /**
     * callProExchange
     *
     * @author daibq
     * @param exchange
     * @param msisdn
     * @param value
     * @param addDay
     * @param expriedDay
     * @param dao
     * @param service
     * @param balance
     * @return
     */
    public static ExchMsg callProExchange(Exchange exchange, String msisdn, String value, Integer expriedDay, CmpreDao dao, String service, String balance) {
        try {
            HashMap<String, ExchMsg> proResponse = null;
            ExchMsg response = null;
            ExchMsg request = null;
            String msg = "";
            String error = "";
            log.info("Step1 - Start send request to Provisioning Exchange : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            if (!DataUtil.isNullOrEmpty(service)) {
                switch (service) {
                    case "SMS":
                        log.info("ServiceA : SMS");
//                        if (DataUtil.isNullOrEmpty(balance)) {
//                            balance = configUtils.getMessage("BALANCE_SMS").trim();
//                        }
                        proResponse = exchange.addBalanceExp(msisdn, balance, value, df.format(DataUtil.addTime(new Date(), expriedDay, null, null, null, 0)));
                        break;
                    case "MINUTE":
                        log.info("ServiceA : MINUTE");
//                        if (DataUtil.isNullOrEmpty(balance)) {
//                            balance = configUtils.getMessage("BALANCE_MINUTE").trim();
//                        }
                        //gui len tong dai tính ra s
                        Long second = Long.parseLong(value) * 60;
                        proResponse = exchange.addBalanceExp(msisdn, balance, String.valueOf(second), df.format(DataUtil.addTime(new Date(), expriedDay, null, null, null, 0)));
                        break;
                    case "DATA":
                        log.info("ServiceA : DATA");
//                        if (DataUtil.isNullOrEmpty(balance)) {
//                            balance = configUtils.getMessage("BALANCE_DATA").trim();
//                        }
                        Double i = Double.parseDouble(value) * 1024 * 1024;
                        String data = String.valueOf(i);
                        proResponse = exchange.addBalanceExp(msisdn, balance, data, df.format(DataUtil.addTime(new Date(), expriedDay, null, null, null, 0)));
                        break;
                    case "VALIDITY":
                        log.info("ServiceA : VALIDITY");
                        proResponse = exchange.addValidity(msisdn, value);
                        break;
                    case "CHARGE":
                        log.info("ServiceA : CHARGE");
//                        if (DataUtil.isNullOrEmpty(balance)) {
//                            balance = configUtils.getMessage("BALANCE_CHANGE").trim();
//                        }
                        proResponse = exchange.addMoneyExp(msisdn, value, balance, expriedDay);
                        break;
                    case "BALANCE":
                        log.info("ServiceA : BALANCE");
//                        if (DataUtil.isNullOrEmpty(balance)) {
//                            balance = configUtils.getMessage("BALANCE_CHANGE").trim();
//                        }
                        proResponse = exchange.addMoneyExp(msisdn, value, balance, expriedDay);
                        break;
                    case "BALANCE_BASIC":
                        log.info("ServiceA : BALANCE_BASIC");
//                        if (DataUtil.isNullOrEmpty(balance)) {
//                            balance = configUtils.getMessage("BALANCE_CHANGE").trim();
//                        }
                        proResponse = exchange.addMoney(msisdn, value, balance);
                        break;
                    default:
                        break;
                }
            }
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callProExchange is " + timeExecute + "ms");
            log.info("Step2- Start response from Provisioning Exchange : " + datefm.format(new Date()));
            if (proResponse != null) {
                response = proResponse.get("RESPONSE");
                request = proResponse.get("REQUEST");
                if (response != null) {
                    String responseXML = response.toString();
                    if (responseXML != null && responseXML.trim().length() >= 4000) {
                        responseXML = responseXML.substring(0, 4000);
                    }
                    log.info("response.getError()(+++++++++++++++++++++++++)" + response.getError());
                    log.info("request.toString(+++++++++++++++++++++++++)");
                    log.info(" request.toString() : " + request.toString().length() + "\n" + request.toString());
                    dao.insertProLog(msisdn, null, request.toString(), responseXML, response.getError());
                    log.info("response.toString(+++++++++++++++++++++++++)");
                    log.info("response.toString()) :" + response.toString().length() + "\n" + response.toString());
                }
            }
            return response;
        } catch (Exception ex) {
            log.error("Err" + ex);
        }
        return null;
    }

    /**
     * callProvisioning
     *
     * @param exchange
     * @param msisdn
     * @param amount
     * @param balance
     * @param addDay
     * @return
     */
    public static ExchMsg callProvisioning(Exchange exchange, String msisdn, String amount, String balance, Integer addDay, String expiredDate, CmpreDao dao) {
        try {
            HashMap<String, ExchMsg> proResponse = null;
            ExchMsg response = null;
            ExchMsg request = null;
            log.info("Step1 - Start send request to Provisioning Exchange : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            if (!DataUtil.isNullOrEmpty(expiredDate)) {
                proResponse = exchange.addBalanceExp(msisdn, balance, amount, expiredDate);
            } else {
                proResponse = exchange.addMoneyExp(msisdn, amount,
                        balance, addDay);
            }
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callProExchange is " + timeExecute + "ms");
            log.info("Step2- Start response from Provisioning Exchange : " + datefm.format(new Date()));
            if (proResponse != null) {
                response = proResponse.get("RESPONSE");
                request = proResponse.get("REQUEST");
                if (response != null) {
                    String responseXML = response.toString();
                    if (responseXML != null && responseXML.trim().length() >= 4000) {
                        responseXML = responseXML.substring(0, 4000);
                    }
                    log.info("response.getError()(+++++++++++++++++++++++++)" + response.getError());
                    log.info("request.toString(+++++++++++++++++++++++++)");
                    log.info(" request.toString() : " + request.toString().length() + "\n" + request.toString());
                    dao.insertProLog(msisdn, null, request.toString(), responseXML, response.getError());
                    log.info("response.toString(+++++++++++++++++++++++++)");
                    log.info("response.toString()) :" + response.toString().length() + "\n" + response.toString());
                }
            }
            return response;
        } catch (Exception ex) {
            log.error("Err" + ex);
        }
        return null;
    }

    //daibq end
    //anjav
    public SoapWSResponse callWebServicePaymemnt(LinkedHashMap<String, Object> params, Boolean enableRetry) {
        try {
            return iCallWebServicePayment(params);
        } catch (Exception ex) {
            if (enableRetry) {
                logger.error("\r\nCalling webservice found an exception ", ex);
                logger.error("\r\n Now retry again");
                try {
                    return iCallWebService(params);
                } catch (Exception e) {
                    logger.error("\r\nCalling webservice found an exception ", e);
                    transLog.setResponse("Calling webservice found an exception: " + e.getMessage());
                    return null;
                }
            } else {
                logger.error("\r\nCalling webservice found an exception ", ex);
                transLog.setResponse("Calling webservice found an exception: " + ex.getMessage());
                return null;
            }
        }
    }

    private SoapWSResponse iCallWebServicePayment(LinkedHashMap<String, Object> params) throws Exception {

        try {
            String webserviceName = wsObject.getWsName();
            PostMethod post = new PostMethod(wsObject.getUrl());
            // replace params
            String requestBody = wsObject.getRawXml();
            if (params != null) {
                for (String param : params.keySet()) {
                    requestBody = requestBody.replace("@" + param, params.get(param).toString());
                }
            }

            RequestEntity entity = new StringRequestEntity(requestBody, "text/xml", "UTF-8");
            post.setRequestEntity(entity);
            post.setRequestHeader("SOAPAction", webserviceName);

            String responseBody = "";
            timeStart = System.currentTimeMillis();

            try {
                transLog.setRequest(requestBody);
                transLog.setRequestTime(CommonUtil.getCurrentTime());
                logger.info(requestBody);
                logger.info("Step1 - Start send request service : " + datefm.format(new Date()));
                httpclient.executeMethod(post);
                transLog.setResponseTime(CommonUtil.getCurrentTime());

                responseBody = post.getResponseBodyAsString();
            } catch (IOException ex) { // connection reset
                logger.error("ExecuteMethodException", ex);
                if (!ex.getMessage().contains("Connection reset")) {
                    // ignore, don't retry

                    transLog.setExtraInfo(ex.getMessage());
                    throw ex;
                }
                logger.warn(
                        "Connection reset during process of sending request " + ex.getMessage() + ", resend request");
                // recreate request
                httpclient = new HttpClient();

                initConnection();
                // send request
                httpclient.executeMethod(post);
                responseBody = post.getResponseBodyAsString();
                return null;
            }

            // logger.info(responseBody);
            SoapWSResponse response = new SoapWSResponse(responseBody);
            transLog.setErrorCode(response.getErrCode());

            timeExecute = System.currentTimeMillis() - timeStart;
            logger.info("Time to iCallWebService is " + timeExecute + "ms");
            transLog.setResponse(responseBody);
            logger.info("Step2- Start response service : " + datefm.format(new Date()));
            return response;
        } catch (Exception ex) {
            logger.error("", ex);
            transLog.setExtraInfo(ex.getMessage());
            return null;
            //throw new Exception(ex.getMessage());

        }
    }

    public static ExchMsg chargeMoneyExchange(String msisdn, Double value, CmpreDao dao) throws Exception {
        ExchMsg request = new ExchMsg();
        try {
            msisdn = msisdn.startsWith("0") ? msisdn.substring(1) : msisdn;
            msisdn = msisdn.startsWith("855") ? msisdn : "855" + msisdn;
            Exchange exchange = new Exchange();
            request.setSynchronous(true);
            request.setCommand("OCSHW_DEDUCT");
            request.set("MSISDN", msisdn);
            request.set("CHARGE_VALUE", Double.toString(value));
            request.set("PARTYCODE", "DONATION");
            System.out.println("request: " + request.toString());
            ExchMsg response = exchange.send(request);
            System.out.println("response: " + new Gson().toJson(response));

            dao.insertProLog(msisdn, null, request.toString(), response.toString(), response.getError());
            return response;
        } catch (Exception ex) {
            log.error("error" + ex.toString());
            dao.insertProLog(msisdn, null, request.toString(), ex.toString(), "99");
        }
        return null;
    }
    
    /**
     * BaseResponsesDto
     *
     * @author daibq
     * @param strUrl
     * @param rq
     * @return
     */
    public static BaseResponsesDto callApiPostWithParamURI(String strUrl, Map<String, Object> rq) {
        log.info("Start call callApiGet ");
        try {
            log.info("Step1 - Start send request service : " + datefm.format(new Date()));
            Long timeStart = System.currentTimeMillis();
            List<NameValuePair> params = new ArrayList<>();
            rq.entrySet().forEach((entry) -> {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            });
            String paramString = URLEncodedUtils.format(params, "utf-8");
            strUrl += "?" + paramString;
            log.info(" Send request: " + strUrl);
            HttpPost post = new HttpPost(strUrl);
            HttpContext httpContext = HttpClientContext.create();
            CloseableHttpResponse response = getHttpClient(strUrl).execute(post, httpContext);
            Long timeExecute = System.currentTimeMillis() - timeStart;
            log.info("Time to callApiGet is " + timeExecute + "ms");
            int responseCode = response.getStatusLine().getStatusCode();
            log.info("ErrorCode : " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return new BaseResponsesDto(responseCode, "System error! please try again");
            } else {
                String result = EntityUtils.toString(response.getEntity());
                log.info("Response : " + result);
                log.info("Step2- Start response service : " + datefm.format(new Date()));
                return new BaseResponsesDto(responseCode, result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
