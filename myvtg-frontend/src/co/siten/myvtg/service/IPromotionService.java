
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.Promotion;


public interface IPromotionService {

	public Promotion findById(String id);


	public boolean isExist(Promotion data);


	public String save(Promotion data);

	public void delete(Promotion data);

	public Page<Promotion> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name);

	public void Approve(boolean forAll, List<String> ids,boolean active);
	
	public Promotion findOneByCodeAndLanguage(String code, String language);
	
	public boolean deleteable(String id);
}
