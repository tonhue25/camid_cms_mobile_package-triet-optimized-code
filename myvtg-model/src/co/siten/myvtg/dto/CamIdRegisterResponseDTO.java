package co.siten.myvtg.dto;

public class CamIdRegisterResponseDTO {
    private String requestId;

    public CamIdRegisterResponseDTO(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
