/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.common.util.pre;

import java.util.HashMap;

/**
 *
 * @author vandungn
 */
public interface Constant {

    public static final String DEFAULT_LANGUAGE = "DEFAULT_LANGUAGE";
    public static final String TIME_MAIN_SLEEP = "TIME_MAIN_SLEEP";
    public static final String DB_SQL = "cmpre";
    public static final String DATABASE_SERVER_USSD_APP = "SERVER_USSD_APP";
    public static final String DATABASE_SERVER_VAS = "DATABASE_SERVER_VAS";
    public static final String DATABASE_SERVER_PRE = "DATABASE_SERVER_CMPRE";
    public static final String DATABASE_SERVER_DB_USSD_CONFIG = "DATABASE_SERVER_DB_USSD_CONFIG";

    public static final String VAS_GW_RECORD_SEQUENCE = "vas_gw_log_seq";
    public static final String TRANS_RECORD_SEQUENCE = "transaction_log_seq";
    public static final int CONFIG_INSERT_BATCH = 100;
    public static final String ERR_DB = "99";
    public static final String ERR_EXCH = "98";
    public static final String ERR_SUCCESS = "00";
    public static final String ERR_PROCESSING = "01";
    public static final String ERR_NOT_FOUND_GROUP = "03";
    public static final String ERR_NOT_FOUND_PRODUCT = "04";
    public static final String ERR_MO_HIS = "26";
    public static final String ERR_RECEIVER = "27";
    public static final String ERR_PASSWORD = "28";
    public static final String ERR_PASSWORD_WRONG = "29";
    public static final String ERR_SYSTEM_BUSY = "30";
    public static final String ERR_SENDER_OVER = "31";
    public static final String ERR_LAST_FIVE = "32";

    public static final String SUCCESS = "SUCCESS";
    public static final String DES_NOT_FOUND_GROUP = "Not found group";
    public static final String DES_NOT_FOUND_PRODUCT = "Not found product";
    public static final String DES_MO_EXITS = "Mo exists record";
    public static final String DES_ERR_EXCH = "Error Exchange";
    //
    public static final String CHECK_4G = "CHECK_4G";
    public static final String GIVINGDATA = "GIVINGDATA";
    public static final String SHARINGDATA = "SHARINGDATA";
    public static final String YOUTUBE = "YOUTUBE";
    public static final String SYNTAX_CHECK = "H";
    //nhutntm
    public static final String OCSHW_INTEGRATIONENQUIRY = "OCSHW_INTEGRATIONENQUIRY";
    public static final String OCSHW_ADJUSTACCOUNT = "OCSHW_ADJUSTACCOUNT";
    public static final String RES_SUCCESS = "0";
    public static final String BALANCE_EXCH = "2600";
    public static final String AND = " and ";

    public static final String SMS_YOUR_BALANCE = "You are using {1} package. Your Super exchange balance is ";
    public static final String SMS_NONE_RENEW = "non-auto-renew";
    public static final String SMS_RENEW = "auto-renew";
    public static final String SMS_RETURN = "{1} USD valid till {2} at 23:59";
    public static final String SMS_RETURN_0 = "Your exchange balance is 0 USD";
    public static final String SMS_SEE_MORE = "Press 1 to see more";

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
    public static final String SMS_CONFIG_FILE = "../etc/sms.properties";
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
    public static HashMap<String, String> lstProductMapping = new HashMap<String, String>();

    public static final String REQUEST = "REQUEST";
    public static final String RESPONSE = "RESPONSE";
}
