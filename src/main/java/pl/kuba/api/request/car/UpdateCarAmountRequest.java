package pl.kuba.api.request.car;

import lombok.Getter;

@Getter
public class UpdateCarAmountRequest {
    private final long id;
    private final int carAmountPerDay;

    public UpdateCarAmountRequest(long id, int carAmountPerDay) {
        this.id = id;
        this.carAmountPerDay = carAmountPerDay;
    }
}
