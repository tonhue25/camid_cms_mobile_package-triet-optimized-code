/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.common.util.pre;

import com.viettel.bccs.cm.BO.StockSimBean;
import com.viettel.bccs.cm.BO.SubRelProductBean;
import com.viettel.pm.common.util.PMConstant;
import com.viettel.pm.database.DAO.PMAPI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

/**
 *
 * @author bongcm
 */
public class CommonSub {

	public static String getCurrentServices(Session cmPreSession, String isdn) throws Exception {
		StringBuffer currentServices = new StringBuffer();
		try {
			String sql = "select relProductCode from SubRelProduct subRelProduct where subId in (select subId from SubMb where isdn = :isdn and status = 2) and status = 1 and endDatetime is null";
			Query query = cmPreSession.createQuery(sql);
			query.setString("isdn", isdn);
			List<String> lstRelProductCode = query.list();
			if (lstRelProductCode != null && lstRelProductCode.size() > 0) {
				for (int i = 0; i < lstRelProductCode.size(); i++) {
					if (lstRelProductCode.get(i) != null && !"".equals(lstRelProductCode.get(i).toString().trim())) {
						if (i == lstRelProductCode.size() - 1) {
							currentServices.append(lstRelProductCode.get(i).toString().trim());
						} else {
							currentServices.append(lstRelProductCode.get(i).toString().trim()).append("-");
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return currentServices.toString();
	}

	public static Map getSubscriberParam(Session cmPreSession, Session imSession, Session pmSession, String isdn,
			String serial, String imsi, Long offerId, String productCode, String modificationType, String countryCode)
			throws Exception {
		try {

			// get param from cm
			Map lstParam = new HashMap();
			lstParam.put("MSISDN", countryCode + isdn);

			// get resource from im
			lstParam.put("SERIAL", serial);
			lstParam.put("IMSI", imsi);

			StockSimBean stkSim = Common.getStockSimBySerialOrImsi(imSession, serial, imsi);
			if (stkSim != null) {
				lstParam.put("EKI", stkSim.getEki());
				lstParam.put("K4SNO", stkSim.getK4sno());
				lstParam.put("CARDTYPE", stkSim.getCardType());
			}

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

			/* vas price_plan */
			Map vasParam = new HashMap();
			List<SubRelProductBean> lstCurrVas = getListCurrVas(cmPreSession, isdn);
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
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String effectDate = simpleDateFormat.format(new Date());

				lstParam.put("EFFECT_DATE", effectDate);
				lstParam.putAll(vasParam);
			}

			return lstParam;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static List getListCurrVas(Session cmPreSession, String isdn) throws Exception {
		try {
			String sql = "select new com.viettel.bccs.cm.BO.SubRelProductBean(srp.relProductCode, srp.isConnected) from co.siten.myvtg.model.cmpre.SubRelProduct srp where srp.subId in (select sub.subId from co.siten.myvtg.model.cmpre.SubMb sub where sub.isdn = :isdn and sub.status = 2) and srp.status = 1 and srp.endDatetime is null";
			Query query = cmPreSession.createQuery(sql);
			query.setString("isdn", isdn);
			String querStr= query.getQueryString();
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
