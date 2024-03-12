package com.example.TransportCompany.DTOs;

import com.example.TransportCompany.entity.Employee;
import com.example.TransportCompany.entity.Transport;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCompanyDto
{
    private int Id;

    private String Name;

    private double Income;

    private List<Employee> Employees;

    private List<Transport> Transportations;
}
