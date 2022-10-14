
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.HobbyDao;
import co.siten.myvtg.model.myvtg.Hobby;


@org.springframework.stereotype.Service("IHobbyService")
public class HobbyService implements IHobbyService {
	@Autowired
	HobbyDao dao;

	public Hobby findById(long id) {
		Hobby data = dao.findOneById(id);
		return data;
	}

	public boolean isExist(Hobby data) {
		return dao.findOneById(data.getId()) != null;
	}

	public Hobby save(Hobby data) {
		Hobby savedData = dao.save(data);
		return savedData;
	}

	@Override
	public void delete(Hobby data) {
		dao.delete(data);
	}

	public Page<Hobby> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		if (lang != null && lang!="") {
			return dao.findByNameContainingIgnoreCaseAndLanguage(name, lang, new PageRequest(page, size,
					new Sort(order)));
		} else {
			return dao.findByNameContainingIgnoreCase(name, new PageRequest(page, size,
					new Sort(order)));
		}
	}

	@Override
	public List<Hobby> findByNameAndLanguage(String name,String lang) {
		// TODO Auto-generated method stub
		return dao.findByNameIgnoreCaseAndLanguage(name,lang);
	}

}
