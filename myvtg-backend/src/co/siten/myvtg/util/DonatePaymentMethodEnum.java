/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

/**
 * public enum DonatePaymentMethodEnum {
 *
 * @author partner7
 */
public enum DonatePaymentMethodEnum {
    UNIT_METFONE("UNIT_METFONE", "USD"),
    UNIT_POINT("UNIT_POINT", "POINT");

    private String paymentMethod;
    private String unit;

    private DonatePaymentMethodEnum(String paymentMethod, String unit) {
        this.paymentMethod = paymentMethod;
        this.unit = unit;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
