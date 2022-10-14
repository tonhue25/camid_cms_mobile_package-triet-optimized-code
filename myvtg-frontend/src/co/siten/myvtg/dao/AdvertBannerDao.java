
package co.siten.myvtg.dao;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.AdvertBanner;

@Repository("AdvertBannerDao")
public interface AdvertBannerDao extends JpaRepository<AdvertBanner, String> {
	public AdvertBanner findOneByIdAndStatus(String id, int status);
	public Page<AdvertBanner> findByDesContainingIgnoreCaseAndStatus(String des,int status, Pageable pageRequest);
	public List<AdvertBanner> findByApprovedAndStatus(int approved, int status);
	public List<AdvertBanner> findByApprovedAndIdInAndStatus(int approved,List<String> ids,int status);
}
