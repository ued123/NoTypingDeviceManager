package com.ntd.entity;


import javax.persistence.*;

@Entity
@Table(name = "user_device_part_relationship")
public class UserDevPartRelation {

    @Id
    private long rel_id;

    @ManyToOne(targetEntity = Device.class,fetch = FetchType.LAZY)
    @JoinColumn(name="device_id")
    private Device device;

    @ManyToOne(targetEntity = Part.class,fetch = FetchType.LAZY)
    @JoinColumn(name="part_id")
    private Part part;

    public long getRel_id() {
        return rel_id;
    }

    public void setRel_id(long rel_id) {
        this.rel_id = rel_id;
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
