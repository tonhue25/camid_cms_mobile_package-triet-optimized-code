package co.siten.apigw.dao;

import co.siten.myvtg.model.apigw.Notification;
import co.siten.myvtg.model.apigw.SubPushNotify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("SubPushNotifyDao")
public interface SubPushNotifyDao extends JpaRepository<SubPushNotify, Long> {

    public SubPushNotify findOneById(long id);

    public Notification save(Notification data);

}
