package co.siten.myvtg.util;

/**
 *
 * @author thomc
 *
 */
public class Constants {

    public static final int ERROR_CODE_INT_0 = 0;
    public static final int ERROR_CODE_INT_1 = 1;
    public static final int ACTIVE = 1;
    public static final int INACTIVE = 0;
    public static final int DELETED = 3;


    public static final int MOHIS_STATUS_INT_0 = 0;
    public static final int MOHIS_STATUS_INT_1 = 1;

    public static final int FAKE_MO_PENDING = 0;
    public static final int FAKE_MO_PROCESSING_SEND = 1;
    public static final int FAKE_MO_WAIT_SYNC_SUB = 2;

    public static final int SUBCRIBER_STATE_CANCEL = 0;
    public static final int SUBCRIBER_STATE_REGISTERED = 1;
    public static final int SUBCRIBER_STATE_PENDDING = 2;

    public static final String ERROR_CODE_0 = "0";
    public static final String ERROR_CODE_00 = "00";
    public static final String ERROR_CODE_000 = "000";
    public static final String ERROR_CODE_1000 = "1000";
    public static final String ERROR_CODE_1 = "1";
    public static final String ERROR_CODE_2 = "2";
    public static final String ERROR_CODE_3 = "3";
    public static final String SLASH = " / ";
    public static final Integer CUSTOMER_RECORD_PER_PAGE = 10;
    public static final Integer CUSTOMER_PAGE_START = 0;

    // SubType
    public static final int SUBTYPE_TRATRUOC = 1;
    public static final int SUBTYPE_TRASAU = 2;

    // PostType
    public static final int POSTTYPE_CALL = 0;
    public static final int POSTTYPE_SMS = 1;
    public static final int POSTTYPE_OTHER = 2;
    public static final int POSTTYPE_DATA = 3;
    public static final int POSTTYPE_VAS = 4;

    public static final String API_KEY = "BDDA22D9177343B6429DA9F9DCB10CC1";
    public static final long REQUEST_TIME_OUT = 60000;

    public static final int ACC_TYPE_MAIN = 1;
    public static final int ACC_TYPE_PROMOTION = 2;
    public static final int ACC_TYPE_DATA = 3;
    public static final int ACC_TYPE_DATAPROMOTION = 4;
    public static final int ACC_TYPE_SMS = 5;
    public static final int ACC_TYPE_CALL = 6;
    public static final int ACC_TYPE_SMS_ROMOTION = 7;
    public static final int ACC_TYPE_CALL_PROMOTION = 8;
    public static final int ACC_TYPE_BACLANCE_PROMOTION = 9;
    public static final int ACC_TYPE_BACLANCE_EXCHANGE = 10;

    public static final String ACT_STATUS_03 = "03";
    public static final String ACT_STATUS_10 = "10";
    public static final String ACT_STATUS_100 = "100";

    public static final int ACTION_TYPE_REGISTER = 0;
    public static final int ACTION_TYPE_CANCEL = 1;
    public static final int ACTION_TYPE_2 = 2;
    public static final int ACTION_TYPE_3 = 3;
    public static final int ACTION_TYPE_BUY_DATA = 2;

    public static final int ACTION_CHANNEL_TYPE_FAKE_USSD = 0;
    public static final int ACTION_CHANNEL_TYPE_FAKE_SMS = 1;
    public static final int ACTION_CHANNEL_TYPE_WEBSERVICE = 2;
    public static final int ACTION_CHANNEL_TYPE_FAKE_MO_TABLE = 3;

    public static final String SERVICE_CODE_AIRTIME = "AIR_TIME";
    public static final String SERVICE_CODE_AUTOSMS = "AUTOSMS";
    public static final String SERVICE_CODE_TRANSFER_MONEY = "TRANSFER_MONEY";

