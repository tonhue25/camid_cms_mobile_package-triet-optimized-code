package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.ServiceGroupDao;
import co.siten.myvtg.model.myvtg.ServiceGroup;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Map;

@Service("ServiceGroupService")
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
@PropertySource(value = {"classpath:config.properties"})
public class ServiceGroupServiceImpl implements ServiceGroupService {

    private static final Logger logger = Logger.getLogger(RedeemServiceImpl.class);
    private static final String DES_FAIL = "ex.des.fai.";
    private static final String DES_SUC = "su.des.suc.";
    @Autowired
    ServiceGroupDao serviceGroupDao;
    @Autowired
    ResponseUtil responseUtil;


    @Override
    public BaseResponseBean getServiceGroups(RequestBean request, String language) {
        try {
            Map<String, Object> wsRequest = request.getWsRequest();
            String search = DataUtil.nullOrValueS(wsRequest.get("search"));
            String sortBy = DataUtil.nullOrValueS(wsRequest.get("sortBy"));
            String sortType = DataUtil.nullOrValueS(wsRequest.get("sortType"));
            Integer page = DataUtil.nullOrValueI(wsRequest.get("page"));
            Integer size = DataUtil.nullOrValueI(wsRequest.get("size"));
            if (DataUtil.isNullOrEmpty(page) || DataUtil.isNullOrEmpty(size)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.invalid.", language);
            }
            page = page - 1;

            if (page < 0) page = Constants.CUSTOMER_PAGE_START;
            if (size <= 0) size = Constants.CUSTOMER_RECORD_PER_PAGE;

            Map<String, Object> serviceGroupList = serviceGroupDao.getListServiceGroup(search, size, page, sortBy, sortType);
            if (DataUtil.isNullOrEmpty(serviceGroupList)) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }

            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, serviceGroupList);
        } catch (Exception ex) {
            logger.error("An error occurred while getServiceGroups ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean addOrUpdateServiceGroup(RequestBean request, String language, String username) {
        try {
            Date date = serviceGroupDao.getTime();
            Map<String, Object> wsRequest = request.getWsRequest();
            String code = DataUtil.nullOrValueS(wsRequest.get("code"));
            String name = DataUtil.nullOrValueS(wsRequest.get("name"));
            String nameKh = DataUtil.nullOrValueS(wsRequest.get("nameKh"));
            String shortDes = DataUtil.nullOrValueS(wsRequest.get("shortDes"));
            String shortDesKh = DataUtil.nullOrValueS(wsRequest.get("shortDesKh"));
            String icon = DataUtil.nullOrValueS(wsRequest.get("icon"));
            String color = DataUtil.nullOrValueS(wsRequest.get("color"));
            Integer serviceGroupType = DataUtil.nullOrValueI(wsRequest.get("serviceGroupType"));
            Integer serviceGroupOrder = DataUtil.nullOrValueI(wsRequest.get("serviceGroupOrder"));
            Long id = DataUtil.nullOrValueL(wsRequest.get("id"));
            if (DataUtil.isNullOrEmpty(code) || DataUtil.isNullOrEmpty(name) || DataUtil.isNullOrEmpty(nameKh)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.invalid.", language);
            }
            if (serviceGroupDao.isExistedServiceGroup(code)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.resource.already.exist.", language);
            }
            ServiceGroup serviceGroup = new ServiceGroup();
            if (id != null) {
                serviceGroup = DataUtil.isNullObject(serviceGroupDao.findById(id))
                        ? new ServiceGroup() : serviceGroupDao.findById(id);
            }
            int status = DataUtil.isNullObject(serviceGroup.getStatus()) ? 1 : serviceGroup.getStatus();
            serviceGroup.setCode(code);
            serviceGroup.setName(name);
            serviceGroup.setNameKh(nameKh);
            serviceGroup.setShortDes(shortDes);
            serviceGroup.setShortDesKh(shortDesKh);
            serviceGroup.setIcon(icon);
            serviceGroup.setColor(color);
            serviceGroup.setServiceGroupType(serviceGroupType);
            serviceGroup.setServiceGroupOrder(serviceGroupOrder);
            serviceGroup.setStatus(status);
            serviceGroup.setCreatedTime(date);

            serviceGroupDao.insert(serviceGroup);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.create.update.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while addServiceGroup ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean changeActive(Long id, String language, String username) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        try {
            if (DataUtil.isNullOrEmpty(id)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.invalid.", language);
            }
            ServiceGroup serviceGroup = serviceGroupDao.findById(id);
            if (serviceGroup == null) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            if (serviceGroup.getStatus() == Constants.ACTIVE) {
                serviceGroup.setStatus(Constants.INACTIVE);
            } else {
                serviceGroup.setStatus(Constants.ACTIVE);
            }
            serviceGroup.setLastUpdatedBy(username);
            serviceGroup.setLastUpdatedTime(date);

            serviceGroupDao.insert(serviceGroup);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.change.active.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while changeActiveGroup ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

    @Override
    public BaseResponseBean delete(Long id, String language, String username) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        try {
            if (DataUtil.isNullOrEmpty(id)) {
                return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "ex.common.parameter.invalid.", language);
            }
            ServiceGroup serviceGroup = serviceGroupDao.findById(id);
            if (serviceGroup == null) {
                return responseUtil.responseBean(Constants.NOT_FOUND_DATA, DES_FAIL, "ex.common.request.resource.notfound.", language);
            }
            serviceGroup.setStatus(Constants.DELETED);
            serviceGroup.setLastUpdatedBy(username);
            serviceGroup.setLastUpdatedTime(date);

            serviceGroupDao.insert(serviceGroup);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.delete.", language);
        } catch (Exception ex) {
            logger.error("An error occurred while deleteServiceGroup ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }

}

