package co.siten.myvtg.dao;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import co.siten.myvtg.dto.MoneyTransferItems;

import javax.persistence.ParameterMode;
import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;

/**
 *
 * @author daibq
 *
 */
@Repository("MkishareDao")
@PropertySource(value = {"classpath:database.properties"})
public class MkishareDao extends AbstractMkishareDao<Object> {

    private static final Logger logger = Logger.getLogger(MkishareDao.class.getName());
    private StringBuffer br = new StringBuffer();

    /**
     * @author daibq
     * @param subID
     * @param passwd
     * @return
     * @throws Exception
     */
    public boolean checkPassword(Long subID, String passwd) throws Exception {
        logger.info("Start checkPassword API off MkishareDao");
        String sql = "select * from ISHARE_SUM where sub_id = ? AND PASSWD = ? ";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, subID).setParameter(1, passwd);

            List<Object[]> lstResult = query.list();
            logger.info("end checkPassword API off MkishareDao");
            if (!lstResult.isEmpty()) {
                return true;
            }

        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * @author daibq
     * @param sub_id
     * @return
     */
    public MoneyTransferItems checkNumberTime(Long sub_id) {
        logger.info("Start checkNumberTime API off MkishareDao");
        String sql = "SELECT count(1) numberTime FROM MONEY_TRANSFER_LOG WHERE sub_id = ? AND insert_date > trunc(sysdate)";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, sub_id);
            List<Object> lstResult = query.list();
            if (!lstResult.isEmpty()) {
                MoneyTransferItems moneyTransferItems = new MoneyTransferItems();

                moneyTransferItems.setNumberTime(Long.parseLong(lstResult.get(0).toString()));
                logger.info("end checkNumberTime API off MkishareDao");
                return moneyTransferItems;
            }
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * @author daibq
     * @param phoneReceiver
     * @return
     */
    public MoneyTransferItems checkNumberTimeReceiver(String phoneReceiver) {
        logger.info("Start checkNumberTimeReceiver API off MkishareDao");
        String sql = "select count(1) numberTime from MONEY_TRANSFER_LOG where receiver= ? and insert_date > trunc(sysdate)";
        try {
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, phoneReceiver);
            List<Object> lstResult = query.list();
            logger.info("end checkNumberTimeReceiver API off MkishareDao");
            if (!lstResult.isEmpty()) {
                MoneyTransferItems moneyTransferItems = new MoneyTransferItems();
                moneyTransferItems.setNumberTime(Long.parseLong(lstResult.get(0).toString()));
                return moneyTransferItems;
            }
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * @author daibq
     * @return
     * @throws Exception
     */
    public long getIndex() throws Exception {
        try {
            ProcedureCall call = getSession().createStoredProcedureCall("call get_ca_cdr_seq(?,?)");
            call.registerParameter("date", Date.class, ParameterMode.IN).bindValue(new Date());
            call.registerParameter("result", Long.class, ParameterMode.OUT);
            Long result = (Long) call.getOutputs().getOutputParameterValue("result");
//            assertEquals(Long.valueOf(2), result);
            return result;
        } catch (Exception ex) {
            logger.error("Error Exception" + ex.getMessage());
        }
        return 0;
    }

    /**
     * @author daibq
     * @param logger
     * @param subID
     * @param msisdn
     * @param receiver
     * @param moneySenderBefore
     * @param moneyTransfer
     * @param moneySenderAfter
     * @param feeTransfer
     * @return
     * @throws Exception
     */
    public int insertMoneyTransferLog(Logger logger, Long subID, String msisdn, String receiver,
            int moneySenderBefore, int moneyTransfer, int moneySenderAfter, Long feeTransfer) throws Exception {
        Session session = null;
        session = getSession();
        String decirption = "success";
//        int feeTransfer = 10000;
        try {
            String sql = "INSERT INTO money_transfer_log (sub_id , msisdn , receiver , money_sender_before , money_transfer , fee_transfer , money_sender_after , insert_date , description) "
                    + "VALUES (? , ? , ? , ? , ? , ? , ? , sysdate , ? )";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, subID)
                    .setParameter(1, msisdn)
                    .setParameter(2, receiver)
                    .setParameter(3, moneySenderBefore)
                    .setParameter(4, moneyTransfer)
                    .setParameter(5, feeTransfer)
                    .setParameter(6, moneySenderAfter)
                    .setParameter(7, decirption);
            int result = query.executeUpdate();
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
        }
        return 0;
    }

    /**
     * daibq
     *
     * @param logger
     * @param msisdn
     * @param messae
     * @param channel
     * @return
     * @throws Exception
     */
    public int insertMT(String msisdn, String messae, String channel) throws Exception {
        logger.info("Start insertMT API off MkishareDao");
//        String channel = "113";
        try {
            String sql = "insert into mt (MT_ID,mo_his_id,msisdn,message,receive_time,retry_num,channel) "
                    + "values(mt_seq.nextval , ? , ? , ? , sysdate , 1 , ?)";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, Long.parseLong(channel))
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
