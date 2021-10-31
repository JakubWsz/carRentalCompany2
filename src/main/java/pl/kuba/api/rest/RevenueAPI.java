package pl.kuba.api.rest;

import org.springframework.web.bind.annotation.*;
import pl.kuba.api.dto.request.car.CarBuySellRequest;
import pl.kuba.domain.services.RevenueService;
import pl.kuba.entities.Revenue;

import java.math.BigDecimal;

@RestController
@RequestMapping("/revenue")
public class RevenueAPI {
    private final RevenueService revenueService;

    public RevenueAPI(RevenueService revenueService) {
        this.revenueService = revenueService;
    }

    @PostMapping("buy-car")
    public Revenue buyCar(@RequestBody CarBuySellRequest carBuySellRequest){
        return revenueService.buyCar(
                carBuySellRequest.getBrand(),
                carBuySellRequest.getModel(),
                carBuySellRequest.getRegistration(),
                carBuySellRequest.getBodyType(),
                carBuySellRequest.getProductionYear(),
                carBuySellRequest.getColor(),
                carBuySellRequest.getCarMileage(),
                carBuySellRequest.getAvailabilityStatus(),
                carBuySellRequest.getAmountPerDay(),
                carBuySellRequest.getPriceOrPayment()
        );
    }

    @DeleteMapping("sell-car")
    public Revenue sellCar (@RequestBody CarBuySellRequest carBuySellRequest){
        return revenueService.sellCar(
                carBuySellRequest.getBrand(),
                carBuySellRequest.getModel(),
                carBuySellRequest.getRegistration(),
                carBuySellRequest.getBodyType(),
                carBuySellRequest.getProductionYear(),
                carBuySellRequest.getColor(),
                carBuySellRequest.getCarMileage(),
                carBuySellRequest.getAvailabilityStatus(),
                carBuySellRequest.getAmountPerDay(),
                carBuySellRequest.getPriceOrPayment()
        );
    }

    @GetMapping("get-total-income")
    public BigDecimal getTotalIncome(){
        return revenueService.countTotalIncome();
    }
}
