package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DETAIL_COLLECTION_GROUP database table.
 * 
 */
@Entity
@Table(name="DETAIL_COLLECTION_GROUP")
@NamedQuery(name="DetailCollectionGroup.findAll", query="SELECT d FROM DetailCollectionGroup d")
public class DetailCollectionGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="AREA_ID")
	private BigDecimal areaId;

	@Column(name="AREA_NAME")
	private String areaName;

	private String assigned;

	@Column(name="CENTER_CODE")
	private BigDecimal centerCode;

	@Column(name="CENTER_ID")
	private BigDecimal centerId;

	@Column(name="CENTER_NAME")
	private String centerName;

	@Column(name="COLLECTION_GROUP_ID")
	private BigDecimal collectionGroupId;

	@Column(name="COMPANY_ID")
	private BigDecimal companyId;

	@Column(name="COMPANY_NAME")
	private String companyName;

	@Column(name="DEPARTMENT_ID")
	private BigDecimal departmentId;

	@Column(name="DEPARTMENT_NAME")
	private String departmentName;

	@Column(name="DEPARTMENT_TYPE")
	private String departmentType;

	@Column(name="GROUP_CODE")
	private String groupCode;

	@Column(name="GROUP_SAM_TYPE")
	private String groupSamType;

	@Column(name="GROUP_TYPE")
	private String groupType;

	private String name;

	@Column(name="PARENT_GROUP_ID")
	private BigDecimal parentGroupId;

	@Column(name="\"PATH\"")
	private String path;

	@Column(name="PATH_NAME")
	private String pathName;

	@Column(name="PAY_AREA_CODE")
	private String payAreaCode;

	private String status;

	private BigDecimal thelevel;

	public DetailCollectionGroup() {
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

	public String getAssigned() {
		return this.assigned;
	}

	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public BigDecimal getCenterCode() {
		return this.centerCode;
	}

	public void setCenterCode(BigDecimal centerCode) {
		this.centerCode = centerCode;
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

	public BigDecimal getCollectionGroupId() {
		return this.collectionGroupId;
	}

	public void setCollectionGroupId(BigDecimal collectionGroupId) {
		this.collectionGroupId = collectionGroupId;
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

	public String getDepartmentType() {
		return this.departmentType;
	}

	public void setDepartmentType(String departmentType) {
		this.departmentType = departmentType;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupSamType() {
		return this.groupSamType;
	}

	public void setGroupSamType(String groupSamType) {
		this.groupSamType = groupSamType;
	}

	public String getGroupType() {
		return this.groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getParentGroupId() {
		return this.parentGroupId;
	}

	public void setParentGroupId(BigDecimal parentGroupId) {
		this.parentGroupId = parentGroupId;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getThelevel() {
		return this.thelevel;
	}

	public void setThelevel(BigDecimal thelevel) {
		this.thelevel = thelevel;
	}

}