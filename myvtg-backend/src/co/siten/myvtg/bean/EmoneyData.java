package co.siten.myvtg.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class EmoneyData {

//    @JsonIgnore()
    private String token;

    @SerializedName("account_data")
//    @JsonIgnore()
    private EmoneyAccountInfo accountInfo;

    public EmoneyAccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(EmoneyAccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
