package hwr.oop.toodleedoo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

public class LocalDateTransformerTest {
    LocalDateTransformer localDateTransformer = new LocalDateTransformer();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    @Test
    void localDateTransformer_createLocalDate_returnsLocalDateSpecifiedByStringParameter() {
        assertThat(localDateTransformer.createLocalDate("22.05.2022")).isEqualTo(LocalDate.parse("22.05.2022", formatter));
    }

    @Test
    void localDateTransformer_getFormatDate_returnsStringInTheCorrectFormatAfterBeingGivenLocalDAteObject() {
        assertThat(localDateTransformer.getFormatDate(LocalDate.parse("22.05.2022", formatter))).isEqualTo("22.05.2022");
    }

    @Test
    void localDateTransformer_createLocalDate_returnsCorrectLocalDateWithoutBeingGivenAYearInString() {
        String date = "22.05.";
        String currentYear = String.valueOf(LocalDate.now().getYear());
        assertThat(localDateTransformer.createLocalDate(date)).isEqualTo(LocalDate.parse("22.05."+currentYear, formatter));
    }

    @Test
    void localDateTransformer_createLocalDate_raisesError_InvalidInputString() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            localDateTransformer.createLocalDate("222.05.");
        });
    }

    @Test
    void localDateTransformer_createLocalDate_GivesLocalDateWithStringInTheFormatddMMYY() {
        String date = "22.05.22";
        assertThat(localDateTransformer.createLocalDate(date)).isEqualTo(LocalDate.parse("22.05.2022", formatter));
    }

    @Test
    void localDateTransformer_createLocalDate_parameterToday_ReturnsLocalDateForToday() {
        assertThat(localDateTransformer.createLocalDate("today")).isEqualTo(LocalDate.now());
    }


}
