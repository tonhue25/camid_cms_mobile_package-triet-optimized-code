package co.siten.myvtg.dto;

import java.util.List;

public class PackageInternetDTO {
    private List<PackageInforDTO> packages;

    public List<PackageInforDTO> getPackages() {
        return packages;
    }

    public void setPackages(List<PackageInforDTO> packages) {
        this.packages = packages;
    }
}
