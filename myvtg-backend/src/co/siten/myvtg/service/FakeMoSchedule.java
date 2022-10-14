package co.siten.myvtg.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.bean.ResponseObj;
import co.siten.myvtg.dao.FakeMoDao;
import co.siten.myvtg.dao.MoHisDao;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.dao.ServiceDao;
import co.siten.myvtg.dao.SubDao;
import co.siten.myvtg.model.myvtg.FakeMo;
import co.siten.myvtg.model.myvtg.MoHis;
import co.siten.myvtg.model.myvtg.Webservice;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.WebServiceUtil;

@PropertySource(value = { "classpath:provisioning.properties" })
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class FakeMoSchedule {
	private static final Logger logger = Logger.getLogger(FakeMoSchedule.class);
	@Autowired
	FakeMoDao fmDao;

	@Autowired
	MoHisDao moHisDao;

	@Autowired
	MyvtgMasterDataDao myvtgDao;

	@Autowired
	ServiceDao serviceDao;

	@Autowired
	SubDao subDao;

	@Autowired
	private Environment environment;

	@Scheduled(fixedRate = 10000)
	public void sendSms() {
		logger.info("start send Sms");
		long processTime = System.nanoTime();
		fmDao.updateFakeMoToProcess(processTime, Constants.FAKE_MO_PROCESSING_SEND);
		List<FakeMo> fmList = fmDao.getFakeMoToProcess(processTime, Constants.FAKE_MO_PROCESSING_SEND);
		logger.info("fmList: " + fmList.size());
		fmDao.updateFakeMoToProcess(processTime, Constants.FAKE_MO_PENDING);
		logger.info("afterupdate" + fmList.size());
		for (FakeMo fm : fmList) {
			logger.info("start send Sms" + fm.getId());
			Webservice wsObject = myvtgDao.getWebserviceById(fm.getWsId());
			WebServiceUtil wsUtil = new WebServiceUtil(wsObject, environment);
			LinkedHashMap<String, Object> params = new LinkedHashMap<>();
			String msisdn = environment.getRequiredProperty("COUNTRY_CODE") + fm.getIsdn();
			params.put("Receiver", fm.getChannel());
			params.put("Sender", msisdn);
			String content = fm.getSyntax();
			if (content == null) {
				content = "";
			}

			try {
				content = Base64.getEncoder().encodeToString(content.getBytes());
				content = URLEncoder.encode(content, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("ex", e);
			}
			params.put("Content", content);
			ResponseObj response = wsUtil.callWebServiceSms(params, true);
			MoHis mohis = new MoHis(fm);
			logger.info("End send Sms" + CommonUtil.convertObjectToJsonString(response));
			if (response != null && Constants.ERROR_CODE_0.equalsIgnoreCase(response.getErrCode())) {
				mohis.setState(Constants.MOHIS_STATUS_INT_1);
			} else {
				mohis.setState(Constants.MOHIS_STATUS_INT_0);
			}
			//After send sms delete
			fmDao.delete(fm);
			moHisDao.persist(mohis);
		}
	}


}
