package co.siten.myvtg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.bean.DataBean;
import co.siten.myvtg.bean.HotNewsBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.bean.ServiceBean;
import co.siten.myvtg.bean.ServiceGroupBean;
import co.siten.myvtg.dao.CmposDao;
import co.siten.myvtg.dao.DataDao;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.dao.PrecallDao;
import co.siten.myvtg.dao.ServiceDao;
import co.siten.myvtg.dao.SubDao;
import co.siten.myvtg.model.myvtg.Sub;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.ExchangeChannel;

/**
 * 
 * @author thomc
 *
 */
@org.springframework.stereotype.Service("MasterDataService")

@PropertySource(value = { "classpath:provisioning.properties" })
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class MasterDataServiceImpl implements MasterDataService {
	@Autowired
	private static Environment environment;
	private static final Logger logger = Logger.getLogger(MasterDataServiceImpl.class);

	@Autowired
	MyvtgMasterDataDao myvtgDao;

	@Autowired
	SubDao subDao;


	@Autowired
	ServiceDao serviceDao;


	@Override
	public void wsGetHotNews(String isdn, int limit, String language, ResponseSuccessBean bean) {
		List<HotNewsBean> beanList = myvtgDao.getHotNewsByLanguage(language, limit);
		bean.setWsResponse(beanList);
	}


	@Override
	public void wsGetJobs(String language, ResponseSuccessBean bean) {
		List<DataBean> beanList = myvtgDao.getJobsByLanguage(language);
		bean.setWsResponse(beanList);

	}

}
