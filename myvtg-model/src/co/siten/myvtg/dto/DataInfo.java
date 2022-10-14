package co.siten.myvtg.dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author daibq
 */
public class DataInfo {
    
    private String total;
    private List<String> detail; // data-exp

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        String str = "";
        for (String string : detail) {
            str += string + " + ";
        }
        return (str.length() > 0 ? (str.substring(0, str.length() - 1)) : " ");
    }
}

