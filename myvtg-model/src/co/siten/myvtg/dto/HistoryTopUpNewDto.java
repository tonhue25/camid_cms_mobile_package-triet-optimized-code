/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 * NewsCovidDto
 *
 * @author phuonghc
 */

public class HistoryTopUpNewDto {

    private String isdn;
    private Double amount;
    private String refill_date;
    private String pinCode;


    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn1) {
        isdn = isdn1;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount1) {
        amount = Math.floor(amount1 * 100) / 100;
    }

    public String getRefill_date() {
        return refill_date;
    }

    public void setRefill_date(String refill_date1) {
        refill_date = refill_date1;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
