package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.siten.myvtg.config.Config;

/**
 * The persistent class for the SUB_MB database table.
 *
 */
@Entity
@Table(name = "SUB_MB")
@NamedQuery(name = "SubMb.findAll", query = "SELECT s FROM SubMb s")
public class SubMb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = Config.SEQUENCE_CM_PRE_SUB_MB, allocationSize = 1)
    @Column(name = "SUB_ID")
    private Long subId;

    @Column(name = "ACT_STATUS")
    private String actStatus;

    private String address;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

//	@Column(name = "BIRTH_PLACE")
//	private String birthPlace;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHANGE_DATETIME")
    private Date changeDatetime;
//
//	@Column(name = "CONTRACT_NO")
//	private String contractNo;

    @Column(name = "CUST_ID")
    private BigDecimal custId;

    private String district;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATETIME")
    private Date endDatetime;

    @Column(name = "FINISH_REASON_ID")
    private BigDecimal finishReasonId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FIRST_EXP_DATE")
    private Date firstExpDate;

    @Column(name = "FIRST_SHOP_CODE")
    private String firstShopCode;

    private String gender;

    private String imsi;

    private String isdn;
//
//	@Column(name = "\"LANGUAGE\"")
//	private String language;

    @Column(name = "LAST_NUMBER")
    private String lastNumber;

//	private String nationality;
    private String notes;

    @Column(name = "NUM_RESET_ZONE")
    private BigDecimal numResetZone;

    @Column(name = "OFFER_ID")
    private Long offerId;

    @Column(name = "ORG_PRODUCT_CODE")
    private String orgProductCode;

    private String password;

//	@Column(name = "\"PATH\"")
//	private String path;
    private String precinct;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

//	@Column(name = "PROFILE_NAME")
//	private String profileName;
    @Temporal(TemporalType.DATE)
    @Column(name = "PROM_VALID_DATETIME")
    private Date promValidDatetime;

    @Column(name = "PROMOTION_CODE")
    private String promotionCode;

    private String province;

    @Column(name = "REG_TYPE")
    private String regType;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SECOND_EXP_DATE")
    private Date secondExpDate;

    private String serial;

    @Column(name = "SHOP_CODE")
    private String shopCode;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "STA_DATETIME")
    private Date staDatetime;

    @Column(name = "START_MONEY")
    private String startMoney;

    private Integer status;

//	private String street;
//	@Column(name = "STREET_BLOCK")
//	private String streetBlock;
    @Column(name = "SUB_NAME")
    private String subName;

    @Column(name = "SUBCOS_HUAWEI")
    private String subcosHuawei;

    @Column(name = "SUBCOS_ZTE")
    private String subcosZte;

    @Column(name = "USER_CREATED")
    private String userCreated;
    @Temporal(TemporalType.DATE)
    @Column(name = "VALID_DATETIME")
    private Date validDatetime;

    private String vip;

    public SubMb() {
    }

    public SubMb(SubMb subMb) {
        this.actStatus = subMb.getActStatus();
        this.address = subMb.getAddress();
        this.birthDate = subMb.getBirthDate();
//		this.birthPlace = subMb.getBirthPlace();
        this.changeDatetime = new Date();
//		this.contractNo = subMb.getContractNo();
        this.custId = subMb.getCustId();
        this.district = subMb.getDistrict();
        this.endDatetime = subMb.getEndDatetime();
        this.finishReasonId = subMb.getFinishReasonId();
        this.firstExpDate = subMb.getFirstExpDate();
        this.firstShopCode = subMb.getFirstShopCode();
        this.gender = subMb.getGender();
        this.imsi = subMb.getImsi();
        this.isdn = subMb.getIsdn();
//		this.language = subMb.getLanguage();
        this.lastNumber = subMb.getLastNumber();
//		this.nationality = subMb.getNationality();
        this.notes = subMb.getNotes();
        this.numResetZone = subMb.getNumResetZone();
        this.offerId = subMb.getOfferId();
        this.orgProductCode = subMb.getOrgProductCode();
        this.password = subMb.getPassword();
//		this.path = subMb.getPath();
        this.precinct = subMb.getPrecinct();
        this.productCode = subMb.getProductCode();
//		this.profileName = subMb.getProfileName();
        this.promotionCode = subMb.getPromotionCode();
        this.promValidDatetime = subMb.getPromValidDatetime();
        this.province = subMb.getProvince();
        this.regType = subMb.getRegType();
        this.secondExpDate = subMb.getSecondExpDate();
        this.serial = subMb.getSerial();
        this.shopCode = subMb.getShopCode();
        this.staDatetime = subMb.getStaDatetime();
        this.startMoney = subMb.getStartMoney();
        this.status = subMb.getStatus();
//		this.street = subMb.getStreet();
//		this.streetBlock = subMb.getStreetBlock();
        this.subName = subMb.getSubName();
        this.subcosHuawei = subMb.getSubcosHuawei();
        this.subcosZte = subMb.getSubcosZte();
        this.userCreated = subMb.getUserCreated();
        this.validDatetime = subMb.getValidDatetime();
        this.vip = subMb.getVip();
    }

    public Long getSubId() {
        return subId;
    }

    public String getActStatus() {
        return actStatus;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }
