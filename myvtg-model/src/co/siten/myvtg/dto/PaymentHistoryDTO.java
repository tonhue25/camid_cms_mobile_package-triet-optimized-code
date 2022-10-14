/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 * @author anjav
 */
public class PaymentHistoryDTO {

    private String paymentAccount;
    private String ftthAccount;
    private double amount;
    private String ftthName;
    private int contractIdInfo;
    private String paymentDate;

    public PaymentHistoryDTO(String paymentAccount, String ftthAccount, double amount, String ftthName, int contractIdInfo, String paymentDate) {
        this.paymentAccount = paymentAccount;
        this.ftthAccount = ftthAccount;
        this.amount = amount;
        this.ftthName = ftthName;
        this.contractIdInfo = contractIdInfo;
        this.paymentDate = paymentDate;
    }

    public PaymentHistoryDTO() {
    }

    public int getContractIdInfo() {
        return contractIdInfo;
    }

    public void setContractIdInfo(int contractIdInfo) {
        this.contractIdInfo = contractIdInfo;
    }

    public String getPaymentAccount() {
        return paymentAccount;
    }

    public void setPaymentAccount(String paymentAccount) {
        this.paymentAccount = paymentAccount;
    }

    public String getFtthAccount() {
        return ftthAccount;
    }

    public void setFtthAccount(String ftthAccount) {
        this.ftthAccount = ftthAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getFtthName() {
        return ftthName;
    }

    public void setFtthName(String ftthName) {
        this.ftthName = ftthName;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}
