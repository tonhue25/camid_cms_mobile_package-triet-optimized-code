package co.siten.myvtg.dto;

public class SummaryPackageDTO {
    private  String packageMonth;
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPackageMonth() {
        return packageMonth;
    }

    public void setPackageMonth(String packageMonth) {
        this.packageMonth = packageMonth;
    }
}
