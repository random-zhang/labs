package com.jie.bean;

public class User {
    private String userPhone;

    private String userName;

    private String userPassword;

    private String userId;

    private byte[] userPortrait;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public byte[] getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(byte[] userPortrait) {
        this.userPortrait = userPortrait;
    }
}