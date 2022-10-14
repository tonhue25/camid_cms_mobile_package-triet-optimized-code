
package co.siten.myvtg.dao;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.HotNew;

@Repository("HotNewDao")
public interface HotNewDao extends JpaRepository<HotNew, String> {
	
	public Page<HotNew> findByEffectTimeBetweenAndLanguageAndStateAndStatus(Date from, Date to,String language, int state,int status,Pageable pageRequest);
	
	public Page<HotNew> findByEffectTimeBetweenAndLanguageAndStatus(Date from, Date to,String language,int status, Pageable pageRequest);

	public Page<HotNew> findByEffectTimeBetweenAndStatus(Date from, Date to,int status, Pageable pageRequest);
	
	public Page<HotNew> findByEffectTimeBetweenAndStateAndStatus(Date from, Date to, int state,int status,Pageable pageRequest);
	
	public HotNew findOneByIdAndType(String id, int type);
	
	public List<HotNew> findByApprovedAndStatus(int approved, int status);
	
	public HotNew findOneByIdAndTypeAndStatus(String id,int type, int status);
	
	public HotNew findOneByApprovedAndIdAndTypeAndStatus(int approved,String id, int type,int status);
	
	public long countByTypeAndIdAndStatus(int type,String id,int status);
	
}
