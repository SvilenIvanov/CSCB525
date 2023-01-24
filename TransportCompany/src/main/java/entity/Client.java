package entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    @ManyToMany
    private Set<Transportation> transportation = new HashSet<>();


    public Client() {
    }

    public Client(String name) {
        this.name = name;
    }
    public Client(String name, Company company) {
        this.name = name;
    }
    public Client(String name, Set<Company> companies) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
