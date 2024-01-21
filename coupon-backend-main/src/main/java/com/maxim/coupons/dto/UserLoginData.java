package com.maxim.coupons.dto;

public class UserLoginData {
    private String userName;
    private String password;

    public UserLoginData() {
    }

    public UserLoginData(String user, String password) {
        this.userName = user;
        this.password = password;
    }

    public String getUser() {
        return userName;
    }

    public void setUser(String user) {
        this.userName = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
