package pl.kuba.api.dto.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.AvailabilityStatus;

@Getter
@AllArgsConstructor
public class AvailableCarRequest {
    private final String branchLocation;
    private final AvailabilityStatus availabilityStatus;
}
