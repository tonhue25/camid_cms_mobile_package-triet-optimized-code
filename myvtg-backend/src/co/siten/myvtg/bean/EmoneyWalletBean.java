package co.siten.myvtg.bean;

import co.siten.myvtg.dto.EmoneyWalletDTO;

import java.util.List;

public class EmoneyWalletBean {
    Long totalRecords;
    List<EmoneyWalletDTO> emoneyWalletList;

    public Long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<EmoneyWalletDTO> getEmoneyWalletList() {
        return emoneyWalletList;
    }

    public void setEmoneyWalletList(List<EmoneyWalletDTO> emoneyWalletList) {
        this.emoneyWalletList = emoneyWalletList;
    }
}
