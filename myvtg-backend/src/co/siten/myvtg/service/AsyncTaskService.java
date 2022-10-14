/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.viettel.common.ViettelService;

import co.siten.myvtg.bean.SubRelProductBean;
import co.siten.myvtg.bean.SubscriberSyncInfoBean;
import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.model.myvtg.Sub;
import co.siten.myvtg.model.myvtg.Subscriber;
import co.siten.myvtg.model.myvtg.SubscriberPK;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.ExchangeChannel;

/**
 *
 * @author LuatNC
 */
@Component
@PropertySource(value = { "classpath:provisioning.properties" })
public class AsyncTaskService {
	private static final Logger logger = Logger.getLogger(AsyncTaskService.class);

	@Autowired
	private Environment environment;

	@Autowired
	MyvtgService myvtgService;
	

	@Autowired
	CmpreService cmpreService;

	@Autowired
	CmposService cmposService;
//
//	@Autowired
//	LogService logService;

	@Autowired
	ProService proService;

	@Async
	public void syncNewSub(String isdn, Long custId, String custName, Date birthday, String productCode, String gender,
			int subType) throws Exception {
		logger.info("syncNewSub: start sync new Subscriber...");
		Sub sub = new Sub();
		sub.setSubId(custId);
		sub.setName(custName);
		sub.setBirthDate(birthday);
		sub.setProductCode(productCode);
		sub.setIsdn(isdn);
		sub.setGender("Male".equalsIgnoreCase(gender) ? Constants.GENDER_MALE : Constants.GENDER_FEMALE);
//		Object appPr = myvtgService.getAppParam(Constants.APP_PARAM_DEFAULT_LANGUAGE);
//		if (appPr == null) {
//			throw new Exception("getAppParam Not found" + Constants.APP_PARAM_DEFAULT_LANGUAGE);
//		}
		//sub.setLanguage(appPr.toString());
		sub.setLastSyncTime(CommonUtil.getCurrentTime());
		sub.setStatus(Constants.SUB_STATUS_1);
		sub.setSubType(subType);
		sub.setRegisterTime(CommonUtil.getCurrentTime());

		// code thêm 3.1
		try {
			String cardType = checkSim3G4G(isdn);
			if (Constants.CARD_TYPE_USIM.equalsIgnoreCase(cardType)) {
				sub.setSimType(Constants.SIM_TYPE_4G);
			} else {
				sub.setSimType(Constants.SIM_TYPE_3G);
			}
			myvtgService.persistSub(sub);

			logger.info("syncNewSub: finish sync new Subscriber...");

		} catch (Exception ex) {
			logger.info("Error check 3G/4G SIM");

		}

//		Log log = new Log();
//		log.setStaTime(new Date());
//		log.setEndTime(new Date());
//		log.setMessage("syncNewSub: " + sub.toString());
//		log.setApiKey("syncNewSub: isdn = " + isdn + ", custId = " + custId + ", productCode = " + productCode);
//		logService.insertLog(log);
	}

	public String checkSim3G4G(String isdn) {
		ViettelService request = new ViettelService();
		ViettelService response = new ViettelService();
		String msisdn = getCountryCode() + isdn;
		String cardType = "";
		try {
			ExchangeChannel channel = proService.getChannel();
			request.setMessageType("1900");
			request.setProcessCode("000002");
			request.set("MSISDN", msisdn);

			// send COMMAND, dung ham sendAll cua lib
			response = (ViettelService) channel.send(request);

			if (response != null) {
				String prRespondCode = response.get("responseCode").toString();
				if (("0".equals(prRespondCode)) || ("405000000".equals(prRespondCode))) {
					cardType = response.get(environment.getRequiredProperty("CardTypeTag")) != null
							? response.get(environment.getRequiredProperty("CardTypeTag")).toString() : "";
				}
			}
			logger.info("Thong tin gui sang Provisioning:\n" + request);
			logger.info("Thong tin tra ve tu Provisioning:\n" + response);
		} catch (Exception e) {
			logger.error("Error get data promotion", e);
			logger.info("Thong tin gui sang Provisioning:\n" + request);
			logger.info("Thong tin tra ve tu Provisioning:\n" + response);
		}
		return cardType;
	}

