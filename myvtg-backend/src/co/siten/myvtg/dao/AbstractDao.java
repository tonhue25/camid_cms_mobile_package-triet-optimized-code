package co.siten.myvtg.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * 
 * @author thomc
 *
 */
public abstract class AbstractDao<T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public abstract Session getSession();


	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		Criteria criteria = createEntityCriteria();
		return criteria.list();
	}

}