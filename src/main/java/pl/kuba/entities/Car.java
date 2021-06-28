package pl.kuba.entities;

import javax.persistence.Entity;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(int carMileage) {
        this.carMileage = carMileage;
    }

    public Boolean getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(Boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public int getAmountPerDay() {
        return amountPerDay;
    }

    public void setAmountPerDay(int amountPerDay) {
        this.amountPerDay = amountPerDay;
    }
}
