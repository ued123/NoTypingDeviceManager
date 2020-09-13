package com.ntd.entity;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "device")
public class Device {

    @Id
    @Column(name = "DEVICE_ID")
    private long deviceId;

    @Column(name = "DEVICE_CATEGORY")
    private String deviceCategory;

    @Column(name = "DEVICE_MODEL")
    private String deviceModel;

    @Column(name = "DEVICE_SERIAL_NUMBER")
    private String deviceSerialNumber;

    @Column(name = "CPU_INFO")
    private String cpuInfo;

    @Column(name = "RAM_INFO")
    private String ramInfo;

    @Column(name = "VOLUME_INFO")
    private String volumeInfo;

    @Column(name ="DEVICE_INFO")
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
