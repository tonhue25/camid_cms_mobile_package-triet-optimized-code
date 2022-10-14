package co.siten.myvtg.service;

import co.siten.myvtg.bean.ApplicationSelect2Bean;
import co.siten.myvtg.model.myvtg.Application;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IApplicationService {

	public Application findById(String id);

	public boolean isExist(Application data);

	public String save(Application data);

	public void delete(Application data);

	public Page<Application> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name);
	
	public void Approve(boolean forAll, List<String> ids,boolean active);

	public List<ApplicationSelect2Bean> getAll();
}
