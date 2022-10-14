package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.StaffDao;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static co.siten.myvtg.controller.BaseController.DES_FAIL;

@Service
public class StaffServiceImpl implements StaffService{

    @Autowired
    private StaffDao staffDao;

    @Autowired
    ResponseUtil responseUtil;

    private static final Logger logger = Logger.getLogger(SubServiceImpl.class);

    private static final String DES_SUC = "su.des.suc.";

    @Override
    public BaseResponseBean getStaffs(RequestBean request, String language) {
        try {
            Map<String, Object> wsRequest = request.getWsRequest();
            String search = DataUtil.nullOrValueS(wsRequest.get("search"));
            String sortBy = DataUtil.nullOrValueS(wsRequest.get("sortBy"));
            String sortType = DataUtil.nullOrValueS(wsRequest.get("sortType"));
            Integer page = DataUtil.nullOrValueI(wsRequest.get("page"));
            Integer size = DataUtil.nullOrValueI(wsRequest.get("size"));
            Long serviceId = DataUtil.nullOrValueL(wsRequest.get("serviceId"));
            page = page - 1;
            if (page < 0) page = Constants.CUSTOMER_PAGE_START;
            if (size <= 0) size = Constants.CUSTOMER_RECORD_PER_PAGE;
            Map<String, Object> subServiceList = staffDao.getListStaff(search, size, page, sortBy, sortType,serviceId);
            return responseUtil.responseBean(Constants.SUCCESS, DES_SUC, "su.common.resource.get.", language, subServiceList);
        } catch (Exception ex) {
            logger.error("An error occurred while getSubServices ", ex);
            return responseUtil.responseBean(Constants.ERROR_SYSTEM, DES_FAIL, "ex.common.system.error.", language);
        }
    }
}
