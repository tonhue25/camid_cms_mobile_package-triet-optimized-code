package co.siten.myvtg.bean;

import java.util.Date;

public class ReportNotiCustomer {
    private String code;
    private String dataPush;
    private String resultPush;
    private Date time;

    public ReportNotiCustomer() {}

    public ReportNotiCustomer(String code, String dataPush, String resultPush, Date time) {
        super();
        this.code = code;
        this.dataPush = dataPush;
        this.resultPush = resultPush;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDataPush() {
        return dataPush;
    }

    public void setDataPush(String dataPush) {
        this.dataPush = dataPush;
    }

    public String getResultPush() {
        return resultPush;
    }

    public void setResultPush(String resultPush) {
        this.resultPush = resultPush;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) { this.time = time; }
}
