package com.example.TransportCompany.controller;

import com.example.TransportCompany.DTOs.CreateTransportDto;
import com.example.TransportCompany.entity.Transport;
import com.example.TransportCompany.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transports")
public class TransportController
{
    @Autowired
    private TransportService Service;

    @GetMapping("/get-all")
    public Iterable<Transport> GetAll(@RequestParam(required = false, defaultValue = "false") boolean sortByDestination) {
        return Service.GetAllTransports(sortByDestination);
    }

    @PostMapping("/create-transportation")
    public Transport CreateTransportation(@RequestBody CreateTransportDto createTransportDto){
        return Service.CreateNew(createTransportDto);
    }

    @PostMapping("/save-data-to-file")
    public void SaveData(){
        Service.SaveDataToFile();
    }
}
