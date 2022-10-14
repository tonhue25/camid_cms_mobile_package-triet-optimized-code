package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ObjectDTO {
    @JsonIgnore
    private String wsCmEndpoint;
    @JsonIgnore
    private String wsCmToken;
    @JsonIgnore
    private String locale;
    @JsonIgnore
    private String districtCode;
    @JsonIgnore
    private String provinceCode;
    private String code;
    private String name;

    public String getWsCmEndpoint() {
        return wsCmEndpoint;
    }

    public void setWsCmEndpoint(String wsCmEndpoint) {
        this.wsCmEndpoint = wsCmEndpoint;
    }

    public String getWsCmToken() {
        return wsCmToken;
    }

    public void setWsCmToken(String wsCmToken) {
        this.wsCmToken = wsCmToken;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
