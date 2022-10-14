
package co.siten.myvtg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.bean.ReportNotiCustomer;
import co.siten.myvtg.model.myvtg.NotifyHis;

@Repository("NotifyHisDao")
public interface NotifyHisDao extends JpaRepository<NotifyHis, Long> {

    @Query("select new co.siten.myvtg.bean.ReportNotiCustomer(s.code, n.data, n.fcmResponse,n.insertTime) from NotifyHis n,Notification s"
            + " where n.isdn = :isdn" + " AND s.id = n.notificationId"
            + " and (n.insertTime >= TO_DATE(:fromDate, 'yyyy-MM-dd') OR :fromDate is null) "
            + " and ((n.insertTime < (TO_DATE(:toDate, 'yyyy-MM-dd')+1)) OR :toDate is null) "
            + " ORDER BY s.code DESC")
    public List<ReportNotiCustomer> reportNotiCustomer(@Param("isdn") String isdn, @Param("fromDate") String fromDate,
                                                       @Param("toDate") String toDate);
}
