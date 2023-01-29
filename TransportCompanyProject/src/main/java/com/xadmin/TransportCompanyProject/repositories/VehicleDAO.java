package com.xadmin.TransportCompanyProject.repositories;

import com.xadmin.TransportCompanyProject.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDAO extends JpaRepository<Vehicle, Long>  {
}