    public static final String WEBSERVICE_NAME_UN_REGISTER_3G = "unRegister3G";
    public static final String WEBSERVICE_NAME_BUY_DATA = "buyData";
    public static final String WEBSERVICE_NAME_REGISTER_AIR_TIME = "registerAirtime";
    public static final String WEBSERVICE_NAME_CASH_ADVANCE = "cashAdvance";
    public static final String WEBSERVICE_NAME_SHARE_MONEY = "shareMoney";
    public static final String WEBSERVICE_NAME_CHECK_CONDITION = "checkCondition";
    public static final String WEBSERVICE_NAME_SENDMT = "sendMT";
    public static final String WEBSERVICE_NAME_TRUTIEN = "addOrReduceMoneyProvisioning";
    public static final String WEBSERVICE_NAME_HUYSO = "deleteSubscriberProvisioning ";
    public static final String WEBSERVICE_NAME_ACTIVE_SUBCRIBER = "activeSubscriberProvisioning";
    public static final String WEBSERVICE_NAME_REGISTER_BUY_ISDN = "registerBuyIsdn";
    public static final String WEBSERVICE_NAME_BLOCK_SUBCRIBER = "blockSubscriberProvisioning";
    public static final String WEBSERVICE_NAME_CHANGE_SIM = "changeSimProvisioning";
    public static final String WEBSERVICE_NAME_CHANGE_SIM_UNITEL = "changeSIM";

    public static final String ACTION_CODE_44 = "44";
    public static final String ACTION_CODE_03 = "03";
    public static final String ACTION_CODE_12 = "12";
    public static final String ACTION_CODE_112 = "112";
    public static final String ACTION_CODE_06 = "06";

    public static final String ACTION_AUDIT_DES_REDUCE = "Reduce money to by Isdn";
    public static final String ACTION_AUDIT_DES_BLOCK = "Block outgoing by MyVTG";
    public static final String ACTION_AUDIT_DES_CHANGE = "Change SIM by MyVTG";

    public static final String SHOP_CODE_MYVTG = "MY_VTG";
    public static final String PK_TYPE_3 = "3";
    public static final int SUB_MB_STATUS_3 = 3;
    public static final int SUB_MB_STATUS_0 = 0;
    public static final int SUB_MB_STATUS_1 = 1;
    public static final int SUB_MB_STATUS_ACTIVE = 2;

    public static final String RETRIEVE_STOCK_ID = "retrieve_stock_id";

    public static final String SCORE_CONVERSION_RULE = "pri_change_post_rule";
    public static final String KEY_HOST = "cc_em_host";
    public static final String KEY_PORT = "cc_em_port";
    public static final String KEY_EMAIL = "cc_email";
    public static final String KEY_AUTH = "cc_em_auth";
    public static final String KEY_SSL = "cc_em_ssl";
    public static final String KEY_PASSWORD = "cc_em_pwd";
    public static final String KEY_USERNAME = "cc_em_username";
    public static final String PRIVILEGE_RULE_INTRO = "privilege_rule_intro";

    public static final String COL_NAME_IMSI = "IMSI";
    public static final String COL_NAME_SERIAL = "SERIAL";
    public static final String TABLE_NAME_SUB_MB = "SUB_MB";

    public static final int STOCK_ISDN_MOBILE_STATUS_3 = 3;
    public static final int STOCK_ISDN_MOBILE_STATUS_2 = 2;
    public static final int STOCK_ISDN_MOBILE_OWNTYPE_1 = 1;

    public static final String MYVTG = "MyVTG";
    public static final String BLOCK_GOING_CALL = "BLOCK_GOING_CALL";
    public static final String ACT_STATUS_00 = "00";
    public static final String ACT_STATUS_000 = "000";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String GIFT_CODE_INVALID = "Gift code is not valid!";
    public static final String NOT_ENOUGH_MARK_EXCHANGE = "Not enough mark to exchange!";
    public static final String SUBID_INVALID = "SubId is not valid!";
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;
    public static final String MARK_TYPE_ID_EXCH_POST = "mark_type_id_exch_post";

    public static final int SUB_STATUS_0 = 0;
    public static final int SUB_STATUS_1 = 1;

    public static final String SYNC_SUBSCRIBER_DAY_CONFIG = "sync_subscriber_day";
    public static final String APP_PARAM_DEFAULT_LANGUAGE = "default_language";

