package com.maxim.coupons.dto;

import com.maxim.coupons.enums.CategoryType;

import java.util.Date;

public class Coupon {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private int amount;
    private CategoryType categoryType;
    private float price;
    private int companyId;

    public Coupon(String name, String description, Date startDate, Date endDate, int amount, CategoryType categoryType, float price, int companyId) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.categoryType = categoryType;
        this.price = price;
        this.companyId = companyId;
    }

    public Coupon(int id, String name, String description, Date startDate, Date endDate, int amount, CategoryType categoryType, float price, int companyId) {
        this(name, description, startDate, endDate, amount, categoryType, price, companyId);
        this.id = id;
    }

    public Coupon() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                ", category=" + categoryType.toString() +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return id == coupon.id;
    }
}
