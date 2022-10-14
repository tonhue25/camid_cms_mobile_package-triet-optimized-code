package co.siten.myvtg.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DataAccountDetailBean {
	private BigDecimal totalVolume;
	private String totalVolumeStr;
	private Date expiredDate;
	private String expiredDateStr;
	private DataPackageBean mainPackage;
	private List<DataPackageBean> dataPackages;
	private List<DataPakageToBuyBean> pakagesToBuy;

	
	public String getTotalVolumeStr() {
		return totalVolumeStr;
	}


	public void setTotalVolumeStr(String totalVolumeStr) {
		this.totalVolumeStr = totalVolumeStr;
	}

        public List<DataPackageBean> getDataPackages() {
                return dataPackages;
        }

        public void setDataPackages(List<DataPackageBean> dataPackages) {
                this.dataPackages = dataPackages;
        }

	public BigDecimal getTotalVolume() {
		return totalVolume;
	}

	
	public String getExpiredDateStr() {
		return expiredDateStr;
	}


	public void setExpiredDateStr(String expiredDateStr) {
		this.expiredDateStr = expiredDateStr;
	}


	public void setTotalVolume(BigDecimal totalVolume) {
		this.totalVolume = totalVolume;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public DataPackageBean getMainPackage() {
		return mainPackage;
	}

	public void setMainPackage(DataPackageBean mainPackage) {
		this.mainPackage = mainPackage;
	}

	public List<DataPakageToBuyBean> getPakagesToBuy() {
		return pakagesToBuy;
	}

	public void setPakagesToBuy(List<DataPakageToBuyBean> pakagesToBuy) {
		this.pakagesToBuy = pakagesToBuy;
	}
	
	

}
