package co.siten.myvtg.model.cmpre;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the REPORT_CONFIG database table.
 * 
 */
@Entity
@Table(name="REPORT_CONFIG")
@NamedQuery(name="ReportConfig.findAll", query="SELECT r FROM ReportConfig r")
public class ReportConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="REPORT_ID")
	private String reportId;

	@Column(name="COLUMN_WRITE_DATA_KIND")
	private String columnWriteDataKind;

	@Column(name="COLUMN_WRITE_DATA_STYLE")
	private String columnWriteDataStyle;

	@Column(name="DB_OWNER")
	private BigDecimal dbOwner;

	@Column(name="MAKE_FILE_NAME_DATE_FORMAT")
	private String makeFileNameDateFormat;

	@Column(name="NO_COLUMN")
	private BigDecimal noColumn;

	private String note;

	@Column(name="REPORT_COMMENT")
	private String reportComment;

	@Column(name="REPORT_CREATE_TIME")
	private Object reportCreateTime;

	@Column(name="REPORT_CREATER")
	private String reportCreater;

	@Column(name="REPORT_DATE")
	private String reportDate;

	@Column(name="REPORT_DATE_FORMAT")
	private String reportDateFormat;

	@Column(name="REPORT_NAME")
	private String reportName;

	@Column(name="REPORT_PARAM")
	private String reportParam;

	@Column(name="REPORT_QUERY")
	private String reportQuery;

	@Column(name="REPORT_STAGE")
	private BigDecimal reportStage;

	@Column(name="REPORT_STAGE_FIELD")
	private String reportStageField;

	@Column(name="REPORT_UPDATE_TIME")
	private Object reportUpdateTime;

	@Column(name="REPORT_UPDATER")
	private String reportUpdater;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="SHEET_MAX_ROW")
	private BigDecimal sheetMaxRow;

	@Column(name="START_COLUMN_DATA")
	private BigDecimal startColumnData;

	@Column(name="START_ROW")
	private BigDecimal startRow;

	@Column(name="TEMPLATE_DIR")
	private String templateDir;

	@Column(name="TEMPLATE_FILE_NAME")
	private String templateFileName;

	@Column(name="TOTAL_COLUMN")
	private BigDecimal totalColumn;

	@Column(name="TOTAL_DATA_COLUMN")
	private BigDecimal totalDataColumn;

	@Column(name="TOTAL_GAP")
	private BigDecimal totalGap;

	@Column(name="TOTAL_NAME")
	private String totalName;

	public ReportConfig() {
	}

	public String getReportId() {
		return this.reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getColumnWriteDataKind() {
		return this.columnWriteDataKind;
	}

	public void setColumnWriteDataKind(String columnWriteDataKind) {
		this.columnWriteDataKind = columnWriteDataKind;
	}

	public String getColumnWriteDataStyle() {
		return this.columnWriteDataStyle;
	}

	public void setColumnWriteDataStyle(String columnWriteDataStyle) {
		this.columnWriteDataStyle = columnWriteDataStyle;
	}

	public BigDecimal getDbOwner() {
		return this.dbOwner;
	}

	public void setDbOwner(BigDecimal dbOwner) {
		this.dbOwner = dbOwner;
	}

	public String getMakeFileNameDateFormat() {
		return this.makeFileNameDateFormat;
	}

	public void setMakeFileNameDateFormat(String makeFileNameDateFormat) {
		this.makeFileNameDateFormat = makeFileNameDateFormat;
	}

	public BigDecimal getNoColumn() {
		return this.noColumn;
	}

	public void setNoColumn(BigDecimal noColumn) {
		this.noColumn = noColumn;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getReportComment() {
		return this.reportComment;
	}

	public void setReportComment(String reportComment) {
		this.reportComment = reportComment;
	}

	public Object getReportCreateTime() {
		return this.reportCreateTime;
	}

	public void setReportCreateTime(Object reportCreateTime) {
		this.reportCreateTime = reportCreateTime;
	}

	public String getReportCreater() {
		return this.reportCreater;
	}

	public void setReportCreater(String reportCreater) {
		this.reportCreater = reportCreater;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportDateFormat() {
		return this.reportDateFormat;
	}

	public void setReportDateFormat(String reportDateFormat) {
		this.reportDateFormat = reportDateFormat;
	}

	public String getReportName() {
		return this.reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportParam() {
		return this.reportParam;
	}

	public void setReportParam(String reportParam) {
		this.reportParam = reportParam;
	}

	public String getReportQuery() {
		return this.reportQuery;
	}

	public void setReportQuery(String reportQuery) {
		this.reportQuery = reportQuery;
	}

	public BigDecimal getReportStage() {
		return this.reportStage;
	}

	public void setReportStage(BigDecimal reportStage) {
		this.reportStage = reportStage;
	}

	public String getReportStageField() {
		return this.reportStageField;
	}

	public void setReportStageField(String reportStageField) {
		this.reportStageField = reportStageField;
	}

	public Object getReportUpdateTime() {
		return this.reportUpdateTime;
	}

	public void setReportUpdateTime(Object reportUpdateTime) {
		this.reportUpdateTime = reportUpdateTime;
	}

	public String getReportUpdater() {
		return this.reportUpdater;
	}

	public void setReportUpdater(String reportUpdater) {
		this.reportUpdater = reportUpdater;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public BigDecimal getSheetMaxRow() {
		return this.sheetMaxRow;
	}

	public void setSheetMaxRow(BigDecimal sheetMaxRow) {
		this.sheetMaxRow = sheetMaxRow;
	}

	public BigDecimal getStartColumnData() {
		return this.startColumnData;
	}

	public void setStartColumnData(BigDecimal startColumnData) {
		this.startColumnData = startColumnData;
	}

	public BigDecimal getStartRow() {
		return this.startRow;
	}

	public void setStartRow(BigDecimal startRow) {
		this.startRow = startRow;
	}

	public String getTemplateDir() {
		return this.templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public String getTemplateFileName() {
		return this.templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public BigDecimal getTotalColumn() {
		return this.totalColumn;
	}

	public void setTotalColumn(BigDecimal totalColumn) {
		this.totalColumn = totalColumn;
	}

	public BigDecimal getTotalDataColumn() {
		return this.totalDataColumn;
	}

	public void setTotalDataColumn(BigDecimal totalDataColumn) {
		this.totalDataColumn = totalDataColumn;
	}

	public BigDecimal getTotalGap() {
		return this.totalGap;
	}

	public void setTotalGap(BigDecimal totalGap) {
		this.totalGap = totalGap;
	}

	public String getTotalName() {
		return this.totalName;
	}

	public void setTotalName(String totalName) {
		this.totalName = totalName;
	}

}