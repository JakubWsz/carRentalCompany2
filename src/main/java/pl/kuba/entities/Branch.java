package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Branch extends BaseEntity {
    private String address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Worker> workers;
    @ManyToMany
    private List<Car> availableCars;

    public Branch(String address) {
        this.address = address;
        this.workers = new ArrayList<>();
        this.availableCars = new ArrayList<>();
    }

    public Branch() {}

    @Override
    public String toString() {
        return "Branch{" +
                "address='" + address + '\'' +
                ", workers=" + workers +
                ", availableCars=" + availableCars +
                '}';
    }
}
