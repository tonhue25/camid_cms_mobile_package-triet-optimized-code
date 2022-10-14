package co.siten.myvtg.bean;

import java.math.BigDecimal;

public class ServiceAutoRenewBean extends ServiceBean{
    private String noneAutoCode;
    private String autoCode;
    public ServiceAutoRenewBean(String name, String code, String shortDes, String iconUrl, String validity, String currency, BigDecimal price, String autoRenew, String subCode, String noneAutoCode, String autoCode) {
        super(name,code, shortDes, iconUrl, validity, currency, price, autoRenew, subCode);
        this.noneAutoCode = noneAutoCode;
        this.autoCode = autoCode;
    }

    public String getNoneAutoCode() {
        return noneAutoCode;
    }

    public void setNoneAutoCode(String noneAutoCode) {
        this.noneAutoCode = noneAutoCode;
    }

    public String getAutoCode() {
        return autoCode;
    }

    public void setAutoCode(String autoCode) {
        this.autoCode = autoCode;
    }
}
