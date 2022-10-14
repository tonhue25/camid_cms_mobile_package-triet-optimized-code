
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.Action;


public interface IActionService {

	public Action findById(String id);


	public boolean isExist(Action data);


	public String save(Action data);

	public void delete(Action data);

	public Page<Action> findPaginated(int page, int size, String sortBy, int sortType, String subServiceCode);
	
	public List<Action> findByStatus(int status);

	public Action findOneBySubServiceCodeAndActionTypeAndChannelType(String subServiceCode,int actionType,int channelType);
	
	public Action findOneByWsId(String wsId);
}
