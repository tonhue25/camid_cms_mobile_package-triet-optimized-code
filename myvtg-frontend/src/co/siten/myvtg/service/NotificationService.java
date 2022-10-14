package co.siten.myvtg.service;

import co.siten.apigw.dao.NotificationDao;
import co.siten.myvtg.bean.NotificationExportDataBean;
import co.siten.myvtg.bean.ReportNotiCustomer;
import co.siten.myvtg.bean.ReportNotificationCampaign;
import co.siten.myvtg.bean.ServiceGroupTypeBean;
import co.siten.myvtg.dao.AppParamDao;
import co.siten.myvtg.dao.NotifyHisDao;
import co.siten.apigw.dao.SubPushNotifyDao;
import co.siten.myvtg.model.apigw.Notification;
import co.siten.myvtg.model.apigw.SubPushNotify;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Service("INotificationService")
public class NotificationService implements INotificationService {

    @Autowired
    NotificationDao dao;

    @Autowired
    AppParamDao appParamDao;

    @Autowired
    SubPushNotifyDao subPushNotifyDao;

    @Autowired
    private NotifyHisDao notiHisDao;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NotificationService.class);

    public Notification findById(Long id) {
        Notification data = dao.findOneById(id);
        return data;
    }

    @Override
    public Long save(Notification data) {
        Notification savedData = dao.save(data);
        return savedData.getId();
    }

    @Override
    public void delete(Notification data) {
        dao.delete(data);
    }

    @Override
    public Page<Notification> findPaginated(int page, int size, String sortBy, int sortType, String effectiveTime,
            Integer status, String title) {
        Sort.Order order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy).ignoreCase();

        if (sortBy.equals("startTime") || sortBy.equals("endTime")) {
            order = new Sort.Order(sortType == 0 ? Direction.ASC : Direction.DESC, sortBy);
        }

        return dao.findByEffectiveTime(effectiveTime, status, title, new PageRequest(page, size, new Sort(order)));
    }

    @Override
    public List<Notification> getList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void Approve(boolean forAll, List<String> ids, boolean active) {
        // TODO Auto-generated method stub
        List<Notification> data;
        List<Long> lsId = new ArrayList<>();
        for (String item : ids) {
            lsId.add(Long.parseLong(item));
        }

        if (forAll) {
            data = dao.findByStatus(1);
        } else {
            data = dao.findByIdInAndStatus(lsId, active ? 0 : 1);
        }

        for (Notification d : data) {
            d.setStatus(active ? 1 : 0);
            dao.save(d);
        }
    }

    @Override
    public List<NotificationExportDataBean> getDataExport(List<Long> lsId) {
        // TODO Auto-generated method stub
        if (lsId.size() == 0) {
            return null;
        }
        return dao.getNotifiClickData(lsId);
    }

    @Override
    public ServiceGroupTypeBean getNotificationType() {
        ServiceGroupTypeBean s = new ServiceGroupTypeBean();
        s.setServiceGroupType(appParamDao.findByName("notification_types").get(0).getValue());
        return s;
    }

    @Override
    public List<ReportNotificationCampaign> reportNotiForCamp(String code, String fromDate, String toDate) {
        return dao.reportNotiCamp(code, fromDate, toDate);
    }

    @Override
    public List<ReportNotiCustomer> reportNotiCus(String isdn, String fromDate, String toDate) {
        return notiHisDao.reportNotiCustomer(isdn, fromDate, toDate);
    }

    @Override
    public Boolean checkIsExistCode(String code, Long id) {

        return !CommonUtil.isEmpty(dao.findByCode(code, id));
    }

    @Override
    public void pushNo(String[] isdn, Long idNo) {
        logger.error("Start pushNo of NotificationService ");
        try {

            for (String str : isdn) {
                SubPushNotify entity = new SubPushNotify();
                entity.setIsdn(DataUtil.fomatIsdn(str));
                entity.setCreateDate(new Date());
                entity.setNotifyId(idNo);
                entity.setStatus(1L);
                subPushNotifyDao.save(entity);
            }
        } catch (Exception e) {
            logger.error("Exception :", e);
        }

    }

    @Override
    public List<Notification> getListNoForPush() {
        logger.error("Start getListNoForPush of NotificationService ");
        return dao.getListNoForPush();
    }

}
