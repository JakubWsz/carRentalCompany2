package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Car;
import pl.kuba.entities.Revenue;
import pl.kuba.infrastructure.CarRepository;
import pl.kuba.infrastructure.RevenueRepository;
import pl.kuba.infrastructure.persistence.RevenueRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RevenueService {
    private final RevenueRepository revenueRepository;
    private final CarRepository carRepository;

    public RevenueService(RevenueRepository revenueRepository, CarRepository carRepository) {
        this.revenueRepository = revenueRepository;
        this.carRepository = carRepository;
    }

    public Revenue invest(Car car, BigDecimal price) {
        Revenue revenue = new Revenue();
        revenue.setAnnualInvestments(price);
        carRepository.save(car);
        return revenueRepository.save(revenue);
    }

    public Revenue addPayment(Car car, BigDecimal payment) {
        Revenue revenue = new Revenue();
        revenue.setAnnualIncome(payment);
        carRepository.delete(car);
        return revenueRepository.save(revenue);
    }

    public BigDecimal countTotalIncome() {
        BigDecimal investmentAmount = BigDecimal.ZERO;
        List<Revenue> investmentsList = revenueRepository.findAll().stream()
                .filter(revenue -> !revenue.getAnnualInvestments().equals(BigDecimal.ZERO))
                .collect(Collectors.toUnmodifiableList());
        for (Revenue investment : investmentsList) {
            investmentAmount.add(investment.getAnnualInvestments());
        }
        BigDecimal incomesAmount = BigDecimal.ZERO;
        List<Revenue> incomesList = revenueRepository.findAll().stream()
                .filter(revenue -> !revenue.getAnnualIncome().equals(BigDecimal.ZERO))
                .collect(Collectors.toUnmodifiableList());
        for (Revenue income : incomesList) {
            incomesAmount.add(income.getAnnualIncome());
        }
        return incomesAmount.subtract(investmentAmount);
    }
}