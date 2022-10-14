
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.ExperienceLink;


public interface IExperienceLinkService {

	public ExperienceLink findById(String id);


	public boolean isExist(ExperienceLink data);


	public String save(ExperienceLink data);

	public void delete(ExperienceLink data);

	public Page<ExperienceLink> findPaginated(int page, int size, String sortBy, int sortType,
			String lang, String name);

	public void Approve(boolean forAll, List<String> ids,boolean active);

}