    public static final String DEFAULT_LANGUAGE = "en";
    public static final String LOCAL_LANGUAGE = "km";
    public static final String CARD_TYPE_USIM = "USIM";
    public static final int SIM_TYPE_4G = 4;
    public static final int SIM_TYPE_3G = 3;
    public static final int SIM_TYPE_3_4G = 7;

    // Config name & value
    public static final String DEVELOPER_MODE = "myvtg.develop.mode";
    public static final String APP_CLIENT_CONFIG = "app_client_config";
    public static final String APP_VERSION_Android = "app_version_android";
    public static final String APP_VERSION_iOS = "app_version_ios";
    public static final String NET_SIM_PACKAGE_LIST = "net_sim_package_list";
    public static final String MARKET_NAME_CONFIG = "market_name";
    public static final String MARKET_NAME_UNITEL = "unitel";
    public static final String MARKET_NAME_MOVITEL = "movitel";
    public static final String MARKET_NAME_METFONE = "metfone";
    public static final String MARKET_NAME_ALL = "all";
    public static final String SERVICE_TYPES_PREPAID_AND_3G = "service_types_4_prepaid_and_3g";
    public static final String SERVICE_TYPES_PREPAID_AND_4G = "service_types_4_prepaid_and_4g";
    public static final String SERVICE_TYPES_POSPAID_AND_3G = "service_types_4_pospaid_and_3g";
    public static final String SERVICE_TYPES_POSPAID_AND_4G = "service_types_4_pospaid_and_4g";

    public static final String ERROR_500 = "500: \"Internal Server Error\"";
    public static final String ERROR_501 = "501: \"Unsuccessfully Recharge!\"";
    public static final String ERROR_502 = "502: \"Unsuccessfully purchased data!\"";
    public static final String ERROR_503 = "503: \"Unsuccessfully purchased new phone number!\"";
    public static final String ERROR_504 = "504: \"Unsuccessfully exchanged new phone number!\"";
    public static final String ERROR_505 = "505: \"Unsuccessfully restored your old phone number!\"";
    public static final String ERROR_506 = "506: \"Unsuccessfully change your SIM!\"";
    public static final String ERROR_507 = "507: \"Unsuccessfully locked your SIM!\"";
    public static final String ERROR_508 = "508: \"Unsuccessfully registered Airtime service!\"";
    public static final String ERROR_509 = "509: \"Unsuccessfully got a loan!\"";
    public static final String ERROR_510 = "510: \"Unsuccessfully subscribed service!\"";
    public static final String ERROR_512 = "512: \"Unsuccessdully sent your comment!\"";
    public static final String ERROR_513 = "513:\"Unsuccessfully exchange postage!\"";
    public static final String ERROR_515 = "515:\"Unsuccessfully exchange data!\"";
    public static final String ERROR_516 = "516: \"Unsuccessfully exchange gift!\"";
    public static final String PRO = "pro";
    public static final String EXCHANGE = "exchange";

    public static final String VOLUMN_STR_UNLIMIT = "unlimited";
    public static final String ERROR_CODE_01 = "01";
    public static final String WS_DETACH_IP = "wsDetachIP";
    public static final String WS_TOPUP_POSTPAID = "topUpPostpaid";
    public static final String APP_VERSION = "app_version";
    public static final String APP_FORCE_UPDATE = "force_update";
    public static final String APP_FORCE_UPDATE_MESSAGE = "force_update_message";
    public static final String WEBSERVICE_UPDATE_CUSTOMER_INFO = "wsUpdateCustomerInfo";
    public static final String WEBSERVICE_GET_CODE = "doLoginByGetCode";
    public static final String WEBSERVICE_VERIFY_CODE = "verifyByCode";
    public static final String WEBSERVICE_GET_PROFILE_BY_ISDN = "getProfileByIsdn";
    public static final String WEBSERVICE_UPDATE_INFO_BY_USER = "updateInfoByUser";

    public static final String WEBSERVICE_SEARCH_NUMBER = "wsSearchNumber";
    public static final String WEBSERVICE_RESERVE_NUMBER = "wsReserveNumber";

    public static final String WS_XPATH_BODY = "/Envelope/Body/";
    public static final String WS_XPATH_RETURN = "/return";
    public static final String WS_XPATH_ERR_CODE = "/errorCode";

