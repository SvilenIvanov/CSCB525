package com.example.TransportCompany.service;

import com.example.TransportCompany.entity.Employee;
import com.example.TransportCompany.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService
{
    @Autowired
    private EmployeeRepository Repository;

    public Iterable<Employee> GetAllEmployees(boolean sortByFee, boolean sortByLicense) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sortByFee) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "fee"));
        }
        if (sortByLicense) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "license"));
        }

        if (!orders.isEmpty()) {
            Sort sort = Sort.by(orders);
            return Repository.findAll(sort);
        }

        return Repository.findAll();
    }

    public Optional<Employee> GetEmployeeById(int id) {
        return Optional.ofNullable(Repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found.")));
    }

    public Employee CreateEmployee(Employee employee) {
        return Repository.save(employee);
    }

    public Employee UpdateEmployee(int id, Employee updatedEmployee) {
        if (Repository.existsById(id)) {
            updatedEmployee.setId(id);
            return Repository.save(updatedEmployee);
        } else {
            throw new EntityNotFoundException("Employee with id " + id + " not found.");
        }
    }

    public void DeleteEmployee(int id) {
        Employee employee = GetEmployeeById(id).orElseThrow(() -> new EntityNotFoundException("Employee with id " + id + " not found."));

        employee.getCompanies().clear();

        Repository.deleteById(id);
    }
}
