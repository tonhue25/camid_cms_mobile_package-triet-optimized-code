/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.bccs.cm.BO;

/**
 *
 * @author bongcm
 */
public class StockSimBean {
    String eki;
    String k4sno;
    Long cardType;
    
    public StockSimBean(String eki, String k4sno, Long cardType) {
		super();
		this.eki = eki;
		this.k4sno = k4sno;
		this.cardType = cardType;
	}

	public String getEki() {
        return eki;
    }

    public void setEki(String eki) {
        this.eki = eki;
    }

    public String getK4sno() {
        return k4sno;
    }

    public void setK4sno(String k4sno) {
        this.k4sno = k4sno;
    }

    public Long getCardType() {
        return cardType;
    }

    public void setCardType(Long cardType) {
        this.cardType = cardType;
    }
    
}
