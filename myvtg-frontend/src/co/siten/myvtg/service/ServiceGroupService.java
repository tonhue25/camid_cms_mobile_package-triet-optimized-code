

package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.ServiceDao;
import co.siten.myvtg.dao.ServiceGroupDao;
import co.siten.myvtg.model.myvtg.Application;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.model.myvtg.ServiceGroup;


@org.springframework.stereotype.Service("IServiceGroupService")
public class ServiceGroupService implements IServiceGroupService {
	@Autowired
	ServiceGroupDao dao;
	
	@Autowired
	ServiceDao serviceDao;

	public ServiceGroup findById(String id) {
		ServiceGroup data = dao.findOneByIdAndStatus(id,1);
		return data;
	}


	public boolean isExist(ServiceGroup data) {
		return dao.findOneByIdAndStatus(data.getId(),1) != null;
	}


	public String save(ServiceGroup data) {
		ServiceGroup id = dao.save(data);
		return id.getId();
	}

	
	@Override
	public void delete(ServiceGroup data) {
		dao.delete(data);
	}
	
	public boolean deleteable(String id){
		return serviceDao.countByServiceGroupIdAndStatus(id,1) == 0;
	}

	public Page<ServiceGroup> findServicePaginated(int page, int size, String sortBy, int sortType,
			String lang, String name) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		if (lang != null && lang!="") {
			return dao.findByNameContainingIgnoreCaseAndLanguageAndStatus(name, lang,1, new PageRequest(page, size,
					new Sort(order)));
		} else {
			return dao.findByNameContainingIgnoreCaseAndStatus(name,1, new PageRequest(page, size,
					new Sort(order)));
		}
	}
	
	public ServiceGroup findOneByCodeAndLanguage(String code, String lang){
		return dao.findOneByCodeAndLanguageAndStatus(code, lang, 1);
	}


	@Override
	public List<ServiceGroup> findByIds(List<String> ids) {
		// TODO Auto-generated method stub
		
		List<ServiceGroup> serviceGroups = dao.findByIdIn(ids);
		return serviceGroups;
		
	}


//	@Override
//	public void Approve(boolean forAll, List<String> ids, boolean active) {
//		List<ServiceGroup> data;
//		if(forAll){
//			data = dao.findByApprovedAndStatus(active?0:1, 1);
//		}
//		else{
//			data = dao.findByApprovedAndIdInAndStatus(active?0:1,ids,1);
//		}
//		
//		for (ServiceGroup d : data) {
//			d.setApproved(active?1:0);
//			dao.save(d);
//		}
//		
//	}
	
}
