package co.siten.myvtg.dao;

import co.siten.myvtg.model.myvtg.Agent;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.util.DataUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("AgentDao")
@PropertySource(value = {"classpath:database.properties"})
public class AgentDao extends AbstractMyvtgDao<Object> {

    private static final Logger logger = Logger.getLogger(AgentDao.class.getName());

    @Autowired
    private Environment environment;

    public Map<String, Object> getListStaff(String search, Integer pageSize, Integer pageNum, String sortBy, String sortType) {
        Map<String, Object> response = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select sg from Agent sg where 1 = 1 ");
        try {
            if (!DataUtil.isNullOrEmpty(search)) {
                sql.append("and (upper(sg.phoneNumber) like upper(concat('%',:search,'%')) ");
                sql.append("or upper(sg.fullName) like upper(concat('%',:search,'%'))) ");
            }
            if (!DataUtil.isNullOrEmpty(sortBy)) {
                switch (sortBy) {
                    case "phoneNumber":
                        sql.append(" order by sg.phoneNumber ");
                        break;
                    case "fullName":
                        sql.append(" order by sg.fullName ");
                        break;
                    default:
                        sql.append("order by sg.lastUpdatedTime ");
                        break;
                }
            } else {
                sql.append(" order by sg.lastUpdatedTime ");
            }
            if (!DataUtil.isNullOrEmpty(sortType)) {
                sql.append(sortType);
            }
            Query query = getSession().createQuery(sql.toString());
            if (!DataUtil.isNullOrEmpty(search)) {
                query.setParameter("search", search);
            }
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum));
                query.setMaxResults(pageSize);
            }
            List<Agent> lstResult = query.list();
            if (DataUtil.isNullOrEmpty(lstResult)) {
                response.put("staffs", new ArrayList<>());
                response.put("totalElements", 0);
                return response;
            }
            response.put("staffs", lstResult);
            response.put("totalElements", query.list().size());
            return response;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        response.put("staffs", new ArrayList<>());
        response.put("totalElements", 0);
        return response;
    }

    public Agent findById(Long id) {
        String sql = "select sg from Agent sg where sg.id = ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, id);
            Agent lstResult = (Agent) query.uniqueResult();
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public Map<String, Object> getStaffByPhone(String phoneNumber) {
        Map<String, Object> response = new HashMap<>();
        response.put("staff", findByPhoneNumber(phoneNumber));
        return response;
    }

    public Agent findByPhoneNumber(String phoneNumber) {
        String sql = "select sg from Agent sg where sg.phoneNumber = ? ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter(0, phoneNumber);
            Agent lstResult = (Agent) query.uniqueResult();
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public List<Agent> getAll() {
        List<Agent> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select sg from Agent sg where 1 = 1");
        try {
            Query query = getSession().createQuery(sql.toString());
            result = query.list();
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return result;
    }

    public List<Agent> getListStaffs(String search, String sortBy, String sortType) {
        List<Agent> lstResult = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select sg from Agent sg where 1 = 1 ");
        try {
            if (!DataUtil.isNullOrEmpty(search)) {
                sql.append("and (upper(sg.phoneNumber) like upper(concat('%',:search,'%')) ");
                sql.append("or upper(sg.fullName) like upper(concat('%',:search,'%'))) ");
            }
            if (!DataUtil.isNullOrEmpty(sortBy)) {
                switch (sortBy) {
                    case "phoneNumber":
                        sql.append(" order by sg.phoneNumber ");
                        break;
                    case "fullName":
                        sql.append(" order by sg.fullName ");
                        break;
                    default:
                        sql.append("order by sg.lastUpdatedTime ");
                        break;
                }
            } else {
                sql.append(" order by sg.lastUpdatedTime ");
            }
            if (!DataUtil.isNullOrEmpty(sortType)) {
                sql.append(sortType);
            }
            Query query = getSession().createQuery(sql.toString());
            if (!DataUtil.isNullOrEmpty(search)) {
                query.setParameter("search", search);
            }
            lstResult = query.list();
            if (DataUtil.isNullOrEmpty(lstResult)) {
                return lstResult;
            }
            return lstResult;
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return lstResult;
    }
}
