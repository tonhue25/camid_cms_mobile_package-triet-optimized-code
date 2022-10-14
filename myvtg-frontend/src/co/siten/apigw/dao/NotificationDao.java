package co.siten.apigw.dao;

import co.siten.myvtg.bean.NotificationExportDataBean;
import co.siten.myvtg.bean.ReportNotificationCampaign;
import co.siten.myvtg.model.apigw.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.domain.Pageable;

@Repository("NotificationDao")
public interface NotificationDao extends JpaRepository<Notification, Long> {

    public Notification findOneById(long id);

    @Query("select s from Notification s where ( :effectiveTime is null OR trunc(s.startTime) <= TO_DATE(:effectiveTime, 'dd/mm/yyyy'))"
            + " and (:effectiveTime is null OR trunc(s.endTime) >= TO_DATE(:effectiveTime, 'dd/mm/yyyy'))"
            + " AND ((:status < 0 OR :status > 3) OR s.status = :status or :status is null)"
            + " AND ( lower(s.title) like lower('%' || :title || '%') OR :title is null)"
            + " AND s.notificationProgramType in (1,2)")
    public Page<Notification> findByEffectiveTime(@Param("effectiveTime") String effectiveTime,
            @Param("status") Integer status, @Param("title") String title, Pageable pageRequest);

    public long countById(Long id);

    public List<Notification> findByStatus(int status);

    public List<Notification> findByIdInAndStatus(List<Long> ids, int status);

    @Query("select new co.siten.myvtg.bean.NotificationExportDataBean(n.isdn,nt.title,nt.message, count(n.isdn),  nt.serviceName,nt.startTime,n.notificationId) from NotificationLogClick n, Notification nt"
            + " where n.notificationId in (:lsId) AND nt.id = n.notificationId GROUP BY n.isdn,nt.title,nt.message,nt.serviceName,nt.startTime,n.notificationId")
    public List<NotificationExportDataBean> getNotifiClickData(@Param("lsId") List<Long> lsId);

    @Query("select new co.siten.myvtg.bean.ReportNotificationCampaign(s.code,count(*) , count(case when n.pushErrorCode = 1 then 1 else null end )) from Notification s, NotifyHis n"
            + " where s.id = n.notificationId"
            + " and (n.insertTime >= TO_DATE(:fromDate, 'yyyy-MM-dd') OR :fromDate is null) "
            + " and ((n.insertTime < (TO_DATE(:toDate, 'yyyy-MM-dd') + 1)) OR :toDate is null) "
            + " and (s.code = :code or :code is null)" + " group by s.code" + " ORDER BY s.code DESC")
    public List<ReportNotificationCampaign> reportNotiCamp(@Param("code") String code,
            @Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query("select s from Notification s where s.code = :code and (s.id != :id or :id is null)")
    public List<Notification> findByCode(@Param("code") String code, @Param("id") Long id);

    @Query("select s from Notification s where notificationProgramType in (1,2)")
    public List<Notification> getListNoForPush();
}
