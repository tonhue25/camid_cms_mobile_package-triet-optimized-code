
package co.siten.myvtg.service;

import java.util.List;
import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.Job;


public interface IJobService {

	public Job findById(long id);


	public boolean isExist(Job data);


	public Job save(Job data);

	public void delete(Job data);

	public List<Job> findByNameAndLanguage(String name,String lang);
	
	public Page<Job> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name);

	
}
