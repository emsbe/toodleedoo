package hwr.oop.toodleedoo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateTransformer {

    public LocalDate createLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            // if the date is given without the year
            if (date.length() == 6) {
                date = date + LocalDate.now().getYear();
            // if the date is given with the year, but 22, not 2022
            } else if (date.length() == 8) {
                date = date.substring(0, Math.min(date.length(), 6)) + LocalDate.now().getYear();
            }
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid input. Please enter your date in the following format: dd.MM.YYYY");
        }
    }

    public String getFormatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

}
