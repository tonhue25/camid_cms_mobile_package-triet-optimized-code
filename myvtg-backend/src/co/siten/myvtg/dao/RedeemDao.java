/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dao;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 * RedeemDao
 *
 * @author partner7
 */
@Repository("RedeemDao")
@PropertySource(value = {"classpath:database.properties"})
public class RedeemDao extends AbstractApigwDao<Object> {

    private static final Logger logger = Logger.getLogger(RedeemDao.class.getName());

    public Long isCheckRedeemAlreadyWithCamidAndIsdn(String isdn, String camid) {
        String sql = "";
        if (StringUtils.isNotEmpty(camid)) {
            sql = "select status from camid_redeem_gift_tet where cam_id=:camid ";
        } else {
            sql = "select status from camid_redeem_gift_tet where isdn=:isdn "; // and (redeem_time is null OR trunc(redeem_time) = trunc(sysdate))";
        }
        try {
            Query query = getSession().createSQLQuery(sql);
            if (StringUtils.isNotEmpty(camid)) {
                query.setParameter("camid", camid);
            } else {
                query.setParameter("isdn", isdn);
            }
            BigDecimal count = (BigDecimal) query.uniqueResult();
            if (count == null) {
                //Record not exist in database
                return -1L;
            }
            return count.longValue();
        } catch (Exception e) {
            logger.error("### An error occured while check isdn redeem", e);
        }
        return 1L;
    }

    public Long checkIsdnExistInDb(String isdn){
        String sql = "select count(*) from camid_redeem_gift_tet where isdn=:isdn ";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn);
            BigDecimal count = (BigDecimal) query.uniqueResult();
            return count.longValue();
        } catch (Exception e) {
            logger.error("### An error occured while check isdn redeem", e);
        }
        return 1L;
    }
    
    public Long isCheckRedeemAlready(String isdn) {
        String sql = "select status from camid_redeem_gift_tet where isdn=:isdn ";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn);
            BigDecimal count = (BigDecimal) query.uniqueResult();
            if (count == null) {
                //Record not exist in database
                return -1L;
            }
            return count.longValue();
        } catch (Exception e) {
            logger.error("### An error occured while check isdn redeem", e);
        }
        return 1L;
    }

    public boolean isFirstTimeOpenApp(String camid, String deviceId) {
        StringBuilder sql = new StringBuilder("select count(*) from camid_first_time_open_app where 1=1 ");
        if (StringUtils.isNotEmpty(camid)) {
            sql.append(" and cam_id =:camid ");
        } else {
            if (StringUtils.isNotEmpty(deviceId)) {
                sql.append(" and device_id =:deviceId ");
            }
        }
        try {

            Query query = getSession().createSQLQuery(sql.toString());
            if (StringUtils.isNotEmpty(camid)) {
                query.setParameter("camid", camid);
            } else {
                if (StringUtils.isNotEmpty(deviceId)) {
                    query.setParameter("deviceId", deviceId);
                }
            }
            BigDecimal count = (BigDecimal) query.uniqueResult();
            if (count.longValue() == 0L) {
                return true;
            }
        } catch (Exception e) {
            logger.error("### An error occured while check camid first open app", e);
        }
        return false;
    }

    public void saveEventFirstTimeOpenApp(Long id, String camid, String deviceId) {
        String sql = "insert into apigw.camid_first_time_open_app(id, cam_id, device_id, first_time_open_app) values "
                + "(:id, :camid, :deviceId, sysdate)";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("id", id);
            query.setParameter("camid", camid);
            query.setParameter("deviceId", deviceId);
            int result = query.executeUpdate();
            if (result == 1) {
                logger.info("### Success save firsttime open app for camid/deviceid" + camid + deviceId);
            }
        } catch (HibernateException he) {
            logger.error("### An error occured while check isdn redeem", he);
        }
    }

    public void savePrepareRedeem(Long id, String isdn, String camid, String deviceId) {
        String sql = "insert into apigw.camid_redeem_gift_tet(id, isdn, cam_id, device_id, status) values (:id, :isdn, :camid, :deviceId, 0) ";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("id", id);
            query.setParameter("isdn", isdn);
            query.setParameter("camid", camid);
            query.setParameter("deviceId", deviceId);
            int result = query.executeUpdate();
            if (result == 1) {
            }
        } catch (Exception e) {
            logger.error("### An error occured while prepare redeem", e);
        }
    }

    public void updatePrepareRedeem(String isdn, String camid, String deviceId) {
        String sql = "update apigw.camid_redeem_gift_tet set isdn=:isdn where cam_id=:camid and device_id=:deviceId";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn);
            query.setParameter("camid", camid);
            query.setParameter("deviceId", deviceId);
            int result = query.executeUpdate();
            if (result == 1) {
            }
        } catch (Exception e) {
            logger.error("### An error occured while prepare redeem", e);
        }
    }

    public boolean isSaveLogSuccess(String isdn, Long status) {
        String sql = "update apigw.camid_redeem_gift_tet set status=:status, redeem_time=sysdate where isdn=:isdn and redeem_time is null";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn);
            query.setParameter("status", status);
            int result = query.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (Exception e) {
            logger.error("### An error occured while save log redeem", e);
        }
        return false;
    }

    public Long getSequence(String sequenceName) {
        try {
            String sql = "select " + sequenceName + ".NEXTVAL from dual";
            Query query = getSession().createSQLQuery(sql);
            List lst = query.list();
            if (lst.isEmpty()) {
                return null;
            }
            return Long.parseLong(lst.get(0).toString());
        } catch (NumberFormatException e) {
            logger.error(String.format("### Cannot find SEQ for %s", sequenceName), e);
            return null;
        }
    }
}
