package pl.kuba.domain.services;

import org.springframework.stereotype.Service;
import pl.kuba.domain.stores.CarStore;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.BodyType;
import pl.kuba.entities.Car;
import pl.kuba.entities.Revenue;
import pl.kuba.infrastructure.persistence.RevenueRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RevenueService {
    private final RevenueRepository revenueStore;
    private final CarStore carStore;

    public RevenueService(RevenueRepository revenueRepository, CarStore carRepository) {
        this.revenueStore = revenueRepository;
        this.carStore = carRepository;
    }

    public Revenue buyCar(String brand, String model, String registration, BodyType bodyType, int productionYear,
                          String color, int carMileage, AvailabilityStatus availabilityStatus, BigDecimal amountPerDay,
                          BigDecimal price) {
        Car car = new Car(brand,model,registration,bodyType,productionYear,color,carMileage,availabilityStatus,
                amountPerDay);
        Revenue revenue = new Revenue();
        revenue.setAnnualInvestments(price);
        car.setModificationDate(LocalDateTime.now());
        carStore.save(car);
        revenue.setModificationDate(LocalDateTime.now());
        return revenueStore.save(revenue);
    }

    public Revenue sellCar(String brand, String model, String registration, BodyType bodyType, int productionYear,
                           String color, int carMileage, AvailabilityStatus availabilityStatus, BigDecimal amountPerDay,
                           BigDecimal payment) {
        Car car = new Car(brand,model,registration,bodyType,productionYear,color,carMileage,availabilityStatus,
                amountPerDay);
        Revenue revenue = new Revenue();
        revenue.setAnnualIncome(payment);
        car.setDeleted(true);
        car.setModificationDate(LocalDateTime.now());
        carStore.save(car);
        revenue.setModificationDate(LocalDateTime.now());
        return revenueStore.save(revenue);
    }

    public BigDecimal countTotalIncome() {
        BigDecimal investmentAmount = BigDecimal.ZERO;
        List<Revenue> investmentsList = revenueStore.findAll().stream()
                .filter(revenue -> !revenue.getAnnualInvestments().equals(BigDecimal.ZERO))
                .collect(Collectors.toUnmodifiableList());
        for (Revenue investment : investmentsList) {
            investmentAmount.add(investment.getAnnualInvestments());
        }
        BigDecimal incomesAmount = BigDecimal.ZERO;
        List<Revenue> incomesList = revenueStore.findAll().stream()
                .filter(revenue -> !revenue.getAnnualIncome().equals(BigDecimal.ZERO))
                .collect(Collectors.toUnmodifiableList());
        for (Revenue income : incomesList) {
            incomesAmount.add(income.getAnnualIncome());
        }
        return incomesAmount.subtract(investmentAmount);
    }
}