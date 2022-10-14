/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import co.siten.myvtg.dto.PaymentHistoryDTO;
import java.util.List;

/**
 *
 * @author anjav
 */
public class PaymentHistoryBean {
    private Long totalPaymentHistorys;
    private List<PaymentHistoryDTO> paymentHistoryList;

    public PaymentHistoryBean(Long totalPaymentHistorys, List<PaymentHistoryDTO> paymentHistoryList) {
        this.totalPaymentHistorys = totalPaymentHistorys;
        this.paymentHistoryList = paymentHistoryList;
    }

    public Long getTotalPaymentHistorys() {
        return totalPaymentHistorys;
    }

    public void setTotalPaymentHistorys(Long totalPaymentHistorys) {
        this.totalPaymentHistorys = totalPaymentHistorys;
    }

    public List<PaymentHistoryDTO> getPaymentHistoryList() {
        return paymentHistoryList;
    }

    public void setPaymentHistoryList(List<PaymentHistoryDTO> paymentHistoryList) {
        this.paymentHistoryList = paymentHistoryList;
    }

    

    public PaymentHistoryBean() {
    }
    
}
