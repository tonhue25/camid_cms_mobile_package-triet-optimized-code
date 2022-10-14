package co.siten.myvtg.service;

import co.siten.myvtg.bean.*;
import co.siten.myvtg.dao.*;
import co.siten.myvtg.dto.NewsCovidDto;
import co.siten.myvtg.dto.NotificationResult;
import co.siten.myvtg.dto.PaymentHistoryDTO;
import co.siten.myvtg.model.myvtg.SubServiceA;
import co.siten.myvtg.model.myvtg.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service("MyvtgService")
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
public class MyvtgService {

    @Autowired
    MyvtgMasterDataDao myvtgDao;

    @Autowired
    SubDao subDao;

    @Autowired
    ServiceDao serviceDao;

    @Autowired
    FakeMoDao fakeMoDao;

    @Autowired
    ChargeHisDao chargeHisDao;

    @Autowired
    TransactionLogDao transactionLogDao;

    @Autowired
    MyMetfoneBusinesDao dao;

    public SubAccountInfoBean getSubByIsdn(String isdn) {
        return subDao.getSubByIsdn(isdn);

    }

    public Sub findByIsdn(String isdn) {
        return subDao.findByIsdn(isdn);
    }

    public void updateSub(Sub s) {
        subDao.update(s);
    }

    public String getAppParam(String name) {
        return myvtgDao.getAppParam(name);
    }

    public List<AccConfig> getAllAccConfig() {
        return myvtgDao.getAllAccConfig();
    }

    public List<DataPricePlanConfig> getAllPricePlanConfig(int simType) {
        return myvtgDao.getAllPricePlanConfig(simType);
    }

    public List<SubServiceA> getRegisteredPackageData(List<String> relProCodes, String language) {
        return myvtgDao.getRegisteredPackageData(relProCodes, language);
    }

    public DataPricePlanConfig getPricePlanConfigByPricePlanId(int pricePlan, int simType) {
        return myvtgDao.getPricePlanConfigByPricePlanId(pricePlan, simType);
    }

    public SubMainInfoBean getSubMainInfo(String isdn) {
        //    System.out.println("================>");
        return serviceDao.getSubMainInfo(isdn);
    }

    public ServiceInfoBean getServiceInfo(String language, String serviceCode) {
        return serviceDao.getServiceInfo(language, serviceCode);

    }

    public ServiceInfoBean getServiceInfoAirtime(String isdn, String language, String serviceCode) {
        return serviceDao.getServiceInfoAirtime(isdn, language, serviceCode);

    }

    public List<SubServiceBean> getCurrentUsedSubServices(String language, String isdn,
                                                          String listRegisteredServiceCodes) {

        return serviceDao.getCurrentUsedSubServices(language, isdn, listRegisteredServiceCodes);
    }

    public Subscriber getSubcriberByIsdnAndServiceCode(String isdn, String serviceCode) {
        return serviceDao.getSubcriberByIsdnAndServiceCode(isdn, serviceCode);

    }

    public void updateSubcriber(String isdn, String serviceCode, int state) {
        serviceDao.updateSubcriber(isdn, serviceCode, state);
    }

    @Cacheable("services")
    public BlockGoingCallBean getServiceInfoForBlockGoingCall(String language, String serviceCode) {
        return serviceDao.getServiceInfoForBlockGoingCall(language, serviceCode);
    }

    public AdvanceServiceBean getAdvancedServiceInfo(String language, String isdn, String serviceCode) {
        return serviceDao.getAdvancedServiceInfo(language, isdn, serviceCode);
    }

    public void persistCharhis(ChargeHis entity) {
        chargeHisDao.persist(entity);
    }

    public void persistFake(FakeMo entity) {
        fakeMoDao.persist(entity);
    }

    public void save(FakeMo entity) {
        fakeMoDao.update(entity);
    }

    public void persistSub(Sub entity) {
        myvtgDao.persist(entity);
    }

    public void updateSubCycleRe(SubCycleRe entity) {
        myvtgDao.update(entity);
    }

    public void updateCharhis(ChargeHis entity) {
        chargeHisDao.update(entity);
    }

    public void persistTransactionLog(TransactionLog entity) {
        transactionLogDao.persist(entity);
    }

    public void persistSubMarkAudit(SubMarkAudit entity) {
        myvtgDao.persist(entity);
    }

    public Webservice getWebserviceByName(String name) {
        return myvtgDao.getWebserviceByName(name);
    }

