package co.siten.myvtg.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author thomc
 *
 */
public abstract class AbstractMyvtgDao< T> extends AbstractDao<T> {

    @Autowired
    @Qualifier(value = "myvtgSessionFactory")
    SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void persist(T entity) {
        Session session = getSession();
        session.persist(entity);
        session.flush();
//        getSession().persist(entity);
    }

    public void update(T entity) {
        Session session = getSession();
        session.saveOrUpdate(entity);
        session.flush();
    }

    public void delete(T entity) {
        Session session = getSession();
        session.delete(entity);
        session.flush();
//        getSession().delete(entity);
    }

    //daibq bo sung
    public String save(T entity) {
        Session session = getSession();
        String id = (String) session.save(entity);
        session.flush();
        return id;
    }

    public Long insert(T entity) {
        Session session = getSession();
        Long id = (Long) session.save(entity);
        session.flush();
        return id;
    }

    public T get(Class cls, Long id) throws Exception {
        if (id instanceof Long) {
            return (T) getSession().get(cls, id);
        }
        throw new Exception("Not support Id type");
    }
    public T get(Class cls, String id) throws Exception {
        if (id instanceof String) {
            return (T) getSession().get(cls, id);
        }
        throw new Exception("Not support Id type");
    }

    public T getCode(Class cls, String code) throws Exception {
        if (code instanceof String) {
            return (T) getSession().get(cls, code);
        }
        throw new Exception("Not support code type");
    }

    public java.sql.Date getTime() {
        String sql = "select SYSDATE as system_datetime FROM DUAL";
        try {
            SQLQuery query = getSession().createSQLQuery(sql);
            Timestamp lstResult = (Timestamp) query.uniqueResult();
            if (lstResult != null) {
                return new Date(lstResult.getTime());
            }

        } catch (Exception ex) {
           return null;
        }
        return null;
    }
}
