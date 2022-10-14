package co.siten.myvtg.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author thomc
 *
 */
public abstract class AbstractApigwDao<T> extends AbstractDao<T> {

    @Autowired
    @Qualifier(value = "apigwSessionFactory")
    private SessionFactory sessionFactory;

    @Override
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
}
