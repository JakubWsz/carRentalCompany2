package pl.kuba.api.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarAvailabilityStatusRequest {
   private final long id;
   private final String date;
}
