package co.siten.myvtg.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.controller.AccountController;
import co.siten.myvtg.model.myvtg.Sub;
import co.siten.myvtg.model.payment.DebitSub;
import co.siten.myvtg.util.CommonUtil;

/**
 * 
 * @author thomc
 *
 */
@Repository("PaymentDao")
@PropertySource(value = { "classpath:database.properties" })
public class PaymentDao extends AbstractPaymentDao<Object> {
	private static final Logger logger = Logger.getLogger(AccountController.class);
	private BigDecimal prepost;
	@Autowired
	private Environment environment;

	public BigDecimal callGetHotChargeSub(long subId) {

		logger.info("prepost start ===================>");
		String schemaCmPos = environment.getRequiredProperty("payment.default_schema");
		getSession().doWork(new Work() {
			public void execute(Connection connection) throws SQLException {

				CallableStatement call = connection
						.prepareCall("{ call " + schemaCmPos + ".PCK_SELFCARE_UTILS.GET_HOT_CHARGE_SUB(?, ?, ?) }");
				try {
					call.registerOutParameter(2, Types.FLOAT); // or whatever it
																// is
					call.registerOutParameter(3, Types.VARCHAR);
					call.setLong(1, subId);
					call.execute();
					prepost = BigDecimal.valueOf(call.getFloat(2));
					logger.info("prepost" + prepost);
				} catch (Exception e) {
					logger.error("", e);
				} finally {
					call.close();
				}

			}
		});
		// logger.info("prepost" + prepost);
		return prepost;
	}

	public BigDecimal getDevPostByIsdn(String isdn) {
		StringBuilder sb = new StringBuilder("SELECT nvl(sum(debitAmountTax),0)  FROM VSelfcarePrepaidCall2 "
				+ "WHERE appliedCycle = :sysdate and isdn = :isdn GROUP BY subId");
		Query query = getSession().createQuery(sb.toString());
		query.setString("isdn", isdn);
		query.setDate("sysdate", CommonUtil.getFirstDateOfCurrent());
		prepost = (BigDecimal) query.uniqueResult();
		return prepost;
	}

	public BigDecimal getDebPreMonthPostByIsdn(String isdn) {
		StringBuilder sb = new StringBuilder("SELECT DISTINCT totCharge FROM VSelfcarePrepaidCall1 "
				+ "WHERE billCycle = :sysdate and isdn = :isdn");
		Query query = getSession().createQuery(sb.toString());
		query.setString("isdn", isdn);
		query.setDate("sysdate", CommonUtil.getFirstDateOfPreviousMonth());
		return (BigDecimal) query.uniqueResult();
	}

	public BigDecimal getDataRemainForPostPaidMetfone(long subId, List<String> productCodeList) {

		StringBuilder sb = new StringBuilder(
				"select SUM(TOTAL_AMOUNT-USED_AMOUNT)/(1024*1024) from bccs_rating.rt_account_benefit WHERE PAID_SUB_ID =:subId "
						+ " and PRICE_PLAN_CODE IN (:productCodeList)");
		// StringBuilder sb = new StringBuilder(
		// "select PRICE_PLAN_CODE, SUM(TOTAL_AMOUNT-USED_AMOUNT) from
		// bccs_rating.rt_account_benefit WHERE PAID_SUB_ID =:subId and
		// PRICE_PLAN_CODE IN (:productCodeList) GROUP BY PRICE_PLAN_CODE");
		Query query = getSession().createSQLQuery(sb.toString());
		query.setLong("subId", subId);
		query.setParameterList("productCodeList", productCodeList);
		BigDecimal data = (BigDecimal) query.uniqueResult();
		// return (BigDecimal) query.uniqueResult();
		return data.setScale(0, RoundingMode.CEILING);
	}

	public BigDecimal getDataRemainForPostPaid(long subId, String productCodeList) {
		BigDecimal data = BigDecimal.ZERO;
		try {

			if (productCodeList == null || productCodeList.isEmpty()) {
				productCodeList = "(' ')";
			}

			StringBuilder sb = new StringBuilder(
					"select SUM(TOTAL_AMOUNT-USED_AMOUNT)/(1024*1024) from bccs_rating.rt_account_benefit WHERE PAID_SUB_ID =:subId "
							+ " and PRICE_PLAN_CODE IN " + productCodeList);
			// StringBuilder sb = new StringBuilder(
			// "select PRICE_PLAN_CODE, SUM(TOTAL_AMOUNT-USED_AMOUNT) from
			// bccs_rating.rt_account_benefit WHERE PAID_SUB_ID =:subId and
			// PRICE_PLAN_CODE IN (:productCodeList) GROUP BY PRICE_PLAN_CODE");
			Query query = getSession().createSQLQuery(sb.toString());
			query.setLong("subId", subId);
			// query.setParameterList("productCodeList", productCodeList);
			data = (BigDecimal) query.uniqueResult();

			// return (BigDecimal) query.uniqueResult();
		} catch (Exception ex) {
			throw ex;
		}
		return data.setScale(0, RoundingMode.CEILING);
	}

	public List<Object[]> getListDataRemainForPostPaidMetfone(long subId, List<String> productCodeList) {
		if (CommonUtil.isEmpty(productCodeList))
			return null;
		StringBuilder sb = new StringBuilder(
				"select PRICE_PLAN_CODE, SUM(TOTAL_AMOUNT-USED_AMOUNT)/(1024*1024) from bccs_rating.rt_account_benefit WHERE PAID_SUB_ID =:subId and PRICE_PLAN_CODE IN (:productCodeList) GROUP BY PRICE_PLAN_CODE");
		Query query = getSession().createSQLQuery(sb.toString());
		query.setLong("subId", subId);
		query.setParameterList("productCodeList", productCodeList);
		List<Object[]> data = query.list();
		return data;
	}

	public List<Object[]> getListDataRemainForPostPaid(long subId, String productCodeList) {
		if (productCodeList == null || productCodeList.isEmpty()) {
			productCodeList = "(' ')";
		}
		StringBuilder sb = new StringBuilder(
				"select PRICE_PLAN_CODE, SUM(TOTAL_AMOUNT-USED_AMOUNT)/(1024*1024) from bccs_rating.rt_account_benefit WHERE PAID_SUB_ID =:subId and "
						+ " PRICE_PLAN_CODE IN " + productCodeList + " GROUP BY PRICE_PLAN_CODE");
		Query query = getSession().createSQLQuery(sb.toString());
		query.setLong("subId", subId);
		// query.setParameterList("productCodeList", productCodeList);
		List<Object[]> data = query.list();
		return data;
	}

	public DebitSub getDevPostByIsdn(Sub sub) {
		try {
			StringBuilder sb = new StringBuilder("SELECT ds FROM DebitSub ds "
					+ " WHERE ds.subId = :subId and TO_CHAR(ds.billCycle,'MM/YYYY') = TO_CHAR(sysdate,'MM/YYYY') ");
			Query query = getSession().createQuery(sb.toString());
			query.setLong("subId", sub.getSubId());
			return (DebitSub) query.uniqueResult();
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}

		return null;
	}

}
