package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;



    public Company() {
    }

    public Company(String name) {
        this.name = name;
    }

    public Company(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Company(long id, String name, String address) {
        this.id = id;
        this.name = name;

    }



    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
