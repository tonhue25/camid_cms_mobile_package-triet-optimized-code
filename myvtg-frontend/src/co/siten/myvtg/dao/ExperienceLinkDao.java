
package co.siten.myvtg.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.ExperienceLink;
import co.siten.myvtg.model.myvtg.Webservice;

@Repository("ExperienceLinkDao")
public interface ExperienceLinkDao extends JpaRepository<ExperienceLink, String> {
	
	public ExperienceLink findOneByIdAndStatus(String id, int status);

	public Page<ExperienceLink> findByNameContainingIgnoreCaseAndStatus(String name,int status, Pageable pageRequest);

	public Page<ExperienceLink> findByNameContainingIgnoreCaseAndLanguageAndStatus(String name,String language,int status, Pageable pageRequest);

	public List<ExperienceLink> findByApprovedAndStatus(int approved, int status);
	
	public List<ExperienceLink> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
}
