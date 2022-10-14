
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import co.siten.myvtg.dao.ActionDao;
import co.siten.myvtg.dao.WebServiceDao;
import co.siten.myvtg.model.myvtg.Webservice;

@org.springframework.stereotype.Service("IWebServiceService")
public class WebServiceService implements IWebServiceService {
	@Autowired
	WebServiceDao dao;

	@Autowired
	ActionDao actionDao;

	public Webservice findById(String id) {
		Webservice data = dao.findOneByIdAndStatus(id, 1);
		return data;
	}

	public boolean isExist(Webservice data) {
		return dao.findOneByIdAndStatus(data.getId(), 1) != null;
	}

	public String save(Webservice data) {
		Webservice id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(Webservice data) {
		dao.delete(data);
	}

	public List<Webservice> getList() {
		return dao.findByStatus(1);
	}

	public Page<Webservice> findPaginated(int page, int size, String sortBy, int sortType, String name) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		return dao.findByWsNameContainingIgnoreCaseAndStatus(name, 1, new PageRequest(page, size, new Sort(order)));
	}

	@Override
	public boolean deleteable(String id) {
		return actionDao.countByWsIdAndStatus(id, 1) == 0;
	}

}
