package co.siten.myvtg.dao;

import co.siten.myvtg.model.myvtg.SubService;
import co.siten.myvtg.util.DataUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("SubServiceDao")
@PropertySource(value = {"classpath:database.properties"})
public class SubServiceDao extends AbstractMyvtgDao<Object>{
    private static final Logger logger = Logger.getLogger(SubServiceDao.class.getName());

    @Autowired
    private Environment environment;

    public boolean isExistedSubService(String code) {
        logger.info("Start isExistedSubService API off SubServiceDao");
        String sql = "select sg.id from SubService sg where sg.code like ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, code);
            List<Object> lstResult = query.list();
            logger.info("End isExistedSubService API off SubServiceDao");
            if (!lstResult.isEmpty()) {
                return true;
            }

        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return false;
    }

    public SubService findById(long id) {
        logger.info("Start findById API off SubServiceDao");
        String sql = "select sg from SubService sg where sg.id = ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, id);
            SubService lstResult = (SubService) query.uniqueResult();
            logger.info("End findById API off SubServiceDao");
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public SubService findByCode(String code) {
        logger.info("Start findById API off SubServiceDao");
        String sql = "select sg from SubService sg where sg.code like ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, code);
            SubService lstResult = (SubService) query.uniqueResult();
            logger.info("End findById API off SubServiceDao");
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public Map<String,Object> getListSubService(String search, Integer pageSize, Integer pageNum, String sortBy, String sortType, Long serviceId) {
        logger.info("Start getListSubService API off SubServiceDao");
        Map<String, Object> response = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        sql.append(" select sg from SubService sg where 1 = 1 and sg.status = 1 ");
        countSql.append(" select count(sg.id) from SubService sg where 1 = 1 and sg.status = 1 ");
        try {
            if (!DataUtil.isNullOrEmpty(search)) {
                sql.append("and (upper(sg.code) like upper(concat('%',:search,'%')) ");
                sql.append("or upper(sg.name) like upper(concat('%',:search,'%'))) ");
                countSql.append("and (upper(sg.code) like upper(concat('%',:search,'%')) ");
                countSql.append("or upper(sg.name) like upper(concat('%',:search,'%'))) ");
            }
            if(!DataUtil.isNullOrEmpty(serviceId)){
                sql.append("and sg.serviceId = :serviceId");
                countSql.append("and sg.serviceId = :serviceId");
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
            if(!DataUtil.isNullOrEmpty(serviceId)){
                query.setParameter("serviceId", serviceId);
                countQuery.setParameter("serviceId", serviceId);
            }
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum));
                query.setMaxResults(pageSize);
            }
            List<SubService> lstResult = query.list();
            Long totalElements = (Long) countQuery.uniqueResult();
            logger.info("End getListSubService API off SubServiceDao");
            if (DataUtil.isNullOrEmpty(lstResult)) {
                response.put("subServices",new ArrayList<>());
                response.put("totalElements",0);
                return response;
            }

            response.put("subServices",lstResult);
            response.put("totalElements",totalElements);
            return response;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        response.put("subServices",new ArrayList<>());
        response.put("totalElements",0);
        return response;
    }
}
