package co.siten.myvtg.dao;

import co.siten.myvtg.dto.*;
import co.siten.myvtg.dto.DiscountDTO;
import co.siten.myvtg.model.myvtg.*;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

/**
 * @author daibq
 */
@Repository("MyMetfoneBusinesDao")
public class MyMetfoneBusinesDao extends AbstractMyvtgDao<Object> {

    //SELECT LOWER(SUBSTR(STANDARD_HASH(SYS_GUID(), 'SHA1'), 0, 32)) AS OGUID FROM DUAL
    private static final Logger logger = Logger.getLogger(MyMetfoneBusinesDao.class.getName());
    private static final Long STATUS_ALL = -1L;
    private static final Long SEARCH_ALL = -1L;
    @Autowired
    CmpreDao cmpreDao;
    @Autowired
    CmposDao cmposDao;
    @Autowired
    SubDao subDao;

    /**
     * randomOTP
     *
     * @return
     * @throws Exception
     * @author daibq
     */
    @SuppressWarnings("unchecked")
    public Integer randomOTP() throws Exception {
        logger.info("begin method randomPin of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("select PCK_RANDOM_OTP.random_otp from dual ");
            query = getSession().createSQLQuery(sql.toString());
            logger.info("end method randomPin of MyMetfoneBusinesDao");
            List listResult = query.list();
            if (!DataUtil.isNullOrEmpty(listResult)) {
                return Integer.valueOf(listResult.get(0).toString());
            }
        } catch (HibernateException ex) {
            throw ex;
        }
        return null;
    }

    /**
     * @param isdn
     * @param otp
     * @param expried
     * @param wsCode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Integer insertOtp(String isdn, String otp, Integer expried, String wsCode) throws Exception {
        logger.info("begin method insertOtp of MyMetfoneBusinesDao");
        try {
            String id = UUID.randomUUID().toString();
            int totalOTP = totalOTP(isdn, wsCode) + 1;
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("INSERT INTO OTP (ID, ISDN, OTP, EXPIRE_TIME, STATUS,CREATE_DATE,SERVICE,TOTAL_GET_OTP) VALUES (:id, :isdn, :otp, sysdate + numToDSInterval( :expried, 'second' ), 1,sysdate,:wsCode,:totalOtp)");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("otp", otp);
            query.setParameter("isdn", isdn);
            query.setParameter("expried", expried);
            query.setParameter("wsCode", wsCode);
            query.setParameter("totalOtp", totalOTP);
            int result = query.executeUpdate();
            logger.info("end method insertOtp of MyMetfoneBusinesDao");
//            session.beginTransaction().commit();
            return result;

        } catch (HibernateException ex) {
            throw ex;
        } //finally {
//            session.close();
//            logger.info("end method randomPin of MyvtgMasterDataDao");
//        }
    }

    /**
     * @param isdn
     * @param otp
     * @param expried
     * @param check
     * @param wsCode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Integer updateOtp(String isdn, String otp, Integer expried, boolean check, String wsCode) throws Exception {
        logger.info("begin method updateOtp of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("UPDATE OTP SET ");
            if (check) {
                sql.append(" OTP =:otp, EXPIRE_TIME =sysdate +  numToDSInterval( :expried, 'second' ),TOTAL_GET_OTP = round(TOTAL_GET_OTP+1,0) ,");
            }
            sql.append("STATUS = :status,CREATE_DATE =sysdate WHERE isdn = :isdn and service = :wsCode");
            query = getSession().createSQLQuery(sql.toString());
            if (check) {
                query.setParameter("otp", otp);
                query.setParameter("status", 1);
                query.setParameter("expried", expried);
            } else {
                query.setParameter("status", 0);
            }
            query.setParameter("isdn", isdn);
            query.setParameter("wsCode", wsCode);
            int result = query.executeUpdate();
            logger.info("end method updateOtp of MyMetfoneBusinesDao");
//            session.beginTransaction().commit();
            return result;

        } catch (HibernateException ex) {
//            session.beginTransaction().rollback();
            throw ex;
        }// finally {
//            session.close();
//            logger.info("end method randomPin of MyvtgMasterDataDao");
//        }
    }

    /**
     * @param isdn
     * @param check
     * @param wsCode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean getCountOtp(String isdn, boolean check, String wsCode) throws Exception {
        logger.info("begin method getOtp of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("select count(1) from OTP WHERE isdn = :isdn and service = :wsCode");
            if (!check) {
                sql.append(" and STATUS = :status ");
            }
            query = getSession().createSQLQuery(sql.toString());
            if (!check) {
                query.setParameter("status", 1);
            }
            query.setParameter("isdn", isdn);
            query.setParameter("wsCode", wsCode);
            logger.info("end method getOtp of MyMetfoneBusinesDao");
            List listResult = query.list();
            return Integer.parseInt(listResult.get(0).toString()) > 0;
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * @param isdn
     * @param otp
     * @param wsCode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean checkOtp(String isdn, String otp, String wsCode) throws Exception {
        logger.info("begin method checkOtp of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("select count(1) from OTP WHERE isdn = :isdn and otp = :otp and sysdate <= EXPIRE_TIME and status = 1 and service =:wsCode ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("otp", otp);
            query.setParameter("wsCode", wsCode);
            logger.info("end method confirmOTP of MyMetfoneBusinesDao");
            return Integer.parseInt(query.list().get(0).toString()) > 0;
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * DAIBQ HAM NAY LÀ HAM CHECK OTP TRONG BẢNG OTP_TOKEN
     *
     * @param isdn
     * @param otp
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public boolean checkOtpToken(String isdn, String otp) throws Exception {
        logger.info("begin method checkOtp of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("select count(1) from OTP_TOKEN WHERE isdn = :isdn and otp = :otp and sysdate <= EXPIRE_TIME and status = 1");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("otp", otp);
            logger.info("end method checkOtp of MyMetfoneBusinesDao");
            return Integer.parseInt(query.list().get(0).toString()) > 0;
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * totalOTP
     *
     * @param isdn
     * @param service
     * @return
     */
    @SuppressWarnings("unchecked")
    private int totalOTP(String isdn, String service) throws Exception {
        String sql = "SELECT case  when TOTAL_GET_OTP is null THEN  0 ELSE total_get_otp END FROM otp where isdn=:isdn and service =:service";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("isdn", isdn);
        query.setParameter("service", service);
        List listResult = query.list();
        if (!DataUtil.isNullOrEmpty(listResult)) {
            return Integer.parseInt(listResult.get(0).toString());
        }
        return 0;
    }

    /**
     * @param isdn
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public int insert(String isdn) throws Exception {
        Session session = getSession();
        String sql = "INSERT INTO SUB_EM_MF (ISDN, STATUS, CRATE_DATE) VALUES (:isdn, 1, sysdate)";
        Query query = session.createSQLQuery(sql);
        query.setParameter("isdn", isdn);
        int result = query.executeUpdate();
        session.flush();
        return result;
    }

    /**
     * checkUseMetfoneCare
     *
     * @param isdn
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public boolean checkUseMetfoneCare(String isdn) throws Exception {
        logger.info("begin method checkMetfoneCareByIsdn of MyMetfoneBusinesDao");
        String sql = "SELECT COUNT(1) FROM SUB_EM_MF WHERE isdn = :isdn and status = 1 AND PROGRAM_CODE =:programCode";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("isdn", isdn);
        query.setParameter("programCode", Constants.METFONE_CARE);
        List listResult = query.list();
        return Integer.parseInt(listResult.get(0).toString()) > 0;
    }

    /**
     * checkUseProgramMyMetfone
     *
     * @param isdn
     * @param programCode
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean checkUseProgramMyMetfone(String isdn, String programCode) throws Exception {
        logger.info("begin method checkMetfoneCareByIsdn of MyMetfoneBusinesDao");
        String sql = "SELECT COUNT(1) FROM SUB_EM_MF WHERE isdn = :isdn and status = 1 and program_code =:programCode";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("isdn", isdn);
        query.setParameter("programCode", programCode);
        List listResult = query.list();
        return Integer.parseInt(listResult.get(0).toString()) > 0;
    }

    /**
     * getListGift
     *
     * @param type
     * @param check
     * @param idVip
     * @param rankingList
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<Object> getListGiftForLuckyGame(String type, boolean check, String idVip, String rankingList) throws Exception {
        logger.info("begin method getListGift of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT g FROM  Gift1 g,GiftType gt ");
//        sql.append(" INNER JOIN  GiftType gt ON g.giftTypeId = gt.id ");
        sql.append(" WHERE");
        sql.append(" g.giftTypeId = gt.id ");
        sql.append(" AND gt.status =1");
        sql.append(" AND g.status = 1");
        sql.append(" AND g.quantity >0");
        sql.append(" AND trunc(sysdate) BETWEEN trunc(g.startDate )AND trunc(g.expireDate)");
        if (check) {
            sql.append(" AND gt.id = :idVip");
        } else {
            sql.append(" AND gt.id <>:idVip");
            sql.append(" AND TO_CHAR(gt.isType) ");
            sql.append(DataUtil.createInQuery("giftType", type.trim()));
            sql.append(" AND REGEXP_INSTR (g.rankingList,:rankingList) > 0");
        }
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("idVip", idVip);
        if (!check) {
            DataUtil.setParamInQuery(query, "giftType", type.trim());
            query.setParameter("rankingList", rankingList);
        }
        return query.list();
    }

    /**
     * getLoyaltyTelcoGift
     *
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<LoyaltyTelcoGift> getLoyaltyTelcoGiftForLuckyGame() throws Exception {
        logger.info("begin method getLoyaltyTelcoGift of MyMetfoneBusinesDao");
        String sql = "SELECT g FROM LoyaltyTelcoGift g WHERE g.status = 1 ";
        Query query = getSession().createQuery(sql);
        return query.list();
    }

    /**
     * getListGift
     *
     * @param type
     * @param giftTypeId
     * @param giftTypeName
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<Object> getListGiftForLuckyGame(String type, String giftTypeId) throws Exception {
        logger.info("begin method getListGiftForLuckyGame of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT g FROM  Gift1 g,GiftType gt ");
        sql.append(" WHERE");
        sql.append(" g.giftTypeId = gt.id ");
        sql.append(" AND gt.status =1");
        sql.append(" AND g.status = 1");
        sql.append(" AND g.quantity >0");
//        sql.append(" AND trunc(sysdate) BETWEEN trunc(g.startDate )AND trunc(g.expireDate)");
        sql.append(" AND trunc(sysdate) BETWEEN trunc(g.startDate )AND NVL(g.expireDate, sysdate + 365)");
        sql.append(" AND TO_CHAR(gt.isType) = :giftType  ");
//        sql.append(" AND LOWER(gt.giftTypeName) =  LOWER(:giftTypeName)");
        sql.append(" AND gt.id ");
        sql.append(DataUtil.createInQuery("giftTypeId", giftTypeId.trim()));
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("giftType", type);
        DataUtil.setParamInQuery(query, "giftTypeId", giftTypeId.trim());
        return query.list();
    }

    /**
     * getListGiftEmoneyForLuckyGame
     *
     * @param giftTypeId
     * @return
     * @throws Exception
     */
    public List<Object> getListGiftByGiftTypeId(String giftTypeId) throws Exception {
        logger.info("begin method getListGiftByGiftId of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT g FROM  Gift1 g ");
        sql.append(" WHERE");
        sql.append(" g.status = 1");
        sql.append(" AND g.quantity >0");
        sql.append(" AND trunc(sysdate) BETWEEN trunc(g.startDate )AND NVL(g.expireDate, sysdate + 365)");
        sql.append(" AND g.giftTypeId = :giftTypeId ");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("giftTypeId", giftTypeId);
        return query.list();
    }

    /**
     * getListGiftEmoneyForLuckyGame
     *
     * @param type
     * @param giftTypeId
     * @return
     * @throws Exception
     */
    public List<GiftType> getListGiftTypeForGame(String type, String giftTypeId) throws Exception {
        logger.info("begin method getListGiftTypeForGame of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT gt FROM GiftType gt ");
        sql.append(" WHERE");
        sql.append(" gt.status =1");
        sql.append(" AND TO_CHAR(gt.isType) = :giftType  ");
        sql.append(" AND gt.id ");
        sql.append(DataUtil.createInQuery("giftTypeId", giftTypeId.trim()));
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("giftType", type);
        DataUtil.setParamInQuery(query, "giftTypeId", giftTypeId.trim());
        return query.list();
    }

