package com.example.TransportCompany.controller;

import com.example.TransportCompany.entity.Client;
import com.example.TransportCompany.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/clients")
public class ClientController
{
    @Autowired
    private ClientService Service;

    @GetMapping("/get-all")
    public Iterable<Client> GetAll()
    {
        return Service.GetAllClients();
    }

    @GetMapping("/get-by-id/{id}")
    public Client GetClientById(@PathVariable int id)
    {
        return Service.GetClientById(id).orElse(null);
    }

    @PostMapping("/create")
    public Client CreateClient(@RequestBody Client client)
    {
        return Service.CreateClient(client);
    }

    @PutMapping("/update-by-id/{id}")
    public Client UpdateClient(@PathVariable int id, @RequestBody Client updatedClient)
    {
        return Service.UpdateClient(id, updatedClient);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public void DeleteClient(@PathVariable int id)
    {
        Service.DeleteClient(id);
    }
}
