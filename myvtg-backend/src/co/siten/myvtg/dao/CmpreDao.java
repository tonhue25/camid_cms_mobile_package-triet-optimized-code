package co.siten.myvtg.dao;

import java.util.*;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import co.siten.myvtg.model.cmpre.SubMb;
import co.siten.myvtg.bean.AllCustSubForSelfcareBean;
import co.siten.myvtg.bean.CamIdDetailBean;
import co.siten.myvtg.bean.HistoryChargeDataDetailsBean;
import co.siten.myvtg.bean.HistoryChargeDetailsBean;
import co.siten.myvtg.bean.HistoryChargeVasDetailsBean;
import co.siten.myvtg.bean.IsdnInfoBean;
import co.siten.myvtg.bean.SubMainInfoBean;
import co.siten.myvtg.bean.SubRelProductBean;
import co.siten.myvtg.bean.SubscriberSyncInfoBean;
import co.siten.myvtg.dto.InviteGameDTO;
import co.siten.myvtg.dto.SubMbDto;

import co.siten.myvtg.model.cmpre.SubSimMb;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.Tuple2;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import javax.print.Doc;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.hibernate.type.LocaleType;
import org.hibernate.type.SerializableToBlobType;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author thomc
 *
 */
@Repository("CmpreDao")
@PropertySource(value = {"classpath:database.properties"})
public class CmpreDao extends AbstractCmpreDao<Object> {

    private static final Logger logger = Logger.getLogger(CmpreDao.class.getName());
    @Autowired
    private Environment environment;

    public AllCustSubForSelfcareBean getCustSubForSelfcare(String isdn) {
        StringBuilder sb = new StringBuilder(
                "SELECT new co.siten.myvtg.bean.AllCustSubForSelfcareBean(s.subId, s.isdn, s.custId, s.custName, s.custStatus,"
                + "	s.offerId, s.productId, s.status, s.statusId, s.actStatus, s.actStatusBits,	"
                + "s.service, s.serviceType, s.lastNumber, s.staDatetime, s.endDatetime, "
                + "s.imsi,s.serial, s.regType, s.promotionCode, s.address, s.idNo, "
                + "s.idIssueDate,s.idIssuePlace, s.birthDate, s.gender, s.userCreated,"
                + " s.shopCode,s.createDatetime, s.custAddress, s.productCode, s.idType, "
                + "s.areaCode,s.provinceCode, s.districtCode, s.precinctCode) "
                + "FROM AllCustSubForSelfcare s WHERE isdn = :isdn and statusId ="
                + Constants.SUB_MB_STATUS_ACTIVE);
        Query query = getSession().createQuery(sb.toString());
        query.setString("isdn", isdn);
//		return (AllCustSubForSelfcareBean) query .uniqueResult();
        if (query.list().size() > 0) {
            return (AllCustSubForSelfcareBean) query.list().get(0);
        } else {
            return null;
        }
    }

    public AllCustSubForSelfcareBean getCustSubForSelfcare(String isdn, String status) {
        StringBuilder sb = new StringBuilder(
                "SELECT new co.siten.myvtg.bean.AllCustSubForSelfcareBean(s.subId, s.isdn, s.custId, s.custName, s.custStatus,"
                + "	s.offerId, s.productId, s.status, s.statusId, s.actStatus, s.actStatusBits,	"
                + "s.service, s.serviceType, s.lastNumber, s.staDatetime, s.endDatetime, "
                + "s.imsi,s.serial, s.regType, s.promotionCode, s.address, s.idNo, "
                + "s.idIssueDate,s.idIssuePlace, s.birthDate, s.gender, s.userCreated,"
                + " s.shopCode,s.createDatetime, s.custAddress, s.productCode, s.idType, "
                + "s.areaCode,s.provinceCode, s.districtCode, s.precinctCode)  FROM AllCustSubForSelfcare s WHERE isdn = :isdn and status = :status");
        Query query = getSession().createQuery(sb.toString());
        query.setString("isdn", isdn);
        query.setString("status", status);
//		return (AllCustSubForSelfcareBean) query.uniqueResult();
        if (query.list().size() > 0) {
            return (AllCustSubForSelfcareBean) query.list().get(0);
        } else {
            return null;
        }

    }

