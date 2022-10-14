
package co.siten.myvtg.service;

import co.siten.myvtg.bean.ApplicationSelect2Bean;
import co.siten.myvtg.dao.ApplicationDao;
import co.siten.myvtg.model.myvtg.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;

@org.springframework.stereotype.Service("IApplicationService")
public class ApplicationService implements IApplicationService {
	@Autowired
	ApplicationDao dao;

	public Application findById(String id) {
		Application data = dao.findOneByIdAndStatus(id,1);
		return data;
	}

	public boolean isExist(Application data) {
		return dao.findOne(data.getId()) != null;
	}

	public String save(Application data) {
		Application id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(Application data) {
		dao.delete(data);
	}

	public Page<Application> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		name = "%"+name+"%";
		if (lang != null && lang!="") {
			return dao.findByNameContainingIgnoreCaseAndLanguageAndStatus(name, lang,1, new PageRequest(page, size,
					new Sort(order)));
		} else {
			return dao.findByNameContainingIgnoreCaseAndStatus(name,1, new PageRequest(page, size,
					new Sort(order)));
		}
	}
	
	public void Approve(boolean forAll, List<String> ids,boolean active){
		List<Application> data;
		if(forAll){
			data = dao.findByApprovedAndStatus(active?0:1, 1);
		}
		else{
			data = dao.findByApprovedAndIdInAndStatus(active?0:1,ids,1);
		}
		
		for (Application d : data) {
			d.setApproved(active?1:0);
			dao.save(d);
		}
	}

	@Override
	public List<ApplicationSelect2Bean> getAll() {
		return dao.getAll();
	}
}
