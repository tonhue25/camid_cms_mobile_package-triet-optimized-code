
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.HotNewDao;
import co.siten.myvtg.dao.PromotionDao;
import co.siten.myvtg.model.myvtg.Promotion;


@org.springframework.stereotype.Service("IPromotionService")
public class PromotionService implements IPromotionService {
	@Autowired
	PromotionDao dao;

	@Autowired
	HotNewDao hotnewsDao;
	
	public Promotion findById(String id) {
		Promotion data = dao.findOneByIdAndStatus(id,1);;
		return data;
	}


	public boolean isExist(Promotion data) {
		return dao.findOne(data.getId()) != null;
	}


	public String save(Promotion data) {
		Promotion id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(Promotion data) {
		dao.delete(data);
	}

	public Page<Promotion> findPaginated(int page, int size, String sortBy, int sortType,
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
	
	
	public Promotion findOneByCodeAndLanguage(String code, String lang){
		return dao.findOneByCodeAndLanguageAndStatus(code,lang,1);
	}
	
	public void Approve(boolean forAll, List<String> ids,boolean active){
		List<Promotion> data;
		if(forAll){
			data = dao.findByApprovedAndStatus(active?0:1, 1);
		}
		else{
			data = dao.findByApprovedAndIdInAndStatus(active?0:1,ids,1);
		}
		
		for (Promotion d : data) {
			d.setApproved(active?1:0);
			dao.save(d);
		}
	}


	@Override
	public boolean deleteable(String id) {
		return hotnewsDao.countByTypeAndIdAndStatus(1,id,1)==0;
	}
	
	
}
