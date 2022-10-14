/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.database.DAO.pre.Webservice.Pr;

import com.viettel.bccs.cm.common.util.pre.CommonSub;
import com.viettel.common.ViettelService;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.Session;

public class ProvisioningV2 {

	private static InterfacePr interfacePr = new InterfacePr();
	public static long CALL_ID = 0;

	public static ViettelService activePrePaidSubscriber(Session imSession, Session pmSession, Session cmPreSession,
			String isdn, String serial, String imsi, Long offerId, String productCode, String countryCode)
			throws Exception {
		ViettelService requestPr = new ViettelService();
		requestPr.set("productId", productCode);
		requestPr.set("modificationType", "0");

		Map lstParam = CommonSub.getSubscriberParam(cmPreSession, imSession, pmSession, isdn, serial, imsi, offerId,
				productCode, "0", countryCode);
		if (lstParam != null && !lstParam.isEmpty()) {
			Iterator paramKey = lstParam.keySet().iterator();
			while (paramKey.hasNext()) {
				Object paramName = paramKey.next();
				Object paramValue = lstParam.get(paramName);
				if (paramValue != null) {
					requestPr.set(paramName.toString(), paramValue);
				} else {
					requestPr.set(paramName.toString(), "");
				}
			}
		}
		ViettelService vtService = interfacePr.request(requestPr);
		return vtService;
	}

	public static ViettelService cancelSubscriber(Session cmPreSession, String isdn, String productCode, String imsi,
			String countryCode) throws Exception {
		long currentTime = System.currentTimeMillis();
		ViettelService requestPr = new ViettelService();
		requestPr.set("MSISDN", countryCode + isdn);
		requestPr.set("productId", productCode);
		requestPr.set("modificationType", "1");
		requestPr.set("IMSI", imsi);
		requestPr.set("CURRENT_SERVICES", CommonSub.getCurrentServices(cmPreSession, isdn));
		ViettelService vtService = interfacePr.request(requestPr);
		return vtService;
	}

	public static ViettelService changeSim(Session imSession, String isdn, String productCode, String oldImsi,
			String oldSerial, String newImsi, String newSerial, String countryCode) throws Exception {
		ViettelService requestPr = new ViettelService();
		requestPr.set("productId", productCode);
		requestPr.set("modificationType", "101");
		requestPr.set("MSISDN", countryCode + isdn);
		requestPr.set("OLD_IMSI", oldImsi);
		requestPr.set("OLD_SERIAL", oldSerial);

		requestPr.set("NEW_IMSI", newImsi);
		requestPr.set("NEW_SERIAL", newSerial);

		return interfacePr.request(requestPr);
	}

	public static ViettelService changeAccountBalance(String isdn, Double changeAmount, Long resetAmount, Long addDay,
			Long accountType, String countryCode) throws Exception {
		if (isdn != null && !"".equals(isdn)) {
			ViettelService requestPr = new ViettelService();
			requestPr.set("MSISDN", countryCode + isdn);
			requestPr.setMessageType("1900");
			if (changeAmount > 0) {
				requestPr.setProcessCode("000031");
				// format double amount
				try {
					DecimalFormat decimalFormat = new DecimalFormat("#.#####");
					requestPr.set("ADD_BALANCE", decimalFormat.format(changeAmount));
				} catch (Exception ex) {
					ex.printStackTrace();
					requestPr.set("ADD_BALANCE", changeAmount);
				}
				requestPr.set("ADD_DAYS", addDay);
				requestPr.set("ACCT_REST_ID", accountType);
				if (resetAmount != null) {
					requestPr.set("RESET_BALANCE", resetAmount);
				}
			} else {
				requestPr.setProcessCode("856135");
				// format double amount
				try {
					DecimalFormat decimalFormat = new DecimalFormat("#.#####");
					requestPr.set("CHARGE_VALUE", decimalFormat.format(changeAmount));
				} catch (Exception ex) {
					ex.printStackTrace();
					requestPr.set("CHARGE_VALUE", changeAmount);
				}
				requestPr.set("PARTY_CODE", "myvtg");
				requestPr.set("ACCT_REST_ID", accountType);
			}
			ViettelService response = interfacePr.request(requestPr);
			return response;
		}
		return null;
	}

	/**
	 * @author : TuanTM11
	 * @createDate : 08 - 06 - 2012
	 * @return : Chặn mở thuê bao Mobile, Homephone trả trước
	 * @throws Exception
	 */
	public static ViettelService blockOpenSub(Session cmPreSession, String productCode, String isdn,
			String modificationType, String oldActStatus, String numWay, String countryCode) throws Exception {

		ViettelService requestPr = new ViettelService();
		requestPr.set("productId", productCode);
		requestPr.set("MSISDN", countryCode + isdn);
		requestPr.set("CURRENT_SERVICES", CommonSub.getCurrentServices(cmPreSession, isdn));

		requestPr.set("modificationType", modificationType);
		if ("300".equals(modificationType)) {
			requestPr.set("NUM_WAY", numWay);
		} else if ("301".equals(modificationType)) {
			requestPr.set("NUM_WAY", numWay);
			requestPr.set("ACT_STATUS", oldActStatus);
		}

		ViettelService responsePr = interfacePr.request(requestPr);
		return responsePr;
	}
}
