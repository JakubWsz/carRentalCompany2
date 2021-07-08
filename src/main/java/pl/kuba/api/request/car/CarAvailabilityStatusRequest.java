package pl.kuba.api.request.car;

import lombok.Getter;

@Getter
public class CarAvailabilityStatusRequest {
   private final long id;
   private final String date;

    public CarAvailabilityStatusRequest(long id, String date) {
        this.id = id;
        this.date = date;
    }
}
