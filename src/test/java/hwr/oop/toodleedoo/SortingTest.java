package hwr.oop.toodleedoo;

import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SortingTest {
    Sorting sorting;
    TaskManager taskList;
    Task taskVacuum;
    Task taskCall;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDateVacuum;
    LocalDate deadlineVacuum;

    @BeforeEach
    void setUp() {
        sorting = new Sorting();
        taskList = new TaskManager();
        dueDateVacuum = LocalDate.parse("25.05.2022", formatter);
        deadlineVacuum = LocalDate.parse("27.05.2022", formatter);
        LocalDate dueDateCall = LocalDate.parse("26.06.2022", formatter);
        LocalDate deadlineCall = LocalDate.parse("01.07.2022", formatter);
        taskVacuum = new Task("vacuum", dueDateVacuum, deadlineVacuum);
        taskCall = new Task("call Mum", dueDateCall, deadlineCall);
        taskList.add(taskVacuum);
    }

}