    /**
     * getLoyaltyTelcoGift
     *
     * @param giftType
     * @param giftTypeName
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<LoyaltyTelcoGift> getLoyaltyTelcoGiftForLuckyGame(String giftType, String telecomTypeId) throws Exception {
        logger.info("begin method getLoyaltyTelcoGiftForLuckyGame of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT g FROM LoyaltyTelcoGift g,GiftType gt ");
        sql.append(" WHERE");
        sql.append(" g.giftTypeId = gt.id ");
        sql.append(" AND gt.status =1");
        sql.append(" AND g.status = 1");
        sql.append(" AND TO_CHAR(gt.isType) = :giftType  ");
//        sql.append(" AND LOWER(gt.giftTypeName) = LOWER(:giftTypeName)");
        sql.append(" AND gt.id ");
        sql.append(DataUtil.createInQuery("telecomTypeId", telecomTypeId));
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("giftType", giftType);
        DataUtil.setParamInQuery(query, "telecomTypeId", telecomTypeId);
        return query.list();
    }

    /**
     * getFbInfo
     *
     * @param isdn
     * @param gameCode
     * @param id
     * @param check
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<Object> getFbInfo(String isdn, String gameCode, String id, boolean check) throws Exception {
        logger.info("begin method getFbInfo of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT g FROM FbInfo g WHERE g.isdn = :isdn and g.gameCode=:gameCode ");
        if (!DataUtil.isNullOrEmpty(id)) {
            sql.append("  and g.id=:id  ");
        }
        if (true) {
            sql.append(" and g.status = 1 ");
        }
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("isdn", isdn);
        query.setParameter("gameCode", gameCode);
        if (!DataUtil.isNullOrEmpty(id)) {
            query.setParameter("id", id);
        }

        return query.list();
    }

    /**
     * getFreePlayGame Lay nhung ban ghi chua cong luot choi(chi cong 3 lan
     * trong ngay)
     *
     * @param isdn
     * @param gameCode
     * @param maxCount
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<ShareFb> getFreePlayGame(String isdn, String gameCode, Integer maxCount) throws Exception {
        logger.info("begin method getFreePlayGame of MyMetfoneBusinesDao");
        Integer count = checkFreePlayGameInDay(isdn, gameCode);
        if (count <= maxCount) {
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT g FROM ShareFb g WHERE g.isdn = :isdn and g.gameCode=:gameCode and g.freePlayGame is null and trunc(crateDate) = trunc(sysdate) ");
            sql.append(" and g.status = 1 ");
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("gameCode", gameCode);
            query.setMaxResults(maxCount);
            return query.list();
        } else {
            return new ArrayList<>();
        }

    }

    /**
     * checkFreePlayGameInDay Lay nhung ban ghi chua cong luot choi
     *
     * @param isdn
     * @param gameCode
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public Integer checkFreePlayGameInDay(String isdn, String gameCode) throws Exception {
        logger.info("begin method getFreePlayGame of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(1) FROM ShareFb g WHERE g.isdn = :isdn and g.gameCode=:gameCode and g.freePlayGame is not null and trunc(crateDate) = trunc(sysdate) ");
        sql.append(" and g.status = 1 ");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("isdn", isdn);
        query.setParameter("gameCode", gameCode);
        return Integer.parseInt(query.list().get(0).toString());
    }

    /**
     * getIsdnInfoByAuthenKey
     *
     * @param authenkey
     * @param check
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public AuthenLoginGame getIsdnInfoByAuthenKey(String authenkey, boolean check) throws Exception {
        logger.info("begin method getIsdnInfoByAuthenKey of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a FROM AuthenLoginGame a WHERE a.authenkey = :authenkey ");
        if (!check) {
            sql.append(" and a.status = 0 ");
        } else {
            sql.append(" and a.status = 1 ");
        }
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("authenkey", authenkey.trim());
        List list = query.list();
        if (!DataUtil.isNullOrEmpty(list)) {
            return (AuthenLoginGame) list.get(0);
        }
        return null;
    }

    /**
     * getLogLuckGame
     *
     * @param isdn
     * @param luckyId
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public LogGaming getLogLuckGame(String isdn, String luckyId) throws Exception {
        logger.info("begin method getLogLuckGame of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a FROM LogGaming a WHERE a.isdn =:isdn and a.id =:luckyId and a.status = 0 ");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("isdn", isdn);
        query.setParameter("luckyId", luckyId);
        List list = query.list();
        if (!DataUtil.isNullOrEmpty(list)) {
            return (LogGaming) list.get(0);
        }
        return null;
    }

    /**
     * getInviteGames
     *
     * @param isdnSender
     * @param gameCode
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<InviteGame> getInviteGames(String isdnSender, String gameCode) throws Exception {
        logger.info("begin method getInviteGames of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT a FROM InviteGame a WHERE a.isdnSender =:isdnSender and a.programCode =:gameCode and a.statusLogin = 1 and a.freePlayGame is null ");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("isdnSender", isdnSender);
        query.setParameter("gameCode", gameCode);
        List<InviteGame> list = query.list();
        return list;
    }

    /**
     * getGiftType
     *
     * @param giftCode
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<GiftType> getGiftType(String giftCode) throws Exception {
        logger.info("begin method getGiftType of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        // and g.giftTypeCode in (:giftCode)
        sql.append("SELECT g FROM GiftType g where g.status = 1 ");
        Query query = getSession().createQuery(sql.toString());
//        query.setParameter("giftCode", giftCode);
        List<GiftType> list = query.list();
        return list;
    }

    /**
     * getGiftTypeById
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<GiftType> getGiftTypeById(String id) throws Exception {
        logger.info("begin method getGiftTypeById of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        // and g.giftTypeCode in (:giftCode)
        sql.append("SELECT g FROM GiftType g where g.status = 1 and id = :id");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("id", id);
        List<GiftType> list = query.list();
        return list;
    }

    /**
     * getLoyaltyTelcoGift
     *
     * @param giftId
     * @param dataType
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<LoyaltyTelcoGift> getLoyaltyTelcoGift(String giftId, String dataType) throws Exception {
        logger.info("begin method getLoyaltyTelcoGift of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        // and g.giftTypeCode in (:giftCode)
        sql.append("SELECT l FROM LoyaltyTelcoGift l where l.status =1 and l.giftId = :giftId and TRIM(l.dataType) =:dataType ");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("giftId", giftId);
        query.setParameter("dataType", dataType);
        List<LoyaltyTelcoGift> list = query.list();
        return list;
    }

    /**
     * getLoyaltyTelcoGift
     *
     * @param giftId
     * @param dataType
     * @return
     * @throws java.lang.Exception
     */
    @SuppressWarnings("unchecked")
    public List<Gift1> getGift(String giftId, String dataType) throws Exception {
        logger.info("begin method getGiftType of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        // and g.giftTypeCode in (:giftCode)
        sql.append("SELECT l FROM Gift1 l where l.status =1 and l.giftId = :giftId and TRIM(l.giftCode) =:dataType ");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("giftId", giftId);
        query.setParameter("dataType", dataType);
        List<Gift1> list = query.list();
        return list;
    }

    /**
     * checkMaxShareOnDay
     *
     * @param isdnSender
     * @return
     * @throws Exception
     */
    public Long checkMaxShareOnDay(String isdnSender) throws Exception {
        logger.info("Start checkMaxShareOnDay of BussinessDAO ");
        StringBuilder sql = new StringBuilder();
        Long count = 0L;
        Query query = null;
        sql.append("SELECT count(1) FROM  invite_log WHERE ISDN_SENDER =:isdnSender and TRUNC(create_date) = TRUNC(sysdate) ");
        query = getSession().createSQLQuery(sql.toString());
        query.setParameter("isdnSender", isdnSender);
        if (!DataUtil.isNullOrEmpty(query.list())) {
            count = Long.parseLong(query.list().get(0).toString());
        }
        return count;
    }

    /**
     * checkRegisterMyMetfone
     *
     * @param isdn
     * @return
     * @throws Exception
     */
    public boolean checkRegisterMyMetfone(String isdn) throws Exception {
        logger.info("Start checkIsdnByIsdn of BussinessDAO ");
        Long count = 0L;
        StringBuilder sql = new StringBuilder();
        logger.info("Start Kiem tra so da dang ki chua de moi");
        sql.append("SELECT count(1) FROM  sub WHERE isdn =:isdn AND status = 1");
        Query query = getSession().createSQLQuery(sql.toString());
        query.setParameter("isdn", isdn);
        if (!DataUtil.isNullOrEmpty(query.list())) {
            count = Long.parseLong(query.list().get(0).toString());
        }
        String log = count <= 0L ? "chua dang ki" : "Da dang ki";
        logger.info(" Count : " + count + ">>>>" + "So: " + isdn + " " + log);
        logger.info("end Kiem tra so da dang ki chua de moi");
        return count <= 0;
    }

    /**
     * checkInviteGame Load danh ba khi thuc hien invite tu game
     *
     * @param programCode
     * @param isdn
     * @return
     * @throws Exception
     */
    public boolean checkInviteGame(String isdn, String programCode) throws Exception {
        logger.info("Start checkIsdnByIsdn of BussinessDAO ");
        Long count = 0L;
        Query query = null;
        StringBuilder sql = new StringBuilder();
        logger.info("Start Load danh ba khi thuc hien invite tu game");
        sql.append("SELECT count(1)FROM INVITE_GAME WHERE isdn_reciver =:isdn AND program_code = :programCode AND trunc(create_date) = trunc(sysdate)");
        query = getSession().createSQLQuery(sql.toString());
        query.setParameter("isdn", isdn);
        query.setParameter("programCode", programCode);
        if (!DataUtil.isNullOrEmpty(query.list())) {
            count = Long.parseLong(query.list().get(0).toString());
        }
        String log = count <= 0L ? "chua duoc moi lan nao trong ngay" : "da dc moi 1 lan trong ngay";
        logger.info(" Count : " + count + ">>>>" + "So: " + isdn + " " + log);
        logger.info("end Load danh ba khi thuc hien invite tu game");
        return count <= 0;
    }

    /**
     * getPromotionByType
     *
     * @param type
     * @param programCode
     * @return
     * @throws Exception
     */
    public PromotionConfig getPromotionByType(Long type, String programCode) throws Exception {
        logger.info("Start getPromotionByType of BussinessDAO ");
        StringBuilder sql = new StringBuilder();
        Query query = null;
        sql.append(" FROM PromotionConfig WHERE type = :type AND programCode = :programCode AND status = 1 ");
        query = getSession().createQuery(sql.toString());
        query.setParameter("type", type);
        query.setParameter("programCode", programCode);
        if (!DataUtil.isNullOrEmpty(query.list())) {
            return (PromotionConfig) query.list().get(0);
        }
        return null;
    }

    /**
     * checkRedeemInviteOrLoginFirst
     *
     * @param isdn
     * @param type
     * @param redeemTpm
     * @return
     * @throws java.lang.Exception
     */
    public boolean checkRedeemInviteOrLoginFirst(String isdn, Long type, String redeemTpm) throws Exception {
        logger.info("Start checkRedeem of BussinessDAO ");
        StringBuilder sqlstr = new StringBuilder();
        if (!DataUtil.isNullObject(type) && (type == 1 || type == 2)) {
            sqlstr.append("SELECT count(1) FROM redeem rd WHERE rd.isdn =:isdn ");
            sqlstr.append(" AND rd.type =:type ");
            if (type == 2) {
                sqlstr.append(" AND rd.REDEEM_TMP_1 =:redeemTpm ");
            }
            Query query = getSession().createSQLQuery(sqlstr.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("type", type);
            if (type == 2) {
                query.setParameter("redeemTpm", redeemTpm);
            }
            List list = query.list();
            Long count = 0L;
            if (!DataUtil.isNullOrEmpty(list)) {
                count = Long.parseLong(list.get(0).toString());
                String log = count <= 0 ? "Chua redeem" : "Da redeem";
                logger.info("Count:" + count + " " + log);
            }
            logger.info("end checkRedeem ");
            return count <= 0;
        }
        return false;
    }

    /**
     * getIsdnSender
     *
     * @param session
     * @param isdnReciver
     * @return
     * @throws java.lang.Exception
     */
    public InviteLogDTO getIsdnSenderInviteMyMetfone(String isdnReciver) throws Exception {
        logger.info("Start getIsdnSender of BussinessDAO ");
        StringBuilder sqlstr = new StringBuilder();
        sqlstr.append("SELECT id id, isdn_sender isdnSender ,TO_CHAR(create_date ,'dd/MM/yyyy HH24:mm:ss') createDateStr  FROM invite_log WHERE isdn_reciver= :isdn and status_login = 0 ORDER BY create_date ");
        SQLQuery query = getSession().createSQLQuery(sqlstr.toString());
        query.addScalar("isdnSender", new StringType())
                .addScalar("createDateStr", new StringType())
                .addScalar("id", new LongType())
                .setResultTransformer(Transformers.aliasToBean(InviteLogDTO.class));
        query.setMaxResults(1);
        query.setParameter("isdn", isdnReciver);
        logger.info("end getIsdnSender of BussinessDAO ");
        if (!DataUtil.isNullOrEmpty(query.list())) {
            return (InviteLogDTO) query.list().get(0);
        }
        return null;
    }

    /**
     * getIsdnSenderGame
     *
     * @param isdnReciver
     * @return
     * @throws java.lang.Exception
     */
    public List<InviteGameDTO> getIsdnSenderGame(String isdnReciver) throws Exception {
        logger.info("Start getIsdnSenderGame of BussinessDAO ");
        StringBuilder sqlstr = new StringBuilder();
        sqlstr.append(" SELECT * FROM  ");
        sqlstr.append(" (SELECT id id, isdn_sender isdnsender, TO_CHAR(create_date, 'dd/MM/yyyy HH24:mm:ss') createdatestr, program_code programCode,");
        sqlstr.append(" row_number() over (partition by program_code order by create_date desc) idx ");
        sqlstr.append(" FROM invite_game ");
        sqlstr.append(" WHERE isdn_reciver =:isdnReciver and status_login =0) WHERE IDX = 1 ");
        SQLQuery query = getSession().createSQLQuery(sqlstr.toString());
        query.addScalar("isdnSender", new StringType())
                .addScalar("createDateStr", new StringType())
                .addScalar("programCode", new StringType())
                .addScalar("id", new LongType())
                .setResultTransformer(Transformers.aliasToBean(InviteGameDTO.class));
        query.setMaxResults(1);
        query.setParameter("isdnReciver", isdnReciver);
        logger.info("end getIsdnSenderGame of BussinessDAO ");
        return query.list();

    }

    /**
     * updateInvite
     *
     * @param session
     * @param isdnReciver
     * @param isdnSender
     * @param statusLogin
     * @param check
     * @param dateStr
     * @return
     * @throws Exception
     */
    public void updateInvite(String isdnReciver, String isdnSender, Long statusLogin, boolean check, String dateStr) throws Exception {
        Session session = getSession();
        logger.info("Start updateInvite of BussinessDAO ");
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE InviteLog SET statusLogin =:statusLogin  WHERE isdnReciver = :isdnReciver ");
        sql.append(" and status_login =0 ");
        if (check) {
            sql.append(" AND isdnSender = :isdnSender AND createDate = TO_DATE(:dateStr, 'yyyymmdd hh24:mi:ss') ");
        } else {
            sql.append(" AND isdnSender <> :isdnSender ");
            if (!DataUtil.isNullObject(dateStr)) {
                sql.append(" OR (isdnSender =:isdnSender AND isdnReciver = :isdnReciver and status_login =0  AND  createDate <> TO_DATE(:dateStr, 'yyyymmdd hh24:mi:ss'))");
            }
        }

        Query query = session.createQuery(sql.toString());
        query.setParameter("statusLogin", statusLogin);
        query.setParameter("isdnReciver", isdnReciver);
        if (!DataUtil.isNullOrEmpty(isdnSender)) {
            query.setParameter("isdnSender", isdnSender);
        }
        if (!DataUtil.isNullObject(dateStr)) {
            query.setParameter("dateStr", dateStr);
        }
        int count = query.executeUpdate();
        System.out.println("count :" + count);
        session.flush();
    }

    /**
     * updateInvite
     *
     * @param isdnReciver
     * @param statusLogin
     * @param id
     * @param check
     * @return
     * @throws Exception
     */
    public void updateInvite(String isdnReciver, Long statusLogin, boolean check, Long id) throws Exception {
        Session session = getSession();
        logger.info("Start updateInvite of BussinessDAO ");
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE InviteLog SET statusLogin =:statusLogin  WHERE status_login =0  ");
        if (check) {
            sql.append(" AND id = :id ");
        } else {
            sql.append(" AND isdnReciver = :isdnReciver and id <>:id ");
        }
        Query query = session.createQuery(sql.toString());
        query.setParameter("statusLogin", statusLogin);
        if (!DataUtil.isNullOrEmpty(isdnReciver)) {
            query.setParameter("isdnReciver", isdnReciver);
        }
        query.setParameter("id", id);
        int count = query.executeUpdate();
        System.out.println("count :" + count);
        session.flush();
    }

    /**
     * updateInviteGame
     *
     * @param isdnReciver
     * @param isdnSender
     * @param statusLogin
     * @param check
     * @param dateStr
     * @param programCode
     * @throws Exception
     */
    public void updateInviteGame(String isdnReciver, String isdnSender, Long statusLogin, boolean check, String dateStr, String programCode) throws Exception {
        logger.info("Start updateInviteGame of BussinessDAO ");
        Session session = getSession();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE InviteGame SET statusLogin =:statusLogin  WHERE isdnReciver = :isdnReciver ");
        sql.append("AND programCode=:programCode ");
        sql.append(" and status_login =0 ");
        if (check) {
            sql.append(" AND isdnSender = :isdnSender AND createDate = TO_DATE(:dateStr, 'yyyymmdd hh24:mi:ss') ");
        } else {
            sql.append(" AND isdnSender <> :isdnSender ");
            if (!DataUtil.isNullObject(dateStr)) {
                sql.append(" OR (isdnSender =:isdnSender AND isdnReciver = :isdnReciver AND programCode=:programCode AND and status_login =0 AND createDate <> TO_DATE(:dateStr, 'yyyymmdd hh24:mi:ss'))");
            }
        }
        Query query = session.createQuery(sql.toString());
        query.setParameter("statusLogin", statusLogin);
        query.setParameter("isdnReciver", isdnReciver);
        query.setParameter("programCode", programCode);
        if (!DataUtil.isNullOrEmpty(isdnSender)) {
            query.setParameter("isdnSender", isdnSender);
        }
        if (!DataUtil.isNullObject(dateStr)) {
            query.setParameter("dateStr", dateStr);
        }
        int count = query.executeUpdate();
        System.out.println("count :" + count);
        session.flush();
    }

    /**
     * updateInviteGame
     *
     * @param isdnReciver
     * @param statusLogin
     * @param check
     * @param id
     * @param programCode
     * @throws Exception
     */
    public void updateInviteGame(String isdnReciver, Long statusLogin, boolean check, Long id, String programCode) throws Exception {
        logger.info("Start updateInviteGame of BussinessDAO ");
        Session session = getSession();
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE InviteGame SET statusLogin =:statusLogin  WHERE status_login =0");
        sql.append("AND programCode=:programCode ");
        if (check) {
            sql.append(" AND id = :id");
        } else {
            sql.append(" AND isdnReciver = :isdnReciver and id <> :id   ");
        }
        Query query = session.createQuery(sql.toString());
        query.setParameter("statusLogin", statusLogin);
        if (!DataUtil.isNullOrEmpty(isdnReciver)) {
            query.setParameter("isdnReciver", isdnReciver);
        }
        query.setParameter("programCode", programCode);
        query.setParameter("id", id);
        int count = query.executeUpdate();
        System.out.println("count :" + count);
        session.flush();
    }

    /**
     * delete
     *
     * @param entity
     */
    @Override
    public void delete(Object entity) {
        super.delete(entity); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * update
     *
     * @param entity
     */
    @Override
    public void update(Object entity) {
        super.update(entity); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * persist
     *
     * @param entity
     */
    @Override
    public void persist(Object entity) {
        super.persist(entity); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * save
     *
     * @param entity
     * @return
     */
    @Override
    public String save(Object entity) {
        return super.save(entity); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * insert
     *
     * @param entity
     * @return
     */
    @Override
    public Long insert(Object entity) {
        return super.insert(entity);
    }

    @Override
    public Session getSession() {
        return super.getSession(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param sequenceName - sequenceName
     * @return Long
     * @throws Exception Exception
     */
    public Long getSequence(String sequenceName) throws Exception {
        String sql = "select " + sequenceName + ".NEXTVAL from dual";
        SQLQuery query = getSession().createSQLQuery(sql);
        List lst = query.list();
        if (lst.isEmpty()) {
            return null;
        }
        return Long.parseLong(lst.get(0).toString());
    }

//    /**
//     * getCategoryDetail
//     *
//     * @param cateId
//     * @param path
//     * @param language
//     * @return
//     */
//    public List<Object> getCategoryDetail(String language, Long cateId, String path) {
//        logger.info("begin method getCategoryDetail of MyMetfoneBusinesDao");
//        StringBuilder sql = new StringBuilder();
//        sql.append(" SELECT new co.siten.myvtg.dto.CategoryDetailDTO( ");
//        sql.append(" cad.id, cad.title, cad.description, cad.icon, ");
//        sql.append(" cad.img, ");
//        sql.append(" cad.link, cad.startTime ");
//        sql.append(")");
//        sql.append(" FROM CategoryDetail cad ");
//        sql.append(" WHERE cad.status = 1 AND cad.categoryId =:cateId  AND cad.language =:language  ");
//        sql.append(" AND trunc(sysdate) BETWEEN trunc(cad.startTime) AND trunc(cad.endTime) ");
//        sql.append(" ORDER BY cad.startTime desc");
//        Query query = getSession().createQuery(sql.toString());
//        query.setParameter("cateId", cateId);
//        query.setParameter("language", language);
//        return query.list();
//    }
    /**
     * getCategoryDetail
     *
     * @param cateId
     * @param path
     * @param language
     * @return
     */
    public List<Object> getCategoryDetail(String language, Long cateId, String path) {
        logger.info("begin method getCategoryDetail of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new co.siten.myvtg.dto.CategoryDetailDTO( ");
        sql.append(" cad.id, cad.title, cad.description, cad.icon, ");
        sql.append(" cad.img, ");
        sql.append(" cad.link, cad.startTime, ");
        sql.append("'");
        sql.append(path);
        sql.append("'");
        sql.append(")");
        sql.append(" FROM CategoryDetail cad ");
        sql.append(" WHERE cad.status = 1 AND cad.categoryId =:cateId  AND cad.language =:language  ");
        sql.append(" AND trunc(sysdate) BETWEEN trunc(cad.startTime) AND trunc(cad.endTime) ");
        sql.append(" ORDER BY cad.startTime desc");
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("cateId", cateId);
        query.setParameter("language", language);
        return query.list();
    }

    /**
     * getCategory
     *
     * @param language
     * @param isHot
     * @param isNews
     * @return
     */
    public List<Category> getCategory(String language, Long isHot, Long isNews) {
        logger.info("begin method getCategory of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT c FROM Category c ");
        sql.append(" WHERE c.status = 1 ");
        if (!DataUtil.isNullObject(isHot)) {
            sql.append(" AND c.ishot =:isHot ");
        }
        if (!DataUtil.isNullObject(isNews)) {
            sql.append(" AND c.isNews =:isNews ");
        }
        sql.append(" ORDER BY c.createDate desc");
        Query query = getSession().createQuery(sql.toString());
        if (!DataUtil.isNullObject(isHot)) {
            query.setParameter("isHot", isHot);
        }
        if (!DataUtil.isNullObject(isNews)) {
            query.setParameter("isNews", isNews);
        }
        return query.list();
    }

    /**
     * getCaptcha
     *
     * @param isdn
     * @param programCode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Captcha getCaptcha(String isdn, String programCode) throws Exception {
        logger.info("begin method getCaptcha of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("select c from Captcha c WHERE isdn = :isdn and programCode = :programCode");
            Query query = getSession().createQuery(sql.toString());
            query.setParameter("isdn", isdn);
            query.setParameter("programCode", programCode);
            List listResult = query.list();
            logger.info("end method getCaptcha of MyMetfoneBusinesDao");
            if (!DataUtil.isNullOrEmpty(listResult)) {
                return (Captcha) listResult.get(0);
            }
            return null;
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * updatCaptcha
     *
     * @param isdn
     * @param captchaCode
     * @param expried
     * @param check
     * @param programCode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Integer updatCaptcha(String isdn, String captchaCode, Integer expried, boolean check, String programCode) throws Exception {
        logger.info("begin method updatCaptcha of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("UPDATE CAPTCHA SET ");
            if (check) {
                sql.append(" expiredTime =sysdate +  numToDSInterval( :expried, 'second' ), captchaCode =:captchaCode, createDate =sysdate, ");
            } else {
                sql.append(" updateDate = sysdate, ");
            }
            sql.append(" STATUS = :status WHERE isdn = :isdn and programCode = :programCode ");
            query = getSession().createQuery(sql.toString());
            if (check) {
                query.setParameter("captchaCode", captchaCode);
                query.setParameter("expried", expried);
                query.setParameter("status", 0);
            } else {
                query.setParameter("status", 1);
            }
            query.setParameter("isdn", isdn);
            query.setParameter("programCode", programCode);
            int result = query.executeUpdate();
            logger.info("end method updatCaptcha of MyMetfoneBusinesDao");
            return result;

        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * insertCaptcha
     *
     * @param isdn
     * @param totalErr
     * @param captchaCode
     * @param programCode
     * @param expried
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public Integer insertCaptcha(String isdn, String captchaCode, Integer expried, String programCode, Long totalErr) throws Exception {
        logger.info("begin method insertCaptcha of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("INSERT INTO Captcha (isdn, captchaCode, expiredTime, status,createDate,programCode,totalError) VALUES (:isdn, :captchaCode, sysdate + numToDSInterval( :expried, 'second' ), 0,sysdate,:programCode,:totalErr)");
            query = getSession().createQuery(sql.toString());
            query.setParameter("captchaCode", captchaCode);
            query.setParameter("isdn", isdn);
            query.setParameter("expried", expried);
            query.setParameter("programCode", programCode);
            query.setParameter("totalErr", totalErr);
            int result = query.executeUpdate();
            logger.info("end method insertCaptcha of MyMetfoneBusinesDao");
            return result;
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * getCaptcha
     *
     * @param isdn
     * @param programCode
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void reset(String isdn, String programCode) throws Exception {
        logger.info("begin method reset of MyMetfoneBusinesDao");
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("update Captcha SET totalError =0 , status = 0 WHERE isdn");
            sql.append(DataUtil.createInQuery("isdn", isdn));
            sql.append(" and programCode = :programCode");
            Query query = getSession().createQuery(sql.toString());
            DataUtil.setParamInQuery(query, "isdn", isdn);
            query.setParameter("programCode", programCode);
            int result = query.executeUpdate();
            logger.info("end method reset of MyMetfoneBusinesDao " + result);
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * @param isdn
     * @param language
     * @param productCodes
     * @return
     */
//    @Cacheable("services")
    public List<ConsultantDTO> getAllConsultant(String language, String isdn, String productCodes) throws Exception {
        logger.info("begin method getAllConsultant of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new co.siten.myvtg.dto.ConsultantDTO(sg.name, sg.code, s.name, s.code, s.shortDes, s.iconUrl, s.isMultPlan) ");
        sql.append(" FROM ServiceGroupA sg, ServiceA s WHERE s.id IN ( ");
        sql.append(" SELECT DISTINCT ss.serviceId  FROM SubServiceA ss WHERE ss.code ");
        sql.append(DataUtil.createInQuery("productCode", productCodes));
        sql.append(" AND ss.language = :language  AND ss.approved=1 ) ");
        sql.append(" AND sg.id  = s.serviceGroupId AND  sg.language = :language  AND sg.status = 1 ");
        sql.append(" AND s.serviceType = 0  AND s.language = :language  AND s.status = 1 AND s.approved=1 ");
        sql.append(" GROUP BY sg.name, sg.code, s.name, s.code, s.shortDes, s.iconUrl, s.isMultPlan  ");
        sql.append(" ORDER BY sg.name, s.name ");
        Query query = getSession().createQuery(sql.toString());
        query.setString("language", language);
        DataUtil.setParamInQuery(query, "productCode", productCodes);
        return query.list();
    }

    /**
     * getOtpByIsdnAndService
     *
     * @param isdn
     * @param service
     * @param otp
     * @return
     * @throws Exception
     */
    public Otp getOtpByIsdnAndService(String isdn, String service, String otp) throws Exception {
        logger.info("begin method getOtpByIsdnAndService of MyMetfoneBusinesDao");
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM Otp o WHERE o.isdn = :isdn and o.service = :service ");
        if (!DataUtil.isNullOrEmpty(otp)) {
            sql.append(" and o.otp = :otp ");
        }
        Query query = getSession().createQuery(sql.toString());
        query.setParameter("isdn", isdn);
        query.setParameter("service", service);
        if (!DataUtil.isNullOrEmpty(otp)) {
            query.setParameter("otp", otp);
        }
        List result = query.list();
        if (!DataUtil.isNullOrEmpty(result)) {
            return (Otp) query.list().get(0);
        }
        return null;
    }

    /**
     * getSummaryPackage
     *
     * @return
     * @throws Exception
     */
    public List<SummaryPackageDTO> getSummaryPackage(String language) {
        try {
            logger.info("begin method getSummaryPackage of MyMetfoneBusinesDao");
            String pathFile = "summary_package_en.json";
            if (language.equals("km")) {
                pathFile = "summary_package_km.json";
            }
            Resource resource = new ClassPathResource(pathFile);
            if (!resource.exists()) {
                logger.info("File " + pathFile + " does not exist ");
                return null;
            }
            Path path = Paths.get(resource.getURI());
            Reader reader = Files.newBufferedReader(path);
            Gson gson = new Gson();
            List<SummaryPackageDTO> results = Arrays.asList(gson.fromJson(reader, SummaryPackageDTO[].class));
            return results;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * addDonatePackage
     *
     * @return
     * @throws Exception
     */
    public Integer addDonatePackage(DonatePackage donatePackage) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method addDonatePackage of MyMetfoneBusinesDao");
            sql.append("INSERT INTO DONATE_PACKAGE (ID, NAME, CODE, TITLE, COIN, ICON_URL, STATUS, DESCRIPTION, CREATED_DATE, CREATED_BY, UPDATED_DATE, UPDATED_BY)");
            sql.append(" VALUES ( :id, :name, :code, :title, :coin, :iconUrl, :status, :description, sysdate, :createdBy, sysdate, :updatedBy) ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", donatePackage.getId());
            query.setParameter("name", donatePackage.getName());
            query.setParameter("code", donatePackage.getCode());
            query.setParameter("title", donatePackage.getTitle());
            query.setParameter("coin", donatePackage.getCoin());
            query.setParameter("iconUrl", donatePackage.getIconUrl());
            query.setParameter("status", donatePackage.getStatus());
            query.setParameter("description", donatePackage.getDescription());
            query.setParameter("createdBy", donatePackage.getCreatedBy());
            query.setParameter("updatedBy", donatePackage.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method addDonatePackage of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot add donate package", exception);
            return 0;
        }
    }

    /**
     * addConfigDonate
     *
     * @return
     * @throws Exception
     */
    public Integer addConfigDonate(List<ConfigDonate> configDonates) {
        StringBuilder sql = null;
        Query query = null;
        try {
            logger.info("begin method addConfigDonate of MyMetfoneBusinesDao");
            int result = 0;
            for (ConfigDonate configDonate : configDonates) {
                Long id = getSequence("CONFIG_DONATE_SEQ");
                sql = new StringBuilder();
                sql.append("INSERT INTO CONFIG_DONATE ( ID, PARAM_NAME, PARAM_KEY, PARAM_VALUE, STATUS)");
                sql.append(" VALUES ( :id, :paramName, :paramKey, :paramValue, :status) ");
                query = getSession().createSQLQuery(sql.toString());
                query.setParameter("id", id);
                query.setParameter("paramName", configDonate.getParamName());
                query.setParameter("paramKey", configDonate.getParamKey());
                query.setParameter("paramValue", configDonate.getPramValue());
                query.setParameter("status", configDonate.getStatus());
                result += query.executeUpdate();
            }
            logger.info("end method addConfigDonate of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot add config donate", exception);
            return 0;
        }
    }

    /**
     * editConfigDonate
     *
     * @return
     * @throws Exception
     */
    public Integer editConfigDonate(List<ConfigDonate> configDonates) {
        StringBuilder sql = null;
        Query query = null;
        try {
            logger.info("begin method editConfigDonate of MyMetfoneBusinesDao");
            int result = 0;
            for (ConfigDonate configDonate : configDonates) {
                sql = new StringBuilder();
                sql.append("UPDATE CONFIG_DONATE SET PARAM_VALUE=:paramValue");
                sql.append(" WHERE  PARAM_KEY=:paramKey");
                query = getSession().createSQLQuery(sql.toString());
                query.setParameter("paramKey", configDonate.getParamKey());
                query.setParameter("paramValue", configDonate.getPramValue());
                result += query.executeUpdate();
            }
            logger.info("end method editConfigDonate of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot edit config donate", exception);
            return 0;
        }
    }

    /**
     * editDonatePackage
     *
     * @return
     * @throws Exception
     */
    public Integer editDonatePackage(DonatePackage donatePackage) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method editDonatePackage of MyMetfoneBusinesDao");
            sql.append("UPDATE DONATE_PACKAGE SET NAME=:name, CODE=:code, TITLE=:title, COIN=:coin, ");
            sql.append(" ICON_URL=:iconUrl, STATUS=:status,");
            sql.append(" DESCRIPTION=:description, UPDATED_DATE=sysdate, UPDATED_BY=:updatedBy WHERE ID=:id");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", donatePackage.getId());
            query.setParameter("name", donatePackage.getName());
            query.setParameter("code", donatePackage.getCode());
            query.setParameter("title", donatePackage.getTitle());
            query.setParameter("coin", donatePackage.getCoin());
            query.setParameter("iconUrl", donatePackage.getIconUrl());
            query.setParameter("status", donatePackage.getStatus());
            query.setParameter("description", donatePackage.getDescription());
            query.setParameter("updatedBy", donatePackage.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method editDonatePackage of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot edit donate package", exception);
            return 0;
        }
    }

    /**
     * deleteDonatePackage
     *
     * @return
     * @throws Exception
     */
    public Integer deleteDonatePackage(Long id) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method deleteDonatePackage of MyMetfoneBusinesDao");
            sql.append("UPDATE DONATE_PACKAGE SET STATUS=3 WHERE ID =:id");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            int result = query.executeUpdate();
            logger.info("end method deleteDonatePackage of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot delete donate package", exception);
            return 0;
        }
    }

    /**
     * findDonatePackageById
     *
     * @return
     * @throws Exception
     */
    public List<DonatePackageWebDTO> findDonatePackageById(Long id) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findDonatePackageById of MyMetfoneBusinesDao");
            sql.append("SELECT dp.ID id, dp.NAME name, dp.CODE code,");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.TITLE,'_en') AND PARAM_NAME='DONATE_PACKAGE') title,");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.TITLE,'_kh') AND PARAM_NAME='DONATE_PACKAGE') titleKH,");
            sql.append(" dp.COIN coin, dp.ICON_URL iconUrl, dp.STATUS status, ");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.DESCRIPTION, '_en') AND PARAM_NAME='DONATE_PACKAGE') description,");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.DESCRIPTION, '_kh') AND PARAM_NAME='DONATE_PACKAGE') descriptionKH,");
            sql.append(" TO_CHAR(dp.CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, dp.CREATED_BY createdBy, TO_CHAR(dp.UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, dp.UPDATED_BY updatedBy ");
            sql.append(" FROM DONATE_PACKAGE dp WHERE dp.ID=:id AND STATUS IN(0,1)");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("name", StringType.INSTANCE)
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("titleKH", StringType.INSTANCE)
                    .addScalar("coin", IntegerType.INSTANCE)
                    .addScalar("iconUrl", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("descriptionKH", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonatePackageWebDTO.class));
            List<DonatePackageWebDTO> donatePackageWebDTOS = query.list();
            logger.info("end method findDonatePackageById of MyMetfoneBusinesDao");
            return donatePackageWebDTOS;
        } catch (Exception e) {
            logger.error("Cannot get donate package by id", e);
            return null;
        }
    }

    /**
     * findDonatePackageByCode
     *
     * @return
     * @throws Exception
     */
    public List<Object> findDonatePackageByCode(Long id, String code) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method findDonatePackageByCode of MyMetfoneBusinesDao");
            sql.append("SELECT * FROM DONATE_PACKAGE WHERE CODE = :code AND STATUS IN(0,1)");
            if (id != null) {
                sql.append(" AND ID <> :id ");
            }
            query = getSession().createSQLQuery(sql.toString());
            query.setString("code", code);
            if (id != null) {
                query.setLong("id", id);
            }
            List<Object> result = query.list();
            logger.info("end method findDonatePackageByCode of MyMetfoneBusinesDao");
            return result;
        } catch (Exception e) {
            logger.error("Cannot find donate package by code", e);
            return null;
        }
    }

    /**
     * findConfigDonateByPramKey
     *
     * @return
     * @throws Exception
     */
    public List<Object> findConfigDonateByPramKey(String title) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            sql.append("SELECT * FROM CONFIG_DONATE WHERE PARAM_KEY = :paramKey AND PARAM_NAME='DONATE_PACKAGE' ");
            query = getSession().createSQLQuery(sql.toString());
            query.setString("paramKey", title);
            List<Object> result = query.list();
            return result;
        } catch (Exception e) {
            logger.error("Cannot find config donate by param key", e);
            return null;
        }
    }

    /**
     * searchDonatePackage
     *
     * @return
     * @throws Exception
     */
    public List<DonatePackageDTO> searchDonatePackage(String search, Integer searchFor, Integer status) {
        List<DonatePackageDTO> result = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        logger.info("searchDonatePackage start");
        try {
            sql.append("select dp.id, dp.code, dp.coin, dp.ICON_URL iconUrl, dp.STATUS status, dp.CREATED_BY createdBy, dp.UPDATED_BY updatedBy, ")
                    .append(" to_char(dp.CREATED_DATE, 'dd/MM/yyyy') createdDate, to_char(dp.UPDATED_DATE, 'dd/MM/yyyy') updatedDate, ")
                    .append(" (SELECT cd.param_value FROM config_donate cd WHERE  cd.param_key = CONCAT(dp.title,'_en')) title, ")
                    .append(" (SELECT cd.param_value FROM config_donate cd WHERE  cd.param_key = CONCAT(dp.title,'_kh')) titleKH, ")
                    .append(" (SELECT cd.param_value FROM config_donate cd WHERE  cd.param_key = CONCAT(dp.description,'_en')) description, ")
                    .append(" (SELECT cd.param_value FROM config_donate cd WHERE  cd.param_key = CONCAT(dp.description,'_kh')) descriptionKH ")
                    .append(" from DONATE_PACKAGE dp where 1=1 ");

            if (searchFor != -1) {
                if (searchFor == 0) {
                    // search for title
                    sql.append(" AND dp.TITLE IN (SELECT DISTINCT SUBSTR(cd1.param_key,0,LENGTH(cd1.param_key)-3) FROM config_donate cd1")
                            .append(" WHERE UPPER(cd1.param_value) LIKE :search AND cd1.param_name = 'DONATE_PACKAGE' AND cd1.param_key LIKE '%title%')");
                } else if (searchFor == 1) {
                    //search for coin
                    sql.append(" AND UPPER(dp.COIN) LIKE :search");
                }
            }
            if (searchFor == -1) {
                sql.append(" AND (dp.TITLE IN (SELECT DISTINCT SUBSTR(cd1.param_key,0,LENGTH(cd1.param_key)-3) FROM config_donate cd1")
                        .append(" WHERE UPPER(cd1.param_value) LIKE :search AND cd1.param_name = 'DONATE_PACKAGE' AND cd1.param_key LIKE '%title%')")
                        .append(" OR UPPER(dp.COIN) LIKE :search)");
            }
            if (status == -1) {
                sql.append(" AND dp.STATUS IN(0,1)");
            }
            if (status != -1) {
                if (status == 0) {
                    sql.append(" AND dp.STATUS =0");
                } else if (status == 1) {
                    sql.append(" AND dp.STATUS =1");
                }
            }
            sql.append(" ORDER BY dp.UPDATED_DATE desc");

            SQLQuery query = getSession().createSQLQuery(sql.toString());
            query.setParameter("search", "%" + search.toUpperCase() + "%");
//            query.setParameter("language", "en");
            query.addScalar("id", new LongType())
                    .addScalar("code", new StringType())
                    .addScalar("coin", new IntegerType())
                    .addScalar("iconUrl", new StringType())
                    .addScalar("status", new IntegerType())
                    .addScalar("createdBy", new StringType())
                    .addScalar("updatedBy", new StringType())
                    .addScalar("createdDate", new StringType())
                    .addScalar("updatedDate", new StringType())
                    .addScalar("title", new StringType())
                    .addScalar("titleKh", new StringType())
                    .addScalar("description", new StringType())
                    .addScalar("descriptionKh", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(DonatePackageDTO.class));
            return query.list();
        } catch (Exception e) {
            logger.error("searchDonatePackage.exception : ", e);
            return result;
        }
    }

    /**
     * getAllDonatePackageApp
     *
     * @return
     * @throws Exception
     */
    public List<DonatePackageAppDTO> getAllDonatePackageApp(String language) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getAllDonatePackageApp of MyMetfoneBusinesDao");
            sql.append("SELECT dp.ID id, dp.NAME name, dp.CODE code, dpp.PRICE price, ");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.TITLE,:languageTitle) AND PARAM_NAME='DONATE_PACKAGE') title,");
            sql.append(" dp.COIN coin, dpp.PAYMENT_METHOD paymentMethod, dpp.UNIT unit, dp.ICON_URL iconUrl, dp.STATUS status,");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.DESCRIPTION, :languageDescription) AND PARAM_NAME='DONATE_PACKAGE') description");
            sql.append(" FROM DONATE_PACKAGE dp, DONATE_PACKAGE_PRICE dpp");
            sql.append(" WHERE  dp.ID = dpp.DONATE_PACKAGE_ID AND dp.STATUS=1 and dpp.status = 1 ORDER BY coin, price ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("languageTitle", "_" + language);
            query.setParameter("languageDescription", "_" + language);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("name", StringType.INSTANCE)
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("price", FloatType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("coin", IntegerType.INSTANCE)
                    .addScalar("paymentMethod", StringType.INSTANCE)
                    .addScalar("unit", StringType.INSTANCE)
                    .addScalar("iconUrl", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonatePackageAppDTO.class));
            List<DonatePackageAppDTO> donatePackageAppDTOS = query.list();
            logger.info("end method getAllDonatePackageApp of MyMetfoneBusinesDao");
            return donatePackageAppDTOS;
        } catch (Exception e) {
            logger.error("Cannot get all donate package price", e);
            return null;
        }
    }

    /**
     * getAllDonatePackageWeb
     *
     * @return
     * @throws Exception
     */
    public List<DonatePackageWebDTO> getAllDonatePackageWeb() {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getAllDonatePackageApp of MyMetfoneBusinesDao");
            sql.append("SELECT dp.ID id, dp.NAME name, dp.CODE code,");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.TITLE,'_en') AND PARAM_NAME='DONATE_PACKAGE') title,");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.TITLE,'_kh') AND PARAM_NAME='DONATE_PACKAGE') titleKH,");
            sql.append(" dp.COIN coin, dp.ICON_URL iconUrl, dp.STATUS status, ");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.DESCRIPTION, '_en') AND PARAM_NAME='DONATE_PACKAGE') description,");
            sql.append(" (SELECT PARAM_VALUE FROM CONFIG_DONATE cp WHERE cp.PARAM_KEY = concat(dp.DESCRIPTION, '_kh') AND PARAM_NAME='DONATE_PACKAGE') descriptionKH,");
            sql.append(" TO_CHAR(dp.CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, dp.CREATED_BY createdBy, TO_CHAR(dp.UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, dp.UPDATED_BY updatedBy");
            sql.append(" FROM DONATE_PACKAGE dp");
            sql.append(" WHERE dp.STATUS IN (0,1) ORDER BY dp.UPDATED_DATE desc");
            query = getSession().createSQLQuery(sql.toString());
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("name", StringType.INSTANCE)
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("titleKH", StringType.INSTANCE)
                    .addScalar("coin", IntegerType.INSTANCE)
                    .addScalar("iconUrl", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("descriptionKH", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonatePackageWebDTO.class));
            List<DonatePackageWebDTO> donatePackageWebDTOS = query.list();
            logger.info("end method getAllDonatePackageWeb of MyMetfoneBusinesDao");
            return donatePackageWebDTOS;
        } catch (Exception e) {
            logger.error("Cannot get all donate package price", e);
            return null;
        }
    }

    /**
     * addDonatePackagePrice
     *
     * @return
     * @throws Exception
     */
    public Integer addDonatePackagePrice(DonatePackagePrice donatePackagePrice) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method addDonatePackagePrice of MyMetfoneBusinesDao");
            sql.append("INSERT INTO DONATE_PACKAGE_PRICE (ID, DONATE_PACKAGE_ID, PAYMENT_METHOD, PRICE, UNIT, STATUS, CREATED_DATE, CREATED_BY, UPDATED_DATE, UPDATED_BY)");
            sql.append(" VALUES ( :id, :donatePackageId, :paymentMethod, :price, :unit, :status, SYSDATE, :createdBy, SYSDATE, :updatedBy) ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", donatePackagePrice.getId());
            query.setParameter("donatePackageId", donatePackagePrice.getDonatePackageId());
            query.setParameter("paymentMethod", donatePackagePrice.getPaymentMethod());
            query.setParameter("price", donatePackagePrice.getPrice());
            query.setParameter("unit", donatePackagePrice.getUnit());
            query.setParameter("status", donatePackagePrice.getStatus());
            query.setParameter("createdBy", donatePackagePrice.getCreatedBy());
            query.setParameter("updatedBy", donatePackagePrice.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method addDonatePackagePrice of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot add donate package price", exception);
            return 0;
        }
    }

    /**
     * findDonatePackagePriceById
     *
     * @return
     * @throws Exception
     */
    public List<DonatePackagePriceDTO> findDonatePackagePriceById(Long id) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findDonatePackagePriceById of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, DONATE_PACKAGE_ID donatePackageId, PAYMENT_METHOD paymentMethod, PRICE price, UNIT unit, STATUS status, ");
            sql.append(" TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy, TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, ");
            sql.append(" UPDATED_BY updatedBy FROM DONATE_PACKAGE_PRICE WHERE ID = :id AND STATUS IN(0,1)");
            query = getSession().createSQLQuery(sql.toString());
            query.setLong("id", id);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("donatePackageId", LongType.INSTANCE)
                    .addScalar("paymentMethod", StringType.INSTANCE)
                    .addScalar("price", FloatType.INSTANCE)
                    .addScalar("unit", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonatePackagePriceDTO.class));
            List<DonatePackagePriceDTO> donatePackagePriceDTOS = query.list();
            logger.info("begin method findDonatePackagePriceById of MyMetfoneBusinesDao");
            return donatePackagePriceDTOS;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * editDonatePackagePrice
     *
     * @return
     * @throws Exception
     */
    public Integer editDonatePackagePrice(DonatePackagePrice donatePackagePrice) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method editDonatePackagePrice of MyMetfoneBusinesDao");
            sql.append("UPDATE DONATE_PACKAGE_PRICE SET PAYMENT_METHOD=:paymentMethod, PRICE=:price, UNIT=:unit, STATUS=:status, UPDATED_DATE=SYSDATE, UPDATED_BY=:updatedBy ");
            sql.append(" WHERE ID=:id");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", donatePackagePrice.getId());
            query.setParameter("paymentMethod", donatePackagePrice.getPaymentMethod());
            query.setParameter("price", donatePackagePrice.getPrice());
            query.setParameter("unit", donatePackagePrice.getUnit());
            query.setParameter("status", donatePackagePrice.getStatus());
            query.setParameter("updatedBy", donatePackagePrice.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method editDonatePackagePrice of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot edit donate package price", exception);
            return 0;
        }
    }

    /**
     * deleteDonatePackagePrice
     *
     * @return
     * @throws Exception
     */
    public Integer deleteDonatePackagePrice(Long id, Long donatePackageId) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method deleteDonatePackagePrice of MyMetfoneBusinesDao");
            sql.append("UPDATE DONATE_PACKAGE_PRICE SET STATUS=3 WHERE ID =:id AND DONATE_PACKAGE_ID =:donatePackageId");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            query.setParameter("donatePackageId", donatePackageId);
            int result = query.executeUpdate();
            logger.info("end method deleteDonatePackagePrice of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot delete donate package price", exception);
            return 0;
        }
    }

    /**
     * getAllDonatePackagePrice
     *
     * @return
     * @throws Exception
     */
    public List<DonatePackagePriceDTO> getAllDonatePackagePrice(Long donatePackageId, String account) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getAllDonatePackagePrice of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, DONATE_PACKAGE_ID donatePackageId, PAYMENT_METHOD paymentMethod, PRICE price, UNIT unit, STATUS status,");
            sql.append(" TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy, TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, ");
            sql.append(" UPDATED_BY updatedBy FROM DONATE_PACKAGE_PRICE WHERE DONATE_PACKAGE_ID=:donatePackageId AND CREATED_BY=:account AND STATUS IN (0,1)");
            sql.append(" ORDER BY createdDate");
            query = getSession().createSQLQuery(sql.toString());
            query.setLong("donatePackageId", donatePackageId);
            query.setString("account", account);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("donatePackageId", LongType.INSTANCE)
                    .addScalar("paymentMethod", StringType.INSTANCE)
                    .addScalar("price", FloatType.INSTANCE)
                    .addScalar("unit", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonatePackagePriceDTO.class));
            List<DonatePackagePriceDTO> donatePackagePriceDTOS = query.list();
            logger.info("end method getAllDonatePackagePrice of MyMetfoneBusinesDao");
            return donatePackagePriceDTOS;
        } catch (Exception e) {
            logger.error("Cannot get all donate package price", e);
            return null;
        }
    }

    /**
     * addDiscount
     *
     * @return
     * @throws Exception
     */
    public Integer addDiscount(Discount discount) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method addDiscount of MyMetfoneBusinesDao");
            sql.append("INSERT INTO DISCOUNT (ID, CODE, DISCOUNT, UNIT, STATUS, DESCRIPTION, CREATED_DATE, CREATED_BY, UPDATED_DATE, UPDATED_BY)");
            sql.append(" VALUES ( :id, :code, :discount, :unit, :status, :description, SYSDATE, :createdBy, SYSDATE, :updatedBy) ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", discount.getId());
            query.setParameter("code", discount.getCode());
            query.setParameter("discount", discount.getDiscount());
            query.setParameter("unit", discount.getUnit());
            query.setParameter("status", discount.getStatus());
            query.setParameter("description", discount.getDescription());
            query.setParameter("createdBy", discount.getCreatedBy());
            query.setParameter("updatedBy", discount.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method addDiscount of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot add discount", exception);
            return 0;
        }
    }

    /**
     * findDiscountById
     *
     * @return
     * @throws Exception
     */
    public List<DiscountDTO> findDiscountById(Long id) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findDiscountById of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CODE code, DISCOUNT discount, UNIT unit, STATUS status, DESCRIPTION description, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy,");
            sql.append(" TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, UPDATED_BY updatedBy FROM DISCOUNT WHERE ID =:id AND STATUS IN(0,1) ORDER BY UPDATED_DATE desc");
            query = getSession().createSQLQuery(sql.toString());
            query.setLong("id", id);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("discount", FloatType.INSTANCE)
                    .addScalar("unit", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class));
            List<DiscountDTO> discountDTOS = query.list();
            logger.info("end method findDiscountById of MyMetfoneBusinesDao");
            return discountDTOS;
        } catch (Exception e) {
            logger.error("Cannot find discount by id", e);
            return null;
        }
    }

    /**
     * findDiscountByCode
     *
     * @return
     * @throws Exception
     */
    public List<DiscountDTO> findDiscountByCode(Long id, String code) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findDiscountByCode of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CODE code, DISCOUNT discount, UNIT unit, STATUS status, DESCRIPTION description, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy,");
            sql.append(" TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, UPDATED_BY updatedBy FROM DISCOUNT WHERE CODE =:code AND STATUS IN(0,1)");
            if (id != null) {
                sql.append(" AND ID<>:id");
            }
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("code", code);
            if (id != null) {
                query.setParameter("id", id);
            }
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("discount", FloatType.INSTANCE)
                    .addScalar("unit", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class));
            List<DiscountDTO> discountDTOS = query.list();
            logger.info("end method findDiscountByCode of MyMetfoneBusinesDao");
            return discountDTOS;
        } catch (Exception e) {
            logger.error("Cannot find discount by code", e);
            return null;
        }
    }

    /**
     * editDiscount
     *
     * @return
     * @throws Exception
     */
    public Integer editDiscount(Discount discount) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method editDiscount of MyMetfoneBusinesDao");
            sql.append("UPDATE DISCOUNT SET CODE=:code, DISCOUNT=:discount, UNIT=:unit, STATUS=:status, DESCRIPTION=:description,");
            sql.append(" UPDATED_DATE=SYSDATE, UPDATED_BY=:updatedBy ");
            sql.append(" WHERE ID=:id");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", discount.getId());
            query.setParameter("code", discount.getCode());
            query.setParameter("discount", discount.getDiscount());
            query.setParameter("unit", discount.getUnit());
            query.setParameter("status", discount.getStatus());
            query.setParameter("description", discount.getDescription());
            query.setParameter("updatedBy", discount.getCreatedBy());
            int result = query.executeUpdate();
            logger.info("end method editDiscount of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot edit discount", exception);
            return 0;
        }
    }

    /**
     * deleteDiscount
     *
     * @return
     * @throws Exception
     */
    public Integer deleteDiscount(Long id) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method deleteDiscount of MyMetfoneBusinesDao");
            sql.append("UPDATE DISCOUNT SET STATUS=3");
            sql.append(" WHERE ID=:id");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            int result = query.executeUpdate();
            logger.info("end method deleteDiscount of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot delete discount", exception);
            return 0;
        }
    }

    /**
     * getAllDiscount
     *
     * @return
     * @throws Exception
     */
    public List<DiscountDTO> getAllDiscount() {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getAllDiscount of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CODE code, DISCOUNT discount, UNIT unit, STATUS status, DESCRIPTION description, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy,");
            sql.append(" TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate , UPDATED_BY updatedBy FROM DISCOUNT WHERE STATUS IN(0,1)");
            sql.append(" ORDER BY UPDATED_DATE desc");
            query = getSession().createSQLQuery(sql.toString());
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("discount", FloatType.INSTANCE)
                    .addScalar("unit", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class));
            List<DiscountDTO> discountDTOS = query.list();
            logger.info("end method getAllDiscount of MyMetfoneBusinesDao");
            return discountDTOS;
        } catch (Exception exception) {
            logger.error("Cannot get all discount", exception);
            return null;
        }
    }

    /**
     * searchDiscount
     *
     * @return
     * @throws Exception
     */
    public List<DiscountDTO> searchDiscount(String search, Integer searchFor, Integer status) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method searchDiscount of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CODE code, DISCOUNT discount, UNIT unit, STATUS status, DESCRIPTION description, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy,");
            sql.append(" TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, UPDATED_BY updatedBy FROM DISCOUNT WHERE 1=1");
            if (searchFor != -1) {
                if (searchFor == 0) {
                    sql.append(" AND UPPER(CODE) LIKE :search");
                } else if (searchFor == 1) {
                    sql.append(" AND DISCOUNT LIKE :search");
                }
            }
            if (status != -1) {
                if (status == 0) {
                    sql.append(" AND STATUS=0");
                } else if (status == 1) {
                    sql.append(" AND STATUS=1");
                }
            }
            if (searchFor == -1) {
                sql.append(" AND( UPPER(CODE) LIKE :search OR DISCOUNT LIKE :search ) ");
            }
            if (status == -1) {
                sql.append(" AND STATUS IN(0,1)");
            }
            sql.append(" ORDER BY UPDATED_DATE desc");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("search", "%" + search.toUpperCase() + "%");
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("code", StringType.INSTANCE)
                    .addScalar("discount", FloatType.INSTANCE)
                    .addScalar("unit", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DiscountDTO.class));
            List<DiscountDTO> discountDTOS = query.list();
            logger.info("end method searchDiscount of MyMetfoneBusinesDao");
            return discountDTOS;
        } catch (Exception exception) {
            logger.error("Cannot search discount", exception);
            return null;
        }
    }

    /**
     * addEmoneyWallet
     *
     * @return
     * @throws Exception
     */
    public Integer addEmoneyWallet(EmoneyWallet emoneyWallet) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method addEmoneyWallet of MyMetfoneBusinesDao");
            sql.append("INSERT INTO EMONEY_WALLET (ID, CUSTOMER_NAME, CAM_ID, EMONEY_ACCOUNT, STATUS, CREATED_DATE, CREATED_BY, UPDATED_DATE, UPDATED_BY)");
            sql.append(" VALUES ( :id, :customerName, :camId, :emoneyAccount, :status, SYSDATE, :createdBy, SYSDATE, :updatedBy) ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", emoneyWallet.getId());
            query.setParameter("customerName", emoneyWallet.getCustomerName());
            query.setParameter("camId", emoneyWallet.getCamId());
            query.setParameter("emoneyAccount", emoneyWallet.getEmoneyAccount());
            query.setParameter("status", emoneyWallet.getStatus());
            query.setParameter("createdBy", emoneyWallet.getCreatedBy());
            query.setParameter("updatedBy", emoneyWallet.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method addEmoneyWallet of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot add emoney wallet", exception);
            return 0;
        }
    }

    /**
     * findEmoneyWalletByEmoneyAccount
     *
     * @return
     * @throws Exception
     */
    public List<EmoneyWalletDTO> findEmoneyWalletByEmoneyAccount(Long id, String emoneyAccount) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findEmoneyWalletByEmoneyAccount of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CUSTOMER_NAME customerName, CAM_ID camId, EMONEY_ACCOUNT emoneyAccount, STATUS status, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy, TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, UPDATED_BY updatedBy");
            sql.append(" FROM EMONEY_WALLET WHERE EMONEY_ACCOUNT =:emoneyAccount AND STATUS IN(0,1)");
            if (id != null) {
                sql.append(" AND ID<>:id");
            }
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("emoneyAccount", emoneyAccount);
            if (id != null) {
                query.setParameter("id", id);
            }
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("customerName", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("emoneyAccount", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(EmoneyWalletDTO.class));
            List<EmoneyWalletDTO> emoneyWalletDTOS = query.list();
            logger.info("end method findEmoneyWalletByEmoneyAccount of MyMetfoneBusinesDao");
            return emoneyWalletDTOS;
        } catch (Exception exception) {
            logger.error("Cannot find emoney wallet by emoney account", exception);
            return null;
        }
    }

    /**
     * findEmoneyWalletById
     *
     * @return
     * @throws Exception
     */
    public List<EmoneyWalletDTO> findEmoneyWalletById(Long id) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findEmoneyWalletById of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CUSTOMER_NAME customerName, CAM_ID camId, EMONEY_ACCOUNT emoneyAccount, STATUS status, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy, TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, UPDATED_BY updatedBy");
            sql.append(" FROM EMONEY_WALLET WHERE ID =:id AND STATUS IN(0,1)");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("customerName", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("emoneyAccount", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(EmoneyWalletDTO.class));
            List<EmoneyWalletDTO> emoneyWalletDTOS = query.list();
            logger.info("end method findEmoneyWalletById of MyMetfoneBusinesDao");
            return emoneyWalletDTOS;
        } catch (Exception exception) {
            logger.error("Cannot find emoney wallet by id", exception);
            return null;
        }
    }

    /**
     * editEmoneyWallet
     *
     * @return
     * @throws Exception
     */
    public Integer editEmoneyWallet(EmoneyWallet emoneyWallet) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method editEmoneyWallet of MyMetfoneBusinesDao");
            sql.append("UPDATE EMONEY_WALLET SET CUSTOMER_NAME =:customerName, CAM_ID=:camId, EMONEY_ACCOUNT=:emoneyAccount,");
            sql.append(" STATUS=:status, UPDATED_DATE=SYSDATE, UPDATED_BY=:updatedBy");
            sql.append(" WHERE ID=:id ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", emoneyWallet.getId());
            query.setParameter("customerName", emoneyWallet.getCustomerName());
            query.setParameter("camId", emoneyWallet.getCamId());
            query.setParameter("emoneyAccount", emoneyWallet.getEmoneyAccount());
            query.setParameter("status", emoneyWallet.getStatus());
            query.setParameter("updatedBy", emoneyWallet.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method editEmoneyWallet of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot edit emoney wallet", exception);
            return 0;
        }
    }

    /**
     * deleteEmoneyWallet
     *
     * @return
     * @throws Exception
     */
    public Integer deleteEmoneyWallet(Long id) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method editEmoneyWallet of MyMetfoneBusinesDao");
            sql.append("UPDATE EMONEY_WALLET SET STATUS=3");
            sql.append(" WHERE ID=:id ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            int result = query.executeUpdate();
            logger.info("end method deleteEmoneyWallet of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot delete emoney wallet", exception);
            return 0;
        }
    }

    /**
     * searchEmoneyWallet
     *
     * @return
     * @throws Exception
     */
    public List<EmoneyWalletDTO> searchEmoneyWallet(String search, Integer searchFor, Integer status) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("end method searchEmoneyWallet of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CUSTOMER_NAME customerName, CAM_ID camId, EMONEY_ACCOUNT emoneyAccount, STATUS status, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, CREATED_BY createdBy,");
            sql.append(" TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, UPDATED_BY updatedBy FROM EMONEY_WALLET WHERE 1=1");
            if (searchFor != -1) {
                if (searchFor == 0) {
                    sql.append(" AND UPPER(CUSTOMER_NAME) LIKE :search");
                } else if (searchFor == 1) {
                    sql.append(" AND CAM_ID LIKE :search");
                } else if (searchFor == 2) {
                    sql.append(" AND EMONEY_ACCOUNT LIKE :search");
                }
            }
            if (status != -1) {
                if (status == 0) {
                    sql.append(" AND status=0");
                } else if (status == 1) {
                    sql.append(" AND status=1");
                }
            }
            if (searchFor == -1) {
                sql.append(" AND( UPPER(CUSTOMER_NAME) LIKE :search OR CAM_ID LIKE :search  OR EMONEY_ACCOUNT LIKE :search) ");
            }
            if (status == -1) {
                sql.append(" AND STATUS IN(0,1)");
            }
            sql.append(" ORDER BY updatedDate desc");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("search", "%" + search.toUpperCase() + "%");
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("customerName", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("emoneyAccount", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(EmoneyWalletDTO.class));
            List<EmoneyWalletDTO> emoneyWalletDTOS = query.list();
            logger.info("end method searchEmoneyWallet of MyMetfoneBusinesDao");
            return emoneyWalletDTOS;
        } catch (Exception exception) {
            logger.error("Cannot search emoney wallet", exception);
            return null;
        }
    }

    /**
     * getAllEmoneyWallet
     *
     * @return
     * @throws Exception
     */
    public List<EmoneyWalletDTO> getAllEmoneyWallet(Integer pageSize, Integer pageNum) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getAllEmoneyWallet of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CUSTOMER_NAME customerName, CAM_ID camId, EMONEY_ACCOUNT emoneyAccount, STATUS status, TO_CHAR(CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate,");
            sql.append(" CREATED_BY createdBy, TO_CHAR(UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, UPDATED_BY updatedBy FROM EMONEY_WALLET WHERE STATUS IN(0,1)");
            sql.append(" ORDER BY UPDATED_DATE desc ");
            query = getSession().createSQLQuery(sql.toString());
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum - 1));
                query.setMaxResults(pageSize);
            }
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("customerName", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("emoneyAccount", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("updatedDate", StringType.INSTANCE)
                    .addScalar("updatedBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(EmoneyWalletDTO.class));
            List<EmoneyWalletDTO> emoneyWalletDTOS = query.list();
            logger.info("end method getAllEmoneyWallet of MyMetfoneBusinesDao");
            return emoneyWalletDTOS;
        } catch (Exception exception) {
            logger.error("Cannot get all emoney wallet", exception);
            return null;
        }
    }

    /**
     * countTotalEmoneyWallet
     *
     * @return
     * @throws Exception
     */
    public Long countTotalEmoneyWallet() {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method countTotalEmoneyWallet of MyMetfoneBusinesDao");
            sql.append("SELECT COUNT(*) FROM EMONEY_WALLET WHERE STATUS IN(0,1)");
            query = getSession().createSQLQuery(sql.toString());
            BigDecimal bd = (BigDecimal) query.uniqueResult();
            if (bd != null) {
                return bd.longValue();
            }
            logger.info("end method countTotalEmoneyWallet of MyMetfoneBusinesDao");
        } catch (Exception exception) {
            logger.error("Cannot count total donate transaction", exception);
        }
        return 0L;
    }

