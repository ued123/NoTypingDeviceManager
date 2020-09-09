package com.ntd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "part")
public class Part {

    @Id
    private long part_id;

    private String part_category;

    private String part_model;

    private String part_manufactor;

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

}
