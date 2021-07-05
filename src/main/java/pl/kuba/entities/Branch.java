package pl.kuba.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

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

    public String getAddress() {
        return address;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public List<Car> getAvailableCars() {
        return availableCars;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    public void setAvailableCars(List<Car> availableCars) {
        this.availableCars = availableCars;
    }
}
