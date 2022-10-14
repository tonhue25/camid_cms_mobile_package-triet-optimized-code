/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dao;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 * AdvertisementDao
 *
 * @author partner7
 */
@Repository("AdvertisementDao")
@PropertySource(value = {"classpath:database.properties"})

public class AdvertisementDao extends AbstractApigwDao<Object> {

    private static final Logger logger = Logger.getLogger(AdvertisementDao.class.getName());

    public Long getIdFromDeviceId(String deviceId) {
        String sql = "select id from camid_invite_app where device_id=:deviceId";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("deviceId", deviceId);
        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString());
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from deviceId" + deviceId + " is not a number", ne);
        } catch (Exception e) {
            logger.error("### An error occured while find id for deviceId=" + deviceId, e);
        }
        return null;
    }
}
