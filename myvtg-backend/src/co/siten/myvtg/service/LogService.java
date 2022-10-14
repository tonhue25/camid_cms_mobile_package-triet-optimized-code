package co.siten.myvtg.service;

import co.siten.myvtg.model.myvtg.Log;
import co.siten.myvtg.model.myvtg.TransactionError;

/**
 * 
 * @author thomc
 *
 */
public interface LogService {
	public void insertLog(Log log);
	
	public void insertTransactionLog(TransactionError log);
}
