package com.maxim.coupons.dto;

import com.maxim.coupons.enums.UserType;

import java.util.Objects;

public class User {
    private int id;
    private String userName;
    private String password;
    private UserType userType;
    private Integer CompanyId;


    public User(int id, String userName, String password, UserType userType, Integer companyId) {
        this(userName, password, userType, companyId);
        this.id = id;
    }

    public User(String userName, String password, UserType userType, Integer companyId) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.CompanyId = companyId;
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public Integer getCompanyId() {
        return CompanyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType.toString() +
                ", CompanyId=" + CompanyId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
