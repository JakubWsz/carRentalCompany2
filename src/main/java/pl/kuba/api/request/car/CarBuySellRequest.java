package pl.kuba.api.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.Car;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CarBuySellRequest {
    private final Car car;
    private final BigDecimal price;
}
