package com.example.TransportCompany.repository;

import com.example.TransportCompany.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Integer>
{

}
