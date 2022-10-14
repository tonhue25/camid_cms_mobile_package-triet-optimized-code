package co.siten.myvtg.bean;

public class EmoneyResponseBean {
    private EmoneyStatus status;
    private EmoneyData data;

    public EmoneyStatus getStatus() {
        return status;
    }

    public void setStatus(EmoneyStatus status) {
        this.status = status;
    }

    public EmoneyData getData() {
        return data;
    }

    public void setData(EmoneyData data) {
        this.data = data;
    }
}

