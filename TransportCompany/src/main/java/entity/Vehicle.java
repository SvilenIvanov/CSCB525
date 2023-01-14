package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import models.FuelType;
import models.LicenseType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    private Set<LicenseType> requiredLicenses;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "vehicle")
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