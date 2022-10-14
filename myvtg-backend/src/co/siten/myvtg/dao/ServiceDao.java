package co.siten.myvtg.dao;

import co.siten.myvtg.bean.*;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.model.myvtg.ServiceA;
import co.siten.myvtg.model.myvtg.Subscriber;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.DataUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author thomc
 */
@Repository("ServiceDao")
@Qualifier("myvtgtx")
@PropertySource(value = {"classpath:database.properties"})
public class ServiceDao extends AbstractMyvtgDao<Object> {

    private static final Logger logger = Logger.getLogger(ServiceDao.class.getName());
    @Autowired
    private Environment environment;
    @Autowired
    CmpreDao cmpreDao;
    @Autowired
    CmposDao cmposDao;
    @Autowired
    SubDao subDao;

    public ServiceA findById(String id) {
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("id", id));
        return (ServiceA) criteria.uniqueResult();
    }

    @Cacheable("services")
    public List<ServiceBean> getAllUnregisterService(String isdn, String language, String listRegisteredServiceCodes) {
        // if (isUnitelMarket()) {

        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {
            listRegisteredServiceCodes = "(' ')";
        }

        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.ServiceBean(sg.name, sg.code, s.name, s.code, s.shortDes, s.iconUrl, s.isMultPlan, s.unit, s.price, s.validity) "
                        + "FROM ServiceGroupA sg, ServiceA s " + "WHERE s.id IN (" + "SELECT DISTINCT ss.serviceId "
                        + " FROM SubServiceA ss " + "WHERE ss.code NOT IN  " + listRegisteredServiceCodes
                        + " AND ss.language = :language " + " AND ss.approved=1 ) "
                        + " AND sg.id  = s.serviceGroupId AND " + " sg.language = :language " + " AND sg.status = 1 "
                        + " AND s.serviceType in (0,31,32,33,34,35,36,37)" + " AND s.language = :language " + " AND s.status = 1 "
                        + " AND s.approved=1 "
                        + "GROUP BY sg.name, sg.code, s.name, s.code, s.shortDes, s.iconUrl, s.isMultPlan, s.unit, s.price, s.validity, sg.serviceGroupOrder, s.serviceOrder "
                        + "ORDER BY sg.serviceGroupOrder asc, s.serviceOrder asc, s.name asc");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        return query.list();
        // } else {
        // StringBuilder sb = new StringBuilder(
        // " SELECT new co.siten.myvtg.bean.ServiceBean(sg.name, sg.code,
        // s.name, s.code, s.shortDes, s.iconUrl, s.isMultPlan) "
        // + "FROM ServiceGroupA sg, ServiceA s " + "WHERE s.id IN (" + "SELECT
        // DISTINCT ss.serviceId "
        // + " FROM SubServiceA ss " + "WHERE ss.code NOT IN " + " (SELECT
        // id.subServiceCode "
        // + " FROM Subscriber WHERE id.isdn = :isdn AND state = 1 AND status =
        // 1) "
        // + "AND ss.language = :language " + " AND ss.approved=1 ) "
        // + " AND sg.id = s.serviceGroupId AND " + " sg.language = :language "
        // + " AND sg.status = 1 " + " AND s.serviceType = 0 " + " AND
        // s.language = :language "
        // + " AND s.status = 1 " + " AND s.approved=1 "
        // + "GROUP BY sg.name, sg.code, s.name, s.code, s.shortDes, s.iconUrl,
        // s.isMultPlan "
        // + "ORDER BY sg.name, s.name ");
        // Query query = getSession().createQuery(sb.toString());
        // query.setString("isdn", isdn);
        // query.setString("language", language);
        // return query.list();
        // }
    }

    public SubMainInfoBean getSubMainInfo(String isdn) {
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.SubMainInfoBean(s.name, s.subType, s.language, s.avatarUrl , s.subId, s.lastSyncTime) "
                        + "FROM Sub s WHERE s.isdn = :isdn");
        Query query = getSession().createQuery(sb.toString());
        query.setString("isdn", isdn);
        return (SubMainInfoBean) query.uniqueResult();
    }

    public ServiceInfoBean getServiceInfo(String language, String serviceCode) {
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.ServiceInfoBean( imgDesUrl,  name,  fullDes, price) "
                        + "FROM ServiceA s WHERE s.language = :language and s.code= :code and s.serviceType= 0 and s.approved = 1 and s.status = 1");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("code", serviceCode);
        return (ServiceInfoBean) query.uniqueResult();

    }

    public ServiceInfoBean getServiceInfoAirtime(String isdn, String language, String serviceCode) {
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.ServiceInfoBean( s.imgDesUrl,  s.name,  s.fullDes, s.price, scr) "
                        + "FROM ServiceA s, SubServiceA ss, Subscriber scr "
                        + "WHERE s.language = :language and s.code= :code and s.serviceType= 0 and s.approved = 1 and s.status = 1 "
                        + "AND s.id = ss.serviceId " + "AND ss.approved = 1 " + "AND ss.language = :language "
                        + "AND ss.status   = 1 " + "AND ss.code     = scr.id.subServiceCode "
                        + "AND scr.id.isdn    = :isdn");
        // ;
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("code", serviceCode);
        query.setString("isdn", isdn);
        return (ServiceInfoBean) query.uniqueResult();
    }

    public List<SubServiceBean> getCurrentUsedSubServices(String language, String isdn,
                                                          String listRegisteredServiceCodes) {
        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {

            listRegisteredServiceCodes = "(' ')";
        }
        StringBuilder sb = new StringBuilder(
                "SELECT new co.siten.myvtg.bean.SubServiceBean( name,  code,  shortDes, iconUrl) "
                        + " FROM SubServiceA s" + " WHERE s.language = :language" + " AND s.code IN "
                        + listRegisteredServiceCodes + " ORDER BY s.name DESC ");

        Query query = getSession().createQuery(sb.toString());
        query.setCacheable(true);
        query.setString("language", language);
        // query.setString("isdn", isdn);

        return query.list();
        // } else {
        // StringBuilder sb = new StringBuilder(
        // " SELECT new co.siten.myvtg.bean.SubServiceBean( name, code,
        // shortDes, iconUrl) "
        // + " FROM SubServiceA s" + " WHERE s.language = :language"
        // + " AND s.code IN (SELECT id.subServiceCode from Subscriber WHERE "
        // + "id.isdn= :isdn AND state= 1 AND status = 1) ORDER BY s.name");
        // Query query = getSession().createQuery(sb.toString());
        // query.setString("language", language);
        // query.setString("isdn", isdn);
        // return query.list();
        // }
    }

    public Subscriber getSubcriberByIsdnAndServiceCode(String isdn, String serviceCode) {
        StringBuilder sb = new StringBuilder(" SELECT sub from Subscriber sub WHERE "
                + "id.isdn= :isdn AND status = 1 AND id.subServiceCode= :code");
        Query query = getSession().createQuery(sb.toString());
        query.setString("code", serviceCode);
        query.setString("isdn", isdn);
        List<Subscriber> l = query.list();

        if (CommonUtil.isEmpty(l)) {
            return null;
        } else {
            return l.get(0);
        }
    }

    public void updateSubcriber(String isdn, String serviceCode, int state) {
        StringBuilder sb = new StringBuilder("update Subscriber set state= :state WHERE "
                + "id.isdn= :isdn AND status = 1 AND id.subServiceCode= :code");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("state", state);
        query.setString("code", serviceCode);
        query.setString("isdn", isdn);
        query.executeUpdate();
    }

    @Cacheable("services")
    public BlockGoingCallBean getServiceInfoForBlockGoingCall(String language, String serviceCode) {
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.BlockGoingCallBean( imgDesUrl,  name,  fullDes, price) "
                        + "FROM ServiceA s WHERE s.language = :language and s.code= :code and s.approved = 1 and s.status = 1");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("code", serviceCode);
        return (BlockGoingCallBean) query.uniqueResult();

    }

    public AdvanceServiceBean getAdvancedServiceInfo(String language, String isdn, String serviceCode) {
        StringBuilder sb = new StringBuilder(" SELECT scr " + "FROM ServiceA s, SubServiceA ss, Subscriber scr "
                + "WHERE s.language = :language and s.code= :code and s.serviceType= 0 and s.approved = 1 and s.status = 1 "
                + "AND s.id = ss.serviceId " + "AND ss.approved = 1 " + "AND ss.language = :language "
                + "AND ss.status = 1 " + "AND ss.code     = scr.id.subServiceCode " + "AND scr.id.isdn=:isdn");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("code", serviceCode);
        query.setString("isdn", isdn);
        Subscriber sub = (Subscriber) query.uniqueResult();

        StringBuilder sb1 = new StringBuilder(" SELECT s " + "FROM ServiceA s, SubServiceA ss "
                + "WHERE s.language = :language and s.code= :code and s.serviceType= 0 and s.approved = 1 and s.status = 1 "
                + "AND s.id = ss.serviceId " + "AND ss.approved = 1 " + "AND ss.language = :language "
                + "AND ss.status = 1 ");
        Query query1 = getSession().createQuery(sb1.toString());
        query1.setString("language", language);
        query1.setString("code", serviceCode);
        ServiceA serviceA = (ServiceA) query1.uniqueResult();

        if (serviceA == null)
            return null;
        return new AdvanceServiceBean(
                serviceA.getServicePage() != null ? serviceA.getServicePage().trim().replaceAll("\n ", "") : "",
                sub == null ? 0 : sub.getState());
    }

    // private Boolean isUnitelMarket() {
    // String marketName =
    // environment.getProperty(Constants.MARKET_NAME_CONFIG);
    // return marketName != null &&
    // marketName.equalsIgnoreCase(Constants.MARKET_NAME_UNITEL);
    // }



    ///////////////////

    public boolean isExistedService(String code) {
        logger.info("Start isExistedService API off ServiceDao");
        String sql = "select sg.id from Service sg where sg.code like ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, code);
            List<Object> lstResult = query.list();
            logger.info("End isExistedService API off ServiceDao");
            if (!lstResult.isEmpty()) {
                return true;
            }

        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return false;
    }

    public Service findByCode(String code) {
        logger.info("Start findById API off ServiceDao");
        String sql = "select sg from Service sg where sg.code like ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, code);
            Service lstResult = (Service) query.uniqueResult();
            logger.info("End findById API off ServiceDao");
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public Service findById(long id) {
        logger.info("Start findById API off ServiceDao");
        String sql = "select sg from Service sg where sg.id = ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, id);
            Service lstResult = (Service) query.uniqueResult();
            logger.info("End findById API off ServiceDao");
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public Map<String, Object> getListService(String search, Integer pageSize, Integer pageNum, String sortBy, String sortType, Long servicegroupId) {
        logger.info("Start getListService API off ServiceDao");
        Map<String, Object> response = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        sql.append(" select sg from Service sg where 1 = 1 and sg.status = 1 ");
        countSql.append(" select count(sg.id) from Service sg where 1 = 1 and sg.status = 1 ");
        try {
            if (!DataUtil.isNullOrEmpty(search)) {
                sql.append("and (upper(sg.code) like upper(concat('%',:search,'%')) ");
                sql.append("or upper(sg.name) like upper(concat('%',:search,'%'))) ");
                countSql.append("and (upper(sg.code) like upper(concat('%',:search,'%')) ");
                countSql.append("or upper(sg.name) like upper(concat('%',:search,'%'))) ");
            }
            if(!DataUtil.isNullOrEmpty(servicegroupId)){
                sql.append("and sg.serviceGroupId = :serviceGroupId");
                countSql.append("and sg.serviceGroupId = :serviceGroupId");
            }
            if (!DataUtil.isNullOrEmpty(sortBy)) {
                switch (sortBy) {
                    case "code":
                        sql.append("order by sg.code ");
                        break;
                    case "name":
                        sql.append("order by sg.name ");
                        break;
                    default:
                        sql.append("order by sg.createdTime ");
                        break;
                }
            } else {
                sql.append("order by sg.createdTime ");
            }
            if (!DataUtil.isNullOrEmpty(sortType)) {
                sql.append(sortType);
            }
            Query query = getSession().createQuery(sql.toString());
            Query countQuery = getSession().createQuery(countSql.toString());
            if (!DataUtil.isNullOrEmpty(search)) {
                query.setParameter("search", search);
                countQuery.setParameter("search", search);
            }
            if(!DataUtil.isNullOrEmpty(servicegroupId)){
                query.setParameter("serviceGroupId", servicegroupId);
                countQuery.setParameter("serviceGroupId", servicegroupId);
            }
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum));
                query.setMaxResults(pageSize);
            }
            List<Service> lstResult = query.list();
            Long totalElements = (Long) countQuery.uniqueResult();
            logger.info("End getListService API off ServiceDao");
            if (DataUtil.isNullOrEmpty(lstResult)) {
                response.put("services", new ArrayList<>());
                response.put("totalElements", 0);
                return response;
            }
            response.put("services", lstResult);
            response.put("totalElements", totalElements);
            return response;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        response.put("services", new ArrayList<>());
        response.put("totalElements", 0);
        return response;
    }


}
