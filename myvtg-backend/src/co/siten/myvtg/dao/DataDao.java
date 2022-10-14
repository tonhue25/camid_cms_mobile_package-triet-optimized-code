package co.siten.myvtg.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.jdbc.Work;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.data.VSelfcareData;
import co.siten.myvtg.model.data.VSelfcareDataToBuy;
import co.siten.myvtg.util.Constants;

/**
 * 
 * @author thomc
 *
 */

@Qualifier("datatx")
@Repository("DataDao")
@PropertySource(value = { "classpath:database.properties" })
public class DataDao extends AbstractDataDao<Object> {
	private static final Logger logger = Logger.getLogger(DataDao.class.getName());
	@Autowired
	private Environment environment;

	String rspCode = "";

	public List<VSelfcareData> getVSelfcareDataByMsisdn(String msisdn) {
		StringBuilder sb = new StringBuilder(
				" SELECT s FROM VSelfcareData s WHERE s.msisdn = :msisdn and expireTime > :sysdate order by expireTime");
		Query query = getSession().createQuery(sb.toString());
		query.setString("msisdn", msisdn);
		query.setDate("sysdate", new Date());
		return query.list();
	}

	public VSelfcareDataToBuy getVSelfcareDataToBuyByName(String dataName) {
		try {
			StringBuilder sb = new StringBuilder(
					" SELECT DISTINCT s FROM VSelfcareDataToBuy s WHERE s.dataName = :dataName");
			Query query = getSession().createQuery(sb.toString());
			query.setString("dataName", dataName);
			return (VSelfcareDataToBuy) query.uniqueResult();
		} catch (Exception e) {
			logger.error("Error", e);
			return null;
		}

	}

	public List<String> getPackageNameAbleToRegisterByProductName(String productName, int simType) {
		List<String> result = new ArrayList<>();

		try {
			if (productName == null || productName.isEmpty())
				return result;
			String schemaData = environment.getRequiredProperty("schemaData.default_schema");
			StringBuilder sb = new StringBuilder();
			if (simType == Constants.SIM_TYPE_3G) {
				sb.append("SELECT package_name FROM " + schemaData + ".map_price_product_data "
						+ " WHERE product_name = :productName " + " AND status = 1 ");
			} else if (simType == Constants.SIM_TYPE_4G) {
				sb.append("SELECT package_name FROM " + schemaData + ".map_price_product_data_4g "
						+ " WHERE product_name = :productName " + " AND status = 1 ");
			} else {
				return result;
			}

			SQLQuery query = getSession().createSQLQuery(sb.toString());
			query.addScalar("package_name", new StringType());
			query.setString("productName", productName);

			return query.list();
		} catch (Exception e) {
			logger.error("error", e);
		}

		return result;
	}

	public String callSelcareFakeMO(String srcMsisdn, String desMsisdn, String msg) {
		String schemaDefault = environment.getRequiredProperty("data.default_schema");

		getSession().doWork(new Work() {
			public void execute(Connection connection) throws SQLException {

				CallableStatement call = connection
						.prepareCall("{? = call " + schemaDefault + ".selfcare_fake_mo(?, ?, ?) }");
				try {
					call.registerOutParameter(1, Types.VARCHAR); // or whatever
																	// it

					call.setString(2, srcMsisdn);
					call.setString(3, desMsisdn);
					call.setString(4, msg);
					call.execute();

					rspCode = call.getString(1);
				} catch (Exception e) {
					logger.error("", e);
				} finally {
					call.close();
				}
			}
		});

		return rspCode;
	}

}
