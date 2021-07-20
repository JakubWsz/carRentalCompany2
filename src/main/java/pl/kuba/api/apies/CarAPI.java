package pl.kuba.api.apies;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.request.car.*;
import pl.kuba.domain.servises.CarService;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.Car;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController("/car")
public class CarAPI {
    private final CarService carService;

    public CarAPI(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars(@RequestParam AvailableCarRequest availableCarRequest) {
        return carService.getAvailableCars(
                availableCarRequest.getBranchLocation()
        );
    }

    @PutMapping("/status")
    public String updateAvailabilityStatus(@RequestParam UpdateCarAvailabilityStatusRequest updateCarAvailabilityStatusRequest) {
        return carService.updateAvailabilityStatus(
                updateCarAvailabilityStatusRequest.getId(),
                updateCarAvailabilityStatusRequest.getAvailabilityStatus(),
                updateCarAvailabilityStatusRequest.getNote()
        );
    }

    @PutMapping("/amount")
    public void updateCarAmountPerDay(@RequestParam UpdateCarAmountRequest updateCarAmountRequest) {
        carService.updateCarAmountPerDay(
                updateCarAmountRequest.getId(),
                updateCarAmountRequest.getCarAmountPerDayGoldCoin(),
                updateCarAmountRequest.getCarAmountPerDayPennyCoin()
        );
    }

    @PutMapping("/mileage")
    public void updateCarMileage(@RequestParam UpdateCarMileageRequest updateCarMileageRequest) {
        carService.updateCarMileage(updateCarMileageRequest.getId(),
                updateCarMileageRequest.getCarMileage()
        );
    }
}