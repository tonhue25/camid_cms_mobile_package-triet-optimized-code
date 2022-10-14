/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.common.util.pre;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.viettel.bccs.cm.BO.StockSimBean;

/**
 *
 * @author bongcm
 */
public class Common {

	public static StockSimBean getStockSimBySerialOrImsi(Session imSession, String serial, String imsi) {
		List param = new ArrayList();
		String sql = "Select new com.viettel.bccs.cm.BO.StockSimBean( ss.eki, ss.kind, ss.cardtype) from StockSim ss where 1 = 1 ";
		if (serial != null && !"".equals(serial)) {
			sql += " AND serial = :serial ";
			param.add(serial);
		}
		if (imsi != null && !"".equals(imsi)) {
			sql += " AND imsi = :imsi ";
			param.add(imsi);
		}

		Query query = imSession.createQuery(sql);

		if (serial != null && !"".equals(serial)) {
			query.setString("serial", serial);
		}
		if (imsi != null && !"".equals(imsi)) {
			query.setString("imsi", imsi);
		}

		List<StockSimBean> lst = query.list();
		if (lst != null && !lst.isEmpty()) {
			return lst.get(0);
		}
		return null;
	}
}
