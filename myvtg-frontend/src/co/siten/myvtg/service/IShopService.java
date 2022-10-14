
package co.siten.myvtg.service;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.Shop;


public interface IShopService {

	public Shop findById(String id);

	public boolean isExist(Shop data);

	public String save(Shop data);

	public void delete(Shop data);

	public Page<Shop> findPaginated(int page, int size, String sortBy, int sortType, String name);
}
