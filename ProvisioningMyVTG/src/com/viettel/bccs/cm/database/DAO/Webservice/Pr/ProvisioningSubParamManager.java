/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.database.DAO.Webservice.Pr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.viettel.bccs.cm.BO.StockSimBean;
import com.viettel.bccs.cm.BO.SubRelProductBean;
import com.viettel.bccs.cm.common.util.pre.Common;
import com.viettel.pm.common.util.PMConstant;
import com.viettel.pm.database.DAO.PMAPI;

public class ProvisioningSubParamManager {

	public static Map getSubscriberParam(Session cmPosSession, Session imSession, Session pmSession, String isdn,
			Long subId, Long contractId, Long custId, Double deposit, String regType, Double quota, String actStatus,
			Long status, Long billCycleFrom, String busType, String subType, Long offerId, String productCode,
			String serial, String imsi, String modificationType, String countryCode) throws Exception {
		try {

			Map lstParam = new HashMap();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String regisDate = simpleDateFormat.format(new Date());

			/* sub and contract infor */
			lstParam.put("MSISDN", countryCode + isdn);
			lstParam.put("SUBID", subId);
			lstParam.put("CUSTID", custId);
			lstParam.put("CONTRACT_ID", contractId);

			if (deposit == null || deposit.equals(0D)) {
				deposit = 0D;
			}
			lstParam.put("DEPOSIT", deposit);

			lstParam.put("REG_TYPE", regType);

			if (quota == null || quota.equals(0D)) {
				quota = 0D;
			}

			lstParam.put("LIMIT_USAGE", quota);
			lstParam.put("ACT_STATUS", actStatus);
			lstParam.put("STATUS", status);
			lstParam.put("COMPLETED_DATE", regisDate);
			lstParam.put("CUST_STA_DATE", regisDate);
			lstParam.put("CONTRACT_EFFECTDATE", regisDate);
			lstParam.put("SUB_EFFECTDATE", regisDate);
			lstParam.put("EFFECT_DATE", regisDate);
			lstParam.put("BILL_CYCLE_FROM", billCycleFrom);
			lstParam.put("CONTRACT_BUS_TYPE", busType);
			lstParam.put("SUB_TYPE", subType);

			// get resource param from im
			lstParam.put("SERIAL", serial);
			lstParam.put("IMSI", imsi);

			StockSimBean stkSim = Common.getStockSimBySerialOrImsi(imSession, serial, imsi);
			if (stkSim != null) {
				lstParam.put("EKI", stkSim.getEki());
				lstParam.put("K4SNO", stkSim.getK4sno());
				lstParam.put("CARDTYPE", stkSim.getCardType());
			}

			if (offerId != null) {
				Map attributeParam = PMAPI.getAttributeParam(offerId, pmSession);
				if (attributeParam != null && !attributeParam.isEmpty()) {
					lstParam.putAll(attributeParam);
				}

				/* price_plan infor */
				Map pmParam = PMAPI.getProductOfferProvisioningParam(offerId, modificationType, PMConstant.PARAM_TYPE_1,
						pmSession);
				if (pmParam != null && !pmParam.isEmpty()) {
					lstParam.putAll(pmParam);
				}
			}

			/* vas price_plan */
			Map vasParam = new HashMap();
			List<SubRelProductBean> lstCurrVas = getListCurrVas(cmPosSession, isdn);
			if (lstCurrVas != null && lstCurrVas.size() > 0) {
				for (SubRelProductBean subRelProduct : lstCurrVas) {
					if (subRelProduct.getRelProductCode() != null
							&& !"".equals(subRelProduct.getRelProductCode().trim())
							&& subRelProduct.getIsConnected().equals(0L)) {
						Map vasPPMap = PMAPI.getVasProvisioningParam(productCode,
								subRelProduct.getRelProductCode().trim(), modificationType, PMConstant.PARAM_TYPE_1,
								pmSession);

						Map vasATMap = PMAPI.getVasAttributeParam(productCode, subRelProduct.getRelProductCode().trim(),
								pmSession);
						if (vasATMap != null && !vasATMap.isEmpty()) {
							vasPPMap.putAll(vasATMap);
						}

						if (vasPPMap != null && !vasPPMap.isEmpty()) {
							Iterator paramKey = vasPPMap.keySet().iterator();
							while (paramKey.hasNext()) {
								Object paramName = paramKey.next();
								Object paramValue = vasPPMap.get(paramName);
								if (vasParam.containsKey(paramName)) {
									vasParam.put(paramName, vasParam.get(paramName) + "_" + paramValue);
								} else {
									vasParam.put(paramName, paramValue);
								}
							}
						}
					}
				}
			}

			// remove cac tham so cung ten voi goi cuoc chinh
			if (!vasParam.isEmpty()) {
				Object[] keys = vasParam.keySet().toArray();
				for (int i = keys.length - 1; i >= 0; i--) {
					Object key = keys[i];
					if (lstParam.containsKey(key)) {
						vasParam.remove(key);
					}
				}
			}

			if (vasParam.size() > 0) {
				lstParam.put("EFFECT_DATE", regisDate);
				lstParam.putAll(vasParam);
			}

			return lstParam;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static List<SubRelProductBean> getListCurrVas(Session cmPosSession, String isdn) throws Exception {
		String sql = "select new com.viettel.bccs.cm.BO.SubRelProductBean(srp.relProductCode, srp.isConnected) from co.siten.myvtg.model.cmpos.SubRelProduct srp where subId in (select subId from co.siten.myvtg.model.cmpos.SubMb where isdn = :isdn and status = 2) and status = 1 and endDatetime is null";
		Query query = cmPosSession.createQuery(sql);
		query.setString("isdn", isdn);
		return query.list();
	}
	

}
