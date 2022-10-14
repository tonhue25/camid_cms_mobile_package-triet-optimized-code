
package co.siten.myvtg.service;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.HotNew;


public interface IHotNewService {

	public HotNew findByIdAndType(String id,int type);


	public boolean isExist(HotNew data);


	public String save(HotNew data);

	public void delete(HotNew data);

	public Page<HotNew> findPaginated(int page, int size, String sortBy, int sortType,String lang,int state, Date from,Date to);

	public HotNew findOneByIdAndType(String id, int type);
	
	
	public void Approve(boolean forAll, List<HotNew> list,boolean active);
	
	public boolean deleteable(String id);
}
