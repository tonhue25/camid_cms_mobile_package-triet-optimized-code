package co.siten.myvtg.dao;


import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thomc
 *
 */
@Repository("SmsDao")
@PropertySource(value = {"classpath:database.properties"})
public class SmsDao extends AbstractSmsDao<Object> {

    private static final Logger logger = Logger.getLogger(SmsDao.class.getName());
    @Autowired
    private Environment environment;

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
        logger.info("Start insertMT off SmsDao");
//        String channel = "113";
        try {
            String sql = "insert into mt (MT_ID,mo_his_id,msisdn,message,receive_time,retry_num,channel) "
                    + "values(mt_seq.nextval , ? , ? , ? , sysdate , 1 , ?)";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, 1)
                    .setParameter(1, msisdn)
                    .setParameter(2, messae)
                    .setParameter(3, channel);
            int result = query.executeUpdate();
//            session.getTransaction().commit();
            logger.info("result: " + result);
            return result;
        } catch (Exception ex) {
            logger.info("error: " + ex.getMessage(), ex);
//            session.beginTransaction().rollback();
        }
        return 0;
    }

}
