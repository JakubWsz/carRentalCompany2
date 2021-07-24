package pl.kuba.api.apies;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.kuba.api.request.car.CarAvailabilityStatusRequest;
import pl.kuba.domain.services.CarAvailabilityAsDatesService;
import pl.kuba.entities.AvailabilityStatus;

import java.time.LocalDate;
import java.util.Map;

@RestController("/car-availability")
public class CarAvailabilityAsDatesAPI {
    private final CarAvailabilityAsDatesService carAvailabilityAsDatesService;

    public CarAvailabilityAsDatesAPI(CarAvailabilityAsDatesService carAvailabilityAsDatesService) {
        this.carAvailabilityAsDatesService = carAvailabilityAsDatesService;
    }

    @GetMapping("/status")
    public Map<LocalDate, AvailabilityStatus> getCarAvailabilityStatusByParticularDate(
            @RequestParam CarAvailabilityStatusRequest carAvailabilityStatusRequest) {
        return carAvailabilityAsDatesService.getCarAvailabilityStatusByParticularDate(
                carAvailabilityStatusRequest.getId(),
                carAvailabilityStatusRequest.getDate()
        );
    }
}
