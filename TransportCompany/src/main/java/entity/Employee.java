package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import models.LicenseTypes;

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
    private BigDecimal salary;
    @ElementCollection(targetClass = LicenseTypes.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<LicenseTypes> licenses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany
    private Set<Transportation> transportations;
}