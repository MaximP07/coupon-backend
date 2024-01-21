package com.maxim.coupons.logic;

import com.maxim.coupons.dto.Company;
import com.maxim.coupons.entity.CompanyEntity;
import com.maxim.coupons.dal.ICompanyDal;
import com.maxim.coupons.enums.ErrorType;
import com.maxim.coupons.exceptions.ApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyLogic {

    private final int MIN_NAME_LENGTH = 4;
    private final int MAX_NAME_LENGTH = 15;
    private final int MAX_LENGTH_OF_COUNTRY_NAME = 20;

    private ICompanyDal companyDal;

    @Autowired
    public CompanyLogic(ICompanyDal companyDal) {
        this.companyDal = companyDal;
    }

    public CompanyLogic() {
    }

    public void addCompany(Company company) throws ApplicationException {
        validateCompany(company);
        CompanyEntity companyEntity = new CompanyEntity(company);
        this.companyDal.save(companyEntity);
    }

    public void removeCompany(int id) throws ApplicationException {
        this.companyDal.deleteById(id);
    }

    public List<Company> getAllCompanies() throws ApplicationException {
        return this.companyDal.getAllCompanies();
    }

    public Company getCompany(int id) throws ApplicationException {
        return this.companyDal.getCompany(id);
    }

    public void updateCompany(Company company) throws ApplicationException {
        validateCompany(company);
        CompanyEntity companyEntity = new CompanyEntity(company);
        this.companyDal.save(companyEntity);
    }

    private void validateCompany(Company company) throws ApplicationException {
        if (company.getName().length() < MIN_NAME_LENGTH) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY_NAME, "Company name is to short : " + company.getName());
        }

        if (company.getName().length() > MAX_NAME_LENGTH) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY_NAME, "company name is too long " + company.getName());
        }

        if (company.getCountry().length() > MAX_LENGTH_OF_COUNTRY_NAME) {
            throw new ApplicationException(ErrorType.INVALID_COMPANY_COUNTY_LENGTH);
        }
    }
}
