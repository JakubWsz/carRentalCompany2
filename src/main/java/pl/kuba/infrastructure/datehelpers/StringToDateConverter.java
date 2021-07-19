package pl.kuba.infrastructure.datehelpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter {

    public static Date convertStringToDate(String stringDate) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
    }
}
