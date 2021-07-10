package pl.kuba.api.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.AvailabilityStatus;

@Getter
@AllArgsConstructor
public class UpdateCarAvailabilityStatusRequest {
    private final long id;
    private final AvailabilityStatus availabilityStatus;
    private final String note;
}
