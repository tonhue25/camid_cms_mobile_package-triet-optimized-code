
package co.siten.myvtg.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Hobby;

@Repository("HobbyDao")
public interface HobbyDao extends JpaRepository<Hobby, String> {

	public Page<Hobby> findByNameContainingIgnoreCase(String name, Pageable pageRequest);

	public Page<Hobby> findByNameContainingIgnoreCaseAndLanguage(String name, String languge, Pageable pageRequest);

	public Hobby findOneById(long id);
	
	public List<Hobby> findByNameIgnoreCaseAndLanguage(String name,String language);
}
