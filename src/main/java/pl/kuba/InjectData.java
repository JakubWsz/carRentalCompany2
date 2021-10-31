package pl.kuba;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import pl.kuba.domain.services.CarService;
import pl.kuba.domain.services.RentalCompanyService;
import pl.kuba.domain.services.RevenueService;
import pl.kuba.entities.AvailabilityStatus;
import pl.kuba.entities.BodyType;

import java.math.BigDecimal;

@Configuration
public class InjectData implements ApplicationRunner {
    private final RevenueService revenueService;
    private final RentalCompanyService rentalCompanyService;

    public InjectData(RevenueService revenueService, RentalCompanyService rentalCompanyService, CarService carService) {
        this.revenueService = revenueService;
        this.rentalCompanyService = rentalCompanyService;
    }

    @Override
    @ConditionalOnProperty(name = "run.enabled", havingValue = "true")
    public void run(ApplicationArguments args) {
        rentalCompanyService.openNewBranch("some branch");
        revenueService.buyCar("xyz","bndf","123123123", BodyType.HATCHBACK,1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));
        revenueService.buyCar("asfassfasf","dsdgsdas","123123123", BodyType.HATCHBACK,1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));
        revenueService.buyCar("xgsdgsgyz","dsdgsdgas","123123123", BodyType.HATCHBACK,1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));
        revenueService.buyCar("xsgsegsegyz","dasdgsdgs","123123123", BodyType.HATCHBACK,1980,
                "white", 123132, AvailabilityStatus.AVAILABLE, BigDecimal.valueOf(120),
                BigDecimal.valueOf(70000));
    }
}
