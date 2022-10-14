package co.siten.myvtg.dao;

import co.siten.myvtg.model.myvtg.ServiceGroup;
import co.siten.myvtg.util.DataUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("ServiceGroupDao")
@PropertySource(value = {"classpath:database.properties"})
public class ServiceGroupDao extends AbstractMyvtgDao<Object> {

    private static final Logger logger = Logger.getLogger(ServiceGroupDao.class.getName());

    @Autowired
    private Environment environment;

    public boolean isExistedServiceGroup(String code) {
        logger.info("Start isExistedServiceGroup API off ServiceGroupDao");
        String sql = "select sg.id from ServiceGroup sg where sg.code like ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, code);
            List<Long> lstResult = query.list();
            logger.info("End isExistedServiceGroup API off ServiceGroupDao");
            if (!lstResult.isEmpty()) {
                return true;
            }

        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return false;
    }

    public ServiceGroup findById(Long id) {
        logger.info("Start findById API off ServiceGroupDao");
        String sql = "select sg from ServiceGroup sg where sg.id = ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, id);
            ServiceGroup lstResult = (ServiceGroup) query.uniqueResult();
            logger.info("End findById API off ServiceGroupDao");
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }


    public List<ServiceGroup> findByCode(String code) {
        logger.info("Start findById API off ServiceGroupDao");
        String sql = "select sg from ServiceGroup sg where sg.id = ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, code);
            List<ServiceGroup> lstResult = query.list();
            logger.info("End findById API off ServiceGroupDao");
            if (lstResult.isEmpty()) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public Map<String,Object> getListServiceGroup(String search, Integer pageSize, Integer pageNum, String sortBy, String sortType) {
        logger.info("Start getListServiceGroup API off ServiceGroupDao");
        Map<String, Object> response = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        StringBuilder countSql = new StringBuilder();
        sql.append(" select sg from ServiceGroup sg where 1 = 1 and sg.status = 1 ");
        countSql.append(" select count(sg.id) from ServiceGroup sg where 1 = 1 and sg.status = 1 ");
        try {
            if (!DataUtil.isNullOrEmpty(search)) {
                sql.append("and (upper(sg.code) like upper(concat('%',:search,'%')) ");
                sql.append("or upper(sg.name) like upper(concat('%',:search,'%'))) ");
                countSql.append("and (upper(sg.code) like upper(concat('%',:search,'%')) ");
                countSql.append("or upper(sg.name) like upper(concat('%',:search,'%'))) ");
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
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum));
                query.setMaxResults(pageSize);
            }
            List<ServiceGroup> lstResult = query.list();
            Long totalElements = (Long) countQuery.uniqueResult();
            logger.info("End getListServiceGroup API off ServiceGroupDao");
            if (DataUtil.isNullOrEmpty(lstResult)) {
                response.put("serviceGroups",new ArrayList<>());
                response.put("totalElements",0);
                return response;
            }
            response.put("serviceGroups",lstResult);
            response.put("totalElements",totalElements);
            return response;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        response.put("serviceGroups",new ArrayList<>());
        response.put("totalElements",0);
        return response;
    }


}
