package com.maxim.coupons.dal;

import com.maxim.coupons.dto.Coupon;
import com.maxim.coupons.entity.CouponEntity;
import com.maxim.coupons.enums.CategoryType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICouponsDal extends CrudRepository<CouponEntity, Integer> {
    @Query("SELECT new com.maxim.coupons.dto.Coupon (c.id, c.name, c.description, c.startDate, c.endDate, c.amount, c.categoryType, c.price, c.companies.id) FROM CouponEntity c")
    List<Coupon> getAllCoupons();

    @Query("SELECT new com.maxim.coupons.dto.Coupon (c.id, c.name, c.description, c.startDate, c.endDate, c.amount, c.categoryType, c.price, c.companies.id) FROM CouponEntity c WHERE c.id = :id")
    Coupon getCoupon(@Param("id") int id);

    @Query("SELECT new com.maxim.coupons.dto.Coupon (c.id, c.name, c.description, c.startDate, c.endDate, c.amount, c.categoryType, c.price, c.companies.id) FROM CouponEntity c WHERE c.price = :maxPrice")
    List<Coupon> getByMaxPrice(@Param("maxPrice") float maxPrice);

    @Query("SELECT new com.maxim.coupons.dto.Coupon (c.id, c.name, c.description, c.startDate, c.endDate, c.amount, c.categoryType, c.price, c.companies.id) FROM CouponEntity c WHERE c.categoryType = :category")
    List<Coupon> getByCategory(@Param("category") CategoryType category);

    @Query("SELECT new com.maxim.coupons.dto.Coupon (c.id, c.name, c.description, c.startDate, c.endDate, c.amount, c.categoryType, c.price, c.companies.id) FROM CouponEntity c WHERE c.companies.id = :companyId")
    List<Coupon> getByCompanyId(@Param("companyId") int id);
}
