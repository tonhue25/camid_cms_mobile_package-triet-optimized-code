package co.siten.myvtg.dao;

import co.siten.myvtg.bean.CamIdNotificationBean;
import co.siten.myvtg.bean.CamIdNotificationCMSBean;
import co.siten.myvtg.bean.CamIdActionTypeBean;
import co.siten.myvtg.bean.CamIdRewardDetailBean;
import co.siten.myvtg.bean.CamIdGame;
import co.siten.myvtg.bean.CamIdGameCategory;
import co.siten.myvtg.bean.CamIdPassiveTypeBean;
import co.siten.myvtg.bean.NotificationBean;
import co.siten.myvtg.dto.NotificationAccountDtoV2;
import co.siten.myvtg.dto.NotificationDtoV2;
import co.siten.myvtg.dto.NotificationResult;
import co.siten.myvtg.model.apigw.AccountNotification;
import co.siten.myvtg.model.apigw.CamIdDeviceToken;
import co.siten.myvtg.model.apigw.DeviceToken;
import co.siten.myvtg.model.apigw.Notification;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.DataUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

/**
 *
 * @author daibq
 *
 */
@Repository("ApiGwDao")
@PropertySource(value = {"classpath:database.properties"})
public class ApiGwDao extends AbstractApigwDao<Object> {

    private static final Logger logger = Logger.getLogger(ApiGwDao.class.getName());
    private static final long DEACTIVE = 0L;
    private static final long ACTIVE = 1L;
    private static final long DELETED = 2L;

