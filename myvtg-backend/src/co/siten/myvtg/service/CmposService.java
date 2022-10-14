package co.siten.myvtg.service;

import co.siten.myvtg.bean.SubMainInfoBean;
import co.siten.myvtg.bean.SubRelProductBean;
import co.siten.myvtg.bean.SubscriberSyncInfoBean;
import co.siten.myvtg.bean.VCustomerBean;
import co.siten.myvtg.dao.CmposDao;
import co.siten.myvtg.model.cmpos.*;
import co.siten.myvtg.util.Tuple2;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service("CmposService")
@Transactional(rollbackFor = Exception.class, value = "cmpostransaction")
public class CmposService {
	@Autowired
	CmposDao cmposDao;

	public SubMbPos getSubMbByIsdnAndStatus(String isdn, int status) {
		return cmposDao.getSubMbByIsdnAndStatus(isdn, status);
	}

	public AllTelServiceSubSelfcare getTelServiceSubSelfcare(String isdn) {
		return cmposDao.getTelServiceSubSelfcare(isdn);
	}

	public SubMainInfoBean getCustSubInfo(String isdn) {
		return cmposDao.getCustSubInfo(isdn);
	}

	public List<SubscriberSyncInfoBean> getSubscriberSyncInfoBean(Long subId, Boolean isLimitTime, Object appPr) {
		return cmposDao.getSubscriberSyncInfoBean(subId, isLimitTime ? Integer.parseInt(appPr.toString()) : 1000);
	}

	public List<SubRelProductBean> getChangeFromSubRelProductTable(Date lastSyncTime) {
		return cmposDao.getChangeFromSubRelProductTable(lastSyncTime);
	}

	public Session getSession() {
		return cmposDao.getSession();
	}

	public void update(Object entity) {
		cmposDao.update(entity);
	}

	public void persist(Object entity) {
		cmposDao.persist(entity);
	}

	public SubMbPos getSubMbBySerial(String serial) {
		return cmposDao.getSubMbBySerial(serial);
	}

	public int updateSubMb(int status, long subId) {
		return cmposDao.updateSubMb(status, subId);
	}

	public co.siten.myvtg.model.cmpos.SubSimMb getSubSimMbBySubIdAndStatus(long subId, int subMbStatus1) {
		return cmposDao.getSubSimMbBySubIdAndStatus(subId, subMbStatus1);
	}

	public int updateSubMbByIsdn(int status, String isdn, Date date) {
		return cmposDao.updateSubMbByIsdn(status, isdn, date);
	}

	public int updateSubMbBySubId(int status, long subId) {
		return cmposDao.updateSubMbBySubId(status, subId);
	}

	public void updateSubIsdnMb(int subMbStatus0, long subId) {
		cmposDao.updateSubIsdnMb(subMbStatus0, subId);
	}

	public Contract getContractById(Long contractId) {
		return cmposDao.getContractById(contractId);
	}

	public Customer getCustomerById(Long custId) {
		return cmposDao.getCustomerById(custId);
	}

	public ContractOffer getContractOffer(long subId) {
		return cmposDao.getContractOffer(subId);
	}

	public void updateOfferSub(int subMbStatus0, long subId) {
		cmposDao.updateOfferSub(subMbStatus0, subId);
	}

	public OfferSub getOfferSub(long subId) {
		return cmposDao.getOfferSub(subId);
	}

	public BigDecimal getNextActionLogPrSeq() {
		return cmposDao.getNextActionLogPrSeq();
	}

	public BigDecimal getNextActionAuditSeq() {
		return cmposDao.getNextActionAuditSeq();
	}

	public String mfGetProductNameByProductCode(String productCode) {
		return cmposDao.mfGetProductNameByProductCode(productCode);
	}

	public List<Tuple2<String, Date>> getRegisteredRelProductCode(Long subId) {
		return cmposDao.getRegisteredRelProductCode(subId);
	}

	public String getListRegisteredServiceCodes(Long subId) {
		return cmposDao.getListRegisteredServiceCodes(subId);
	}

	public Map<String, Date> getListRegisteredAndExpiredDateServiceCodes(Long subId) {
		return cmposDao.getListRegisteredAndExpiredDateServiceCodes(subId);
	}

        //get sevices Expired
        public String getListRegisteredServiceCodesExpired(Long subId, Date startTime, Date endTime) {
		return cmposDao.getListRegisteredServiceCodesExpired(subId, startTime, endTime);
	}

	public List<String> getRegisteredServiceCodes(Long subId) {
		return cmposDao.getRegisteredServiceCodes(subId);
	}


}
