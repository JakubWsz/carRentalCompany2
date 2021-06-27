package pl.kuba.entities;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
@Embeddable
public class Car extends BaseEntity {
    private String brand;
    private String model;
    private BodyType bodyType;
    private int productionYear;
    private String color;
    private int carMileage;
    private Boolean availabilityStatus;
    private int amountPerDay;
}
