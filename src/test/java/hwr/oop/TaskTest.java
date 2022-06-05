package hwr.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class TaskTest {

    Task task;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;


    @BeforeEach
    void setUp() {
        LocalDate dueDate = LocalDate.parse("25.05.2022", formatter);
        LocalDate deadline = LocalDate.parse("27.05.2022", formatter);
        task = new Task("vacuum", dueDate, deadline);
    }

    @Test
    void task_getTaskName_returnsGivenTaskName() {
        assertThat(task.getTaskName()).isEqualTo("vacuum");
    }

    @Test
    void task_getDate_returnsGivenDate() {
        assertThat(task.getDate()).isEqualTo("2022-05-25");
    }

    @Test
    void task_getDeadline_returnsGivenDeadline() {
        assertThat(task.getDeadline()).isEqualTo("2022-05-27");
    }

    @Test
    void task_testThatReturnedDateIsOfLocalDateClass () {
        assertThat(task.getDeadline()).isInstanceOf(LocalDate.class);
    }
}
