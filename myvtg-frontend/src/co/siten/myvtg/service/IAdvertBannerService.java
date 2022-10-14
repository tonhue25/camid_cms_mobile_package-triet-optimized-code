
package co.siten.myvtg.service;

import java.util.List;
import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.AdvertBanner;


public interface IAdvertBannerService {

	public AdvertBanner findById(String id);


	public boolean isExist(AdvertBanner data);


	public String save(AdvertBanner data);

	public void delete(AdvertBanner data);

	public Page<AdvertBanner> findPaginated(int page, int size, String sortBy, int sortType, String name);

	public void Approve(boolean forAll, List<String> ids,boolean active);

}
