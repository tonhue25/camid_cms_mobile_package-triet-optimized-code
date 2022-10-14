/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.dto.ConnectExchInfo;
import co.siten.myvtg.dto.InforSub;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.ExchangeChannel;
import com.viettel.bccs.cm.common.util.pre.Constant;
import com.viettel.bccs.cm.common.util.pre.DateUtils;
import com.viettel.common.ExchMsg;
import com.viettel.common.ViettelService;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author daibq
 */
@org.springframework.stereotype.Service("ExchangeService")
public class ExchangeService {

    @Autowired
    ProService proService;

    private static final Logger logger = Logger.getLogger(ExchangeService.class);
    private String loggerLabel = "ExchangeService: ";
    private long timeSt;
    private long timeEx;
    protected StringBuffer br = new StringBuffer();
    private ExchangeChannel channel;
    private static HashMap loggerExchMap;
    private static long minTimeExch;
    private static long[] timesExchLevel;
    private SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyyMMddHHmmss");
    private DecimalFormat df = new DecimalFormat("#0");

    /**
     * getInforOcs
     *
     * @param msisdn
     * @return
     */
    public InforSub getInforOcs(String msisdn) {
        InforSub result = null;
        try {
            msisdn = msisdn.startsWith("855") ? msisdn : "855" + msisdn;
            ExchMsg request = new ExchMsg();
            request.setCommand(Constant.OCSHW_INTEGRATIONENQUIRY);
            request.set("MSISDN", msisdn);
            ExchMsg response = exchangeSend(request, msisdn);
            if (Constant.RES_SUCCESS.equals(response.getError())) {
                if (response != null) {
                    result = new InforSub();
                    String balanceIdKey = "ACCOUNT_TYPE_LIST";
                    String balanceValueKey = "BALANCE_LIST";
                    String expireValueKey = "EXPIRE_TIME_LIST";
                    String priceValueKey = "PRICE_PLAN_INDEX_LIST";
                    String cycStartValueKey = "CYCLE_START_TIME_LIST";
                    String cycEndValueKey = "CYCLE_END_TIME_LIST";
                    String statusKey = "BILL_STATUS_LIST";
                    String activeStop = "ACTIVE_STOP";

                    String[] typeIds = ((String) response.get(balanceIdKey)).split("&");
                    String[] balances = ((String) response.get(balanceValueKey)).split("&");
                    String[] expires = ((String) response.get(expireValueKey)).split("&");
                    String[] pricePlan = ((String) response.get(priceValueKey)).split("&");
                    result.setActiveStop(new Timestamp(DateUtils.convertStringToTime((String) response.get(activeStop), "yyyyMMdd").getTime()));
                    result.setLstPrice(typeIds);
                    result.setLstBalance(balances);
                    result.setLstExpire(expires);
                    result.setLstPricePlan(pricePlan);
                    return result;
                } else {
                    logger.info("\r\nERROR Pro get balance response is null --> " + response);
                    return null;
                }

            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error " + ex.getMessage());
        }
        return result;
    }

    /**
     * Lệnh trừ tiền âm khách hàng
     *
     * @param msisdn
     * @param money
     * @param account
     * @return String
     * @throws Exception
     */
    public HashMap<String, ExchMsg> addMoney(String msisdn, int money, String account, String partyCode) throws Exception {
        ExchMsg request = new ExchMsg();
        HashMap<String, ExchMsg> lstResult = new HashMap<String, ExchMsg>();
        request.setCommand(Constant.OCSHW_ADJUSTACCOUNT);
        request.set("ISDN", msisdn.startsWith("855") ? msisdn : "855" + msisdn);
        request.set("AMOUNT", String.valueOf(money));
        request.set("ACCT_REST_ID", account);
        request.set("PARTYCODE", partyCode);
        lstResult.put(Constant.REQUEST, request);
        try {
            ExchMsg response = exchangeSend(request, msisdn);
            lstResult.put(Constant.RESPONSE, response);
            return lstResult;
        } catch (Exception ex) {
            logger.error("Error when call provisioning", ex);
            throw ex;
        }
    }

    /**
     * viettelServiceSend
     *
     * @param request
     * @param msisdn
     * @return
     */
    public ViettelService viettelServiceSend(ViettelService request, String msisdn) {
        ViettelService response = new ViettelService();
        try {
            setTimeSt(System.currentTimeMillis());
            logger.info("REQUEST: " + request);
            //
            channel = proService.getChannel();
            //
            response = (ViettelService) channel.send(request);

        } catch (IOException ex) {
            logger.error("IOException when send to service => return error: 001", ex);
            response.setResponseCode(Constant.ErrorCode.TIMEOUT);
        } catch (Exception ex) {
            logger.error("error when send to exchange => return error: 000 ", ex);
            response.setError(Constant.ErrorCode.ERROR);
        } finally {
            logger.info("RESPONSE: " + response);
            logTime("Time to " + request.getProcessCode());
        }
        return response;
    }

    /**
     * exchangeSend
     *
     * @param request
     * @param msisdn
     * @return
     */
    public ExchMsg exchangeSend(ExchMsg request, String msisdn) {
        ExchMsg response = new ExchMsg();
        try {
            setTimeSt(System.currentTimeMillis());
            logger.info("REQUEST: " + request);
            //
            channel = proService.getChannel(Constants.EXCHANGE);
            //
            response = (ExchMsg) channel.send(request);

        } catch (IOException ex) {
            logger.error("IOException when send to exchange => return error: 001 ", ex);
            response.setError(Constant.ErrorCode.TIMEOUT);

        } catch (Exception ex) {
            logger.error("error when send to exchange => return error: 000 ", ex);
            response.setError(Constant.ErrorCode.ERROR);
        } finally {
            logger.info("RESPONSE: " + response);
            logTime("Time to " + request.getCommand());
        }

        return response;
    }

    /**
     * toDateMsg
     *
     * @param date
     * @return
     */
    private String toDateMsg(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return sdf.format(cal.getTime());
    }

    /**
     *
     * @param timeSt
     */
    public void setTimeSt(long timeSt) {
        this.timeSt = timeSt;
    }

    public void logTime(String strLog) {
        timeEx = System.currentTimeMillis() - timeSt;
        if (timeEx >= minTimeExch && loggerExchMap != null) {
            br.setLength(0);
            br.append(loggerLabel).
                    append(getTimeLevelExch(timeEx)).append(": ").
                    append(strLog).
                    append(": ").
                    append(timeEx).
                    append(" ms");

            logger.warn(br);
        } else {
            br.setLength(0);
            br.append(loggerLabel).
                    append(strLog).
                    append(": ").
                    append(timeEx).
                    append(" ms");

            logger.info(br);
        }
    }

    /**
     * Log cham exchange, service
     *
     * @param times
     * @return
     */
    private String getTimeLevelExch(long times) {
        if (loggerExchMap != null) {
            int key = Arrays.binarySearch(timesExchLevel, times);
            if (key < 0) {
                key = -key - 2;
            }

            String label = (String) loggerExchMap.get(key);
            return (label == null) ? "-" : label;
        }
        return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * getTime
     *
     * @param n
     * @return
     */
    public String getTime(int n) {
        String dt;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, n);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        return dt;
    }
}
