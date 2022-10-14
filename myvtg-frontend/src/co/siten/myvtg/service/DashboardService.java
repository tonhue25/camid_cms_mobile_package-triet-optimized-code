
package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.siten.myvtg.bean.DashboardInfoBean;
import co.siten.myvtg.bean.DataByStringBean;
import co.siten.myvtg.bean.DataByTimeBean;
import co.siten.myvtg.dao.LogDao;
import co.siten.myvtg.dao.SubDao;
import co.siten.myvtg.dao.TransactionErrorDao;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;

@Service("IDashboardService")
public class DashboardService implements IDashboardService {
	@Autowired
	SubDao subDao;
	@Autowired
	TransactionErrorDao errorDao;
	@Autowired
	LogDao logDao;

	@Override
	public DashboardInfoBean getDashboardInfo() {
		DashboardInfoBean bean = new DashboardInfoBean();

		bean.setSubTotal(subDao.countByStatus(Constants.SUB_STATUS_1));

		bean.setNewSubTotal(subDao.countByStatusAndRegisterTime(Constants.SUB_STATUS_1, CommonUtil.getCurrentTime()));
		
        bean.setActiveSubTotal(
				subDao.countByStatusAndLastActiveTime(Constants.SUB_STATUS_1, CommonUtil.getCurrentTime()));

		bean.setErrorTotal(errorDao.countByStaTimeGreaterThanEqual(CommonUtil.getCurrentTime()));

		List<DataByTimeBean> subList = subDao.getDataByTimeBean(CommonUtil.getPreviousMonth());
		List<DataByTimeBean> requestList = logDao.getDataByTimeBean(CommonUtil.getPreviousMonth());
		List<DataByStringBean> osList = subDao.getDataByStringBean();

		bean.setOsList(osList);
		bean.setRequestList(requestList);
		bean.setSubList(subList);
		return bean;
	}

}
