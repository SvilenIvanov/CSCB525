package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import models.TransportationType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @ManyToMany
    private Set<Client> paidClients = new HashSet<>();

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

    public void payClient(Client client) {
        this.paidClients.add(client);
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