
package co.siten.myvtg.service;

import co.siten.myvtg.bean.SubserviceSelect2Bean;
import co.siten.myvtg.model.myvtg.SubService;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ISubServiceService {

	public SubService findById(String id);

	public boolean isExist(SubService data);

	public String save(SubService data);

	public void delete(SubService data);

	public Page<SubService> findServicePaginated(int page, int size, String sortBy, int sortType, String serviceId,
			String lang,String name);

	public SubService findOneByCodeAndLanguage(String code,String lang);
	
	public void Approve(boolean forAll, List<String> ids,boolean active);
	
	public boolean deleteable(String id);

	public List<SubserviceSelect2Bean> getAll();
}
