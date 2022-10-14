/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.database.DAO.Webservice.Pr;

import com.viettel.bccs.cm.common.util.pre.CommonSub;
import com.viettel.common.ViettelService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import org.hibernate.Session;

public class ProvisioningV2 {

	private static InterfacePr interfacePr = new InterfacePr();

	public static ViettelService activeSubscriber(Session cmPosSession, Session imSession, Session pmSession,
			String isdn, Long subId, Long contractId, Long custId, Double deposit, String regType, Double quota,
			String actStatus, Long status, Long billCycleFrom, String busType, String subType, Long offerId,
			String productCode, String serial, String imsi, String countryCode) throws Exception {
		ViettelService requestPr = new ViettelService();
		requestPr.set("productId", productCode);
		requestPr.set("modificationType", "0");

		// Lấy ra các tham số đấu nối của thuê bao
		Map lstParam = ProvisioningSubParamManager.getSubscriberParam(cmPosSession, imSession, pmSession, isdn, subId,
				contractId, custId, deposit, regType, quota, actStatus, status, billCycleFrom, busType, subType,
				offerId, productCode, serial, imsi, "0", countryCode);

		if (lstParam != null && !lstParam.isEmpty()) {
			// Dau kem KM
			// lstParam = ProvisioningSubParamManager.getParamInOCS(lstParam,
			// sub, cmPosSession);

			// // Tham so 3GOW - 3GVPN
			// if (sub.getProductCode() != null) {
			// if (Common.is3GOWProductCode(sub.getProductCode())) {
			// requestPr.set("PROFILE", Constant.PROFILE_3GOW);
			// }
			// }

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

			return interfacePr.request(requestPr);
		}

		return null;
	}

	public static ViettelService cancelSubscriber(Session cmPosSession, String isdn, String productCode, String imsi, String countryCode)
			throws Exception {
		ViettelService requestPr = new ViettelService();
		requestPr.set("productId", productCode);
		requestPr.set("modificationType", "1");
		requestPr.set("MSISDN", countryCode + isdn);

		requestPr.set("IMSI", imsi);

		requestPr.set("CURRENT_SERVICES", CommonSub.getCurrentServices(cmPosSession, isdn));

		// Get expire_date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		requestPr.set("EXPIRE_DATE", simpleDateFormat.format(new Date()));

		return interfacePr.request(requestPr);
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

	public static ViettelService blockOpenSubscriber(Session cmPosSession, String isdn, String productCode,
			String actStatus, String modificationType, String numWay, String countryCode) throws Exception {

		ViettelService requestPr = new ViettelService();
		requestPr.set("productId", productCode);
		requestPr.set("modificationType", modificationType);
		requestPr.set("MSISDN", countryCode + isdn);
		requestPr.set("ACT_STATUS", actStatus);
		requestPr.set("CURRENT_SERVICES", CommonSub.getCurrentServices(cmPosSession, isdn));

		requestPr.set("NUM_WAY", numWay);
		requestPr.set("BLOCK_TYPE", "KHYC");
		// Get effective_date
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		requestPr.set("EFFECTIVE_DATE", simpleDateFormat.format(new Date()));

		return interfacePr.request(requestPr);
	}
}