//
//	public String getBirthPlace() {
//		return birthPlace;
//	}

    public Date getChangeDatetime() {
        return changeDatetime;
    }

//	public String getContractNo() {
//		return contractNo;
//	}
    public BigDecimal getCustId() {
        return custId;
    }

    public String getDistrict() {
        return district;
    }

    public Date getEndDatetime() {
        return endDatetime;
    }

    public BigDecimal getFinishReasonId() {
        return finishReasonId;
    }

    public Date getFirstExpDate() {
        return firstExpDate;
    }

    public String getFirstShopCode() {
        return firstShopCode;
    }

    public String getGender() {
        return gender;
    }

    public String getImsi() {
        return imsi;
    }

    public String getIsdn() {
        return isdn;
    }
//
//	public String getLanguage() {
//		return language;
//	}

    public String getLastNumber() {
        return lastNumber;
    }
//
//	public String getNationality() {
//		return nationality;
//	}

    public String getNotes() {
        return notes;
    }

    public BigDecimal getNumResetZone() {
        return numResetZone;
    }

    public Long getOfferId() {
        return offerId;
    }

    public String getOrgProductCode() {
        return orgProductCode;
    }

    public String getPassword() {
        return password;
    }
//
//	public String getPath() {
//		return path;
//	}

    public String getPrecinct() {
        return precinct;
    }

    public String getProductCode() {
        return productCode;
    }
//
//	public String getProfileName() {
//		return profileName;
//	}

    public Date getPromValidDatetime() {
        return promValidDatetime;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public String getProvince() {
        return province;
    }

    public String getRegType() {
        return regType;
    }

    public Date getSecondExpDate() {
        return secondExpDate;
    }

    public String getSerial() {
        return serial;
    }

    public String getShopCode() {
        return shopCode;
    }

    public Date getStaDatetime() {
        return staDatetime;
    }

    public String getStartMoney() {
        return startMoney;
    }

    public Integer getStatus() {
        return status;
    }
//
//	public String getStreet() {
//		return street;
//	}
//
//	public String getStreetBlock() {
//		return streetBlock;
//	}

    public String getSubName() {
        return subName;
    }

    public String getSubcosHuawei() {
        return subcosHuawei;
    }

    public String getSubcosZte() {
        return subcosZte;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public Date getValidDatetime() {
        return validDatetime;
    }

    public String getVip() {
        return vip;
    }

    public void setSubId(Long subId) {
        this.subId = subId;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
//
//	public void setBirthPlace(String birthPlace) {
//		this.birthPlace = birthPlace;
//	}

    public void setChangeDatetime(Date changeDatetime) {
        this.changeDatetime = changeDatetime;
    }
//
//	public void setContractNo(String contractNo) {
//		this.contractNo = contractNo;
//	}

    public void setCustId(BigDecimal custId) {
        this.custId = custId;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setEndDatetime(Date endDatetime) {
        this.endDatetime = endDatetime;
    }

    public void setFinishReasonId(BigDecimal finishReasonId) {
        this.finishReasonId = finishReasonId;
    }

    public void setFirstExpDate(Date firstExpDate) {
        this.firstExpDate = firstExpDate;
    }

    public void setFirstShopCode(String firstShopCode) {
        this.firstShopCode = firstShopCode;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public void setIsdn(String isdn) {
        this.isdn = isdn;
    }
//
//	public void setLanguage(String language) {
//		this.language = language;
//	}

    public void setLastNumber(String lastNumber) {
        this.lastNumber = lastNumber;
    }
//
//	public void setNationality(String nationality) {
//		this.nationality = nationality;
//	}

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setNumResetZone(BigDecimal numResetZone) {
        this.numResetZone = numResetZone;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public void setOrgProductCode(String orgProductCode) {
        this.orgProductCode = orgProductCode;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//	public void setPath(String path) {
//		this.path = path;
//	}

    public void setPrecinct(String precinct) {
        this.precinct = precinct;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
//
//	public void setProfileName(String profileName) {
//		this.profileName = profileName;
//	}

    public void setPromValidDatetime(Date promValidDatetime) {
        this.promValidDatetime = promValidDatetime;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }

    public void setSecondExpDate(Date secondExpDate) {
        this.secondExpDate = secondExpDate;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setShopCode(String shopCode) {
        this.shopCode = shopCode;
    }

    public void setStaDatetime(Date staDatetime) {
        this.staDatetime = staDatetime;
    }

    public void setStartMoney(String startMoney) {
        this.startMoney = startMoney;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//	public void setStreet(String street) {
//		this.street = street;
//	}
//
//	public void setStreetBlock(String streetBlock) {
//		this.streetBlock = streetBlock;
//	}
    public void setSubName(String subName) {
        this.subName = subName;
    }

    public void setSubcosHuawei(String subcosHuawei) {
        this.subcosHuawei = subcosHuawei;
    }

    public void setSubcosZte(String subcosZte) {
        this.subcosZte = subcosZte;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public void setValidDatetime(Date validDatetime) {
        this.validDatetime = validDatetime;
    }

    public void setVip(String vip) {
        this.vip = vip;
    } 

}
