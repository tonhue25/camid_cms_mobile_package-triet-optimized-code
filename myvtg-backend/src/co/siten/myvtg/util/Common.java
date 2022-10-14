/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.siten.myvtg.util;
/**
 *
 * @author daibq
 */
public class Common {

    public static class TransType {

        public static final int EXCHANGE = 1;
        public static final int SERVICE = 2;
        public static final int WEBSERVICE = 3;
    }

    public static class ErrorCode {

        public static final String TIMEOUT = "001";
        public static final String ERROR = "000";

        public static final String MSG_SUCCESS = "0000";
        public static final String MSG_RIGHT_ID_NO = "0001";
        public static final String MSG_SPECIAL_ISDN = "0007";
        public static final String MSG_NORMAL_ISDN = "0008";
        public static final String MSG_RIGHT_CONTACT = "0010";
    }
    /**
     * Id to remove transaction.
     */
    public static final String ID_REMOVE_TRANS = "9999";
    // Connection
    /**
     * Schema BILLING
     */
    public static final String BILL_SCHEMA = "BILLING";
    /**
     * Schema Payment
     */
    public static final String PAYMENT_SCHEMA = "PAYMENT";
    /**
     * Schema BCCS_IM
     */
    public static final String IM_SCHEMA = "IM";
    /**
     * Schema CM_PRE2
     */
    public static final String CM_PRE_SCHEMA = "CM_PRE2";
    /**
     * Schema CM_POS2
     */
    public static final String CM_POS_SCHEMA = "CM_POS2";

    /**
     * Schema CM_PRE2 NEW
     */
    public static final String CM_PRE_NEW_SCHEMA = "CM_PRE2_NEW";

    // SMS config
    public static final String SMS_CONFIG_FILE = "../resources/sms.properties";
    public static final String USIM = "USIM";
    public static final String URL_SMS = "URL_SMS";
    public static final String SMS_ISDN_SEND = "SMS_ISDN_SEND";
    public static final String USER_NAME_SMS = "USER_NAME_SMS";
    public static final String PASSWORD_SMS = "PASSWORD_SMS";
    public static final String XMNLS_SMS = "XMNLS_SMS";
    public static final String SERVICE_ID = "VCI";
    public static final String CONTENT_TYPE = "0";
    public static final String SMS_STATUS = "1";
    public static final String SESSION_ID = "0";

    public static final long USD_EXCHANGE_RATE = 1000000L;
    public static final int CHANGE_SIM_SUCCESS = 1;
    public static final int ERROR_SYSTEM = 2;
    public static final int ERROR_AUTHORIZE = 3;
    public static final int ERROR_AUTHEN_FAIL = 4;
    public static final int ERROR_COMMON = 5;
    
    //
    public static final String TRANS_CHANGE_SIM_SEQ = "trans_change_sim_seq";
    public static final String TRANS_CHANGE_SIM_DETAIL_SEQ = "logs_change_sim_SEQ";
    public static final String ERR3050 = "ERR3050";
    public static final String CHANGE_SIM_4G = "CHANGE_SIM_4G";
    public static final String TYPE_CHECK_PRE = "1";
    public static final String ERR_CHECK_PRE = "01";
    public static final String TYPE_CHECK_OLD_SERIAL = "2";
    public static final String ERR_CHECK_OLD_SERIAL = "02";
    public static final String TYPE_EXIST_ISDN = "3";
    public static final String ERR_EXIST_ISDN = "03";
    public static final String TYPE_CHECK_USIM = "4";
    public static final String ERR_CHECK_USIM = "04";
    public static final String TYPE_CHECK_NEW_SERIAL = "5";
    public static final String ERR_CHECK_NEW_SERIAL = "05";
    public static final String TYPE_CHECK_SERIAL_ACTIVE = "6";
    public static final String ERR_CHECK_SERIAL_ACTIVE = "06";
    public static final String TYPE_CHECK_NICE_NUMBER = "7";
    public static final String ERR_CHECK_NICE_NUMBER = "07";
    public static final String TYPE_TERMINATE_DB = "8";
    public static final String ERR_TERMINATE_DB = "08";
    public static final String TYPE_TERMINATE_PRO = "9";
    public static final String ERR_TERMINATE_PRO = "09";
    public static final String TYPE_CHANGE_SIM = "10";
    public static final String ERR_CHANGE_SIM = "10";
    //
    public static final String TYPE_VALIDATE = "1";
    public static final String TYPE_TERMINATE = "2";
    public static final String TYPE_CHANGE_SIM_4G = "3";
    public static final String TYPE_CHANGE_SIM_ERR = "4";
    public static final Long REASON_ID_CHANGE_SIM_AGENT = 12599L;
    
    public static final String TYPE_CHANGE_DETAIL_VALIDATE = "1";
    public static final String TYPE_CHANGE_DETAIL_CHANGE = "2";
    public static final String TYPE_CHANGE_DETAIL_ADD_COM = "3";
    public static final String TYPE_CHANGE_DETAIL_ERR = "4";
    public static final String  CHANGE_SIM_4G_AGENT = "CHANGE_SIM_4G_AGENT";
    public static final int  APP_TYPE_CHANGE_CUS = 1;
    public static final int  APP_TYPE_CHANGE_AGENT = 2;
    
    public static final String TYPE_CAMID_EXCHANGE = "ex";
    public static final String TYPE_CAMID_PROMOTION = "prom";
    public static final String TYPE_CAMID_BASIC = "basic";
    public static final String TYPE_CAMID_DATA = "data";
    
