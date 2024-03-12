package com.example.TransportCompany.entity;

import com.example.TransportCompany.misc.GoodsType;
import com.example.TransportCompany.misc.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transports")
public class Transport
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Timestamp departureDate;

    private Timestamp deliveryDate;

    private String destination;

    private double price;

    @Enumerated(EnumType.STRING)
    private GoodsType goods;

    private Double weight;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicle;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", departureDate=" + departureDate +
                ", deliveryDate=" + deliveryDate +
                ", destination='" + destination + '\'' +
                ", price=" + price +
                ", goods=" + goods +
                ", weight=" + weight +
                ", vehicle=" + vehicle +
                ", clientId=" + (client != null ? client.getId() : "null") +
                ", companyId=" + (company != null ? company.getId() : "null") +
                ", employeeId=" + (employee != null ? employee.getId() : "null") +
                '}';
    }
}
