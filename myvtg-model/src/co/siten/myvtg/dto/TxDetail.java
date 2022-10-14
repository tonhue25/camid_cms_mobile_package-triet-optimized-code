/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author daibq
 * @des Chi tiet hoa don emoney
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TxDetail {

    private Long transDetailId;
    private String txPaymentTokenId;
    private String transDetailCode;
    private Long paymentType;
    private Long status;
    private String merchantCode;
    private String merchantServiceType;
    private Double transAmount;
    private String customerPhoneNumber;
    private String transDescription;
    private String transTime;
    private String paidTid;
    private String refId;
    private String currencyCode;
    private String acceptPaymentCurrencyCode;
    private String paymentQrCode;

//    private Long transDetailId;
//    private String txPaymentTokenId;
//    private String transDetailCode;
//    private Long paymentType;
//    private Long status;
//    private String merchantCode;
//    private String merchantServiceType;
//    private Double transAmount;
//    private String customerPhoneNumber;
//    private String transDescription;
//    private String transTime;
//    private String paidTid;
//    private String refId;
//    private String currencyCode;
//    private String acceptPaymentCurrencyCode;
//    private String paymentQrCode;
//    private Long merchantId;
//    private Long merchantServiceId;
//    private Long emServiceProvideId;
//    private Long emServiceId;
//    private String emServiceCode;
//    private Double transAmountConvert;
//    private Double currencyExchangeRate;
//    private Long discountType;
//    private Double discountAmount;
//    private Double commissionAmount;
//    private Double transFee;
//    private Double transTotalAmount;
//    private Double transTotalAmountConvert;
//    private String description;
//    private Long paidTime;
//    private String paidEmoneyAccount;
//    private Double paidFee;
//    private String paidChanel;
//    private Double paidAmount;
//    private String paidCurrencyCode;
//    private String paidTip;
//    private Double paidTotalAmount;
//    private String customerName;
//    private Long emCoporationId;
//    private String emCoporationMsisdn;
//    private String statusDescription;
//    private String lastBalance;
    public Long getTransDetailId() {
        return transDetailId;
    }

    public void setTransDetailId(Long transDetailId) {
        this.transDetailId = transDetailId;
    }

    public String getTxPaymentTokenId() {
        return txPaymentTokenId;
    }

    public void setTxPaymentTokenId(String txPaymentTokenId) {
        this.txPaymentTokenId = txPaymentTokenId;
    }

    public String getTransDetailCode() {
        return transDetailCode;
    }

    public void setTransDetailCode(String transDetailCode) {
        this.transDetailCode = transDetailCode;
    }

    public Long getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Long paymentType) {
        this.paymentType = paymentType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantServiceType() {
        return merchantServiceType;
    }

    public void setMerchantServiceType(String merchantServiceType) {
        this.merchantServiceType = merchantServiceType;
    }

    public Double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Double transAmount) {
        this.transAmount = transAmount;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getTransDescription() {
        return transDescription;
    }

    public void setTransDescription(String transDescription) {
        this.transDescription = transDescription;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getPaidTid() {
        return paidTid;
    }

    public void setPaidTid(String paidTid) {
        this.paidTid = paidTid;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getAcceptPaymentCurrencyCode() {
        return acceptPaymentCurrencyCode;
    }

    public void setAcceptPaymentCurrencyCode(String acceptPaymentCurrencyCode) {
        this.acceptPaymentCurrencyCode = acceptPaymentCurrencyCode;
    }

    public String getPaymentQrCode() {
        return paymentQrCode;
    }

    public void setPaymentQrCode(String paymentQrCode) {
        this.paymentQrCode = paymentQrCode;
    }
}
