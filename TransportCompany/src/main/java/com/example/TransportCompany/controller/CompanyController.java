package com.example.TransportCompany.controller;

import com.example.TransportCompany.DTOs.GetCompanyDto;
import com.example.TransportCompany.entity.Company;
import com.example.TransportCompany.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController
{
    @Autowired
    private CompanyService Service;

    @GetMapping("/get-all")
    public Iterable<GetCompanyDto> getAllCompanies(@RequestParam(required = false, defaultValue = "false") boolean sortByIncome,
                                                   @RequestParam(required = false, defaultValue = "false") boolean sortByName
                                                   ) {
        return Service.GetAllCompanies(sortByName, sortByIncome);
    }

    @GetMapping("/get-by-id/{id}")
    public GetCompanyDto GetCompanyById(@PathVariable int id)
    {
        return Service.GetCompanyBaseDataById(id).orElse(null);
    }

    @PostMapping("/create")
    public Company CreateCompany(@RequestBody Company company)
    {
        return Service.CreateCompany(company);
    }

    @PutMapping("/update-by-id/{id}")
    public Company UpdateCompany(@PathVariable int id, @RequestBody Company updatedCompany)
    {
        return Service.UpdateCompany(id, updatedCompany);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void DeleteCompany(@PathVariable int id)
    {
        Service.DeleteCompany(id);
    }

    @PostMapping("/{companyId}/hire/{employeeId}")
    public void HireEmployee(@PathVariable int companyId, @PathVariable int employeeId) {
        Service.HireEmployee(companyId, employeeId);
    }

    @PostMapping("/{companyId}/fire")
    public void FireEmployees(@PathVariable int companyId, @RequestBody List<Integer> employeeIds) {
        Service.FireEmployees(companyId, employeeIds);
    }
}
