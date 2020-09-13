package com.ntd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Id
    @Column(name = "USER_ID")
    private long uesrId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_ORGAN")
    private String userOrgan;

    @Column(name = "USER_STATE")
    private String userState;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PASSWORD_TYPE")
    private String passwordType;

    public long getUesrId() {
        return uesrId;
    }

    public void setUesrId(long uesrId) {
        this.uesrId = uesrId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrgan() {
        return userOrgan;
    }

    public void setUserOrgan(String userOrgan) {
        this.userOrgan = userOrgan;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }
}
