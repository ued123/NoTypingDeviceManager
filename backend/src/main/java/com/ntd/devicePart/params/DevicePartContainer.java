package com.ntd.devicePart.params;

import com.ntd.common.constant.Characters;

/**
 * DEVICE-PART 데이터들 담을수 있는 클래스
 * 
 * @author HoYa
 *
 */
public class DevicePartContainer {

    private long part_id;

    private String part_category = Characters.BLANK;

    private String part_model = Characters.BLANK;

    private String part_manufactor = Characters.BLANK;

    private long device_id;

    private String device_category = Characters.BLANK;

    private String device_model = Characters.BLANK;

    private String device_serial_number = Characters.BLANK;

    private String cpu_info = Characters.BLANK;

    private String ram_info = Characters.BLANK;

    private String volume_info = Characters.BLANK;

    private String device_info = Characters.BLANK;

    public long getPart_id() {
        return part_id;
    }

    public void setPart_id(long part_id) {
        this.part_id = part_id;
    }

    public String getPart_category() {
        return part_category;
    }

    public void setPart_category(String part_category) {
        this.part_category = part_category;
    }

    public String getPart_model() {
        return part_model;
    }

    public void setPart_model(String part_model) {
        this.part_model = part_model;
    }

    public String getPart_manufactor() {
        return part_manufactor;
    }

    public void setPart_manufactor(String part_manufactor) {
        this.part_manufactor = part_manufactor;
    }

    public long getDevice_id() {
        return device_id;
    }

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
