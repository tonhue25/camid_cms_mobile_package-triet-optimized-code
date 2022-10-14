/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.util.*;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import sendmt.MtStub;

/**
 *
 * @author daibq
 */
@org.springframework.stereotype.Service("SmsService")
@PropertySource(value = {"classpath:sms.properties"})
public class SmsService {

    @Autowired
    private Environment environment;
    private static final Logger logger = Logger.getLogger(SmsService.class);

    public static String SENDER;
    private MtStub mtStub = null;

    private MtStub initMtStub() {
        String url = environment.getProperty(Common.URL_SMS, "http://10.79.123.154:9008/vasp/Service.asmx");
        logger.info(url);
        SENDER = environment.getProperty(Common.SMS_ISDN_SEND, "CamID");
        logger.info(SENDER);
        String userName = environment.getProperty(Common.USER_NAME_SMS, "sms2013");
        logger.info(userName);
        String password = environment.getProperty(Common.PASSWORD_SMS, "sms2013");
        logger.info(password);
        String xmnlsSms = environment.getProperty(Common.XMNLS_SMS, "http://tempuri.org/");
        logger.info(xmnlsSms);

        MtStub stub = new MtStub(url, xmnlsSms, userName, password);
        return stub;
    }

    /**
     * connectServer
     */
    public void connectServer() {
        MtStub tempMtStub = initMtStub();
        setMtStub(tempMtStub);
    }

    public MtStub getMtStub() {
        return mtStub;
    }

    public void setMtStub(MtStub mtStub) {
        this.mtStub = mtStub;
    }

    // Disable this func.
    private int sendSMS(String content) throws Exception {
        return -1;//sendSMS(Constant.RECEIPVER, content);
    }

    public int sendSMS(String isdn, String content) throws Exception {
        String msisdn = CommonUtils.getMsisdn(isdn);
        String isdnSend = SENDER;
        return sendSMS(msisdn, content, isdnSend);
    }
// Common.SERVICE_ID

    public int sendSMS(String isdn, String content, String sender) throws Exception {
        return sendSMS(isdn, content, sender, null);
    }

    public int sendSMS(String isdn, String content, String sender, String serviceId) throws Exception {
        try {

            if (isdn == null || isdn.trim().length() == 0) {
                return -1;
            }

            if (content == null || content.trim().length() == 0) {
                return -1;
            }

            if (sender == null || sender.trim().length() == 0) {
                return -1;
            }

//            if (serviceId == null || serviceId.trim().length() == 0) {
//                return -1;
//            }
            logger.info("Start Send SMS to isdn : " + isdn + " with content : " + content);
            MtStub stub = getMtStub();
            int statusSend = stub.send(Common.SESSION_ID, serviceId, sender,
                    isdn, Common.CONTENT_TYPE, content, Common.SMS_STATUS);

            logger.info("Status send when return: " + statusSend);
            return statusSend;

        } catch (Exception e) {
            logger.error("Exception at sendSMS : " + e.getMessage(), e);
            throw e;
        }
    }

    /**
     * sendUnicode
     *
     * @param isdn
     * @param content
     * @return
     * @throws Exception
     */
    public int sendUnicode(String isdn, String content) throws Exception {
        String msisdn = CommonUtils.getMsisdn(isdn);
        String isdnSend = SENDER;
        return sendUnicode(msisdn, content.getBytes("UTF-16BE"), isdnSend);
    }

    /**
     * sendUnicode
     *
     * @author daibq
     * @param isdn
     * @param content
     * @param sender
     * @return
     * @throws Exception
     */
    public int sendUnicode(String isdn, byte[] content, String sender) throws Exception {
        return sendUnicode(isdn, content, sender, null);
    }

    /**
     * @author daibq
     * @param isdn
     * @param content
     * @param sender
     * @param serviceId
     * @return
     * @throws Exception
     */
    public int sendUnicode(String isdn, byte[] content, String sender, String serviceId) throws Exception {
        try {
            logger.info("Start Send SMS to isdn : " + isdn + " with content : " + new String(content, StandardCharsets.UTF_8));
            MtStub stub = getMtStub();
            int statusSend = stub.send(Common.SESSION_ID, serviceId, sender,
                    isdn, "1", content, Common.SMS_STATUS);

            logger.info("Status send when return: " + statusSend);
            return statusSend;

        } catch (Exception e) {
            logger.error("Exception at sendSMS : " + e.getMessage(), e);
            throw e;
        }
    }

    public int sendErrorToIsdn(String content) throws Exception {

        return -1;//sendSMS(Constant.RECEIPVER, content);
    }
}
