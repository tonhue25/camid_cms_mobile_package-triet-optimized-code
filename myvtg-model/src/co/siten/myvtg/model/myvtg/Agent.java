package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "AGENT")
@NamedQuery(name = "Agent.findAll", query = "SELECT a FROM Agent a")
public class Agent implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "AGENT_SEQ", allocationSize = 1)
    @Id
    private Long  id;

    @Column(name = "CAM_ID")
    private Long  camId;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "EMPLOYEE_CODE")
    private String employeeCode;

    @Column(name = "EMPLOYEE_TYPE")
    private Integer employeeType;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "EMONEY_ACCOUNT_NUMBER")
    private String emoneyAccountNumber;

    @Column(name = "SHOWROOM_NAME")
    private String showroomName;

    @Column(name = "SHOWROOM_ADDRESS")
    private String showroomAddress;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_TIME")
    private Date createdTime;

    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "LAST_UPDATED_TIME")
    private Date lastUpdatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCamId() {
        return camId;
    }

    public void setCamId(Long camId) {
        this.camId = camId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public Integer getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(Integer employeeType) {
        this.employeeType = employeeType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmoneyAccountNumber() {
        return emoneyAccountNumber;
    }

    public void setEmoneyAccountNumber(String emoneyAccountNumber) {
        this.emoneyAccountNumber = emoneyAccountNumber;
    }

    public String getShowroomName() {
        return showroomName;
    }

    public void setShowroomName(String showroomName) {
        this.showroomName = showroomName;
    }

    public String getShowroomAddress() {
        return showroomAddress;
    }

    public void setShowroomAddress(String showroomAddress) {
        this.showroomAddress = showroomAddress;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
