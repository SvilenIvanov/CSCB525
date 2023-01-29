package com.xadmin.TransportCompanyProject.entity;


import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Column(name="income", nullable = false)
    private double income;


    @OneToMany
    @Column(name = "transportations")
    private Set<Transportation> transportations;

    @OneToMany
    @Column(name = "employees")
    private Set<Employee> employees;
    @OneToMany
    @Column(name = "vehicles")
    private Set<Vehicle> vehicles;

    @ManyToMany
    @Column(name = "clients")
    private Set<Client> clients;

    public Company() {
        this.name = "";
        this.transportations = new HashSet<>();
        this.employees = new HashSet<>();
        this.vehicles = new HashSet<>();
        this.clients = new HashSet<>();
    }
    public Company(String name) {
        this();
        this.name = name;
    }
    public Company(String name, Set<Employee> employees) {
        this(name);
        this.employees = employees;
    }
    public Company(String name, Set<Employee> employees, Set<Vehicle> vehicles) {
        this(name, employees);
        this.vehicles = vehicles;
    }
    public Company(String name, Set<Employee> employees, Set<Vehicle> vehicles, Set<Client> clients) {
        this(name, employees, vehicles);
        this.clients = clients;
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
            client.getCompanies().add(this);
        }
    }

    public void removeClient(Client client) {
        this.clients.remove(client);
        client.setCompany(null);
    }

    public void updateClient(Client client) {
        this.clients.remove(client);
        this.clients.add(client);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(Set<Transportation> transportations) {
        this.transportations = transportations;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
