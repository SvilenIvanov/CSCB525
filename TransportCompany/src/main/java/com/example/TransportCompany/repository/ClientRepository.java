package com.example.TransportCompany.repository;

import com.example.TransportCompany.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer>
{

}
