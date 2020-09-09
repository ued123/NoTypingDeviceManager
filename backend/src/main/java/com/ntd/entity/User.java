package com.ntd.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Id
    private long user_id;

    private String user_name;

    private String user_organ;

    private String user_state;

    private String password;

    private String password_type;

    public long getId() {
        return user_id;
    }

    public void setId(long id) {
        this.user_id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_organ() {
        return user_organ;
    }

    public void setUser_organ(String user_organ) {
        this.user_organ = user_organ;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_type() {
        return password_type;
    }

    public void setPassword_type(String password_type) {
        this.password_type = password_type;
    }
}
