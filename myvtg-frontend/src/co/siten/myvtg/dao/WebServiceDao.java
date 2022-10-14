
package co.siten.myvtg.dao;
import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.siten.myvtg.model.myvtg.Webservice;


@Repository("WebServiceDao")
public interface WebServiceDao extends JpaRepository<Webservice, String> {

	public List<Webservice> findByStatus(int status);
	
	public Webservice findOneByIdAndStatus(String id, int status);
	
	public Page<Webservice> findByWsNameContainingIgnoreCaseAndStatus(String wsName, int status, Pageable pageRequest);
}
