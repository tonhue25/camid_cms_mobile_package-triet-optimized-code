package co.siten.myvtg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.dao.DataDao;
import co.siten.myvtg.model.data.VSelfcareData;
import co.siten.myvtg.model.data.VSelfcareDataToBuy;

@org.springframework.stereotype.Service("DataService")
@Transactional(rollbackFor = Exception.class, value = "datatransaction")
public class DataService {
	@Autowired
	DataDao dataDao;

	public List<VSelfcareData> getVSelfcareDataByMsisdn(String msisdn) {
		return dataDao.getVSelfcareDataByMsisdn(msisdn);
	}

	public VSelfcareDataToBuy getVSelfcareDataToBuyByName(String dataName) {
		return dataDao.getVSelfcareDataToBuyByName(dataName);
	}

	public List<String> getPackageNameAbleToRegisterByProductName(String productName, int simType) {
		return dataDao.getPackageNameAbleToRegisterByProductName(productName, simType);
	}

	public String callSelcareFakeMO(String srcMsisdn, String desMsisdn, String msg) {
		return dataDao.callSelcareFakeMO(srcMsisdn, desMsisdn, msg);
	}
}
