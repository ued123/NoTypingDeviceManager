package com.ntd.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "device")
public class Device {

    @Id
    private long device_id;

    private String device_category;

    private String device_model;

    private String device_serial_number;

    private String cpu_info;

    private String ram_info;

    private String volume_info;

    private String device_info;
//
//    @OneToMany(mappedBy = "device") //참조를 당하는 쪽에서 읽기만 가능!
//    private Collection<User> members;
//
//    public Collection<Device> getMembers() {
//        return members;
//    }

   /* public void setMembers(Collection<Device> members) {
        this.members = members;
    }*/
//
   /* public long getDevice_id() {
        return device_id;
    }*/

    public void setDevice_id(long device_id) {
        this.device_id = device_id;
    }

    public String getDevice_category() {
        return device_category;
    }

    public void setDevice_category(String device_category) {
        this.device_category = device_category;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getDevice_serial_number() {
        return device_serial_number;
    }

    public void setDevice_serial_number(String device_serial_number) {
        this.device_serial_number = device_serial_number;
    }

    public String getCpu_info() {
        return cpu_info;
    }

    public void setCpu_info(String cpu_info) {
        this.cpu_info = cpu_info;
    }

    public String getRam_info() {
        return ram_info;
    }

    public void setRam_info(String ram_info) {
        this.ram_info = ram_info;
    }

    public String getVolume_info() {
        return volume_info;
    }

    public void setVolume_info(String volume_info) {
        this.volume_info = volume_info;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }
}
