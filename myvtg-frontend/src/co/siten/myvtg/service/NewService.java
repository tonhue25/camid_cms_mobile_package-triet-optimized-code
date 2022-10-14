
package co.siten.myvtg.service;

import co.siten.myvtg.bean.NewsSelect2Bean;
import co.siten.myvtg.dao.HotNewDao;
import co.siten.myvtg.dao.NewDao;
import co.siten.myvtg.model.myvtg.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.List;


@org.springframework.stereotype.Service("INewService")
public class NewService implements INewService {
	@Autowired
	NewDao dao;

	@Autowired
	HotNewDao hotnewsDao;
	
	public New findById(String id) {
		New data = dao.findOneByIdAndStatus(id,1);;
		return data;
	}


	public boolean isExist(New data) {
		return dao.findOne(data.getId()) != null;
	}


	public String save(New data) {
		New id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(New data) {
		dao.delete(data);
	}

	public Page<New> findPaginated(int page, int size, String sortBy, int sortType,
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
	
	public void Approve(boolean forAll, List<String> ids,boolean active){
		List<New> data;
		if(forAll){
			data = dao.findByApprovedAndStatus(active?0:1, 1);
		}
		else{
			data = dao.findByApprovedAndIdInAndStatus(active?0:1,ids,1);
		}
		
		for (New d : data) {
			d.setApproved(active?1:0);
			dao.save(d);
		}
	}

	@Override
	public boolean deleteable(String id) {
		return hotnewsDao.countByTypeAndIdAndStatus(2,id,1)==0;
	}

	@Override
	public List<NewsSelect2Bean> getAll() {
		return dao.getAll();
	}
}
