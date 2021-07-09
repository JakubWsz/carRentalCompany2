package pl.kuba.api.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCarAmountRequest {
    private final long id;
    private final int carAmountPerDay;
}
