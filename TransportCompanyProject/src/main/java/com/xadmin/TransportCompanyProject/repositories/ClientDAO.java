package com.xadmin.TransportCompanyProject.repositories;


import com.xadmin.TransportCompanyProject.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDAO extends JpaRepository<Client, Long> {

}
