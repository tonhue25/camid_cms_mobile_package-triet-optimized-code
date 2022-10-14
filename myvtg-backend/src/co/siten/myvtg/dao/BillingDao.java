package co.siten.myvtg.dao;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import co.siten.myvtg.bean.PostageInfoBean;

/**
 * 
 * @author thomc
 *
 */
@Repository("BillingDao")
@PropertySource(value = {"classpath:database.properties"})
public class BillingDao extends AbstractBillingDao<Object> {
	@Autowired
	private Environment environment;
	private static final Logger logger = Logger.getLogger(BillingDao.class);
	private BigDecimal prepost;

	public PostageInfoBean getPostageInfo(String isdn, int subType,
			Date startDate, Date endDate, String countryCode) {
		PostageInfoBean bean = new PostageInfoBean();

		// CALL
		StringBuilder sb1 = new StringBuilder(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(0, COUNT(c.startTime), SUM(totalCharge)) FROM co.siten.myvtg.model.billing.VSelfcareDetailCall c "
						+ " WHERE ( c.callingNumber= :isdn OR c.callingNumber= :msisdn) "
						+ " AND c.startTime >= :startTime AND c.startTime <= :endTime ");
		Query query = getSession().createQuery(sb1.toString());
		query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startDate);
		query.setDate("endTime", endDate);

		try {
			PostageInfoBean bean1 = (PostageInfoBean) query.uniqueResult();
			if (bean1 != null) {
				bean.setCallFee(bean1.getCallFee());
				bean.setCallRc(bean1.getCallRc());
			}
		} catch (Exception e) {
			logger.error("error", e);
		}

		// SMS
		StringBuilder sb2 = new StringBuilder(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(1, COUNT(c.startTime), SUM(totalCharge)) FROM co.siten.myvtg.model.billing.VSelfcareDetailSms c "
						+ " WHERE ( c.callingNumber= :isdn OR c.callingNumber= :msisdn) "
						+ " AND c.startTime >= :startTime AND c.startTime <= :endTime ");

		Query query2 = getSession().createQuery(sb2.toString());
		query2.setString("isdn", isdn);
		query2.setString("msisdn", countryCode + isdn);
		query2.setDate("startTime", startDate);
		query2.setDate("endTime", endDate);

		try {
			PostageInfoBean bean2 = (PostageInfoBean) query2.uniqueResult();
			if (bean2 != null) {
				bean.setSmsFee(bean2.getSmsFee());
				bean.setSmsRc(bean2.getSmsRc());
			}
		} catch (Exception e) {
			logger.error("error", e);
		}

		// OTHERS
		StringBuilder sb3 = new StringBuilder(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(2, COUNT(c.startTime), SUM(totalCharge)) FROM co.siten.myvtg.model.billing.VSelfcareDetailOther c "
						+ " WHERE ( c.callingNumber= :isdn OR c.callingNumber= :msisdn) "
						+ " AND c.startTime >= :startTime AND c.startTime <= :endTime ");

		Query query3 = getSession().createQuery(sb3.toString());

		query3.setString("isdn", isdn);
		query3.setString("msisdn", countryCode + isdn);
		query3.setDate("startTime", startDate);
		query3.setDate("endTime", endDate);

		try {
			PostageInfoBean bean3 = (PostageInfoBean) query3.uniqueResult();
			if (bean3 != null) {
				bean.setOtherFee(bean3.getOtherFee());
				bean.setOtherRc(bean3.getOtherRc());
			}
		} catch (Exception e) {
			logger.error("error", e);
		}

		return bean;
	}

	public BigDecimal callGetHotChargeSub(long subId) {
		String schemaCmPos =
				environment.getRequiredProperty("billing.default_schema");
		getSession().doWork(new Work() {
			public void execute(Connection connection) throws SQLException {

				CallableStatement call = connection.prepareCall("{? = call "
						+ schemaCmPos
						+ ".view_hot_charge_pkg.get_hot_charge_follow_id(?, ?, ?) }");
				try {
					call.registerOutParameter(1, Types.FLOAT); // or whatever it

					call.setLong(2, subId);
					call.setLong(3, 1);
					call.setLong(4, 0);
					call.execute();
					prepost = BigDecimal.valueOf(call.getFloat(1));
				} catch (Exception e) {
					logger.error("", e);
				} finally {
					call.close();
				}

			}
		});
		return prepost;
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoCall(
			String isdn, Date startTime, Date endTime, String countryCode,
			int size, int page, String order) {

		StringBuilder sb = new StringBuilder(
				"SELECT new co.siten.myvtg.bean.PostageDetailInfoBean(s.calledNumber , "
						+ " 0, s.startTime, s.duration, s.totalCharge) "
						+ " FROM co.siten.myvtg.model.billing.VSelfcareDetailCall s "
						+ " WHERE (s.callingNumber= :isdn OR s.callingNumber= :msisdn) and s.startTime >= :startTime and s.startTime <= :endTime "
						+ " ORDER BY s.startTime " + order + " ");
		Query query = getSession().createQuery(sb.toString());
		query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);
		query.setFirstResult((page - 1) * size);
		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}

		return query.list();
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoSms(
			String isdn, Date startTime, Date endTime, String countryCode,
			int size, int page, String order) {

		StringBuilder sb = new StringBuilder(
				"SELECT new co.siten.myvtg.bean.PostageDetailInfoBean(s.calledNumber , "
						+ " 0, s.startTime, s.duration, s.totalCharge) "
						+ " FROM co.siten.myvtg.model.billing.VSelfcareDetailSms s "
						+ " WHERE (s.callingNumber= :isdn OR s.callingNumber= :msisdn) and s.startTime >= :startTime and s.startTime <= :endTime "
						+ " ORDER BY s.startTime " + order + " ");
		Query query = getSession().createQuery(sb.toString());
		query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);
		query.setFirstResult((page - 1) * size);
		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}

		return query.list();
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoOther(
			String isdn, Date startTime, Date endTime, String countryCode,
			int size, int page, String order) {

		StringBuilder sb = new StringBuilder(
				"SELECT new co.siten.myvtg.bean.PostageDetailInfoBean(s.calledNumber , "
						+ " 0, s.startTime, s.duration, s.totalCharge) "
						+ " FROM co.siten.myvtg.model.billing.VSelfcareDetailOther s "
						+ " WHERE (s.callingNumber= :isdn OR s.callingNumber= :msisdn) and s.startTime >= :startTime and s.startTime <= :endTime "
						+ " ORDER BY s.startTime " + order + " ");
		Query query = getSession().createQuery(sb.toString());
		query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);
		query.setFirstResult((page - 1) * size);
		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}

		return query.list();
	}
}
