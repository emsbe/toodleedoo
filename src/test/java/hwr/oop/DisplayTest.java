package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisplayTest {
    TaskList taskList;
    Task taskVacuum;
    Display display;
    OutputStream outputStream;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    LocalDate dueDate;
    LocalDate deadline;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        LocalDate dueDate = LocalDate.parse("25.05.2022", formatter);
        LocalDate deadline = LocalDate.parse("27.05.2022", formatter);
        taskVacuum = new Task("vacuum", dueDate, deadline);
        display = new Display();
        outputStream = new ByteArrayOutputStream();
    }
    @Test
    void display_showAll_showAllTasksInTaskList() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", dueDate, deadline));
        display.showAllTasksIn(taskList);
        //String output = showAllTasksIn(outputStream);
        //assertThat(output).isEqualTo
        // TODO: Wie kann man das testen?

    }

    @Test
    void display_showView() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", LocalDate.parse("23.06.2022", formatter), LocalDate.parse("25.05.2022", formatter)));
        display.showViewOf(taskList, "deadline");
        // TODO: Wie kann man das testen?
    }

    @Test
    void display_showTaskAt() {
        taskList.add(taskVacuum);
        taskList.add(new Task("call Begüm", LocalDate.parse("23.06.2022", formatter), LocalDate.parse("25.05.2022", formatter)));
        display.showTaskAt(taskList,"23.06.2022");
        //TODO: richtig testen
    }
}