    public SubCycleRe getSubCycleReBySubId(BigDecimal subId) {
        return myvtgDao.getSubCycleReBySubId(subId);
    }

    public Sub findById(String id) {
        return subDao.findById(id);
    }

    public int updateSubNameAndSyncDate(String isdn, String name, Date now) {
        return subDao.updateSubNameAndSyncDate(isdn, name, now);
    }

    public Long getSubIdByIsdn(String isdn) {
        return subDao.getSubIdByIsdn(isdn);
    }

    public String getIsdnBySubId(Long subId) {
        return subDao.getIsdnBySubId(subId);
    }

    public DataPackageInfoBean getDataPackageInfo(Sub sub, String packageCode, String lang, String serviceTypes,
                                                  List<String> listRegisteredServiceCodes) {

        return myvtgDao.getDataPackageInfo(sub, packageCode, lang, serviceTypes, listRegisteredServiceCodes);
    }

    public WebserviceBean getWebserviceByServiceCodeAndActionType(String serviceCode, int actionType) {
        return myvtgDao.getWebserviceByServiceCodeAndActionType(serviceCode, actionType);
    }

    public List<AccConfig> getAllAccConfigByLanguage(String language) {
        return myvtgDao.getAllAccConfigByLanguage(language);
    }

    public String getAppParamByVersion(String name, String version) {
        return myvtgDao.getAppParamByVersion(name, version);
    }

    public List<ServiceBean> getAllUnregisterService(String isdn, String language, String listRegisteredServiceCodes) {
        return serviceDao.getAllUnregisterService(isdn, language, listRegisteredServiceCodes);
    }

    public List<ServiceBean> getCurrentUsedAllServices(String language, String isdn,
                                                       String listRegisteredServiceCodes) {
        return myvtgDao.getCurrentUsedAllServices(language, isdn, listRegisteredServiceCodes);
    }

    public List<ServiceDetailBean> getServiceDetail(String language, String isdn, String serviceCode,
                                                    List<String> listRegisteredServiceCodes) {
        return myvtgDao.getServiceDetail(language, isdn, serviceCode, listRegisteredServiceCodes);
    }

    public List<ServiceBean> getServicesByGroup(String language, String isdn, String serviceGroupCode,
                                                String listRegisteredServiceCodes, String validity) {
        return myvtgDao.getServicesByGroup(language, isdn, serviceGroupCode, listRegisteredServiceCodes, validity);
    }

    public List<ServiceBean> getAllDataPackages(String language, String isdn, String serviceTypes,
                                                String listRegisteredServiceCodes) {
        return myvtgDao.getAllDataPackages(language, isdn, serviceTypes, listRegisteredServiceCodes);
    }

    public List<Subscriber> getSubscriberListByIsdn(String isdn) {
        return myvtgDao.getSubscriberListByIsdn(isdn);
    }

    public Boolean insertBatchSubscriber(String isdn, Long subId,
                                         List<SubscriberSyncInfoBean> subscriberSyncInfoBeans) {
        return insertBatchSubscriber(isdn, subId, subscriberSyncInfoBeans);
    }

    public Boolean updateBatchSubscriber(List<Subscriber> subscribers) {
        return myvtgDao.updateBatchSubscriber(subscribers);
    }

    public Subscriber getSubscriberById(Long subId, String subServiceCode) {
        return myvtgDao.getSubscriberById(subId, subServiceCode);
    }

    public Boolean insertBatchSubscriber(List<Subscriber> newItems) {
        return myvtgDao.insertBatchSubscriber(newItems);
    }
    //daibq bo sung start

    public List<Object> getHistoryTopup(String isdn, String comman) throws Exception {
        return myvtgDao.getHistoryTopup(isdn, comman);
    }

    public Map<String, List<Object>> getHistoryTopUpNew(String isdn, String command) throws Exception {
        return myvtgDao.getHistoryTopUpNew(isdn, command);
    }

    public Webservice getWS(String wsName) throws Exception {
        return myvtgDao.getWebserviceByName(wsName);
    }
//

    public List<StoresBean> wsGetNearestStores(BigDecimal longitude, BigDecimal latitude) throws Exception {
        return myvtgDao.getNearestStores(longitude, latitude, 10f, false);
    }

    public List<InfoUpdateVsApp> getLoginAfterUpdateVersion(String isdn, Long status, String version) throws Exception {
        return myvtgDao.getLoginAfterUpdateVersion(isdn, status, version);
    }

