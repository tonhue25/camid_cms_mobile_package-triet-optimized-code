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
public class AccountRankDTO {

    Long accountRankId;
    Long vtAccId;
    Long rankId;
    String rankName;
    String startDate;
    String endDate;
    Double pointValue;
    String pointExpireDate;

    public Long getAccountRankId() {
        return accountRankId;
    }

    public void setAccountRankId(Long accountRankId) {
        this.accountRankId = accountRankId;
    }

    public Long getVtAccId() {
        return vtAccId;
    }

    public void setVtAccId(Long vtAccId) {
        this.vtAccId = vtAccId;
    }

    public Long getRankId() {
        return rankId;
    }

    public void setRankId(Long rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Double getPointValue() {
        return pointValue;
    }

    public void setPointValue(Double pointValue) {
        this.pointValue = pointValue;
    }

    public String getPointExpireDate() {
        return pointExpireDate;
    }

    public void setPointExpireDate(String pointExpireDate) {
        this.pointExpireDate = pointExpireDate;
    }

}
