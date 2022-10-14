
package co.siten.myvtg.dao;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.Action;

@Repository("ActionDao")
public interface ActionDao extends JpaRepository<Action, String> {

	public List<Action> findByStatus(int status);
	
	public Page<Action> findByIdSubServiceCodeAndStatus(String subServiceCode, int status,Pageable pageRequest);
	
	public Action findOneByIdSubServiceCodeAndIdActionTypeAndIdChannelTypeAndStatus(String subServiceCode,int actionType,int channelType,int status);
	
	public Action findOneByWsIdAndStatus(String wsId,int status);
	
	public Action findOneByIdAndStatus(String id, int status);
	
	public long countByIdSubServiceCodeAndStatus(String id, int status);
	
	public long countByWsIdAndStatus(String id, int status);
}