	@Async
	public void updateSub(String isdn, Long custId, String custName, Date birthday, String productCode, String gender) {
		if (CommonUtil.isEmpty(isdn)) {
			return;
		}

		Sub s = myvtgService.findByIsdn(isdn);
		if (!CommonUtil.isEmpty(custName)) {
			s.setName(custName);
		}
		if (!CommonUtil.isEmpty(productCode)) {
			s.setProductCode(productCode);
		}
		if (!CommonUtil.isEmpty(gender)) {
			s.setGender("Male".equalsIgnoreCase(gender) ? Constants.GENDER_MALE : Constants.GENDER_FEMALE);
		}
		if (birthday != null) {
			s.setBirthDate(birthday);
		}
		s.setLastSyncTime(CommonUtil.getCurrentTime());
		myvtgService.updateSub(s);

//		Log log = new Log();
//		log.setStaTime(new Date());
//		log.setEndTime(new Date());
//		log.setMessage("updateSub: " + s.toString());
//		log.setApiKey("updateSub: isdn = " + isdn + ", custId = " + custId + ", productCode = " + productCode);
//		logService.insertLog(log);
	}

	@Async
	public void syncSubscriberState(String isdn, Long subId, int subType, Boolean isLimitTime) throws Exception {
		logger.info("isdn: " + isdn + ", subId: " + subId);
		Long startTime = System.nanoTime();
		logger.info("syncSubscriberState: start sync Subscriber's service...: " + startTime);

		Object appPr = myvtgService.getAppParam("sync_subscriber_day");
		if (appPr == null) {
			throw new Exception("getAppParam Not found" + "sync_subscriber_day");
		}
		List<SubscriberSyncInfoBean> result1;
		if (subType == Constants.SUBTYPE_TRATRUOC) {
			result1 = cmpreService.getSubscriberSyncInfoBean(subId, isLimitTime, appPr);
		} else {
			result1 = cmposService.getSubscriberSyncInfoBean(subId, isLimitTime, appPr);
		}

		/*
		 * List<SubscriberSyncInfoBean> result2 =
		 * myvtgService.getQueries().getUnitelSubscriberVASSyncInfoBean(
		 * environment.getRequiredProperty("COUNTRY_CODE") + isdn,
		 * Integer.parseInt(appPr.toString()));
		 */
		List<SubscriberSyncInfoBean> result = new ArrayList<>();
		if (result1 != null && !result1.isEmpty()) {
			result.addAll(result1);
		}
		/*
		 * if (result2 != null && !result2.isEmpty()) { result.addAll(result2);
		 * }
		 */
		if (result.isEmpty())
			return;

		// Get subscriber state from MyVTG database
		List<Subscriber> subscribers = myvtgService.getSubscriberListByIsdn(isdn);
		if (subscribers == null || subscribers.isEmpty()) {
			myvtgService.insertBatchSubscriber(isdn, subId, result);
		} else {
			syncSubscriberState(isdn, subId, result, subscribers);
		}

		logger.info("syncSubscriberState: start sync new Subscriber's service...: " + +System.nanoTime());
		logger.info("syncSubscriberState: processing time: " + (System.nanoTime() - startTime));

//		Log log = new Log();
//		log.setInsertDate(new Date());
//		log.setStaTime(new Date());
//		log.setEndTime(new Date());
//		log.setMessage("syncSubscriberState: processing time: " + (System.nanoTime() - startTime));
//		log.setApiKey("syncSubscriberState: isdn = " + isdn + ", subId = " + subId);
//		logService.insertLog(log);
	}

