/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 * NotificationDtoV2Expand
 *
 * @author phuonghc
 */
public class NotificationDtoV2Expand extends NotificationDtoV2 {

    private Long accountNotifyId;
    private Integer isRead;

    public NotificationDtoV2Expand() {
        super();
    }

    public Long getAccountNotifyId() {
        return accountNotifyId;
    }

    public void setAccountNotifyId(Long accountNotifyId) {
        this.accountNotifyId = accountNotifyId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

}
