package co.siten.myvtg.dao;

import co.siten.myvtg.model.apigw.Account;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository("AccountDao")
@PropertySource(value = {"classpath:database.properties"})
public class AccountDao extends AbstractApigwDao<Object> {

    private static final Logger logger = Logger.getLogger(AccountDao.class.getName());

    @Autowired
    private Environment environment;

    public Account findById(BigDecimal id) {
        logger.info("Start findById API off AccountDao");
        String sql = "select sg from Account sg where sg.id = :id ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter("id", id);
            Account lstResult = (Account) query.uniqueResult();
            logger.info("End findById API off AccountDao");
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }

    public Account findByPhoneNumber(String phoneNumber) {
        logger.info("Start findByPhoneNumber API off AccountDao");
        String sql = "select sg from Account sg where sg.phoneNumber = :phoneNumber ";
        try {
            Query query = getSession().createQuery(sql);
            query.setParameter("phoneNumber", phoneNumber);
            Account lstResult = (Account) query.uniqueResult();
            if (lstResult != null) {
                return lstResult;
            }
        } catch (Exception ex) {
            logger.info("Error db " + ex.getMessage(), ex);
        }
        return null;
    }
}
