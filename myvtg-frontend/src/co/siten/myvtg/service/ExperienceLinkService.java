
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.ExperienceLinkDao;
import co.siten.myvtg.model.myvtg.ExperienceLink;

@org.springframework.stereotype.Service("IExperienceLinkService")
public class ExperienceLinkService implements IExperienceLinkService {
	@Autowired
	ExperienceLinkDao dao;

	public ExperienceLink findById(String id) {
		ExperienceLink data = dao.findOneByIdAndStatus(id, 1);
		return data;
	}

	public boolean isExist(ExperienceLink data) {
		return dao.findOne(data.getId()) != null;
	}

	public String save(ExperienceLink data) {
		ExperienceLink id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(ExperienceLink data) {
		dao.delete(data);
	}

	public Page<ExperienceLink> findPaginated(int page, int size, String sortBy, int sortType, String lang,
			String name) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		if (lang != null && lang != "") {
			return dao.findByNameContainingIgnoreCaseAndLanguageAndStatus(name, lang, 1,
					new PageRequest(page, size, new Sort(order)));
		} else {
			return dao.findByNameContainingIgnoreCaseAndStatus(name, 1, new PageRequest(page, size, new Sort(order)));
		}
	}

	public void Approve(boolean forAll, List<String> ids, boolean active) {
		List<ExperienceLink> data;
		if (forAll) {
			data = dao.findByApprovedAndStatus(active ? 0 : 1, 1);
		} else {
			data = dao.findByApprovedAndIdInAndStatus(active ? 0 : 1, ids, 1);
		}

		for (ExperienceLink d : data) {
			d.setApproved(active ? 1 : 0);
			dao.save(d);
		}
	}
}
