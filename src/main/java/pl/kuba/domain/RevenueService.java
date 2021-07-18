package pl.kuba.domain;

import org.springframework.stereotype.Service;
import pl.kuba.entities.Car;
import pl.kuba.entities.Revenue;
import pl.kuba.infrastructure.CarStore;
import pl.kuba.infrastructure.RevenueStore;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RevenueService {
    private final RevenueStore revenueStore;
    private final CarStore carStore;

    public RevenueService(RevenueStore revenueRepository, CarStore carRepository) {
        this.revenueStore = revenueRepository;
        this.carStore = carRepository;
    }

    public Revenue invest(Car car, BigDecimal price) {
        Revenue revenue = new Revenue();
        revenue.setAnnualInvestments(price);
        carStore.save(car);
        return revenueStore.save(revenue);
    }

    public Revenue addPayment(Car car, BigDecimal payment) {
        Revenue revenue = new Revenue();
        revenue.setAnnualIncome(payment);
        carStore.delete(car);
        return revenueStore.save(revenue);
    }

    public BigDecimal countTotalIncome() {
        BigDecimal investmentAmount = BigDecimal.ZERO;
        List<Revenue> investmentsList = revenueStore.findAll().stream()
                .filter(revenue -> !revenue.getAnnualInvestments().equals(BigDecimal.ZERO))
                .collect(Collectors.toUnmodifiableList());
        for (Revenue investment : investmentsList) {
            investmentAmount.add(investment.getAnnualInvestments());
        }
        BigDecimal incomesAmount = BigDecimal.ZERO;
        List<Revenue> incomesList = revenueStore.findAll().stream()
                .filter(revenue -> !revenue.getAnnualIncome().equals(BigDecimal.ZERO))
                .collect(Collectors.toUnmodifiableList());
        for (Revenue income : incomesList) {
            incomesAmount.add(income.getAnnualIncome());
        }
        return incomesAmount.subtract(investmentAmount);
    }
}