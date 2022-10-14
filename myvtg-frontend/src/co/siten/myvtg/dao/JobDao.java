
package co.siten.myvtg.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Job;

@Repository("JobDao")
public interface JobDao extends JpaRepository<Job, String> {

	public Page<Job> findByNameContainingIgnoreCase(String name, Pageable pageRequest);

	public Page<Job> findByNameContainingIgnoreCaseAndLanguage(String name, String languge, Pageable pageRequest);

	public Job findOneById(long id);
	
	public List<Job> findByNameIgnoreCaseAndLanguage(String name,String language);
}
