package co.siten.myvtg.util;

public class PageUtil {
    private int totalElement;
    private int currentPage;

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public PageUtil(int totalElement, int currentPage) {
        this.totalElement = totalElement;
        this.currentPage = currentPage;
    }

}
