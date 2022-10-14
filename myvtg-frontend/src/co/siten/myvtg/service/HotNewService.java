
package co.siten.myvtg.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.HotNewDao;
import co.siten.myvtg.model.myvtg.Application;
import co.siten.myvtg.model.myvtg.HotNew;

@org.springframework.stereotype.Service("IHotNewService")
public class HotNewService implements IHotNewService {
	@Autowired
	HotNewDao dao;

	public HotNew findByIdAndType(String id, int type) {
		HotNew data = dao.findOneByIdAndTypeAndStatus(id, type, 1);
		return data;
	}

	public boolean isExist(HotNew data) {
		return dao.findOneByIdAndTypeAndStatus(data.getId(), data.getType(), 1) != null;
	}

	public String save(HotNew data) {
		HotNew id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(HotNew data) {
		dao.delete(data);
	}

	public Page<HotNew> findPaginated(int page, int size, String sortBy, int sortType, String lang, int state,
			Date from, Date to) {
		Calendar c = Calendar.getInstance();
		c.set(1900, 1, 1);
		if (from == null)
			from = c.getTime();

		if (to == null) {
			c.set(3000, 1, 1);
			to = c.getTime();
		}
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();

		if (lang != null && lang != "") {
			if (state == 0 || state == 1) {
				return dao.findByEffectTimeBetweenAndLanguageAndStateAndStatus(from, to, lang, state, 1,
						new PageRequest(page, size, new Sort(order)));
			} else {
				return dao.findByEffectTimeBetweenAndLanguageAndStatus(from, to, lang, 1,
						new PageRequest(page, size, new Sort(order)));
			}

		} else {
			if (state == 0 || state == 1) {
				return dao.findByEffectTimeBetweenAndStateAndStatus(from, to, state, 1,
						new PageRequest(page, size, new Sort(order)));
			}
			return dao.findByEffectTimeBetweenAndStatus(from, to, 1, new PageRequest(page, size, new Sort(order)));
		}
	}

	@Override
	public HotNew findOneByIdAndType(String id, int type) {
		return dao.findOneByIdAndType(id, type);
	}

	public void Approve(boolean forAll, List<HotNew> list, boolean active) {
		List<HotNew> data = new ArrayList<HotNew>();
		if (forAll) {
			data = dao.findByApprovedAndStatus(active ? 0 : 1, 1);
		} else {

			for (HotNew hotNew : list) {
				HotNew e = dao.findOneByApprovedAndIdAndTypeAndStatus(active ? 0 : 1, hotNew.getId(), hotNew.getType(),
						1);
				if (e != null)
					data.add(e);
			}
		}

		for (HotNew d : data) {
			d.setApproved(active ? 1 : 0);
			dao.save(d);
		}
	}

	@Override
	public boolean deleteable(String id) {
		// TODO Auto-generated method stub
		return true;
	}
}
