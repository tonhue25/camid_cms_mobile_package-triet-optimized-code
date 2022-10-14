package co.siten.myvtg.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.bean.IsdnToBuyBean;
import co.siten.myvtg.model.sm.StockSim;
import co.siten.myvtg.util.Constants;

/**
 * 
 * @author thomc
 *
 */

@Qualifier("smtx")
@Repository("SmDao")
public class SmDao extends AbstractSmDao< Object> {
	public List<IsdnToBuyBean> getIsdnToBuy(int subType, int pageSize, int page, BigDecimal minPrice,
			BigDecimal maxPrice, String isdnPattern) {
		String table;
		if (subType == Constants.SUBTYPE_TRATRUOC) {
			table = "VSelfcareStockIsdnPrep";
		} else {
			table = "VSelfcareStockIsdnPop";
		}
		if (page == 0)
			page = 1;
		StringBuilder sb = new StringBuilder(
				"SELECT new co.siten.myvtg.bean.IsdnToBuyBean(s.isdn, s.price) FROM " + table + " s WHERE 1=1 ");

		if (minPrice != null) {
			sb.append("AND s.price >= :minPrice ");
		}

		if (maxPrice != null) {
			sb.append("AND s.price <= :maxPrice ");
		}

		if (isdnPattern != null) {
			sb.append("AND s.isdn like :isdnPattern ");
		}

		Query query = getSession().createQuery(sb.toString());

		if (minPrice != null) {
			query.setBigDecimal("minPrice", minPrice);
		}

		if (maxPrice != null) {
			query.setBigDecimal("maxPrice", maxPrice);
		}

		if (isdnPattern != null) {
			isdnPattern = isdnPattern.replace("*", "%");
			if (!isdnPattern.contains("*")) {
				isdnPattern = "%" + isdnPattern + "%";
			}
			query.setString("isdnPattern", isdnPattern);
		}

		query.setMaxResults(pageSize);
		query.setFirstResult((page - 1) * pageSize);
		return query.list();
	}

	public void updateStockIsdnMobile(int status, int ownType, BigDecimal owner_id, String isdn) {
		StringBuilder sb = new StringBuilder(
				"UPDATE StockIsdnMobile SET status= :status, ownerType  = :ownerType, ownerId= :ownerId Where isdn= :isdn");
		Query query = getSession().createQuery(sb.toString());
		query.setInteger("status", status);
		query.setInteger("ownerType", ownType);
		query.setBigDecimal("ownerId", owner_id);
		query.setString("isdn", isdn);
		query.executeUpdate();
	}

	public void updateStatusStockIsdnMobile(int status, String isdnToBuy) {
		StringBuilder sb = new StringBuilder("UPDATE StockIsdnMobile SET status= :status Where isdn= :isdn");
		Query query = getSession().createQuery(sb.toString());
		query.setInteger("status", status);
		query.setString("isdn", isdnToBuy);
		query.executeUpdate();

	}

	public StockSim getStockSimBySerial(String serial) {
		StringBuilder sb = new StringBuilder("SELECT DISTINCT ss FROM StockSim ss Where serial= :serial");
		Query query = getSession().createQuery(sb.toString());
		query.setString("serial", serial);
		return (StockSim) query.uniqueResult();
	}

}
