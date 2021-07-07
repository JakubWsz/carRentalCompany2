package pl.kuba.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Revenue extends BaseEntity{
    private BigDecimal annualIncome;
    private BigDecimal totalIncome;
    private BigDecimal annualInvestments;
}
