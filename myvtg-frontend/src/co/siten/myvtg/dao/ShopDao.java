
package co.siten.myvtg.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Application;
import co.siten.myvtg.model.myvtg.ExperienceLink;
import co.siten.myvtg.model.myvtg.Shop;

@Repository("ShopDao")
public interface ShopDao extends JpaRepository<Shop, String> {

	public Page<Shop> findByNameContainingIgnoreCaseAndStatus(String name,int status, Pageable pageRequest);
	
	public Shop findOneByIdAndStatus(String id, int status);
}
