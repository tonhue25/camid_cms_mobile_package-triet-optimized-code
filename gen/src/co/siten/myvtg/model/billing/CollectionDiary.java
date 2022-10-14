package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the COLLECTION_DIARY database table.
 * 
 */
@Entity
@Table(name="COLLECTION_DIARY")
@NamedQuery(name="CollectionDiary.findAll", query="SELECT c FROM CollectionDiary c")
public class CollectionDiary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AMOUNT_CONTRACT")
	private BigDecimal amountContract;

	@Column(name="AREA_ID")
	private BigDecimal areaId;

	@Column(name="AREA_NAME")
	private String areaName;

	private String barcode;

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

	@Column(name="CHARGE_REPORT_ID")
	private BigDecimal chargeReportId;

	@Column(name="CHARGE_REPORT_ROW_ID")
	private String chargeReportRowId;

	@Column(name="CHARGE_STATUS")
	private String chargeStatus;

	@Column(name="COMPANY_ID")
	private BigDecimal companyId;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="CONTRACT_FORM_MNGT")
	private String contractFormMngt;

	@Column(name="CONTRACT_FORM_MNGT_GROUP")
	private String contractFormMngtGroup;

	@Column(name="CONTRACT_FORM_MNGT_NAME")
	private String contractFormMngtName;

	@Column(name="CONTRACT_ID")
	private BigDecimal contractId;

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

	@Column(name="IS_DAI_LY")
	private BigDecimal isDaiLy;

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

	@Column(name="PAY_AREA_CODE")
	private String payAreaCode;

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

	@Column(name="SERVICE_TYPES")
	private String serviceTypes;

	@Column(name="SIGN_CHARGE")
	private BigDecimal signCharge;

	@Column(name="STAFF_CODE")
	private String staffCode;

	@Column(name="STAFF_ID")
	private BigDecimal staffId;

	@Column(name="STAFF_NAME")
	private String staffName;

	@Column(name="SUB_TYPE")
	private String subType;

	public CollectionDiary() {
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

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
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

	public BigDecimal getChargeReportId() {
		return this.chargeReportId;
	}

	public void setChargeReportId(BigDecimal chargeReportId) {
		this.chargeReportId = chargeReportId;
	}

	public String getChargeReportRowId() {
		return this.chargeReportRowId;
	}

	public void setChargeReportRowId(String chargeReportRowId) {
		this.chargeReportRowId = chargeReportRowId;
	}

	public String getChargeStatus() {
		return this.chargeStatus;
	}

	public void setChargeStatus(String chargeStatus) {
		this.chargeStatus = chargeStatus;
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

	public String getContractFormMngtGroup() {
		return this.contractFormMngtGroup;
	}

	public void setContractFormMngtGroup(String contractFormMngtGroup) {
		this.contractFormMngtGroup = contractFormMngtGroup;
	}

	public String getContractFormMngtName() {
		return this.contractFormMngtName;
	}

	public void setContractFormMngtName(String contractFormMngtName) {
		this.contractFormMngtName = contractFormMngtName;
	}

	public BigDecimal getContractId() {
		return this.contractId;
	}

	public void setContractId(BigDecimal contractId) {
		this.contractId = contractId;
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

	public BigDecimal getIsDaiLy() {
		return this.isDaiLy;
	}

	public void setIsDaiLy(BigDecimal isDaiLy) {
		this.isDaiLy = isDaiLy;
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

	public String getPayAreaCode() {
		return this.payAreaCode;
	}

	public void setPayAreaCode(String payAreaCode) {
		this.payAreaCode = payAreaCode;
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

	public String getServiceTypes() {
		return this.serviceTypes;
	}

	public void setServiceTypes(String serviceTypes) {
		this.serviceTypes = serviceTypes;
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

	public String getSubType() {
		return this.subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}