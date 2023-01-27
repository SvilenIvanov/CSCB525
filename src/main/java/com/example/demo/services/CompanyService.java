package com.example.demo.services;

import com.example.demo.entity.Company;
import com.example.demo.repositories.CompanyDAO;
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
