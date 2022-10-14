/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import co.siten.myvtg.dto.InfoMsgDTO;

/**
 *
 * @author namdh1
 */
public class ForceUpdateVersionBean {

    String forceUpgrade;
    String recommendUpgrade;
    String updateInfo;
    String currVersion;
    String newVersion;
    private InfoMsgDTO infoMsg;
    

    public String getForceUpgrade() {
        return forceUpgrade;
    }

    public void setForceUpgrade(String forceUpgrade) {
        this.forceUpgrade = forceUpgrade;
    }

    public String getRecommendUpgrade() {
        return recommendUpgrade;
    }

    public void setRecommendUpgrade(String recommendUpgrade) {
        this.recommendUpgrade = recommendUpgrade;
    }

    public String getUpdateInfo() {
        return updateInfo;
    }

    public void setUpdateInfo(String updateInfo) {
        this.updateInfo = updateInfo;
    }

    public String getCurrVersion() {
        return currVersion;
    }

    public void setCurrVersion(String currVersion) {
        this.currVersion = currVersion;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    public InfoMsgDTO getInfoMsg() {
        return infoMsg;
    }

    public void setInfoMsg(InfoMsgDTO infoMsg) {
        this.infoMsg = infoMsg;
    }

}
