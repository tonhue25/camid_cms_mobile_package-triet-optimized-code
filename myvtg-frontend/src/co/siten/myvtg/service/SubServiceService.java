
package co.siten.myvtg.service;

import co.siten.myvtg.bean.SubserviceSelect2Bean;
import co.siten.myvtg.dao.ActionDao;
import co.siten.myvtg.dao.SubServiceDao;
import co.siten.myvtg.model.myvtg.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

@org.springframework.stereotype.Service("ISubServiceService")
public class SubServiceService implements ISubServiceService {
	@Autowired
	SubServiceDao dao;

	@Autowired
	ActionDao actionDao;

	public SubService findById(String id) {
		SubService data = dao.findOneByIdAndStatus(id, 1);
		return data;
	}

	public boolean isExist(SubService data) {
		return dao.findOneByCodeAndLanguageAndStatus(data.getCode(), data.getLanguage(), 1) != null;
	}

	public String save(SubService data) {
		SubService id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(SubService data) {
		dao.delete(data);
	}

	public Page<SubService> findServicePaginated(int page, int size, String sortBy, int sortType, String serviceId,
			String lang, String name) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		
		if (lang != null && lang != "") {
			if (serviceId != null) {
				return dao.findByNameContainingIgnoreCaseAndServiceIdAndLanguageAndStatus(name,serviceId, lang, 1,
						new PageRequest(page, size, new Sort(order)));
			} else {
				return dao.findByNameContainingIgnoreCaseAndLanguageAndStatus(name,lang, 1, new PageRequest(page, size, new Sort(order)));
			}

		} else {
			if (serviceId != null) {
				return dao.findByNameContainingIgnoreCaseAndServiceIdAndStatus(name,serviceId, 1, new PageRequest(page, size, new Sort(order)));
			} else {
				return dao.findByNameContainingIgnoreCaseAndStatus(name,1, new PageRequest(page, size, new Sort(order)));
			}
		}
	}

	@Override
	public SubService findOneByCodeAndLanguage(String code, String lang) {
		return dao.findOneByCodeAndLanguageAndStatus(code, lang, 1);
	}

	public void Approve(boolean forAll, List<String> ids, boolean active) {
		List<SubService> data;
		if (forAll) {
			data = dao.findByApprovedAndStatus(active ? 0 : 1, 1);
		} else {
			data = dao.findByApprovedAndIdInAndStatus(active ? 0 : 1, ids, 1);
		}

		for (SubService d : data) {
			d.setApproved(active ? 1 : 0);
			dao.save(d);
		}
	}

	@Override
	public boolean deleteable(String id) {
		SubService subService = findById(id);
		if (subService != null) {
			return actionDao.countByIdSubServiceCodeAndStatus(subService.getCode(), 1) == 0;
		}
		return false;
	}

	@Override
	public List<SubserviceSelect2Bean> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}
}
