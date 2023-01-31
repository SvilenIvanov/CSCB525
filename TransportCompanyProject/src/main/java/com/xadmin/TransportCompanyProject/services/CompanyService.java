package com.xadmin.TransportCompanyProject.services;

import com.xadmin.TransportCompanyProject.entity.Client;
import com.xadmin.TransportCompanyProject.entity.Company;
import com.xadmin.TransportCompanyProject.repositories.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {


    private final CompanyDAO companyDAO;

    @Autowired
    public CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    public void createCompany(Company company){
        companyDAO.save(company);
    }
    public void saveCompany(Company company){
        companyDAO.saveAndFlush(company);
    }

    public void addClientToCompany(Long companyId, Client client) {
        Optional<Company> optionalCompany = companyDAO.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.addClient(client);
            client.getCompanies().add(company);
        }
    }

    public void removeClientFromCompany(Long companyId, Client client) {
        Optional<Company> optionalCompany = companyDAO.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.removeClient(client);
            client.getCompanies().remove(company);
        }
    }

    public void updateClientForCompany(Long companyId, Client client) {
        Optional<Company> optionalCompany = companyDAO.findById(companyId);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.updateClient(client);
        }
    }

    public void addClientToCompany(Company company, Client client) {
        if (!company.getClients().contains(client)) {
            company.getClients().add(client);
            client.getCompanies().add(company);
        }
    }

    public void removeClientFromCompany(Company company, Client client) {
        company.getClients().remove(client);
        client.getCompanies().remove(company);
    }

    public void updateClientForCompany(Company company, Client client) {
        Set<Company> companies = client.getCompanies();
        companies.remove(company);
        companies.add(company);
        company.getClients().remove(client);
        company.getClients().add(client);
    }

    public List<Company> sortByName() {
        List<Company> company = companyDAO.findAll();
        company.sort(Comparator.comparing(Company::getName));
        return company;
    }

    public List<Company> sortByIncome() {
        List<Company> company = companyDAO.findAll();
        company.sort(Comparator.comparing(Company::getIncome));
        return company;
    }

    public boolean hasClient(Company company, Client client) {
        return company.getClients().contains(client);
    }

}
