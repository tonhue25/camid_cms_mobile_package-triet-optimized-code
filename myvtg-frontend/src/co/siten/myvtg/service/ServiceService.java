package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.HotNewDao;
import co.siten.myvtg.dao.ServiceDao;
import co.siten.myvtg.dao.SubServiceDao;
import co.siten.myvtg.model.myvtg.Service;

@org.springframework.stereotype.Service("IServiceService")
public class ServiceService implements IServiceService {
	@Autowired
	ServiceDao dao;

	@Autowired
	SubServiceDao subServiceDao;

	@Autowired
	HotNewDao hotnewsDao;

	public Service findById(String id) {
		Service data = dao.findOneByIdAndStatus(id, 1);
		return data;
	}

	public boolean isExist(Service data) {
		return dao.findOneByIdAndStatus(data.getId(), 1) != null;
	}

	public String save(Service data) {
		Service id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(Service data) {
		dao.delete(data);
	}

	public Page<Service> findServicePaginated(int page, int size, String sortBy, int sortType, String groupId,
			String lang, String name, int serviceType) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		if (lang != null && lang != "" && groupId != null) {
			if (serviceType == 2) {
				return dao.findByNameContainingIgnoreCaseAndServiceGroupIdAndLanguageAndStatus(name, groupId, lang, 1,
						new PageRequest(page, size, new Sort(order)));
			} else {
				return dao.findByNameContainingIgnoreCaseAndServiceGroupIdAndLanguageAndStatusAndServiceType(name,
						groupId, lang, 1, serviceType, new PageRequest(page, size, new Sort(order)));
			}

		} else if (groupId != null) {
			if (serviceType == 2) {
				return dao.findByNameContainingIgnoreCaseAndServiceGroupIdAndStatus(name, groupId, 1,
						new PageRequest(page, size, new Sort(order)));
			} else {
				return dao.findByNameContainingIgnoreCaseAndServiceGroupIdAndStatusAndServiceType(name, groupId, 1,
						serviceType, new PageRequest(page, size, new Sort(order)));
			}
		} else if (lang != null && lang != "") {
			if (serviceType == 2) {

				return dao.findByNameContainingIgnoreCaseAndLanguageAndStatus(name, lang, 1,
						new PageRequest(page, size, new Sort(order)));
			} else {
				return dao.findByNameContainingIgnoreCaseAndLanguageAndStatusAndServiceType(name, lang, 1, serviceType,
						new PageRequest(page, size, new Sort(order)));
			}
		} else {
			if (serviceType == 2) {
				return dao.findByNameContainingIgnoreCaseAndStatus(name, 1,
						new PageRequest(page, size, new Sort(order)));
			} else {
				return dao.findByNameContainingIgnoreCaseAndStatusAndServiceType(name, 1, serviceType,
						new PageRequest(page, size, new Sort(order)));
			}
		}
	}

	@Override
	public Service findOneByCodeAndLanguageAndServiceType(String code, String lang, int type) {
		return dao.findOneByCodeAndLanguageAndServiceTypeAndStatus(code, lang,type, 1);
	}

	public void Approve(boolean forAll, List<String> ids, boolean active) {
		List<Service> data;
		if (forAll) {
			data = dao.findByApprovedAndStatus(active ? 0 : 1, 1);
		} else {
			data = dao.findByApprovedAndIdInAndStatus(active ? 0 : 1, ids, 1);
		}

		for (Service d : data) {
			d.setApproved(active ? 1 : 0);
			dao.save(d);
		}
	}

	@Override
	public List<Service> findByIds(List<String> ids) {
		// TODO Auto-generated method stub

		List<Service> services = dao.findByIdIn(ids);
		return services;

	}

	@Override
	public boolean deleteable(String id) {
		return ((subServiceDao.countByServiceIdAndStatus(id, 1) == 0)
				&& (hotnewsDao.countByTypeAndIdAndStatus(0, id, 1) == 0));
	}

}
