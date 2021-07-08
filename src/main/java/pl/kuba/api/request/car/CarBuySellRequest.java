package pl.kuba.api.request.car;

import lombok.Getter;
import lombok.Setter;
import pl.kuba.entities.Car;

import java.math.BigDecimal;

@Getter
@Setter
public class CarBuySellRequest {
    private final Car car;
    private final BigDecimal price;

    public CarBuySellRequest(Car car, BigDecimal price) {
        this.car = car;
        this.price = price;
    }
}
