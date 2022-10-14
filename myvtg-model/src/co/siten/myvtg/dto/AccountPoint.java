/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import java.util.Date;

/**
 *
 * @author minhnhutnt
 */
public class AccountPoint {

    private Long accountPointId;
    private Long pointId;
    private Long vtAccId;
    private Double pointValue;
    private Date pointExpireDate;
    private Long productId;
    private String productName;
    private String createDate;
    private String pointName;
    private String pointType;
    private Long status;
    private String description;

    public Long getAccountPointId() {
        return accountPointId;
    }

    public void setAccountPointId(Long accountPointId) {
        this.accountPointId = accountPointId;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Long getVtAccId() {
        return vtAccId;
    }

    public void setVtAccId(Long vtAccId) {
        this.vtAccId = vtAccId;
    }

    public Double getPointValue() {
        return pointValue;
    }

    public void setPointValue(Double pointValue) {
        this.pointValue = pointValue;
    }

    public Date getPointExpireDate() {
        return pointExpireDate;
    }

    public void setPointExpireDate(Date pointExpireDate) {
        this.pointExpireDate = pointExpireDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
