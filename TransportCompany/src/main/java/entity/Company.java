package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name="name", nullable = false)
    private String name;

    @OneToMany
    @Column(name = "transportations")
    private Set<Transportation> transportations;

    @OneToMany
    @Column(name = "employees")
    private Set<Employee> employees;
    @OneToMany
    @Column(name = "vehicles")
    private Set<Vehicle> vehicles;

    @ManyToMany
    @Column(name = "clients")
    private Set<Client> clients;

    public Company() {
        this.name = "";
        this.transportations = new HashSet<>();
        this.employees = new HashSet<>();
        this.vehicles = new HashSet<>();
        this.clients = new HashSet<>();
    }
    public Company(String name) {
        this();
        this.name = name;
    }
    public Company(String name, Set<Employee> employees) {
        this(name);
        this.employees = employees;
    }
    public Company(String name, Set<Employee> employees, Set<Vehicle> vehicles) {
        this(name, employees);
        this.vehicles = vehicles;
    }
    public Company(String name, Set<Employee> employees, Set<Vehicle> vehicles, Set<Client> clients) {
        this(name, employees, vehicles);
        this.clients = clients;
    }

    public void addClients(Client client) {
        this.clients.add(client);
    }
    public void addClients(Set<Client> clients) {
        this.clients.addAll(clients);
    }


    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
