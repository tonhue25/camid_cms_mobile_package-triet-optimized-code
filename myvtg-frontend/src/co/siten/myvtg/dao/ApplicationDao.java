package co.siten.myvtg.dao;

import co.siten.myvtg.bean.ApplicationSelect2Bean;
import co.siten.myvtg.model.myvtg.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ApplicationDao")
public interface ApplicationDao extends JpaRepository<Application, String> {

	public Application findOneByIdAndStatus(String id, int status);

	@Query(value = "SELECT * FROM Application WHERE (name like ?1 or short_Des like ?1) and status=?2  /*#pageable*/ ", countQuery = "SELECT count(*) FROM Application WHERE (name like ?1 or short_Des like ?1) and status=?2", nativeQuery = true)
	public Page<Application> findByNameContainingIgnoreCaseAndStatus(String name, int status, Pageable pageRequest);

	public Page<Application> findByNameContainingIgnoreCaseAndLanguageAndStatus(String name, String languge, int status,
			Pageable pageRequest);

	public List<Application> findByApprovedAndStatus(int approved, int status);

	public List<Application> findByApprovedAndIdInAndStatus(int approved, List<String> ids, int status);

	@Query("select new co.siten.myvtg.bean.ApplicationSelect2Bean(n.id,n.shortDes) from Application n where n.status =1")
	public List<ApplicationSelect2Bean> getAll();
}
