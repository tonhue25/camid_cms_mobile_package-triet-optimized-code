package co.siten.myvtg.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.bean.PostageInfoBean;
import co.siten.myvtg.dao.BillingDao;

@org.springframework.stereotype.Service("BillingService")
@Transactional(rollbackFor = Exception.class, value = "billingtransaction")
public class BillingService {
	@Autowired
	BillingDao billingDao;

	public PostageInfoBean getPostageInfo(String isdn, int subType, Date startDate, Date endDate, String countryCode) {
		return billingDao.getPostageInfo(isdn, subType, startDate, endDate, countryCode);
	}

	public BigDecimal callGetHotChargeSub(long subId) {
		return billingDao.callGetHotChargeSub(subId);
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoCall(String isdn, Date startTime,
			Date endTime, String countryCode, int size, int page, String order) {
		return billingDao.getPostageDetailInfoCall(isdn, startTime, endTime, countryCode, size, page, order);
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoSms(String isdn, Date startTime,
			Date endTime, String countryCode, int size, int page, String order) {
		return billingDao.getPostageDetailInfoSms(isdn, startTime, endTime, countryCode, size, page, order);
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoOther(String isdn, Date startTime,
			Date endTime, String countryCode, int size, int page, String order) {
		return billingDao.getPostageDetailInfoOther(isdn, startTime, endTime, countryCode, size, page, order);
	}

}
