package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "DONATE_PACKAGE_PRICE")
public class DonatePackagePrice implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "DONATE_PACKAGE_PRICE_SEQ_GENERATOR", sequenceName = "DONATE_PACKAGE_PRICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DONATE_PACKAGE_PRICE_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "DONATE_PACKAGE_ID")
    private Long donatePackageId;
    @Column(name = "PAYMENT_METHOD")
    private String paymentMethod;
    @Column(name = "PRICE")
    private Float price;
    @Column(name = "UNIT")
    private String unit;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "OBJ_DATA1")
    private Integer objData1;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_DATE")
    private Date updatedDate;
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDonatePackageId() {
        return donatePackageId;
    }

    public void setDonatePackageId(Long donatePackageId) {
        this.donatePackageId = donatePackageId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getObjData1() {
        return objData1;
    }

    public void setObjData1(Integer objData1) {
        this.objData1 = objData1;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
