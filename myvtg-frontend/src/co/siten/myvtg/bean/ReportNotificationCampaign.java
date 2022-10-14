package co.siten.myvtg.bean;

public class ReportNotificationCampaign {
    private Long numberPushed;
    private Long numberSuccess;
    private String programCode;

    public ReportNotificationCampaign(String programCode, Long numberPushed, Long numberSuccess) {
        super();
        this.numberPushed = numberPushed;
        this.numberSuccess = numberSuccess;
        this.programCode = programCode;
    }

    public ReportNotificationCampaign() {}

    public Long getNumberPushed() {
        return numberPushed;
    }

    public void setNumberPushed(Long numberPushed) {
        this.numberPushed = numberPushed;
    }

    public Long getNumberSuccess() {
        return numberSuccess;
    }

    public void setNumberSuccess(Long numberSuccess) {
        this.numberSuccess = numberSuccess;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

}
