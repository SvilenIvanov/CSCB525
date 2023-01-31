package com.xadmin.TransportCompanyProject.repositories;


import com.xadmin.TransportCompanyProject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDAO extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);
    Optional<Client> findByName(String name);
}
