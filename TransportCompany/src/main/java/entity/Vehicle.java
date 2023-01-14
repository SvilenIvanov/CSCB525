package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import models.FuelType;
import models.LicenseType;

import javax.persistence.*;
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
    private String make;
    @NotNull
    private String model;
    @NotNull
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @NotNull
    private short peopleCapacity;
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

    public Vehicle(String make, String model, FuelType fuelType, short peopleCapacity, int cargoCapacityKg, Set<LicenseType> requiredLicenses, Company company) {
        this.make = make;
        this.model = model;
        this.fuelType = fuelType;
        this.peopleCapacity = peopleCapacity;
        this.cargoCapacityKg = cargoCapacityKg;
        this.requiredLicenses = requiredLicenses;
        this.company = company;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", fuelType=" + fuelType +
                ", peopleCapacity=" + peopleCapacity +
                ", cargoCapacityKg=" + cargoCapacityKg +
                ", requiredLiscences=" + requiredLicenses +
                '}';
    }
}