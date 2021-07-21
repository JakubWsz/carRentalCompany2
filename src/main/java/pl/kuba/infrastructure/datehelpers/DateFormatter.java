package pl.kuba.infrastructure.datehelpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateFormatter {
    public static String dateFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.now().format(formatter);
    }
}