package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Entity
public class CarAvailabilityAsDates extends BaseEntity{
    private LocalDate rentDate;
    private LocalDate returnDate;

    public CarAvailabilityAsDates() {

    }
}
