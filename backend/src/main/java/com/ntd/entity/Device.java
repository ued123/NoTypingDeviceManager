package com.ntd.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "device")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Device implements DevicePartEntityBinder{

	@Id
	@Column(name = "device_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	@Column(name = "device_info")
	private String deviceInfo;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "user_device_part_relationship", joinColumns = {
			@JoinColumn(name = "device_id", referencedColumnName = "device_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "part_id", referencedColumnName = "part_id", nullable = false, updatable = false) })
	private Set<Part> parts = new HashSet<>();

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

	public Set<Part> getParts() {
		return parts;
	}

	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}

	@Override
	public String obtainDeviceOrPartModel() {
		return this.deviceModel;
	}

}
