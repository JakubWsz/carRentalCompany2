package pl.kuba.api.rest;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.dto.request.car.*;
import pl.kuba.domain.services.CarService;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.Car;

import java.util.List;

@RestController()
@RequestMapping("/car")
public class CarAPI {
    private final CarService carService;

    public CarAPI(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/available")
    public List<Car> getAvailableCars(@RequestParam String branchLocation) {
        return carService.getAvailableCars(
                branchLocation
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