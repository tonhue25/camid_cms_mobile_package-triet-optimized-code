///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.siten.myvtg.service;
//
//import co.siten.myvtg.dao.CmposDao;
//import co.siten.myvtg.dao.CmpreDao;
//import co.siten.myvtg.dao.MyvtgMasterDataDao;
//import co.siten.myvtg.model.myvtg.AppParam;
//import co.siten.myvtg.util.DateTimeUtils;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author LuatNC
// */
//@PropertySource(value = { "classpath:provisioning.properties" })
//@Transactional(rollbackFor = Exception.class, value = "fulltransaction")
//public class SyncSubscriberScheduler {
//	private static final Logger logger = Logger.getLogger(SyncSubscriberScheduler.class);
//	@Autowired
//	MyvtgMasterDataDao myvtgDao;
//
//	@Autowired
//	CmpreDao cmpreDao;
//
//	@Autowired
//	CmposDao cmposDao;
//
//	@Autowired
//	private AsyncTaskService asyncTaskService;
//
//	// @Scheduled(fixedRate = 45000)
//	public void syncSubscriber() {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY HH:MM:SSSS");
//		Date start = new Date();
//		logger.info("Start Sync subscriber status: " + dateFormat.format(start));
//
//		try {
//			syncSubscriberPrepaid();
//			syncSubscriberPostpaid();
//		} catch (Exception e) {
//			// logger.error("ex", e);
//		}
//
//		Date finish = new Date();
//		logger.info("Finish Sync subscriber status: " + dateFormat.format(finish));
//		logger.info("Process sysnc time: " + (finish.getTime() - start.getTime()) + "milisecond");
//	}
//
//	private void syncSubscriberPrepaid() {
//		AppParam appParam = (AppParam) myvtgDao.getAppParamObject("last_sync_prepaid_subrelproduct_time");
//		if (appParam == null) {
//			appParam = new AppParam();
//			appParam.setName("");
//			appParam.setValue(new Long(DateTimeUtils.addNumSecond(new Date(), -60).getTime()).toString());
//
//			myvtgDao.persist(appParam);
//		}
//
//		String strLastSynTime = appParam.getValue();
//		Long startTime = 0L;
//		Date now = new Date();
//		if (strLastSynTime == null || strLastSynTime.isEmpty()) {
//			startTime = DateTimeUtils.addNumSecond(now, -60).getTime();
//		} else {
//			// don't allow to run too long time range (max 10')
//			startTime = Math.max(Long.parseLong(strLastSynTime), DateTimeUtils.addNumSecond(now, -600).getTime());
//		}
//		Date lastSyncTime = new Date(startTime);
//
//		try {
//			// Make a mark to other process doesn't process again.
//			Date dbTime = cmpreDao.getQueries().getLastSynSubRelProductTime();
//			appParam.setValue(new Long(dbTime.getTime()).toString());
//			myvtgDao.update(appParam);
//
//			// Start processing
//			asyncTaskService.syncSubscriber4PrePaid(lastSyncTime);
//
//		} catch (Exception e) {
//			logger.error("ex", e);
//		}
//	}
//
//	private void syncSubscriberPostpaid() {
//		AppParam appParam = (AppParam) myvtgDao.getAppParamObject("last_sync_postpaid_subrelproduct_time");
//		if (appParam == null) {
//			appParam = new AppParam();
//			appParam.setName("");
//			appParam.setValue(new Long(DateTimeUtils.addNumSecond(new Date(), -60).getTime()).toString());
//
//			myvtgDao.persist(appParam);
//		}
//
//		String strLastSynTime = appParam.getValue();
//		Long startTime = 0L;
//		Date now = new Date();
//		if (strLastSynTime == null || strLastSynTime.isEmpty()) {
//			startTime = DateTimeUtils.addNumSecond(now, -60).getTime();
//		} else {
//			// don't allow to run too long time range (max 10')
//			startTime = Math.max(Long.parseLong(strLastSynTime), DateTimeUtils.addNumSecond(now, -600).getTime());
//		}
//		Date lastSyncTime = new Date(startTime);
//
//		try {
//			// Make a mark to other process doesn't process again.
//			Date dbTime = cmposDao.getQueries().getLastSynSubRelProductTime();
//			appParam.setValue(new Long(dbTime.getTime()).toString());
//			myvtgDao.update(appParam);
//
//			// Start processing
//			asyncTaskService.syncSubscriber4PostPaid(lastSyncTime);
//		} catch (Exception e) {
//			logger.error("ex", e);
//		}
//	}
//}
