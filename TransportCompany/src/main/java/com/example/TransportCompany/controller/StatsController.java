package com.example.TransportCompany.controller;

import com.example.TransportCompany.DTOs.GetStatsDto;
import com.example.TransportCompany.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stats")
public class StatsController
{
    @Autowired
    private StatsService Service;

    @GetMapping("/get-stats")
    public GetStatsDto GetStats() {
        return Service.GetStats();
    }
}
