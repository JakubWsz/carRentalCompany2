package pl.kuba.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kuba.domain.servises.CarService;
import pl.kuba.domain.stores.CarStore;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.BodyType;
import pl.kuba.entities.Car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CarServiceTest {
    @Test
    public void updateCarMileageShouldChangeMileageValue() {
        //given
        TestCarStore testCarStore = new TestCarStore();
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        System.out.println(car.getId());
        testCarStore.save(car);
        CarService carService = new CarService(new TestCarStore(), null, null);

        //when
        carService.updateCarMileage(0L, 12);

        //then
        Assertions.assertEquals(testCarStore.findById(0L).get().getCarMileage(), 12);
    }

    @Test
    public void updateCarAmountPerDayShouldChangeAmountValue() {
        //given
        TestCarStore testCarStore = new TestCarStore();
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        testCarStore.save(car);
        CarService carService = new CarService(new TestCarStore(), null, null);

        //when
        carService.updateCarAmountPerDay(car.getId(), 145, 99);

        Assertions.assertEquals(testCarStore.findById(car.getId()).get().getAmountPerDay()
                , new BigDecimal(String.format("%d.%d", 145, 99)));
    }

    @Test
    public void updateAvailabilityStatus() {
        //given
        TestCarStore testCarStore = new TestCarStore();
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        testCarStore.save(car);
        CarService carService = new CarService(new TestCarStore(), null, null);
        //when
        carService.updateAvailabilityStatus(car.getId(), AvailabilityStatus.AVAILABLE, "");

        //then
        Assertions.assertEquals(testCarStore.findById(car.getId()).get().getAvailabilityStatus(), AvailabilityStatus.AVAILABLE);
    }
    }
class TestCarStore implements CarStore {
   private final List<Car> carList = new ArrayList<>();

    @Override
    public  Optional<Car> findById(Long id) {
        return carList.stream()
                .filter(car1 -> car1.getId() == id)
                .findFirst();
    }

    @Override
    public Car save(Car car) {
        carList.add(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(carList);
    }
}
