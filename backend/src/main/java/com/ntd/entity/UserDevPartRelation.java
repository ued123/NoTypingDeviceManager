package com.ntd.entity;


import javax.persistence.*;

@Entity
@Table(name = "user_device_part_relationship")
public class UserDevPartRelation {

    @Id
    @Column(name = "REL_ID")
    private long relId;

    @ManyToOne(targetEntity = Device.class,fetch = FetchType.LAZY)
    @JoinColumn(name="DEVICE_ID")
    private Device device;

    @ManyToOne(targetEntity = Part.class,fetch = FetchType.LAZY)
    @JoinColumn(name="PART_ID")
    private Part part;

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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
