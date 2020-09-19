package com.ntd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "part")
public class Part {

    @Id
    @Column(name = "part_id")
    private long partId;

    @Column(name = "part_category")
    private String partCategory;

    @Column(name = "part_model")
    private String partModel;

    @Column(name = "part_manufactor")
    private String partManufactor;

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
}
