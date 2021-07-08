package pl.kuba.api.request.car;

import lombok.Getter;

@Getter
public class UpdateCarMileageRequest {
    private final long id;
    private final int carMileage;

    public UpdateCarMileageRequest(long id, int carMileage) {
        this.id = id;
        this.carMileage = carMileage;
    }
}
