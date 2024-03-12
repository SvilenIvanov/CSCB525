package com.example.TransportCompany.service;

import com.example.TransportCompany.entity.Client;
import com.example.TransportCompany.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService
{
    @Autowired
    private ClientRepository Repository;

    public Iterable<Client> GetAllClients() {
        return Repository.findAll();
    }

    public Optional<Client> GetClientById(int id) {
        return Optional.ofNullable(Repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found.")));
    }

    public Client CreateClient(Client client) {
        return Repository.save(client);
    }

    public Client UpdateClient(int id, Client updatedClient) {
        if (Repository.existsById(id)) {
            updatedClient.setId(id);
            return Repository.save(updatedClient);
        } else {
            throw new EntityNotFoundException("Client with id " + id + " not found.");
        }
    }

    public void DeleteClient(int id) {
        Client client  = GetClientById(id).orElseThrow(() -> new EntityNotFoundException("Client with id " + id + " not found."));

        Repository.delete(client);
    }

    public boolean IsClientBudgetValid(Client client, double price){
        return client.getBudget() >= price;
    }
}
