
package co.siten.myvtg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.ShopDao;
import co.siten.myvtg.model.myvtg.Shop;

@org.springframework.stereotype.Service("IShopService")
public class ShopService implements IShopService {
	@Autowired
	ShopDao dao;

	public Shop findById(String id) {
		Shop data = dao.findOneByIdAndStatus(id, 1);
		;
		return data;
	}

	public boolean isExist(Shop data) {
		return dao.findOne(data.getId()) != null;
	}

	public String save(Shop data) {
		Shop id = dao.save(data);
		return id.getId();
	}

	@Override
	public void delete(Shop data) {
		dao.delete(data);
	}

	public Page<Shop> findPaginated(int page, int size, String sortBy, int sortType, String name) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		return dao.findByNameContainingIgnoreCaseAndStatus(name, 1, new PageRequest(page, size, new Sort(order)));

	}

}