	private void syncSubscriberState(String isdn, Long subId, List<SubscriberSyncInfoBean> subscriberSyncInfoBeans,
			List<Subscriber> subscribers) {
		List<SubscriberSyncInfoBean> newSubscribers = new ArrayList<>();
		List<Subscriber> updateSubscribers = new ArrayList<>();
		Boolean found = false;

		for (SubscriberSyncInfoBean scrsi : subscriberSyncInfoBeans) {
			// TODO: Warning - try to remove 4G_/3G_ from service code
			String serviceCode = scrsi.getSubServiceCode();
			// serviceCode = serviceCode.replace("3G_", "").replace("4G_", "");
			scrsi.setSubServiceCode(serviceCode);

			for (Subscriber scr : subscribers) {
				if (scr.getId().getSubServiceCode().equalsIgnoreCase(serviceCode)) {
					if (scrsi.getRegisterTime() != null)
						scr.setRegisterTime(scrsi.getRegisterTime());
					if (scrsi.getCancelTime() != null)
						scr.setCancelTime(new Timestamp(scrsi.getCancelTime().getTime()));
					scr.setState(scrsi.getState());
					scr.setLastUpdatedBy("SYNC PROCESS - 01");
					scr.setLastUpdatedTime(new Date());
					updateSubscribers.add(scr);

					subscribers.remove(scr);
					found = true;
					break;
				}
			}

			if (!found) {
				newSubscribers.add(scrsi);
			}

			found = false;
		}

		if (!newSubscribers.isEmpty()) {
			myvtgService.insertBatchSubscriber(isdn, subId, newSubscribers);
		}

		// try to remove all service/package unsuccessfully register.
		subscribers.removeAll(updateSubscribers);
		if (!subscribers.isEmpty()) {
			for (Subscriber scr : subscribers) {
				// update state un-register
				scr.setState(0);
			}
			updateSubscribers.addAll(subscribers);
		}
		if (!updateSubscribers.isEmpty()) {
			myvtgService.updateBatchSubscriber(updateSubscribers);
		}
	}

	private String getCountryCode() {
		return environment.getRequiredProperty("COUNTRY_CODE");
	}

	void syncSubscriber4PrePaid(Date lastSyncTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("DD/mm/YYYY HH:MM:SSSS");
		Date start = new Date();
		logger.info("Start Sync subscriber for PREPAID: " + dateFormat.format(start));
		List<SubRelProductBean> subRelProductBeans = cmpreService.getChangeFromSubRelProductTable(lastSyncTime);
		syncSubScriber(subRelProductBeans, start);

		Date finish = new Date();
		logger.info("Finish Sync subscriber for PREPAID: " + dateFormat.format(finish));
		logger.info("Process sysnc time: " + (finish.getTime() - start.getTime()) + "milisecond");
	}

	void syncSubscriber4PostPaid(Date lastSyncTime) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("DD/mm/YYYY HH:MM:SSSS");
		Date start = new Date();
		logger.info("Start Sync subscriber for PREPAID: " + dateFormat.format(start));
		List<SubRelProductBean> subRelProductBeans = cmposService.getChangeFromSubRelProductTable(lastSyncTime);
		syncSubScriber(subRelProductBeans, start);

		Date finish = new Date();
		logger.info("Finish Sync subscriber for PREPAID: " + dateFormat.format(finish));
		logger.info("Process sysnc time: " + (finish.getTime() - start.getTime()) + "milisecond");
	}

	private void syncSubScriber(List<SubRelProductBean> subRelProductBeans, Date startTime) {
		if (subRelProductBeans != null && !subRelProductBeans.isEmpty()) {
			List<Subscriber> updatedItems = new ArrayList<>();
			List<Subscriber> newItems = new ArrayList<>();
			for (SubRelProductBean rc : subRelProductBeans) {
				Subscriber scr = myvtgService.getSubscriberById(rc.getSubId(), rc.getRelProductCode());
				// update
				if (scr != null) {
					scr.setRegisterTime(rc.getRegDate());
					scr.setCancelTime(new Timestamp(rc.getCancelDate().getTime()));
					scr.setLastUpdatedBy("SYN PRCOESS");
					scr.setLastUpdatedTime(startTime);
					scr.setState(rc.getStatus());
					scr.setStatus(rc.getStatus());

					updatedItems.add(scr);
				} else { // insert
					String isdn = myvtgService.getIsdnBySubId(rc.getSubId());
					if (isdn != null) {
						scr = new Subscriber();
						SubscriberPK pk = new SubscriberPK();
						pk.setIsdn(isdn);
						pk.setSubServiceCode(rc.getRelProductCode());
						scr.setId(pk);
						scr.setRegisterTime(rc.getRegDate());
						scr.setCancelTime(new Timestamp(rc.getCancelDate().getTime()));
						scr.setLastUpdatedBy("SYN PRCOESS");
						scr.setLastUpdatedTime(startTime);
						scr.setState(rc.getStatus());
						scr.setStatus(rc.getStatus());

						newItems.add(scr);
					}
				}
			}

			// insert & update batch
			if (!updatedItems.isEmpty()) {
				myvtgService.updateBatchSubscriber(updatedItems);
			}

			if (!newItems.isEmpty()) {
				myvtgService.insertBatchSubscriber(newItems);
			}
		}
	}
}
