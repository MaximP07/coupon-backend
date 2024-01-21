package com.maxim.coupons.controllers;

import com.maxim.coupons.dto.Company;
import com.maxim.coupons.exceptions.ApplicationException;
import com.maxim.coupons.logic.CompanyLogic;
import com.maxim.coupons.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    private CompanyLogic companyLogic;

    @Autowired
    public CompaniesController(CompanyLogic companyLogic) {
        this.companyLogic = companyLogic;
    }

    @GetMapping
    public List<Company> getAllCompanies(@RequestHeader("Authorization") String token) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return this.companyLogic.getAllCompanies();
    }

    @PostMapping
    public void addCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        companyLogic.addCompany(company);
    }

    @PutMapping
    public void updateCompany(@RequestHeader("Authorization") String token, @RequestBody Company company) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        companyLogic.updateCompany(company);
    }

    @GetMapping("/{companyId}")
    public Company getCompany(@RequestHeader("Authorization") String token, @PathVariable("companyId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        return companyLogic.getCompany(id);
    }

    @DeleteMapping
    public void deleteCompany(@RequestHeader("Authorization") String token, @PathVariable("companyId") int id) throws ApplicationException {
        Claims successfulLoginDetails = JWTUtils.decodeJWT(token);
        companyLogic.removeCompany(id);
    }
}
