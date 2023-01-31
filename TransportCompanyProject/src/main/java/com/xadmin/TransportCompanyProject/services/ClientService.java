package com.xadmin.TransportCompanyProject.services;

import com.xadmin.TransportCompanyProject.entity.Client;
import com.xadmin.TransportCompanyProject.repositories.ClientDAO;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }
    public void createClient(Client client) {
        clientDAO.save(client);
    }

    public Client saveClient(@NotNull Client client) {
        return clientDAO.save(client);
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public Optional<Client> getClientById(long id) {
        return clientDAO.findById(id);
    }

    public void deleteClient(@NotNull Client client) {
        clientDAO.delete(client);
    }
}
