package com.example.TransportCompany.entity;

import com.example.TransportCompany.misc.LicenseType;
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
@Table(name = "employees")
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private double fee;

    @Enumerated(EnumType.STRING)
    private LicenseType license = LicenseType.A;

    private boolean isOnBusinessTravel = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_company",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Company> companies;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Transport> transports;
}
