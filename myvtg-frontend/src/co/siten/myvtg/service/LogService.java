
package co.siten.myvtg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import co.siten.myvtg.dao.CmsLogDao;
import co.siten.myvtg.dao.LogDao;
import co.siten.myvtg.model.myvtg.CmsLog;
import co.siten.myvtg.model.myvtg.Log;

@org.springframework.stereotype.Service("ILogService")
public class LogService implements ILogService {
	@Autowired
	LogDao dao;

	@Autowired
	CmsLogDao cmsDao;

	public Log findById(String id) {
		Log data = dao.findOne(id);
		return data;
	}

	@Override
	public void delete(Log data) {
		dao.delete(data);
	}

	public Page<Log> findPaginated(int page, int size, String sortBy, int sortType) {
		Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();
		return dao.findAll(new PageRequest(page, size,
				new Sort(order)));
	}

	public void insertLog(Log log) {
		dao.save(log);
	}

	public void insertCmsLog(CmsLog log) {
		cmsDao.save(log);
	}

}
