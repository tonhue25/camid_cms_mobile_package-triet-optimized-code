package co.siten.myvtg.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.bean.IsdnToBuyBean;
import co.siten.myvtg.dao.SmDao;
import co.siten.myvtg.model.sm.StockSim;

@org.springframework.stereotype.Service("SmService")
@Transactional(rollbackFor = Exception.class, value = "")
public class SmService {

	@Autowired
	SmDao smDao;

	public List<IsdnToBuyBean> getIsdnToBuy(int subType, int pageSize, int page, BigDecimal minPrice,
			BigDecimal maxPrice, String isdnPattern) {
		return smDao.getIsdnToBuy(subType, pageSize, page, minPrice, maxPrice, isdnPattern);
	}

	public void updateStockIsdnMobile(int status, int ownType, BigDecimal owner_id, String isdn) {
		smDao.updateStockIsdnMobile(status, ownType, owner_id, isdn);
	}

	public void updateStatusStockIsdnMobile(int status, String isdnToBuy) {
		smDao.updateStatusStockIsdnMobile(status, isdnToBuy);
	}

	public StockSim getStockSimBySerial(String serial) {
		return smDao.getStockSimBySerial(serial);
	}
	
	public Session getSession() {
		return smDao.getSession();
	}
}
