/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 * NotificationAccountDtoV2
 *
 * @author phuonghc
 */
public class NotificationAccountDtoV2 {

    private Long accountNotifyId;
    private Integer isRead;

    public NotificationAccountDtoV2() {
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
