package com.maxim.coupons.dal;

import com.maxim.coupons.dto.Company;
import com.maxim.coupons.entity.CompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICompanyDal extends CrudRepository<CompanyEntity, Integer> {
    @Query("SELECT new com.maxim.coupons.dto.Company (c.id, c.name, c.country) FROM CompanyEntity c")
    List<Company> getAllCompanies();

    @Query("SELECT new com.maxim.coupons.dto.Company (c.id, c.name, c.country) FROM CompanyEntity c WHERE c.id = :id")
    Company getCompany(@Param("id") int id);
}
