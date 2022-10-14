///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.siten.myvtg.dao.queries;
//
//import java.io.Serializable;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.hibernate.Query;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.jdbc.Work;
//import org.hibernate.type.DateType;
//import org.hibernate.type.IntegerType;
//import org.hibernate.type.LongType;
//import org.hibernate.type.StringType;
//
//import co.siten.myvtg.bean.PostageInfoBean;
//import co.siten.myvtg.bean.SubRelProductBean;
//import co.siten.myvtg.bean.SubscriberSyncInfoBean;
//import co.siten.myvtg.util.CommonUtil;
//import co.siten.myvtg.util.Constants;
//import co.siten.myvtg.util.Tuple2;
//import co.siten.myvtg.util.Tuple6;
//import org.hibernate.HibernateException;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author LuatNC
// */
//
//@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
//public class Queries implements Serializable {
//	private static final Logger logger = Logger.getLogger(Queries.class.getName());
//	private SessionFactory sessionFactory;
//	private String defaultSchema;
//        private String rspCode = "";
//
//	public Queries(SessionFactory sessionFactory, String defaultSchema) {
//		this.sessionFactory = sessionFactory;
//		this.defaultSchema = defaultSchema;
//	}
//
////	public PostageInfoBean getPostageInfo(String isdn, int subType, Date startDate, Date endDate) {
////		endDate = CommonUtil.truncDatePlus1(endDate);
////		StringBuilder sb1 = new StringBuilder(
////				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(totalCharge)) FROM co.siten.myvtg.model.billing.VSelfcareDetailCall c "
////						+ "WHERE ( c.calledNumber= :isdn OR c.callingNumber= :isdn) and "
////						+ "c.startTime >= :startTime and c.startTime <= :endTime ");
////
////		StringBuilder sb2 = new StringBuilder(
////				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(totalCharge)) FROM co.siten.myvtg.model.billing.VSelfcareDetailSms c "
////						+ "WHERE (c.calledNumber= :isdn OR c.callingNumber= :isdn) and "
////						+ "c.startTime >= :startTime and c.startTime <= :endTime ");
////
////		StringBuilder sb3 = new StringBuilder(
////				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(totalCharge) ) FROM co.siten.myvtg.model.billing.VSelfcareDetailOther c "
////						+ "WHERE ( c.calledNumber= :isdn OR c.callingNumber= :isdn) and "
////						+ "c.startTime >= :startTime and c.startTime <= :endTime ");
////
////		Query query = getSession().createQuery(sb1.toString());
////
////		query.setString("isdn", isdn);
////		query.setDate("startTime", startDate);
////		query.setDate("endTime", endDate);
////
////		Query query2 = getSession().createQuery(sb2.toString());
////
////		query2.setString("isdn", isdn);
////		query2.setDate("startTime", startDate);
////		query2.setDate("endTime", endDate);
////
////		Query query3 = getSession().createQuery(sb3.toString());
////
////		query3.setString("isdn", isdn);
////		query3.setDate("startTime", startDate);
////		query3.setDate("endTime", endDate);
////
////		PostageInfoBean bean = new PostageInfoBean();
////		try {
////			PostageInfoBean bean1 = (PostageInfoBean) query.uniqueResult();
////			bean.setCallFee(bean1.getCallFee());
////		} catch (Exception e) {
////			logger.error("error", e);
////		}
////
////		// try {
////		// PostageInfoBean bean2 = (PostageInfoBean) query2.uniqueResult();
////		// } catch (Exception e) {
////		// logger.error("error", e);
////		// }
////		//
////		// try {
////		// PostageInfoBean bean3 = (PostageInfoBean) query3.uniqueResult();
////		// } catch (Exception e) {
////		// logger.error("error", e);
////		// }
////
////		return bean;
////	}
//
//	
////
////	public List<SubscriberSyncInfoBean> getUnitelSubscriberVASSyncInfoBean(String msisdn, int dayNum) {
////		try {
////			StringBuilder sb = new StringBuilder("SELECT t.sub_service_code, "
////					+ " TO_DATE(substr(t.action_time_id,0,25), 'YYYY/MM/DD HH24:MI:SS:SSSSS') as reg_time, "
////					+ " TO_DATE(substr(t.action_time_id,0,25), 'YYYY/MM/DD HH24:MI:SS:SSSSS') as cancel_time, "
////					+ " (case when substr(t.action_time_id,27,1) = 2 then 0 else 1 end) as state " + " FROM "
////					+ " (SELECT Distinct(v.vas_code) as sub_service_code, "
////					+ " MAX(TO_CHAR(v.action_time, 'YYYY/MM/DD HH24:MI:SS:SSSSS') || ';' || v.action_id) as action_time_id "
////					+ " FROM vas_tt.vas_use_history v " + " WHERE v.msisdn = :msisdn " + " AND v.description='SUCCESS' "
////					+ " AND v.action_time >= (SYSDATE - :dayNum) " + " GROUP BY v.vas_code) t ");
////
////			SQLQuery query = getSession().createSQLQuery(sb.toString());
////			query.addScalar("sub_service_code", new StringType()).addScalar("reg_time", new DateType())
////					.addScalar("cancel_time", new DateType()).addScalar("state", new IntegerType());
////
////			query.setString("msisdn", msisdn);
////			query.setInteger("dayNum", dayNum);
////
////			List<Object[]> rows = query.list();
////			if (CommonUtil.isEmpty(rows))
////				return new ArrayList<>();
////			List<SubscriberSyncInfoBean> result = new ArrayList<>();
////			for (Object[] row : rows) {
////				SubscriberSyncInfoBean item = new SubscriberSyncInfoBean();
////				item.setSubServiceCode(row[0].toString());
////				item.setRegisterTime((Date) row[1]);
////				item.setCancelTime((Date) row[2]);
////				item.setState((Integer) row[3]);
////
////				result.add(item);
////			}
////
////			return result;
////		} catch (Exception e) {
////			logger.error("error", e);
////		}
////
////		return new ArrayList<>();
////	}
//
//
//
////
////	public List<Tuple6<String, String, Integer, Long, Long, Long>> getRegisteredPackageData(int simType,
////			String relProCodes, String ltPackageName) {
////		try {
////			StringBuilder sb = new StringBuilder("");
////
////			if (simType == Constants.SIM_TYPE_3G) {
////				if (ltPackageName != null && !ltPackageName.isEmpty()) {
////					sb.append(
////							"SELECT '' vas_code, '' package_name, a.add_time_expire add_day_expire_rating, a.add_time_expire add_time_expire, a.data_free, a.money_fee \n"
////									+ "FROM " + defaultSchema + ".map_brand_index_product a " + "WHERE product_name='"
////									+ ltPackageName + "' ");
////				} else {
////					sb.append(
////							"SELECT b.vas_code, b.package_name, b.add_day_expire_rating, b.add_time_expire, b.data_free, b.money_fee "
////									+ " FROM " + defaultSchema + ".map_data_code b " + " WHERE b.vas_code in "
////									+ relProCodes);
////				}
////			} else if (simType == Constants.SIM_TYPE_4G) {
////				if (ltPackageName != null && !ltPackageName.isEmpty()) {
////					sb.append(
////							"SELECT '' vas_code, '' package_name, a.add_time_expire add_day_expire_rating, a.add_time_expire add_time_expire, a.data_free, a.money_fee \n"
////									+ "FROM " + defaultSchema + ".map_brand_index_product_4g a "
////									+ "WHERE product_name='" + ltPackageName + "' ");
////				} else {
////					sb.append(
////							" SELECT b.vas_code, b.package_name, b.add_day_expire_rating, b.add_time_expire, b.data_free, b.money_fee "
////									+ " FROM " + defaultSchema + ".map_data_code_4g b " + " WHERE b.vas_code in "
////									+ relProCodes);
////				}
////			} else {
////				return null;
////			}
////
////			SQLQuery query = getSession().createSQLQuery(sb.toString());
////			query.addScalar("vas_code", new StringType());
////			query.addScalar("package_name", new StringType());
////			query.addScalar("add_day_expire_rating", new IntegerType());
////			query.addScalar("add_time_expire", new LongType());
////			query.addScalar("data_free", new LongType());
////			query.addScalar("money_fee", new LongType());
////
////			List<Object[]> rows = query.list();
////			if (CommonUtil.isEmpty(rows))
////				return null;
////
////			List<Tuple6<String, String, Integer, Long, Long, Long>> result = new ArrayList<>();
////			for (Object[] row : rows) {
////				result.add(new Tuple6<String, String, Integer, Long, Long, Long>((String) row[0], (String) row[1],
////						(Integer) row[2], (Long) row[3], (Long) row[4], (Long) row[5]));
////			}
////
////			return result;
////		} catch (Exception e) {
////			logger.error("error", e);
////		}
////
////		return null;
////	}
//
//
////	public Date getLastSynSubRelProductTime() {
////		try {
////			SQLQuery query = getSession().createSQLQuery("SELECT sysdate from dual");
////			query.addScalar("sysdate", new DateType());
////			return (Date) query.list().get(0);
////		} catch (Exception e) {
////			logger.error("error", e);
////		}
////
////		return null;
////	}
//        
//        /**
//         * Call function to insert MO table
//         */
//       
//        
//}
