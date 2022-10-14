package co.siten.myvtg.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.bean.AllCustSubForSelfcareBean;
import co.siten.myvtg.bean.CamIdDetailBean;
import co.siten.myvtg.bean.HistoryChargeDataDetailsBean;
import co.siten.myvtg.bean.HistoryChargeDetailsBean;
import co.siten.myvtg.bean.HistoryChargeVasDetailsBean;
import co.siten.myvtg.bean.SubMainInfoBean;
import co.siten.myvtg.bean.SubRelProductBean;
import co.siten.myvtg.bean.SubscriberSyncInfoBean;
import co.siten.myvtg.dao.CmpreDao;
import co.siten.myvtg.model.cmpre.SubIsdnMb;
import co.siten.myvtg.model.cmpre.SubMb;
import co.siten.myvtg.model.cmpre.SubSimMb;
import co.siten.myvtg.util.Tuple2;

@org.springframework.stereotype.Service("CmpreService")
@Transactional(rollbackFor = Exception.class, value = "cmpretransaction")
public class CmpreService {

    @Autowired
    CmpreDao cmpreDao;

    public SubMb getSubMbByIsdnAndStatus(String isdn, int status) {
        return cmpreDao.getSubMbByIsdnAndStatus(isdn, status);
    }

    public AllCustSubForSelfcareBean getCustSubForSelfcare(String isdn) {
        return cmpreDao.getCustSubForSelfcare(isdn);
    }

    public SubMainInfoBean getCustSubInfo(String isdn) {
        return cmpreDao.getCustSubInfo(isdn);
    }

    public List<SubscriberSyncInfoBean> getSubscriberSyncInfoBean(Long subId, Boolean isLimitTime, Object appPr) {
        return cmpreDao.getSubscriberSyncInfoBean(subId, isLimitTime ? Integer.parseInt(appPr.toString()) : 1000);
    }

    public String getActStatus(String isdn) {
        return cmpreDao.getActStatus(isdn);
    }

    public List<SubRelProductBean> getChangeFromSubRelProductTable(Date lastSyncTime) {
        return cmpreDao.getChangeFromSubRelProductTable(lastSyncTime);
    }

    public AllCustSubForSelfcareBean getCustSubForSelfcare(String isdn, String status) {
        return cmpreDao.getCustSubForSelfcare(isdn, status);
    }

    public SubMb getSubMbBySerial(String serial) {
        return cmpreDao.getSubMbBySerial(serial);
    }

    public int updateSubMbBySubId(int status, long subId) {
        return cmpreDao.updateSubMbBySubId(status, subId);
    }

    public int updateSubMbByIsdn(int status, String isdn, Date date) {
        return cmpreDao.updateSubMbByIsdn(status, isdn, date);
    }

    public void updateSubIsdnMb(int subMbStatus0, long subId) {
        cmpreDao.updateSubIsdnMb(subMbStatus0, subId);
    }

    public SubSimMb getSubSimMbBySubIdAndStatus(long subId, int subMbStatus1) {
        return cmpreDao.getSubSimMbBySubIdAndStatus(subId, subMbStatus1);
    }

    public Session getSession() {
        return cmpreDao.getSession();
    }

    public void persist(SubMb entity) {
        cmpreDao.persist(entity);
    }

    public void persistSubIsdnMb(SubIsdnMb entity) {
        cmpreDao.persist(entity);
    }

    public void persistActionLogPr(co.siten.myvtg.model.cmpre.ActionLogPr entity) {
        cmpreDao.persist(entity);
    }

    public void persistActionAudit(co.siten.myvtg.model.cmpre.ActionAudit entity) {
        cmpreDao.persist(entity);
    }

    public void update(SubMb entity) {
        cmpreDao.update(entity);
    }

    public String mfGetProductNameByProductCode(String productCode) {
        return cmpreDao.mfGetProductNameByProductCode(productCode);
    }

    public List<Tuple2<String, Date>> getRegisteredRelProductCode(Long subId) {
        return cmpreDao.getRegisteredRelProductCode(subId);
    }

    public String getListRegisteredServiceCodes(Long subId) {
        return cmpreDao.getListRegisteredServiceCodes(subId);
    }

    public Map<String, Date> getListRegisteredAndExpiredDateServiceCodes(Long subId) {
        return cmpreDao.getListRegisteredAndExpiredDateServiceCodes(subId);
    }
	  public boolean synRegisterSub(String isdn) {
        return cmpreDao.synRegisterSub(isdn);
    }
    //get service expired

    public String getListRegisteredServiceCodesExpired(Long subId, Date startTime, Date endTime) {
        return cmpreDao.getListRegisteredServiceCodesExpired(subId, startTime, endTime);
    }

    public List<String> getRegisteredServiceCodes(Long subId) {
        return cmpreDao.getRegisteredServiceCodes(subId);
    }

    public Double getTotalCamIdDetailCall(String isdn, Date startTime, Date endTime, String type) {
        return cmpreDao.getTotalCamIdDetailCall(isdn, startTime, endTime, type);
    }

    public Double getTotalCamIdDetailData(String isdn, Date startTime, Date endTime, String type) {
        return cmpreDao.getTotalCamIdDetailData(isdn, startTime, endTime, type);
    }

    public Double getTotalCamIdDetailSms(String isdn, Date startTime, Date endTime, String type) {
        return cmpreDao.getTotalCamIdDetailSms(isdn, startTime, endTime, type);
    }

    public Double getTotalCamIdDetailVas(String isdn, Date startTime, Date endTime, String type) {
        return cmpreDao.getTotalCamIdDetailVas(isdn, startTime, endTime, type);
    }

    public List<CamIdDetailBean> getCamIdDataDetail(String isdn, Date startTime, Date endTime) {
        return cmpreDao.getCamIdDataDetail(isdn, startTime, endTime);
    }

    public List<HistoryChargeDataDetailsBean> getCamIdDataDetails(String isdn, Date startTime, Date endTime) {
        return cmpreDao.getCamIdDataDetails(isdn, startTime, endTime);
    }

    //
    public List<HistoryChargeDetailsBean> getHistoryChargeSmsDetails(String isdn, Date startTime, Date endTime, String parentType) {
        return cmpreDao.getHistoryChargeSmsDetails(isdn, startTime, endTime, parentType);
    }

    public List<HistoryChargeDetailsBean> getHistoryChargeCallingDetails(String isdn, Date startTime, Date endTime, String parentType) {
        return cmpreDao.getHistoryChargeCallingDetails(isdn, startTime, endTime, parentType);
    }

    public List<HistoryChargeDataDetailsBean> getHistoryChargeDataDetails(String isdn, Date startTime, Date endTime, String parentType) {
        return cmpreDao.getHistoryChargeDataDetails(isdn, startTime, endTime, parentType);
    }

    public List<HistoryChargeVasDetailsBean> getHistoryChargeVasDetails(String isdn, Date startTime, Date endTime, String parentType) {
        return cmpreDao.getHistoryChargeVasDetails(isdn, startTime, endTime, parentType);
    }
}
