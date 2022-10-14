package co.siten.myvtg.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.CmsLog;

@Repository("CmsLogDao")
public interface CmsLogDao extends JpaRepository<CmsLog, BigDecimal> {
	

}
