package pl.kuba.api.dto.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCarMileageRequest {
    private final long id;
    private final int carMileage;
}
