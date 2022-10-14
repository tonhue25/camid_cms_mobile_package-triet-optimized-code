package co.siten.myvtg.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PrecinctDTO{
    private String precinctCode;
    private String precinctName;
    @JsonIgnore
    private ObjectDTO object;

    public String getPrecinctCode() {
        return precinctCode;
    }

    public void setPrecinctCode(String precinctCode) {
        this.precinctCode = precinctCode;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public ObjectDTO getObject() {
        return object;
    }

    public void setObject(ObjectDTO object) {
        this.object = object;
    }
}

