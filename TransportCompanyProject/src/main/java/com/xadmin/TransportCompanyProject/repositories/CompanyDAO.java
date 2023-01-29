package com.xadmin.TransportCompanyProject.repositories;

import com.xadmin.TransportCompanyProject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {
    Optional<Company> findById(Long id);
    void deleteById(Long id);
}
