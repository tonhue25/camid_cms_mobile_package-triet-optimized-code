
package co.siten.myvtg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.JobDao;
import co.siten.myvtg.model.myvtg.Job;


@org.springframework.stereotype.Service("IJobService")
public class JobService implements IJobService {
	@Autowired
	JobDao dao;

	public Job findById(long id) {
		Job data = dao.findOneById(id);
		return data;
	}

	public boolean isExist(Job data) {
		return dao.findOneById(data.getId()) != null;
	}

	public Job save(Job data) {
		Job savedData = dao.save(data);
		return savedData;
	}

	@Override
	public void delete(Job data) {
		dao.delete(data);
	}

	public Page<Job> findPaginated(int page, int size, String sortBy, int sortType,
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
	public List<Job> findByNameAndLanguage(String name,String language) {
		// TODO Auto-generated method stub
		return dao.findByNameIgnoreCaseAndLanguage(name,language);		
	}

}
