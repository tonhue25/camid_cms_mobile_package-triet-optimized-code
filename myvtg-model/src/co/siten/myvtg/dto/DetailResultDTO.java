/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author buiquangdai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailResultDTO {

    //common
    @JsonProperty("advice")
    private String advice;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("isdn")
    private String isdn;
    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    //consultant
    private String productCode;
    private String productName;
    private Long productId;
    private String statusCustomer;
    private String programName;
    private String syntaxRegister;
    private String typeConsultant;
    //get ticktox - sabay  member
    private Long regMemId;
    private String insertDate;
    //add ticktox - sabay  member
    private String desStatus;

    //use convertJoson from SOAP
    //consultant
    @JsonProperty("productCode")
    private String productcode;
    @JsonProperty("productName")
    private String productname;
    @JsonProperty("productId")
    private Long productid;
    @JsonProperty("statusCustomer")
    private String statuscustomer;
    @JsonProperty("programName")
    private String programname;
    @JsonProperty("syntaxRegister")
    private String syntaxregister;
    @JsonProperty("typeConsultant")
    private String typeconsultant;
    //get ticktox - sabay  member
    @JsonProperty("regMemId")
    private Long regmemid;
    @JsonProperty("insertDate")
    private String insertdate;
    //add ticktox - sabay  member
    @JsonProperty("desStatus")
    private String desstatus;

    //SubInfoForPayment
    private String phoneNumber;
    @JsonProperty("totalAmount")
    private double transferCurrency;
    @JsonProperty("customerName")
    private String customerName;
    @JsonProperty("contractIdInfo")
    private String contractIdInfor;
    @JsonProperty("paid")
    private double paymentAmount;
    @JsonProperty("paymentCycle")
    private String paymentCycle;

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public String getContractIdInfo() {
        return contractIdInfor;
    }

    public void setContractIdInfor(String contractIdInfor) {
        this.contractIdInfor = contractIdInfor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getTransferCurrency() {
        return transferCurrency;
    }

    public void setTransferCurrency(double transferCurrency) {
        this.transferCurrency = transferCurrency;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getStatusCustomer() {
        return statusCustomer;
    }

    public void setStatusCustomer(String statusCustomer) {
        this.statusCustomer = statusCustomer;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getSyntaxRegister() {
        return syntaxRegister;
    }

    public void setSyntaxRegister(String syntaxRegister) {
        this.syntaxRegister = syntaxRegister;
    }

    public String getTypeConsultant() {
        return typeConsultant;
    }

    public void setTypeConsultant(String typeConsultant) {
        this.typeConsultant = typeConsultant;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Long getRegMemId() {
        return regMemId;
    }

    public void setRegMemId(Long regMemId) {
        this.regMemId = regMemId;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesStatus() {
        return desStatus;
    }

    public void setDesStatus(String desStatus) {
        this.desStatus = desStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
