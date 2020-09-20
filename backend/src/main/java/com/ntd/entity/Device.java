package com.ntd.entity;

import javax.persistence.*;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @Column(name = "device_id")
    private long deviceId;

    @Column(name = "device_category")
    private String deviceCategory;

    @Column(name = "device_model")
    private String deviceModel;

    @Column(name = "device_serial_number")
    private String deviceSerialNumber;

    @Column(name = "cpu_info")
    private String cpuInfo;

    @Column(name = "ram_info")
    private String ramInfo;

    @Column(name = "volume_info")
    private String volumeInfo;

    @Column(name ="device_info")
    private String deviceInfo;

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCategory() {
        return deviceCategory;
    }

    public void setDeviceCategory(String deviceCategory) {
        this.deviceCategory = deviceCategory;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public String getCpuInfo() {
        return cpuInfo;
    }

    public void setCpuInfo(String cpuInfo) {
        this.cpuInfo = cpuInfo;
    }

    public String getRamInfo() {
        return ramInfo;
    }

    public void setRamInfo(String ramInfo) {
        this.ramInfo = ramInfo;
    }

    public String getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(String volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }
}