    /**
     * checkAgUserExist
     *
     * @param isdn
     * @return
     */
    public boolean checkAgUserExist(String isdn) {
        logger.info("begin method checkAgUserExist of ApiGwDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("select count(1) from AG_USER WHERE USER_NAME = :isdn and status = 1");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("isdn", isdn);
            logger.info("end method checkAgUserExist of ApiGwDao");
            return Integer.parseInt(query.list().get(0).toString()) > 0;
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     *
     * @param isdn
     * @param pass
     * @param salt
     * @return
     */
    public boolean changePass(String isdn, String pass, String salt) {
        logger.info("begin method changePass of ApiGwDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("UPDATE AG_USER SET PASSWORD = :pass,SALT_VALUE =:salt, CHANGED_TIME = sysdate WHERE USER_NAME = :isdn and status = 1");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("pass", pass);
            query.setParameter("salt", salt);
            logger.info("end method changePass of ApiGwDao");
            int result = query.executeUpdate();
            return result > 0;
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     *
     * @param isdn
     * @param pageSize
     * @param pageNum
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<NotificationBean> getListNotificationByIsdn(String isdn, Integer pageSize, Integer pageNum) {
        try {
            String sb = " SELECT new co.siten.myvtg.bean.NotificationBean(a.id, n.message, n.id,n.title, n.serviceCode,"
                    + " n.message, n.startTime, n.endTime, a.isRead,"
                    + " n.icon, n.image, n.notificationType, "
                    + "	a.insertTime, a.params, a.data) "
                    + " FROM co.siten.myvtg.model.apigw.Notification n, "
                    + "	co.siten.myvtg.model.apigw.AccountNotification a "
                    + " WHERE n.id = a.notificationId AND n.isSave = 1 "
                    + "		  AND a.isdn = :isdn " //AND n.endTime >=sysdate
                    + " ORDER BY a.insertTime desc";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum - 1));
                query.setMaxResults(pageSize);
            }
            return query.list();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * getExPriedActionRedeemLoginFirst
     *
     * @param session
     * @param isdn
     * @param addDay
     * @param isdnTmp
     *
     * @return
     * @throws Exception
     */
    public Date getExPriedActionRedeem(String isdn, int addDay, String isdnTmp) throws Exception {
        logger.info("Start getExPriedActionRedeem of BussinessDAO ");
        StringBuilder sql = new StringBuilder();
        Query query = null;
        if (DataUtil.isNullOrEmpty(isdnTmp)) {
            sql.append(" SELECT to_char(last_login_first + :addDay +1 ,'dd/MM/yyyy') FROM apigw.ag_user ag ");
            sql.append(" WHERE ag.user_name = :isdn");
        } else {
            sql.append(" SELECT to_char(update_date + :addDay +1 ,'dd/MM/yyyy') FROM sub_push_notify WHERE isdn = :isdn and param LIKE :isdnTmp and status =0 ORDER BY update_date desc ");
        }
        query = getSession().createSQLQuery(sql.toString());
        query.setParameter("addDay", addDay);
        query.setParameter("isdn", isdn);
        if (!DataUtil.isNullOrEmpty(isdnTmp)) {
//            query.setParameter("isdnTmp", String.format("%%%s%%", isdnTmp.trim(), "%%%s%%"));

            query.setParameter("isdnTmp", "%" + isdnTmp.trim() + "%");

        }

        if (!DataUtil.isNullOrEmpty(query.list())) {
            return DataUtil.convertStringToDate(query.list().get(0).toString(), "dd/MM/yyyy");
        }
        return null;
    }

    /**
     * checkLoginFirst
     *
     * @param isdn
     * @param checkPromotion
     * @return
     * @throws Exception
     */
    public boolean checkLoginFirst(String isdn) throws Exception {
        logger.info("Start checkIsdnByIsdn of BussinessDAO ");
        Long count = 0L;
        Query query = null;
        StringBuilder sql = new StringBuilder();
        count = 0L;
        logger.info("Start Kiem tra dang nhap lan dau");
        sql.append(" SELECT to_char(ag.last_login_time,'dd/mm/yyyy hh24:mi:ss'), ");
        sql.append(" to_char(ag.last_login_first,'dd/mm/yyyy hh24:mi:ss')");
        sql.append(" FROM apigw.ag_user ag WHERE ag.user_name = :isdn and ag.status =1");
        query = getSession().createSQLQuery(sql.toString());
        query.setParameter("isdn", isdn);
        List<Object[]> listResult = query.list();
        if (DataUtil.isNullOrEmpty(listResult)) {
            logger.info("isdn : " + isdn + " Chưa login");
        } else {
            String currLogin = DataUtil.isNullObject(listResult.get(0)[0]) ? "" : listResult.get(0)[0].toString();
            String loginFirst = DataUtil.isNullObject(listResult.get(0)[1]) ? "" : listResult.get(0)[1].toString();
            logger.info("LAST_LOGIN_TIME: " + currLogin);
            logger.info("LAST_LOGIN_FIRST: " + loginFirst);
            if (DataUtil.isNullOrEmpty(currLogin) && DataUtil.isNullOrEmpty(loginFirst)) {
                logger.info("isdn : " + isdn);
                logger.info("LAST_LOGIN_TIME:is null");
                logger.info("LAST_LOGIN_FIRST: is null ");
            } else if (!DataUtil.isNullOrEmpty(currLogin) && DataUtil.isNullOrEmpty(loginFirst)) {
                logger.info("isdn : " + isdn);
                logger.info("LAST_LOGIN_TIME: " + currLogin);
                logger.info("LAST_LOGIN_FIRST: is null ");
            } else if (!DataUtil.isNullOrEmpty(currLogin) && !DataUtil.isNullOrEmpty(loginFirst)) {
                logger.info("Login hien lan dau luc :" + loginFirst);
                logger.info("Login hien tai luc:" + currLogin);
                if (currLogin.equals(loginFirst)) {
                    count = 1L;
                    logger.info("isdn : " + isdn + " login lan dau ");
                } else {
                    logger.info("isdn : " + isdn + " login khong phai lan dau ");
                }
            }
        }

        logger.info("End Kiem tra dang nhap lan dau");
        return count > 0;

    }

    /**
     * getDeviceTokenByIsdn
     *
     * @param isdn
     * @return
     */
    public DeviceToken getDeviceTokenByIsdn(String isdn) {
        try {
            String sb = "SELECT dt FROM DeviceToken dt WHERE dt.isdn = :isdn ";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);

            return (DeviceToken) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * getListAccountNotifyByIsdnAndNoti
     *
     * @param isdn
     * @param notificationId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<AccountNotification> getListAccountNotifyByIsdnAndNoti(String isdn, Long notificationId) {
        try {
            String sb = " SELECT a from AccountNotification a "
                    + " WHERE a.isdn= :isdn and a.notificationId = :notificationId";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);
            query.setLong("notificationId", notificationId);
            return query.list();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * getListAccountNotifyByIsdnAndNoti
     *
     * @param isdn
     * @param notificationId
     * @param accNotificationId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<AccountNotification> getListAccountNotifyByIsdnAndNoti(String isdn, Long notificationId, Long accNotificationId) {
        try {
            String sb = " SELECT a from AccountNotification a "
                    + " WHERE a.isdn= :isdn and a.notificationId = :notificationId and a.id = :accNotificationId";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);
            query.setLong("notificationId", notificationId);
            query.setLong("accNotificationId", accNotificationId);
            return query.list();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * updateAccountNotification
     *
     * @param isdn
     * @param complaintId
     * @return
     */
    public int updateAccountNotification(String isdn, String complaintId) {
        try {
            String sb = "UPDATE ACCOUNT_NOTIFICATION  SET is_read = 1 WHERE ISDN =:isdn AND data LIKE :complaintId";
            Query query = getSession().createSQLQuery(sb);
            query.setString("isdn", isdn);
            query.setParameter("complaintId", "%" + complaintId + "%");
            int result = query.executeUpdate();
            return result;
        } catch (Exception e) {
            logger.error("error", e);
            return 0;
        }
    }

    /**
     * findNotificationById
     *
     * @param id
     * @return
     */
    public Notification findNotificationById(Long id) {
        try {
            String sb = " SELECT a from Notification a WHERE a.id= :id";
            Query query = getSession().createQuery(sb);
            query.setLong("id", id);
            return (Notification) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
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
    public void persist(Object entity) {
        super.persist(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object entity) {
        super.update(entity); //To change body of generated methods, choose Tools | Templates.
    }

    public void testRe(String isdn) {
        try {
            String sb = "DELETE FROM Device_Login  WHERE USER_NAME = :isdn ";
            Query query = getSession().createSQLQuery(sb);
            query.setString("isdn", isdn);
            int result = query.executeUpdate();
            logger.error("DELETE result : " + result);
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    public boolean checkResgister(String isdn) throws Exception {
        logger.info("Start checkResgister of BussinessDAO ");
        Query query = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT count(1) ");
        sql.append(" FROM apigw.ag_user ag WHERE ag.user_name = :isdn and status = 1");
        query = getSession().createSQLQuery(sql.toString());
        query.setParameter("isdn", isdn);
        return Integer.parseInt(query.list().get(0).toString()) <= 0;
    }

    //phuonghc 22062020
    public Long getTotalRecordNotificationVersion2() {
        String sql = "SELECT COUNT(*) FROM NOTIFICATION WHERE ACTION_TYPE_V2 is not null AND NOTIFICATION_TYPE = 12 AND STATUS IN (0,1)";
        try {
            Query query = getSession().createSQLQuery(sql);
            List result = query.list();
            if (result.isEmpty()) {
                return 0L;
            }
            return Long.valueOf(result.get(0).toString());
        } catch (NumberFormatException ne) {
            logger.error("An error occured when count total notification" + ne.getMessage());
            return 0L;
        }
    }

    public List<NotificationDtoV2> getAllActiveListNotificationVersion2(Integer from, Integer to) {
        List<NotificationDtoV2> result;
        StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT id notificationId, title, message, "
                + "                TO_CHAR(CREATED_TIME, 'dd/mm/yyyy hh24:mi:ss') createDate, "
                + "                notification_image_v2 notificationImage, topic_v2 topic, "
                + "                notification_type notificationType, link_v2 link, status, description_v2 description, time_v2 time, action_type_v2 actionType, "
                + "                RANK() OVER( ORDER BY CREATED_TIME DESC) myrank FROM NOTIFICATION "
                + "                WHERE status = 1 AND action_type_v2 is not null AND notification_type = 12) t");

        if (from != null && to != null) {
            sql.append(" WHERE myrank BETWEEN :from AND :to");
        }
        try {
            Query query = getSession().createSQLQuery(sql.toString())
                    .addScalar("notificationId", LongType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("message", StringType.INSTANCE)
                    .addScalar("createDate", StringType.INSTANCE)
                    .addScalar("time", IntegerType.INSTANCE)
                    .addScalar("link", StringType.INSTANCE)
                    .addScalar("notificationImage", StringType.INSTANCE)
                    .addScalar("topic", StringType.INSTANCE)
                    .addScalar("notificationType", StringType.INSTANCE)
                    .addScalar("status", StringType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("actionType", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(NotificationDtoV2.class));
            if (from != null && to != null) {
                query.setParameter("from", from);
                query.setParameter("to", to);
            }

            result = query.list();
            if (result.isEmpty()) {
                return new ArrayList<>();
            }
            return result;
        } catch (Exception e) {
            logger.error("An error occured when get list of Notification: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public int addNewDeviceToken(String camid, String deviceID, String token, int operating, String lang, String versionApp) {
        Session session = getSession();
        Long accNotifiId = getSequence("CAMID_DEVICE_TOKEN_SEQ");
        String sql = "INSERT INTO camid_device_token(ID, CAM_ID, DEVICE_ID,TOKEN,CREATE_DATE, LAST_UPDATE, OPERATING_SYSTEM, LANG,STATUS,VERSION_APP) VALUES (?,?,?,?,sysdate,sysdate,?,?,1,?)";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, accNotifiId);
        query.setParameter(1, camid == null ? "" : camid);
        query.setParameter(2, deviceID);
        query.setParameter(3, token);
        query.setParameter(4, operating);
        query.setParameter(5, lang);
        query.setParameter(6, versionApp == null ? "" : versionApp);
        try {
            int result = query.executeUpdate();
            session.flush();
            return result;
        } catch (HibernateException he) {
            logger.error("Cannot addNewDeviceToken version 2", he);
            return 0;
        }
    }

    public NotificationResult addNewNotificationVersion2(NotificationDtoV2 newNotification) {
        Session session = getSession();
        Long notificationId = this.getSequence("NOTIFICATION_SEQ");
        Long status = DEACTIVE;
        try {
            status = Long.parseLong(newNotification.getStatus());
        } catch (NumberFormatException e) {
            logger.info("Status is not a number, using status defaule DEACTIVE", e);
        }
        String sql = "INSERT INTO NOTIFICATION (ID, TITLE, MESSAGE, LINK_V2, CREATED_TIME, NOTIFICATION_IMAGE_V2, TOPIC_V2, NOTIFICATION_TYPE, STATUS, DESCRIPTION_V2, ACTION_TYPE_V2, TIME_V2) "
                + "VALUES (?,?,?,?,sysdate,?,?,?,?,?,?,?)";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, notificationId);
        query.setParameter(1, newNotification.getTitle());
        query.setParameter(2, newNotification.getMessage());
        query.setParameter(3, newNotification.getLink());
        query.setParameter(4, newNotification.getNotificationImage());
        query.setParameter(5, newNotification.getTopic());
        query.setParameter(6, newNotification.getNotificationType());
        query.setParameter(7, status);
        query.setParameter(8, newNotification.getDescription());
        query.setParameter(9, newNotification.getActionType());
        query.setParameter(10, newNotification.getTime());

        NotificationResult notifyRe = new NotificationResult();
        try {
            int result = query.executeUpdate();
            session.flush();
            notifyRe.setResult(result);
            notifyRe.setNotificationId(notificationId);
            return notifyRe;
        } catch (HibernateException he) {
            logger.error("Cannot add new notification version 2", he);
            notifyRe.setResult(0);
            return notifyRe;
        }

    }

    public int updateNotificationVersion2(NotificationDtoV2 notification) {
        Session session = getSession();
        Long status = 0L;
        try {
            status = Long.parseLong(notification.getStatus());
        } catch (NumberFormatException ne) {
            logger.info("Status is not a number using status default DEACTIVE", ne);
        }

        String sql = "UPDATE NOTIFICATION SET TITLE =:title, MESSAGE=:message, LINK_V2=:link, LAST_UPDATED_TIME = sysdate, "
                + "TIME_V2=:time, NOTIFICATION_IMAGE_V2=:notificationImage, TOPIC_V2=:topic, NOTIFICATION_TYPE=:notificationType, "
                + "STATUS=:status, DESCRIPTION_V2=:des, ACTION_TYPE_V2=:actionType "
                + "WHERE ID=:id";

        Query query = session.createSQLQuery(sql);
        query.setParameter("title", notification.getTitle());
        query.setParameter("message", notification.getMessage());
        query.setParameter("link", notification.getLink());
        query.setParameter("time", notification.getTime());
        query.setParameter("notificationImage", notification.getNotificationImage());
        query.setParameter("topic", notification.getTopic());
        query.setParameter("notificationType", notification.getNotificationType());
        query.setParameter("status", status);
        query.setParameter("des", notification.getDescription());
        query.setParameter("actionType", notification.getActionType());
        query.setParameter("id", notification.getNotificationId());
        try {
            int result = query.executeUpdate();
            session.flush();
            return result;
        } catch (HibernateException he) {
            logger.error("### Cannot update information for notification with id " + notification.getNotificationId() + " with reason: ", he);
            return 0;
        }
    }

    public int deleteNotificationVersion2(NotificationDtoV2 notificationDel) {
        Session session = getSession();
        String sql = "UPDATE NOTIFICATION SET STATUS=:status, DESCRIPTION_V2=:des "
                + "WHERE ID=:id";

        Query query = session.createSQLQuery(sql);
        query.setParameter("status", notificationDel.getStatus());
        query.setParameter("des", notificationDel.getDescription());
        query.setParameter("id", notificationDel.getNotificationId());

        try {
            int result = query.executeUpdate();
            session.flush();
            return result;
        } catch (HibernateException he) {
            logger.error("Cannot delete notification with id = " + notificationDel.getNotificationId() + " with reason: ", he);
            return 0;
        }
    }

    public int updateIsReadNotificationV2(String isdn, String notificationId, String notificationType) {
        return 0;
    }

    public int saveLogAfterSendNotification(String isdn, String notificationId) {
        Session session = getSession();
        Long accNotifiId = getSequence("ACCOUNT_NOTIFICATION_SEQ");
        String sql = "INSERT INTO ACCOUNT_NOTIFICATION(ID, ISDN, NOTIFICATION_ID, INSERT_TIME, IS_READ, PARAMS) VALUES (?,?,?,sysdate,?,?)";
        Query query = session.createSQLQuery(sql);
        query.setParameter(0, accNotifiId);
        query.setParameter(1, isdn);
        query.setParameter(2, notificationId);
        query.setParameter(3, 1); // read!
        query.setParameter(4, "[]");

        try {
            int result = query.executeUpdate();
            session.flush();
            return result;
        } catch (HibernateException he) {
            logger.error("Cannot saveLogAfterSendNotification version 2", he);
            return 0;
        }
    }

    public List<NotificationDtoV2> getAllListNotificationVersion2(Long from, Long to) {
        List<NotificationDtoV2> result;
        StringBuilder sql = new StringBuilder("SELECT * FROM (SELECT n.id notificationId, n.title, n.message, "
                + "                TO_CHAR(n.CREATED_TIME, 'dd/mm/yyyy hh24:mi:ss') createDate, "
                + "                n.notification_image_v2 notificationImage, n.topic_v2 topic, "
                + "                n.notification_type notificationType, n.link_v2 link, n.status, n.description_v2 description, n.time_v2 time, n.action_type_v2 actionType, "
                + "                RANK() OVER( ORDER BY n.CREATED_TIME DESC) myrank FROM NOTIFICATION n "
                + "                WHERE status in (1,0) AND action_type_v2 is not null) t");

        if (from != null && to != null) {
            sql.append(" WHERE myrank BETWEEN :from AND :to");
        }
        try {
            Query query = getSession().createSQLQuery(sql.toString())
                    .addScalar("notificationId", LongType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("message", StringType.INSTANCE)
                    .addScalar("createDate", StringType.INSTANCE)
                    .addScalar("time", IntegerType.INSTANCE)
                    .addScalar("link", StringType.INSTANCE)
                    .addScalar("notificationImage", StringType.INSTANCE)
                    .addScalar("topic", StringType.INSTANCE)
                    .addScalar("notificationType", StringType.INSTANCE)
                    .addScalar("status", StringType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("actionType", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(NotificationDtoV2.class));
            if (from != null && to != null) {
                query.setParameter("from", from);
                query.setParameter("to", to);
            }

            result = query.list();
            if (result.isEmpty()) {
                return new ArrayList<>();
            }
            return result;
        } catch (Exception e) {
            logger.error("An error occured when get list of Notification: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public NotificationDtoV2 getDetailNotificationVersion2(String notificationId) {
        String sql = "SELECT ID notificationId, TITLE title, MESSAGE message, "
                + "TO_CHAR(CREATED_TIME, 'dd/mm/yyyy hh24:mi:ss') createDate, "
                + "notification_image_v2 notificationImage, topic_v2 topic, "
                + "notification_type notificationType, link_v2 link, status, "
                + "description_v2 description, time_v2 time, action_type_v2 actionType FROM NOTIFICATION "
                + "WHERE id=:id";

        Query query = getSession().createSQLQuery(sql)
                .addScalar("notificationId", LongType.INSTANCE)
                .addScalar("title", StringType.INSTANCE)
                .addScalar("message", StringType.INSTANCE)
                .addScalar("createDate", StringType.INSTANCE)
                .addScalar("time", IntegerType.INSTANCE)
                .addScalar("link", StringType.INSTANCE)
                .addScalar("notificationImage", StringType.INSTANCE)
                .addScalar("topic", StringType.INSTANCE)
                .addScalar("notificationType", StringType.INSTANCE)
                .addScalar("status", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .addScalar("actionType", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(NotificationDtoV2.class));
        query.setParameter("id", notificationId);
        List<NotificationDtoV2> temp = query.list();
        if (temp != null && temp.size() > 0) {
            return temp.get(0);
        }
        return new NotificationDtoV2();
    }

    public NotificationAccountDtoV2 findIsReadNotificationByIsdn(String isdn, Long notificationId) {
        String sql = "SELECT ID accountNotifyId, IS_READ isRead FROM ACCOUNT_NOTIFICATION WHERE ISDN=:isdn AND NOTIFICATION_ID=:noti";
        Query query = getSession().createSQLQuery(sql)
                .addScalar("accountNotifyId", LongType.INSTANCE)
                .addScalar("isRead", IntegerType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(NotificationAccountDtoV2.class));
        query.setParameter("isdn", isdn);
        query.setParameter("noti", notificationId);
        List<NotificationAccountDtoV2> listNotifiAcc = query.list();

        if (listNotifiAcc != null && listNotifiAcc.size() > 0) {
            return listNotifiAcc.get(0);
        }
        return new NotificationAccountDtoV2();
    }

    private Long getSequence(String sequenceName) {
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

    public List<CamIdNotificationBean> wsGetListCamIDNotification(String camid, Integer pageSize, Integer pageNum, boolean isClearAllBefore, boolean isBlockNotification, boolean isUnReadStatus, String osDevice, String deviceId) {
        try {
            StringBuilder callSql = new StringBuilder("select * from( select cn.sub_id subId, cn.passive_type_id passiveTypeId, cnc.notification_id notifyId, cn.button_title buttonTitle, cn.notification_type type, cn.title titleEn, ");
            callSql.append("cn.title_kh titleKh, cnt.name actionType, cn.link, cn.create_date time, cn.icon_url iconUrl, cn.message_en valueEn, cn.message_kh valueKh, ");
            callSql.append("cnc.read_status readStatus, cn.action_object_id actionObjectId, cn.feature_image featureImage, cn.video_url videoUrl, cn.is_exchange isExchange,cn.id_Message idMessage, \n"
                    + " ROW_NUMBER() OVER (PARTITION BY notification_id ORDER BY notification_id DESC ) AS ROW_NUM,cn.create_date,cnc.CREATED_TIME ");

            callSql.append("from camid_notification_customer cnc inner join camid_notification cn on cnc.notification_id = cn.id ");
            callSql.append("left join camid_notification_type cnt on cn.action_type_id = cnt.id ");
            callSql.append("where cnc.camid =:camid and cnc.device_id=:deviceId and cn.status <> 0 and cn.os_device in (2,:os) ");
            if (isClearAllBefore) {
                callSql.append("and cnc.CREATED_TIME >= to_date((select to_CHAR(clear_date,'dd/MM/yyyy hh24:mi:ss') from camid_customer_clear_all where camid =:camid), 'dd/MM/yyyy hh24:mi:ss') ");
            }
            if (isUnReadStatus) {
                callSql.append("and cnc.read_status = 0 ");
            }
            if (isBlockNotification) {
                callSql.append("and cn.create_date < (select block_date from camid_customer_allow_receive where camid =:camid) ");
            }
            callSql.append(") WHERE row_num=1 order by create_date DESC");
            logger.info("Sql of getNotificationDetails " + callSql);

            SQLQuery query = getSession().createSQLQuery(callSql.toString());
            query.addScalar("subId", new StringType());
            query.addScalar("passiveTypeId", new StringType());
            query.addScalar("notifyId", new LongType());
            query.addScalar("type", new StringType());
            query.addScalar("valueEn", new StringType());
            query.addScalar("valueKh", new StringType());
            query.addScalar("link", new StringType());
            query.addScalar("readStatus", new IntegerType());
            query.addScalar("iconUrl", new StringType());
            query.addScalar("time", new StringType());
            query.addScalar("titleEn", new StringType());
            query.addScalar("titleKh", new StringType());
            query.addScalar("actionType", new StringType());
            query.addScalar("buttonTitle", new StringType());
            query.addScalar("actionObjectId", new StringType());
            query.addScalar("featureImage", new StringType());
            query.addScalar("videoUrl", new StringType());
            query.addScalar("isExchange", new LongType());
            query.addScalar("idMessage", new StringType());
            query.setResultTransformer(Transformers.aliasToBean(CamIdNotificationBean.class));
            query.setParameter("camid", camid);
            query.setParameter("os", osDevice);
            query.setParameter("deviceId", deviceId);

            if (pageSize != 0 && pageNum != null && !isUnReadStatus) {
                query.setFirstResult(pageSize * (pageNum - 1));
                query.setMaxResults(pageSize);
            }

            List<CamIdNotificationBean> listDetails = query.list();
            if (CommonUtil.isEmpty(listDetails)) {
                return new ArrayList<>();
            }

            return listDetails;
        } catch (Exception e) {
            logger.error("### An error occured while get list CamIdNotification with camid=" + camid, e);
        }
        return new ArrayList<CamIdNotificationBean>();
    }

    public int wsUpdateIsReadCamIDNotification(long notifyId, String camid) {
        try {
//            Long id = this.getSequence("CAMID_NOTIFICATION_CUS_SEQ");
//            String sql = "insert into camid_notification_customer(id, camid, notification_id, read_status) "
//                    + "values (?, ?, ?, 1)";
            String sql = "update camid_notification_customer set read_status = 1 where camid =:camId and NOTIFICATION_ID =:notificationId";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("camId", camid);
            query.setParameter("notificationId", notifyId);
            int result = query.executeUpdate();
            getSession().flush();
            return result;
        } catch (Exception e) {
            logger.error("### An error occured while update isRead with isdn=" + camid, e);
            return 0;
        }
    }

    public NotificationResult addNewCamIdNotification(CamIdNotificationCMSBean camIdNotify) {
        Long id = this.getSequence("CAMID_NOTIFICATION_SEQ");
        StringBuilder sql = new StringBuilder("insert into CAMID_NOTIFICATION("
                + "id, notification_type, action_type_id, link, button_title, title, message_en, message_kh, time, sub_id, schedule, create_date, update_date, language, feature_image, status, interval, title_kh, path_file_sub_id, "
                + "action_object_id, video_url, is_exchange, icon_url, list_test_phone, test_status, os_device, passive_type_id, TIME_RANGE_PASSIVE ) values (");
        sql.append(":id, :notificationType, :actionTypeId, :link, :buttonTitle, :title, :messageEn, :messageKh, :time, :subId, to_date(:schedule, 'dd/MM/yyyy hh24:mi:ss'), sysdate, sysdate, :language, :featureImage, :status, "
                + " :interval, :titleKh, :pathFileSubId, :actionObjectId, :videoUrl, :isExchange, :iconUrl, :listTestPhone, :testStatus, :osDevice, :passiveTypeId, :passiveRange");
        sql.append(")");

        Query query = getSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        query.setParameter("notificationType", camIdNotify.getNotificationType());
        query.setParameter("actionTypeId", camIdNotify.getActionTypeId(), LongType.INSTANCE); // Ensure that after autoBoxing not get null pointer exception
        query.setParameter("link", camIdNotify.getLink());
        query.setParameter("buttonTitle", camIdNotify.getButtonTitle());
        query.setParameter("title", camIdNotify.getTitleEn());
        query.setParameter("messageEn", camIdNotify.getMessageEn());
        query.setParameter("messageKh", camIdNotify.getMessageKh());
        query.setParameter("time", camIdNotify.getTime());
        query.setParameter("subId", camIdNotify.getSubId());
        query.setParameter("schedule", camIdNotify.getSchedule());
        query.setParameter("language", camIdNotify.getLanguageOfNotify());
        query.setParameter("featureImage", camIdNotify.getFeatureImage());
        query.setParameter("status", camIdNotify.getStatus());
        query.setParameter("interval", camIdNotify.getInterval());
        query.setParameter("titleKh", camIdNotify.getTitleKh());
        query.setParameter("pathFileSubId", camIdNotify.getPathFileSubId());
        query.setParameter("actionObjectId", camIdNotify.getActionObjectId());
        query.setParameter("videoUrl", camIdNotify.getVideoUrl());
        query.setParameter("isExchange", camIdNotify.getIsExchange(), LongType.INSTANCE);
        query.setParameter("iconUrl", camIdNotify.getIconUrl());
        query.setParameter("listTestPhone", camIdNotify.getTestPhones());
        query.setParameter("testStatus", camIdNotify.getTestStatus());
        query.setParameter("osDevice", camIdNotify.getOsDevice());
        query.setParameter("passiveTypeId", camIdNotify.getPassiveTypeId(), LongType.INSTANCE);
        query.setParameter("passiveRange", camIdNotify.getPassiveRange());

//        query.setParameter("PRIORITY", camIdNotify.getPriority());
//        query.setParameter("CHANNELID", camIdNotify.getChannelID());
//        query.setParameter("SAFECHILDRENTYPE", camIdNotify.getSafeChildrenType());
//        query.setParameter("DEVICE_ID", camIdNotify.getDevice_id());
//        query.setParameter("LAT", camIdNotify.getLat());
//        query.setParameter("LNG", camIdNotify.getLng());
//        query.setParameter("CRITICAL_SOUND", camIdNotify.getCriticalSound());
//        query.setParameter("NAME_SOUND", camIdNotify.getNameSound());
//        query.setParameter("VOLUME_SOUND", camIdNotify.getVolumeSound());        
        NotificationResult notificationResult = new NotificationResult();
        try {
            int resultInsert = query.executeUpdate();
            getSession().flush();
            notificationResult.setResult(resultInsert);
            notificationResult.setNotificationId(id);
            return notificationResult;
        } catch (HibernateException he) {
            logger.error("### Cannot addNewCamIdNotification", he);
            notificationResult.setResult(0);
            return notificationResult;
        }
    }

    public NotificationResult addNewCamIdNotificationSend(CamIdNotificationCMSBean camIdNotify) {
        Long id = this.getSequence("CAMID_NOTIFICATION_SEQ");
        StringBuilder sql = new StringBuilder("insert into CAMID_NOTIFICATION(id, notification_type, action_type_id, link, button_title, title, message_en, message_kh, time, sub_id, schedule, create_date, update_date, language, feature_image, status, interval, title_kh, path_file_sub_id, action_object_id, video_url, is_exchange, icon_url, list_test_phone, test_status,PRIORITY,CHANNELID,SAFECHILDRENTYPE,DEVICE_ID,LAT,LNG,CRITICAL_SOUND,NAME_SOUND,VOLUME_SOUND,OS_DEVICE,ID_MESSAGE ) values (");
        sql.append(":id, :notificationType, :actionTypeId, :link, :buttonTitle, :title, :messageEn, :messageKh, :time, :subId, to_date(:schedule, 'dd/MM/yyyy hh24:mi:ss'), sysdate, sysdate, :language, :featureImage, :status,  :interval, :titleKh, :pathFileSubId, :actionObjectId, :videoUrl, :isExchange, :iconUrl, :listTestPhone, :testStatus,:PRIORITY,:CHANNELID,:SAFECHILDRENTYPE,:DEVICE_ID,:LAT,:LNG,:CRITICAL_SOUND,:NAME_SOUND,:VOLUME_SOUND,2,:ID_MESSAGE");
        sql.append(")");
        Query query = this.getSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        query.setParameter("notificationType", camIdNotify.getNotificationType());
        query.setParameter("actionTypeId", camIdNotify.getActionTypeId(), LongType.INSTANCE);
        query.setParameter("link", camIdNotify.getLink());
        query.setParameter("buttonTitle", camIdNotify.getButtonTitle());
        query.setParameter("title", camIdNotify.getTitleEn());
        query.setParameter("messageEn", camIdNotify.getMessageEn());
        query.setParameter("messageKh", camIdNotify.getMessageKh());
        query.setParameter("time", camIdNotify.getTime());
        query.setParameter("subId", camIdNotify.getSubId());
        query.setParameter("schedule", camIdNotify.getSchedule());
        query.setParameter("language", camIdNotify.getLanguageOfNotify());
        query.setParameter("featureImage", camIdNotify.getFeatureImage());
        query.setParameter("status", camIdNotify.getStatus());
        query.setParameter("interval", camIdNotify.getInterval());
        query.setParameter("titleKh", camIdNotify.getTitleKh());
        query.setParameter("pathFileSubId", camIdNotify.getPathFileSubId());
        query.setParameter("actionObjectId", camIdNotify.getActionObjectId());
        query.setParameter("videoUrl", camIdNotify.getVideoUrl());
        query.setParameter("isExchange", camIdNotify.getIsExchange(), LongType.INSTANCE);
        query.setParameter("iconUrl", camIdNotify.getIconUrl());
        query.setParameter("listTestPhone", camIdNotify.getTestPhones());
        query.setParameter("testStatus", camIdNotify.getTestStatus());
        query.setParameter("PRIORITY", camIdNotify.getPriority());
        query.setParameter("CHANNELID", camIdNotify.getChannelID());
        query.setParameter("SAFECHILDRENTYPE", camIdNotify.getSafeChildrenType());
        query.setParameter("DEVICE_ID", camIdNotify.getDevice_id());
        query.setParameter("LAT", camIdNotify.getLat());
        query.setParameter("LNG", camIdNotify.getLng());
        query.setParameter("CRITICAL_SOUND", camIdNotify.getCriticalSound());
        query.setParameter("NAME_SOUND", camIdNotify.getNameSound());
        query.setParameter("VOLUME_SOUND", camIdNotify.getVolumeSound());
        query.setParameter("ID_MESSAGE", camIdNotify.getIsMessage());
        NotificationResult notificationResult = new NotificationResult();
        try {
            int resultInsert = query.executeUpdate();
            getSession().flush();
            notificationResult.setResult(resultInsert);
            notificationResult.setNotificationId(id);
            return notificationResult;
        } catch (HibernateException he) {
            logger.error("### Cannot addNewCamIdNotification", he);
            notificationResult.setResult(0);
            return notificationResult;
        }
    }

    public int updateCamIdNotification(CamIdNotificationCMSBean camIdNotify) {
        String sql = "update CAMID_NOTIFICATION set notification_type=:type, action_type_id=:actionTypeId, link=:link, button_title=:buttonTitle, title=:titleEn, "
                + " message_en =:messageEn, message_kh =:messageKh, time=:time, sub_id=:subId, schedule= to_date(:schedule, 'dd/MM/yyyy hh24:mi:ss'), update_date = sysdate, language=:language, feature_image=:featureImage, "
                + "status =:status, interval=:interval, title_kh=:titleKh, path_file_sub_id=:pathFileSubId, action_object_id=:actionObjectId, video_url=:videoUrl, is_exchange=:isExchange, icon_url=:iconUrl, list_test_phone=:listTestPhone, test_status=:testStatus,"
                + "os_device=:osDevice, passive_type_id=:passiveTypeId, TIME_RANGE_PASSIVE=:passiveRange where id=:id";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("type", camIdNotify.getNotificationType());
        query.setParameter("actionTypeId", camIdNotify.getActionTypeId(), LongType.INSTANCE); // avoid null pointer exception
        query.setParameter("link", camIdNotify.getLink());
        query.setParameter("buttonTitle", camIdNotify.getButtonTitle());
        query.setParameter("titleEn", camIdNotify.getTitleEn());
        query.setParameter("titleKh", camIdNotify.getTitleKh());
        query.setParameter("messageEn", camIdNotify.getMessageEn());
        query.setParameter("messageKh", camIdNotify.getMessageKh());
        query.setParameter("time", camIdNotify.getTime());
        query.setParameter("subId", camIdNotify.getSubId());
        query.setParameter("schedule", camIdNotify.getSchedule());
        query.setParameter("language", camIdNotify.getLanguageOfNotify());
        query.setParameter("featureImage", camIdNotify.getFeatureImage());
        query.setParameter("status", camIdNotify.getStatus());
        query.setParameter("interval", camIdNotify.getInterval());
        query.setParameter("pathFileSubId", camIdNotify.getPathFileSubId());
        query.setParameter("actionObjectId", camIdNotify.getActionObjectId());
        query.setParameter("videoUrl", camIdNotify.getVideoUrl());
        query.setParameter("isExchange", camIdNotify.getIsExchange(), LongType.INSTANCE);
        query.setParameter("iconUrl", camIdNotify.getIconUrl());
        query.setParameter("listTestPhone", camIdNotify.getTestPhones());
        query.setParameter("testStatus", camIdNotify.getTestStatus());
        query.setParameter("osDevice", camIdNotify.getOsDevice());
        query.setParameter("passiveTypeId", camIdNotify.getPassiveTypeId(), LongType.INSTANCE);
        query.setParameter("passiveRange", camIdNotify.getPassiveRange());

        query.setParameter("id", camIdNotify.getNotifyId());

        try {
            int result = query.executeUpdate();
            getSession().flush();
            return result;
        } catch (HibernateException he) {
            logger.error("### Cannot update information for CamId notification with id " + camIdNotify.getNotifyId() + " with reason: ", he);
            return 0;
        }
    }

    public int deleteCamIdNotification(Long id) {
        String sql = "update CAMID_NOTIFICATION set status=0, update_date = sysdate where id=:id";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("id", id);

        try {
            int result = query.executeUpdate();
            getSession().flush();
            return result;
        } catch (HibernateException he) {
            logger.error("### Cannot delete for CamId notification with id " + id + " with reason: ", he);
            return 0;
        }
    }

    public List<CamIdActionTypeBean> getListCamIdNotificationType(String type) {
        List<CamIdActionTypeBean> camNotifyTypeBeanList = new ArrayList<CamIdActionTypeBean>();
        String sql = "select id, name, value from camid_notification_type where status = 1 and type =:type order by id";
        Query query = getSession().createSQLQuery(sql)
                .addScalar("id", new LongType())
                .addScalar("name", new StringType())
                .addScalar("value", new StringType())
                .setResultTransformer(Transformers.aliasToBean(CamIdActionTypeBean.class));
        query.setParameter("type", type);
        try {
            camNotifyTypeBeanList = query.list();
        } catch (Exception e) {
            logger.error("### An error occured while get list CamidNotificationType", e);
            camNotifyTypeBeanList = new ArrayList<CamIdActionTypeBean>();
        }
        return camNotifyTypeBeanList;
    }

    public List<CamIdNotificationCMSBean> getAllCamIdNotification(int pageNumber, int pageSize, Date from, Date to, String status, String keyWord) {
        List<CamIdNotificationCMSBean> result = new ArrayList<CamIdNotificationCMSBean>();
        StringBuilder sql = new StringBuilder();
        try {

            List<Object> parameters = new ArrayList<>();

            sql.append("select cn.id notifyId, cn.notification_type notificationType, cnt.value actionType, cn.action_type_id actionTypeId, cn.link link, cn.button_title buttonTitle, cn.title titleEn, "
                    + "cn.message_en messageEn, cn.message_kh messageKh, cn.time time, cn.title_kh titleKh, cn.os_device osDevice, cn.passive_type_id passiveTypeId, "
                    + "cn.sub_id subId, to_char(cn.schedule, 'dd/MM/yyyy hh24:mi:ss') schedule, to_char(cn.create_date, 'dd/MM/yyyy hh24:mi:ss')  createDate, to_char(cn.update_date, 'dd/MM/yyyy hh24:mi:ss') updateDate, "
                    + "cn.language languageOfNotify, cn.status, cn.feature_image featureImage, cn.create_date, cn.interval, cn.path_file_sub_id pathFileSubId, cn.action_object_id actionObjectId, cn.video_url videoUrl, "
                    + "cn.is_exchange isExchange, cn.icon_url iconUrl, cn.list_test_phone listTestPhone, nvl(cn.test_status, 1) testStatus "
                    + "from camid_notification cn left join camid_notification_type cnt on cn.action_type_id = cnt.id where cn.status not in (0, 5) ");

            if (!DataUtil.isNullObject(from)) {
                sql.append("and cn.update_date >= ? ");
                parameters.add(from);
            }

            if (!DataUtil.isNullObject(to)) {
                sql.append("and cn.update_date < ?");
                parameters.add(to);
            }

            if (!DataUtil.isNullOrEmpty(status)) {
                sql.append("and cn.status = ? ");
                parameters.add(Long.valueOf(status));
            }

            if (!DataUtil.isNullOrEmpty(keyWord)) {
                sql.append(" and (UPPER(cn.title) like ? or UPPER(cn.title_kh) like ?) ");
                parameters.add("%" + keyWord + "%");
                parameters.add("%" + keyWord + "%");
            }

            sql.append("order by create_date desc");

            Query query = getSession().createSQLQuery(sql.toString())
                    .addScalar("notifyId", new LongType())
                    .addScalar("notificationType", new StringType())
                    .addScalar("actionType", new StringType())
                    .addScalar("actionTypeId", new LongType())
                    .addScalar("link", new StringType())
                    .addScalar("buttonTitle", new StringType())
                    .addScalar("titleEn", new StringType())
                    .addScalar("titleKh", new StringType())
                    .addScalar("messageEn", new StringType())
                    .addScalar("messageKh", new StringType())
                    .addScalar("time", new LongType())
                    .addScalar("subId", new StringType())
                    .addScalar("schedule", new StringType())
                    .addScalar("createDate", new StringType())
                    .addScalar("updateDate", new StringType())
                    .addScalar("languageOfNotify", new StringType())
                    .addScalar("status", new LongType())
                    .addScalar("featureImage", new StringType())
                    .addScalar("interval", new LongType())
                    .addScalar("pathFileSubId", new StringType())
                    .addScalar("actionObjectId", new StringType())
                    .addScalar("videoUrl", new StringType())
                    .addScalar("isExchange", new LongType())
                    .addScalar("iconUrl", new StringType())
                    .addScalar("listTestPhone", new StringType())
                    .addScalar("testStatus", new LongType())
                    .addScalar("osDevice", new LongType())
                    .addScalar("passiveTypeId", new LongType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdNotificationCMSBean.class));

            for (int i = 0; i < parameters.size(); i++) {
                query.setParameter(i, parameters.get(i));
            }

            if (pageNumber > 0) {
                query.setFirstResult(pageSize * (pageNumber - 1));
                query.setMaxResults(pageSize);
            }
            result = query.list();
        } catch (Exception e) {
            logger.error("### An error occured while get list getAllCamIdNotification", e);
            result = new ArrayList<CamIdNotificationCMSBean>();
        }
        return result;
    }

    public boolean clearAllCamIdNotification(String camId) {
        String tableName = "camid_customer_clear_all";
        Long id = this.findIdOfRecordLastTimeUpdateNotificationWithCamId(camId, tableName);
        String sql = "";
        if (id > 0) {
            sql = "update camid_customer_clear_all set update_date = sysdate, clear_date = sysdate where id =? and camid =?";
        } else {
            id = this.getSequence("CAMID_CUSTOMER_SEQ");
            sql = "insert into camid_customer_clear_all(id, camid, created_date, update_date, status, clear_date) "
                    + "values(?, ?, sysdate, sysdate, 1, sysdate)";
        }
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, id);
        query.setParameter(1, camId);
        try {
            int result = query.executeUpdate();
            getSession().flush();
            return result == 1;
        } catch (HibernateException he) {
            logger.error("### Cannot clean All for camId " + camId + " with reason: ", he);
            return false;
        }
    }

    public boolean isClearAllBefore(String camId) {
        String sql = "select count(*) from camid_customer_clear_all where camid =:camId";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("camId", camId);
        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString()) > 0;
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from camId" + camId + " is not a number", ne);
        } catch (Exception e) {
            logger.error("### An error occured while find id for camId=" + camId, e);
        }
        return false;
    }

    public CamIdDeviceToken getCamIdDeviceTokenByCamId(String camId, String deviceId) {
        try {
            StringBuilder sb = new StringBuilder("SELECT dt FROM CamIdDeviceToken dt WHERE 1=1 ");
            if (StringUtils.isNotEmpty(camId)) {
                sb.append("and camId=:camId ");
            }
            if (StringUtils.isNotEmpty(deviceId)) {
                sb.append("and deviceId=:deviceId ");
            }
            Query query = getSession().createQuery(sb.toString());
            if (StringUtils.isNotEmpty(camId)) {
                query.setString("camId", camId);
            }
            if (StringUtils.isNotEmpty(deviceId)) {
                query.setString("deviceId", deviceId);
            }
            List<CamIdDeviceToken> result = query.list();
            if (result != null && result.size() > 0) {
                return result.get(0);
            }
            return null;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public boolean isReadNotifyBefore(String camId, Long notifyId) {
        String sql = "select read_status from camid_notification_customer where camid =:camId and notification_id=:notifyId ";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("camId", camId);
        query.setParameter("notifyId", notifyId);
        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString()) > 0;
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from camId" + camId + " is not a number");
        } catch (Exception e) {
            logger.error("### An error occured while find id for camId=" + camId, e);
        }
        return false;
    }

    public CamIdNotificationCMSBean getCamIdNotificationById(String camId) {
        try {
            String sql = "select * from (select cn.id notifyId, cn.notification_type notificationType, cnt.value actionType, cn.action_type_id actionTypeId, cn.link link, cn.button_title buttonTitle, cn.title titleEn, "
                    + "cn.message_en messageEn, cn.message_kh messageKh, cn.time time, cn.title_kh titleKh, "
                    + "cn.sub_id subId, to_char(cn.schedule, 'dd/MM/yyyy hh24:mi:ss') schedule, to_char(cn.create_date, 'dd/MM/yyyy hh24:mi:ss') createDate, to_char(cn.update_date, 'dd/MM/yyyy hh24:mi:ss') updateDate, "
                    + "cn.language languageOfNotify, cn.status, cn.feature_image featureImage, cn.interval, cn.path_file_sub_id pathFileSubId, action_object_id actionObjectId, video_url videoUrl, is_exchange isExchange, "
                    + "icon_url iconUrl, cn.list_test_phone listTestPhone, nvl(cn.test_status, 1) testStatus, os_device osDevice, PASSIVE_TYPE_ID passiveTypeId, TIME_RANGE_PASSIVE passiveRange "
                    + "from camid_notification cn left join camid_notification_type cnt on cn.action_type_id = cnt.id where cn.status <> 0 and cn.id =:camId)";
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("notifyId", new LongType())
                    .addScalar("notificationType", new StringType())
                    .addScalar("actionType", new StringType())
                    .addScalar("actionTypeId", new LongType())
                    .addScalar("link", new StringType())
                    .addScalar("buttonTitle", new StringType())
                    .addScalar("titleEn", new StringType())
                    .addScalar("titleKh", new StringType())
                    .addScalar("messageEn", new StringType())
                    .addScalar("messageKh", new StringType())
                    .addScalar("time", new LongType())
                    .addScalar("subId", new StringType())
                    .addScalar("schedule", new StringType())
                    .addScalar("createDate", new StringType())
                    .addScalar("updateDate", new StringType())
                    .addScalar("languageOfNotify", new StringType())
                    .addScalar("status", new LongType())
                    .addScalar("featureImage", new StringType())
                    .addScalar("interval", new LongType())
                    .addScalar("pathFileSubId", new StringType())
                    .addScalar("actionObjectId", new StringType())
                    .addScalar("videoUrl", new StringType())
                    .addScalar("isExchange", new LongType())
                    .addScalar("iconUrl", new StringType())
                    .addScalar("listTestPhone", new StringType())
                    .addScalar("testStatus", new LongType())
                    .addScalar("osDevice", new LongType())
                    .addScalar("passiveTypeId", new LongType())
                    .addScalar("passiveRange", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdNotificationCMSBean.class));

            query.setParameter("camId", Long.valueOf(camId));

            List<CamIdNotificationCMSBean> result = query.list();
            if (result != null && !result.isEmpty()) {
                return result.get(0);
            }
            logger.info("### Cannot find any camidNotification, return empty object");
            return new CamIdNotificationCMSBean();
        } catch (Exception e) {
            logger.error("### An error occured while getCamIdNotificationById:" + camId, e);
            return new CamIdNotificationCMSBean();
        }
    }

    public Long getTotalRecordCamIdNotify() {
        String sql = "select count(*) from camid_notification where status <> 0";
        try {
            Query query = getSession().createSQLQuery(sql);
            List result = query.list();
            if (result.isEmpty()) {
                return 0L;
            }
            return Long.valueOf(result.get(0).toString());
        } catch (NumberFormatException ne) {
            logger.error("An error occured when count total notification" + ne.getMessage());
            return 0L;
        }
    }

    public boolean blockReceiveCamidNotification(String camId, String blockReceive) {
        String tableName = "camid_customer_allow_receive";
        Long id = this.findIdOfRecordLastTimeUpdateNotificationWithCamId(camId, tableName);
        String sql = "";
        if (id > 0) {
            sql = "update " + tableName + " set update_date = sysdate, block_date = sysdate, status =? where id=? and camid=? ";
        } else {
            sql = "insert into " + tableName + " (id, camid, created_date, update_date, status, block_date) values"
                    + " (?, ?, sysdate, sysdate, 1, sysdate)";
        }
        Query query = getSession().createSQLQuery(sql);
        if (id > 0) {
            query.setParameter(0, blockReceive);
            query.setParameter(1, id);
            query.setParameter(2, camId);
        } else {
            id = this.getSequence("CAMID_CUSTOMER_BLOCK_SEQ");
            query.setParameter(0, id);
            query.setParameter(1, camId);
        }

        try {
            int result = query.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (HibernateException he) {
            logger.error("### An error occured while block receive id for camId=" + camId, he);
        } catch (Exception e) {
            logger.error("### An error occured while block receive id for camId=" + camId, e);
        }
        return false;
    }

    public boolean isBlockNotification(String camId) {
        String sql = "select status from camid_customer_allow_receive where camid =:camId";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("camId", camId);
        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString()) > 0;
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from camId" + camId + " is not a number", ne);
        } catch (Exception e) {
            logger.error("### An error occured while find id for camId=" + camId, e);
        }
        return false;
    }

    //14.08.2021 update flow impact notification for unknow account
    public List<CamIdNotificationBean> wsGetListCamIDNotificationUnknow(String deviceId, Integer pageSize, Integer pageNum, boolean isClearAllBefore, boolean isUnReadStatus, boolean isBlock) {
        try {
            StringBuilder callSql = new StringBuilder("select * from( select cnc.notification_id notifyId, cn.button_title buttonTitle, cn.notification_type type, cn.title titleEn, ");
            callSql.append("cn.title_kh titleKh, cnt.name actionType, cn.link, cn.create_date time, cn.icon_url iconUrl, cn.message_en valueEn, cn.message_kh valueKh, ");
            callSql.append("cnc.read_status readStatus, cn.action_object_id actionObjectId, cn.feature_image featureImage, cn.video_url videoUrl, cn.is_exchange isExchange,cn.id_Message idMessage, \n"
                    + "            ROW_NUMBER() OVER (PARTITION BY notification_id ORDER BY notification_id DESC ) AS ROW_NUM,cn.create_date,cnc.CREATED_TIME ");

            callSql.append("from camid_notification_customer cnc inner join camid_notification cn on cnc.notification_id = cn.id ");
            callSql.append("left join camid_notification_type cnt on cn.action_type_id = cnt.id ");
            callSql.append("where cnc.device_id =:deviceId and cn.status <> 0 ");
            if (isClearAllBefore) {
                callSql.append("and cnc.CREATED_TIME >= to_date((select to_CHAR(clear_date,'dd/MM/yyyy hh24:mi:ss') from camid_customer_clear_all where device_id =:deviceId), 'dd/MM/yyyy hh24:mi:ss') ");
            }
            if (isUnReadStatus) {
                callSql.append("and cnc.read_status = 0 ");
            }
            if (isBlock) {
                callSql.append("and cn.create_date < (select block_date from camid_customer_allow_receive where device_id =:deviceId) ");
            }

            callSql.append(") where ROW_NUM=1 order by create_date desc");
            logger.info("Sql of getNotificationDetails " + callSql);

            SQLQuery query = getSession().createSQLQuery(callSql.toString());

            query.addScalar("notifyId", new LongType());
            query.addScalar("type", new StringType());
            query.addScalar("valueEn", new StringType());
            query.addScalar("valueKh", new StringType());
            query.addScalar("link", new StringType());
            query.addScalar("readStatus", new IntegerType());
            query.addScalar("iconUrl", new StringType());
            query.addScalar("time", new StringType());
            query.addScalar("titleEn", new StringType());
            query.addScalar("titleKh", new StringType());
            query.addScalar("actionType", new StringType());
            query.addScalar("buttonTitle", new StringType());
            query.addScalar("actionObjectId", new StringType());
            query.addScalar("featureImage", new StringType());
            query.addScalar("videoUrl", new StringType());
            query.addScalar("isExchange", new LongType());
            query.addScalar("idMessage", new StringType());
            query.setResultTransformer(Transformers.aliasToBean(CamIdNotificationBean.class));
            query.setParameter("deviceId", deviceId);

            if (pageSize != 0 && pageNum != null && !isUnReadStatus) {
                query.setFirstResult(pageSize * (pageNum - 1));
                query.setMaxResults(pageSize);
            }

            List<CamIdNotificationBean> listDetails = query.list();
            if (CommonUtil.isEmpty(listDetails)) {
                return new ArrayList<>();
            }

            return listDetails;
        } catch (Exception e) {
            logger.error("### An error occured while get list CamIdNotification with deviceId=" + deviceId, e);
        }
        return new ArrayList<CamIdNotificationBean>();
    }

    public int wsUpdateIsReadCamIDNotificationUnknow(long notifyId, String deviceId) {
        try {
            String sql = "update camid_notification_customer set read_status = 1 where device_Id =:deviceId and NOTIFICATION_ID =:notificationId";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("deviceId", deviceId);
            query.setParameter("notificationId", notifyId);
            int result = query.executeUpdate();
            getSession().flush();
            return result;
        } catch (HibernateException e) {
            logger.error("### An error occured while update isRead with isdn=" + deviceId, e);
            return 0;
        }
    }

    public boolean isReadNotifyBeforeUnknow(String deviceId, Long notifyId) {
        String sql = "select read_status from camid_notification_customer where device_Id =:deviceId and notification_id=:notifyId ";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("deviceId", deviceId);
        query.setParameter("notifyId", notifyId);
        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString()) > 0;
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from deviceId" + deviceId + " is not a number");
        } catch (Exception e) {
            logger.error("### An error occured while find id for deviceId=" + deviceId, e);
        }
        return false;
    }

    public boolean clearAllCamIdNotificationUnknow(String deviceId) {
        String tableName = "camid_customer_clear_all";
        Long id = this.findIdOfRecordLastTimeUpdateNotificationWithDeviceId(deviceId, tableName);
        String sql = "";
        if (id > 0) {
            sql = "update camid_customer_clear_all set update_date = sysdate, clear_date = sysdate where id =? and device_id =?";
        } else {
            id = this.getSequence("CAMID_CUSTOMER_SEQ");
            sql = "insert into camid_customer_clear_all(id, device_id, created_date, update_date, status, clear_date) "
                    + "values(?, ?, sysdate, sysdate, 1, sysdate)";
        }
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, id);
        query.setParameter(1, deviceId);
        try {
            int result = query.executeUpdate();
            getSession().flush();
            return result == 1;
        } catch (HibernateException he) {
            logger.error("### Cannot clean All for camId " + deviceId + " with reason: ", he);
            return false;
        }
    }

    public boolean blockReceiveCamidNotificationUnknow(String deviceId, String blockReceive) {
        String tableName = "camid_customer_allow_receive";
        Long id = this.findIdOfRecordLastTimeUpdateNotificationWithDeviceId(deviceId, tableName);
        String sql = "";
        if (id > 0) {
            sql = "update " + tableName + " set update_date = sysdate, block_date = sysdate, status =? where id=? and device_id=? ";
        } else {
            sql = "insert into " + tableName + " (id, device_id, created_date, update_date, status, block_date) values"
                    + " (?, ?, sysdate, sysdate, 1, sysdate)";
        }
        Query query = getSession().createSQLQuery(sql);
        if (id > 0) {
            query.setParameter(0, blockReceive);
            query.setParameter(1, id);
            query.setParameter(2, deviceId);
        } else {
            id = this.getSequence("CAMID_CUSTOMER_BLOCK_SEQ");
            query.setParameter(0, id);
            query.setParameter(1, deviceId);
        }

        try {
            int result = query.executeUpdate();
            if (result == 1) {
                return true;
            }
        } catch (HibernateException he) {
            logger.error("### An error occured while block receive id for deviceId=" + deviceId, he);
        } catch (Exception e) {
            logger.error("### An error occured while block receive id for deviceId=" + deviceId, e);
        }
        return false;
    }

    public boolean isClearAllBeforeUnknow(String deviceId) {
        String sql = "select count(*) from camid_customer_clear_all where device_id =:deviceId";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("deviceId", deviceId);
        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString()) > 0;
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from deviceId" + deviceId + " is not a number", ne);
        } catch (Exception e) {
            logger.error("### An error occured while find id for deviceId=" + deviceId, e);
        }
        return false;
    }

    //New
    public boolean updateCamIdStatus(String camid, String login, String deviceId, String lang, String versionApp) {
        CamIdDeviceToken deviceToken = getCamIdDeviceTokenByCamId("", deviceId);
        if ("0".equals(camid)) {
            camid = "";
        }
        if (deviceToken != null) {
            StringBuilder sql = new StringBuilder("update camid_device_token set is_login =:login, ");
            if (StringUtils.isNotEmpty(camid)) {
                sql.append(" cam_id=:camid, ");
            }
            sql.append(" lang=:lang,last_update=sysdate,version_app=:versionApp where device_id =:deviceId ");
            Query query = getSession().createSQLQuery(sql.toString());
            query.setParameter("login", login);
            if (StringUtils.isNotEmpty(camid)) {
                query.setParameter("camid", camid);
            }
            query.setParameter("deviceId", deviceId);
            query.setParameter("lang", lang);
            query.setParameter("versionApp", versionApp == null ? "" : versionApp);
            try {
                int result = query.executeUpdate();
                if (result == 1) {
                    return true;
                }
            } catch (HibernateException he) {
                logger.error("### An error occured while update status login for camid=" + camid, he);
            }
        } else {
            deviceToken = new CamIdDeviceToken();
            deviceToken.setCamId(camid);
            deviceToken.setDeviceId(deviceId);
            deviceToken.setCreateDate(CommonUtil.getCurrentTime());
            deviceToken.setLastUpdate(CommonUtil.getCurrentTime());
            deviceToken.setLang(lang);
            deviceToken.setVersionApp(versionApp);
            persist(deviceToken);
        }
        return true;
    }

    public List<CamIdRewardDetailBean> getListRewardDetail(String rewardDetail) {
        String sql = "select id, name, value from camid_reward_detail where status = 1 and value like :value order by created_date";
        Query query = getSession().createSQLQuery(sql)
                .addScalar("id", new LongType())
                .addScalar("name", new StringType())
                .addScalar("value", new StringType())
                .setResultTransformer(Transformers.aliasToBean(CamIdRewardDetailBean.class));
        query.setParameter("value", "%" + rewardDetail + "%");
        try {
            List<CamIdRewardDetailBean> result = query.list();
            if (result != null && !result.isEmpty()) {
                return result;
            }
        } catch (HibernateException he) {
            logger.error("### An error occured while get lst reward detail: ", he);
        }
        return new ArrayList<CamIdRewardDetailBean>();
    }

    public boolean isBlockNotificationUnknow(String deviceId) {
        String sql = "select status from camid_customer_allow_receive where device_id =:deviceId";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("deviceId", deviceId);
        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString()) > 0;
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from deviceId" + deviceId + " is not a number", ne);
        } catch (Exception e) {
            logger.error("### An error occured while find id for deviceId=" + deviceId, e);
        }
        return false;
    }

    public List<CamIdPassiveTypeBean> getListPassiveType(Long pageNumber) {
        String sql = "select id, name, code from (select rownum r, id, name, code from camid_passive_type where status = 1 order by id) where r < :numrow";
        try {
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("name", new StringType())
                    .addScalar("code", new StringType())
                    .addScalar("id", new LongType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdPassiveTypeBean.class
                            ));
            query.setParameter("numrow", pageNumber * 10);
            List result = query.list();
            if (result != null && result.size() > 0) {
                return result;
            }
        } catch (NumberFormatException e) {
            logger.error("### An error occured ", e);
        }
        return new ArrayList<>();
    }

    public List<CamIdPassiveTypeBean> getListPassiveTypeControl(Long pageNumber) {
        String sql = "select id, code, name, status, createdTime, updatedTime from (select rownum r, cpc.id id, cpt.code code, cpt.name name, cpc.status status, "
                + "to_char(cpc.created_time, 'dd/mm/yyyy hh24:mi:ss') createdTime,to_char(cpc.updated_time, 'dd/mm/yyyy hh24:mi:ss') updatedTime "
                + "from camid_passive_type cpt inner join camid_passive_config cpc on cpt.id = cpc.passive_type_id and cpc.status in (0,1) order by id ) where r <=:numrow";
        try {
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("id", new LongType())
                    .addScalar("code", new StringType())
                    .addScalar("name", new StringType())
                    .addScalar("status", new LongType())
                    .addScalar("createdTime", new StringType())
                    .addScalar("updatedTime", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdPassiveTypeBean.class
                            ));
            query.setParameter("numrow", pageNumber * 10);
            List result = query.list();
            if (result != null && result.size() > 0) {
                return result;
            }
        } catch (NumberFormatException e) {
            logger.error("### An error occured ", e);
        }
        return new ArrayList<>();
    }

    public boolean updatePassiveTypeControl(Long id, Long status) {
        try {
            String sql = "update camid_passive_config set status =:status where id=:id";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("status", status);
            query.setParameter("id", id);
            int result = query.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            logger.error("### An error occured ", e);
        }
        return false;
    }

    public String getOsDevice(String camid, String deviceId) {
        String sql = "select operating_system from camid_device_token where cam_id =:camid and device_id = :deviceId";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("camid", camid);
            query.setParameter("deviceId", deviceId);
            BigDecimal result = (BigDecimal) query.uniqueResult();
            return result.toString();
        } catch (Exception e) {
            logger.error("### An error occured while find OSdevice for camId=" + camid + "get default values = 2");
        }
        return "2";
    }

    public List<CamIdGame> getGames() {
        String sql = "select id, name from camid_game_app where visible =:visible and state =:state";
        try {
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("id", new LongType())
                    .addScalar("name", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdGame.class
                            ));
            query.setParameter("visible", "1");
            query.setParameter("state", "ACTIVE");
            List<CamIdGame> result = query.list();
            if (result != null && !result.isEmpty()) {
                return result;
            }
        } catch (Exception e) {
            logger.error("### An error occured while get list games", e);
        }
        return new ArrayList<>();
    }

    public List<CamIdGameCategory> getGameCategories() {
        String sql = "select DISTINCT gc.name, gc.id, gc.state status, gc.name_km from (select * from camid_game_app where state=:state and visible =:visible) g join camid_game_category gc on g.category_id = gc.id";
        try {
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("id", new LongType())
                    .addScalar("name", new StringType())
                    .addScalar("status", new StringType())
                    .addScalar("name_km", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdGameCategory.class
                            ));
            query.setParameter("visible", "1");
            query.setParameter("state", "ACTIVE");
            List<CamIdGameCategory> result = query.list();
            if (result != null && !result.isEmpty()) {
                return result;
            }
        } catch (Exception e) {
            logger.error("### An error occured while get list games", e);
        }
        return new ArrayList<>();
    }

