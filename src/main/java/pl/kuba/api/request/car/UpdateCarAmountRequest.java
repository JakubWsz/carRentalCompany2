package pl.kuba.api.request.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCarAmountRequest {
    private final long id;
    private final int carAmountPerDay;

    public UpdateCarAmountRequest(long id, int carAmountPerDay) {
        this.id = id;
        this.carAmountPerDay = carAmountPerDay;
    }
}
