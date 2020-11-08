package com.ntd.user.params;

import com.ntd.common.constant.Characters;

/**
 * USER 정보 데이터들 담을수 있는 클래스
 * 
 * @author HoYa
 *
 */
public class UserContainer {

    private long uesrId;

    private String userName = Characters.BLANK;;

    private String userOrgan = Characters.BLANK;;

    private String userState = Characters.BLANK;;

    private String password = Characters.BLANK;;

    private String passwordType = Characters.BLANK;;

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
