package com.maxim.coupons.entity;

import com.maxim.coupons.dto.Coupon;
import com.maxim.coupons.enums.CategoryType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "coupons")
public class CouponEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "startDate", unique = false, nullable = false)
    private Date startDate;

    @Column(name = "endDate", unique = false, nullable = false)
    private Date endDate;

    @Column(name = "amount", unique = false, nullable = false)
    private int amount;

    @Column(name = "categoryType", unique = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Column(name = "price", unique = false, nullable = false)
    private float price;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private CompanyEntity companies;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "coupons", fetch = FetchType.EAGER)
    private List<PurchaseEntity> couponsPurchases;


    public CouponEntity(Coupon coupon) {
        this.id = coupon.getId();
        this.name = coupon.getName();
        this.description = coupon.getDescription();
        this.startDate = coupon.getStartDate();
        this.endDate = coupon.getEndDate();
        this.amount = coupon.getAmount();
        this.categoryType = coupon.getCategoryType();
        this.price = coupon.getPrice();
    }

    public CouponEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompanyEntity getCompany() {
        return companies;
    }

    public void setCompany(CompanyEntity company) {
        this.companies = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<PurchaseEntity> getCouponsPurchases() {
        return couponsPurchases;
    }

    public void setCouponsPurchases(List<PurchaseEntity> couponsPurchases) {
        this.couponsPurchases = couponsPurchases;
    }
}
