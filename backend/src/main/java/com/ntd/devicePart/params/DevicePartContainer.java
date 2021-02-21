package com.ntd.devicePart.params;

import java.util.HashSet;
import java.util.Set;

import com.ntd.common.constant.Characters;
import com.ntd.entity.Device;
import com.ntd.entity.Part;

/**
 * DEVICE-PART 데이터들 담을수 있는 클래스
 * 
 * @author HoYa
 *
 */
public class DevicePartContainer {

	private long partId;

	private String partCategory = Characters.BLANK;

	private String partModel = Characters.BLANK;

	private String partManufactor = Characters.BLANK;

	private long deviceId;

	private String deviceCategory = Characters.BLANK;

	private String deviceModel = Characters.BLANK;

	private String deviceSerialNumber = Characters.BLANK;

	private String cpuInfo = Characters.BLANK;

	private String ramInfo = Characters.BLANK;

	private String volumeInfo = Characters.BLANK;

	private String deviceInfo = Characters.BLANK;

	private boolean doSearchDefault = false;

	private Set<Part> parts = new HashSet<>();

	public long getPartId() {
		return partId;
	}

	public void setPartId(long partId) {
		this.partId = partId;
	}

	public String getPartCategory() {
		return partCategory;
	}

	public void setPartCategory(String partCategory) {
		this.partCategory = partCategory;
	}

	public String getPartModel() {
		return partModel;
	}

	public void setPartModel(String partModel) {
		this.partModel = partModel;
	}

	public String getPartManufactor() {
		return partManufactor;
	}

	public void setPartManufactor(String partManufactor) {
		this.partManufactor = partManufactor;
	}

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

	public boolean isDoSearchDefault() {
		return doSearchDefault;
	}

	public void setDoSearchDefault(boolean doSearchDefault) {
		this.doSearchDefault = doSearchDefault;
	}

	public Set<Part> getParts() {
		return parts;
	}

	public void setParts(Set<Part> parts) {
		this.parts = parts;
	}

	public void addPart(Part part) {
		this.parts.add(part);
	}

	public void setDevice(Device device) {
		if (device == null) {
			return;
		}
		this.deviceId = device.getDeviceId();
		this.deviceModel = device.getDeviceModel();
		this.deviceInfo = device.getDeviceInfo();
		this.deviceSerialNumber = device.getDeviceSerialNumber();
		this.deviceCategory = device.getDeviceCategory();
		this.cpuInfo = device.getCpuInfo();
		this.ramInfo = device.getRamInfo();
		this.volumeInfo = device.getVolumeInfo();
		this.parts = device.getParts();
	}

	public void setPart(Part part) {
		if (part == null) {
			return;
		}
		this.partId = part.getPartId();
		this.partModel = part.getPartModel();
		this.partCategory = part.getPartCategory();
		this.partManufactor = part.getPartManufactor();
	}

	public Part convertPart() {
		Part part = new Part();
		part.setPartId(this.partId);
		part.setPartModel(this.partModel);
		part.setPartCategory(this.partCategory);
		part.setPartManufactor(this.partManufactor);
		return part;
	}

	public Device convertDevice() {
		Device device = new Device();
		device.setDeviceId(deviceId);
		device.setDeviceModel(this.deviceModel);
		device.setDeviceCategory(this.deviceCategory);
		device.setDeviceSerialNumber(this.deviceSerialNumber);
		device.setCpuInfo(this.cpuInfo);
		device.setRamInfo(this.ramInfo);
		device.setVolumeInfo(this.volumeInfo);
		device.setDeviceInfo(this.deviceInfo);
		return device;
	}

	public boolean paramsEmptyByPart() {

		return (partCategory.length() + partManufactor.length() + partModel.length()) == 0;
	}

	public boolean paramsEmptyByDevice() {

		return (deviceCategory.length() + deviceSerialNumber.length() + deviceModel.length() + cpuInfo.length()
				+ ramInfo.length() + volumeInfo.length() + deviceInfo.length()) == 0;
	}

}