    public List<CamIdGame> getGamesByCateId(Long cateId) {
        String sql = "select id, name, link, icon_url from camid_game_app where visible =:visible and state =:state and category_id =:id ";
        try {
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("id", new LongType())
                    .addScalar("name", new StringType())
                    .addScalar("link", new StringType())
                    .addScalar("icon_url", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdGame.class
                            ));
            query.setParameter("visible", "1");
            query.setParameter("state", "ACTIVE");
            query.setParameter("id", cateId);
            List<CamIdGame> result = query.list();
            if (result != null && !result.isEmpty()) {
                return result;
            }
        } catch (Exception e) {
            logger.error("### An error occured while get list games", e);
        }
        return new ArrayList<>();
    }

    public CamIdGame getGameById(Long id) {
        String sql = "select id, name, link from camid_game_app where visible =:visible and state =:state and id=:id";
        try {
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("id", new LongType())
                    .addScalar("name", new StringType())
                    .addScalar("link", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdGame.class
                            ));
            query.setParameter("visible", "1");
            query.setParameter("state", "ACTIVE");
            query.setParameter("id", id);
            CamIdGame result = (CamIdGame) query.uniqueResult();
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            logger.error("### An error occured while get game by id", e);
        }
        return null;
    }

    public CamIdGameCategory getGameCategoryById(Long id) {
        String sql = "select DISTINCT gc.name, gc.id, gc.state status, gc.name_km from (select * from camid_game_app where state=:state and visible =:visible) g join camid_game_category gc "
                + "on g.category_id = gc.id where  gc.id=:id";
        try {
            Query query = getSession().createSQLQuery(sql)
                    .addScalar("id", new LongType())
                    .addScalar("name", new StringType())
                    .addScalar("status", new StringType())
                    .addScalar("name_km", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(CamIdGameCategory.class
                            ));
            query.setParameter("visible", "1");
            query.setParameter("state", "ACTIVE");
            query.setParameter("id", id);
            CamIdGameCategory result = (CamIdGameCategory) query.uniqueResult();
            if (result != null) {
                return result;
            }
        } catch (Exception e) {
            logger.error("### An error occured while get game by id", e);
        }
        return null;
    }

    //------------------------
    //------------------------
    //------------------------
    private Long findIdOfRecordLastTimeUpdateNotificationWithCamId(String camId, String tableName) {
        String sql = "select id from " + tableName + " where camid=:camId";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("camId", camId);

        try {
            List result = query.list();
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString());
            }
        } catch (NumberFormatException ne) {
            logger.error("### Id get from camId" + camId + " is not a number", ne);
        } catch (Exception e) {
            logger.error("### An error occured while find id for camId=" + camId, e);
        }
        return -1L;
    }

    private Long findIdOfRecordLastTimeUpdateNotificationWithDeviceId(String deviceId, String tableName) {
        String sql = "select id from " + tableName + " where device_id=:deviceId";
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
        return -1L;
    }

}
