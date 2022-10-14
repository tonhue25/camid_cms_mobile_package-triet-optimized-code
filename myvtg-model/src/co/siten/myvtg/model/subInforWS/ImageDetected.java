/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.subInforWS;

/**
 * ImageDetected
 *
 * @author partner7
 */
public class ImageDetected {

    private String errorCode;
    private String messageCode;
    private String idNumber; //personalNumber
    private String dobEn; //birthday
    private String provinceEn;
    private String sexEn; //sex
    private String name; // surname + givenName
    //
    private String nationality; //country
    private String expiryDate;

    public ImageDetected() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDobEn() {
        return dobEn;
    }

    public void setDobEn(String dobEn) {
        this.dobEn = dobEn;
    }

    public String getProvinceEn() {
        return provinceEn;
    }

    public void setProvinceEn(String provinceEn) {
        this.provinceEn = provinceEn;
    }

    public String getSexEn() {
        return sexEn;
    }

    public void setSexEn(String sexEn) {
        this.sexEn = sexEn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
