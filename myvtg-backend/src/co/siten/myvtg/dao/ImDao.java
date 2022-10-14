/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author phuonghc
 */
@Repository("ImDao")
@PropertySource(value = {"classpath:database.properties"})
public class ImDao extends AbstractImDao<Object>{

    private static final Logger LOGGER = Logger.getLogger(ImDao.class.getName());
    /**
     * Check the ISSN is nice number
     * @param isdn
     * @return boolean
     * @throws Exception
     */
    public boolean checkIsNiceNumber(String isdn) throws Exception
    {
        LOGGER.info(String.format("Start check nice number with isdn=%s", isdn));
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append("select count(1) from bccs_im.stock_isdn_mobile where isdn=:isdn AND rules_id is not null");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            query.setParameter("isdn", isdn);
            List listResult = query.list();
            Long numOfInfo = Long.parseLong(listResult.get(0).toString());
            return numOfInfo != 0L;
        }
        catch (HibernateException e)
        {
            LOGGER.error(String.format("An error occured when check nice number with isdn=%s", isdn));
            throw new Exception(e);
        }
    }
    
    public boolean isRequestAvailable(String id, String isdn) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("select count(1) from bccs_im.req_update_cusinfor where msisdn=:isdn AND status = 4 and id=:id");
            SQLQuery query = getSession().createSQLQuery(sb.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("id", id);
            List listResult = query.list();
            Long numOfInfo = Long.parseLong(listResult.get(0).toString());
            return numOfInfo != 0L;
        } catch (HibernateException e) {
            LOGGER.error(String.format("An error occured when isRequestAvailable with isdn=%s", isdn));
        }
        return false;
    }

    public boolean updateResponseFromEmoney(String id, String isdn, String processTime, String tid, String response, String status) {
        String sql = "update bccs_im.req_update_cusinfor set status = 1, process_time=to_date(:time, 'dd/mm/yyyy hh24:mi:ss'), tid=:tid, process_response=:response, REQUEST_STATUS=:status "
                + "where status =4 and msisdn=:isdn and id=:id";
        try {
            SQLQuery query = getSession().createSQLQuery(sql);
            query.setParameter("time", processTime);
            query.setParameter("tid", tid);
            query.setParameter("response", response);
            query.setParameter("status", status);
            query.setParameter("isdn", isdn);
            query.setParameter("id", id);
            int result = query.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (HibernateException he) {
            LOGGER.error(String.format("An error occured when updateResponseFromEmoney with isdn=%s", isdn));
        }
        return false;
    }

    public boolean saveLogEmoneyResponse(Long id, String isdn, String processTime, String tid, String response, String status, String requestId) {
        String sql = "insert into bccs_im.req_update_cusinfor_log(id, msisdn, response, process_time, status, tid, request_id)"
                + " values (:id, :msisdn, :response, to_date(:processTime, 'dd/mm/yyyy hh24:mi:ss'), "
                + ":status, :tid, :requestId)";
        try {
            SQLQuery query = getSession().createSQLQuery(sql);
            query.setParameter("id", id);
            query.setParameter("msisdn", isdn);
            query.setParameter("response", response);
            query.setParameter("processTime", processTime);
            query.setParameter("status", status);
            query.setParameter("tid", tid);
            query.setParameter("requestId", requestId);

            int result = query.executeUpdate();
            return result == 1;
        } catch (Exception he) {
            LOGGER.error(String.format("An error occured when updateResponseFromEmoney with isdn=%s", isdn));
        }
        return false;
    }

    public Long getSequence(String sequenceName) {
        try {
            String sql = "select " + sequenceName + ".NEXTVAL from dual";
            SQLQuery query = getSession().createSQLQuery(sql);
            List lst = query.list();
            if (lst.isEmpty()) {
                return null;
            }
            return Long.parseLong(lst.get(0).toString());
        } catch (NumberFormatException e) {
            LOGGER.error(String.format("### Cannot find SEQ for %s", sequenceName), e);
            return null;
        }
    }
}
