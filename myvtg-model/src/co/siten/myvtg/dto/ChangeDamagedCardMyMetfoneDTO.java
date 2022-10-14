/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author huunghi
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeDamagedCardMyMetfoneDTO {

    private String isRightInfor;
    private List<Object> listChangeDamagedCard;

    public String getIsRightInfor() {
        return isRightInfor;
    }

    public void setIsRightInfor(String isRightInfor) {
        this.isRightInfor = isRightInfor;
    }

    public List<Object> getListChangeDamagedCard() {
        return listChangeDamagedCard;
    }

    public void setListChangeDamagedCard(List<Object> listChangeDamagedCard) {
        this.listChangeDamagedCard = listChangeDamagedCard;
    }
}
