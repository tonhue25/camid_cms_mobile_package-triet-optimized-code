package co.siten.myvtg.bean;

public class EmoneyAccountInfo {
    private String transId;
    private boolean enoughBalance;
    private boolean emoneyAccount;
    private String expiresTimeOTP;


    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public boolean isEnoughBalance() {
        return enoughBalance;
    }

    public void setEnoughBalance(boolean enoughBalance) {
        this.enoughBalance = enoughBalance;
    }

    public boolean isEmoneyAccount() {
        return emoneyAccount;
    }

    public void setEmoneyAccount(boolean emoneyAccount) {
        this.emoneyAccount = emoneyAccount;
    }

    public String getExpiresTimeOTP() {
        return expiresTimeOTP;
    }

    public void setExpiresTimeOTP(String expiresTimeOTP) {
        this.expiresTimeOTP = expiresTimeOTP;
    }
}
