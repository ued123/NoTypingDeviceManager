package com.ntd.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "part")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Part  implements DevicePartEntityBinder{

	@Id
	@Column(name = "part_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long partId;

	@Column(name = "part_category")
	private String partCategory;

	@Column(name = "part_model")
	private String partModel;

	@Column(name = "part_manufactor")
	private String partManufactor;

    @ManyToMany(mappedBy = "parts", fetch = FetchType.LAZY)
    private Set<Device> devices = new HashSet<>();

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

	@Override
	public String obtainDeviceOrPartModel() {
		return this.partModel;
	}

}
