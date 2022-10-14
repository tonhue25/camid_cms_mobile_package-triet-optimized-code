/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

/**
 *
 * @author buiquangdai
 */
public class InforOfRankDTO {

    String code;
    String message;
    AccountRankDTO accountRankDTO;

    public AccountRankDTO getAccountRankDTO() {
        return accountRankDTO;
    }

    public void setAccountRankDTO(AccountRankDTO accountRankDTO) {
        this.accountRankDTO = accountRankDTO;
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
