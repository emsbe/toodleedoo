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

    @Test
    void task_compareTo_comparesTaskWithGreaterTask_returnsNegativeInteger() {
        Task otherTask = new Task("call Mum", LocalDate.parse("06.06.2022", formatter), LocalDate.parse("07.06.2022", formatter));
        assertThat(task.compareTo(otherTask)).isLessThan(0);
    }

    @Test
    void task_compareTo_comparesTaskWithTaskWithLesserDate_returnsPositiveInteger() {
        Task otherTask = new Task("call Mum", LocalDate.parse("24.04.2022", formatter), LocalDate.parse("07.06.2022", formatter));
        assertThat(task.compareTo(otherTask)).isGreaterThan(0);
    }

    @Test
    void task_compareTo_comparesTwoTasksWithTheSameDate_returnsZero() {
        Task otherTask = new Task("call Mum", LocalDate.parse("25.05.2022", formatter), LocalDate.parse("07.06.2022", formatter));
        assertThat(task.compareTo(otherTask)).isEqualTo(0);
    }
}
