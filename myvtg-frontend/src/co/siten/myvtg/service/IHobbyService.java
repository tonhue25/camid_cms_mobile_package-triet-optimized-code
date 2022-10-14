
package co.siten.myvtg.service;

import java.util.List;
import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.Hobby;
import co.siten.myvtg.model.myvtg.Job;


public interface IHobbyService {

	public Hobby findById(long id);


	public boolean isExist(Hobby data);


	public Hobby save(Hobby data);

	public void delete(Hobby data);

	public List<Hobby> findByNameAndLanguage(String name,String lang);
	
	public Page<Hobby> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name);

	
}
