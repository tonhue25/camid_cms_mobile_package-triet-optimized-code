
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.Webservice;


public interface IWebServiceService {

	public Webservice findById(String id);


	public boolean isExist(Webservice data);


	public String save(Webservice data);

	public void delete(Webservice data);

	public List<Webservice> getList();
	
	public Page<Webservice> findPaginated(int page, int size, String sortBy, int sortType,String name);

	public boolean deleteable(String id);
}
