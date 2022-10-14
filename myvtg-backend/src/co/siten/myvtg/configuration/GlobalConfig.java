package co.siten.myvtg.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "global") // prefix global, find global.* values
@PropertySource(value = {"classpath:config.properties"})
public class GlobalConfig {
    private String name;
    @Value(value = "${global.passportUrl}")
    private String passportUrl;
    private String userName;
    private String password;
    private String domainCode;
    @Value(value = "${global.domainCodeValue}")
    private String domainCodeValue;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportUrl() {
        return passportUrl;
    }

    public void setPassportUrl(String passportUrl) {
        this.passportUrl = passportUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomainCode() {
        return domainCode;
    }

    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }

    public String getDomainCodeValue() {
        return domainCodeValue;
    }

    public void setDomainCodeValue(String domainCodeValue) {
        this.domainCodeValue = domainCodeValue;
    }
}
