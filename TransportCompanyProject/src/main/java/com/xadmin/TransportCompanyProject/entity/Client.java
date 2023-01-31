package com.xadmin.TransportCompanyProject.entity;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @NotNull
    @Column(name="address", nullable = false)
    private String address;

    @ManyToMany
    @JoinTable(name = "client_company",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies;

    public Client() {
        this.name = "";
        this.address = "";
        this.companies = new HashSet<>();
    }
    public Client(String name, String address) {
        this();
        this.name = name;
        this.address = address;
    }
    public Client(String name, String address, Set<Company> companies) {
        this(name, address);
        this.companies = companies;
    }

    public void addCompany(Company company) {
        companies.add(company);
        company.getClients().add(this);
    }

    public void removeCompany(Company company) {
        this.companies.remove(company);
        company.getClients().remove(this);
    }

    public void updateCompany(Company company) {
        this.companies.remove(company);
        this.companies.add(company);
    }
    public boolean hasCompany(Company company) {
        return companies.contains(company);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}