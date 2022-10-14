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
public abstract class AbstractCmpreDao<T> extends AbstractDao<T> {
	@Autowired
	@Qualifier(value = "cmpreSessionFactory")
	SessionFactory sessionFactory;

	@Override
	public Session getSession() {
		sessionFactory.getCurrentSession();
		return sessionFactory.getCurrentSession();
	}

	public void persist(T entity) {
		getSession().persist(entity);
	}

	public void update(T entity) {
		getSession().saveOrUpdate(entity);
	}

	public void delete(T entity) {
		getSession().delete(entity);
	}
}