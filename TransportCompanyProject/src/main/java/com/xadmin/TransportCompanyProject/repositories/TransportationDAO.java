package com.xadmin.TransportCompanyProject.repositories;

import com.xadmin.TransportCompanyProject.entity.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationDAO extends JpaRepository<Transportation, Long> {
}
