package com.xadmin.TransportCompanyProject.services;

import com.xadmin.TransportCompanyProject.entity.Company;
import com.xadmin.TransportCompanyProject.repositories.CompanyDAO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyDAO companyDao;

    public CompanyService(CompanyDAO companyDao) {
        this.companyDao = companyDao;
    }

    public void createCompany(String name){
        Company company = new Company(name);
        companyDao.saveAndFlush(company);
    }


    public List<Company> sortByName() {
        List<Company> company = companyDao.findAll();
        company.sort(Comparator.comparing(Company::getName));
        return company;
    }

    public List<Company> sortByIncome() {
        List<Company> company = companyDao.findAll();
        company.sort(Comparator.comparing(Company::getIncome));
        return company;
    }
}
