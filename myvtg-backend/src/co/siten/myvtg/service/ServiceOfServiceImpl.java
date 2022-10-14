package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.ServiceDao;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

@org.springframework.stereotype.Service("ServiceOfService")
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
@PropertySource(value = {"classpath:config.properties"})
public class ServiceOfServiceImpl implements ServiceOfService {

    private static final Logger logger = Logger.getLogger(RedeemServiceImpl.class);
    private static final String DES_FAIL = "ex.des.fai.";
    private static final String DES_SUC = "su.des.suc.";
    @Autowired
    ServiceDao serviceDao;
    @Autowired
    ResponseUtil responseUtil;
    @Autowired
    ConfigUtils configUtils;


    @Override
    public BaseResponseBean getServices(RequestBean request, String language) {
        try {
            Map<String, Object> wsRequest = request.getWsRequest();
            String search = DataUtil.nullOrValueS(wsRequest.get("search"));
            String sortBy = DataUtil.nullOrValueS(wsRequest.get("sortBy"));
            String sortType = DataUtil.nullOrValueS(wsRequest.get("sortType"));
            Integer page = DataUtil.nullOrValueI(wsRequest.get("page"));
            Integer size = DataUtil.nullOrValueI(wsRequest.get("size"));
            Long serviceGroupId = DataUtil.nullOrValueL(wsRequest.get("serviceGroupId"));

            if (DataUtil.isNullOrEmpty(page) || DataUtil.isNullOrEmpty(size)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            page = page - 1;
            if (page < 0) page = Constants.CUSTOMER_PAGE_START;
            if (size <= 0) size = Constants.CUSTOMER_RECORD_PER_PAGE;

            Map<String, Object> serviceList = serviceDao.getListService(search, size, page, sortBy, sortType, serviceGroupId);
            if (DataUtil.isNullOrEmpty(serviceList)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, serviceList);
        } catch (Exception ex) {
            logger.error("An error occurred while getServices ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean addOrUpdateService(RequestBean requestBean, String language, String username) {

        try {
            Date date = serviceDao.getTime();
            Map<String, Object> wsRequest = requestBean.getWsRequest();
            String code = DataUtil.nullOrValueS(wsRequest.get("code"));
            String name = DataUtil.nullOrValueS(wsRequest.get("name"));
            String nameKh = DataUtil.nullOrValueS(wsRequest.get("nameKh"));

            String shortDes = DataUtil.nullOrValueS(wsRequest.get("shortDes"));
            String fullDes = DataUtil.nullOrValueS(wsRequest.get("fullDes"));
            String shortDesKh = DataUtil.nullOrValueS(wsRequest.get("shortDesKh"));
            String fullDesKh = DataUtil.nullOrValueS(wsRequest.get("fullDesKh"));
            String iconUrl = DataUtil.nullOrValueS(wsRequest.get("iconUrl"));
            String imgDesUrl = DataUtil.nullOrValueS(wsRequest.get("imgDesUrl"));
            String webLink = DataUtil.nullOrValueS(wsRequest.get("webLink"));
            String servicePage = DataUtil.nullOrValueS(wsRequest.get("servicePage"));
            String validity = DataUtil.nullOrValueS(wsRequest.get("validity"));
            String formatCode = DataUtil.nullOrValueS(wsRequest.get("formatCode"));
            String unit = DataUtil.nullOrValueS(wsRequest.get("unit"));
            Integer isMultPlan = DataUtil.nullOrValueI(wsRequest.get("isMultPlan"));
            Integer serviceOrder = DataUtil.nullOrValueI(wsRequest.get("serviceOrder"));
            Integer shortCode = DataUtil.nullOrValueI(wsRequest.get("shortCode"));
            Integer actionType = DataUtil.nullOrValueI(wsRequest.get("actionType"));
            Integer serviceType = DataUtil.nullOrValueI(wsRequest.get("serviceType"));
            Integer showSubService = DataUtil.nullOrValueI(wsRequest.get("showSubService"));
            Integer recommend = DataUtil.nullOrValueI(wsRequest.get("recommend"));
            Long serviceGroupId = DataUtil.nullOrValueL(wsRequest.get("serviceGroupId"));
            Long id = DataUtil.nullOrValueL(wsRequest.get("id"));
            BigDecimal price = DataUtil.nullOrValueB(wsRequest.get("price"));

            if (DataUtil.isNullOrEmpty(code) || DataUtil.isNullOrEmpty(name) || DataUtil.isNullOrEmpty(nameKh)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            if (serviceDao.isExistedService((code))) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.resource.already.exist.", language);
            }
            Service service = new Service();
            if(id != null){
                service = DataUtil.isNullObject(serviceDao.findById(id))
                        ? new Service() : serviceDao.findById(id);
            }

            int status = DataUtil.isNullObject(service.getStatus()) ? 1 : service.getStatus();
            service.setCode(code);
            service.setName(name);
            service.setNameKh(nameKh);
            service.setServiceGroupId(serviceGroupId);
            service.setShortDes(shortDes);
            service.setShortDesKh(shortDesKh);
            service.setFullDes(fullDes);
            service.setFullDesKh(fullDesKh);
            service.setIconUrl(iconUrl);
            service.setImgDesUrl(imgDesUrl);
            service.setWebLink(webLink);
            service.setShortCode(shortCode);
            service.setActionType(actionType);
            service.setServiceType(serviceType);
            service.setPrice(price);
            service.setIsMultPlan(isMultPlan);
            service.setServicePage(servicePage);
            service.setServiceOrder(serviceOrder);
            service.setValidity(validity);
            service.setFormatCode(formatCode);
            service.setUnit(unit);
            service.setShowSubService(showSubService);
            service.setRecommend(recommend);
            service.setStatus(status);
            service.setCreatedBy(username);
            service.setCreatedTime(date);

            serviceDao.insert(service);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.create.update.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while addService ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean changeActive(Long id, String language, String username) {
        try {
            Date date = serviceDao.getTime();
            if (DataUtil.isNullOrEmpty(id)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            Service service = serviceDao.findById(id);
            if (service == null) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            if (service.getStatus() == Constants.ACTIVE) {
                service.setStatus(Constants.INACTIVE);
            } else {
                service.setStatus(Constants.ACTIVE);
            }
            service.setLastUpdatedBy(username);
            service.setLastUpdatedTime(date);

            serviceDao.insert(service);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.change.active.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while changeActiveService ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean deleteService(Long id, String language, String username) {

        try {
            Date date = serviceDao.getTime();
            if (DataUtil.isNullOrEmpty(id)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.error.", language);
            }
            Service service = serviceDao.findById(id);
            if (service == null) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            service.setStatus(Constants.DELETED);
            service.setLastUpdatedBy(username);
            service.setLastUpdatedTime(date);

            serviceDao.insert(service);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.delete.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteService ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

}

