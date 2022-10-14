/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import java.util.List;

/**
 * NotificationBeanV2
 *
 * @author phuonghc
 */
public class NotificationBeanV2 {

    private Long totalNews;
    private Object notificationList;

    public NotificationBeanV2() {
    }

    public Long getTotalNews() {
        return totalNews;
    }

    public void setTotalNews(Long totalNews) {
        this.totalNews = totalNews;
    }

    public Object getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(Object notificationList) {
        this.notificationList = notificationList;
    }

}
