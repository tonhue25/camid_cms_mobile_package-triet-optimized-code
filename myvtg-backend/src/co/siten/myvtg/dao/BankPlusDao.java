/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dao;

import co.siten.myvtg.util.DataUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author daibq
 */
@Repository("BankPlusDao")
@PropertySource(value = {"classpath:database.properties"})
public class BankPlusDao extends AbstractBankPlusDao<Object> {

    private static final Logger logger = Logger.getLogger(BankPlusDao.class.getName());
    private StringBuffer br = new StringBuffer();

    /**
     *
     * @param msisdn
     * @return
     */
    public boolean checkWallet(String msisdn) {
        try {
            String sql = "SELECT   1 "
                    + "  FROM   bankplus.cust_ussd cu, bankplus.cust_account b "
                    + "  WHERE       msisdn = ? "
                    + "         AND cu.acc_id = b.acc_id "
                    + "         AND b.acc_status <> ? "
                    + "         AND b.viettel_bank_code = ? ";
            Query query = getSession().createSQLQuery(sql);
            query.setParameter(0, msisdn);
            query.setParameter(1, "3");
            query.setParameter(2, "FMC");
            List lstResult = query.list();
            logger.info("checkWallet " + lstResult.size());
            if (!DataUtil.isNullOrEmpty(lstResult)) {
                return true;
            }
        } catch (Exception ex) {
            logger.info("Error checkWallet " + ex);
            return false;
        }
        return false;
    }

    @Override
    public Object get(Class cls, Long id) throws Exception {
        return super.get(cls, id); //To change body of generated methods, choose Tools | Templates.
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
    public void delete(Object entity) {
        super.delete(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object entity) {
        super.update(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(Object entity) {
        super.persist(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Session getSession() {
        return super.getSession(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
