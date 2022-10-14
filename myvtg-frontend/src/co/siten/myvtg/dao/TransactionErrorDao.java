
package co.siten.myvtg.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.TransactionError;

@Repository("TransactionErrorDao")
public interface TransactionErrorDao extends JpaRepository<TransactionError, Long> {
	public Long countByStaTimeGreaterThanEqual(Date now);

}
