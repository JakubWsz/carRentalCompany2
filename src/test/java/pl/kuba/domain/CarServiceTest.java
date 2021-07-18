package pl.kuba.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.BodyType;
import pl.kuba.entities.Car;

import java.math.BigDecimal;
import java.util.Optional;

class CarServiceTest {


    @Test
    public void updateCarMileageShouldChangeMileageValue() {
        //given
        CarService carService = new CarService(new TestCarStore(), null, null);
        //when
        carService.updateCarMileage(1, 12);

        //then
        Assertions.assertEquals(testCarRepository.findById(1L).get().getCarMileage(), 12);
    }

    @Test
    public void updateCarAmountPerDayShouldChangeAmountValue() {
        //given
        TestCarRepository testCarRepository = new TestCarRepository();
        CarService carService = new CarService(new TestCarStore(), null, null);

        //when
        carService.updateCarAmountPerDay(1L, 145, 99);

        Assertions.assertEquals(testCarRepository.findById(1L).get().getAmountPerDay()
                , new BigDecimal(String.format("%d.%d", 145, 99)));
    }

    @Test
    public void updateAvailabilityStatus() {
        //given
        TestCarRepository testCarRepository = new TestCarRepository();
        CarService carService = new CarService(new TestCarStore(), null, null);

        //when
        carService.updateAvailabilityStatus(1L,AvailabilityStatus.AVAILABLE,"");

        //then
        Assertions.assertEquals(testCarRepository.findById(1L).get().getAvailabilityStatus(), AvailabilityStatus.AVAILABLE );
    }


    static class TestCarStore implements CarStore {
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));

        @Override
        public Optional<Car> findById(Long id) {
            return Optional.of(car);
        }
    }
}