//--------------------------------------------------------------------------
    public boolean isValidInput(Long id, String tableName) {
        String sql = "select count (*) from " + tableName + " where status in (0,1) and id = ?";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, id);
        try {
            List result = query.list();
            if (result != null && !result.isEmpty()) {
                Long count = Long.valueOf(result.get(0).toString());
                if (count == 1) {
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            logger.error("### Cannot check input validate", e);
        }
        return false;
    }

    public Double getPriceOfDonatePackageId(String id, String paymentMethod) {
        String sql = "select price from DONATE_PACKAGE_PRICE where DONATE_PACKAGE_ID =:id and PAYMENT_METHOD=:paymentMethod and status = 1 ";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.setParameter("paymentMethod", paymentMethod);
        try {
            List result = query.list();
            if (result != null && !result.isEmpty()) {
                Double count = Double.valueOf(result.get(0).toString());
                return count;
            }
        } catch (NumberFormatException e) {
            logger.error("### Cannot check input validate", e);
        }
        return 0D;
    }

    public Double calculateTotalPriceAfterDiscount(Double price, Long channelId) {
        List<Long> totalDiscount = this.getTotalDiscountOfChannelFromId(channelId);
        Double finalPrice = price;
        for (Long discount : totalDiscount) {
            finalPrice = finalPrice * (1 - discount / 100D);
        }
        return finalPrice;
    }

    public Map<String, String> getBonusInformationForSaveLogDonate(Long donatePackageId, String paymentMethod) {
        Map<String, String> mappingData = new HashMap<>();
        String sql = "select dpp.unit || ','  || dp.code || ',' || dp.coin from donate_package dp join donate_package_price dpp on  dp.id = dpp.donate_package_id "
                + "where dpp.donate_package_id=? and dpp.PAYMENT_METHOD=?";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, donatePackageId);
        query.setParameter(1, paymentMethod);
        try {
            List result = query.list();
            if (result != null && !result.isEmpty()) {
                String data = result.get(0).toString();
                String[] dataSplit = data.split(",", -1);
                mappingData.put("unit", dataSplit[0]);
                mappingData.put("code", dataSplit[1]);
                mappingData.put("coin", dataSplit[2]);
            }
        } catch (Exception e) {
            logger.error("### Error while create new log for DONATE_TRANSACTION");
        }
        return mappingData;
    }

    public Long getChannelDiscountDetailIdForSaveLogDonate(Long channelId) {
        String sql = "select id from channel_discount_detail where channel_id = ? and status = 1";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, channelId);
        try {
            List result = query.list();
            if (result != null && !result.isEmpty()) {
                return Long.valueOf(result.get(0).toString());
            }
        } catch (NumberFormatException e) {
            logger.error("### Error while create new log for DONATE_TRANSACTION");
        }
        return 0L;
    }

    public void saveLogAfterDonate(String customerName, String phoneNumber, String camId, String coin, Double price, String code,
            String unit, Double priceAfterDiscount, String channelName, Long channelId, Long status, String comment, String paymentMethod,
            Long chanelDiscountDetailId, String discountValue) throws Exception {
        Long id = this.getSequence("DONATE_TRANSACTION_SEQ");
        String sql = "Insert into DONATE_TRANSACTION (ID, DONATE_CUSTOMER_NAME, DONATE_USER_ID, DONATE_USER_MSISDN, STREAMER_CHANNEL_NAME, STREAMER_USER_ID, STREAMER_CHANNEL_ID, "
                + "DONATE_PACKAGE_CODE, COIN, PAYMENT_METHOD, CHANNEL_DISCOUNT_DETAIL_ID, DISCOUNT_VALUE, DISCOUNT_UNIT, TRANS_AMOUNT, DISCOUNT_AMOUNT, TOTAL_AMOUNT, TRANS_UNIT, "
                + "STATUS, STATUS_DESC, COMMENT_CUSTOMER, CREATED_DATE, CREATED_BY) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, sysdate, ?)";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, id);
        query.setParameter(1, customerName);
        query.setParameter(2, camId);
        query.setParameter(3, phoneNumber);
        query.setParameter(4, channelName);
        query.setParameter(5, "");
        query.setParameter(6, channelId);
        query.setParameter(7, code);
        query.setParameter(8, coin);
        query.setParameter(9, paymentMethod);
        query.setParameter(10, chanelDiscountDetailId);
        query.setParameter(11, discountValue);
        query.setParameter(12, unit);
        query.setParameter(13, price);
        query.setParameter(14, (price - priceAfterDiscount) * 100.00 / 100.00);
        query.setParameter(15, priceAfterDiscount * 100.00 / 100.00);
        query.setParameter(16, unit);
        query.setParameter(17, status);
        query.setParameter(18, status == 1L ? "Success" : "Fail");
        query.setParameter(19, comment);
        query.setParameter(20, "system");
        try {
            int result = query.executeUpdate();
            if (result == 1) {
                logger.info("### Insert log success: DONATE_TRANSACTION");
            }
        } catch (Exception e) {
            logger.error("### Error while create new log for DONATE_TRANSACTION", e);
        }

    }

    public String getThankMessage(Long channelId, String language) {
        String sql = "select \"COMMENT\" from CHANNEL where id = ?";
        if ("km".equals(language) || "kh".equals(language)) {
            sql = "select \"COMMENT_KH\" from CHANNEL where id = ?";
        }
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, channelId);
        try {
            List result = query.list();
            if (result != null && !result.isEmpty() && result.get(0) != null) {
                return result.get(0).toString();
            }
        } catch (Exception e) {
            logger.error("### Cannot check input validate", e);
        }
        return "Thanks for {{user}} was donated {{coin}} coin";

    }

    public List<Long> getTotalDiscountOfChannelFromId(Long channelId) {
        String sql = "select discount from DISCOUNT where id in (select discount_id from CHANNEL_DISCOUNT_DETAIL where channel_id = ? and status = 1) and status = 1";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter(0, channelId);
        try {
            List result = query.list();
            if (result != null && !result.isEmpty()) {
                List<Long> discountList = new ArrayList<Long>();
                for (Object big : result) {
                    discountList.add(((BigDecimal) big).longValue());
                }
                return discountList;
            }
        } catch (NumberFormatException e) {
            logger.error("### Cannot check input validate", e);
        }
        return new ArrayList<Long>();
    }

    /**
     * searchDonateTransaction
     *
     * @return
     * @throws Exception
     */
    public List<DonateTransactionDTO> searchDonateTransaction(String search, Integer searchFor, Integer status, String from, String to) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("end method searchDonateTransaction of MyMetfoneBusinesDao");
            sql.append("SELECT dt.ID id, dt.DONATE_CUSTOMER_NAME donateCustomerName, dt.DONATE_USER_ID donateUserId, dt.DONATE_USER_MSISDN donateUserMsisdn, dt.STREAMER_CHANNEL_NAME streamerChannelName, ");
            sql.append(" dt.STREAMER_USER_ID streamerUserId, dt.STREAMER_CHANNEL_ID streamerChannelId, dt.DONATE_PACKAGE_CODE donatePackageCode, dt.COIN coin, ");
            sql.append(" dt.PAYMENT_METHOD paymentMethod, dt.CHANNEL_DISCOUNT_DETAIL_ID channelDiscountDetailId, dt.DISCOUNT_VALUE discountValue, dt.DISCOUNT_UNIT discountUnit, ");
            sql.append(" dt.TRANS_AMOUNT transAmount, dt.DISCOUNT_AMOUNT discountAmount, dt.TOTAL_AMOUNT totalAmount, dt.TRANS_UNIT transUnit, dt.PROCESS_STATUS processStatus, ");
            sql.append(" dt.PROCESS_SYNC_DESC processSyncDesc, TO_CHAR(dt.PROCESS_TIME ,'dd/MM/yyyy HH24:mi:ss') processTime, dt.PROCESS_DESC processDesc, dt.WITHDRAW_ID withDrawId, dt.STATUS status, dt.STATUS_DESC statusDesc, dt.COMMENT_CUSTOMER commentCustomer, ");
            sql.append(" TO_CHAR(dt.CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, dt.CREATED_BY createdBy, TO_CHAR(dt.UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, dt.UPDATED_BY updatedBy ");
            sql.append(" FROM DONATE_TRANSACTION dt WHERE dt.STATUS IN(0,1)");
            if (searchFor != -1) {
                if (null != searchFor) {
                    switch (searchFor) {
                        case 0:
                            sql.append(" AND UPPER(dt.DONATE_CUSTOMER_NAME) LIKE :search");
                            break;
                        case 1:
                            sql.append(" AND dt.DONATE_USER_MSISDN LIKE :search");
                            break;
                        case 2:
                            sql.append(" AND dt.DONATE_USER_ID LIKE :search");
                            break;
                        case 3:
                            sql.append(" AND UPPER(dt.STREAMER_CHANNEL_NAME) LIKE :search");
                            break;
                        case 4:
                            sql.append(" AND dt.STREAMER_CHANNEL_ID LIKE :search");
                            break;
                        default:
                            break;
                    }
                }
            }
            if (from != null && to != null) {
                sql.append(" AND trunc(dt.CREATED_DATE) BETWEEN TO_DATE( :from, 'dd/MM/yyyy' ) AND TO_DATE( :to, 'dd/MM/yyyy' )");
            }
            if (status != -1) {
                if (status == 0) {
                    sql.append(" AND dt.STATUS=0");
                } else if (status == 1) {
                    sql.append(" AND dt.STATUS=1");
                }
            }
            if (searchFor == -1) {
                sql.append(" AND( UPPER(dt.DONATE_CUSTOMER_NAME) LIKE :search OR dt.DONATE_USER_ID LIKE :search OR dt.DONATE_USER_MSISDN LIKE :search "
                        + "OR UPPER(dt.STREAMER_CHANNEL_NAME) LIKE :search OR dt.STREAMER_CHANNEL_ID LIKE :search) ");
            }
            if (status == -1) {
                sql.append(" AND dt.STATUS IN(0,1)");
            }
            sql.append(" ORDER BY dt.CREATED_DATE desc");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("search", "%" + search.toUpperCase() + "%");
            if (from != null && to != null) {
                query.setParameter("from", from);
                query.setParameter("to", to);
            }
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("donateCustomerName", StringType.INSTANCE)
                    .addScalar("donateUserId", LongType.INSTANCE)
                    .addScalar("donateUserMsisdn", StringType.INSTANCE)
                    .addScalar("streamerChannelName", StringType.INSTANCE)
                    .addScalar("streamerUserId", LongType.INSTANCE)
                    .addScalar("streamerChannelId", LongType.INSTANCE)
                    .addScalar("donatePackageCode", StringType.INSTANCE)
                    .addScalar("coin", IntegerType.INSTANCE)
                    .addScalar("paymentMethod", StringType.INSTANCE)
                    .addScalar("channelDiscountDetailId", LongType.INSTANCE)
                    .addScalar("discountValue", StringType.INSTANCE)
                    .addScalar("discountUnit", StringType.INSTANCE)
                    .addScalar("transAmount", FloatType.INSTANCE)
                    .addScalar("discountAmount", FloatType.INSTANCE)
                    .addScalar("totalAmount", FloatType.INSTANCE)
                    .addScalar("transUnit", StringType.INSTANCE)
                    .addScalar("withDrawId", LongType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("statusDesc", StringType.INSTANCE)
                    .addScalar("commentCustomer", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonateTransactionDTO.class));
            List<DonateTransactionDTO> donateTransactionDTOS = query.list();
            logger.info("end method searchDonateTransaction of MyMetfoneBusinesDao");
            return donateTransactionDTOS;
        } catch (Exception exception) {
            logger.error("Cannot search donate transaction", exception);
            return null;
        }
    }

    /**
     * findDonateTransactionById
     *
     * @return
     * @throws Exception
     */
    public List<DonateTransactionDTO> findDonateTransactionById(Long id) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findDonateTransactionById of MyMetfoneBusinesDao");
            sql.append("SELECT dt.ID id, dt.DONATE_CUSTOMER_NAME donateCustomerName, dt.DONATE_USER_ID donateUserId, dt.DONATE_USER_MSISDN donateUserMsisdn, dt.STREAMER_CHANNEL_NAME streamerChannelName, ");
            sql.append(" dt.STREAMER_USER_ID streamerUserId, dt.STREAMER_CHANNEL_ID streamerChannelId, dt.DONATE_PACKAGE_CODE donatePackageCode, dt.COIN coin, ");
            sql.append(" dt.PAYMENT_METHOD paymentMethod, dt.CHANNEL_DISCOUNT_DETAIL_ID channelDiscountDetailId, dt.DISCOUNT_VALUE discountValue, dt.DISCOUNT_UNIT discountUnit, ");
            sql.append(" dt.TRANS_AMOUNT transAmount, dt.DISCOUNT_AMOUNT discountAmount, dt.TOTAL_AMOUNT totalAmount, dt.TRANS_UNIT transUnit, dt.PROCESS_STATUS processStatus, ");
            sql.append(" dt.PROCESS_SYNC_DESC processSyncDesc, TO_CHAR(dt.PROCESS_TIME ,'dd/MM/yyyy HH24:mi:ss') processTime, dt.PROCESS_DESC processDesc, dt.WITHDRAW_ID withDrawId, dt.STATUS status, dt.STATUS_DESC statusDesc, dt.COMMENT_CUSTOMER commentCustomer, ");
            sql.append(" TO_CHAR(dt.CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, dt.CREATED_BY createdBy, TO_CHAR(dt.UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, dt.UPDATED_BY updatedBy ");
            sql.append(" FROM DONATE_TRANSACTION dt WHERE dt.ID=:id AND dt.STATUS IN(0,1)");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("donateCustomerName", StringType.INSTANCE)
                    .addScalar("donateUserId", LongType.INSTANCE)
                    .addScalar("donateUserMsisdn", StringType.INSTANCE)
                    .addScalar("streamerChannelName", StringType.INSTANCE)
                    .addScalar("streamerUserId", LongType.INSTANCE)
                    .addScalar("streamerChannelId", LongType.INSTANCE)
                    .addScalar("donatePackageCode", StringType.INSTANCE)
                    .addScalar("coin", IntegerType.INSTANCE)
                    .addScalar("paymentMethod", StringType.INSTANCE)
                    .addScalar("channelDiscountDetailId", LongType.INSTANCE)
                    .addScalar("discountValue", FloatType.INSTANCE)
                    .addScalar("discountUnit", StringType.INSTANCE)
                    .addScalar("transAmount", FloatType.INSTANCE)
                    .addScalar("discountAmount", FloatType.INSTANCE)
                    .addScalar("totalAmount", FloatType.INSTANCE)
                    .addScalar("transUnit", StringType.INSTANCE)
                    .addScalar("withDrawId", LongType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("statusDesc", StringType.INSTANCE)
                    .addScalar("commentCustomer", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonateTransactionDTO.class));
            List<DonateTransactionDTO> donateTransactionDTOS = query.list();
            logger.info("end method findDonateTransactionById of MyMetfoneBusinesDao");
            return donateTransactionDTOS;
        } catch (Exception exception) {
            logger.error("Cannot find donate transaction by id", exception);
            return null;
        }
    }

    /**
     * getAllDonateTransaction
     *
     * @return
     * @throws Exception
     */
    public List<DonateTransactionDTO> getAllDonateTransaction(Integer pageSize, Integer pageNum) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getAllDonateTransaction of MyMetfoneBusinesDao");
            sql.append("SELECT dt.ID id, dt.DONATE_CUSTOMER_NAME donateCustomerName, dt.DONATE_USER_ID donateUserId, dt.DONATE_USER_MSISDN donateUserMsisdn, dt.STREAMER_CHANNEL_NAME streamerChannelName, ");
            sql.append(" dt.STREAMER_USER_ID streamerUserId, dt.STREAMER_CHANNEL_ID streamerChannelId, dt.DONATE_PACKAGE_CODE donatePackageCode, dt.COIN coin, ");
            sql.append(" dt.PAYMENT_METHOD paymentMethod, dt.CHANNEL_DISCOUNT_DETAIL_ID channelDiscountDetailId, dt.DISCOUNT_VALUE discountValue, dt.DISCOUNT_UNIT discountUnit, ");
            sql.append(" dt.TRANS_AMOUNT transAmount, dt.DISCOUNT_AMOUNT discountAmount, dt.TOTAL_AMOUNT totalAmount, dt.TRANS_UNIT transUnit, dt.PROCESS_STATUS processStatus, ");
            sql.append(" dt.PROCESS_SYNC_DESC processSyncDesc, TO_CHAR(dt.PROCESS_TIME ,'dd/MM/yyyy HH24:mi:ss') processTime, dt.PROCESS_DESC processDesc, dt.WITHDRAW_ID withDrawId, dt.STATUS status, dt.STATUS_DESC statusDesc, dt.COMMENT_CUSTOMER commentCustomer, ");
            sql.append(" TO_CHAR(dt.CREATED_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate, dt.CREATED_BY createdBy, TO_CHAR(dt.UPDATED_DATE ,'dd/MM/yyyy HH24:mi:ss') updatedDate, dt.UPDATED_BY updatedBy, dt.COMMENT_KH_CUSTOMER commentKhCustomer ");
            sql.append(" FROM DONATE_TRANSACTION dt WHERE dt.STATUS IN(0,1)");
            sql.append(" ORDER BY dt.CREATED_DATE desc ");
            query = getSession().createSQLQuery(sql.toString());
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum - 1));
                query.setMaxResults(pageSize);
            }
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("donateCustomerName", StringType.INSTANCE)
                    .addScalar("donateUserId", LongType.INSTANCE)
                    .addScalar("donateUserMsisdn", StringType.INSTANCE)
                    .addScalar("streamerChannelName", StringType.INSTANCE)
                    .addScalar("streamerUserId", LongType.INSTANCE)
                    .addScalar("streamerChannelId", LongType.INSTANCE)
                    .addScalar("donatePackageCode", StringType.INSTANCE)
                    .addScalar("coin", IntegerType.INSTANCE)
                    .addScalar("paymentMethod", StringType.INSTANCE)
                    .addScalar("channelDiscountDetailId", LongType.INSTANCE)
                    .addScalar("discountValue", FloatType.INSTANCE)
                    .addScalar("discountUnit", StringType.INSTANCE)
                    .addScalar("transAmount", FloatType.INSTANCE)
                    .addScalar("discountAmount", FloatType.INSTANCE)
                    .addScalar("totalAmount", FloatType.INSTANCE)
                    .addScalar("transUnit", StringType.INSTANCE)
                    .addScalar("withDrawId", LongType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("statusDesc", StringType.INSTANCE)
                    .addScalar("commentCustomer", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("createdBy", StringType.INSTANCE)
                    .addScalar("commentKhCustomer", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(DonateTransactionDTO.class));
            List<DonateTransactionDTO> donateTransactionDTOS = query.list();
            logger.info("end method getAllDonateTransaction of MyMetfoneBusinesDao");
            return donateTransactionDTOS;
        } catch (Exception exception) {
            logger.error("Cannot get all donate transaction", exception);
            return null;
        }
    }

    /**
     * countTotalDonateTransaction
     *
     * @return
     * @throws Exception
     */
    public Long countTotalDonateTransaction() {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method countTotalDonateTransaction of MyMetfoneBusinesDao");
            sql.append("SELECT COUNT(*)");
            sql.append(" FROM DONATE_TRANSACTION WHERE STATUS IN(0,1)");
            query = getSession().createSQLQuery(sql.toString());
            List result = query.list();

            logger.info("end method countTotalDonateTransaction of MyMetfoneBusinesDao");
            if (result != null && result.size() > 0) {
                return Long.valueOf(result.get(0).toString());
            }
            return 0L;
        } catch (Exception exception) {
            logger.error("Cannot count total donate transaction", exception);
            return null;
        }
    }

    /**
     * addChannelDiscountDetail
     *
     * @return
     * @throws Exception
     */
    public Integer addChannelDiscountDetail(ChannelDiscountDetail channelDiscountDetail) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method addChannelDiscountDetail of MyMetfoneBusinesDao");
            sql.append("INSERT INTO CHANNEL_DISCOUNT_DETAIL (ID, CHANNEL_ID, DISCOUNT_ID, STATUS, CREATED_DATE, CREATED_BY, UPDATED_DATE, UPDATED_BY)");
            sql.append(" VALUES ( :id, :channelId, :discountId, :status, SYSDATE, :createdBy, SYSDATE, :updatedBy) ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", channelDiscountDetail.getId());
            query.setParameter("channelId", channelDiscountDetail.getChannelId());
            query.setParameter("discountId", channelDiscountDetail.getDiscountId());
            query.setParameter("status", channelDiscountDetail.getStatus());
            query.setParameter("createdBy", channelDiscountDetail.getCreatedBy());
            query.setParameter("updatedBy", channelDiscountDetail.getUpdatedBy());
            int result = query.executeUpdate();
            logger.info("end method addChannelDiscountDetail of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot add discount channel detail", exception);
            return 0;
        }
    }

    /**
     * findChannelDiscountDetail
     *
     * @return
     * @throws Exception
     */
    public List<Object> findChannelDiscountDetail(Long id, Long channelId, Long discountId) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method findChannelDiscountDetail of MyMetfoneBusinesDao");
            sql.append("SELECT * FROM CHANNEL_DISCOUNT_DETAIL WHERE CHANNEL_ID=:channelId AND DISCOUNT_ID=:discountId AND STATUS IN(0,1)");
            if (id != null) {
                sql.append(" AND ID<>:id");
            }
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("channelId", channelId);
            query.setParameter("discountId", discountId);
            if (id != null) {
                query.setParameter("id", id);
            }
            List<Object> result = query.list();
            logger.info("end method findChannelDiscountDetail of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot find channel discount detail", exception);
            return null;
        }
    }

    /**
     * findChannelById
     *
     * @return
     * @throws Exception
     */
    public List<ChannelDTO> findChannelById(Long id) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method findChannelById of MyMetfoneBusinesDao");
            sql.append("SELECT ID id, CHANNEL_NAME channelName, IMAGE_URL imageUrl, CAM_ID camId, MSISDN msisdn, STATUS status, \"COMMENT\" \"comment\", TO_CHAR(CREATE_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate");
            sql.append(" FROM CHANNEL WHERE ID =:id AND STATUS IN(0,1)");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", id);
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("channelName", StringType.INSTANCE)
                    .addScalar("imageUrl", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("msisdn", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("comment", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(ChannelDTO.class));
            List<ChannelDTO> channelDTOS = query.list();
            logger.info("end method findChannelById of MyMetfoneBusinesDao");
            return channelDTOS;
        } catch (Exception exception) {
            logger.error("Cannot find channel by id", exception);
            return null;
        }
    }

    /**
     * editChannel
     *
     * @return
     * @throws Exception
     */
    public Integer editChannel(Channel channel) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method editChannel of MyMetfoneBusinesDao");
            sql.append("UPDATE CHANNEL SET CHANNEL_NAME =:channelName, IMAGE_URL=:imageUrl,");
            if (channel.getCamId() != null) {
                sql.append(" CAM_ID=:camId,");
            }
            sql.append(" MSISDN=:msisdn, STATUS=:status, \"COMMENT\"=:comment, CREATE_DATE=SYSDATE, banner_url=:bannerUrl, comment_kh=:commentKh ");
            sql.append(" WHERE ID=:id ");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", channel.getId());
            query.setParameter("channelName", channel.getChannelName());
            query.setParameter("imageUrl", channel.getImageUrl());
            if (channel.getCamId() != null) {
                query.setParameter("camId", channel.getCamId());
            }
            query.setParameter("msisdn", channel.getMsisdn());
            query.setParameter("status", channel.getStatus());
            query.setParameter("comment", channel.getComment());
            query.setParameter("bannerUrl", channel.getBannerUrl());
            query.setParameter("commentKh", channel.getCommentKh());
            int result = query.executeUpdate();
            logger.info("end method editChannel of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot edit channel", exception);
            return 0;
        }
    }

    /**
     * addChannel
     *
     * @return
     * @throws Exception
     */
    public Integer addChannel(Channel channel) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method addChannel of MyMetfoneBusinesDao");
            sql.append("INSERT INTO CHANNEL (ID, CHANNEL_NAME, IMAGE_URL,");
            if (channel.getCamId() != null) {
                sql.append(" CAM_ID,");
            }
            sql.append(" MSISDN, STATUS, \"COMMENT\", CREATE_DATE, BANNER_URL, COMMENT_KH)");
            sql.append(" VALUES ( :id, :channelName, :imageUrl,");
            if (channel.getCamId() != null) {
                sql.append(" :camId,");
            }
            sql.append(" :msisdn, :status, :comment, SYSDATE, :bannerUrl, :commentKh)");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("id", channel.getId());
            query.setParameter("channelName", channel.getChannelName());
            query.setParameter("imageUrl", channel.getImageUrl());
            if (channel.getCamId() != null) {
                query.setParameter("camId", channel.getCamId());
            }
            query.setParameter("msisdn", channel.getMsisdn());
            query.setParameter("status", channel.getStatus());
            query.setParameter("comment", channel.getComment());
            query.setParameter("bannerUrl", channel.getBannerUrl());
            query.setParameter("commentKh", channel.getCommentKh());
            int result = query.executeUpdate();
            logger.info("end method addChannel of MyMetfoneBusinesDao");
            return result;
        } catch (Exception exception) {
            logger.error("Cannot add channel", exception);
            return 0;
        }
    }

    /**
     * getListChannel
     *
     * @return
     */
    public List<ChannelDTO> getListChannel() {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getListChannel of MyMetfoneBusinesDao");
            sql.append("SELECT c.ID id, c.CHANNEL_NAME channelName, c.IMAGE_URL imageUrl, c.CAM_ID camId, c.MSISDN msisdn, ");
            sql.append(" c.STATUS status, c.\"COMMENT\" \"comment\", TO_CHAR(c.CREATE_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate,"
                    + " c.banner_url bannerUrl, ew.emoney_account emoneyAccount, c.comment_kh commentKh, ew.id emoneyAccountId ");
            sql.append(" FROM CHANNEL c left join EMONEY_WALLET ew ON c.cam_id = ew.cam_id WHERE c.STATUS IN(0,1)");
            sql.append(" ORDER BY c.CREATE_DATE desc");
            query = getSession().createSQLQuery(sql.toString());
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("channelName", StringType.INSTANCE)
                    .addScalar("imageUrl", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("msisdn", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("comment", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("bannerUrl", StringType.INSTANCE)
                    .addScalar("emoneyAccount", StringType.INSTANCE)
                    .addScalar("commentKh", StringType.INSTANCE)
                    .addScalar("emoneyAccountId", LongType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(ChannelDTO.class));
            List<ChannelDTO> channelDTOS = query.list();
            logger.info("end method getListChannel of MyMetfoneBusinesDao");
            return channelDTOS;
        } catch (Exception exception) {
            logger.error("Cannot get list channel", exception);
            return null;
        }
    }

    /**
     * getListPaymentCode
     *
     * @return
     * @throws Exception
     */
    public List<String> getListPaymentMethod() {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method getListPaymentMethod of MyMetfoneBusinesDao");
            sql.append("SELECT PARAM_KEY FROM CONFIG_DONATE WHERE PARAM_NAME ='DONATE_PACKAGE_PAYMENT_CODE_UNIT'");
            query = getSession().createSQLQuery(sql.toString());
            List result = query.list();
            if (DataUtil.isNullOrEmpty(result)) {
                return new ArrayList<String>();
            }
            List<String> units = new ArrayList<String>();
            for (Object object : result) {
                units.add(object.toString());
            }
            logger.info("end method getListPaymentMethod of MyMetfoneBusinesDao");
            return units;
        } catch (Exception exception) {
            logger.error("Cannot get list payment method", exception);
            return null;
        }
    }

    /**
     * findUnitPaymentMethod
     *
     * @return
     * @throws Exception
     */
    public List<String> findUnitPaymentMethod(String paymentMethod) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method findUnitPaymentMethod of MyMetfoneBusinesDao");
            sql.append("SELECT PARAM_VALUE FROM CONFIG_DONATE WHERE PARAM_NAME ='DONATE_PACKAGE_PAYMENT_CODE_UNIT' AND PARAM_KEY=:paymentMethod");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("paymentMethod", paymentMethod);
            List result = query.list();
            if (DataUtil.isNullOrEmpty(result)) {
                return new ArrayList<String>();
            }
            List<String> units = new ArrayList<String>();
            for (Object object : result) {
                units.add(object.toString());
            }
            logger.info("end method findUnitPaymentMethod of MyMetfoneBusinesDao");
            return units;
        } catch (Exception exception) {
            logger.error("Cannot get unit by payment method", exception);
            return null;
        }
    }

    /**
     * findUser
     *
     * @return
     * @throws Exception
     */
    public List<String> findUser(String account) {
        StringBuilder sql = new StringBuilder();
        Query query = null;
        try {
            logger.info("begin method findUser of MyMetfoneBusinesDao");
            sql.append("SELECT LOGIN FROM USERS WHERE ACTIVATED = 1 AND LOGIN=:account");
            query = getSession().createSQLQuery(sql.toString());
            query.setParameter("account", account);
            List result = query.list();
            if (DataUtil.isNullOrEmpty(result)) {
                return new ArrayList<String>();
            }
            List<String> logins = new ArrayList<String>();
            for (Object object : result) {
                logins.add(object.toString());
            }
            logger.info("end method findUser of MyMetfoneBusinesDao");
            return logins;
        } catch (Exception exception) {
            logger.error("Cannot get user", exception);
            return null;
        }
    }

    public List<ChannelDiscountDetailDTO> getAllChannelDiscountDetail(String channelId) {
        logger.info("Start business getAllChannelDiscountDetail of LoyaltyRoutingServiceImpl");
        String sql = "select d.id id, d.channel_id channelId, c.channel_name channelName, d.discount_id discountId, s.code discountCode, d.status,"
                + " to_char(d.created_date, 'dd/mm/yyyy') createdDate, s.description description "
                + "from channel_discount_detail d join channel c on d.channel_id = c.id "
                + "join discount s on d.discount_id = s.id where d.channel_id =:id and d.status in (0,1) ";
        Query query = getSession().createSQLQuery(sql)
                .addScalar("id", LongType.INSTANCE)
                .addScalar("channelId", LongType.INSTANCE)
                .addScalar("channelName", StringType.INSTANCE)
                .addScalar("discountId", LongType.INSTANCE)
                .addScalar("discountCode", StringType.INSTANCE)
                .addScalar("status", LongType.INSTANCE)
                .addScalar("createdDate", StringType.INSTANCE)
                .addScalar("description", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(ChannelDiscountDetailDTO.class));
        query.setParameter("id", channelId);
        try {
            List<ChannelDiscountDetailDTO> result = query.list();
            return result;
        } catch (Exception e) {
            logger.error("### An error occured while get list channel discount detail", e);
        }
        return null;
    }

    public List<ChannelDTO> getChannelDetail(String channelId) {
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getChannelDetail of MyMetfoneBusinesDao");
            sql.append("SELECT c.ID id, c.CHANNEL_NAME channelName, c.IMAGE_URL imageUrl, c.CAM_ID camId, c.MSISDN msisdn, ");
            sql.append(" c.STATUS status, c.\"COMMENT\" \"comment\", TO_CHAR(c.CREATE_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate,"
                    + " c.banner_url bannerUrl, ew.emoney_account emoneyAccount, c.comment_kh commentKh ");
            sql.append(" FROM CHANNEL c left join EMONEY_WALLET ew ON c.cam_id = ew.cam_id WHERE c.STATUS IN(0,1) and c.id=:id ");
            sql.append(" ORDER BY c.CREATE_DATE");
            query = getSession().createSQLQuery(sql.toString());
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("channelName", StringType.INSTANCE)
                    .addScalar("imageUrl", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("msisdn", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("comment", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("bannerUrl", StringType.INSTANCE)
                    .addScalar("emoneyAccount", StringType.INSTANCE)
                    .addScalar("commentKh", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(ChannelDTO.class));
            query.setParameter("id", channelId);
            List<ChannelDTO> channelDTOS = query.list();
            logger.info("end method getChannelDetail of MyMetfoneBusinesDao");
            return channelDTOS;
        } catch (Exception exception) {
            logger.error("Cannot get channel detail with id=" + channelId, exception);
            return null;
        }
    }

    public List<ChannelDTO> searchChannel(Long status, Long searchFor, String data) {
        String regex = "^[0-9]*$";
        boolean isNumber = Pattern.matches(regex, data);
        StringBuilder sql = new StringBuilder();
        SQLQuery query = null;
        try {
            logger.info("begin method getChannelDetail of MyMetfoneBusinesDao");
            sql.append("SELECT c.ID id, c.CHANNEL_NAME channelName, c.IMAGE_URL imageUrl, c.CAM_ID camId, c.MSISDN msisdn, ");
            sql.append(" c.STATUS status, c.\"COMMENT\" \"comment\", TO_CHAR(c.CREATE_DATE ,'dd/MM/yyyy HH24:mi:ss') createdDate,"
                    + " c.banner_url bannerUrl, ew.emoney_account emoneyAccount, c.comment_kh commentKh ");
            sql.append(" FROM CHANNEL c left join EMONEY_WALLET ew ON c.cam_id = ew.cam_id WHERE 1=1 ");
            if (!Objects.equals(STATUS_ALL, status)) {
                sql.append(" and c.status =:status ");
            }
            if (!Objects.equals(SEARCH_ALL, searchFor)) {
                if (0L == searchFor) {
                    sql.append(" and c.id =:data ");
                }
                if (1L == searchFor) {
                    sql.append(" and UPPER(c.channel_name) like :data ");
                }
                if (2L == searchFor) {
                    sql.append(" and c.cam_id =:data ");
                }
                if (3L == searchFor) {
                    sql.append(" and c.msisdn =:data ");
                }
            } else {
                if (StringUtils.isNotEmpty(data)) {
                    if (isNumber) {
                        sql.append(" and (c.cam_id =:data or c.msisdn =:data )");
                    } else {
                        sql.append(" and (UPPER(c.channel_name) like :data )");
                    }
                }
            }
            sql.append(" ORDER BY c.CREATE_DATE desc");
            query = getSession().createSQLQuery(sql.toString());
            query.addScalar("id", LongType.INSTANCE)
                    .addScalar("channelName", StringType.INSTANCE)
                    .addScalar("imageUrl", StringType.INSTANCE)
                    .addScalar("camId", LongType.INSTANCE)
                    .addScalar("msisdn", StringType.INSTANCE)
                    .addScalar("status", IntegerType.INSTANCE)
                    .addScalar("comment", StringType.INSTANCE)
                    .addScalar("createdDate", StringType.INSTANCE)
                    .addScalar("bannerUrl", StringType.INSTANCE)
                    .addScalar("emoneyAccount", StringType.INSTANCE)
                    .addScalar("commentKh", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(ChannelDTO.class));
            if (!Objects.equals(STATUS_ALL, status)) {
                query.setParameter("status", status);
            }
            if (!Objects.equals(SEARCH_ALL, searchFor)) {
                if (1L == searchFor) {
                    query.setParameter("data", "%" + data.toUpperCase() + "%");
                } else {
                    query.setParameter("data", data);
                }
            } else {
                if (StringUtils.isNotEmpty(data)) {
                    if (isNumber) {
                        query.setParameter("data", data);
                    } else {
                        query.setParameter("data", "%" + data.toUpperCase() + "%");
                    }
                }
            }
            List<ChannelDTO> channelDTOS = query.list();
            logger.info("end method getChannelDetail of MyMetfoneBusinesDao");
            return channelDTOS;
        } catch (Exception e) {
            logger.error("Cannot get channel detail", e);
            return null;
        }
    }

    /*
        camid 1:1 emoney_account
     */
    public boolean isCamIdExistInEmoneyWallet(String camId) {
        String sql = "select count(*) from emoney_wallet where cam_id=:camId";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("camId", camId);
        try {
            List result = query.list();
            if (result != null && !result.isEmpty()) {
                int count = Integer.valueOf(result.get(0).toString());
                return count > 0;
            }
        } catch (Exception e) {
            logger.error("Cannot check isCamIdExistInEmoneyWallet", e);
        }
        return true;
    }

    public boolean deleteDiscountChannel(String channelId, String discountId, String account) {
        String sql = "update channel_discount_detail set status = 3, updated_date = sysdate, updated_by =:account where channel_id=:cId and discount_id=:dId ";
        Query query = getSession().createSQLQuery(sql);
        query.setParameter("account", account);
        query.setParameter("cId", channelId);
        query.setParameter("dId", discountId);
        try {
            int result = query.executeUpdate();
            if (result != 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            logger.error("Cannot check deleteDiscountChannel", e);
            return false;
        }
    }

    public boolean updateTotalTimeConfirmWrongOTP(String isdn, String service, boolean isReset) {
        String sql = "";
        if (isReset) {
            sql = "update otp set total_fail = 0, status = 1 where isdn=:isdn and service=:service";
        } else {
            sql = "update otp set total_fail = nvl(total_fail,0) + 1 where isdn=:isdn and service=:service";
        }
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn);
            query.setParameter("service", service);
            int result = query.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            logger.error("Cannot updateTotalTimeConfirmWrongOTP", e);
            return false;
        }
    }

    public Long getTotalTimeFailOTP(String isdn, String service) {
        String sql = "select nvl(total_fail,0) from otp where isdn=:isdn and service=:service and status = 0";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("isdn", isdn);
            query.setParameter("service", service);
            query.setMaxResults(1);
            List result = query.list();
            if (result != null && !result.isEmpty()) {
                return Long.valueOf(result.get(0).toString());
            }
        } catch (Exception e) {
            logger.error("Cannot checkTotalTimeFailOTP", e);
        }
        return 5L;
    }

    public boolean lockIsdnWrongOtpSeveralTime(String isdn, String service, Long second) {
        String sql = "update otp set lock_until = sysdate + (1/86400*:second), total_fail = 0 where isdn=:isdn and service=:service";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("second", second);
            query.setParameter("isdn", isdn);
            query.setParameter("service", service);
            int result = query.executeUpdate();
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("Cannot lockIsdnWrongOtpSeveralTime", e);
        }
        return false;
    }

    public boolean checkDonatePackageWithMethodExisted(String donatePakageId, String paymentMethod, String id) {
        String sql = "select count(*) from donate_package_price where donate_package_id=:dpid and status in (1,0) and payment_method=:method ";
        if (StringUtils.isNotEmpty(id)) {
            sql += "and id<>:id";
        }
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter("dpid", donatePakageId);
            query.setParameter("method", paymentMethod);
            if (StringUtils.isNotEmpty(id)) {
                query.setParameter("id", id);
            }

            BigDecimal result = (BigDecimal) query.uniqueResult();

            if (result.longValue() == 0L) {
                return false;
            }
        } catch (Exception e) {
            logger.error("Cannot checkDonatePackageWithMethodExisted", e);
        }
        return true;
    }
}
