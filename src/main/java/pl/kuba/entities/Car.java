package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Car extends BaseEntity {
    private String brand;
    private String model;
    private String registration;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private int productionYear;
    private String color;
    private int carMileage;
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;
    private BigDecimal amountPerDay;

    public Car(String brand, String model, String registration, BodyType bodyType, int productionYear,
               String color, int carMileage, AvailabilityStatus availabilityStatus, BigDecimal amountPerDay) {
        this.brand = brand;
        this.model = model;
        this.registration = registration;
        this.bodyType = bodyType;
        this.productionYear = productionYear;
        this.color = color;
        this.carMileage = carMileage;
        this.availabilityStatus = availabilityStatus;
        this.amountPerDay = amountPerDay;
    }

    public Car() {

    }
}
