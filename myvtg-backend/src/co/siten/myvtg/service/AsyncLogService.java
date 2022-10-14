/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.dao.MyvtgMasterDataDao;
import co.siten.myvtg.model.myvtg.Log;
import co.siten.myvtg.model.myvtg.TransactionError;

/**
 *
 * @author ThoMC
 */
@Component
@PropertySource(value = { "classpath:provisioning.properties" })
@Transactional(rollbackFor = Exception.class, value = "myvtglogtransaction")
public class AsyncLogService implements LogService {
	@Autowired
	MyvtgMasterDataDao myvtgDao;

	@Async
        @Override
	public void insertLog(Log log) {
		myvtgDao.persist(log);
	}

	@Async
	@Override
	public void insertTransactionLog(TransactionError log) {
		myvtgDao.persist(log);
	}
}
