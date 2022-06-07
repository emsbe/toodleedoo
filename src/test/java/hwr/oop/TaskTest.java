package hwr.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TaskTest {

    Task taskVacuum;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;


    @BeforeEach
    void setUp() {
        LocalDate dueDate = LocalDate.parse("25.05.2022", formatter);
        LocalDate deadline = LocalDate.parse("27.05.2022", formatter);
        taskVacuum = new Task("vacuum", dueDate, deadline);
    }

    @Test
    void task_getTaskName_returnsGivenTaskName() {
        assertThat(taskVacuum.getTaskName()).isEqualTo("vacuum");
    }

    @Test
    void task_getDate_returnsGivenDate() {
        assertThat(taskVacuum.getDate()).isEqualTo("2022-05-25");
    }

    @Test
    void task_getDeadline_returnsGivenDeadline() {
        assertThat(taskVacuum.getDeadline()).isEqualTo("2022-05-27");
    }

    @Test
    void task_testThatReturnedDateIsOfLocalDateClass () {
        assertThat(taskVacuum.getDeadline()).isInstanceOf(LocalDate.class);
    }

    @Test
    void task_compareTo_comparesTaskWithGreaterTask_returnsNegativeInteger() {
        Task taskCall = new Task("call Mum", LocalDate.parse("06.06.2022", formatter), LocalDate.parse("07.06.2022", formatter));
        assertThat(taskVacuum.compareTo(taskCall)).isLessThan(0);
    }

    @Test
    void task_compareTo_comparesTaskWithTaskWithLesserDate_returnsPositiveInteger() {
        Task taskCall = new Task("call Mum", LocalDate.parse("24.04.2022", formatter), LocalDate.parse("07.06.2022", formatter));
        assertThat(taskVacuum.compareTo(taskCall)).isGreaterThan(0);
    }

    @Test
    void task_compareTo_comparesTwoTasksWithTheSameDate_returnsZero() {
        Task taskCall = new Task("call Mum", LocalDate.parse("25.05.2022", formatter), LocalDate.parse("07.06.2022", formatter));
        assertThat(taskVacuum.compareTo(taskCall)).isEqualTo(0);
    }
}
