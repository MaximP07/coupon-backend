package com.maxim.coupons.logic;

import com.maxim.coupons.dal.ICouponsDal;
import com.maxim.coupons.dto.Coupon;
import com.maxim.coupons.entity.CouponEntity;
import com.maxim.coupons.enums.CategoryType;
import com.maxim.coupons.enums.ErrorType;
import com.maxim.coupons.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponsLogic {

    private final int MIN_NAME_LENGTH = 4;
    private final int MAX_NAME_LENGTH = 15;
    private final int MIN_LENGTH_OF_DESCRIPTION = 10;
    private final int MAX_LENGTH_OF_DESCRIPTION = 100;
    private final int MAX_COUPON_PRICE = 100;
    private ICouponsDal couponsDal;

    @Autowired
    public CouponsLogic(ICouponsDal couponsDal) {
        this.couponsDal = couponsDal;
    }

    public CouponsLogic() {
    }

    public void addCoupon(Coupon coupon) throws ApplicationException {
        validateCoupon(coupon);
        CouponEntity couponEntity = new CouponEntity(coupon);
        this.couponsDal.save(couponEntity);
    }

    public List<Coupon> getAllCoupons() throws ApplicationException {
        return this.couponsDal.getAllCoupons();
    }

    public Coupon getCoupon(int id) throws ApplicationException {
        return this.couponsDal.getCoupon(id);
    }

    public List<Coupon> getByMaxPrice(float maxPrice) throws ApplicationException {
        return this.couponsDal.getByMaxPrice(maxPrice);
    }

    public List<Coupon> getByCategory(CategoryType category) throws ApplicationException {
        return this.couponsDal.getByCategory(category);
    }

    public List<Coupon> getByCompanyId(int companyId) throws ApplicationException {
        return this.couponsDal.getByCompanyId(companyId);
    }

    public void removeCoupon(int id) throws ApplicationException {
        this.couponsDal.deleteById(id);
    }

    public void updateCoupon(Coupon coupon) throws ApplicationException {
        validateCoupon(coupon);
        CouponEntity couponEntity = new CouponEntity(coupon);
        this.couponsDal.save(couponEntity);
    }

    private void validateCoupon(Coupon coupon) throws ApplicationException {
        if (coupon.getName().length() < MIN_NAME_LENGTH) {
            throw new ApplicationException(ErrorType.INVALID_COUPON_NAME, "Coupons name is to short : " + coupon.getName());
        }

        if (coupon.getName().length() > MAX_NAME_LENGTH) {
            throw new ApplicationException(ErrorType.INVALID_COUPON_NAME, "Coupon name is too long " + coupon.getName());
        }

        if (coupon.getDescription().length() < MIN_LENGTH_OF_DESCRIPTION) {
            throw new ApplicationException(ErrorType.INVALID_DESCRIPTION);
        }

        if (coupon.getDescription().length() > MAX_LENGTH_OF_DESCRIPTION) {
            throw new ApplicationException(ErrorType.INVALID_DESCRIPTION);
        }

        if (coupon.getPrice() <= 0) {
            throw new ApplicationException(ErrorType.INVALID_PRICE);
        }

        if (coupon.getPrice() > MAX_COUPON_PRICE) {
            throw new ApplicationException(ErrorType.INVALID_PRICE);
        }

        if (coupon.getAmount() <= 0) {
            throw new ApplicationException(ErrorType.INVALID_AMOUNT_OF_COUPONS);
        }

        if (coupon.getEndDate().before(coupon.getStartDate())) {
            throw new ApplicationException(ErrorType.INVALID_START_DATE_OR_END_DATE_OF_THE_COUPON);
        }

        if (coupon.getCategoryType() == null) {
            throw new ApplicationException(ErrorType.INVALID_CATEGORY);
        }
    }
}

