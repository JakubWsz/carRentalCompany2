package pl.kuba.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kuba.domain.repository.TestBranchStore;
import pl.kuba.domain.repository.TestCarStore;
import pl.kuba.domain.repository.TestReservationStore;
import pl.kuba.domain.servises.CarService;
import pl.kuba.entities.*;

import java.math.BigDecimal;
import java.time.LocalDate;

class CarServiceTest {

    @Test
    public void updateCarMileageShouldChangeMileageValue() {
        //given
        TestCarStore testCarStore = new TestCarStore();
        TestBranchStore testBranchStore = new TestBranchStore();
        TestReservationStore testReservationStore = new TestReservationStore();
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        testCarStore.save(car);
        CarService carService = new CarService(testCarStore, testBranchStore, testReservationStore);

        //when
        carService.updateCarMileage(0L, 12);

        //then
        Assertions.assertEquals(testCarStore.findById(0L).get().getCarMileage(), 12);
    }

    @Test
    public void updateCarAmountPerDayShouldChangeAmountValue() {
        //given
        TestCarStore testCarStore = new TestCarStore();
        TestBranchStore testBranchStore = new TestBranchStore();
        TestReservationStore testReservationStore = new TestReservationStore();
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        testCarStore.save(car);
        CarService carService = new CarService(testCarStore, testBranchStore, testReservationStore);

        //when
        carService.updateCarAmountPerDay(car.getId(), 145, 99);

        Assertions.assertEquals(testCarStore.findById(car.getId()).get().getAmountPerDay()
                , new BigDecimal(String.format("%d.%d", 145, 99)));
    }

    @Test
    public void updateAvailabilityStatusShouldReturnUpdatedStatus() {
        //given
        TestCarStore testCarStore = new TestCarStore();
        TestBranchStore testBranchStore = new TestBranchStore();
        TestReservationStore testReservationStore = new TestReservationStore();
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.BROKEN, BigDecimal.valueOf(120L));
        testCarStore.save(car);
        CarService carService = new CarService(testCarStore, testBranchStore, testReservationStore);
        //when
        carService.updateAvailabilityStatus(car.getId(), AvailabilityStatus.AVAILABLE, "");

        //then
        Assertions.assertEquals(testCarStore.findById(car.getId()).get().getAvailabilityStatus(), AvailabilityStatus.AVAILABLE);
    }

    @Test
    public void getAvailableCarsShouldReturn3Cars() {
        //given
        TestCarStore testCarStore = new TestCarStore();
        TestBranchStore testBranchStore = new TestBranchStore();
        TestReservationStore testReservationStore = new TestReservationStore();
        Branch branch = new Branch("Tontoronto");
        testBranchStore.save(branch);
        Car car = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        testCarStore.save(car);
        Car car1 = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        testCarStore.save(car1);
        Car car2 = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.BROKEN, BigDecimal.valueOf(120L));
        testCarStore.save(car2);
        Car car3 = new Car("Mercedes", "benc", BodyType.CONVERTIBLE, 1999, "black",
                10000, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120L));
        testCarStore.save(car3);
        Reservation reservation = new Reservation(LocalDate.now(), new Client(), car, LocalDate.now(),
                LocalDate.now(), branch, branch, car.getAmountPerDay());
        testReservationStore.save(reservation);
        Reservation reservation1 = new Reservation(LocalDate.now(), new Client(), car1, LocalDate.now(),
                LocalDate.now(), branch, branch, car.getAmountPerDay());
        testReservationStore.save(reservation1);
        Reservation reservation2 = new Reservation(LocalDate.now(), new Client(), car2, LocalDate.now(),
                LocalDate.now(), branch, branch, car.getAmountPerDay());
        testReservationStore.save(reservation2);
        Reservation reservation3 = new Reservation(LocalDate.now(), new Client(), car3, LocalDate.now(),
                LocalDate.now(), branch, branch, car.getAmountPerDay());
        testReservationStore.save(reservation3);
        CarService carService = new CarService(testCarStore, testBranchStore, testReservationStore);

        //when
        int size = carService.getAvailableCars("Tontoronto").size();

        //given
        Assertions.assertEquals(3, size);
    }
}

