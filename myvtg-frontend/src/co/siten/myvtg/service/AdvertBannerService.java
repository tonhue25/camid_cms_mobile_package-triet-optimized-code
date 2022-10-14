
package co.siten.myvtg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.AdvertBannerDao;
import co.siten.myvtg.model.myvtg.AdvertBanner;

@org.springframework.stereotype.Service("IAdvertBannerService")
public class AdvertBannerService implements IAdvertBannerService {
	@Autowired
	AdvertBannerDao dao;

	public AdvertBanner findById(String id) {
		AdvertBanner data = dao.findOneByIdAndStatus(id, 1);
		return data;
	}

	public boolean isExist(AdvertBanner data) {
		return dao.findOne(data.getId()) != null;
	}

	public String save(AdvertBanner data) {
		AdvertBanner id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(AdvertBanner data) {
		dao.delete(data);
	}

	public Page<AdvertBanner> findPaginated(int page, int size, String sortBy, int sortType, String des) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		return dao.findByDesContainingIgnoreCaseAndStatus(des, 1, new PageRequest(page, size, new Sort(order)));
	}

	public void Approve(boolean forAll, List<String> ids, boolean active) {
		List<AdvertBanner> data;
		if (forAll) {
			data = dao.findByApprovedAndStatus(active ? 0 : 1, 1);
		} else {
			data = dao.findByApprovedAndIdInAndStatus(active ? 0 : 1, ids, 1);
		}

		for (AdvertBanner d : data) {
			d.setApproved(active ? 1 : 0);
			dao.save(d);
		}
	}
}
