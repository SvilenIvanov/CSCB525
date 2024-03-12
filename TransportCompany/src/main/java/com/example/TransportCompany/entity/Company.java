package com.example.TransportCompany.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private double income = 0;

    @ManyToMany(mappedBy = "companies", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Transport> transports;
}
