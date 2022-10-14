package co.siten.myvtg.bean;

import java.util.Date;

public class NotificationExportDataBean {
    private String isdn;
    private String notifiTitle;
    private String notifiMessage;
    private String action;
    private String actionValue;
    private Long totalClick;
    private String service;
    private Date date;
    private Long id;

    public NotificationExportDataBean(String isdn, String notifiTitle, String notifiMessage, Long totalClick,
                                      String service, Date date, Long id) {
        super();
        this.isdn = isdn;
        this.notifiTitle = notifiTitle;
        this.notifiMessage = notifiMessage;
        this.totalClick = totalClick;
        this.service = service;
        this.date = date;
        this.id = id;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getNotifiTitle() {
        return notifiTitle;
    }

    public void setNotifiTitle(String notifiTitle) {
        this.notifiTitle = notifiTitle;
    }

    public String getNotifiMessage() {
        return notifiMessage;
    }

    public void setNotifiMessage(String notifiMessage) {
        this.notifiMessage = notifiMessage;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public Long getTotalClick() {
        return totalClick;
    }

    public void setTotalClick(Long totalClick) {
        this.totalClick = totalClick;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
