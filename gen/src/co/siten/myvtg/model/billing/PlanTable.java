package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PLAN_TABLE database table.
 * 
 */
@Entity
@Table(name="PLAN_TABLE")
@NamedQuery(name="PlanTable.findAll", query="SELECT p FROM PlanTable p")
public class PlanTable implements Serializable {
	private static final long serialVersionUID = 1L;

	private BigDecimal bytes;

	private BigDecimal cardinality;

	private BigDecimal cost;

	@Column(name="CPU_COST")
	private BigDecimal cpuCost;

	private String distribution;

	private BigDecimal id;

	@Column(name="IO_COST")
	private BigDecimal ioCost;

	@Column(name="OBJECT_INSTANCE")
	private BigDecimal objectInstance;

	@Column(name="OBJECT_NAME")
	private String objectName;

	@Column(name="OBJECT_NODE")
	private String objectNode;

	@Column(name="OBJECT_OWNER")
	private String objectOwner;

	@Column(name="OBJECT_TYPE")
	private String objectType;

	@Column(name="\"OPERATION\"")
	private String operation;

	private String optimizer;

	private String options;

	@Lob
	private String other;

	@Column(name="OTHER_TAG")
	private String otherTag;

	@Column(name="PARENT_ID")
	private BigDecimal parentId;

	@Column(name="PARTITION_ID")
	private BigDecimal partitionId;

	@Column(name="PARTITION_START")
	private String partitionStart;

	@Column(name="PARTITION_STOP")
	private String partitionStop;

	@Column(name="\"POSITION\"")
	private BigDecimal position;

	private String remarks;

	@Column(name="SEARCH_COLUMNS")
	private BigDecimal searchColumns;

	@Column(name="STATEMENT_ID")
	private String statementId;

	@Column(name="TEMP_SPACE")
	private BigDecimal tempSpace;

	@Temporal(TemporalType.DATE)
	@Column(name="\"TIMESTAMP\"")
	private Date timestamp;

	public PlanTable() {
	}

	public BigDecimal getBytes() {
		return this.bytes;
	}

	public void setBytes(BigDecimal bytes) {
		this.bytes = bytes;
	}

	public BigDecimal getCardinality() {
		return this.cardinality;
	}

	public void setCardinality(BigDecimal cardinality) {
		this.cardinality = cardinality;
	}

	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getCpuCost() {
		return this.cpuCost;
	}

	public void setCpuCost(BigDecimal cpuCost) {
		this.cpuCost = cpuCost;
	}

	public String getDistribution() {
		return this.distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIoCost() {
		return this.ioCost;
	}

	public void setIoCost(BigDecimal ioCost) {
		this.ioCost = ioCost;
	}

	public BigDecimal getObjectInstance() {
		return this.objectInstance;
	}

	public void setObjectInstance(BigDecimal objectInstance) {
		this.objectInstance = objectInstance;
	}

	public String getObjectName() {
		return this.objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectNode() {
		return this.objectNode;
	}

	public void setObjectNode(String objectNode) {
		this.objectNode = objectNode;
	}

	public String getObjectOwner() {
		return this.objectOwner;
	}

	public void setObjectOwner(String objectOwner) {
		this.objectOwner = objectOwner;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getOptimizer() {
		return this.optimizer;
	}

	public void setOptimizer(String optimizer) {
		this.optimizer = optimizer;
	}

	public String getOptions() {
		return this.options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getOtherTag() {
		return this.otherTag;
	}

	public void setOtherTag(String otherTag) {
		this.otherTag = otherTag;
	}

	public BigDecimal getParentId() {
		return this.parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

	public BigDecimal getPartitionId() {
		return this.partitionId;
	}

	public void setPartitionId(BigDecimal partitionId) {
		this.partitionId = partitionId;
	}

	public String getPartitionStart() {
		return this.partitionStart;
	}

	public void setPartitionStart(String partitionStart) {
		this.partitionStart = partitionStart;
	}

	public String getPartitionStop() {
		return this.partitionStop;
	}

	public void setPartitionStop(String partitionStop) {
		this.partitionStop = partitionStop;
	}

	public BigDecimal getPosition() {
		return this.position;
	}

	public void setPosition(BigDecimal position) {
		this.position = position;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getSearchColumns() {
		return this.searchColumns;
	}

	public void setSearchColumns(BigDecimal searchColumns) {
		this.searchColumns = searchColumns;
	}

	public String getStatementId() {
		return this.statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public BigDecimal getTempSpace() {
		return this.tempSpace;
	}

	public void setTempSpace(BigDecimal tempSpace) {
		this.tempSpace = tempSpace;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}