package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the AREA database table.
 *
 */
@Entity
@NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "AREA_CODE")
    private String areaCode;

    @Column(name = "CEN_CODE")
    private String cenCode;

    private String district;

    @Column(name = "FULL_NAME")
    private String fullName;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String name;

    @Column(name = "ORDER_NO")
    private BigDecimal orderNo;

    @Column(name = "PARENT_CODE")
    private String parentCode;

    private String precinct;

    private String province;

    @Column(name = "PSTN_CODE")
    private String pstnCode;

    private BigDecimal status;

    private String street;

    @Column(name = "STREET_BLOCK")
    private String streetBlock;

    @Column(name = "\"TYPE\"")
    private String type;

    @Column(name = "NAME_LC")
    private String nameLc;

    public Area() {
    }

    public String getAreaCode() {
        return this.areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getCenCode() {
        return this.cenCode;
    }

    public void setCenCode(String cenCode) {
        this.cenCode = cenCode;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getLatitude() {
        return this.latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return this.longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(BigDecimal orderNo) {
        this.orderNo = orderNo;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getPrecinct() {
        return this.precinct;
    }

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPstnCode() {
        return this.pstnCode;
    }

    public void setPstnCode(String pstnCode) {
        this.pstnCode = pstnCode;
    }

    public BigDecimal getStatus() {
        return this.status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetBlock() {
        return this.streetBlock;
    }

    public void setStreetBlock(String streetBlock) {
        this.streetBlock = streetBlock;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameLc() {
        return nameLc;
    }

    public void setNameLc(String nameLc) {
        this.nameLc = nameLc;
    }

}
