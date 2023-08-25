package com.maxim.coupons.entity;


import com.maxim.coupons.dto.User;
import com.maxim.coupons.enums.UserType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "userName", unique = true, nullable = false)
    private String userName;

    @Column(name = "password", unique = false, nullable = false)
    private String password;

    @Column(name = "userType", unique = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private CompanyEntity companies;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "users", fetch = FetchType.EAGER)
    private List<PurchaseEntity> purchasesList;

    public UserEntity(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.userType = user.getUserType();
    }

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public CompanyEntity getCompanies() {
        return companies;
    }

    public void setCompanies(CompanyEntity companies) {
        this.companies = companies;
    }

    public List<PurchaseEntity> getPurchasesList() {
        return purchasesList;
    }

    public void setPurchasesList(List<PurchaseEntity> purchasesList) {
        this.purchasesList = purchasesList;
    }
}
