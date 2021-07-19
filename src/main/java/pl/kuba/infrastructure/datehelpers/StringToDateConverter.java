package pl.kuba.infrastructure.datehelpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToDateConverter {

    public static LocalDate convertStringToDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(stringDate, formatter);
    }
}