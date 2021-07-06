package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Car extends BaseEntity {
    private String brand;
    private String model;
    private BodyType bodyType;
    private int productionYear;
    private String color;
    private int carMileage;
    private Boolean availabilityStatus;
    private int amountPerDay;

    public Car(String brand, String model, BodyType bodyType, int productionYear, String color,
               int carMileage, Boolean availabilityStatus, int amountPerDay) {
        this.brand = brand;
        this.model = model;
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
