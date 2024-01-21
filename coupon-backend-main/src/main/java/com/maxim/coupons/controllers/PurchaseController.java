package com.maxim.coupons.controllers;

import com.maxim.coupons.dto.Purchase;
import com.maxim.coupons.dto.PurchaseToClient;
import com.maxim.coupons.enums.CategoryType;
import com.maxim.coupons.exceptions.ApplicationException;
import com.maxim.coupons.logic.PurchaseLogic;
import com.maxim.coupons.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    private PurchaseLogic purchaseLogic;

    @Autowired
    public PurchaseController(PurchaseLogic purchaseLogic) {
        this.purchaseLogic = purchaseLogic;
    }

    @GetMapping
    public List<PurchaseToClient> getAllPurchases(@RequestHeader("Authorization") String token) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.purchaseLogic.getAllPurchases();
    }

    @GetMapping("/{purchaseId}")
    public PurchaseToClient getPurchase(@RequestHeader("Authorization") String token, @PathVariable("purchaseId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return purchaseLogic.getPurchase(id);
    }

    @GetMapping("/userId")
    public List<PurchaseToClient> getByUserId(@RequestHeader("Authorization") String token, @RequestParam("userId") int userId) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.purchaseLogic.getByUserId(userId);
    }

    @GetMapping("/PurchasesByDateRange")
    public List<PurchaseToClient> getPurchasesByDateRange(@RequestHeader("Authorization") String token, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.purchaseLogic.getPurchasesByDateRange(startDate, endDate);
    }

    @GetMapping("/companyId")
    public List<PurchaseToClient> getByCompanyId(@RequestHeader("Authorization") String token, @RequestParam("companyId") int companyId) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.purchaseLogic.getByCompanyId(companyId);
    }

    @GetMapping("/PurchasesByCategory")
    public List<PurchaseToClient> getPurchasesByCategory(@RequestHeader("Authorization") String token, @RequestParam("userId") int userId, @RequestParam("couponCategory") CategoryType couponCategory) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.purchaseLogic.getPurchasesByCategory(userId, couponCategory);
    }

    @PostMapping
    public void addPurchase(@RequestHeader("Authorization") String token, @RequestBody Purchase purchase) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        purchaseLogic.addPurchase(purchase);
    }

    @PutMapping
    public void updatePurchase(@RequestHeader("Authorization") String token, @RequestBody Purchase purchase) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        purchaseLogic.updatePurchase(purchase);
    }

    @DeleteMapping
    public void deletePurchase(@RequestHeader("Authorization") String token, @PathVariable("purchaseId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        purchaseLogic.removePurchase(id);
    }
}
