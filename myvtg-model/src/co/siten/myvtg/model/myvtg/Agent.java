package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AGENT")
@NamedQuery(name = "Agent.findAll", query = "SELECT a FROM Agent a")
public class Agent extends AbstractEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "AGENT_2", allocationSize = 1)
    @Id
    private Long id;

    @Column(name = "CAM_ID")
    private Integer camId;

    @Column(name = "FULL_NAME",nullable = false, length = 400)
    private String fullName;

    @Column(name = "PHONE_NUMBER",nullable = false, length = 255)
    private String phoneNumber;

    @Column(name = "EMPLOYEE_CODE",nullable = false, length = 50)
    private String employeeCode;

    @Column(name = "EMPLOYEE_TYPE")
    private Integer employeeType;

    @Column(name = "POSITION",nullable = false, length = 255)
    private String position;

    @Column(name = "DEPARTMENT",nullable = false, length = 255)
    private String department;

    @Column(name = "EMONEY_ACCOUNT_NUMBER",nullable = false, length = 255)
    private String emoneyAccountNumber;

    @Column(name = "SHOWROOM_NAME",nullable = false, length = 255)
    private String showroomName;

    @Column(name = "SHOWROOM_ADDRESS",nullable = false, length = 255)
    private String showroomAddress;

    @Column(name = "NOTE",nullable = false, length = 2000)
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCamId() {
        return camId;
    }

    public void setCamId(Integer camId) {
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
