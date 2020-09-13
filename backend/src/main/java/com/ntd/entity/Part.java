package com.ntd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "part")
public class Part {

    @Id
    @Column(name = "PARD_ID")
    private long partId;

    @Column(name = "PART_CATEGORY")
    private String partCategory;

    @Column(name = "PART_MODEL")
    private String partModel;

    @Column(name = "PART_MANUFACTOR")
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
