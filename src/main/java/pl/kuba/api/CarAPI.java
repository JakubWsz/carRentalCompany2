package pl.kuba.api;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.request.car.*;
import pl.kuba.domain.CarService;
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

    @PostMapping("/buy")
    public Car buyCar(@RequestBody CarBuySellRequest carBuyRequest) {
        return carService.buyCar(
                carBuyRequest.getCar(),
                carBuyRequest.getPrice()
        );
    }

    @DeleteMapping("/sell")
    public void sellCar(@RequestParam CarBuySellRequest carBuyRequest) {
        carService.sellCar(
                carBuyRequest.getCar(),
                carBuyRequest.getPrice()
        );
    }

    @GetMapping("available")
    public List<Car> getAvailableCars(@RequestParam AvailableCarRequest availableCarRequest) throws ParseException {
        return carService.getAvailableCars(
                availableCarRequest.getBranchLocation(),
                availableCarRequest.getDate()
        );
    }

    @GetMapping("status")
    public Map<LocalDate, AvailabilityStatus> getCarAvailabilityStatusByParticularDate(
            @RequestParam CarAvailabilityStatusRequest carAvailabilityStatusRequest) throws ParseException {
        return carService.getCarAvailabilityStatusByParticularDate(
                carAvailabilityStatusRequest.getId(),
                carAvailabilityStatusRequest.getDate()
        );
    }

    @PutMapping("status")
    public String updateAvailabilityStatus(@RequestParam UpdateCarAvailabilityStatusRequest updateCarAvailabilityStatusRequest) {
        return carService.updateAvailabilityStatus(
                updateCarAvailabilityStatusRequest.getId(),
                updateCarAvailabilityStatusRequest.getAvailabilityStatus(),
                updateCarAvailabilityStatusRequest.getNote()
        );
    }

    @PutMapping("amount")
    public void updateCarAmountPerDay(@RequestParam UpdateCarAmountRequest updateCarAmountRequest) {
        carService.updateCarAmountPerDay(
                updateCarAmountRequest.getId(),
                updateCarAmountRequest.getCarAmountPerDayGoldCoin(),
                updateCarAmountRequest.getCarAmountPerDayPennyCoin()
        );
    }

    @PutMapping("mileage")
    public void updateCarMileage(@RequestParam UpdateCarMileageRequest updateCarMileageRequest) {
        carService.updateCarMileage(updateCarMileageRequest.getId(),
                updateCarMileageRequest.getCarMileage()
        );
    }
}