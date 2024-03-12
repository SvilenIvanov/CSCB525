package com.example.TransportCompany.repository;

import com.example.TransportCompany.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>
{

}
