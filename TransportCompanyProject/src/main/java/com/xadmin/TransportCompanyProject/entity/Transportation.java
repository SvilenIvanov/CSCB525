package com.xadmin.TransportCompanyProject.entity;

import com.xadmin.TransportCompanyProject.models.TransportationType;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "transportation")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String startingPoint;
    @NotNull
    private String endingPoint;
    @NotNull
    private Date departure;
    @NotNull
    private Date arrival;
    @NotNull
    private BigDecimal basePrice;
    @NotNull
    private BigDecimal totalPrice;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransportationType type;
    private int peopleOnboard;
    private int cargoSize;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee driver;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToMany
    private Set<Client> clients = new HashSet<>();

    public long getId() {
        return id;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TransportationType getType() {
        return type;
    }

    public void setType(TransportationType type) {
        this.type = type;
    }

    public int getPeopleOnboard() {
        return peopleOnboard;
    }

    public void setPeopleOnboard(int peopleOnboard) {
        this.peopleOnboard = peopleOnboard;
    }

    public int getCargoSize() {
        return cargoSize;
    }

    public void setCargoSize(int cargoSize) {
        this.cargoSize = cargoSize;
    }

    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Transportation() {
    }

    public Transportation(String startingPoint, String endingPoint, Date departure, Date arrival, BigDecimal basePrice, TransportationType type,
                          int cargoSize, Employee driver, Company comp, Vehicle vehicle, Set<Client> clients) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.departure = departure;
        this.arrival = arrival;
        this.clients = clients;
        if (type == TransportationType.PASSENGER_TRIP) {
            this.peopleOnboard = (short) (this.clients.size() + 1);
        }
        else {
            this.peopleOnboard = 1;
        }
        this.cargoSize = cargoSize;
        this.vehicle = vehicle;
        this.driver = driver;
        this.basePrice = basePrice;
        this.type = type;
        this.company = comp;

        if (this.type == TransportationType.PASSENGER_TRIP) {
            this.totalPrice = this.basePrice.multiply(BigDecimal.valueOf(this.clients.size()));
        }
        else {
            this.totalPrice = this.basePrice.multiply(BigDecimal.valueOf(cargoSize));
        }
    }
    public Transportation(String startingPoint, String endingPoint, Date departure, Date arrival, BigDecimal basePrice, TransportationType type,
                          int cargoSize, Employee driver, Company comp, Vehicle vehicle, Client client) {
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.departure = departure;
        this.arrival = arrival;
        this.clients.add(client);
        if (type == TransportationType.PASSENGER_TRIP) {
            this.peopleOnboard = (short) (this.clients.size() + 1);
        }
        else {
            this.peopleOnboard = 1;
        }
        this.cargoSize = cargoSize;
        this.vehicle = vehicle;
        this.driver = driver;
        this.basePrice = basePrice;
        this.type = type;
        this.company = comp;

        if (this.type == TransportationType.PASSENGER_TRIP) {
            this.totalPrice = this.basePrice.multiply(BigDecimal.valueOf(this.clients.size()));
        }
        else {
            this.totalPrice = this.basePrice.multiply(BigDecimal.valueOf(cargoSize));
        }
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", startingPoint='" + startingPoint + '\'' +
                ", endingPoint='" + endingPoint + '\'' +
                ", departure=" + departure + '\'' +
                ", arrival=" + arrival + '\'' +
                ", basePrice=" + basePrice + '\'' +
                ", totalPrice=" + totalPrice + '\'' +
                ", type=" + type + '\'' +
                ", peopleOnboard=" + peopleOnboard + '\'' +
                ", cargoSize=" + cargoSize + '\'' +
                ", driver=" + driver + '\'' +
                ", vehicle=" + vehicle + '\'' +
                ", company=" + company + '\'' +
                ", passengers=" + clients + '\''+
                '}';
    }


}