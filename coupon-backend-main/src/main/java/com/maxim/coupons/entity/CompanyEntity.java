package com.maxim.coupons.entity;

import com.maxim.coupons.dto.Company;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "country", unique = false, nullable = false)
    private String country;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "companies", fetch = FetchType.EAGER)
    private List<UserEntity> companyUsers;

    public CompanyEntity(Company company) {
        this.id = company.getId();
        this.name = company.getName();
        this.country = company.getCountry();
    }

    public CompanyEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<UserEntity> getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(List<UserEntity> companyUsers) {
        this.companyUsers = companyUsers;
    }
}
