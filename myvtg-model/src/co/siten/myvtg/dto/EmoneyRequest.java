/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author daibq
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmoneyRequest {

    private Long paymentType;
    private String content;
    private Double transAmount;
    private String refId;
    private String currency;
    private String customerPhoneNumber;
    private String ccyAcceptPayment;
    private String txPaymentTokenId;
    private String paymentContent;

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCcyAcceptPayment() {
        return ccyAcceptPayment;
    }

    public void setCcyAcceptPayment(String ccyAcceptPayment) {
        this.ccyAcceptPayment = ccyAcceptPayment;
    }

    public String getTxPaymentTokenId() {
        return txPaymentTokenId;
    }

    public void setTxPaymentTokenId(String txPaymentTokenId) {
        this.txPaymentTokenId = txPaymentTokenId;
    }

    public String getPaymentContent() {
        return paymentContent;
    }

    public void setPaymentContent(String paymentContent) {
        this.paymentContent = paymentContent;
    }

}
