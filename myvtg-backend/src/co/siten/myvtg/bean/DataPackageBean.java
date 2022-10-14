package co.siten.myvtg.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DataPackageBean {
	private String name;
	private String code;
	private BigDecimal volume;
	private String volumeStr;
	private BigDecimal price;
	private Date expiredDate;
	private String expiredDateStr;
	private List<DataPakageToBuyBean> pakagesToBuy;

	@JsonIgnore
	private Integer accCode;

	@JsonIgnore
	public Integer getAccCode() {
		return accCode;
	}

	public void setAccCode(Integer accCode) {
		this.accCode = accCode;
	}

	public List<DataPakageToBuyBean> getPakagesToBuy() {
		return pakagesToBuy;
	}

	public void setPakagesToBuy(List<DataPakageToBuyBean> pakagesToBuy) {
		this.pakagesToBuy = pakagesToBuy;
	}

	public DataPackageBean() {
		super();
	}

	public String getVolumeStr() {
		return volumeStr;
	}

	public void setVolumeStr(String volumeStr) {
		this.volumeStr = volumeStr;
	}

	public DataPackageBean(String name, String code, BigDecimal volume, BigDecimal price, Date expiredDate) {
		super();
		this.name = name;
		this.code = code;
		this.volume = volume;
		this.price = price;
		this.expiredDate = expiredDate;
	}

	public DataPackageBean(String name, String code, BigDecimal volume, BigDecimal price, Date expiredDate,
			String expiredDateStr) {
		super();
		this.name = name;
		this.code = code;
		this.volume = volume;
		this.price = price;
		this.expiredDate = expiredDate;
		this.expiredDateStr = expiredDateStr;
	}

	public DataPackageBean(String name, String code, BigDecimal volume, BigDecimal price, Date expiredDate,
			String expiredDateStr, String volumeStr) {
		super();
		this.name = name;
		this.code = code;
		this.volume = volume;
		this.price = price;
		this.expiredDate = expiredDate;
		this.expiredDateStr = expiredDateStr;
		this.volumeStr = volumeStr;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date duration) {
		this.expiredDate = duration;
	}

	public String getExpiredDateStr() {
		return expiredDateStr;
	}

	public void setExpiredDateStr(String expiredDateStr) {
		this.expiredDateStr = expiredDateStr;
	}

}
