package pl.kuba.api.dto.response.user;

import pl.kuba.entities.Car;
import pl.kuba.entities.Worker;

import java.util.List;

public class BranchDetailsView {
    private long id;
    private String address;
    private List<Worker> workers;
    private List<Car> availableCars;

    public BranchDetailsView(long id, String address, List<Worker> workers, List<Car> availableCars) {
        this.id = id;
        this.address = address;
        this.workers = workers;
        this.availableCars = availableCars;
    }

    public long getId() {
        return id;
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
}
