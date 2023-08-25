package com.maxim.coupons.entity;

import com.maxim.coupons.dto.Purchase;
import com.maxim.coupons.enums.CategoryType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchases")
public class PurchaseEntity {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private CouponEntity coupons;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private UserEntity users;

    @Column(name = "amount", unique = false, nullable = false)
    private int amount;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private CompanyEntity companies;

    @Column(name = "date", unique = false, nullable = false)
    private Date date;

    @Column(name = "categoryType", unique = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    public PurchaseEntity(Purchase purchase) {
        this.id = purchase.getId();
        this.amount = purchase.getAmount();
        this.date = purchase.getDate();
        this.categoryType = purchase.getCouponCategory();
    }

    public PurchaseEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CouponEntity getCoupons() {
        return coupons;
    }

    public void setCoupons(CouponEntity coupons) {
        this.coupons = coupons;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }

    public CompanyEntity getCompany() {
        return companies;
    }

    public void setCompany(CompanyEntity company) {
        this.companies = company;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public CompanyEntity getCompanies() {
        return companies;
    }

    public void setCompanies(CompanyEntity companies) {
        this.companies = companies;
    }
}
