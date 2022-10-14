/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dao;

import co.siten.myvtg.bean.IsdnInfoBean;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daibq
 */
@Repository("PromoReportAppDao")
@PropertySource(value = {"classpath:database.properties"})
public class PromoReportAppDao extends AbstractPromoReportAppDao<Object> {

    private static final Logger logger = Logger.getLogger(PromoReportAppDao.class.getName());
    private StringBuffer br = new StringBuffer();

    /**
     *
     * getDonateEmoney
     *
     * @param maxResult
     * @param currency
     * @return
     */
    public List<Object> getDonateEmoney(int maxResult, String currency) {
        logger.info("Start getDonateEmoney off PromoReportAppDao ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ROW_NUMBER() OVER (ORDER BY ACTIVE_DATE DESC) sn, MSISDN isdn, DONATE amount, ");
            sql.append("'");
            sql.append(currency);
            sql.append("'");
            sql.append(" currency ");
            sql.append(" FROM bankplus.sub_emoney_donate ");
            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.addScalar("sn", new StringType())
                    .addScalar("isdn", new StringType())
                    .addScalar("amount", new StringType())
                    .addScalar("currency", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(IsdnInfoBean.class));
            query.setMaxResults(maxResult);
            return query.list();
        } catch (Exception ex) {
            logger.info("Error getDonateEmoney " + ex);
        }
        return new ArrayList<>();
    }

    /**
     *
     * getTotalDonateEmoney
     *
     * @return
     */
    public Double getTotalDonateEmoney() {
        logger.info("Start getTotalDonateEmoney off PromoReportAppDao ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT NVL(SUM(TO_NUMBER(DONATE)),0) FROM bankplus.sub_emoney_donate");
            Query query = getSession().createSQLQuery(sql.toString());
            return Double.parseDouble(query.list().get(0).toString());
        } catch (Exception ex) {
            logger.info("Error getTotalDonateEmoney " + ex);
        }
        return 0D;
    }

    /**
     *
     * getDonateMocha
     *
     * @param maxResult
     * @param currency
     * @return
     */
    public List<Object> getDonateMocha(int maxResult, String currency) {
        logger.info("Start getDonateMocha off PromoReportAppDao ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ROW_NUMBER() OVER (ORDER BY ACTIVE_DATE DESC) sn, MSISDN isdn, DONATE amount, ");
            sql.append("'");
            sql.append(currency);
            sql.append("'");
            sql.append(" currency ");
            sql.append(" FROM cm_pre2.sub_mocha_donate ");
            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.addScalar("sn", new StringType())
                    .addScalar("isdn", new StringType())
                    .addScalar("amount", new StringType())
                    .addScalar("currency", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(IsdnInfoBean.class));
            query.setMaxResults(maxResult);
            return query.list();
        } catch (Exception ex) {
            logger.info("Error getDonateMocha " + ex);
        }
        return new ArrayList<>();
    }

    /**
     *
     * getTotalDonateMocha
     *
     * @param valueDonate
     * @return
     */
    public Double getTotalDonateMocha(Double valueDonate) {
        logger.info("Start getTotalDonateMocha off PromoReportAppDao ");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select COUNT(1) from cm_pre2.sub_mocha");
            Query query = getSession().createSQLQuery(sql.toString());
            return Double.parseDouble(query.list().get(0).toString()) * valueDonate;
        } catch (Exception ex) {
            logger.info("Error getTotalDonateMocha " + ex);
        }
        return 0D;
    }

    @Override
    public Object get(Class cls, Long id) throws Exception {
        return super.get(cls, id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long insert(Object entity) {
        return super.insert(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String save(Object entity) {
        return super.save(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object entity) {
        super.delete(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object entity) {
        super.update(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(Object entity) {
        super.persist(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Session getSession() {
        return super.getSession(); //To change body of generated methods, choose Tools | Templates.
    }

}
