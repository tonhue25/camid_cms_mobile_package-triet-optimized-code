
package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffBean {

    String camId;
    String fullName;
    String phoneNumber;
    String position;
    String department;
    String eMoneyAccountNumber;
    String showroomName;
    String showroomAddress;
    String note;

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

    public String geteMoneyAccountNumber() {
        return eMoneyAccountNumber;
    }

    public void seteMoneyAccountNumber(String eMoneyAccountNumber) {
        this.eMoneyAccountNumber = eMoneyAccountNumber;
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

    public String getCamId() {
        return camId;
    }

    public void setCamId(String camId) {
        this.camId = camId;
    }
}
