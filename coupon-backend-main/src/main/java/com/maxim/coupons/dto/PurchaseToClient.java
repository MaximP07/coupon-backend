package com.maxim.coupons.dto;

import com.maxim.coupons.enums.CategoryType;

import java.util.Date;

public class PurchaseToClient extends Purchase {
    public PurchaseToClient(int couponId, int userId, int amount, int companyId, Date date, CategoryType couponCategory) {
        super(couponId, userId, amount, companyId, date, couponCategory);
    }

    public PurchaseToClient(int id, int couponId, int userId, int amount, int companyId, Date date, CategoryType couponCategory) {
        super(id, couponId, userId, amount, companyId, date, couponCategory);
    }
}
