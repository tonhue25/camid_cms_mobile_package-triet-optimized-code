package co.siten.myvtg.bean;

import co.siten.myvtg.dto.DonateTransactionDTO;

import java.util.List;

public class DonateTransactionBean {
    Long totalRecords;
    List<DonateTransactionDTO> donateTransactionList;

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<DonateTransactionDTO> getDonateTransactionList() {
        return donateTransactionList;
    }

    public void setDonateTransactionList(List<DonateTransactionDTO> donateTransactionList) {
        this.donateTransactionList = donateTransactionList;
    }
}