    //SMS_GLOBAL config (send over Metfone)
    public static final String URL_SMS_GLOBAL = "URL_SMS_GLOBAL";
    public static final String USER_NAME_SMS_GLOBAL = "USER_NAME_SMS_GLOBAL";
    public static final String PASSWORD_SMS_GLOBAL = "PASSWORD_SMS_GLOBAL";
    public static final String RECV_TIMEOUT_GLOBAL = "RECV_TIMEOUT_GLOBAL";
    public static final String CONNECT_TIMEOUT_GLOBAL = "CONNECT_TIMEOUT_GLOBAL";
    public static final String CP_CODE = "CAMID";
    public static final String REQUEST_ID = "REQUEST_ID";
    public static final String SERVICE_ID_GLOBAL = "SERVICE_ID";
    public static final String COMMAND_CODE = "COMMAND_CODE";
    public static final String SMS_RESPONSE_MESSAGE = "MESSAGE";
    public static final String SMS_RESPONSE_RESULT = "RESULT";


    // ws cm config
    public static final String URL_WS_GLOBAL = "URL_WS_GLOBAL";
    public static final String TOKEN_WS_GLOBAL = "TOKEN_WS_GLOBAL";
    public static final String SOURCE_WS_GLOBAL = "SOURCE_WS_GLOBAL";
    public static final String MESSAGE_WS_RESPONSE = "MESSAGE_WS_RESPONSE";
    public static final String ERROR_CODE_WS_RESPONSE = "ERROR_CODE_WS_RESPONSE";
    public static final String MESSAGE_WS_RESPONSE_FORGET = "MESSAGE_WS_RESPONSE_FORGET";
    public static final String ERROR_CODE_WS_RESPONSE_FORGET = "ERROR_CODE_WS_RESPONSE_FORGET";
    public static final String ERROR_CODE_SEARCH_RESPONSE = "ERROR_CODE_SEARCH_RESPONSE";
    public static final String MESSAGE_SEARCH_RESPONSE = "MESSAGE_SEARCH_RESPONSE";
    public static final String TITLE_REGISTER_SUCCESS = "TITLE_REGISTER_SUCCESS";
    public static final String TITLE_REGISTER_SUCCESS_KM = "TITLE_REGISTER_SUCCESS_KM";
    public static final String MESSAGE_REGISTER_SUCCESS = "MESSAGE_REGISTER_SUCCESS";
    public static final String MESSAGE_REGISTER_SUCCESS_KM = "MESSAGE_REGISTER_SUCCESS_KM";
    public static final String SEARCH_REQUEST_FTTH_NOT_FOUND= "SEARCH_REQUEST_FTTH_NOT_FOUND";
    public static final String SEARCH_REQUEST_FTTH_NOT_FOUND_KM= "SEARCH_REQUEST_FTTH_NOT_FOUND_KM";
    public static final String SERVER_ERROR= "SERVER_ERROR";
    public static final String SERVER_ERROR_KM= "SERVER_ERROR_KM";


    // change card config
    public static final String URL_CHANGECARD_GLOBAL = "URL_CHANGECARD_GLOBAL";
    public static final String TOKEN_CHANGECARD_GLOBAL = "TOKEN_CHANGECARD_GLOBAL";
    public static final String LOCALE_CHANGECARD_GLOBAL = "LOCALE_CHANGECARD_GLOBAL";

    //login to cms esport
    public static final String URL_API_GET_TOKEN= "URL_API_GET_TOKEN";
    public static final String PREFIX_GET_TOKEN= "PREFIX_GET_TOKEN";
    public static final String DEVICE_GET_TOKEN= "DEVICE_GET_TOKEN";
    public static final String APPCODE_GET_TOKEN= "APPCODE_GET_TOKEN";

    // emoney
    public static final String EMONEY_URL_API_GET_ACCOUNT_CONFIRM = "EMONEY_URL_API_GET_ACCOUNT_CONFIRM";
    public static final String EMONEY_URL_API_GET_TOKEN = "EMONEY_URL_API_GET_TOKEN";
    public static final String EMONEY_URL_API_GET_ACCOUNT_INFO = "EMONEY_URL_API_GET_ACCOUNT_INFO";
    public static final String EMONEY_URL_API_RESEND_OTP = "EMONEY_URL_API_RESEND_OTP";
    public static final String EMONEY_USERNAME = "EMONEY_USERNAME";
    public static final String EMONEY_PASSWORD = "EMONEY_PASSWORD";

    //
    public static final String URL_BCCS_INTERNAL = "URL_BCCS_INTERNAL";
    public static final String TOKEN_WEB_BCCS = "TOKEN_WEB_BCCS";
    public static final String USERNAME_BCCS = "USERNAME_BCCS";
    public static final String PASSWORD_BCCS = "PASSWORD_BCCS";
    //
    public static final String ERROR_CODE_RESPONSE = "ERROR_CODE_RESPONSE";
    public static final String ERROR_DESCRIPTION_RESPONSE = "ERROR_DESCRIPTION_RESPONSE";
    public static final String MESSAGE_CODE_RESPONSE = "MESSAGE_CODE_RESPONSE";
    public static final String REQUIRED_OTP_RESPONSE = "REQUIRED_OTP_RESPONSE";
    //
    public static final String ADDED_DATE_RESPONSE = "ADDED_DATE_RESPONSE";
    public static final String URL_BCCSGW = "URL_BCCSGW";}
