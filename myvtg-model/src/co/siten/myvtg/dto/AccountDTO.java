/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author buiquangdai
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private String productName;
    private String vtAccountId;
    private String customerId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVtAccountId() {
        return vtAccountId;
    }

    public void setVtAccountId(String vtAccountId) {
        this.vtAccountId = vtAccountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
}
