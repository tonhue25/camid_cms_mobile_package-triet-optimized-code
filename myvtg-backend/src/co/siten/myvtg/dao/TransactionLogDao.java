package co.siten.myvtg.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.TransactionLog;

/**
 * 
 * @author thomc
 *
 */

@Repository("TransactionLogDao")
@Qualifier("myvtgtx")
public class TransactionLogDao extends AbstractMyvtgDao< TransactionLog> {

	public TransactionLog findById(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (TransactionLog) criteria.uniqueResult();
	}
}
