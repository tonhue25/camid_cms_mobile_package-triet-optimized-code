package co.siten.myvtg.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Application;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.model.myvtg.ServiceGroup;


@Repository("ServiceDao")
public interface ServiceDao extends JpaRepository<Service, String> {
	public Page<Service> findByNameContainingIgnoreCaseAndServiceGroupIdAndLanguageAndStatus(String name, String serviceGroupId,String language,int status, Pageable pageRequest);

	
	public Page<Service> findByNameContainingIgnoreCaseAndServiceGroupIdAndLanguageAndStatusAndServiceType(String name, String serviceGroupId,String language,int status,int serviceType, Pageable pageRequest);
	
	
	public Page<Service> findByNameContainingIgnoreCaseAndServiceGroupIdAndStatus(String name, String serviceGroupId,int status,Pageable pageRequest);
	
	
	public Page<Service> findByNameContainingIgnoreCaseAndServiceGroupIdAndStatusAndServiceType (String name, String serviceGroupId,int status,int serviceType,Pageable pageRequest);

	public Page<Service> findByNameContainingIgnoreCaseAndLanguageAndStatus(String name, String language,int status, Pageable pageRequest);
	
	public Page<Service> findByNameContainingIgnoreCaseAndLanguageAndStatusAndServiceType(String name, String language,int status,int serviceType, Pageable pageRequest);

	public Page<Service> findByNameContainingIgnoreCaseAndStatus(String name,int status, Pageable pageRequest);
	
	public Page<Service> findByNameContainingIgnoreCaseAndStatusAndServiceType(String name,int status,int serviceType, Pageable pageRequest);
	
	
	public Service findOneByCodeAndLanguageAndServiceTypeAndStatus(String code,String language,int serviceType,int status);
	
	
	public Service findOneByIdAndStatus(String id, int status);
	
	public List<Service> findByApprovedAndStatus(int approved, int status);
	
	public List<Service> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
	
	public List<Service> findByIdIn(List<String> ids);
	
	public long countByServiceGroupIdAndStatus(String serviceGroupId,int status);
}
