package com.maxim.coupons.dto;

import com.maxim.coupons.enums.CategoryType;

import java.util.Date;
import java.util.Objects;

public class Purchase {
    private int id;
    private int couponId;
    private int userId;
    private int amount;
    private int companyId;
    private Date date;
    private CategoryType couponCategory;

    public Purchase(int id, int couponId, int userId, int amount, int companyId, Date date, CategoryType couponCategory) {
        this(couponId, userId, amount, companyId, date, couponCategory);
        this.id = id;
    }

    public Purchase(int couponId, int userId, int amount, int companyId, Date date, CategoryType couponCategory) {
        this.couponId = couponId;
        this.userId = userId;
        this.amount = amount;
        this.companyId = companyId;
        this.date = date;
        this.couponCategory = couponCategory;
    }

    public CategoryType getCouponCategory() {
        return couponCategory;
    }

    public void setCouponCategory(CategoryType couponCategory) {
        this.couponCategory = couponCategory;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Purchase() {
    }

    public int getId() {
        return id;
    }


    public int getCouponId() {
        return couponId;
    }

    public int getUserId() {
        return userId;
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

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", couponId='" + couponId + '\'' +
                ", userId='" + userId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
