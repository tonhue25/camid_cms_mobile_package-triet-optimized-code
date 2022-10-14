/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.apigw;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Advertisement
 *
 * @author partner7
 */
@Entity
@Table(name = "CAMID_INVITE_APP")
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    @SequenceGenerator(name = "CAMID_INVITE_APP_SEQ_GENERATOR", sequenceName = "CAMID_INVITE_APP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAMID_INVITE_APP_SEQ_GENERATOR")
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "PARTNER_ID")
    private String partnerId;
    @Column(name = "DEVICE_ID")
    private String deviceId;
    @Column(name = "VERSION_APP")
    private String versionApp;
    @Column(name = "VERSION_DEVICE")
    private String versionDevice;
    @Column(name = "DEVICE_OS")
    private String deviceOS;
    @Column(name = "MODEL_DEVICE")
    private String modelDevice;
    @Column(name = "CAMID")
    private Long camId;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "UPDATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Column(name = "type")
    private String type;

    public Advertisement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getVersionApp() {
        return versionApp;
    }

    public void setVersionApp(String versionApp) {
        this.versionApp = versionApp;
    }

    public String getVersionDevice() {
        return versionDevice;
    }

    public void setVersionDevice(String versionDevice) {
        this.versionDevice = versionDevice;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    public String getModelDevice() {
        return modelDevice;
    }

    public void setModelDevice(String modelDevice) {
        this.modelDevice = modelDevice;
    }

    public Long getCamId() {
        return camId;
    }

    public void setCamId(Long camId) {
        this.camId = camId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
