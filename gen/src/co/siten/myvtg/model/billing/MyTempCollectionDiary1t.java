package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the MY_TEMP_COLLECTION_DIARY_1T database table.
 * 
 */
@Entity
@Table(name="MY_TEMP_COLLECTION_DIARY_1T")
@NamedQuery(name="MyTempCollectionDiary1t.findAll", query="SELECT m FROM MyTempCollectionDiary1t m")
public class MyTempCollectionDiary1t implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AMOUNT_CONTRACT")
	private BigDecimal amountContract;

	@Column(name="AREA_ID")
	private BigDecimal areaId;

	@Column(name="AREA_NAME")
	private String areaName;

	@Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;

	@Column(name="BILL_CYCLE_FROM")
	private BigDecimal billCycleFrom;

	@Column(name="CATEGORY_ID")
	private String categoryId;

	@Column(name="CENTER_ID")
	private BigDecimal centerId;

	@Column(name="CENTER_NAME")
	private String centerName;

	@Column(name="COMPANY_ID")
	private BigDecimal companyId;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CONTRACT_FORM_MNGT")
	private String contractFormMngt;

	@Column(name="CONTRACT_FORM_MNGT_NAME")
	private String contractFormMngtName;

	@Column(name="CONTRACT_TYPE_ID")
	private String contractTypeId;

	@Column(name="CONTRACT_TYPE_NAME")
	private String contractTypeName;

	@Column(name="DEPARTMENT_ID")
	private BigDecimal departmentId;

	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	@Column(name="GROUP_ID")
	private BigDecimal groupId;

	@Column(name="GROUP_NAME")
	private String groupName;

	@Column(name="HAND_OR_MACHINE")
	private BigDecimal handOrMachine;

	@Column(name="ITEM_NO_FROM")
	private BigDecimal itemNoFrom;

	@Column(name="ITEM_NO_TO")
	private BigDecimal itemNoTo;

	@Column(name="JOB_IN")
	private BigDecimal jobIn;

	@Column(name="NUMBER_CONTRACT")
	private BigDecimal numberContract;

	@Column(name="\"PATH\"")
	private String path;

	@Column(name="PATH_NAME")
	private String pathName;

	@Column(name="PRINT_AREA_ID")
	private BigDecimal printAreaId;

	@Column(name="PRINT_AREA_NAME")
	private String printAreaName;

	@Column(name="PRINT_ORDER")
	private String printOrder;

	@Column(name="PRINT_TYPE")
	private String printType;

	@Column(name="PRIVILEGE_TYPE")
	private BigDecimal privilegeType;

	@Column(name="SIGN_CHARGE")
	private BigDecimal signCharge;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	@Column(name="STAFF_NAME")
	private String staffName;

	public MyTempCollectionDiary1t() {
	}

	public BigDecimal getAmountContract() {
		return this.amountContract;
	}

	public void setAmountContract(BigDecimal amountContract) {
		this.amountContract = amountContract;
	}

	public BigDecimal getAreaId() {
		return this.areaId;
	}

	public void setAreaId(BigDecimal areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Date getBillCycle() {
		return this.billCycle;
	}

	public void setBillCycle(Date billCycle) {
		this.billCycle = billCycle;
	}

	public BigDecimal getBillCycleFrom() {
		return this.billCycleFrom;
	}

	public void setBillCycleFrom(BigDecimal billCycleFrom) {
		this.billCycleFrom = billCycleFrom;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getCenterId() {
		return this.centerId;
	}

	public void setCenterId(BigDecimal centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return this.centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public BigDecimal getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(BigDecimal companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContractFormMngt() {
		return this.contractFormMngt;
	}

	public void setContractFormMngt(String contractFormMngt) {
		this.contractFormMngt = contractFormMngt;
	}

	public String getContractFormMngtName() {
		return this.contractFormMngtName;
	}

	public void setContractFormMngtName(String contractFormMngtName) {
		this.contractFormMngtName = contractFormMngtName;
	}

	public String getContractTypeId() {
		return this.contractTypeId;
	}

	public void setContractTypeId(String contractTypeId) {
		this.contractTypeId = contractTypeId;
	}

	public String getContractTypeName() {
		return this.contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}

	public BigDecimal getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(BigDecimal departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public BigDecimal getGroupId() {
		return this.groupId;
	}

	public void setGroupId(BigDecimal groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BigDecimal getHandOrMachine() {
		return this.handOrMachine;
	}

	public void setHandOrMachine(BigDecimal handOrMachine) {
		this.handOrMachine = handOrMachine;
	}

	public BigDecimal getItemNoFrom() {
		return this.itemNoFrom;
	}

	public void setItemNoFrom(BigDecimal itemNoFrom) {
		this.itemNoFrom = itemNoFrom;
	}

	public BigDecimal getItemNoTo() {
		return this.itemNoTo;
	}

	public void setItemNoTo(BigDecimal itemNoTo) {
		this.itemNoTo = itemNoTo;
	}

	public BigDecimal getJobIn() {
		return this.jobIn;
	}

	public void setJobIn(BigDecimal jobIn) {
		this.jobIn = jobIn;
	}

	public BigDecimal getNumberContract() {
		return this.numberContract;
	}

	public void setNumberContract(BigDecimal numberContract) {
		this.numberContract = numberContract;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPathName() {
		return this.pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public BigDecimal getPrintAreaId() {
		return this.printAreaId;
	}

	public void setPrintAreaId(BigDecimal printAreaId) {
		this.printAreaId = printAreaId;
	}

	public String getPrintAreaName() {
		return this.printAreaName;
	}

	public void setPrintAreaName(String printAreaName) {
		this.printAreaName = printAreaName;
	}

	public String getPrintOrder() {
		return this.printOrder;
	}

	public void setPrintOrder(String printOrder) {
		this.printOrder = printOrder;
	}

	public String getPrintType() {
		return this.printType;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public BigDecimal getPrivilegeType() {
		return this.privilegeType;
	}

	public void setPrivilegeType(BigDecimal privilegeType) {
		this.privilegeType = privilegeType;
	}

	public BigDecimal getSignCharge() {
		return this.signCharge;
	}

	public void setSignCharge(BigDecimal signCharge) {
		this.signCharge = signCharge;
	}

	public String getStaffCode() {
		return this.staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public BigDecimal getStaffId() {
		return this.staffId;
	}

	public void setStaffId(BigDecimal staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

}