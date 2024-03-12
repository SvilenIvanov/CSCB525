package com.example.TransportCompany.repository;

import com.example.TransportCompany.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>
{

}
