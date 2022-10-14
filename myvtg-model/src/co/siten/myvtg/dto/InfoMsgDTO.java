/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 *
 * @author buiquangdai
 */
public class InfoMsgDTO {

    private String infoUpdateMsg;
    private Long isShowInfoUpdate;
    private String infoGameMsg;
    private Long isShowInfoGameMsg;
    private Long isShowInfoCovid19;

    public Long getIsShowInfoCovid19() {
        return isShowInfoCovid19;
    }

    public void setIsShowInfoCovid19(Long isShowInfoCovid19) {
        this.isShowInfoCovid19 = isShowInfoCovid19;
    }

    public String getInfoUpdateMsg() {
        return infoUpdateMsg;
    }

    public void setInfoUpdateMsg(String infoUpdateMsg) {
        this.infoUpdateMsg = infoUpdateMsg;
    }

    public Long getIsShowInfoUpdate() {
        return isShowInfoUpdate;
    }

    public void setIsShowInfoUpdate(Long isShowInfoUpdate) {
        this.isShowInfoUpdate = isShowInfoUpdate;
    }

    public String getInfoGameMsg() {
        return infoGameMsg;
    }

    public void setInfoGameMsg(String infoGameMsg) {
        this.infoGameMsg = infoGameMsg;
    }

    public Long getIsShowInfoGameMsg() {
        return isShowInfoGameMsg;
    }

    public void setIsShowInfoGameMsg(Long isShowInfoGameMsg) {
        this.isShowInfoGameMsg = isShowInfoGameMsg;
    }

}