    public String getAppParamByTypAndName(String name) throws Exception {
        return myvtgDao.getAppParamByTypAndName(name);
    }

    public Long insert(Object entity) throws Exception {
        return myvtgDao.insert(entity);
    }

    public void update(Object entity) throws Exception {
        myvtgDao.update(entity);
    }

    public Long insertBusiness(Object entity) throws Exception {
        return dao.insert(entity);
    }

    public void updateBusiness(Object entity) throws Exception {
        dao.update(entity);
    }

    public Captcha getCaptcha(String isdn, String programCode) throws Exception {
        return dao.getCaptcha(isdn, programCode);
    }

    public int updateCaptcha(String isdn, String captchaCode, Integer expried, boolean check, String programCode) throws Exception {
        return dao.updatCaptcha(isdn, captchaCode, expried, check, programCode);
    }

    public int insertCaptcha(String isdn, String captchaCode, Integer expried, String programCode, Long totalErr) throws Exception {
        return dao.insertCaptcha(isdn, captchaCode, expried, programCode, totalErr);
    }

    //daibq end
    //phuonghc
    public Long getTotalRecordNewsCovid() {
        return myvtgDao.getTotalRecordNewsCovid();
    }

    public List<NewsCovidDto> getNewsCovid19List(Long from, Long to) {
        return myvtgDao.getNewsCovid19List(from, to);
    }

    public NotificationResult addNewsCovid19(NewsCovid19Entity newsCovid19Entity) {
        return myvtgDao.addNewsCovid19(newsCovid19Entity);
    }

    public int updateNewsCovid19(NewsCovid19Entity newsCovid19Entity) {
        return myvtgDao.updateNewsCovid19(newsCovid19Entity);
    }

    public int deleteNewsCovid19(NewsCovid19Entity newsCovid19Entity) {
        return myvtgDao.deleteNewsCovid19(newsCovid19Entity);
    }

    public NewsCovidDto getDetailNewsCovid19(NewsCovid19Entity newsCovid19Entity) {
        return myvtgDao.getDetailNewsCovid19(newsCovid19Entity);
    }

    public List<CodeFiredbaseConsole> getListCodeFiredBaseConsole() {
        return myvtgDao.getListCodeFiredBaseConsole();
    }

    //anle
    public NotificationResult addPaymentHistory(PaymentHistory paymentHistory) {
        return myvtgDao.addPaymentHistory(paymentHistory);
    }

    public Long getTotalRecordPaymentHistory(String isdn, String fromDate, String toDate) {
        return myvtgDao.getTotalRecordPaymentHistory(isdn, fromDate, toDate);
    }

    List<PaymentHistoryDTO> getPaymentHistoryListById(String isdn, Integer pageSize, Integer pageNum, String fromDate, String toDate) {
        return myvtgDao.getPaymentHistoryListById(isdn, pageSize, pageNum, fromDate, toDate);
    }

    public Object findPaymentHistoryByTid(Long tid) {
        return myvtgDao.findPaymentHistoryByTid(tid);
    }

    public int updatePaymentHistory(PaymentHistory paymentHistory) {
        return myvtgDao.updatePaymentHistory(paymentHistory);
    }

    public List<Object> getHistoryTopUpNewByDate(String isdn, String command, String fromDate, String toDate, int total, Integer pageSize, Integer pageNum) throws Exception {
        return myvtgDao.getHistoryTopUpNewByDate(isdn, command, fromDate, toDate, total, pageSize, pageNum);
    }

    public int getTotalHistoryTopUp(String isdn, String command, String fromDate, String toDate) throws Exception {
        return myvtgDao.getTotalHistoryTopUp(isdn, command, fromDate, toDate);
    }
	public List<ServiceAutoRenewBean> getCurrentUserAutoRenewAllServices(String language,
                                                       String listRegisteredServiceCodes) {
        return myvtgDao.getCurrentUserAutoRenewAllServices(language, listRegisteredServiceCodes);
    }
    public List<Object> getHistoryTopUpLucky(String isdn, String command, String fromDate, String toDate, int total, Integer pageSize, Integer pageNum, String type, String userId) throws Exception {
        return myvtgDao.getHistoryTopUpLucky(isdn, command, fromDate, toDate, total, pageSize, pageNum, type, userId);
    }

    public int getTotalHistoryTopUpLucky(String isdn, String command, String fromDate, String toDate, String userId) throws Exception {
        return myvtgDao.getTotalHistoryTopUpLucky(isdn, command, fromDate, toDate, userId);
    }
}
