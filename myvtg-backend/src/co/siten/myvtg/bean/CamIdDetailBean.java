/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import co.siten.myvtg.util.DataUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kiennt
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CamIdDetailBean {
    
    private String callingNumber;
    private String calledNumber;
    private Date startTime;
    private long duration;
    private double basicBalance;
    private double promBalance;
    private double chargeAmount;
    @JsonIgnore
    private String startTimeStr;

    public String getCallingNumber() {
        return callingNumber;
    }

    public void setCallingNumber(String callingNumber) {
        this.callingNumber = callingNumber;
    }

    public String getCalledNumber() {
        return calledNumber;
    }

    public void setCalledNumber(String calledNumber) {
        this.calledNumber = calledNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getBasicBalance() {
        return basicBalance;
    }

    public void setBasicBalance(double basicBalance) {
        this.basicBalance = basicBalance;
    }

    public double getPromBalance() {
        return promBalance;
    }

    public void setPromBalance(double fromBalance) {
        this.promBalance = fromBalance;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getStartTimeStr() {
        if (!DataUtil.isNullObject(startTime)) {
            return startTimeStr = new SimpleDateFormat("dd/MM/yyyy").format(startTime);
        }
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }
}