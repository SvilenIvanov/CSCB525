package com.xadmin.TransportCompanyProject.services;

import com.xadmin.TransportCompanyProject.entity.Company;
import com.xadmin.TransportCompanyProject.repositories.CompanyDAO;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyDAO companyDao;

    public CompanyService(CompanyDAO companyDao) {
        this.companyDao = companyDao;
    }

    public void createCompany(){
        Company company = new Company("Name");
        companyDao.saveAndFlush(company);
    }
}
