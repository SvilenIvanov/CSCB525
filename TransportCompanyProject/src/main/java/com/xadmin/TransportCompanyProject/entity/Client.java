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
    private String name;

    @ManyToMany
    private Set<Transportation> transportation = new HashSet<>();


    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }
    public Client(String name,Company company) {
        this.name = name;
    }
    public Client(String name, Set<Company> companies) {
        this.name = name;
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

    public Set<Transportation> getTransportation() {
        return transportation;
    }

    public void setTransportation(Set<Transportation> transportation) {
        this.transportation = transportation;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
