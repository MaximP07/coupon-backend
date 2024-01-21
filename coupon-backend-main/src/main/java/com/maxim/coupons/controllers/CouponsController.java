package com.maxim.coupons.controllers;

import com.maxim.coupons.dto.Coupon;
import com.maxim.coupons.exceptions.ApplicationException;
import com.maxim.coupons.logic.CouponsLogic;
import com.maxim.coupons.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponsController {
    private CouponsLogic couponsLogic;

    @Autowired
    public CouponsController(CouponsLogic couponsLogic) {
        this.couponsLogic = couponsLogic;
    }

    @GetMapping
    public List<Coupon> getAllCoupons() throws ApplicationException {
        return this.couponsLogic.getAllCoupons();
    }

    @PostMapping
    public void addCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        couponsLogic.addCoupon(coupon);
    }

    @PutMapping
    public void updateCoupon(@RequestHeader("Authorization") String token, @RequestBody Coupon coupon) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        couponsLogic.updateCoupon(coupon);
    }

    @GetMapping("/{couponId}")
    public Coupon getCoupon(@RequestHeader("Authorization") String token, @PathVariable("couponId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return couponsLogic.getCoupon(id);
    }

    @DeleteMapping
    public void deleteCoupon(@RequestHeader("Authorization") String token, @PathVariable("couponId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        couponsLogic.removeCoupon(id);
    }
}
