/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.BO;

import java.math.BigDecimal;

/**
 *
 * @author bongcm
 */
public class SubRelProductBean {

    private String relProductCode;
    private Integer isConnected;
    
    

    public SubRelProductBean(String relProductCode, Integer isConnected) {
		super();
		this.relProductCode = relProductCode;
		this.isConnected = isConnected;
	}

	public String getRelProductCode() {
        return relProductCode;
    }

    public void setRelProductCode(String relProductCode) {
        this.relProductCode = relProductCode;
    }

    public Integer getIsConnected() {
        return isConnected;
    }

    public void setIsConnected(Integer isConnected) {
        this.isConnected = isConnected;
    }
    
}
