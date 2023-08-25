package com.maxim.coupons.dal;

import com.maxim.coupons.dto.PurchaseToClient;
import com.maxim.coupons.entity.PurchaseEntity;
import com.maxim.coupons.enums.CategoryType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IPurchaseDal extends CrudRepository<PurchaseEntity, Integer> {
    @Query("SELECT new com.maxim.coupons.dto.PurchaseToClient(p.id, p.coupons.id, p.users.id, p.amount, p.companies.id, p.date, p.categoryType) FROM PurchaseEntity p")
    List<PurchaseToClient> getAllPurchases();

    @Query("SELECT new com.maxim.coupons.dto.PurchaseToClient(p.id, p.coupons.id, p.users.id, p.amount, p.companies.id, p.date, p.categoryType) FROM PurchaseEntity p WHERE p.id = :id")
    PurchaseToClient getPurchase(@Param("id") int id);

    @Query("SELECT new com.maxim.coupons.dto.PurchaseToClient(p.id, p.coupons.id, p.users.id, p.amount, p.companies.id, p.date, p.categoryType) FROM PurchaseEntity p WHERE p.users.id = :userId")
    List<PurchaseToClient> getByUserId(@Param("userId") int userId);

    @Query("SELECT new com.maxim.coupons.dto.PurchaseToClient(p.id, p.coupons.id, p.users.id, p.amount, p.companies.id, p.date, p.categoryType) FROM PurchaseEntity p WHERE p.date >= :startDate AND p.date <= :endDate")
    List<PurchaseToClient> getPurchasesByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT new com.maxim.coupons.dto.PurchaseToClient(p.id, p.coupons.id, p.users.id, p.amount, p.companies.id, p.date, p.categoryType) FROM PurchaseEntity p WHERE p.companies.id = :companyId")
    List<PurchaseToClient> getByCompanyId(@Param("companyId") int companyId);

    @Query("SELECT new com.maxim.coupons.dto.PurchaseToClient(p.id, p.coupons.id, p.users.id, p.amount, p.companies.id, p.date, p.categoryType) FROM PurchaseEntity p WHERE p.users.id = :userId AND p.categoryType = :couponCategory")
    List<PurchaseToClient> getPurchasesByCategory(@Param("userId") int userId, @Param("couponCategory") CategoryType couponCategory);
}
