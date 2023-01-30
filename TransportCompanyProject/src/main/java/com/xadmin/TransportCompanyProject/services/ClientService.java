package com.xadmin.TransportCompanyProject.services;

import com.xadmin.TransportCompanyProject.entity.Client;
import com.xadmin.TransportCompanyProject.repositories.ClientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    public void createClient(Client client) {
        clientDAO.save(client);
    }

    public Client findClientById(Long id) {
        return clientDAO.findById(id).orElse(null);
    }

    public List<Client> findAllClients() {
        return clientDAO.findAll();
    }

    public void updateClient(Client client) {
        clientDAO.save(client);
    }

    public void deleteClient(Long id) {
        clientDAO.deleteById(id);
    }
}
