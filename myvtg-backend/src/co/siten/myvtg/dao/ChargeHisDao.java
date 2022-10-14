package co.siten.myvtg.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.ChargeHis;
import co.siten.myvtg.model.myvtg.TransactionLog;

/**
 * 
 * @author thomc
 *
 */

@Repository("ChargeHisDao")
@Qualifier("myvtgtx")
public class ChargeHisDao extends AbstractMyvtgDao<ChargeHis> {

	public TransactionLog findById(Integer id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("chargeHisId", id));
		return (TransactionLog) criteria.uniqueResult();
	}
}
