package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Branch extends BaseEntity {
    private String address;
    @OneToMany
    private List<Worker> workers;
    @ManyToMany
    private List<Car> availableCars;

    public Branch(String address) {
        this.address = address;
        this.workers = new ArrayList<>();
        this.availableCars = new ArrayList<>();
    }

    public Branch() {

    }
}
