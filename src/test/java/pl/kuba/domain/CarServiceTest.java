package pl.kuba.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.helpers.TestCarAvailabilityAsDatesRepository;
import pl.kuba.helpers.TestCarRepository;

import java.math.BigDecimal;

class CarServiceTest {


    @Test
    public void updateCarMileageShouldChangeMileageValue() {
        //given
        TestCarRepository testCarRepository = new TestCarRepository();
        CarService carService = new CarService(testCarRepository, null, null,
                null);
        //when
        carService.updateCarMileage(1, 12);

        //then
        Assertions.assertEquals(testCarRepository.findById(1L).get().getCarMileage(), 12);
    }

    @Test
    public void updateCarAmountPerDayShouldChangeAmountValue() {
        //given
        TestCarRepository testCarRepository = new TestCarRepository();
        CarService carService = new CarService(testCarRepository, null, null,
                null);

        //when
        carService.updateCarAmountPerDay(1L, 145, 99);

        Assertions.assertEquals(testCarRepository.findById(1L).get().getAmountPerDay()
                , new BigDecimal(String.format("%d.%d", 145, 99)));
    }

    @Test
    public void updateAvailabilityStatus() {
        //given
        TestCarRepository testCarRepository = new TestCarRepository();
        CarService carService = new CarService(testCarRepository, null, null,
                null);

        //when
        carService.updateAvailabilityStatus(1L, AvailabilityStatus.AVAILABLE, "");

        //then
        Assertions.assertEquals(testCarRepository.findById(1L).get().getAvailabilityStatus(), AvailabilityStatus.AVAILABLE);
    }

    @Test
    public void getCarAvailabilityStatusByParticularDateShouldReturnMapWithDatesAndAvailabilityStatus() {
        //given
        TestCarRepository testCarRepository = new TestCarRepository();
        TestCarAvailabilityAsDatesRepository carAvailabilityAsDatesRepository = new TestCarAvailabilityAsDatesRepository();
        CarService carService;


        //when
        carService.getCarAvailabilityStatusByParticularDate(1,"21-08-2021");

        //then
        Assertions.assertEquals(testCarRepository.findById(1L).get().getAvailabilityStatus(), AvailabilityStatus.RENT);
    }


}