package co.siten.myvtg.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.FakeMo;

/**
 * 
 * @author thomc
 *
 */

@Repository("FakeMoDao")
@Qualifier("myvtgtx")
public class FakeMoDao extends AbstractMyvtgDao<FakeMo> {
	public List<FakeMo> getFakeMoToProcess(Long time, int status) {
		StringBuilder sb = new StringBuilder(
				"SELECT fm FROM FakeMo fm WHERE status= :status and processTime= :time ORDER BY createdTime ASC");
		Query query = getSession().createQuery(sb.toString());
		query.setLong("time", time);
		query.setInteger("status", status);
		return query.list();
	}

	public List<FakeMo> getFakeMoToSync(Long time, int status) {
		StringBuilder sb = new StringBuilder(
				"SELECT fm FROM FakeMo fm WHERE status= :status and processTime <= :time ORDER BY createdTime ASC");
		Query query = getSession().createQuery(sb.toString());
		query.setLong("time", time);
		query.setInteger("status", status);
		return query.list();
	}

	public int updateFakeMoToProcess(Long time, int status) {
		StringBuilder sb = new StringBuilder(
				"UPDATE FakeMo SET processTime = :processTime, status= :status WHERE ((status is null) OR (status != 2))");
		if (status == 0) {
			sb.append(" AND processTime =:time");
		}
		Query query = getSession().createQuery(sb.toString());
		if (status == 0) {
			query.setLong("processTime", 0);
			query.setLong("time", time);
		} else {
			query.setLong("processTime", time);
		}
		query.setLong("status", status);
		int result = query.executeUpdate();
		return result;
	}
}
