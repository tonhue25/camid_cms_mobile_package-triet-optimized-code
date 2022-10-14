package co.siten.myvtg.service;

import org.springframework.data.domain.Page;

import co.siten.myvtg.model.myvtg.CmsLog;
import co.siten.myvtg.model.myvtg.Log;

public interface ILogService {

	public Log findById(String id);

	public void delete(Log data);

	public Page<Log> findPaginated(int page, int size, String sortBy, int sortType);

	public void insertLog(Log log);

	public void insertCmsLog(CmsLog log);

}
