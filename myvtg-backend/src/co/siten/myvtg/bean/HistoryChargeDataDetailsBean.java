/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import co.siten.myvtg.util.DataUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.text.SimpleDateFormat;

/**
 *
 * @author kiennt88
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryChargeDataDetailsBean {

    private String date;
    private long total;
    private double money;
    private String unit;

    @JsonIgnore
    private String startTimeStr;

    public String getDate() {
        if (!DataUtil.isNullObject(startTimeStr)) {
            return date = startTimeStr;
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }
}
