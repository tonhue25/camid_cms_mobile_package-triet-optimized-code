/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import java.util.List;

/**
 *
 * @author daibq
 */
public class InforAccountPoint{
    private String code;
    private String message;
    private List<AccountPoint> listAccountPoint;

    public List<AccountPoint> getListAccountPoint() {
        return listAccountPoint;
    }

    public void setListAccountPoint(List<AccountPoint> listAccountPoint) {
        this.listAccountPoint = listAccountPoint;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
