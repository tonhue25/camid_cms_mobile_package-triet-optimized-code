package co.siten.myvtg.service;

import co.siten.myvtg.bean.NotificationExportDataBean;
import co.siten.myvtg.bean.ReportNotiCustomer;
import co.siten.myvtg.bean.ReportNotificationCampaign;
import co.siten.myvtg.bean.ServiceGroupTypeBean;
import co.siten.myvtg.model.apigw.Notification;
import org.springframework.data.domain.Page;

import java.util.List;

public interface INotificationService {

    public Notification findById(Long id);

    public Long save(Notification data);

    public void delete(Notification data);

    public List<Notification> getList();

    public Page<Notification> findPaginated(int page, int size, String sortBy, int sortType, String effectiveTime,
            Integer status, String title);

    public void Approve(boolean forAll, List<String> ids, boolean active);

    public List<NotificationExportDataBean> getDataExport(List<Long> lsId);

    public ServiceGroupTypeBean getNotificationType();

    public List<ReportNotificationCampaign> reportNotiForCamp(String code, String fromDate, String toDate);

    public List<ReportNotiCustomer> reportNotiCus(String isdn, String fromDate, String toDate);

    public Boolean checkIsExistCode(String code, Long id);

    public void pushNo(String[] isdn, Long idNo);

    public List<Notification> getListNoForPush();

}
