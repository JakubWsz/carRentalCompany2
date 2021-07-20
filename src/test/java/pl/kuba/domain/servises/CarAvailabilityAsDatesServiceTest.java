package pl.kuba.domain.servises;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kuba.domain.repository.TestCarAvailabilityAsDatesStore;
import pl.kuba.domain.repository.TestRentStore;
import pl.kuba.domain.repository.TestReservationStore;
import pl.kuba.domain.repository.TestReturnStore;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.Car;
import pl.kuba.entities.Rent;
import pl.kuba.entities.Reservation;

class CarAvailabilityAsDatesServiceTest {

    @Test
    public void getCarAvailabilityStatusByParticularDateShouldReturnMapWith3AvailableCars() {
        //given
        TestCarAvailabilityAsDatesStore testCarAvailabilityAsDatesStore = new TestCarAvailabilityAsDatesStore();
        TestRentStore testRentStore = new TestRentStore();
        TestReturnStore testReturnStore = new TestReturnStore();
        TestReservationStore testReservationStore = new TestReservationStore();

        CarAvailabilityAsDatesService carAvailabilityAsDatesService = new CarAvailabilityAsDatesService(
                testCarAvailabilityAsDatesStore, testRentStore, testReturnStore, testReservationStore);

        Car car = new Car(null, null, null, 1992, null, 100
                , null, null);
        Car car1 = new Car(null, null, null, 1992, null, 100
                , null, null);
        Car car2 = new Car(null, null, null, 1992, null, 100
                , null, null);
        Car car3 = new Car(null, null, null, 1992, null, 100
                , null, null);
        for (int i = 0; i <= 4; i++) {
            car.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        }
        testRentStore.save(new Rent(null,null,new Reservation()))

        for (int i = 0; i <= 2; i++) {
            car1.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        }

        for (int i = 0; i <= 8; i++) {
            car2.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        }

        for (int i = 0; i <= 4; i++) {
            car3.setAvailabilityStatus(AvailabilityStatus.RENT);
        }

        //when
        carAvailabilityAsDatesService.getCarAvailabilityStatusByParticularDate(car.getId(), "12-12-2021");
        carAvailabilityAsDatesService.getCarAvailabilityStatusByParticularDate(car1.getId(), "12-12-2021");
        carAvailabilityAsDatesService.getCarAvailabilityStatusByParticularDate(car2.getId(), "12-12-2021");
        carAvailabilityAsDatesService.getCarAvailabilityStatusByParticularDate(car3.getId(), "12-12-2021");

        //then
        Assertions.assertEquals(testReservationStore.findById(car.getId()).get().getCar().getAvailabilityStatus(),  AvailabilityStatus.AVAILABLE);
        Assertions.assertEquals(testReservationStore.findById(car1.getId()).get().getCar().getAvailabilityStatus(),  AvailabilityStatus.AVAILABLE);
        Assertions.assertEquals(testReservationStore.findById(car2.getId()).get().getCar().getAvailabilityStatus(),  AvailabilityStatus.AVAILABLE);
        Assertions.assertEquals(testReservationStore.findById(car3.getId()).get().getCar().getAvailabilityStatus(),  AvailabilityStatus.RENT);
    }
}