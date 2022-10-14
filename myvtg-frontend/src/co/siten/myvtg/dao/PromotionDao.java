
package co.siten.myvtg.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Application;
import co.siten.myvtg.model.myvtg.ExperienceLink;
import co.siten.myvtg.model.myvtg.Promotion;

@Repository("PromotionDao")
public interface PromotionDao extends JpaRepository<Promotion, String> {

	public Page<Promotion> findByNameContainingIgnoreCaseAndStatus(String name,int status, Pageable pageRequest);
	
	public Page<Promotion> findByNameContainingIgnoreCaseAndLanguageAndStatus(String name,String language,int status, Pageable pageRequest);
	
	public Promotion findOneByCodeAndLanguageAndStatus(String code, String language, int status);
	
	public List<Promotion> findByApprovedAndStatus(int approved, int status);
	
	public List<Promotion> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
	
	public Promotion findOneByIdAndStatus(String id, int status);
}
