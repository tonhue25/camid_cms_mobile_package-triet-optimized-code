package co.siten.myvtg.dao;

import co.siten.myvtg.bean.NewsSelect2Bean;
import co.siten.myvtg.model.myvtg.New;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("NewDao")
public interface NewDao extends JpaRepository<New, String> {

	public Page<New> findByNameContainingIgnoreCaseAndStatus(String name,int status, Pageable pageRequest);

	public Page<New> findByNameContainingIgnoreCaseAndLanguageAndStatus(String name, String languge,int status, Pageable pageRequest);
	
	public List<New> findByApprovedAndStatus(int approved, int status);
	
	public List<New> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
	
	public New findOneByIdAndStatus(String id, int status);

	@Query("select new co.siten.myvtg.bean.NewsSelect2Bean(n.id,n.name) from New n where n.status = 1")
	public List<NewsSelect2Bean> getAll();
}
