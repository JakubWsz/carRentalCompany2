package pl.kuba.api.request.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarAvailabilityStatusRequest {
    private final long id;
    private final Boolean availabilityStatus;
    private final String note;

    public UpdateCarAvailabilityStatusRequest(long id, Boolean availabilityStatus, String note) {
        this.id = id;
        this.availabilityStatus = availabilityStatus;
        this.note = note;
    }
}