    public static final int WIDTH_QR_CODE = 100;
    public static final int HEIGHT_QR_CODE = 100;

    public static String ERROR_SUCCESS = "0";
    public static String ERROR_PARAMETER_INVALID = "1";
    public static String ERROR_UNAUTHORIZED = "401";
    public static String NOT_FOUND_DATA = "2";
    public static String ERROR_SEND_SMS = "3";
    public static String SUBMB_BLOCK = "4";
    public static String NUMBER_TIME = "5";
    public static String NOT_ENOUGH_MONEY = "6";
    public static String SYSTEM_BUSY = "7";
    public static String LESS_DAYS = "8";
    public static String NOT_SUCCESS = "9";
    public static String TOO_MUCH_MONEY = "10";
    public static String ERROR_CHANGE_LOYALTY = "11";
    public static String ERROR_CONFIG_WS = "12";
    public static final String MAX_IMAGE_SIZE = "20";
    public static String ERROR_SYSTEM = "99";
    public static String NOT_ACCOUNT_EMONEY = "13";
    public static String ERR_TRANS_EMONEY = "14";
    public static String ERR_MAX_SEND_OTP = "401";

    public static final String CALL_PRO = "CALL_PRO";
    public static final String MAX_SELECT_INVITE = "MAX_SELECT_INVITE";
    public static final String MY_METFONE_INVITE_REDEEM = "INVITE_REDEEM";
    public static final String MY_METFONE_ADD_PONT = "ADD_POINT";
    public static final String MAX_INVITE_ON_DAY = "MAX_INVITE_ON_DAY";

    public static String SUCCESS = "00";
    public static String PARAMETER_INVALID = "01";
    public static String NOT_FOUND = "02";
    public static String TIME_OUT = "04";
    public static String MAX_SHARE = "03";
    public static String MAX_TIME = "05";
    public static String ERROR_CONF = "06";
    public static String ERROR_REDEEM = "07";

    public static String COMMAN = "IN_CARDTOPUP";
    public static final String WEBSERVICE_CHANGE_KEEP_SIM = "wsChangeIsdnKeepSim";
    public static final String WEBSERVICE_SEARCH_BY_SIM = "wsSearchNumberToBuy";
    public static final String WEBSERVICE_LOCK_ISDN_HIS = "wsGetListOrderNumberHistory";
    public static final String WEBSERVICE_LOCK_ISDN = "wsLockIsdnToBuy";
    public static final String WEBSERVICE_SYS_VAS_RECORD = "wsGetInfoPackageVas";
    public static final String WEBSERVICE_ISHARE = "wsTransferIshare";
    public static final String CHECK_OTP_CHANGE_ISDN_KEEP_SIM = "wsGetOtpMetfone";
    public static final String CHECK_OTP_ISHARE = "wsGetOtpIshare";
    public static final String WEBSERVICE_CHANGE_LOYALTY = "wsChangeLoyalty";
    public static final String WEBSERVICE_LOYALTY_POINT = "wsLoyaltyPoint";
    public static final String WEBSERVICE_LOYALTY_RANK = "wsGetAccountRank";
    public static final String WEBSERVICE_CREATE_INVOICE = "wsCreateInvoiceEmoney";
    public static final String WEBSERVICE_CF_PAY_EMONEY = "wsConfirmPayEmoney";

    //mail
    public static final String INTERNET_WIFI_HOST = "HOST";
    public static final String INTERNET_WIFI_PORT = "PORT";
    public static final String INTERNET_WIFI_AUTHEN = "AUTHEN";
    public static final String INTERNET_WIFI_SSL = "SSL";
    public static final String INTERNET_WIFI_FROM = "FROM";
    public static final String INTERNET_WIFI_PASS = "PASS";
    public static final String INTERNET_WIFI_TO = "TO";
    public static final String INTERNET_WIFI_CC = "CC";
    public static final String INTERNET_WIFI_BCC = "BCC";
    public static final String METFONE_CARE = "METFONE+";
    public static final Long TRANSFER = 0L;
    public static final String STATUS_PAYMENT_WAIT = "1,2";
    public static final Long STATUS_PAID = 3L;
    public static final Long STATUS_PAYMENT_ERR = 4L;
    public static final Long STATUS_USE = 1L;
    public static final Long STATUS_NOT_USE = 0L;
    public static final String APP_FORCE_IF_UPDATE_MESSAGE = "info_force_update_message";
    public static final String IF_GAME_MESSAGE = "info_game_message";
    public static final String OPEN_TEST_GAME = "OPEN_TEST_GAME";
    public static final String OPEN_TEST_COMPLAINT = "OPEN_TEST_COMPLAINT";

