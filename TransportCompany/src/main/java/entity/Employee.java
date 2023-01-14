package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import models.LicenseType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String address;

    @NotNull
    private String name;
    @NotNull
    private BigDecimal salary;
    @ElementCollection(targetClass = LicenseType.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<LicenseType> licenses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany
    private Set<Transportation> transportations;

    public Employee() {
    }

    public Employee(String name, String address, BigDecimal salary, Company company, Set<LicenseType> licenses) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.company = company;
        this.licenses = licenses;
    }
    public Employee(String name, String address, BigDecimal salary, Company company, LicenseType license) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.company = company;
        this.licenses.add(license);
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}