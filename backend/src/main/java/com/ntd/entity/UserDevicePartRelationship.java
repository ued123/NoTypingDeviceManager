package com.ntd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 장비-부품 관계 테이블과 매핑되는 Entity
 * 
 * @author hoya
 *
 */
@Entity
@Table(name = "user_device_part_relationship")
public class UserDevicePartRelationship implements DevicePartEntityBinder {

	@Id
	@Column(name = "rel_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long relId;

	@Column(name = "device_id")
	private long deviceId;

	@Column(name = "part_id")
	private long partId;

	public long getRelId() {
		return relId;
	}

	public void setRelId(long relId) {
		this.relId = relId;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

	public long getPartId() {
		return partId;
	}

	public void setPartId(long partId) {
		this.partId = partId;
	}

	@Override
	public String obtainDeviceOrPartModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
