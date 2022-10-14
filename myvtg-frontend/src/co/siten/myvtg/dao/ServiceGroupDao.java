
package co.siten.myvtg.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Action;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.model.myvtg.ServiceGroup;

@Repository("ServiceGroupDao")
public interface ServiceGroupDao extends JpaRepository<ServiceGroup, String> {

	public Page<ServiceGroup> findByNameContainingIgnoreCaseAndStatus(String name,int status, Pageable pageRequest);
	
	public Page<ServiceGroup> findByNameContainingIgnoreCaseAndLanguageAndStatus(String name,String language,int status, Pageable pageRequest);
	
	public ServiceGroup findOneByCodeAndLanguageAndStatus(String code, String language,int status);
	
	public List<ServiceGroup> findByStatus(int status);
	
	public ServiceGroup findOneByIdAndStatus(String id, int status);
	
	public List<ServiceGroup> findByIdIn(List<String> ids);
	
//	public List<ServiceGroup> findByApprovedAndStatus(int approved, int status);
//	
//	public List<ServiceGroup> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
}
