package pl.kuba.api.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCarAvailabilityStatusRequest {
    private final long id;
    private final Boolean availabilityStatus;
    private final String note;
}
