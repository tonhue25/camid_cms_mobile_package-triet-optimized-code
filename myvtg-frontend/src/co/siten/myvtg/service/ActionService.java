
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.ActionDao;
import co.siten.myvtg.model.myvtg.Action;

@org.springframework.stereotype.Service("IActionService")
public class ActionService implements IActionService {
	@Autowired
	ActionDao dao;

	public Action findById(String id) {
		Action data = dao.findOneByIdAndStatus(id, 1);
		return data;
	}

	public boolean isExist(Action data) {
		return dao.findOneByIdSubServiceCodeAndIdActionTypeAndIdChannelTypeAndStatus(data.getId().getSubServiceCode(),
				data.getId().getActionType(), data.getId().getChannelType(), 1) != null;
	}

	public String save(Action data) {
		Action savedData = dao.save(data);
		return savedData.getId().getSubServiceCode();
	}

	@Override
	public void delete(Action data) {
		dao.delete(data);
	}

	public List<Action> findByStatus(int status) {
		return dao.findByStatus(status);
	}

	public Action findOneBySubServiceCodeAndActionTypeAndChannelType(String subServiceCode, int actionType,
			int channelType) {
		return dao.findOneByIdSubServiceCodeAndIdActionTypeAndIdChannelTypeAndStatus(subServiceCode, actionType,
				channelType, 1);
	}

	public Action findOneByWsId(String wsId) {
		return dao.findOneByWsIdAndStatus(wsId, 1);
	}

	@Override
	public Page<Action> findPaginated(int page, int size, String sortBy, int sortType, String subServiceCode) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		return dao.findByIdSubServiceCodeAndStatus(subServiceCode, 1, new PageRequest(page, size, new Sort(order)));
	}

}
