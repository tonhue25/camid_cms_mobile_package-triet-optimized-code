package co.siten.myvtg.dao;

import co.siten.myvtg.bean.SubMainInfoBean;
import co.siten.myvtg.bean.SubRelProductBean;
import co.siten.myvtg.bean.SubscriberSyncInfoBean;
import co.siten.myvtg.bean.VCustomerBean;
import co.siten.myvtg.model.cmpos.*;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.Tuple2;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author thomc
 *
 */
@Repository("CmposDao")
@PropertySource(value = {"classpath:database.properties"})
public class CmposDao extends AbstractCmposDao<Object> {

    private static final Logger logger = Logger.getLogger(CmposDao.class.getName());
    @Autowired
    private Environment environment;

    public AllTelServiceSubSelfcare getTelServiceSubSelfcare(String isdn) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT s FROM AllTelServiceSubSelfcare s WHERE isdn = :isdn and statusId = "
                    + Constants.SUB_MB_STATUS_ACTIVE);
            Query query = getSession().createQuery(sb.toString());
            query.setString("isdn", isdn);
            return (AllTelServiceSubSelfcare) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public SubMbPos getSubMbByIsdnAndStatus(String isdn, int status) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT DISTINCT sub FROM co.siten.myvtg.model.cmpos.SubMb sub WHERE status = :status AND isdn = :isdn");
            Query query = getSession().createQuery(sb.toString());
            query.setString("isdn", isdn);
            query.setInteger("status", status);
            return (SubMbPos) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public SubMbPos getSubMbBySerial(String serial) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT DISTINCT sub FROM co.siten.myvtg.model.cmpos.SubMb sub WHERE status =2 AND serial = :serial AND custId is null and actStatus = '03'");
            Query query = getSession().createQuery(sb.toString());
            query.setString("serial", serial);
            return (SubMbPos) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public int updateSubMb(int status, long subId) {
        StringBuilder sb = new StringBuilder(
                "UPDATE co.siten.myvtg.model.cmpos.SubMb SET status= :status Where subId= :subId");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("status", status);
        query.setLong("subId", subId);
        query.setLong("status", status);
        int result = query.executeUpdate();
        return result;
    }

    public co.siten.myvtg.model.cmpos.SubSimMb getSubSimMbBySubIdAndStatus(long subId, int subMbStatus1) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT DISTINCT sub FROM co.siten.myvtg.model.cmpos.SubSimMb sub WHERE status = :subMbStatus AND subId = :subId");
            Query query = getSession().createQuery(sb.toString());
            query.setLong("subId", subId);
            query.setInteger("subMbStatus", subMbStatus1);
            return (co.siten.myvtg.model.cmpos.SubSimMb) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public int updateSubMbByIsdn(int status, String isdn, Date date) {
        StringBuilder sb = new StringBuilder(
                "UPDATE co.siten.myvtg.model.cmpre.SubMb SET status= :status, endDatetime= :date Where isdn= :isdn");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("status", status);
        query.setDate("date", date);
        query.setString("isdn", isdn);
        int result = query.executeUpdate();
        return result;
    }

    public int updateSubMbBySubId(int status, long subId) {
        StringBuilder sb = new StringBuilder(
                "UPDATE co.siten.myvtg.model.cmpos.SubMb SET status= :status Where subId= :subId");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("status", status);
        query.setLong("subId", subId);
        int result = query.executeUpdate();
        return result;
    }

    public void updateSubIsdnMb(int subMbStatus0, long subId) {
        StringBuilder sb = new StringBuilder(
                "UPDATE co.siten.myvtg.model.cmpos.SubIsdnMb SET status= :status, endDatetime = SYSDATE Where subId= :subId");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("status", subMbStatus0);
        query.setLong("subId", subId);
        query.executeUpdate();
    }

    public Contract getContractById(Long contractId) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT contract FROM Contract contract WHERE contractId = :contractId");
            Query query = getSession().createQuery(sb.toString());
            query.setLong("contractId", contractId);
            return (Contract) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public Customer getCustomerById(Long custId) {
        StringBuilder sb = new StringBuilder("SELECT customer FROM Customer contract WHERE custId = :custId");
        Query query = getSession().createQuery(sb.toString());
        query.setLong("custId", custId);
        return (Customer) query.uniqueResult();
    }

    public ContractOffer getContractOffer(long subId) {
        StringBuilder sb = new StringBuilder("SELECT contractOffer FROM ContractOffer contractOffer WHERE "
                + "contractOfferId IN (select contractOfferId FROM OfferSub WHERE status=1 and subId = :subId) and status=1");
        Query query = getSession().createQuery(sb.toString());
        query.setLong("subId", subId);
        List<ContractOffer> rl = query.list();
        if (!CommonUtil.isEmpty(rl)) {
            return rl.get(0);
        }
        return null;
    }

    public void updateOfferSub(int subMbStatus0, long subId) {
        StringBuilder sb = new StringBuilder(
                "UPDATE co.siten.myvtg.model.cmpos.OfferSub SET status= :status Where subId= :subId");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("status", subMbStatus0);
        query.setLong("subId", subId);
        query.executeUpdate();
    }

    public OfferSub getOfferSub(long subId) {
        try {
            StringBuilder sb = new StringBuilder("SELECT contract FROM OfferSub contract WHERE subId = :subId");
            Query query = getSession().createQuery(sb.toString());
            query.setLong("subId", subId);
            return (OfferSub) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public BigDecimal getNextActionLogPrSeq() {
        try {
            String schemaCmPos = environment.getRequiredProperty("cmpos.default_schema");

            Query query = getSession()
                    .createSQLQuery("select " + schemaCmPos + ".ACTION_LOG_PR_SEQ.nextval as num from dual")
                    .addScalar("num", BigDecimalType.INSTANCE);

            return ((BigDecimal) query.uniqueResult());
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public BigDecimal getNextActionAuditSeq() {
        try {
            String schemaCmPos = environment.getRequiredProperty("cmpos.default_schema");
            Query query = getSession()
                    .createSQLQuery("select " + schemaCmPos + ".ACTION_AUDIT_SEQ.nextval as num from dual")
                    .addScalar("num", BigDecimalType.INSTANCE);

            return ((BigDecimal) query.uniqueResult());
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public String mfGetProductNameByProductCode(String productCode) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpos.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a. product_name_sc " + " FROM " + schemaDefault + ".vw_product_offer a "
                    + " WHERE a.service_type_id IN ('1', '2') AND a.product_code = :productCode");

            SQLQuery query = getSession().createSQLQuery(sb.toString());
            query.addScalar("product_name_sc", new StringType());

            query.setString("productCode", productCode);

            List<Object> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return "";
            } else {
                return rows.get(0).toString();
            }

        } catch (Exception e) {
            logger.error("error", e);
        }

        return "";
    }

    public SubMainInfoBean getCustSubInfo(String isdn) {
        try {
            String sb = "  SELECT DISTINCT new co.siten.myvtg.bean.SubMainInfoBean(s.custName, 2, s.subId , s.birthDate ) "
                    + " FROM AllTelServiceSubSelfcare s WHERE s.isdn = :isdn and statusId =2 ";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);
            return (SubMainInfoBean) query.list().get(0);
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public List<SubscriberSyncInfoBean> getSubscriberSyncInfoBean(Long subId, int dayNum) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpos.default_schema");
            StringBuilder sb = new StringBuilder(" SELECT Distinct(a.rel_product_code) as sub_service_code, "
                    + " max(a.reg_date) as reg_time, " + " max(a.end_datetime) as cancel_time, "
                    + " max(CASE WHEN (a.end_datetime is not null and  a.status = 0) THEN 0 ELSE 1 END) state "
                    + " FROM " + schemaDefault + ".sub_rel_product a " + " WHERE a.sub_id = :subId "
                    + " AND ((a.reg_date >= (SYSDATE - :dayNum)) OR (a.end_datetime >= (SYSDATE - :dayNum))) "
                    + " AND a.status = 1 AND a.is_connected = 1 " + " GROUP BY a.rel_product_code ");

            SQLQuery query = getSession().createSQLQuery(sb.toString());
            query.addScalar("sub_service_code", new StringType()).addScalar("reg_time", new DateType())
                    .addScalar("cancel_time", new DateType()).addScalar("state", new IntegerType());
            query.setLong("subId", subId);
            query.setInteger("dayNum", dayNum);

            List<Object[]> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return new ArrayList<>();
            }
            List<SubscriberSyncInfoBean> result = new ArrayList<>();
            for (Object[] row : rows) {
                SubscriberSyncInfoBean item = new SubscriberSyncInfoBean();
                item.setSubServiceCode(row[0].toString());
                item.setRegisterTime((Date) row[1]);
                item.setCancelTime((Date) row[2]);
                item.setState((Integer) row[3]);

                result.add(item);
            }

            return result;
        } catch (Exception e) {
            logger.error("error", e);
        }

        return new ArrayList<>();
    }

    public List<SubRelProductBean> getChangeFromSubRelProductTable(Date fromTime) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpos.default_schema");
            StringBuilder sb = new StringBuilder("SELECT a.sub_id," + " a.sta_datetime," + " a.end_datetime,"
                    + " a.reg_date," + " a.rel_product_code," + " a.status" + " FROM " + schemaDefault
                    + ".sub_rel_product a" + " WHERE a.is_connected = 1" + " AND (reg_date  >= :fromTime"
                    + " OR end_datetime >= :fromTime)");

            SQLQuery query = getSession().createSQLQuery(sb.toString());
            query.addScalar("sub_id", new LongType());
            query.addScalar("sta_datetime", new DateType());
            query.addScalar("end_datetime", new DateType());
            query.addScalar("reg_date", new DateType());
            query.addScalar("rel_product_code", new StringType());
            query.addScalar("status", new IntegerType());

            query.setDate("fromTime", fromTime);

            List<Object[]> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return new ArrayList<>();
            }

            List<SubRelProductBean> result = new ArrayList<>();
            for (Object[] row : rows) {
                result.add(new SubRelProductBean((Long) row[0], (Date) row[1], (Date) row[2], (Date) row[3],
                        row[4].toString(), (Integer) row[5]));
            }

            return result;
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public List<Tuple2<String, Date>> getRegisteredRelProductCode(Long subId) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpos.default_schema");
            StringBuilder sb = new StringBuilder("SELECT a.rel_product_code, a.sta_datetime FROM " + schemaDefault
                    + ".sub_rel_product a WHERE a.sub_id = :subId AND  a.status = 1 AND a.is_connected = 1");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            query.addScalar("rel_product_code", new StringType());
            query.addScalar("sta_datetime", new DateType());
            query.setLong("subId", subId);

            List<Object[]> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return new ArrayList<>();
            }
            List<Tuple2<String, Date>> result = new ArrayList<>();
            for (Object[] row : rows) {
                result.add(new Tuple2<String, Date>(row[0].toString(), (Date) row[1]));
            }

            return result;
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public String getListRegisteredServiceCodes(Long subId) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpos.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a.rel_product_code" + " FROM " + schemaDefault + ".sub_rel_product a"
                    + " WHERE a.sub_id = :subId" + " AND  a.status = 1" + " AND a.is_connected = 1");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//			query.setCacheable(true);
            query.addScalar("rel_product_code", new StringType());
            query.setLong("subId", subId);

            List<Object> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return null;
            }

            StringBuilder result = new StringBuilder("(");
            for (Object row : rows) {
                String code = (String) row;
                result.append("'" + code + "',");
            }

            // append empty object
            result.append("' ')");

            System.out.println("##############" + result.toString());
            return result.toString();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public Map<String, Date> getListRegisteredAndExpiredDateServiceCodes(Long subId) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a.rel_product_code, a.end_datetime" + " FROM " + schemaDefault + ".sub_rel_product a"
                            + " WHERE a.sub_id = :subId" + " AND  a.status = 1" + " AND a.is_connected = 1");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//            query.setCacheable(true);
            query.addScalar("rel_product_code", new StringType());
            query.addScalar("end_datetime", new DateType());
            query.setLong("subId", subId);

            List<Object[]> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return new HashMap<>();
            }

            Map<String, Date> result = new HashMap<>();
            for (Object[] row : rows) {
                result.put(row[0].toString(), (Date)row[1]);
            }

            System.out.println("##############" + result.toString());
            return result;
        } catch (Exception e) {
            logger.error("error", e);
        }

        return new HashMap<>();
    }

    // get services expired
    public String getListRegisteredServiceCodesExpired(Long subId, Date startTime, Date endTime) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpos.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a.rel_product_code" + " FROM " + schemaDefault + ".sub_rel_product a"
                    + " WHERE a.sub_id = :subId" + " AND  a.status = 0" + " AND a.is_connected = 1 "
                    + "and a.end_datetime >= :startTime and a.end_datetime < :endTime");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//			query.setCacheable(true);
            query.addScalar("rel_product_code", new StringType());
            query.setLong("subId", subId);
            query.setDate("startTime", startTime);
            query.setDate("endTime", endTime);
            List<Object> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return null;
            }

            StringBuilder result = new StringBuilder("(");
            for (Object row : rows) {
                String code = (String) row;
                result.append("'" + code + "',");
            }

            // append empty object
            result.append("' ')");

            System.out.println("##############" + result.toString());
            return result.toString();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public List<String> getRegisteredServiceCodes(Long subId) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpos.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a.rel_product_code" + " FROM " + schemaDefault + ".sub_rel_product a"
                    + " WHERE a.sub_id = :subId" + " AND  a.status = 1" + " AND a.is_connected = 1");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//			query.setCacheable(true);
            query.addScalar("rel_product_code", new StringType());
            query.setLong("subId", subId);

            List<Object> rows = query.list();
            if (CommonUtil.isEmpty(rows)) {
                return null;
            }

            List<String> result = new ArrayList<>();
            for (Object row : rows) {
                result.add((String) row);
            }

            return result;
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }
}
