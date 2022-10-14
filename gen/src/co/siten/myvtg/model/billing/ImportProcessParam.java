package co.siten.myvtg.model.billing;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the IMPORT_PROCESS_PARAM database table.
 * 
 */
@Entity
@Table(name="IMPORT_PROCESS_PARAM")
@NamedQuery(name="ImportProcessParam.findAll", query="SELECT i FROM ImportProcessParam i")
public class ImportProcessParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IMPORT_PROCESS_PARAM_ID")
	private long importProcessParamId;

	@Column(name="CDR_OBJECT")
	private String cdrObject;

	@Column(name="CURRENT_SEQ")
	private BigDecimal currentSeq;

	@Temporal(TemporalType.DATE)
	@Column(name="\"CURRENT_TIME\"")
	private Date currentTime;

	@Column(name="DELAY_TIME")
	private BigDecimal delayTime;

	private String description;

	@Column(name="DOWNLOAD_OPTION")
	private BigDecimal downloadOption;

	private String drive;

	private String extension;

	@Column(name="FILE_PATTERN")
	private String filePattern;

	@Column(name="FILE_PATTERN_OK")
	private String filePatternOk;

	@Column(name="FTP_BACKUP_DIR")
	private String ftpBackupDir;

	@Column(name="FTP_HOST")
	private String ftpHost;

	@Column(name="FTP_PASSWORD")
	private String ftpPassword;

	@Column(name="FTP_USERNAME")
	private String ftpUsername;

	@Column(name="IS_DELETE")
	private BigDecimal isDelete;

	@Column(name="KEY_SEARCH")
	private BigDecimal keySearch;

	@Column(name="LIMITED_FILE")
	private BigDecimal limitedFile;

	@Column(name="LOCAL_BACKUP_DIR")
	private String localBackupDir;

	@Column(name="LOCAL_DIR")
	private String localDir;

	@Column(name="LOCAL_RATED_DIR")
	private String localRatedDir;

	@Column(name="LOCAL_UNRATED_DIR")
	private String localUnratedDir;

	@Column(name="LOCAL_UPDATE_DIR")
	private String localUpdateDir;

	@Column(name="LOG_FILE_PATH")
	private String logFilePath;

	@Column(name="MAX_SEQ")
	private BigDecimal maxSeq;

	@Column(name="MIN_SEQ")
	private BigDecimal minSeq;

	@Column(name="NUMBER_OF_COMMIT")
	private BigDecimal numberOfCommit;

	@Column(name="PARAM_COUNT")
	private BigDecimal paramCount;

	@Column(name="PORT_GROUP")
	private BigDecimal portGroup;

	@Column(name="PROCESS_MANAGER_CODE")
	private String processManagerCode;

	@Column(name="SERVER_DIR")
	private String serverDir;

	@Column(name="SERVICE_CODE")
	private String serviceCode;

	@Column(name="SERVICE_PARAM_FILE")
	private String serviceParamFile;

	@Column(name="SERVICE_TYPE")
	private String serviceType;

	@Column(name="TABLE_NAME")
	private String tableName;

	@Column(name="TYPES_OF_PAYMENT")
	private String typesOfPayment;

	public ImportProcessParam() {
	}

	public long getImportProcessParamId() {
		return this.importProcessParamId;
	}

	public void setImportProcessParamId(long importProcessParamId) {
		this.importProcessParamId = importProcessParamId;
	}

	public String getCdrObject() {
		return this.cdrObject;
	}

	public void setCdrObject(String cdrObject) {
		this.cdrObject = cdrObject;
	}

	public BigDecimal getCurrentSeq() {
		return this.currentSeq;
	}

	public void setCurrentSeq(BigDecimal currentSeq) {
		this.currentSeq = currentSeq;
	}

	public Date getCurrentTime() {
		return this.currentTime;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}

	public BigDecimal getDelayTime() {
		return this.delayTime;
	}

	public void setDelayTime(BigDecimal delayTime) {
		this.delayTime = delayTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDownloadOption() {
		return this.downloadOption;
	}

	public void setDownloadOption(BigDecimal downloadOption) {
		this.downloadOption = downloadOption;
	}

	public String getDrive() {
		return this.drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getFilePattern() {
		return this.filePattern;
	}

	public void setFilePattern(String filePattern) {
		this.filePattern = filePattern;
	}

	public String getFilePatternOk() {
		return this.filePatternOk;
	}

	public void setFilePatternOk(String filePatternOk) {
		this.filePatternOk = filePatternOk;
	}

	public String getFtpBackupDir() {
		return this.ftpBackupDir;
	}

	public void setFtpBackupDir(String ftpBackupDir) {
		this.ftpBackupDir = ftpBackupDir;
	}

	public String getFtpHost() {
		return this.ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	public String getFtpPassword() {
		return this.ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public String getFtpUsername() {
		return this.ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public BigDecimal getIsDelete() {
		return this.isDelete;
	}

	public void setIsDelete(BigDecimal isDelete) {
		this.isDelete = isDelete;
	}

	public BigDecimal getKeySearch() {
		return this.keySearch;
	}

	public void setKeySearch(BigDecimal keySearch) {
		this.keySearch = keySearch;
	}

	public BigDecimal getLimitedFile() {
		return this.limitedFile;
	}

	public void setLimitedFile(BigDecimal limitedFile) {
		this.limitedFile = limitedFile;
	}

	public String getLocalBackupDir() {
		return this.localBackupDir;
	}

	public void setLocalBackupDir(String localBackupDir) {
		this.localBackupDir = localBackupDir;
	}

	public String getLocalDir() {
		return this.localDir;
	}

	public void setLocalDir(String localDir) {
		this.localDir = localDir;
	}

	public String getLocalRatedDir() {
		return this.localRatedDir;
	}

	public void setLocalRatedDir(String localRatedDir) {
		this.localRatedDir = localRatedDir;
	}

	public String getLocalUnratedDir() {
		return this.localUnratedDir;
	}

	public void setLocalUnratedDir(String localUnratedDir) {
		this.localUnratedDir = localUnratedDir;
	}

	public String getLocalUpdateDir() {
		return this.localUpdateDir;
	}

	public void setLocalUpdateDir(String localUpdateDir) {
		this.localUpdateDir = localUpdateDir;
	}

	public String getLogFilePath() {
		return this.logFilePath;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	public BigDecimal getMaxSeq() {
		return this.maxSeq;
	}

	public void setMaxSeq(BigDecimal maxSeq) {
		this.maxSeq = maxSeq;
	}

	public BigDecimal getMinSeq() {
		return this.minSeq;
	}

	public void setMinSeq(BigDecimal minSeq) {
		this.minSeq = minSeq;
	}

	public BigDecimal getNumberOfCommit() {
		return this.numberOfCommit;
	}

	public void setNumberOfCommit(BigDecimal numberOfCommit) {
		this.numberOfCommit = numberOfCommit;
	}

	public BigDecimal getParamCount() {
		return this.paramCount;
	}

	public void setParamCount(BigDecimal paramCount) {
		this.paramCount = paramCount;
	}

	public BigDecimal getPortGroup() {
		return this.portGroup;
	}

	public void setPortGroup(BigDecimal portGroup) {
		this.portGroup = portGroup;
	}

	public String getProcessManagerCode() {
		return this.processManagerCode;
	}

	public void setProcessManagerCode(String processManagerCode) {
		this.processManagerCode = processManagerCode;
	}

	public String getServerDir() {
		return this.serverDir;
	}

	public void setServerDir(String serverDir) {
		this.serverDir = serverDir;
	}

	public String getServiceCode() {
		return this.serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceParamFile() {
		return this.serviceParamFile;
	}

	public void setServiceParamFile(String serviceParamFile) {
		this.serviceParamFile = serviceParamFile;
	}

	public String getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTypesOfPayment() {
		return this.typesOfPayment;
	}

	public void setTypesOfPayment(String typesOfPayment) {
		this.typesOfPayment = typesOfPayment;
	}

}