package com.example.TransportCompany.service;

import com.example.TransportCompany.DTOs.GetCompanyDto;
import com.example.TransportCompany.entity.Company;
import com.example.TransportCompany.entity.Employee;
import com.example.TransportCompany.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService
{
    @Autowired
    private CompanyRepository Repository;

    @Autowired
    private EmployeeService EmployeeService;

    public Iterable<GetCompanyDto> GetAllCompanies(boolean sortByName, boolean sortByIncome)
    {
        List<Sort.Order> orders = new ArrayList<>();

        if (sortByIncome) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "income"));
        }
        if (sortByName) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "name"));
        }

        List<Company> companies;
        if (!orders.isEmpty()) {
            Sort sort = Sort.by(orders);
            companies = Repository.findAll(sort);
        } else {
            companies = Repository.findAll();
        }
        return companies.stream()
                .map(this::MapCompanyBaseData)
                .collect(Collectors.toList());
    }

    public Optional<GetCompanyDto> GetCompanyBaseDataById(int id)
    {
        return Optional.ofNullable(Repository.findById(id)
                .map(this::MapCompanyBaseData)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found.")));
    }

    public Optional<Company> GetCompanyById(int id)
    {
        return Optional.ofNullable(Repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found.")));
    }

    public Company CreateCompany(Company company)
    {
        return Repository.save(company);
    }

    public Company UpdateCompany(int id, Company updatedCompany)
    {
        if (Repository.existsById(id))
        {
            updatedCompany.setId(id);
            return Repository.save(updatedCompany);
        }
        else
            throw new EntityNotFoundException("Company with id " + id + " not found.");
    }

    @Transactional
    public void DeleteCompany(int id)
    {
        Company company = GetCompanyById(id).orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found."));

        for (Employee employee : company.getEmployees()) {
            employee.getCompanies().remove(company);
        }

        company.getEmployees().clear();

        Repository.deleteById(id);
    }

    @Transactional
    public void HireEmployee(int companyId, int employeeId)
    {
        Company company = GetCompanyById(companyId).orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found."));

        Employee employee = EmployeeService.GetEmployeeById(employeeId).orElseThrow(() -> new EntityNotFoundException("Employee with id " + employeeId + " not found."));

        if (!company.getEmployees().contains(employee))
        {
            company.getEmployees().add(employee);
            employee.getCompanies().add(company);

            Repository.save(company);
        }
    }

    @Transactional
    public void FireEmployees(int companyId, List<Integer> employeeIds)
    {
        Company company = GetCompanyById(companyId).orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found."));

        for (int employeeId : employeeIds) {
            Employee employee = EmployeeService.GetEmployeeById(employeeId).orElseThrow(() -> new EntityNotFoundException("Employee with id " + employeeId + " not found."));

            company.getEmployees().remove(employee);
            employee.getCompanies().remove(company);
        }

        Repository.save(company);
    }

    private GetCompanyDto MapCompanyBaseData(Company company)
    {
        GetCompanyDto dto = new GetCompanyDto();

        dto.setId(company.getId());
        dto.setName(company.getName());
        dto.setEmployees(company.getEmployees());
        dto.setTransportations(company.getTransports());
        dto.setIncome(company.getIncome());

        return dto;
    }
}
