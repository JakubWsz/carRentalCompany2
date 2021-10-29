package pl.kuba.api.dto.request.branch;

import lombok.Getter;
import lombok.Setter;
import pl.kuba.entities.Car;
import pl.kuba.entities.Worker;

import java.util.List;

@Getter
@Setter
public class UpdateBranchRequest {
    private String address;
    private List<Worker> workers;
    private List<Car> availableCars;
}
