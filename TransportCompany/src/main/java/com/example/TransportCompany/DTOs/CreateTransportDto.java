package com.example.TransportCompany.DTOs;

import com.example.TransportCompany.misc.GoodsType;
import com.example.TransportCompany.misc.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CreateTransportDto
{
    private int CompanyId;

    private int ClientId;

    private int EmployeeId;

    private double Price;

    private Double Weight;

    private String Destination;

    private Timestamp DepartureDate;

    private Timestamp DeliveryDate;

    private VehicleType Vehicle;

    private GoodsType Goods;
}
