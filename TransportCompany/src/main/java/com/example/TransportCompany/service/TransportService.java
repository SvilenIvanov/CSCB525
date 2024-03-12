package com.example.TransportCompany.service;

import com.example.TransportCompany.DTOs.CreateTransportDto;
import com.example.TransportCompany.entity.Client;
import com.example.TransportCompany.entity.Company;
import com.example.TransportCompany.entity.Employee;
import com.example.TransportCompany.entity.Transport;
import com.example.TransportCompany.exceptions.*;
import com.example.TransportCompany.misc.GoodsType;
import com.example.TransportCompany.repository.ClientRepository;
import com.example.TransportCompany.repository.CompanyRepository;
import com.example.TransportCompany.repository.EmployeeRepository;
import com.example.TransportCompany.repository.TransportRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransportService
{
    @Autowired
    private TransportRepository Repository;

    @Autowired
    private CompanyService CompanyService;

    @Autowired
    private ClientService ClientService;

    @Autowired
    private EmployeeService EmployeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Transport> GetAllTransports(boolean sortByDestination) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sortByDestination) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "destination"));
        }

        if (!orders.isEmpty()) {
            Sort sort = Sort.by(orders);
            return Repository.findAll(sort);
        }

        return Repository.findAll();
    }

    public void SaveDataToFile(){
        Iterable<Transport> transports = Repository.findAll();

        String filePath = "transports_data.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Transport transport : transports) {
                writer.write(transport.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while saving data to file: " + e.getMessage());
        }
    }

    public Transport CreateNew(CreateTransportDto createTransportDto){
        ValidateTransportationAndUpdateEntities(createTransportDto);

        Transport transport = new Transport();

        transport.setPrice(createTransportDto.getPrice());
        transport.setWeight(createTransportDto.getWeight());
        transport.setDestination(createTransportDto.getDestination());
        transport.setDepartureDate(createTransportDto.getDepartureDate());
        transport.setDeliveryDate(createTransportDto.getDeliveryDate());
        transport.setVehicle(createTransportDto.getVehicle());
        transport.setGoods(createTransportDto.getGoods());

        return transport;
    }

    private void ValidateTransportationAndUpdateEntities(CreateTransportDto createTransportDto){
        int employeeId = createTransportDto.getEmployeeId();
        int clientId = createTransportDto.getClientId();
        int companyId = createTransportDto.getCompanyId();

        double price = createTransportDto.getPrice();

        Employee employee = EmployeeService.GetEmployeeById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + employeeId + " not found."));

        Client client = ClientService.GetClientById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("Client with id " + clientId + " not found."));

        Company company = CompanyService.GetCompanyById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found."));

        if (!IsDeliveryDateInFuture(createTransportDto.getDeliveryDate()))
            throw new InvalidDeliveryDateException("Delivery date must be in the future");

        if (!ClientService.IsClientBudgetValid(client, price))
            throw new BudgetTooLowException("Budget:" + client.getBudget() + " of client with id:" + clientId + " is too low");

        if (company.getIncome() + price - employee.getFee() < 0)
            throw new BudgetTooLowException("Company budget:" + company.getIncome() + "is too low");

        if (employee.isOnBusinessTravel())
            throw new EmployeeUnavailableException("Employee with id:" + employeeId + " is already on business travel");

        if (!IsEmployedInCompany(employeeId, company))
            throw new NotEmployedException("Employee with id:" + employeeId + " is not employed in company with id:" + companyId);

        if (createTransportDto.getGoods() == GoodsType.Stock && createTransportDto.getWeight() == null)
            throw new WeightNeededException("Weight is mandatory when the goods type is stock");

        UpdateEntitiesForTransport(employee, client, company, price, createTransportDto);
    }

    private static boolean IsEmployedInCompany(int employeeId, Company company)
    {
        return company.getEmployees()
                .stream()
                .map(Employee::getId)
                .toList()
                .contains(employeeId);
    }

    private void UpdateEntitiesForTransport(Employee employee, Client client, Company company, double price, CreateTransportDto createTransportDto) {
        employee.setOnBusinessTravel(true);
        client.setBudget(client.getBudget() - price);
        company.setIncome(company.getIncome() + price - employee.getFee());

        Transport transport = new Transport();

        transport.setPrice(createTransportDto.getPrice());
        transport.setWeight(createTransportDto.getWeight());
        transport.setDestination(createTransportDto.getDestination());
        transport.setDepartureDate(createTransportDto.getDepartureDate());
        transport.setDeliveryDate(createTransportDto.getDeliveryDate());
        transport.setVehicle(createTransportDto.getVehicle());
        transport.setGoods(createTransportDto.getGoods());
        transport.setClient(client);
        transport.setCompany(company);
        transport.setEmployee(employee);

        SaveEntities(employee, client, company, transport);
    }

    private void SaveEntities(Employee employee, Client client, Company company, Transport transport)
    {
        employeeRepository.save(employee);
        clientRepository.save(client);
        companyRepository.save(company);
        Repository.save(transport);
    }

    private boolean IsDeliveryDateInFuture(Timestamp DeliveryDate) {
        LocalDateTime deliveryDateTime = DeliveryDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        LocalDateTime now = LocalDateTime.now();

        return deliveryDateTime.isAfter(now);
    }
}