    public static final String getComType = "getComType";
    public static final String getComTypeCamID = "getComTypeCamID";
    public static final String getListComplaintType = "getListComplaintType";
    public static final String getListComplaintGroupType = "getListComplaintGroupType";
    public static final String exChangeCardGetlistRequest = "exChangeCardGetlistRequest";
    public static final String exChangeCardAddNewRequest = "exChangeCardAddNewRequest";
    public static final String submitComplaintMyMetfone = "submitComplaintMyMetfone";
    public static final String getProcessList = "getProcessList";
    public static final String getComplaintFilterList = "getComplaintFilterList";
    public static final String getComplaintHistory = "getComplaintHistory";
    public static final String getComplaintConfirm = "getComplaintConfirm";
    public static final String reopenComplain = "reopenComplain";
    public static final String closeComplain = "closeComplain";
    public static final String updateComplaint = "updateComplaint";
    public static final String rateComplain = "rateComplain";
    public static final String getConsultant = "wsGetConsultant";
    public static final String getListMemberSabay = "wsGetListMemberSabay";
    public static final String checkAddMemberSabay = "wsCheckAddMemberSabay";
    public static final String addMemberSabay = "wsAddMemberSabay";
    public static final String removeMemberSabay = "wsRemoveMemberSabay";
    public static final String getListMemberTickTox = "wsGetListMemberTiktok";
    public static final String checkAddMemberTickTox = "wsCheckAddMemberTiktok";
    public static final String addMemberTickTox = "wsAddMemberTiktok";
    public static final String removeMemberTickTox = "wsRemoveMemberTiktok";
    public static final String command = "ADD";
    public static final Long english = 1L;
    public static final Long khmer = 0L;
    public static final Long sendSMS = 0L;
    public static final Long action = 51L;
    public static final String POLYCY_TIKTOK = "POLYCY_TIKTOK";
    public static final String POLYCY_SABAY = "POLYCY_SABAY";
    //phuonghc
    public static final String IF_COVID_MESSAGE = "info_covid_message";
    //phuonghc
    public static final String PARENT_TYPE_IS_PROMOTION = "PROMOTION";
    public static final String PARENT_TYPE_IS_BASIC = "BASIC";
    public static final String PARENT_TYPE_IS_EXCHANGE = "EXCHANGE";
    public static final String PARENT_TYPE_IS_DATA = "DATA";
    
    public static final String TYPE_IS_SMS = "SMS";
    public static final String TYPE_IS_CALLING = "CALLING";
    public static final String TYPE_IS_VAS = "VAS";
    public static final String TYPE_IS_DATA = "DATA";
    
    public static final String CAMID_NOTIFY_ACTION_TYPE = "ACTION_TYPE";
    public static final String CAMID_NOTIFY_NONE = "NONE";
    public static final String CAMID_NOTIFY_LINK = "LINK";
    public static final String EMPTY_STRING = "";
 //khieudv
    public static final Long paramIDReceived = 0L;
    public static final Long paramIDProcessing = 1L;
    public static final Long paramIDResponded = 2L;
    public static final Long paramIDClosed = 3L;
    
    public static final String paramCodeReceived = "Received";
    public static final String paramCodeProcessing = "Processing";
    public static final String paramCodeResponded = "Responded";
    public static final String paramCodeClosed = "Closed";
    
    public static final String UNIT_METFONE = "UNIT_METFONE";
    public static final String UNIT_POINT = "UNIT_POINT";
    public static final String UNIT_EMONEY = "UNIT_EMONEY";
}
