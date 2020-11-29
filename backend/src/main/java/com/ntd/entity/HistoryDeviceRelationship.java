package com.ntd.entity;

import javax.persistence.*;

@Entity
@Table(name = "history_device_relationship")
public class HistoryDeviceRelationship {

	@Id
	@Column(name = "REL_ID")
	private long relId;

	@ManyToOne(targetEntity = Device.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "DEVICE_ID")
	private Device device;

	@ManyToOne(targetEntity = Part.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "HISTORY_ID")
	private UserHistory userHistory;

	public long getRelId() {
		return relId;
	}

	public void setRelId(long relId) {
		this.relId = relId;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public UserHistory getUserHistory() {
		return userHistory;
	}

	public void setUserHistory(UserHistory userHistory) {
		this.userHistory = userHistory;
	}

}
