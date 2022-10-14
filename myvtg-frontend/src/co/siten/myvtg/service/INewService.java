package co.siten.myvtg.service;

import co.siten.myvtg.bean.NewsSelect2Bean;
import co.siten.myvtg.model.myvtg.New;
import org.springframework.data.domain.Page;

import java.util.List;

public interface INewService {

	public New findById(String id);

	public boolean isExist(New data);

	public String save(New data);

	public void delete(New data);

	public Page<New> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name);
	
	public void Approve(boolean forAll, List<String> ids,boolean active);
	
	public boolean deleteable(String id);

	public List<NewsSelect2Bean> getAll();
}
