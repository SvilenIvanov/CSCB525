package com.xadmin.TransportCompanyProject.repositories;

import com.xadmin.TransportCompanyProject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {
}
