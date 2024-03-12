package com.example.TransportCompany.controller;

import com.example.TransportCompany.entity.Employee;
import com.example.TransportCompany.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController
{
    @Autowired
    private EmployeeService Service;

    @GetMapping("/get-all")
    public Iterable<Employee> GetAll(@RequestParam(required = false, defaultValue = "false") boolean sortByFee,
                                     @RequestParam(required = false, defaultValue = "false") boolean sortByLicense) {
        return Service.GetAllEmployees(sortByFee, sortByLicense);
    }

    @GetMapping("/get-by-id/{id}")
    public Employee GetEmployeeById(@PathVariable int id)
    {
        return Service.GetEmployeeById(id).orElse(null);
    }

    @PostMapping("/create")
    public Employee CreateEmployee(@RequestBody Employee employee)
    {
        return Service.CreateEmployee(employee);
    }

    @PutMapping("/update-by-id/{id}")
    public Employee UpdateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee)
    {
        return Service.UpdateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void DeleteEmployee(@PathVariable int id)
    {
        Service.DeleteEmployee(id);
    }
}
