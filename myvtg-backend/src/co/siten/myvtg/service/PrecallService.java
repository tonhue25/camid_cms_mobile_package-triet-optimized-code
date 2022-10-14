package co.siten.myvtg.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.bean.PostageInfoBean;
import co.siten.myvtg.dao.PrecallDao;

@org.springframework.stereotype.Service("PrecallService")
@Transactional(rollbackFor = Exception.class, value = "precalltransaction")
public class PrecallService {
	@Autowired
	PrecallDao precallDao;

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoCall(String isdn, Date startTime,
			Date endTime, String countryCode, int size, int page, String order) {
		return precallDao.getPostageDetailInfoCall(isdn, startTime, endTime, countryCode, size, page, order);
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoSms(String isdn, Date startTime,
			Date endTime, String countryCode, int size, int page, String order) {
		return precallDao.getPostageDetailInfoSms(isdn, startTime, endTime, countryCode, size, page, order);
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoOther(Environment environment,
			String isdn, Date startTime, Date endTime, String countryCode, int size, int page, String order) {
		return precallDao.getPostageDetailInfoOther(environment, isdn, startTime, endTime, countryCode, size, page,
				order);
	}

	/**
	 * @NamDH1: Added:20171109 for request change from VTC
	 * @param environment
	 * @param isdn
	 * @param startTime
	 * @param endTime
	 * @param countryCode
	 * @param size
	 * @param page
	 * @param order
	 * @return
	 */
	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoData(Environment environment,
			String isdn, Date startTime, Date endTime, String countryCode, int size, int page, String order) {
		return precallDao.getPostageDetailInfoData(environment, isdn, startTime, endTime, countryCode, size, page,
				order);
	}

	/**
	 * @NamDH1: Added:20171109 for request change from VTC
	 * @param environment
	 * @param isdn
	 * @param startTime
	 * @param endTime
	 * @param countryCode
	 * @param size
	 * @param page
	 * @param order
	 * @return
	 */

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoVas(Environment environment, String isdn,
			Date startTime, Date endTime, String countryCode, int size, int page, String order) {
		return precallDao.getPostageDetailInfoVas(environment, isdn, startTime, endTime, countryCode, size, page,
				order);
	}

	public PostageInfoBean getPostageInfo(Environment environment, String isdn, int subType, Date startDate,

			Date endDate, String type, String countryCode) {
		return precallDao.getPostageInfo(environment, isdn, subType, startDate,

				endDate, type, countryCode);
	}

	/**
	 * @namdh1 add for split data, vas from other
	 * @param environment
	 * @param isdn
	 * @param subType
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @param countryCode
	 * @return
	 */
	public PostageInfoBean getPostageInfoSplitDataVas(Environment environment, String isdn, int subType, Date startDate,

			Date endDate, String type, String countryCode) {
		return precallDao.getPostageInfoSplitDataVas(environment, isdn, subType, startDate,

				endDate, type, countryCode);
	}

}
