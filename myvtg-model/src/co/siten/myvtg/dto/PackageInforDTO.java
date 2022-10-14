package co.siten.myvtg.dto;

public class PackageInforDTO {
    private Long id;
    private Long payAdvance;
    private Long speed;
    private String price;
    private String name;
    private String installation;
    private String deposit;
    private String modemWifi;
    private String specialPromotion;
    private String account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPayAdvance() {
        return payAdvance;
    }

    public void setPayAdvance(Long payAdvance) {
        this.payAdvance = payAdvance;
    }

    public Long getSpeed() {
        return speed;
    }

    public void setSpeed(Long speed) {
        this.speed = speed;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstallation() {
        return installation;
    }

    public void setInstallation(String installation) {
        this.installation = installation;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getModemWifi() {
        return modemWifi;
    }

    public void setModemWifi(String modemWifi) {
        this.modemWifi = modemWifi;
    }

    public String getSpecialPromotion() {
        return specialPromotion;
    }

    public void setSpecialPromotion(String specialPromotion) {
        this.specialPromotion = specialPromotion;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
