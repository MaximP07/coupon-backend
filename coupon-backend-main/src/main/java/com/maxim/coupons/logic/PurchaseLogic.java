package com.maxim.coupons.logic;

import com.maxim.coupons.dal.ICompanyDal;
import com.maxim.coupons.dal.ICouponsDal;
import com.maxim.coupons.dal.IPurchaseDal;
import com.maxim.coupons.dal.IUsersDal;
import com.maxim.coupons.dto.*;
import com.maxim.coupons.entity.PurchaseEntity;
import com.maxim.coupons.enums.CategoryType;
import com.maxim.coupons.enums.ErrorType;
import com.maxim.coupons.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PurchaseLogic {
    private IPurchaseDal purchaseDal;
    private ICouponsDal couponsDal;
    private IUsersDal usersDal;
    private ICompanyDal companyDal;
    private CouponsLogic couponsLogic;
    private CompanyLogic companyLogic;
    private UsersLogic usersLogic;

    @Autowired
    public PurchaseLogic(IPurchaseDal purchaseDal, ICouponsDal couponsDal, IUsersDal usersDal, ICompanyDal companyDal) {
        this.purchaseDal = purchaseDal;
        this.couponsDal = couponsDal;
        this.usersDal = usersDal;
        this.companyDal = companyDal;
        this.couponsLogic = new CouponsLogic();
        this.companyLogic = new CompanyLogic();
        this.usersLogic = new UsersLogic();
    }

    public void addPurchase(Purchase purchase) throws ApplicationException {
        validatePurchase(purchase);
        PurchaseEntity purchaseEntity = new PurchaseEntity(purchase);
        this.purchaseDal.save(purchaseEntity);
    }

    public List<PurchaseToClient> getAllPurchases() throws ApplicationException {
        return this.purchaseDal.getAllPurchases();
    }

    public PurchaseToClient getPurchase(int id) throws ApplicationException {
        return this.purchaseDal.getPurchase(id);
    }

    public List<PurchaseToClient> getByUserId(int userId) throws ApplicationException {
        return this.purchaseDal.getByUserId(userId);
    }

    public List<PurchaseToClient> getPurchasesByDateRange(Date startDate, Date endDate) throws ApplicationException {
        return this.purchaseDal.getPurchasesByDateRange(startDate, endDate);
    }

    public void removePurchase(int id) throws ApplicationException {
        this.purchaseDal.deleteById(id);
    }

    public void updatePurchase(Purchase purchase) throws ApplicationException {
        validatePurchase(purchase);
        PurchaseEntity purchaseEntity = new PurchaseEntity(purchase);
        this.purchaseDal.save(purchaseEntity);
    }

    public List<PurchaseToClient> getByCompanyId(int companyId) throws ApplicationException {
        return this.purchaseDal.getByCompanyId(companyId);
    }

    public List<PurchaseToClient> getPurchasesByCategory(int userId, CategoryType couponCategory) throws ApplicationException {
        return this.purchaseDal.getPurchasesByCategory(userId, couponCategory);
    }

    private void validatePurchase(Purchase purchase) throws ApplicationException {

        Coupon coupon = couponsLogic.getCoupon(purchase.getCouponId());
        if (coupon == null) {
            throw new ApplicationException(ErrorType.INVALID_PURCHASE_COUPON_ID, " couponID do not exist " + purchase.getCouponId());
        }

        User user = usersLogic.getUser(purchase.getUserId());
        if (user == null) {
            throw new ApplicationException(ErrorType.INVALID_PURCHASE_USER_ID, " UserID do not exist " + purchase.getUserId());
        }

        if (purchase.getAmount() <= 0) {
            throw new ApplicationException(ErrorType.INVALID_PURCHASE_AMOUNT);
        }
        Company company = companyLogic.getCompany(purchase.getCompanyId());
        if (company == null) {
            throw new ApplicationException(ErrorType.INVALID_PURCHASE_COMPANY_ID, " CompanyID do not exist " + purchase.getCompanyId());
        }
    }
}
