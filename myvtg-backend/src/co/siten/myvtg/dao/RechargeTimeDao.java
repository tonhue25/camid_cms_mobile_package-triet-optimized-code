package co.siten.myvtg.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.RechargeTime;
import co.siten.myvtg.model.myvtg.TransactionLog;

/**
 * 
 * @author thomc
 *
 */

@Repository("RechargeTimeDao")
@Qualifier("myvtgtx")
public class RechargeTimeDao extends AbstractMyvtgDao< RechargeTime> {

	public TransactionLog findById(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (TransactionLog) criteria.uniqueResult();
	}
}
