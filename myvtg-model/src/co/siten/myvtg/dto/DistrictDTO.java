package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DistrictDTO {
    private String districtCode;
    private String districtName;
    @JsonIgnore
    private ObjectDTO object;

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }
}