    public SubMb getSubMbByIsdnAndStatus(String isdn, int status) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT DISTINCT sub FROM co.siten.myvtg.model.cmpre.SubMb sub WHERE status = :status AND isdn = :isdn");
            Query query = getSession().createQuery(sb.toString());
            query.setString("isdn", isdn);
            query.setInteger("status", status);
            return (SubMb) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public SubMb getSubMbBySerial(String serial) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT DISTINCT sub FROM co.siten.myvtg.model.cmpre.SubMb sub WHERE serial = :serial  and actStatus = :actStatus");
            Query query = getSession().createQuery(sb.toString());
            query.setString("serial", serial);
            query.setString("actStatus", "03");
            return (SubMb) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
        }

        return null;
    }

    public int updateSubMbBySubId(int status, long subId) {
        StringBuilder sb = new StringBuilder(
                "UPDATE co.siten.myvtg.model.cmpre.SubMb SET status= :status Where subId= :subId");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("status", status);
        query.setLong("subId", subId);
        int result = query.executeUpdate();
        return result;
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

    public void updateSubIsdnMb(int subMbStatus0, long subId) {
        StringBuilder sb = new StringBuilder(
                "UPDATE co.siten.myvtg.model.cmpre.SubIsdnMb SET status= :status, endDatetime = SYSDATE Where subId= :subId");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("status", subMbStatus0);
        query.setLong("subId", subId);
        query.executeUpdate();
    }

    public SubSimMb getSubSimMbBySubIdAndStatus(long subId, int subMbStatus1) {
        try {
            StringBuilder sb = new StringBuilder(
                    "SELECT sub FROM co.siten.myvtg.model.cmpre.SubSimMb sub WHERE status = :subMbStatus AND subId = :subId");
            Query query = getSession().createQuery(sb.toString());
            query.setLong("subId", subId);
            query.setInteger("subMbStatus", subMbStatus1);
            return (SubSimMb) query.uniqueResult();
        } catch (Exception e) {
            logger.info("error", e);
        }

        return null;
    }

    public String getActStatus(String isdn) {
        try {
            StringBuilder sb = new StringBuilder(
                    "Select DISTINCT s.actStatus from co.siten.myvtg.model.cmpre.SubMb s where s.isdn =:isdn AND s.status = 2");
            Query query = getSession().createQuery(sb.toString());
            query.setString("isdn", isdn);
            return (String) query.uniqueResult();
        } catch (Exception e) {
            logger.info("error", e);
        }

        return null;
    }

//	public SubMainInfoBean getCustSubInfo(String isdn) {
//		try {
//			StringBuilder sb = new StringBuilder(
//					" SELECT DISTINCT new co.siten.myvtg.bean.SubMainInfoBean(s.custName, 1,  s.subId ) "
//							+ "FROM AllCustSubForSelfcare s WHERE s.isdn = :isdn");
//			Query query = getSession().createQuery(sb.toString());
//			query.setString("isdn", isdn);
////			return (SubMainInfoBean) query.uniqueResult();
//                       if( query.list().size()> 0)                                
//			return (SubMainInfoBean) query.list().get(0);
//		} catch (Exception e) {
//			logger.info("error", e);
//		}
//		return null;
//	}
//	public SubMainInfoBean getCustSubInfo(String isdn) {
//		try {
//			StringBuilder sb = new StringBuilder(
//					" SELECT DISTINCT new co.siten.myvtg.bean.SubMainInfoBean(s.subName, 1,  s.subId  ) "
//							+ "FROM SubMb s WHERE s.isdn = :isdn and status = 2 ");
//			Query query = getSession().createQuery(sb.toString());
//			query.setString("isdn", isdn);
//			return (SubMainInfoBean) query.uniqueResult();
//		} catch (Exception e) {
//			logger.info("error", e);
//		}
//		return null;
//	}
    public SubMainInfoBean getCustSubInfo(String isdn) {
        try {
            String sb = " SELECT DISTINCT new co.siten.myvtg.bean.SubMainInfoBean(s.custName, 1,  s.subId , s.birthDate ) "
                    + "FROM AllCustSubForSelfcare s WHERE s.isdn = :isdn and statusId =2 ";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);
            return (SubMainInfoBean) query.uniqueResult();
        } catch (Exception e) {
            logger.info("error", e);
        }
        return null;
    }

    public String mfGetProductNameByProductCode(String productCode) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
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

    public List<SubscriberSyncInfoBean> getSubscriberSyncInfoBean(Long subId, int dayNum) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
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
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
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
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
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
//daibq bo sung chuyen table sub_rel_product => sub_rel_product_new

    public String getListRegisteredServiceCodes(Long subId) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a.rel_product_code" + " FROM " + schemaDefault + ".sub_rel_product_new a"
                    + " WHERE a.sub_id = :subId" + " AND  a.status = 1" + " AND a.is_connected = 1");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//            query.setCacheable(true);
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
                    "SELECT a.rel_product_code, a.end_datetime" + " FROM " + schemaDefault + ".sub_rel_product_new a"
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

    public boolean synRegisterSub(String isdn) {
        try {
            String schemaCmPre = environment.getRequiredProperty("cmpre.default_schema");
            Integer result = getSession().doReturningWork(new ReturningWork<Integer>() {
                @Override
                public Integer execute(Connection connection) throws SQLException {
                    // do something useful
                    CallableStatement call = connection
                            .prepareCall("call " + schemaCmPre + ".pkg_syn_vas.check_vas (NULL, ?)");
                    try {
                        call.setString(1, isdn);
                        call.execute();
                    } catch (Exception e) {
                        logger.error("", e);
                    } finally {
                        call.close();
                    }
                    return 1;
                }
            });
            if (result == 1) {
                return true;
            } else {
                return false;
            }
//            getSession().doWork(new Work() {
//                public void execute(Connection connection) throws SQLException {
//                    CallableStatement call = connection
//                            .prepareCall("call " + schemaCmPre + ".pkg_syn_vas.check_vas (NULL, ?)");
//                    try {
//                        call.setString(1, isdn);
//                        call.execute();
//                    } catch (Exception e) {
//                        logger.error("", e);
//                    } finally {
//                        call.close();
//                    }
//                }
//            });
        } catch (Exception ex) {

        }
        return true;
    }

    //get services expired
    public String getListRegisteredServiceCodesExpired(Long subId, Date startTime, Date endTime) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a.rel_product_code" + " FROM " + schemaDefault + ".sub_rel_product_new_log a"
                    + " WHERE a.sub_id = :subId" + " AND  a.status = 0" + " AND a.is_connected = 1 and a.end_datetime >= :startTime and a.end_datetime < :endTime");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//            query.setCacheable(true);
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

    //daibq bo sung chuyen table sub_rel_product => sub_rel_product_new
    public List<String> getRegisteredServiceCodes(Long subId) {
        try {
            String schemaDefault = environment.getRequiredProperty("cmpre.default_schema");
            StringBuilder sb = new StringBuilder(
                    "SELECT a.rel_product_code" + " FROM " + schemaDefault + ".sub_rel_product_new a"
                    + " WHERE a.sub_id = :subId" + " AND  a.status = 1" + " AND a.is_connected = 1");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
//            query.setCacheable(true);
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

    /**
     * daibq
     *
     * @param isdn
     * @return
     */
    public SubMb getSubMb(String isdn) {
        String sql = "SELECT SUB_ID  , ACT_STATUS  , PRODUCT_CODE , TO_CHAR(STA_DATETIME,'yyyy-mm-dd hh24:mi:ss')  FROM SUB_MB WHERE ISDN = ? AND STATUS = 2 ";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, isdn);
            List<Object[]> lstResult = query.list();
            if (!DataUtil.isNullOrEmpty(lstResult)) {
                SubMb subMb = new SubMb();
                subMb.setIsdn(isdn);
                subMb.setSubId(Long.parseLong(lstResult.get(0)[0].toString()));
                subMb.setActStatus(lstResult.get(0)[1].toString());
                subMb.setProductCode(lstResult.get(0)[2].toString());
                subMb.setStaDatetime(Timestamp.valueOf(lstResult.get(0)[3].toString()));
                return subMb;
            }
            return null;

        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

//    /**
//     * daibq
//     *
//     * @param logger
//     * @param msisdn
//     * @param messae
//     * @param channel
//     * @return
//     * @throws Exception
//     */
//    public int insertMT(Logger logger, String msisdn, String messae, String channel) throws Exception {
////        String channel = "113";
//        try {
//            String sql = "insert into mt (MT_ID,mo_his_id,msisdn,message,receive_time,retry_num,channel) "
//                    + "values(mt_seq.nextval , ? , ? , ? , sysdate , 1 , ?)";
//            Query query = getSession().createSQLQuery(sql);
//            query.setParameter(0, Long.parseLong(channel))
//                    .setParameter(1, msisdn)
//                    .setParameter(2, messae)
//                    .setParameter(3, channel);
//            int result = query.executeUpdate();
////            session.getTransaction().commit();
//            return result;
//        } catch (Exception ex) {
//            logger.info("error: " + ex.getMessage(), ex);
////            session.beginTransaction().rollback();
//        }
//        return 0;
//    }
    /**
     * @author daibq
     * @param isdn
     * @return
     */
    public SubMb getSubHp(String isdn) {
        String sql = "SELECT SUB_ID  , ACT_STATUS  , PRODUCT_CODE , TO_CHAR(STA_DATETIME,'yyyy-mm-dd hh24:mi:ss')  FROM SUB_HP WHERE ISDN = ? AND STATUS = 2 ";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, isdn);
            List<Object[]> lstResult = query.list();
            if (!DataUtil.isNullOrEmpty(lstResult)) {
                SubMb subMb = new SubMb();
                subMb.setIsdn(isdn);
                subMb.setSubId(Long.parseLong(lstResult.get(0)[0].toString()));
                subMb.setActStatus(lstResult.get(0)[1].toString());
                subMb.setProductCode(lstResult.get(0)[2].toString());
                subMb.setStaDatetime(Timestamp.valueOf(lstResult.get(0)[3].toString()));

                return subMb;
            }
            return null;

        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * @author daibq
     * @param logger
     * @param fromIsdn
     * @param toIsdn
     * @param transfermoney
     * @param fee
     * @param serialno
     * @param fileName
     * @return
     * @throws Exception
     */
    public int insertLogTransferMoney(Logger logger, String fromIsdn, String toIsdn, Double transfermoney, Long fee, Long serialno, String fileName) throws Exception {
        try {
            String sql = "insert into cm_pre2.money_transfer(from_msisdn,to_msisdn,transfermoney,oper_fee,trate_time,serialno,file_name,newbalance_calling,newbalance_called,oper_result)"
                    + "values(?,?,?,?,sysdate,?,?,0,0,0)";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, fromIsdn)
                    .setParameter(1, toIsdn)
                    .setParameter(2, transfermoney)
                    .setParameter(3, fee)
                    .setParameter(4, serialno)
                    .setParameter(5, fileName);
            int result = query.executeUpdate();
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return 0;
    }

    /**
     * @author daibq
     * @param isdn
     * @param serial
     * @param request
     * @param response
     * @param code
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void insertProLog(String isdn, String serial, String request, String response, String code) throws Exception {
        logger.info("begin method insertProLog of CmpreDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("INSERT INTO ACTION_LOG_PR (id,ISDN,SERIAL,REQUEST,RESPONSE,RESPONSE_CODE,CREATE_DATE) VALUES (SEQ_ACTION_LOG_PR.nextval,:isdn,:serial,:request,:response,:code,SYSDATE)");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("request", request);
            query.setParameter("response", response);
            query.setParameter("serial", serial);
            query.setParameter("isdn", isdn);
            query.setParameter("code", code);
            query.executeUpdate();
            logger.info("end method insertProLog of CmpreDao");
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * @author daibq
     * @param logger
     * @param isdn
     * @param productCode
     * @return
     * @throws Exception
     */
    public List<Object> checkPackage(Logger logger, String isdn, String productCode) throws Exception {
        List<Object> list = new ArrayList<>();
        try {
            String sql = "select * from sub_rel_product_new WHERE isdn =:isdn and rel_product_code = :productCode";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn)
                    .setParameter("productCode", productCode);
            List listResult = query.list();
            if (!DataUtil.isNullOrEmpty(listResult)) {
                for (int i = 0; i < listResult.size(); i++) {
                    IsdnInfoBean bean = new IsdnInfoBean();
                    bean.setIsdn(listResult.get(i).toString());
                    list.add(bean);
                }
            }
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return list;
    }

    /**
     * @author daibq
     * @param logger
     * @param isdn
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public List<Object> chargeHisInMonthByIsdn(Logger logger, String isdn, Date startDate, Date endDate) throws Exception {
        List<Object> list = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append(" select  sum (basic_balance) ,to_char(start_time,'dd/MM/yyyy') ");
            sql.append(" FROM ");
            sql.append(" ( ");
            sql.append(" select start_time,calling_number,basic_balance,'call' service  "
                    + "FROM V_SELFCARE_DETAIL_CALL"
                    + " where start_time >=:startDate "
                    + " and start_time < :endDate "
                    + " and calling_number =:isdn");
            sql.append(" union all ");
            sql.append(" select start_time,calling_number,basic_balance,'sms' service  "
                    + " FROM V_SELFCARE_DETAIL_SMS "
                    + " where start_time >=:startDate "
                    + "and start_time < :endDate  "
                    + "and calling_number =:isdn");
            sql.append(" union all ");
            sql.append(" select start_time,calling_number,basic_balance,'data' service "
                    + "FROM V_SELFCARE_DETAIL_DATA "
                    + "where start_time >=:startDate "
                    + " and start_time < :endDate "
                    + "and calling_number =:isdn");
            sql.append(" union all ");
            sql.append(" select start_time,calling_number,basic_balance,'vas' service  "
                    + "FROM V_SELFCARE_DETAIL_VAS "
                    + "where start_time >=:startDate "
                    + "and start_time < :endDate "
                    + "and calling_number =:isdn");
            sql.append(")");
            sql.append(" group by to_char(start_time,'dd/MM/yyyy'),calling_number"
                    + " ORDER BY to_char(start_time,'dd/MM/yyyy') ");
            Query query = getSession().createSQLQuery(sql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            List listResult = query.list();
            if (!DataUtil.isNullOrEmpty(listResult)) {
                for (int i = 0; i < listResult.size(); i++) {
                    Object[] object = (Object[]) listResult.get(i);
                    IsdnInfoBean bean = new IsdnInfoBean();
                    bean.setMoneyUse(Double.parseDouble(object[0].toString()));
                    bean.setDate(object[1].toString());
                    list.add(bean);
                }
            }
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return list;
    }

    /**
     * daibq getSubMbByIsdn
     *
     * @param isdn
     * @return
     */
    public SubMb getSubMbByIsdn(String isdn) {
//        String sql = "SELECT ISDN,SUB_ID,CUST_ID,STATUS,ACT_STATUS,SERIAL,PRODUCT_CODE  FROM SubMb s WHERE s.isdn = :isdn AND s.status = 2 AND act_status = '00' ";
        String sql = "SELECT s  FROM SubMb s WHERE s.isdn = :isdn AND s.status = 2 AND s.actStatus = '00' ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter("isdn", isdn);
            List<SubMb> lstResult = query.list();
            if (!DataUtil.isNullOrEmpty(lstResult)) {
                SubMb subMb = lstResult.get(0);
                return subMb;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * daibq getIdNoByCustId
     *
     * @param custId
     * @return
     */
    public String getIdNoByCustId(BigDecimal custId) {
        String sql = "SELECT ID_NO  FROM CUSTOMER  WHERE  CUST_ID = :custId ";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("custId", custId);
            List<Object> lstResult = query.list();
            if (!DataUtil.isNullOrEmpty(lstResult)) {
                return lstResult.get(0).toString();
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * daibq getIdNoByIsdn
     *
     * @param isdn
     * @return
     */
    public SubMbDto getCustByIsdn(String isdn) {
        try {
            //sb.act_status = '00'
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT ct.ID_NO idNo,ct.CUST_ID custId ,ct.NAME custName,ct.SEX sex,sb.ISDN isdn,ct.email email ");
            sql.append(" FROM SUB_MB sb ");
            sql.append(" LEFT JOIN CUSTOMER ct ON sb.CUST_ID = ct.CUST_ID AND sb.status = 2");
            sql.append(" WHERE  sb.isdn = :isdn");
            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.addScalar("idNo", new StringType())
                    .addScalar("custId", new LongType())
                    .addScalar("custName", new StringType())
                    .addScalar("sex", new StringType())
                    .addScalar("isdn", new StringType())
                    .addScalar("email", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(SubMbDto.class));
            query.setParameter("isdn", isdn);
            List lstResult = query.list();
            if (!DataUtil.isNullOrEmpty(lstResult)) {
                return (SubMbDto) lstResult.get(0);
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public boolean updateSubMbByIsdn(String isdn, BigDecimal custId) {
        try {
            String sql = "UPDATE sub_mb SET CUST_ID = :custId where isdn = :isdn AND status = 2 and act_status = '00'";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn)
                    .setParameter("custId", custId);
            int result = query.executeUpdate();
            return result > 0;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * Check the ISDN is updated information or not
     *
     * @param isdn
     * @return boolean
     * @throws Exception
     */
    public boolean checkIsUpdatedInfoIsdn(String isdn) throws Exception {
        try {
            logger.info(String.format("Start check Updated Information with isdn=%s", isdn));
            StringBuilder sb = new StringBuilder();
            sb.append("select count(1) from sub_mb sub inner JOIN customer cus on sub.cust_id = cus.cust_id");
            sb.append(" where sub.isdn = :isdn");
            sb.append(" and sub.status = 2");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            query.setParameter("isdn", isdn);
            List listResult = query.list();
            Long numOfInfo = Long.parseLong(listResult.get(0).toString());
            return numOfInfo == 0L;
        } catch (HibernateException e) {
            logger.error(String.format("An error occured when check Updated Information with isdn=%s", isdn));
            throw new Exception(e);
        }
    }

    /**
     * daibq getSubMbByIsdn
     *
     * @param isdn
     * @param actStatus
     * @return
     */
    public SubMb getSubMbByIsdn(String isdn, String actStatus) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT s  FROM SubMb s WHERE s.isdn = :isdn AND s.status = 2 ");
            if (!DataUtil.isNullOrEmpty(actStatus)) {
                sql.append("AND s.actStatus =:actStatus");
            }
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("isdn", isdn);
            if (!DataUtil.isNullOrEmpty(actStatus)) {
                query.setParameter("actStatus", actStatus);
            }
            List<SubMb> lstResult = query.list();
            if (!DataUtil.isNullOrEmpty(lstResult)) {
                SubMb subMb = lstResult.get(0);
                return subMb;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getTotalCamIDExDetailCall
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    public Double getTotalCamIdDetailCall(String isdn, Date startTime, Date endTime, String type) {
        try {

            StringBuilder callSql = new StringBuilder(
                    "SELECT nvl(SUM(charge_amount), 0) FROM  v_camid_" + type + "_detail_call c "
                    + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");

            SQLQuery query = getSession().createSQLQuery(callSql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            return Double.parseDouble(query.list().get(0).toString());

        } catch (NumberFormatException ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getTotalCamIDExDetailData
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    public Double getTotalCamIdDetailData(String isdn, Date startTime, Date endTime, String type) {
        try {

            StringBuilder callSql = new StringBuilder(
                    "SELECT nvl(SUM(charge_amount), 0) FROM  v_camid_" + type + "_detail_data c "
                    + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");

            SQLQuery query = getSession().createSQLQuery(callSql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            return Double.parseDouble(query.list().get(0).toString());

        } catch (NumberFormatException ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getTotalCamIDExDetailData
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    public Double getTotalCamIdDetailSms(String isdn, Date startTime, Date endTime, String type) {
        try {

            StringBuilder callSql = new StringBuilder(
                    "SELECT nvl(SUM(charge_amount), 0) FROM  v_camid_" + type + "_detail_sms c "
                    + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");

            SQLQuery query = getSession().createSQLQuery(callSql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            return Double.parseDouble(query.list().get(0).toString());

        } catch (NumberFormatException ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getTotalCamIDExDetailData
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    public Double getTotalCamIdDetailVas(String isdn, Date startTime, Date endTime, String type) {
        try {

            String callSql = "SELECT nvl(SUM(charge_amount), 0) FROM  v_camid_" + type + "_detail_vas c "
                    + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ";

            SQLQuery query = getSession().createSQLQuery(callSql);
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            return Double.parseDouble(query.list().get(0).toString());

        } catch (NumberFormatException ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getTotalCamIDExDetailData
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    public List<CamIdDetailBean> getCamIdDataDetail(String isdn, Date startTime, Date endTime) {
        try {

            StringBuilder callSql = new StringBuilder(
                    "SELECT c.calling_number callingNumber, c.called_number calledNumber, c.start_time startTime, nvl(c.duration, 0) duration, "
                    + "c.basic_balance basicBalance, c.prom_balance promBalance, nvl(c.charge_amount,0) chargeAmount "
                    + "FROM v_camid_data_detail_data c "
                    + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");

            SQLQuery query = getSession().createSQLQuery(callSql.toString());
            query.addScalar("callingNumber", new StringType());
            query.addScalar("calledNumber", new StringType());
            query.addScalar("startTime", new DateType());
            query.addScalar("duration", new LongType());
            query.addScalar("basicBalance", new DoubleType());
            query.addScalar("promBalance", new DoubleType());
            query.addScalar("chargeAmount", new DoubleType());
            query.setResultTransformer(Transformers.aliasToBean(CamIdDetailBean.class));
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            List<CamIdDetailBean> listDetails = query.list();
            if (CommonUtil.isEmpty(listDetails)) {
                return new ArrayList<>();
            }

            return listDetails;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getTotalCamIDExDetailData
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param type
     * @return
     */
    public List<HistoryChargeDataDetailsBean> getCamIdDataDetails(String isdn, Date startTime, Date endTime) {
        try {

            String callSql = "SELECT * FROM (SELECT nvl(sum(c.duration), 0) total, nvl(sum(c.charge_amount),0) money, to_char(start_time,'dd/MM/yyyy') startTimeStr ,'MB' unit "
                    + "FROM v_camid_data_detail_data c "
                    + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime "
                    + "GROUP BY to_char(c.start_time,'dd/MM/yyyy'), 'MB', c.start_time ORDER BY c.start_time DESC)";

            SQLQuery query = getSession().createSQLQuery(callSql);
            query.addScalar("total", new LongType());
            query.addScalar("money", new DoubleType());
            query.addScalar("startTimeStr", new StringType());
            query.addScalar("unit", new StringType());
            query.setResultTransformer(Transformers.aliasToBean(HistoryChargeDataDetailsBean.class));
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            List<HistoryChargeDataDetailsBean> listDetails = query.list();
            if (CommonUtil.isEmpty(listDetails)) {
                return new ArrayList<>();
            }

            return listDetails;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getHistoryChargeSmsDetails
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param parentType
     * @return
     */
    public List<HistoryChargeDetailsBean> getHistoryChargeSmsDetails(String isdn, Date startTime, Date endTime, String parentType) {
        try {
            String sql = "";
            switch (parentType.toUpperCase()) {
                case Constants.PARENT_TYPE_IS_EXCHANGE: {
                    sql = "SELECT c.called_number phoneNumber, TO_CHAR(c.START_TIME , 'DD/MM/YYYY') startTimeStr, TO_CHAR(c.START_TIME , 'HH:MI:SS') time, nvl(c.charge_amount, 0) money "
                            + "FROM v_camid_ex_detail_sms c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ORDER BY c.start_time desc";
                    break;
                }
                case Constants.PARENT_TYPE_IS_BASIC: {
                    sql = "SELECT c.called_number phoneNumber, TO_CHAR(c.START_TIME , 'DD/MM/YYYY') startTimeStr, TO_CHAR(c.START_TIME , 'HH:MI:SS') time, nvl(c.charge_amount, 0) money "
                            + "FROM v_camid_basic_detail_sms c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ORDER BY c.start_time desc";
                    break;
                }
                case Constants.PARENT_TYPE_IS_PROMOTION: {
                    sql = "SELECT c.called_number phoneNumber, TO_CHAR(c.START_TIME , 'DD/MM/YYYY') startTimeStr, TO_CHAR(c.START_TIME , 'HH:MI:SS') time, nvl(c.charge_amount, 0) money "
                            + "FROM v_camid_prom_detail_sms c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ORDER BY c.start_time desc";
                    break;
                }
                default: {
                    return new ArrayList<HistoryChargeDetailsBean>();
                }
            }

            SQLQuery query = getSession().createSQLQuery(sql);
            query.addScalar("phoneNumber", new StringType());
            query.addScalar("startTimeStr", new StringType());
            query.addScalar("time", new StringType());
            query.addScalar("money", new DoubleType());
            query.setResultTransformer(Transformers.aliasToBean(HistoryChargeDetailsBean.class));
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            List<HistoryChargeDetailsBean> listDetails = query.list();
            if (CommonUtil.isEmpty(listDetails)) {
                return new ArrayList<>();
            }

            return listDetails;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getHistoryChargeCallingDetails
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param parentType
     * @return
     */
    public List<HistoryChargeDetailsBean> getHistoryChargeCallingDetails(String isdn, Date startTime, Date endTime, String parentType) {
        try {
            String sql = "";
            switch (parentType.toUpperCase()) {
                case Constants.PARENT_TYPE_IS_BASIC: {
                    sql = "SELECT c.called_number phoneNumber, TO_CHAR(c.START_TIME , 'DD/MM/YYYY') startTimeStr, TO_CHAR(c.START_TIME , 'HH:MI:SS') time, nvl(c.charge_amount, 0) money "
                            + "FROM v_camid_basic_detail_call c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ORDER BY c.start_time desc";
                    break;
                }
                case Constants.PARENT_TYPE_IS_EXCHANGE: {
                    sql = "SELECT c.called_number phoneNumber, TO_CHAR(c.START_TIME , 'DD/MM/YYYY') startTimeStr, TO_CHAR(c.START_TIME , 'HH:MI:SS') time, nvl(c.charge_amount, 0) money "
                            + "FROM v_camid_ex_detail_call c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ORDER BY c.start_time desc";
                    break;
                }
                case Constants.PARENT_TYPE_IS_PROMOTION: {
                    sql = "SELECT c.called_number phoneNumber, TO_CHAR(c.START_TIME , 'DD/MM/YYYY') startTimeStr, TO_CHAR(c.START_TIME , 'HH:MI:SS') time, nvl(c.charge_amount, 0) money "
                            + "FROM v_camid_prom_detail_call c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ORDER BY c.start_time desc";
                    break;
                }
                default: {
                    return new ArrayList<HistoryChargeDetailsBean>();
                }
            }
            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.addScalar("phoneNumber", new StringType());
            query.addScalar("startTimeStr", new StringType());
            query.addScalar("time", new StringType());
            query.addScalar("money", new DoubleType());
            query.setResultTransformer(Transformers.aliasToBean(HistoryChargeDetailsBean.class));
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            List<HistoryChargeDetailsBean> listDetails = query.list();
            if (CommonUtil.isEmpty(listDetails)) {
                return new ArrayList<>();
            }

            return listDetails;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * kiennt getHistoryChargeCallingDetails
     *
     * @param isdn
     * @param startTime
     * @param endTime
     * @param parentType
     * @return
     */
    public List<HistoryChargeDataDetailsBean> getHistoryChargeDataDetails(String isdn, Date startTime, Date endTime, String parentType) {
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT nvl(sum(c.duration), 0) total, nvl(sum(c.charge_amount),0) money, to_char(start_time,'dd/MM/yyyy') startTimeStr ,'MB' unit FROM (");
            switch (parentType.toUpperCase()) {
                case Constants.PARENT_TYPE_IS_BASIC: {
                    sql.append("SELECT * FROM v_camid_basic_detail_data c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");
                    break;
                }
                case Constants.PARENT_TYPE_IS_EXCHANGE: {
                    sql.append("SELECT * FROM v_camid_ex_detail_data c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");
                    break;
                }
                case Constants.PARENT_TYPE_IS_PROMOTION: {
                    sql.append("SELECT * FROM v_camid_prom_detail_data c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");
                    break;
                }
                case Constants.PARENT_TYPE_IS_DATA: {
                    sql.append("SELECT * FROM v_camid_data_detail_data c "
                            + "WHERE c.calling_number = :isdn and c.start_time >= :startTime and c.start_time < :endTime ");
                    break;
                }
                default: {
                    return new ArrayList<HistoryChargeDataDetailsBean>();
                }
            }
            sql.append(") c GROUP BY TO_CHAR(c.start_time, 'dd/MM/yyyy'), c.start_time ORDER BY c.start_time desc)");
            logger.info("Sql of getHistoryChargeDataDetails " + sql.toString());

            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.addScalar("total", new LongType());
            query.addScalar("money", new DoubleType());

            query.addScalar("startTimeStr", new StringType());
            query.addScalar("unit", new StringType());
            query.setResultTransformer(Transformers.aliasToBean(HistoryChargeDataDetailsBean.class));
            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            List<HistoryChargeDataDetailsBean> listDetails = query.list();
            if (CommonUtil.isEmpty(listDetails)) {
                return new ArrayList<>();
            }

            return listDetails;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
            return new ArrayList<>();
        }
    }

    public List<HistoryChargeVasDetailsBean> getHistoryChargeVasDetails(String isdn, Date startTime, Date endTime, String parentType) {
        try {
            String sql = "";
            if (Constants.PARENT_TYPE_IS_BASIC.equals(parentType.toUpperCase())) {
                sql = "SELECT nvl(c.basic_balance, 0) total, nvl(c.charge_amount,0) money, to_char(start_time,'dd/MM/yyyy') startTime , c.called_number code, c.type type FROM "
                        + "V_CAMID_BASIC_DETAIL_VAS c WHERE c.calling_number =:isdn and c.start_time >= :startTime and c.start_time < :endTime order by start_time desc";
            } else {
                return new ArrayList<HistoryChargeVasDetailsBean>();
            }

            Query query = getSession().createSQLQuery(sql)
                    .addScalar("total", new DoubleType())
                    .addScalar("money", new DoubleType())
                    .addScalar("startTime", new StringType())
                    .addScalar("code", new StringType())
                    .addScalar("type", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(HistoryChargeVasDetailsBean.class));

            query.setParameter("isdn", isdn);
            query.setParameter("startTime", startTime);
            query.setParameter("endTime", endTime);

            List<HistoryChargeVasDetailsBean> result = query.list();
            if (result == null || result.isEmpty()) {
                return new ArrayList<HistoryChargeVasDetailsBean>();
            }
            return result;

        } catch (Exception e) {
            logger.error("### An error occured while get data of isdn=" + isdn, e);
            return new ArrayList<HistoryChargeVasDetailsBean>();
        }
    }

    public boolean checkServiceApParamIsWorking(String param) {
        String sql = "select count(*) from cm_pre2.ap_param where status = 1 and param_code=:param and param_type=:param";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("param", param);
            BigDecimal count = (BigDecimal) query.uniqueResult();
            if (count.longValue() > 0L) {
                return true;
            }
        } catch (Exception e) {
            logger.error("### An error occured while check state active with param= " + param, e);
        }
        return false;
    }
    
        public int insertMT(String msisdn, String messae, String channel) throws Exception {
        logger.info("Start insertMT API off MkishareDao");
//        String channel = "113";
        try {
            String sql = "insert into mt (MT_ID,mo_his_id,msisdn,message,receive_time,retry_num,channel) "
                    + "values(mt_seq.nextval , ? , ? , ? , sysdate , 1 , ?)";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, 123456L)
                    .setParameter(1, msisdn)
                    .setParameter(2, messae)
                    .setParameter(3, channel);
            int result = query.executeUpdate();
            logger.info("end insertMT API off MkishareDao");
//            session.getTransaction().commit();
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
//            session.beginTransaction().rollback();
        }
        return 0;
    }

}
