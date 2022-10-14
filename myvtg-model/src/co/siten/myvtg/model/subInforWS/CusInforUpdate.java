/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.subInforWS;

import java.util.List;

/**
 * CusInforUpdate gateway call from app camid to BCCS api
 *
 * @author partner7
 */
public class CusInforUpdate {

    private String isdn;
    private String lang;
    private String isScan;
    private String idType;
    private String fullName;
    private String idNumber;
    private String dob;
    private String gender;
    private String issueDate;
    private String province;
    private String district;
    private String commune;
    private String street;
    private String homeNo;
    private String fullAddress;
    private String nationality;
    private String contact;
    private String expireDate;
    private String visaDate;
    private String subName;
    private String subGender;
    private String subDateBirth;
    private String relationship;
    private List<ImageCus> imageList;

    public CusInforUpdate() {
    }

    public String getIsdn() {
        return isdn;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getIsScan() {
        return isScan;
    }

    public void setIsScan(String isScan) {
        this.isScan = isScan;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNo() {
        return homeNo;
    }

    public void setHomeNo(String homeNo) {
        this.homeNo = homeNo;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getVisaDate() {
        return visaDate;
    }

    public void setVisaDate(String visaDate) {
        this.visaDate = visaDate;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getSubGender() {
        return subGender;
    }

    public void setSubGender(String subGender) {
        this.subGender = subGender;
    }

    public String getSubDateBirth() {
        return subDateBirth;
    }

    public void setSubDateBirth(String subDateBirth) {
        this.subDateBirth = subDateBirth;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public List<ImageCus> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageCus> imageList) {
        this.imageList = imageList;
    }

}
