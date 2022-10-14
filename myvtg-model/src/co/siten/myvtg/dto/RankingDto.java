/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 *
 * @author namdh1
 */
public class RankingDto {

    String rankId;
    String rankName;
    String minPoint;
    String maxPoint;
    String arpu;
    String minAge;
    String usedTimeDescription;
    String updatedTime;
    String updatedBy;
    String description;

    public String getRankId() {
        return rankId;
    }

    public void setRankId(String rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(String minPoint) {
        this.minPoint = minPoint;
    }

    public String getMaxPoint() {
        return maxPoint;
    }

    public void setMaxPoint(String maxPoint) {
        this.maxPoint = maxPoint;
    }

    public String getArpu() {
        return arpu;
    }

    public void setArpu(String arpu) {
        this.arpu = arpu;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

  
    public String getUsedTimeDescription() {
        return usedTimeDescription;
    }

    public void setUsedTimeDescription(String usedTimeDescription) {
        this.usedTimeDescription = usedTimeDescription;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Ranking{" + "rankId=" + rankId + ", rankName=" + rankName + ", minPoint=" + minPoint + ", maxPoint=" + maxPoint + ", arpu=" + arpu + ", minAge=" + minAge + ", usedTimeDescription=" + usedTimeDescription + ", updatedTime=" + updatedTime + ", updatedBy=" + updatedBy + ", description=" + description + '}';
    }

    public RankingDto() {
    }

}
