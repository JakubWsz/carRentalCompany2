package pl.kuba.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CarAvailabilityAsDates {
    private final LocalDate rentDate;
    private final LocalDate returnDate;
}
