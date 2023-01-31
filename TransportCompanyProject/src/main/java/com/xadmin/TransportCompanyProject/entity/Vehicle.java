package com.xadmin.TransportCompanyProject.entity;

import com.xadmin.TransportCompanyProject.models.FuelType;
import com.xadmin.TransportCompanyProject.models.LicenseType;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public void setPeopleCapacity(int peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    public int getCargoCapacityKg() {
        return cargoCapacityKg;
    }

    public void setCargoCapacityKg(int cargoCapacityKg) {
        this.cargoCapacityKg = cargoCapacityKg;
    }

    public Set<LicenseType> getRequiredLicenses() {
        return requiredLicenses;
    }

    public void setRequiredLicenses(Set<LicenseType> requiredLicenses) {
        this.requiredLicenses = requiredLicenses;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Transportation> getTrips() {
        return trips;
    }

    public void setTrips(Set<Transportation> trips) {
        this.trips = trips;
    }

    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @NotNull
    private int peopleCapacity;
    @NotNull
    private int cargoCapacityKg;

    @ElementCollection(targetClass = LicenseType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)

    private Set<LicenseType> requiredLicenses = new HashSet<>();

    @ManyToOne
    private Company company;

    @OneToMany
    private Set<Transportation> trips;

    public Vehicle() {
    }

    public Vehicle(String brand, String model, FuelType fuelType, int peopleCapacity, int cargoCapacityKg, Set<LicenseType> requiredLicenses, Company company) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.peopleCapacity = peopleCapacity;
        this.cargoCapacityKg = cargoCapacityKg;
        this.requiredLicenses = requiredLicenses;
        this.company = company;
    }

    public Vehicle(String brand, String model, FuelType fuelType, int peopleCapacity, int cargoCapacityKg, LicenseType requiredLicenses, Company company) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.peopleCapacity = peopleCapacity;
        this.cargoCapacityKg = cargoCapacityKg;
        this.requiredLicenses.add(requiredLicenses);
        this.company = company;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", fuelType=" + fuelType +
                ", peopleCapacity=" + peopleCapacity +
                ", cargoCapacityKg=" + cargoCapacityKg +
                ", requiredLiscences=" + requiredLicenses +
                '}';
    }
}