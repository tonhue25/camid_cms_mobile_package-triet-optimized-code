package co.siten.myvtg.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.bean.VCustomerBean;
import co.siten.myvtg.util.CommonUtil;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.type.DateType;
import org.hibernate.type.StringType;

/**
 * 
 * @author thomc
 *
 */
@Repository("LoyaltyDao")
@Qualifier("loytaltytx")

public class LoyaltyDao extends AbstractLoyaltyDao<Object> {
    
    	private static final Logger logger = Logger.getLogger(LoyaltyDao.class.getName());
    
	@Autowired
	private Environment environment;
	        

	public VCustomerBean getVCustomerByIsdn(String isdn) {

		try {
			String schemaDefault = environment.getRequiredProperty("loyalty.default_schema");
			StringBuilder sb = new StringBuilder(
					"SELECT  a.cus_name, a.rank, a.contact, a.expired_date, a.url \n" +
							"FROM " + schemaDefault + ".scan_vip_his a WHERE (a.contact = :contact2 or a.contact = :contact)  \n" );
			SQLQuery query = getSession().createSQLQuery(sb.toString());
			query.addScalar("cus_name", new StringType());
			query.addScalar("rank", new StringType());
			query.addScalar("contact", new StringType());
			query.addScalar("expired_date", new StringType());
			query.addScalar("url", new StringType());
			query.setString("contact", isdn);
			query.setString("contact2", "0"+isdn);

			List<Object[]> rows = query.list();
			if (CommonUtil.isEmpty(rows)) {
				return null;
			}

			VCustomerBean cus = new VCustomerBean();
			cus.setCusName(rows.get(0)[0].toString());
			cus.setRank(rows.get(0)[1].toString());
			cus.setContact(rows.get(0)[2].toString());
			cus.setExpiredDate(rows.get(0)[3].toString());
			cus.setUrl(rows.get(0)[4].toString());
			return cus;
		}
		catch (Exception e) {
			logger.error("error", e);
		}
		return null;
	}
}
