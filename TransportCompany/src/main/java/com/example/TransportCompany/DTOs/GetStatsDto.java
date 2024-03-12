package com.example.TransportCompany.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class GetStatsDto
{
    private TransportStatsData transportInfo;

    private Map<Integer, Integer> employeeTransports;

    private Map<Integer, Double> companyEarnings;
}

