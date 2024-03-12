package com.example.TransportCompany.service;

import com.example.TransportCompany.DTOs.GetCompanyDto;
import com.example.TransportCompany.DTOs.GetStatsDto;
import com.example.TransportCompany.DTOs.TransportStatsData;
import com.example.TransportCompany.entity.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Service
public class StatsService
{
    @Autowired
    private TransportService TransportService;

    @Autowired
    private CompanyService CompanyService;

    public GetStatsDto GetStats(){
        Iterable<Transport> transports = TransportService.GetAllTransports(false);

        List<Transport> transportList = StreamSupport.stream(transports.spliterator(), false).toList();;

        GetStatsDto stats = new GetStatsDto();
        TransportStatsData transportsData = new TransportStatsData();

        transportsData.setCount(transportList.size());
        transportsData.setTotalAmountEarned(transportList.stream()
                                            .mapToDouble(Transport::getPrice)
                                            .sum());

        Map<Integer, Integer> employeeTransports = new HashMap<>();
        for (Transport transport : transports) {
            if (transport.getEmployee() != null) {
                int employeeId = transport.getEmployee().getId();
                employeeTransports.put(employeeId, employeeTransports.getOrDefault(employeeId, 0) + 1);
            }
        }

        Iterable<GetCompanyDto> companies = CompanyService.GetAllCompanies(true, true);

        Map<Integer, Double> companyEarnings = new HashMap<>();
        for (GetCompanyDto company : companies){
            companyEarnings.put(company.getId(), company.getIncome());
        }

        stats.setEmployeeTransports(employeeTransports);
        stats.setTransportInfo(transportsData);
        stats.setCompanyEarnings(companyEarnings);

        return stats;
    }
}
