/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

/**
 *
 * @author buiquangdai
 */
@JsonInclude(Include.NON_NULL)
public class RqLooyaltyRouting {

    //common
    private String productName;
    private String idNo;
    private String address;
    private String dob;
    private String vtAccId;
    private String custId;
    private String productId;
    //createViettelAccountExt
    private String custName;
    private String customerId;
    //updateCustomerExt

    private String customerName;
    private String gender;
    private String token;
    //adjustAccountPoint
    private String isdn;
    private String transId;
    private String pointAmount;
    private String transTypeId;
    private String pointId;
    //inactiveAccount

    //referProductBccsCustomer
    private List<AccountDTO> mainAccountDTO;
    private List<AccountDTO> listReferenceAccount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getVtAccId() {
        return vtAccId;
    }

    public void setVtAccId(String vtAccId) {
        this.vtAccId = vtAccId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(String pointAmount) {
        this.pointAmount = pointAmount;
    }

    public String getTransTypeId() {
        return transTypeId;
    }

    public void setTransTypeId(String transTypeId) {
        this.transTypeId = transTypeId;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public List<AccountDTO> getMainAccountDTO() {
        return mainAccountDTO;
    }

    public void setMainAccountDTO(List<AccountDTO> mainAccountDTO) {
        this.mainAccountDTO = mainAccountDTO;
    }

    public List<AccountDTO> getListReferenceAccount() {
        return listReferenceAccount;
    }

    public void setListReferenceAccount(List<AccountDTO> listReferenceAccount) {
        this.listReferenceAccount = listReferenceAccount;
    }

}
