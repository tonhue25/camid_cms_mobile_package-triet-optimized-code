
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.ServiceGroup;


public interface IServiceGroupService {

	public ServiceGroup findById(String id);


	public boolean isExist(ServiceGroup data);


	public String save(ServiceGroup data);

	public void delete(ServiceGroup data);

	public Page<ServiceGroup> findServicePaginated(int page, int size, String sortBy, int sortType,
			String lang, String name);

	public ServiceGroup findOneByCodeAndLanguage(String code, String lang);
	
	public List<ServiceGroup> findByIds(List<String> ids);
	
	public boolean deleteable(String id);
	
//	public void Approve(boolean forAll, List<String> ids,boolean active);
}
