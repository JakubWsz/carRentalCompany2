package pl.kuba.api.dto.request.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.BodyType;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CarBuySellRequest {
    private final String brand;
    private final String model;
    private final String registration;
    private final BodyType bodyType;
    private final int productionYear;
    private final String color;
    private final int carMileage;
    private final AvailabilityStatus availabilityStatus;
    private final BigDecimal amountPerDay;
    private final BigDecimal priceOrPayment;
}
