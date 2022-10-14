package co.siten.myvtg.dao;

import co.siten.myvtg.bean.SubserviceSelect2Bean;
import co.siten.myvtg.model.myvtg.SubService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SubServiceDao")
public interface SubServiceDao extends JpaRepository<SubService, String> {

	public Page<SubService> findByNameContainingIgnoreCaseAndServiceIdAndLanguageAndStatus(String name,String serviceId,String language,int status, Pageable pageRequest);
	
	public Page<SubService> findByNameContainingIgnoreCaseAndServiceIdAndStatus(String name,String serviceId,int status, Pageable pageRequest);
	
	public Page<SubService> findByNameContainingIgnoreCaseAndLanguageAndStatus(String name,String language, int status, Pageable pageRequest);
	
	public Page<SubService> findByNameContainingIgnoreCaseAndStatus(String name,int status, Pageable pageRequest);
	
	public SubService findOneByCodeAndLanguageAndStatus(String code,String language,int status);
	
	public SubService findOneByIdAndStatus(String id, int status);

	public List<SubService> findByApprovedAndStatus(int approved, int status);
	
	public List<SubService> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
	
	public long countByServiceIdAndStatus(String serviceId,int status);

	@Query("select new co.siten.myvtg.bean.SubserviceSelect2Bean(s.id,g.code ,s.shortDes )"
			+ " from SubService s, Service g " + " where s.serviceId = g.id AND s.status = 1 and g.status = 1 AND s.approved = 1 AND g.approved = 1")
	public List<SubserviceSelect2Bean> getAll();
	
	
}
