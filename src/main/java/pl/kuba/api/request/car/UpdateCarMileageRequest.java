package pl.kuba.api.request.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarMileageRequest {
    private final long id;
    private final int carMileage;

    public UpdateCarMileageRequest(long id, int carMileage) {
        this.id = id;
        this.carMileage = carMileage;
    }
}
